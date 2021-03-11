<%@taglib  prefix='s' uri='/struts-tags'%>
<head>
    <script src='<s:url value='../dwr/interface/PaginaDWR.js'/>' type='text/javascript'></script>
    <script src='<s:url value='../js/admon/pagina.js'/>' type='text/javascript'></script>
    <title>
        <s:property escape='false' value='nombreActionMenu'/>
    </title>
</head>
<body>
    <div class='form-wrap panel-body'>
        <div class='titulo ui-corner-top'>
            <div class='title-text hidden'><s:property escape='false' value='nombreActionMenu'/></div>
            <div class='button-container'>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-default' id='btnNewPagina'><s:text name='key_pagina_bnew'/></div>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-primary' id='btnSavePagina'><s:text name='key_pagina_bsave'/></div>
            </div>
            <div style='clear: both'></div>
        </div>
        <!-- Hidden -->
        <input type='hidden' id='key_pagina_title' value='<s:text name='key_pagina_title'/>'/>
        <input type='hidden' id='key_pagina_modificacionUsuario' value='<s:text name='key_pagina_modificacionUsuario'/>'/>
        <input type='hidden' id='key_pagina_modificacionFecha' value='<s:text name='key_pagina_modificacionFecha'/>'/>
        <input type='hidden' id='paginaGridOrderByColumn' value='modificacionFecha'/>
        <input type='hidden' id='paginaGridOrderByType' value='des'/>
        <input type='hidden' id='paginaGridCurrentPage' value='1'/>
        <input type='hidden' id='paginaGridRowsByPage' value='10'/>
        <input type='hidden' id='idFromIframe' value=''/>
        <s:hidden id='gridVisiblePagina' name='gridVisiblePagina'/>
        <s:hidden id='gridIndividualModePagina' name='gridIndividualModePagina'/>

        <s:form id='paginaform' name='paginaform' cssClass='pair-wrap'>
            <div class='pair-hidden '>
                <div class='etiqueta' id='pagina_paginaIdText'>
                    <s:text name='key_pagina_paginaid'/>
                </div>
                <s:textfield name='paginaId' value='0' cssClass='isNumericInteger form-control' />
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta' id='pagina_nombreText'>
                    <s:text name='key_pagina_nombre'/>
                </div>
                <s:textfield name='nombre' maxLength='187' cssClass='textbox form-control'/>
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta' id='pagina_anidarText'>
                    <s:text name='key_pagina_anidar'/>
                </div>
                <select id='paginaform_anidar' class='select form-control'></select>
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta' id='pagina_urlText'>
                    <s:text name='key_pagina_url'/>
                </div>
                <s:textarea name='url' cssClass='textbox form-control'/>
            </div>
            <div class='pair col-sm-4 '>
                <div class='etiqueta' id='pagina_iconoText'>
                    <s:text name='key_pagina_icono'/>
                </div>
                <s:textfield name='icono' maxLength='187' cssClass='textbox form-control'/>
            </div>
            <div class='pair col-sm-4 '>
                <div class='etiqueta' id='pagina_ordenText'>
                    <s:text name='key_pagina_orden'/>
                </div>
                <s:textfield name='orden' maxLength='20' cssClass='textbox isNumericInteger form-control'/>
            </div>
            <div class='pair col-sm-4 '>
                <div class='pair-checkbox'>
                    <s:checkbox name='noIncluirEnMenu' cssClass='checkbox'/>
                    <div class='etiqueta-checkbox' id='pagina_noIncluirEnMenuText'>
                        <s:text name='key_pagina_noincluirenmenu'/>
                    </div>
                </div>
            </div>
            <div class='pair-hidden'>
                <div class='pair-checkbox'>
                    <s:checkbox name='privado' cssClass='checkbox'/>
                    <div class='etiqueta-checkbox' id='pagina_privadoText'>
                        <s:text name='key_pagina_privado'/>
                    </div>
                </div>
            </div>
            <div class='pair-hidden'>
                <div class='pair-checkbox'>
                    <s:checkbox name='sinGrid' cssClass='checkbox'/>
                    <div class='etiqueta-checkbox' id='pagina_sinGridText'>
                        <s:text name='key_pagina_singrid'/>
                    </div>
                </div>
            </div>
            <div class='pair-hidden'>
                <div class='pair-checkbox'>
                    <s:checkbox name='individual' cssClass='checkbox'/>
                    <div class='etiqueta-checkbox' id='pagina_individualText'>
                        <s:text name='key_pagina_individual'/>
                    </div>
                </div>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='pagina_estatusIdText'>
                    <s:text name='key_pagina_estatusid'/>
                </div>
                <s:select list='estatusPagina' listKey='valor' listValue='nombre' name='estatusId' cssClass='select form-control' />
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='pagina_eliminadoIdText'>
                    <s:text name='key_pagina_eliminadoid'/>
                </div>
                <s:select list='eliminadoPagina' listKey='valor' listValue='nombre' name='eliminadoId' cssClass='select form-control' />
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='pagina_creacionFechaText'>
                    <s:text name='key_pagina_creacionfecha'/>
                </div>
                <s:textfield name='creacionFecha' cssClass='textbox isDate form-control'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='pagina_creacionUsuarioText'>
                    <s:text name='key_pagina_creacionusuario'/>
                </div>
                <s:textfield name='creacionUsuario' maxLength='20' cssClass='textbox isNumericInteger form-control'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='pagina_modificacionFechaText'>
                    <s:text name='key_pagina_modificacionFecha'/>
                </div>
                <s:textfield name='modificacionFecha' cssClass='textbox isDate form-control'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='pagina_modificacionUsuarioText'>
                    <s:text name='key_pagina_modificacionusuario'/>
                </div>
                <s:textfield name='modificacionUsuario' maxLength='20' cssClass='textbox isNumericInteger form-control'/>
            </div>
            <div style='clear: both'></div>
        </s:form>

        <div class='pair-wrap divisor'></div>
        <div class='grid-container'>
            <table id='paginaGrid'></table>
            <div id='paginaGridPagerId'></div>
        </div>
        <div class='pair-wrap divisor'></div>
        <br>
    </div>
</body>

