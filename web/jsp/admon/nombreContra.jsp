<%@taglib  prefix='s' uri='/struts-tags'%>
<head>
    <script src='<s:url value='../dwr/interface/NombreContraDWR.js'/>' type='text/javascript'></script>
    <script src='<s:url value='../js/admon/nombreContra.js'/>' type='text/javascript'></script>
    <title>
        <s:property escape='false' value='nombreActionMenu'/>
    </title>
</head>
<body>
    <div class='form-wrap panel-body'>
        <div class='titulo ui-corner-top'>
            <div class='title-text hidden'><s:property escape='false' value='nombreActionMenu'/></div>
            <div class='button-container'>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-default' id='btnNewNombreContra'><s:text name='key_nombrecontra_bnew'/></div>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-primary' id='btnSaveNombreContra'><s:text name='key_nombrecontra_bsave'/></div>
            </div>
            <div style='clear: both'></div>
        </div>
        <!-- Hidden -->
        <input type='hidden' id='key_nombrecontra_title' value='<s:text name='key_nombrecontra_title'/>'/>
        <input type='hidden' id='key_nombrecontra_modificacionUsuario' value='<s:text name='key_nombrecontra_modificacionUsuario'/>'/>
        <input type='hidden' id='key_nombrecontra_modificacionFecha' value='<s:text name='key_nombrecontra_modificacionFecha'/>'/>
        <input type='hidden' id='nombreContraGridOrderByColumn' value='modificacionFecha'/>
        <input type='hidden' id='nombreContraGridOrderByType' value='des'/>
        <input type='hidden' id='nombreContraGridCurrentPage' value='1'/>
        <input type='hidden' id='nombreContraGridRowsByPage' value='10'/>
        <input type='hidden' id='idFromIframe' value=''/>
        <s:hidden id='gridVisibleNombreContra' name='gridVisibleNombreContra'/>
        <s:hidden id='gridIndividualModeNombreContra' name='gridIndividualModeNombreContra'/>

        <s:form id='nombreContraform' name='nombreContraform' cssClass='pair-wrap'>
            <div class='pair-hidden '>
                <div class='etiqueta' id='nombreContra_nombreContraIdText'>
                    <s:text name='key_nombrecontra_nombrecontraid'/>
                </div>
                <s:textfield name='nombreContraId' value='0' cssClass='isNumericInteger' />
            </div>
            <div class='pair col-sm-4'>
                <span class='etiqueta-small' id='nombreContra_clasificacionContraIdText'>
                    <s:text name='key_nombrecontra_clasificacioncontraid'/>
                </span>
                <!--<s:property escape='false' value='clasificacionContraNombreContraLink'/>-->
                <s:select list='clasificacionContraNombreContra' listKey='clasificacionContraId' listValue='nombre' name='clasificacionContraId' cssClass='select form-control' />
            </div>
            <div class='pair col-sm-4'>
                <span class='etiqueta-small' id='nombreContra_subclasificacionContraIdText'>
                    <s:text name='key_nombrecontra_subclasificacioncontraid'/>
                </span>
                <!--<s:property escape='false' value='subclasificacionContraNombreContraLink'/>-->
                <s:select list='subclasificacionContraNombreContra' listKey='subclasificacionContraId' listValue='nombre' name='subclasificacionContraId' cssClass='select form-control' />
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta' id='nombreContra_nombreText'>
                    <s:text name='key_nombrecontra_nombre'/>
                </div>
                <s:textfield name='nombre' maxLength='187' cssClass='textbox form-control'/>
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta' id='nombreContra_tipoValorIdText'>
                    <s:text name='key_nombrecontra_tipovalorid'/>
                </div>
                <s:select list='tipoValorNombreContra' listKey='valor' listValue='nombre' name='tipoValorId' cssClass='select form-control' />
            </div>
            <div class='pair col-sm-4'>
                <div class='pair-checkbox'>
                    <s:checkbox name='checkboxContra' cssClass='checkbox'/>
                    <div class='etiqueta-checkbox' id='nombreContra_checkboxContraText'>
                        <s:text name='key_nombrecontra_checkboxcontra'/>
                    </div>
                </div>
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta' id='nombreContra_ordenText'>
                    <s:text name='key_nombrecontra_orden'/>
                </div>
                <s:textfield name='orden' maxLength='20' cssClass='textbox form-control isNumericInteger'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='nombreContra_estatusIdText'>
                    <s:text name='key_nombrecontra_estatusid'/>
                </div>
                <s:select list='estatusNombreContra' listKey='valor' listValue='nombre' name='estatusId' cssClass='select form-control' />
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='nombreContra_eliminadoIdText'>
                    <s:text name='key_nombrecontra_eliminadoid'/>
                </div>
                <s:select list='eliminadoNombreContra' listKey='valor' listValue='nombre' name='eliminadoId' cssClass='select form-control' />
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='nombreContra_creacionFechaText'>
                    <s:text name='key_nombrecontra_creacionfecha'/>
                </div>
                <s:textfield name='creacionFecha' cssClass='textbox isDate'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='nombreContra_creacionUsuarioText'>
                    <s:text name='key_nombrecontra_creacionusuario'/>
                </div>
                <s:textfield name='creacionUsuario' maxLength='9' cssClass='textbox form-control isNumericInteger'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='nombreContra_modificacionFechaText'>
                    <s:text name='key_nombrecontra_modificacionFecha'/>
                </div>
                <s:textfield name='modificacionFecha' cssClass='textbox isDate'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='nombreContra_modificacionUsuarioText'>
                    <s:text name='key_nombrecontra_modificacionusuario'/>
                </div>
                <s:textfield name='modificacionUsuario' maxLength='9' cssClass='textbox form-control isNumericInteger'/>
            </div>
            <div style='clear: both'></div>
        </s:form>

        <div class='pair-wrap divisor'></div>
        <div class='grid-container'>
            <table id='nombreContraGrid'></table>
            <div id='nombreContraGridPagerId'></div>
        </div>
        <div class='pair-wrap divisor'></div>
        <br>
    </div>
</body>

