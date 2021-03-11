package com.admon.model.admon;

import com.admon.bss.admon.EstadoBss;
import com.admon.model.BaseModel;
import java.util.ArrayList;
import java.util.List;

public class EstadoAction extends BaseModel {

    /* Inyeccion de dependencias con Spring. Estas dependencias se configuran en 
 * el applicationContext.xml, y ademas necesitan un metodo setter por cada 
 * variable el cual es anexado al final de la clase. */
    private EstadoBss estadoBss;

    // Elementos de struts2 para el JSP
    private List paisEstado = new ArrayList();
    private String paisEstadoLink = new String();
    private List estatusEstado = new ArrayList();
    private List eliminadoEstado = new ArrayList();
    String gridVisibleEstado = new String();
    String gridIndividualModeEstado = new String();
    String nombreActionMenu = new String();

    public EstadoAction() {
    }

    /*
 * Método que llena los elementos del formulario del JSP mediante Struts al cargar la página estado.jsp 
 * @return Regresa un código de tipo String, el método execute() es ejecutado y la respuesta es evaluada, finalmente se manda llamar al JSP correspondiente según la respuesta.
     */
    @Override
    public String execute() {
        setNombreActionMenu(estadoBss.getNombreActionMenu());
        // <editor-fold defaultstate="collapsed" desc="Getters">
        // Obtener todos los registros de la tabla Pais.
        setPaisEstado(estadoBss.getPais());
        setPaisEstadoLink(getLink("Estado", "pais"));
        // Obtener los parámetros del catálogo Estatus
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEstatusEstado(estadoBss.getParametros("key_estatus"));
        // Obtener los parámetros del catálogo Eliminado
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEliminadoEstado(estadoBss.getParametros("key_eliminado"));
        // </editor-fold>

        setGridVisibleEstado(estadoBss.hasGrid());
        setGridIndividualModeEstado(estadoBss.isIndividual());
        return SUCCESS;
    }

    /* Cada elemento que será llenado por Struts al cargar la página
 * deben tener métodos Getter y Setter. */
 /* Getter y Setter select */
    public List getPaisEstado() {
        return paisEstado;
    }

    public void setPaisEstado(List pais) {
        this.paisEstado = pais;
    }

    /* Getter y Setter popup */
    public String getPaisEstadoLink() {
        return paisEstadoLink;
    }

    public void setPaisEstadoLink(String paisEstadoLink) {
        this.paisEstadoLink = paisEstadoLink;
    }

    /* Getter y Setter select */
    public List getEstatusEstado() {
        return estatusEstado;
    }

    public void setEstatusEstado(List estatus) {
        this.estatusEstado = estatus;
    }

    /* Getter y Setter select */
    public List getEliminadoEstado() {
        return eliminadoEstado;
    }

    public void setEliminadoEstado(List eliminado) {
        this.eliminadoEstado = eliminado;
    }

    public String getGridVisibleEstado() {
        return gridVisibleEstado;
    }

    public void setGridVisibleEstado(String gridVisibleEstado) {
        this.gridVisibleEstado = gridVisibleEstado;
    }

    public String getGridIndividualModeEstado() {
        return gridIndividualModeEstado;
    }

    public void setGridIndividualModeEstado(String gridIndividualModeEstado) {
        this.gridIndividualModeEstado = gridIndividualModeEstado;
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
    public void setEstadoBss(EstadoBss estadoBss) {
        this.estadoBss = estadoBss;
    }

}
