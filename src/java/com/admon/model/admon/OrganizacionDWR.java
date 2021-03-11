package com.admon.model.admon;

import com.admon.bss.admon.OrganizacionBss;
import com.admon.entity.admon.*;
import com.admon.model.BaseModel;
import com.admon.pkg.entity.OrganizacionGenerarRS;
import java.util.List;

public class OrganizacionDWR extends BaseModel {

    /*
 * Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase.
     */
    private OrganizacionBss organizacionBss;

    public OrganizacionDWR() {
    }

    /*
 * Guarda la lista <b>organizacionList</b> en la tabla <b>Organizacion</b>.
 * @param organizacionList Lista a guardar.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    public List<Integer> save(List<Organizacion> organizacionList) {
        return organizacionBss.save(obtieneSessionVar().getUsuarioId(), organizacionList);
    }

    public void saveOrganizacionCredencial(Integer organizacionId,
            List<OrganizacionCredencial> organizacionCredencialList) {
        organizacionBss.saveOrganizacionCredencial(obtieneSessionVar().getUsuarioId(),
                organizacionId, organizacionCredencialList);
    }

    public void saveOrganizacionUsuario(Integer organizacionId,
            List<OrganizacionUsuario> organizacionUsuarioList) {
        organizacionBss.saveOrganizacionUsuario(obtieneSessionVar().getUsuarioId(),
                organizacionId, organizacionUsuarioList);
    }

    public String crearOrganizacionGenerar(Integer organizacionId,
            List<OrganizacionGenerar> organizacionGenerarList) {
       return organizacionBss.crearOrganizacionGenerar(obtieneSessionVar().getUsuarioId(),
                organizacionId, organizacionGenerarList);
    }
    /*
 * Método que actualiza la información de <b>organizacion</b> en la tabla de <b>Organizacion</b>.
 * @param organizacionList Lista de registros que se actualizarán en la tabla de <b>Organizacion</b> en la BD.
     */
    public List<Integer> update(List<Organizacion> organizacionList) {
        return organizacionBss.update(obtieneSessionVar().getUsuarioId(), organizacionList);
    }

    /*
 * Método que elimina el registro <b>organizacion</b> en la tabla de <b>Organizacion</b>.
 * Este método se llama <b>remove</b> debido a que en javascript (que se encarga de llamar a este método) <b>delete</b> es una palabra reservada.
 * @param organizacionList Lista de registros que se eliminarán en la tabla de <b>Organizacion</b> en la BD.
     */
    public void remove(List<Integer> organizacionIdList) {
        organizacionBss.delete(obtieneSessionVar().getUsuarioId(), organizacionIdList);
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
        return organizacionBss.isValidoNombre(nombre);
    }

    /*
 * Método que busca el registro <b>organizacion</b> por su ID en la tabla de <b>Organizacion</b>.
 * @param id es el ID del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>Organizacion</b> con la información del registro <b>organizacion</b>.
     */
    public Organizacion findById(Integer organizacionId) {
        return organizacionBss.findById(organizacionId);
    }

    public List<OrganizacionCredencial> findOrganizacionCredencialByIntProperty(String propertyName, Integer value) {
        return organizacionBss.findOrganizacionCredencialByIntProperty(propertyName, value);
    }

    public List<OrganizacionUsuario> findOrganizacionUsuarioByIntProperty(String propertyName, Integer value) {
        return organizacionBss.findOrganizacionUsuarioByIntProperty(propertyName, value);
    }

    public List<OrganizacionGenerar> findOrganizacionGenerarByIntProperty(String propertyName, Integer value) {
        return organizacionBss.findOrganizacionGenerarByIntProperty(propertyName, value);
    }
    
    /*
 * Método que hace una consulta a la tabla Organizacion y obtiene los registros que coincidan con el objeto <b>organizacion</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
 * @param p Número de página que se desea mostrar al usuario en el widget JQGrid.
 * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
 * @param order Indica por que campo sera ordenada la búsqueda.
 * @param ordenTipo Tipo de ordenado acendente o decendente.
 * @param Organizacion objeto que contiene los parámetros a buscar.
 * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    public Grid findByCriteria(int p, int maxResult, String order, String ordenTipo, Organizacion organizacion) {
        return organizacionBss.findByCriteria(p, maxResult, order, ordenTipo, organizacion);
    }

    /**
     * Método que obtiene el primer resultado de una consulta, utilizado cuando
     * la pagina tiene la propiedad individual
     *
     * @return regresa el primer resultado de la consulta, si no hay resultados
     * regresa null
     */
    public Organizacion findFirst() {
        return organizacionBss.findFirst();
    }

    /*
 * Método que actualiza el Estatus de los objetos en la lista
 * <b>organizacionList</b> en la tabla de <b>Organizacion</b>.
 * @param userId userId Es el ID del usuario que realizó la operación.
 * @param estatusId Es el ID del estatus al cual se quiere cambiar.
 * @param organizacionList Es una lista que contiene los registros los cuales se
 * quiere actualizar su Estatus.
     */
//    public void setEstatus(Integer estatusId, List<Integer> organizacionList) {
//        organizacionBss.setEstatus(obtieneSessionVar().getUsuarioId(), estatusId, organizacionList);
//    }

    /*
     * Método que actualiza el creaEventos de los objetos en la lista
 * <b>organizacionList</b> en la tabla de <b>Organizacion</b>.
 * @param userId userId Es el ID del usuario que realizó la operación.
 * @param estatusId Es el ID del estatus al cual se quiere cambiar.
 * @param organizacionList Es una lista que contiene los registros los cuales se
 * quiere actualizar su CreaEventos.
     */
    public void setCreaEventos(Integer creaEventosId, List<Integer> organizacionList) {
        organizacionBss.setCreaEventos(obtieneSessionVar().getUsuarioId(), creaEventosId, organizacionList);
    }

    /*
     * Método que actualiza el servicioCompleto de los objetos en la lista
 * <b>organizacionList</b> en la tabla de <b>Organizacion</b>.
 * @param userId userId Es el ID del usuario que realizó la operación.
 * @param servicioCompletoId Es el ID del estatus al cual se quiere cambiar.
 * @param organizacionList Es una lista que contiene los registros los cuales se
 * quiere actualizar su servicioCompleto.
     */
    public void setServicioCompleto(Integer servicioCompletoId, List<Integer> organizacionList) {
        organizacionBss.setServicioCompleto(obtieneSessionVar().getUsuarioId(), servicioCompletoId, organizacionList);
    }    
    /*
 * Metodos que actualizan los campos <select> en el jsp despues de haber hecho
 * una edición de datos en un popup externo.
     */
    // <editor-fold defaultstate="collapsed" desc="Getters Detalle(s)">
    /**
     * Método auxiliar utilizado para la funcionalidad "detalle"
     * (ORGANIZACION_DETALLE) campo Pais.
     *
     * @return regresa una lista de objetos.
     */
//public List<Pais> getOrganizacionDetallePais(){
//return organizacionBss.getOrganizacionDetallePais();
//}
    /**
     * Método auxiliar utilizado para la funcionalidad "detalle"
     * (ORGANIZACION_DETALLE) campo Estado.
     *
     * @return regresa una lista de objetos.
     */
//public List<Estado> getOrganizacionDetalleEstado(){
//return organizacionBss.getOrganizacionDetalleEstado();
//}
    /**
     * Método auxiliar utilizado para la funcionalidad "detalle"
     * (ORGANIZACION_DETALLE) campo Ciudad.
     *
     * @return regresa una lista de objetos.
     */
//public List<Ciudad> getOrganizacionDetalleCiudad(){
//return organizacionBss.getOrganizacionDetalleCiudad();
//}
    // </editor-fold>
    public List<Estado> filtrarEstado(int paisId) {
        return organizacionBss.filtrarEstado(paisId);
    }

    public List<Ciudad> filtrarCiudad(int estadoId) {
        return organizacionBss.filtrarCiudad(estadoId);
    }

    public String getReportDataTest(String order, String ordenTipo, Organizacion organizacion) {
        return organizacionBss.getReportDataTest(order, ordenTipo, organizacion);
    }

    /*
 * Inyección de dependencias con Spring, cada
 * referencia definida al inicio de la clase requiere un método
 * Setter.
     */
    public void setOrganizacionBss(OrganizacionBss organizacionBss) {
        this.organizacionBss = organizacionBss;
    }

}
