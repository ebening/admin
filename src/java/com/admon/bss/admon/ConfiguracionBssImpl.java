package com.admon.bss.admon;

import com.admon.bss.BaseBss;
import com.admon.dao.admon.ConfiguracionDAO;
import com.admon.entity.admon.*;
import com.admon.model.admon.ConfiguracionAction;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

public class ConfiguracionBssImpl extends BaseBss implements ConfiguracionBss {

    /* Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase. */
    private ConfiguracionDAO configuracionDAO;
    private UsuarioBss usuarioBss;
    // Elementos de struts2 para el JSP (Embebido)
    private ConfiguracionParametroBss configuracionParametroBss;

    public ConfiguracionBssImpl() {
    }

    /*
 * Método que guarda los objetos contenidos en la lista <b>objectList</b> en la tabla de <b>Configuracion</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de objetos que se guardarán en la tabla de <b>Configuracion</b> en la BD.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    @Override
    public List<Integer> save(Integer userId, List<Configuracion> configuracionList) {
        List<Integer> savedList = new ArrayList<Integer>();
        for (Configuracion configuracion : configuracionList) {
            configuracion.setEstatusId(ACTIVO);
            configuracion.setEliminadoId(NOELIMINADO);
            configuracion.setModificacionUsuario(userId);
            configuracion.setCreacionUsuario(userId);
            configuracion.setCreacionFecha(new Timestamp(new Date().getTime()));
            configuracion.setModificacionFecha(new Timestamp(new Date().getTime()));
            savedList.add(configuracionDAO.save(configuracion));
        }
        return savedList;
    }

    @Override
    public void saveConfiguracionParametro(int userId, int configuracionId,
            List<ConfiguracionParametro> detalleNuevoList) {
        List<ConfiguracionParametro> detalleActualList
                = configuracionParametroBss.findByIntProperty("configuracionId",
                        configuracionId);
        // Delete
        for (ConfiguracionParametro detalle : detalleActualList) {
            boolean isDeletable = true;
            for (ConfiguracionParametro detalleNuevo : detalleNuevoList) {
                if (detalle.getConfiguracionParametroId()
                        == detalleNuevo.getConfiguracionParametroId()) {
                    isDeletable = false;
                }
            }
            if (isDeletable) {
                configuracionParametroBss.delete(userId,
                        Arrays.asList(detalle.getConfiguracionParametroId()));
            }
        }
        // Save y update
        for (ConfiguracionParametro detalle : detalleNuevoList) {
            if (detalle.getConfiguracionParametroId() == 0) {
                configuracionParametroBss.save(userId, Arrays.asList(detalle));
            } else {
                configuracionParametroBss.update(userId, Arrays.asList(detalle));
            }
        }
    }

    /*
 * Método que actualiza la información de <b>configuracion</b> en la tabla de <b>Configuracion</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param configuracionList Lista de registros que se actualizarán en la tabla de <b>Configuracion</b> en la BD.
     */
    @Override
    public List<Integer> update(Integer userId, List<Configuracion> configuracionList) {
        List<Integer> idList = new ArrayList();
        for (Configuracion configuracion : configuracionList) {
            configuracion.setModificacionUsuario(userId);
            configuracion.setModificacionFecha(new Timestamp(new Date().getTime()));
            configuracionDAO.update(configuracion);
            idList.add(configuracion.getConfiguracionId());
        }
        return idList;
    }

    /*
 * Método que elimina el registro <b>configuracion</b> en la tabla de <b>Configuracion</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param configuracionList Lista de registros que se eliminarán en la tabla de <b>Configuracion</b> en la BD.
     */
    @Override
    public void delete(Integer userId, List<Integer> configuracionIdList) {
        for (Integer configuracionId : configuracionIdList) {
            Configuracion configuracion = findById(configuracionId);
            configuracion.setEliminadoId(ELIMINADO);
            configuracion.setModificacionUsuario(userId);
            configuracion.setModificacionFecha(new Timestamp(new Date().getTime()));
            configuracionDAO.update(configuracion);
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
 * Método que busca el registro <b>configuracion</b> por su NOMBRE en la tabla de <b>Configuracion</b>.
 * @param nombre es el NOMBRE del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>Configuracion</b> con la información de la consulta.
     */
    @Override
    public Configuracion findByName(String nombre) {
        return getFirst(findByCriteria(createDetachedCriteria()
                .add(Expression.eq("nombre", nombre.trim()))));
    }

    /*
 * Busca el registro <b>configuracion</b> por su ID en la tabla de <b>Configuracion</b>.
 * @param id es el ID del registro a obtener.
 * @return Regresa un objeto <b>Configuracion</b> con la información del registro <b>configuracion</b>.
     */
    @Override
    public Configuracion findById(Integer configuracionId) {
        Configuracion configuracion = configuracionDAO.findById(configuracionId);
        if (configuracion == null) {
            return null;
        } else {
            return resolveDescription(Arrays.asList(configuracion)).get(0);
        }
    }

    @Override
    public List<Configuracion> findByIntProperty(String propertyName, Integer value) {
        return resolveDescription(findByCriteria(
                createDetachedCriteria().add(Expression.eq(propertyName, value))));
    }

    @Override
    public List<ConfiguracionParametro> findConfiguracionParametroByIntProperty(String propertyName, Integer value) {
        return configuracionParametroBss.findByIntProperty(propertyName, value);
    }

    /*
 * Método que hace una consulta a la tabla Configuracion y obtiene los registros que coincidan con el objeto <b>configuracion</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
 * @param displayedPage Número de página que se desea mostrar al usuario en el widget JQGrid.
 * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
 * @param order Indica por que campo sera ordenada la búsqueda.
 * @param ordenTipo Tipo de ordenado acendente o decendente.
 * @param Configuracion objeto que contiene los parámetros a buscar.
 * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    @Override
    public Grid findByCriteria(int displayedPage, int maxResult, String order,
            String ordenTipo, Configuracion configuracion) {
        Grid grid = new Grid();
        DetachedCriteria criteria = createDetachedCriteria();
        // Auxiliar para busqueda por rango de fechas campo: CreacionFecha
        if (configuracion.getCreacionFechaInicial() != null) {
            criteria.add(Expression.ge("creacionFecha", getStartOfDay(configuracion.getCreacionFechaInicial())));
        }
        if (configuracion.getCreacionFechaFinal() != null) {
            criteria.add(Expression.le("creacionFecha", getEndOfDay(configuracion.getCreacionFechaFinal())));
        }
        // Auxiliar para busqueda por rango de fechas campo: ModificacionFecha
        if (configuracion.getModificacionFechaInicial() != null) {
            criteria.add(Expression.ge("modificacionFecha", getStartOfDay(configuracion.getModificacionFechaInicial())));
        }
        if (configuracion.getModificacionFechaFinal() != null) {
            criteria.add(Expression.le("modificacionFecha", getEndOfDay(configuracion.getModificacionFechaFinal())));
        }
        criteria.add(Example.create(configuracion));
        if (ordenTipo.equals("asc")) {
            criteria.addOrder(Order.asc(order));
        } else {
            criteria.addOrder(Order.desc(order));
        }
        int resultadosTotales = findSizeByCriteria(copy(criteria));
        List<Configuracion> configuracionList = findByCriteriaLimit(criteria,
                maxResult * (displayedPage - 1), maxResult);
        grid.setGrid(resolveDescription(configuracionList));
        grid.setTotal(resultadosTotales);
        grid.setPaginas(lastPage(resultadosTotales, maxResult));
        return grid;
    }

    @Override
    public Configuracion findFirst() {
        List<Configuracion> configuracionList = findByCriteria(createDetachedCriteria());
        if (configuracionList.isEmpty()) {
            return null;
        } else {
            return configuracionList.get(0);
        }
    }

    public List<Configuracion> resolveDescription(List<Configuracion> list) {
        if (!list.isEmpty()) {
            List<Usuario> usuarioList = usuarioBss.findAll();
            List<ConfiguracionParametro> estatusList = getParametros("key_estatus");
            List<ConfiguracionParametro> eliminadoList = getParametros("key_eliminado");
            for (Configuracion configuracion : list) {

                configuracion.setUsuarioModificacion("");
                if (configuracion.getModificacionUsuario() != null) {
                    for (Usuario o : usuarioList) {
                        if (o.getUsuarioId() == configuracion.getModificacionUsuario()) {
                            configuracion.setUsuarioModificacion(o.getUsuario());
                            break;
                        }
                    }
                }

                configuracion.setEstatus("");
                if (configuracion.getEstatusId() != null) {
                    for (ConfiguracionParametro o : estatusList) {
                        if (Integer.parseInt(o.getValor()) == configuracion.getEstatusId()) {
                            configuracion.setEstatus(o.getNombre());
                            break;
                        }
                    }
                }

                configuracion.setEliminado("");
                if (configuracion.getEliminadoId() != null) {
                    for (ConfiguracionParametro o : eliminadoList) {
                        if (Integer.parseInt(o.getValor()) == configuracion.getEliminadoId()) {
                            configuracion.setEliminado(o.getNombre());
                            break;
                        }
                    }
                }
            }
        }
        return list;
    }

    public Integer findSizeByCriteria(DetachedCriteria criteria) {
        return configuracionDAO.findSizeByCriteria(generalizarCriteria(criteria));
    }

    public List<Configuracion> findByCriteriaLimit(DetachedCriteria criteria, Integer from, Integer to) {
        return configuracionDAO.findByCriteriaLimit(generalizarCriteria(criteria), from, to);
    }

    /*
 * Método que realiza un búsqueda en la BD obteniendo todos los registros que coincidan con los parámetros
 * definidos en el objeto <b>criteria</b>.
 *
 * @param criteria Es un objeto que contiene los parámetros de búsqueda.
 * @return Regresa una lista de objetos <b>Configuracion</b> que coinciden con los parámetros definidos en
 * <b>criteria</b>.
     */
    @Override
    public List<Configuracion> findByCriteria(DetachedCriteria criteria) {
        return configuracionDAO.findByCriteria(generalizarCriteria(criteria));
    }

    @Override
    public List<Configuracion> findByCriteriaIgnorePrivacy(Configuracion configuracion) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Example.create(configuracion));
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        return configuracionDAO.findByCriteria(criteria);
    }

    public DetachedCriteria generalizarCriteria(DetachedCriteria criteria) {
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        resolvePrivacy(criteria, ConfiguracionAction.class);
        return criteria;
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>Configuracion</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>Configuracion</b>.
     */
    @Override
    public List<Configuracion> findAll() {
        return findByCriteria(createDetachedCriteria().addOrder(Order.asc("nombre")));
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>Configuracion</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>Configuracion</b>.
     */
    @Override
    public List<Configuracion> findActive() {
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
        return configuracionDAO.createDetachedCriteria();
    }

    /*
 * Método que actualiza el Estatus de los ID' contenidos en la lista
 * <b>configuracionList</b> en la tabla de <b>Configuracion</b>.
 * @param userId userId Es el ID del usuario que realizó la operación.
 * @param estatusId Es el ID del estatus al cual se quiere cambiar.
 * @param configuracionList Es una lista que contiene los registros los cuales se
 * quiere actualizar su Estatus.
     */
    @Override
    public void setEstatus(Integer userId, Integer estatusId, List<Integer> configuracionList) {
        for (Integer configuracionId : configuracionList) {
            Configuracion configuracion = findById(configuracionId);
            if (estatusId == 1) {
                configuracion.setEstatusId(ACTIVO);
            } else {
                configuracion.setEstatusId(INACTIVO);
            }
            update(userId, Arrays.asList(configuracion));
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
    public String getReportDataTest(String order, String ordenTipo, Configuracion configuracion) {
        Grid grid = findByCriteria(1, 10000, order, ordenTipo, configuracion);
        return addReportDataToSession(grid.getGrid());
    }

    /*
 * Metodos que actualizan los campos <select> en el jsp despues de haber hecho
 * una edición de datos en un popup externo.
     */
    // <editor-fold defaultstate="collapsed" desc="Getters Detalle(s)">
    @Override
    public List<Configuracion> getConfiguracionParametroConfiguracion() {
        return configuracionParametroBss.getConfiguracion();
    }
    // </editor-fold>

    @Override
    public String hasGrid() {
        return hasGrid(ConfiguracionAction.class).toString();
    }

    @Override
    public String isIndividual() {
        return isIndividual(ConfiguracionAction.class).toString();
    }

    @Override
    public String hasGridConfiguracionParametro() {
        return configuracionParametroBss.hasGrid().toString();
    }

    @Override
    public String isIndividualConfiguracionParametro() {
        return configuracionParametroBss.isIndividual().toString();
    }

    @Override
    public String getNombreActionMenu() {
        Pagina pagina = findPageByActionClass(ConfiguracionAction.class);
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
    public void setConfiguracionDAO(ConfiguracionDAO configuracionDAO) {
        this.configuracionDAO = configuracionDAO;
    }

    public void setConfiguracionParametroBss(ConfiguracionParametroBss configuracionParametroBss) {
        this.configuracionParametroBss = configuracionParametroBss;
    }

    public void setUsuarioBss(UsuarioBss usuarioBss) {
        this.usuarioBss = usuarioBss;
    }

}
