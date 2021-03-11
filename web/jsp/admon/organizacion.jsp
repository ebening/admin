<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib  prefix='s' uri='/struts-tags'%>
<head>

    <script src='<s:url value='../dwr/interface/OrganizacionDWR.js'/>' type='text/javascript'  charset="UTF-8"></script>
    <script src='<s:url value='../js/admon/organizacion.js'/>' type='text/javascript'  charset="UTF-8"></script>
    <title>
        <s:property escape='false' value='nombreActionMenu'/>
    </title>
</head>
<body>

    <div class='form-wrap panel-body'>
        <div class='titulo ui-corner-top'>
            <div class='title-text hidden'><s:property escape='false' value='nombreActionMenu'/></div>
            <div class='button-container'>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-default' id='btnNewOrganizacion'><s:text name='key_organizacion_bnew'/></div>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-primary' id='btnSaveOrganizacion'><s:text name='key_organizacion_bsave'/></div>
            </div>
            <div style='clear: both'></div>
        </div>
        <!-- Hidden -->
        <input type='hidden' id='key_organizacion_title' value='<s:text name='key_organizacion_title'/>'/>
        <input type='hidden' id='key_organizacion_modificacionUsuario' value='<s:text name='key_organizacion_modificacionUsuario'/>'/>
        <input type='hidden' id='key_organizacion_modificacionFecha' value='<s:text name='key_organizacion_modificacionFecha'/>'/>
        <input type='hidden' id='organizacionGridOrderByColumn' value='modificacionFecha'/>
        <input type='hidden' id='organizacionGridOrderByType' value='des'/>
        <input type='hidden' id='organizacionGridCurrentPage' value='1'/>
        <input type='hidden' id='organizacionGridRowsByPage' value='10'/>
        <input type='hidden' id='idFromIframe' value=''/>
        <s:hidden id='gridVisibleOrganizacion' name='gridVisibleOrganizacion'/>
        <s:hidden id='gridIndividualModeOrganizacion' name='gridIndividualModeOrganizacion'/>
        <s:hidden id='gridVisibleOrganizacionCredencial' name='gridVisibleOrganizacionCredencial'/>
        <s:hidden id='gridIndividualModeOrganizacionCredencial' name='gridIndividualModeOrganizacionCredencial'/>
        <s:hidden id='gridVisibleOrganizacionUsuario' name='gridVisibleOrganizacionUsuario'/>
        <s:hidden id='gridIndividualModeOrganizacionUsuario' name='gridIndividualModeOrganizacionUsuario'/>
        <div class="panel-body tabs">
            <ul class="nav nav-tabs">
                <!--<ul class="nav nav-pills">-->
                <li class="panelOrganizacion active"><a href="#" data-toggle="tab">Organizacion</a></li>
                <li class="panelOrganizacionCredencial"><a href="#" data-toggle="tab">Credencial</a></li>
                <li class="panelOrganizacionUsuario"><a href="#" data-toggle="tab">Usuario</a></li>
                <li class="panelOrganizacionGenerar"><a href="#" data-toggle="tab">Generar</a></li>
            </ul>	
            <div id='panelOrganizacion' style="background-color: #F6F6F6;">

                <div class='pair-wrap divisor'></div>
                <!-- Divisor -->
                <!-- Titulo -->
                <!--                <div class='title-text alert alert-info'><s:text name='key_organizacion_title'/>
                                </div>  -->
                <s:form id='organizacionform' name='organizacionform' cssClass='pair-wrap'>
                    <div class='pair-hidden '>
                        <div class='etiqueta' id='organizacion_organizacionIdText'>
                            <s:text name='key_organizacion_organizacionid'/>
                        </div>
                        <s:textfield name='organizacionId' value='0' cssClass='isNumericInteger' />
                    </div>
                    <div class='pair col-sm-5'>
                        <div class='etiqueta' id='organizacion_nombreText'>
                            <s:text name='key_organizacion_nombre'/>
                        </div>
                        <s:textfield name='nombre' maxLength='187' cssClass='textbox  form-control'/>
                    </div>
                    <div class='pair col-sm-4 '>
                        <div class='etiqueta' id='organizacion_nombreComercialText'>
                            <s:text name='key_organizacion_nombrecomercial'/>
                        </div>
                        <s:textfield name='nombreComercial' maxLength='187' cssClass='textbox  form-control'/>
                    </div>
                    <div class='pair col-sm-3'>
                        <div class='etiqueta' id='organizacion_nombreCortoText'>
                            <s:text name='key_organizacion_nombrecorto'/>
                        </div>
                        <s:textfield name='nombreCorto' maxLength='187' cssClass='textbox  form-control'/>
                    </div>
                    <div class='pair col-sm-9'>
                        <div class='etiqueta' id='organizacion_descripcionText'>
                            <s:text name='key_organizacion_descripcion'/>
                        </div>
                        <s:textfield name='descripcion' cssClass='textbox form-control'/>
                    </div>
                    <div class='pair col-sm-3 '>
                        <div class='etiqueta' id='organizacion_rfcText'>
                            <s:text name='key_organizacion_rfc'/>
                        </div>
                        <s:textfield name='rfc' maxLength='187' cssClass='textbox  form-control'/>
                    </div>
                    <div class='pair col-sm-6 '>
                        <div class='etiqueta' id='organizacion_direccionText'>
                            <s:text name='key_organizacion_direccion'/>
                        </div>
                        <s:textfield name='direccion' maxLength='187' cssClass='textbox  form-control'/>
                    </div>
                    <div class='pair col-sm-6 '>
                        <div class='etiqueta' id='organizacion_direccionAlternativaText'>
                            <s:text name='key_organizacion_direccionalternativa'/>
                        </div>
                        <s:textfield name='direccionAlternativa' maxLength='187' cssClass='textbox  form-control'/>
                    </div>
                    <div class='pair col-sm-4 '>
                        <span class='etiqueta-small' id='organizacion_paisIdText'>
                            <s:text name='key_organizacion_paisid'/>
                        </span>
                        <%--<s:property escape='false' value='paisOrganizacionLink'/>--%>
                        <s:select list='paisOrganizacion' listKey='paisId' listValue='nombre' name='paisId' cssClass='select form-control ' />
                    </div>
                    <div class='pair col-sm-4 '>
                        <span class='etiqueta-small' id='organizacion_estadoIdText'>
                            <s:text name='key_organizacion_estadoid'/>
                        </span>
                        <%--<s:property escape='false' value='estadoOrganizacionLink'/>--%>
                        <s:select list='estadoOrganizacion' listKey='estadoId' listValue='nombre' name='estadoId' cssClass='select form-control ' />
                    </div>
                    <div class='pair col-sm-4 '>
                        <span class='etiqueta-small' id='organizacion_ciudadIdText'>
                            <s:text name='key_organizacion_ciudadid'/>
                        </span>
                        <%--<s:property escape='false' value='ciudadOrganizacionLink'/>--%>
                        <s:select list='ciudadOrganizacion' listKey='ciudadId' listValue='nombre' name='ciudadId' cssClass='select form-control ' />
                    </div>
                    <div style='clear: both'></div>
                    <div class='pair-wrap divisor'></div>
                    <div class='etiqueta' >
                        Contactos
                    </div>
                    <div class='pair col-sm-4 '>
                        <div class='etiqueta' id='organizacion_nombreContactoText'>
                            <s:text name='key_organizacion_nombrecontacto'/>
                        </div>
                        <s:textfield name='nombreContacto' maxLength='187' cssClass='textbox  form-control'/>
                    </div>
                    <div class='pair col-sm-4 '>
                        <div class='etiqueta' id='organizacion_correoContactoText'>
                            <s:text name='key_organizacion_correocontacto'/>
                        </div>
                        <s:textfield name='correoContacto' maxLength='187' cssClass='textbox  form-control'/>
                    </div>
                    <div class='pair col-sm-4 '>
                        <div class='etiqueta' id='organizacion_telefonoContactoText'>
                            <s:text name='key_organizacion_telefonocontacto'/>
                        </div>
                        <s:textfield name='telefonoContacto' maxLength='187' cssClass='textbox  form-control'/>
                    </div>
                    <div class='pair col-sm-4 '>
                        <div class='etiqueta' id='organizacion_celularContactoText'>
                            <s:text name='key_organizacion_celularcontacto'/>
                        </div>
                        <s:textfield name='celularContacto' maxLength='187' cssClass='textbox  form-control'/>
                    </div>
                    <div class='pair col-sm-4 '>
                        <div class='etiqueta' id='organizacion_creaEventosIdText'>
                            <s:text name='key_organizacion_creaeventosid'/>
                        </div>
                        <s:select list='creaEventosOrganizacion' listKey='valor' listValue='nombre' name='creaEventosId' cssClass='select form-control' />
                    </div>
                    <div class='pair col-sm-4 '>
                        <div class='etiqueta' id='organizacion_servicioCompletoIdText'>
                            <s:text name='key_organizacion_serviciocompletoid'/>
                        </div>
                        <s:select list='servicioCompletoOrganizacion' listKey='valor' listValue='nombre' name='servicioCompletoId' cssClass='select form-control' />
                    </div>
                    <div class='pair-hidden '>
                        <div class='etiqueta' id='organizacion_eliminadoIdText'>
                            <s:text name='key_organizacion_eliminadoid'/>
                        </div>
                        <s:select list='eliminadoOrganizacion' listKey='valor' listValue='nombre' name='eliminadoId' cssClass='select form-control' />
                    </div>
                    <div class='pair-hidden '>
                        <div class='etiqueta' id='organizacion_creacionFechaText'>
                            <s:text name='key_organizacion_creacionfecha'/>
                        </div>
                        <s:textfield name='creacionFecha' cssClass='textbox  form-control isDate'/>
                    </div>
                    <div class='pair-hidden '>
                        <div class='etiqueta' id='organizacion_creacionUsuarioText'>
                            <s:text name='key_organizacion_creacionusuario'/>
                        </div>
                        <s:textfield name='creacionUsuario' maxLength='9' cssClass='textbox  form-control isNumericInteger'/>
                    </div>
                    <div class='pair-hidden '>
                        <div class='etiqueta' id='organizacion_modificacionFechaText'>
                            <s:text name='key_organizacion_modificacionFecha'/>
                        </div>
                        <s:textfield name='modificacionFecha' cssClass='textbox  form-control isDate'/>
                    </div>
                    <div class='pair-hidden '>
                        <div class='etiqueta' id='organizacion_modificacionUsuarioText'>
                            <s:text name='key_organizacion_modificacionusuario'/>
                        </div>
                        <s:textfield name='modificacionUsuario' maxLength='9' cssClass='textbox  form-control isNumericInteger'/>
                    </div>
                    <div style='clear: both'></div>
                </s:form>
                <div class='pair-wrap divisor'></div>
                <div class='grid-container'>
                    <table id='organizacionGrid'></table>
                    <div id='organizacionGridPagerId'></div>

                    <div class='pair-wrap divisor'></div>
                    <br>
                </div>
            </div>
            <div id='panelOrganizacionCredencial'>
                <div class='pair-wrap divisor'></div>
                <!-- Divisor -->
                <div class='pair-wrap' style="background-color: #F6F6F6;">
                    <!-- Titulo -->
                    <input type='hidden' id='key_organizacioncredencial_title' value='<s:text name='key_organizacioncredencial_title'/>'/>
                    <div class='title-text alert alert-info'><s:text name='key_organizacioncredencial_title'/>
                        <!-- BotÃ³n Agregar -->
                        <div class='button-container-detalle'>
                            <div class='button-custom button-detalle mb-xs mt-xs mr-xs btn btn-default' id='btnAddOrganizacionCredencial'><s:text name='key_organizacioncredencial_badd'/></div>
                            <div class='hidden button button-detalle mb-xs mt-xs mr-xs btn btn-default' id='btnAddOrganizacionCredencialNoValidation'><s:text name='key_organizacioncredencial_badd'/></div>
                            <div style='clear: both'></div>
                        </div>
                    </div>
                    <s:form id='organizacionCredencialform' name='organizacionCredencialform' cssClass='pair-wrap'>
                        <div class='pair-hidden '>
                            <div class='etiqueta' id='organizacionCredencial_filaIdText'>
                                filaId
                            </div>
                            <s:textfield name='filaId' value='0' cssClass='isNumericInteger' />
                        </div>
                        <div class='pair-hidden '>
                            <div class='etiqueta' id='organizacionCredencial_organizacionCredencialIdText'>
                                <s:text name='key_organizacioncredencial_organizacioncredencialid'/>
                            </div>
                            <s:textfield name='organizacionCredencialId' value='0' cssClass='isNumericInteger' />
                        </div>
                        <div class='pair-hidden '>
                            <span class='etiqueta-small' id='organizacionCredencial_organizacionIdText'>
                                <s:text name='key_organizacioncredencial_organizacionid'/>
                            </span>
                            <%--<s:property escape='false' value='organizacionOrganizacionCredencialLink'/>--%>
                            <s:select list='organizacionOrganizacionCredencial' listKey='organizacionId' listValue='nombre' name='organizacionId' cssClass='select form-control' />
                        </div>
                        <div class='pair col-sm-5 '>
                            <div class='etiqueta' id='organizacionCredencial_nombreText'>
                                <s:text name='key_organizacioncredencial_nombre'/>
                            </div>
                            <s:textfield name='nombre' maxLength='187' cssClass='textbox  form-control'/>
                        </div>
                        <div class='pair col-sm-4 '>
                            <div class='etiqueta' id='organizacionCredencial_domainText'>
                                <s:text name='key_organizacioncredencial_domain'/>
                            </div>
                            <s:textfield name='domain' maxLength='187' cssClass='textbox  form-control'/>
                        </div>
                        <div class='pair col-sm-3'>
                            <span class='etiqueta-small' id='organizacionCredencial_aplicacionIdText'>
                                <s:text name='key_organizacioncredencial_aplicacion'/>
                            </span>
                            <s:select list='organizacionCredencialesAplicaciones' listKey='valor' listValue='nombre' name='aplicacionId' cssClass='select form-control ' />
                        </div>
<!--                        <div class='pair-hidden '>
                            <div class='etiqueta' id='organizacionCredencial_idcredencialText'>
                                <s:text name='key_organizacioncredencial_idcredencial'/>
                            </div>
                            <s:textfield name='idcredencial' maxLength='187' cssClass='textbox  form-control'/>
                        </div>-->
                        <div class='pair col-sm-4 '>
                            <div class='etiqueta' id='organizacionCredencial_idanalyticsText'>
                                <s:text name='key_organizacioncredencial_idanalytics'/>
                            </div>
                            <s:textfield name='idanalytics' maxLength='187' cssClass='textbox  form-control'/>
                        </div>
                        <div class='pair col-sm-4 '>
                            <div class='etiqueta' id='organizacionCredencial_idappText'>
                                <s:text name='key_organizacioncredencial_idapp'/>
                            </div>
                            <s:textfield name='idapp' maxLength='187' cssClass='textbox  form-control'/>
                        </div>
                        <div class='pair col-sm-4 '>
                            <div class='etiqueta' id='organizacionCredencial_idrecapchaText'>
                                <s:text name='key_organizacioncredencial_idrecapcha'/>
                            </div>
                            <s:textfield name='idrecapcha' maxLength='187' cssClass='textbox  form-control'/>
                        </div>
                        <div class='pair-hidden '>
                            <div class='etiqueta' id='organizacionCredencial_estatusIdText'>
                                <s:text name='key_organizacioncredencial_estatusid'/>
                            </div>
                            <s:select list='estatusOrganizacionCredencial' listKey='valor' listValue='nombre' name='estatusId' cssClass='select form-control' />
                        </div>
                        <div class='pair-hidden '>
                            <div class='etiqueta' id='organizacionCredencial_eliminadoIdText'>
                                <s:text name='key_organizacioncredencial_eliminadoid'/>
                            </div>
                            <s:select list='eliminadoOrganizacionCredencial' listKey='valor' listValue='nombre' name='eliminadoId' cssClass='select form-control' />
                        </div>
                        <div class='pair-hidden '>
                            <div class='etiqueta' id='organizacionCredencial_creacionFechaText'>
                                <s:text name='key_organizacioncredencial_creacionfecha'/>
                            </div>
                            <s:textfield name='creacionFecha' cssClass='textbox  form-control isDate'/>
                        </div>
                        <div class='pair-hidden '>
                            <div class='etiqueta' id='organizacionCredencial_creacionUsuarioText'>
                                <s:text name='key_organizacioncredencial_creacionusuario'/>
                            </div>
                            <s:textfield name='creacionUsuario' maxLength='9' cssClass='textbox  form-control isNumericInteger'/>
                        </div>
                        <div class='pair-hidden '>
                            <div class='etiqueta' id='organizacionCredencial_modificacionFechaText'>
                                <s:text name='key_organizacioncredencial_modificacionFecha'/>
                            </div>
                            <s:textfield name='modificacionFecha' cssClass='textbox  form-control isDate'/>
                        </div>
                        <div class='pair-hidden '>
                            <div class='etiqueta' id='organizacionCredencial_modificacionUsuarioText'>
                                <s:text name='key_organizacioncredencial_modificacionusuario'/>
                            </div>
                            <s:textfield name='modificacionUsuario' maxLength='9' cssClass='textbox  form-control isNumericInteger'/>
                        </div>
                        <div style='clear: both'></div>
                    </s:form>
                    <div class='pair-wrap divisor'></div>
                    <div class='grid-container'>
                        <table id='organizacionCredencialGrid'></table>
                        <div id='organizacionCredencialGridPagerId'></div>
                    </div>
                </div>
            </div>
            <div id='panelOrganizacionUsuario'>
                <div class='pair-wrap divisor'></div>
                <!-- Divisor -->
                <div class='pair-wrap' style="background-color: #F6F6F6;">
                    <!-- Titulo -->
                    <input type='hidden' id='key_organizacionusuario_title' value='<s:text name='key_organizacionusuario_title'/>'/>
                    <div class='title-text alert alert-info'><s:text name='key_organizacionusuario_title'/>
                        <!-- BotÃ³n Agregar -->
                        <div class='button-container-detalle'>
                            <div class='button-custom button-detalle mb-xs mt-xs mr-xs btn btn-default' id='btnAddOrganizacionUsuario'><s:text name='key_organizacionusuario_badd'/></div>
                            <div class='hidden button button-detalle mb-xs mt-xs mr-xs btn btn-default' id='btnAddOrganizacionUsuarioNoValidation'><s:text name='key_organizacionusuario_badd'/></div>
                            <div style='clear: both'></div>
                        </div>
                    </div>
                    <s:form id='organizacionUsuarioform' name='organizacionUsuarioform' cssClass='pair-wrap'>
                        <div class='pair-hidden '>
                            <div class='etiqueta' id='organizacionUsuario_filaIdText'>
                                filaId
                            </div>
                            <s:textfield name='filaId' value='0' cssClass='isNumericInteger' />
                        </div>
                        <div class='pair-hidden '>
                            <div class='etiqueta' id='organizacionUsuario_organizacionUsuarioIdText'>
                                <s:text name='key_organizacionusuario_organizacionusuarioid'/>
                            </div>
                            <s:textfield name='organizacionUsuarioId' value='0' cssClass='isNumericInteger' />
                        </div>
                        <div class='pair-hidden '>
                            <span class='etiqueta-small' id='organizacionUsuario_organizacionIdText'>
                                <s:text name='key_organizacionusuario_organizacionid'/>
                            </span>
                            <%--<s:property escape='false' value='organizacionOrganizacionUsuarioLink'/>--%>
                            <s:select list='organizacionOrganizacionUsuario' listKey='organizacionId' listValue='nombre' name='organizacionId' cssClass='select' />
                        </div>
                        <div class='pair col-sm-4'>
                            <div class='etiqueta' id='organizacionUsuario_usuarioAdmonText'>
                                <s:text name='key_organizacionusuario_usuarioadmon'/>
                            </div>
                            <s:textfield name='usuarioAdmon' maxLength='187' cssClass='textbox form-control'/>
                        </div>
                        <div class='pair col-sm-4'>
                            <div class='etiqueta' id='organizacionUsuario_contrasenaText'>
                                <s:text name='key_organizacionusuario_contrasena'/>
                            </div>
                            <s:password name='contrasena' cssClass='textbox form-control'/>
                        </div>
                        <div class='pair col-sm-4'>
                            <div class='etiqueta' >
                                <s:text name='key_organizacionusuario_confirmarcontrasena'/>
                            </div>
                            <s:password name='confirmarContrasena' maxLength='187' cssClass='textbox form-control'/>
                        </div>
                        <div class='pair col-sm-4'>
                            <div class='etiqueta' id='organizacionUsuario_nombreText'>
                                <s:text name='key_organizacionusuario_nombre'/>
                            </div>
                            <s:textfield name='nombre' maxLength='187' cssClass='textbox form-control'/>
                        </div>
                        <div class='pair col-sm-4'>
                            <div class='etiqueta' id='organizacionUsuario_apellidoPaternoText'>
                                <s:text name='key_organizacionusuario_apellidopaterno'/>
                            </div>
                            <s:textfield name='apellidoPaterno' maxLength='187' cssClass='textbox form-control'/>
                        </div>
                        <div class='pair col-sm-4'>
                            <div class='etiqueta' id='organizacionUsuario_apellidoMaternoText'>
                                <s:text name='key_organizacionusuario_apellidomaterno'/>
                            </div>
                            <s:textfield name='apellidoMaterno' maxLength='187' cssClass='textbox form-control'/>
                        </div>
                        <div class='pair col-sm-4'>
                            <div class='etiqueta' id='organizacionUsuario_correoText'>
                                <s:text name='key_organizacionusuario_correo'/>
                            </div>
                            <s:textfield name='correo' maxLength='187' cssClass='textbox form-control'/>
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
                            <div class='etiqueta' id='organizacionUsuario_creacionFechaText'>
                                <s:text name='key_organizacionusuario_creacionfecha'/>
                            </div>
                            <s:textfield name='creacionFecha' cssClass='textbox form-control isDate'/>
                        </div>
                        <div class='pair-hidden '>
                            <div class='etiqueta' id='organizacionUsuario_creacionusuarioUsuarioText'>
                                <s:text name='key_organizacionUsuario_creacionusuariou'/>
                            </div>
                            <s:textfield name='creacionUsuario' maxLength='9' cssClass='textbox form-control isNumericInteger'/>
                        </div>
                        <div class='pair-hidden '>
                            <div class='etiqueta' id='organizacionUsuario_modificacionFechaText'>
                                <s:text name='key_organizacionusuario_modificacionFecha'/>
                            </div>
                            <s:textfield name='modificacionFecha' cssClass='textbox form-control isDate'/>
                        </div>
                        <div class='pair-hidden '>
                            <div class='etiqueta' id='organizacionUsuario_modificacionUsuarioText'>
                                <s:text name='key_organizacionusuario_modificacionusuario'/>
                            </div>
                            <s:textfield name='modificacionUsuario' maxLength='9' cssClass='textbox form-control isNumericInteger'/>
                        </div>
                        <div style='clear: both'></div>
                    </s:form>
                    <div class='pair-wrap divisor'></div>
                    <div class='grid-container'>
                        <table id='organizacionUsuarioGrid'></table>
                        <div id='organizacionUsuarioGridPagerId'></div>
                    </div>
                </div>
            </div>

            <div id='panelOrganizacionGenerar'>
                <div class='pair-wrap divisor'></div>
                <!-- Divisor -->
                <div class='pair-wrap' style="background-color: #F6F6F6;">
                    <!-- Titulo -->
                    <div class='title-text alert alert-info'><s:text name='Generar'/>
                    </div>

                    <div class='col-sm-7'>
                        <s:form id='organizacionGenerarform' name='organizacionGenerarform' cssClass='pair-wrap'>
                            <div class="box">
                                    <div class="box-header">
                                            <h2><i class="fa fa-list"></i>Requerimientos para creación</h2>

                        <div class='pair-hidden '>
                            <div class='etiqueta' id='organizacionGenerar_organizacionGenerarIdText'>
                                filaId
                            </div>
                            <s:textfield name='organizacionGenerarId' value='0' cssClass='isNumericInteger' />
                        </div>
                        <div class='pair-hidden '>
                            <div class='etiqueta' id='organizacionGenerar_nombreText'>
                                <s:text name='key_organizaciogenerar_nombre'/>
                            </div>
                            <s:textfield name='nombre' maxLength='187' cssClass='textbox form-control'/>
                        </div>
                        <div class='pair-hidden '>
                            <div class='etiqueta-small' id='organizacionGenerar_conceptoText'>
                                <s:text name='key_organizaciongenerar_concepto'/>
                            </div>
                            <s:textfield name='concepto' cssClass='isNumericInteger'/>
                        </div>
                         </s:form>                                           
                         <!--<div class='grid-container'>-->
                            <!--<table id='organizacionGenerarGrid'></table>-->
<!--                           <div id='organizacionGenerarGridPagerId'></div>-->
                        <!--</div>-->
                        <div class='pair-wrap divisor'></div>
                        <div id="getRequerimientosList"></div>
                                    </div>
                            </div>
                            
<!--                            <div class='pair col-sm-4'>
                                <div class='etiqueta' id='organizacionGenerar_regOrganizacionIdText'>

                                </div>
                             </div>-->

                    </div>                    

                    <div class='col-sm-5'>
<!--                        <div class='alert alert-info'>
                            <div>Aplicaciones<br> 
                                <div style="font-size: 10px;">¿Qué aplicacines se le crearán a la organización?</div></div>
                        </div>-->
                                    <div class="box-header">
                                            <h2>Aplicaciones</h2>
                                    </div>
                                    <div class="box-content">
                                        <div class="todo">
                                        <ul class="todo-list">
                                            <li>
                                            <label>
                                                <input type="checkbox" id="admin"> 
                                                Administración
                                            </label>
                                            </li>
                                            <li>
                                            <label>
                                                <input type="checkbox" id="taquilla"> 
                                                Taquilla
                                            </label>
                                            </li>
                                            <li>
                                            <label>
                                                <input type="checkbox" id="web"> 
                                                Web
                                            </label>
                                            </li>
                                        </ul>
                                        </div>

                                    </div>
                         <div class='button-container-generar'>
                            <div class='hidden button-custom button-generar mb-xs mt-xs mr-xs btn btn-default' id='btnCreaOrganizacionGenerar'><s:text name='key_organizaciongenerar_bcrea'/></div>
                            <div class='button-custom mb-xs mt-xs mr-xs btn btn-primary' id='btnCreaOrganizacionGenerarNoValidation'><s:text name='key_organizaciongenerar_bcrea'/></div>
                            <div style='clear: both'></div>
                        </div>
                    </div>

                    <div class="col-sm-12">
                        <div class='pair-wrap divisor'></div>
                        <div class="box-header">
                                <h2>Mensajes de Salida</h2>
                        </div>                       
                        <div class='grid-container'>
<!--                            <table id='organizacionGenerarGrid'></table>
                            <div id='organizacionGenerarGridPagerId'></div>-->
                        </div>
                        <div class='pair-wrap divisor'></div>
                        <br>               
                        <div id="getMensajesList"></div>      
                    </div>
                    
                </div>
                    
                    
            </div>
            <div class='pair-wrap divisor'></div>
        </div>
    </div>
</body>

