package com.admon.model.admon;

import com.admon.bss.admon.OrganizacionGenerarBss;
import com.admon.entity.admon.*;
import com.admon.model.BaseModel;
import java.util.List;

public class OrganizacionGenerarDWR extends BaseModel {

    /*
 * Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase.
     */
    private OrganizacionGenerarBss organizacionGenerarBss;

    public OrganizacionGenerarDWR() {
    }

    /*
 * Guarda la lista <b>organizacionGenerarList</b> en la tabla <b>OrganizacionGenerar</b>.
 * @param organizacionGenerarList Lista a guardar.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    public List<Integer> save(List<OrganizacionGenerar> organizacionGenerarList) {
        return organizacionGenerarBss.save(obtieneSessionVar().getUsuarioId(), organizacionGenerarList);
    }

    /*
 * Método que actualiza la información de <b>organizacionGenerar</b> en la tabla de <b>OrganizacionGenerar</b>.
 * @param organizacionGenerarList Lista de registros que se actualizarán en la tabla de <b>OrganizacionGenerar</b> en la BD.
     */
    public List<Integer> update(List<OrganizacionGenerar> organizacionGenerarList) {
        return organizacionGenerarBss.update(obtieneSessionVar().getUsuarioId(), organizacionGenerarList);
    }

    /*
 * Método que elimina el registro <b>organizacionGenerar</b> en la tabla de <b>OrganizacionGenerar</b>.
 * Este método se llama <b>remove</b> debido a que en javascript (que se encarga de llamar a este método) <b>delete</b> es una palabra reservada.
 * @param organizacionGenerarList Lista de registros que se eliminarán en la tabla de <b>OrganizacionGenerar</b> en la BD.
     */
    public void remove(List<Integer> organizacionGenerarIdList) {
        organizacionGenerarBss.delete(obtieneSessionVar().getUsuarioId(), organizacionGenerarIdList);
    }

    /*
 * Guarda la lista <b>organizacionGenerarList</b> en la tabla <b>OrganizacionGenerar</b>.
 * @param organizacionGenerarList Lista a guardar.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    public String crear(Integer organizacionId, List<OrganizacionGenerar> organizacionGenerarList) {
        return organizacionGenerarBss.crear(obtieneSessionVar().getUsuarioId(), organizacionId, organizacionGenerarList.get(0));
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
        return organizacionGenerarBss.isValidoNombre(nombre);
    }

    /*
 * Método que busca el registro <b>organizacionGenerar</b> por su ID en la tabla de <b>OrganizacionGenerar</b>.
 * @param id es el ID del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>OrganizacionGenerar</b> con la información del registro <b>organizacionGenerar</b>.
     */
    public OrganizacionGenerar findById(Integer organizacionGenerarId) {
        return organizacionGenerarBss.findById(organizacionGenerarId);
    }

    /*
 * Método que hace una consulta a la tabla OrganizacionGenerar y obtiene los registros que coincidan con el objeto <b>organizacionGenerar</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
 * @param p Número de página que se desea mostrar al usuario en el widget JQGrid.
 * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
 * @param order Indica por que campo sera ordenada la búsqueda.
 * @param ordenTipo Tipo de ordenado acendente o decendente.
 * @param OrganizacionGenerar objeto que contiene los parámetros a buscar.
 * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    public Grid findByCriteria(int p, int maxResult, String order, String ordenTipo, OrganizacionGenerar organizacionGenerar) {
        return organizacionGenerarBss.findByCriteria(p, maxResult, order, ordenTipo, organizacionGenerar);
    }

    /**
     * Método que obtiene el primer resultado de una consulta, utilizado cuando
     * la pagina tiene la propiedad individual
     *
     * @return regresa el primer resultado de la consulta, si no hay resultados
     * regresa null
     */
    public OrganizacionGenerar findFirst() {
        return organizacionGenerarBss.findFirst();
    }

    /*
 * Método que actualiza el Estatus de los objetos en la lista
 * <b>organizacionGenerarList</b> en la tabla de <b>OrganizacionGenerar</b>.
 * @param userId userId Es el ID del usuario que realizó la operación.
 * @param estatusId Es el ID del estatus al cual se quiere cambiar.
 * @param organizacionGenerarList Es una lista que contiene los registros los cuales se
 * quiere actualizar su Estatus.
     */
    public void setEstatus(Integer estatusId, List<Integer> organizacionGenerarList) {
        organizacionGenerarBss.setEstatus(obtieneSessionVar().getUsuarioId(), estatusId, organizacionGenerarList);
    }

    /*
 * Metodos que actualizan los campos <select> en el jsp despues de haber hecho
 * una edición de datos en un popup externo.
     */
    public List<Organizacion> getOrganizacion() {
        return organizacionGenerarBss.getOrganizacion();
    }

    public String getReportDataTest(String order, String ordenTipo, OrganizacionGenerar organizacionGenerar) {
        return organizacionGenerarBss.getReportDataTest(order, ordenTipo, organizacionGenerar);
    }

    /*
 * Inyección de dependencias con Spring, cada
 * referencia definida al inicio de la clase requiere un método
 * Setter.
     */
    public void setOrganizacionGenerarBss(OrganizacionGenerarBss organizacionGenerarBss) {
        this.organizacionGenerarBss = organizacionGenerarBss;
    }

}
