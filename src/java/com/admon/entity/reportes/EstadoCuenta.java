package com.admon.entity.reportes;

import java.io.Serializable;
import java.sql.Date;

public class EstadoCuenta implements Serializable {

    private Integer organizacionId;
    private Integer eventoId;
    
    //Campos auxiliares para reporte: Empresa
    private String nombreRazonSocial;
    private String nombreComercial;
    private String RFC;
    private String domicilio;
    private Date fechaLimitePago;

    //Campos auxiliares para reporte: Generales
    private String nombreOrganizacion;
    private String nombreEvento;
    private Integer noBoletos;
    private Integer noCortesias;
    private Integer noCancelaciones;
    private Integer noBoletoDuro;
    private Double total;
    private Double monto;
    private Double descuento;
    private Double cargoXServicio;
    private Double comisionTaasgo;

    //Campos auxiliares para reporte: Parametros
    private Double IVA;
    private Double PorcentajeXCortesia;
    private Double PorcentajeXCancelacion;
    private Double PorcentajeXBoletoDuro;
    /*
     *Constructor de la clase EstadoCuenta.java
     */

    public EstadoCuenta() {
    }

    public String getNombreRazonSocial() {
        return nombreRazonSocial;
    }

    public void setNombreRazonSocial(String nombreRazonSocial) {
        this.nombreRazonSocial = nombreRazonSocial;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Date getFechaLimitePago() {
        return fechaLimitePago;
    }

    public void setFechaLimitePago(Date fechaLimitePago) {
        this.fechaLimitePago = fechaLimitePago;
    }

    public String getNombreOrganizacion() {
        return nombreOrganizacion;
    }

    public void setNombreOrganizacion(String nombreOrganizacion) {
        this.nombreOrganizacion = nombreOrganizacion;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public Integer getNoBoletos() {
        return noBoletos;
    }

    public void setNoBoletos(Integer noBoletos) {
        this.noBoletos = noBoletos;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Double getCargoXServicio() {
        return cargoXServicio;
    }

    public void setCargoXServicio(Double cargoXServicio) {
        this.cargoXServicio = cargoXServicio;
    }

    public Double getComisionTaasgo() {
        return comisionTaasgo;
    }

    public void setComisionTaasgo(Double comisionTaasgo) {
        this.comisionTaasgo = comisionTaasgo;
    }

    public Integer getNoCortesias() {
        return noCortesias;
    }

    public void setNoCortesias(Integer noCortesias) {
        this.noCortesias = noCortesias;
    }

    public Integer getNoCancelaciones() {
        return noCancelaciones;
    }

    public void setNoCancelaciones(Integer noCancelaciones) {
        this.noCancelaciones = noCancelaciones;
    }

    public Integer getNoBoletoDuro() {
        return noBoletoDuro;
    }

    public void setNoBoletoDuro(Integer noBoletoDuro) {
        this.noBoletoDuro = noBoletoDuro;
    }

    public Double getIVA() {
        return IVA;
    }

    public void setIVA(Double IVA) {
        this.IVA = IVA;
    }

    public Double getPorcentajeXCortesia() {
        return PorcentajeXCortesia;
    }

    public void setPorcentajeXCortesia(Double PorcentajeXCortesia) {
        this.PorcentajeXCortesia = PorcentajeXCortesia;
    }

    public Double getPorcentajeXCancelacion() {
        return PorcentajeXCancelacion;
    }

    public void setPorcentajeXCancelacion(Double PorcentajeXCancelacion) {
        this.PorcentajeXCancelacion = PorcentajeXCancelacion;
    }

    public Double getPorcentajeXBoletoDuro() {
        return PorcentajeXBoletoDuro;
    }

    public void setPorcentajeXBoletoDuro(Double PorcentajeXBoletoDuro) {
        this.PorcentajeXBoletoDuro = PorcentajeXBoletoDuro;
    }

    public Integer getOrganizacionId() {
        return organizacionId;
    }

    public void setOrganizacionId(Integer organizacionId) {
        this.organizacionId = organizacionId;
    }

    public Integer getEventoId() {
        return eventoId;
    }

    public void setEventoId(Integer eventoId) {
        this.eventoId = eventoId;
    }

}
