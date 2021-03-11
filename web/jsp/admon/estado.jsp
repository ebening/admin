<%@taglib  prefix='s' uri='/struts-tags'%>
<head>
    <script src='<s:url value='../dwr/interface/EstadoDWR.js'/>' type='text/javascript'></script>
    <script src='<s:url value='../js/admon/estado.js'/>' type='text/javascript'></script>
    <title>
        <s:property escape='false' value='nombreActionMenu'/>
    </title>
</head>
<body>
    <div class='form-wrap panel-body'>
        <div class='titulo ui-corner-top'>
            <div class='title-text hidden'><s:property escape='false' value='nombreActionMenu'/></div>
            <div class='button-container'>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-default' id='btnNewEstado'><s:text name='key_estado_bnew'/></div>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-primary' id='btnSaveEstado'><s:text name='key_estado_bsave'/></div>
            </div>
            <div style='clear: both'></div>
        </div>
        <!-- Hidden -->
        <input type='hidden' id='key_estado_title' value='<s:text name='key_estado_title'/>'/>
        <input type='hidden' id='key_estado_modificacionUsuario' value='<s:text name='key_estado_modificacionUsuario'/>'/>
        <input type='hidden' id='key_estado_modificacionFecha' value='<s:text name='key_estado_modificacionFecha'/>'/>
        <input type='hidden' id='estadoGridOrderByColumn' value='modificacionFecha'/>
        <input type='hidden' id='estadoGridOrderByType' value='des'/>
        <input type='hidden' id='estadoGridCurrentPage' value='1'/>
        <input type='hidden' id='estadoGridRowsByPage' value='10'/>
        <input type='hidden' id='idFromIframe' value=''/>
        <s:hidden id='gridVisibleEstado' name='gridVisibleEstado'/>
        <s:hidden id='gridIndividualModeEstado' name='gridIndividualModeEstado'/>

        <s:form id='estadoform' name='estadoform' cssClass='pair-wrap'>
            <div class='pair-hidden '>
                <div class='etiqueta' id='estado_estadoIdText'>
                    <s:text name='key_estado_estadoid'/>
                </div>
                <s:textfield name='estadoId' value='0' cssClass='isNumericInteger' />
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta' id='estado_codeText'>
                    <s:text name='key_estado_code'/>
                </div>
                <s:textfield name='code' maxLength='187' cssClass='textbox form-control '/>
            </div>
            <div class='pair col-sm-4'>
                <span class='etiqueta-small' id='estado_paisIdText'>
                    <s:text name='key_estado_paisid'/>
                </span>
                <s:select list='paisEstado' listKey='paisId' listValue='nombre' name='paisId' cssClass='select form-control ' />
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta' id='estado_nombreText'>
                    <s:text name='key_estado_nombre'/>
                </div>
                <s:textfield name='nombre' maxLength='187' cssClass='textbox form-control '/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='estado_estatusIdText'>
                    <s:text name='key_estado_estatusid'/>
                </div>
                <s:select list='estatusEstado' listKey='valor' listValue='nombre' name='estatusId' cssClass='select' />
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='estado_eliminadoIdText'>
                    <s:text name='key_estado_eliminadoid'/>
                </div>
                <s:select list='eliminadoEstado' listKey='valor' listValue='nombre' name='eliminadoId' cssClass='select' />
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='estado_creacionFechaText'>
                    <s:text name='key_estado_creacionfecha'/>
                </div>
                <s:textfield name='creacionFecha' cssClass='textbox form-control  isDate'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='estado_creacionUsuarioText'>
                    <s:text name='key_estado_creacionusuario'/>
                </div>
                <s:textfield name='creacionUsuario' maxLength='9' cssClass='textbox form-control  isNumericInteger'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='estado_modificacionFechaText'>
                    <s:text name='key_estado_modificacionFecha'/>
                </div>
                <s:textfield name='modificacionFecha' cssClass='textbox form-control  isDate'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='estado_modificacionUsuarioText'>
                    <s:text name='key_estado_modificacionusuario'/>
                </div>
                <s:textfield name='modificacionUsuario' maxLength='9' cssClass='textbox form-control  isNumericInteger'/>
            </div>
            <div style='clear: both'></div>
        </s:form>

        <div class='pair-wrap divisor'></div>
        <div class='grid-container'>
            <table id='estadoGrid'></table>
            <div id='estadoGridPagerId'></div>
        </div>
        <div class='pair-wrap divisor'></div>
        <br>
    </div>
</body>

