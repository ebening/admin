package com.admon.entity.admon;

import java.io.Serializable;
import java.sql.Timestamp;

public class Comision implements Serializable {

    private Integer comisionId;
    private Integer organizacionId;

    private Integer clasificacionContraId;
    private String nombreClasificacion;
    private Integer subclasificacionContraId;
    private String nombreSubclasificacion;
    private Integer nombreContraId;
    private String tipoValor;
    private Double valor;
    private Integer checkbox;
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
    private String estatus;
    private String eliminado;
    private String usuarioModificacion;

    /*
*Constructor de la clase Comision.java
     */
    public Comision() {
    }

    public Integer getComisionId() {
        return comisionId;
    }

    public void setComisionId(Integer comisionId) {
        this.comisionId = comisionId;
    }

    public Integer getClasificacionContraId() {
        return clasificacionContraId;
    }

    public void setClasificacionContraId(Integer clasificacionContraId) {
        this.clasificacionContraId = clasificacionContraId;
    }

    public String getNombreClasificacion() {
        return nombreClasificacion;
    }

    public void setNombreClasificacion(String nombreClasificacion) {
        this.nombreClasificacion = nombreClasificacion;
    }

    public Integer getSubclasificacionContraId() {
        return subclasificacionContraId;
    }

    public void setSubclasificacionContraId(Integer subclasificacionContraId) {
        this.subclasificacionContraId = subclasificacionContraId;
    }

    public String getNombreSubclasificacion() {
        return nombreSubclasificacion;
    }

    public void setNombreSubclasificacion(String nombreSubclasificacion) {
        this.nombreSubclasificacion = nombreSubclasificacion;
    }

    public Integer getNombreContraId() {
        return nombreContraId;
    }

    public void setNombreContraId(Integer nombreContraId) {
        this.nombreContraId = nombreContraId;
    }

    public String getTipoValor() {
        return tipoValor;
    }

    public void setTipoValor(String tipoValor) {
        this.tipoValor = tipoValor;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(Integer checkbox) {
        this.checkbox = checkbox;
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

    public Integer getOrganizacionId() {
        return organizacionId;
    }

    public void setOrganizacionId(Integer organizacionId) {
        this.organizacionId = organizacionId;
    }

}
