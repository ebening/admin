package com.admon.model.admon;

import com.admon.bss.admon.ConfiguracionParametroBss;
import com.admon.model.BaseModel;
import java.util.ArrayList;
import java.util.List;

public class ConfiguracionParametroAction extends BaseModel {

    /* Inyeccion de dependencias con Spring. Estas dependencias se configuran en 
 * el applicationContext.xml, y ademas necesitan un metodo setter por cada 
 * variable el cual es anexado al final de la clase. */
    private ConfiguracionParametroBss configuracionParametroBss;

    // Elementos de struts2 para el JSP
    private List configuracionConfiguracionParametro = new ArrayList();
    private String configuracionConfiguracionParametroLink = new String();
    private List estatusConfiguracionParametro = new ArrayList();
    private List eliminadoConfiguracionParametro = new ArrayList();
    String gridVisibleConfiguracionParametro = new String();
    String gridIndividualModeConfiguracionParametro = new String();
    String nombreActionMenu = new String();

    public ConfiguracionParametroAction() {
    }

    /*
 * Método que llena los elementos del formulario del JSP mediante Struts al cargar la página configuracionParametro.jsp 
 * @return Regresa un código de tipo String, el método execute() es ejecutado y la respuesta es evaluada, finalmente se manda llamar al JSP correspondiente según la respuesta.
     */
    @Override
    public String execute() {
        setNombreActionMenu(configuracionParametroBss.getNombreActionMenu());
        // <editor-fold defaultstate="collapsed" desc="Getters">
        // Obtener todos los registros de la tabla Configuracion.
        setConfiguracionConfiguracionParametro(configuracionParametroBss.getConfiguracion());
        setConfiguracionConfiguracionParametroLink(getLink("ConfiguracionParametro", "configuracion"));
        // Obtener los parámetros del catálogo Estatus
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEstatusConfiguracionParametro(configuracionParametroBss.getParametros("key_estatus"));
        // Obtener los parámetros del catálogo Eliminado
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEliminadoConfiguracionParametro(configuracionParametroBss.getParametros("key_eliminado"));
        // </editor-fold>

        setGridVisibleConfiguracionParametro(configuracionParametroBss.hasGrid());
        setGridIndividualModeConfiguracionParametro(configuracionParametroBss.isIndividual());
        return SUCCESS;
    }

    /* Cada elemento que será llenado por Struts al cargar la página
 * deben tener métodos Getter y Setter. */
 /* Getter y Setter select */
    public List getConfiguracionConfiguracionParametro() {
        return configuracionConfiguracionParametro;
    }

    public void setConfiguracionConfiguracionParametro(List configuracion) {
        this.configuracionConfiguracionParametro = configuracion;
    }

    /* Getter y Setter popup */
    public String getConfiguracionConfiguracionParametroLink() {
        return configuracionConfiguracionParametroLink;
    }

    public void setConfiguracionConfiguracionParametroLink(String configuracionConfiguracionParametroLink) {
        this.configuracionConfiguracionParametroLink = configuracionConfiguracionParametroLink;
    }

    /* Getter y Setter select */
    public List getEstatusConfiguracionParametro() {
        return estatusConfiguracionParametro;
    }

    public void setEstatusConfiguracionParametro(List estatus) {
        this.estatusConfiguracionParametro = estatus;
    }

    /* Getter y Setter select */
    public List getEliminadoConfiguracionParametro() {
        return eliminadoConfiguracionParametro;
    }

    public void setEliminadoConfiguracionParametro(List eliminado) {
        this.eliminadoConfiguracionParametro = eliminado;
    }

    public String getGridVisibleConfiguracionParametro() {
        return gridVisibleConfiguracionParametro;
    }

    public void setGridVisibleConfiguracionParametro(String gridVisibleConfiguracionParametro) {
        this.gridVisibleConfiguracionParametro = gridVisibleConfiguracionParametro;
    }

    public String getGridIndividualModeConfiguracionParametro() {
        return gridIndividualModeConfiguracionParametro;
    }

    public void setGridIndividualModeConfiguracionParametro(String gridIndividualModeConfiguracionParametro) {
        this.gridIndividualModeConfiguracionParametro = gridIndividualModeConfiguracionParametro;
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
    public void setConfiguracionParametroBss(ConfiguracionParametroBss configuracionParametroBss) {
        this.configuracionParametroBss = configuracionParametroBss;
    }

}
