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
import com.admon.dao.admon.OperacionDAO;
import com.admon.entity.admon.Aplicacion;
import com.admon.entity.admon.ConfiguracionParametro;
import com.admon.entity.admon.Grid;
import com.admon.entity.admon.Modulo;
import com.admon.entity.admon.Operacion;
import com.admon.entity.admon.Pagina;
import com.admon.model.admon.OperacionAction;

public class OperacionBss extends BaseBss  {

    /* Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase. */
    private OperacionDAO operacionDAO;
    private ConfiguracionParametroBss configuracionParametroBss;

    public OperacionBss() {
    }

    /*
 * Método que guarda los objetos contenidos en la lista <b>objectList</b> en la tabla de <b>Operacion</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de objetos que se guardarán en la tabla de <b>Operacion</b> en la BD.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    public List<Long> save(Integer userId, List<Operacion> operacionList) {
        List<Long> savedList = new ArrayList<Long>();
        for (Operacion operacion : operacionList) {
            operacion.setEstatusId(ACTIVO);
            operacion.setEliminadoId(NOELIMINADO);
            operacion.setModificacionUsuario(userId);
            operacion.setCreacionUsuario(userId);
            operacion.setCreacionFecha(new Timestamp(new Date().getTime()));
            operacion.setModificacionFecha(new Timestamp(new Date().getTime()));
            operacionDAO.saveOperacion(operacion);
        }
        return savedList;
    }

    /*
 * Método que actualiza la información de <b>operacion</b> en la tabla de <b>Operacion</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param operacionList Lista de registros que se actualizarán en la tabla de <b>Operacion</b> en la BD.
     */
    public List<Long> update(Integer userId, List<Operacion> operacionList) {
        List<Long> idList = new ArrayList<Long>();
        for (Operacion operacion : operacionList) {
            operacion.setModificacionUsuario(userId);
            operacion.setModificacionFecha(new Timestamp(new Date().getTime()));
            operacionDAO.update(operacion);
            idList.add(operacion.getOperacionId());
        }
        return idList;
    }

    /*
 * Método que elimina el registro <b>operacion</b> en la tabla de <b>Operacion</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param operacionList Lista de registros que se eliminarán en la tabla de <b>Operacion</b> en la BD.
     */
    public void delete(Integer userId, List<Long> operacionIdList) {
        for (Long operacionId : operacionIdList) {
            Operacion operacion = new Operacion();
            operacion.setOperacionId(operacionId);
            operacion.setEliminadoId(ELIMINADO);
            operacion.setModificacionUsuario(userId);
            operacion.setModificacionFecha(new Timestamp(new Date().getTime()));
            operacionDAO.logicDelete(operacion);
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
 * Método que busca el registro <b>operacion</b> por su NOMBRE en la tabla de <b>Operacion</b>.
 * @param nombre es el NOMBRE del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>Operacion</b> con la información de la consulta.
     */
    public Operacion findByName(String nombre) {
        return getFirst(findByCriteria(createDetachedCriteria()
                .add(Expression.eq("nombre", nombre.trim()))));
    }

    /*
 * Busca el registro <b>operacion</b> por su ID en la tabla de <b>Operacion</b>.
 * @param id es el ID del registro a obtener.
 * @return Regresa un objeto <b>Operacion</b> con la información del registro <b>operacion</b>.
     */
    public Operacion findById(Long operacionId) {
        return operacionDAO.getOperacion(operacionId);
    }

    public Operacion findFirst() {
        List<Operacion> operacionList = findByCriteria(createDetachedCriteria());
        if (operacionList.isEmpty()) {
            return null;
        } else {
            return operacionList.get(0);
        }
    }

    public Integer findSizeByCriteria(DetachedCriteria criteria) {
        return operacionDAO.findSizeByCriteria(generalizarCriteria(criteria));
    }

    public List<Operacion> findByCriteriaLimit(DetachedCriteria criteria, Integer from, Integer to) {
        return operacionDAO.findByCriteriaLimit(generalizarCriteria(criteria), from, to);
    }

    /*
 * Método que realiza un búsqueda en la BD obteniendo todos los registros que coincidan con los parámetros
 * definidos en el objeto <b>criteria</b>.
 *
 * @param criteria Es un objeto que contiene los parámetros de búsqueda.
 * @return Regresa una lista de objetos <b>Operacion</b> que coinciden con los parámetros definidos en
 * <b>criteria</b>.
     */
    public List<Operacion> findByCriteria(DetachedCriteria criteria) {
        return operacionDAO.findByCriteria(generalizarCriteria(criteria));
    }
    
    public Grid findByCriteria(int displayedPage, int maxResult, String order, String ordenTipo, Operacion operacion) {
        Grid grid = new Grid();
        
        Integer indexInicio = maxResult * (displayedPage - 1);
        Integer indexFinal = indexInicio+maxResult;
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("indexInicio", indexInicio+1);
        parametros.put("indexFinal", indexFinal);
        int resultadosTotales = operacionDAO.getCount(parametros);
        List<Operacion> list = operacionDAO.getOperaciones(parametros);
        grid.setGrid(list);
        grid.setTotal(resultadosTotales);
        grid.setPaginas(lastPage(resultadosTotales, maxResult));
        return grid;
    }
    
    public List<Aplicacion> getAplicaciones(){
    	return operacionDAO.getAplicaciones();
    }
    
    public List<Modulo> getModulos(Long aplicacionId){
    	return operacionDAO.getModulos(aplicacionId);
    }

    public List<Operacion> findByCriteriaIgnorePrivacy(Operacion operacion) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Example.create(operacion));
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        return operacionDAO.findByCriteria(criteria);
    }

    public DetachedCriteria generalizarCriteria(DetachedCriteria criteria) {
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        resolvePrivacy(criteria, OperacionAction.class);
        return criteria;
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>Operacion</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>Operacion</b>.
     */
    public List<Operacion> findAll() {
        return findByCriteria(createDetachedCriteria().addOrder(Order.asc("nombre")));
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>Operacion</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>Operacion</b>.
     */
    public List<Operacion> findActive() {
        return findByCriteria(createDetachedCriteria().add(
                Expression.eq("estatusId", ACTIVO)).addOrder(Order.asc("nombre")));
    }

    /*
 * Método que obtiene un objeto DetachedCriteria.
 *
 * @return Regresa un objeto DetachedCriteria.
     */
    public DetachedCriteria createDetachedCriteria() {
        return operacionDAO.createDetachedCriteria();
    }

    /*
 * Método que actualiza el Estatus de los ID' contenidos en la lista
 * <b>operacionList</b> en la tabla de <b>Operacion</b>.
 * @param userId userId Es el ID del usuario que realizó la operación.
 * @param estatusId Es el ID del estatus al cual se quiere cambiar.
 * @param operacionList Es una lista que contiene los registros los cuales se
 * quiere actualizar su Estatus.
     */
    public void setEstatus(Integer userId, Integer estatusId, List<Long> operacionList) {
        for (Long operacionId : operacionList) {
            Operacion operacion = findById(operacionId);
            if (estatusId == 1) {
                operacion.setEstatusId(ACTIVO);
            } else {
                operacion.setEstatusId(INACTIVO);
            }
            update(userId, Arrays.asList(operacion));
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
        return hasGrid(OperacionAction.class).toString();
    }

    public String isIndividual() {
        return isIndividual(OperacionAction.class).toString();
    }

    public String getNombreActionMenu() {
        Pagina pagina = findPageByActionClass(OperacionAction.class);
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
    public void setOperacionDAO(OperacionDAO operacionDAO) {
        this.operacionDAO = operacionDAO;
    }

    public void setConfiguracionParametroBss(ConfiguracionParametroBss configuracionParametroBss) {
        this.configuracionParametroBss = configuracionParametroBss;
    }
    
}
