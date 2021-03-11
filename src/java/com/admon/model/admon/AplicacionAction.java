package com.admon.model.admon;

import com.admon.bss.admon.AplicacionBss;
import com.admon.model.BaseModel;
import java.util.ArrayList;
import java.util.List;

public class AplicacionAction extends BaseModel {

    /* Inyeccion de dependencias con Spring. Estas dependencias se configuran en 
 * el applicationContext.xml, y ademas necesitan un metodo setter por cada 
 * variable el cual es anexado al final de la clase. */
    private AplicacionBss aplicacionBss;

    // Elementos de struts2 para el JSP
    private List estatusAplicacion = new ArrayList();
    private List eliminadoAplicacion = new ArrayList();

    String gridVisibleAplicacion = new String();
    String gridIndividualModeAplicacion = new String();
    String nombreActionMenu = new String();

    public AplicacionAction() {
    }

    /*
 * Método que llena los elementos del formulario del JSP mediante Struts al cargar la página aplicacion.jsp 
 * @return Regresa un código de tipo String, el método execute() es ejecutado y la respuesta es evaluada, finalmente se manda llamar al JSP correspondiente según la respuesta.
     */
    @Override
    public String execute() {
        setNombreActionMenu(aplicacionBss.getNombreActionMenu());
        // <editor-fold defaultstate="collapsed" desc="Getters">
        // Obtener los parámetros del catálogo Estatus
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEstatusAplicacion(aplicacionBss.getParametros("key_estatus"));
        // Obtener los parámetros del catálogo Eliminado
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEliminadoAplicacion(aplicacionBss.getParametros("key_eliminado"));

        setGridVisibleAplicacion(aplicacionBss.hasGrid());
        setGridIndividualModeAplicacion(aplicacionBss.isIndividual());

        return SUCCESS;
    }

    /* Cada elemento que será llenado por Struts al cargar la página
 * deben tener métodos Getter y Setter. */
 /* Getter y Setter select */
    public List getEstatusAplicacion() {
        return estatusAplicacion;
    }

    public void setEstatusAplicacion(List estatus) {
        this.estatusAplicacion = estatus;
    }

    /* Getter y Setter select */
    public List getEliminadoAplicacion() {
        return eliminadoAplicacion;
    }

    public void setEliminadoAplicacion(List eliminado) {
        this.eliminadoAplicacion = eliminado;
    }

    public String getGridVisibleAplicacion() {
        return gridVisibleAplicacion;
    }

    public void setGridVisibleAplicacion(String gridVisibleAplicacion) {
        this.gridVisibleAplicacion = gridVisibleAplicacion;
    }

    public String getGridIndividualModeAplicacion() {
        return gridIndividualModeAplicacion;
    }

    public void setGridIndividualModeAplicacion(String gridIndividualModeAplicacion) {
        this.gridIndividualModeAplicacion = gridIndividualModeAplicacion;
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
    public void setAplicacionBss(AplicacionBss aplicacionBss) {
        this.aplicacionBss = aplicacionBss;
    }

}
