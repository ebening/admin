package com.admon.model.seguridad.interceptor;

import com.admon.entity.admon.Pagina;
import com.admon.model.BaseModel;
import com.admon.model.util.SessionVar;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

public class AuthenticationInterceptor extends BaseModel implements Interceptor {

    @Override
    public void destroy() {
    }

    @Override
    public void init() {
    }

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        // Obtener la sesión
        Map sesion = actionInvocation.getInvocationContext().getSession();
        SessionVar sessionVar = (SessionVar) sesion.get("SESSION_PORTAL");

        if (sessionVar == null) {
            // No hay sesión
            return Action.LOGIN;
        } else {
            if (sessionVar.getUsuarioId() == null) {
                // SessionVar no válido
                return Action.LOGIN;
            } else {
                if (validarAcceso(actionInvocation)) {
                    // Con acceso
                    return actionInvocation.invoke();
                } else {
                    // Sin acceso
                    return Action.ERROR;
                }
            }
        }
    }

    /*
* Método que valida si un usuario tiene acceso a la página que solicito.
*
* @param actionInvocation parametro para obtener la sesion de usuario.
* @return regresa true si el usuario tiene acceso de lo contrario regresa false.
     */
    private boolean validarAcceso(ActionInvocation actionInvocation) {
        HttpServletRequest requested = ServletActionContext.getRequest();
        String urlSolicitada = requested.getRequestURI();

        // Default access
        if (urlSolicitada.equalsIgnoreCase("/admon/index.action")) {
            return true;
        }
// Default access
        if (urlSolicitada.equalsIgnoreCase("/admon/seguridad/perfilUsuario.action")) {
            return true;
        }

// Acceso a reportes
        if (urlSolicitada.endsWith("Reporte.action")) {
            return true;
        }
        // Validar acceso
        Map sesion = actionInvocation.getInvocationContext().getSession();
        SessionVar sessionVar = (SessionVar) sesion.get("SESSION_PORTAL");
        List<Pagina> paginas = sessionVar.getPagina();
        for (Pagina pagina : paginas) {
            String pag = pagina.getUrl();
            if (pag != null) {
// Remover parámetros de la url
                if (pag.contains("?")) {
                    pag = pag.split("\\?")[0];
                }
                if (pag.equalsIgnoreCase(urlSolicitada)
                        || urlSolicitada.equalsIgnoreCase("/admon/admon/download.action")
                        || urlSolicitada.equalsIgnoreCase("/admon/admon/exportarPDF.action")
                        || urlSolicitada.equalsIgnoreCase("/admon/reportes/estadoCuentaExportar.action")) {
                    sessionVar.setRequest(pagina);
                    return true;
                }
            }
        }
        return false;
    }
}
