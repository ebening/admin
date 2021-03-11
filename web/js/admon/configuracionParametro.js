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
    utilInput.fixRadios('#configuracionParametroform');


    // <editor-fold defaultstate="collapsed" desc="Botones">
    // Botón : NUEVO
    jQuery('#btnNewConfiguracionParametro').click(function () {
        utilForm.reset('#configuracionParametroform');
        jQuery('#configuracionParametroform_configuracionParametroId').val(0);
        configuracionParametroJS.configuracionParametro = null;
    }).customButtonEffect('#btnNewConfiguracionParametro');
    // Botón : GUARDAR
    jQuery('#btnSaveConfiguracionParametro').click(function () {
        configuracionParametroJS.prepareToSave();
    }).customButtonEffectBlue('#btnSaveConfiguracionParametro');
    // </editor-fold>

    if (jQuery('#gridVisibleConfiguracionParametro').val() == 'true') {
        jQuery('#configuracionParametroGrid').jqGrid({
            url: '',
            datatype: '',
            colNames: [
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
                '', '', ''],
            colModel: [{
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
                    hidden: false,
                    search: true
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
                    hidden: true,
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
            pager: jQuery('#configuracionParametroGridPagerId'),
            caption: jQuery('#key_configuracionparametro_title').val()
        });
        // jQuery UI Dialogs
        utilDialog.setStandardDialog('#d-removeConfiguracionParametro', jQuery('#msgAlertaText').val(), jQuery('#msgDeseaEliminarText').val(), ['Aceptar@configuracionParametroJS.remove()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-removeSeleccionConfiguracionParametro', jQuery('#msgAlertaText').val(), jQuery('#msgDeseaEliminarSeleccionText').val(), ['Aceptar@configuracionParametroJS.removeSelected()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-confirmSetEstatusConfiguracionParametro', jQuery('#msgAlertaText').val(), jQuery('#msgCambiarEstatusText').val(), ['Aceptar@configuracionParametroJS.setEstatusSeleccion()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-noSelectedConfiguracionParametro', jQuery('#msgAlertaText').val(), jQuery('#msgSeleccioneUnoOMas').val(), ['Aceptar@']);
        utilDialog.setStandardDialog('#d-registroDuplicadoConfiguracionParametro', jQuery('#msgAlertaText').val(), jQuery('#msgYaExisteRegistro').val(), ['Aceptar@configuracionParametroJS.doCommit()', 'Cancelar@']);
        utilDialog.setCustomDialogSelectColumnsToExport("#configuracionParametroColumnasExportar", '#configuracionParametroGrid');
        utilDialog.setCustomDialogSetEstatus('#configuracionParametroSetEstatusActivoInactivo',
                'configuracionParametroJS.prepareToSetEstatus(1)', 'configuracionParametroJS.prepareToSetEstatus(2)');

        // Inicializaciones extra para el grid
        utilGrid.setup('#configuracionParametroGrid', '#configuracionParametroGridPagerId', '#configuracionParametroGridCurrentPage', '#configuracionParametroGridOrderByColumn',
                '#configuracionParametroGridOrderByType', 'configuracionParametroJS');

        // Crear el modal de busqueda
        utilSearch.buildSearch('#d-searchConfiguracionParametro');

        // Recargar el widget jqGrid
        configuracionParametroJS.reloadGrid();

    }
    // Limpiar la página
    jQuery('#btnNewConfiguracionParametro').click();
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
    if (jQuery('#gridIndividualModeConfiguracionParametro').val() == 'true') {
        jQuery('#btnNewConfiguracionParametro').hide();
        configuracionParametroJS.findFirst();
    }
});

var configuracionParametroJS = {
    /* Variable auxiliar para persistir un objeto (resultado de una consulta) 
     * obtenido mediante el callback de la función "findById(...)". Este objeto
     * es necesario para la funcionalidad de selects encadenados pero puede ser
     * utilizado para otros propósitos. El objeto existe solamente mientras se
     * esta editando la información en el formulario. Al presionar el botón
     * Nuevo o Guardar cualquier referencia a éste regresará "null". El 
     * objeto debe utilizarse como modo de "Solo Lectura" (la modificación de 
     * este objeto no se verá reflejada en la base de datos) */
    configuracionParametro: null,
    /*
     * Variable auxiliar para mantener las busquedas del grid al momento de paginar, ordenar, etc.
     */
    cacheDWR: null,
    /*
     * Función que guarda un nuevo registro en la BD.
     */
    save: function () {
        utilEffect.showProgressBar();
        var configuracionParametro = utilObject.buildObject('#configuracionParametroform', new ConfiguracionParametroDWR());
        configuracionParametro.configuracionParametroId = 0;
        var listaConfiguracionParametros = [configuracionParametro];
        ConfiguracionParametroDWR.save(listaConfiguracionParametros, configuracionParametroJS.saveOrUpdateCallback);
    },
    /*
     * Función que actualiza un registro existente en la BD.
     */
    update: function () {
        utilEffect.showProgressBar();
        var configuracionParametro = utilObject.buildObject('#configuracionParametroform', new ConfiguracionParametroDWR());
        configuracionParametro.configuracionParametroId = jQuery('#configuracionParametroform_configuracionParametroId').val();
        var listaConfiguracionParametros = [configuracionParametro];
        ConfiguracionParametroDWR.update(listaConfiguracionParametros, configuracionParametroJS.saveOrUpdateCallback);
    },
    /*
     * Callback de la funcion save() y update(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param savedIds (array), lista de id's de objetos que
     * se guardaron en base de datos.
     */
    saveOrUpdateCallback: function (savedIds) {
        // Grid Multiple
        if (jQuery('#gridIndividualModeConfiguracionParametro').val() == 'false') {
            utilForm.reset('#configuracionParametroform');
            jQuery('#configuracionParametroform_configuracionParametroId').val(0);
            configuracionParametroJS.configuracionParametro = null;
            configuracionParametroJS.reloadGrid();
        }
        utilEffect.hideProgressBar();
        utilEffect.showToast('success', jQuery('#msgGuardadoOk').val());
    },
    /*
     * Función que elimina un registro en el grid al presionar el botón eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como parámetro.
     */
    remove: function () {
        utilEffect.showProgressBar();
        var id = configuracionParametroJS.getEliminarId();
        var listaIds = [id];
        ConfiguracionParametroDWR.remove(listaIds, configuracionParametroJS.removeCallback);
    },
    /*
     * Función que elimina los registros seleccionados en el grid al presionar el botón eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como parámetro.
     */
    removeSelected: function () {
        utilEffect.showProgressBar();
        var listaIds = utilGrid.gridGetSelectedRows('#configuracionParametroGrid');
        ConfiguracionParametroDWR.remove(listaIds, configuracionParametroJS.removeCallback);
    },
    /*
     * Callback de la función remove(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    removeCallback: function () {
        jQuery('#btnNewConfiguracionParametro').click();
        configuracionParametroJS.reloadGrid();
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
        ConfiguracionParametroDWR.findById(id, configuracionParametroJS.findByIdCallback);
    },
    /*
     * Callback de la función findById(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto con la información de la consulta.
     */
    findByIdCallback: function (data) {
        utilForm.reset('#configuracionParametroform');
        utilForm.populate('#configuracionParametroform', data);
        configuracionParametroJS.configuracionParametro = data;
        utilEffect.hideProgressBar();
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
        configuracionParametroJS.cacheDWR = obj;
        var page = jQuery('#configuracionParametroGridCurrentPage').val();
        var rows = jQuery('#configuracionParametroGridRowsByPage').val();
        var order = jQuery('#configuracionParametroGridOrderByColumn').val();
        var orderType = jQuery('#configuracionParametroGridOrderByType').val();
        utilEffect.showProgressBar();
        ConfiguracionParametroDWR.findByCriteria(page, rows, order, orderType, obj, configuracionParametroJS.findByCriteriaCallback);
    },
    /*
     * Callback de la función findByCriteria(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto (Grid) con la información de la 
     * consulta.
     */
    findByCriteriaCallback: function (data) {
        utilGrid.gridUpdate('#configuracionParametroGrid', '#configuracionParametroGridCurrentPage', '#configuracionParametroGridRowsByPage', data);
        utilEffect.hideProgressBar();
    },
    /**
     * Funcion utilizado cuando la pagina es individual
     */
    findFirst: function () {
        utilEffect.showProgressBar();
        ConfiguracionParametroDWR.findFirst(configuracionParametroJS.findByIdCallback);
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
        var nombre = jQuery('#configuracionParametroform_nombre').val();
        ConfiguracionParametroDWR.isValidoNombre(nombre, configuracionParametroJS.isValidoNombreCallback);
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
        if (isValido == false && jQuery('#configuracionParametroform_configuracionParametroId').val() != 0) {
            configuracionParametroJS.doCommit();
            return;
        }

        // Nuevo registro, en este caso se permite el commit
        if (isValido == true && jQuery('#configuracionParametroform_configuracionParametroId').val() == 0) {
            configuracionParametroJS.doCommit();
            return;
        }

        // Registro existente, se le cambio el nombre, en este caso se permite el commit
        if (isValido == true && jQuery('#configuracionParametroform_configuracionParametroId').val() != 0) {
            configuracionParametroJS.doCommit();
            return;
        }

        // Mostrar mensaje de registro duplicado 
        utilDialog.showDialog('#d-registroDuplicadoConfiguracionParametro');
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
        ConfiguracionParametroDWR.setEstatus(estatusId, listaObjetos, configuracionParametroJS.setEstatusCallback);
    },
    /*
     * Función que cambia el Estatus de los elementos seleccionados en el widget
     * de jqGrid.
     * 
     */
    setEstatusSeleccion: function () {
        utilEffect.showProgressBar();
        var estatusId = configuracionParametroJS.estatusId;
        var listaObjetos = utilGrid.gridGetSelectedRows('#configuracionParametroGrid');
        ConfiguracionParametroDWR.setEstatus(estatusId, listaObjetos, configuracionParametroJS.setEstatusCallback);
    },
    /*
     * Callback de la función setEstatus(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     */
    setEstatusCallback: function () {
        configuracionParametroJS.reloadGrid();
        utilEffect.hideProgressBar();
    },
    /*
     * Función que muestra un mensaje de confirmación para Activar o Inactivar los registros
     * seleccionados del widget jQGrid.
     */
    prepareToSetEstatus: function (estatusId) {
        var seleccion = utilGrid.gridGetSelectedRows('#configuracionParametroGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedConfiguracionParametro');
        } else {
            configuracionParametroJS.estatusId = estatusId;
            // utilDialog.showDialog( '#d-confirmSetEstatusConfiguracionParametro' );
            configuracionParametroJS.setEstatusSeleccion();
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar los registros seleccionados
     * del widget jQGrid.
     */
    prepareToRemoveSelected: function () {
        var seleccion = utilGrid.gridGetSelectedRows('#configuracionParametroGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedConfiguracionParametro');
        } else {
            utilDialog.showDialog('#d-removeSeleccionConfiguracionParametro');
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar un regristro
     * del widget jQGrid.
     */
    prepareToRemove: function (id) {
        configuracionParametroJS.setEliminarId(id);
        utilDialog.showDialog('#d-removeConfiguracionParametro');
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
        configuracionParametroJS.eliminarId = eliminarId;
    },
    /*
     * Getter de la variable eliminarId
     **/
    getEliminarId: function () {
        return configuracionParametroJS.eliminarId;
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
        var validation = validanguage.validateForm('configuracionParametroform');
        if (validation.result) {
            configuracionParametroJS.isValidoNombre();
        }
    },
    /*
     * Función que inicia el proceso para GUARDAR y ACTUALIZAR/MODIFICAR
     * Una vez que se pasaron las validaciones se realiza el commit.
     */
    doCommit: function () {
        if (jQuery('#configuracionParametroform_configuracionParametroId').val() == 0) {
            configuracionParametroJS.save();
        } else {
            configuracionParametroJS.update();
        }
    },
    /*
     * Función que es llamada para actualizar la información del grid.
     */
    reloadGrid: function () {
        if (configuracionParametroJS.cacheDWR != null) {
            configuracionParametroJS.findByCriteria(configuracionParametroJS.cacheDWR);
        } else {
            configuracionParametroJS.findByCriteria(new ConfiguracionParametroDWR());
        }
    },
    /*
     * Función que obtene las opciones de un elemento select configuracionParametroform_configuracionId para recargarlo mediante AJAX.
     */
    getConfiguracion: function () {
        utilEffect.showProgressBar();
        ConfiguracionParametroDWR.getConfiguracion(configuracionParametroJS.getConfiguracionCallback);
    },
    /*
     * Callback de la función getConfiguracion() esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    getConfiguracionCallback: function (list) {
        var idCmb = jQuery('#configuracionParametroform_configuracionId').val();
        jQuery('#configuracionParametroform_configuracionId').find('option').remove();
        for (var i = 0; i < list.length; i++) {
            var object = list[i];
            var id = object.configuracionId;
            var nombre = object.nombre;
            jQuery('#configuracionParametroform_configuracionId').append('<option value="' + id + '">' + nombre + '</option>');
        }
        // Si el usuario selecciono una opcion en el popup entonces ponerlo como seleccionado
        var sel = parseInt(jQuery('#idFromIframe').val());
        if (sel == 0) {
            // La opcion del popup seleccionada esta inactiva, validar la opcion seleccionada antes de abrir el popup
            if (jQuery("#configuracionParametroform_configuracionId option[value='" + idCmb + "']").length > 0) {
                jQuery('#configuracionParametroform_configuracionId').val(idCmb);
            } else {
                jQuery('#configuracionParametroform_configuracionId').val(0);
            }
        } else {
            // Checar si la seleccion esta activa
            if (jQuery("#configuracionParametroform_configuracionId option[value='" + sel + "']").length > 0) {
                jQuery('#configuracionParametroform_configuracionId').val(sel);
            }
        }
        utilEffect.hideProgressBar();
    },
    /*
     * Función que es activa el modal de busqueda.
     */
    openSearch: function () {
        utilSearch.openSearch('#d-searchConfiguracionParametro', '#configuracionParametroGrid', '#configuracionParametroform', configuracionParametroJS, new ConfiguracionParametroDWR());
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
        configuracionParametroJS.entity = entity;
        configuracionParametroJS.headers = headers;
        configuracionParametroJS.format = format;
        configuracionParametroJS.reportName = jQuery('#key_configuracionparametro_title').val() +
                '_' + utilMisc.getTodayDate('-');
        var criteriaExample = new ConfiguracionParametroDWR();
        if (configuracionParametroJS.cacheDWR != null) {
            criteriaExample = configuracionParametroJS.cacheDWR;
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
        ConfiguracionParametroDWR.getReportDataTest(sortBy, sortType, criteriaExample, configuracionParametroJS.exportarDatosCallback);
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
                configuracionParametroJS.entity + '&headers=' + configuracionParametroJS.headers +
                '&reportName=' + configuracionParametroJS.reportName + '&format=' + configuracionParametroJS.format +
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

