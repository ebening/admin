<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib  prefix='s' uri='/struts-tags'%>
<head>
    <script src='<s:url value='../dwr/interface/SeguridadRolRestriccionParDWR.js'/>' type='text/javascript' charset="UTF-8"></script>
    <script src='<s:url value='../js/admon/restriccion.js'/>' type='text/javascript' charset="UTF-8"></script>
    <title>
        <s:property escape='false' value='nombreActionMenu'/>
    </title>
</head>
<body>
    <div class='form-wrap panel-body'>
        <div class='titulo ui-corner-top'>
            <div class='title-text hidden'><s:property escape='false' value='nombreActionMenu'/></div>
            <div class='button-container'>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-default' id='btnNewRestriccion'><s:text name='key_restriccion_bnew'/></div>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-primary' id='btnSaveRestriccion'><s:text name='key_restriccion_bsave'/></div>
            </div>
            <div style='clear: both'></div>
        </div>
        <!-- Hidden -->
        <input type='hidden' id='key_restriccion_title' value='<s:text name='key_restriccion_title'/>'/>
        <input type='hidden' id='key_restriccion_modificacionUsuario' value='<s:text name='key_restriccion_modificacionUsuario'/>'/>
        <input type='hidden' id='key_restriccion_modificacionFecha' value='<s:text name='key_restriccion_modificacionFecha'/>'/>
        <input type='hidden' id='catalogoGridOrderByColumn' value='modificacionFecha'/>
        <input type='hidden' id='catalogoGridOrderByType' value='des'/>
        <input type='hidden' id='catalogoGridCurrentPage' value='1'/>
        <input type='hidden' id='catalogoGridRowsByPage' value='10'/>
        <input type='hidden' id='idFromIframe' value=''/>
        <s:hidden id='gridVisibleCatalogo' name='gridVisibleCatalogo'/>
        <s:hidden id='gridIndividualModeCatalogo' name='gridIndividualModeCatalogo'/>

        <s:form id='restriccionform' name='restriccionform' cssClass='pair-wrap'>
            <div class='pair-hidden '>
                <div class='etiqueta' id='restriccion_restriccionIdText'>
                    <s:text name='key_restriccion_restriccionid'/>
                </div>
                <s:textfield name='rolId' value='0' cssClass='isNumericInteger' />
            </div>
            <div class='pair col-sm-3 '>
                <span class='etiqueta-small' id='rol_aplicacionIdText'>
                    <s:text name='key_restriccion_aplicacion'/>
                </span>
                <s:select list='aplicacion' listKey='valor' listValue='nombre' name='aplicacionId' cssClass='select form-control ' />
            </div>
            <div class='pair col-sm-3 '>
                <span class='etiqueta-small' id='restriccion_moduloIdText'>
                    <s:text name='key_restriccion_modulo'/>
                </span>
                <s:select list='modulo' listKey='moduloId' listValue='nombre' name='moduloId' cssClass='select form-control ' />
            </div>
            <div class='pair col-sm-3 '>
                <span class='etiqueta-small' id='restriccion_rolIdText'>
                    <s:text name='key_restriccion_listaRol'/>
                </span>
                <s:select list='roles' listKey='rolId' listValue='nombre' name='rolesRolId' cssClass='select form-control ' />
            </div>
            <div class='pair col-sm-3 '>
                <span class='etiqueta-small' id='restriccion_catalogoIdText'>
                    <s:text name='key_restriccion_catalogo'/>
                </span>
                <s:select list='catalogo' listKey='catalogoId' listValue='nombre' name='catalogoId' cssClass='select form-control ' />
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='restriccion_creacionFechaText'>
                    <s:text name='key_restriccion_creacionfecha'/>
                </div>
                <s:textfield name='creacionFechaRestriccion' cssClass='textbox form-control isDate'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='restriccion_creacionUsuarioText'>
                    <s:text name='key_restriccion_creacionusuario'/>
                </div>
                <s:textfield name='creacionUsuarioRestriccion' maxLength='9' cssClass='textbox form-control isNumericInteger'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='restriccion_modificacionUsuarioText'>
                    <s:text name='key_restriccion_modificacionusuario'/>
                </div>
                <s:textfield name='modificacionUsuarioRestriccion' maxLength='9' cssClass='textbox form-control isNumericInteger'/>
            </div>
            <div style='clear: both'></div>
        </s:form>
        <div class='pair-wrap divisor'></div>
        <div class='grid-container'>
            <table id='catalogoGrid'></table>
            <div id='catalogoGridPagerId'></div>
        </div>
    </div>
</body>

