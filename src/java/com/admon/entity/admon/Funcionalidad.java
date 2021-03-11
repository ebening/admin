package com.admon.entity.admon;

import java.io.Serializable;
import java.sql.Timestamp;

public class Funcionalidad implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long funcionalidadId;
	private Long operacionId;
	private Long moduloId;
	private Long aplicacionId;
	private String htmlId;
	private Long tipoId;
	private String descripcion;
        private String metodo;
	private Timestamp creacionFecha;
	private Integer creacionUsuario;
	private Timestamp modificacionFecha;
	private Integer modificacionUsuario;
	private Integer estatusId;
	private Integer eliminadoId;
	
	private String nombreES;
	private String nombreEN;
	private String aplicacion;
	private String modulo;
	private String operacion;
	private String modificacionUsuarioStr;
	private String tipo;
	
	public Long getFuncionalidadId() {
		return funcionalidadId;
	}
	public void setFuncionalidadId(Long funcionalidadId) {
		this.funcionalidadId = funcionalidadId;
	}
	public Long getOperacionId() {
		return operacionId;
	}
	public void setOperacionId(Long operacionId) {
		this.operacionId = operacionId;
	}
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
	public String getHtmlId() {
		return htmlId;
	}
	public void setHtmlId(String htmlId) {
		this.htmlId = htmlId;
	}
	public Long getTipoId() {
		return tipoId;
	}
	public void setTipoId(Long tipoId) {
		this.tipoId = tipoId;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	public String getModulo() {
		return modulo;
	}
	public void setModulo(String modulo) {
		this.modulo = modulo;
	}
	public String getOperacion() {
		return operacion;
	}
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	public String getModificacionUsuarioStr() {
		return modificacionUsuarioStr;
	}
	public void setModificacionUsuarioStr(String modificacionUsuarioStr) {
		this.modificacionUsuarioStr = modificacionUsuarioStr;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

        public String getMetodo() {
            return metodo;
        }

        public void setMetodo(String metodo) {
            this.metodo = metodo;
        }

	
}
