jQuery(document).ready(function () {

    // Actualizar "CatalogoPadre" al seleccionar "Organizacion" si es Dependiente en formulario catalogo
    jQuery('#catalogoform_organizacionId').change(function () {
        if (jQuery('#catalogoform_esDependiente').is(':checked')) {
            catalogoJS.filtrarCatalogoPadre();
        }
    });

    // Habilitar "CatalogoPadre" al activar "EsDependiente" en formulario catalogo
    jQuery('#catalogoform_esDependiente').change(function () {
        if (jQuery('#catalogoform_esDependiente').is(':checked')) {
            jQuery('#catalogoform_catalogoPadreId').removeAttr("disabled", "disabled").removeAttr("readonly", "readonly");
            jQuery('#catalogoform_parametroDependienteId').removeAttr("disabled", "disabled").removeAttr("readonly", "readonly");
        } else {
            jQuery('#catalogoform_catalogoPadreId').attr("disabled", "disabled").attr("readonly", "readonly");
            jQuery('#catalogoform_parametroDependienteId').attr("disabled", "disabled").attr("readonly", "readonly");
        }
    });

    // Actualizar "CatalogoPadre_Parametro" al seleccionar "CatalogoPadre" si es Dependiente en formulario catalogo
    jQuery('#catalogoform_catalogoPadreId').change(function () {
        if (jQuery('#catalogoform_esDependiente').is(':checked')) {
            catalogoJS.filtrarCatalogoPadreParametroDependiente();
        }
    });

    jQuery('#catalogoform_esDependiente').change();

    // Crear el mensaje 'Espere...'
    utilEffect.createProgressBar('Espere...');

    // Elementos button
    jQuery('.button').button();
    // Elementos datepicker
    utilInput.setAsDatepicker('.isDate');
    // Elementos datetimepicker
    utilInput.setAsDateTimePicker('.isDateTime');
    // Elementos colorpicker
    utilInput.setAsColorpicker('.color-selector');
    // Elementos radio
    utilInput.fixRadios('#catalogoform');


    // <editor-fold defaultstate="collapsed" desc="Botones">
    // Botón : NUEVO
    jQuery('#btnNewCatalogo').click(function () {
        utilForm.reset('#catalogoform');
        jQuery('#catalogoform_catalogoId').val(0);
        jQuery('#catalogoform_esDependiente').attr("disabled", "disabled");
        jQuery('#catalogoform_generico').attr("disabled", "disabled");
        jQuery('#catalogoform_catalogoPadreId').attr("disabled", "disabled").attr("readonly", "readonly");
        jQuery('#catalogoform_parametroDependienteId').attr("disabled", "disabled").attr("readonly", "readonly");
        // Limpiar el formulario y grid de catalogoParametro
        catalogoParametroLenguajeJS.reset();
        catalogoParametroJS.reset();
        catalogoJS.catalogo = null;
    }).customButtonEffect('#btnNewCatalogo');
    // Botón : GUARDAR
    jQuery('#btnSaveCatalogo').click(function () {
        catalogoJS.prepareToSave();
    }).customButtonEffectBlue('#btnSaveCatalogo');
    // Botón AGREGAR (formulario catalogoParametro)
    jQuery('#btnAddCatalogoParametro').click(function () {
        catalogoParametroJS.prepareToAgregar();
    }).css({'float': 'right', 'margin-right': '4px'}).customButtonEffectBlue('#btnAddCatalogoParametro');
    // Botón AGREGAR -Sin validación- (formulario catalogoParametro)
    jQuery('#btnAddCatalogoParametroNoValidation').click(function () {
        catalogoParametroJS.agregar();
    });
    // Botón AGREGAR (formulario catalogoParametroLenguaje)
    jQuery('#btnAddCatalogoParametroLenguaje').click(function () {
        catalogoParametroLenguajeJS.prepareToAgregar();
    }).css({'float': 'right', 'margin-right': '4px'}).customButtonEffectBlue('#btnAddCatalogoParametroLenguaje');
    // Botón AGREGAR -Sin validación- (formulario catalogoParametroLenguaje)
    jQuery('#btnAddCatalogoParametroLenguajeNoValidation').click(function () {
        catalogoParametroLenguajeJS.agregar();
    });
    // </editor-fold>

    if (jQuery('#gridVisibleCatalogo').val() == 'true') {
        jQuery('#catalogoGrid').jqGrid({
            url: '',
            datatype: '',
            colNames: [
                jQuery('#catalogo_catalogoIdText').html(),
                jQuery('#catalogo_organizacionIdText').html(),
                jQuery('#catalogo_organizacionIdText').html(),
                jQuery('#catalogo_nombreText').html(),
                jQuery('#catalogo_claveText').html(),
                jQuery('#catalogo_descripcionText').html(),
                jQuery('#catalogo_estatusIdText').html(),
                jQuery('#catalogo_eliminadoIdText').html(),
                jQuery('#catalogo_creacionFechaText').html(),
                jQuery('#catalogo_creacionUsuarioText').html(),
                jQuery('#catalogo_modificacionFechaText').html(),
                jQuery('#catalogo_modificacionUsuarioText').html(),
                '', '', ''],
            colModel: [{
                    name: 'catalogoId',
                    index: 'catalogoId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'organizacionId',
                    index: 'organizacionId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'organizacionNombre',
                    index: 'organizacionNombre',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: false,
                    sortable: false
                }, {
                    name: 'nombre',
                    index: 'nombre',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'clave',
                    index: 'clave',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'descripcion',
                    index: 'descripcion',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: false
                }, {//
                    name: 'estatusId',
                    index: 'estatusId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: true
                }, {
                    name: 'eliminadoId',
                    index: 'eliminadoId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'creacionFecha',
                    index: 'creacionFecha',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'creacionUsuario',
                    index: 'creacionUsuario',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'modificacionFecha',
                    index: 'modificacionFecha',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'modificacionUsuario',
                    index: 'modificacionUsuario',
                    align: 'center',
                    resizable: false,
                    width: 65,
                    hidden: false,
                    search: false
                }, {
                    name: 'StateCatalogo',
                    index: 'StateCatalogo',
                    align: 'center',
                    width: 20,
                    sortable: false,
                    search: false,
                    resizable: false
                }, {
                    name: 'EditCatalogo',
                    index: 'EditCatalogo',
                    align: 'center',
                    width: 20,
                    sortable: false,
                    search: false,
                    resizable: false
                }, {
                    name: 'RemoveCatalogo',
                    index: 'RemoveCatalogo',
                    align: 'center',
                    width: 20,
                    sortable: false,
                    search: false,
                    resizable: false
                }],
            height: 230,
            toolbar: false,
            hidegrid: true,
            multiselect: false,
            viewrecords: true,
            rowList: [10, 20, 30, 50, 100],
            pager: jQuery('#catalogoGridPagerId'),
            caption: jQuery('#key_catalogo_title').val()
        });
        // jQuery UI Dialogs
        utilDialog.setStandardDialog('#d-removeCatalogo', jQuery('#msgAlertaText').val(), jQuery('#msgDeseaEliminarText').val(), ['Aceptar@catalogoJS.remove()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-removeSeleccionCatalogo', jQuery('#msgAlertaText').val(), jQuery('#msgDeseaEliminarSeleccionText').val(), ['Aceptar@catalogoJS.removeSelected()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-confirmSetEstatusCatalogo', jQuery('#msgAlertaText').val(), jQuery('#msgCambiarEstatusText').val(), ['Aceptar@catalogoJS.setEstatusSeleccion()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-noSelectedCatalogo', jQuery('#msgAlertaText').val(), jQuery('#msgSeleccioneUnoOMas').val(), ['Aceptar@']);
        utilDialog.setStandardDialog('#d-confirma-eliminar-catalogo', jQuery('#msgAlertaText').val(), jQuery('#msgConfirmaEliminar').val(), ['Aceptar@catalogoJS.remove();', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-registroDuplicadoCatalogo', jQuery('#msgAlertaText').val(), jQuery('#msgYaExisteRegistro').val(), ['Aceptar@catalogoJS.doCommit()', 'Cancelar@']);
        utilDialog.setCustomDialogSelectColumnsToExport("#catalogoColumnasExportar", '#catalogoGrid');
        utilDialog.setCustomDialogSetEstatus('#catalogoSetEstatusActivoInactivo', 'catalogoJS.prepareToSetEstatus(1)', 'catalogoJS.prepareToSetEstatus(2)');

        // Inicializaciones extra para el grid
        utilGrid.setup('#catalogoGrid', '#catalogoGridPagerId', '#catalogoGridCurrentPage', '#catalogoGridOrderByColumn',
                '#catalogoGridOrderByType', 'catalogoJS');

        // Crear el modal de busqueda
        utilSearch.buildSearch('#d-searchCatalogo');

        // Recargar el widget jqGrid
        catalogoJS.reloadGrid();

    }

    // Grid catalogoParametro embebido
    if (jQuery('#gridVisibleCatalogoParametro').val() == 'true') {
        jQuery('#catalogoParametroGrid').jqGrid({
            url: '',
            datatype: '',
            colNames: [
                'Fila Id',
                jQuery('#catalogoParametro_catalogoParametroIdText').html(),
                jQuery('#catalogoParametro_organizacionIdText').html(),
                jQuery('#catalogoParametro_catalogoIdText').html(),
                jQuery('#catalogoParametro_claveText').html(),
                jQuery('#catalogoParametro_valorText').html(),
                jQuery('#catalogoParametro_ordenText').html(),
                jQuery('#catalogoParametro_visibleText').html(),
                jQuery('#catalogoParametro_estatusIdText').html(),
                jQuery('#catalogoParametro_eliminadoIdText').html(),
                jQuery('#catalogoParametro_creacionFechaText').html(),
                jQuery('#catalogoParametro_creacionUsuarioText').html(),
                jQuery('#catalogoParametro_modificacionFechaText').html(),
                jQuery('#catalogoParametro_modificacionUsuarioText').html(),
                '', '', ''],
            colModel: [{
                    name: 'filaId',
                    index: 'filaId',
                    hidden: true
                },
                {
                    name: 'catalogoParametroId',
                    index: 'catalogoParametroId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'organizacionId',
                    index: 'organizacionId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false

                }, {
                    name: 'catalogoId',
                    index: 'catalogoId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false

                }, {
                    name: 'clave',
                    index: 'clave',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'valor',
                    index: 'valor',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'orden',
                    index: 'orden',
                    align: 'center',
                    width: 30,
                    resizable: false,
                    hidden: false,
                    search: false
                }, {
                    name: 'visible',
                    index: 'visible',
                    align: 'center',
                    width: 40,
                    sortable: false,
                    search: false,
                    resizable: false
                }, {
                    name: 'estatusId',
                    index: 'estatusId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: true
                }, {
                    name: 'eliminadoId',
                    index: 'eliminadoId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'creacionFecha',
                    index: 'creacionFecha',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'creacionUsuario',
                    index: 'creacionUsuario',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'modificacionFecha',
                    index: 'modificacionFecha',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: false
                }, {
                    name: 'usuarioModificacion',
                    index: 'usuarioModificacion',
                    align: 'center',
                    resizable: false,
                    width: 65,
                    hidden: false,
                    search: false
                }, {
                    name: 'StateCatalogo',
                    index: 'StateCatalogo',
                    align: 'center',
                    width: 20,
                    sortable: false,
                    search: false,
                    resizable: false
                }, {
                    name: 'EditCatalogo',
                    index: 'EditCatalogo',
                    align: 'center',
                    width: 20,
                    sortable: false,
                    search: false,
                    resizable: false
                }, {
                    name: 'RemoveCatalogo',
                    index: 'RemoveCatalogo',
                    align: 'center',
                    width: 20,
                    sortable: false,
                    search: false,
                    resizable: false
                }],
            height: 230,
            toolbar: false,
            hidegrid: true,
            multiselect: false,
            viewrecords: false,
            pager: jQuery('#catalogoParametroGridPagerId'),
            caption: jQuery('#key_catalogoparametro_title').val()
        });
        jQuery('#catalogoParametroGridPagerId').hide();
        jQuery('#catalogoParametroGrid').setGridWidth(jQuery("#gbox_" + "catalogoParametroGrid").closest(".grid-container").width() - 5);
    }

    // Grid catalogoParametroLenguaje embebido
    if (jQuery('#gridVisibleCatalogoParametroLenguaje').val() == 'true') {
        jQuery('#catalogoParametroLenguajeGrid').jqGrid({
            url: '',
            datatype: '',
            colNames: [
                'Fila Id',
                jQuery('#catalogoParametroLenguaje_catalogoParametroLenguajeIdText').html(),
                jQuery('#catalogoParametroLenguaje_organizacionIdText').html(),
                jQuery('#catalogoParametroLenguaje_catalogoIdText').html(),
                jQuery('#catalogoParametroLenguaje_lenguajeIdText').html(),
                jQuery('#catalogoParametroLenguaje_etiquetaText').html(),
                jQuery('#catalogoParametroLenguaje_estatusIdText').html(),
                jQuery('#catalogoParametroLenguaje_eliminadoIdText').html(),
                jQuery('#catalogoParametroLenguaje_creacionFechaText').html(),
                jQuery('#catalogoParametroLenguaje_creacionUsuarioText').html(),
                jQuery('#catalogoParametroLenguaje_modificacionFechaText').html(),
                jQuery('#catalogoParametroLenguaje_modificacionUsuarioText').html(),
                '', '', ''],
            colModel: [{
                    name: 'filaId',
                    index: 'filaId',
                    hidden: true
                },
                {
                    name: 'catalogoParametroLenguajeId',
                    index: 'catalogoParametroLenguajeId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'organizacionId',
                    index: 'organizacionId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false

                }, {
                    name: 'catalogoId',
                    index: 'catalogoId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false

                }, {
                    name: 'lenguajeId',
                    index: 'lenguajeId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false

                }, {
                    name: 'etiqueta',
                    index: 'etiqueta',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'estatusId',
                    index: 'estatusId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: true
                }, {
                    name: 'eliminadoId',
                    index: 'eliminadoId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'creacionFecha',
                    index: 'creacionFecha',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'creacionUsuario',
                    index: 'creacionUsuario',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'modificacionFecha',
                    index: 'modificacionFecha',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: false
                }, {
                    name: 'usuarioModificacion',
                    index: 'usuarioModificacion',
                    align: 'center',
                    resizable: false,
                    width: 55,
                    hidden: false,
                    search: false
                }, {
                    name: 'StateCatalogo',
                    index: 'StateCatalogo',
                    align: 'center',
                    width: 20,
                    sortable: false,
                    search: false,
                    resizable: false
                }, {
                    name: 'EditCatalogo',
                    index: 'EditCatalogo',
                    align: 'center',
                    width: 20,
                    sortable: false,
                    search: false,
                    resizable: false
                }, {
                    name: 'RemoveCatalogo',
                    index: 'RemoveCatalogo',
                    align: 'center',
                    width: 20,
                    sortable: false,
                    search: false,
                    resizable: false
                }],
            height: 230,
            toolbar: false,
            hidegrid: true,
            multiselect: false,
            viewrecords: false,
            pager: jQuery('#catalogoParametroLenguajeGridPagerId'),
            caption: jQuery('#key_catalogoparametrolenguaje_title').val()
        });
        jQuery('#catalogoParametroLenguajeGridPagerId').hide();
        jQuery('#catalogoParametroLenguajeGrid').setGridWidth(
                jQuery("#gbox_" + "catalogoParametroLenguajeGrid").closest(".grid-container").width() - 5);
    }

    // Limpiar la página
    jQuery('#btnNewCatalogo').click();
    // Edición externa
    jQuery('.new-window').tooltip();
    jQuery('.new-window').contents().hide();
    jQuery('.new-window').click(function () {
        utilForm.hideTooltips();
        jQuery('#idFromIframe').val(0);
        var url = jQuery(this).find('.url').text();
        var callback = jQuery(this).find('.callback').text();
        utilWindow.openNewWindow(url, callback, 1100);
    });

    // Si es individual precargar el formulario
    if (jQuery('#gridIndividualModeCatalogo').val() == 'true') {
        jQuery('#btnNewCatalogo').hide();
        catalogoJS.findFirst();
    }
});

// Objeto auxiliar para la funcionalidad de desglose.
var desgloseCatalogoParametroJS = {
    desglose: function (selectIdList) {
        desgloseCatalogoParametroJS.cleanDesglose();
        utilForm.desglose(selectIdList);
    },
    cleanDesglose: function () {
        utilForm.cleanDesglose("#catalogoParametroform");
    }
};

// Objeto auxiliar para la funcionalidad de desglose.
var desgloseCatalogoParametroLenguajeJS = {
    desglose: function (selectIdList) {
        desgloseCatalogoParametroLenguajeJS.cleanDesglose();
        utilForm.desglose(selectIdList);
    },
    cleanDesglose: function () {
        utilForm.cleanDesglose("#catalogoParametroLenguajeform");
    }
};


var catalogoParametroLenguajeJS = {
    isDesglose: false,
    filaId: null,
    catalogoParametroLenguaje: null,
    /**
     * Limpia el formulario.
     */
    clean: function () {
        utilForm.reset('#catalogoParametroLenguajeform');
        jQuery('#catalogoParametroLenguajeform_filaId').val(0);
        jQuery('#catalogoParametroLenguajeform_catalogoParametroLenguajeId').val(0);
        catalogoParametroLenguajeJS.catalogoParametroLenguaje = null;
        catalogoParametroLenguajeJS.isDesglose = false;
        jQuery('form[name="catalogoParametroLenguajeform"]').addClass("base-desglose");
    },
    /**
     * Limpia el formulario y el grid de catalogoParametroLenguaje, usado cuando el
     * usuario presiona el botón Nuevo.
     */
    reset: function () {
        catalogoParametroLenguajeJS.clean();
        jQuery('#catalogoParametroLenguajeGrid').clearGridData();
    },
    /** 
     * Valida los campos del formulario de catalogoParametroLenguaje antes de
     * agregarlos al grid.
     */
    prepareToAgregar: function () {
        var validation = validanguage.validateForm('catalogoParametroLenguajeform');
        if (validation.result) {
            catalogoParametroLenguajeJS.agregar();
            catalogoParametroLenguajeJS.clean();
        }
    },
    /** 
     * Función llamada cuando se presiona el botón Agregar en el formulario de 
     * catalogoParametroLenguaje.
     */
    agregar: function () {
        // Formar el objeto con los datos del formulario
        var catalogoParametroLenguaje = utilObject.buildObject(
                '#catalogoParametroLenguajeform', new CatalogoParametroLenguajeDWR());
        // Valdar si se esta agregando uno nuevo o uno editado
        if (jQuery('#catalogoParametroLenguajeform_filaId') == 0) {
            catalogoParametroLenguajeJS.agregarNuevo(catalogoParametroLenguaje);
        } else {
            catalogoParametroLenguajeJS.agregarEditado(catalogoParametroLenguaje);
        }
    },
    /* Agrega un registro editado al grid de catalogoParametroLenguaje */
    agregarEditado: function (object) {
        object.filaId = jQuery('#catalogoParametroLenguajeform_filaId').val();
        jQuery('#catalogoParametroLenguajeGrid').delRowData(object.filaId);
        catalogoParametroLenguajeJS.agregarNuevo(object);
    },
    /**
     * Agrega un nuevo registro al grid de catalogoParametroLenguaje, de manera 
     * predeterminada la fila se agrega al final de las existentes.
     * 
     * @param object, es un objeto CatalogoParametroLenguaje con la información a
     * agregar.
     */
    agregarNuevo: function (object) {
        //debugger;
        var filaId = catalogoParametroLenguajeJS.findFilaIdMax() + 1;
        // Activo
        var activo_css = 'ui-icon ui-icon-bullet ui-icon-center';
        var activo_title = 'Activo';
        var activoCatalogoParametroLenguaje_onclick = 'catalogoParametroLenguajeJS.setEstatus( ' + object.id + ',' + object.catalogoParametroId + ',' + object.organizacionId + ', 2 )';
        // Inactivo
        var inactivo_css = 'ui-icon ui-icon-radio-off ui-icon-center';
        var inactivo_title = 'Inactivo';
        var inactivoCatalogoParametroLenguaje_onclick = 'catalogoParametroLenguajeJS.setEstatus( ' + object.id + ',' + object.catalogoParametroId + ',' + object.organizacionId + ', 1 )';
        // Editar
        var editar_css = 'ui-icon ui-icon-pencil ui-icon-center';
        var editar_title = 'Editar';
        var editar_onclick = 'catalogoParametroLenguajeJS.findByFilaId(' + filaId + ')';
        // Eliminar
        var eliminar_css = 'ui-icon ui-icon-trash ui-icon-center';
        var eliminar_title = 'Eliminar';
        var eliminar_onclick = 'catalogoParametroLenguajeJS.prepareToRemove(' + filaId + ')';

        object.EditCatalogo = "<span class = '" + editar_css + "' title = '" + editar_title + "' style = 'cursor:pointer' onclick = '" + editar_onclick + "'></span>";
        object.RemoveCatalogo = "<span class = '" + eliminar_css + "' title = '" + eliminar_title + "' style = 'cursor:pointer' onclick = '" + eliminar_onclick + "'></span>";
        if (object.estatusId == 'Activo' || object.estatusId == 1) {
            object.StateCatalogo = "<span class = '" + activo_css + "' title = '" + activo_title + "' style = 'cursor:pointer' onclick = '" + activoCatalogoParametroLenguaje_onclick + "'></span>";
        } else {
            object.StateCatalogo = "<span class = '" + inactivo_css + "' title = '" + inactivo_title + "' style = 'cursor:pointer' onclick = '" + inactivoCatalogoParametroLenguaje_onclick + "'></span>";
        }
        // Numero de Fila
        object.filaId = filaId;
        // Limpiar campos si es nuevo
        if (object.catalogoParametroLenguajeId == 0) {
            utilObject.cleanCamposControl(object);
            utilObject.resolveDescription('#catalogoParametroLenguajeform', object);
        }
        // Parsear fechas
        var propiedades = utilObject.getProperties(object);
        for (var i = 0; i < propiedades.length; i++) {
            var propiedad = propiedades[ i ] + '';
            if (utilString.containsIgnoreCase(propiedad, 'fecha') ||
                    utilString.containsIgnoreCase(propiedad, 'date')) {
                if (object[ propiedad ] != null) {
                    object[ propiedad ] = jQuery.datepicker.formatDate(
                            'dd/mm/yy', new Date(object[ propiedad ])
                            );
                }
            }
        }
        // Enviar los datos al grid
        jQuery('#catalogoParametroLenguajeGrid').addRowData(filaId, object);
    },
    /**
     * Obtiene el numero de fila mas alto en el grid.
     * 
     * @return regresa un entero igual al numero de la fila mas alto.
     */
    findFilaIdMax: function () {
        var filaIdMax = 0;
        var filaList = jQuery('#catalogoParametroLenguajeGrid').getRowData();
        for (var i = 0; i < filaList.length; i++) {
            if (parseInt(filaList[i].filaId) > filaIdMax) {
                filaIdMax = parseInt(filaList[i].filaId);
            }
        }
        return parseInt(filaIdMax);
    },
    /** 
     * Función llamada al presionar el botón Editar en el registro de 
     * catalogoParametroLenguaje. Obtiene un objeto desde el grid mediante el numero de la
     * fila, el objeto es parseado para llenar los campos en el formulario.
     * 
     * @param filaId, es el numero de la fila.
     */
    /* Función llamada al presionar el botón Editar en el registro de catalogoParametroLenguaje*/
    findByFilaId: function (filaId) {
        var filas = jQuery('#catalogoParametroLenguajeGrid').getRowData();
        for (var i = 0; i < filas.length; i++) {
            if (filas[ i ].filaId == filaId) {
                catalogoParametroLenguajeJS.clean();
                catalogoParametroLenguajeJS.catalogoParametroLenguaje = filas[i];
                utilForm.populate('#catalogoParametroLenguajeform', filas[ i ]);
                catalogoParametroLenguajeJS.isDesglose = true;
                jQuery('form[name="catalogoParametroLenguajeform"]').removeClass("base-desglose");
                break;
            }
        }
    },
    /** 
     * Función llamada al presionar el botón Eliminar en el registro de
     * catalogoParametroLenguaje.
     * 
     * @param filaId, es el numero de la fila.
     */
    prepareToRemove: function (filaId) {
        catalogoParametroLenguajeJS.filaId = filaId;
        utilDialog.showDialog('#d-confirma-eliminar-catalogoparametrolenguaje');
    },
    /** 
     * Función que elimina una fila del grid catalogoparametrolenguaje, el numero de 
     * fila a eliminar es el que se encuentra almacenado en la variale
     * catalogoparametrolenguajeJS.filaId.
     */
    remove: function () {
        jQuery('#catalogoParametroLenguajeGrid').delRowData(catalogoParametroLenguajeJS.filaId);
        catalogoParametroLenguajeJS.clean();
    },
    /** 
     * Guarda los registros del detalle.
     * 
     * @param catalogoId, es el id de catalogo.
     */
    save: function (catalogoParametroId) {
        var dwrObjectList = new Array();
        // Funcionalidad desglose
        if (catalogoParametroLenguajeJS.isDesglose) {
            jQuery('form[name="catalogoParametroLenguajeform"]').each(function () {
                if (jQuery(this).hasClass('base-desglose') == false) {
                    // Construir el objeto
                    var obj = utilObject.buildObject('#' +
                            jQuery(this).attr('id'), new CatalogoParametroLenguajeDWR());
                    obj.catalogoParametroId = catalogoParametroId;
                    dwrObjectList.push(obj);
                }
            });
        } else {
            dwrObjectList = utilObject.buildDWRObjectListByGridId(
                    '#catalogoParametroLenguajeGrid', new CatalogoParametroLenguajeDWR());
            for (var i = 0; i < dwrObjectList.length; i++) {
                dwrObjectList[ i ].catalogoParametroId = catalogoParametroId;
            }
        }
        CatalogoParametroLenguajeDWR.saveCatalogoParametroLenguaje(catalogoParametroId, jQuery('#catalogoParametroform_organizacionId').val(), dwrObjectList, catalogoParametroLenguajeJS.saveCallback);
    },
    /* Callback de la función save(), esta función es llamada por DWR al terminar
     * de realizar la operación */
    saveCallback: function () {
        utilEffect.showToast('success', jQuery('#msgGuardadoOk').val());
    },
    /* Función que obtiene catalogoParametroLenguaje */
    findCatalogoParametroBy: function (catalogoParametroId, organizacionId) {
        CatalogoParametroLenguajeDWR.findCatalogoParametroBy(catalogoParametroId, organizacionId, catalogoParametroLenguajeJS.findCatalogoParametroByCallback);
    },
    /**
     * Callback de la función findCatalogoParametroLenguajeByIntProperty(), esta función es llamada por DWR al terminar
     * de realizar la operación 
     * 
     * @param data, es una lista de objetos CatalogoParametroLenguaje que serán
     * insertados en el grid
     */
    findCatalogoParametroByCallback: function (data) {
        catalogoParametroLenguajeJS.reset();
        if (data != null) {
            for (var i = 0; i < data.length; i++) {
                catalogoParametroLenguajeJS.agregarNuevo(data[ i ]);
            }
        }
        utilEffect.hideProgressBar();
    },
    deleteMe: function () {
        // do nothing
    }
}

var catalogoParametroJS = {
    isDesglose: false,
    filaId: null,
    catalogoParametro: null,
    /**
     * Limpia el formulario.
     */
    clean: function () {
        utilForm.reset('#catalogoParametroform');
        jQuery('#catalogoParametroform_filaId').val(0);
        jQuery('#catalogoParametroform_catalogoParametroId').val(0);
        catalogoParametroJS.catalogoParametro = null;
        catalogoParametroJS.isDesglose = false;
        jQuery('form[name="catalogoParametroform"]').addClass("base-desglose");
    },
    /**
     * Limpia el formulario y el grid de catalogoParametro, usado cuando el
     * usuario presiona el botón Nuevo.
     */
    reset: function () {
        catalogoParametroJS.clean();
        jQuery('#catalogoParametroGrid').clearGridData();
    },
    /** 
     * Valida los campos del formulario de catalogoParametro antes de
     * agregarlos al grid.
     */
    prepareToAgregar: function () {
        var validation = validanguage.validateForm('catalogoParametroform');
        if (validation.result) {
            catalogoParametroJS.agregar();
            catalogoParametroJS.clean();
        }
    },
    /** 
     * Función llamada cuando se presiona el botón Agregar en el formulario de 
     * catalogoParametro.
     */
    agregar: function () {
        // Formar el objeto con los datos del formulario
        var catalogoParametro = utilObject.buildObject(
                '#catalogoParametroform', new CatalogoParametroDWR());
        // Valdar si se esta agregando uno nuevo o uno editado
        if (jQuery('#catalogoParametroform_filaId') == 0) {
            catalogoParametroJS.agregarNuevo(catalogoParametro);
        } else {
            catalogoParametroJS.agregarEditado(catalogoParametro);
        }
    },
    /* Agrega un registro editado al grid de catalogoParametro */
    agregarEditado: function (object) {
        object.filaId = jQuery('#catalogoParametroform_filaId').val();
        jQuery('#catalogoParametroGrid').delRowData(object.filaId);
        catalogoParametroJS.agregarNuevo(object);
    },
    /**
     * Agrega un nuevo registro al grid de catalogoParametro, de manera 
     * predeterminada la fila se agrega al final de las existentes.
     * 
     * @param object, es un objeto CatalogoParametro con la información a
     * agregar.
     */
    agregarNuevo: function (object) {
        //debugger;
        var filaId = catalogoParametroJS.findFilaIdMax() + 1;
        // Activo
        var activo_css = 'ui-icon ui-icon-bullet ui-icon-center';
        var activo_title = 'Activo';
        var activoCatalogoParametro_onclick = 'catalogoParametroJS.setEstatus( ' + object.id + ',' + object.catalogoId + ',' + object.organizacionId + ', 2 )';
        // Inactivo
        var inactivo_css = 'ui-icon ui-icon-radio-off ui-icon-center';
        var inactivo_title = 'Inactivo';
        var inactivoCatalogoParametro_onclick = 'catalogoParametroJS.setEstatus( ' + object.id + ',' + object.catalogoId + ',' + object.organizacionId + ', 1 )';
        // Editar
        var editar_css = 'ui-icon ui-icon-pencil ui-icon-center';
        var editar_title = 'Editar';
        var editar_onclick = 'catalogoParametroJS.findByFilaId(' + filaId + ')';
        // Eliminar
        var eliminar_css = 'ui-icon ui-icon-trash ui-icon-center';
        var eliminar_title = 'Eliminar';
        var eliminar_onclick = 'catalogoParametroJS.prepareToRemove(' + filaId + ')';
        // Visible
        var visible_css = 'ui-icon ui-icon-bullet ui-icon-center';
        var visible_title = 'Visible';
        var visibleCatalogoParametro_onclick = 'catalogoParametroJS.setVisible( ' + object.id + ',' + object.catalogoId + ',' + object.organizacionId + ', 0 )';
        // No Visible
        var novisible_css = 'ui-icon ui-icon-radio-off ui-icon-center';
        var novisible_title = 'No Visible';
        var novisibleCatalogoParametro_onclick = 'catalogoParametroJS.setVisible( ' + object.id + ',' + object.catalogoId + ',' + object.organizacionId + ', 1 )';

        object.EditCatalogo = "<span class = '" + editar_css + "' title = '" + editar_title + "' style = 'cursor:pointer' onclick = '" + editar_onclick + "'></span>";
        object.RemoveCatalogo = "<span class = '" + eliminar_css + "' title = '" + eliminar_title + "' style = 'cursor:pointer' onclick = '" + eliminar_onclick + "'></span>";
        if (object.estatusId == 'Activo' || object.estatusId == 1) {
            object.StateCatalogo = "<span class = '" + activo_css + "' title = '" + activo_title + "' style = 'cursor:pointer' onclick = '" + activoCatalogoParametro_onclick + "'></span>";
        } else {
            object.StateCatalogo = "<span class = '" + inactivo_css + "' title = '" + inactivo_title + "' style = 'cursor:pointer' onclick = '" + inactivoCatalogoParametro_onclick + "'></span>";
        }
        if (object.visible == true || object.visible == 1) {
            object.visible = "<span class = '" + visible_css + "' title = '" + visible_title + "' style = 'cursor:pointer' onclick = '" + visibleCatalogoParametro_onclick + "'></span>";
        } else {
            object.visible = "<span class = '" + novisible_css + "' title = '" + novisible_title + "' style = 'cursor:pointer' onclick = '" + novisibleCatalogoParametro_onclick + "'></span>";
        }
        // Numero de Fila
        object.filaId = filaId;
        // Limpiar campos si es nuevo
        if (object.catalogoParametroId == 0) {
            utilObject.cleanCamposControl(object);
            utilObject.resolveDescription('#catalogoParametroform', object);
        }
        // Parsear fechas
        var propiedades = utilObject.getProperties(object);
        for (var i = 0; i < propiedades.length; i++) {
            var propiedad = propiedades[ i ] + '';
            if (utilString.containsIgnoreCase(propiedad, 'fecha') ||
                    utilString.containsIgnoreCase(propiedad, 'date')) {
                if (object[ propiedad ] != null) {
                    object[ propiedad ] = jQuery.datepicker.formatDate(
                            'dd/mm/yy', new Date(object[ propiedad ])
                            );
                }
            }
        }
        // Enviar los datos al grid
        jQuery('#catalogoParametroGrid').addRowData(filaId, object);
    },
    /**
     * Obtiene el numero de fila mas alto en el grid.
     * 
     * @return regresa un entero igual al numero de la fila mas alto.
     */
    findFilaIdMax: function () {
        var filaIdMax = 0;
        var filaList = jQuery('#catalogoParametroGrid').getRowData();
        for (var i = 0; i < filaList.length; i++) {
            if (parseInt(filaList[i].filaId) > filaIdMax) {
                filaIdMax = parseInt(filaList[i].filaId);
            }
        }
        return parseInt(filaIdMax);
    },
    /** 
     * Función llamada al presionar el botón Editar en el registro de 
     * catalogoParametro. Obtiene un objeto desde el grid mediante el numero de la
     * fila, el objeto es parseado para llenar los campos en el formulario.
     * 
     * @param filaId, es el numero de la fila.
     */
    /* Función llamada al presionar el botón Editar en el registro de catalogoParametro*/
    findByFilaId: function (filaId) {
        var filas = jQuery('#catalogoParametroGrid').getRowData();
        for (var i = 0; i < filas.length; i++) {
            if (filas[ i ].filaId == filaId) {
                catalogoParametroJS.clean();
                catalogoParametroJS.catalogoParametro = filas[i];
                utilForm.populate('#catalogoParametroform', filas[ i ]);
                catalogoParametroJS.isDesglose = true;
                jQuery('form[name="catalogoParametroform"]').removeClass("base-desglose");
                catalogoParametroLenguajeJS.findCatalogoParametroBy(jQuery('#catalogoParametroform_catalogoParametroId').val(), jQuery('#catalogoParametroform_organizacionId').val());
                break;
            }
        }
    },
    /** 
     * Función llamada al presionar el botón Eliminar en el registro de
     * catalogoParametro.
     * 
     * @param filaId, es el numero de la fila.
     */
    prepareToRemove: function (filaId) {
        catalogoParametroJS.filaId = filaId;
        utilDialog.showDialog('#d-confirma-eliminar-catalogoparametro');
    },
    /** 
     * Función que elimina una fila del grid catalogoparametro, el numero de 
     * fila a eliminar es el que se encuentra almacenado en la variale
     * catalogoparametroJS.filaId.
     */
    remove: function () {
        jQuery('#catalogoParametroGrid').delRowData(catalogoParametroJS.filaId);
        catalogoParametroJS.clean();
    },
    /** 
     * Guarda los registros del detalle.
     * 
     * @param catalogoId, es el id de catalogo.
     */
    save: function (catalogoId) {
        var dwrObjectList = new Array();
        // Funcionalidad desglose
        if (catalogoParametroJS.isDesglose) {
            jQuery('form[name="catalogoParametroform"]').each(function () {
                if (jQuery(this).hasClass('base-desglose') === false) {
                    // Construir el objeto
                    var obj = utilObject.buildObject('#' +
                            jQuery(this).attr('id'), new CatalogoParametroDWR());
                    obj.catalogoId = catalogoId;
                    dwrObjectList.push(obj);
                }
            });
        } else {
            dwrObjectList = utilObject.buildDWRObjectListByGridId('#catalogoParametroGrid', new CatalogoParametroDWR());
            for (var i = 0; i < dwrObjectList.length; i++) {
                dwrObjectList[ i ].catalogoId = catalogoId;
            }
        }
        CatalogoParametroDWR.saveCatalogoParametro(catalogoId, jQuery('#catalogoParametroform_organizacionId').val(), dwrObjectList, catalogoParametroJS.saveCallback);
    },
    /* Callback de la función save(), esta función es llamada por DWR al terminar
     * de realizar la operación */
    saveCallback: function (savedIds) {
        // Funcionalidad desglose
        if (catalogoParametroJS.isDesglose) {
            catalogoParametroLenguajeJS.save(savedIds[ 0 ]);
        } else {
            utilEffect.showToast('success', jQuery('#msgGuardadoOk').val());
        }
    },
    /* Función que obtiene catalogoParametro */
    findCatalogoParametroBy: function (catalogoId, organizacionId) {
        CatalogoParametroDWR.findCatalogoParametroBy(catalogoId, organizacionId, catalogoParametroJS.findCatalogoParametroByCallback);
    },
    /**
     * Callback de la función findCatalogoParametroByIntProperty(), esta función es llamada por DWR al terminar
     * de realizar la operación 
     * 
     * @param data, es una lista de objetos CatalogoParametro que serán
     * insertados en el grid
     */
    findCatalogoParametroByCallback: function (data) {
        catalogoParametroJS.reset();
        if (data != null) {
            for (var i = 0; i < data.length; i++) {
                catalogoParametroJS.agregarNuevo(data[ i ]);
            }
        }
        utilEffect.hideProgressBar();
    },
    deleteMe: function () {
        // do nothing
    },
    /*
     * Función que cambia el estatus de un registro en el grid al presionar el botón Activar/Inactivar.
     * Manda llamar al método <b>setEstatus</b> de DWR, éste método.
     *
     * @param id (int) Es el id del registro al cual se le va a cambiar el estatusId
     * @param estatusId (int) Es el nuevo estatusId
     */
    setEstatus: function (id, catalogoId, organizacionId, estatusId) {
        utilEffect.showProgressBar();
        var listaCatalogoParametros = [{catalogoParametroId: id, catalogoId: catalogoId, organizacionId: organizacionId}];
        CatalogoParametroDWR.setEstatus(estatusId, listaCatalogoParametros, catalogoParametroJS.setEstatusCallback);
    },
    /*
     * Función que cambia el Estatus de los elementos seleccionados en el widget
     * de jqGrid.
     * 
     */
    setEstatusSeleccion: function () {
        utilEffect.showProgressBar();
        var estatusId = catalogoParametroJS.estatusId;
        var listaCatalogoParametros = utilGrid.gridGetSelectedRows('#catalogoParametroGrid');
        CatalogoParametroDWR.setEstatus(estatusId, listaCatalogoParametros, catalogoParametroJS.setEstatusCallback);
    },
    /*
     * Callback de la función setEstatus(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     */
    setEstatusCallback: function () {
        catalogoParametroJS.reloadGrid();
        utilEffect.hideProgressBar();
    },
    /*
     * Función que muestra un mensaje de confirmación para Activar o Inactivar los registros
     * seleccionados del widget jQGrid.
     */
    prepareToSetEstatus: function (estatusId) {
        var seleccion = utilGrid.gridGetSelectedRows('#catalogoParametroGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedCatalogoParametro');
        } else {
            catalogoParametroJS.estatusId = estatusId;
            // utilDialog.showDialog( '#d-confirmSetEstatusCatalogoParametro' );
            catalogoParametroJS.setEstatusSeleccion();
        }
    },
    /*
     * Función que cambia el estatus de un registro en el grid al presionar el botón Activar/Inactivar.
     * Manda llamar al método <b>setVisible</b> de DWR, éste método.
     *
     * @param id (int) Es el id del registro al cual se le va a cambiar el visible
     * @param visible (int) Es el nuevo visible
     */
    setVisible: function (id, catalogoId, organizacionId, visible) {
        utilEffect.showProgressBar();
        var listaCatalogoParametros = [{catalogoParametroId: id, catalogoId: catalogoId, organizacionId: organizacionId}];
        CatalogoParametroDWR.setVisible(visible, listaCatalogoParametros, catalogoParametroJS.setVisibleCallback);
    },
    /*
     * Función que cambia el Visible de los elementos seleccionados en el widget
     * de jqGrid.
     * 
     */
    setVisibleSeleccion: function () {
        utilEffect.showProgressBar();
        var visible = catalogoParametroJS.visible;
        var listaCatalogoParametros = utilGrid.gridGetSelectedRows('#catalogoParametroGrid');
        CatalogoParametroDWR.setVisible(visible, listaCatalogoParametros, catalogoParametroJS.setVisibleCallback);
    },
    /*
     * Callback de la función setVisible(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     */
    setVisibleCallback: function () {
        catalogoParametroJS.reloadGrid();
        utilEffect.hideProgressBar();
    },
    /*
     * Función que muestra un mensaje de confirmación para Activar o Inactivar los registros
     * seleccionados del widget jQGrid.
     */
    prepareToSetVisible: function (visible) {
        var seleccion = utilGrid.gridGetSelectedRows('#catalogoParametroGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedCatalogoParametro');
        } else {
            catalogoParametroJS.visible = visible;
            // utilDialog.showDialog( '#d-confirmSetVisibleCatalogoParametro' );
            catalogoParametroJS.setVisibleSeleccion();
        }
    }
}

var catalogoJS = {
    /* Variable auxiliar para persistir un objeto (resultado de una consulta) 
     * obtenido mediante el callback de la función "findById(...)". Este objeto
     * es necesario para la funcionalidad de selects encadenados pero puede ser
     * utilizado para otros propósitos. El objeto existe solamente mientras se
     * esta editando la información en el formulario. Al presionar el botón
     * Nuevo o Guardar cualquier referencia a éste regresará "null". El 
     * objeto debe utilizarse como modo de "Solo Lectura" (la modificación de 
     * este objeto no se verá reflejada en la base de datos) */
    catalogo: null,
    /*
     * Variable auxiliar para mantener las busquedas del grid al momento de paginar, ordenar, etc.
     */
    cacheDWR: null,
    /*
     * Función que guarda un nuevo registro en la BD.
     */
    save: function () {
        utilEffect.showProgressBar();
        var catalogo = utilObject.buildObject('#catalogoform', new CatalogoDWR());
        catalogo.catalogoId = 0;
        var listaCatalogos = [catalogo];
        CatalogoDWR.save(listaCatalogos, catalogoJS.saveOrUpdateCallback);
    },
    /*
     * Función que actualiza un registro existente en la BD.
     */
    update: function () {
        utilEffect.showProgressBar();
        var catalogo = utilObject.buildObject('#catalogoform', new CatalogoDWR());
        catalogo.catalogoId = jQuery('#catalogoform_catalogoId').val();
        var listaCatalogos = [catalogo];
        CatalogoDWR.update(listaCatalogos, catalogoJS.saveOrUpdateCallback);
    },
    /*
     * Callback de la funcion save() y update(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param savedIds (array), lista de id's de objetos que
     * se guardaron en base de datos.
     */
    saveOrUpdateCallback: function (savedIds) {
        catalogoParametroJS.save(savedIds[ 0 ]);
        jQuery('#btnNewCatalogo').click();
        catalogoJS.catalogo = null;
        catalogoJS.reloadGrid();
    },
    /*
     * Función que elimina un registro en el grid al presionar el botón eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como parámetro.
     */
    remove: function () {
        utilEffect.showProgressBar();
        var id = catalogoJS.getEliminarId();
        var organizacionId = catalogoJS.getEliminarOrganizacionId();
        var listaCatalogos = [{catalogoId: id, organizacionId: organizacionId}];
        CatalogoDWR.remove(listaCatalogos, catalogoJS.removeCallback);
    },
    /*
     * Función que elimina los registros seleccionados en el grid al presionar el botón eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como parámetro.
     */
    removeSelected: function () {
        utilEffect.showProgressBar();
        var listaCatalogos = utilGrid.gridGetSelectedRows('#catalogoGrid');
        CatalogoDWR.remove(listaCatalogos, catalogoJS.removeCallback);
    },
    /*
     * Callback de la función remove(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    removeCallback: function () {
        jQuery('#btnNewCatalogo').click();
        catalogoJS.reloadGrid();
    },
    /*
     * Función que realiza una búsqueda de un objeto por su ID en la BD.
     * Usualmente es llamado cuando el usuario presiona el icono <b>Editar</b>
     * de una fila en el widget jqGrid.
     * 
     * @param id (Integer) Es el id del objeto que se quiere obtener.
     */
    findById: function (id, organizacionId) {
        utilEffect.showProgressBar();
        CatalogoDWR.findById(id, organizacionId, catalogoJS.findByIdCallback);
    },
    /*
     * Callback de la función findById(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto con la información de la consulta.
     */
    findByIdCallback: function (data) {
        utilForm.reset('#catalogoform');
        utilForm.populate('#catalogoform', data);
        catalogoJS.catalogo = data;
        jQuery('#catalogoform_esDependiente').attr("disabled", "disabled");
        jQuery('#catalogoform_generico').attr("disabled", "disabled");
        jQuery('#catalogoform_catalogoPadreId').attr("disabled", "disabled").attr("readonly", "readonly");
        jQuery('#catalogoform_parametroDependienteId').attr("disabled", "disabled").attr("readonly", "readonly");
        catalogoParametroJS.findCatalogoParametroBy(jQuery('#catalogoform_catalogoId').val(), jQuery('#catalogoform_organizacionId').val());
    },
    /*
     * Función que realiza una búsqueda en la BD. Este objeto contiene 
     * todos los criterios para realizar la consulta.
     * 
     * @param obj (Object) Es el objeto con el cual se va a realizar la 
     * consulta. El resultado será una lista de objetos que concuerden con las
     * propiedades del objeto que se envió como parámetro.
     */
    findByCriteria: function (obj) {
        catalogoJS.cacheDWR = obj;
        var page = jQuery('#catalogoGridCurrentPage').val();
        var rows = jQuery('#catalogoGridRowsByPage').val();
        var order = jQuery('#catalogoGridOrderByColumn').val();
        var orderType = jQuery('#catalogoGridOrderByType').val();
        utilEffect.showProgressBar();
        CatalogoDWR.findByCriteria(page, rows, order, orderType, obj, catalogoJS.findByCriteriaCallback);
    },
    /*
     * Callback de la función findByCriteria(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto (Grid) con la información de la 
     * consulta.
     */
    findByCriteriaCallback: function (data) {
        console.log(data);
        utilGrid.gridUpdate('#catalogoGrid', '#catalogoGridCurrentPage', '#catalogoGridRowsByPage', data);
        // Limpiar el formulario y grid de catalogo, catalogoParametro, catalogoParametroLenguaje
        jQuery('#btnNewCatalogo').click();
        utilEffect.hideProgressBar();
    },
    /**
     * Funcion utilizado cuando la pagina es individual
     */
    findFirst: function () {
        utilEffect.showProgressBar();
        CatalogoDWR.findFirst(catalogoJS.findByIdCallback);
    },
    /*
     * Función que válida el atributo <b>NOMBRE</b> de un registro en la BD. 
     * Se valida si ya existe un registro con el mismo nombre, esto con el 
     * propósito de advertir al usuario sobre registros duplicados.
     * Por default el <b>CoreGenerator</b> está configurado solo para mostrar
     * advertencias al usuario y permite más de un registro con el mismo
     * nombre en la BD, está metodología puede cambiarse manualmente modificando
     * el <b>callback</b> de esta función.
     * NOTICE: Ésta función es generada por el <b>CoreGenerator</b>
     * únicamente cuando la tabla tiene el campo 'NOMBRE', si la tabla no
     * contiene ese campo la función estará ausente. 
     * 
     * Ésta función siempre es llamada antes de realizar algún commit a la BD 
     * (guardar ó actualizar/modificar).
     */
    isValidoNombre: function () {
        var nombre = jQuery('#catalogoform_nombre').val();
        CatalogoDWR.isValidoNombre(nombre, catalogoJS.isValidoNombreCallback);
    },
    /*
     * Callback de la función isValidoNombre(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param isValido (boolean) es true cuando no existe ningún registro con
     * el nombre a validar, false cuando hay al menos un registro con el mismo
     * nombre.
     */
    isValidoNombreCallback: function (isValido) {
        // Cuando se esta actualizando un registro pero no se cambia el nombre, 
        // la función isValidoNombre() siempre regresa false al momento de 
        // intentar guardar por que el registro que se esta editando tiene el mismo 
        // nombre que en base de datos, en este caso se permite el commit.
        if (isValido == false && jQuery('#catalogoform_catalogoId').val() != 0) {
            catalogoJS.doCommit();
            return;
        }
        // Nuevo registro, en este caso se permite el commit
        if (isValido == true && jQuery('#catalogoform_catalogoId').val() == 0) {
            catalogoJS.doCommit();
            return;
        }
        // Registro existente, se le cambio el nombre, en este caso se permite el commit
        if (isValido == true && jQuery('#catalogoform_catalogoId').val() != 0) {
            catalogoJS.doCommit();
            return;
        }
        // Mostrar mensaje de registro duplicado 
        utilDialog.showDialog('#d-registroDuplicadoCatalogo');
    },
    /*
     * Función que cambia el estatus de un registro en el grid al presionar el botón Activar/Inactivar.
     * Manda llamar al método <b>setEstatus</b> de DWR, éste método.
     *
     * @param id (int) Es el id del registro al cual se le va a cambiar el estatusId
     * @param estatusId (int) Es el nuevo estatusId
     */
    setEstatus: function (id, organizacionId, estatusId) {
        utilEffect.showProgressBar();
        var listaCatalogos = [{catalogoId: id, organizacionId: organizacionId}];
        CatalogoDWR.setEstatus(estatusId, listaCatalogos, catalogoJS.setEstatusCallback);
    },
    /*
     * Función que cambia el Estatus de los elementos seleccionados en el widget
     * de jqGrid.
     * 
     */
    setEstatusSeleccion: function () {
        utilEffect.showProgressBar();
        var estatusId = catalogoJS.estatusId;
        var listaCatalogos = utilGrid.gridGetSelectedRows('#catalogoGrid');
        CatalogoDWR.setEstatus(estatusId, listaCatalogos, catalogoJS.setEstatusCallback);
    },
    /*
     * Callback de la función setEstatus(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     */
    setEstatusCallback: function () {
        catalogoJS.reloadGrid();
        utilEffect.hideProgressBar();
    },
    /*
     * Función que muestra un mensaje de confirmación para Activar o Inactivar los registros
     * seleccionados del widget jQGrid.
     */
    prepareToSetEstatus: function (estatusId) {
        var seleccion = utilGrid.gridGetSelectedRows('#catalogoGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedCatalogo');
        } else {
            catalogoJS.estatusId = estatusId;
            // utilDialog.showDialog( '#d-confirmSetEstatusCatalogo' );
            catalogoJS.setEstatusSeleccion();
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar los registros seleccionados
     * del widget jQGrid.
     */
    prepareToRemoveSelected: function () {
        var seleccion = utilGrid.gridGetSelectedRows('#catalogoGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedCatalogo');
        } else {
            utilDialog.showDialog('#d-removeSeleccionCatalogo');
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar un regristro
     * del widget jQGrid.
     */
    prepareToRemove: function (id, organizacionId) {
        catalogoJS.setEliminarId(id, organizacionId);
        utilDialog.showDialog('#d-removeCatalogo');
    },
    /*
     * Variable para guardar el ID de la fila que se va a eliminar, si 
     * el usuario confirma la decisión al presionar el botón Aceptar del cuadro de diálogo. 
     **/
    eliminarId: 0,
    eliminarOrganizacionId: 0,
    /*
     * Setter de la variable eliminarId
     **/
    setEliminarId: function (eliminarId, organizacionId) {
        catalogoJS.eliminarId = eliminarId;
        catalogoJS.eliminarOrganizacionId = organizacionId;
    },
    /*
     * Getter de la variable eliminarId
     **/
    getEliminarId: function () {
        return catalogoJS.eliminarId;
    },
    /*
     * Getter de la variable eliminarId
     **/
    getEliminarOrganizacionId: function () {
        return catalogoJS.eliminarOrganizacionId;
    },
    /*
     * Variable para guardar el estatus según el botón que se presionó: Activar o Inactivar si 
     * el usuario confirma la decisión al presionar el botón Aceptar del cuadro de diálogo. 
     **/
    estatusId: 0,
    /*
     * Función que se ejecuta al presionar el botón : GUARDAR.
     */
    prepareToSave: function () {
        var validation = validanguage.validateForm('catalogoform');
        if (validation.result) {
            catalogoJS.isValidoNombre();
        }
    },
    /*
     * Función que inicia el proceso para GUARDAR y ACTUALIZAR/MODIFICAR
     * Una vez que se pasaron las validaciones se realiza el commit.
     */
    doCommit: function () {
        if (jQuery('#catalogoform_catalogoId').val() == 0) {
            catalogoJS.save();
        } else {
            catalogoJS.update();
        }
    },
    /*
     * Función que es llamada para actualizar la información del grid.
     */
    reloadGrid: function () {
        if (catalogoJS.cacheDWR != null) {
            catalogoJS.findByCriteria(catalogoJS.cacheDWR);
        } else {
            catalogoJS.findByCriteria(new CatalogoDWR());
        }
    },
    /*
     * Función que es activa el modal de busqueda.
     */
    openSearch: function () {
        utilSearch.openSearch('#d-searchCatalogo', '#catalogoGrid', '#catalogoform', catalogoJS, new CatalogoDWR());
    },
    /*
     * Funcion que inicia la exportacion del grid a PDF mediante struts invocando una url y 
     * madandole parametros, esta funcion es llamada por utilMisc.prepareToExportPDF en el arcvivo 
     * util.js.
     * 
     * @param entity es el nombre del entity en Java que representa este grid
     * @param headers son los encabezados de la tabla, son los mismo que los nombres de las columnas del grid
     * @param sortBy es el nombre del campo por el cual seran ordenados los resultados
     * @param sortType es el tipo de orden: ascendente o descendente
     * @param format es el formato a exportar: pdf o excel 
     * 
     */
    entity: null,
    headers: null,
    format: null,
    reportName: null,
    exportarDatos: function (entity, headers, sortBy, sortType, format) {
        catalogoJS.entity = entity;
        catalogoJS.headers = headers;
        catalogoJS.format = format;
        catalogoJS.reportName = jQuery('#key_catalogo_title').val() +
                '_' + utilMisc.getTodayDate('-');
        var criteriaExample = new CatalogoDWR();
        if (catalogoJS.cacheDWR != null) {
            criteriaExample = catalogoJS.cacheDWR;
        }
        // utilEffect.showProgressBar();
        jQuery().toastmessage('showToast', {
            text: jQuery('#msgFiltrandoInformacion').val(),
            sticky: false,
            position: 'bottom-center',
            type: 'notice',
            closeText: '',
            close: function () {
            }
        });
        CatalogoDWR.getReportDataTest(sortBy, sortType, criteriaExample, catalogoJS.exportarDatosCallback);
    },
    exportarDatosCallback: function (reportKey) {
        // utilEffect.hideProgressBar();
        jQuery().toastmessage('showToast', {
            text: 'Generando reporte...',
            sticky: false,
            position: 'middle-center',
            type: 'notice',
            closeText: '',
            close: function () {
            }
        });
        window.location.href = '/admon/admon/exportarPDF.action?entity=' +
                catalogoJS.entity + '&headers=' + catalogoJS.headers +
                '&reportName=' + catalogoJS.reportName + '&format=' + catalogoJS.format +
                '&reportKey=' + reportKey;
    },
    filtrarCatalogoPadre: function () {
        utilEffect.showProgressBar();
        CatalogoDWR.filtrarCatalogo(jQuery('#catalogoform_organizacionId').val(), catalogoJS.filtrarCatalogoPadreCallback);
    },
    filtrarCatalogoPadreCallback: function (data) {
        if (data == null || data.length == 0) {
            utilInput.removeOptionsSelectAndAddDummy('#catalogoform_catalogoPadreId', '--Seleccione--');
        } else {
            utilInput.populateSelect('#catalogoform_catalogoPadreId', data, 'catalogoPadreId', 'nombre');
            if (catalogoJS.catalogo !== null) {
                if (catalogoJS.catalogo.catalogoPadreId !== null) {
                    if (utilInput.existsOptionByValueInSelect('#catalogoform_catalogoPadreId', catalogoJS.catalogo.catalogoPadreId)) {
                        jQuery('#catalogoform_catalogoPadreId').val(catalogoJS.catalogo.catalogoPadreId);
                    } else {
                        jQuery('#catalogoform_catalogoPadreId').val(0);
                    }
                }
            }
            jQuery('#catalogoform_catalogoPadreId').trigger('change');
        }
        utilEffect.hideProgressBar();
    },
    filtrarCatalogoPadreParametroDependiente: function () {
        utilEffect.showProgressBar();
        CatalogoDWR.filtrarCatalogoParametro(jQuery('#catalogoform_catalogoPadreId').val(), jQuery('#catalogoform_organizacionId').val(), catalogoJS.filtrarCatalogoPadreParametroDependienteCallback);
    },
    filtrarCatalogoPadreParametroDependienteCallback: function (data) {
        if (data == null || data.length == 0) {
            utilInput.removeOptionsSelectAndAddDummy('#catalogoform_parametroDependienteId', '--Seleccione--');
        } else {
            utilInput.populateSelect('#catalogoform_parametroDependienteId', data, 'parametroDependienteId', 'nombre');
            if (catalogoJS.catalogo !== null) {
                if (catalogoJS.catalogo.parametroDependienteId !== null) {
                    if (utilInput.existsOptionByValueInSelect('#catalogoform_parametroDependienteId', catalogoJS.catalogo.parametroDependienteId)) {
                        jQuery('#catalogoform_parametroDependienteId').val(catalogoJS.catalogo.parametroDependienteId);
                    } else {
                        jQuery('#catalogoform_parametroDependienteId').val(0);
                    }
                }
            }
            jQuery('#catalogoform_parametroDependienteId').trigger('change');
        }
        utilEffect.hideProgressBar();
    }
};

/*
 * Funcion que setea el id del elemento seleccionado en la fila del jqgrid en un hidden cuando el grid
 * esta en un iframe de edicion externa. El id es utilizado para que al momento de cerrar el dialogo
 * se actualize su respectivo select y setear el valor en ese mismo select con el id que el usuario selecciono.
 */
function setIdFromIframe(id) {
    jQuery('#idFromIframe').val(id);
}
// Validaciones de catalogoform
validanguage.el.catalogoform_organizacionId = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};
validanguage.el.catalogoform_clave = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
validanguage.el.catalogoform_nombre = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
validanguage.el.catalogoform_catalogoPadreId = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter, {
            name: function (_evt) {
                console.log(_evt);
                if (jQuery("#catalogoform_esDependiente").is(":checked") && Number(jQuery("#catalogoform_catalogoPadreId").val()) <= 0) {
                    return false;
                } else {
                    return true;
                }
            },
            errorMsg: 'Seleccione un registro.',
            onerror: utilEffect.showValidationTooltip,
            onsuccess: utilEffect.hideValidationTooltip
        }]};
validanguage.el.catalogoform_parametroDependienteId = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter, {
            name: function (_evt) {
                console.log(_evt);
                if (jQuery("#catalogoform_esDependiente").is(":checked") && Number(jQuery("#catalogoform_catalogoPadreId").val()) <= 0 && Number(jQuery("#catalogoform_parametroDependienteId").val()) <= 0) {
                    return false;
                } else {
                    return true;
                }
            },
            errorMsg: 'Seleccione un registro.',
            onerror: utilEffect.showValidationTooltip,
            onsuccess: utilEffect.hideValidationTooltip
        }]};
validanguage.el.catalogoform_seleccionaColor = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};
validanguage.el.catalogoform_descripcion = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter, {
            name: function (text) {
                if (text.length > 3750) {
                    return false;
                } else {
                    return true;
                }
            },
            errorMsg: 'Solo se permiten 200 carácteres como máximo.',
            onerror: utilEffect.showValidationTooltip,
            onsuccess: utilEffect.hideValidationTooltip
        }
    ]};
// Validaciones de catalogoParametroform
validanguage.el.catalogoParametroform_organizacionId = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};
validanguage.el.catalogoParametroform_catalogoId = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};
validanguage.el.catalogoParametroform_clave = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
validanguage.el.catalogoParametroform_valor = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};
validanguage.el.catalogoParametroform_orden = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};
validanguage.el.catalogoParametroform_descripcion = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter, {
            name: function (text) {
                if (text.length > 400) {
                    return false;
                } else {
                    return true;
                }
            },
            errorMsg: 'Solo se permiten 400 carácteres como máximo.',
            onerror: utilEffect.showValidationTooltip,
            onsuccess: utilEffect.hideValidationTooltip
        }
    ]};
// Validaciones de catalogoParametroLenguajeform
validanguage.el.catalogoParametroLenguajeform_organizacionId = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};
validanguage.el.catalogoParametroLenguajeform_catalogoParametroId = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};
validanguage.el.catalogoParametroLenguajeform_lenguajeId = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};
validanguage.el.catalogoParametroLenguajeform_etiqueta = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};
