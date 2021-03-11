<%@taglib  prefix='s' uri='/struts-tags'%>
<head>
    <script src='<s:url value='../dwr/interface/CatalogoDWR.js'/>' type='text/javascript'></script>
    <script src='<s:url value='../dwr/interface/CatalogoParametroDWR.js'/>' type='text/javascript'></script>
    <script src='<s:url value='../dwr/interface/CatalogoParametroLenguajeDWR.js'/>' type='text/javascript'></script>
    <script src='<s:url value='../js/admon/catalogo.js'/>' type='text/javascript'></script>
    <title>
        <s:property escape='false' value='nombreActionMenu'/>
    </title>
</head>
<body>
    <div class='form-wrap panel-body'>
        <div class='titulo ui-corner-top'>
            <div class='title-text hidden'><s:property escape='false' value='nombreActionMenu'/></div>
            <div class='button-container'>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-default' id='btnNewCatalogo'><s:text name='key_catalogo_bnew'/></div>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-primary' id='btnSaveCatalogo'><s:text name='key_catalogo_bsave'/></div>
            </div>
            <div style='clear: both'></div>
        </div>
        <!-- Hidden -->
        <input type='hidden' id='key_catalogo_title' value='<s:text name='key_catalogo_title'/>'/>
        <input type='hidden' id='key_catalogo_modificacionUsuario' value='<s:text name='key_catalogo_modificacionUsuario'/>'/>
        <input type='hidden' id='key_catalogo_modificacionFecha' value='<s:text name='key_catalogo_modificacionFecha'/>'/>
        <input type='hidden' id='catalogoGridOrderByColumn' value='modificacionFecha'/>
        <input type='hidden' id='catalogoGridOrderByType' value='des'/>
        <input type='hidden' id='catalogoGridCurrentPage' value='1'/>
        <input type='hidden' id='catalogoGridRowsByPage' value='10'/>
        <input type='hidden' id='idFromIframe' value=''/>
        <s:hidden id='gridVisibleCatalogo' name='gridVisibleCatalogo'/>
        <s:hidden id='gridIndividualModeCatalogo' name='gridIndividualModeCatalogo'/>
        <s:hidden id='gridVisibleCatalogoParametro' name='gridVisibleCatalogoParametro'/>
        <s:hidden id='gridIndividualModeCatalogoParametro' name='gridIndividualModeCatalogoParametro'/>
        <s:hidden id='gridVisibleCatalogoParametroLenguaje' name='gridVisibleCatalogoParametroLenguaje'/>
        <s:hidden id='gridIndividualModeCatalogoParametroLenguaje' name='gridIndividualModeCatalogoParametroLenguaje'/>

        <s:form id='catalogoform' name='catalogoform' cssClass='pair-wrap'>
            <div class='pair-hidden '>
                <div class='etiqueta' id='catalogo_catalogoIdText'>
                    <s:text name='key_catalogo_catalogoid'/>
                </div>
                <s:textfield name='catalogoId' value='0' cssClass='isNumericInteger' />
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta' id='catalogo_organizacionIdText'>
                    <s:text name='key_catalogo_organizacionid'/>
                </div>
                <s:select list='organizacionCatalogo' listKey='organizacionId' listValue='nombre' name='organizacionId' cssClass='select form-control' />
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta' id='catalogo_claveText'>
                    <s:text name='key_catalogo_clave'/>
                </div>
                <s:textfield name='clave' maxLength='30' cssClass='textbox form-control'/>
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta' id='catalogo_nombreText'>
                    <s:text name='key_catalogo_nombre'/>
                </div>
                <s:textfield name='nombre' maxLength='70' cssClass='textbox form-control'/>
            </div>

            <div class='pair col-sm-2 '>
                <div class='pair-checkbox'>
                    <s:checkbox name='generico' cssClass='checkbox' disabled="disabled"/>
                    <div class='etiqueta-checkbox' id='catalogo_genericoText'>
                        <s:text name='key_catalogo_generico'/>
                    </div>
                </div>
            </div>
            <div class='pair col-sm-2 '>
                <div class='pair-checkbox'>
                    <s:checkbox name='esDependiente' cssClass='checkbox'/>
                    <div class='etiqueta-checkbox' id='catalogo_esDependienteText'>
                        <s:text name='key_catalogo_esdependiente'/>
                    </div>
                </div>
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta' id='catalogo_catalogoPadreIdText'>
                    <s:text name='key_catalogo_catalogopadreid'/>
                </div>
                <s:select list='catalogoPadreCatalogo' listKey='organizacionId' listValue='nombre' name='catalogoPadreId' cssClass='select form-control' />
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta' id='catalogo_parametroDependienteIdText'>
                    <s:text name='key_catalogo_parametrodependienteid'/>
                </div>
                <s:select list='parametroDependienteCatalogo' listKey='catalogoParametroId' listValue='valor' name='parametroDependienteId' cssClass='select form-control' />
            </div>
            <div class='pair col-sm-2 '>
                <div class='etiqueta' id='catalogo_seleccionaColorText'>
                    <s:text name='key_catalogo_seleccionacolor'/>
                </div>
                <s:textfield name='seleccionaColor' value='0' cssClass='textbox form-control isNumericInteger' />
            </div>

            <div class='pair col-sm-10'>
                <div class='etiqueta' id='catalogo_descripcionText'>
                    <s:text name='key_catalogo_descripcion'/>
                </div>
                <s:textarea name='descripcion' cssClass='textbox form-control'/>
            </div>

            <div class='pair-hidden '>
                <div class='etiqueta' id='catalogo_estatusIdText'>
                    <s:text name='key_catalogo_estatusid'/>
                </div>
                <s:select list='estatusCatalogo' listKey='valor' listValue='nombre' name='estatusId' cssClass='select form-control' />
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='catalogo_eliminadoIdText'>
                    <s:text name='key_catalogo_eliminadoid'/>
                </div>
                <s:select list='eliminadoCatalogo' listKey='valor' listValue='nombre' name='eliminadoId' cssClass='select form-control' />
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='catalogo_creacionFechaText'>
                    <s:text name='key_catalogo_creacionfecha'/>
                </div>
                <s:textfield name='creacionFecha' cssClass='textbox isDate form-control'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='catalogo_creacionUsuarioText'>
                    <s:text name='key_catalogo_creacionusuario'/>
                </div>
                <s:textfield name='creacionUsuario' maxLength='20' cssClass='textbox isNumericInteger form-control'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='catalogo_modificacionFechaText'>
                    <s:text name='key_catalogo_modificacionFecha'/>
                </div>
                <s:textfield name='modificacionFecha' cssClass='textbox isDate form-control'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='catalogo_modificacionUsuarioText'>
                    <s:text name='key_catalogo_modificacionusuario'/>
                </div>
                <s:textfield name='modificacionUsuario' maxLength='20' cssClass='textbox isNumericInteger form-control'/>
            </div>
            <div style='clear: both'></div>
        </s:form>
        <div class='pair-wrap divisor'></div>
        <!-- Divisor -->
        <div class='pair-wrap' style="background-color: #F6F6F6;">
            <!-- Titulo -->
            <input type='hidden' id='key_catalogoparametro_title' value='<s:text name='key_catalogoparametro_title'/>'/>
            <div class='title-text alert alert-info'><s:text name='key_catalogoparametro_title'/>
                <!-- Botón Agregar -->
                <div class='button-container-detalle'>
                    <div class='button-custom button-detalle mb-xs mt-xs mr-xs btn btn-default' id='btnAddCatalogoParametro'><s:text name='key_catalogoparametro_badd'/></div>
                    <div class='hidden button button-detalle mb-xs mt-xs mr-xs btn btn-default' id='btnAddCatalogoParametroNoValidation'><s:text name='key_catalogoparametro_badd'/></div>
                    <div style='clear: both'></div>
                </div>
            </div>

            <div style='clear: both'></div>
            <s:form id='catalogoParametroform' name='catalogoParametroform' cssClass='pair-wrap'>
                <div class='pair-hidden '>
                    <div class='etiqueta' id='catalogoParametro_filaIdText'>
                        filaId
                    </div>
                    <s:textfield name='filaId' value='0' cssClass='isNumericInteger form-control' />
                </div>
                <div class='pair-hidden '>
                    <div class='etiqueta' id='catalogoParametro_catalogoParametroIdText'>
                        <s:text name='key_catalogoparametro_catalogoparametroid'/>
                    </div>
                    <s:textfield name='catalogoParametroId' value='0' cssClass='isNumericInteger' />
                </div>
                <div class='pair col-sm-4 pair-hidden'>
                    <div class='etiqueta' id='catalogoParametro_organizacionIdText'>
                        <s:text name='key_catalogoparametro_organizacionid'/>
                    </div>
                    <s:textfield name='organizacionId' value='0' cssClass='isNumericInteger' />
                </div>
                <div class='pair col-sm-4 pair-hidden'>
                    <div class='etiqueta' id='catalogopParametro_catalogoIdText'>
                        <s:text name='key_catalogoparametro_catalogoid'/>
                    </div>
                    <s:textfield name='catalogoId' value='0' cssClass='isNumericInteger' />
                </div>
                <div class='pair col-sm-4'>
                    <div class='etiqueta' id='catalogoParametro_claveText'>
                        <s:text name='key_catalogoparametro_clave'/>
                    </div>
                    <s:textfield name='clave' maxLength='30' cssClass='textbox form-control'/>
                </div>

                <div class='pair col-sm-4'>
                    <div class='etiqueta' id='catalogoParametro_valorText'>
                        <s:text name='key_catalogoparametro_valor'/>
                    </div>
                    <s:textfield name='valor' maxLength='50' cssClass='textbox form-control'/>
                </div>
                <div class='pair col-sm-2 '>
                    <div class='etiqueta' id='catalogoParametro_ordenText'>
                        <s:text name='key_catalogoparametro_orden'/>
                    </div>
                    <s:textfield name='orden' maxLength='10' cssClass='textbox isNumericInteger form-control'/>
                </div>
                <div class='pair col-sm-2 '>
                    <div class='pair-checkbox'>
                        <s:checkbox name='visible' cssClass='checkbox'/>
                        <div class='etiqueta-checkbox' id='catalogoParametro_visibleText'>
                            <s:text name='key_catalogoparametro_visible'/>
                        </div>
                    </div>
                </div>
                <div class='pair col-sm-10'>
                    <div class='etiqueta' id='catalogoParametro_descripcionText'>
                        <s:text name='key_catalogoparametro_descripcion'/>
                    </div>
                    <s:textarea name='descripcion' cssClass='textbox form-control'/>
                </div>

                <div class='pair-hidden '>
                    <div class='etiqueta' id='catalogoParametro_estatusIdText'>
                        <s:text name='key_catalogoparametro_estatusid'/>
                    </div>
                    <s:select list='estatusCatalogoParametro' listKey='valor' listValue='nombre' name='estatusId' cssClass='select form-control' />
                </div>
                <div class='pair-hidden '>
                    <div class='etiqueta' id='catalogoParametro_eliminadoIdText'>
                        <s:text name='key_catalogoparametro_eliminadoid'/>
                    </div>
                    <s:select list='eliminadoCatalogoParametro' listKey='valor' listValue='nombre' name='eliminadoId' cssClass='select form-control' />
                </div>
                <div class='pair-hidden '>
                    <div class='etiqueta' id='catalogoParametro_creacionFechaText'>
                        <s:text name='key_catalogoparametro_creacionfecha'/>
                    </div>
                    <s:textfield name='creacionFecha' cssClass='textbox isDate form-control'/>
                </div>
                <div class='pair-hidden '>
                    <div class='etiqueta' id='catalogoParametro_creacionUsuarioText'>
                        <s:text name='key_catalogoparametro_creacionusuario'/>
                    </div>
                    <s:textfield name='creacionUsuario' maxLength='20' cssClass='textbox isNumericInteger form-control'/>
                </div>
                <div class='pair-hidden '>
                    <div class='etiqueta' id='catalogoParametro_modificacionFechaText'>
                        <s:text name='key_catalogoparametro_modificacionFecha'/>
                    </div>
                    <s:textfield name='modificacionFecha' cssClass='textbox isDate form-control'/>
                </div>
                <div class='pair-hidden '>
                    <div class='etiqueta' id='catalogoParametro_modificacionUsuarioText'>
                        <s:text name='key_catalogoparametro_modificacionusuario'/>
                    </div>
                    <s:textfield name='modificacionUsuario' maxLength='20' cssClass='textbox isNumericInteger form-control'/>
                </div>
                <div style='clear: both'></div>
            </s:form>
            <div class='pair-wrap divisor'></div>
            <!-- Divisor -->
            <div class='pair-wrap' style="background-color: #F6F6F6;">
                <!-- Titulo -->
                <input type='hidden' id='key_catalogoparametrolenguaje_title' value='<s:text name='key_catalogoparametrolenguaje_title'/>'/>
                <div class='title-text alert alert-info'><s:text name='key_catalogoparametrolenguaje_title'/>
                    <!-- Botón Agregar -->
                    <div class='button-container-detalle'>
                        <div class='button-custom button-detalle mb-xs mt-xs mr-xs btn btn-default' id='btnAddCatalogoParametroLenguaje'><s:text name='key_catalogoparametrolenguaje_badd'/></div>
                        <div class='hidden button button-detalle mb-xs mt-xs mr-xs btn btn-default' id='btnAddCatalogoParametroLenguajeNoValidation'><s:text name='key_catalogoparametrolenguaje_badd'/></div>
                        <div style='clear: both'></div>
                    </div>
                </div>

                <div style='clear: both'></div>
                <s:form id='catalogoParametroLenguajeform' name='catalogoParametroLenguajeform' cssClass='pair-wrap'>
                    <div class='pair-hidden '>
                        <div class='etiqueta' id='catalogoParametroLenguaje_filaIdText'>
                            filaId
                        </div>
                        <s:textfield name='filaId' value='0' cssClass='isNumericInteger form-control' />
                    </div>
                    <div class='pair-hidden '>
                        <div class='etiqueta' id='catalogoParametroLenguaje_catalogoParametroLenguajeIdText'>
                            <s:text name='key_catalogoparametrolenguaje_catalogoparametrolenguajeid'/>
                        </div>
                        <s:textfield name='catalogoParametroLenguajeId' value='0' cssClass='isNumericInteger' />
                    </div>

                    <div class='pair col-sm-4 pair-hidden'>
                        <div class='etiqueta' id='catalogoParametroLenguaje_organizacionIdText'>
                            <s:text name='key_catalogoparametrolenguaje_organizacionid'/>
                        </div>
                        <s:textfield name='organizacionId' value='0' cssClass='isNumericInteger' />
                    </div>
                    <div class='pair col-sm-4 pair-hidden'>
                        <div class='etiqueta' id='catalogopParametro_catalogoParametroIdText'>
                            <s:text name='key_catalogoparametrolenguaje_catalogoparametroid'/>
                        </div>
                        <s:textfield name='catalogoParametroId' value='0' cssClass='isNumericInteger' />
                    </div>
                    <div class='pair col-sm-4'>
                        <div class='etiqueta' id='catalogoParametroLenguaje_lenguajeIdText'>
                            <s:text name='key_catalogoparametrolenguaje_lenguajeid'/>
                        </div>
                        <s:select list='lenguajeCatalogoParametroLenguaje' listKey='lenguajeId' listValue='nombre' name='lenguajeId' cssClass='select form-control' />
                    </div>
                    <div class='pair col-sm-6'>
                        <div class='etiqueta' id='catalogoParametroLenguaje_etiquetaText'>
                            <s:text name='key_catalogoparametrolenguaje_etiqueta'/>
                        </div>
                        <s:textfield name='etiqueta' maxLength='100' cssClass='textbox form-control'/>
                    </div>

                    <div class='pair-hidden '>
                        <div class='etiqueta' id='catalogoParametroLenguaje_estatusIdText'>
                            <s:text name='key_catalogoparametrolenguaje_estatusid'/>
                        </div>
                        <s:select list='estatusCatalogoParametro' listKey='valor' listValue='nombre' name='estatusId' cssClass='select form-control' />
                    </div>
                    <div class='pair-hidden '>
                        <div class='etiqueta' id='catalogoParametroLenguaje_eliminadoIdText'>
                            <s:text name='key_catalogoparametrolenguaje_eliminadoid'/>
                        </div>
                        <s:select list='eliminadoCatalogoParametro' listKey='valor' listValue='nombre' name='eliminadoId' cssClass='select form-control' />
                    </div>
                    <div class='pair-hidden '>
                        <div class='etiqueta' id='catalogoParametroLenguaje_creacionFechaText'>
                            <s:text name='key_catalogoparametrolenguaje_creacionfecha'/>
                        </div>
                        <s:textfield name='creacionFecha' cssClass='textbox isDate form-control'/>
                    </div>
                    <div class='pair-hidden '>
                        <div class='etiqueta' id='catalogoParametroLenguaje_creacionUsuarioText'>
                            <s:text name='key_catalogoparametrolenguaje_creacionusuario'/>
                        </div>
                        <s:textfield name='creacionUsuario' maxLength='20' cssClass='textbox isNumericInteger form-control'/>
                    </div>
                    <div class='pair-hidden '>
                        <div class='etiqueta' id='catalogoParametroLenguaje_modificacionFechaText'>
                            <s:text name='key_catalogoparametrolenguaje_modificacionFecha'/>
                        </div>
                        <s:textfield name='modificacionFecha' cssClass='textbox isDate form-control'/>
                    </div>
                    <div class='pair-hidden '>
                        <div class='etiqueta' id='catalogoParametroLenguaje_modificacionUsuarioText'>
                            <s:text name='key_catalogoparametrolenguaje_modificacionusuario'/>
                        </div>
                        <s:textfield name='modificacionUsuario' maxLength='20' cssClass='textbox isNumericInteger form-control'/>
                    </div>
                    <div style='clear: both'></div>
                </s:form>

                <div class='pair-wrap divisor'></div>
                <div class='grid-container'>
                    <table id='catalogoParametroLenguajeGrid'></table>
                    <div id='catalogoParametroLenguajeGridPagerId'></div>
                </div>
            </div>
            <div class='pair-wrap divisor'></div>
            <div class='grid-container'>
                <table id='catalogoParametroGrid'></table>
                <div id='catalogoParametroGridPagerId'></div>
            </div>
        </div>

        <div class='pair-wrap divisor'></div>
        <div class='grid-container'>
            <table id='catalogoGrid'></table>
            <div id='catalogoGridPagerId'></div>
        </div>
        <div class='pair-wrap divisor'></div>
        <br>
    </div>
</body>

