package com.admon.bss.seguridad;

import com.admon.model.util.SessionVar;

public interface PerfilUsuarioBss {

    public SessionVar getDatosSession();

    public Integer cambiarContrasena(String contrasenaActual,
            String nuevaContrasena, boolean enviarCorreo);
}
