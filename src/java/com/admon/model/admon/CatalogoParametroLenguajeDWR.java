package com.admon.model.admon;

import com.admon.bss.admon.CatalogoParametroLenguajeBss;
import com.admon.entity.admon.CatalogoParametro;
import com.admon.entity.admon.CatalogoParametroLenguaje;
import com.admon.entity.admon.Grid;
import com.admon.model.BaseModel;
import java.util.List;

public class CatalogoParametroLenguajeDWR extends BaseModel {

    /*
     * Inyección de dependencias con Spring. Estas dependencias se
     * configuran en el applicationContext.xml, y además necesitan un
     * método setter por cada variable que se anexa al final de la clase.
     */
    private CatalogoParametroLenguajeBss catalogoParametroLenguajeBss;

    public CatalogoParametroLenguajeDWR() {
    }

    /*
     * Guarda la lista <b>catalogoParametroLenguajeList</b> en la tabla <b>CatalogoParametroLenguaje</b>.
     * @param catalogoParametroLenguajeList Lista a guardar.
     * @return Regresa una lista con los id's de los registros guardados.
     */
    public List<Integer> save(List<CatalogoParametroLenguaje> catalogoParametroLenguajeList) {
        return catalogoParametroLenguajeBss.save(obtieneSessionVar().getUsuarioId(), catalogoParametroLenguajeList);
    }

    public void saveCatalogoParametroLenguaje(Integer catalogoParametroId, Integer organizacionId,
            List<CatalogoParametroLenguaje> catalogoParametroLenguajeList) {
        catalogoParametroLenguajeBss.saveCatalogoParametroLenguaje(obtieneSessionVar().getUsuarioId(),
                catalogoParametroId, organizacionId, catalogoParametroLenguajeList);
    }
    /*
     * Método que actualiza la información de <b>catalogoParametroLenguaje</b> en la tabla de <b>CatalogoParametroLenguaje</b>.
     * @param catalogoParametroLenguajeList Lista de registros que se actualizarán en la tabla de <b>CatalogoParametroLenguaje</b> en la BD.
     */

    public List<Integer> update(List<CatalogoParametroLenguaje> catalogoParametroLenguajeList) {
        return catalogoParametroLenguajeBss.update(obtieneSessionVar().getUsuarioId(), catalogoParametroLenguajeList);
    }

    /*
     * Método que elimina el registro <b>catalogoParametroLenguaje</b> en la tabla de <b>CatalogoParametroLenguaje</b>.
     * Este método se llama <b>remove</b> debido a que en javascript (que se encarga de llamar a este método) <b>delete</b> es una palabra reservada.
     * @param catalogoParametroLenguajeList Lista de registros que se eliminarán en la tabla de <b>CatalogoParametroLenguaje</b> en la BD.
     */
    public void remove(List<Integer> catalogoParametroLenguajeIdList) {
        catalogoParametroLenguajeBss.delete(obtieneSessionVar().getUsuarioId(), catalogoParametroLenguajeIdList);
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
        return catalogoParametroLenguajeBss.isValidoNombre(nombre);
    }

    /*
     * Método que busca el registro <b>catalogoParametroLenguaje</b> por su ID en la tabla de <b>CatalogoParametroLenguaje</b>.
     * @param id es el ID del registro que se quiere obtener.
     * @return Regresa un objeto tipo <b>CatalogoParametroLenguaje</b> con la información del registro <b>catalogoParametroLenguaje</b>.
     */
    public CatalogoParametroLenguaje findById(Integer catalogoParametroLenguajeId) {
        return catalogoParametroLenguajeBss.findById(catalogoParametroLenguajeId);
    }

    public List<CatalogoParametroLenguaje> findCatalogoParametroBy(Integer catalogoParametroId, Integer organizacionId) {
        return catalogoParametroLenguajeBss.findCatalogoParametroBy(catalogoParametroId, organizacionId);
    }
    /*
     * Método que hace una consulta a la tabla CatalogoParametroLenguaje y obtiene los registros que coincidan con el objeto <b>catalogoParametroLenguaje</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
     * @param p Número de página que se desea mostrar al usuario en el widget JQGrid.
     * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
     * @param order Indica por que campo sera ordenada la búsqueda.
     * @param ordenTipo Tipo de ordenado acendente o decendente.
     * @param CatalogoParametroLenguaje objeto que contiene los parámetros a buscar.
     * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */

    public Grid findByCriteria(int p, int maxResult, String order, String ordenTipo, CatalogoParametroLenguaje catalogoParametroLenguaje) {
        return catalogoParametroLenguajeBss.findByCriteria(p, maxResult, order, ordenTipo, catalogoParametroLenguaje);
    }

    /**
     * Método que obtiene el primer resultado de una consulta, utilizado cuando
     * la pagina tiene la propiedad individual
     *
     * @return regresa el primer resultado de la consulta, si no hay resultados
     * regresa null
     */
    public CatalogoParametroLenguaje findFirst() {
        return catalogoParametroLenguajeBss.findFirst();
    }

    /*
     * Método que actualiza el Estatus de los objetos en la lista
     * <b>catalogoParametroLenguajeList</b> en la tabla de <b>CatalogoParametroLenguaje</b>.
     * @param userId userId Es el ID del usuario que realizó la operación.
     * @param estatusId Es el ID del estatus al cual se quiere cambiar.
     * @param catalogoParametroLenguajeList Es una lista que contiene los registros los cuales se
     * quiere actualizar su Estatus.
     */
    public void setEstatus(Integer estatusId, List<Integer> catalogoParametroLenguajeList) {
        catalogoParametroLenguajeBss.setEstatus(obtieneSessionVar().getUsuarioId(), estatusId, catalogoParametroLenguajeList);
    }

    /*
     * Metodos que actualizan los campos <select> en el jsp despues de haber hecho
     * una edición de datos en un popup externo.
     */
    public List<CatalogoParametro> getCatalogoParametro() {
        return catalogoParametroLenguajeBss.getCatalogoParametro();
    }

    public String getReportDataTest(String order, String ordenTipo, CatalogoParametroLenguaje catalogoParametroLenguaje) {
        return catalogoParametroLenguajeBss.getReportDataTest(order, ordenTipo, catalogoParametroLenguaje);
    }

    /*
     * Inyección de dependencias con Spring, cada
     * referencia definida al inicio de la clase requiere un método
     * Setter.
     */
    public void setCatalogoParametroLenguajeBss(CatalogoParametroLenguajeBss catalogoParametroLenguajeBss) {
        this.catalogoParametroLenguajeBss = catalogoParametroLenguajeBss;
    }

}
