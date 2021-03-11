<%@taglib  prefix='s' uri='/struts-tags'%>
<head>
    <script src='<s:url value='../dwr/interface/PerfilPaginaDWR.js'/>' type='text/javascript'></script>
    <script src='<s:url value='../js/admon/perfilPagina.js'/>' type='text/javascript'></script>

    <!-- Accesos -->
    <script src='<s:url value='../js/jquerycomponentes/jquery.jstree.js'/>' type='text/javascript'></script>

    <title>
        <s:property escape='false' value='nombreActionMenu'/>
    </title>
</head>
<body>
    <div class='form-wrap panel-body'>
        <div class='titulo ui-corner-top'>
            <div class='title-text hidden'><s:property escape='false' value='nombreActionMenu'/></div>
            <div class='button-container'>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-default' id='btnNewPerfilPagina'><s:text name='key_perfilpagina_bnew'/></div>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-primary' id='btnSavePerfilPagina'><s:text name='key_perfilpagina_bsave'/></div>
            </div>
            <div style='clear: both'></div>
        </div>
        <!-- Hidden -->
        <input type='hidden' id='key_perfilpagina_title' value='<s:text name='key_perfilpagina_title'/>'/>
        <input type='hidden' id='key_perfilpagina_modificacionUsuario' value='<s:text name='key_perfilpagina_modificacionUsuario'/>'/>
        <input type='hidden' id='key_perfilpagina_modificacionFecha' value='<s:text name='key_perfilpagina_modificacionFecha'/>'/>
        <input type='hidden' id='perfilPaginaGridOrderByColumn' value='modificacionFecha'/>
        <input type='hidden' id='perfilPaginaGridOrderByType' value='des'/>
        <input type='hidden' id='perfilPaginaGridCurrentPage' value='1'/>
        <input type='hidden' id='perfilPaginaGridRowsByPage' value='10'/>
        <input type='hidden' id='idFromIframe' value=''/>
        <s:hidden id='gridVisiblePerfilPagina' name='gridVisiblePerfilPagina'/>
        <s:hidden id='gridIndividualModePerfilPagina' name='gridIndividualModePerfilPagina'/>

        <!-- Textos de error -->
        <input type='hidden' id='key_usuariopagina_noselecciontitulo' value='<s:text name='key_usuariopagina_noselecciontitulo'/>'/>
        <input type='hidden' id='key_usuariopagina_noseleccionmensaje' value='<s:text name='key_usuariopagina_noseleccionmensaje'/>'/>
        <input type='hidden' id='key_usuariopagina_guardadocorrectotitulo' value='<s:text name='key_usuariopagina_guardadocorrectotitulo'/>'/>
        <input type='hidden' id='key_usuariopagina_guardadocorrectomensaje' value='<s:text name='key_usuariopagina_guardadocorrectomensaje'/>'/>
        <input type='hidden' id='key_usuariopagina_nohayaccesostitulo' value='<s:text name='key_usuariopagina_nohayaccesostitulo'/>'/>
        <input type='hidden' id='key_usuariopagina_nohayaccesosmensaje' value='<s:text name='key_usuariopagina_nohayaccesosmensaje'/>'/>
        <input type='hidden' id='key_usuariopagina_seleccionusuariotitulo' value='<s:text name='key_usuariopagina_seleccionusuariotitulo'/>'/>
        <input type='hidden' id='key_usuariopagina_seleccionusuariomensaje' value='<s:text name='key_usuariopagina_seleccionusuariomensaje'/>'/>
        <div class='col-sm-8'>
            <s:form id='perfilPaginaform' name='perfilPaginaform' cssClass='pair-wrap'>
                <div class='pair-hidden '>
                    <div class='etiqueta' id='perfilPagina_perfilPaginaIdText'>
                        <s:text name='key_perfilpagina_perfilpaginaid'/>
                    </div>
                    <s:textfield name='perfilPaginaId' value='0' cssClass='isNumericInteger' />
                </div>
                <div class='pair col-sm-4'>
                    <div class='etiqueta' id='perfilPagina_perfilIdText'>
                        <s:text name='key_perfilpagina_perfilid'/>
                    </div>
                    <s:select list='perfilPerfilPagina' listKey='perfilId' listValue='nombre' name='perfilId' cssClass='select form-control' />
                </div>
                <div class='pair-hidden '>
                    <div class='etiqueta' id='perfilPagina_paginaIdText'>
                        <s:text name='key_perfilpagina_paginaid'/>
                    </div>
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
            </s:form>
        </div>
        <div class='col-sm-4'>
            <div class='alert alert-info'>
                <div>Accesos por Perfil<br> 
                    <div style="font-size: 10px;">Para modificar los accesos utilize la pantalla de Perfil</div></div>
            </div>
            <div class='tree-container panel-body-nopadding border'>
                <div class='accesos-container'>
                    <div class='accesos'></div>
                </div>
                <div class='paginas'>
                </div>
            </div>
        </div>

        <div class="clear-both"></div>
        <br>
    </div>
</div>
</body>

