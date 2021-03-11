package com.admon.entity.admon;

import java.io.Serializable;
import java.sql.Timestamp;

public class CatalogoParametroLenguaje implements Serializable {

    private Integer catalogoParametroLenguajeId;
    private Integer catalogoParametroId;
    private Integer lenguajeId;
    private Integer organizacionId;
    private String etiqueta;

    private Integer estatusId;
    private Integer eliminadoId;
    private Timestamp creacionFecha;
    private Integer creacionUsuario;
    private Timestamp modificacionFecha;
    private Integer modificacionUsuario;

    // Campos auxiliares para busquedas por rango de fechas
    private Timestamp creacionFechaInicial;
    private Timestamp creacionFechaFinal;
    // Campos auxiliares para busquedas por rango de fechas
    private Timestamp modificacionFechaInicial;
    private Timestamp modificacionFechaFinal;

    //Campos auxiliares
    private String catalogoParametro;
    private String lenguaje;
    private String organizacion;
    private String estatus;
    private String eliminado;
    private String usuarioModificacion;

    public CatalogoParametroLenguaje() {
    }

    public Integer getCatalogoParametroLenguajeId() {
        return catalogoParametroLenguajeId;
    }

    public void setCatalogoParametroLenguajeId(Integer catalogoParametroLenguajeId) {
        this.catalogoParametroLenguajeId = catalogoParametroLenguajeId;
    }

    public Integer getCatalogoParametroId() {
        return catalogoParametroId;
    }

    public void setCatalogoParametroId(Integer catalogoParametroId) {
        this.catalogoParametroId = catalogoParametroId;
    }

    public Integer getLenguajeId() {
        return lenguajeId;
    }

    public void setLenguajeId(Integer lenguajeId) {
        this.lenguajeId = lenguajeId;
    }

    public Integer getOrganizacionId() {
        return organizacionId;
    }

    public void setOrganizacionId(Integer organizacionId) {
        this.organizacionId = organizacionId;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
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

    public String getCatalogoParametro() {
        return catalogoParametro;
    }

    public void setCatalogoParametro(String catalogoParametro) {
        this.catalogoParametro = catalogoParametro;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
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
