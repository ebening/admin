package com.admon.model.admon;

import com.admon.bss.admon.SeguridadRolBss;
import com.admon.entity.admon.*;
import com.admon.model.BaseModel;
import java.util.List;

public class SeguridadRolDWR extends BaseModel {

    /*
 * Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase.
     */
    private SeguridadRolBss seguridadRolBss;

    public SeguridadRolDWR() {
    }

    /*
 * Guarda la lista <b>rolList</b> en la tabla <b>Seguridad_Rol</b>.
 * @param rolList Lista a guardar.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    public List<Integer> save(List<SeguridadRol> rolList) {
        return seguridadRolBss.save(obtieneSessionVar().getUsuarioId(), rolList);
    }

    /*
 * Método que actualiza la información de <b>rol</b> en la tabla de <b>Seguridad_Rol</b>.
 * @param rolList Lista de registros que se actualizarán en la tabla de <b>Seguridad_Rol</b> en la BD.
     */
    public List<Integer> update(List<SeguridadRol> rolList) {
        return seguridadRolBss.update(obtieneSessionVar().getUsuarioId(), rolList);
    }

    /*
 * Método que elimina el registro <b>rol</b> en la tabla de <b>Seguridad_Rol</b>.
 * Este método se llama <b>remove</b> debido a que en javascript (que se encarga de llamar a este método) <b>delete</b> es una palabra reservada.
 * @param rolList Lista de registros que se eliminarán en la tabla de <b>Seguridad_Rol</b> en la BD.
     */
    public void remove(List<Integer> rolList) {
        seguridadRolBss.delete(obtieneSessionVar().getUsuarioId(), rolList);
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
        return seguridadRolBss.isValidoNombre(nombre);
    }

    /*
 * Método que busca el registro <b>rol</b> por su ID en la tabla de <b>Seguridad_Rol</b>.
 * @param id es el ID del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>Seguridad_Rol</b> con la información del registro <b>rol</b>.
     */
    public SeguridadRol findById(Integer rolId) {
        return seguridadRolBss.findById(rolId);
    }


    /*
 * Método que hace una consulta a la tabla Seguridad_Rol y obtiene los registros que coincidan con el objeto <b>rol</b> 
    que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto 
    <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
 * @param p Número de página que se desea mostrar al usuario en el widget JQGrid.
 * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
 * @param order Indica por que campo sera ordenada la búsqueda.
 * @param ordenTipo Tipo de ordenado acendente o decendente.
 * @param SeguridadRol objeto que contiene los parámetros a buscar.
 * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    public Grid findByCriteria(int p, int maxResult, String order, String ordenTipo, SeguridadRol rol) {
        return seguridadRolBss.findByCriteria(p, maxResult, order, ordenTipo, rol);
    }

    /**
     * Método que obtiene el primer resultado de una consulta, utilizado cuando
     * la pagina tiene la propiedad individual
     *
     * @return regresa el primer resultado de la consulta, si no hay resultados
     * regresa null
     */
    public SeguridadRol findFirst() {
        return seguridadRolBss.findFirst();
    }

    /*
 * Método que actualiza el Estatus de los objetos en la lista
 * <b>rolList</b> en la tabla de <b>Seguridad_Rol</b>.
 * @param userId userId Es el ID del usuario que realizó la operación.
 * @param estatusId Es el ID del estatus al cual se quiere cambiar.
 * @param userList Es una lista que contiene los registros los cuales se
 * quiere actualizar su Estatus.
     */
    public void setEstatus(Integer estatusId, List<Integer> userList) {
        seguridadRolBss.setEstatus(obtieneSessionVar().getUsuarioId(), estatusId, userList);
    }

    public String getReportDataTest(String order, String ordenTipo, SeguridadRol rol) {
        return seguridadRolBss.getReportDataTest(order, ordenTipo, rol);
    }

    /*
 * Inyección de dependencias con Spring, cada
 * referencia definida al inicio de la clase requiere un método
 * Setter.
     */
    public void setSeguridadRolBss(SeguridadRolBss seguridadRolBss) {
        this.seguridadRolBss = seguridadRolBss;
    }
    
     /*
* Obtiene una lista de todos los registros válidos (activos y no eliminados) de la tabla PAGINA.
*
* @return regresa una lista de todos los registros de la tabla PAGINA
     */
    public List<Pagina> obtienePaginas() {
        List<Pagina> a=seguridadRolBss.obtienePaginas();
        return a;
    }

}
