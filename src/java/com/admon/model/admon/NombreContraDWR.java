package com.admon.model.admon;

import com.admon.bss.admon.NombreContraBss;
import com.admon.entity.admon.*;
import com.admon.model.BaseModel;
import java.util.List;

public class NombreContraDWR extends BaseModel {

    /*
 * Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase.
     */
    private NombreContraBss nombreContraBss;

    public NombreContraDWR() {
    }

    /*
 * Guarda la lista <b>nombreContraList</b> en la tabla <b>NombreContra</b>.
 * @param nombreContraList Lista a guardar.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    public List<Integer> save(List<NombreContra> nombreContraList) {
        return nombreContraBss.save(obtieneSessionVar().getUsuarioId(), nombreContraList);
    }

    /*
 * Método que actualiza la información de <b>nombreContra</b> en la tabla de <b>NombreContra</b>.
 * @param nombreContraList Lista de registros que se actualizarán en la tabla de <b>NombreContra</b> en la BD.
     */
    public List<Integer> update(List<NombreContra> nombreContraList) {
        return nombreContraBss.update(obtieneSessionVar().getUsuarioId(), nombreContraList);
    }

    /*
 * Método que elimina el registro <b>nombreContra</b> en la tabla de <b>NombreContra</b>.
 * Este método se llama <b>remove</b> debido a que en javascript (que se encarga de llamar a este método) <b>delete</b> es una palabra reservada.
 * @param nombreContraList Lista de registros que se eliminarán en la tabla de <b>NombreContra</b> en la BD.
     */
    public void remove(List<Integer> nombreContraIdList) {
        nombreContraBss.delete(obtieneSessionVar().getUsuarioId(), nombreContraIdList);
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
        return nombreContraBss.isValidoNombre(nombre);
    }

    /*
 * Método que busca el registro <b>nombreContra</b> por su ID en la tabla de <b>NombreContra</b>.
 * @param id es el ID del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>NombreContra</b> con la información del registro <b>nombreContra</b>.
     */
    public NombreContra findById(Integer nombreContraId) {
        return nombreContraBss.findById(nombreContraId);
    }

    /*
 * Método que hace una consulta a la tabla NombreContra y obtiene los registros que coincidan con el objeto <b>nombreContra</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
 * @param p Número de página que se desea mostrar al usuario en el widget JQGrid.
 * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
 * @param order Indica por que campo sera ordenada la búsqueda.
 * @param ordenTipo Tipo de ordenado acendente o decendente.
 * @param NombreContra objeto que contiene los parámetros a buscar.
 * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    public Grid findByCriteria(int p, int maxResult, String order, String ordenTipo, NombreContra nombreContra) {
        return nombreContraBss.findByCriteria(p, maxResult, order, ordenTipo, nombreContra);
    }

    /**
     * Método que obtiene el primer resultado de una consulta, utilizado cuando
     * la pagina tiene la propiedad individual
     *
     * @return regresa el primer resultado de la consulta, si no hay resultados
     * regresa null
     */
    public NombreContra findFirst() {
        return nombreContraBss.findFirst();
    }

    /*
 * Método que actualiza el Estatus de los objetos en la lista
 * <b>nombreContraList</b> en la tabla de <b>NombreContra</b>.
 * @param userId userId Es el ID del usuario que realizó la operación.
 * @param estatusId Es el ID del estatus al cual se quiere cambiar.
 * @param nombreContraList Es una lista que contiene los registros los cuales se
 * quiere actualizar su Estatus.
     */
    public void setEstatus(Integer estatusId, List<Integer> nombreContraList) {
        nombreContraBss.setEstatus(obtieneSessionVar().getUsuarioId(), estatusId, nombreContraList);
    }

    /*
 * Metodos que actualizan los campos <select> en el jsp despues de haber hecho
 * una edición de datos en un popup externo.
     */
    public List<ClasificacionContra> getClasificacionContra() {
        return nombreContraBss.getClasificacionContra();
    }

    public List<SubclasificacionContra> getSubclasificacionContra() {
        return nombreContraBss.getSubclasificacionContra();
    }

    public List<SubclasificacionContra> filtrarSubclasificacionContra(int clasificacionContraId) {
        return nombreContraBss.filtrarSubclasificacionContra(clasificacionContraId);
    }

    public String getReportDataTest(String order, String ordenTipo, NombreContra nombreContra) {
        return nombreContraBss.getReportDataTest(order, ordenTipo, nombreContra);
    }

    /*
 * Inyección de dependencias con Spring, cada
 * referencia definida al inicio de la clase requiere un método
 * Setter.
     */
    public void setNombreContraBss(NombreContraBss nombreContraBss) {
        this.nombreContraBss = nombreContraBss;
    }

}
