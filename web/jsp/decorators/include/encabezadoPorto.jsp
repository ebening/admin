<%@taglib  prefix='s' uri='/struts-tags'%>
<%@page import="com.admon.model.util.SessionVar"%>
<%SessionVar sessionVar = (SessionVar) session.getAttribute("SESSION_PORTAL");%>
<script src='<s:url value='/js/decorators/include/encabezado.js'/>' type='text/javascript'></script>
<!-- start: header -->
<header class="header">
    <div class="logo-container">
        <a href="" class="logo">
            <img src="<s:url value="/css/themes/porto/images/logo.png"/>" height="35" alt="<%=sessionVar.getConfiguraciones().get("Aplicacion nombre completo")%>" />
        </a>
        <div class="visible-xs toggle-sidebar-left" data-toggle-class="sidebar-left-opened" data-target="html" data-fire-event="sidebar-left-opened">
            <i class="fa fa-bars" aria-label="Toggle sidebar" style="margin-top:9px; cursor: pointer;"></i>
        </div>
    </div>

    <!-- start: search & user box -->
    <div class="header-right">

        <span class="separator"></span>

        <div id="userbox" class="userbox">
            <a href="#" data-toggle="dropdown">
                <figure class="profile-picture">
                    <img src="<s:url value="/css/themes/porto/images/!logged2-user.jpg"/>" 
                         alt="<%=sessionVar.getNombre()%> <%=sessionVar.getApellidoPaterno()%>" 
                         class="img-circle" 
                         data-lock-picture="<s:url value="/css/themes/porto/images/!logged2-user.jpg"/>" />
                </figure>
                <div class="profile-info" 
                     data-lock-name="<%=sessionVar.getNombre()%> <%=sessionVar.getApellidoPaterno()%>" 
                     data-lock-email="<%=sessionVar.getCorreo()%>">
                    <span class="name"><%=sessionVar.getNombre()%> <%=sessionVar.getApellidoPaterno()%></span>
                    <span class="role"><%=sessionVar.getPerfil()%></span>
                </div>

                <i class="fa custom-caret"></i>
            </a>

            <div class="dropdown-menu">
                <ul class="list-unstyled">
                    <li class="divider"></li>
                    <li>
                        <a role="menuitem" tabindex="-1" href="#" class="link">
                            <i class="fa fa-user"></i> <s:text name='key_encabezado_cambiarcontrasena'/>
                        </a>
                    </li>
                    <li>
                        <a role="menuitem" tabindex="-1" href="<s:url value="/jsp/seguridad/logout.jsp"/>" >
                            <i class="fa fa-power-off"></i> <s:text name='key_encabezado_salir'/>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <!-- end: search & user box -->
</header>
<!-- end: header -->