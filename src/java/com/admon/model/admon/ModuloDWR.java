package com.admon.model.admon;

import java.util.List;

import com.admon.bss.admon.ModuloBss;
import com.admon.entity.admon.Aplicacion;
import com.admon.entity.admon.Grid;
import com.admon.entity.admon.Modulo;
import com.admon.model.BaseModel;

public class ModuloDWR extends BaseModel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
 * Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase.
     */
    private ModuloBss moduloBss;

    public ModuloDWR() {
    }

    /*
 * Guarda la lista <b>moduloList</b> en la tabla <b>Modulo</b>.
 * @param moduloList Lista a guardar.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    public List<Long> save(List<Modulo> moduloList) {
        return moduloBss.save(obtieneSessionVar().getUsuarioId(), moduloList);
    }

    /*
 * Método que actualiza la información de <b>modulo</b> en la tabla de <b>Modulo</b>.
 * @param moduloList Lista de registros que se actualizarán en la tabla de <b>Modulo</b> en la BD.
     */
    public List<Long> update(List<Modulo> moduloList) {
        return moduloBss.update(obtieneSessionVar().getUsuarioId(), moduloList);
    }

    /*
 * Método que elimina el registro <b>modulo</b> en la tabla de <b>Modulo</b>.
 * Este método se llama <b>remove</b> debido a que en javascript (que se encarga de llamar a este método) <b>delete</b> es una palabra reservada.
 * @param moduloList Lista de registros que se eliminarán en la tabla de <b>Modulo</b> en la BD.
     */
    public void remove(List<Long> moduloIdList) {
        moduloBss.delete(obtieneSessionVar().getUsuarioId(), moduloIdList);
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
        return moduloBss.isValidoNombre(nombre);
    }

    /*
 * Método que busca el registro <b>modulo</b> por su ID en la tabla de <b>Modulo</b>.
 * @param id es el ID del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>Modulo</b> con la información del registro <b>modulo</b>.
     */
    public Modulo findById(Long moduloId) {
        return moduloBss.findById(moduloId);
    }

    /**
     * Método que obtiene el primer resultado de una consulta, utilizado cuando
     * la pagina tiene la propiedad individual
     *
     * @return regresa el primer resultado de la consulta, si no hay resultados
     * regresa null
     */
    public Modulo findFirst() {
        return moduloBss.findFirst();
    }

    /*
 * Método que actualiza el Estatus de los objetos en la lista
 * <b>moduloList</b> en la tabla de <b>Modulo</b>.
 * @param userId userId Es el ID del usuario que realizó la operación.
 * @param estatusId Es el ID del estatus al cual se quiere cambiar.
 * @param moduloList Es una lista que contiene los registros los cuales se
 * quiere actualizar su Estatus.
     */
    public void setEstatus(Integer estatusId, List<Long> moduloList) {
        moduloBss.setEstatus(obtieneSessionVar().getUsuarioId(), estatusId, moduloList);
    }

    /*
 * Inyección de dependencias con Spring, cada
 * referencia definida al inicio de la clase requiere un método
 * Setter.
     */
    public void setModuloBss(ModuloBss moduloBss) {
        this.moduloBss = moduloBss;
    }
    
    public List<Modulo> getModulos(Long aplicacionId){
    	return moduloBss.getModulos(aplicacionId);
    }
    
    public Grid findByCriteria(int p, int maxResult, String order, String ordenTipo, Modulo modulo) {
        return moduloBss.findByCriteria(p, maxResult, order, ordenTipo, modulo);
    }

}
