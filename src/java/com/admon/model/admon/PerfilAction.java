package com.admon.model.admon;

import com.admon.bss.admon.PerfilBss;
import com.admon.model.BaseModel;
import java.util.ArrayList;
import java.util.List;

public class PerfilAction extends BaseModel {

    /* Inyeccion de dependencias con Spring. Estas dependencias se configuran en 
 * el applicationContext.xml, y ademas necesitan un metodo setter por cada 
 * variable el cual es anexado al final de la clase. */
    private PerfilBss perfilBss;

    // Elementos de struts2 para el JSP
    private List estatusPerfil = new ArrayList();
    private List eliminadoPerfil = new ArrayList();
    // Elementos de struts2 para el JSP (Embebido)
    private List perfilPerfilPagina = new ArrayList();
    private String perfilPerfilPaginaLink = new String();
    private List paginaPerfilPagina = new ArrayList();
    private String paginaPerfilPaginaLink = new String();
    private List estatusPerfilPagina = new ArrayList();
    private List eliminadoPerfilPagina = new ArrayList();
    String gridVisiblePerfil = new String();
    String gridIndividualModePerfil = new String();
    String gridVisiblePerfilPagina = new String();
    String gridIndividualModePerfilPagina = new String();
    String nombreActionMenu = new String();

    public PerfilAction() {
    }

    /*
 * Método que llena los elementos del formulario del JSP mediante Struts al cargar la página perfil.jsp 
 * @return Regresa un código de tipo String, el método execute() es ejecutado y la respuesta es evaluada, finalmente se manda llamar al JSP correspondiente según la respuesta.
     */
    @Override
    public String execute() {
        setNombreActionMenu(perfilBss.getNombreActionMenu());
        // <editor-fold defaultstate="collapsed" desc="Getters">
        // Obtener los parámetros del catálogo Estatus
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEstatusPerfil(perfilBss.getParametros("key_estatus"));
        // Obtener los parámetros del catálogo Eliminado
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEliminadoPerfil(perfilBss.getParametros("key_eliminado"));
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Getters Detalle(s)">
        // Obtener todos los registros de la tabla Perfil.
        setPerfilPerfilPagina(perfilBss.getPerfilPaginaPerfil());
        setPerfilPerfilPaginaLink(getLink("PerfilPagina", "perfil"));
        // Obtener todos los registros de la tabla Pagina.
        setPaginaPerfilPagina(perfilBss.getPerfilPaginaPagina());
        setPaginaPerfilPaginaLink(getLink("PerfilPagina", "pagina"));
        // Obtener los parámetros del catálogo Estatus
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEstatusPerfilPagina(perfilBss.getParametros("key_estatus"));
        // Obtener los parámetros del catálogo Eliminado
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEliminadoPerfilPagina(perfilBss.getParametros("key_eliminado"));
        // </editor-fold>

        setGridVisiblePerfil(perfilBss.hasGrid());
        setGridIndividualModePerfil(perfilBss.isIndividual());
        setGridVisiblePerfilPagina(perfilBss.hasGridPerfilPagina());
        setGridIndividualModePerfilPagina(perfilBss.isIndividualPerfilPagina());
        return SUCCESS;
    }

    /* Cada elemento que será llenado por Struts al cargar la página
 * deben tener métodos Getter y Setter. */
 /* Getter y Setter select */
    public List getEstatusPerfil() {
        return estatusPerfil;
    }

    public void setEstatusPerfil(List estatus) {
        this.estatusPerfil = estatus;
    }

    /* Getter y Setter select */
    public List getEliminadoPerfil() {
        return eliminadoPerfil;
    }

    public void setEliminadoPerfil(List eliminado) {
        this.eliminadoPerfil = eliminado;
    }

    /* Getter y Setter select */
    public List getPerfilPerfilPagina() {
        return perfilPerfilPagina;
    }

    public void setPerfilPerfilPagina(List perfil) {
        this.perfilPerfilPagina = perfil;
    }

    /* Getter y Setter popup */
    public String getPerfilPerfilPaginaLink() {
        return perfilPerfilPaginaLink;
    }

    public void setPerfilPerfilPaginaLink(String perfilPerfilPaginaLink) {
        this.perfilPerfilPaginaLink = perfilPerfilPaginaLink;
    }

    /* Getter y Setter select */
    public List getPaginaPerfilPagina() {
        return paginaPerfilPagina;
    }

    public void setPaginaPerfilPagina(List pagina) {
        this.paginaPerfilPagina = pagina;
    }

    /* Getter y Setter popup */
    public String getPaginaPerfilPaginaLink() {
        return paginaPerfilPaginaLink;
    }

    public void setPaginaPerfilPaginaLink(String paginaPerfilPaginaLink) {
        this.paginaPerfilPaginaLink = paginaPerfilPaginaLink;
    }

    /* Getter y Setter select */
    public List getEstatusPerfilPagina() {
        return estatusPerfilPagina;
    }

    public void setEstatusPerfilPagina(List estatus) {
        this.estatusPerfilPagina = estatus;
    }

    /* Getter y Setter select */
    public List getEliminadoPerfilPagina() {
        return eliminadoPerfilPagina;
    }

    public void setEliminadoPerfilPagina(List eliminado) {
        this.eliminadoPerfilPagina = eliminado;
    }

    public String getGridVisiblePerfil() {
        return gridVisiblePerfil;
    }

    public void setGridVisiblePerfil(String gridVisiblePerfil) {
        this.gridVisiblePerfil = gridVisiblePerfil;
    }

    public String getGridIndividualModePerfil() {
        return gridIndividualModePerfil;
    }

    public void setGridIndividualModePerfil(String gridIndividualModePerfil) {
        this.gridIndividualModePerfil = gridIndividualModePerfil;
    }

    public String getGridVisiblePerfilPagina() {
        return gridVisiblePerfilPagina;
    }

    public void setGridVisiblePerfilPagina(String gridVisiblePerfilPagina) {
        this.gridVisiblePerfilPagina = gridVisiblePerfilPagina;
    }

    public String getGridIndividualModePerfilPagina() {
        return gridIndividualModePerfilPagina;
    }

    public void setGridIndividualModePerfilPagina(String gridIndividualModePerfilPagina) {
        this.gridIndividualModePerfilPagina = gridIndividualModePerfilPagina;
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
    public void setPerfilBss(PerfilBss perfilBss) {
        this.perfilBss = perfilBss;
    }

}
