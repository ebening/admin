package com.admon.bss.admon;

import com.admon.bss.BaseBss;
import com.admon.dao.admon.AplicacionDAO;
import com.admon.entity.admon.*;
import com.admon.model.admon.AplicacionAction;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

public class AplicacionBssImpl extends BaseBss implements AplicacionBss {

    /* Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase. */
    private AplicacionDAO aplicacionDAO;
    private UsuarioBss usuarioBss;
    private OrganizacionBss organizacionBss;
    // Elementos de struts2 para el JSP (Embebido)
    private ConfiguracionParametroBss configuracionParametroBss;

    public AplicacionBssImpl() {
    }

    /*
 * Método que guarda los objetos contenidos en la lista <b>objectList</b> en la tabla de <b>Aplicacion</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de objetos que se guardarán en la tabla de <b>Aplicacion</b> en la BD.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    @Override
    public List<Long> save(Integer userId, List<Aplicacion> aplicacionList) {
        List<Long> savedList = new ArrayList<Long>();
        for (Aplicacion aplicacion : aplicacionList) {
            aplicacion.setEstatusId(ACTIVO);
            aplicacion.setEliminadoId(NOELIMINADO);
            aplicacion.setModificacionUsuario(userId);
            aplicacion.setCreacionUsuario(userId);
            aplicacion.setCreacionFecha(new Timestamp(new Date().getTime()));
            aplicacion.setModificacionFecha(new Timestamp(new Date().getTime()));
            savedList.add(aplicacionDAO.save(aplicacion));
        }
        return savedList;
    }

    @Override
    public void saveConfiguracionParametro(int userId, int aplicacionId,
            List<ConfiguracionParametro> detalleNuevoList) {
        List<ConfiguracionParametro> detalleActualList
                = configuracionParametroBss.findByIntProperty("aplicacionId",
                        aplicacionId);
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
 * Método que actualiza la información de <b>aplicacion</b> en la tabla de <b>Aplicacion</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param aplicacionList Lista de registros que se actualizarán en la tabla de <b>Aplicacion</b> en la BD.
     */
    @Override
    public List<Long> update(Integer userId, List<Aplicacion> aplicacionList) {
        List<Long> idList = new ArrayList();
        for (Aplicacion aplicacion : aplicacionList) {
            aplicacion.setModificacionUsuario(userId);
            aplicacion.setModificacionFecha(new Timestamp(new Date().getTime()));
            aplicacionDAO.update(aplicacion);
            idList.add(aplicacion.getAplicacionId());
        }
        return idList;
    }

    /*
 * Método que elimina el registro <b>aplicacion</b> en la tabla de <b>Aplicacion</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param aplicacionList Lista de registros que se eliminarán en la tabla de <b>Aplicacion</b> en la BD.
     */
    @Override
    public void delete(Integer userId, List<Long> aplicacionIdList) {
        for (Long aplicacionId : aplicacionIdList) {
            Aplicacion aplicacion = findById(aplicacionId);
            aplicacion.setEliminadoId(ELIMINADO);
            aplicacion.setModificacionUsuario(userId);
            aplicacion.setModificacionFecha(new Timestamp(new Date().getTime()));
            aplicacionDAO.update(aplicacion);
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
 * Método que busca el registro <b>aplicacion</b> por su NOMBRE en la tabla de <b>Aplicacion</b>.
 * @param nombre es el NOMBRE del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>Aplicacion</b> con la información de la consulta.
     */
    @Override
    public Aplicacion findByName(String nombre) {   
        return getFirst(findByCriteria(createDetachedCriteria()
                .add(Expression.eq("nombre", nombre.trim()))));
    }

    /*
 * Busca el registro <b>aplicacion</b> por su ID en la tabla de <b>Aplicacion</b>.
 * @param id es el ID del registro a obtener.
 * @return Regresa un objeto <b>Aplicacion</b> con la información del registro <b>aplicacion</b>.
     */
    @Override
    public Aplicacion findById(Long aplicacionId) {
        Aplicacion aplicacion = aplicacionDAO.findById(aplicacionId);
        if (aplicacion == null) {
            return null;
        } else {
            return resolveDescription(Arrays.asList(aplicacion)).get(0);
        }
    }

    @Override
    public List<Aplicacion> findByIntProperty(String propertyName, Integer value) {
        return resolveDescription(findByCriteria(
                createDetachedCriteria().add(Expression.eq(propertyName, value))));
    }

    @Override
    public List<ConfiguracionParametro> findConfiguracionParametroByIntProperty(String propertyName, Integer value) {
        return configuracionParametroBss.findByIntProperty(propertyName, value);
    }

    /*
 * Método que hace una consulta a la tabla Aplicacion y obtiene los registros que coincidan con el objeto <b>aplicacion</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
 * @param displayedPage Número de página que se desea mostrar al usuario en el widget JQGrid.
 * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
 * @param order Indica por que campo sera ordenada la búsqueda.
 * @param ordenTipo Tipo de ordenado acendente o decendente.
 * @param Aplicacion objeto que contiene los parámetros a buscar.
 * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    @Override
    public Grid findByCriteria(int displayedPage, int maxResult, String order,
            String ordenTipo, Aplicacion aplicacion) {
        Grid grid = new Grid();
        DetachedCriteria criteria = createDetachedCriteria();
        // Auxiliar para busqueda por rango de fechas campo: CreacionFecha
      /*  if (aplicacion.getCreacionFechaInicial() != null) {
            criteria.add(Expression.ge("creacionFecha", getStartOfDay(aplicacion.getCreacionFechaInicial())));
        }
        if (aplicacion.getCreacionFechaFinal() != null) {
            criteria.add(Expression.le("creacionFecha", getEndOfDay(aplicacion.getCreacionFechaFinal())));
        }
        // Auxiliar para busqueda por rango de fechas campo: ModificacionFecha
        if (aplicacion.getModificacionFechaInicial() != null) {
            criteria.add(Expression.ge("modificacionFecha", getStartOfDay(aplicacion.getModificacionFechaInicial())));
        }
        if (aplicacion.getModificacionFechaFinal() != null) {
            criteria.add(Expression.le("modificacionFecha", getEndOfDay(aplicacion.getModificacionFechaFinal())));
        }*/
        criteria.add(Example.create(aplicacion));
        if (ordenTipo.equals("asc")) {
            criteria.addOrder(Order.asc(order));
        } else {
            criteria.addOrder(Order.desc(order));
        }
        int resultadosTotales = findSizeByCriteria(copy(criteria));
        List<Aplicacion> aplicacionList = findByCriteriaLimit(criteria,
                maxResult * (displayedPage - 1), maxResult);
        grid.setGrid(resolveDescription(aplicacionList));
        grid.setTotal(resultadosTotales);
        grid.setPaginas(lastPage(resultadosTotales, maxResult));
        return grid;
    }

    @Override
    public Aplicacion findFirst() {
        List<Aplicacion> aplicacionList = findByCriteria(createDetachedCriteria());
        if (aplicacionList.isEmpty()) {
            return null;
        } else {
            return aplicacionList.get(0);
        }
    }

    public List<Aplicacion> resolveDescription(List<Aplicacion> list) {
        if (!list.isEmpty()) {
            List<Usuario> usuarioList = usuarioBss.findAll();
            List<ConfiguracionParametro> estatusList = getParametros("key_estatus");
            List<ConfiguracionParametro> eliminadoList = getParametros("key_eliminado");
            for (Aplicacion aplicacion : list) {

                aplicacion.setUsuarioModificacion("");
                if (aplicacion.getModificacionUsuario() != null) {
                    for (Usuario o : usuarioList) {
                        if (o.getUsuarioId().intValue() == aplicacion.getModificacionUsuario().intValue()) {
                            aplicacion.setUsuarioModificacion(o.getUsuario());
                            break;
                        }
                    }
                }

                aplicacion.setEstatus("");
                if (aplicacion.getEstatusId() != null) {
                    for (ConfiguracionParametro o : estatusList) {
                        if (Integer.parseInt(o.getValor()) == aplicacion.getEstatusId()) {
                            aplicacion.setEstatus(o.getNombre());
                            break;
                        }
                    }
                }

                aplicacion.setEliminado("");
                if (aplicacion.getEliminadoId() != null) {
                    for (ConfiguracionParametro o : eliminadoList) {
                        if (Integer.parseInt(o.getValor()) == aplicacion.getEliminadoId()) {
                            aplicacion.setEliminado(o.getNombre());
                            break;
                        }
                    }
                }
            }
        }
        return list;
    }

    public Integer findSizeByCriteria(DetachedCriteria criteria) {
        return aplicacionDAO.findSizeByCriteria(generalizarCriteria(criteria));
    }

    public List<Aplicacion> findByCriteriaLimit(DetachedCriteria criteria, Integer from, Integer to) {
        return aplicacionDAO.findByCriteriaLimit(generalizarCriteria(criteria), from, to);
    }

    /*
 * Método que realiza un búsqueda en la BD obteniendo todos los registros que coincidan con los parámetros
 * definidos en el objeto <b>criteria</b>.
 *
 * @param criteria Es un objeto que contiene los parámetros de búsqueda.
 * @return Regresa una lista de objetos <b>Aplicacion</b> que coinciden con los parámetros definidos en
 * <b>criteria</b>.
     */
    @Override
    public List<Aplicacion> findByCriteria(DetachedCriteria criteria) {
        return aplicacionDAO.findByCriteria(generalizarCriteria(criteria));
    }

    @Override
    public List<Aplicacion> findByCriteriaIgnorePrivacy(Aplicacion aplicacion) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Example.create(aplicacion));
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        return aplicacionDAO.findByCriteria(criteria);
    }

    public DetachedCriteria generalizarCriteria(DetachedCriteria criteria) {
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        resolvePrivacy(criteria, AplicacionAction.class);
        return criteria;
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>Aplicacion</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>Aplicacion</b>.
     */
    @Override
    public List<Aplicacion> findAll() { 
        return findByCriteria(createDetachedCriteria().addOrder(Order.asc("nombre")));
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>Aplicacion</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>Aplicacion</b>.
     */
    @Override
    public List<Aplicacion> findActive() {
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
        return aplicacionDAO.createDetachedCriteria();
    }

    /*
 * Método que actualiza el Estatus de los ID' contenidos en la lista
 * <b>aplicacionList</b> en la tabla de <b>Aplicacion</b>.
 * @param userId userId Es el ID del usuario que realizó la operación.
 * @param estatusId Es el ID del estatus al cual se quiere cambiar.
 * @param aplicacionList Es una lista que contiene los registros los cuales se
 * quiere actualizar su Estatus.
     */
    @Override
    public void setEstatus(Integer userId, Integer estatusId, List<Long> aplicacionList) {
        for (Long aplicacionId : aplicacionList) {
            Aplicacion aplicacion = findById(aplicacionId);
            if (estatusId == 1) {
                aplicacion.setEstatusId(ACTIVO);
            } else {
                aplicacion.setEstatusId(INACTIVO);
            }
            update(userId, Arrays.asList(aplicacion));
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
 * Metodos que actualizan los campos <select> en el jsp despues de haber hecho
 * una edición de datos en un popup externo.
     */
    @Override
    public List<Organizacion> getOrganizaciones() {
        return addDummy(organizacionBss.findActive(), new Organizacion(), Organizacion.class);
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
    public String getReportDataTest(String order, String ordenTipo, Aplicacion aplicacion) {
        Grid grid = findByCriteria(1, 10000, order, ordenTipo, aplicacion);
        return addReportDataToSession(grid.getGrid());
    }

    /*
 * Metodos que actualizan los campos <select> en el jsp despues de haber hecho
 * una edición de datos en un popup externo.
     */
    // <editor-fold defaultstate="collapsed" desc="Getters Detalle(s)">
   /* @Override
    public List<Aplicacion> getConfiguracionParametroAplicacion() {
        return configuracionParametroBss.getConfiguracion();
    }*/
    // </editor-fold>

    @Override
    public String hasGrid() {
        return hasGrid(AplicacionAction.class).toString();
    }

    @Override
    public String isIndividual() {
        return isIndividual(AplicacionAction.class).toString();
    }

    @Override
    public String hasGridAplicacionParametro() {
        return configuracionParametroBss.hasGrid().toString();
    }

    @Override
    public String isIndividualAplicacionParametro() {
        return configuracionParametroBss.isIndividual().toString();
    }

    @Override
    public String getNombreActionMenu() {
        Pagina pagina = findPageByActionClass(AplicacionAction.class);
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
    public void setAplicacionDAO(AplicacionDAO aplicacionDAO) {
        this.aplicacionDAO = aplicacionDAO;
    }

    public void setConfiguracionParametroBss(ConfiguracionParametroBss configuracionParametroBss) {
        this.configuracionParametroBss = configuracionParametroBss;
    }

    public void setUsuarioBss(UsuarioBss usuarioBss) {
        this.usuarioBss = usuarioBss;
    }
    
    public void setOrganizacionBss(OrganizacionBss organizacionBss) {
        this.organizacionBss = organizacionBss;
    }

}
