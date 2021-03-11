package com.admon.model.reportes;

import com.admon.model.BaseModel;
import java.util.ArrayList;
import java.util.List;
import com.admon.bss.reportes.EstadoCuentaBss;

public class EstadoCuentaAction extends BaseModel {

    private EstadoCuentaBss estadoCuentaBss;

    private List organizacionEstadoCuenta = new ArrayList();
    private String organizacionEstadoCuentaLink = new String();
    private List eventoEstadoCuenta = new ArrayList();
    private String eventoEstadoCuentaLink = new String();
    private String gridVisibleEstadoCuenta = new String();
    private String gridIndividualModeEstadoCuenta = new String();
    private String nombreActionMenu = new String();

    public EstadoCuentaAction() {
    }

    @Override
    public String execute() {
        setNombreActionMenu(estadoCuentaBss.getNombreActionMenu());
        setOrganizacionEstadoCuenta(estadoCuentaBss.getOrganizacion());
        setOrganizacionEstadoCuentaLink(getLink("EstadoCuenta", "organizacion"));
        setEventoEstadoCuenta(new ArrayList());
        setOrganizacionEstadoCuentaLink(getLink("EstadoCuenta", "evento"));
        setGridVisibleEstadoCuenta(estadoCuentaBss.hasGrid());
        setGridIndividualModeEstadoCuenta(estadoCuentaBss.isIndividual());
        return SUCCESS;
    }

    public void setEstadoCuentaBss(EstadoCuentaBss estadoCuentaBss) {
        this.estadoCuentaBss = estadoCuentaBss;
    }

    public List getOrganizacionEstadoCuenta() {
        return organizacionEstadoCuenta;
    }

    public void setOrganizacionEstadoCuenta(List organizacionEstadoCuenta) {
        this.organizacionEstadoCuenta = organizacionEstadoCuenta;
    }

    public String getOrganizacionEstadoCuentaLink() {
        return organizacionEstadoCuentaLink;
    }

    public void setOrganizacionEstadoCuentaLink(String organizacionEstadoCuentaLink) {
        this.organizacionEstadoCuentaLink = organizacionEstadoCuentaLink;
    }

    public String getGridVisibleEstadoCuenta() {
        return gridVisibleEstadoCuenta;
    }

    public void setGridVisibleEstadoCuenta(String gridVisibleEstadoCuenta) {
        this.gridVisibleEstadoCuenta = gridVisibleEstadoCuenta;
    }

    public String getGridIndividualModeEstadoCuenta() {
        return gridIndividualModeEstadoCuenta;
    }

    public void setGridIndividualModeEstadoCuenta(String gridIndividualModeEstadoCuenta) {
        this.gridIndividualModeEstadoCuenta = gridIndividualModeEstadoCuenta;
    }

    public String getNombreActionMenu() {
        return nombreActionMenu;
    }

    public void setNombreActionMenu(String nombreActionMenu) {
        this.nombreActionMenu = nombreActionMenu;
    }

    public List getEventoEstadoCuenta() {
        return eventoEstadoCuenta;
    }

    public void setEventoEstadoCuenta(List eventoEstadoCuenta) {
        this.eventoEstadoCuenta = eventoEstadoCuenta;
    }

    public String getEventoEstadoCuentaLink() {
        return eventoEstadoCuentaLink;
    }

    public void setEventoEstadoCuentaLink(String eventoEstadoCuentaLink) {
        this.eventoEstadoCuentaLink = eventoEstadoCuentaLink;
    }

}
