package com.admon.model.admon;

import com.admon.bss.admon.OrganizacionGenerarBss;
import com.admon.model.BaseModel;
import java.util.ArrayList;
import java.util.List;

public class OrganizacionGenerarAction extends BaseModel {

    /* Inyeccion de dependencias con Spring. Estas dependencias se configuran en 
 * el applicationContext.xml, y ademas necesitan un metodo setter por cada 
 * variable el cual es anexado al final de la clase. */
    private OrganizacionGenerarBss organizacionGenerarBss;

    // Elementos de struts2 para el JSP
    private List organizacionOrganizacionGenerar = new ArrayList();
    private List organizacionGeneraresAplicaciones = new ArrayList();
    private String organizacionOrganizacionGenerarLink = new String();
    private List estatusOrganizacionGenerar = new ArrayList();
    private List eliminadoOrganizacionGenerar = new ArrayList();
    String gridVisibleOrganizacionGenerar = new String();
    String gridIndividualModeOrganizacionGenerar = new String();
    String nombreActionMenu = new String();

    public OrganizacionGenerarAction() {
    }

    /*
 * Método que llena los elementos del formulario del JSP mediante Struts al cargar la página organizacionGenerar.jsp 
 * @return Regresa un código de tipo String, el método execute() es ejecutado y la respuesta es evaluada, finalmente se manda llamar al JSP correspondiente según la respuesta.
     */
    @Override
    public String execute() {
        setNombreActionMenu(organizacionGenerarBss.getNombreActionMenu());
        // <editor-fold defaultstate="collapsed" desc="Getters">
        // Obtener todos los registros de la tabla Organizacion.
        setOrganizacionOrganizacionGenerar(organizacionGenerarBss.getOrganizacion());
        setOrganizacionOrganizacionGenerarLink(getLink("OrganizacionGenerar", "organizacion"));
        //Obtiene todo los registros de la tabla de catalgo de configuracion de aplicaciones 
        setOrganizacionGeneraresAplicaciones(organizacionGenerarBss.getParametros("key_aplicacion"));
        // Obtener los parámetros del catálogo Estatus
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEstatusOrganizacionGenerar(organizacionGenerarBss.getParametros("key_estatus"));
        // Obtener los parámetros del catálogo Eliminado
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEliminadoOrganizacionGenerar(organizacionGenerarBss.getParametros("key_eliminado"));
        // </editor-fold>

        setGridVisibleOrganizacionGenerar(organizacionGenerarBss.hasGrid());
        setGridIndividualModeOrganizacionGenerar(organizacionGenerarBss.isIndividual());
        return SUCCESS;
    }

    /* Cada elemento que será llenado por Struts al cargar la página
 * deben tener métodos Getter y Setter. */
 /* Getter y Setter select */
    public List getOrganizacionOrganizacionGenerar() {
        return organizacionOrganizacionGenerar;
    }

    public void setOrganizacionOrganizacionGenerar(List organizacion) {
        this.organizacionOrganizacionGenerar = organizacion;
    }

    public List getOrganizacionGeneraresAplicaciones() {
        return organizacionGeneraresAplicaciones;
    }

    public void setOrganizacionGeneraresAplicaciones(List organizacionGeneraresAplicaciones) {
        this.organizacionGeneraresAplicaciones = organizacionGeneraresAplicaciones;
    }

    /* Getter y Setter popup */
    public String getOrganizacionOrganizacionGenerarLink() {
        return organizacionOrganizacionGenerarLink;
    }

    public void setOrganizacionOrganizacionGenerarLink(String organizacionOrganizacionGenerarLink) {
        this.organizacionOrganizacionGenerarLink = organizacionOrganizacionGenerarLink;
    }

    /* Getter y Setter select */
    public List getEstatusOrganizacionGenerar() {
        return estatusOrganizacionGenerar;
    }

    public void setEstatusOrganizacionGenerar(List estatus) {
        this.estatusOrganizacionGenerar = estatus;
    }

    /* Getter y Setter select */
    public List getEliminadoOrganizacionGenerar() {
        return eliminadoOrganizacionGenerar;
    }

    public void setEliminadoOrganizacionGenerar(List eliminado) {
        this.eliminadoOrganizacionGenerar = eliminado;
    }

    public String getGridVisibleOrganizacionGenerar() {
        return gridVisibleOrganizacionGenerar;
    }

    public void setGridVisibleOrganizacionGenerar(String gridVisibleOrganizacionGenerar) {
        this.gridVisibleOrganizacionGenerar = gridVisibleOrganizacionGenerar;
    }

    public String getGridIndividualModeOrganizacionGenerar() {
        return gridIndividualModeOrganizacionGenerar;
    }

    public void setGridIndividualModeOrganizacionGenerar(String gridIndividualModeOrganizacionGenerar) {
        this.gridIndividualModeOrganizacionGenerar = gridIndividualModeOrganizacionGenerar;
    }

    public String getNombreActionMenu() {
        return nombreActionMenu;
    }

    public void setNombreActionMenu(String nombreActionMenu) {
        this.nombreActionMenu = nombreActionMenu;
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
