package com.admon.pkg.entity;

import java.io.Serializable;

public class EstadoCuentaDetalleRS  implements Serializable {

    private Integer identificador;
    private Integer organizacionId;
    private String nombreEvento;
    private String nombrePresentacion;
    private String canalVenta;
    private String mesFactura;
    private Integer noBoletos;
    private Double balance;
    private Double total;
    private Double monto;
    private Double descuento;
    private Double cargoXServicio;
    private Double comisionTaasgo;

    /*
     *Constructor de la clase EstadoCuentaDetalleRS.java
     */
    public EstadoCuentaDetalleRS() {
    }

    public Integer getOrganizacionId() {
        return organizacionId;
    }

    public void setOrganizacionId(Integer organizacionId) {
        this.organizacionId = organizacionId;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getNombrePresentacion() {
        return nombrePresentacion;
    }

    public void setNombrePresentacion(String nombrePresentacion) {
        this.nombrePresentacion = nombrePresentacion;
    }

    public String getCanalVenta() {
        return canalVenta;
    }

    public void setCanalVenta(String canalVenta) {
        this.canalVenta = canalVenta;
    }

    public String getMesFactura() {
        return mesFactura;
    }

    public void setMesFactura(String mesFactura) {
        this.mesFactura = mesFactura;
    }

    public Integer getNoBoletos() {
        return noBoletos;
    }

    public void setNoBoletos(Integer noBoletos) {
        this.noBoletos = noBoletos;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
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

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

}
