package com.admon.bss.seguridad;

import com.admon.entity.admon.Pagina;
import com.admon.entity.admon.Usuario;
import com.admon.model.util.SessionVar;
import java.util.List;
import java.util.Map;

public interface LoginBss {

    /*
* Método que genera un objeto SessionVar a partir de la validación de usuario y contraseña durante el
* proceso de login.
*
* @param usuario es el nombre de usuario
* @param contrasena es la contraseña de usuario
* @return regresa un objecto SessionVar que contiene los datos del usuario autenticado
     */
    public SessionVar login(String usuario, String contrasena);

    /*
* Obtiene un usuario con el usuario y contraseña especificada..
*
* @param usuario es el usuario
* @param contrasena es la contraseña
* @return El usuario que coincida con las credenciales especificadas
     */
    public Usuario obtieneUsuario(String usuario, String contrasena);

    /*
* Método que obtiene todas las páginas a las que el usuario tiene acceso.
*
* @param usuarioId es el id de usuario
* @return paginaList (List<Pagina>) regresa una lista de todas las páginas a las que el usuario tiene
* acceso.
     */
    public List<Pagina> obtieneMenuByUsuarioId(int usuarioId);

    /*
* Método que obtiene las configuraciones de la aplicación
*
* @return regresa un objeto con las configuraciones
     */
    public Map<String, String> obtieneConfiguraciones();
}
