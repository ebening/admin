package com.admon.model.admon;


import com.admon.bss.admon.SeguridadPerfilRolBss;
import com.admon.entity.admon.*;
import com.admon.model.BaseModel;
import java.util.List;

public class SeguridadPerfilRolDWR extends BaseModel {

    /*
 * Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase.
     */
    private SeguridadPerfilRolBss seguridadPerfilRolBss;

    public SeguridadPerfilRolDWR() {
    }

    /*
 * Guarda la lista <b>seguridadPerfilRolList</b> en la tabla <b>SeguridadPerfilRol</b>.
 * @param seguridadPerfilRolList Lista a guardar.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    public List<Integer> save(List<SeguridadPerfilRol> seguridadPerfilRolList) {
        return seguridadPerfilRolBss.save(obtieneSessionVar().getUsuarioId(), seguridadPerfilRolList);
    }

    /*
 * Método que actualiza la información de <b>seguridadPerfilRol</b> en la tabla de <b>SeguridadPerfilRol</b>.
 * @param seguridadPerfilRolList Lista de registros que se actualizarán en la tabla de <b>SeguridadPerfilRol</b> en la BD.
     */
    public List<Integer> update(List<SeguridadPerfilRol> seguridadPerfilRolList) {
        return seguridadPerfilRolBss.update(obtieneSessionVar().getUsuarioId(), seguridadPerfilRolList);
    }

    /*
 * Método que elimina el registro <b>seguridadPerfilRol</b> en la tabla de <b>SeguridadPerfilRol</b>.
 * Este método se llama <b>remove</b> debido a que en javascript (que se encarga de llamar a este método) <b>delete</b> es una palabra reservada.
 * @param seguridadPerfilRolList Lista de registros que se eliminarán en la tabla de <b>SeguridadPerfilRol</b> en la BD.
     */
    public void remove(List<Integer> seguridadPerfilRolIdList) {
        seguridadPerfilRolBss.delete(obtieneSessionVar().getUsuarioId(), seguridadPerfilRolIdList);
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
        return seguridadPerfilRolBss.isValidoNombre(nombre);
    }

    /*
 * Método que busca el registro <b>seguridadPerfilRol</b> por su ID en la tabla de <b>SeguridadPerfilRol</b>.
 * @param id es el ID del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>SeguridadPerfilRol</b> con la información del registro <b>seguridadPerfilRol</b>.
     */
    public SeguridadPerfilRol findById(Integer seguridadPerfilRolId) {
        return seguridadPerfilRolBss.findById(seguridadPerfilRolId);
    }

    /*
 * Método que hace una consulta a la tabla SeguridadPerfilRol y obtiene los registros que coincidan con el objeto <b>seguridadPerfilRol</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
 * @param p Número de página que se desea mostrar al usuario en el widget JQGrid.
 * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
 * @param order Indica por que campo sera ordenada la búsqueda.
 * @param ordenTipo Tipo de ordenado acendente o decendente.
 * @param SeguridadPerfilRol objeto que contiene los parámetros a buscar.
 * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    public Grid findByCriteria(int p, int maxResult, String order, String ordenTipo, SeguridadPerfilRol seguridadPerfilRol) {
        return seguridadPerfilRolBss.findByCriteria(p, maxResult, order, ordenTipo, seguridadPerfilRol);
    }

    /**
     * Método que obtiene el primer resultado de una consulta, utilizado cuando
     * la pagina tiene la propiedad individual
     *
     * @return regresa el primer resultado de la consulta, si no hay resultados
     * regresa null
     */
    public SeguridadPerfilRol findFirst() {
        return seguridadPerfilRolBss.findFirst();
    }

    /*
 * Metodos que actualizan los campos <select> en el jsp despues de haber hecho
 * una edición de datos en un popup externo.
     */
    public String getReportDataTest(String order, String ordenTipo, SeguridadPerfilRol seguridadPerfilRol) {
        return seguridadPerfilRolBss.getReportDataTest(order, ordenTipo, seguridadPerfilRol);
    }

    /*
 * Inyección de dependencias con Spring, cada
 * referencia definida al inicio de la clase requiere un método
 * Setter.
     */

    /**
     * @param seguridadPerfilRolBss the seguridadPerfilRolBss to set
     */
    public void setSeguridadPerfilRolBss(SeguridadPerfilRolBss seguridadPerfilRolBss) {
        this.seguridadPerfilRolBss = seguridadPerfilRolBss;
    }
   

}
