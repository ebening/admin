package com.admon.model.admon;

import com.admon.entity.admon.*;
import com.admon.model.BaseModel;
import java.util.List;
import com.admon.bss.admon.CiudadBss;

public class CiudadDWR extends BaseModel {

    /*
 * Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase.
     */
    private CiudadBss ciudadBss;

    public CiudadDWR() {
    }

    /*
 * Guarda la lista <b>ciudadList</b> en la tabla <b>Ciudad</b>.
 * @param ciudadList Lista a guardar.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    public List<Integer> save(List<Ciudad> ciudadList) {
        return ciudadBss.save(obtieneSessionVar().getUsuarioId(), ciudadList);
    }

    /*
 * Método que actualiza la información de <b>ciudad</b> en la tabla de <b>Ciudad</b>.
 * @param ciudadList Lista de registros que se actualizarán en la tabla de <b>Ciudad</b> en la BD.
     */
    public List<Integer> update(List<Ciudad> ciudadList) {
        return ciudadBss.update(obtieneSessionVar().getUsuarioId(), ciudadList);
    }

    /*
 * Método que elimina el registro <b>ciudad</b> en la tabla de <b>Ciudad</b>.
 * Este método se llama <b>remove</b> debido a que en javascript (que se encarga de llamar a este método) <b>delete</b> es una palabra reservada.
 * @param ciudadList Lista de registros que se eliminarán en la tabla de <b>Ciudad</b> en la BD.
     */
    public void remove(List<Integer> ciudadIdList) {
        ciudadBss.delete(obtieneSessionVar().getUsuarioId(), ciudadIdList);
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
        return ciudadBss.isValidoNombre(nombre);
    }

    /*
 * Método que busca el registro <b>ciudad</b> por su ID en la tabla de <b>Ciudad</b>.
 * @param id es el ID del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>Ciudad</b> con la información del registro <b>ciudad</b>.
     */
    public Ciudad findById(Integer ciudadId) {
        return ciudadBss.findById(ciudadId);
    }

    /*
 * Método que hace una consulta a la tabla Ciudad y obtiene los registros que coincidan con el objeto <b>ciudad</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
 * @param p Número de página que se desea mostrar al usuario en el widget JQGrid.
 * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
 * @param order Indica por que campo sera ordenada la búsqueda.
 * @param ordenTipo Tipo de ordenado acendente o decendente.
 * @param Ciudad objeto que contiene los parámetros a buscar.
 * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    public Grid findByCriteria(int p, int maxResult, String order, String ordenTipo, Ciudad ciudad) {
        return ciudadBss.findByCriteria(p, maxResult, order, ordenTipo, ciudad);
    }

    /**
     * Método que obtiene el primer resultado de una consulta, utilizado cuando
     * la pagina tiene la propiedad individual
     *
     * @return regresa el primer resultado de la consulta, si no hay resultados
     * regresa null
     */
    public Ciudad findFirst() {
        return ciudadBss.findFirst();
    }

    /*
 * Método que actualiza el Estatus de los objetos en la lista
 * <b>ciudadList</b> en la tabla de <b>Ciudad</b>.
 * @param userId userId Es el ID del usuario que realizó la operación.
 * @param estatusId Es el ID del estatus al cual se quiere cambiar.
 * @param ciudadList Es una lista que contiene los registros los cuales se
 * quiere actualizar su Estatus.
     */
    public void setEstatus(Integer estatusId, List<Integer> ciudadList) {
        ciudadBss.setEstatus(obtieneSessionVar().getUsuarioId(), estatusId, ciudadList);
    }

    /*
 * Metodos que actualizan los campos <select> en el jsp despues de haber hecho
 * una edición de datos en un popup externo.
     */
    public List<Pais> getPais() {
        return ciudadBss.getPais();
    }

    public List<Estado> getEstado() {
        return ciudadBss.getEstado();
    }

    public List<Estado> filtrarEstado(int paisId) {
        return ciudadBss.filtrarEstado(paisId);
    }

    public String getReportDataTest(String order, String ordenTipo, Ciudad ciudad) {
        return ciudadBss.getReportDataTest(order, ordenTipo, ciudad);
    }

    /*
 * Inyección de dependencias con Spring, cada
 * referencia definida al inicio de la clase requiere un método
 * Setter.
     */
    public void setCiudadBss(CiudadBss ciudadBss) {
        this.ciudadBss = ciudadBss;
    }

}
