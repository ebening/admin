<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@taglib  prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <link rel='icon' type='image/gif' href='/admon/images/logo/favicon.png'>
            <meta name="http-equiv" content="Content-type: text/html; charset=UTF-8"/>

            <title><decorator:title default="Inicio" /></title>

            <script src="<s:url value="/js/jquerycore/jquery.min.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/js/jquerycore/jquery-ui.min.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/js/extras/theme.js"/>" type='text/javascript'></script>

            <link type="text/css" href="<s:url value="/css/estilos/estilos.css"/>" rel="stylesheet"/>
            <link type="text/css" href="<s:url value="/css/estilos/security.css"/>" rel="stylesheet"/>
            <link type="text/css" href="<s:url value="/css/estilos/upper.css"/>" rel="stylesheet"/>
            <link type="text/css" href="<s:url value="/css/estilos/layout.css"/>" rel="stylesheet"/>
            <!-- Estilos SB  -->
            <link type='text/css' href="<s:url value="/css/estilos/bootstrap.min.css"/>" rel='stylesheet'/>
            <!-- Removido -->
            <!--<link type='text/css' href="<s:url value="/css/estilos/datepicker3.css"/>" rel='stylesheet'/>-->
            <link type='text/css' href="<s:url value="/css/estilos/styles.css"/>" rel='stylesheet'/>

            <!--[if lt IE 9]>
            <script src="<s:url value="/js/jquerycomponentes/excanvas.js"/>" type="text/javascript"></script>
            <![endif]-->
            <link href="<s:url value="/css/jquerycomponentes/jquery.timepicker-1.4.css"/>" type="text/css" rel="stylesheet"/>
            <link href="<s:url value="/css/jquerycomponentes/jquery.tipped-3.1.8.css"/>" type="text/css" rel="stylesheet"/>
            <link href="<s:url value="/css/jquerycomponentes/jquery.jqGrid-4.5.2.css"/>" type="text/css" rel="stylesheet"/>
            <link href="<s:url value="/css/jquerycomponentes/jquery.colorpicker-23.05.2009.css"/>" type="text/css" rel="stylesheet"/>
            <link href="<s:url value="/css/jquerycomponentes/jquery.toastmessage.css"/>" type='text/css' rel='stylesheet'/>
            <script src="<s:url value="/js/jquerycomponentes/jquery.jqGrid-4.5.2-locale-es.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/js/jquerycomponentes/jquery.jqGrid-4.5.2.min.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/js/jquerycomponentes/jquery.ui.datepicker-locale-es.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/js/jquerycomponentes/jquery.timepicker-1.4.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/js/jquerycomponentes/jquery.tipped-3.1.8.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/js/jquerycomponentes/jquery.maskedinput-1.3.1.min.js"/>" type="text/javascript"></script>
            <!-- Removido -->
            <!--
            <link href="<s:url value="/css/jquerycomponentes/jquery.jqGrid-toolbarMenu-3.0-plugin.css"/>" type="text/css" rel="stylesheet"/>
            <script src="<s:url value="/js/jquerycomponentes/jquery.jqGrid-toolbarMenu-3.0-plugin-min.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/js/jquerycomponentes/jquery.tinysort-1.5.3.js"/>" type="text/javascript"></script>
            -->
            <script src="<s:url value="/js/jquerycomponentes/jquery.colorpicker-23.05.2009.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/js/jquerycomponentes/jquery.watermark-1.0.1.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/js/jquerycomponentes/jquery.toastmessage.js"/>" type='text/javascript'></script>

            <script src="<s:url value="/js/validador/validanguage-1.0.3.js"/>" type="text/javascript"></script>

            <script src="<s:url value="/js/extras/funciones.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/js/extras/util.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/js/extras/tooltip.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/js/extras/fade.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/js/extras/bootstrap.js"/>" type="text/javascript"></script>
            <!-- Removido -->
            <!--<script src="<s:url value="/js/extras/bootstrap-datepicker.js"/>" type="text/javascript"></script>-->
            <script src="<s:url value="/js/extras/lumino.glyphs.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/js/validador/validaciones.js"/>" type="text/javascript"></script>
            <!-- Para iconos -->
            <link type='text/css' href="<s:url value="/font/font-awesome-4.7.0/css/font-awesome.min.css"/>" rel='stylesheet'/>

            <!-- DWR -->
            <script src="<s:url value="/dwr/engine.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/dwr/util.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/js/extras/dwrErrorHandler.js"/>" type="text/javascript"></script>

            <decorator:head/>
    </head>
    <body>

        <!-- Textos Genericos -->    
        <%@ include file='/jsp/textos/generico.jsp'%>

        <div class='view' style='visibility: hidden'>
            <div class='ui-corner-all'>
                <%@ include file='/jsp/decorators/include/encabezado.jsp'%>
            </div>
            <div class='middle contenedor-sombra ui-corner-all'>
                <div class="sidebar-left">
                    <%@ include file='/jsp/decorators/include/menuVertical.jsp'%>      
                </div>
                <div class="sidebar-right">
                    <decorator:body/>
                </div>
                <div class="clear-both"></div>
            </div>
            <%@ include file='/jsp/decorators/include/pie.jsp'%>
        </div>
    </body>
</html>

