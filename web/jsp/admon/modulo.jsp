<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib  prefix='s' uri='/struts-tags'%>
<head>
    <link type="text/css" href="<s:url value="../css/customcss/custom-css-jqgrid.css"/>" rel="stylesheet"/>
    <script src='<s:url value='../dwr/interface/ModuloDWR.js'/>' type='text/javascript' charset="UTF-8"></script>
    <script src='<s:url value='../js/admon/modulo.js'/>' type='text/javascript' charset="UTF-8"></script>
    <title>
        <s:property escape='false' value='nombreActionMenu'/>
    </title>
    
</head>
<body>
    <div class='form-wrap panel-body'>
        <div class='titulo ui-corner-top'>
            <div class='title-text hidden'><s:property escape='false' value='nombreActionMenu'/></div>
            <div class='button-container'>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-default' id='btnNewModulo'><s:text name='modulo.new'/></div>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-primary' id='btnSaveModulo'><s:text name='modulo.save'/></div>
            </div>
            <div style='clear: both'></div>
        </div>
        <!-- Hidden -->
        <input type='hidden' id='moduloId'/>
        <input type='hidden' id='modulo_moduloId' value='<s:text name='modulo.moduloId'/>'/>
        <input type='hidden' id='modulo_aplicacionId' value='<s:text name='modulo.aplicacionId'/>'/>
        <input type='hidden' id='modulo_aplicacion' value='<s:text name='modulo.aplicacion'/>'/>
        <input type='hidden' id='modulo_orden' value='<s:text name='modulo.orden'/>'/>
        <input type='hidden' id='modulo_nombreES' value='<s:text name='modulo.nombreES'/>'/>
        <input type='hidden' id='modulo_nombreEN' value='<s:text name='modulo.nombreEN'/>'/>
        <input type='hidden' id='modulo_descripcion' value='<s:text name='modulo.descripcion'/>'/>
        <input type='hidden' id='modulo_nombreAction' value='<s:text name='modulo.nombreAction'/>'/>
        <input type='hidden' id='modulo_html' value='<s:text name='modulo.html'/>'/>
        <input type='hidden' id='modulo_url' value='<s:text name='modulo.url'/>'/>
        <input type='hidden' id='modulo_modificar' value='<s:text name='modulo.modificar'/>'/>
        <input type='hidden' id='modulo_eliminar' value='<s:text name='modulo.eliminar'/>'/>
        <input type='hidden' id='modulo_activo' value='<s:text name='modulo.activo'/>'/>
        <input type='hidden' id='modulo_fechaModificacion' value='<s:text name='modulo.fechaModificacion'/>'/>
        <input type='hidden' id='modulo_usuarioModificacion' value='<s:text name='modulo.usuarioModificacion'/>'/>
        <s:hidden id='gridVisibleModulo' name='gridVisibleModulo'/>
        <input type='hidden' id='moduloGridOrderByColumn' value='modificacionFecha'/>
        <input type='hidden' id='moduloGridOrderByType' value='des'/>
        <input type='hidden' id='moduloGridCurrentPage' value='1'/>
        <input type='hidden' id='moduloGridRowsByPage' value='10'/>
        
        <s:form id='moduloform' name='moduloform' cssClass='pair-wrap'>
            <div class='pair col-sm-4'>
                <div class='etiqueta'>
                    <s:text name='modulo.aplicacion'/>
                </div>
                <s:select list='aplicaciones' listKey='aplicacionId' listValue='nombre' 
                	name='aplicacionId' id="aplicacionId" cssClass='select form-control' />
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta'>
                    <s:text name='modulo.orden'/>
                </div>
                <s:textfield name='orden' maxLength='5' cssClass='textbox form-control isNumericInteger'/>
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta'>
                    <s:text name='modulo.nombreES'/>
                </div>
                <s:textfield name='nombreES' cssClass='textbox form-control'/>
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta'>
                    <s:text name='modulo.nombreEN'/>
                </div>
                <s:textfield name='nombreEN' cssClass='textbox form-control'/>
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta'>
                    <s:text name='modulo.descripcion'/>
                </div>
                <s:textfield name='descripcion' cssClass='textbox form-control'/>
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta'>
                    <s:text name='modulo.nombreAction'/>
                </div>
                <s:textfield name='nombreAccion' cssClass='textbox form-control'/>
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta'>
                    <s:text name='modulo.nombreObjeto'/>
                </div>
                <s:textfield name='nombreObjeto' cssClass='textbox form-control'/>
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta'>
                    <s:text name='modulo.html'/>
                </div>
                <s:textfield name='htmlId' cssClass='textbox form-control'/>
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta'>
                    <s:text name='modulo.url'/>
                </div>
                <s:textfield name='url' cssClass='textbox form-control'/>
            </div>
            <div style='clear: both'></div>
        </s:form>

        <div class='pair-wrap divisor'></div>
        <div class='grid-container'>
            <table id='moduloGrid'></table>
            <div id='moduloGridPagerId'></div>
        </div>
        <div class='pair-wrap divisor'></div>
        <br>
    </div>
</body>

