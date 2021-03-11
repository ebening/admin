package com.admon.model.admon;

import com.admon.bss.admon.PaginaBss;
import com.admon.entity.admon.*;
import com.admon.model.BaseModel;
import java.util.ArrayList;
import java.util.List;

public class PaginaAction extends BaseModel {

    /* Inyeccion de dependencias con Spring. Estas dependencias se configuran en 
 * el applicationContext.xml, y ademas necesitan un metodo setter por cada 
 * variable el cual es anexado al final de la clase. */
    private PaginaBss paginaBss;

    // Elementos de struts2 para el JSP
    private List estatusPagina = new ArrayList();
    private List eliminadoPagina = new ArrayList();
    public List anidarPagina = new ArrayList();
    String gridVisiblePagina = new String();
    String gridIndividualModePagina = new String();
    String nombreActionMenu = new String();

    public PaginaAction() {
    }

    /*
 * Método que llena los elementos del formulario del JSP mediante Struts al cargar la página pagina.jsp 
 * @return Regresa un código de tipo String, el método execute() es ejecutado y la respuesta es evaluada, finalmente se manda llamar al JSP correspondiente según la respuesta.
     */
    @Override
    public String execute() {
        setNombreActionMenu(paginaBss.getNombreActionMenu());
        // <editor-fold defaultstate="collapsed" desc="Getters">
        // Obtener los parámetros del catálogo Estatus
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEstatusPagina(paginaBss.getParametros("key_estatus"));
        // Obtener los parámetros del catálogo Eliminado
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEliminadoPagina(paginaBss.getParametros("key_eliminado"));
        // </editor-fold>

        setGridVisiblePagina(paginaBss.hasGrid());
        setGridIndividualModePagina(paginaBss.isIndividual());
        return SUCCESS;
    }

    /* Cada elemento que será llenado por Struts al cargar la página
 * deben tener métodos Getter y Setter. */
 /* Getter y Setter select */
    public List getEstatusPagina() {
        return estatusPagina;
    }

    public void setEstatusPagina(List estatus) {
        this.estatusPagina = estatus;
    }

    /* Getter y Setter select */
    public List getEliminadoPagina() {
        return eliminadoPagina;
    }

    public void setEliminadoPagina(List eliminado) {
        this.eliminadoPagina = eliminado;
    }

    public List getAnidarPagina() {
        return anidarPagina;
    }

    public void setAnidarPagina(List anidarPagina) {
        this.anidarPagina = anidarPagina;
    }

    public String getGridVisiblePagina() {
        return gridVisiblePagina;
    }

    public void setGridVisiblePagina(String gridVisiblePagina) {
        this.gridVisiblePagina = gridVisiblePagina;
    }

    public String getGridIndividualModePagina() {
        return gridIndividualModePagina;
    }

    public void setGridIndividualModePagina(String gridIndividualModePagina) {
        this.gridIndividualModePagina = gridIndividualModePagina;
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
    public void setPaginaBss(PaginaBss paginaBss) {
        this.paginaBss = paginaBss;
    }

}
