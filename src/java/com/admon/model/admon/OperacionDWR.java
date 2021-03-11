package com.admon.model.admon;

import java.util.List;

import com.admon.bss.admon.OperacionBss;
import com.admon.entity.admon.Aplicacion;
import com.admon.entity.admon.Grid;
import com.admon.entity.admon.Modulo;
import com.admon.entity.admon.Operacion;
import com.admon.model.BaseModel;

public class OperacionDWR extends BaseModel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
 * Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase.
     */
    private OperacionBss operacionBss;

    public OperacionDWR() {
    }

    /*
 * Guarda la lista <b>operacionList</b> en la tabla <b>Operacion</b>.
 * @param operacionList Lista a guardar.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    public List<Long> save(List<Operacion> operacionList) {
        return operacionBss.save(obtieneSessionVar().getUsuarioId(), operacionList);
    }

    /*
 * Método que actualiza la información de <b>operacion</b> en la tabla de <b>Operacion</b>.
 * @param operacionList Lista de registros que se actualizarán en la tabla de <b>Operacion</b> en la BD.
     */
    public List<Long> update(List<Operacion> operacionList) {
        return operacionBss.update(obtieneSessionVar().getUsuarioId(), operacionList);
    }

    /*
 * Método que elimina el registro <b>operacion</b> en la tabla de <b>Operacion</b>.
 * Este método se llama <b>remove</b> debido a que en javascript (que se encarga de llamar a este método) <b>delete</b> es una palabra reservada.
 * @param operacionList Lista de registros que se eliminarán en la tabla de <b>Operacion</b> en la BD.
     */
    public void remove(List<Long> operacionIdList) {
        operacionBss.delete(obtieneSessionVar().getUsuarioId(), operacionIdList);
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
        return operacionBss.isValidoNombre(nombre);
    }

    /*
 * Método que busca el registro <b>operacion</b> por su ID en la tabla de <b>Operacion</b>.
 * @param id es el ID del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>Operacion</b> con la información del registro <b>operacion</b>.
     */
    public Operacion findById(Long operacionId) {
        return operacionBss.findById(operacionId);
    }

    /**
     * Método que obtiene el primer resultado de una consulta, utilizado cuando
     * la pagina tiene la propiedad individual
     *
     * @return regresa el primer resultado de la consulta, si no hay resultados
     * regresa null
     */
    public Operacion findFirst() {
        return operacionBss.findFirst();
    }

    /*
 * Método que actualiza el Estatus de los objetos en la lista
 * <b>operacionList</b> en la tabla de <b>Operacion</b>.
 * @param userId userId Es el ID del usuario que realizó la operación.
 * @param estatusId Es el ID del estatus al cual se quiere cambiar.
 * @param operacionList Es una lista que contiene los registros los cuales se
 * quiere actualizar su Estatus.
     */
    public void setEstatus(Integer estatusId, List<Long> operacionList) {
        operacionBss.setEstatus(obtieneSessionVar().getUsuarioId(), estatusId, operacionList);
    }

    /*
 * Inyección de dependencias con Spring, cada
 * referencia definida al inicio de la clase requiere un método
 * Setter.
     */
    public void setOperacionBss(OperacionBss operacionBss) {
        this.operacionBss = operacionBss;
    }
    
    public List<Modulo> getModulos(Long aplicacionId){
    	return operacionBss.getModulos(aplicacionId);
    }
    
    public List<Aplicacion> getAplicaciones(){
    	return operacionBss.getAplicaciones();
    }
    
    public Grid findByCriteria(int p, int maxResult, String order, String ordenTipo, Operacion operacion) {
        return operacionBss.findByCriteria(p, maxResult, order, ordenTipo, operacion);
    }

}
