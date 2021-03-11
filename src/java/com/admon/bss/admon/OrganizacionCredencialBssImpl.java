package com.admon.bss.admon;

import com.admon.bss.BaseBss;
import com.admon.dao.admon.OrganizacionCredencialDAO;
import com.admon.entity.admon.*;
import com.admon.model.admon.OrganizacionCredencialAction;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

public class OrganizacionCredencialBssImpl extends BaseBss implements OrganizacionCredencialBss {

    /* Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase. */
    private OrganizacionCredencialDAO organizacionCredencialDAO;
    private ConfiguracionParametroBss configuracionParametroBss;
    private UsuarioBss usuarioBss;
    private OrganizacionBss organizacionBss;
    private OrganizacionGenerarBss organizacionGenerarBss;
   
    public OrganizacionCredencialBssImpl() {
    }

    /*
 * Método que guarda los objetos contenidos en la lista <b>objectList</b> en la tabla de <b>OrganizacionCredencial</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de objetos que se guardarán en la tabla de <b>OrganizacionCredencial</b> en la BD.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    @Override
    public List<Integer> save(Integer userId, List<OrganizacionCredencial> organizacionCredencialList) {
        List<Integer> savedList = new ArrayList<Integer>();
        for (OrganizacionCredencial organizacionCredencial : organizacionCredencialList) {
            organizacionCredencial.setEstatusId(ACTIVO);
            organizacionCredencial.setEliminadoId(NOELIMINADO);
            organizacionCredencial.setModificacionUsuario(userId);
            organizacionCredencial.setCreacionUsuario(userId);
            organizacionCredencial.setCreacionFecha(new Timestamp(new Date().getTime()));
            organizacionCredencial.setModificacionFecha(new Timestamp(new Date().getTime()));
            savedList.add(organizacionCredencialDAO.save(organizacionCredencial));
            
            //Guardar el requisito de Credencial para Generar la organización
            organizacionGenerarBss.update(userId, organizacionGenerarBss.
                                            registraRequisitoCreacion(organizacionCredencial.getOrganizacionId()
                                                    ,userId
                                                    ,new Integer(codigos.getString("key_tipo_requisito"))
                                                    ,new Integer(codigos.getString("key_concepto_credencial"))));

        }
        return savedList;
    }

    /*
 * Método que actualiza la información de <b>organizacionCredencial</b> en la tabla de <b>OrganizacionCredencial</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param organizacionCredencialList Lista de registros que se actualizarán en la tabla de <b>OrganizacionCredencial</b> en la BD.
     */
    @Override
    public List<Integer> update(Integer userId, List<OrganizacionCredencial> organizacionCredencialList) {
        List<Integer> idList = new ArrayList();
        for (OrganizacionCredencial organizacionCredencial : organizacionCredencialList) {
            organizacionCredencial.setModificacionUsuario(userId);
            organizacionCredencial.setModificacionFecha(new Timestamp(new Date().getTime()));
            organizacionCredencialDAO.update(organizacionCredencial);
            idList.add(organizacionCredencial.getOrganizacionCredencialId());
        }
        return idList;
    }

    /*
 * Método que elimina el registro <b>organizacionCredencial</b> en la tabla de <b>OrganizacionCredencial</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param organizacionCredencialList Lista de registros que se eliminarán en la tabla de <b>OrganizacionCredencial</b> en la BD.
     */
    @Override
    public void delete(Integer userId, List<Integer> organizacionCredencialIdList) {
        for (Integer organizacionCredencialId : organizacionCredencialIdList) {
            OrganizacionCredencial organizacionCredencial = findById(organizacionCredencialId);
            organizacionCredencial.setEliminadoId(ELIMINADO);
            organizacionCredencial.setModificacionUsuario(userId);
            organizacionCredencial.setModificacionFecha(new Timestamp(new Date().getTime()));
            organizacionCredencialDAO.update(organizacionCredencial);
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
 * Método que busca el registro <b>organizacionCredencial</b> por su NOMBRE en la tabla de <b>OrganizacionCredencial</b>.
 * @param nombre es el NOMBRE del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>OrganizacionCredencial</b> con la información de la consulta.
     */
    @Override
    public OrganizacionCredencial findByName(String nombre) {
        return getFirst(findByCriteria(createDetachedCriteria()
                .add(Expression.eq("nombre", nombre.trim()))));
    }

    /*
 * Busca el registro <b>organizacionCredencial</b> por su ID en la tabla de <b>OrganizacionCredencial</b>.
 * @param id es el ID del registro a obtener.
 * @return Regresa un objeto <b>OrganizacionCredencial</b> con la información del registro <b>organizacionCredencial</b>.
     */
    @Override
    public OrganizacionCredencial findById(Integer organizacionCredencialId) {
        OrganizacionCredencial organizacionCredencial = organizacionCredencialDAO.findById(organizacionCredencialId);
        if (organizacionCredencial == null) {
            return null;
        } else {
            return resolveDescription(Arrays.asList(organizacionCredencial)).get(0);
        }
    }

    @Override
    public List<OrganizacionCredencial> findByIntProperty(String propertyName, Integer value) {
        return resolveDescription(findByCriteria(
                createDetachedCriteria().add(Expression.eq(propertyName, value))));
    }

    /*
 * Método que hace una consulta a la tabla OrganizacionCredencial y obtiene los registros que coincidan con el objeto <b>organizacionCredencial</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
 * @param displayedPage Número de página que se desea mostrar al usuario en el widget JQGrid.
 * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
 * @param order Indica por que campo sera ordenada la búsqueda.
 * @param ordenTipo Tipo de ordenado acendente o decendente.
 * @param OrganizacionCredencial objeto que contiene los parámetros a buscar.
 * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    @Override
    public Grid findByCriteria(int displayedPage, int maxResult, String order,
            String ordenTipo, OrganizacionCredencial organizacionCredencial) {
        Grid grid = new Grid();
        DetachedCriteria criteria = createDetachedCriteria();
        // Auxiliar para busqueda por rango de fechas campo: CreacionFecha
        if (organizacionCredencial.getCreacionFechaInicial() != null) {
            criteria.add(Expression.ge("creacionFecha", getStartOfDay(organizacionCredencial.getCreacionFechaInicial())));
        }
        if (organizacionCredencial.getCreacionFechaFinal() != null) {
            criteria.add(Expression.le("creacionFecha", getEndOfDay(organizacionCredencial.getCreacionFechaFinal())));
        }
        // Auxiliar para busqueda por rango de fechas campo: ModificacionFecha
        if (organizacionCredencial.getModificacionFechaInicial() != null) {
            criteria.add(Expression.ge("modificacionFecha", getStartOfDay(organizacionCredencial.getModificacionFechaInicial())));
        }
        if (organizacionCredencial.getModificacionFechaFinal() != null) {
            criteria.add(Expression.le("modificacionFecha", getEndOfDay(organizacionCredencial.getModificacionFechaFinal())));
        }
        criteria.add(Example.create(organizacionCredencial));
        if (ordenTipo.equals("asc")) {
            criteria.addOrder(Order.asc(order));
        } else {
            criteria.addOrder(Order.desc(order));
        }
        int resultadosTotales = findSizeByCriteria(copy(criteria));
        List<OrganizacionCredencial> organizacionCredencialList = findByCriteriaLimit(criteria,
                maxResult * (displayedPage - 1), maxResult);
        grid.setGrid(resolveDescription(organizacionCredencialList));
        grid.setTotal(resultadosTotales);
        grid.setPaginas(lastPage(resultadosTotales, maxResult));
        return grid;
    }

    @Override
    public OrganizacionCredencial findFirst() {
        List<OrganizacionCredencial> organizacionCredencialList = findByCriteria(createDetachedCriteria());
        if (organizacionCredencialList.isEmpty()) {
            return null;
        } else {
            return organizacionCredencialList.get(0);
        }
    }

    public List<OrganizacionCredencial> resolveDescription(List<OrganizacionCredencial> list) {
        if (!list.isEmpty()) {
            List<Usuario> usuarioList = usuarioBss.findAll();
            List<Organizacion> organizacionList = organizacionBss.findAll();
            List<ConfiguracionParametro> estatusList = getParametros("key_estatus");
            List<ConfiguracionParametro> eliminadoList = getParametros("key_eliminado");
            List<ConfiguracionParametro> aplicacionList = getParametros("key_aplicacion");
            
            for (OrganizacionCredencial organizacionCredencial : list) {

                organizacionCredencial.setUsuarioModificacion("");
                if (organizacionCredencial.getModificacionUsuario() != null) {
                    for (Usuario o : usuarioList) {
                        if (o.getUsuarioId().equals(organizacionCredencial.getModificacionUsuario())) {
                            organizacionCredencial.setUsuarioModificacion(o.getUsuario());
                            break;
                        }
                    }
                }

                organizacionCredencial.setOrganizacion("");
                if (organizacionCredencial.getOrganizacionId() != null) {
                    for (Organizacion o : organizacionList) {
                        if (o.getOrganizacionId().equals(organizacionCredencial.getOrganizacionId())) {
                            organizacionCredencial.setOrganizacion(o.getNombre());
                            break;
                        }
                    }
                }

                organizacionCredencial.setEstatus("");
                if (organizacionCredencial.getEstatusId() != null) {
                    for (ConfiguracionParametro o : estatusList) {
                        if (Integer.parseInt(o.getValor()) == organizacionCredencial.getEstatusId()) {
                            organizacionCredencial.setEstatus(o.getNombre());
                            break;
                        }
                    }
                }

                organizacionCredencial.setEliminado("");
                if (organizacionCredencial.getEliminadoId() != null) {
                    for (ConfiguracionParametro o : eliminadoList) {
                        if (Integer.parseInt(o.getValor()) == organizacionCredencial.getEliminadoId()) {
                            organizacionCredencial.setEliminado(o.getNombre());
                            break;
                        }
                    }
                }
                
                organizacionCredencial.setAplicacion("");
                if (organizacionCredencial.getAplicacionId() != null) {
                    for (ConfiguracionParametro o : aplicacionList) {
                        if (Integer.parseInt(o.getValor()) == organizacionCredencial.getAplicacionId()) {
                            organizacionCredencial.setAplicacion(o.getNombre());
                            break;
                        }
                    }
                }

            }
        }
        return list;
    }

    public Integer findSizeByCriteria(DetachedCriteria criteria) {
        return organizacionCredencialDAO.findSizeByCriteria(generalizarCriteria(criteria));
    }

    public List<OrganizacionCredencial> findByCriteriaLimit(DetachedCriteria criteria, Integer from, Integer to) {
        return organizacionCredencialDAO.findByCriteriaLimit(generalizarCriteria(criteria), from, to);
    }

    /*
 * Método que realiza un búsqueda en la BD obteniendo todos los registros que coincidan con los parámetros
 * definidos en el objeto <b>criteria</b>.
 *
 * @param criteria Es un objeto que contiene los parámetros de búsqueda.
 * @return Regresa una lista de objetos <b>OrganizacionCredencial</b> que coinciden con los parámetros definidos en
 * <b>criteria</b>.
     */
    @Override
    public List<OrganizacionCredencial> findByCriteria(DetachedCriteria criteria) {
        return organizacionCredencialDAO.findByCriteria(generalizarCriteria(criteria));
    }

    @Override
    public List<OrganizacionCredencial> findByCriteriaIgnorePrivacy(OrganizacionCredencial organizacionCredencial) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Example.create(organizacionCredencial));
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        return organizacionCredencialDAO.findByCriteria(criteria);
    }

    public DetachedCriteria generalizarCriteria(DetachedCriteria criteria) {
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        resolvePrivacy(criteria, OrganizacionCredencialAction.class);
        return criteria;
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>OrganizacionCredencial</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>OrganizacionCredencial</b>.
     */
    @Override
    public List<OrganizacionCredencial> findAll() {
        return findByCriteria(createDetachedCriteria().addOrder(Order.asc("nombre")));
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>OrganizacionCredencial</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>OrganizacionCredencial</b>.
     */
    @Override
    public List<OrganizacionCredencial> findActive() {
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
        return organizacionCredencialDAO.createDetachedCriteria();
    }

    /*
 * Método que actualiza el Estatus de los ID' contenidos en la lista
 * <b>organizacionCredencialList</b> en la tabla de <b>OrganizacionCredencial</b>.
 * @param userId userId Es el ID del usuario que realizó la operación.
 * @param estatusId Es el ID del estatus al cual se quiere cambiar.
 * @param organizacionCredencialList Es una lista que contiene los registros los cuales se
 * quiere actualizar su Estatus.
     */
    @Override
    public void setEstatus(Integer userId, Integer estatusId, List<Integer> organizacionCredencialList) {
        for (Integer organizacionCredencialId : organizacionCredencialList) {
            OrganizacionCredencial organizacionCredencial = findById(organizacionCredencialId);
            if (estatusId == 1) {
                organizacionCredencial.setEstatusId(ACTIVO);
            } else {
                organizacionCredencial.setEstatusId(INACTIVO);
            }
            update(userId, Arrays.asList(organizacionCredencial));
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
    public String getReportDataTest(String order, String ordenTipo, OrganizacionCredencial organizacionCredencial) {
        Grid grid = findByCriteria(1, 10000, order, ordenTipo, organizacionCredencial);
        return addReportDataToSession(grid.getGrid());
    }

    /*
 * Metodos que actualizan los campos <select> en el jsp despues de haber hecho
 * una edición de datos en un popup externo.
     */
    @Override
    public List<Organizacion> getOrganizacion() {
        return addDummy(organizacionBss.findActive(), new Organizacion(), Organizacion.class);
    }

    @Override
    public String hasGrid() {
        return hasGrid(OrganizacionCredencialAction.class).toString();
    }

    @Override
    public String isIndividual() {
        return isIndividual(OrganizacionCredencialAction.class).toString();
    }

    @Override
    public String getNombreActionMenu() {
        Pagina pagina = findPageByActionClass(OrganizacionCredencialAction.class);
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
    public void setOrganizacionCredencialDAO(OrganizacionCredencialDAO organizacionCredencialDAO) {
        this.organizacionCredencialDAO = organizacionCredencialDAO;
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

    public void setOrganizacionGenerarBss(OrganizacionGenerarBss organizacionGenerarBss) {
        this.organizacionGenerarBss = organizacionGenerarBss;
    }

}
