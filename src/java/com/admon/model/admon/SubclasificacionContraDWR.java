package com.admon.model.admon;

import com.admon.bss.admon.SubclasificacionContraBss;
import com.admon.entity.admon.*;
import com.admon.model.BaseModel;
import java.util.List;

public class SubclasificacionContraDWR extends BaseModel {

    /*
 * Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase.
     */
    private SubclasificacionContraBss subclasificacionContraBss;

    public SubclasificacionContraDWR() {
    }

    /*
 * Guarda la lista <b>subclasificacionContraList</b> en la tabla <b>SubclasificacionContra</b>.
 * @param subclasificacionContraList Lista a guardar.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    public List<Integer> save(List<SubclasificacionContra> subclasificacionContraList) {
        return subclasificacionContraBss.save(obtieneSessionVar().getUsuarioId(), subclasificacionContraList);
    }

    /*
 * Método que actualiza la información de <b>subclasificacionContra</b> en la tabla de <b>SubclasificacionContra</b>.
 * @param subclasificacionContraList Lista de registros que se actualizarán en la tabla de <b>SubclasificacionContra</b> en la BD.
     */
    public List<Integer> update(List<SubclasificacionContra> subclasificacionContraList) {
        return subclasificacionContraBss.update(obtieneSessionVar().getUsuarioId(), subclasificacionContraList);
    }

    /*
 * Método que elimina el registro <b>subclasificacionContra</b> en la tabla de <b>SubclasificacionContra</b>.
 * Este método se llama <b>remove</b> debido a que en javascript (que se encarga de llamar a este método) <b>delete</b> es una palabra reservada.
 * @param subclasificacionContraList Lista de registros que se eliminarán en la tabla de <b>SubclasificacionContra</b> en la BD.
     */
    public void remove(List<Integer> subclasificacionContraIdList) {
        subclasificacionContraBss.delete(obtieneSessionVar().getUsuarioId(), subclasificacionContraIdList);
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
        return subclasificacionContraBss.isValidoNombre(nombre);
    }

    /*
 * Método que busca el registro <b>subclasificacionContra</b> por su ID en la tabla de <b>SubclasificacionContra</b>.
 * @param id es el ID del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>SubclasificacionContra</b> con la información del registro <b>subclasificacionContra</b>.
     */
    public SubclasificacionContra findById(Integer subclasificacionContraId) {
        return subclasificacionContraBss.findById(subclasificacionContraId);
    }

    /*
 * Método que hace una consulta a la tabla SubclasificacionContra y obtiene los registros que coincidan con el objeto <b>subclasificacionContra</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
 * @param p Número de página que se desea mostrar al usuario en el widget JQGrid.
 * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
 * @param order Indica por que campo sera ordenada la búsqueda.
 * @param ordenTipo Tipo de ordenado acendente o decendente.
 * @param SubclasificacionContra objeto que contiene los parámetros a buscar.
 * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    public Grid findByCriteria(int p, int maxResult, String order, String ordenTipo, SubclasificacionContra subclasificacionContra) {
        return subclasificacionContraBss.findByCriteria(p, maxResult, order, ordenTipo, subclasificacionContra);
    }

    /**
     * Método que obtiene el primer resultado de una consulta, utilizado cuando
     * la pagina tiene la propiedad individual
     *
     * @return regresa el primer resultado de la consulta, si no hay resultados
     * regresa null
     */
    public SubclasificacionContra findFirst() {
        return subclasificacionContraBss.findFirst();
    }

    /*
 * Método que actualiza el Estatus de los objetos en la lista
 * <b>subclasificacionContraList</b> en la tabla de <b>SubclasificacionContra</b>.
 * @param userId userId Es el ID del usuario que realizó la operación.
 * @param estatusId Es el ID del estatus al cual se quiere cambiar.
 * @param subclasificacionContraList Es una lista que contiene los registros los cuales se
 * quiere actualizar su Estatus.
     */
    public void setEstatus(Integer estatusId, List<Integer> subclasificacionContraList) {
        subclasificacionContraBss.setEstatus(obtieneSessionVar().getUsuarioId(), estatusId, subclasificacionContraList);
    }

    /*
 * Metodos que actualizan los campos <select> en el jsp despues de haber hecho
 * una edición de datos en un popup externo.
     */
    public List<ClasificacionContra> getClasificacionContra() {
        return subclasificacionContraBss.getClasificacionContra();
    }

    public String getReportDataTest(String order, String ordenTipo, SubclasificacionContra subclasificacionContra) {
        return subclasificacionContraBss.getReportDataTest(order, ordenTipo, subclasificacionContra);
    }

    /*
 * Inyección de dependencias con Spring, cada
 * referencia definida al inicio de la clase requiere un método
 * Setter.
     */
    public void setSubclasificacionContraBss(SubclasificacionContraBss subclasificacionContraBss) {
        this.subclasificacionContraBss = subclasificacionContraBss;
    }

}
