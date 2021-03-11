package com.admon.entity.admon;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class OrganizacionGenerar implements Serializable {

    private Integer organizacionGenerarId;
    private Integer organizacionId;
    private String nombre;
    private Integer conceptoId;
    private Integer tipo;
    private Integer estatusId;
    private Integer eliminadoId;
    private Timestamp creacionFecha;
    private Integer creacionUsuario;
    private Timestamp modificacionFecha;
    private Integer modificacionUsuario;
    private String estatus;
    private String eliminado;
    private String aplicacion;
    private Integer admin;
    private Integer taquilla;
    private Integer web;

    /*
*Constructor de la clase OrganizacionGenerar.java
     */
    public OrganizacionGenerar() {
    }

    public Integer getOrganizacionGenerarId() {
        return organizacionGenerarId;
    }

    public void setOrganizacionGenerarId(Integer organizacionGenerarId) {
        this.organizacionGenerarId = organizacionGenerarId;
    }

    public Integer getOrganizacionId() {
        return organizacionId;
    }

    public void setOrganizacionId(Integer organizacionId) {
        this.organizacionId = organizacionId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(String aplicacion) {
        this.aplicacion = aplicacion;
    }

    public Integer getConceptoId() {
        return conceptoId;
    }

    public void setConceptoId(Integer conceptoId) {
        this.conceptoId = conceptoId;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getEstatusId() {
        return estatusId;
    }

    public void setEstatusId(Integer estatusId) {
        this.estatusId = estatusId;
    }

    public Integer getEliminadoId() {
        return eliminadoId;
    }

    public void setEliminadoId(Integer eliminadoId) {
        this.eliminadoId = eliminadoId;
    }

    public Timestamp getCreacionFecha() {
        return creacionFecha;
    }

    public void setCreacionFecha(Timestamp creacionFecha) {
        this.creacionFecha = creacionFecha;
    }

    public Integer getCreacionUsuario() {
        return creacionUsuario;
    }

    public void setCreacionUsuario(Integer creacionUsuario) {
        this.creacionUsuario = creacionUsuario;
    }

    public Timestamp getModificacionFecha() {
        return modificacionFecha;
    }

    public void setModificacionFecha(Timestamp modificacionFecha) {
        this.modificacionFecha = modificacionFecha;
    }

    public Integer getModificacionUsuario() {
        return modificacionUsuario;
    }

    public void setModificacionUsuario(Integer modificacionUsuario) {
        this.modificacionUsuario = modificacionUsuario;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getEliminado() {
        return eliminado;
    }

    public void setEliminado(String eliminado) {
        this.eliminado = eliminado;
    }

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }

    public Integer getTaquilla() {
        return taquilla;
    }

    public void setTaquilla(Integer taquilla) {
        this.taquilla = taquilla;
    }

    public Integer getWeb() {
        return web;
    }

    public void setWeb(Integer web) {
        this.web = web;
    }

}
