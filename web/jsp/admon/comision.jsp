<%@taglib  prefix='s' uri='/struts-tags'%>
<head>
    <script src='<s:url value='../dwr/interface/ComisionDWR.js'/>' type='text/javascript'></script>
    <script src='<s:url value='../js/admon/comision.js'/>' type='text/javascript'></script>
    <title>
        <s:property escape='false' value='nombreActionMenu'/>
    </title>
</head>
<body>
    <div class='form-wrap panel-body'>
        <div class='titulo ui-corner-top'>
            <div class='title-text hidden'><s:property escape='false' value='nombreActionMenu'/></div>
            <div class='button-container'>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-default' id='btnNewComision'><s:text name='key_comision_bnew'/></div>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-primary' id='btnSaveComision'><s:text name='key_comision_bsave'/></div>
            </div>
            <div style='clear: both'></div>
        </div>
        <!-- Hidden -->
        <input type='hidden' id='key_comision_title' value='<s:text name='key_comision_title'/>'/>
        <input type='hidden' id='key_comision_modificacionUsuario' value='<s:text name='key_comision_modificacionUsuario'/>'/>
        <input type='hidden' id='key_comision_modificacionFecha' value='<s:text name='key_comision_modificacionFecha'/>'/>
        <input type='hidden' id='comisionGridOrderByColumn' value='modificacionFecha'/>
        <input type='hidden' id='comisionGridOrderByType' value='des'/>
        <input type='hidden' id='comisionGridCurrentPage' value='1'/>
        <input type='hidden' id='comisionGridRowsByPage' value='10'/>
        <input type='hidden' id='idFromIframe' value=''/>
        <s:hidden id='gridVisibleComision' name='gridVisibleComision'/>
        <s:hidden id='gridIndividualModeComision' name='gridIndividualModeComision'/>

        <s:form id='comisionform' name='comisionform' cssClass='pair-wrap'>
            <div class='pair-hidden '>
                <div class='etiqueta' id='comision_comisionIdText'>
                    <s:text name='key_comision_comisionid'/>
                </div>
                <s:textfield name='comisionId' value='0' cssClass='isNumericInteger' />
            </div>
            <div class='pair col-sm-4'>
                <span class='etiqueta-small' id='comision_clasificacionContraIdText'>
                    <s:text name='key_comision_clasificacioncontraid'/>
                </span>
                <!--<s:property escape='false' value='clasificacionContraComisionLink'/>-->
                <s:select list='clasificacionContraComision' listKey='clasificacionContraId' listValue='nombre' name='clasificacionContraId' cssClass='select form-control' />
            </div>
            <div class='pair col-sm-4 hidden'>
                <div class='etiqueta' id='comision_nombreClasificacionText'>
                    <s:text name='key_comision_nombreclasificacion'/>
                </div>
                <s:textfield name='nombreClasificacion' maxLength='187' cssClass='textbox form-control'/>
            </div>
            <div class='pair col-sm-4'>
                <span class='etiqueta-small' id='comision_subclasificacionContraIdText'>
                    <s:text name='key_comision_subclasificacioncontraid'/>
                </span>
                <!--<s:property escape='false' value='subclasificacionContraComisionLink'/>-->
                <s:select list='subclasificacionContraComision' listKey='subclasificacionContraId' listValue='nombre' name='subclasificacionContraId' cssClass='select form-control' />
            </div>
            <div class='pair col-sm-4 hidden'>
                <div class='etiqueta' id='comision_nombreSubclasificacionText'>
                    <s:text name='key_comision_nombresubclasificacion'/>
                </div>
                <s:textfield name='nombreSubclasificacion' maxLength='187' cssClass='textbox form-control'/>
            </div>
            <div class='pair col-sm-4'>
                <span class='etiqueta-small' id='comision_nombreContraIdText'>
                    <s:text name='key_comision_nombrecontraid'/>
                </span>
                <!--<s:property escape='false' value='nombreContraComisionLink'/>-->
                <s:select list='nombreContraComision' listKey='nombreContraId' listValue='nombre' name='nombreContraId' cssClass='select form-control' />
            </div>
            <div class='pair col-sm-4 hidden'>
                <div class='etiqueta' id='comision_nombreText'>
                    <s:text name='key_comision_nombre'/>
                </div>
                <s:textfield name='nombre' maxLength='187' cssClass='textbox form-control'/>
            </div>
            <div class='pair col-sm-2'>
                <div class='etiqueta' id='comision_valorText'>
                    <s:text name='key_comision_valor'/>
                </div>
                <s:textfield name='valor' maxLength='17' cssClass='textbox form-control isNumericFloat'/>
            </div>
            <div class='pair col-sm-1'>
                <div class='etiqueta' id='comision_tipoValorText'>
                    <!--<s:text name='key_comision_tipovalor'/>-->
                    <br>
                </div>
                <!--<s:textfield name='tipoValor' maxLength='187' cssClass='textbox form-control' />-->
                <input id="comisionform_tipoValor" class="textbox form-control" name="tipoValor" maxlength="187" value="" type="text" readonly>
            </div>
            <div class='pair col-sm-4' id="comisionCheck">
                <div class='pair-checkbox'>
                    <s:checkbox name='checkbox' cssClass='checkbox'/>
                    <div class='etiqueta-checkbox' id='comision_checkboxText'>
                        <s:text name='key_comision_checkbox'/>
                    </div>
                </div>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='comision_estatusIdText'>
                    <s:text name='key_comision_estatusid'/>
                </div>
                <s:select list='estatusComision' listKey='valor' listValue='nombre' name='estatusId' cssClass='select form-control' />
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='comision_eliminadoIdText'>
                    <s:text name='key_comision_eliminadoid'/>
                </div>
                <s:select list='eliminadoComision' listKey='valor' listValue='nombre' name='eliminadoId' cssClass='select form-control' />
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='comision_creacionFechaText'>
                    <s:text name='key_comision_creacionfecha'/>
                </div>
                <s:textfield name='creacionFecha' cssClass='textbox isDate'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='comision_creacionUsuarioText'>
                    <s:text name='key_comision_creacionusuario'/>
                </div>
                <s:textfield name='creacionUsuario' maxLength='9' cssClass='textbox form-control isNumericInteger'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='comision_modificacionFechaText'>
                    <s:text name='key_comision_modificacionFecha'/>
                </div>
                <s:textfield name='modificacionFecha' cssClass='textbox isDate'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='comision_modificacionUsuarioText'>
                    <s:text name='key_comision_modificacionusuario'/>
                </div>
                <s:textfield name='modificacionUsuario' maxLength='9' cssClass='textbox form-control isNumericInteger'/>
            </div>
            <div style='clear: both'></div>
        </s:form>

        <div class='pair-wrap divisor'></div>
        <div class='grid-container'>
            <table id='comisionGrid'></table>
            <div id='comisionGridPagerId'></div>
        </div>
        <div class='pair-wrap divisor'></div>
        <br>
    </div>
</body>

