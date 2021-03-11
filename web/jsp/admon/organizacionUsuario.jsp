<%@taglib  prefix='s' uri='/struts-tags'%>
<head>
    <script src='<s:url value='../dwr/interface/OrganizacionUsuarioDWR.js'/>' type='text/javascript'></script>
    <script src='<s:url value='../js/admon/organizacionUsuario.js'/>' type='text/javascript'></script>
    <title>
        <s:property escape='false' value='nombreActionMenu'/>
    </title>
</head>
<body>
    <div class='form-wrap ui-widget ui-widget-content ui-corner-all b-none'>
        <div class='title ui-widget-header ui-corner-top'>
            <div class='title-text'><s:property escape='false' value='nombreActionMenu'/></div>
            <div class='button-container'>
                <div class='button-custom' id='btnNewOrganizacionUsuario'><span class='ui-icon ui-icon-plus icon-fix'> </span><s:text name='key_organizacionusuario_bnew'/></div>
                <div class='button-custom' id='btnSaveOrganizacionUsuario'><span class='ui-icon ui-icon-disk icon-fix'> </span><s:text name='key_organizacionusuario_bsave'/></div>
            </div>
            <div style='clear: both'></div>
        </div>
        <!-- Hidden -->
        <input type='hidden' id='key_organizacionusuario_title' value='<s:text name='key_organizacionusuario_title'/>'/>
        <input type='hidden' id='key_organizacionusuario_modificacionUsuario' value='<s:text name='key_organizacionusuario_modificacionUsuario'/>'/>
        <input type='hidden' id='key_organizacionusuario_modificacionFecha' value='<s:text name='key_organizacionusuario_modificacionFecha'/>'/>
        <input type='hidden' id='organizacionUsuarioGridOrderByColumn' value='modificacionFecha'/>
        <input type='hidden' id='organizacionUsuarioGridOrderByType' value='des'/>
        <input type='hidden' id='organizacionUsuarioGridCurrentPage' value='1'/>
        <input type='hidden' id='organizacionUsuarioGridRowsByPage' value='10'/>
        <input type='hidden' id='idFromIframe' value=''/>
        <s:hidden id='gridVisibleOrganizacionUsuario' name='gridVisibleOrganizacionUsuario'/>
        <s:hidden id='gridIndividualModeOrganizacionUsuario' name='gridIndividualModeOrganizacionUsuario'/>

        <s:form id='organizacionUsuarioform' name='organizacionUsuarioform' cssClass='pair-wrap'>
            <div class='pair-hidden '>
                <div class='etiqueta' id='organizacionUsuario_organizacionUsuarioIdText'>
                    <s:text name='key_organizacionusuario_organizacionusuarioid'/>
                </div>
                <s:textfield name='organizacionUsuarioId' value='0' cssClass='isNumericInteger' />
            </div>
            <div class='pair '>
                <span class='etiqueta-small' id='organizacionUsuario_organizacionIdText'>
                    <s:text name='key_organizacionusuario_organizacionid'/>
                </span>
                <s:property escape='false' value='organizacionOrganizacionUsuarioLink'/>
                <s:select list='organizacionOrganizacionUsuario' listKey='organizacionId' listValue='nombre' name='organizacionId' cssClass='select' />
            </div>
            <div class='pair '>
                <div class='etiqueta' id='organizacionUsuario_usuarioAdmonText'>
                    <s:text name='key_organizacionusuario_usuarioadmon'/>
                </div>
                <s:textfield name='usuarioAdmon' maxLength='187' cssClass='textbox'/>
            </div>
            <div class='pair '>
                <div class='etiqueta' id='organizacionUsuario_contrasenaText'>
                    <s:text name='key_organizacionusuario_contrasena'/>
                </div>
                <s:password name='contrasena' cssClass='textbox'/>
            </div>
            <div class='pair '>
                <div class='etiqueta' >
                    <s:text name='key_organizacionusuario_confirmarcontrasena'/>
                </div>
                <s:password name='confirmarContrasena' maxLength='187' cssClass='textbox'/>
            </div>
            <div class='pair '>
                <div class='etiqueta' id='organizacionUsuario_nombreText'>
                    <s:text name='key_organizacionusuario_nombre'/>
                </div>
                <s:textfield name='nombre' maxLength='187' cssClass='textbox'/>
            </div>
            <div class='pair '>
                <div class='etiqueta' id='organizacionUsuario_apellidoPaternoText'>
                    <s:text name='key_organizacionusuario_apellidopaterno'/>
                </div>
                <s:textfield name='apellidoPaterno' maxLength='187' cssClass='textbox'/>
            </div>
            <div class='pair '>
                <div class='etiqueta' id='organizacionUsuario_apellidoMaternoText'>
                    <s:text name='key_organizacionusuario_apellidomaterno'/>
                </div>
                <s:textfield name='apellidoMaterno' maxLength='187' cssClass='textbox'/>
            </div>
            <div class='pair '>
                <div class='etiqueta' id='organizacionUsuario_correoText'>
                    <s:text name='key_organizacionusuario_correo'/>
                </div>
                <s:textfield name='correo' maxLength='187' cssClass='textbox'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='organizacionUsuario_estatusIdText'>
                    <s:text name='key_organizacionusuario_estatusid'/>
                </div>
                <s:select list='estatusOrganizacionUsuario' listKey='valor' listValue='nombre' name='estatusId' cssClass='select' />
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='organizacionUsuario_eliminadoIdText'>
                    <s:text name='key_organizacionusuario_eliminadoid'/>
                </div>
                <s:select list='eliminadoOrganizacionUsuario' listKey='valor' listValue='nombre' name='eliminadoId' cssClass='select' />
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='organizacionCREACION_USUARIOFechaText'>
                    <s:text name='key_organizacionCREACION_USUARIOfecha'/>
                </div>
                <s:textfield name='creacionFecha' cssClass='textbox isDate'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='organizacionCREACION_USUARIOUsuarioText'>
                    <s:text name='key_organizacionCREACION_USUARIOusuario'/>
                </div>
                <s:textfield name='creacionUsuario' maxLength='9' cssClass='textbox isNumericInteger'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='organizacionUsuario_modificacionFechaText'>
                    <s:text name='key_organizacionusuario_modificacionFecha'/>
                </div>
                <s:textfield name='modificacionFecha' cssClass='textbox isDate'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='organizacionUsuario_modificacionUsuarioText'>
                    <s:text name='key_organizacionusuario_modificacionusuario'/>
                </div>
                <s:textfield name='modificacionUsuario' maxLength='9' cssClass='textbox isNumericInteger'/>
            </div>
            <div style='clear: both'></div>
        </s:form>

        <div class='pair-wrap divisor'></div>
        <div class='grid-container'>
            <table id='organizacionUsuarioGrid'></table>
            <div id='organizacionUsuarioGridPagerId'></div>
        </div>
        <div class='pair-wrap divisor'></div>
        <br>
    </div>
</body>

