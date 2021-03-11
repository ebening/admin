package com.admon.model.admon;

import com.admon.bss.admon.SeguridadRolBss;
import com.admon.model.BaseModel;
import java.util.ArrayList;
import java.util.List;

public class SeguridadRolAction extends BaseModel {

    /* Inyeccion de dependencias con Spring. Estas dependencias se configuran en 
 * el applicationContext.xml, y ademas necesitan un metodo setter por cada 
 * variable el cual es anexado al final de la clase. */
    private SeguridadRolBss seguridadRolBss;

    private List rolAplicacion =new ArrayList();
    private List estatusRol = new ArrayList();
    private List eliminadoRol = new ArrayList();
    // Elementos de struts2 para el JSP (Embebido)
    private String gridVisibleRol = new String();
    private String gridIndividualModeRol = new String();
    private String gridVisibleRolPagina = new String();
    private String gridIndividualModeRolPagina = new String();
    private String nombreActionMenu = new String();

    public SeguridadRolAction() {
    }

    /*
 * Método que llena los elementos del formulario del JSP mediante Struts al cargar la página rol.jsp 
 * @return Regresa un código de tipo String, el método execute() es ejecutado y la respuesta es evaluada, finalmente se manda llamar al JSP correspondiente según la respuesta.
     */
    @Override
    public String execute() {
        setNombreActionMenu(seguridadRolBss.getNombreActionMenu());
        // <editor-fold defaultstate="collapsed" desc="Getters">
        // <editor-fold defaultstate="collapsed" desc="Getters Detalle(s)">
        // Obtener todos los registros de la tabla Seguridad_Rol.
        // Obtener los parámetros del catálogo Aplicación
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setRolAplicacion(seguridadRolBss.getParametros("key_aplicacion"));
        // Obtener los parámetros del catálogo Estatus
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEstatusRol(seguridadRolBss.getParametros("key_estatus"));
        // Obtener los parámetros del catálogo Eliminado
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEliminadoRol(seguridadRolBss.getParametros("key_eliminado"));
        // </editor-fold>

        setGridVisibleRol(seguridadRolBss.hasGrid());
        setGridIndividualModeRol(seguridadRolBss.isIndividual());
        return SUCCESS;
    }

    /* Cada elemento que será llenado por Struts al cargar la página
 * deben tener métodos Getter y Setter. */
 /* Getter y Setter select */

    public List getEstatusRol() {
        return estatusRol;
    }

    public void setEstatusRol(List estatusRol) {
        this.estatusRol = estatusRol;
    }

    public List getEliminadoRol() {
        return eliminadoRol;
    }

    public void setEliminadoRol(List eliminadoRol) {
        this.eliminadoRol = eliminadoRol;
    }

    public String getGridVisibleRol() {
        return gridVisibleRol;
    }

    public void setGridVisibleRol(String gridVisibleRol) {
        this.gridVisibleRol = gridVisibleRol;
    }

    public String getGridIndividualModeRol() {
        return gridIndividualModeRol;
    }

    public void setGridIndividualModeRol(String gridIndividualModeRol) {
        this.gridIndividualModeRol = gridIndividualModeRol;
    }

    public String getGridVisibleRolPagina() {
        return gridVisibleRolPagina;
    }

    public void setGridVisibleRolPagina(String gridVisibleRolPagina) {
        this.gridVisibleRolPagina = gridVisibleRolPagina;
    }

    public String getGridIndividualModeRolPagina() {
        return gridIndividualModeRolPagina;
    }

    public void setGridIndividualModeRolPagina(String gridIndividualModeRolPagina) {
        this.gridIndividualModeRolPagina = gridIndividualModeRolPagina;
    }

    public String getNombreActionMenu() {
        return nombreActionMenu;
    }

    public void setNombreActionMenu(String nombreActionMenu) {
        this.nombreActionMenu = nombreActionMenu;
    }
    
    public List getRolAplicacion() {
        return rolAplicacion;
    }

    public void setRolAplicacion(List rolAplicacion) {
        this.rolAplicacion = rolAplicacion;
    }

    
    
    
       /*
 * Inyección de dependencias con Spring, cada
 * referencia definida al inicio de la clase requiere un método
 * Setter.
     */
    public void setSeguridadRolBss(SeguridadRolBss seguridadRolBss) {
        this.seguridadRolBss = seguridadRolBss;
    }

}
