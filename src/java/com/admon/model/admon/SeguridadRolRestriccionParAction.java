package com.admon.model.admon;

import com.admon.bss.admon.SeguridadRolRestriccionParBss;
import com.admon.model.BaseModel;
import java.util.ArrayList;
import java.util.List;
public class SeguridadRolRestriccionParAction extends BaseModel {

    /* Inyeccion de dependencias con Spring. Estas dependencias se configuran en 
 * el applicationContext.xml, y ademas necesitan un metodo setter por cada 
 * variable el cual es anexado al final de la clase. */
    private SeguridadRolRestriccionParBss seguridadRolRestriccionParBss;

    // Elementos de struts2 para el JSP
    private String nombreActionMenu = new String();
    private List aplicacion = new ArrayList();
    private List modulo = new ArrayList();
    private List roles = new ArrayList();
    private List catalogo = new ArrayList();
    private String gridVisibleCatalogo = new String();

    public SeguridadRolRestriccionParAction() {
    }

    /*
 * Método que llena los elementos del formulario del JSP mediante Struts al cargar la página seguridadRestriccionesRolRestriccion.jsp 
 * @return Regresa un código de tipo String, el método execute() es ejecutado y la respuesta es evaluada, finalmente se manda llamar al JSP correspondiente según la respuesta.
     */
    @Override
    public String execute() {
        // <editor-fold defaultstate="collapsed" desc="Getters">
        setNombreActionMenu(seguridadRolRestriccionParBss.getNombreActionMenu());
        // Obtener los parámetros del catálogo Aplicación
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setAplicacion(seguridadRolRestriccionParBss.getParametros("key_aplicacion"));
        // Obtener Roles
        setRoles(seguridadRolRestriccionParBss.getRoles());
        // Obtener Catalogos
        //setCatalogo(seguridadRolRestriccionParBss.getCatalogos());
        // </editor-fold>
        //setGridVisibleCatalogo(seguridadRolRestriccionParBss.hasGridCatalogo());
        return SUCCESS;
    }

    /* Cada elemento que será llenado por Struts al cargar la página
 * deben tener métodos Getter y Setter. */
 /* Getter y Setter select */
    public String getNombreActionMenu() {
        return nombreActionMenu;
    }

    public void setNombreActionMenu(String nombreActionMenu) {
        this.nombreActionMenu = nombreActionMenu;
    }

    public List getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(List aplicacion) {
        this.aplicacion = aplicacion;
    }

    public List getModulo() {
        return modulo;
    }

    public void setModulo(List modulo) {
        this.modulo = modulo;
    }

    public List getRoles() {
        return roles;
    }

    public void setRoles(List roles) {
        this.roles = roles;
    }

    /*public List getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(List catalogo) {
        this.catalogo = catalogo;
    }
    
    public String getGridVisibleCatalogo() {
        return gridVisibleCatalogo;
    }

    public void setGridVisibleCatalogo(String gridVisibleCatalogo) {
        this.gridVisibleCatalogo = gridVisibleCatalogo;
    }*/
    

    /*
 * Inyección de dependencias con Spring, cada
 * referencia definida al inicio de la clase requiere un método
 * Setter.
     */
    public void setSeguridadRolRestriccionParBss(SeguridadRolRestriccionParBss seguridadRolRestriccionParBss) {
        this.seguridadRolRestriccionParBss = seguridadRolRestriccionParBss;
    }


}
