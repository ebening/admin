<%@taglib  prefix='s' uri='/struts-tags'%>

<%@page import="com.admon.model.util.SessionVar"%>
<%SessionVar sessionVar = (SessionVar) session.getAttribute("SESSION_PORTAL");%>

<script src='<s:url value='/js/decorators/include/encabezado.js'/>' type='text/javascript'></script>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <!--<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#sidebar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>-->
            <!--<a class="navbar-brand" href="#"><span>Lumino</span>Admin</a>-->
            <!--<img src='/admon/images/logo/logo_header.png' title='Logo' alt='Logo'>-->
            <!-- Nombre de la aplicación -->
            <div class='navbar-brand'>
                <span><%=sessionVar.getConfiguraciones().get("Aplicacion nombre completo")%></span>
            </div>
            <ul class="user-menu">
                <li class="dropdown pull-right">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    
                    <table>
                        <!--<tr>
                        <td><span class='ui-icon ui-icon-calendar'></span></td>
                        <td><div class='session-date'></div></td>
                        </tr>-->
                        <tr>
                            <td><svg class="glyph stroked male-user"><use xlink:href="#stroked-male-user"></use></svg> </td>
                            <td>
                                <div class='username-long' style='color: #ffffff;' title='<%=sessionVar.getNombre()%> <%=sessionVar.getApellidoPaterno()%>'><%=sessionVar.getNombre()%>&nbsp;<%=sessionVar.getApellidoPaterno()%></div>
                            </td>
                            <td><span class="caret"></span></td>
                        </tr>
                    </table>
                    
                    </a>
                <ul class="dropdown-menu" role="menu">
                    <li class="link"><a href="#"><svg class="glyph stroked male-user"><use xlink:href="#stroked-male-user"></use></svg> <s:text name='key_perfilusuario_cambiarcontrasena'/></a></li>
                    <!--<li><a href="#"><svg class="glyph stroked gear"><use xlink:href="#stroked-gear"></use></svg> Settings</a></li>-->
                    <li class="salir"><a href="#"><svg class="glyph stroked cancel"><use xlink:href="#stroked-cancel"></use></svg> <s:text name='key_encabezado_salir'/></a></li>
                </ul>
                </li>

                <table>
                    <!--<tr>
                    <td><span class='ui-icon ui-icon-calendar'></span></td>
                    <td><div class='session-date'></div></td>
                    </tr>-->
                    <!--<tr>
                        <td><span class='ui-icon ui-icon-person'></span></td>
                        <td>
                            <div class='username-long' style='color: #ffffff;' title='<%=sessionVar.getNombre()%> <%=sessionVar.getApellidoPaterno()%>'><%=sessionVar.getNombre()%>&nbsp;<%=sessionVar.getApellidoPaterno()%></div>
                        </td>
                        <td><span class='ui-icon ui-icon-circle-triangle-s'></span></td>
                    </tr>-->
                </table>
                <!-- Botón Salir/Logout -->
                <!--<div id='logout' title='<s:text name='key_encabezado_salir'/>'>
                <s:text name='key_encabezado_salir'/>
                <span class='ui-icon ui-icon-circle-close' style='float: right'> </span>
                </div>-->
                <!-- Botón Minimizar -->
                <!--<div id='minimize' title='Minimizar'>
                <span class='ui-icon ui-icon-circle-triangle-n'> </span>
                </div>-->
            </ul>
        </div>

    </div><!-- /.container-fluid -->
</nav>
