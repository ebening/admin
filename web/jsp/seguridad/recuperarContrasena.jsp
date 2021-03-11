<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@taglib  prefix='s' uri='/struts-tags'%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//ES" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
    <meta name='http-equiv' content='Content-type: text/html; charset=UTF-8'/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <link rel='icon' type='image/gif' href='/admon/images/logo/favicon.png'>
    <!-- DWR -->
    <script src="<s:url value="/dwr/engine.js"/>" type='text/javascript'></script>
    <script src="<s:url value="/dwr/util.js"/>" type='text/javascript'></script>
    <script src="<s:url value="/dwr/interface/RecuperarContrasenaDWR.js"/>" type='text/javascript' ></script>
    <!-- JQuery -->
    <script src="<s:url value="/js/jquerycore/jquery.min.js"/>" type='text/javascript'></script>
    <script src="<s:url value="/js/jquerycore/jquery-ui.min.js"/>" type='text/javascript'></script>
    <!-- JS -->
    <script src="<s:url value="/js/extras/theme.js"/>" type='text/javascript'></script>
    <script src="<s:url value="/js/extras/util.js"/>" type='text/javascript'></script>
    <!--[if lt IE 9]>
    <script src="<s:url value="/js/jquerycomponentes/excanvas.js"/>" type='text/javascript'></script>
    <![endif]-->
    <!-- Tooltip -->
    <link href="<s:url value="/css/jquerycomponentes/jquery.tipped-3.1.8.css"/>" type='text/css' rel='stylesheet'/>
    <script src="<s:url value="/js/jquerycomponentes/jquery.tipped-3.1.8.js"/>" type='text/javascript'></script>
    <script src="<s:url value="/js/extras/tooltip.js"/>" type='text/javascript'></script>
    <!-- Validaciones -->
    <script src="<s:url value="/js/validador/validanguage-1.0.3.js"/>" type="text/javascript"></script>
    <script src="<s:url value="/js/validador/validaciones.js"/>" type="text/javascript"></script>
    <!-- Estilos -->
    <link type='text/css' href="<s:url value="/css/estilos/estilos.css"/>" rel='stylesheet'/>
    <link type='text/css' href="<s:url value="/css/estilos/layout.css"/>" rel='stylesheet'/>
    <link type='text/css' href="<s:url value="/css/estilos/security.css"/>" rel='stylesheet'/>
    <!-- Porto  -->
    <link type='text/css' href='<s:url value='/css/themes/porto/vendor/bootstrap/css/bootstrap.css'/>' rel='stylesheet'/>
    <link type='text/css' href='<s:url value='/css/themes/porto/vendor/font-awesome/css/font-awesome.css'/>' rel='stylesheet'/>
    <link type='text/css' href='<s:url value='/css/themes/porto/vendor/magnific-popup/magnific-popup.css'/>' rel='stylesheet'/>
    <link type='text/css' href='<s:url value='/css/themes/porto/stylesheets/theme.css'/>' rel='stylesheet'/>
    <link type='text/css' href='<s:url value='/css/themes/porto/stylesheets/skins/default.css'/>' rel='stylesheet'/>
    <link type='text/css' href='<s:url value='/css/themes/porto/stylesheets/theme-custom.css'/>' rel='stylesheet'/>
    <link type='text/css' href='<s:url value='/css/themes/porto/vendor/modernizr/modernizr.js'/>' rel='stylesheet'/>
    <!-- JS -->
    <script src='<s:url value='/js/seguridad/recuperarContrasena.js'/>' type='text/javascript'></script>
    <title>
        <s:text name='key_recuperarcontrasena_titulo'/>
    </title>
</head>

<body>

    <!-- Textos de error -->
    <input type='hidden' id='key_recuperarcontrasena_usuariorequeridotitulo' value='<s:text name='key_recuperarcontrasena_usuariorequeridotitulo'/>'/>
    <input type='hidden' id='key_recuperarcontrasena_usuariorequeridomensaje' value='<s:text name='key_recuperarcontrasena_usuariorequeridomensaje'/>'/>
    <input type='hidden' id='key_recuperarcontrasena_oktitulo' value='<s:text name='key_recuperarcontrasena_oktitulo'/>'/>
    <input type='hidden' id='key_recuperarcontrasena_usuariotooltip' value='<s:text name='key_recuperarcontrasena_usuariotooltip'/>'/>

    <!-- JQuery dialogs-->
    <div id='jquery_dialogs' style='display: none'>
        <div id='d-respuesta' title="<s:text name='key_recuperarcontrasena_respuestatitulo'/>">
            <span style='float: left;' class='ui-icon ui-icon-info'></span>
            <div style='float: right; width:90%;' id='respuesta-content'></div>
        </div>
    </div>


    <!-- start: page -->
    <section class="body-sign">
        <div class="center-sign">
            <a href="/" class="logo pull-left">
                <img src="<s:url value='/css/themes/porto/images/logo.png'/>" height="54" alt="" />
            </a>

            <div class="panel panel-sign">
                <div class="panel-title-sign mt-xl text-right">
                    <h2 class="title text-uppercase text-weight-bold m-none">
                        <i class="fa fa-user mr-xs"></i> <s:text name='key_recuperarcontrasena_titulo' />
                    </h2>
                </div>
                <s:form id='recuperarContrasenaform' name='recuperarContrasenaform'>  
                    <div class="panel-body">

                        <!-- Ayuda  -->
                        <div class="alert alert-info">
                            <p class="m-none text-weight-semibold h6"><s:text name='key_recuperarcontrasena_ayuda' /></p>
                        </div>


                        <!-- Usuario -->

                        <div class="form-group mb-none">
                            <div class="input-group">
                                <input type='text' name='usuario' value='' placeholder='Usuario' id='recuperarContrasenaform_usuario' 
                                       class="form-control input-lg">
                                <!-- Solucion COR-127/Bug Struts2 -->
                                <s:textfield name='dummy' cssStyle='display:none;'/>
                                <span class="input-group-btn">
                                    <div id='btnRecuperar' class="btn btn-primary btn-lg">
                                        <s:text name='key_recuperarcontrasena_recuperar'/>
                                    </div>
                                </span>
                            </div>
                        </div>

                        <br>

                        <!-- Link Regresar a Login -->
                        <a href="<s:url value="/jsp/seguridad/login.jsp"/>" class='login-link'>
                            <s:text name='key_recuperarcontrasena_regresaralogin'/>
                        </a>

                    </div>
                </s:form>
            </div>
        </div>
    </section>
    <!-- end: page -->
    <%@ include file='/jsp/decorators/include/pie.jsp'%> 
</body>

