<%@taglib  prefix='s' uri='/struts-tags'%>
<head>
    <script src='<s:url value='../dwr/interface/CiudadDWR.js'/>' type='text/javascript'></script>
    <script src='<s:url value='../js/admon/ciudad.js'/>' type='text/javascript'></script>
    <title>
        <s:property escape='false' value='nombreActionMenu'/>
    </title>
</head>
<body>
    <div class='form-wrap panel-body'>
        <div class='titulo ui-corner-top'>
            <div class='title-text hidden'><s:property escape='false' value='nombreActionMenu'/></div>
            <div class='button-container'>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-default' id='btnNewCiudad'><s:text name='key_ciudad_bnew'/></div>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-primary' id='btnSaveCiudad'><s:text name='key_ciudad_bsave'/></div>
            </div>
            <div style='clear: both'></div>
        </div>
        <!-- Hidden -->
        <input type='hidden' id='key_ciudad_title' value='<s:text name='key_ciudad_title'/>'/>
        <input type='hidden' id='key_ciudad_modificacionUsuario' value='<s:text name='key_ciudad_modificacionUsuario'/>'/>
        <input type='hidden' id='key_ciudad_modificacionFecha' value='<s:text name='key_ciudad_modificacionFecha'/>'/>
        <input type='hidden' id='ciudadGridOrderByColumn' value='modificacionFecha'/>
        <input type='hidden' id='ciudadGridOrderByType' value='des'/>
        <input type='hidden' id='ciudadGridCurrentPage' value='1'/>
        <input type='hidden' id='ciudadGridRowsByPage' value='10'/>
        <input type='hidden' id='idFromIframe' value=''/>
        <s:hidden id='gridVisibleCiudad' name='gridVisibleCiudad'/>
        <s:hidden id='gridIndividualModeCiudad' name='gridIndividualModeCiudad'/>

        <s:form id='ciudadform' name='ciudadform' cssClass='pair-wrap'>
            <div class='pair-hidden '>
                <div class='etiqueta' id='ciudad_ciudadIdText'>
                    <s:text name='key_ciudad_ciudadid'/>
                </div>
                <s:textfield name='ciudadId' value='0' cssClass='isNumericInteger' />
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta' id='ciudad_codeText'>
                    <s:text name='key_ciudad_code'/>
                </div>
                <s:textfield name='code' maxLength='187' cssClass='textbox form-control '/>
            </div>
            <div class='pair col-sm-4'>
                <span class='etiqueta-small' id='ciudad_paisIdText'>
                    <s:text name='key_ciudad_paisid'/>
                </span>
                <s:select list='paisCiudad' listKey='paisId' listValue='nombre' name='paisId' cssClass='select form-control ' />
            </div>
            <div class='pair col-sm-4'>
                <span class='etiqueta-small' id='ciudad_estadoIdText'>
                    <s:text name='key_ciudad_estadoid'/>
                </span>
                <s:select list='estadoCiudad' listKey='estadoId' listValue='nombre' name='estadoId' cssClass='select form-control ' />
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta' id='ciudad_nombreText'>
                    <s:text name='key_ciudad_nombre'/>
                </div>
                <s:textfield name='nombre' maxLength='187' cssClass='textbox form-control '/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='ciudad_estatusIdText'>
                    <s:text name='key_ciudad_estatusid'/>
                </div>
                <s:select list='estatusCiudad' listKey='valor' listValue='nombre' name='estatusId' cssClass='select' />
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='ciudad_eliminadoIdText'>
                    <s:text name='key_ciudad_eliminadoid'/>
                </div>
                <s:select list='eliminadoCiudad' listKey='valor' listValue='nombre' name='eliminadoId' cssClass='select' />
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='ciudad_creacionFechaText'>
                    <s:text name='key_ciudad_creacionfecha'/>
                </div>
                <s:textfield name='creacionFecha' cssClass='textbox form-control  isDate'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='ciudad_creacionUsuarioText'>
                    <s:text name='key_ciudad_creacionusuario'/>
                </div>
                <s:textfield name='creacionUsuario' maxLength='9' cssClass='textbox form-control  isNumericInteger'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='ciudad_modificacionFechaText'>
                    <s:text name='key_ciudad_modificacionFecha'/>
                </div>
                <s:textfield name='modificacionFecha' cssClass='textbox form-control  isDate'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='ciudad_modificacionUsuarioText'>
                    <s:text name='key_ciudad_modificacionusuario'/>
                </div>
                <s:textfield name='modificacionUsuario' maxLength='9' cssClass='textbox form-control  isNumericInteger'/>
            </div>
            <div style='clear: both'></div>
        </s:form>

        <div class='pair-wrap divisor'></div>
        <div class='grid-container'>
            <table id='ciudadGrid'></table>
            <div id='ciudadGridPagerId'></div>
        </div>
        <div class='pair-wrap divisor'></div>
        <br>
    </div>
</body>

