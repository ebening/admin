package com.admon.entity.admon;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Pagina implements Serializable {

    private Integer paginaId;
    private String nombre;
    private Integer anidar;
    private String url;
    private String icono;
    private Integer orden;
    private Integer noIncluirEnMenu;
    private Integer privado;
    private Integer sinGrid;
    private Integer individual;
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
    private String estatus;
    private String eliminado;
    private String usuarioModificacion;
    private String anidarNombre;

    private List<Pagina> submenu = new ArrayList();
    private boolean isSubmenuPopulated = false;

    /*
*Constructor de la clase Pagina.java
     */
    public Pagina() {
    }

    public Integer getPaginaId() {
        return paginaId;
    }

    public void setPaginaId(Integer paginaId) {
        this.paginaId = paginaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAnidar() {
        return anidar;
    }

    public void setAnidar(Integer anidar) {
        this.anidar = anidar;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Integer getNoIncluirEnMenu() {
        return noIncluirEnMenu;
    }

    public void setNoIncluirEnMenu(Integer noIncluirEnMenu) {
        this.noIncluirEnMenu = noIncluirEnMenu;
    }

    public Integer getPrivado() {
        return privado;
    }

    public void setPrivado(Integer privado) {
        this.privado = privado;
    }

    public Integer getSinGrid() {
        return sinGrid;
    }

    public void setSinGrid(Integer sinGrid) {
        this.sinGrid = sinGrid;
    }

    public Integer getIndividual() {
        return individual;
    }

    public void setIndividual(Integer individual) {
        this.individual = individual;
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

    public String getAnidarNombre() {
        return anidarNombre;
    }

    public void setAnidarNombre(String anidarNombre) {
        this.anidarNombre = anidarNombre;
    }

    public List<Pagina> getSubmenu() {
        return submenu;
    }

    public void setSubmenu(List<Pagina> submenu) {
        this.submenu = submenu;
    }

    public boolean isSubmenuPopulated() {
        return isSubmenuPopulated;
    }

    public void setIsSubmenuPopulated(boolean isSubmenuPopulated) {
        this.isSubmenuPopulated = isSubmenuPopulated;
    }
}
