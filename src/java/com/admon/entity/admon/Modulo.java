package com.admon.entity.admon;

import java.io.Serializable;
import java.sql.Timestamp;

public class Modulo implements Serializable {

    private static final long serialVersionUID = 1L;
	
        private Long moduloId;
	private Long aplicacionId;
	private Long organizacionId;
	private String url;
	private Integer orden;
	private String descripcion;
	private String nombreAccion;
        private String nombreObjeto;
        private String htmlId;
	private Timestamp creacionFecha;
	private Integer creacionUsuario;
	private Timestamp modificacionFecha;
	private Integer modificacionUsuario;
	private Integer estatusId;
	private Integer eliminadoId;
	
	private String nombreES;
	private String nombreEN;
	private String nombre;
	private String aplicacion;
	private String modulo;
	private String modificacionUsuarioStr;

	
	public Long getModuloId() {
		return moduloId;
	}
	public void setModuloId(Long moduloId) {
		this.moduloId = moduloId;
	}
	public Long getAplicacionId() {
		return aplicacionId;
	}
	public void setAplicacionId(Long aplicacionId) {
		this.aplicacionId = aplicacionId;
	}
	public Long getOrganizacionId() {
		return organizacionId;
	}
	public void setOrganizacionId(Long organizacionId) {
		this.organizacionId = organizacionId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getOrden() {
		return orden;
	}
	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getNombreAccion() {
		return nombreAccion;
	}
	public void setNombreAccion(String nombreAccion) {
		this.nombreAccion = nombreAccion;
	}
        public String getNombreObjeto() {
            return nombreObjeto;
        }
        public void setNombreObjeto(String nombreObjeto) {
            this.nombreObjeto = nombreObjeto;
        }
	public String getHtmlId() {
		return htmlId;
	}
	public void setHtmlId(String htmlId) {
		this.htmlId = htmlId;
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
	public String getNombreES() {
		return nombreES;
	}
	public void setNombreES(String nombreES) {
		this.nombreES = nombreES;
	}
	public String getNombreEN() {
		return nombreEN;
	}
	public void setNombreEN(String nombreEN) {
		this.nombreEN = nombreEN;
	}
	public String getAplicacion() {
		return aplicacion;
	}
	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}
	public String getModificacionUsuarioStr() {
		return modificacionUsuarioStr;
	}
	public void setModificacionUsuarioStr(String modificacionUsuarioStr) {
		this.modificacionUsuarioStr = modificacionUsuarioStr;
	}
	public String getModulo() {
		return modulo;
	}
	public void setModulo(String modulo) {
		this.modulo = modulo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
        
}
