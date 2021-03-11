package com.admon.bss.admon;

import com.admon.bss.BaseBss;
import com.admon.dao.admon.SeguridadRolRestriccionParDAO;
import com.admon.entity.admon.*;
import com.admon.model.admon.SeguridadRolRestriccionParAction;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

public class SeguridadRolRestriccionParBssImpl extends BaseBss implements SeguridadRolRestriccionParBss {

    /* Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase. */
    private SeguridadRolRestriccionParDAO seguridadRestriccionesRolDAO;
    private ConfiguracionParametroBss configuracionParametroBss;
    private SeguridadRolBss seguridadRolBss;
    //private CatalogoBss catalogoBss;
    private ModuloBss moduloBss;
   // private ConfiguracionParametroBss configuracionParametroBss;
   // private UsuarioBss usuarioBss;

    public SeguridadRolRestriccionParBssImpl() {
    }

    /*
 * Método que guarda los objetos contenidos en la lista <b>objectList</b> en la tabla de <b>Seguridad_Rol_Restriccion_Par</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de objetos que se guardarán en la tabla de <b>Seguridad_Rol_Restriccion_Par</b> en la BD.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    @Override
    public List<Integer> save(Integer userId, List<SeguridadRolRestriccionPar> seguridadRestriccionesRolList) {
        List<Integer> savedList = new ArrayList<Integer>();
        for (SeguridadRolRestriccionPar seguridadRestriccionesRol : seguridadRestriccionesRolList) {
            seguridadRestriccionesRol.setCreacionUsuario(userId);
            seguridadRestriccionesRol.setCreacionFecha(new Timestamp(new Date().getTime()));
            savedList.add(seguridadRestriccionesRolDAO.save(seguridadRestriccionesRol));
        }
        return savedList;
    }

    /*
 * Método que actualiza la información de <b>seguridadRestriccionesRol</b> en la tabla de <b>Seguridad_Rol_Restriccion_Par</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param seguridadRestriccionesRolList Lista de registros que se actualizarán en la tabla de <b>Seguridad_Rol_Restriccion_Par</b> en la BD.
     */
    @Override
    public List<Integer> update(Integer userId, List<SeguridadRolRestriccionPar> seguridadRestriccionesRolList) {
        List<Integer> idList = new ArrayList();
        for (SeguridadRolRestriccionPar seguridadRestriccionesRol : seguridadRestriccionesRolList) {
            seguridadRestriccionesRolDAO.update(seguridadRestriccionesRol);
            idList.add(seguridadRestriccionesRol.getCatalogoParametroId());
        }
        return idList;
    }

    /*
 * Método que elimina el registro <b>seguridadRestriccionesRol</b> en la tabla de <b>Seguridad_Rol_Restriccion_Par</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param seguridadRestriccionesRolList Lista de registros que se eliminarán en la tabla de <b>Seguridad_Rol_Restriccion_Par</b> en la BD.
     */
    @Override
    public void delete(Integer userId, List<Integer> seguridadRestriccionesRolIdList) {
        for (Integer seguridadRestriccionesRolId : seguridadRestriccionesRolIdList) {
            SeguridadRolRestriccionPar seguridadRestriccionesRol = findById(seguridadRestriccionesRolId);
            seguridadRestriccionesRolDAO.update(seguridadRestriccionesRol);
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
 * Método que busca el registro <b>seguridadRestriccionesRol</b> por su NOMBRE en la tabla de <b>Seguridad_Rol_Restriccion_Par</b>.
 * @param nombre es el NOMBRE del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>Seguridad_Rol_Restriccion_Par</b> con la información de la consulta.
     */
    @Override
    public SeguridadRolRestriccionPar findByName(String nombre) {
        return getFirst(findByCriteria(createDetachedCriteria()
                .add(Expression.eq("nombre", nombre.trim()))));
    }

    /*
 * Busca el registro <b>seguridadRestriccionesRol</b> por su ID en la tabla de <b>Seguridad_Rol_Restriccion_Par</b>.
 * @param id es el ID del registro a obtener.
 * @return Regresa un objeto <b>Seguridad_Rol_Restriccion_Par</b> con la información del registro <b>seguridadRestriccionesRol</b>.
     */
    @Override
    public SeguridadRolRestriccionPar findById(Integer seguridadRestriccionesRolId) {
        SeguridadRolRestriccionPar seguridadRestriccionesRol = seguridadRestriccionesRolDAO.findById(seguridadRestriccionesRolId);
        if (seguridadRestriccionesRol == null) {
            return null;
        } else {
            return resolveDescription(Arrays.asList(seguridadRestriccionesRol)).get(0);
        }
    }

    @Override
    public List<SeguridadRolRestriccionPar> findByIntProperty(String propertyName, Integer value) {
        return resolveDescription(findByCriteria(
                createDetachedCriteria().add(Expression.eq(propertyName, value))));
    }

    /*
 * Método que hace una consulta a la tabla Seguridad_Rol_Restriccion_Par y obtiene los registros que coincidan con el objeto <b>seguridadRestriccionesRol</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
 * @param displayedPage Número de página que se desea mostrar al usuario en el widget JQGrid.
 * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
 * @param order Indica por que campo sera ordenada la búsqueda.
 * @param ordenTipo Tipo de ordenado acendente o decendente.
 * @param Seguridad_Rol_Restriccion_Par objeto que contiene los parámetros a buscar.
 * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    @Override
    public Grid findByCriteria(int displayedPage, int maxResult, String order,
            String ordenTipo, SeguridadRolRestriccionPar seguridadRestriccionesRol) {
        Grid grid = new Grid();
        DetachedCriteria criteria = createDetachedCriteria();
       
        criteria.add(Example.create(seguridadRestriccionesRol));
        if (ordenTipo.equals("asc")) {
            criteria.addOrder(Order.asc(order));
        } else {
            criteria.addOrder(Order.desc(order));
        }
        int resultadosTotales = findSizeByCriteria(copy(criteria));
        List<SeguridadRolRestriccionPar> seguridadRestriccionesRolList = findByCriteriaLimit(criteria,
                maxResult * (displayedPage - 1), maxResult);
        grid.setGrid(resolveDescription(seguridadRestriccionesRolList));
        grid.setTotal(resultadosTotales);
        grid.setPaginas(lastPage(resultadosTotales, maxResult));
        return grid;
    }

    @Override
    public SeguridadRolRestriccionPar findFirst() {
        List<SeguridadRolRestriccionPar> seguridadRestriccionesRolList = findByCriteria(createDetachedCriteria());
        if (seguridadRestriccionesRolList.isEmpty()) {
            return null;
        } else {
            return seguridadRestriccionesRolList.get(0);
        }
    }
    
    public List<SeguridadRolRestriccionPar> resolveDescription(List<SeguridadRolRestriccionPar> list) {
        if (!list.isEmpty()) {
           // List<Usuario> usuarioList = usuarioBss.findAll();
            //List<ConfiguracionParametro> estatusList = getParametros("key_estatus");
            //List<ConfiguracionParametro> eliminadoList = getParametros("key_eliminado");
           
        }
        return list;
    }

    public Integer findSizeByCriteria(DetachedCriteria criteria) {
        return seguridadRestriccionesRolDAO.findSizeByCriteria(generalizarCriteria(criteria));
    }

    public List<SeguridadRolRestriccionPar> findByCriteriaLimit(DetachedCriteria criteria, Integer from, Integer to) {
        return seguridadRestriccionesRolDAO.findByCriteriaLimit(generalizarCriteria(criteria), from, to);
    }

    /*
 * Método que realiza un búsqueda en la BD obteniendo todos los registros que coincidan con los parámetros
 * definidos en el objeto <b>criteria</b>.
 *
 * @param criteria Es un objeto que contiene los parámetros de búsqueda.
 * @return Regresa una lista de objetos <b>Seguridad_Rol_Restriccion_Par</b> que coinciden con los parámetros definidos en
 * <b>criteria</b>.
     */
    @Override
    public List<SeguridadRolRestriccionPar> findByCriteria(DetachedCriteria criteria) {
        return seguridadRestriccionesRolDAO.findByCriteria(generalizarCriteria(criteria));
    }

    @Override
    public List<SeguridadRolRestriccionPar> findByCriteriaIgnorePrivacy(SeguridadRolRestriccionPar seguridadRestriccionesRol) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Example.create(seguridadRestriccionesRol));
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        return seguridadRestriccionesRolDAO.findByCriteria(criteria);
    }

    public DetachedCriteria generalizarCriteria(DetachedCriteria criteria) {
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        resolvePrivacy(criteria, SeguridadRolRestriccionParAction.class);
        return criteria;
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>Seguridad_Rol_Restriccion_Par</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>Seguridad_Rol_Restriccion_Par</b>.
     */
    @Override
    public List<SeguridadRolRestriccionPar> findAll() {
        return findByCriteria(createDetachedCriteria().addOrder(Order.asc("nombre")));
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>Seguridad_Rol_Restriccion_Par</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>Seguridad_Rol_Restriccion_Par</b>.
     */
    @Override
    public List<SeguridadRolRestriccionPar> findActive() {
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
        return seguridadRestriccionesRolDAO.createDetachedCriteria();
    }

    /*
 * Método que actualiza el Estatus de los ID' contenidos en la lista
 * <b>seguridadRestriccionesRolList</b> en la tabla de <b>Seguridad_Rol_Restriccion_Par</b>.
 * @param userId userId Es el ID del usuario que realizó la operación.
 * @param estatusId Es el ID del estatus al cual se quiere cambiar.
 * @param seguridadRestriccionesRolList Es una lista que contiene los registros los cuales se
 * quiere actualizar su Estatus.
     */
   /* @Override
    public void setEstatus(Integer userId, Integer estatusId, List<Integer> seguridadRestriccionesRolList) {
        for (Integer seguridadRestriccionesRolId : seguridadRestriccionesRolList) {
            SeguridadRolRestriccionPar seguridadRestriccionesRol = findById(seguridadRestriccionesRolId);
            if (estatusId == 1) {
                seguridadRestriccionesRol.setEstatusId(ACTIVO);
            } else {
                seguridadRestriccionesRol.setEstatusId(INACTIVO);
            }
            update(userId, Arrays.asList(seguridadRestriccionesRol));
        }
    }*/

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
    public String getReportDataTest(String order, String ordenTipo, SeguridadRolRestriccionPar seguridadRestriccionesRol) {
        Grid grid = findByCriteria(1, 10000, order, ordenTipo, seguridadRestriccionesRol);
        return addReportDataToSession(grid.getGrid());
    }
     /*
 * Métodos que obtiene  datos para jsp
     */
    @Override
    public List<SeguridadRol> getRoles() {
        return seguridadRolBss.findActive();
    }
    
    @Override
    public List<Organizacion> filtrarModulosByAplicacion(int aplicacionId) {
        return new ArrayList<Organizacion>();
    }
    
    /*@Override
    public List<Catalogo> getCatalogos() {
        return catalogoBss.findActive();
    }*/
    
    @Override
    public List<Modulo> getModulosByAplicacion(int aplicacionId) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Expression.eq("aplicacionId", aplicacionId));
        return moduloBss.findByCriteria(criteria);
    }

    /*
 * Metodos que actualizan los campos <select> en el jsp despues de haber hecho
 * una edición de datos en un popup externo.
     */

    @Override
    public String hasGrid() {
        return hasGrid(SeguridadRolRestriccionParAction.class).toString();
    }

    @Override
    public String isIndividual() {
        return isIndividual(SeguridadRolRestriccionParAction.class).toString();
    }
    
    /*@Override
    public String hasGridCatalogo() {
        return catalogoBss.hasGrid().toString();
    }*/

    @Override
    public String getNombreActionMenu() {
        Pagina pagina = findPageByActionClass(SeguridadRolRestriccionParAction.class);
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
    public void setSeguridadRestriccionesRolDAO(SeguridadRolRestriccionParDAO seguridadRestriccionesRolDAO) {
        this.seguridadRestriccionesRolDAO = seguridadRestriccionesRolDAO;
    }
    
    public void setConfiguracionParametroBss(ConfiguracionParametroBss configuracionParametroBss) {
        this.configuracionParametroBss = configuracionParametroBss;
    }
    
    public void setSeguridadRolBss(SeguridadRolBss seguridadRolBss) {
        this.seguridadRolBss = seguridadRolBss;
    }
    
    
    /*public void setCatalogoBss(CatalogoBss catalogoBss) {
        this.catalogoBss = catalogoBss;
    }*/
    
   public void setModuloBss(ModuloBss moduloBss) {
        this.moduloBss = moduloBss;
    }
    /*public void setConfiguracionParametroBss(ConfiguracionParametroBss configuracionParametroBss) {
        this.configuracionParametroBss = configuracionParametroBss;
    }*/

    /*public void setUsuarioBss(UsuarioBss usuarioBss) {
        this.usuarioBss = usuarioBss;
    }*/


   

}
