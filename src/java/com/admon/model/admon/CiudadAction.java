package com.admon.model.admon;

import com.admon.model.BaseModel;
import java.util.ArrayList;
import java.util.List;
import com.admon.bss.admon.CiudadBss;

public class CiudadAction extends BaseModel {

    /* Inyeccion de dependencias con Spring. Estas dependencias se configuran en 
 * el applicationContext.xml, y ademas necesitan un metodo setter por cada 
 * variable el cual es anexado al final de la clase. */
    private CiudadBss ciudadBss;

    // Elementos de struts2 para el JSP
    private List paisCiudad = new ArrayList();
    private String paisCiudadLink = new String();
    private List estadoCiudad = new ArrayList();
    private String estadoCiudadLink = new String();
    private List estatusCiudad = new ArrayList();
    private List eliminadoCiudad = new ArrayList();
    String gridVisibleCiudad = new String();
    String gridIndividualModeCiudad = new String();
    String nombreActionMenu = new String();

    public CiudadAction() {
    }

    /*
 * Método que llena los elementos del formulario del JSP mediante Struts al cargar la página ciudad.jsp 
 * @return Regresa un código de tipo String, el método execute() es ejecutado y la respuesta es evaluada, finalmente se manda llamar al JSP correspondiente según la respuesta.
     */
    @Override
    public String execute() {
        setNombreActionMenu(ciudadBss.getNombreActionMenu());
        // <editor-fold defaultstate="collapsed" desc="Getters">
        // Obtener todos los registros de la tabla Pais.
        setPaisCiudad(ciudadBss.getPais());
        setPaisCiudadLink(getLink("Ciudad", "pais"));
        // Elemento con dependencias (selects encadenados)
        // Se carga con DWR al seleccionar un select previo
        setEstadoCiudad(new ArrayList());
        // Obtener los parámetros del catálogo Estatus
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEstatusCiudad(ciudadBss.getParametros("key_estatus"));
        // Obtener los parámetros del catálogo Eliminado
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEliminadoCiudad(ciudadBss.getParametros("key_eliminado"));
        // </editor-fold>

        setGridVisibleCiudad(ciudadBss.hasGrid());
        setGridIndividualModeCiudad(ciudadBss.isIndividual());
        return SUCCESS;
    }

    /* Cada elemento que será llenado por Struts al cargar la página
 * deben tener métodos Getter y Setter. */
 /* Getter y Setter select */
    public List getPaisCiudad() {
        return paisCiudad;
    }

    public void setPaisCiudad(List pais) {
        this.paisCiudad = pais;
    }

    /* Getter y Setter popup */
    public String getPaisCiudadLink() {
        return paisCiudadLink;
    }

    public void setPaisCiudadLink(String paisCiudadLink) {
        this.paisCiudadLink = paisCiudadLink;
    }

    /* Getter y Setter select */
    public List getEstadoCiudad() {
        return estadoCiudad;
    }

    public void setEstadoCiudad(List estado) {
        this.estadoCiudad = estado;
    }

    /* Getter y Setter popup */
    public String getEstadoCiudadLink() {
        return estadoCiudadLink;
    }

    public void setEstadoCiudadLink(String estadoCiudadLink) {
        this.estadoCiudadLink = estadoCiudadLink;
    }

    /* Getter y Setter select */
    public List getEstatusCiudad() {
        return estatusCiudad;
    }

    public void setEstatusCiudad(List estatus) {
        this.estatusCiudad = estatus;
    }

    /* Getter y Setter select */
    public List getEliminadoCiudad() {
        return eliminadoCiudad;
    }

    public void setEliminadoCiudad(List eliminado) {
        this.eliminadoCiudad = eliminado;
    }

    public String getGridVisibleCiudad() {
        return gridVisibleCiudad;
    }

    public void setGridVisibleCiudad(String gridVisibleCiudad) {
        this.gridVisibleCiudad = gridVisibleCiudad;
    }

    public String getGridIndividualModeCiudad() {
        return gridIndividualModeCiudad;
    }

    public void setGridIndividualModeCiudad(String gridIndividualModeCiudad) {
        this.gridIndividualModeCiudad = gridIndividualModeCiudad;
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
    public void setCiudadBss(CiudadBss ciudadBss) {
        this.ciudadBss = ciudadBss;
    }

}
