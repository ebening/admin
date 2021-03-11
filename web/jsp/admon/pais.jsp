<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib  prefix='s' uri='/struts-tags'%>
<head>
    <script src='<s:url value='../dwr/interface/PaisDWR.js'/>' type='text/javascript' charset="UTF-8"></script>
    <script src='<s:url value='../js/admon/pais.js'/>' type='text/javascript' charset="UTF-8"></script>
    <title>
        <s:property escape='false' value='nombreActionMenu'/>
    </title>
</head>
<body>
    <div class='form-wrap panel-body'>
        <div class='titulo ui-corner-top'>
            <div class='title-text hidden'><s:property escape='false' value='nombreActionMenu'/></div>
            <div class='button-container'>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-default' id='btnNewPais'><s:text name='key_pais_bnew'/></div>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-primary' id='btnSavePais'><s:text name='key_pais_bsave'/></div>
            </div>
            <div style='clear: both'></div>
        </div>
        <!-- Hidden -->
        <input type='hidden' id='key_pais_title' value='<s:text name='key_pais_title'/>'/>
        <input type='hidden' id='key_pais_modificacionUsuario' value='<s:text name='key_pais_modificacionUsuario'/>'/>
        <input type='hidden' id='key_pais_modificacionFecha' value='<s:text name='key_pais_modificacionFecha'/>'/>
        <input type='hidden' id='paisGridOrderByColumn' value='modificacionFecha'/>
        <input type='hidden' id='paisGridOrderByType' value='des'/>
        <input type='hidden' id='paisGridCurrentPage' value='1'/>
        <input type='hidden' id='paisGridRowsByPage' value='10'/>
        <input type='hidden' id='idFromIframe' value=''/>
        <s:hidden id='gridVisiblePais' name='gridVisiblePais'/>
        <s:hidden id='gridIndividualModePais' name='gridIndividualModePais'/>

        <s:form id='paisform' name='paisform' cssClass='pair-wrap'>
            <div class='pair-hidden '>
                <div class='etiqueta' id='pais_paisIdText'>
                    <s:text name='key_pais_paisid'/>
                </div>
                <s:textfield name='paisId' value='0' cssClass='isNumericInteger' />
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta' id='pais_codeText'>
                    <s:text name='key_pais_code'/>
                </div>
                <s:textfield name='code' maxLength='187' cssClass='textbox form-control '/>
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta' id='pais_nombreText'>
                    <s:text name='key_pais_nombre'/>
                </div>
                <s:textfield name='nombre' maxLength='187' cssClass='textbox form-control '/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='pais_estatusIdText'>
                    <s:text name='key_pais_estatusid'/>
                </div>
                <s:select list='estatusPais' listKey='valor' listValue='nombre' name='estatusId' cssClass='select' />
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='pais_eliminadoIdText'>
                    <s:text name='key_pais_eliminadoid'/>
                </div>
                <s:select list='eliminadoPais' listKey='valor' listValue='nombre' name='eliminadoId' cssClass='select' />
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='pais_creacionFechaText'>
                    <s:text name='key_pais_creacionfecha'/>
                </div>
                <s:textfield name='creacionFecha' cssClass='textbox form-control isDate'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='pais_creacionUsuarioText'>
                    <s:text name='key_pais_creacionusuario'/>
                </div>
                <s:textfield name='creacionUsuario' maxLength='9' cssClass='textbox form-control isNumericInteger'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='pais_modificacionFechaText'>
                    <s:text name='key_pais_modificacionFecha'/>
                </div>
                <s:textfield name='modificacionFecha' cssClass='textbox form-control isDate'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='pais_modificacionUsuarioText'>
                    <s:text name='key_pais_modificacionusuario'/>
                </div>
                <s:textfield name='modificacionUsuario' maxLength='9' cssClass='textbox form-control isNumericInteger'/>
            </div>
            <div style='clear: both'></div>
        </s:form>

        <div class='pair-wrap divisor'></div>
        <div class='grid-container'>
            <table id='paisGrid'></table>
            <div id='paisGridPagerId'></div>
        </div>
        <div class='pair-wrap divisor'></div>
        <br>
    </div>
</body>

