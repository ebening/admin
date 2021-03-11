package com.admon.model.admon;


import com.admon.bss.admon.SeguridadPerfilBss;
import com.admon.entity.admon.*;
import com.admon.model.BaseModel;
import java.util.List;

public class SeguridadPerfilDWR extends BaseModel {

    /*
 * Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase.
     */
    private SeguridadPerfilBss seguridadPerfilBss;

    public SeguridadPerfilDWR() {
    }

    /*
 * Guarda la lista <b>seguridadPerfilList</b> en la tabla <b>SeguridadPerfil</b>.
 * @param seguridadPerfilList Lista a guardar.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    public List<Integer> save(List<SeguridadPerfil> seguridadPerfilList) {
        return seguridadPerfilBss.save(obtieneSessionVar().getUsuarioId(), seguridadPerfilList);
    }

    /*
 * Método que actualiza la información de <b>seguridadPerfil</b> en la tabla de <b>SeguridadPerfil</b>.
 * @param seguridadPerfilList Lista de registros que se actualizarán en la tabla de <b>SeguridadPerfil</b> en la BD.
     */
    public List<Integer> update(List<SeguridadPerfil> seguridadPerfilList) {
        return seguridadPerfilBss.update(obtieneSessionVar().getUsuarioId(), seguridadPerfilList);
    }

    /*
 * Método que elimina el registro <b>seguridadPerfil</b> en la tabla de <b>SeguridadPerfil</b>.
 * Este método se llama <b>remove</b> debido a que en javascript (que se encarga de llamar a este método) <b>delete</b> es una palabra reservada.
 * @param seguridadPerfilList Lista de registros que se eliminarán en la tabla de <b>SeguridadPerfil</b> en la BD.
     */
    public void remove(List<Integer> seguridadPerfilIdList) {
        seguridadPerfilBss.delete(obtieneSessionVar().getUsuarioId(), seguridadPerfilIdList);
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
        return seguridadPerfilBss.isValidoNombre(nombre);
    }

    /*
 * Método que busca el registro <b>seguridadPerfil</b> por su ID en la tabla de <b>SeguridadPerfil</b>.
 * @param id es el ID del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>SeguridadPerfil</b> con la información del registro <b>seguridadPerfil</b>.
     */
    public SeguridadPerfil findById(Integer seguridadPerfilId) {
        return seguridadPerfilBss.findById(seguridadPerfilId);
    }

    /*
 * Método que hace una consulta a la tabla SeguridadPerfil y obtiene los registros que coincidan con el objeto <b>seguridadPerfil</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
 * @param p Número de página que se desea mostrar al usuario en el widget JQGrid.
 * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
 * @param order Indica por que campo sera ordenada la búsqueda.
 * @param ordenTipo Tipo de ordenado acendente o decendente.
 * @param SeguridadPerfil objeto que contiene los parámetros a buscar.
 * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    public Grid findByCriteria(int p, int maxResult, String order, String ordenTipo, SeguridadPerfil seguridadPerfil) {
        return seguridadPerfilBss.findByCriteria(p, maxResult, order, ordenTipo, seguridadPerfil);
    }

    /**
     * Método que obtiene el primer resultado de una consulta, utilizado cuando
     * la pagina tiene la propiedad individual
     *
     * @return regresa el primer resultado de la consulta, si no hay resultados
     * regresa null
     */
    public SeguridadPerfil findFirst() {
        return seguridadPerfilBss.findFirst();
    }

    /*
 * Método que actualiza el Estatus de los objetos en la lista
 * <b>seguridadPerfilList</b> en la tabla de <b>SeguridadPerfil</b>.
 * @param userId userId Es el ID del usuario que realizó la operación.
 * @param estatusId Es el ID del estatus al cual se quiere cambiar.
 * @param seguridadPerfilList Es una lista que contiene los registros los cuales se
 * quiere actualizar su Estatus.
     */
    public void setEstatus(Integer estatusId, List<Integer> seguridadPerfilList) {
        seguridadPerfilBss.setEstatus(obtieneSessionVar().getUsuarioId(), estatusId, seguridadPerfilList);
    }

    /*
 * Metodos que actualizan los campos <select> en el jsp despues de haber hecho
 * una edición de datos en un popup externo.
     */
    public String getReportDataTest(String order, String ordenTipo, SeguridadPerfil seguridadPerfil) {
        return seguridadPerfilBss.getReportDataTest(order, ordenTipo, seguridadPerfil);
    }

    /*
 * Inyección de dependencias con Spring, cada
 * referencia definida al inicio de la clase requiere un método
 * Setter.
     */
    public void setSeguridadPerfilBss(SeguridadPerfilBss seguridadPerfilBss) {
        this.seguridadPerfilBss = seguridadPerfilBss;
    }

}
