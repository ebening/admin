<%@taglib  prefix='s' uri='/struts-tags'%>
<head>
    <script src='<s:url value='../dwr/interface/ConfiguracionDWR.js'/>' type='text/javascript'></script>
    <script src='<s:url value='../js/admon/configuracion.js'/>' type='text/javascript'></script>
    <title>
        <s:property escape='false' value='nombreActionMenu'/>
    </title>
</head>
<body>
    <div class='form-wrap panel-body'>
        <div class='titulo ui-corner-top'>
            <div class='title-text hidden'><s:property escape='false' value='nombreActionMenu'/></div>
            <div class='button-container'>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-default' id='btnNewConfiguracion'><s:text name='key_configuracion_bnew'/></div>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-primary' id='btnSaveConfiguracion'><s:text name='key_configuracion_bsave'/></div>
            </div>
            <div style='clear: both'></div>
        </div>
        <!-- Hidden -->
        <input type='hidden' id='key_configuracion_title' value='<s:text name='key_configuracion_title'/>'/>
        <input type='hidden' id='key_configuracion_modificacionUsuario' value='<s:text name='key_configuracion_modificacionUsuario'/>'/>
        <input type='hidden' id='key_configuracion_modificacionFecha' value='<s:text name='key_configuracion_modificacionFecha'/>'/>
        <input type='hidden' id='configuracionGridOrderByColumn' value='modificacionFecha'/>
        <input type='hidden' id='configuracionGridOrderByType' value='des'/>
        <input type='hidden' id='configuracionGridCurrentPage' value='1'/>
        <input type='hidden' id='configuracionGridRowsByPage' value='10'/>
        <input type='hidden' id='idFromIframe' value=''/>
        <s:hidden id='gridVisibleConfiguracion' name='gridVisibleConfiguracion'/>
        <s:hidden id='gridIndividualModeConfiguracion' name='gridIndividualModeConfiguracion'/>
        <s:hidden id='gridVisibleConfiguracionParametro' name='gridVisibleConfiguracionParametro'/>
        <s:hidden id='gridIndividualModeConfiguracionParametro' name='gridIndividualModeConfiguracionParametro'/>

        <s:form id='configuracionform' name='configuracionform' cssClass='pair-wrap'>
            <div class='pair-hidden '>
                <div class='etiqueta' id='configuracion_configuracionIdText'>
                    <s:text name='key_configuracion_configuracionid'/>
                </div>
                <s:textfield name='configuracionId' value='0' cssClass='isNumericInteger' />
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta' id='configuracion_nombreText'>
                    <s:text name='key_configuracion_nombre'/>
                </div>
                <s:textfield name='nombre' maxLength='187' cssClass='textbox form-control'/>
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta' id='configuracion_descripcionText'>
                    <s:text name='key_configuracion_descripcion'/>
                </div>
                <s:textarea name='descripcion' cssClass='textbox form-control'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='configuracion_estatusIdText'>
                    <s:text name='key_configuracion_estatusid'/>
                </div>
                <s:select list='estatusConfiguracion' listKey='valor' listValue='nombre' name='estatusId' cssClass='select form-control' />
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='configuracion_eliminadoIdText'>
                    <s:text name='key_configuracion_eliminadoid'/>
                </div>
                <s:select list='eliminadoConfiguracion' listKey='valor' listValue='nombre' name='eliminadoId' cssClass='select form-control' />
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='configuracion_creacionFechaText'>
                    <s:text name='key_configuracion_creacionfecha'/>
                </div>
                <s:textfield name='creacionFecha' cssClass='textbox isDate form-control'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='configuracion_creacionUsuarioText'>
                    <s:text name='key_configuracion_creacionusuario'/>
                </div>
                <s:textfield name='creacionUsuario' maxLength='20' cssClass='textbox isNumericInteger form-control'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='configuracion_modificacionFechaText'>
                    <s:text name='key_configuracion_modificacionFecha'/>
                </div>
                <s:textfield name='modificacionFecha' cssClass='textbox isDate form-control'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='configuracion_modificacionUsuarioText'>
                    <s:text name='key_configuracion_modificacionusuario'/>
                </div>
                <s:textfield name='modificacionUsuario' maxLength='20' cssClass='textbox isNumericInteger form-control'/>
            </div>
            <div style='clear: both'></div>
        </s:form>
        <div class='pair-wrap divisor'></div>
        <!-- Divisor -->
        <div class='pair-wrap' style="background-color: #F6F6F6;">
            <!-- Titulo -->
            <input type='hidden' id='key_configuracionparametro_title' value='<s:text name='key_configuracionparametro_title'/>'/>
            <div class='title-text alert alert-info'><s:text name='key_configuracionparametro_title'/>
                <!-- Botón Agregar -->
                <div class='button-container-detalle'>
                    <div class='button-custom button-detalle mb-xs mt-xs mr-xs btn btn-default' id='btnAddConfiguracionParametro'><s:text name='key_configuracionparametro_badd'/></div>
                    <div class='hidden button button-detalle mb-xs mt-xs mr-xs btn btn-default' id='btnAddConfiguracionParametroNoValidation'><s:text name='key_configuracionparametro_badd'/></div>
                    <div style='clear: both'></div>
                </div>
            </div>

            <div style='clear: both'></div>
            <s:form id='configuracionParametroform' name='configuracionParametroform' cssClass='pair-wrap'>
                <div class='pair-hidden '>
                    <div class='etiqueta' id='configuracionParametro_filaIdText'>
                        filaId
                    </div>
                    <s:textfield name='filaId' value='0' cssClass='isNumericInteger form-control' />
                </div>
                <div class='pair-hidden '>
                    <div class='etiqueta' id='configuracionParametro_configuracionParametroIdText'>
                        <s:text name='key_configuracionparametro_configuracionparametroid'/>
                    </div>
                    <s:textfield name='configuracionParametroId' value='0' cssClass='isNumericInteger form-control' />
                </div>
                <div class='pair-hidden '>
                    <span class='etiqueta-small' id='configuracionParametro_configuracionIdText'>
                        <s:text name='key_configuracionparametro_configuracionid'/>
                    </span>
                    <s:property escape='false' value='configuracionConfiguracionParametroLink'/>
                    <s:select list='configuracionConfiguracionParametro' listKey='configuracionId' listValue='nombre' name='configuracionId' cssClass='select form-control' />
                </div>
                <div class='pair col-sm-4'>
                    <div class='etiqueta' id='configuracionParametro_nombreText'>
                        <s:text name='key_configuracionparametro_nombre'/>
                    </div>
                    <s:textfield name='nombre' maxLength='187' cssClass='textbox form-control'/>
                </div>
                <div class='pair col-sm-4'>
                    <div class='etiqueta' id='configuracionParametro_valorText'>
                        <s:text name='key_configuracionparametro_valor'/>
                    </div>
                    <s:textfield name='valor' maxLength='191' cssClass='textbox form-control'/>
                </div>
                <div class='pair col-sm-4'>
                    <div class='etiqueta' id='configuracionParametro_descripcionText'>
                        <s:text name='key_configuracionparametro_descripcion'/>
                    </div>
                    <s:textarea name='descripcion' cssClass='textbox form-control'/>
                </div>
                <div class='pair-hidden '>
                    <div class='etiqueta' id='configuracionParametro_estatusIdText'>
                        <s:text name='key_configuracionparametro_estatusid'/>
                    </div>
                    <s:select list='estatusConfiguracionParametro' listKey='valor' listValue='nombre' name='estatusId' cssClass='select form-control' />
                </div>
                <div class='pair-hidden '>
                    <div class='etiqueta' id='configuracionParametro_eliminadoIdText'>
                        <s:text name='key_configuracionparametro_eliminadoid'/>
                    </div>
                    <s:select list='eliminadoConfiguracionParametro' listKey='valor' listValue='nombre' name='eliminadoId' cssClass='select form-control' />
                </div>
                <div class='pair-hidden '>
                    <div class='etiqueta' id='configuracionParametro_creacionFechaText'>
                        <s:text name='key_configuracionparametro_creacionfecha'/>
                    </div>
                    <s:textfield name='creacionFecha' cssClass='textbox isDate form-control'/>
                </div>
                <div class='pair-hidden '>
                    <div class='etiqueta' id='configuracionParametro_creacionUsuarioText'>
                        <s:text name='key_configuracionparametro_creacionusuario'/>
                    </div>
                    <s:textfield name='creacionUsuario' maxLength='20' cssClass='textbox isNumericInteger form-control'/>
                </div>
                <div class='pair-hidden '>
                    <div class='etiqueta' id='configuracionParametro_modificacionFechaText'>
                        <s:text name='key_configuracionparametro_modificacionFecha'/>
                    </div>
                    <s:textfield name='modificacionFecha' cssClass='textbox isDate form-control'/>
                </div>
                <div class='pair-hidden '>
                    <div class='etiqueta' id='configuracionParametro_modificacionUsuarioText'>
                        <s:text name='key_configuracionparametro_modificacionusuario'/>
                    </div>
                    <s:textfield name='modificacionUsuario' maxLength='20' cssClass='textbox isNumericInteger form-control'/>
                </div>
                <div style='clear: both'></div>
            </s:form>
            <div class='pair-wrap divisor'></div>
            <div class='grid-container'>
                <table id='configuracionParametroGrid'></table>
                <div id='configuracionParametroGridPagerId'></div>
            </div>
        </div>

        <div class='pair-wrap divisor'></div>
        <div class='grid-container'>
            <table id='configuracionGrid'></table>
            <div id='configuracionGridPagerId'></div>
        </div>
        <div class='pair-wrap divisor'></div>
        <br>
    </div>
</body>

