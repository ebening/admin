<%@taglib  prefix='s' uri='/struts-tags'%>
<head>
    <script src='<s:url value='../dwr/interface/AplicacionDWR.js'/>' type='text/javascript'></script>
    <script src='<s:url value='../js/admon/aplicacion.js'/>' type='text/javascript'></script>
    <title>
        <s:property escape='false' value='nombreActionMenu'/>
    </title>
</head>
<body>
    <div class='form-wrap panel-body'>
        <div class='titulo ui-corner-top'>
            <div class='title-text hidden'><s:property escape='false' value='nombreActionMenu'/></div>
            <div class='button-container'>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-default' id='btnNewAplicacion'><s:text name='key_aplicacion_bnew'/></div>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-primary' id='btnSaveAplicacion'><s:text name='key_aplicacion_bsave'/></div>
            </div>
            <div style='clear: both'></div>
        </div>
        <!-- Hidden -->
        <input type='hidden' id='key_aplicacion_title' value='<s:text name='key_aplicacion_title'/>'/>
        <input type='hidden' id='key_aplicacion_modificacionUsuario' value='<s:text name='key_aplicacion_modificacionUsuario'/>'/>
        <input type='hidden' id='key_aplicacion_modificacionFecha' value='<s:text name='key_aplicacion_modificacionFecha'/>'/>
        <input type='hidden' id='aplicacionGridOrderByColumn' value='modificacionFecha'/>
        <input type='hidden' id='aplicacionGridOrderByType' value='des'/>
        <input type='hidden' id='aplicacionGridCurrentPage' value='1'/>
        <input type='hidden' id='aplicacionGridRowsByPage' value='10'/>
        <input type='hidden' id='idFromIframe' value=''/>
        <s:hidden id='gridVisibleAplicacion' name='gridVisibleAplicacion'/>
        <s:hidden id='gridIndividualModeAplicacion' name='gridIndividualModeAplicacion'/>

        <s:form id='aplicacionform' name='aplicacionform' cssClass='pair-wrap'>
            <div class='pair-hidden '>
                <div class='etiqueta' id='aplicacion_aplicacionIdText'>
                    <s:text name='key_aplicacion_aplicacionid'/>
                </div>
                <s:textfield name='aplicacionId' value='1' cssClass='isNumericInteger form-control' />
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta' id='aplicacion_nombreText'>
                    <s:text name='key_aplicacion_nombreES'/>
                </div>
                <s:textfield name='nombre' maxLength='200' cssClass='textbox form-control'/>
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta' id='aplicacion_nombreInglesText'>
                    <s:text name='key_aplicacion_nombreEN'/>
                </div>
                <s:textfield name='nombreIngles' maxLength='200' cssClass='textbox form-control'/>
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta' id='aplicacion_descripcionText'>
                    <s:text name='key_aplicacion_descripcion'/>
                </div>
                <s:textfield name='descripcion' cssClass='textbox form-control'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='aplicacion_estatusIdText'>
                    <s:text name='key_aplicacion_estatusid'/>
                </div>
                <s:select list='estatusAplicacion' listKey='valor' listValue='nombre' name='estatusId' cssClass='select form-control' />
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='aplicacion_eliminadoIdText'>
                    <s:text name='key_aplicacion_eliminadoid'/>
                </div>
                <s:select list='eliminadoAplicacion' listKey='valor' listValue='nombre' name='eliminadoId' cssClass='select form-control' />
            </div>
            
            <div class='pair-hidden '>
                <div class='etiqueta' id='aplicacion_creacionFechaText'>
                    <s:text name='key_aplicacion_creacionfecha'/>
                </div>
                <s:textfield name='creacionFecha' cssClass='textbox isDate form-control'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='aplicacion_creacionUsuarioText'>
                    <s:text name='key_aplicacion_creacionusuario'/>
                </div>
                <s:textfield name='creacionUsuario' maxLength='20' cssClass='textbox isNumericInteger form-control'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='aplicacion_modificacionFechaText'>
                    <s:text name='key_aplicacion_modificacionFecha'/>
                </div>
                <s:textfield name='modificacionFecha' cssClass='textbox isDate form-control'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='aplicacion_modificacionUsuarioText'>
                    <s:text name='key_aplicacion_modificacionUsuario'/>
                </div>
                <s:textfield name='modificacionUsuario' maxLength='20' cssClass='textbox isNumericInteger form-control'/>
            </div>
            <div style='clear: both'></div>
        </s:form>

        <div class='pair-wrap divisor'></div>
        <div class='grid-container'>
            <table id='aplicacionGrid'></table>
            <div id='aplicacionGridPagerId'></div>
        </div>
        <div class='pair-wrap divisor'></div>
        <br>
    </div>
</body>

