package com.admon.model.admon;

import com.admon.bss.admon.OrganizacionUsuarioBss;
import com.admon.model.BaseModel;
import java.util.ArrayList;
import java.util.List;

public class OrganizacionUsuarioAction extends BaseModel {

    /* Inyeccion de dependencias con Spring. Estas dependencias se configuran en 
 * el applicationContext.xml, y ademas necesitan un metodo setter por cada 
 * variable el cual es anexado al final de la clase. */
    private OrganizacionUsuarioBss organizacionUsuarioBss;

    // Elementos de struts2 para el JSP
    private List organizacionOrganizacionUsuario = new ArrayList();
    private String organizacionOrganizacionUsuarioLink = new String();
    private List estatusOrganizacionUsuario = new ArrayList();
    private List eliminadoOrganizacionUsuario = new ArrayList();
    String gridVisibleOrganizacionUsuario = new String();
    String gridIndividualModeOrganizacionUsuario = new String();
    String nombreActionMenu = new String();

    public OrganizacionUsuarioAction() {
    }

    /*
 * Método que llena los elementos del formulario del JSP mediante Struts al cargar la página organizacionUsuario.jsp 
 * @return Regresa un código de tipo String, el método execute() es ejecutado y la respuesta es evaluada, finalmente se manda llamar al JSP correspondiente según la respuesta.
     */
    @Override
    public String execute() {
        setNombreActionMenu(organizacionUsuarioBss.getNombreActionMenu());
        // <editor-fold defaultstate="collapsed" desc="Getters">
        // Obtener todos los registros de la tabla Organizacion.
        setOrganizacionOrganizacionUsuario(organizacionUsuarioBss.getOrganizacion());
        setOrganizacionOrganizacionUsuarioLink(getLink("OrganizacionUsuario", "organizacion"));
        // Obtener los parámetros del catálogo Estatus
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEstatusOrganizacionUsuario(organizacionUsuarioBss.getParametros("key_estatus"));
        // Obtener los parámetros del catálogo Eliminado
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEliminadoOrganizacionUsuario(organizacionUsuarioBss.getParametros("key_eliminado"));
        // </editor-fold>

        setGridVisibleOrganizacionUsuario(organizacionUsuarioBss.hasGrid());
        setGridIndividualModeOrganizacionUsuario(organizacionUsuarioBss.isIndividual());
        return SUCCESS;
    }

    /* Cada elemento que será llenado por Struts al cargar la página
 * deben tener métodos Getter y Setter. */
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
    public void setOrganizacionUsuarioBss(OrganizacionUsuarioBss organizacionUsuarioBss) {
        this.organizacionUsuarioBss = organizacionUsuarioBss;
    }

}
