package com.admon.model.seguridad;

import com.admon.bss.seguridad.RecuperarContrasenaBss;
import com.admon.model.BaseModel;

public class RecuperarContrasenaDWR extends BaseModel {

    /*
* Inyección de dependencias con Spring. Estas dependencias se configuran en el applicationContext.xml, y
* además necesitan un método setter por cada variable que se anexa al final de la clase.
     */
    public RecuperarContrasenaBss recuperarContrasenaBss;

    public RecuperarContrasenaDWR() {
    }

    /*
* Método para recuperar la contraseña de un usuario por su nombre de usuario. La contraseña es enviada
* por correo electronico a la dirección especificada para ese usuario.
*
* @param user (String) es el nombre de usuario.
     */
    public String recuperarContrasena(String usuario) {
        return recuperarContrasenaBss.recuperarContrasena(usuario);
    }

    /*
* Inyección de dependencias con Spring, cada referencia definida al inicio de la clase requiere un método
* Setter.
     */
    public void setRecuperarContrasenaBss(RecuperarContrasenaBss recuperarContrasenaBss) {
        this.recuperarContrasenaBss = recuperarContrasenaBss;
    }
}
