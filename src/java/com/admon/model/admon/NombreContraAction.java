package com.admon.model.admon;

import com.admon.bss.admon.NombreContraBss;
import com.admon.model.BaseModel;
import java.util.ArrayList;
import java.util.List;

public class NombreContraAction extends BaseModel {

    /* Inyeccion de dependencias con Spring. Estas dependencias se configuran en 
 * el applicationContext.xml, y ademas necesitan un metodo setter por cada 
 * variable el cual es anexado al final de la clase. */
    private NombreContraBss nombreContraBss;

    // Elementos de struts2 para el JSP
    private List clasificacionContraNombreContra = new ArrayList();
    private String clasificacionContraNombreContraLink = new String();
    private List subclasificacionContraNombreContra = new ArrayList();
    private String subclasificacionContraNombreContraLink = new String();
    private List tipoValorNombreContra = new ArrayList();
    private String tipoValorNombreContraLink = new String();
    private List estatusNombreContra = new ArrayList();
    private List eliminadoNombreContra = new ArrayList();
    String gridVisibleNombreContra = new String();
    String gridIndividualModeNombreContra = new String();
    String nombreActionMenu = new String();

    public NombreContraAction() {
    }

    /*
 * Método que llena los elementos del formulario del JSP mediante Struts al cargar la página nombreContra.jsp 
 * @return Regresa un código de tipo String, el método execute() es ejecutado y la respuesta es evaluada, finalmente se manda llamar al JSP correspondiente según la respuesta.
     */
    @Override
    public String execute() {
        setNombreActionMenu(nombreContraBss.getNombreActionMenu());
        // <editor-fold defaultstate="collapsed" desc="Getters">
        // Obtener todos los registros de la tabla ClasificacionContra.
        setClasificacionContraNombreContra(nombreContraBss.getClasificacionContra());
        setClasificacionContraNombreContraLink(getLink("NombreContra", "clasificacionContra"));
        // Elemento con dependencias (selects encadenados)
        // Se carga con DWR al seleccionar un select previo
        setSubclasificacionContraNombreContra(new ArrayList());
        // Obtener los parámetros del catálogo TipoValor
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setTipoValorNombreContra(nombreContraBss.getParametros("key_tipovalor"));
        // Obtener los parámetros del catálogo Estatus
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEstatusNombreContra(nombreContraBss.getParametros("key_estatus"));
        // Obtener los parámetros del catálogo Eliminado
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEliminadoNombreContra(nombreContraBss.getParametros("key_eliminado"));
        // </editor-fold>

        setGridVisibleNombreContra(nombreContraBss.hasGrid());
        setGridIndividualModeNombreContra(nombreContraBss.isIndividual());
        return SUCCESS;
    }

    /* Cada elemento que será llenado por Struts al cargar la página
 * deben tener métodos Getter y Setter. */
 /* Getter y Setter select */
    public List getClasificacionContraNombreContra() {
        return clasificacionContraNombreContra;
    }

    public void setClasificacionContraNombreContra(List clasificacionContra) {
        this.clasificacionContraNombreContra = clasificacionContra;
    }

    /* Getter y Setter popup */
    public String getClasificacionContraNombreContraLink() {
        return clasificacionContraNombreContraLink;
    }

    public void setClasificacionContraNombreContraLink(String clasificacionContraNombreContraLink) {
        this.clasificacionContraNombreContraLink = clasificacionContraNombreContraLink;
    }

    /* Getter y Setter select */
    public List getSubclasificacionContraNombreContra() {
        return subclasificacionContraNombreContra;
    }

    public void setSubclasificacionContraNombreContra(List subclasificacionContra) {
        this.subclasificacionContraNombreContra = subclasificacionContra;
    }

    /* Getter y Setter popup */
    public String getSubclasificacionContraNombreContraLink() {
        return subclasificacionContraNombreContraLink;
    }

    public void setSubclasificacionContraNombreContraLink(String subclasificacionContraNombreContraLink) {
        this.subclasificacionContraNombreContraLink = subclasificacionContraNombreContraLink;
    }

    /* Getter y Setter select */
    public List getTipoValorNombreContra() {
        return tipoValorNombreContra;
    }

    public void setTipoValorNombreContra(List tipoValor) {
        this.tipoValorNombreContra = tipoValor;
    }

    /* Getter y Setter popup */
    public String getTipoValorNombreContraLink() {
        return tipoValorNombreContraLink;
    }

    public void setTipoValorNombreContraLink(String tipoValorNombreContraLink) {
        this.tipoValorNombreContraLink = tipoValorNombreContraLink;
    }

    /* Getter y Setter select */
    public List getEstatusNombreContra() {
        return estatusNombreContra;
    }

    public void setEstatusNombreContra(List estatus) {
        this.estatusNombreContra = estatus;
    }

    /* Getter y Setter select */
    public List getEliminadoNombreContra() {
        return eliminadoNombreContra;
    }

    public void setEliminadoNombreContra(List eliminado) {
        this.eliminadoNombreContra = eliminado;
    }

    public String getGridVisibleNombreContra() {
        return gridVisibleNombreContra;
    }

    public void setGridVisibleNombreContra(String gridVisibleNombreContra) {
        this.gridVisibleNombreContra = gridVisibleNombreContra;
    }

    public String getGridIndividualModeNombreContra() {
        return gridIndividualModeNombreContra;
    }

    public void setGridIndividualModeNombreContra(String gridIndividualModeNombreContra) {
        this.gridIndividualModeNombreContra = gridIndividualModeNombreContra;
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
    public void setNombreContraBss(NombreContraBss nombreContraBss) {
        this.nombreContraBss = nombreContraBss;
    }

}
