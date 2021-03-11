package com.admon.model.seguridad;

import com.admon.bss.seguridad.PerfilUsuarioBss;
import com.admon.model.BaseModel;

public class PerfilUsuarioDWR extends BaseModel {

    public PerfilUsuarioBss perfilUsuarioBss;

    public Integer cambiarContrasena(String contrasenaActual, String nuevaContrasena, boolean enviarCorreo) {
        return perfilUsuarioBss.cambiarContrasena(contrasenaActual, nuevaContrasena, enviarCorreo);
    }

    public void setPerfilUsuarioBss(PerfilUsuarioBss perfilUsuarioBss) {
        this.perfilUsuarioBss = perfilUsuarioBss;
    }
}
