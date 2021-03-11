package com.admon.bss.admon;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import com.admon.bss.BaseBss;
import com.admon.dao.admon.ModuloDAO;
import com.admon.entity.admon.Aplicacion;
import com.admon.entity.admon.ConfiguracionParametro;
import com.admon.entity.admon.Grid;
import com.admon.entity.admon.Modulo;
import com.admon.entity.admon.Organizacion;
import com.admon.entity.admon.Pagina;
import com.admon.model.admon.ModuloAction;

public class ModuloBss extends BaseBss  {

    /* Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase. */
    private ModuloDAO moduloDAO;
    private ConfiguracionParametroBss configuracionParametroBss;
    private OrganizacionBss organizacionBss;

    public ModuloBss() {
    }

    /*
 * Método que guarda los objetos contenidos en la lista <b>objectList</b> en la tabla de <b>Modulo</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de objetos que se guardarán en la tabla de <b>Modulo</b> en la BD.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    public List<Long> save(Integer userId, List<Modulo> moduloList) {
        List<Long> savedList = new ArrayList<Long>();
        for (Modulo modulo : moduloList) {
            modulo.setEstatusId(ACTIVO);
            modulo.setEliminadoId(NOELIMINADO);
            modulo.setModificacionUsuario(userId);
            modulo.setCreacionUsuario(userId);
            modulo.setCreacionFecha(new Timestamp(new Date().getTime()));
            modulo.setModificacionFecha(new Timestamp(new Date().getTime()));
            moduloDAO.saveModulo(modulo);
        }
        return savedList;
    }

    /*
 * Método que actualiza la información de <b>modulo</b> en la tabla de <b>Modulo</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param moduloList Lista de registros que se actualizarán en la tabla de <b>Modulo</b> en la BD.
     */
    public List<Long> update(Integer userId, List<Modulo> moduloList) {
        List<Long> idList = new ArrayList<Long>();
        for (Modulo modulo : moduloList) {
            modulo.setModificacionUsuario(userId);
            modulo.setModificacionFecha(new Timestamp(new Date().getTime()));
            moduloDAO.update(modulo);
            idList.add(modulo.getModuloId());
        }
        return idList;
    }

    /*
 * Método que elimina el registro <b>modulo</b> en la tabla de <b>Modulo</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param moduloList Lista de registros que se eliminarán en la tabla de <b>Modulo</b> en la BD.
     */
    public void delete(Integer userId, List<Long> moduloIdList) {
        for (Long moduloId : moduloIdList) {
            Modulo modulo = new Modulo();
            modulo.setModuloId(moduloId);
            modulo.setEliminadoId(ELIMINADO);
            modulo.setModificacionUsuario(userId);
            modulo.setModificacionFecha(new Timestamp(new Date().getTime()));
            moduloDAO.logicDelete(modulo);
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
    public boolean isValidoNombre(String nombre) {
        return !(findByCriteria(createDetachedCriteria().add(
                Expression.eq("nombre", nombre.trim()))).size() >= 1);
    }

    /*
 * Método que busca el registro <b>modulo</b> por su NOMBRE en la tabla de <b>Modulo</b>.
 * @param nombre es el NOMBRE del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>Modulo</b> con la información de la consulta.
     */
    public Modulo findByName(String nombre) {
        return getFirst(findByCriteria(createDetachedCriteria()
                .add(Expression.eq("nombre", nombre.trim()))));
    }

    /*
 * Busca el registro <b>modulo</b> por su ID en la tabla de <b>Modulo</b>.
 * @param id es el ID del registro a obtener.
 * @return Regresa un objeto <b>Modulo</b> con la información del registro <b>modulo</b>.
     */
    public Modulo findById(Long moduloId) {
        return moduloDAO.getModulo(moduloId);
    }

    public Modulo findFirst() {
        List<Modulo> moduloList = findByCriteria(createDetachedCriteria());
        if (moduloList.isEmpty()) {
            return null;
        } else {
            return moduloList.get(0);
        }
    }

    public Integer findSizeByCriteria(DetachedCriteria criteria) {
        return moduloDAO.findSizeByCriteria(generalizarCriteria(criteria));
    }

    public List<Modulo> findByCriteriaLimit(DetachedCriteria criteria, Integer from, Integer to) {
        return moduloDAO.findByCriteriaLimit(generalizarCriteria(criteria), from, to);
    }

    /*
 * Método que realiza un búsqueda en la BD obteniendo todos los registros que coincidan con los parámetros
 * definidos en el objeto <b>criteria</b>.
 *
 * @param criteria Es un objeto que contiene los parámetros de búsqueda.
 * @return Regresa una lista de objetos <b>Modulo</b> que coinciden con los parámetros definidos en
 * <b>criteria</b>.
     */
    public List<Modulo> findByCriteria(DetachedCriteria criteria) {
        return moduloDAO.findByCriteria(generalizarCriteria(criteria));
    }
    
    public Grid findByCriteria(int displayedPage, int maxResult, String order, String ordenTipo, Modulo modulo) {
        Grid grid = new Grid();
        
        Integer indexInicio = maxResult * (displayedPage - 1);
        Integer indexFinal = indexInicio+maxResult;
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("indexInicio", indexInicio+1);
        parametros.put("indexFinal", indexFinal);
        int resultadosTotales = moduloDAO.getCount(parametros);
        List<Modulo> list = moduloDAO.getModulos(parametros);
        grid.setGrid(list);
        grid.setTotal(resultadosTotales);
        grid.setPaginas(lastPage(resultadosTotales, maxResult));
        return grid;
    }
    
    public List<Organizacion> getOrganizaciones(){
    	return moduloDAO.getOrganizaciones();
    }

    public List<Aplicacion> getAplicaciones(){
    	return moduloDAO.getAplicaciones();
    }
    
    public List<Modulo> getModulos(Long aplicacionId){
    	return moduloDAO.getModulos(aplicacionId);
    }

    public List<Modulo> findByCriteriaIgnorePrivacy(Modulo modulo) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Example.create(modulo));
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        return moduloDAO.findByCriteria(criteria);
    }

    public DetachedCriteria generalizarCriteria(DetachedCriteria criteria) {
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        resolvePrivacy(criteria, ModuloAction.class);
        return criteria;
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>Modulo</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>Modulo</b>.
     */
    public List<Modulo> findAll() {
        return findByCriteria(createDetachedCriteria().addOrder(Order.asc("nombre")));
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>Modulo</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>Modulo</b>.
     */
    public List<Modulo> findActive() {
        return findByCriteria(createDetachedCriteria().add(
                Expression.eq("estatusId", ACTIVO)).addOrder(Order.asc("nombre")));
    }

    /*
 * Método que obtiene un objeto DetachedCriteria.
 *
 * @return Regresa un objeto DetachedCriteria.
     */
    public DetachedCriteria createDetachedCriteria() {
        return moduloDAO.createDetachedCriteria();
    }

    /*
 * Método que actualiza el Estatus de los ID' contenidos en la lista
 * <b>moduloList</b> en la tabla de <b>Modulo</b>.
 * @param userId userId Es el ID del usuario que realizó la operación.
 * @param estatusId Es el ID del estatus al cual se quiere cambiar.
 * @param moduloList Es una lista que contiene los registros los cuales se
 * quiere actualizar su Estatus.
     */
    public void setEstatus(Integer userId, Integer estatusId, List<Long> moduloList) {
        for (Long moduloId : moduloList) {
            Modulo modulo = findById(moduloId);
            if (estatusId == 1) {
                modulo.setEstatusId(ACTIVO);
            } else {
                modulo.setEstatusId(INACTIVO);
            }
            update(userId, Arrays.asList(modulo));
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
    public List<ConfiguracionParametro> getParametros(String catalogKey) {
        return addDummy(configuracionParametroBss.getParametros(catalogKey), new ConfiguracionParametro(), ConfiguracionParametro.class);
    }

    /*
 * Metodos que actualizan los campos <select> en el jsp despues de haber hecho
 * una edición de datos en un popup externo.
     */

    public String hasGrid() {
        return hasGrid(ModuloAction.class).toString();
    }

    public String isIndividual() {
        return isIndividual(ModuloAction.class).toString();
    }

    public String getNombreActionMenu() {
        Pagina pagina = findPageByActionClass(ModuloAction.class);
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
    public void setModuloDAO(ModuloDAO moduloDAO) {
        this.moduloDAO = moduloDAO;
    }

    public void setConfiguracionParametroBss(ConfiguracionParametroBss configuracionParametroBss) {
        this.configuracionParametroBss = configuracionParametroBss;
    }
    
    public void setOrganizacionBss(OrganizacionBss organizacionBss) {
        this.organizacionBss = organizacionBss;
    }
    
}
