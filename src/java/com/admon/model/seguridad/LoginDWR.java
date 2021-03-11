package com.admon.model.seguridad;

import com.admon.bss.seguridad.LoginBss;
import com.admon.model.BaseModel;
import com.admon.model.util.SessionVar;
import org.directwebremoting.WebContextFactory;

public class LoginDWR extends BaseModel {

    /*
* Inyección de dependencias con Spring. Estas dependencias se configuran en el applicationContext.xml, y
* además necesitan un método setter por cada variable que se anexa al final de la clase.
     */
    private LoginBss loginBss;

    public LoginDWR() {
    }

    /*
* Método que valida un usuario en el sistema mediante su nombre de usuario y contraseña. Si el usuario es
* válido se crea una variable de sesión que guarda un objeto con las propiedades de sesión para ese
* usuario.
*
* @param usuario es el nombre de usuario a autenticar.
* @param contrasena es la contrasena de usuario a autenticar.
* @return url String que contiene una url
     */
    public String login(String usuario, String contrasena) {
        try {
            SessionVar sessionVar = loginBss.login(usuario, contrasena);
            if (sessionVar.getUrl() != null) {
                WebContextFactory.get().getSession().setAttribute("SESSION_PORTAL", sessionVar);
            }
            return sessionVar.getUrl();
        } catch (Exception e) {
            System.out.println("Error login: " + e.getMessage());
            return "";
        }
    }

    /*
* Método que invalida la sesión actual de manera explícita.
*
     */
    public void logout() {
        WebContextFactory.get().getSession().invalidate();
    }

    /*
* Inyección de dependencias con Spring, cada referencia definida al inicio de la clase requiere un método
* Setter.
     */
    public void setLoginBss(LoginBss loginBss) {
        this.loginBss = loginBss;
    }
}
