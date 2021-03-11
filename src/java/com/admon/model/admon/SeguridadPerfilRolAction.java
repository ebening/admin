package com.admon.model.admon;


import com.admon.bss.admon.SeguridadPerfilRolBss;
import com.admon.model.BaseModel;
import java.util.ArrayList;
import java.util.List;

public class SeguridadPerfilRolAction extends BaseModel {

    /* Inyeccion de dependencias con Spring. Estas dependencias se configuran en 
 * el applicationContext.xml, y ademas necesitan un metodo setter por cada 
 * variable el cual es anexado al final de la clase. */
    private SeguridadPerfilRolBss seguridadPerfilRolBss;

    // Elementos de struts2 para el JSP
    private List eliminadoSeguridadPerfilRol = new ArrayList();
    private String gridVisibleSeguridadPerfilRol = new String();
    private String gridIndividualModeSeguridadPerfilRol = new String();
    private String nombreActionMenu = new String();

    public SeguridadPerfilRolAction() {
    }

    /*
 * Método que llena los elementos del formulario del JSP mediante Struts al cargar la página seguridadPerfilRol.jsp 
 * @return Regresa un código de tipo String, el método execute() es ejecutado y la respuesta es evaluada, finalmente se manda llamar al JSP correspondiente según la respuesta.
     */
    @Override
    public String execute() {
        setNombreActionMenu(seguridadPerfilRolBss.getNombreActionMenu());
        // <editor-fold defaultstate="collapsed" desc="Getters">
        // Obtener los parámetros del catálogo Eliminado
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEliminadoSeguridadPerfilRol(seguridadPerfilRolBss.getParametros("key_eliminado"));
        // </editor-fold>

        setGridVisibleSeguridadPerfilRol(seguridadPerfilRolBss.hasGrid());
        setGridIndividualModeSeguridadPerfilRol(seguridadPerfilRolBss.isIndividual());
        return SUCCESS;
    }

    /* Cada elemento que será llenado por Struts al cargar la página
 * deben tener métodos Getter y Setter. */
 /* Getter y Setter select */
    /**
     * @return the eliminadoSeguridadPerfilRol
     */
    public List getEliminadoSeguridadPerfilRol() {
        return eliminadoSeguridadPerfilRol;
    }

    /**
     * @param eliminadoSeguridadPerfilRol the eliminadoSeguridadPerfilRol to set
     */
    public void setEliminadoSeguridadPerfilRol(List eliminadoSeguridadPerfilRol) {
        this.eliminadoSeguridadPerfilRol = eliminadoSeguridadPerfilRol;
    }

    /**
     * @return the gridVisibleSeguridadPerfilRol
     */
    public String getGridVisibleSeguridadPerfilRol() {
        return gridVisibleSeguridadPerfilRol;
    }

    /**
     * @param gridVisibleSeguridadPerfilRol the gridVisibleSeguridadPerfilRol to set
     */
    public void setGridVisibleSeguridadPerfilRol(String gridVisibleSeguridadPerfilRol) {
        this.gridVisibleSeguridadPerfilRol = gridVisibleSeguridadPerfilRol;
    }

    /**
     * @return the gridIndividualModeSeguridadPerfilRol
     */
    public String getGridIndividualModeSeguridadPerfilRol() {
        return gridIndividualModeSeguridadPerfilRol;
    }

    /**
     * @param gridIndividualModeSeguridadPerfilRol the gridIndividualModeSeguridadPerfilRol to set
     */
    public void setGridIndividualModeSeguridadPerfilRol(String gridIndividualModeSeguridadPerfilRol) {
        this.gridIndividualModeSeguridadPerfilRol = gridIndividualModeSeguridadPerfilRol;
    }

    /**
     * @return the nombreActionMenu
     */
    public String getNombreActionMenu() {
        return nombreActionMenu;
    }

    /**
     * @param nombreActionMenu the nombreActionMenu to set
     */
    public void setNombreActionMenu(String nombreActionMenu) {
        this.nombreActionMenu = nombreActionMenu;
    }
   
   
    /*
 * Inyección de dependencias con Spring, cada
 * referencia definida al inicio de la clase requiere un método
 * Setter.
     */
    public void setSeguridadPerfilRolBss(SeguridadPerfilRolBss seguridadPerfilRolBss) {
        this.seguridadPerfilRolBss = seguridadPerfilRolBss;
    }

}
