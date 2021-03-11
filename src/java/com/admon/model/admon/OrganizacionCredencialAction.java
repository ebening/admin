package com.admon.model.admon;

import com.admon.bss.admon.OrganizacionCredencialBss;
import com.admon.model.BaseModel;
import java.util.ArrayList;
import java.util.List;

public class OrganizacionCredencialAction extends BaseModel {

    /* Inyeccion de dependencias con Spring. Estas dependencias se configuran en 
 * el applicationContext.xml, y ademas necesitan un metodo setter por cada 
 * variable el cual es anexado al final de la clase. */
    private OrganizacionCredencialBss organizacionCredencialBss;

    // Elementos de struts2 para el JSP
    private List organizacionOrganizacionCredencial = new ArrayList();
    private List organizacionCredencialesAplicaciones = new ArrayList();
    private String organizacionOrganizacionCredencialLink = new String();
    private List estatusOrganizacionCredencial = new ArrayList();
    private List eliminadoOrganizacionCredencial = new ArrayList();
    String gridVisibleOrganizacionCredencial = new String();
    String gridIndividualModeOrganizacionCredencial = new String();
    String nombreActionMenu = new String();

    public OrganizacionCredencialAction() {
    }

    /*
 * Método que llena los elementos del formulario del JSP mediante Struts al cargar la página organizacionCredencial.jsp 
 * @return Regresa un código de tipo String, el método execute() es ejecutado y la respuesta es evaluada, finalmente se manda llamar al JSP correspondiente según la respuesta.
     */
    @Override
    public String execute() {
        setNombreActionMenu(organizacionCredencialBss.getNombreActionMenu());
        // <editor-fold defaultstate="collapsed" desc="Getters">
        // Obtener todos los registros de la tabla Organizacion.
        setOrganizacionOrganizacionCredencial(organizacionCredencialBss.getOrganizacion());
        setOrganizacionOrganizacionCredencialLink(getLink("OrganizacionCredencial", "organizacion"));
        //Obtiene todo los registros de la tabla de catalgo de configuracion de aplicaciones 
        setOrganizacionCredencialesAplicaciones(organizacionCredencialBss.getParametros("key_aplicacion"));
        // Obtener los parámetros del catálogo Estatus
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEstatusOrganizacionCredencial(organizacionCredencialBss.getParametros("key_estatus"));
        // Obtener los parámetros del catálogo Eliminado
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEliminadoOrganizacionCredencial(organizacionCredencialBss.getParametros("key_eliminado"));
        // </editor-fold>

        setGridVisibleOrganizacionCredencial(organizacionCredencialBss.hasGrid());
        setGridIndividualModeOrganizacionCredencial(organizacionCredencialBss.isIndividual());
        return SUCCESS;
    }

    /* Cada elemento que será llenado por Struts al cargar la página
 * deben tener métodos Getter y Setter. */
 /* Getter y Setter select */
    public List getOrganizacionOrganizacionCredencial() {
        return organizacionOrganizacionCredencial;
    }

    public void setOrganizacionOrganizacionCredencial(List organizacion) {
        this.organizacionOrganizacionCredencial = organizacion;
    }

    public List getOrganizacionCredencialesAplicaciones() {
        return organizacionCredencialesAplicaciones;
    }

    public void setOrganizacionCredencialesAplicaciones(List organizacionCredencialesAplicaciones) {
        this.organizacionCredencialesAplicaciones = organizacionCredencialesAplicaciones;
    }

    /* Getter y Setter popup */
    public String getOrganizacionOrganizacionCredencialLink() {
        return organizacionOrganizacionCredencialLink;
    }

    public void setOrganizacionOrganizacionCredencialLink(String organizacionOrganizacionCredencialLink) {
        this.organizacionOrganizacionCredencialLink = organizacionOrganizacionCredencialLink;
    }

    /* Getter y Setter select */
    public List getEstatusOrganizacionCredencial() {
        return estatusOrganizacionCredencial;
    }

    public void setEstatusOrganizacionCredencial(List estatus) {
        this.estatusOrganizacionCredencial = estatus;
    }

    /* Getter y Setter select */
    public List getEliminadoOrganizacionCredencial() {
        return eliminadoOrganizacionCredencial;
    }

    public void setEliminadoOrganizacionCredencial(List eliminado) {
        this.eliminadoOrganizacionCredencial = eliminado;
    }

    public String getGridVisibleOrganizacionCredencial() {
        return gridVisibleOrganizacionCredencial;
    }

    public void setGridVisibleOrganizacionCredencial(String gridVisibleOrganizacionCredencial) {
        this.gridVisibleOrganizacionCredencial = gridVisibleOrganizacionCredencial;
    }

    public String getGridIndividualModeOrganizacionCredencial() {
        return gridIndividualModeOrganizacionCredencial;
    }

    public void setGridIndividualModeOrganizacionCredencial(String gridIndividualModeOrganizacionCredencial) {
        this.gridIndividualModeOrganizacionCredencial = gridIndividualModeOrganizacionCredencial;
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
    public void setOrganizacionCredencialBss(OrganizacionCredencialBss organizacionCredencialBss) {
        this.organizacionCredencialBss = organizacionCredencialBss;
    }

}
