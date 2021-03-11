package com.admon.bss.admon;

import com.admon.bss.BaseBss;
import com.admon.dao.admon.PaisDAO;
import com.admon.entity.admon.*;
import com.admon.model.admon.PaisAction;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

public class PaisBssImpl extends BaseBss implements PaisBss {

    /* Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase. */
    private PaisDAO paisDAO;
    private ConfiguracionParametroBss configuracionParametroBss;
    private UsuarioBss usuarioBss;

    public PaisBssImpl() {
    }

    /*
 * Método que guarda los objetos contenidos en la lista <b>objectList</b> en la tabla de <b>Pais</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de objetos que se guardarán en la tabla de <b>Pais</b> en la BD.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    @Override
    public List<Integer> save(Integer userId, List<Pais> paisList) {
        List<Integer> savedList = new ArrayList<Integer>();
        for (Pais pais : paisList) {
            pais.setEstatusId(ACTIVO);
            pais.setEliminadoId(NOELIMINADO);
            pais.setModificacionUsuario(userId);
            pais.setCreacionUsuario(userId);
            pais.setCreacionFecha(new Timestamp(new Date().getTime()));
            pais.setModificacionFecha(new Timestamp(new Date().getTime()));
            savedList.add(paisDAO.save(pais));
        }
        return savedList;
    }

    /*
 * Método que actualiza la información de <b>pais</b> en la tabla de <b>Pais</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param paisList Lista de registros que se actualizarán en la tabla de <b>Pais</b> en la BD.
     */
    @Override
    public List<Integer> update(Integer userId, List<Pais> paisList) {
        List<Integer> idList = new ArrayList();
        for (Pais pais : paisList) {
            pais.setModificacionUsuario(userId);
            pais.setModificacionFecha(new Timestamp(new Date().getTime()));
            paisDAO.update(pais);
            idList.add(pais.getPaisId());
        }
        return idList;
    }

    /*
 * Método que elimina el registro <b>pais</b> en la tabla de <b>Pais</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param paisList Lista de registros que se eliminarán en la tabla de <b>Pais</b> en la BD.
     */
    @Override
    public void delete(Integer userId, List<Integer> paisIdList) {
        for (Integer paisId : paisIdList) {
            Pais pais = findById(paisId);
            pais.setEliminadoId(ELIMINADO);
            pais.setModificacionUsuario(userId);
            pais.setModificacionFecha(new Timestamp(new Date().getTime()));
            paisDAO.update(pais);
        }
    }

    /*
 * Método que evalúa si existe al menos un registro con un determinado
 * nombre en la tabla.
 * @param nombre Es el nombre que se desea buscar en la tabla de la BD.
 * @return Regresa <b>false</b> si existe al menos un registro con el
 * nombre específicado como parámetro en el método, si no existe ningún
 * registro con ese nombre regresa un <b>true</b>.
     */
    @Override
    public boolean isValidoNombre(String nombre) {
        return !(findByCriteria(createDetachedCriteria().add(
                Expression.eq("nombre", nombre.trim()))).size() >= 1);
    }

    /*
 * Método que busca el registro <b>pais</b> por su NOMBRE en la tabla de <b>Pais</b>.
 * @param nombre es el NOMBRE del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>Pais</b> con la información de la consulta.
     */
    @Override
    public Pais findByName(String nombre) {
        return getFirst(findByCriteria(createDetachedCriteria()
                .add(Expression.eq("nombre", nombre.trim()))));
    }

    /*
 * Busca el registro <b>pais</b> por su ID en la tabla de <b>Pais</b>.
 * @param id es el ID del registro a obtener.
 * @return Regresa un objeto <b>Pais</b> con la información del registro <b>pais</b>.
     */
    @Override
    public Pais findById(Integer paisId) {
        Pais pais = paisDAO.findById(paisId);
        if (pais == null) {
            return null;
        } else {
            return resolveDescription(Arrays.asList(pais)).get(0);
        }
    }

    @Override
    public List<Pais> findByIntProperty(String propertyName, Integer value) {
        return resolveDescription(findByCriteria(
                createDetachedCriteria().add(Expression.eq(propertyName, value))));
    }

    /*
 * Método que hace una consulta a la tabla Pais y obtiene los registros que coincidan con el objeto <b>pais</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
 * @param displayedPage Número de página que se desea mostrar al usuario en el widget JQGrid.
 * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
 * @param order Indica por que campo sera ordenada la búsqueda.
 * @param ordenTipo Tipo de ordenado acendente o decendente.
 * @param Pais objeto que contiene los parámetros a buscar.
 * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    @Override
    public Grid findByCriteria(int displayedPage, int maxResult, String order,
            String ordenTipo, Pais pais) {
        Grid grid = new Grid();
        DetachedCriteria criteria = createDetachedCriteria();
        // Auxiliar para busqueda por rango de fechas campo: CreacionFecha
        if (pais.getCreacionFechaInicial() != null) {
            criteria.add(Expression.ge("creacionFecha", getStartOfDay(pais.getCreacionFechaInicial())));
        }
        if (pais.getCreacionFechaFinal() != null) {
            criteria.add(Expression.le("creacionFecha", getEndOfDay(pais.getCreacionFechaFinal())));
        }
        // Auxiliar para busqueda por rango de fechas campo: ModificacionFecha
        if (pais.getModificacionFechaInicial() != null) {
            criteria.add(Expression.ge("modificacionFecha", getStartOfDay(pais.getModificacionFechaInicial())));
        }
        if (pais.getModificacionFechaFinal() != null) {
            criteria.add(Expression.le("modificacionFecha", getEndOfDay(pais.getModificacionFechaFinal())));
        }
        criteria.add(Example.create(pais));
        if (ordenTipo.equals("asc")) {
            criteria.addOrder(Order.asc(order));
        } else {
            criteria.addOrder(Order.desc(order));
        }
        int resultadosTotales = findSizeByCriteria(copy(criteria));
        List<Pais> paisList = findByCriteriaLimit(criteria,
                maxResult * (displayedPage - 1), maxResult);
        grid.setGrid(resolveDescription(paisList));
        grid.setTotal(resultadosTotales);
        grid.setPaginas(lastPage(resultadosTotales, maxResult));
        return grid;
    }

    @Override
    public Pais findFirst() {
        List<Pais> paisList = findByCriteria(createDetachedCriteria());
        if (paisList.isEmpty()) {
            return null;
        } else {
            return paisList.get(0);
        }
    }

    public List<Pais> resolveDescription(List<Pais> list) {
        if (!list.isEmpty()) {
            List<Usuario> usuarioList = usuarioBss.findAll();
            List<ConfiguracionParametro> estatusList = getParametros("key_estatus");
            List<ConfiguracionParametro> eliminadoList = getParametros("key_eliminado");
            for (Pais pais : list) {

                pais.setUsuarioModificacion("");
                if (pais.getModificacionUsuario() != null) {
                    for (Usuario o : usuarioList) {
                        if (o.getUsuarioId() == pais.getModificacionUsuario()) {
                            pais.setUsuarioModificacion(o.getUsuario());
                            break;
                        }
                    }
                }

                pais.setEstatus("");
                if (pais.getEstatusId() != null) {
                    for (ConfiguracionParametro o : estatusList) {
                        if (Integer.parseInt(o.getValor()) == pais.getEstatusId()) {
                            pais.setEstatus(o.getNombre());
                            break;
                        }
                    }
                }

                pais.setEliminado("");
                if (pais.getEliminadoId() != null) {
                    for (ConfiguracionParametro o : eliminadoList) {
                        if (Integer.parseInt(o.getValor()) == pais.getEliminadoId()) {
                            pais.setEliminado(o.getNombre());
                            break;
                        }
                    }
                }
            }
        }
        return list;
    }

    public Integer findSizeByCriteria(DetachedCriteria criteria) {
        return paisDAO.findSizeByCriteria(generalizarCriteria(criteria));
    }

    public List<Pais> findByCriteriaLimit(DetachedCriteria criteria, Integer from, Integer to) {
        return paisDAO.findByCriteriaLimit(generalizarCriteria(criteria), from, to);
    }

    /*
 * Método que realiza un búsqueda en la BD obteniendo todos los registros que coincidan con los parámetros
 * definidos en el objeto <b>criteria</b>.
 *
 * @param criteria Es un objeto que contiene los parámetros de búsqueda.
 * @return Regresa una lista de objetos <b>Pais</b> que coinciden con los parámetros definidos en
 * <b>criteria</b>.
     */
    @Override
    public List<Pais> findByCriteria(DetachedCriteria criteria) {
        return paisDAO.findByCriteria(generalizarCriteria(criteria));
    }

    @Override
    public List<Pais> findByCriteriaIgnorePrivacy(Pais pais) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Example.create(pais));
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        return paisDAO.findByCriteria(criteria);
    }

    public DetachedCriteria generalizarCriteria(DetachedCriteria criteria) {
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        resolvePrivacy(criteria, PaisAction.class);
        return criteria;
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>Pais</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>Pais</b>.
     */
    @Override
    public List<Pais> findAll() {
        return findByCriteria(createDetachedCriteria().addOrder(Order.asc("nombre")));
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>Pais</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>Pais</b>.
     */
    @Override
    public List<Pais> findActive() {
        return findByCriteria(createDetachedCriteria().add(
                Expression.eq("estatusId", ACTIVO)).addOrder(Order.asc("nombre")));
    }

    /*
 * Método que obtiene un objeto DetachedCriteria.
 *
 * @return Regresa un objeto DetachedCriteria.
     */
    @Override
    public DetachedCriteria createDetachedCriteria() {
        return paisDAO.createDetachedCriteria();
    }

    /*
 * Método que actualiza el Estatus de los ID' contenidos en la lista
 * <b>paisList</b> en la tabla de <b>Pais</b>.
 * @param userId userId Es el ID del usuario que realizó la operación.
 * @param estatusId Es el ID del estatus al cual se quiere cambiar.
 * @param paisList Es una lista que contiene los registros los cuales se
 * quiere actualizar su Estatus.
     */
    @Override
    public void setEstatus(Integer userId, Integer estatusId, List<Integer> paisList) {
        for (Integer paisId : paisList) {
            Pais pais = findById(paisId);
            if (estatusId == 1) {
                pais.setEstatusId(ACTIVO);
            } else {
                pais.setEstatusId(INACTIVO);
            }
            update(userId, Arrays.asList(pais));
        }
    }

    /*
 * Método que obtiene los parámetros que le corresponden
 * a un determinado catálogo. Éste método es llamado por los Action
 * para obtener los parámetros del catálogo
 * @param catalogKey Es el código del catálogo del cual se quieren obtener los parámetros.
 * Los códigos se encuentran en el paquete <b>com.admon.bss.resources</b> 
 * en el archivo <b>CodesBss.properties</b>.
 * @return Regresa una lista con los parámetros del catálogo.
     */
    @Override
    public List<ConfiguracionParametro> getParametros(String catalogKey) {
        return addDummy(configuracionParametroBss.getParametros(catalogKey), new ConfiguracionParametro(), ConfiguracionParametro.class);
    }

    /*
 * Método que hace una consulta a la tabla Usuario y obtiene todos sus
 * registros correctamente filtrados con un objeto DetachedCriteria para ser
 * utilizados en los reportes de los grid (PDF y Excel).
 *
 * @param order Indica por que campo sera ordenada la búsqueda.
 * @param ordenTipo Tipo de ordenado acendente o decendente.
 * @return Regresa una lista de objetos <b>Usuario</b> con los datos a
 * mostrar en el reporte.
     */
    @Override
    public String getReportDataTest(String order, String ordenTipo, Pais pais) {
        Grid grid = findByCriteria(1, 10000, order, ordenTipo, pais);
        return addReportDataToSession(grid.getGrid());
    }

    /*
 * Metodos que actualizan los campos <select> en el jsp despues de haber hecho
 * una edición de datos en un popup externo.
     */

    @Override
    public String hasGrid() {
        return hasGrid(PaisAction.class).toString();
    }

    @Override
    public String isIndividual() {
        return isIndividual(PaisAction.class).toString();
    }

    @Override
    public String getNombreActionMenu() {
        Pagina pagina = findPageByActionClass(PaisAction.class);
        if (pagina == null) {
            return "";
        } else {
            return pagina.getNombre();
        }
    }

    /*
 * Inyección de dependencias con Spring, cada
 * referencia definida al inicio de la clase requiere un método
 * Setter.
     */
    public void setPaisDAO(PaisDAO paisDAO) {
        this.paisDAO = paisDAO;
    }

    public void setConfiguracionParametroBss(ConfiguracionParametroBss configuracionParametroBss) {
        this.configuracionParametroBss = configuracionParametroBss;
    }

    public void setUsuarioBss(UsuarioBss usuarioBss) {
        this.usuarioBss = usuarioBss;
    }
}
