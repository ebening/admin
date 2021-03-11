<%@taglib  prefix='s' uri='/struts-tags'%>
<head>
    <script src='<s:url value='../dwr/interface/EstadoCuentaDWR.js'/>' type='text/javascript'></script>
    <script src='<s:url value='../js/reportes/estadoCuenta.js'/>' type='text/javascript'></script>
    <title>
        <s:property escape='false' value='nombreActionMenu'/>
    </title>
</head>
<body>
    <div class='form-wrap panel-body'>
        <div class='titulo ui-corner-top'>
            <div class='title-text hidden'><s:property escape='false' value='nombreActionMenu'/></div>
            <div class='button-container'>
            </div>
            <div style='clear: both'></div>
        </div>
        <!-- Hidden -->
        <input type='hidden' id='key_estadocuenta_title' value='<s:text name='key_estadocuenta_title'/>'/>
        <input type='hidden' id='key_estadocuenta_organizacion' value='<s:text name='key_estadocuenta_organizacion'/>'/>
        <input type='hidden' id='key_estadocuenta_evento' value='<s:text name='key_estadocuenta_evento'/>'/>
        <input type='hidden' id='key_estadocuenta_boletosvendidos' value='<s:text name='key_estadocuenta_boletosvendidos'/>'/>
        <input type='hidden' id='key_estadocuenta_boletoscancelados' value='<s:text name='key_estadocuenta_boletoscancelados'/>'/>
        <input type='hidden' id='key_estadocuenta_boletosduro' value='<s:text name='key_estadocuenta_boletosduro'/>'/>
        <input type='hidden' id='key_estadocuenta_coletoscortesias' value='<s:text name='key_estadocuenta_coletoscortesias'/>'/>
        <input type='hidden' id='key_estadocuenta_venta' value='<s:text name='key_estadocuenta_venta'/>'/>
        <input type='hidden' id='key_estadocuenta_cargoxsevicio' value='<s:text name='key_estadocuenta_cargoxsevicio'/>'/>
        <input type='hidden' id='key_estadocuenta_total' value='<s:text name='key_estadocuenta_total'/>'/>
        <input type='hidden' id='key_estadocuenta_gananciataasgo' value='<s:text name='key_estadocuenta_gananciataasgo'/>'/>


        <input type='hidden' id='estadoCuentaGridOrderByColumn' value='organizacionId'/>
        <input type='hidden' id='estadoCuentaGridOrderByType' value='asc'/>
        <input type='hidden' id='estadoCuentaGridCurrentPage' value='1'/>
        <input type='hidden' id='estadoCuentaGridRowsByPage' value='250'/>
        <input type='hidden' id='idFromIframe' value=''/>
        <s:hidden id='gridVisibleEstadoCuenta' name='gridVisibleEstadoCuenta'/>
        <s:hidden id='gridIndividualModeEstadoCuenta' name='gridIndividualModeEstadoCuenta'/>

        <s:form id='estadoCuentaform' name='estadoCuentaform' cssClass='pair-wrap'>
            <div class='pair col-sm-2'>
                <br />
                <div class='etiqueta' id='estadoCuenta_FechaPagoText'>
                    <s:text name='key_estadocuenta_fechapago'/>
                </div>
            </div>
            <div class='pair col-sm-3'>
                <div class='etiqueta' id='estadoCuenta_FechaPagoInicioText'>
                    <s:text name='key_estadocuenta_fechapagoinicio'/>
                </div>
                <s:textfield name='fechaPagoInicio' cssClass='textbox form-control isDate'/>
            </div>
            <div class='pair col-sm-3'>
                <div class='etiqueta' id='estadoCuenta_FechaPagoFinText'>
                    <s:text name='key_estadocuenta_fechapagofin'/>
                </div>
                <s:textfield name='fechaPagoFin' cssClass='textbox form-control isDate'/>
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta' id='estadoCuenta_Boton_Buscar' style="height: 15px !important;">&nbsp;</div>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-primary' id='btnBuscarEstadoCuenta'><s:text name='key_estadocuenta_bsearch'/></div>
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta' id='estadoCuenta_organizacionIdText'>
                    <s:text name='key_estadocuenta_organizacionid'/>
                </div>
                <s:select list='organizacionEstadoCuenta' listKey='organizacionId' listValue='nombre' name='organizacionId' cssClass='select form-control' />
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta' id='estadoCuenta_EventoIdText'>
                    <s:text name='key_estadocuenta_eventoid'/>
                </div>
                <s:select list='eventoEstadoCuenta' listKey='eventoId' listValue='nombre' name='eventoId' cssClass='select form-control' />
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta' id='estadoCuenta_Boton_Exportar' style="height: 15px !important;">&nbsp;</div>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-primary' id='btnExportarEstadoCuenta'><s:text name='key_estadocuenta_bexport'/></div>
            </div>
            <div style='clear: both'></div>
        </s:form>
        <div class='pair-wrap divisor'></div>
        <div class='grid-container'>
            <table id='estadoCuentaGrid'></table>
            <div id='estadoCuentaGridPagerId'></div>
        </div>
        <div class='pair-wrap divisor'></div>
        <br>
    </div>
</body>

