package com.admon.model.admon;

import com.admon.bss.admon.PaisBss;
import com.admon.model.BaseModel;
import java.util.ArrayList;
import java.util.List;

public class PaisAction extends BaseModel {

    /* Inyeccion de dependencias con Spring. Estas dependencias se configuran en 
 * el applicationContext.xml, y ademas necesitan un metodo setter por cada 
 * variable el cual es anexado al final de la clase. */
    private PaisBss paisBss;

    // Elementos de struts2 para el JSP
    private List estatusPais = new ArrayList();
    private List eliminadoPais = new ArrayList();
    String gridVisiblePais = new String();
    String gridIndividualModePais = new String();
    String nombreActionMenu = new String();

    public PaisAction() {
    }

    /*
 * Método que llena los elementos del formulario del JSP mediante Struts al cargar la página pais.jsp 
 * @return Regresa un código de tipo String, el método execute() es ejecutado y la respuesta es evaluada, finalmente se manda llamar al JSP correspondiente según la respuesta.
     */
    @Override
    public String execute() {
        setNombreActionMenu(paisBss.getNombreActionMenu());
        // <editor-fold defaultstate="collapsed" desc="Getters">
        // Obtener los parámetros del catálogo Estatus
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEstatusPais(paisBss.getParametros("key_estatus"));
        // Obtener los parámetros del catálogo Eliminado
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEliminadoPais(paisBss.getParametros("key_eliminado"));
        // </editor-fold>

        setGridVisiblePais(paisBss.hasGrid());
        setGridIndividualModePais(paisBss.isIndividual());
        return SUCCESS;
    }

    /* Cada elemento que será llenado por Struts al cargar la página
 * deben tener métodos Getter y Setter. */
 /* Getter y Setter select */
    public List getEstatusPais() {
        return estatusPais;
    }

    public void setEstatusPais(List estatus) {
        this.estatusPais = estatus;
    }

    /* Getter y Setter select */
    public List getEliminadoPais() {
        return eliminadoPais;
    }

    public void setEliminadoPais(List eliminado) {
        this.eliminadoPais = eliminado;
    }

    public String getGridVisiblePais() {
        return gridVisiblePais;
    }

    public void setGridVisiblePais(String gridVisiblePais) {
        this.gridVisiblePais = gridVisiblePais;
    }

    public String getGridIndividualModePais() {
        return gridIndividualModePais;
    }

    public void setGridIndividualModePais(String gridIndividualModePais) {
        this.gridIndividualModePais = gridIndividualModePais;
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
    public void setPaisBss(PaisBss paisBss) {
        this.paisBss = paisBss;
    }

}
