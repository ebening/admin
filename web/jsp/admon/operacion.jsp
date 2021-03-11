<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib  prefix='s' uri='/struts-tags'%>
<head>
    <link type="text/css" href="<s:url value="../css/customcss/custom-css-jqgrid.css"/>" rel="stylesheet"/>
    <script src='<s:url value='../dwr/interface/OperacionDWR.js'/>' type='text/javascript' charset="UTF-8"></script>
    <script src='<s:url value='../js/admon/operacion.js'/>' type='text/javascript' charset="UTF-8"></script>
    <title>
        <s:property escape='false' value='nombreActionMenu'/>
    </title>
</head>
<body>
    <div class='form-wrap panel-body'>
        <div class='titulo ui-corner-top'>
            <div class='title-text hidden'><s:property escape='false' value='nombreActionMenu'/></div>
            <div class='button-container'>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-default' id='btnNewOperacion'><s:text name='operacion.new'/></div>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-primary' id='btnSaveOperacion'><s:text name='operacion.save'/></div>
            </div>
            <div style='clear: both'></div>
        </div>
        <!-- Hidden -->
        <input type='hidden' id='operacionId'/>
        <input type='hidden' id='operacion_operacionId' value='<s:text name='operacion.operacionId'/>'/>
        <input type='hidden' id='operacion_aplicacionId' value='<s:text name='operacion.aplicacionId'/>'/>
        <input type='hidden' id='operacion_aplicacion' value='<s:text name='operacion.aplicacion'/>'/>
        <input type='hidden' id='operacion_moduloId' value='<s:text name='operacion.moduloId'/>'/>
        <input type='hidden' id='operacion_modulo' value='<s:text name='operacion.modulo'/>'/>
        <input type='hidden' id='operacion_orden' value='<s:text name='operacion.orden'/>'/>
        <input type='hidden' id='operacion_nombreES' value='<s:text name='operacion.nombreES'/>'/>
        <input type='hidden' id='operacion_nombreEN' value='<s:text name='operacion.nombreEN'/>'/>
        <input type='hidden' id='operacion_descripcion' value='<s:text name='operacion.descripcion'/>'/>
        <input type='hidden' id='operacion_nombreAction' value='<s:text name='operacion.nombreAction'/>'/>
        <input type='hidden' id='operacion_html' value='<s:text name='operacion.html'/>'/>
        <input type='hidden' id='operacion_url' value='<s:text name='operacion.url'/>'/>
        <input type='hidden' id='operacion_modificar' value='<s:text name='operacion.modificar'/>'/>
        <input type='hidden' id='operacion_eliminar' value='<s:text name='operacion.eliminar'/>'/>
        <input type='hidden' id='operacion_activo' value='<s:text name='operacion.activo'/>'/>
        <input type='hidden' id='operacion_fechaModificacion' value='<s:text name='operacion.fechaModificacion'/>'/>
        <input type='hidden' id='operacion_usuarioModificacion' value='<s:text name='operacion.usuarioModificacion'/>'/>
        <s:hidden id='gridVisibleOperacion' name='gridVisibleOperacion'/>
        <input type='hidden' id='operacionGridOrderByColumn' value='modificacionFecha'/>
        <input type='hidden' id='operacionGridOrderByType' value='des'/>
        <input type='hidden' id='operacionGridCurrentPage' value='1'/>
        <input type='hidden' id='operacionGridRowsByPage' value='10'/>
        
        <s:form id='operacionform' name='operacionform' cssClass='pair-wrap'>
            <div class='pair col-sm-4'>
                <div class='etiqueta'>
                    <s:text name='operacion.aplicacion'/>
                </div>
                <s:select list='aplicaciones' listKey='aplicacionId' listValue='nombre' 
                	name='aplicacionId' id="aplicacionId" cssClass='select form-control' onchange="operacionJS.getModulos();" />
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta'>
                    <s:text name='operacion.modulo'/>
                </div>
                <s:select list='modulos' listKey='moduloId' listValue='nombre' id="moduloId" name='moduloId' cssClass='select form-control' />
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta'>
                    <s:text name='operacion.orden'/>
                </div>
                <s:textfield name='orden' maxLength='5' cssClass='textbox form-control isNumericInteger'/>
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta'>
                    <s:text name='operacion.nombreES'/>
                </div>
                <s:textfield name='nombreES' cssClass='textbox form-control'/>
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta'>
                    <s:text name='operacion.nombreEN'/>
                </div>
                <s:textfield name='nombreEN' cssClass='textbox form-control'/>
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta'>
                    <s:text name='operacion.descripcion'/>
                </div>
                <s:textfield name='descripcion' cssClass='textbox form-control'/>
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta'>
                    <s:text name='operacion.nombreAction'/>
                </div>
                <s:textfield name='nombreAccion' cssClass='textbox form-control'/>
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta'>
                    <s:text name='operacion.html'/>
                </div>
                <s:textfield name='htmlId' cssClass='textbox form-control'/>
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta'>
                    <s:text name='operacion.url'/>
                </div>
                <s:textfield name='url' cssClass='textbox form-control'/>
            </div>
            <div style='clear: both'></div>
        </s:form>

        <div class='pair-wrap divisor'></div>
        <div class='grid-container'>
            <table id='operacionGrid'></table>
            <div id='operacionGridPagerId'></div>
        </div>
        <div class='pair-wrap divisor'></div>
        <br>
    </div>
</body>

