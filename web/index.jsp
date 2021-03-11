<%@taglib  prefix="s" uri="/struts-tags"%>
<link href="<s:url value="/css/jquerycomponentes/jquery.jqplot.min.css"/>" type="text/css" rel="stylesheet"/>
<script src="<s:url value="/js/jquerycomponentes/jquery.jqplot.min.js"/>" type="text/javascript"></script>
<script src="<s:url value="/js/jquerycomponentes/jqplot.pieRenderer.min.js"/>" type="text/javascript"></script>
<%@page import="com.admon.model.util.SessionVar"%>
<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%SessionVar sv = (SessionVar) session.getAttribute("SESSION_PORTAL");%>
<html>
    <head>
        <title>Inicio</title>
    </head>
    <body>
        <div class='form-wrap panel-body'>
            <br><br><br><br><br>
            <br><br><br><br><br> 
        </div>
    </body>
</html>

