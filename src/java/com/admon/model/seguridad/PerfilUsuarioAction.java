package com.admon.model.seguridad;

import com.admon.bss.seguridad.PerfilUsuarioBss;
import com.admon.model.BaseModel;
import com.admon.model.util.SessionVar;

public class PerfilUsuarioAction extends BaseModel {

    public PerfilUsuarioBss perfilUsuarioBss;
    public String nombre = new String();
    public String usuario = new String();
    public String correo = new String();

    /**
     * Método que llena los elementos del formulario del JSP mediante Struts al
     * cargar la página estatusPago.jsp
     *
     * @return Regresa un código de tipo String, el método execute() es
     * ejecutado y la respuesta es evaluada, finalmente se manda llamar al JSP
     * correspondiente según la respuesta.
     */
    @Override
    public String execute() {
        SessionVar sessionVar = perfilUsuarioBss.getDatosSession();
        String ap = "";
        if (sessionVar.getApellidoPaterno() != null) {
            ap = sessionVar.getApellidoPaterno();
        }
        String am = "";
        if (sessionVar.getApellidoMaterno() != null) {
            am = sessionVar.getApellidoMaterno();
        }
        setNombre(sessionVar.getNombre() + " " + ap + " " + am);
        setUsuario(sessionVar.getUsuario());
        setCorreo(sessionVar.getCorreo());
        return SUCCESS;
    }

    public void setPerfilUsuarioBss(PerfilUsuarioBss perfilUsuarioBss) {
        this.perfilUsuarioBss = perfilUsuarioBss;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
