<%@taglib  prefix='s' uri='/struts-tags'%>
<head>
    <script src='<s:url value='../dwr/interface/SubclasificacionContraDWR.js'/>' type='text/javascript'></script>
    <script src='<s:url value='../js/admon/subclasificacionContra.js'/>' type='text/javascript'></script>
    <title>
        <s:property escape='false' value='nombreActionMenu'/>
    </title>
</head>
<body>
    <div class='form-wrap panel-body'>
        <div class='titulo ui-corner-top'>
            <div class='title-text hidden'><s:property escape='false' value='nombreActionMenu'/></div>
            <div class='button-container'>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-default' id='btnNewSubclasificacionContra'><s:text name='key_subclasificacioncontra_bnew'/></div>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-primary' id='btnSaveSubclasificacionContra'><s:text name='key_subclasificacioncontra_bsave'/></div>
            </div>
            <div style='clear: both'></div>
        </div>
        <!-- Hidden -->
        <input type='hidden' id='key_subclasificacioncontra_title' value='<s:text name='key_subclasificacioncontra_title'/>'/>
        <input type='hidden' id='key_subclasificacioncontra_modificacionUsuario' value='<s:text name='key_subclasificacioncontra_modificacionUsuario'/>'/>
        <input type='hidden' id='key_subclasificacioncontra_modificacionFecha' value='<s:text name='key_subclasificacioncontra_modificacionFecha'/>'/>
        <input type='hidden' id='subclasificacionContraGridOrderByColumn' value='modificacionFecha'/>
        <input type='hidden' id='subclasificacionContraGridOrderByType' value='des'/>
        <input type='hidden' id='subclasificacionContraGridCurrentPage' value='1'/>
        <input type='hidden' id='subclasificacionContraGridRowsByPage' value='10'/>
        <input type='hidden' id='idFromIframe' value=''/>
        <s:hidden id='gridVisibleSubclasificacionContra' name='gridVisibleSubclasificacionContra'/>
        <s:hidden id='gridIndividualModeSubclasificacionContra' name='gridIndividualModeSubclasificacionContra'/>

        <s:form id='subclasificacionContraform' name='subclasificacionContraform' cssClass='pair-wrap'>
            <div class='pair-hidden '>
                <div class='etiqueta' id='subclasificacionContra_subclasificacionContraIdText'>
                    <s:text name='key_subclasificacioncontra_subclasificacioncontraid'/>
                </div>
                <s:textfield name='subclasificacionContraId' value='0' cssClass='isNumericInteger' />
            </div>
            <div class='pair col-sm-4'>
                <span class='etiqueta-small' id='subclasificacionContra_clasificacionContraIdText'>
                    <s:text name='key_subclasificacioncontra_clasificacioncontraid'/>
                </span>
                <!--<s:property escape='false' value='clasificacionContraSubclasificacionContraLink'/>-->
                <s:select list='clasificacionContraSubclasificacionContra' listKey='clasificacionContraId' listValue='nombre' name='clasificacionContraId' cssClass='select form-control' />
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta' id='subclasificacionContra_nombreText'>
                    <s:text name='key_subclasificacioncontra_nombre'/>
                </div>
                <s:textfield name='nombre' maxLength='187' cssClass='textbox form-control'/>
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta' id='subclasificacionContra_ordenText'>
                    <s:text name='key_subclasificacioncontra_orden'/>
                </div>
                <s:textfield name='orden' maxLength='20' cssClass='textbox form-control isNumericInteger'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='subclasificacionContra_estatusIdText'>
                    <s:text name='key_subclasificacioncontra_estatusid'/>
                </div>
                <s:select list='estatusSubclasificacionContra' listKey='valor' listValue='nombre' name='estatusId' cssClass='select form-control' />
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='subclasificacionContra_eliminadoIdText'>
                    <s:text name='key_subclasificacioncontra_eliminadoid'/>
                </div>
                <s:select list='eliminadoSubclasificacionContra' listKey='valor' listValue='nombre' name='eliminadoId' cssClass='select form-control' />
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='subclasificacionContra_creacionFechaText'>
                    <s:text name='key_subclasificacioncontra_creacionfecha'/>
                </div>
                <s:textfield name='creacionFecha' cssClass='textbox isDate'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='subclasificacionContra_creacionUsuarioText'>
                    <s:text name='key_subclasificacioncontra_creacionusuario'/>
                </div>
                <s:textfield name='creacionUsuario' maxLength='9' cssClass='textbox form-control isNumericInteger'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='subclasificacionContra_modificacionFechaText'>
                    <s:text name='key_subclasificacioncontra_modificacionFecha'/>
                </div>
                <s:textfield name='modificacionFecha' cssClass='textbox isDate'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='subclasificacionContra_modificacionUsuarioText'>
                    <s:text name='key_subclasificacioncontra_modificacionusuario'/>
                </div>
                <s:textfield name='modificacionUsuario' maxLength='9' cssClass='textbox form-control isNumericInteger'/>
            </div>
            <div style='clear: both'></div>
        </s:form>

        <div class='pair-wrap divisor'></div>
        <div class='grid-container'>
            <table id='subclasificacionContraGrid'></table>
            <div id='subclasificacionContraGridPagerId'></div>
        </div>
        <div class='pair-wrap divisor'></div>
        <br>
    </div>
</body>

