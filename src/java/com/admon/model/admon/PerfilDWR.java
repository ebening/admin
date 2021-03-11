package com.admon.model.admon;

import com.admon.bss.admon.PerfilBss;
import com.admon.entity.admon.*;
import com.admon.model.BaseModel;
import java.util.List;

public class PerfilDWR extends BaseModel {

    /*
 * Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase.
     */
    private PerfilBss perfilBss;

    public PerfilDWR() {
    }

    /*
 * Guarda la lista <b>perfilList</b> en la tabla <b>Perfil</b>.
 * @param perfilList Lista a guardar.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    public List<Integer> save(List<Perfil> perfilList) {
        return perfilBss.save(obtieneSessionVar().getUsuarioId(), perfilList);
    }

    public void savePerfilPagina(Integer perfilId,
            List<PerfilPagina> perfilPaginaList) {
        perfilBss.savePerfilPagina(obtieneSessionVar().getUsuarioId(),
                perfilId, perfilPaginaList);
    }

    /*
 * Método que actualiza la información de <b>perfil</b> en la tabla de <b>Perfil</b>.
 * @param perfilList Lista de registros que se actualizarán en la tabla de <b>Perfil</b> en la BD.
     */
    public List<Integer> update(List<Perfil> perfilList) {
        return perfilBss.update(obtieneSessionVar().getUsuarioId(), perfilList);
    }

    /*
 * Método que elimina el registro <b>perfil</b> en la tabla de <b>Perfil</b>.
 * Este método se llama <b>remove</b> debido a que en javascript (que se encarga de llamar a este método) <b>delete</b> es una palabra reservada.
 * @param perfilList Lista de registros que se eliminarán en la tabla de <b>Perfil</b> en la BD.
     */
    public void remove(List<Integer> perfilIdList) {
        perfilBss.delete(obtieneSessionVar().getUsuarioId(), perfilIdList);
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
        return perfilBss.isValidoNombre(nombre);
    }

    /*
 * Método que busca el registro <b>perfil</b> por su ID en la tabla de <b>Perfil</b>.
 * @param id es el ID del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>Perfil</b> con la información del registro <b>perfil</b>.
     */
    public Perfil findById(Integer perfilId) {
        return perfilBss.findById(perfilId);
    }

    public List<PerfilPagina> findPerfilPaginaByIntProperty(String propertyName, Integer value) {
        return perfilBss.findPerfilPaginaByIntProperty(propertyName, value);
    }

    /*
 * Método que hace una consulta a la tabla Perfil y obtiene los registros que coincidan con el objeto <b>perfil</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
 * @param p Número de página que se desea mostrar al usuario en el widget JQGrid.
 * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
 * @param order Indica por que campo sera ordenada la búsqueda.
 * @param ordenTipo Tipo de ordenado acendente o decendente.
 * @param Perfil objeto que contiene los parámetros a buscar.
 * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    public Grid findByCriteria(int p, int maxResult, String order, String ordenTipo, Perfil perfil) {
        return perfilBss.findByCriteria(p, maxResult, order, ordenTipo, perfil);
    }

    /**
     * Método que obtiene el primer resultado de una consulta, utilizado cuando
     * la pagina tiene la propiedad individual
     *
     * @return regresa el primer resultado de la consulta, si no hay resultados
     * regresa null
     */
    public Perfil findFirst() {
        return perfilBss.findFirst();
    }

    /*
 * Método que actualiza el Estatus de los objetos en la lista
 * <b>perfilList</b> en la tabla de <b>Perfil</b>.
 * @param userId userId Es el ID del usuario que realizó la operación.
 * @param estatusId Es el ID del estatus al cual se quiere cambiar.
 * @param perfilList Es una lista que contiene los registros los cuales se
 * quiere actualizar su Estatus.
     */
    public void setEstatus(Integer estatusId, List<Integer> perfilList) {
        perfilBss.setEstatus(obtieneSessionVar().getUsuarioId(), estatusId, perfilList);
    }

    /*
 * Metodos que actualizan los campos <select> en el jsp despues de haber hecho
 * una edición de datos en un popup externo.
     */
    // <editor-fold defaultstate="collapsed" desc="Getters Detalle(s)">
    /**
     * Método auxiliar utilizado para la funcionalidad "detalle" (PERFIL_PAGINA)
     * campo Pagina.
     *
     * @return regresa una lista de objetos.
     */
    public List<Pagina> getPerfilPaginaPagina() {
        return perfilBss.getPerfilPaginaPagina();
    }
    // </editor-fold>

    public String getReportDataTest(String order, String ordenTipo, Perfil perfil) {
        return perfilBss.getReportDataTest(order, ordenTipo, perfil);
    }

    /*
 * Inyección de dependencias con Spring, cada
 * referencia definida al inicio de la clase requiere un método
 * Setter.
     */
    public void setPerfilBss(PerfilBss perfilBss) {
        this.perfilBss = perfilBss;
    }

}
