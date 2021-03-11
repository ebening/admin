package com.admon.entity.admon;

import java.io.Serializable;
import java.sql.Timestamp;

public class NombreContra implements Serializable {

    private Integer nombreContraId;
    private Integer clasificacionContraId;
    private Integer subclasificacionContraId;
    private String nombre;
    private Integer tipoValorId;
    private Integer checkboxContra;
    private Integer orden;
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
    private String clasificacionContra;
    private String subclasificacionContra;
    private String tipoValor;
    private String estatus;
    private String eliminado;
    private String usuarioModificacion;

    /*
*Constructor de la clase NombreContra.java
     */
    public NombreContra() {
    }

    public Integer getNombreContraId() {
        return nombreContraId;
    }

    public void setNombreContraId(Integer nombreContraId) {
        this.nombreContraId = nombreContraId;
    }

    public Integer getClasificacionContraId() {
        return clasificacionContraId;
    }

    public void setClasificacionContraId(Integer clasificacionContraId) {
        this.clasificacionContraId = clasificacionContraId;
    }

    public Integer getSubclasificacionContraId() {
        return subclasificacionContraId;
    }

    public void setSubclasificacionContraId(Integer subclasificacionContraId) {
        this.subclasificacionContraId = subclasificacionContraId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getTipoValorId() {
        return tipoValorId;
    }

    public void setTipoValorId(Integer tipoValorId) {
        this.tipoValorId = tipoValorId;
    }

    public Integer getCheckboxContra() {
        return checkboxContra;
    }

    public void setCheckboxContra(Integer checkboxContra) {
        this.checkboxContra = checkboxContra;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
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

    public String getClasificacionContra() {
        return clasificacionContra;
    }

    public void setClasificacionContra(String clasificacionContra) {
        this.clasificacionContra = clasificacionContra;
    }

    public String getSubclasificacionContra() {
        return subclasificacionContra;
    }

    public void setSubclasificacionContra(String subclasificacionContra) {
        this.subclasificacionContra = subclasificacionContra;
    }

    public String getTipoValor() {
        return tipoValor;
    }

    public void setTipoValor(String tipoValor) {
        this.tipoValor = tipoValor;
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
