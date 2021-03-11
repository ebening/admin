package com.admon.bss.admon;

import com.admon.bss.BaseBss;
import com.admon.dao.admon.OrganizacionDAO;
import com.admon.entity.admon.*;
import com.admon.model.admon.OrganizacionAction;
import com.admon.pkg.entity.OrganizacionGenerarRS;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

public class OrganizacionBssImpl extends BaseBss implements OrganizacionBss {

    /* Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase. */
    private OrganizacionDAO organizacionDAO;
    private ConfiguracionParametroBss configuracionParametroBss;
    private UsuarioBss usuarioBss;
    // Elementos de struts2 para el JSP (Embebido)
    private OrganizacionCredencialBss organizacionCredencialBss;
    // Elementos de struts2 para el JSP (Embebido)
    private OrganizacionUsuarioBss organizacionUsuarioBss;
    private OrganizacionGenerarBss organizacionGenerarBss;

    private PaisBss paisBss;
    private EstadoBss estadoBss;
    private CiudadBss ciudadBss;

    public OrganizacionBssImpl() {
    }

    /*
 * Método que guarda los objetos contenidos en la lista <b>objectList</b> en la tabla de <b>Organizacion</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de objetos que se guardarán en la tabla de <b>Organizacion</b> en la BD.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    @Override
    public List<Integer> save(Integer userId, List<Organizacion> organizacionList) {
        List<Integer> savedList = new ArrayList<Integer>();
        for (Organizacion organizacion : organizacionList) {
            organizacion.setCreaEventosId(ACTIVO);
            organizacion.setServicioCompletoId(ACTIVO);
            organizacion.setEliminadoId(NOELIMINADO);
            organizacion.setModificacionUsuario(userId);
            organizacion.setCreacionUsuario(userId);
            organizacion.setCreacionFecha(new Timestamp(new Date().getTime()));
            organizacion.setModificacionFecha(new Timestamp(new Date().getTime()));
            savedList.add(organizacionDAO.save(organizacion));
        }
        return savedList;
    }

    @Override
    public List<Integer> saveOrganizacionCredencial(int userId, int organizacionId,
            List<OrganizacionCredencial> detalleNuevoList) {
        List<OrganizacionCredencial> detalleActualList
                = organizacionCredencialBss.findByIntProperty("organizacionId",
                        organizacionId);
        // Delete
        for (OrganizacionCredencial detalle : detalleActualList) {
            boolean isDeletable = true;
            for (OrganizacionCredencial detalleNuevo : detalleNuevoList) {
                if (detalle.getOrganizacionCredencialId().equals(detalleNuevo.getOrganizacionCredencialId())) {
                    isDeletable = false;
                }
            }
            if (isDeletable) {
                organizacionCredencialBss.delete(userId,
                        Arrays.asList(detalle.getOrganizacionCredencialId()));
            }
        }
        List<Integer> savedOrUpdatedId = new ArrayList<Integer>();
        // Save y update
        for (OrganizacionCredencial detalle : detalleNuevoList) {
            if (detalle.getOrganizacionCredencialId() == 0) {
                savedOrUpdatedId.addAll(organizacionCredencialBss.save(userId, Arrays.asList(detalle)));
            } else {
               savedOrUpdatedId.addAll( organizacionCredencialBss.update(userId, Arrays.asList(detalle)));
            }
        }
        return savedOrUpdatedId;
    }

    @Override
    public void saveOrganizacionUsuario(int userId, int organizacionId,
            List<OrganizacionUsuario> detalleNuevoList) {
        List<OrganizacionUsuario> detalleActualList
                = organizacionUsuarioBss.findByIntProperty("organizacionId",
                        organizacionId);
        // Delete
        for (OrganizacionUsuario detalle : detalleActualList) {
            boolean isDeletable = true;
            for (OrganizacionUsuario detalleNuevo : detalleNuevoList) {
                if (detalle.getOrganizacionUsuarioId().equals(detalleNuevo.getOrganizacionUsuarioId())) {
                    isDeletable = false;
                }
            }
            if (isDeletable) {
                organizacionUsuarioBss.delete(userId,
                        Arrays.asList(detalle.getOrganizacionUsuarioId()));
            }
        }
        // Save y update
        for (OrganizacionUsuario detalle : detalleNuevoList) {
            if (detalle.getOrganizacionUsuarioId() == 0) {
                organizacionUsuarioBss.save(userId, Arrays.asList(detalle));
            } else {
                organizacionUsuarioBss.update(userId, Arrays.asList(detalle));
            }
        }
    }

    @Override
    public String crearOrganizacionGenerar(int userId, int organizacionId,
            List<OrganizacionGenerar> detalleNuevoList) {
        List<OrganizacionGenerar> detalleActualList
                = organizacionGenerarBss.findByIntProperty("organizacionId",
                        organizacionId);
        // Delete
//        for (OrganizacionGenerar detalle : detalleActualList) {
//            boolean isDeletable = true;
//            for (OrganizacionGenerar detalleNuevo : detalleNuevoList) {
//                if (detalle.getOrganizacionGenerarId()
//                        == detalleNuevo.getOrganizacionGenerarId()) {
//                    isDeletable = false;
//                }
//            }
//            if (isDeletable) {
//                organizacionGenerarBss.delete(userId,
//                        Arrays.asList(detalle.getOrganizacionGenerarId()));
//            }
//        }
        // Save y update
 //       for (OrganizacionGenerar detalle : detalleNuevoList) {
 //           if (detalle.getOrganizacionGenerarId() == 0) {
               return organizacionGenerarBss.crear(userId, organizacionId, detalleNuevoList.get(0));
 //           } else {
 //               organizacionGenerarBss.update(userId, Arrays.asList(detalle));
 //           }
 //       }
    }
    /*
 * Método que actualiza la información de <b>organizacion</b> en la tabla de <b>Organizacion</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param organizacionList Lista de registros que se actualizarán en la tabla de <b>Organizacion</b> en la BD.
     */
    @Override
    public List<Integer> update(Integer userId, List<Organizacion> organizacionList) {
        List<Integer> idList = new ArrayList();
        for (Organizacion organizacion : organizacionList) {
            organizacion.setModificacionUsuario(userId);
            organizacion.setModificacionFecha(new Timestamp(new Date().getTime()));
            organizacionDAO.update(organizacion);
            idList.add(organizacion.getOrganizacionId());
        }
        return idList;
    }

    /*
 * Método que elimina el registro <b>organizacion</b> en la tabla de <b>Organizacion</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param organizacionList Lista de registros que se eliminarán en la tabla de <b>Organizacion</b> en la BD.
     */
    @Override
    public void delete(Integer userId, List<Integer> organizacionIdList) {
        for (Integer organizacionId : organizacionIdList) {
            Organizacion organizacion = findById(organizacionId);
            organizacion.setEliminadoId(ELIMINADO);
            organizacion.setModificacionUsuario(userId);
            organizacion.setModificacionFecha(new Timestamp(new Date().getTime()));
            organizacionDAO.update(organizacion);
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
 * Método que busca el registro <b>organizacion</b> por su NOMBRE en la tabla de <b>Organizacion</b>.
 * @param nombre es el NOMBRE del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>Organizacion</b> con la información de la consulta.
     */
    @Override
    public Organizacion findByName(String nombre) {
        return getFirst(findByCriteria(createDetachedCriteria()
                .add(Expression.eq("nombre", nombre.trim()))));
    }

    /*
 * Busca el registro <b>organizacion</b> por su ID en la tabla de <b>Organizacion</b>.
 * @param id es el ID del registro a obtener.
 * @return Regresa un objeto <b>Organizacion</b> con la información del registro <b>organizacion</b>.
     */
    @Override
    public Organizacion findById(Integer organizacionId) {
        Organizacion organizacion = organizacionDAO.findById(organizacionId);
        if (organizacion == null) {
            return null;
        } else {
            return resolveDescription(Arrays.asList(organizacion)).get(0);
        }
    }

    @Override
    public List<Organizacion> findByIntProperty(String propertyName, Integer value) {
        return resolveDescription(findByCriteria(
                createDetachedCriteria().add(Expression.eq(propertyName, value))));
    }

    @Override
    public List<OrganizacionCredencial> findOrganizacionCredencialByIntProperty(String propertyName, Integer value) {
        return organizacionCredencialBss.findByIntProperty(propertyName, value);
    }

    @Override
    public List<OrganizacionUsuario> findOrganizacionUsuarioByIntProperty(String propertyName, Integer value) {
        return organizacionUsuarioBss.findByIntProperty(propertyName, value);
    }

    @Override
    public List<OrganizacionGenerar> findOrganizacionGenerarByIntProperty(String propertyName, Integer value) {
        return organizacionGenerarBss.findByIntProperty(propertyName, value);
    }

    /*
 * Método que hace una consulta a la tabla Organizacion y obtiene los registros que coincidan con el objeto <b>organizacion</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
 * @param displayedPage Número de página que se desea mostrar al usuario en el widget JQGrid.
 * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
 * @param order Indica por que campo sera ordenada la búsqueda.
 * @param ordenTipo Tipo de ordenado acendente o decendente.
 * @param Organizacion objeto que contiene los parámetros a buscar.
 * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    @Override
    public Grid findByCriteria(int displayedPage, int maxResult, String order,
            String ordenTipo, Organizacion organizacion) {
        Grid grid = new Grid();
        DetachedCriteria criteria = createDetachedCriteria();
        // Auxiliar para busqueda por rango de fechas campo: creacionFecha
        if (organizacion.getCreacionFechaInicial() != null) {
            criteria.add(Expression.ge("creacionFecha", getStartOfDay(organizacion.getCreacionFechaInicial())));
        }
        if (organizacion.getCreacionFechaFinal() != null) {
            criteria.add(Expression.le("creacionFecha", getEndOfDay(organizacion.getCreacionFechaFinal())));
        }
        // Auxiliar para busqueda por rango de fechas campo: modificacionFecha
        if (organizacion.getModificacionFechaInicial() != null) {
            criteria.add(Expression.ge("modificacionFecha", getStartOfDay(organizacion.getModificacionFechaInicial())));
        }
        if (organizacion.getModificacionFechaFinal() != null) {
            criteria.add(Expression.le("modificacionFecha", getEndOfDay(organizacion.getModificacionFechaFinal())));
        }
        criteria.add(Example.create(organizacion));
        if (ordenTipo.equals("asc")) {
            criteria.addOrder(Order.asc(order));
        } else {
            criteria.addOrder(Order.desc(order));
        }
        int resultadosTotales = findSizeByCriteria(copy(criteria));
        List<Organizacion> organizacionList = findByCriteriaLimit(criteria,
                maxResult * (displayedPage - 1), maxResult);
        grid.setGrid(resolveDescription(organizacionList));
        grid.setTotal(resultadosTotales);
        grid.setPaginas(lastPage(resultadosTotales, maxResult));
        return grid;
    }

    @Override
    public Organizacion findFirst() {
        List<Organizacion> organizacionList = findByCriteria(createDetachedCriteria());
        if (organizacionList.isEmpty()) {
            return null;
        } else {
            return organizacionList.get(0);
        }
    }

    public List<Organizacion> resolveDescription(List<Organizacion> list) {
        if (!list.isEmpty()) {
            List<Usuario> usuarioList = usuarioBss.findAll();
            List<Pais> paisList = paisBss.findAll();
            List<Estado> estadoList = estadoBss.findAll();
            List<Ciudad> ciudadList = ciudadBss.findAll();
            //List<ConfiguracionParametro> estatusList = getParametros("key_estatus");
            List<ConfiguracionParametro> creaEventosList = getParametros("key_estatus");
            List<ConfiguracionParametro> servicioCompletoList = getParametros("key_estatus");
            List<ConfiguracionParametro> eliminadoList = getParametros("key_eliminado");
            for (Organizacion organizacion : list) {

                organizacion.setUsuarioModificacion("");
                if (organizacion.getModificacionUsuario() != null) {
                    for (Usuario o : usuarioList) {
                        if (o.getUsuarioId().equals(organizacion.getModificacionUsuario())) {
                            organizacion.setUsuarioModificacion(o.getUsuario());
                            break;
                        }
                    }
                }

                organizacion.setCreaEventos("");
                if (organizacion.getCreaEventosId() != null) {
                    for (ConfiguracionParametro o : creaEventosList) {
                        if (Integer.parseInt(o.getValor()) == organizacion.getCreaEventosId()) {
                            organizacion.setCreaEventos(o.getNombre());
                            break;
                        }
                    }
                }

                organizacion.setCiudad("");
                if (organizacion.getCiudadId() != null) {
                    for (Ciudad o : ciudadList) {
                        if (o.getCiudadId().equals(organizacion.getCiudadId())) {
                            organizacion.setCiudad(o.getNombre());
                            break;
                        }
                    }
                }
                
                organizacion.setEstado("");
                if (organizacion.getEstadoId() != null) {
                    for (Estado o : estadoList) {
                        if (o.getEstadoId().equals(organizacion.getEstadoId())) {
                            organizacion.setEstado(o.getNombre());
                            break;
                        }
                    }
                }

                organizacion.setPais("");
                if (organizacion.getPaisId() != null) {
                    for (Pais o : paisList) {
                        if (o.getPaisId().equals(organizacion.getPaisId())) {
                            organizacion.setPais(o.getNombre());
                            break;
                        }
                    }
                }

                organizacion.setServicioCompleto("");
                if (organizacion.getServicioCompletoId() != null) {
                    for (ConfiguracionParametro o : servicioCompletoList) {
                        if (Integer.parseInt(o.getValor()) == organizacion.getServicioCompletoId()) {
                            organizacion.setServicioCompleto(o.getNombre());
                            break;
                        }
                    }
                }
                
                organizacion.setEliminado("");
                if (organizacion.getEliminadoId() != null) {
                    for (ConfiguracionParametro o : eliminadoList) {
                        if (Integer.parseInt(o.getValor()) == organizacion.getEliminadoId()) {
                            organizacion.setEliminado(o.getNombre());
                            break;
                        }
                    }
                }
            }
        }
        return list;
    }

    public Integer findSizeByCriteria(DetachedCriteria criteria) {
        return organizacionDAO.findSizeByCriteria(generalizarCriteria(criteria));
    }

    public List<Organizacion> findByCriteriaLimit(DetachedCriteria criteria, Integer from, Integer to) {
        return organizacionDAO.findByCriteriaLimit(generalizarCriteria(criteria), from, to);
    }

    /*
 * Método que realiza un búsqueda en la BD obteniendo todos los registros que coincidan con los parámetros
 * definidos en el objeto <b>criteria</b>.
 *
 * @param criteria Es un objeto que contiene los parámetros de búsqueda.
 * @return Regresa una lista de objetos <b>Organizacion</b> que coinciden con los parámetros definidos en
 * <b>criteria</b>.
     */
    @Override
    public List<Organizacion> findByCriteria(DetachedCriteria criteria) {
        return organizacionDAO.findByCriteria(generalizarCriteria(criteria));
    }

    @Override
    public List<Organizacion> findByCriteriaIgnorePrivacy(Organizacion organizacion) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Example.create(organizacion));
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        return organizacionDAO.findByCriteria(criteria);
    }

    public DetachedCriteria generalizarCriteria(DetachedCriteria criteria) {
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        resolvePrivacy(criteria, OrganizacionAction.class);
        return criteria;
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>Organizacion</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>Organizacion</b>.
     */
    @Override
    public List<Organizacion> findAll() {
        return findByCriteria(createDetachedCriteria().addOrder(Order.asc("nombre")));
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>Organizacion</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>Organizacion</b>.
     */
    @Override
    public List<Organizacion> findActive() {
        return findByCriteria(createDetachedCriteria().add(
                Expression.eq("creaEventosId", ACTIVO)).addOrder(Order.asc("nombre")));
    }

    /*
 * Método que obtiene un objeto DetachedCriteria.
 *
 * @return Regresa un objeto DetachedCriteria.
     */
    @Override
    public DetachedCriteria createDetachedCriteria() {
        return organizacionDAO.createDetachedCriteria();
    }

    /*
 * Método que actualiza el CreaEventos de los ID' contenidos en la lista
 * <b>organizacionList</b> en la tabla de <b>Organizacion</b>.
 * @param userId userId Es el ID del usuario que realizó la operación.
 * @param creaEventosId Es el ID del creaEventos al cual se quiere cambiar.
 * @param organizacionList Es una lista que contiene los registros los cuales se
 * quiere actualizar su CreaEventos.
     */
    @Override
    public void setCreaEventos(Integer userId, Integer creaEventosId, List<Integer> organizacionList) {
        for (Integer organizacionId : organizacionList) {
            Organizacion organizacion = findById(organizacionId);
            if (creaEventosId == 1) {
                organizacion.setCreaEventosId(ACTIVO);
                organizacion.setServicioCompletoId(ACTIVO);
            } else {
                organizacion.setCreaEventosId(INACTIVO);
                organizacion.setServicioCompletoId(INACTIVO);
            }
            update(userId, Arrays.asList(organizacion));
        }
    }

    /*
 * Método que actualiza el ServicioCompleto de los ID' contenidos en la lista
 * <b>organizacionList</b> en la tabla de <b>Organizacion</b>.
 * @param userId userId Es el ID del usuario que realizó la operación.
 * @param servicioCompletoId Es el ID del estatus al cual se quiere cambiar.
 * @param organizacionList Es una lista que contiene los registros los cuales se
 * quiere actualizar su ServicioCompleto.
     */
    @Override
    public void setServicioCompleto(Integer userId, Integer servicioCompletoId, List<Integer> organizacionList) {
        for (Integer organizacionId : organizacionList) {
            Organizacion organizacion = findById(organizacionId);
            if (servicioCompletoId == 1) {
                organizacion.setServicioCompletoId(ACTIVO);
            } else {
                organizacion.setServicioCompletoId(INACTIVO);
            }
            update(userId, Arrays.asList(organizacion));
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
    public String getReportDataTest(String order, String ordenTipo, Organizacion organizacion) {
        Grid grid = findByCriteria(1, 10000, order, ordenTipo, organizacion);
        return addReportDataToSession(grid.getGrid());
    }

    /*
 * Metodos que actualizan los campos <select> en el jsp despues de haber hecho
 * una edición de datos en un popup externo.
     */
    // <editor-fold defaultstate="collapsed" desc="Getters Detalle(s)">
    @Override
    public List<Organizacion> getOrganizacionCredencialOrganizacion() {
        return organizacionCredencialBss.getOrganizacion();
    }

    @Override
    public List<Pais> getPais() {
        return addDummy(paisBss.findActive(), new Pais(), Pais.class);
    }

    @Override
    public List<Estado> getEstado() {
        return addDummy(estadoBss.findActive(), new Estado(), Estado.class);
    }

    @Override
    public List<Ciudad> getCiudad() {
        return addDummy(ciudadBss.findActive(), new Ciudad(), Ciudad.class);
    }

    @Override
    public List<Organizacion> getOrganizacionUsuarioOrganizacion() {
        return organizacionUsuarioBss.getOrganizacion();
    }
    // </editor-fold>

    @Override
    public String hasGrid() {
        return hasGrid(OrganizacionAction.class).toString();
    }

    @Override
    public String isIndividual() {
        return isIndividual(OrganizacionAction.class).toString();
    }

    @Override
    public String hasGridOrganizacionCredencial() {
        return organizacionCredencialBss.hasGrid().toString();
    }

    @Override
    public String isIndividualOrganizacionCredencial() {
        return organizacionCredencialBss.isIndividual().toString();
    }

    @Override
    public String hasGridOrganizacionUsuario() {
        return organizacionUsuarioBss.hasGrid().toString();
    }

    @Override
    public String isIndividualOrganizacionUsuario() {
        return organizacionUsuarioBss.isIndividual().toString();
    }

    @Override
    public String getNombreActionMenu() {
        Pagina pagina = findPageByActionClass(OrganizacionAction.class);
        if (pagina == null) {
            return "";
        } else {
            return pagina.getNombre();
        }
    }

    // Selects encadenados
    @Override
    public List<Estado> filtrarEstado(int paisId) {
        return estadoBss.filtrarEstado(paisId);
    }

    // Selects encadenados
    @Override
    public List<Ciudad> filtrarCiudad(int estadoId) {
        return ciudadBss.filtrarCiudad(estadoId);
    }

    /*
 * Inyección de dependencias con Spring, cada
 * referencia definida al inicio de la clase requiere un método
 * Setter.
     */
    public void setOrganizacionDAO(OrganizacionDAO organizacionDAO) {
        this.organizacionDAO = organizacionDAO;
    }

    public void setConfiguracionParametroBss(ConfiguracionParametroBss configuracionParametroBss) {
        this.configuracionParametroBss = configuracionParametroBss;
    }

    public void setUsuarioBss(UsuarioBss usuarioBss) {
        this.usuarioBss = usuarioBss;
    }

    // Inyeccion de dependencias (Embebido)
    public void setOrganizacionCredencialBss(OrganizacionCredencialBss organizacionCredencialBss) {
        this.organizacionCredencialBss = organizacionCredencialBss;
    }

    // Inyeccion de dependencias (Embebido)
    public void setOrganizacionGenerarBss(OrganizacionGenerarBss organizacionGenerarBss) {
        this.organizacionGenerarBss = organizacionGenerarBss;
    }
    
    // Inyeccion de dependencias (Embebido)
    public void setOrganizacionUsuarioBss(OrganizacionUsuarioBss organizacionUsuarioBss) {
        this.organizacionUsuarioBss = organizacionUsuarioBss;
    }

    public PaisBss getPaisBss() {
        return paisBss;
    }

    public void setPaisBss(PaisBss paisBss) {
        this.paisBss = paisBss;
    }

    public void setEstadoBss(EstadoBss estadoBss) {
        this.estadoBss = estadoBss;
    }

    public CiudadBss getCiudadBss() {
        return ciudadBss;
    }

    public void setCiudadBss(CiudadBss ciudadBss) {
        this.ciudadBss = ciudadBss;
    }

    @Override
    public List<Organizacion> getOrganizacionGenerarOrganizacion() {
        return organizacionGenerarBss.getOrganizacion();
    }

    @Override
    public String hasGridOrganizacionGenerar() {
        return organizacionGenerarBss.hasGrid().toString();
    }

    @Override
    public String isIndividualOrganizacionGenerar() {
        return organizacionGenerarBss.isIndividual().toString();
    }
}
