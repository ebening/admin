<%@taglib  prefix='s' uri='/struts-tags'%>
<head>
    <script src='<s:url value='../dwr/interface/ClasificacionContraDWR.js'/>' type='text/javascript'></script>
    <script src='<s:url value='../js/admon/clasificacionContra.js'/>' type='text/javascript'></script>
    <title>
        <s:property escape='false' value='nombreActionMenu'/>
    </title>
</head>
<body>
    <div class='form-wrap panel-body'>
        <div class='titulo ui-corner-top'>
            <div class='title-text hidden'><s:property escape='false' value='nombreActionMenu'/></div>
            <div class='button-container'>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-default' id='btnNewClasificacionContra'><s:text name='key_clasificacioncontra_bnew'/></div>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-primary' id='btnSaveClasificacionContra'><s:text name='key_clasificacioncontra_bsave'/></div>
            </div>
            <div style='clear: both'></div>
        </div>
        <!-- Hidden -->
        <input type='hidden' id='key_clasificacioncontra_title' value='<s:text name='key_clasificacioncontra_title'/>'/>
        <input type='hidden' id='key_clasificacioncontra_modificacionUsuario' value='<s:text name='key_clasificacioncontra_modificacionUsuario'/>'/>
        <input type='hidden' id='key_clasificacioncontra_modificacionFecha' value='<s:text name='key_clasificacioncontra_modificacionFecha'/>'/>
        <input type='hidden' id='clasificacionContraGridOrderByColumn' value='modificacionFecha'/>
        <input type='hidden' id='clasificacionContraGridOrderByType' value='des'/>
        <input type='hidden' id='clasificacionContraGridCurrentPage' value='1'/>
        <input type='hidden' id='clasificacionContraGridRowsByPage' value='10'/>
        <input type='hidden' id='idFromIframe' value=''/>
        <s:hidden id='gridVisibleClasificacionContra' name='gridVisibleClasificacionContra'/>
        <s:hidden id='gridIndividualModeClasificacionContra' name='gridIndividualModeClasificacionContra'/>

        <s:form id='clasificacionContraform' name='clasificacionContraform' cssClass='pair-wrap'>
            <div class='pair-hidden '>
                <div class='etiqueta' id='clasificacionContra_clasificacionContraIdText'>
                    <s:text name='key_clasificacioncontra_clasificacioncontraid'/>
                </div>
                <s:textfield name='clasificacionContraId' value='0' cssClass='isNumericInteger' />
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta' id='clasificacionContra_nombreText'>
                    <s:text name='key_clasificacioncontra_nombre'/>
                </div>
                <s:textfield name='nombre' maxLength='187' cssClass='textbox form-control'/>
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta' id='clasificacionContra_ordenText'>
                    <s:text name='key_clasificacioncontra_orden'/>
                </div>
                <s:textfield name='orden' maxLength='20' cssClass='textbox form-control isNumericInteger'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='clasificacionContra_estatusIdText'>
                    <s:text name='key_clasificacioncontra_estatusid'/>
                </div>
                <s:select list='estatusClasificacionContra' listKey='valor' listValue='nombre' name='estatusId' cssClass='select form-control' />
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='clasificacionContra_eliminadoIdText'>
                    <s:text name='key_clasificacioncontra_eliminadoid'/>
                </div>
                <s:select list='eliminadoClasificacionContra' listKey='valor' listValue='nombre' name='eliminadoId' cssClass='select form-control' />
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='clasificacionContra_creacionFechaText'>
                    <s:text name='key_clasificacioncontra_creacionfecha'/>
                </div>
                <s:textfield name='creacionFecha' cssClass='textbox isDate'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='clasificacionContra_creacionUsuarioText'>
                    <s:text name='key_clasificacioncontra_creacionusuario'/>
                </div>
                <s:textfield name='creacionUsuario' maxLength='9' cssClass='textbox form-control isNumericInteger'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='clasificacionContra_modificacionFechaText'>
                    <s:text name='key_clasificacioncontra_modificacionFecha'/>
                </div>
                <s:textfield name='modificacionFecha' cssClass='textbox isDate'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='clasificacionContra_modificacionUsuarioText'>
                    <s:text name='key_clasificacioncontra_modificacionusuario'/>
                </div>
                <s:textfield name='modificacionUsuario' maxLength='9' cssClass='textbox form-control isNumericInteger'/>
            </div>
            <div style='clear: both'></div>
        </s:form>

        <div class='pair-wrap divisor'></div>
        <div class='grid-container'>
            <table id='clasificacionContraGrid'></table>
            <div id='clasificacionContraGridPagerId'></div>
        </div>
        <div class='pair-wrap divisor'></div>
        <br>
    </div>
</body>

