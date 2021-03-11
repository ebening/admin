package com.admon.bss.seguridad;

import com.admon.bss.BaseBss;
import com.admon.bss.admon.ConfiguracionParametroBss;
import com.admon.bss.admon.PaginaBss;
import com.admon.bss.admon.PerfilBss;
import com.admon.bss.admon.UsuarioBss;
import com.admon.bss.admon.PerfilPaginaBss;
import com.admon.entity.admon.ConfiguracionParametro;
import com.admon.entity.admon.Pagina;
import com.admon.entity.admon.Perfil;
import com.admon.entity.admon.Usuario;
import com.admon.entity.admon.PerfilPagina;
import com.admon.model.util.SessionVar;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginBssImpl extends BaseBss implements LoginBss {

    /*
* Inyección de dependencias con Spring. Estas dependencias se configuran en el applicationContext.xml, y
* además necesitan un método setter por cada variable que se anexa al final de la clase.
     */
    public UsuarioBss usuarioBss;
    public PaginaBss paginaBss;
    private PerfilPaginaBss perfilPaginaBss;
    private PerfilBss perfilBss;
    public ConfiguracionParametroBss configuracionParametroBss;

    public LoginBssImpl() {
    }

    /*
* Método que genera un objeto SessionVar a partir de la validación de usuario y contraseña durante el
* proceso de login.
*
* @param usuario es el nombre de usuario
* @param contrasena es la contraseña de usuario
* @return regresa un objecto SessionVar que contiene los datos del usuario autenticado
     */
    @Override
    public SessionVar login(String usuario, String contrasena) {
        SessionVar sessionVar = new SessionVar();
        Usuario user = obtieneUsuario(usuario, contrasena);         
        Perfil perfil =  perfilBss.findById(user.getPerfilId());
        if (user != null) {
            sessionVar.setUsuario(user.getUsuario());
            sessionVar.setNombre(user.getNombre());
            sessionVar.setApellidoPaterno(user.getApellidoPaterno());
            sessionVar.setApellidoMaterno(user.getApellidoMaterno());
            sessionVar.setUsuarioId(user.getUsuarioId());
            sessionVar.setCorreo(user.getCorreo());
            sessionVar.setPagina(obtieneMenuByUsuarioId(user.getUsuarioId()));
            sessionVar.setHtmlMenu(nestMenu(sessionVar.getPagina()));
            sessionVar.setUrl("/admon/index.action");
            sessionVar.setConfiguraciones(obtieneConfiguraciones());
            sessionVar.setReportData(new HashMap<String, List>());
            sessionVar.setPerfil(perfil.getNombre());
        }
        return sessionVar;
    }

    /*
* Obtiene un usuario con el usuario y contraseña especificada..
*
* @param usuario es el usuario
* @param contrasena es la contraseña
* @return El usuario que coincida con las credenciales especificadas
     */
    @Override
    public Usuario obtieneUsuario(String usuario, String contrasena) {
        Usuario user;
        List<Usuario> list = usuarioBss.findByCredentials(usuario, encripta(contrasena));
        if (list.size() == 1) {
            user = list.get(0);
        } else {
            user = null;
        }
        return user;
    }

    /*
* Método que obtiene todas las páginas a las que el usuario tiene acceso.
*
* @param usuarioId es el id de usuario
* @return paginaList (List<Pagina>) regresa una lista de todas las páginas a las que el usuario tiene
* acceso.
     */
    @Override
    public List<Pagina> obtieneMenuByUsuarioId(int usuarioId) {
        Usuario usuario = usuarioBss.findById(usuarioId);
        List<PerfilPagina> perfilPaginaList = perfilPaginaBss.findByIntProperty("perfilId", usuario.getPerfilId());
        List<Integer> perfilPaginaIdList = new ArrayList<Integer>();
        for (PerfilPagina perfilPagina : perfilPaginaList) {
            perfilPaginaIdList.add(perfilPagina.getPaginaId());
        }
        if (perfilPaginaIdList.isEmpty()) {
            return new ArrayList<Pagina>();
        }
        return paginaBss.findByPaginaIdList(perfilPaginaIdList);
    }

    List<Pagina> nestedPages = new ArrayList();
    List<Pagina> allPages = new ArrayList();
    boolean isBaseRoot = true;

    public String nestMenu(List<Pagina> paginaList) {
        nestedPages = new ArrayList();
        allPages = paginaList;
        // ByPass NullPointerException
        for (Pagina pagina : allPages) {
            if (pagina.getAnidar() == null) {
                pagina.setAnidar(0);
            }
            if (pagina.getOrden() == null) {
                pagina.setOrden(0);
            }
        }
        isBaseRoot = true;
        setChilds(allPages);
        for (Pagina pagina : allPages) {
            if (pagina.getAnidar() == 0) {
                nestedPages.add(pagina);
            }
        }
        ordenarPagina(nestedPages);
        String htmlMenu = menuToHtml(nestedPages);
        return htmlMenu;
    }

    public void ordenarPagina(List<Pagina> paginaList) {
        Collections.sort(paginaList, new Comparator<Pagina>() {
            @Override
            public int compare(Pagina e1, Pagina e2) {
                if (e1.getOrden().intValue()
                        == e2.getOrden()) {
                    return 0;
                }
                return e1.getOrden() < e2.getOrden() ? -1 : 1;
            }
        });
    }

    public String menuToHtml(List<Pagina> menuList) {
        StringBuilder sb = new StringBuilder();
        if (menuList.isEmpty()) {
        } else {
            // Esto para indicar el tag UL raíz
            String attr;
            String indx = "";
            if (isBaseRoot) {
                attr = "nav nav-main";
                indx = "   <li>\n"
                        + "    <a href='/admon/index.action'>\n"
                        + "        <i class='fa fa-tachometer' aria-hidden='true'></i>\n"
                        + "            <span>Inicio</span>\n"
                        + "    </a>\n"
                        + "</li>";
                isBaseRoot = false;
            } else {
                attr = "nav nav-children";
            }

            sb.append("<ul class=\"").append(attr).append("\">\n");
            sb.append(indx);
            for (Pagina pagina : menuList) {
                if (pagina.getNoIncluirEnMenu() != null) {
                    if (pagina.getNoIncluirEnMenu() == 0) {
                        sb.append(menuToHtmlHelper(pagina));
                    }
                }
            }
            sb.append("</ul>\n");
        }
        return sb.toString();
    }

    public String menuToHtmlHelper(Pagina pagina) {
        StringBuilder sb = new StringBuilder();

        // Tiene submenu
        String cssParentClass = "";
        if (!pagina.getSubmenu().isEmpty()) {
            cssParentClass = "nav-parent";
        }

        sb.append("<li class=\"").append(cssParentClass).append("\">\n");

        // Url
        if (pagina.getUrl() == null) {
            sb.append("<a>");
        } else {
            if (pagina.getUrl().trim().equals("")) {
                sb.append("<a>");
            } else {
                sb.append("<a href='").append(pagina.getUrl()).append("'>");
            }
        }

        // Icono
        String icono = "fa-circle";
        if (pagina.getIcono() == null) {
        } else {
            if (pagina.getIcono().trim().equals("")) {
            } else {
                icono = pagina.getIcono();
            }
        }
        sb.append("<i class=\"fa ").append(icono).append("\" aria-hidden=\"true\"></i>\n");
        // Nombre
        sb.append("<span>").append(pagina.getNombre()).append("</span>\n");
        sb.append("</a>");

        sb.append(menuToHtml(pagina.getSubmenu()));
        sb.append("</li>\n");
        return sb.toString();
    }

    /**
     *
     * @param baseList
     */
    public void setChilds(List<Pagina> baseList) {
        for (Pagina parent : baseList) {
            if (!parent.isSubmenuPopulated()) {
                parent.setSubmenu(getChilds(parent));
                parent.setIsSubmenuPopulated(true);
            }
            setChilds(parent.getSubmenu());
        }
    }

    /**
     * Obtiene una lista de objetos pagina que son childs de otro
     *
     * @param paginaParent pagina de la cual se obtienen los descendientes
     * @return
     */
    public List<Pagina> getChilds(Pagina paginaParent) {
        List<Pagina> childList = new ArrayList();
        for (Pagina paginaActual : allPages) {
            if (paginaActual.getAnidar().intValue()
                    == paginaParent.getPaginaId()) {
                childList.add(paginaActual);
            }
        }
        ordenarPagina(childList);
        return childList;
    }

    /*
* Método que obtiene las configuraciones de la aplicación
*
* @return regresa un objeto con las configuraciones
     */
    @Override
    public Map<String, String> obtieneConfiguraciones() {
        Map<String, String> configuraciones = new HashMap<String, String>();
        List<ConfiguracionParametro> configuracionParametroLista = configuracionParametroBss.findAll();
        for (ConfiguracionParametro configuracionParametro : configuracionParametroLista) {
            configuraciones.put(configuracionParametro.getNombre(), configuracionParametro.getValor());
        }
        configuraciones.put("build_number", BUILD_NUMBER);
        configuraciones.put("build_timestamp", BUILD_TIMESTAMP);
        return configuraciones;
    }

    /*
* Inyección de dependencias con Spring, cada referencia definida al inicio de la clase requiere un método
* Setter.
     */
    public void setUsuarioBss(UsuarioBss usuarioBss) {
        this.usuarioBss = usuarioBss;
    }

    public void setPaginaBss(PaginaBss paginaBss) {
        this.paginaBss = paginaBss;
    }

    public void setConfiguracionParametroBss(ConfiguracionParametroBss configuracionParametroBss) {
        this.configuracionParametroBss = configuracionParametroBss;
    }

    /**
     * @param perfilPaginaBss the perfilPaginaBss to set
     */
    public void setPerfilPaginaBss(PerfilPaginaBss perfilPaginaBss) {
        this.perfilPaginaBss = perfilPaginaBss;
    }

    /**
     * @return the perfilBss
     */
    public PerfilBss getPerfilBss() {
        return perfilBss;
    }

    /**
     * @param perfilBss the perfilBss to set
     */
    public void setPerfilBss(PerfilBss perfilBss) {
        this.perfilBss = perfilBss;
    }
}
