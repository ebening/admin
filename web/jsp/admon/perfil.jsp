<%@taglib  prefix='s' uri='/struts-tags'%>
<head>
    <script src='<s:url value='../dwr/interface/PerfilDWR.js'/>' type='text/javascript'></script>
    <script src='<s:url value='../js/admon/perfil.js'/>' type='text/javascript'></script>
    <title>
        <s:property escape='false' value='nombreActionMenu'/>
    </title>
</head>
<body>
    <div class='form-wrap panel-body'>
        <div class='titulo ui-corner-top'>
            <div class='title-text hidden'><s:property escape='false' value='nombreActionMenu'/></div>
            <div class='button-container'>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-default' id='btnNewPerfil'><s:text name='key_perfil_bnew'/></div>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-primary' id='btnSavePerfil'><s:text name='key_perfil_bsave'/></div>
            </div>
            <div style='clear: both'></div>
        </div>
        <!-- Hidden -->
        <input type='hidden' id='key_perfil_title' value='<s:text name='key_perfil_title'/>'/>
        <input type='hidden' id='key_perfil_modificacionUsuario' value='<s:text name='key_perfil_modificacionUsuario'/>'/>
        <input type='hidden' id='key_perfil_modificacionFecha' value='<s:text name='key_perfil_modificacionFecha'/>'/>
        <input type='hidden' id='perfilGridOrderByColumn' value='modificacionFecha'/>
        <input type='hidden' id='perfilGridOrderByType' value='des'/>
        <input type='hidden' id='perfilGridCurrentPage' value='1'/>
        <input type='hidden' id='perfilGridRowsByPage' value='10'/>
        <input type='hidden' id='idFromIframe' value=''/>
        <s:hidden id='gridVisiblePerfil' name='gridVisiblePerfil'/>
        <s:hidden id='gridIndividualModePerfil' name='gridIndividualModePerfil'/>
        <s:hidden id='gridVisiblePerfilPagina' name='gridVisiblePerfilPagina'/>
        <s:hidden id='gridIndividualModePerfilPagina' name='gridIndividualModePerfilPagina'/>

        <s:form id='perfilform' name='perfilform' cssClass='pair-wrap'>
            <div class='pair-hidden '>
                <div class='etiqueta' id='perfil_perfilIdText'>
                    <s:text name='key_perfil_perfilid'/>
                </div>
                <s:textfield name='perfilId' value='0' cssClass='isNumericInteger' />
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta' id='perfil_nombreText'>
                    <s:text name='key_perfil_nombre'/>
                </div>
                <s:textfield name='nombre' maxLength='187' cssClass='textbox form-control'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='perfil_estatusIdText'>
                    <s:text name='key_perfil_estatusid'/>
                </div>
                <s:select list='estatusPerfil' listKey='valor' listValue='nombre' name='estatusId' cssClass='select' />
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='perfil_eliminadoIdText'>
                    <s:text name='key_perfil_eliminadoid'/>
                </div>
                <s:select list='eliminadoPerfil' listKey='valor' listValue='nombre' name='eliminadoId' cssClass='select' />
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='perfil_creacionFechaText'>
                    <s:text name='key_perfil_creacionfecha'/>
                </div>
                <s:textfield name='creacionFecha' cssClass='textbox isDate'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='perfil_creacionUsuarioText'>
                    <s:text name='key_perfil_creacionusuario'/>
                </div>
                <s:textfield name='creacionUsuario' maxLength='20' cssClass='textbox isNumericInteger'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='perfil_modificacionFechaText'>
                    <s:text name='key_perfil_modificacionFecha'/>
                </div>
                <s:textfield name='modificacionFecha' cssClass='textbox isDate'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='perfil_modificacionUsuarioText'>
                    <s:text name='key_perfil_modificacionusuario'/>
                </div>
                <s:textfield name='modificacionUsuario' maxLength='20' cssClass='textbox isNumericInteger'/>
            </div>
            <div style='clear: both'></div>
        </s:form>
        <!-- Divisor -->
        <div class='pair-wrap divisor hidden'></div>
        <div class='gradient1 border pair-wrap hidden'>
            <!-- Titulo -->
            <input type='hidden' id='key_perfilpagina_title' value='<s:text name='key_perfilpagina_title'/>'/>
            <!-- Botón Agregar -->
            <div class='button-container-detalle'>
                <div class='button button-detalle' id='btnAddPerfilPagina'><s:text name='key_perfilpagina_badd'/></div>
                <div class='hidden button button-detalle' id='btnAddPerfilPaginaNoValidation'><s:text name='key_perfilpagina_badd'/></div>
                <div style='clear: both'></div>
            </div>
            <s:form id='perfilPaginaform' name='perfilPaginaform' cssClass='pair-wrap'>
                <div class='pair-hidden '>
                    <div class='etiqueta' id='perfilPagina_filaIdText'>
                        filaId
                    </div>
                    <s:textfield name='filaId' value='0' cssClass='isNumericInteger' />
                </div>
                <div class='pair-hidden '>
                    <div class='etiqueta' id='perfilPagina_perfilPaginaIdText'>
                        <s:text name='key_perfilpagina_perfilpaginaid'/>
                    </div>
                    <s:textfield name='perfilPaginaId' value='0' cssClass='isNumericInteger' />
                </div>
                <div class='pair-hidden '>
                    <span class='etiqueta-small' id='perfilPagina_perfilIdText'>
                        <s:text name='key_perfilpagina_perfilid'/>
                    </span>
                    <s:property escape='false' value='perfilPerfilPaginaLink'/>
                    <s:select list='perfilPerfilPagina' listKey='perfilId' listValue='nombre' name='perfilId' cssClass='select' />
                </div>
                <div class='pair-hidden '>
                    <span class='etiqueta-small' id='perfilPagina_paginaIdText'>
                        <s:text name='key_perfilpagina_paginaid'/>
                    </span>
                    <s:property escape='false' value='paginaPerfilPaginaLink'/>
                    <s:select list='paginaPerfilPagina' listKey='paginaId' listValue='nombre' name='paginaId' cssClass='select' />
                </div>
                <div class='pair-hidden '>
                    <div class='etiqueta' id='perfilPagina_estatusIdText'>
                        <s:text name='key_perfilpagina_estatusid'/>
                    </div>
                    <s:select list='estatusPerfilPagina' listKey='valor' listValue='nombre' name='estatusId' cssClass='select' />
                </div>
                <div class='pair-hidden '>
                    <div class='etiqueta' id='perfilPagina_eliminadoIdText'>
                        <s:text name='key_perfilpagina_eliminadoid'/>
                    </div>
                    <s:select list='eliminadoPerfilPagina' listKey='valor' listValue='nombre' name='eliminadoId' cssClass='select' />
                </div>
                <div class='pair-hidden '>
                    <div class='etiqueta' id='perfilPagina_creacionFechaText'>
                        <s:text name='key_perfilpagina_creacionfecha'/>
                    </div>
                    <s:textfield name='creacionFecha' cssClass='textbox isDate'/>
                </div>
                <div class='pair-hidden '>
                    <div class='etiqueta' id='perfilPagina_creacionUsuarioText'>
                        <s:text name='key_perfilpagina_creacionusuario'/>
                    </div>
                    <s:textfield name='creacionUsuario' maxLength='20' cssClass='textbox isNumericInteger'/>
                </div>
                <div class='pair-hidden '>
                    <div class='etiqueta' id='perfilPagina_modificacionFechaText'>
                        <s:text name='key_perfilpagina_modificacionFecha'/>
                    </div>
                    <s:textfield name='modificacionFecha' cssClass='textbox isDate'/>
                </div>
                <div class='pair-hidden '>
                    <div class='etiqueta' id='perfilPagina_modificacionUsuarioText'>
                        <s:text name='key_perfilpagina_modificacionusuario'/>
                    </div>
                    <s:textfield name='modificacionUsuario' maxLength='20' cssClass='textbox isNumericInteger'/>
                </div>
                <div style='clear: both'></div>
            </s:form>

            <div class='grid-container'>
                <table id='perfilPaginaGrid'></table>
                <div id='perfilPaginaGridPagerId'></div>
            </div>
        </div>

        <div class='pair-wrap divisor'></div>
        <div class='grid-container'>
            <table id='perfilGrid'></table>
            <div id='perfilGridPagerId'></div>
        </div>
        <div class='pair-wrap divisor'></div>
        <br>
    </div>
</body>

