package com.admon.model.admon;

import com.admon.bss.admon.CatalogoBss;
//import com.admon.bss.admon.CatalogoParametroBss;
//import com.admon.bss.admon.CatalogoParametroLenguajeBss;
import com.admon.model.BaseModel;
import java.util.ArrayList;
import java.util.List;

public class CatalogoAction extends BaseModel {

    /* Inyeccion de dependencias con Spring. Estas dependencias se configuran en 
     * el applicationContext.xml, y ademas necesitan un metodo setter por cada 
     * variable el cual es anexado al final de la clase. */
    private CatalogoBss catalogoBss;
//    private CatalogoParametroBss catalogoParametroBss;
//    private CatalogoParametroLenguajeBss catalogoParametroLenguajeBss;

    // Elementos de struts2 para el JSP
    private List organizacionCatalogo = new ArrayList();
    private String organizacionCatalogoLink = new String();
    private List catalogoPadreCatalogo = new ArrayList();
    private String catalogoPadreCatalogoLink = new String();
    private List parametroDependienteCatalogo = new ArrayList();
    private String parametroDependienteCatalogoLink = new String();
    private List estatusCatalogo = new ArrayList();
    private List eliminadoCatalogo = new ArrayList();

    // Elementos de struts2 para el JSP (Embebido)
    private List catalogoCatalogoParametro = new ArrayList();
    private String catalogoCatalogoParametroLink = new String();
    private List organizacionCatalogoParametro = new ArrayList();
    private String organizacionCatalogoParametroLink = new String();
    private List estatusCatalogoParametro = new ArrayList();
    private List eliminadoCatalogoParametro = new ArrayList();

    // Elementos de struts2 para el JSP (Embebido)
    private List catalogoParametroCatalogoParametroLenguaje = new ArrayList();
    private String catalogoParametroCatalogoParametroLenguajeLink = new String();
    private List lenguajeCatalogoParametroLenguaje = new ArrayList();
    private String lenguajeCatalogoParametroLenguajeLink = new String();
    private List organizacionCatalogoParametroLenguaje = new ArrayList();
    private String organizacionCatalogoParametroLenguajeLink = new String();
    private List estatusCatalogoParametroLenguaje = new ArrayList();
    private List eliminadoCatalogoParametroLenguaje = new ArrayList();

    private String gridVisibleCatalogo = new String();
    private String gridIndividualModeCatalogo = new String();

    private String gridVisibleCatalogoParametro = new String();
    private String gridIndividualModeCatalogoParametro = new String();

    private String gridVisibleCatalogoParametroLenguaje = new String();
    private String gridIndividualModeCatalogoParametroLenguaje = new String();

    private String nombreActionMenu = new String();

    /*
     * Método que llena los elementos del formulario del JSP mediante Struts al cargar la página catalogo.jsp 
     * @return Regresa un código de tipo String, el método execute() es ejecutado y la respuesta es evaluada, finalmente se manda llamar al JSP correspondiente según la respuesta.
     */
    @Override
    public String execute() {
        setNombreActionMenu(catalogoBss.getNombreActionMenu());
        // <editor-fold defaultstate="collapsed" desc="Getters">
        // Obtener todos los registros de la tabla Organizacion.
        setOrganizacionCatalogo(catalogoBss.getOrganizacion());
        setOrganizacionCatalogoLink(getLink("Catalogo", "organizacion"));
        // Obtener todos los registros de la tabla Catalogo.
        setCatalogoPadreCatalogo(catalogoBss.findActive());
        setCatalogoPadreCatalogoLink(getLink("Catalogo", "catalogo"));
        // Obtener todos los registros de la tabla CatalogoParametro.
        setParametroDependienteCatalogo(new ArrayList());
        setParametroDependienteCatalogoLink(getLink("Catalogo", "catalogoParametro"));
        // Obtener los parámetros del catálogo Estatus
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEstatusCatalogo(catalogoBss.getParametros("key_estatus"));
        // Obtener los parámetros del catálogo Eliminado
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEliminadoCatalogo(catalogoBss.getParametros("key_eliminado"));
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Getters Detalle(s)">
        // Obtener todos los registros de la tabla Catalogo.
        setCatalogoCatalogoParametro(catalogoBss.findActive());
        setCatalogoCatalogoParametroLink(getLink("Catalogo", "catalogo"));
        // Obtener todos los registros de la tabla Organizacion.
        setOrganizacionCatalogoParametro(catalogoBss.getOrganizacion());
        setOrganizacionCatalogoParametroLink(getLink("Catalogo", "organizacion"));
        // Obtener los parámetros del catálogo Estatus
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEstatusCatalogoParametro(catalogoBss.getParametros("key_estatus"));
        // Obtener los parámetros del catálogo Eliminado
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEliminadoCatalogoParametro(catalogoBss.getParametros("key_eliminado"));
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Getters Detalle(s)">
        // Obtener todos los registros de la tabla Catalogo.
        setCatalogoParametroCatalogoParametroLenguaje(catalogoBss.findActive());
        setCatalogoParametroCatalogoParametroLenguajeLink(getLink("Catalogo", "catalogoParametro"));
        // Obtener todos los registros de la tabla Lenguaje.
        setLenguajeCatalogoParametroLenguaje(catalogoBss.getLenguaje());
        setLenguajeCatalogoParametroLenguajeLink(getLink("Catalogo", "lenguaje"));
        // Obtener todos los registros de la tabla Organizacion.
        setOrganizacionCatalogoParametroLenguaje(catalogoBss.getOrganizacion());
        setOrganizacionCatalogoParametroLenguajeLink(getLink("Catalogo", "organizacion"));
        // Obtener los parámetros del catálogo Estatus
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEstatusCatalogoParametroLenguaje(catalogoBss.getParametros("key_estatus"));
        // Obtener los parámetros del catálogo Eliminado
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEliminadoCatalogoParametroLenguaje(catalogoBss.getParametros("key_eliminado"));
        // </editor-fold>

        setGridVisibleCatalogo(catalogoBss.hasGrid());
        setGridIndividualModeCatalogo(catalogoBss.isIndividual());
        setGridVisibleCatalogoParametro(catalogoBss.hasGridCatalogoParametro());
        setGridIndividualModeCatalogoParametro(catalogoBss.isIndividualCatalogoParametro());
        setGridVisibleCatalogoParametroLenguaje(catalogoBss.hasGridCatalogoParametroLenguaje());
        setGridIndividualModeCatalogoParametroLenguaje(catalogoBss.isIndividualCatalogoParametroLenguaje());
        return SUCCESS;
    }

    /* Cada elemento que será llenado por Struts al cargar la página
     * deben tener métodos Getter y Setter. */
    /* Getter y Setter select */
    public List getOrganizacionCatalogo() {
        return organizacionCatalogo;
    }

    public void setOrganizacionCatalogo(List organizacionCatalogo) {
        this.organizacionCatalogo = organizacionCatalogo;
    }

    public String getOrganizacionCatalogoLink() {
        return organizacionCatalogoLink;
    }

    public void setOrganizacionCatalogoLink(String organizacionCatalogoLink) {
        this.organizacionCatalogoLink = organizacionCatalogoLink;
    }

    public List getCatalogoPadreCatalogo() {
        return catalogoPadreCatalogo;
    }

    public void setCatalogoPadreCatalogo(List catalogoPadreCatalogo) {
        this.catalogoPadreCatalogo = catalogoPadreCatalogo;
    }

    public String getCatalogoPadreCatalogoLink() {
        return catalogoPadreCatalogoLink;
    }

    public void setCatalogoPadreCatalogoLink(String catalogoPadreCatalogoLink) {
        this.catalogoPadreCatalogoLink = catalogoPadreCatalogoLink;
    }

    public List getParametroDependienteCatalogo() {
        return parametroDependienteCatalogo;
    }

    public void setParametroDependienteCatalogo(List parametroDependienteCatalogo) {
        this.parametroDependienteCatalogo = parametroDependienteCatalogo;
    }

    public String getParametroDependienteCatalogoLink() {
        return parametroDependienteCatalogoLink;
    }

    public void setParametroDependienteCatalogoLink(String parametroDependienteCatalogoLink) {
        this.parametroDependienteCatalogoLink = parametroDependienteCatalogoLink;
    }

    public List getEstatusCatalogo() {
        return estatusCatalogo;
    }

    public void setEstatusCatalogo(List estatusCatalogo) {
        this.estatusCatalogo = estatusCatalogo;
    }

    public List getEliminadoCatalogo() {
        return eliminadoCatalogo;
    }

    public void setEliminadoCatalogo(List eliminadoCatalogo) {
        this.eliminadoCatalogo = eliminadoCatalogo;
    }

    public List getCatalogoCatalogoParametro() {
        return catalogoCatalogoParametro;
    }

    public void setCatalogoCatalogoParametro(List catalogoCatalogoParametro) {
        this.catalogoCatalogoParametro = catalogoCatalogoParametro;
    }

    public String getCatalogoCatalogoParametroLink() {
        return catalogoCatalogoParametroLink;
    }

    public void setCatalogoCatalogoParametroLink(String catalogoCatalogoParametroLink) {
        this.catalogoCatalogoParametroLink = catalogoCatalogoParametroLink;
    }

    public List getOrganizacionCatalogoParametro() {
        return organizacionCatalogoParametro;
    }

    public void setOrganizacionCatalogoParametro(List organizacionCatalogoParametro) {
        this.organizacionCatalogoParametro = organizacionCatalogoParametro;
    }

    public String getOrganizacionCatalogoParametroLink() {
        return organizacionCatalogoParametroLink;
    }

    public void setOrganizacionCatalogoParametroLink(String organizacionCatalogoParametroLink) {
        this.organizacionCatalogoParametroLink = organizacionCatalogoParametroLink;
    }

    public List getEstatusCatalogoParametro() {
        return estatusCatalogoParametro;
    }

    public void setEstatusCatalogoParametro(List estatusCatalogoParametro) {
        this.estatusCatalogoParametro = estatusCatalogoParametro;
    }

    public List getEliminadoCatalogoParametro() {
        return eliminadoCatalogoParametro;
    }

    public void setEliminadoCatalogoParametro(List eliminadoCatalogoParametro) {
        this.eliminadoCatalogoParametro = eliminadoCatalogoParametro;
    }

    public List getCatalogoParametroCatalogoParametroLenguaje() {
        return catalogoParametroCatalogoParametroLenguaje;
    }

    public void setCatalogoParametroCatalogoParametroLenguaje(List catalogoParametroCatalogoParametroLenguaje) {
        this.catalogoParametroCatalogoParametroLenguaje = catalogoParametroCatalogoParametroLenguaje;
    }

    public String getCatalogoParametroCatalogoParametroLenguajeLink() {
        return catalogoParametroCatalogoParametroLenguajeLink;
    }

    public void setCatalogoParametroCatalogoParametroLenguajeLink(String catalogoParametroCatalogoParametroLenguajeLink) {
        this.catalogoParametroCatalogoParametroLenguajeLink = catalogoParametroCatalogoParametroLenguajeLink;
    }

    public List getLenguajeCatalogoParametroLenguaje() {
        return lenguajeCatalogoParametroLenguaje;
    }

    public void setLenguajeCatalogoParametroLenguaje(List lenguajeCatalogoParametroLenguaje) {
        this.lenguajeCatalogoParametroLenguaje = lenguajeCatalogoParametroLenguaje;
    }

    public String getLenguajeCatalogoParametroLenguajeLink() {
        return lenguajeCatalogoParametroLenguajeLink;
    }

    public void setLenguajeCatalogoParametroLenguajeLink(String lenguajeCatalogoParametroLenguajeLink) {
        this.lenguajeCatalogoParametroLenguajeLink = lenguajeCatalogoParametroLenguajeLink;
    }

    public List getOrganizacionCatalogoParametroLenguaje() {
        return organizacionCatalogoParametroLenguaje;
    }

    public void setOrganizacionCatalogoParametroLenguaje(List organizacionCatalogoParametroLenguaje) {
        this.organizacionCatalogoParametroLenguaje = organizacionCatalogoParametroLenguaje;
    }

    public String getOrganizacionCatalogoParametroLenguajeLink() {
        return organizacionCatalogoParametroLenguajeLink;
    }

    public void setOrganizacionCatalogoParametroLenguajeLink(String organizacionCatalogoParametroLenguajeLink) {
        this.organizacionCatalogoParametroLenguajeLink = organizacionCatalogoParametroLenguajeLink;
    }

    public List getEstatusCatalogoParametroLenguaje() {
        return estatusCatalogoParametroLenguaje;
    }

    public void setEstatusCatalogoParametroLenguaje(List estatusCatalogoParametroLenguaje) {
        this.estatusCatalogoParametroLenguaje = estatusCatalogoParametroLenguaje;
    }

    public List getEliminadoCatalogoParametroLenguaje() {
        return eliminadoCatalogoParametroLenguaje;
    }

    public void setEliminadoCatalogoParametroLenguaje(List eliminadoCatalogoParametroLenguaje) {
        this.eliminadoCatalogoParametroLenguaje = eliminadoCatalogoParametroLenguaje;
    }

    public String getGridVisibleCatalogo() {
        return gridVisibleCatalogo;
    }

    public void setGridVisibleCatalogo(String gridVisibleCatalogo) {
        this.gridVisibleCatalogo = gridVisibleCatalogo;
    }

    public String getGridIndividualModeCatalogo() {
        return gridIndividualModeCatalogo;
    }

    public void setGridIndividualModeCatalogo(String gridIndividualModeCatalogo) {
        this.gridIndividualModeCatalogo = gridIndividualModeCatalogo;
    }

    public String getGridVisibleCatalogoParametro() {
        return gridVisibleCatalogoParametro;
    }

    public void setGridVisibleCatalogoParametro(String gridVisibleCatalogoParametro) {
        this.gridVisibleCatalogoParametro = gridVisibleCatalogoParametro;
    }

    public String getGridIndividualModeCatalogoParametro() {
        return gridIndividualModeCatalogoParametro;
    }

    public void setGridIndividualModeCatalogoParametro(String gridIndividualModeCatalogoParametro) {
        this.gridIndividualModeCatalogoParametro = gridIndividualModeCatalogoParametro;
    }

    public String getGridVisibleCatalogoParametroLenguaje() {
        return gridVisibleCatalogoParametroLenguaje;
    }

    public void setGridVisibleCatalogoParametroLenguaje(String gridVisibleCatalogoParametroLenguaje) {
        this.gridVisibleCatalogoParametroLenguaje = gridVisibleCatalogoParametroLenguaje;
    }

    public String getGridIndividualModeCatalogoParametroLenguaje() {
        return gridIndividualModeCatalogoParametroLenguaje;
    }

    public void setGridIndividualModeCatalogoParametroLenguaje(String gridIndividualModeCatalogoParametroLenguaje) {
        this.gridIndividualModeCatalogoParametroLenguaje = gridIndividualModeCatalogoParametroLenguaje;
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
    public void setCatalogoBss(CatalogoBss catalogoBss) {
        this.catalogoBss = catalogoBss;
    }

//    public void setCatalogoParametroBss(CatalogoParametroBss catalogoParametroBss) {
//        this.catalogoParametroBss = catalogoParametroBss;
//    }
//
//    public void setCatalogoParametroLenguajeBss(CatalogoParametroLenguajeBss catalogoParametroLenguajeBss) {
//        this.catalogoParametroLenguajeBss = catalogoParametroLenguajeBss;
//    }

}
