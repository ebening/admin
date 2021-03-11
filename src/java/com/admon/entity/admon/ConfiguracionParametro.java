package com.admon.entity.admon;

import java.io.Serializable;
import java.sql.Timestamp;

public class ConfiguracionParametro implements Serializable {

    private Integer configuracionParametroId;
    private Integer configuracionId;
    private String nombre;
    private String valor;
    private String descripcion;
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
    private String configuracion;
    private String estatus;
    private String eliminado;
    private String usuarioModificacion;

    /*
*Constructor de la clase ConfiguracionParametro.java
     */
    public ConfiguracionParametro() {
    }

    public Integer getConfiguracionParametroId() {
        return configuracionParametroId;
    }

    public void setConfiguracionParametroId(Integer configuracionParametroId) {
        this.configuracionParametroId = configuracionParametroId;
    }

    public Integer getConfiguracionId() {
        return configuracionId;
    }

    public void setConfiguracionId(Integer configuracionId) {
        this.configuracionId = configuracionId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public String getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(String configuracion) {
        this.configuracion = configuracion;
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
