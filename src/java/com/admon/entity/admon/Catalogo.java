package com.admon.entity.admon;

import java.io.Serializable;
import java.sql.Timestamp;

public class Catalogo implements Serializable {

    private Integer catalogoId;
    private Integer organizacionId;
    private String nombre;
    private String clave;
    private Integer seleccionaColor;
    private String descripcion;

    private Integer catalogoPadreId;
    private Boolean esDependiente;
    private Integer parametroDependienteId;
    private Boolean generico;

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
    private String organizacionNombre;
    private String catalogoPadre;
    private String parametroDependiente;
    private String estatus;
    private String eliminado;
    private String usuarioModificacion;

    public Catalogo() {
    }

    public Integer getCatalogoId() {
        return catalogoId;
    }

    public void setCatalogoId(Integer catalogoId) {
        this.catalogoId = catalogoId;
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

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Integer getSeleccionaColor() {
        return seleccionaColor;
    }

    public void setSeleccionaColor(Integer seleccionaColor) {
        this.seleccionaColor = seleccionaColor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCatalogoPadreId() {
        return catalogoPadreId;
    }

    public void setCatalogoPadreId(Integer catalogoPadreId) {
        this.catalogoPadreId = catalogoPadreId;
    }

    public Boolean getEsDependiente() {
        return esDependiente;
    }

    public void setEsDependiente(Boolean esDependiente) {
        this.esDependiente = esDependiente;
    }

    public Integer getParametroDependienteId() {
        return parametroDependienteId;
    }

    public void setParametroDependienteId(Integer parametroDependienteId) {
        this.parametroDependienteId = parametroDependienteId;
    }

    public Boolean getGenerico() {
        return generico;
    }

    public void setGenerico(Boolean generico) {
        this.generico = generico;
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

    public String getOrganizacionNombre() {
        return organizacionNombre;
    }

    public void setOrganizacionNombre(String organizacionNombre) {
        this.organizacionNombre = organizacionNombre;
    }

    public String getCatalogoPadre() {
        return catalogoPadre;
    }

    public void setCatalogoPadre(String catalogoPadre) {
        this.catalogoPadre = catalogoPadre;
    }

    public String getParametroDependiente() {
        return parametroDependiente;
    }

    public void setParametroDependiente(String parametroDependiente) {
        this.parametroDependiente = parametroDependiente;
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
