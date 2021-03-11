package com.admon.model.admon;

import com.admon.bss.admon.PaisBss;
import com.admon.bss.admon.SeguridadPerfilBss;
import com.admon.model.BaseModel;
import java.util.ArrayList;
import java.util.List;

public class SeguridadPerfilAction extends BaseModel {

    /* Inyeccion de dependencias con Spring. Estas dependencias se configuran en 
 * el applicationContext.xml, y ademas necesitan un metodo setter por cada 
 * variable el cual es anexado al final de la clase. */
    private SeguridadPerfilBss seguridadPerfilBss;

    // Elementos de struts2 para el JSP
    private List estatusSeguridadPerfil = new ArrayList();
    private List eliminadoSeguridadPerfil = new ArrayList();
    private String gridVisibleSeguridadPerfil = new String();
    private String gridIndividualModeSeguridadPerfil = new String();
    private String nombreActionMenu = new String();

    public SeguridadPerfilAction() {
    }

    /*
 * Método que llena los elementos del formulario del JSP mediante Struts al cargar la página seguridadPerfil.jsp 
 * @return Regresa un código de tipo String, el método execute() es ejecutado y la respuesta es evaluada, finalmente se manda llamar al JSP correspondiente según la respuesta.
     */
    @Override
    public String execute() {
        setNombreActionMenu(seguridadPerfilBss.getNombreActionMenu());
        // <editor-fold defaultstate="collapsed" desc="Getters">
        // Obtener los parámetros del catálogo Estatus
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEstatusSeguridadPerfil(seguridadPerfilBss.getParametros("key_estatus"));
        // Obtener los parámetros del catálogo Eliminado
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEliminadoSeguridadPerfil(seguridadPerfilBss.getParametros("key_eliminado"));
        // </editor-fold>

        setGridVisibleSeguridadPerfil(seguridadPerfilBss.hasGrid());
        setGridIndividualModeSeguridadPerfil(seguridadPerfilBss.isIndividual());
        return SUCCESS;
    }

    /* Cada elemento que será llenado por Struts al cargar la página
 * deben tener métodos Getter y Setter. */
 /* Getter y Setter select */
    public List getEstatusSeguridadPerfil() {
        return estatusSeguridadPerfil;
    }

    public void setEstatusSeguridadPerfil(List estatusSeguridadPerfil) {
        this.estatusSeguridadPerfil = estatusSeguridadPerfil;
    }

    public List getEliminadoSeguridadPerfil() {
        return eliminadoSeguridadPerfil;
    }

    public void setEliminadoSeguridadPerfil(List eliminadoSeguridadPerfil) {
        this.eliminadoSeguridadPerfil = eliminadoSeguridadPerfil;
    }

    public String getGridVisibleSeguridadPerfil() {
        return gridVisibleSeguridadPerfil;
    }

    public void setGridVisibleSeguridadPerfil(String gridVisibleSeguridadPerfil) {
        this.gridVisibleSeguridadPerfil = gridVisibleSeguridadPerfil;
    }

    public String getGridIndividualModeSeguridadPerfil() {
        return gridIndividualModeSeguridadPerfil;
    }

    public void setGridIndividualModeSeguridadPerfil(String gridIndividualModeSeguridadPerfil) {
        this.gridIndividualModeSeguridadPerfil = gridIndividualModeSeguridadPerfil;
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
    public void setSeguridadPerfilBss(SeguridadPerfilBss seguridadPerfilBss) {
        this.seguridadPerfilBss = seguridadPerfilBss;
    }

}
