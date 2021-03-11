package com.admon.model.admon;

import com.admon.model.BaseModel;
import java.util.ArrayList;
import java.util.List;
import com.admon.bss.admon.ComisionBss;

public class ComisionAction extends BaseModel {

    /* Inyeccion de dependencias con Spring. Estas dependencias se configuran en 
 * el applicationContext.xml, y ademas necesitan un metodo setter por cada 
 * variable el cual es anexado al final de la clase. */
    private ComisionBss comisionBss;

    // Elementos de struts2 para el JSP
    private List clasificacionContraComision = new ArrayList();
    private String clasificacionContraComisionLink = new String();
    private List subclasificacionContraComision = new ArrayList();
    private String subclasificacionContraComisionLink = new String();
//private List nombreContraComision = new ArrayList();
//private String nombreContraComisionLink = new String();
    private List estatusComision = new ArrayList();
    private List eliminadoComision = new ArrayList();
    String gridVisibleComision = new String();
    String gridIndividualModeComision = new String();
    String nombreActionMenu = new String();

    public ComisionAction() {
    }

    /*
 * Método que llena los elementos del formulario del JSP mediante Struts al cargar la página comision.jsp 
 * @return Regresa un código de tipo String, el método execute() es ejecutado y la respuesta es evaluada, finalmente se manda llamar al JSP correspondiente según la respuesta.
     */
    @Override
    public String execute() {
        setNombreActionMenu(comisionBss.getNombreActionMenu());
        // <editor-fold defaultstate="collapsed" desc="Getters">
        // Obtener todos los registros de la tabla ClasificacionContra.
        setClasificacionContraComision(comisionBss.getClasificacionContra());
        setClasificacionContraComisionLink(getLink("Comision", "clasificacionContra"));
        // Elemento con dependencias (selects encadenados)
        // Se carga con DWR al seleccionar un select previo
        setSubclasificacionContraComision(new ArrayList());
        // Elemento con dependencias (selects encadenados)
        // Se carga con DWR al seleccionar un select previo
//setNombreContraComision(new ArrayList());
        // Obtener los parámetros del catálogo Estatus
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEstatusComision(comisionBss.getParametros("key_estatus"));
        // Obtener los parámetros del catálogo Eliminado
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEliminadoComision(comisionBss.getParametros("key_eliminado"));
        // </editor-fold>

        setGridVisibleComision(comisionBss.hasGrid());
        setGridIndividualModeComision(comisionBss.isIndividual());
        return SUCCESS;
    }

    /* Cada elemento que será llenado por Struts al cargar la página
 * deben tener métodos Getter y Setter. */
 /* Getter y Setter select */
    public List getClasificacionContraComision() {
        return clasificacionContraComision;
    }

    public void setClasificacionContraComision(List clasificacionContra) {
        this.clasificacionContraComision = clasificacionContra;
    }

    /* Getter y Setter popup */
    public String getClasificacionContraComisionLink() {
        return clasificacionContraComisionLink;
    }

    public void setClasificacionContraComisionLink(String clasificacionContraComisionLink) {
        this.clasificacionContraComisionLink = clasificacionContraComisionLink;
    }

    /* Getter y Setter select */
    public List getSubclasificacionContraComision() {
        return subclasificacionContraComision;
    }

    public void setSubclasificacionContraComision(List subclasificacionContra) {
        this.subclasificacionContraComision = subclasificacionContra;
    }

    /* Getter y Setter popup */
    public String getSubclasificacionContraComisionLink() {
        return subclasificacionContraComisionLink;
    }

    public void setSubclasificacionContraComisionLink(String subclasificacionContraComisionLink) {
        this.subclasificacionContraComisionLink = subclasificacionContraComisionLink;
    }

    /* Getter y Setter select */
//public List getNombreContraComision() {
//return nombreContraComision;
//}
//
//public void setNombreContraComision(List nombreContra) {
//this.nombreContraComision = nombreContra;
//}
///* Getter y Setter popup */
//public String getNombreContraComisionLink() {
//return nombreContraComisionLink;
//}
//
//public void setNombreContraComisionLink(String nombreContraComisionLink) {
//this.nombreContraComisionLink = nombreContraComisionLink;
//}

    /* Getter y Setter select */
    public List getEstatusComision() {
        return estatusComision;
    }

    public void setEstatusComision(List estatus) {
        this.estatusComision = estatus;
    }

    /* Getter y Setter select */
    public List getEliminadoComision() {
        return eliminadoComision;
    }

    public void setEliminadoComision(List eliminado) {
        this.eliminadoComision = eliminado;
    }

    public String getGridVisibleComision() {
        return gridVisibleComision;
    }

    public void setGridVisibleComision(String gridVisibleComision) {
        this.gridVisibleComision = gridVisibleComision;
    }

    public String getGridIndividualModeComision() {
        return gridIndividualModeComision;
    }

    public void setGridIndividualModeComision(String gridIndividualModeComision) {
        this.gridIndividualModeComision = gridIndividualModeComision;
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
    public void setComisionBss(ComisionBss comisionBss) {
        this.comisionBss = comisionBss;
    }

}
