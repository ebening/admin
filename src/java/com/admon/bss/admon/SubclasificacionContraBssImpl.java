package com.admon.bss.admon;

import com.admon.bss.BaseBss;
import com.admon.dao.admon.SubclasificacionContraDAO;
import com.admon.entity.admon.*;
import com.admon.model.admon.SubclasificacionContraAction;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

public class SubclasificacionContraBssImpl extends BaseBss implements SubclasificacionContraBss {

    /* Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase. */
    private SubclasificacionContraDAO subclasificacionContraDAO;
    private ConfiguracionParametroBss configuracionParametroBss;
    private UsuarioBss usuarioBss;
    private ClasificacionContraBss clasificacionContraBss;

    public SubclasificacionContraBssImpl() {
    }

    /*
 * Método que guarda los objetos contenidos en la lista <b>objectList</b> en la tabla de <b>SubclasificacionContra</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de objetos que se guardarán en la tabla de <b>SubclasificacionContra</b> en la BD.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    @Override
    public List<Integer> save(Integer userId, List<SubclasificacionContra> subclasificacionContraList) {
        List<Integer> savedList = new ArrayList<Integer>();
        for (SubclasificacionContra subclasificacionContra : subclasificacionContraList) {
            subclasificacionContra.setEstatusId(ACTIVO);
            subclasificacionContra.setEliminadoId(NOELIMINADO);
            subclasificacionContra.setModificacionUsuario(userId);
            subclasificacionContra.setCreacionUsuario(userId);
            subclasificacionContra.setCreacionFecha(new Timestamp(new Date().getTime()));
            subclasificacionContra.setModificacionFecha(new Timestamp(new Date().getTime()));
            savedList.add(subclasificacionContraDAO.save(subclasificacionContra));
        }
        return savedList;
    }

    /*
 * Método que actualiza la información de <b>subclasificacionContra</b> en la tabla de <b>SubclasificacionContra</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param subclasificacionContraList Lista de registros que se actualizarán en la tabla de <b>SubclasificacionContra</b> en la BD.
     */
    @Override
    public List<Integer> update(Integer userId, List<SubclasificacionContra> subclasificacionContraList) {
        List<Integer> idList = new ArrayList();
        for (SubclasificacionContra subclasificacionContra : subclasificacionContraList) {
            subclasificacionContra.setModificacionUsuario(userId);
            subclasificacionContra.setModificacionFecha(new Timestamp(new Date().getTime()));
            subclasificacionContraDAO.update(subclasificacionContra);
            idList.add(subclasificacionContra.getSubclasificacionContraId());
        }
        return idList;
    }

    /*
 * Método que elimina el registro <b>subclasificacionContra</b> en la tabla de <b>SubclasificacionContra</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param subclasificacionContraList Lista de registros que se eliminarán en la tabla de <b>SubclasificacionContra</b> en la BD.
     */
    @Override
    public void delete(Integer userId, List<Integer> subclasificacionContraIdList) {
        for (Integer subclasificacionContraId : subclasificacionContraIdList) {
            SubclasificacionContra subclasificacionContra = findById(subclasificacionContraId);
            subclasificacionContra.setEliminadoId(ELIMINADO);
            subclasificacionContra.setModificacionUsuario(userId);
            subclasificacionContra.setModificacionFecha(new Timestamp(new Date().getTime()));
            subclasificacionContraDAO.update(subclasificacionContra);
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
 * Método que busca el registro <b>subclasificacionContra</b> por su NOMBRE en la tabla de <b>SubclasificacionContra</b>.
 * @param nombre es el NOMBRE del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>SubclasificacionContra</b> con la información de la consulta.
     */
    @Override
    public SubclasificacionContra findByName(String nombre) {
        return getFirst(findByCriteria(createDetachedCriteria()
                .add(Expression.eq("nombre", nombre.trim()))));
    }

    /*
 * Busca el registro <b>subclasificacionContra</b> por su ID en la tabla de <b>SubclasificacionContra</b>.
 * @param id es el ID del registro a obtener.
 * @return Regresa un objeto <b>SubclasificacionContra</b> con la información del registro <b>subclasificacionContra</b>.
     */
    @Override
    public SubclasificacionContra findById(Integer subclasificacionContraId) {
        SubclasificacionContra subclasificacionContra = subclasificacionContraDAO.findById(subclasificacionContraId);
        if (subclasificacionContra == null) {
            return null;
        } else {
            return resolveDescription(Arrays.asList(subclasificacionContra)).get(0);
        }
    }

    @Override
    public List<SubclasificacionContra> findByIntProperty(String propertyName, Integer value) {
        return resolveDescription(findByCriteria(
                createDetachedCriteria().add(Expression.eq(propertyName, value))));
    }

    /*
 * Método que hace una consulta a la tabla SubclasificacionContra y obtiene los registros que coincidan con el objeto <b>subclasificacionContra</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
 * @param displayedPage Número de página que se desea mostrar al usuario en el widget JQGrid.
 * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
 * @param order Indica por que campo sera ordenada la búsqueda.
 * @param ordenTipo Tipo de ordenado acendente o decendente.
 * @param SubclasificacionContra objeto que contiene los parámetros a buscar.
 * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    @Override
    public Grid findByCriteria(int displayedPage, int maxResult, String order,
            String ordenTipo, SubclasificacionContra subclasificacionContra) {
        Grid grid = new Grid();
        DetachedCriteria criteria = createDetachedCriteria();
        // Auxiliar para busqueda por rango de fechas campo: CreacionFecha
        if (subclasificacionContra.getCreacionFechaInicial() != null) {
            criteria.add(Expression.ge("creacionFecha", getStartOfDay(subclasificacionContra.getCreacionFechaInicial())));
        }
        if (subclasificacionContra.getCreacionFechaFinal() != null) {
            criteria.add(Expression.le("creacionFecha", getEndOfDay(subclasificacionContra.getCreacionFechaFinal())));
        }
        // Auxiliar para busqueda por rango de fechas campo: ModificacionFecha
        if (subclasificacionContra.getModificacionFechaInicial() != null) {
            criteria.add(Expression.ge("modificacionFecha", getStartOfDay(subclasificacionContra.getModificacionFechaInicial())));
        }
        if (subclasificacionContra.getModificacionFechaFinal() != null) {
            criteria.add(Expression.le("modificacionFecha", getEndOfDay(subclasificacionContra.getModificacionFechaFinal())));
        }
        criteria.add(Example.create(subclasificacionContra));
        if (ordenTipo.equals("asc")) {
            criteria.addOrder(Order.asc(order));
        } else {
            criteria.addOrder(Order.desc(order));
        }
        int resultadosTotales = findSizeByCriteria(copy(criteria));
        List<SubclasificacionContra> subclasificacionContraList = findByCriteriaLimit(criteria,
                maxResult * (displayedPage - 1), maxResult);
        grid.setGrid(resolveDescription(subclasificacionContraList));
        grid.setTotal(resultadosTotales);
        grid.setPaginas(lastPage(resultadosTotales, maxResult));
        return grid;
    }

    @Override
    public SubclasificacionContra findFirst() {
        List<SubclasificacionContra> subclasificacionContraList = findByCriteria(createDetachedCriteria());
        if (subclasificacionContraList.isEmpty()) {
            return null;
        } else {
            return subclasificacionContraList.get(0);
        }
    }

    public List<SubclasificacionContra> resolveDescription(List<SubclasificacionContra> list) {
        if (!list.isEmpty()) {
            List<Usuario> usuarioList = usuarioBss.findAll();
            List<ClasificacionContra> clasificacionContraList = clasificacionContraBss.findAll();
            List<ConfiguracionParametro> estatusList = getParametros("key_estatus");
            List<ConfiguracionParametro> eliminadoList = getParametros("key_eliminado");
            for (SubclasificacionContra subclasificacionContra : list) {

                subclasificacionContra.setUsuarioModificacion("");
                if (subclasificacionContra.getModificacionUsuario() != null) {
                    for (Usuario o : usuarioList) {
                        if (o.getUsuarioId() == subclasificacionContra.getModificacionUsuario()) {
                            subclasificacionContra.setUsuarioModificacion(o.getUsuario());
                            break;
                        }
                    }
                }

                subclasificacionContra.setClasificacionContra("");
                if (subclasificacionContra.getClasificacionContraId() != null) {
                    for (ClasificacionContra o : clasificacionContraList) {
                        if (o.getClasificacionContraId() == subclasificacionContra.getClasificacionContraId()) {
                            subclasificacionContra.setClasificacionContra(o.getNombre());
                            break;
                        }
                    }
                }

                subclasificacionContra.setEstatus("");
                if (subclasificacionContra.getEstatusId() != null) {
                    for (ConfiguracionParametro o : estatusList) {
                        if (Integer.parseInt(o.getValor()) == subclasificacionContra.getEstatusId()) {
                            subclasificacionContra.setEstatus(o.getNombre());
                            break;
                        }
                    }
                }

                subclasificacionContra.setEliminado("");
                if (subclasificacionContra.getEliminadoId() != null) {
                    for (ConfiguracionParametro o : eliminadoList) {
                        if (Integer.parseInt(o.getValor()) == subclasificacionContra.getEliminadoId()) {
                            subclasificacionContra.setEliminado(o.getNombre());
                            break;
                        }
                    }
                }
            }
        }
        return list;
    }

    public Integer findSizeByCriteria(DetachedCriteria criteria) {
        return subclasificacionContraDAO.findSizeByCriteria(generalizarCriteria(criteria));
    }

    public List<SubclasificacionContra> findByCriteriaLimit(DetachedCriteria criteria, Integer from, Integer to) {
        return subclasificacionContraDAO.findByCriteriaLimit(generalizarCriteria(criteria), from, to);
    }

    /*
 * Método que realiza un búsqueda en la BD obteniendo todos los registros que coincidan con los parámetros
 * definidos en el objeto <b>criteria</b>.
 *
 * @param criteria Es un objeto que contiene los parámetros de búsqueda.
 * @return Regresa una lista de objetos <b>SubclasificacionContra</b> que coinciden con los parámetros definidos en
 * <b>criteria</b>.
     */
    @Override
    public List<SubclasificacionContra> findByCriteria(DetachedCriteria criteria) {
        return subclasificacionContraDAO.findByCriteria(generalizarCriteria(criteria));
    }

    @Override
    public List<SubclasificacionContra> findByCriteriaIgnorePrivacy(SubclasificacionContra subclasificacionContra) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Example.create(subclasificacionContra));
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        return subclasificacionContraDAO.findByCriteria(criteria);
    }

    public DetachedCriteria generalizarCriteria(DetachedCriteria criteria) {
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        resolvePrivacy(criteria, SubclasificacionContraAction.class);
        return criteria;
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>SubclasificacionContra</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>SubclasificacionContra</b>.
     */
    @Override
    public List<SubclasificacionContra> findAll() {
        return findByCriteria(createDetachedCriteria().addOrder(Order.asc("nombre")));
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>SubclasificacionContra</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>SubclasificacionContra</b>.
     */
    @Override
    public List<SubclasificacionContra> findActive() {
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
        return subclasificacionContraDAO.createDetachedCriteria();
    }

    /*
 * Método que actualiza el Estatus de los ID' contenidos en la lista
 * <b>subclasificacionContraList</b> en la tabla de <b>SubclasificacionContra</b>.
 * @param userId userId Es el ID del usuario que realizó la operación.
 * @param estatusId Es el ID del estatus al cual se quiere cambiar.
 * @param subclasificacionContraList Es una lista que contiene los registros los cuales se
 * quiere actualizar su Estatus.
     */
    @Override
    public void setEstatus(Integer userId, Integer estatusId, List<Integer> subclasificacionContraList) {
        for (Integer subclasificacionContraId : subclasificacionContraList) {
            SubclasificacionContra subclasificacionContra = findById(subclasificacionContraId);
            if (estatusId == 1) {
                subclasificacionContra.setEstatusId(ACTIVO);
            } else {
                subclasificacionContra.setEstatusId(INACTIVO);
            }
            update(userId, Arrays.asList(subclasificacionContra));
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
    public String getReportDataTest(String order, String ordenTipo, SubclasificacionContra subclasificacionContra) {
        Grid grid = findByCriteria(1, 10000, order, ordenTipo, subclasificacionContra);
        return addReportDataToSession(grid.getGrid());
    }

    /*
 * Metodos que actualizan los campos <select> en el jsp despues de haber hecho
 * una edición de datos en un popup externo.
     */
    @Override
    public List<ClasificacionContra> getClasificacionContra() {
        return addDummy(clasificacionContraBss.findActive(), new ClasificacionContra(), ClasificacionContra.class);
    }

    @Override
    public String hasGrid() {
        return hasGrid(SubclasificacionContraAction.class).toString();
    }

    @Override
    public String isIndividual() {
        return isIndividual(SubclasificacionContraAction.class).toString();
    }

    @Override
    public String getNombreActionMenu() {
        Pagina pagina = findPageByActionClass(SubclasificacionContraAction.class);
        if (pagina == null) {
            return "";
        } else {
            return pagina.getNombre();
        }
    }

    // Selects encadenados metodos
    @Override
    public List<SubclasificacionContra> filtrarSubclasificacionContra(int clasificacionContraId) {
        return addDummy(findByCriteria(createDetachedCriteria().add(Expression.eq("clasificacionContraId", clasificacionContraId))
        ), new SubclasificacionContra(), SubclasificacionContra.class);
    }

    /*
 * Inyección de dependencias con Spring, cada
 * referencia definida al inicio de la clase requiere un método
 * Setter.
     */
    public void setSubclasificacionContraDAO(SubclasificacionContraDAO subclasificacionContraDAO) {
        this.subclasificacionContraDAO = subclasificacionContraDAO;
    }

    public void setConfiguracionParametroBss(ConfiguracionParametroBss configuracionParametroBss) {
        this.configuracionParametroBss = configuracionParametroBss;
    }

    public void setUsuarioBss(UsuarioBss usuarioBss) {
        this.usuarioBss = usuarioBss;
    }

    public void setClasificacionContraBss(ClasificacionContraBss clasificacionContraBss) {
        this.clasificacionContraBss = clasificacionContraBss;
    }

}
