package com.admon.bss.admon;

import com.admon.bss.BaseBss;
import com.admon.dao.admon.OrganizacionUsuarioDAO;
import com.admon.entity.admon.*;
import com.admon.model.admon.OrganizacionUsuarioAction;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

public class OrganizacionUsuarioBssImpl extends BaseBss implements OrganizacionUsuarioBss {

    /* Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase. */
    private OrganizacionUsuarioDAO organizacionUsuarioDAO;
    private ConfiguracionParametroBss configuracionParametroBss;
    private UsuarioBss usuarioBss;
    private OrganizacionBss organizacionBss;

    public OrganizacionUsuarioBssImpl() {
    }

    /*
 * Método que guarda los objetos contenidos en la lista <b>objectList</b> en la tabla de <b>OrganizacionUsuario</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de objetos que se guardarán en la tabla de <b>OrganizacionUsuario</b> en la BD.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    @Override
    public List<Integer> save(Integer userId, List<OrganizacionUsuario> organizacionUsuarioList) {
        List<Integer> savedList = new ArrayList<Integer>();
        for (OrganizacionUsuario organizacionUsuario : organizacionUsuarioList) {
            organizacionUsuario.setEstatusId(ACTIVO);
            organizacionUsuario.setEliminadoId(NOELIMINADO);
            organizacionUsuario.setModificacionUsuario(userId);
            organizacionUsuario.setCreacionUsuario(userId);
            organizacionUsuario.setCreacionFecha(new Timestamp(new Date().getTime()));
            organizacionUsuario.setModificacionFecha(new Timestamp(new Date().getTime()));
            savedList.add(organizacionUsuarioDAO.save(organizacionUsuario));
        }
        return savedList;
    }

    /*
 * Método que actualiza la información de <b>organizacionUsuario</b> en la tabla de <b>OrganizacionUsuario</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param organizacionUsuarioList Lista de registros que se actualizarán en la tabla de <b>OrganizacionUsuario</b> en la BD.
     */
    @Override
    public List<Integer> update(Integer userId, List<OrganizacionUsuario> organizacionUsuarioList) {
        List<Integer> idList = new ArrayList();
        for (OrganizacionUsuario organizacionUsuario : organizacionUsuarioList) {
            organizacionUsuario.setModificacionUsuario(userId);
            organizacionUsuario.setModificacionFecha(new Timestamp(new Date().getTime()));
            organizacionUsuarioDAO.update(organizacionUsuario);
            idList.add(organizacionUsuario.getOrganizacionUsuarioId());
        }
        return idList;
    }

    /*
 * Método que elimina el registro <b>organizacionUsuario</b> en la tabla de <b>OrganizacionUsuario</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param organizacionUsuarioList Lista de registros que se eliminarán en la tabla de <b>OrganizacionUsuario</b> en la BD.
     */
    @Override
    public void delete(Integer userId, List<Integer> organizacionUsuarioIdList) {
        for (Integer organizacionUsuarioId : organizacionUsuarioIdList) {
            OrganizacionUsuario organizacionUsuario = findById(organizacionUsuarioId);
            organizacionUsuario.setEliminadoId(ELIMINADO);
            organizacionUsuario.setModificacionUsuario(userId);
            organizacionUsuario.setModificacionFecha(new Timestamp(new Date().getTime()));
            organizacionUsuarioDAO.update(organizacionUsuario);
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
 * Método que busca el registro <b>organizacionUsuario</b> por su NOMBRE en la tabla de <b>OrganizacionUsuario</b>.
 * @param nombre es el NOMBRE del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>OrganizacionUsuario</b> con la información de la consulta.
     */
    @Override
    public OrganizacionUsuario findByName(String nombre) {
        return getFirst(findByCriteria(createDetachedCriteria()
                .add(Expression.eq("nombre", nombre.trim()))));
    }

    /*
 * Busca el registro <b>organizacionUsuario</b> por su ID en la tabla de <b>OrganizacionUsuario</b>.
 * @param id es el ID del registro a obtener.
 * @return Regresa un objeto <b>OrganizacionUsuario</b> con la información del registro <b>organizacionUsuario</b>.
     */
    @Override
    public OrganizacionUsuario findById(Integer organizacionUsuarioId) {
        OrganizacionUsuario organizacionUsuario = organizacionUsuarioDAO.findById(organizacionUsuarioId);
        if (organizacionUsuario == null) {
            return null;
        } else {
            return resolveDescription(Arrays.asList(organizacionUsuario)).get(0);
        }
    }

    @Override
    public List<OrganizacionUsuario> findByIntProperty(String propertyName, Integer value) {
        return resolveDescription(findByCriteria(
                createDetachedCriteria().add(Expression.eq(propertyName, value))));
    }

    /*
 * Método que hace una consulta a la tabla OrganizacionUsuario y obtiene los registros que coincidan con el objeto <b>organizacionUsuario</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
 * @param displayedPage Número de página que se desea mostrar al usuario en el widget JQGrid.
 * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
 * @param order Indica por que campo sera ordenada la búsqueda.
 * @param ordenTipo Tipo de ordenado acendente o decendente.
 * @param OrganizacionUsuario objeto que contiene los parámetros a buscar.
 * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    @Override
    public Grid findByCriteria(int displayedPage, int maxResult, String order,
            String ordenTipo, OrganizacionUsuario organizacionUsuario) {
        Grid grid = new Grid();
        DetachedCriteria criteria = createDetachedCriteria();
        // Auxiliar para busqueda por rango de fechas campo: CreacionFecha
        if (organizacionUsuario.getCreacionFechaInicial() != null) {
            criteria.add(Expression.ge("creacionFecha", getStartOfDay(organizacionUsuario.getCreacionFechaInicial())));
        }
        if (organizacionUsuario.getCreacionFechaFinal() != null) {
            criteria.add(Expression.le("creacionFecha", getEndOfDay(organizacionUsuario.getCreacionFechaFinal())));
        }
        // Auxiliar para busqueda por rango de fechas campo: ModificacionFecha
        if (organizacionUsuario.getModificacionFechaInicial() != null) {
            criteria.add(Expression.ge("modificacionFecha", getStartOfDay(organizacionUsuario.getModificacionFechaInicial())));
        }
        if (organizacionUsuario.getModificacionFechaFinal() != null) {
            criteria.add(Expression.le("modificacionFecha", getEndOfDay(organizacionUsuario.getModificacionFechaFinal())));
        }
        criteria.add(Example.create(organizacionUsuario));
        if (ordenTipo.equals("asc")) {
            criteria.addOrder(Order.asc(order));
        } else {
            criteria.addOrder(Order.desc(order));
        }
        int resultadosTotales = findSizeByCriteria(copy(criteria));
        List<OrganizacionUsuario> organizacionUsuarioList = findByCriteriaLimit(criteria,
                maxResult * (displayedPage - 1), maxResult);
        grid.setGrid(resolveDescription(organizacionUsuarioList));
        grid.setTotal(resultadosTotales);
        grid.setPaginas(lastPage(resultadosTotales, maxResult));
        return grid;
    }

    @Override
    public OrganizacionUsuario findFirst() {
        List<OrganizacionUsuario> organizacionUsuarioList = findByCriteria(createDetachedCriteria());
        if (organizacionUsuarioList.isEmpty()) {
            return null;
        } else {
            return organizacionUsuarioList.get(0);
        }
    }

    public List<OrganizacionUsuario> resolveDescription(List<OrganizacionUsuario> list) {
        if (!list.isEmpty()) {
            List<Usuario> usuarioList = usuarioBss.findAll();
            List<Organizacion> organizacionList = organizacionBss.findAll();
            List<ConfiguracionParametro> estatusList = getParametros("key_estatus");
            List<ConfiguracionParametro> eliminadoList = getParametros("key_eliminado");
            for (OrganizacionUsuario organizacionUsuario : list) {

                organizacionUsuario.setUsuarioModificacion("");
                if (organizacionUsuario.getModificacionUsuario() != null) {
                    for (Usuario o : usuarioList) {
                        if (o.getUsuarioId().equals(organizacionUsuario.getModificacionUsuario())) {
                            organizacionUsuario.setUsuarioModificacion(o.getUsuario());
                            break;
                        }
                    }
                }

                organizacionUsuario.setOrganizacion("");
                if (organizacionUsuario.getOrganizacionId() != null) {
                    for (Organizacion o : organizacionList) {
                        if (o.getOrganizacionId().equals(organizacionUsuario.getOrganizacionId())) {
                            organizacionUsuario.setOrganizacion(o.getNombre());
                            break;
                        }
                    }
                }

                organizacionUsuario.setEstatus("");
                if (organizacionUsuario.getEstatusId() != null) {
                    for (ConfiguracionParametro o : estatusList) {
                        if (Integer.parseInt(o.getValor()) == organizacionUsuario.getEstatusId()) {
                            organizacionUsuario.setEstatus(o.getNombre());
                            break;
                        }
                    }
                }

                organizacionUsuario.setEliminado("");
                if (organizacionUsuario.getEliminadoId() != null) {
                    for (ConfiguracionParametro o : eliminadoList) {
                        if (Integer.parseInt(o.getValor()) == organizacionUsuario.getEliminadoId()) {
                            organizacionUsuario.setEliminado(o.getNombre());
                            break;
                        }
                    }
                }
            }
        }
        return list;
    }

    public Integer findSizeByCriteria(DetachedCriteria criteria) {
        return organizacionUsuarioDAO.findSizeByCriteria(generalizarCriteria(criteria));
    }

    public List<OrganizacionUsuario> findByCriteriaLimit(DetachedCriteria criteria, Integer from, Integer to) {
        return organizacionUsuarioDAO.findByCriteriaLimit(generalizarCriteria(criteria), from, to);
    }

    /*
 * Método que realiza un búsqueda en la BD obteniendo todos los registros que coincidan con los parámetros
 * definidos en el objeto <b>criteria</b>.
 *
 * @param criteria Es un objeto que contiene los parámetros de búsqueda.
 * @return Regresa una lista de objetos <b>OrganizacionUsuario</b> que coinciden con los parámetros definidos en
 * <b>criteria</b>.
     */
    @Override
    public List<OrganizacionUsuario> findByCriteria(DetachedCriteria criteria) {
        return organizacionUsuarioDAO.findByCriteria(generalizarCriteria(criteria));
    }

    @Override
    public List<OrganizacionUsuario> findByCriteriaIgnorePrivacy(OrganizacionUsuario organizacionUsuario) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Example.create(organizacionUsuario));
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        return organizacionUsuarioDAO.findByCriteria(criteria);
    }

    public DetachedCriteria generalizarCriteria(DetachedCriteria criteria) {
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        resolvePrivacy(criteria, OrganizacionUsuarioAction.class);
        return criteria;
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>OrganizacionUsuario</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>OrganizacionUsuario</b>.
     */
    @Override
    public List<OrganizacionUsuario> findAll() {
        return findByCriteria(createDetachedCriteria().addOrder(Order.asc("nombre")));
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>OrganizacionUsuario</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>OrganizacionUsuario</b>.
     */
    @Override
    public List<OrganizacionUsuario> findActive() {
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
        return organizacionUsuarioDAO.createDetachedCriteria();
    }

    /*
 * Método que actualiza el Estatus de los ID' contenidos en la lista
 * <b>organizacionUsuarioList</b> en la tabla de <b>OrganizacionUsuario</b>.
 * @param userId userId Es el ID del usuario que realizó la operación.
 * @param estatusId Es el ID del estatus al cual se quiere cambiar.
 * @param organizacionUsuarioList Es una lista que contiene los registros los cuales se
 * quiere actualizar su Estatus.
     */
    @Override
    public void setEstatus(Integer userId, Integer estatusId, List<Integer> organizacionUsuarioList) {
        for (Integer organizacionUsuarioId : organizacionUsuarioList) {
            OrganizacionUsuario organizacionUsuario = findById(organizacionUsuarioId);
            if (estatusId == 1) {
                organizacionUsuario.setEstatusId(ACTIVO);
            } else {
                organizacionUsuario.setEstatusId(INACTIVO);
            }
            update(userId, Arrays.asList(organizacionUsuario));
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
    public String getReportDataTest(String order, String ordenTipo, OrganizacionUsuario organizacionUsuario) {
        Grid grid = findByCriteria(1, 10000, order, ordenTipo, organizacionUsuario);
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
        return hasGrid(OrganizacionUsuarioAction.class).toString();
    }

    @Override
    public String isIndividual() {
        return isIndividual(OrganizacionUsuarioAction.class).toString();
    }

    @Override
    public String getNombreActionMenu() {
        Pagina pagina = findPageByActionClass(OrganizacionUsuarioAction.class);
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
    public void setOrganizacionUsuarioDAO(OrganizacionUsuarioDAO organizacionUsuarioDAO) {
        this.organizacionUsuarioDAO = organizacionUsuarioDAO;
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
