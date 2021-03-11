package com.admon.model.admon;

import com.admon.bss.admon.OrganizacionUsuarioBss;
import com.admon.entity.admon.*;
import com.admon.model.BaseModel;
import java.util.List;

public class OrganizacionUsuarioDWR extends BaseModel {

    /*
 * Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase.
     */
    private OrganizacionUsuarioBss organizacionUsuarioBss;

    public OrganizacionUsuarioDWR() {
    }

    /*
 * Guarda la lista <b>organizacionUsuarioList</b> en la tabla <b>OrganizacionUsuario</b>.
 * @param organizacionUsuarioList Lista a guardar.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    public List<Integer> save(List<OrganizacionUsuario> organizacionUsuarioList) {
        return organizacionUsuarioBss.save(obtieneSessionVar().getUsuarioId(), organizacionUsuarioList);
    }

    /*
 * Método que actualiza la información de <b>organizacionUsuario</b> en la tabla de <b>OrganizacionUsuario</b>.
 * @param organizacionUsuarioList Lista de registros que se actualizarán en la tabla de <b>OrganizacionUsuario</b> en la BD.
     */
    public List<Integer> update(List<OrganizacionUsuario> organizacionUsuarioList) {
        return organizacionUsuarioBss.update(obtieneSessionVar().getUsuarioId(), organizacionUsuarioList);
    }

    /*
 * Método que elimina el registro <b>organizacionUsuario</b> en la tabla de <b>OrganizacionUsuario</b>.
 * Este método se llama <b>remove</b> debido a que en javascript (que se encarga de llamar a este método) <b>delete</b> es una palabra reservada.
 * @param organizacionUsuarioList Lista de registros que se eliminarán en la tabla de <b>OrganizacionUsuario</b> en la BD.
     */
    public void remove(List<Integer> organizacionUsuarioIdList) {
        organizacionUsuarioBss.delete(obtieneSessionVar().getUsuarioId(), organizacionUsuarioIdList);
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
        return organizacionUsuarioBss.isValidoNombre(nombre);
    }

    /*
 * Método que busca el registro <b>organizacionUsuario</b> por su ID en la tabla de <b>OrganizacionUsuario</b>.
 * @param id es el ID del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>OrganizacionUsuario</b> con la información del registro <b>organizacionUsuario</b>.
     */
    public OrganizacionUsuario findById(Integer organizacionUsuarioId) {
        return organizacionUsuarioBss.findById(organizacionUsuarioId);
    }

    /*
 * Método que hace una consulta a la tabla OrganizacionUsuario y obtiene los registros que coincidan con el objeto <b>organizacionUsuario</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
 * @param p Número de página que se desea mostrar al usuario en el widget JQGrid.
 * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
 * @param order Indica por que campo sera ordenada la búsqueda.
 * @param ordenTipo Tipo de ordenado acendente o decendente.
 * @param OrganizacionUsuario objeto que contiene los parámetros a buscar.
 * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    public Grid findByCriteria(int p, int maxResult, String order, String ordenTipo, OrganizacionUsuario organizacionUsuario) {
        return organizacionUsuarioBss.findByCriteria(p, maxResult, order, ordenTipo, organizacionUsuario);
    }

    /**
     * Método que obtiene el primer resultado de una consulta, utilizado cuando
     * la pagina tiene la propiedad individual
     *
     * @return regresa el primer resultado de la consulta, si no hay resultados
     * regresa null
     */
    public OrganizacionUsuario findFirst() {
        return organizacionUsuarioBss.findFirst();
    }

    /*
 * Método que actualiza el Estatus de los objetos en la lista
 * <b>organizacionUsuarioList</b> en la tabla de <b>OrganizacionUsuario</b>.
 * @param userId userId Es el ID del usuario que realizó la operación.
 * @param estatusId Es el ID del estatus al cual se quiere cambiar.
 * @param organizacionUsuarioList Es una lista que contiene los registros los cuales se
 * quiere actualizar su Estatus.
     */
    public void setEstatus(Integer estatusId, List<Integer> organizacionUsuarioList) {
        organizacionUsuarioBss.setEstatus(obtieneSessionVar().getUsuarioId(), estatusId, organizacionUsuarioList);
    }

    /*
 * Metodos que actualizan los campos <select> en el jsp despues de haber hecho
 * una edición de datos en un popup externo.
     */
    public List<Organizacion> getOrganizacion() {
        return organizacionUsuarioBss.getOrganizacion();
    }

    public String getReportDataTest(String order, String ordenTipo, OrganizacionUsuario organizacionUsuario) {
        return organizacionUsuarioBss.getReportDataTest(order, ordenTipo, organizacionUsuario);
    }

    /*
 * Inyección de dependencias con Spring, cada
 * referencia definida al inicio de la clase requiere un método
 * Setter.
     */
    public void setOrganizacionUsuarioBss(OrganizacionUsuarioBss organizacionUsuarioBss) {
        this.organizacionUsuarioBss = organizacionUsuarioBss;
    }

}
