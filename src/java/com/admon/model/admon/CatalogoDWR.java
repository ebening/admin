package com.admon.model.admon;

import com.admon.bss.admon.CatalogoBss;
import com.admon.entity.admon.Catalogo;
import com.admon.entity.admon.CatalogoParametro;
import com.admon.entity.admon.Grid;
import com.admon.model.BaseModel;
import java.util.List;

public class CatalogoDWR extends BaseModel {

    /**
     * Inyección de dependencias con Spring. Estas dependencias se configuran en
     * el applicationContext.xml, y además necesitan un método setter por cada
     * variable que se anexa al final de la clase.
     */
    private CatalogoBss catalogoBss;

    public CatalogoDWR() {
    }

    /**
     * Guarda la lista <b>catalogoList</b> en la tabla <b>Catalogo</b>.
     *
     * @param catalogoList Lista a guardar.
     * @return Regresa una lista con los id's de los registros guardados.
     */
    public List<Integer> save(List<Catalogo> catalogoList) {
        return catalogoBss.save(obtieneSessionVar().getUsuarioId(), catalogoList);
    }

    /**
     * Método que actualiza la información de <b>catalogo</b> en la tabla de
     * <b>Catalogo</b>.
     *
     * @param catalogoList Lista de registros que se actualizarán en la tabla de
     * <b>Catalogo</b> en la BD.
     */
    public List<Integer> update(List<Catalogo> catalogoList) {
        return catalogoBss.update(obtieneSessionVar().getUsuarioId(), catalogoList);
    }

    /**
     * Método que elimina el registro <b>catalogo</b> en la tabla de
     * <b>Catalogo</b>. Este método se llama <b>remove</b> debido a que en
     * javascript (que se encarga de llamar a este método) <b>delete</b> es una
     * palabra reservada.
     *
     * @param catalogoList Lista de registros que se eliminarán en la tabla de
     * <b>Catalogo</b> en la BD.
     */
    public void remove(List<Catalogo> catalogoIdList) {
        catalogoBss.delete(obtieneSessionVar().getUsuarioId(), catalogoIdList);
    }

    /**
     * Método que evalúa si existe al menos un registro con un determinado
     * nombre en la tabla.
     *
     * @param nombre Es el nombre que se desea buscar en la tabla de la BD.
     * @return Regresa <b>false</b> si existe al menos un registro con el nombre
     * específicado como parámetro en el método, si no existe ningún registro
     * con ese nombre regresa un <b>true</b>.
     */
    public boolean isValidoNombre(String nombre) {
        return catalogoBss.isValidoNombre(nombre);
    }

    /**
     * Método que busca el registro <b>catalogo</b> por su ID en la tabla de
     * <b>Catalogo</b>.
     *
     * @param id es el ID del registro que se quiere obtener.
     * @return Regresa un objeto tipo <b>Catalogo</b> con la información del
     * registro <b>catalogo</b>.
     */
    public Catalogo findById(Integer catalogoId, Integer organizacionId) {
        return catalogoBss.findById(catalogoId, organizacionId);
    }

    /**
     * Método que hace una consulta a la tabla Catalogo y obtiene los registros
     * que coincidan con el objeto <b>catalogo</b> que es mandado como parámetro
     * en el método. Los resultados de la consulta son páginados y encapsulados
     * en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid
     * del JSP.
     *
     * @param p Número de página que se desea mostrar al usuario en el widget
     * JQGrid.
     * @param maxResult Cantidad de registros por página que el widget JQGrid
     * puede mostrar.
     * @param order Indica por que campo sera ordenada la búsqueda.
     * @param ordenTipo Tipo de ordenado acendente o decendente.
     * @param Catalogo objeto que contiene los parámetros a buscar.
     * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la
     * pantalla.
     */
    public Grid findByCriteria(int p, int maxResult, String order, String ordenTipo, Catalogo catalogo) {
        return catalogoBss.findByCriteria(p, maxResult, order, ordenTipo, catalogo);
    }

    /**
     * Método que obtiene el primer resultado de una consulta, utilizado cuando
     * la pagina tiene la propiedad individual
     *
     * @return regresa el primer resultado de la consulta, si no hay resultados
     * regresa null
     */
    public Catalogo findFirst() {
        return catalogoBss.findFirst();
    }

    /**
     * Método que actualiza el Estatus de los objetos en la lista
     * <b>catalogoList</b> en la tabla de <b>Catalogo</b>.
     *
     * @param userId userId Es el ID del usuario que realizó la operación.
     * @param estatusId Es el ID del estatus al cual se quiere cambiar.
     * @param catalogoList Es una lista que contiene los registros los cuales se
     * quiere actualizar su Estatus.
     */
    public void setEstatus(Integer estatusId, List<Catalogo> catalogoList) {
        catalogoBss.setEstatus(obtieneSessionVar().getUsuarioId(), estatusId, catalogoList);
    }

    /**
     * Metodos que actualizan los campos <select> en el jsp despues de haber
     * hecho una edición de datos en un popup externo.
     */
    public String getReportDataTest(String order, String ordenTipo, Catalogo catalogo) {
        return catalogoBss.getReportDataTest(order, ordenTipo, catalogo);
    }

    public List<Catalogo> filtrarCatalogoPadre(int organizacionId) {
        List<Catalogo> listCatalogo = null;
        listCatalogo = catalogoBss.filtrarCatalogo(organizacionId);
        return listCatalogo;
    }

    public List<CatalogoParametro> filtrarCatalogoParametro(int catalogoPadreId, int organizacionId) {
        List<CatalogoParametro> listCatalogoParametro = null;
        listCatalogoParametro = catalogoBss.filtrarCatalogoParametro(catalogoPadreId, organizacionId);
        return listCatalogoParametro;
    }

    /**
     * Inyección de dependencias con Spring, cada referencia definida al inicio
     * de la clase requiere un método Setter.
     */
    public void setCatalogoBss(CatalogoBss catalogoBss) {
        this.catalogoBss = catalogoBss;
    }

}
