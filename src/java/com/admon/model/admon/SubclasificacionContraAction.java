package com.admon.model.admon;

import com.admon.bss.admon.SubclasificacionContraBss;
import com.admon.model.BaseModel;
import java.util.ArrayList;
import java.util.List;

public class SubclasificacionContraAction extends BaseModel {

    /* Inyeccion de dependencias con Spring. Estas dependencias se configuran en 
 * el applicationContext.xml, y ademas necesitan un metodo setter por cada 
 * variable el cual es anexado al final de la clase. */
    private SubclasificacionContraBss subclasificacionContraBss;

    // Elementos de struts2 para el JSP
    private List clasificacionContraSubclasificacionContra = new ArrayList();
    private String clasificacionContraSubclasificacionContraLink = new String();
    private List estatusSubclasificacionContra = new ArrayList();
    private List eliminadoSubclasificacionContra = new ArrayList();
    String gridVisibleSubclasificacionContra = new String();
    String gridIndividualModeSubclasificacionContra = new String();
    String nombreActionMenu = new String();

    public SubclasificacionContraAction() {
    }

    /*
 * Método que llena los elementos del formulario del JSP mediante Struts al cargar la página subclasificacionContra.jsp 
 * @return Regresa un código de tipo String, el método execute() es ejecutado y la respuesta es evaluada, finalmente se manda llamar al JSP correspondiente según la respuesta.
     */
    @Override
    public String execute() {
        setNombreActionMenu(subclasificacionContraBss.getNombreActionMenu());
        // <editor-fold defaultstate="collapsed" desc="Getters">
        // Obtener todos los registros de la tabla ClasificacionContra.
        setClasificacionContraSubclasificacionContra(subclasificacionContraBss.getClasificacionContra());
        setClasificacionContraSubclasificacionContraLink(getLink("SubclasificacionContra", "clasificacionContra"));
        // Obtener los parámetros del catálogo Estatus
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEstatusSubclasificacionContra(subclasificacionContraBss.getParametros("key_estatus"));
        // Obtener los parámetros del catálogo Eliminado
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEliminadoSubclasificacionContra(subclasificacionContraBss.getParametros("key_eliminado"));
        // </editor-fold>

        setGridVisibleSubclasificacionContra(subclasificacionContraBss.hasGrid());
        setGridIndividualModeSubclasificacionContra(subclasificacionContraBss.isIndividual());
        return SUCCESS;
    }

    /* Cada elemento que será llenado por Struts al cargar la página
 * deben tener métodos Getter y Setter. */
 /* Getter y Setter select */
    public List getClasificacionContraSubclasificacionContra() {
        return clasificacionContraSubclasificacionContra;
    }

    public void setClasificacionContraSubclasificacionContra(List clasificacionContra) {
        this.clasificacionContraSubclasificacionContra = clasificacionContra;
    }

    /* Getter y Setter popup */
    public String getClasificacionContraSubclasificacionContraLink() {
        return clasificacionContraSubclasificacionContraLink;
    }

    public void setClasificacionContraSubclasificacionContraLink(String clasificacionContraSubclasificacionContraLink) {
        this.clasificacionContraSubclasificacionContraLink = clasificacionContraSubclasificacionContraLink;
    }

    /* Getter y Setter select */
    public List getEstatusSubclasificacionContra() {
        return estatusSubclasificacionContra;
    }

    public void setEstatusSubclasificacionContra(List estatus) {
        this.estatusSubclasificacionContra = estatus;
    }

    /* Getter y Setter select */
    public List getEliminadoSubclasificacionContra() {
        return eliminadoSubclasificacionContra;
    }

    public void setEliminadoSubclasificacionContra(List eliminado) {
        this.eliminadoSubclasificacionContra = eliminado;
    }

    public String getGridVisibleSubclasificacionContra() {
        return gridVisibleSubclasificacionContra;
    }

    public void setGridVisibleSubclasificacionContra(String gridVisibleSubclasificacionContra) {
        this.gridVisibleSubclasificacionContra = gridVisibleSubclasificacionContra;
    }

    public String getGridIndividualModeSubclasificacionContra() {
        return gridIndividualModeSubclasificacionContra;
    }

    public void setGridIndividualModeSubclasificacionContra(String gridIndividualModeSubclasificacionContra) {
        this.gridIndividualModeSubclasificacionContra = gridIndividualModeSubclasificacionContra;
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
    public void setSubclasificacionContraBss(SubclasificacionContraBss subclasificacionContraBss) {
        this.subclasificacionContraBss = subclasificacionContraBss;
    }

}
