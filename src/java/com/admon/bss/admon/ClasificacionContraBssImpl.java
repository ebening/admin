package com.admon.bss.admon;

import com.admon.bss.BaseBss;
import com.admon.dao.admon.ClasificacionContraDAO;
import com.admon.entity.admon.*;
import com.admon.model.admon.ClasificacionContraAction;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

public class ClasificacionContraBssImpl extends BaseBss implements ClasificacionContraBss {

    /* Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase. */
    private ClasificacionContraDAO clasificacionContraDAO;
    private ConfiguracionParametroBss configuracionParametroBss;
    private UsuarioBss usuarioBss;

    public ClasificacionContraBssImpl() {
    }

    /*
 * Método que guarda los objetos contenidos en la lista <b>objectList</b> en la tabla de <b>ClasificacionContra</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de objetos que se guardarán en la tabla de <b>ClasificacionContra</b> en la BD.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    @Override
    public List<Integer> save(Integer userId, List<ClasificacionContra> clasificacionContraList) {
        List<Integer> savedList = new ArrayList<Integer>();
        for (ClasificacionContra clasificacionContra : clasificacionContraList) {
            clasificacionContra.setEstatusId(ACTIVO);
            clasificacionContra.setEliminadoId(NOELIMINADO);
            clasificacionContra.setModificacionUsuario(userId);
            clasificacionContra.setCreacionUsuario(userId);
            clasificacionContra.setCreacionFecha(new Timestamp(new Date().getTime()));
            clasificacionContra.setModificacionFecha(new Timestamp(new Date().getTime()));
            savedList.add(clasificacionContraDAO.save(clasificacionContra));
        }
        return savedList;
    }

    /*
 * Método que actualiza la información de <b>clasificacionContra</b> en la tabla de <b>ClasificacionContra</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param clasificacionContraList Lista de registros que se actualizarán en la tabla de <b>ClasificacionContra</b> en la BD.
     */
    @Override
    public List<Integer> update(Integer userId, List<ClasificacionContra> clasificacionContraList) {
        List<Integer> idList = new ArrayList();
        for (ClasificacionContra clasificacionContra : clasificacionContraList) {
            clasificacionContra.setModificacionUsuario(userId);
            clasificacionContra.setModificacionFecha(new Timestamp(new Date().getTime()));
            clasificacionContraDAO.update(clasificacionContra);
            idList.add(clasificacionContra.getClasificacionContraId());
        }
        return idList;
    }

    /*
 * Método que elimina el registro <b>clasificacionContra</b> en la tabla de <b>ClasificacionContra</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param clasificacionContraList Lista de registros que se eliminarán en la tabla de <b>ClasificacionContra</b> en la BD.
     */
    @Override
    public void delete(Integer userId, List<Integer> clasificacionContraIdList) {
        for (Integer clasificacionContraId : clasificacionContraIdList) {
            ClasificacionContra clasificacionContra = findById(clasificacionContraId);
            clasificacionContra.setEliminadoId(ELIMINADO);
            clasificacionContra.setModificacionUsuario(userId);
            clasificacionContra.setModificacionFecha(new Timestamp(new Date().getTime()));
            clasificacionContraDAO.update(clasificacionContra);
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
 * Método que busca el registro <b>clasificacionContra</b> por su NOMBRE en la tabla de <b>ClasificacionContra</b>.
 * @param nombre es el NOMBRE del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>ClasificacionContra</b> con la información de la consulta.
     */
    @Override
    public ClasificacionContra findByName(String nombre) {
        return getFirst(findByCriteria(createDetachedCriteria()
                .add(Expression.eq("nombre", nombre.trim()))));
    }

    /*
 * Busca el registro <b>clasificacionContra</b> por su ID en la tabla de <b>ClasificacionContra</b>.
 * @param id es el ID del registro a obtener.
 * @return Regresa un objeto <b>ClasificacionContra</b> con la información del registro <b>clasificacionContra</b>.
     */
    @Override
    public ClasificacionContra findById(Integer clasificacionContraId) {
        ClasificacionContra clasificacionContra = clasificacionContraDAO.findById(clasificacionContraId);
        if (clasificacionContra == null) {
            return null;
        } else {
            return resolveDescription(Arrays.asList(clasificacionContra)).get(0);
        }
    }

    @Override
    public List<ClasificacionContra> findByIntProperty(String propertyName, Integer value) {
        return resolveDescription(findByCriteria(
                createDetachedCriteria().add(Expression.eq(propertyName, value))));
    }

    /*
 * Método que hace una consulta a la tabla ClasificacionContra y obtiene los registros que coincidan con el objeto <b>clasificacionContra</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
 * @param displayedPage Número de página que se desea mostrar al usuario en el widget JQGrid.
 * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
 * @param order Indica por que campo sera ordenada la búsqueda.
 * @param ordenTipo Tipo de ordenado acendente o decendente.
 * @param ClasificacionContra objeto que contiene los parámetros a buscar.
 * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    @Override
    public Grid findByCriteria(int displayedPage, int maxResult, String order,
            String ordenTipo, ClasificacionContra clasificacionContra) {
        Grid grid = new Grid();
        DetachedCriteria criteria = createDetachedCriteria();
        // Auxiliar para busqueda por rango de fechas campo: CreacionFecha
        if (clasificacionContra.getCreacionFechaInicial() != null) {
            criteria.add(Expression.ge("creacionFecha", getStartOfDay(clasificacionContra.getCreacionFechaInicial())));
        }
        if (clasificacionContra.getCreacionFechaFinal() != null) {
            criteria.add(Expression.le("creacionFecha", getEndOfDay(clasificacionContra.getCreacionFechaFinal())));
        }
        // Auxiliar para busqueda por rango de fechas campo: ModificacionFecha
        if (clasificacionContra.getModificacionFechaInicial() != null) {
            criteria.add(Expression.ge("modificacionFecha", getStartOfDay(clasificacionContra.getModificacionFechaInicial())));
        }
        if (clasificacionContra.getModificacionFechaFinal() != null) {
            criteria.add(Expression.le("modificacionFecha", getEndOfDay(clasificacionContra.getModificacionFechaFinal())));
        }
        criteria.add(Example.create(clasificacionContra));
        if (ordenTipo.equals("asc")) {
            criteria.addOrder(Order.asc(order));
        } else {
            criteria.addOrder(Order.desc(order));
        }
        int resultadosTotales = findSizeByCriteria(copy(criteria));
        List<ClasificacionContra> clasificacionContraList = findByCriteriaLimit(criteria,
                maxResult * (displayedPage - 1), maxResult);
        grid.setGrid(resolveDescription(clasificacionContraList));
        grid.setTotal(resultadosTotales);
        grid.setPaginas(lastPage(resultadosTotales, maxResult));
        return grid;
    }

    @Override
    public ClasificacionContra findFirst() {
        List<ClasificacionContra> clasificacionContraList = findByCriteria(createDetachedCriteria());
        if (clasificacionContraList.isEmpty()) {
            return null;
        } else {
            return clasificacionContraList.get(0);
        }
    }

    public List<ClasificacionContra> resolveDescription(List<ClasificacionContra> list) {
        if (!list.isEmpty()) {
            List<Usuario> usuarioList = usuarioBss.findAll();
            List<ConfiguracionParametro> estatusList = getParametros("key_estatus");
            List<ConfiguracionParametro> eliminadoList = getParametros("key_eliminado");
            for (ClasificacionContra clasificacionContra : list) {

                clasificacionContra.setUsuarioModificacion("");
                if (clasificacionContra.getModificacionUsuario() != null) {
                    for (Usuario o : usuarioList) {
                        if (o.getUsuarioId() == clasificacionContra.getModificacionUsuario()) {
                            clasificacionContra.setUsuarioModificacion(o.getUsuario());
                            break;
                        }
                    }
                }

                clasificacionContra.setEstatus("");
                if (clasificacionContra.getEstatusId() != null) {
                    for (ConfiguracionParametro o : estatusList) {
                        if (Integer.parseInt(o.getValor()) == clasificacionContra.getEstatusId()) {
                            clasificacionContra.setEstatus(o.getNombre());
                            break;
                        }
                    }
                }

                clasificacionContra.setEliminado("");
                if (clasificacionContra.getEliminadoId() != null) {
                    for (ConfiguracionParametro o : eliminadoList) {
                        if (Integer.parseInt(o.getValor()) == clasificacionContra.getEliminadoId()) {
                            clasificacionContra.setEliminado(o.getNombre());
                            break;
                        }
                    }
                }
            }
        }
        return list;
    }

    public Integer findSizeByCriteria(DetachedCriteria criteria) {
        return clasificacionContraDAO.findSizeByCriteria(generalizarCriteria(criteria));
    }

    public List<ClasificacionContra> findByCriteriaLimit(DetachedCriteria criteria, Integer from, Integer to) {
        return clasificacionContraDAO.findByCriteriaLimit(generalizarCriteria(criteria), from, to);
    }

    /*
 * Método que realiza un búsqueda en la BD obteniendo todos los registros que coincidan con los parámetros
 * definidos en el objeto <b>criteria</b>.
 *
 * @param criteria Es un objeto que contiene los parámetros de búsqueda.
 * @return Regresa una lista de objetos <b>ClasificacionContra</b> que coinciden con los parámetros definidos en
 * <b>criteria</b>.
     */
    @Override
    public List<ClasificacionContra> findByCriteria(DetachedCriteria criteria) {
        return clasificacionContraDAO.findByCriteria(generalizarCriteria(criteria));
    }

    @Override
    public List<ClasificacionContra> findByCriteriaIgnorePrivacy(ClasificacionContra clasificacionContra) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Example.create(clasificacionContra));
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        return clasificacionContraDAO.findByCriteria(criteria);
    }

    public DetachedCriteria generalizarCriteria(DetachedCriteria criteria) {
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        resolvePrivacy(criteria, ClasificacionContraAction.class);
        return criteria;
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>ClasificacionContra</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>ClasificacionContra</b>.
     */
    @Override
    public List<ClasificacionContra> findAll() {
        return findByCriteria(createDetachedCriteria().addOrder(Order.asc("nombre")));
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>ClasificacionContra</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>ClasificacionContra</b>.
     */
    @Override
    public List<ClasificacionContra> findActive() {
        return findByCriteria(createDetachedCriteria().add(
                Expression.eq("estatusId", ACTIVO)).addOrder(Order.asc("orden")));
    }

    /*
 * Método que obtiene un objeto DetachedCriteria.
 *
 * @return Regresa un objeto DetachedCriteria.
     */
    @Override
    public DetachedCriteria createDetachedCriteria() {
        return clasificacionContraDAO.createDetachedCriteria();
    }

    /*
 * Método que actualiza el Estatus de los ID' contenidos en la lista
 * <b>clasificacionContraList</b> en la tabla de <b>ClasificacionContra</b>.
 * @param userId userId Es el ID del usuario que realizó la operación.
 * @param estatusId Es el ID del estatus al cual se quiere cambiar.
 * @param clasificacionContraList Es una lista que contiene los registros los cuales se
 * quiere actualizar su Estatus.
     */
    @Override
    public void setEstatus(Integer userId, Integer estatusId, List<Integer> clasificacionContraList) {
        for (Integer clasificacionContraId : clasificacionContraList) {
            ClasificacionContra clasificacionContra = findById(clasificacionContraId);
            if (estatusId == 1) {
                clasificacionContra.setEstatusId(ACTIVO);
            } else {
                clasificacionContra.setEstatusId(INACTIVO);
            }
            update(userId, Arrays.asList(clasificacionContra));
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
    public String getReportDataTest(String order, String ordenTipo, ClasificacionContra clasificacionContra) {
        Grid grid = findByCriteria(1, 10000, order, ordenTipo, clasificacionContra);
        return addReportDataToSession(grid.getGrid());
    }

    /*
 * Metodos que actualizan los campos <select> en el jsp despues de haber hecho
 * una edición de datos en un popup externo.
     */
    @Override
    public String hasGrid() {
        return hasGrid(ClasificacionContraAction.class).toString();
    }

    @Override
    public String isIndividual() {
        return isIndividual(ClasificacionContraAction.class).toString();
    }

    @Override
    public String getNombreActionMenu() {
        Pagina pagina = findPageByActionClass(ClasificacionContraAction.class);
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
    public void setClasificacionContraDAO(ClasificacionContraDAO clasificacionContraDAO) {
        this.clasificacionContraDAO = clasificacionContraDAO;
    }

    public void setConfiguracionParametroBss(ConfiguracionParametroBss configuracionParametroBss) {
        this.configuracionParametroBss = configuracionParametroBss;
    }

    public void setUsuarioBss(UsuarioBss usuarioBss) {
        this.usuarioBss = usuarioBss;
    }
}
