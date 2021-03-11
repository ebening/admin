package com.admon.entity.admon;

import java.io.Serializable;
import java.sql.Timestamp;

public class OrganizacionCredencial implements Serializable {

    private Integer organizacionCredencialId;
    private Integer organizacionId;
    private String nombre;
    private String domain;
    private Integer aplicacionId;
    private String idapp;
    private String idrecapcha;
    private String idanalytics;
    private Integer estatusId;
    private Integer eliminadoId;
    private Timestamp creacionFecha;

    // Campos auxiliares para busquedas por rango de fechas
    private Timestamp creacionFechaInicial;
    private Timestamp creacionFechaFinal;

    private Integer creacionUsuario;
    private Timestamp modificacionFecha;

    // Campos auxiliares para busquedas por rango de fechas
    private Timestamp modificacionFechaInicial;
    private Timestamp modificacionFechaFinal;

    private Integer modificacionUsuario;
    private String organizacion;
    private String estatus;
    private String eliminado;
    private String aplicacion;
    private String usuarioModificacion;

    /*
*Constructor de la clase OrganizacionCredencial.java
     */
    public OrganizacionCredencial() {
    }

    public Integer getOrganizacionCredencialId() {
        return organizacionCredencialId;
    }

    public void setOrganizacionCredencialId(Integer organizacionCredencialId) {
        this.organizacionCredencialId = organizacionCredencialId;
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

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(String aplicacion) {
        this.aplicacion = aplicacion;
    }

    public Integer getAplicacionId() {
        return aplicacionId;
    }

    public void setAplicacionId(Integer aplicacionId) {
        this.aplicacionId = aplicacionId;
    }

    public String getIdapp() {
        return idapp;
    }

    public void setIdapp(String idapp) {
        this.idapp = idapp;
    }

    public String getIdrecapcha() {
        return idrecapcha;
    }

    public void setIdrecapcha(String idrecapcha) {
        this.idrecapcha = idrecapcha;
    }

    public String getIdanalytics() {
        return idanalytics;
    }

    public void setIdanalytics(String idanalytics) {
        this.idanalytics = idanalytics;
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

    public Timestamp getCreacionFechaInicial() {
        return creacionFechaInicial;
    }

    public void setCreacionFechaInicial(Timestamp creacionFechaInicial) {
        this.creacionFechaInicial = creacionFechaInicial;
    }

    public Timestamp getCreacionFechaFinal() {
        return creacionFechaFinal;
    }

    public void setCreacionFechaFinal(Timestamp creacionFechaFinal) {
        this.creacionFechaFinal = creacionFechaFinal;
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

    public Timestamp getModificacionFechaInicial() {
        return modificacionFechaInicial;
    }

    public void setModificacionFechaInicial(Timestamp modificacionFechaInicial) {
        this.modificacionFechaInicial = modificacionFechaInicial;
    }

    public Timestamp getModificacionFechaFinal() {
        return modificacionFechaFinal;
    }

    public void setModificacionFechaFinal(Timestamp modificacionFechaFinal) {
        this.modificacionFechaFinal = modificacionFechaFinal;
    }

    public Integer getModificacionUsuario() {
        return modificacionUsuario;
    }

    public void setModificacionUsuario(Integer modificacionUsuario) {
        this.modificacionUsuario = modificacionUsuario;
    }

    public String getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
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

    public String getUsuarioModificacion() {
        return usuarioModificacion;
    }

    public void setUsuarioModificacion(String usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

}
