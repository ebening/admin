package com.admon.model.admon;

import com.admon.bss.admon.AplicacionBss;
import com.admon.entity.admon.*;
import com.admon.model.BaseModel;
import java.util.List;

public class AplicacionDWR extends BaseModel {

    /*
 * Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase.
     */
    private AplicacionBss aplicacionBss;

    public AplicacionDWR() {
    }

   /*
 * Guarda la lista <b>aplicacionList</b> en la tabla <b>Aplicacion</b>.
 * @param aplicacionList Lista a guardar.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    public List<Long> save(List<Aplicacion> aplicacionList) {
        return aplicacionBss.save(obtieneSessionVar().getUsuarioId(), aplicacionList);
    }

    /*
 * Método que actualiza la información de <b>pagina</b> en la tabla de <b>Aplicacion</b>.
 * @param aplicacionList Lista de registros que se actualizarán en la tabla de <b>Aplicacion</b> en la BD.
     */
    public List<Long> update(List<Aplicacion> aplicacionList) {
        return aplicacionBss.update(obtieneSessionVar().getUsuarioId(), aplicacionList);
    }

    /*
 * Método que elimina el registro <b>pagina</b> en la tabla de <b>Aplicacion</b>.
 * Este método se llama <b>remove</b> debido a que en javascript (que se encarga de llamar a este método) <b>delete</b> es una palabra reservada.
 * @param aplicacionList Lista de registros que se eliminarán en la tabla de <b>Aplicacion</b> en la BD.
     */
    public void remove(List<Long> paginaIdList) {
        aplicacionBss.delete(obtieneSessionVar().getUsuarioId(), paginaIdList);
    }

    /*
 * Método para obtener las páginas que conforman el menu.
 *
 * @return regresa una lista de objetos Aplicacion.
     */
    public List<Aplicacion> getAplicaciones() {
        // Obtener todos los registros de la tabla Aplicacion y ordenarlo alfabeticamente.
        return aplicacionBss.findAll();
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
        return aplicacionBss.isValidoNombre(nombre);
    }

    /*
 * Método que busca el registro <b>pagina</b> por su ID en la tabla de <b>Aplicacion</b>.
 * @param id es el ID del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>Aplicacion</b> con la información del registro <b>pagina</b>.
     */
    public Aplicacion findById(Long paginaId) {
        return aplicacionBss.findById(paginaId);
    }

    /*
 * Método que hace una consulta a la tabla Aplicacion y obtiene los registros que coincidan con el objeto <b>pagina</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
 * @param p Número de página que se desea mostrar al usuario en el widget JQGrid.
 * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
 * @param order Indica por que campo sera ordenada la búsqueda.
 * @param ordenTipo Tipo de ordenado acendente o decendente.
 * @param Aplicacion objeto que contiene los parámetros a buscar.
 * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    public Grid findByCriteria(int p, int maxResult, String order, String ordenTipo, Aplicacion pagina) {
        return aplicacionBss.findByCriteria(p, maxResult, order, ordenTipo, pagina);
    }

    /**
     * Método que obtiene el primer resultado de una consulta, utilizado cuando
     * la pagina tiene la propiedad individual
     *
     * @return regresa el primer resultado de la consulta, si no hay resultados
     * regresa null
     */
    public Aplicacion findFirst() {
        return aplicacionBss.findFirst();
    }

    /*
 * Método que actualiza el Estatus de los objetos en la lista
 * <b>aplicacionList</b> en la tabla de <b>Aplicacion</b>.
 * @param userId userId Es el ID del usuario que realizó la operación.
 * @param estatusId Es el ID del estatus al cual se quiere cambiar.
 * @param aplicacionList Es una lista que contiene los registros los cuales se
 * quiere actualizar su Estatus.
     */
    public void setEstatus(Integer estatusId, List<Long> aplicacionList) {
        aplicacionBss.setEstatus(obtieneSessionVar().getUsuarioId(), estatusId, aplicacionList);
    }

    /*
 * Metodos que actualizan los campos <select> en el jsp despues de haber hecho
 * una edición de datos en un popup externo.
     */
    public String getReportDataTest(String order, String ordenTipo, Aplicacion pagina) {
        return aplicacionBss.getReportDataTest(order, ordenTipo, pagina);
    }
    /*
 * Inyección de dependencias con Spring, cada
 * referencia definida al inicio de la clase requiere un método
 * Setter.
     */
    public void setAplicacionBss(AplicacionBss aplicacionBss) {
        this.aplicacionBss = aplicacionBss;
    }

}
