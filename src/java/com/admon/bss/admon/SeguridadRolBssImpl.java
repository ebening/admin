package com.admon.bss.admon;
import com.admon.bss.BaseBss;
import com.admon.dao.admon.SeguridadRolDAO;
import com.admon.entity.admon.*;
import com.admon.model.admon.SeguridadRolAction;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

public class SeguridadRolBssImpl extends BaseBss implements SeguridadRolBss{
   /* Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase. */
    private SeguridadRolDAO seguridadRolDAO;
    private ConfiguracionParametroBss configuracionParametroBss;
    private UsuarioBss usuarioBss;
    private PaginaBss paginaBss;

    public SeguridadRolBssImpl() {
    }

    /*
 * Método que guarda los objetos contenidos en la lista <b>objectList</b> en la tabla de <b>Seguridad_Rol</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de objetos que se guardarán en la tabla de <b>Seguridad_Rol</b> en la BD.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    @Override
    public List<Integer> save(Integer userId, List<SeguridadRol> seguridadRolList) {
        List<Integer> savedList = new ArrayList<Integer>();
        for (SeguridadRol rol : seguridadRolList) {
            rol.setEstatusId(ACTIVO);
            rol.setEliminadoId(NOELIMINADO);
            rol.setModificacionUsuario(userId);
            rol.setCreacionUsuario(userId);
            rol.setCreacionFecha(new Timestamp(new Date().getTime()));
            rol.setModificacionFecha(new Timestamp(new Date().getTime()));
            savedList.add(seguridadRolDAO.save(rol));
        }
        return savedList;
    }


    /*
 * Método que actualiza la información de <b>rol</b> en la tabla de <b>Seguridad_Rol</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param rolList Lista de registros que se actualizarán en la tabla de <b>Seguridad_Rol</b> en la BD.
     */
    @Override
    public List<Integer> update(Integer userId, List<SeguridadRol> seguridadRolList) {
        List<Integer> idList = new ArrayList();
        for (SeguridadRol rol : seguridadRolList) {
            rol.setModificacionUsuario(userId);
            rol.setModificacionFecha(new Timestamp(new Date().getTime()));
            seguridadRolDAO.update(rol);
            idList.add(rol.getRolId());
        }
        return idList;
    }

    /*
 * Método que elimina el registro <b>rol</b> en la tabla de <b>Seguridad_Rol</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param rolList Lista de registros que se eliminarán en la tabla de <b>Seguridad_Rol</b> en la BD.
     */
    @Override
    public void delete(Integer userId, List<Integer> seguridadRolList) {
        for (Integer rolId : seguridadRolList) {
            SeguridadRol rol = findById(rolId);
            rol.setEliminadoId(ELIMINADO);
            rol.setModificacionUsuario(rolId);
            rol.setModificacionFecha(new Timestamp(new Date().getTime()));
            seguridadRolDAO.update(rol);
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
 * Método que busca el registro <b>rol</b> por su NOMBRE en la tabla de <b>Seguridad_Rol</b>.
 * @param nombre es el NOMBRE del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>SeguridadRol</b> con la información de la consulta.
     */
    @Override
    public SeguridadRol findByName(String nombre) {
        return getFirst(findByCriteria(createDetachedCriteria()
                .add(Expression.eq("nombre", nombre.trim()))));
    }

    /*
 * Busca el registro <b>rol</b> por su ID en la tabla de <b>SeguridadRol</b>.
 * @param id es el ID del registro a obtener.
 * @return Regresa un objeto <b>SeguridadRol</b> con la información del registro <b>rol</b>.
     */
    @Override
    public SeguridadRol findById(Integer rolId) {
        SeguridadRol rol = seguridadRolDAO.findById(rolId);
        if (rol == null) {
            return null;
        } else {
            return resolveDescription(Arrays.asList(rol)).get(0);
        }
    }

    @Override
    public List<SeguridadRol> findByIntProperty(String propertyName, Integer value) {
        return resolveDescription(findByCriteria(
                createDetachedCriteria().add(Expression.eq(propertyName, value))));
    }

    /*
 * Método que hace una consulta a la tabla Seguridad_Rol y obtiene los registros que coincidan con el objeto <b>rol</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
 * @param displayedPage Número de página que se desea mostrar al usuario en el widget JQGrid.
 * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
 * @param order Indica por que campo sera ordenada la búsqueda.
 * @param ordenTipo Tipo de ordenado acendente o decendente.
 * @param SeguridadRol objeto que contiene los parámetros a buscar.
 * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    @Override
    public Grid findByCriteria(int displayedPage, int maxResult, String order,
        String ordenTipo, SeguridadRol rol) {
        Grid grid = new Grid();
        DetachedCriteria criteria = createDetachedCriteria();
        // Auxiliar para busqueda por rango de fechas campo: CreacionFecha
        if (rol.getCreacionFechaInicial() != null) {
            criteria.add(Expression.ge("creacionFecha", getStartOfDay(rol.getCreacionFechaInicial())));
        }
        if (rol.getCreacionFechaFinal() != null) {
            criteria.add(Expression.le("creacionFecha", getEndOfDay(rol.getCreacionFechaFinal())));
        }
        // Auxiliar para busqueda por rango de fechas campo: ModificacionFecha
        if (rol.getModificacionFechaInicial() != null) {
            criteria.add(Expression.ge("modificacionFecha", getStartOfDay(rol.getModificacionFechaInicial())));
        }
        if (rol.getModificacionFechaFinal() != null) {
            criteria.add(Expression.le("modificacionFecha", getEndOfDay(rol.getModificacionFechaFinal())));
        }
        criteria.add(Example.create(rol));
        if (ordenTipo.equals("asc")) {
            criteria.addOrder(Order.asc(order));
        } else {
            criteria.addOrder(Order.desc(order));
        }
        int resultadosTotales = findSizeByCriteria(copy(criteria));
        List<SeguridadRol> rolList = findByCriteriaLimit(criteria,
                maxResult * (displayedPage - 1), maxResult);
        grid.setGrid(resolveDescription(rolList));
        grid.setTotal(resultadosTotales);
        grid.setPaginas(lastPage(resultadosTotales, maxResult));
        return grid;
    }

    @Override
    public SeguridadRol findFirst() {
        List<SeguridadRol> rolList = findByCriteria(createDetachedCriteria());
        if (rolList.isEmpty()) {
            return null;
        } else {
            return rolList.get(0);
        }
    }

    public List<SeguridadRol> resolveDescription(List<SeguridadRol> list) {
        if (!list.isEmpty()) {
            List<Usuario> usuarioList = usuarioBss.findAll();
            List<ConfiguracionParametro> estatusList = getParametros("key_estatus");
            List<ConfiguracionParametro> eliminadoList = getParametros("key_eliminado");
            List<ConfiguracionParametro> aplicacionList = getParametros("key_aplicacion");
            for (SeguridadRol rol : list) {

                rol.setUsuarioModificacion("");
                if (rol.getModificacionUsuario() != null) {
                    for (Usuario o : usuarioList) {
                        if (o.getUsuarioId().intValue() == rol.getModificacionUsuario().intValue()) {
                            rol.setUsuarioModificacion(o.getUsuario());
                            break;
                        }
                    }
                }

                rol.setEstatus("");
                if (rol.getEstatusId() != null) {
                    for (ConfiguracionParametro o : estatusList) {
                        if (Integer.parseInt(o.getValor()) == rol.getEstatusId()) {
                            rol.setEstatus(o.getNombre());
                            break;
                        }
                    }
                }

                rol.setEliminado("");
                if (rol.getEliminadoId() != null) {
                    for (ConfiguracionParametro o : eliminadoList) {
                        if (Integer.parseInt(o.getValor()) == rol.getEliminadoId()) {
                            rol.setEliminado(o.getNombre());
                            break;
                        }
                    }
                }
                
                rol.setAplicacion("");
                if (rol.getAplicacionId() != null) {
                    for (ConfiguracionParametro o : aplicacionList) {
                        if (Integer.parseInt(o.getValor()) == rol.getAplicacionId()) {
                            rol.setAplicacion(o.getNombre());
                            break;
                        }
                    }
                }
            }
        }
        return list;
    }

    public Integer findSizeByCriteria(DetachedCriteria criteria) {
        return seguridadRolDAO.findSizeByCriteria(generalizarCriteria(criteria));
    }

    public List<SeguridadRol> findByCriteriaLimit(DetachedCriteria criteria, Integer from, Integer to) {
        return seguridadRolDAO.findByCriteriaLimit(generalizarCriteria(criteria), from, to);
    }

    /*
 * Método que realiza un búsqueda en la BD obteniendo todos los registros que coincidan con los parámetros
 * definidos en el objeto <b>criteria</b>.
 *
 * @param criteria Es un objeto que contiene los parámetros de búsqueda.
 * @return Regresa una lista de objetos <b>SeguridadRol</b> que coinciden con los parámetros definidos en
 * <b>criteria</b>.
     */
    @Override
    public List<SeguridadRol> findByCriteria(DetachedCriteria criteria) {
        return seguridadRolDAO.findByCriteria(generalizarCriteria(criteria));
    }

    @Override
    public List<SeguridadRol> findByCriteriaIgnorePrivacy(SeguridadRol rol) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Example.create(rol));
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        return seguridadRolDAO.findByCriteria(criteria);
    }

    public DetachedCriteria generalizarCriteria(DetachedCriteria criteria) {
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        resolvePrivacy(criteria, SeguridadRolAction.class);
        return criteria;
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>Seguridad_Rol</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>SeguridadRol</b>.
     */
    @Override
    public List<SeguridadRol> findAll() {
        return findByCriteria(createDetachedCriteria().addOrder(Order.asc("nombre")));
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>Seguridad_Rol</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>SeguridadRol</b>.
     */
    @Override
    public List<SeguridadRol> findActive() {
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
        return seguridadRolDAO.createDetachedCriteria();
    }

    /*
 * Método que actualiza el Estatus de los ID' contenidos en la lista
 * <b>rolList</b> en la tabla de <b>Seguridad_Rol</b>.
 * @param userId userId Es el ID del usuario que realizó la operación.
 * @param estatusId Es el ID del estatus al cual se quiere cambiar.
 * @param rolList Es una lista que contiene los registros los cuales se
 * quiere actualizar su Estatus.
     */
    @Override
    public void setEstatus(Integer userId, Integer estatusId, List<Integer> rolList) {
        for (Integer rolId : rolList) {
            SeguridadRol rol = findById(rolId);
            if (estatusId == 1) {
                rol.setEstatusId(ACTIVO);
            } else {
                rol.setEstatusId(INACTIVO);
            }
            update(userId, Arrays.asList(rol));
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
    public String getReportDataTest(String order, String ordenTipo, SeguridadRol rol) {
        Grid grid = findByCriteria(1, 10000, order, ordenTipo, rol);
        return addReportDataToSession(grid.getGrid());
    }

    /*
 * Metodos que actualizan los campos <select> en el jsp despues de haber hecho
 * una edición de datos en un popup externo.
     */
    // <editor-fold defaultstate="collapsed" desc="Getters Detalle(s)">
    @Override
    public List<Pagina> getPagina() {
        return addDummy(paginaBss.findAll(), new Pagina(), Pagina.class);
    }
    
    @Override
    public String hasGrid() {
        return hasGrid(SeguridadRolAction.class).toString();
    }

    @Override
    public String isIndividual() {
        return isIndividual(SeguridadRolAction.class).toString();
    }
    
    @Override
    public String getNombreActionMenu() {
        Pagina pagina = findPageByActionClass(SeguridadRolAction.class);
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
    public void setSeguridadRolDAO(SeguridadRolDAO rolDAO) {
        this.seguridadRolDAO = rolDAO;
    }

    public void setConfiguracionParametroBss(ConfiguracionParametroBss configuracionParametroBss) {
        this.configuracionParametroBss = configuracionParametroBss;
    }

    public void setUsuarioBss(UsuarioBss usuarioBss) {
        this.usuarioBss = usuarioBss;
    }

    public void setPaginaBss(PaginaBss paginaBss) {
        this.paginaBss = paginaBss;
    }
       /*
* Obtiene una lista de todos los registros válidos (activos y no eliminados) de la tabla PAGINA.
*
* @return regresa una lista de todos los registros de la tabla PAGINA
     */
    @Override
    public List<Pagina> obtienePaginas() {
        DetachedCriteria criteria = paginaBss.createDetachedCriteria();
        criteria.add(Expression.eq("estatusId", ACTIVO));
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        return paginaBss.findByCriteria(criteria);
    }


      
}
