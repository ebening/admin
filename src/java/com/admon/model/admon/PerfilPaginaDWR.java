package com.admon.model.admon;

import com.admon.bss.admon.PerfilPaginaBss;
import com.admon.entity.admon.*;
import com.admon.model.BaseModel;
import java.util.List;

public class PerfilPaginaDWR extends BaseModel {

    /*
 * Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase.
     */
    private PerfilPaginaBss perfilPaginaBss;

    public PerfilPaginaDWR() {
    }

    /*
 * Guarda la lista <b>perfilPaginaList</b> en la tabla <b>PerfilPagina</b>.
 * @param perfilPaginaList Lista a guardar.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    public List<Integer> save(int perfilId, List<Integer> perfilPaginaList) {
        return perfilPaginaBss.save(obtieneSessionVar().getUsuarioId(), perfilId, perfilPaginaList);
    }

    /*
 * Método que actualiza la información de <b>perfilPagina</b> en la tabla de <b>PerfilPagina</b>.
 * @param perfilPaginaList Lista de registros que se actualizarán en la tabla de <b>PerfilPagina</b> en la BD.
     */
    public List<Integer> update(List<PerfilPagina> perfilPaginaList) {
        return perfilPaginaBss.update(obtieneSessionVar().getUsuarioId(), perfilPaginaList);
    }

    /*
 * Método que elimina el registro <b>perfilPagina</b> en la tabla de <b>PerfilPagina</b>.
 * Este método se llama <b>remove</b> debido a que en javascript (que se encarga de llamar a este método) <b>delete</b> es una palabra reservada.
 * @param perfilPaginaList Lista de registros que se eliminarán en la tabla de <b>PerfilPagina</b> en la BD.
     */
    public void remove(List<Integer> perfilPaginaIdList) {
        perfilPaginaBss.delete(obtieneSessionVar().getUsuarioId(), perfilPaginaIdList);
    }

    /*
 * Método que busca el registro <b>perfilPagina</b> por su ID en la tabla de <b>PerfilPagina</b>.
 * @param id es el ID del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>PerfilPagina</b> con la información del registro <b>perfilPagina</b>.
     */
    public PerfilPagina findById(Integer perfilPaginaId) {
        return perfilPaginaBss.findById(perfilPaginaId);
    }

    /*
 * Método que hace una consulta a la tabla PerfilPagina y obtiene los registros que coincidan con el objeto <b>perfilPagina</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
 * @param p Número de página que se desea mostrar al usuario en el widget JQGrid.
 * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
 * @param order Indica por que campo sera ordenada la búsqueda.
 * @param ordenTipo Tipo de ordenado acendente o decendente.
 * @param PerfilPagina objeto que contiene los parámetros a buscar.
 * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    public Grid findByCriteria(int p, int maxResult, String order, String ordenTipo, PerfilPagina perfilPagina) {
        return perfilPaginaBss.findByCriteria(p, maxResult, order, ordenTipo, perfilPagina);
    }

    /**
     * Método que obtiene el primer resultado de una consulta, utilizado cuando
     * la pagina tiene la propiedad individual
     *
     * @return regresa el primer resultado de la consulta, si no hay resultados
     * regresa null
     */
    public PerfilPagina findFirst() {
        return perfilPaginaBss.findFirst();
    }

    /*
 * Método que actualiza el Estatus de los objetos en la lista
 * <b>perfilPaginaList</b> en la tabla de <b>PerfilPagina</b>.
 * @param userId userId Es el ID del usuario que realizó la operación.
 * @param estatusId Es el ID del estatus al cual se quiere cambiar.
 * @param perfilPaginaList Es una lista que contiene los registros los cuales se
 * quiere actualizar su Estatus.
     */
    public void setEstatus(Integer estatusId, List<Integer> perfilPaginaList) {
        perfilPaginaBss.setEstatus(obtieneSessionVar().getUsuarioId(), estatusId, perfilPaginaList);
    }

    /*
 * Metodos que actualizan los campos <select> en el jsp despues de haber hecho
 * una edición de datos en un popup externo.
     */
    public List<Perfil> getPerfil() {
        return perfilPaginaBss.getPerfil();
    }

    public List<Pagina> getPagina() {
        return perfilPaginaBss.getPagina();
    }

    public String getReportDataTest(String order, String ordenTipo, PerfilPagina perfilPagina) {
        return perfilPaginaBss.getReportDataTest(order, ordenTipo, perfilPagina);
    }

    /*
 * Inyección de dependencias con Spring, cada
 * referencia definida al inicio de la clase requiere un método
 * Setter.
     */
    public void setPerfilPaginaBss(PerfilPaginaBss perfilPaginaBss) {
        this.perfilPaginaBss = perfilPaginaBss;
    }

    /*
* Obtiene una lista de todos los registros válidos (activos y no eliminados) de la tabla PAGINA.
*
* @return regresa una lista de todos los registros de la tabla PAGINA
     */
    public List<Pagina> obtienePaginas() {
        return perfilPaginaBss.obtienePaginas();
    }

    /*
* Obtiene una lista de todas las paginas a las que un determinado perfil tiene acceso.
*
* @param perfilId es el usuario del cual se quiere obtener los accesos
* @return regresa una lista de todos los registros de la tabla PAGINA
     */
    public List<Integer> obtieneAccesos(int perfilId) {
        return perfilPaginaBss.obtieneAccesos(perfilId);
    }

}
