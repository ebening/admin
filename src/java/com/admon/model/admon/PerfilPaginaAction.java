package com.admon.model.admon;

import com.admon.bss.admon.PerfilPaginaBss;
import com.admon.model.BaseModel;
import java.util.ArrayList;
import java.util.List;

public class PerfilPaginaAction extends BaseModel {

    /* Inyeccion de dependencias con Spring. Estas dependencias se configuran en 
 * el applicationContext.xml, y ademas necesitan un metodo setter por cada 
 * variable el cual es anexado al final de la clase. */
    private PerfilPaginaBss perfilPaginaBss;

    // Elementos de struts2 para el JSP
    private List perfilPerfilPagina = new ArrayList();
    private String perfilPerfilPaginaLink = new String();
    private List paginaPerfilPagina = new ArrayList();
    private String paginaPerfilPaginaLink = new String();
    private List estatusPerfilPagina = new ArrayList();
    private List eliminadoPerfilPagina = new ArrayList();
    String gridVisiblePerfilPagina = new String();
    String gridIndividualModePerfilPagina = new String();
    String nombreActionMenu = new String();

    public PerfilPaginaAction() {
    }

    /*
 * Método que llena los elementos del formulario del JSP mediante Struts al cargar la página perfilPagina.jsp 
 * @return Regresa un código de tipo String, el método execute() es ejecutado y la respuesta es evaluada, finalmente se manda llamar al JSP correspondiente según la respuesta.
     */
    @Override
    public String execute() {
        setNombreActionMenu(perfilPaginaBss.getNombreActionMenu());
        // <editor-fold defaultstate="collapsed" desc="Getters">
        // Obtener todos los registros de la tabla Perfil.
        setPerfilPerfilPagina(perfilPaginaBss.getPerfil());
        setPerfilPerfilPaginaLink(getLink("PerfilPagina", "perfil"));
        // Obtener todos los registros de la tabla Pagina.
        setPaginaPerfilPagina(perfilPaginaBss.getPagina());
        setPaginaPerfilPaginaLink(getLink("PerfilPagina", "pagina"));
        // Obtener los parámetros del catálogo Estatus
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEstatusPerfilPagina(perfilPaginaBss.getParametros("key_estatus"));
        // Obtener los parámetros del catálogo Eliminado
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEliminadoPerfilPagina(perfilPaginaBss.getParametros("key_eliminado"));
        // </editor-fold>

        setGridVisiblePerfilPagina(perfilPaginaBss.hasGrid());
        setGridIndividualModePerfilPagina(perfilPaginaBss.isIndividual());
        return SUCCESS;
    }

    /* Cada elemento que será llenado por Struts al cargar la página
 * deben tener métodos Getter y Setter. */
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
    public void setPerfilPaginaBss(PerfilPaginaBss perfilPaginaBss) {
        this.perfilPaginaBss = perfilPaginaBss;
    }

}
