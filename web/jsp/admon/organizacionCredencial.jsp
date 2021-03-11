<%@taglib  prefix='s' uri='/struts-tags'%>
<head>
    <script src='<s:url value='../dwr/interface/OrganizacionCredencialDWR.js'/>' type='text/javascript'></script>
    <script src='<s:url value='../js/admon/organizacionCredencial.js'/>' type='text/javascript'></script>
    <title>
        <s:property escape='false' value='nombreActionMenu'/>
    </title>
</head>
<body>
    <div class='form-wrap ui-widget ui-widget-content ui-corner-all b-none'>
        <div class='title ui-widget-header ui-corner-top'>
            <div class='title-text'><s:property escape='false' value='nombreActionMenu'/></div>
            <div class='button-container'>
                <div class='button-custom' id='btnNewOrganizacionCredencial'><span class='ui-icon ui-icon-plus icon-fix'> </span><s:text name='key_organizacioncredencial_bnew'/></div>
                <div class='button-custom' id='btnSaveOrganizacionCredencial'><span class='ui-icon ui-icon-disk icon-fix'> </span><s:text name='key_organizacioncredencial_bsave'/></div>
            </div>
            <div style='clear: both'></div>
        </div>
        <!-- Hidden -->
        <input type='hidden' id='key_organizacioncredencial_title' value='<s:text name='key_organizacioncredencial_title'/>'/>
        <input type='hidden' id='key_organizacioncredencial_modificacionUsuario' value='<s:text name='key_organizacioncredencial_modificacionUsuario'/>'/>
        <input type='hidden' id='key_organizacioncredencial_modificacionFecha' value='<s:text name='key_organizacioncredencial_modificacionFecha'/>'/>
        <input type='hidden' id='organizacionCredencialGridOrderByColumn' value='modificacionFecha'/>
        <input type='hidden' id='organizacionCredencialGridOrderByType' value='des'/>
        <input type='hidden' id='organizacionCredencialGridCurrentPage' value='1'/>
        <input type='hidden' id='organizacionCredencialGridRowsByPage' value='10'/>
        <input type='hidden' id='idFromIframe' value=''/>
        <s:hidden id='gridVisibleOrganizacionCredencial' name='gridVisibleOrganizacionCredencial'/>
        <s:hidden id='gridIndividualModeOrganizacionCredencial' name='gridIndividualModeOrganizacionCredencial'/>

        <s:form id='organizacionCredencialform' name='organizacionCredencialform' cssClass='pair-wrap'>
            <div class='pair-hidden '>
                <div class='etiqueta' id='organizacionCredencial_organizacionCredencialIdText'>
                    <s:text name='key_organizacioncredencial_organizacioncredencialid'/>
                </div>
                <s:textfield name='organizacionCredencialId' value='0' cssClass='isNumericInteger' />
            </div>
            <div class='pair '>
                <span class='etiqueta-small' id='organizacionCredencial_organizacionIdText'>
                    <s:text name='key_organizacioncredencial_organizacionid'/>
                </span>
                <s:property escape='false' value='organizacionOrganizacionCredencialLink'/>
                <s:select list='organizacionOrganizacionCredencial' listKey='organizacionId' listValue='nombre' name='organizacionId' cssClass='select' />
            </div>
            <div class='pair '>
                <div class='etiqueta' id='organizacionCredencial_nombreText'>
                    <s:text name='key_organizacioncredencial_nombre'/>
                </div>
                <s:textfield name='nombre' maxLength='187' cssClass='textbox'/>
            </div>
            <div class='pair '>
                <div class='etiqueta' id='organizacionCredencial_domainText'>
                    <s:text name='key_organizacioncredencial_domain'/>
                </div>
                <s:textfield name='domain' maxLength='187' cssClass='textbox'/>
            </div>
            <div class='pair col-sm-4'>
                <span class='etiqueta-small' id='organizacionCredencial_aplicacionText'>
                    <s:text name='key_organizacioncredencial_aplicacion'/>
                </span>
                <s:select list='organizacionCredencialesAplicaciones' listKey='valor' listValue='nombre' name='idanalytics' cssClass='select form-control ' />
            </div>
            <div class='pair '>
                <div class='etiqueta' id='organizacionCredencial_idcredencialText'>
                    <s:text name='key_organizacioncredencial_idcredencial'/>
                </div>
                <s:textfield name='idcredencial' maxLength='187' cssClass='textbox'/>
            </div>
            <div class='pair '>
                <div class='etiqueta' id='organizacionCredencial_idanalyticsText'>
                    <s:text name='key_organizacioncredencial_idanalytics'/>
                </div>
                <s:textfield name='idanalytics' maxLength='187' cssClass='textbox'/>
            </div>
            <div class='pair '>
                <div class='etiqueta' id='organizacionCredencial_idappText'>
                    <s:text name='key_organizacioncredencial_idapp'/>
                </div>
                <s:textfield name='idapp' maxLength='187' cssClass='textbox'/>
            </div>
            <div class='pair '>
                <div class='etiqueta' id='organizacionCredencial_idrecapchaText'>
                    <s:text name='key_organizacioncredencial_idrecapcha'/>
                </div>
                <s:textfield name='idrecapcha' maxLength='187' cssClass='textbox'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='organizacionCredencial_estatusIdText'>
                    <s:text name='key_organizacioncredencial_estatusid'/>
                </div>
                <s:select list='estatusOrganizacionCredencial' listKey='valor' listValue='nombre' name='estatusId' cssClass='select' />
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='organizacionCredencial_eliminadoIdText'>
                    <s:text name='key_organizacioncredencial_eliminadoid'/>
                </div>
                <s:select list='eliminadoOrganizacionCredencial' listKey='valor' listValue='nombre' name='eliminadoId' cssClass='select' />
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='organizacionCredencial_creacionFechaText'>
                    <s:text name='key_organizacioncredencial_creacionfecha'/>
                </div>
                <s:textfield name='creacionFecha' cssClass='textbox isDate'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='organizacionCredencial_creacionUsuarioText'>
                    <s:text name='key_organizacioncredencial_creacionusuario'/>
                </div>
                <s:textfield name='creacionUsuario' maxLength='9' cssClass='textbox isNumericInteger'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='organizacionCredencial_modificacionFechaText'>
                    <s:text name='key_organizacioncredencial_modificacionFecha'/>
                </div>
                <s:textfield name='modificacionFecha' cssClass='textbox isDate'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='organizacionCredencial_modificacionUsuarioText'>
                    <s:text name='key_organizacioncredencial_modificacionusuario'/>
                </div>
                <s:textfield name='modificacionUsuario' maxLength='9' cssClass='textbox isNumericInteger'/>
            </div>
            <div style='clear: both'></div>
        </s:form>

        <div class='pair-wrap divisor'></div>
        <div class='grid-container'>
            <table id='organizacionCredencialGrid'></table>
            <div id='organizacionCredencialGridPagerId'></div>
        </div>
        <div class='pair-wrap divisor'></div>
        <br>
    </div>
</body>

