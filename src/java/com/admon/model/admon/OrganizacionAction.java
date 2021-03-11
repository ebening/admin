package com.admon.model.admon;

import com.admon.bss.admon.OrganizacionBss;
import com.admon.model.BaseModel;
import java.util.ArrayList;
import java.util.List;

public class OrganizacionAction extends BaseModel {

    /* Inyeccion de dependencias con Spring. Estas dependencias se configuran en 
 * el applicationContext.xml, y ademas necesitan un metodo setter por cada 
 * variable el cual es anexado al final de la clase. */
    private OrganizacionBss organizacionBss;

    // Elementos de struts2 para el JSP
//    private List estatusOrganizacion = new ArrayList();
    private List creaEventosOrganizacion = new ArrayList();
     private List servicioCompletoOrganizacion = new ArrayList();   
    private List eliminadoOrganizacion = new ArrayList();
    // Elementos de struts2 para el JSP (Embebido)
    private List organizacionOrganizacionCredencial = new ArrayList();
    private String organizacionOrganizacionCredencialLink = new String();
    private List estatusOrganizacionCredencial = new ArrayList();
    private List eliminadoOrganizacionCredencial = new ArrayList();
    private List organizacionCredencialesAplicaciones = new ArrayList();    
    // Elementos de struts2 para el JSP (Embebido)
    private List paisOrganizacion = new ArrayList();
    private String paisOrganizacionLink = new String();
    private List estadoOrganizacion = new ArrayList();
    private String estadoOrganizacionLink = new String();
    private List ciudadOrganizacion = new ArrayList();
    private String ciudadOrganizacionLink = new String();
    // Elementos de struts2 para el JSP (Embebido)
    private List organizacionOrganizacionUsuario = new ArrayList();
    private String organizacionOrganizacionUsuarioLink = new String();
    private List estatusOrganizacionUsuario = new ArrayList();
    private List eliminadoOrganizacionUsuario = new ArrayList();
    private List organizacionOrganizacionGenerar = new ArrayList();
    private String organizacionOrganizacionGenerarLink = new String();
    String gridVisibleOrganizacion = new String();
    String gridIndividualModeOrganizacion = new String();
    String gridVisibleOrganizacionCredencial = new String();
    String gridIndividualModeOrganizacionCredencial = new String();
    String gridVisibleOrganizacionUsuario = new String();
    String gridIndividualModeOrganizacionUsuario = new String();
    String gridVisibleOrganizacionGenerar = new String();
    String gridIndividualModeOrganizacionGenerar = new String();
   String nombreActionMenu = new String();

    public OrganizacionAction() {
    }

    /*
 * Método que llena los elementos del formulario del JSP mediante Struts al cargar la página organizacion.jsp 
 * @return Regresa un código de tipo String, el método execute() es ejecutado y la respuesta es evaluada, finalmente se manda llamar al JSP correspondiente según la respuesta.
     */
    @Override
    public String execute() {
        setNombreActionMenu(organizacionBss.getNombreActionMenu());
        // <editor-fold defaultstate="collapsed" desc="Getters">
        // Obtener los parámetros del catálogo Estatus
        // por su key en com.admon.bss.resourcesCodesBss.properties
//        setEstatusOrganizacion(organizacionBss.getParametros("key_estatus"));
        // Obtener los parámetros del catálogo Estatus
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setCreaEventosOrganizacion(organizacionBss.getParametros("key_estatus"));
        // Obtener los parámetros del catálogo Estatus
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setServicioCompletoOrganizacion(organizacionBss.getParametros("key_estatus"));
        // Obtener los parámetros del catálogo Eliminado
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEliminadoOrganizacion(organizacionBss.getParametros("key_eliminado"));
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Getters Detalle(s)">
        // Obtener todos los registros de la tabla Organizacion.
        setOrganizacionOrganizacionCredencial(organizacionBss.getOrganizacionCredencialOrganizacion());
        setOrganizacionOrganizacionCredencialLink(getLink("OrganizacionCredencial", "organizacion"));
        // Obtener los parámetros del catálogo Estatus
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEstatusOrganizacionCredencial(organizacionBss.getParametros("key_estatus"));
        //Obtiene todo los registros de la tabla de catalgo de configuracion de aplicaciones 
        setOrganizacionCredencialesAplicaciones(organizacionBss.getParametros("key_aplicacion"));
        // Obtener los parámetros del catálogo Eliminado
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEliminadoOrganizacionCredencial(organizacionBss.getParametros("key_eliminado"));
        // Obtener todos los registros de la tabla Pais.
        setPaisOrganizacion(organizacionBss.getPais());
        setPaisOrganizacionLink(getLink("paisOrganizacion", "pais"));
        // Elemento con dependencias (selects encadenados)
        // Se carga con DWR al seleccionar un select previo
        setEstadoOrganizacion(organizacionBss.getEstado());
        setPaisOrganizacionLink(getLink("estadoOrganizacion", "estado"));
        // Elemento con dependencias (selects encadenados)
        // Se carga con DWR al seleccionar un select previo
        setCiudadOrganizacion(organizacionBss.getCiudad());
        setPaisOrganizacionLink(getLink("ciudadOrganizacion", "ciudad"));
        // Obtener todos los registros de la tabla OrganizacionUsuario.
        setOrganizacionOrganizacionUsuario(organizacionBss.getOrganizacionUsuarioOrganizacion());
        setOrganizacionOrganizacionUsuarioLink(getLink("OrganizacionGenerar", "organizacion"));
        // Obtener todos los registros de la tabla OrganizacionUsuario.
        setOrganizacionOrganizacionGenerar(organizacionBss.getOrganizacionGenerarOrganizacion());
        setOrganizacionOrganizacionGenerarLink(getLink("OrganizacionGenerar", "organizacion"));
        // Obtener los parámetros del catálogo Estatus
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEstatusOrganizacionUsuario(organizacionBss.getParametros("key_estatus"));
        // Obtener los parámetros del catálogo Eliminado
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEliminadoOrganizacionUsuario(organizacionBss.getParametros("key_eliminado"));
        // </editor-fold>

        setGridVisibleOrganizacion(organizacionBss.hasGrid());
        setGridIndividualModeOrganizacion(organizacionBss.isIndividual());
        setGridVisibleOrganizacionCredencial(organizacionBss.hasGridOrganizacionCredencial());
        setGridIndividualModeOrganizacionCredencial(organizacionBss.isIndividualOrganizacionCredencial());
        setGridVisibleOrganizacionUsuario(organizacionBss.hasGridOrganizacionUsuario());
        setGridIndividualModeOrganizacionUsuario(organizacionBss.isIndividualOrganizacionUsuario());
        setGridVisibleOrganizacionGenerar(organizacionBss.hasGridOrganizacionGenerar());
        setGridIndividualModeOrganizacionGenerar(organizacionBss.isIndividualOrganizacionGenerar());
        return SUCCESS;
    }

    /* Cada elemento que será llenado por Struts al cargar la página
 * deben tener métodos Getter y Setter. */
 /* Getter y Setter select */
//    public List getEstatusOrganizacion() {
//        return estatusOrganizacion;
//    }

//    public void setEstatusOrganizacion(List estatus) {
//        this.estatusOrganizacion = estatus;
//    }

        /* Cada elemento que será llenado por Struts al cargar la página
 * deben tener métodos Getter y Setter. */
 /* Getter y Setter select */
    public List getCreaEventosOrganizacion() {
        return creaEventosOrganizacion;
    }

    public void setCreaEventosOrganizacion(List creaEventos) {
        this.creaEventosOrganizacion = creaEventos;
    }
 
    /* Getter y Setter select */
    public List getServicioCompletoOrganizacion() {
        return servicioCompletoOrganizacion;
    }

    public void setServicioCompletoOrganizacion(List servicioCompleto) {
        this.servicioCompletoOrganizacion = servicioCompleto;
    }
    
    /* Getter y Setter select */
    public List getEliminadoOrganizacion() {
        return eliminadoOrganizacion;
    }

    public void setEliminadoOrganizacion(List eliminado) {
        this.eliminadoOrganizacion = eliminado;
    }

    /* Getter y Setter select */
    public List getOrganizacionOrganizacionCredencial() {
        return organizacionOrganizacionCredencial;
    }

    public void setOrganizacionOrganizacionCredencial(List organizacion) {
        this.organizacionOrganizacionCredencial = organizacion;
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

    public List getOrganizacionCredencialesAplicaciones() {
        return organizacionCredencialesAplicaciones;
    }

    public void setOrganizacionCredencialesAplicaciones(List organizacionCredencialesAplicaciones) {
        this.organizacionCredencialesAplicaciones = organizacionCredencialesAplicaciones;
    }

    /* Getter y Setter select */
    public List getEliminadoOrganizacionCredencial() {
        return eliminadoOrganizacionCredencial;
    }

    public void setEliminadoOrganizacionCredencial(List eliminado) {
        this.eliminadoOrganizacionCredencial = eliminado;
    }

    /* Getter y Setter select */
    public List getPaisOrganizacion() {
        return paisOrganizacion;
    }

    public void setPaisOrganizacion(List pais) {
        this.paisOrganizacion = pais;
    }

    /* Getter y Setter popup */
    public String getPaisOrganizacionLink() {
        return paisOrganizacionLink;
    }

    public void setPaisOrganizacionLink(String paisOrganizacionLink) {
        this.paisOrganizacionLink = paisOrganizacionLink;
    }

    /* Getter y Setter select */
    public List getEstadoOrganizacion() {
        return estadoOrganizacion;
    }

    public void setEstadoOrganizacion(List estado) {
        this.estadoOrganizacion = estado;
    }

    /* Getter y Setter popup */
    public String getEstadoOrganizacionLink() {
        return estadoOrganizacionLink;
    }

    public void setEstadoOrganizacionLink(String estadoOrganizacionLink) {
        this.estadoOrganizacionLink = estadoOrganizacionLink;
    }

    /* Getter y Setter select */
    public List getCiudadOrganizacion() {
        return ciudadOrganizacion;
    }

    public void setCiudadOrganizacion(List ciudad) {
        this.ciudadOrganizacion = ciudad;
    }

    /* Getter y Setter popup */
    public String getCiudadOrganizacionLink() {
        return ciudadOrganizacionLink;
    }

    public void setCiudadOrganizacionLink(String ciudadOrganizacionLink) {
        this.ciudadOrganizacionLink = ciudadOrganizacionLink;
    }

/* Getter y Setter select */
    public List getOrganizacionOrganizacionUsuario() {
        return organizacionOrganizacionUsuario;
    }

    public void setOrganizacionOrganizacionUsuario(List organizacion) {
        this.organizacionOrganizacionUsuario = organizacion;
    }

    /* Getter y Setter popup */
    public String getOrganizacionOrganizacionUsuarioLink() {
        return organizacionOrganizacionUsuarioLink;
    }

    public void setOrganizacionOrganizacionUsuarioLink(String organizacionOrganizacionUsuarioLink) {
        this.organizacionOrganizacionUsuarioLink = organizacionOrganizacionUsuarioLink;
    }


    public List getOrganizacionOrganizacionGenerar() {
        return organizacionOrganizacionGenerar;
    }

    public void setOrganizacionOrganizacionGenerar(List organizacionOrganizacionGenerar) {
        this.organizacionOrganizacionGenerar = organizacionOrganizacionGenerar;
    }

    /* Getter y Setter popup */
    public String getOrganizacionOrganizacionGenerarLink() {
        return organizacionOrganizacionGenerarLink;
    }

    public void setOrganizacionOrganizacionGenerarLink(String organizacionOrganizacionGenerarLink) {
        this.organizacionOrganizacionGenerarLink = organizacionOrganizacionGenerarLink;
    }
    
    /* Getter y Setter select */
    public List getEstatusOrganizacionUsuario() {
        return estatusOrganizacionUsuario;
    }

    public void setEstatusOrganizacionUsuario(List estatus) {
        this.estatusOrganizacionUsuario = estatus;
    }

    /* Getter y Setter select */
    public List getEliminadoOrganizacionUsuario() {
        return eliminadoOrganizacionUsuario;
    }

    public void setEliminadoOrganizacionUsuario(List eliminado) {
        this.eliminadoOrganizacionUsuario = eliminado;
    }

    public String getGridVisibleOrganizacion() {
        return gridVisibleOrganizacion;
    }

    public void setGridVisibleOrganizacion(String gridVisibleOrganizacion) {
        this.gridVisibleOrganizacion = gridVisibleOrganizacion;
    }

    public String getGridIndividualModeOrganizacion() {
        return gridIndividualModeOrganizacion;
    }

    public void setGridIndividualModeOrganizacion(String gridIndividualModeOrganizacion) {
        this.gridIndividualModeOrganizacion = gridIndividualModeOrganizacion;
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

    public String getGridVisibleOrganizacionUsuario() {
        return gridVisibleOrganizacionUsuario;
    }

    public void setGridVisibleOrganizacionUsuario(String gridVisibleOrganizacionUsuario) {
        this.gridVisibleOrganizacionUsuario = gridVisibleOrganizacionUsuario;
    }

    public String getGridIndividualModeOrganizacionUsuario() {
        return gridIndividualModeOrganizacionUsuario;
    }

    public void setGridIndividualModeOrganizacionUsuario(String gridIndividualModeOrganizacionUsuario) {
        this.gridIndividualModeOrganizacionUsuario = gridIndividualModeOrganizacionUsuario;
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
    public void setOrganizacionBss(OrganizacionBss organizacionBss) {
        this.organizacionBss = organizacionBss;
    }

}
