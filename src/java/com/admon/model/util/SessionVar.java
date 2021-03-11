package com.admon.model.util;

import com.admon.entity.admon.Pagina;
import java.util.List;
import java.util.Map;

public class SessionVar {

    public Integer usuarioId;
    public String usuario;
    public String nombre;
    public String apellidoPaterno;
    public String apellidoMaterno;
    public String correo;
    public String url;
    public List<Pagina> pagina;
    public Pagina request;
    public Map<String, String> configuraciones;
    public Map<String, List> reportData;
    private String htmlMenu;
    private String perfil;

    public SessionVar() {
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public String getCorreo() {
        return correo;
    }

    public String getUrl() {
        return url;
    }

    public List<Pagina> getPagina() {
        return pagina;
    }

    public Map<String, String> getConfiguraciones() {
        return configuraciones;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPagina(List<Pagina> pagina) {
        this.pagina = pagina;
    }

    public void setConfiguraciones(Map<String, String> configuraciones) {
        this.configuraciones = configuraciones;
    }

    public Pagina getRequest() {
        return request;
    }

    public void setRequest(Pagina request) {
        this.request = request;
    }

    public Map<String, List> getReportData() {
        return reportData;
    }

    public void setReportData(Map<String, List> reportData) {
        this.reportData = reportData;
    }

    public String getHtmlMenu() {
        return htmlMenu;
    }

    public void setHtmlMenu(String htmlMenu) {
        this.htmlMenu = htmlMenu;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
}
