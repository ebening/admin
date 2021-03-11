package com.admon.bss.seguridad;

import com.admon.entity.admon.ConfiguracionParametro;
import com.admon.entity.admon.Usuario;
import java.util.List;

public interface RecuperarContrasenaBss {

    /*
* Método para recuperar la contraseña de un usuario por su nombre de usuario. La contraseña es enviada
* por correo electronico a la dirección especificada para ese usuario.
*
* @param user (String) es el nombre de usuario.
* @return Regresa un string con un mensaje sobre el resultado del método
     */
    public String recuperarContrasena(String user);

    public String cambiarContrasena(String user);

    /*
* Método que obtiene las configuraciones para el envio de correo.
*
* @return Regresa una lista de objetos ConfiguracionParametro con la información de la consulta
     */
    public List<ConfiguracionParametro> obtieneConfiguraciones(int configuracionId);

    /*
* Método que busca en la base de datos los datos del usuario con el usuario especificado.
*
* @param user Es el nombre de usuario
* @return Regresa un objeto Usuario con la información de la consulta o null si no se encontró el usuario.
     */
    public Usuario obtieneUsuario(String user);

}
