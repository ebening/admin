package com.admon.model.admon;

import com.admon.bss.admon.ClasificacionContraBss;
import com.admon.model.BaseModel;
import java.util.ArrayList;
import java.util.List;

public class ClasificacionContraAction extends BaseModel {

    /* Inyeccion de dependencias con Spring. Estas dependencias se configuran en 
 * el applicationContext.xml, y ademas necesitan un metodo setter por cada 
 * variable el cual es anexado al final de la clase. */
    private ClasificacionContraBss clasificacionContraBss;

    // Elementos de struts2 para el JSP
    private List estatusClasificacionContra = new ArrayList();
    private List eliminadoClasificacionContra = new ArrayList();
    String gridVisibleClasificacionContra = new String();
    String gridIndividualModeClasificacionContra = new String();
    String nombreActionMenu = new String();

    public ClasificacionContraAction() {
    }

    /*
 * Método que llena los elementos del formulario del JSP mediante Struts al cargar la página clasificacionContra.jsp 
 * @return Regresa un código de tipo String, el método execute() es ejecutado y la respuesta es evaluada, finalmente se manda llamar al JSP correspondiente según la respuesta.
     */
    @Override
    public String execute() {
        setNombreActionMenu(clasificacionContraBss.getNombreActionMenu());
        // <editor-fold defaultstate="collapsed" desc="Getters">
        // Obtener los parámetros del catálogo Estatus
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEstatusClasificacionContra(clasificacionContraBss.getParametros("key_estatus"));
        // Obtener los parámetros del catálogo Eliminado
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEliminadoClasificacionContra(clasificacionContraBss.getParametros("key_eliminado"));
        // </editor-fold>

        setGridVisibleClasificacionContra(clasificacionContraBss.hasGrid());
        setGridIndividualModeClasificacionContra(clasificacionContraBss.isIndividual());
        return SUCCESS;
    }

    /* Cada elemento que será llenado por Struts al cargar la página
 * deben tener métodos Getter y Setter. */
 /* Getter y Setter select */
    public List getEstatusClasificacionContra() {
        return estatusClasificacionContra;
    }

    public void setEstatusClasificacionContra(List estatus) {
        this.estatusClasificacionContra = estatus;
    }

    /* Getter y Setter select */
    public List getEliminadoClasificacionContra() {
        return eliminadoClasificacionContra;
    }

    public void setEliminadoClasificacionContra(List eliminado) {
        this.eliminadoClasificacionContra = eliminado;
    }

    public String getGridVisibleClasificacionContra() {
        return gridVisibleClasificacionContra;
    }

    public void setGridVisibleClasificacionContra(String gridVisibleClasificacionContra) {
        this.gridVisibleClasificacionContra = gridVisibleClasificacionContra;
    }

    public String getGridIndividualModeClasificacionContra() {
        return gridIndividualModeClasificacionContra;
    }

    public void setGridIndividualModeClasificacionContra(String gridIndividualModeClasificacionContra) {
        this.gridIndividualModeClasificacionContra = gridIndividualModeClasificacionContra;
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
    public void setClasificacionContraBss(ClasificacionContraBss clasificacionContraBss) {
        this.clasificacionContraBss = clasificacionContraBss;
    }

}
