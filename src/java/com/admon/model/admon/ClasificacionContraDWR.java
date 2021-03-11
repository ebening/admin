package com.admon.model.admon;

import com.admon.bss.admon.ClasificacionContraBss;
import com.admon.entity.admon.*;
import com.admon.model.BaseModel;
import java.util.List;

public class ClasificacionContraDWR extends BaseModel {

    /*
 * Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase.
     */
    private ClasificacionContraBss clasificacionContraBss;

    public ClasificacionContraDWR() {
    }

    /*
 * Guarda la lista <b>clasificacionContraList</b> en la tabla <b>ClasificacionContra</b>.
 * @param clasificacionContraList Lista a guardar.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    public List<Integer> save(List<ClasificacionContra> clasificacionContraList) {
        return clasificacionContraBss.save(obtieneSessionVar().getUsuarioId(), clasificacionContraList);
    }

    /*
 * Método que actualiza la información de <b>clasificacionContra</b> en la tabla de <b>ClasificacionContra</b>.
 * @param clasificacionContraList Lista de registros que se actualizarán en la tabla de <b>ClasificacionContra</b> en la BD.
     */
    public List<Integer> update(List<ClasificacionContra> clasificacionContraList) {
        return clasificacionContraBss.update(obtieneSessionVar().getUsuarioId(), clasificacionContraList);
    }

    /*
 * Método que elimina el registro <b>clasificacionContra</b> en la tabla de <b>ClasificacionContra</b>.
 * Este método se llama <b>remove</b> debido a que en javascript (que se encarga de llamar a este método) <b>delete</b> es una palabra reservada.
 * @param clasificacionContraList Lista de registros que se eliminarán en la tabla de <b>ClasificacionContra</b> en la BD.
     */
    public void remove(List<Integer> clasificacionContraIdList) {
        clasificacionContraBss.delete(obtieneSessionVar().getUsuarioId(), clasificacionContraIdList);
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
        return clasificacionContraBss.isValidoNombre(nombre);
    }

    /*
 * Método que busca el registro <b>clasificacionContra</b> por su ID en la tabla de <b>ClasificacionContra</b>.
 * @param id es el ID del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>ClasificacionContra</b> con la información del registro <b>clasificacionContra</b>.
     */
    public ClasificacionContra findById(Integer clasificacionContraId) {
        return clasificacionContraBss.findById(clasificacionContraId);
    }

    /*
 * Método que hace una consulta a la tabla ClasificacionContra y obtiene los registros que coincidan con el objeto <b>clasificacionContra</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
 * @param p Número de página que se desea mostrar al usuario en el widget JQGrid.
 * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
 * @param order Indica por que campo sera ordenada la búsqueda.
 * @param ordenTipo Tipo de ordenado acendente o decendente.
 * @param ClasificacionContra objeto que contiene los parámetros a buscar.
 * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    public Grid findByCriteria(int p, int maxResult, String order, String ordenTipo, ClasificacionContra clasificacionContra) {
        return clasificacionContraBss.findByCriteria(p, maxResult, order, ordenTipo, clasificacionContra);
    }

    /**
     * Método que obtiene el primer resultado de una consulta, utilizado cuando
     * la pagina tiene la propiedad individual
     *
     * @return regresa el primer resultado de la consulta, si no hay resultados
     * regresa null
     */
    public ClasificacionContra findFirst() {
        return clasificacionContraBss.findFirst();
    }

    /*
 * Método que actualiza el Estatus de los objetos en la lista
 * <b>clasificacionContraList</b> en la tabla de <b>ClasificacionContra</b>.
 * @param userId userId Es el ID del usuario que realizó la operación.
 * @param estatusId Es el ID del estatus al cual se quiere cambiar.
 * @param clasificacionContraList Es una lista que contiene los registros los cuales se
 * quiere actualizar su Estatus.
     */
    public void setEstatus(Integer estatusId, List<Integer> clasificacionContraList) {
        clasificacionContraBss.setEstatus(obtieneSessionVar().getUsuarioId(), estatusId, clasificacionContraList);
    }

    /*
 * Metodos que actualizan los campos <select> en el jsp despues de haber hecho
 * una edición de datos en un popup externo.
     */
    public String getReportDataTest(String order, String ordenTipo, ClasificacionContra clasificacionContra) {
        return clasificacionContraBss.getReportDataTest(order, ordenTipo, clasificacionContra);
    }

    /*
 * Inyección de dependencias con Spring, cada
 * referencia definida al inicio de la clase requiere un método
 * Setter.
     */
    public void setClasificacionContraBss(ClasificacionContraBss clasificacionContraBss) {
        this.clasificacionContraBss = clasificacionContraBss;
    }

}
