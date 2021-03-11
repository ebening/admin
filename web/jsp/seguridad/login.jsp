<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@taglib  prefix='s' uri='/struts-tags'%>

<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//ES' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>
<head>
    <meta name='http-equiv' content='Content-type: text/html; charset=UTF-8'/>
    <link rel='icon' type='image/gif' href='/admon/images/logo/favicon.png'>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <!-- DWR -->
    <script src='<s:url value='/dwr/engine.js'/>' type='text/javascript'></script>
    <script src='<s:url value='/dwr/util.js'/>' type='text/javascript'></script>
    <script src='<s:url value='/dwr/interface/LoginDWR.js'/>' type='text/javascript' ></script>
    <!-- JQuery -->
    <script src='<s:url value='/js/jquerycore/jquery.min.js'/>' type='text/javascript'></script>
    <script src='<s:url value='/js/jquerycore/jquery-ui.min.js'/>' type='text/javascript'></script>
    <!-- JS -->
    <script src='<s:url value='/js/extras/theme.js'/>' type='text/javascript'></script>
    <script src='<s:url value='/js/extras/util.js'/>' type='text/javascript'></script>
    <!--[if lt IE 9]>
    <script src='<s:url value='/js/jquerycomponentes/excanvas.js'/>' type='text/javascript'></script>
    <![endif]-->
    <!-- Tooltip -->
    <link href='<s:url value='/css/jquerycomponentes/jquery.tipped-3.1.8.css'/>' type='text/css' rel='stylesheet'/>
    <script src='<s:url value='/js/jquerycomponentes/jquery.tipped-3.1.8.js'/>' type='text/javascript'></script>
    <script src='<s:url value='/js/extras/tooltip.js'/>' type='text/javascript'></script>
    <!-- Validaciones -->
    <script src='<s:url value='/js/validador/validanguage-1.0.3.js'/>' type='text/javascript'></script>
    <script src='<s:url value='/js/validador/validaciones.js'/>' type='text/javascript'></script>
    <!-- Estilos -->
    <link type='text/css' href='<s:url value='/css/estilos/estilos.css'/>' rel='stylesheet'/>
    <link type='text/css' href='<s:url value='/css/estilos/layout.css'/>' rel='stylesheet'/>
    <link type='text/css' href='<s:url value='/css/estilos/security.css'/>' rel='stylesheet'/>
    <!-- Porto  -->
    <link type='text/css' href='<s:url value='/css/themes/porto/vendor/bootstrap/css/bootstrap.css'/>' rel='stylesheet'/>
    <link type='text/css' href='<s:url value='/css/themes/porto/vendor/font-awesome/css/font-awesome.css'/>' rel='stylesheet'/>
    <link type='text/css' href='<s:url value='/css/themes/porto/vendor/magnific-popup/magnific-popup.css'/>' rel='stylesheet'/>
    <link type='text/css' href='<s:url value='/css/themes/porto/stylesheets/theme.css'/>' rel='stylesheet'/>
    <link type='text/css' href='<s:url value='/css/themes/porto/stylesheets/skins/default.css'/>' rel='stylesheet'/>
    <link type='text/css' href='<s:url value='/css/themes/porto/stylesheets/theme-custom.css'/>' rel='stylesheet'/>
    <link type='text/css' href='<s:url value='/css/themes/porto/vendor/modernizr/modernizr.js'/>' rel='stylesheet'/>
    <!-- JS -->
    <script src='<s:url value='/js/seguridad/loginAlternativo.js'/>' type='text/javascript'></script>
    <title>
        <s:text name='key_login_titulo'/>
    </title>
</head>

<body>

    <!-- Textos de error -->
    <input type='hidden' id='key_login_loginerrortitulo' value='<s:text name='key_login_loginerrortitulo'/>'/>
    <input type='hidden' id='key_login_loginerrormensaje' value='<s:text name='key_login_loginerrormensaje'/>'/>
    <input type='hidden' id='key_login_usuariorequeridotitulo' value='<s:text name='key_login_usuariorequeridotitulo'/>'/>
    <input type='hidden' id='key_login_usuariorequeridomensaje' value='<s:text name='key_login_usuariorequeridomensaje'/>'/>
    <input type='hidden' id='key_login_contrasenarequeridatitulo' value='<s:text name='key_login_contrasenarequeridatitulo'/>'/>
    <input type='hidden' id='key_login_contrasenarequeridamensaje' value='<s:text name='key_login_contrasenarequeridamensaje'/>'/>
    <input type='hidden' id='key_login_usuariotooltip' value='<s:text name='key_login_usuariotooltip'/>'/>
    <input type='hidden' id='key_login_contrasenatooltip' value='<s:text name='key_login_contrasenatooltip'/>'/>

    <!-- Textos Genericos -->    
    <%@ include file='/jsp/textos/generico.jsp'%>



    <!-- start: page -->
    <section class="body-sign">
        <div class="center-sign">
            <a href="" class="logo pull-left">
                <img src="<s:url value='/css/themes/porto/images/logo.png'/>" height="54" alt="" />
            </a>

            <div class="panel panel-sign">
                <div class="panel-title-sign mt-xl text-right">
                    <h2 class="title text-uppercase text-weight-bold m-none">
                        <i class="fa fa-user mr-xs"></i> <s:text name='key_login_titulo' />
                    </h2>
                </div>
                <s:form id='loginform' name='loginform'>  
                    <div class="panel-body">
                        <div class="form-group mb-lg">
                            <label><s:text name='key_login_usuario'/></label>
                            <div class="input-group input-group-icon">
                                <input name="username" type="text" 
                                       id='loginform_usuario' 
                                       class="form-control input-lg" />
                                <span class="input-group-addon">
                                    <span class="icon icon-lg">
                                        <i class="fa fa-user"></i>
                                    </span>
                                </span>
                            </div>
                        </div>

                        <div class="form-group mb-lg">
                            <div class="clearfix">
                                <label class="pull-left"><s:text name='key_login_contrasena'/></label>
                                <a href='<s:url value='/jsp/seguridad/recuperarContrasena.jsp'/>' 
                                   class='login-link pull-right'><s:text name='key_login_recuperarcontrasena'/></a>
                            </div>
                            <div class="input-group input-group-icon">
                                <input name="pwd" type="password" 
                                       id='loginform_contrasena' 
                                       class="form-control input-lg" />
                                <span class="input-group-addon">
                                    <span class="icon icon-lg">
                                        <i class="fa fa-lock"></i>
                                    </span>
                                </span>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-sm-8">
                            </div>
                            <div class="col-sm-4 text-right">
                                <div class="btnEntrar btn btn-primary hidden-xs"><s:text name='key_login_entrar'/></div>
                                <div class="btnEntrar btn btn-primary btn-block btn-lg visible-xs mt-lg"><s:text name='key_login_entrar'/></div>
                            </div>
                        </div>
                    </div>
                </s:form>
            </div>
        </div>
    </section>
    <!-- end: page -->

    <!-- JQuery dialogs-->
    <div id='jquery_dialogs'></div>

    <%@ include file='/jsp/decorators/include/pie.jsp'%> 
</body>

