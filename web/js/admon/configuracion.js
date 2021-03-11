jQuery(document).ready(function () {


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
    utilInput.fixRadios('#configuracionform');


    // <editor-fold defaultstate="collapsed" desc="Botones">
    // Botón : NUEVO
    jQuery('#btnNewConfiguracion').click(function () {
        utilForm.reset('#configuracionform');
        jQuery('#configuracionform_configuracionId').val(0);
        // Limpiar el formulario y grid de configuracionParametro
        configuracionParametroJS.reset();
        configuracionJS.configuracion = null;
    }).customButtonEffect('#btnNewConfiguracion');
    // Botón : GUARDAR
    jQuery('#btnSaveConfiguracion').click(function () {
        configuracionJS.prepareToSave();
    }).customButtonEffectBlue('#btnSaveConfiguracion');
    // Botón AGREGAR (formulario configuracionParametro)
    jQuery('#btnAddConfiguracionParametro').click(function () {
        configuracionParametroJS.prepareToAgregar();
    }).css({'float': 'right', 'margin-right': '4px'}).customButtonEffectBlue('#btnAddConfiguracionParametro');
    // Botón AGREGAR -Sin validación- (formulario configuracionParametro)
    jQuery('#btnAddConfiguracionParametroNoValidation').click(function () {
        configuracionParametroJS.agregar();
    });
    // </editor-fold>

    if (jQuery('#gridVisibleConfiguracion').val() == 'true') {
        jQuery('#configuracionGrid').jqGrid({
            url: '',
            datatype: '',
            colNames: [
                jQuery('#configuracion_configuracionIdText').html(),
                jQuery('#configuracion_nombreText').html(),
                jQuery('#configuracion_descripcionText').html(),
                jQuery('#configuracion_estatusIdText').html(),
                jQuery('#configuracion_eliminadoIdText').html(),
                jQuery('#configuracion_creacionFechaText').html(),
                jQuery('#configuracion_creacionUsuarioText').html(),
                jQuery('#configuracion_modificacionFechaText').html(),
                jQuery('#configuracion_modificacionUsuarioText').html(),
                '', '', ''],
            colModel: [{
                    name: 'configuracionId',
                    index: 'configuracionId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'nombre',
                    index: 'nombre',
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
                    search: true
                }, {
                    name: 'modificacionUsuario',
                    index: 'modificacionUsuario',
                    align: 'center',
                    resizable: false,
                    width: 55,
                    hidden: false,
                    search: false
                }, {
                    name: 'State',
                    index: 'State',
                    align: 'center',
                    width: 20,
                    sortable: false,
                    search: false,
                    resizable: false
                }, {
                    name: 'Edit',
                    index: 'Edit',
                    align: 'center',
                    width: 20,
                    sortable: false,
                    search: false,
                    resizable: false
                }, {
                    name: 'Remove',
                    index: 'Remove',
                    align: 'center',
                    width: 20,
                    sortable: false,
                    search: false,
                    resizable: false
                }],
            height: 230,
            toolbar: false,
            hidegrid: true,
            multiselect: true,
            viewrecords: true,
            rowList: [10, 20, 30, 50, 100],
            pager: jQuery('#configuracionGridPagerId'),
            caption: jQuery('#key_configuracion_title').val()
        });
        // jQuery UI Dialogs
        utilDialog.setStandardDialog('#d-removeConfiguracion', jQuery('#msgAlertaText').val(), jQuery('#msgDeseaEliminarText').val(), ['Aceptar@configuracionJS.remove()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-removeSeleccionConfiguracion', jQuery('#msgAlertaText').val(), jQuery('#msgDeseaEliminarSeleccionText').val(), ['Aceptar@configuracionJS.removeSelected()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-confirmSetEstatusConfiguracion', jQuery('#msgAlertaText').val(), jQuery('#msgCambiarEstatusText').val(), ['Aceptar@configuracionJS.setEstatusSeleccion()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-noSelectedConfiguracion', jQuery('#msgAlertaText').val(), jQuery('#msgSeleccioneUnoOMas').val(), ['Aceptar@']);
        utilDialog.setStandardDialog('#d-confirma-eliminar-configuracionparametro', jQuery('#msgAlertaText').val(), jQuery('#msgConfirmaEliminar').val(), ['Aceptar@configuracionParametroJS.remove();', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-registroDuplicadoConfiguracion', jQuery('#msgAlertaText').val(), jQuery('#msgYaExisteRegistro').val(), ['Aceptar@configuracionJS.doCommit()', 'Cancelar@']);
        utilDialog.setCustomDialogSelectColumnsToExport("#configuracionColumnasExportar", '#configuracionGrid');
        utilDialog.setCustomDialogSetEstatus('#configuracionSetEstatusActivoInactivo',
                'configuracionJS.prepareToSetEstatus(1)', 'configuracionJS.prepareToSetEstatus(2)');

        // Inicializaciones extra para el grid
        utilGrid.setup('#configuracionGrid', '#configuracionGridPagerId', '#configuracionGridCurrentPage', '#configuracionGridOrderByColumn',
                '#configuracionGridOrderByType', 'configuracionJS');

        // Crear el modal de busqueda
        utilSearch.buildSearch('#d-searchConfiguracion');

        // Recargar el widget jqGrid
        configuracionJS.reloadGrid();

    }
    // Grid configuracionParametro embebido
    if (jQuery('#gridVisibleConfiguracionParametro').val() == 'true') {
        jQuery('#configuracionParametroGrid').jqGrid({
            url: '',
            datatype: '',
            colNames: [
                'Fila Id',
                jQuery('#configuracionParametro_configuracionParametroIdText').html(),
                jQuery('#configuracionParametro_configuracionIdText').html(),
                jQuery('#configuracionParametro_nombreText').html(),
                jQuery('#configuracionParametro_valorText').html(),
                jQuery('#configuracionParametro_descripcionText').html(),
                jQuery('#configuracionParametro_estatusIdText').html(),
                jQuery('#configuracionParametro_eliminadoIdText').html(),
                jQuery('#configuracionParametro_creacionFechaText').html(),
                jQuery('#configuracionParametro_creacionUsuarioText').html(),
                jQuery('#configuracionParametro_modificacionFechaText').html(),
                jQuery('#configuracionParametro_modificacionUsuarioText').html(),
                '', ''],
            colModel: [{
                    name: 'filaId',
                    index: 'filaId',
                    hidden: true
                },
                {
                    name: 'configuracionParametroId',
                    index: 'configuracionParametroId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'configuracionId',
                    index: 'configuracionId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false

                }, {
                    name: 'nombre',
                    index: 'nombre',
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
                    name: 'descripcion',
                    index: 'descripcion',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: false
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
                    hidden: true,
                    search: false
                }, {
                    name: 'modificacionUsuario',
                    index: 'modificacionUsuario',
                    align: 'center',
                    resizable: false,
                    width: 55,
                    hidden: true,
                    search: false
                }, {
                    name: 'Edit',
                    index: 'Edit',
                    align: 'center',
                    width: 20,
                    sortable: false,
                    search: false,
                    resizable: false
                }, {
                    name: 'Remove',
                    index: 'Remove',
                    align: 'center',
                    width: 20,
                    sortable: false,
                    search: false,
                    resizable: false
                }],
            height: 230,
            toolbar: false,
            hidegrid: false,
            multiselect: false,
            viewrecords: false,
            pager: jQuery('#configuracionParametroGridPagerId'),
            caption: jQuery('#key_configuracionparametro_title').val()
        });
        jQuery('#configuracionParametroGridPagerId').hide();
        jQuery('#configuracionParametroGrid').setGridWidth(
                jQuery("#gbox_" + "configuracionParametroGrid").closest(".grid-container").width() - 5);
    }
    // Limpiar la página
    jQuery('#btnNewConfiguracion').click();
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
    if (jQuery('#gridIndividualModeConfiguracion').val() == 'true') {
        jQuery('#btnNewConfiguracion').hide();
        configuracionJS.findFirst();
    }
});

// Objeto auxiliar para la funcionalidad de desglose.
var desgloseConfiguracionParametroJS = {
    desglose: function (selectIdList) {
        desgloseConfiguracionParametroJS.cleanDesglose();
        utilForm.desglose(selectIdList);
    },
    cleanDesglose: function () {
        utilForm.cleanDesglose("#configuracionParametroform");
    }
};
var configuracionParametroJS = {
    isDesglose: false,
    filaId: null,
    configuracionParametro: null,
    /**
     * Limpia el formulario.
     */
    clean: function () {
        utilForm.reset('#configuracionParametroform');
        jQuery('#configuracionParametroform_filaId').val(0);
        jQuery('#configuracionParametroform_configuracionParametroId').val(0);
        configuracionParametroJS.configuracionParametro = null;
        configuracionParametroJS.configuracionParametroList = null;
    },
    /**
     * Limpia el formulario y el grid de configuracionParametro, usado cuando el
     * usuario presiona el botón Nuevo.
     */
    reset: function () {
        configuracionParametroJS.clean();
        jQuery('#configuracionParametroGrid').clearGridData();
    },
    /** 
     * Valida los campos del formulario de configuracionParametro antes de
     * agregarlos al grid.
     */
    prepareToAgregar: function () {
        var validation = validanguage.validateForm('configuracionParametroform');
        if (validation.result) {
            configuracionParametroJS.agregar();
            configuracionParametroJS.clean();
        }
    },
    /** 
     * Función llamada cuando se presiona el botón Agregar en el formulario de 
     * configuracionParametro.
     */
    agregar: function () {
        // Formar el objeto con los datos del formulario
        var configuracionParametro = utilObject.buildObject(
                '#configuracionParametroform', new ConfiguracionParametroDWR());
        // Valdar si se esta agregando uno nuevo o uno editado
        if (jQuery('#configuracionParametroform_filaId') == 0) {
            configuracionParametroJS.agregarNuevo(configuracionParametro);
        } else {
            configuracionParametroJS.agregarEditado(configuracionParametro);
        }
    },
    /* Agrega un registro editado al grid de configuracionParametro */
    agregarEditado: function (object) {
        object.filaId = jQuery('#configuracionParametroform_filaId').val();
        jQuery('#configuracionParametroGrid').delRowData(object.filaId);
        configuracionParametroJS.agregarNuevo(object);
    },
    /**
     * Agrega un nuevo registro al grid de configuracionParametro, de manera 
     * predeterminada la fila se agrega al final de las existentes.
     * 
     * @param object, es un objeto ConfiguracionParametro con la información a
     * agregar.
     */
    agregarNuevo: function (object) {
        var filaId = configuracionParametroJS.findFilaIdMax() + 1;
        // Editar
        var editar_css = 'ui-icon ui-icon-pencil ui-icon-center';
        var editar_title = 'Editar';
        var editar_onclick = 'configuracionParametroJS.findByFilaId(' + filaId + ')';
        // Eliminar
        var eliminar_css = 'ui-icon ui-icon-trash ui-icon-center';
        var eliminar_title = 'Eliminar';
        var eliminar_onclick = 'configuracionParametroJS.prepareToRemove(' + filaId + ')';
        object.Edit = "<span class = '" + editar_css +
                "' title = '" + editar_title +
                "' style = 'cursor:pointer' onclick = '" + editar_onclick + "'></span>";
        object.Remove = "<span class = '" + eliminar_css +
                "' title = '" + eliminar_title +
                "' style = 'cursor:pointer' onclick = '" + eliminar_onclick + "'></span>";

        // Numero de Fila
        object.filaId = filaId;
        // Limpiar campos si es nuevo
        if (object.configuracionParametroId == 0) {
            utilObject.cleanCamposControl(object);
            utilObject.resolveDescription('#configuracionParametroform', object);
        }
        // Parsear fechas
        var propiedades = utilObject.getProperties(object);
        for (var i = 0; i < propiedades.length; i++) {
            var propiedad = propiedades[ i ] + '';
            if (propiedad.indexOf('fecha') >= 0) {
                if (object[ propiedad ] != null) {
                    object[ propiedad ] = jQuery.datepicker.formatDate(
                            'dd/mm/yy', new Date(object[ propiedad ])
                            );
                }
            }
        }
        // Enviar los datos al grid
        jQuery('#configuracionParametroGrid').addRowData(filaId, object);
    },
    /**
     * Obtiene el numero de fila mas alto en el grid.
     * 
     * @return regresa un entero igual al numero de la fila mas alto.
     */
    findFilaIdMax: function () {
        var filaIdMax = 0;
        var filaList = jQuery('#configuracionParametroGrid').getRowData();
        for (var i = 0; i < filaList.length; i++) {
            if (parseInt(filaList[i].filaId) > filaIdMax) {
                filaIdMax = parseInt(filaList[i].filaId);
            }
        }
        return parseInt(filaIdMax);
    },
    /** 
     * Función llamada al presionar el botón Editar en el registro de 
     * configuracionParametro. Obtiene un objeto desde el grid mediante el numero de la
     * fila, el objeto es parseado para llenar los campos en el formulario.
     * 
     * @param filaId, es el numero de la fila.
     */
    /* Función llamada al presionar el botón Editar en el registro de configuracionParametro*/
    findByFilaId: function (filaId) {
        var filas = jQuery('#configuracionParametroGrid').getRowData();
        for (var i = 0; i < filas.length; i++) {
            if (filas[ i ].filaId == filaId) {
                configuracionParametroJS.clean();
                configuracionParametroJS.configuracionParametro = filas[i];
                utilForm.populate('#configuracionParametroform', filas[ i ]);
                break;
            }
        }
    },
    /** 
     * Función llamada al presionar el botón Eliminar en el registro de
     * configuracionParametro.
     * 
     * @param filaId, es el numero de la fila.
     */
    prepareToRemove: function (filaId) {
        configuracionParametroJS.filaId = filaId;
        utilDialog.showDialog('#d-confirma-eliminar-configuracionparametro');
    },
    /** 
     * Función que elimina una fila del grid configuracionparametro, el numero de 
     * fila a eliminar es el que se encuentra almacenado en la variale
     * configuracionparametroJS.filaId.
     */
    remove: function () {
        jQuery('#configuracionParametroGrid').delRowData(configuracionParametroJS.filaId);
        configuracionParametroJS.clean();
    },

    /** 
     * Guarda los registros del detalle.
     * 
     * @param configuracionId, es el id de configuracion.
     */
    save: function (configuracionId) {
        var dwrObjectList = new Array();
        // Funcionalidad desglose
        if (configuracionParametroJS.isDesglose) {
            jQuery('form[name="configuracionParametroform"]').each(function () {
                if (jQuery(this).hasClass('base-desglose') == false) {
                    // Construir el objeto
                    var obj = utilObject.buildObject('#' +
                            jQuery(this).attr('id'), new ConfiguracionParametroDWR());
                    obj.configuracionId = configuracionId;
                    dwrObjectList.push(obj);
                }
            });
        } else {
            dwrObjectList = utilObject.buildDWRObjectListByGridId(
                    '#configuracionParametroGrid', new ConfiguracionParametroDWR());
            for (var i = 0; i < dwrObjectList.length; i++) {
                dwrObjectList[ i ].configuracionId = configuracionId;
            }
        }
        ConfiguracionDWR.saveConfiguracionParametro(configuracionId,
                dwrObjectList, configuracionParametroJS.saveCallback);
    },
    /* Callback de la función save(), esta función es llamada por DWR al terminar
     * de realizar la operación */
    saveCallback: function () {
        utilEffect.showToast('success', jQuery('#msgGuardadoOk').val());
    },
    /* Función que obtiene configuracionParametro */
    findConfiguracionParametroByIntProperty: function (configuracionId) {
        ConfiguracionDWR.findConfiguracionParametroByIntProperty("configuracionId", configuracionId,
                configuracionParametroJS.findConfiguracionParametroByIntPropertyCallback);
    },
    /**
     * Callback de la función findConfiguracionParametroByIntProperty(), esta función es llamada por DWR al terminar
     * de realizar la operación 
     * 
     * @param data, es una lista de objetos ConfiguracionParametro que serán
     * insertados en el grid
     */
    findConfiguracionParametroByIntPropertyCallback: function (data) {
        configuracionParametroJS.reset();
        if (data != null) {
            for (var i = 0; i < data.length; i++) {
                configuracionParametroJS.agregarNuevo(data[ i ]);
            }

        }
        utilEffect.hideProgressBar();
    },
    deleteMe: function () {
        // do nothing
    }
}
var configuracionJS = {
    /* Variable auxiliar para persistir un objeto (resultado de una consulta) 
     * obtenido mediante el callback de la función "findById(...)". Este objeto
     * es necesario para la funcionalidad de selects encadenados pero puede ser
     * utilizado para otros propósitos. El objeto existe solamente mientras se
     * esta editando la información en el formulario. Al presionar el botón
     * Nuevo o Guardar cualquier referencia a éste regresará "null". El 
     * objeto debe utilizarse como modo de "Solo Lectura" (la modificación de 
     * este objeto no se verá reflejada en la base de datos) */
    configuracion: null,
    /*
     * Variable auxiliar para mantener las busquedas del grid al momento de paginar, ordenar, etc.
     */
    cacheDWR: null,
    /*
     * Función que guarda un nuevo registro en la BD.
     */
    save: function () {
        utilEffect.showProgressBar();
        var configuracion = utilObject.buildObject('#configuracionform', new ConfiguracionDWR());
        configuracion.configuracionId = 0;
        var listaConfiguracions = [configuracion];
        ConfiguracionDWR.save(listaConfiguracions, configuracionJS.saveOrUpdateCallback);
    },
    /*
     * Función que actualiza un registro existente en la BD.
     */
    update: function () {
        utilEffect.showProgressBar();
        var configuracion = utilObject.buildObject('#configuracionform', new ConfiguracionDWR());
        configuracion.configuracionId = jQuery('#configuracionform_configuracionId').val();
        var listaConfiguracions = [configuracion];
        ConfiguracionDWR.update(listaConfiguracions, configuracionJS.saveOrUpdateCallback);
    },
    /*
     * Callback de la funcion save() y update(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param savedIds (array), lista de id's de objetos que
     * se guardaron en base de datos.
     */
    saveOrUpdateCallback: function (savedIds) {
        configuracionParametroJS.save(savedIds[ 0 ]);
        jQuery('#btnNewConfiguracion').click();
        configuracionJS.configuracion = null;
        configuracionJS.reloadGrid();
    },
    /*
     * Función que elimina un registro en el grid al presionar el botón eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como parámetro.
     */
    remove: function () {
        utilEffect.showProgressBar();
        var id = configuracionJS.getEliminarId();
        var listaIds = [id];
        ConfiguracionDWR.remove(listaIds, configuracionJS.removeCallback);
    },
    /*
     * Función que elimina los registros seleccionados en el grid al presionar el botón eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como parámetro.
     */
    removeSelected: function () {
        utilEffect.showProgressBar();
        var listaIds = utilGrid.gridGetSelectedRows('#configuracionGrid');
        ConfiguracionDWR.remove(listaIds, configuracionJS.removeCallback);
    },
    /*
     * Callback de la función remove(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    removeCallback: function () {
        jQuery('#btnNewConfiguracion').click();
        configuracionJS.reloadGrid();
    },
    /*
     * Función que realiza una búsqueda de un objeto por su ID en la BD.
     * Usualmente es llamado cuando el usuario presiona el icono <b>Editar</b>
     * de una fila en el widget jqGrid.
     * 
     * @param id (Integer) Es el id del objeto que se quiere obtener.
     */
    findById: function (id) {
        utilEffect.showProgressBar();
        ConfiguracionDWR.findById(id, configuracionJS.findByIdCallback);
    },
    /*
     * Callback de la función findById(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto con la información de la consulta.
     */
    findByIdCallback: function (data) {
        utilForm.reset('#configuracionform');
        utilForm.populate('#configuracionform', data);
        configuracionJS.configuracion = data;
        configuracionParametroJS.findConfiguracionParametroByIntProperty(
                jQuery('#configuracionform_configuracionId').val());
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
        configuracionJS.cacheDWR = obj;
        var page = jQuery('#configuracionGridCurrentPage').val();
        var rows = jQuery('#configuracionGridRowsByPage').val();
        var order = jQuery('#configuracionGridOrderByColumn').val();
        var orderType = jQuery('#configuracionGridOrderByType').val();
        utilEffect.showProgressBar();
        ConfiguracionDWR.findByCriteria(page, rows, order, orderType, obj, configuracionJS.findByCriteriaCallback);
    },
    /*
     * Callback de la función findByCriteria(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto (Grid) con la información de la 
     * consulta.
     */
    findByCriteriaCallback: function (data) {
        utilGrid.gridUpdate('#configuracionGrid', '#configuracionGridCurrentPage', '#configuracionGridRowsByPage', data);
        utilEffect.hideProgressBar();
    },
    /**
     * Funcion utilizado cuando la pagina es individual
     */
    findFirst: function () {
        utilEffect.showProgressBar();
        ConfiguracionDWR.findFirst(configuracionJS.findByIdCallback);
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
        var nombre = jQuery('#configuracionform_nombre').val();
        ConfiguracionDWR.isValidoNombre(nombre, configuracionJS.isValidoNombreCallback);
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
        if (isValido == false && jQuery('#configuracionform_configuracionId').val() != 0) {
            configuracionJS.doCommit();
            return;
        }

        // Nuevo registro, en este caso se permite el commit
        if (isValido == true && jQuery('#configuracionform_configuracionId').val() == 0) {
            configuracionJS.doCommit();
            return;
        }

        // Registro existente, se le cambio el nombre, en este caso se permite el commit
        if (isValido == true && jQuery('#configuracionform_configuracionId').val() != 0) {
            configuracionJS.doCommit();
            return;
        }

        // Mostrar mensaje de registro duplicado 
        utilDialog.showDialog('#d-registroDuplicadoConfiguracion');
    },

    /*
     * Función que cambia el estatus de un registro en el grid al presionar el botón Activar/Inactivar.
     * Manda llamar al método <b>setEstatus</b> de DWR, éste método.
     *
     * @param id (int) Es el id del registro al cual se le va a cambiar el estatusId
     * @param estatusId (int) Es el nuevo estatusId
     */
    setEstatus: function (id, estatusId) {
        utilEffect.showProgressBar();
        var listaObjetos = [id];
        ConfiguracionDWR.setEstatus(estatusId, listaObjetos, configuracionJS.setEstatusCallback);
    },
    /*
     * Función que cambia el Estatus de los elementos seleccionados en el widget
     * de jqGrid.
     * 
     */
    setEstatusSeleccion: function () {
        utilEffect.showProgressBar();
        var estatusId = configuracionJS.estatusId;
        var listaObjetos = utilGrid.gridGetSelectedRows('#configuracionGrid');
        ConfiguracionDWR.setEstatus(estatusId, listaObjetos, configuracionJS.setEstatusCallback);
    },
    /*
     * Callback de la función setEstatus(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     */
    setEstatusCallback: function () {
        configuracionJS.reloadGrid();
        utilEffect.hideProgressBar();
    },
    /*
     * Función que muestra un mensaje de confirmación para Activar o Inactivar los registros
     * seleccionados del widget jQGrid.
     */
    prepareToSetEstatus: function (estatusId) {
        var seleccion = utilGrid.gridGetSelectedRows('#configuracionGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedConfiguracion');
        } else {
            configuracionJS.estatusId = estatusId;
            // utilDialog.showDialog( '#d-confirmSetEstatusConfiguracion' );
            configuracionJS.setEstatusSeleccion();
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar los registros seleccionados
     * del widget jQGrid.
     */
    prepareToRemoveSelected: function () {
        var seleccion = utilGrid.gridGetSelectedRows('#configuracionGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedConfiguracion');
        } else {
            utilDialog.showDialog('#d-removeSeleccionConfiguracion');
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar un regristro
     * del widget jQGrid.
     */
    prepareToRemove: function (id) {
        configuracionJS.setEliminarId(id);
        utilDialog.showDialog('#d-removeConfiguracion');
    },

    /*
     * Variable para guardar el ID de la fila que se va a eliminar, si 
     * el usuario confirma la decisión al presionar el botón Aceptar del cuadro de diálogo. 
     **/
    eliminarId: 0,
    /*
     * Setter de la variable eliminarId
     **/
    setEliminarId: function (eliminarId) {
        configuracionJS.eliminarId = eliminarId;
    },
    /*
     * Getter de la variable eliminarId
     **/
    getEliminarId: function () {
        return configuracionJS.eliminarId;
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
        var validation = validanguage.validateForm('configuracionform');
        if (validation.result) {
            configuracionJS.isValidoNombre();
        }
    },
    /*
     * Función que inicia el proceso para GUARDAR y ACTUALIZAR/MODIFICAR
     * Una vez que se pasaron las validaciones se realiza el commit.
     */
    doCommit: function () {
        if (jQuery('#configuracionform_configuracionId').val() == 0) {
            configuracionJS.save();
        } else {
            configuracionJS.update();
        }
    },
    /*
     * Función que es llamada para actualizar la información del grid.
     */
    reloadGrid: function () {
        if (configuracionJS.cacheDWR != null) {
            configuracionJS.findByCriteria(configuracionJS.cacheDWR);
        } else {
            configuracionJS.findByCriteria(new ConfiguracionDWR());
        }
    },
    /*
     * Función que es activa el modal de busqueda.
     */
    openSearch: function () {
        utilSearch.openSearch('#d-searchConfiguracion', '#configuracionGrid', '#configuracionform', configuracionJS, new ConfiguracionDWR());
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
        configuracionJS.entity = entity;
        configuracionJS.headers = headers;
        configuracionJS.format = format;
        configuracionJS.reportName = jQuery('#key_configuracion_title').val() +
                '_' + utilMisc.getTodayDate('-');
        var criteriaExample = new ConfiguracionDWR();
        if (configuracionJS.cacheDWR != null) {
            criteriaExample = configuracionJS.cacheDWR;
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
        ConfiguracionDWR.getReportDataTest(sortBy, sortType, criteriaExample, configuracionJS.exportarDatosCallback);
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
                configuracionJS.entity + '&headers=' + configuracionJS.headers +
                '&reportName=' + configuracionJS.reportName + '&format=' + configuracionJS.format +
                '&reportKey=' + reportKey;
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
// Validaciones de configuracionform
validanguage.el.configuracionform_nombre = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
validanguage.el.configuracionform_descripcion = {
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
            errorMsg: 'Solo se permiten 3750 carácteres como máximo.',
            onerror: utilEffect.showValidationTooltip,
            onsuccess: utilEffect.hideValidationTooltip
        }
    ]};
// Validaciones de configuracionParametroform
validanguage.el.configuracionParametroform_configuracionId = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};
validanguage.el.configuracionParametroform_nombre = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
validanguage.el.configuracionParametroform_valor = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};
validanguage.el.configuracionParametroform_descripcion = {
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
            errorMsg: 'Solo se permiten 3750 carácteres como máximo.',
            onerror: utilEffect.showValidationTooltip,
            onsuccess: utilEffect.hideValidationTooltip
        }
    ]};

