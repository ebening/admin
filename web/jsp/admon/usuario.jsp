<%@taglib  prefix='s' uri='/struts-tags'%>
<head>
    <script src='<s:url value='../dwr/interface/UsuarioDWR.js'/>' type='text/javascript'></script>
    <script src='<s:url value='../js/admon/usuario.js'/>' type='text/javascript'></script>

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
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-default' id='btnNewUsuario'><s:text name='key_usuario_bnew'/></div>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-primary' id='btnSaveUsuario'><s:text name='key_usuario_bsave'/></div>
            </div>
            <div style='clear: both'></div>
        </div>
        <!-- Hidden -->
        <input type='hidden' id='key_usuario_title' value='<s:text name='key_usuario_title'/>'/>
        <input type='hidden' id='key_usuario_modificacionUsuario' value='<s:text name='key_usuario_modificacionUsuario'/>'/>
        <input type='hidden' id='key_usuario_modificacionFecha' value='<s:text name='key_usuario_modificacionFecha'/>'/>
        <input type='hidden' id='usuarioGridOrderByColumn' value='modificacionFecha'/>
        <input type='hidden' id='usuarioGridOrderByType' value='des'/>
        <input type='hidden' id='usuarioGridCurrentPage' value='1'/>
        <input type='hidden' id='usuarioGridRowsByPage' value='10'/>
        <input type='hidden' id='idFromIframe' value=''/>
        <!-- Textos de error -->
        <input type='hidden' id='key_usuariopagina_noselecciontitulo' value='<s:text name='key_usuariopagina_noselecciontitulo'/>'/>
        <input type='hidden' id='key_usuariopagina_noseleccionmensaje' value='<s:text name='key_usuariopagina_noseleccionmensaje'/>'/>
        <input type='hidden' id='key_usuariopagina_guardadocorrectotitulo' value='<s:text name='key_usuariopagina_guardadocorrectotitulo'/>'/>
        <input type='hidden' id='key_usuariopagina_guardadocorrectomensaje' value='<s:text name='key_usuariopagina_guardadocorrectomensaje'/>'/>
        <input type='hidden' id='key_usuariopagina_nohayaccesostitulo' value='<s:text name='key_usuariopagina_nohayaccesostitulo'/>'/>
        <input type='hidden' id='key_usuariopagina_nohayaccesosmensaje' value='<s:text name='key_usuariopagina_nohayaccesosmensaje'/>'/>
        <input type='hidden' id='key_usuariopagina_seleccionusuariotitulo' value='<s:text name='key_usuariopagina_seleccionusuariotitulo'/>'/>
        <input type='hidden' id='key_usuariopagina_seleccionusuariomensaje' value='<s:text name='key_usuariopagina_seleccionusuariomensaje'/>'/>

        <s:hidden id='gridVisibleUsuario' name='gridVisibleUsuario'/>
        <s:hidden id='gridIndividualModeUsuario' name='gridIndividualModeUsuario'/>
        <s:hidden id='gridVisibleUsuarioPagina' name='gridVisibleUsuarioPagina'/>
        <s:hidden id='gridIndividualModeUsuarioPagina' name='gridIndividualModeUsuarioPagina'/>

        <div class='col-sm-8'>
            <s:form id='usuarioform' name='usuarioform' cssClass='pair-wrap'>
                <div class='pair-hidden '>
                    <div class='etiqueta' id='usuario_usuarioIdText'>
                        <s:text name='key_usuario_usuarioid'/>
                    </div>
                    <s:textfield name='usuarioId' value='0' cssClass='isNumericInteger form-control' />
                </div>
                <div class='pair col-sm-4'>
                    <div class='etiqueta' id='usuario_usuarioText'>
                        <s:text name='key_usuario_usuario'/>
                    </div>
                    <s:textfield name='usuario' maxLength='37' cssClass='textbox form-control'/>
                </div>
                <div class='pair col-sm-4'>
                    <div class='etiqueta' id='usuario_contrasenaText'>
                        <s:text name='key_usuario_contrasena'/>
                    </div>
                    <s:password name='contrasena' cssClass='textbox form-control'/>
                </div>
                <div class='pair col-sm-4'>
                    <div class='etiqueta' >
                        <s:text name='key_usuario_confirmarcontrasena'/>
                    </div>
                    <s:password name='confirmarContrasena' maxLength='187' cssClass='textbox form-control'/>
                </div>
                <div class='pair col-sm-4'>
                    <div class='etiqueta' id='usuario_nombreText'>
                        <s:text name='key_usuario_nombre'/>
                    </div>
                    <s:textfield name='nombre' maxLength='187' cssClass='textbox form-control'/>
                </div>
                <div class='pair col-sm-4'>
                    <div class='etiqueta' id='usuario_apellidoPaternoText'>
                        <s:text name='key_usuario_apellidopaterno'/>
                    </div>
                    <s:textfield name='apellidoPaterno' maxLength='187' cssClass='textbox form-control'/>
                </div>
                <div class='pair col-sm-4'>
                    <div class='etiqueta' id='usuario_apellidoMaternoText'>
                        <s:text name='key_usuario_apellidomaterno'/>
                    </div>
                    <s:textfield name='apellidoMaterno' maxLength='187' cssClass='textbox form-control'/>
                </div>
                <div class='pair col-sm-4'>
                    <div class='etiqueta' id='usuario_correoText'>
                        <s:text name='key_usuario_correo'/>
                    </div>
                    <s:textfield name='correo' maxLength='187' cssClass='textbox form-control'/>
                </div>
                <div class='pair col-sm-4'>
                    <span class='etiqueta-small' id='usuario_perfilIdText'>
                        <s:text name='key_usuario_perfilid'/>
                    </span>
                    <s:select list='perfilUsuario' listKey='perfilId' listValue='nombre' name='perfilId' cssClass='select form-control' />
                </div>
                <div class='pair-hidden '>
                    <div class='etiqueta' id='usuario_estatusIdText'>
                        <s:text name='key_usuario_estatusid'/>
                    </div>
                    <s:select list='estatusUsuario' listKey='valor' listValue='nombre' name='estatusId' cssClass='select form-control' />
                </div>
                <div class='pair-hidden '>
                    <div class='etiqueta' id='usuario_eliminadoIdText'>
                        <s:text name='key_usuario_eliminadoid'/>
                    </div>
                    <s:select list='eliminadoUsuario' listKey='valor' listValue='nombre' name='eliminadoId' cssClass='select form-control' />
                </div>
                <div class='pair-hidden '>
                    <div class='etiqueta' id='CREACION_USUARIOFechaText'>
                        <s:text name='key_CREACION_USUARIOfecha'/>
                    </div>
                    <s:textfield name='creacionFecha' cssClass='textbox isDate form-control'/>
                </div>
                <div class='pair-hidden '>
                    <div class='etiqueta' id='CREACION_USUARIOUsuarioText'>
                        <s:text name='key_CREACION_USUARIOusuario'/>
                    </div>
                    <s:textfield name='creacionUsuario' maxLength='20' cssClass='textbox isNumericInteger form-control'/>
                </div>
                <div class='pair-hidden '>
                    <div class='etiqueta' id='usuario_modificacionFechaText'>
                        <s:text name='key_usuario_modificacionFecha'/>
                    </div>
                    <s:textfield name='modificacionFecha' cssClass='textbox isDate form-control'/>
                </div>
                <div class='pair-hidden '>
                    <div class='etiqueta' id='usuario_modificacionUsuarioText'>
                        <s:text name='key_usuario_modificacionusuario'/>
                    </div>
                    <s:textfield name='modificacionUsuario' maxLength='20' cssClass='textbox isNumericInteger form-control'/>
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

        <div class="col-sm-12">
            <div class='pair-wrap divisor'></div>
            <div class='grid-container'>
                <table id='usuarioGrid'></table>
                <div id='usuarioGridPagerId'></div>
            </div>
            <div class='pair-wrap divisor'></div>
            <br>
        </div>
    </div>
</body>

