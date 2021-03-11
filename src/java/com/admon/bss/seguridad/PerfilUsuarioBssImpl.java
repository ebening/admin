package com.admon.bss.seguridad;

import com.admon.bss.BaseBss;
import com.admon.bss.admon.UsuarioBss;
import com.admon.entity.admon.Usuario;
import com.admon.model.util.SessionVar;
import java.util.Arrays;

public class PerfilUsuarioBssImpl extends BaseBss implements PerfilUsuarioBss {

    public UsuarioBss usuarioBss;
    public RecuperarContrasenaBss recuperarContrasenaBss;

    @Override
    public SessionVar getDatosSession() {
        return getUsuarioSession();
    }

    @Override
    public Integer cambiarContrasena(String contrasenaActual,
            String nuevaContrasena, boolean enviarCorreo) {
        SessionVar sessionVar = getDatosSession();
        if (sessionVar == null) {
// No hay sesion
            return 2;
        } else {
            Usuario usuario = usuarioBss.findById(sessionVar.getUsuarioId());
            if (usuario == null) {
// No hay usuario
                return 2;
            } else {
                if (contrasenaActual.equalsIgnoreCase(
                        desencripta(usuario.getContrasena()))) {
                    usuario.setContrasena(nuevaContrasena);
                    usuarioBss.update(sessionVar.getUsuarioId(), Arrays.asList(usuario));
                    if (enviarCorreo) {
                        recuperarContrasenaBss.cambiarContrasena(usuario.getUsuario());
                    }
// Todo ok!
                    return 0;
                } else {
// La contraseña actual es diferente
                    return 1;
                }
            }
        }
    }

    public void setUsuarioBss(UsuarioBss usuarioBss) {
        this.usuarioBss = usuarioBss;
    }

    public void setRecuperarContrasenaBss(RecuperarContrasenaBss recuperarContrasenaBss) {
        this.recuperarContrasenaBss = recuperarContrasenaBss;
    }
}
