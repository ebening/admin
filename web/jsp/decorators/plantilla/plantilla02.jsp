<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@taglib  prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <link rel='icon' type='image/gif' href='/admon/images/logo/favicon.png'rel="stylesheet"/>
        <!-- Mobile Metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <meta name="http-equiv" content="Content-type: text/html; charset=UTF-8"/>

        <title><decorator:title default="Inicio" /></title>

        <!-- Web Fonts  -->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800|Shadows+Into+Light" rel="stylesheet" type="text/css">

            <script src="<s:url value="/js/jquerycore/jquery.min.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/js/jquerycore/jquery-ui.min.js"/>" type="text/javascript"></script>
            <!-- Tema predeteminado de jquery -->
            <script src="<s:url value="/js/extras/theme.js"/>" type='text/javascript'></script>
            <!-- Tema Porto 
            <link rel="stylesheet" href="<s:url value="/css/themes/porto/vendor/jquery-ui/jquery-ui.css"/>" rel="stylesheet"/>
            <link rel="stylesheet" href="<s:url value="/css/themes/porto/vendor/jquery-ui/jquery-ui.theme.css"/>" rel="stylesheet"/>
            -->
            <link type="text/css" href="<s:url value="/css/estilos/estilosCompatPorto.css"/>" rel="stylesheet"/>

            <!--
            <link type="text/css" href="<s:url value="/css/estilos/estilos.css"/>" rel="stylesheet"/>
            <link type="text/css" href="<s:url value="/css/estilos/security.css"/>" rel="stylesheet"/>
            <link type="text/css" href="<s:url value="/css/estilos/upper.css"/>" rel="stylesheet"/>
            <link type="text/css" href="<s:url value="/css/estilos/layout.css"/>" rel="stylesheet"/>
            -->

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

            <script src="<s:url value="/js/jquerycomponentes/jquery.colorpicker-23.05.2009.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/js/jquerycomponentes/jquery.watermark-1.0.1.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/js/jquerycomponentes/jquery.toastmessage.js"/>" type='text/javascript'></script>

            <script src="<s:url value="/js/validador/validanguage-1.0.3.js"/>" type="text/javascript"></script>

            <script src="<s:url value="/js/extras/funciones.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/js/extras/util.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/js/extras/tooltip.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/js/extras/fade.js"/>" type="text/javascript"></script>

            <script src="<s:url value="/js/validador/validaciones.js"/>" type="text/javascript"></script>

            <!-- DWR -->
            <script src="<s:url value="/dwr/engine.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/dwr/util.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/js/extras/dwrErrorHandler.js"/>" type="text/javascript"></script>

            <!-- Porto  -->
            <link type='text/css' href='<s:url value='/css/themes/porto/vendor/bootstrap/css/bootstrap.css'/>' rel='stylesheet'/>
            <link type='text/css' href='<s:url value='/css/themes/porto/vendor/font-awesome/css/font-awesome.css'/>' rel='stylesheet'/>
            <link type='text/css' href='<s:url value='/css/themes/porto/vendor/magnific-popup/magnific-popup.css'/>' rel='stylesheet'/>
            <link type='text/css' href='<s:url value='/css/themes/porto/stylesheets/theme.css'/>' rel='stylesheet'/>
            <link type='text/css' href='<s:url value='/css/themes/porto/stylesheets/skins/default.css'/>' rel='stylesheet'/>
            <link type='text/css' href='<s:url value='/css/themes/porto/stylesheets/theme-custom.css'/>' rel='stylesheet'/>

            <link type='text/css' href="<s:url value="/css/themes/porto/vendor/bootstrap-multiselect/bootstrap-multiselect.css"/>" rel="stylesheet"/>
            <link type='text/css' href="<s:url value="/css/themes/porto/vendor/morris.js/morris.css"/>" rel="stylesheet"/>

            <script src="<s:url value="/css/themes/porto/vendor/modernizr/modernizr.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/css/themes/porto/vendor/jquery-browser-mobile/jquery.browser.mobile.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/css/themes/porto/vendor/bootstrap/js/bootstrap.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/css/themes/porto/vendor/nanoscroller/nanoscroller.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/css/themes/porto/vendor/magnific-popup/jquery.magnific-popup.js"/>"  type="text/javascript"></script>
            <script src="<s:url value="/css/themes/porto/vendor/jquery-placeholder/jquery-placeholder.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/js/extras/lumino.glyphs.js"/>" type="text/javascript"></script>
            
            <!-- Specific Page Vendor -->
            <script src="<s:url value="/css/themes/porto/vendor/jqueryui-touch-punch/jqueryui-touch-punch.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/css/themes/porto/vendor/jquery-appear/jquery-appear.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/css/themes/porto/vendor/bootstrap-multiselect/bootstrap-multiselect.js"/>" type="text/javascript"></script>

            <script src="<s:url value="/css/themes/porto/vendor/jquery.easy-pie-chart/jquery.easy-pie-chart.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/css/themes/porto/vendor/flot/jquery.flot.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/css/themes/porto/vendor/flot.tooltip/flot.tooltip.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/css/themes/porto/vendor/flot/jquery.flot.pie.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/css/themes/porto/vendor/flot/jquery.flot.categories.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/css/themes/porto/vendor/flot/jquery.flot.resize.js"/>" type="text/javascript"></script>

            <script src="<s:url value="/css/themes/porto/vendor/jquery-sparkline/jquery-sparkline.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/css/themes/porto/vendor/raphael/raphael.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/css/themes/porto/vendor/morris.js/morris.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/css/themes/porto/vendor/gauge/gauge.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/css/themes/porto/vendor/snap.svg/snap.svg.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/css/themes/porto/vendor/liquid-meter/liquid.meter.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/css/themes/porto/vendor/jqvmap/jquery.vmap.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/css/themes/porto/vendor/jqvmap/data/jquery.vmap.sampledata.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/css/themes/porto/vendor/jqvmap/maps/jquery.vmap.world.js"/>" type="text/javascript"></script>

            <script src="<s:url value="/css/themes/porto/vendor/jqvmap/maps/continents/jquery.vmap.africa.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/css/themes/porto/vendor/jqvmap/maps/continents/jquery.vmap.asia.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/css/themes/porto/vendor/jqvmap/maps/continents/jquery.vmap.australia.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/css/themes/porto/vendor/jqvmap/maps/continents/jquery.vmap.europe.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/css/themes/porto/vendor/jqvmap/maps/continents/jquery.vmap.north-america.js"/>" type="text/javascript"></script>
            <script src="<s:url value="/css/themes/porto/vendor/jqvmap/maps/continents/jquery.vmap.south-america.js"/>" type="text/javascript"></script>

            <decorator:head/>
    </head>
    <body>

        <section class="body">
            <!-- Header -->
            <%@ include file='/jsp/decorators/include/encabezadoPorto.jsp'%>

            <!-- Work area -->
            <div class="inner-wrapper">
                <!-- Menu Vertical -->
                <%@ include file='/jsp/decorators/include/menuVerticalIzquierdoPorto.jsp'%>
                <!-- Pantalla dinámica -->
                <section role="main" class="content-body">
                    <header class="page-header">
                        <h2><decorator:title/></h2>
                    </header>
                    <decorator:body/>
                    <!-- Pie -->
                    <br>
                    <%@ include file='/jsp/decorators/include/pie.jsp'%>
                </section>
            </div>
            <%@ include file='/jsp/decorators/include/menuVerticalDerechoPorto.jsp'%>
        </section>
        <!-- Textos Genericos -->    
        <%@ include file='/jsp/textos/generico.jsp'%>

        <!-- Theme Base, Components and Settings -->
        <script src="<s:url value="/css/themes/porto/javascripts/theme.js"/>" type="text/javascript"></script>

        <!-- Theme Custom -->
        <script src="<s:url value="/css/themes/porto/javascripts/theme.custom.js"/>" type="text/javascript"></script>

        <!-- Theme Initialization Files -->
        <script src="<s:url value="/css/themes/porto/javascripts/theme.init.js"/>" type="text/javascript"></script> 
        <script src="<s:url value="/js/admon/menu.js"/>" type="text/javascript"></script> 
    </body>

</html>

