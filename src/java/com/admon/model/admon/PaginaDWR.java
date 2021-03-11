package com.admon.model.admon;

import com.admon.bss.admon.PaginaBss;
import com.admon.entity.admon.*;
import com.admon.model.BaseModel;
import java.util.List;

public class PaginaDWR extends BaseModel {

    /*
 * Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase.
     */
    private PaginaBss paginaBss;

    public PaginaDWR() {
    }

    /*
 * Guarda la lista <b>paginaList</b> en la tabla <b>Pagina</b>.
 * @param paginaList Lista a guardar.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    public List<Integer> save(List<Pagina> paginaList) {
        return paginaBss.save(obtieneSessionVar().getUsuarioId(), paginaList);
    }

    /*
 * Método que actualiza la información de <b>pagina</b> en la tabla de <b>Pagina</b>.
 * @param paginaList Lista de registros que se actualizarán en la tabla de <b>Pagina</b> en la BD.
     */
    public List<Integer> update(List<Pagina> paginaList) {
        return paginaBss.update(obtieneSessionVar().getUsuarioId(), paginaList);
    }

    /*
 * Método que elimina el registro <b>pagina</b> en la tabla de <b>Pagina</b>.
 * Este método se llama <b>remove</b> debido a que en javascript (que se encarga de llamar a este método) <b>delete</b> es una palabra reservada.
 * @param paginaList Lista de registros que se eliminarán en la tabla de <b>Pagina</b> en la BD.
     */
    public void remove(List<Integer> paginaIdList) {
        paginaBss.delete(obtieneSessionVar().getUsuarioId(), paginaIdList);
    }

    /*
 * Método para obtener las páginas que conforman el menu.
 *
 * @return regresa una lista de objetos Pagina.
     */
    public List<Pagina> getMenus() {
        // Obtener todos los registros de la tabla Pagina y ordenarlo alfabeticamente.
        return paginaBss.getMenus();
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
        return paginaBss.isValidoNombre(nombre);
    }

    /*
 * Método que busca el registro <b>pagina</b> por su ID en la tabla de <b>Pagina</b>.
 * @param id es el ID del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>Pagina</b> con la información del registro <b>pagina</b>.
     */
    public Pagina findById(Integer paginaId) {
        return paginaBss.findById(paginaId);
    }

    /*
 * Método que hace una consulta a la tabla Pagina y obtiene los registros que coincidan con el objeto <b>pagina</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
 * @param p Número de página que se desea mostrar al usuario en el widget JQGrid.
 * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
 * @param order Indica por que campo sera ordenada la búsqueda.
 * @param ordenTipo Tipo de ordenado acendente o decendente.
 * @param Pagina objeto que contiene los parámetros a buscar.
 * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    public Grid findByCriteria(int p, int maxResult, String order, String ordenTipo, Pagina pagina) {
        return paginaBss.findByCriteria(p, maxResult, order, ordenTipo, pagina);
    }

    /**
     * Método que obtiene el primer resultado de una consulta, utilizado cuando
     * la pagina tiene la propiedad individual
     *
     * @return regresa el primer resultado de la consulta, si no hay resultados
     * regresa null
     */
    public Pagina findFirst() {
        return paginaBss.findFirst();
    }

    /*
 * Método que actualiza el Estatus de los objetos en la lista
 * <b>paginaList</b> en la tabla de <b>Pagina</b>.
 * @param userId userId Es el ID del usuario que realizó la operación.
 * @param estatusId Es el ID del estatus al cual se quiere cambiar.
 * @param paginaList Es una lista que contiene los registros los cuales se
 * quiere actualizar su Estatus.
     */
    public void setEstatus(Integer estatusId, List<Integer> paginaList) {
        paginaBss.setEstatus(obtieneSessionVar().getUsuarioId(), estatusId, paginaList);
    }

    /*
 * Metodos que actualizan los campos <select> en el jsp despues de haber hecho
 * una edición de datos en un popup externo.
     */
    public String getReportDataTest(String order, String ordenTipo, Pagina pagina) {
        return paginaBss.getReportDataTest(order, ordenTipo, pagina);
    }

    /*
 * Inyección de dependencias con Spring, cada
 * referencia definida al inicio de la clase requiere un método
 * Setter.
     */
    public void setPaginaBss(PaginaBss paginaBss) {
        this.paginaBss = paginaBss;
    }

}
