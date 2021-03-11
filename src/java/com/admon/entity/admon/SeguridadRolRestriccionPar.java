package com.admon.entity.admon;

import java.io.Serializable;
import java.sql.Timestamp;

public class SeguridadRolRestriccionPar implements Serializable {
    
    private Integer rolId;
    private Integer catalogoParametroId;
    private Integer aplicacionId;
    private Timestamp creacionFecha;
    private Integer creacionUsuario;
    private String usuarioModificacion;

    /*
*Constructor de la clase Catálogo.java
     */
    public SeguridadRolRestriccionPar() {
    }

    public Integer getRolId() {
        return rolId;
    }

    public void setRolId(Integer rolId) {
        this.rolId = rolId;
    }

    public Integer getCatalogoParametroId() {
        return catalogoParametroId;
    }

    public void setCatalogoParametroId(Integer catalogoParametroId) {
        this.catalogoParametroId = catalogoParametroId;
    }

    public Integer getAplicacionId() {
        return aplicacionId;
    }

    public void setAplicacionId(Integer aplicacionId) {
        this.aplicacionId = aplicacionId;
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

    public String getUsuarioModificacion() {
        return usuarioModificacion;
    }

    public void setUsuarioModificacion(String usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

}
