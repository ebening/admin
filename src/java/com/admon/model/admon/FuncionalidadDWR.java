package com.admon.model.admon;

import java.util.List;

import com.admon.bss.admon.FuncionalidadBss;
import com.admon.entity.admon.Aplicacion;
import com.admon.entity.admon.Grid;
import com.admon.entity.admon.Modulo;
import com.admon.entity.admon.Funcionalidad;
import com.admon.entity.admon.Operacion;
import com.admon.model.BaseModel;

public class FuncionalidadDWR extends BaseModel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
 * Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase.
     */
    private FuncionalidadBss funcionalidadBss;

    public FuncionalidadDWR() {
    }

    /*
 * Guarda la lista <b>funcionalidadList</b> en la tabla <b>Funcionalidad</b>.
 * @param funcionalidadList Lista a guardar.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    public List<Long> save(List<Funcionalidad> funcionalidadList) {
        return funcionalidadBss.save(obtieneSessionVar().getUsuarioId(), funcionalidadList);
    }

    /*
 * Método que actualiza la información de <b>funcionalidad</b> en la tabla de <b>Funcionalidad</b>.
 * @param funcionalidadList Lista de registros que se actualizarán en la tabla de <b>Funcionalidad</b> en la BD.
     */
    public List<Long> update(List<Funcionalidad> funcionalidadList) {
        return funcionalidadBss.update(obtieneSessionVar().getUsuarioId(), funcionalidadList);
    }

    /*
 * Método que elimina el registro <b>funcionalidad</b> en la tabla de <b>Funcionalidad</b>.
 * Este método se llama <b>remove</b> debido a que en javascript (que se encarga de llamar a este método) <b>delete</b> es una palabra reservada.
 * @param funcionalidadList Lista de registros que se eliminarán en la tabla de <b>Funcionalidad</b> en la BD.
     */
    public void remove(List<Long> funcionalidadIdList) {
        funcionalidadBss.delete(obtieneSessionVar().getUsuarioId(), funcionalidadIdList);
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
        return funcionalidadBss.isValidoNombre(nombre);
    }

    /*
 * Método que busca el registro <b>funcionalidad</b> por su ID en la tabla de <b>Funcionalidad</b>.
 * @param id es el ID del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>Funcionalidad</b> con la información del registro <b>funcionalidad</b>.
     */
    public Funcionalidad findById(Long funcionalidadId) {
        return funcionalidadBss.findById(funcionalidadId);
    }

    /**
     * Método que obtiene el primer resultado de una consulta, utilizado cuando
     * la pagina tiene la propiedad individual
     *
     * @return regresa el primer resultado de la consulta, si no hay resultados
     * regresa null
     */
    public Funcionalidad findFirst() {
        return funcionalidadBss.findFirst();
    }

    /*
 * Método que actualiza el Estatus de los objetos en la lista
 * <b>funcionalidadList</b> en la tabla de <b>Funcionalidad</b>.
 * @param userId userId Es el ID del usuario que realizó la operación.
 * @param estatusId Es el ID del estatus al cual se quiere cambiar.
 * @param funcionalidadList Es una lista que contiene los registros los cuales se
 * quiere actualizar su Estatus.
     */
    public void setEstatus(Integer estatusId, List<Long> funcionalidadList) {
        funcionalidadBss.setEstatus(obtieneSessionVar().getUsuarioId(), estatusId, funcionalidadList);
    }

    /*
 * Inyección de dependencias con Spring, cada
 * referencia definida al inicio de la clase requiere un método
 * Setter.
     */
    public void setFuncionalidadBss(FuncionalidadBss funcionalidadBss) {
        this.funcionalidadBss = funcionalidadBss;
    }
    
    public List<Operacion> getOperaciones(Long muduloId){
    	return funcionalidadBss.getOperaciones(muduloId);
    }
        
    public List<Modulo> getModulos(Long aplicacionId){
    	return funcionalidadBss.getModulos(aplicacionId);
    }
    
    public List<Aplicacion> getAplicaciones(){
    	return funcionalidadBss.getAplicaciones();
    }
    
    public Grid findByCriteria(int p, int maxResult, String order, String ordenTipo, Funcionalidad funcionalidad) {
        return funcionalidadBss.findByCriteria(p, maxResult, order, ordenTipo, funcionalidad);
    }

}
