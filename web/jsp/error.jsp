
<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@taglib  prefix='s' uri='/struts-tags'%>
<!DOCTYPE html>
<html>
    <head>
        <link type='text/css' href="<s:url value="/css/estilos/security.css"/>" rel='stylesheet'/>
        <title><s:text name='key_error_titulo' /></title>
    </head>
    <body >
        <div class='ui-widget ui-widget-content ui-corner-all'>
            <div class='error-container'>
                <div class='error-titulo'>
                    <span style='display: inline-block; margin: 4px 4px -3px 0;' class='ui-icon ui-icon-alert'></span>
                    <s:text name='key_error_titulo' />
                    <span style='display: inline-block; margin: 4px 4px -3px 0;' class='ui-icon ui-icon-alert'></span>
                </div>
                <div class='error-mensaje'><s:text name='key_error_mensaje' /></div>
                <div class='error-mensaje'><s:text name='key_error_solucion' /></div>
            </div>
        </div>
    </body>
</html>

