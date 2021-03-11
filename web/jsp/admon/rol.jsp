<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib  prefix='s' uri='/struts-tags'%>
<head>
    <script src='<s:url value='../dwr/interface/SeguridadRolDWR.js'/>' type='text/javascript' charset="UTF-8"></script>
    <script src='<s:url value='../js/admon/rol.js'/>' type='text/javascript' charset="UTF-8"></script>
     <!-- Accesos -->
    <script src='<s:url value='../js/jquerycomponentes/jquery.jstree.js'/>' type='text/javascript'></script>

    <title>
        <s:property escape='false' value='nombreActionMenu'/>
    </title>
</head>
<body>
    <div class='form-wrap panel-body'>
        <div class='titulo ui-corner-top'>
            <div class='title-text hidden'><s:property escape='false' value='nombreActionMenu'/></div>
            <div class='button-container'>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-default' id='btnNewRol'><s:text name='key_rol_bnew'/></div>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-primary' id='btnSaveRol'><s:text name='key_rol_bsave'/></div>
            </div>
            <div style='clear: both'></div>
        </div>
        <!-- Hidden -->
        <input type='hidden' id='key_rol_title' value='<s:text name='key_rol_title'/>'/>
        <input type='hidden' id='key_rol_modificacionUsuario' value='<s:text name='key_rol_modificacionUsuario'/>'/>
        <input type='hidden' id='key_rol_modificacionFecha' value='<s:text name='key_rol_modificacionFecha'/>'/>
        <input type='hidden' id='rolGridOrderByColumn' value='modificacionFecha'/>
        <input type='hidden' id='rolGridOrderByType' value='des'/>
        <input type='hidden' id='rolGridCurrentPage' value='1'/>
        <input type='hidden' id='rolGridRowsByPage' value='10'/>
        <input type='hidden' id='idFromIframe' value=''/>
        <s:hidden id='gridVisibleRol' name='gridVisibleRol'/>
        <s:hidden id='gridIndividualModeRol' name='gridIndividualModeRol'/>
        

        <s:form id='rolform' name='rolform' cssClass='pair-wrap'>
            <div class='pair-hidden '>
                <div class='etiqueta' id='rol_rolIdText'>
                    <s:text name='key_rol_rolid'/>
                </div>
                <s:textfield name='rolId' value='0' cssClass='isNumericInteger' />
            </div>
            <div class='pair col-sm-3 '>
                <span class='etiqueta-small' id='rol_aplicacionIdText'>
                    <s:text name='key_rol_aplicacion'/>
                </span>
                <s:select list='rolAplicacion' listKey='valor' listValue='nombre' name='aplicacionId' cssClass='select form-control ' />
            </div>
            <div class='pair col-sm-3'>
                <div class='etiqueta' id='rol_nombreText'>
                    <s:text name='key_rol_nombre'/>
                </div>
                <s:textfield name='nombre' cssClass='textbox form-control '/>
            </div>
            <div class='pair col-sm-3'>
                <div class='etiqueta' id='rol_nombreinglesText'>
                    <s:text name='key_rol_nombreingles'/>
                </div>
                <s:textfield name='nombreingles' cssClass='textbox form-control '/>
            </div>
            <div class='paid col-sm-3'>
                <div class='etiqueta' id='rol_descripcionText'>
                    <s:text name='key_rol_descripcion'/>
                </div>
                <s:textfield name='descripcion' cssClass='textbox form-control'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='rol_estatusIdText'>
                    <s:text name='key_rol_estatusid'/>
                </div>
                <s:select list='estatusRol' listKey='valor' listValue='nombre' name='estatusId' cssClass='select' />
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='rol_eliminadoIdText'>
                    <s:text name='key_rol_eliminadoid'/>
                </div>
                <s:select list='eliminadoRol' listKey='valor' listValue='nombre' name='eliminadoId' cssClass='select' />
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='rol_creacionFechaText'>
                    <s:text name='key_rol_creacionfecha'/>
                </div>
                <s:textfield name='creacionFecha' cssClass='textbox form-control isDate'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='rol_creacionUsuarioText'>
                    <s:text name='key_rol_creacionusuario'/>
                </div>
                <s:textfield name='creacionUsuario' maxLength='9' cssClass='textbox form-control isNumericInteger'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='rol_modificacionFechaText'>
                    <s:text name='key_rol_modificacionFecha'/>
                </div>
                <s:textfield name='modificacionFecha' cssClass='textbox form-control isDate'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='rol_modificacionUsuarioText'>
                    <s:text name='key_rol_modificacionusuario'/>
                </div>
                <s:textfield name='modificacionUsuario' maxLength='9' cssClass='textbox form-control isNumericInteger'/>
            </div>
            <div style='clear: both'></div>
        </s:form>

        <div class='pair-wrap divisor'></div>
        <div class='grid-container'>
            <table id='rolGrid'></table>
            <div id='rolGridPagerId'></div>
        </div>
        <div class='pair-wrap divisor'></div>
        <br>
        <div class='col-sm-12'>
            <div class='alert alert-info'>
                <div>MÓDULOS,OPERACIONES O FUNCIONALIDADES POR ROL<br> 
                    <div style="font-size: 10px;">Árbol de selección</div></div>
            </div>
            <div class='tree-container panel-body-nopadding border'>
                <div class='accesos-container'>
                    <div class='accesos'></div>
                </div>
                <div class='paginas'>
                </div>
            </div>
        </div>
    </div>
</body>

