<%@taglib  prefix='s' uri='/struts-tags'%>
<head>
    <script src='<s:url value='../dwr/interface/ConfiguracionParametroDWR.js'/>' type='text/javascript'></script>
    <script src='<s:url value='../js/admon/configuracionParametro.js'/>' type='text/javascript'></script>
    <title>
        <s:property escape='false' value='nombreActionMenu'/>
    </title>
</head>
<body>
    <div class='form-wrap panel-body'>
        <div class='titulo ui-corner-top'>
            <div class='title-text'><s:property escape='false' value='nombreActionMenu'/></div>
            <div class='button-container'>
                <div class='button-custom' id='btnNewConfiguracionParametro'><s:text name='key_configuracionparametro_bnew'/></div>
                <div class='button-custom' id='btnSaveConfiguracionParametro'><s:text name='key_configuracionparametro_bsave'/></div>
            </div>
            <div style='clear: both'></div>
        </div>
        <!-- Hidden -->
        <input type='hidden' id='key_configuracionparametro_title' value='<s:text name='key_configuracionparametro_title'/>'/>
        <input type='hidden' id='key_configuracionparametro_modificacionUsuario' value='<s:text name='key_configuracionparametro_modificacionUsuario'/>'/>
        <input type='hidden' id='key_configuracionparametro_modificacionFecha' value='<s:text name='key_configuracionparametro_modificacionFecha'/>'/>
        <input type='hidden' id='configuracionParametroGridOrderByColumn' value='modificacionFecha'/>
        <input type='hidden' id='configuracionParametroGridOrderByType' value='des'/>
        <input type='hidden' id='configuracionParametroGridCurrentPage' value='1'/>
        <input type='hidden' id='configuracionParametroGridRowsByPage' value='10'/>
        <input type='hidden' id='idFromIframe' value=''/>
        <s:hidden id='gridVisibleConfiguracionParametro' name='gridVisibleConfiguracionParametro'/>
        <s:hidden id='gridIndividualModeConfiguracionParametro' name='gridIndividualModeConfiguracionParametro'/>

        <s:form id='configuracionParametroform' name='configuracionParametroform' cssClass='pair-wrap'>
            <div class='pair-hidden '>
                <div class='etiqueta' id='configuracionParametro_configuracionParametroIdText'>
                    <s:text name='key_configuracionparametro_configuracionparametroid'/>
                </div>
                <s:textfield name='configuracionParametroId' value='0' cssClass='isNumericInteger' />
            </div>
            <div class='pair '>
                <span class='etiqueta-small' id='configuracionParametro_configuracionIdText'>
                    <s:text name='key_configuracionparametro_configuracionid'/>
                </span>
                <s:property escape='false' value='configuracionConfiguracionParametroLink'/>
                <s:select list='configuracionConfiguracionParametro' listKey='configuracionId' listValue='nombre' name='configuracionId' cssClass='select' />
            </div>
            <div class='pair '>
                <div class='etiqueta' id='configuracionParametro_nombreText'>
                    <s:text name='key_configuracionparametro_nombre'/>
                </div>
                <s:textfield name='nombre' maxLength='187' cssClass='textbox'/>
            </div>
            <div class='pair '>
                <div class='etiqueta' id='configuracionParametro_valorText'>
                    <s:text name='key_configuracionparametro_valor'/>
                </div>
                <s:textfield name='valor' maxLength='191' cssClass='textbox'/>
            </div>
            <div class='pair-textarea '>
                <div class='etiqueta' id='configuracionParametro_descripcionText'>
                    <s:text name='key_configuracionparametro_descripcion'/>
                </div>
                <s:textarea name='descripcion' cssClass='textarea'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='configuracionParametro_estatusIdText'>
                    <s:text name='key_configuracionparametro_estatusid'/>
                </div>
                <s:select list='estatusConfiguracionParametro' listKey='catalogoParametroId' listValue='nombre' name='estatusId' cssClass='select' />
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='configuracionParametro_eliminadoIdText'>
                    <s:text name='key_configuracionparametro_eliminadoid'/>
                </div>
                <s:select list='eliminadoConfiguracionParametro' listKey='catalogoParametroId' listValue='nombre' name='eliminadoId' cssClass='select' />
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='configuracionParametro_creacionFechaText'>
                    <s:text name='key_configuracionparametro_creacionfecha'/>
                </div>
                <s:textfield name='creacionFecha' cssClass='textbox isDate'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='configuracionParametro_creacionUsuarioText'>
                    <s:text name='key_configuracionparametro_creacionusuario'/>
                </div>
                <s:textfield name='creacionUsuario' maxLength='20' cssClass='textbox isNumericInteger'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='configuracionParametro_modificacionFechaText'>
                    <s:text name='key_configuracionparametro_modificacionFecha'/>
                </div>
                <s:textfield name='modificacionFecha' cssClass='textbox isDate'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='configuracionParametro_modificacionUsuarioText'>
                    <s:text name='key_configuracionparametro_modificacionusuario'/>
                </div>
                <s:textfield name='modificacionUsuario' maxLength='20' cssClass='textbox isNumericInteger'/>
            </div>
            <div style='clear: both'></div>
        </s:form>

        <div class='pair-wrap divisor'></div>
        <div class='grid-container'>
            <table id='configuracionParametroGrid'></table>
            <div id='configuracionParametroGridPagerId'></div>
        </div>
        <div class='pair-wrap divisor'></div>
        <br>
    </div>
</body>

