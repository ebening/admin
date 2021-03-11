<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix='s' uri='/struts-tags'%>
<!DOCTYPE html>
<html>
    <head>
        <meta name='http-equiv' content='Content-type: text/html; charset=UTF-8'/>
        <!-- DWR -->
        <script src="<s:url value="/dwr/engine.js"/>" type='text/javascript'></script>
        <script src="<s:url value="/dwr/util.js"/>" type='text/javascript'></script>
        <script src="<s:url value="/dwr/interface/PerfilUsuarioDWR.js"/>" type='text/javascript' ></script>
        <!-- JQuery -->
        <script src="<s:url value="/js/jquerycore/jquery.min.js"/>" type='text/javascript'></script>
        <script src="<s:url value="/js/jquerycore/jquery-ui.min.js"/>" type='text/javascript'></script>
        <!-- JS -->
        <script src="<s:url value="/js/extras/theme.js"/>" type='text/javascript'></script>
        <script src="<s:url value="/js/extras/util.js"/>" type='text/javascript'></script>
        <!--[if lt IE 9]>
        <script src="<s:url value="/js/jquerycomponentes/excanvas.js"/>" type='text/javascript'></script>
        <![endif]-->
        <!-- Toast -->
        <link href="<s:url value="/css/jquerycomponentes/jquery.toastmessage.css"/>" type='text/css' rel='stylesheet'/>
        <link href="<s:url value="/css/jquerycomponentes/jquery.onoff.css"/>" type='text/css' rel='stylesheet'/>
        <script src="<s:url value="/js/jquerycomponentes/jquery.showpassword.js"/>" type='text/javascript'></script>
        <script src="<s:url value="/js/jquerycomponentes/jquery.onoff.js"/>" type='text/javascript'></script>
        <script src="<s:url value="/js/jquerycomponentes/jquery.toastmessage.js"/>" type='text/javascript'></script>
        <!-- Tooltip -->
        <link href="<s:url value="/css/jquerycomponentes/jquery.tipped-3.1.8.css"/>" type='text/css' rel='stylesheet'/>
        <script src="<s:url value="/js/jquerycomponentes/jquery.tipped-3.1.8.js"/>" type='text/javascript'></script>
        <script src="<s:url value="/js/extras/tooltip.js"/>" type='text/javascript'></script>
        <!-- Validaciones -->
        <script src="<s:url value="/js/validador/validanguage-1.0.3.js"/>" type="text/javascript"></script>
        <script src="<s:url value="/js/validador/validaciones.js"/>" type="text/javascript"></script>
        <!-- Estilos -->
        <link type='text/css' href="<s:url value="/css/estilos/estilos.css"/>" rel='stylesheet'/>
        <!-- JS -->
        <script src='<s:url value='/js/seguridad/perfilUsuario.js'/>' type='text/javascript'></script>
        <!-- Para iconos -->
        <link type='text/css' href="<s:url value="/font/font-awesome-4.7.0/css/font-awesome.min.css"/>" rel='stylesheet'/>
        
        <script src="<s:url value="/js/extras/lumino.glyphs.js"/>" type="text/javascript"></script>

    </head>
    <body class='body no-scrollbar'>

        <!-- Textos Genericos -->    
        <%@ include file='/jsp/textos/generico.jsp'%>

        <div class='form-wrap'>

            <div class='titulo ui-corner-top'>
                <div class='title-text'><s:text name='key_perfilusuario_cambiarcontrasena'/></div>
                <div class='clear-both'></div>
            </div>

            <table class='full-width'>
                <tr>
                    <td rowspan='4'>
                        <div style='
                             width: 80px;
                             height: 80px;
                             line-height: 80px;
                             background-color: #1EBFAE; 
                             text-align: center; 
                             font-size: 32px; 
                             color: #fff;
                             border-radius: 3px;'>
                            <i class="fa fa-user-o"></i>
                        </div>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td><s:textfield name='nombre' disabled='true' cssClass='textbox-display td-input' /></td>
                </tr>
                <tr>
                    <td></td>
                    <td><s:textfield name='usuario' disabled='true' cssClass='textbox-display td-input' /></td>
                </tr>
                <tr>
                    <td></td>
                    <td><s:textfield name='correo' disabled='true' cssClass='textbox-display td-input' /></td>
                </tr>
            </table>
            <div class='pair-wrap divisor-small-margin'></div>
            <s:form id='perfilUsuarioform' name='perfilUsuarioform'>
                <table class="full-width">
                    <tr>
                        <td class='td-etiqueta-b'><div class='etiqueta text-to-right'><s:text name='key_perfilusuario_contrasenaanterior'/>:&nbsp;</div></td>
                        <td><input type='password' id='contrasenaAnterior' data-typetoggle='#toggle-view' class='textbox' /></td>
                    </tr>
                    <tr>
                        <td class='td-etiqueta-b'><div class='etiqueta text-to-right'><s:text name='key_perfilusuario_nuevacontrasena'/>:&nbsp;</div></td>
                        <td><input type='password' id='contrasena' maxLength='250' data-typetoggle='#toggle-view' class='textbox' /></td>
                    </tr>
                    <tr>
                        <td class='td-etiqueta-b'><div class='etiqueta text-to-right'><s:text name='key_perfilusuario_confirmarnuevacontrasena'/>:&nbsp;</div></td>
                        <td><input type='password' id='confirmarContrasena' data-typetoggle='#toggle-view' class='textbox' /></td>
                    </tr>
                    <tr>
                        <td class='td-etiqueta-b'><div class='etiqueta text-to-right'><s:text name='key_perfilusuario_enviarmecopia'/>:&nbsp;</div></td>
                        <td><s:checkbox name='enviarCorreo' cssClass='checkbox' cssStyle='margin-top: 6px;'/></div></td>
                    </tr>
                </table>   
            </s:form>
            <!--
            <div class='pair-wrap divisor'></div>
            <div style="width: calc(100% - 5px);">
                <div style='margin-top: 8px; display: none;' class='float-right'>
                    <span class='float-left' style='width: 30px; font-weight: bold; color:#5F6468;'>***</span>
                    <span class='float-left' style='width: 50px;'><s:checkbox name='toggle-view'/></span>
                    <span class='float-left' style='width: 30px; font-weight: bold; color:#5F6468;'>ABC</span>
                </div>
                <div class='float-left' style='width: 150px;'>
                    
                </div>
                <div class='clear-both'></div>
            </div>
            -->
            <div class='pair-wrap divisor'></div>
            <div style="width: calc(100% - 5px);">
                <div class='button-custom' id='confirmar'><s:text name='key_perfilusuario_confirmar' /></div>
            </div>
            
            <!--
            <div class='button half-width float-right' id='cancelar'><s:text name='key_perfilusuario_cancelar' /></div>
            -->
            <div class='clear-both'></div>
        </div>
    </body>
</html>

