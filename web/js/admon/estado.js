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
    utilInput.fixRadios('#estadoform');


    // <editor-fold defaultstate="collapsed" desc="Botones">
    // Botón : NUEVO
    jQuery('#btnNewEstado').click(function () {
        utilForm.reset('#estadoform');
        jQuery('#estadoform_estadoId').val(0);
        estadoJS.estado = null;
    }).customButtonEffect('#btnNewEstado');
    // Botón : GUARDAR
    jQuery('#btnSaveEstado').click(function () {
        estadoJS.prepareToSave();
    }).customButtonEffectBlue('#btnSaveEstado');
    // </editor-fold>

    if (jQuery('#gridVisibleEstado').val() == 'true') {
        jQuery('#estadoGrid').jqGrid({
            url: '',
            datatype: '',
            colNames: [
                jQuery('#estado_estadoIdText').html(),
                jQuery('#estado_codeText').html(),
                jQuery('#estado_paisIdText').html(),
                jQuery('#estado_nombreText').html(),
                jQuery('#estado_estatusIdText').html(),
                jQuery('#estado_eliminadoIdText').html(),
                jQuery('#estado_creacionFechaText').html(),
                jQuery('#estado_creacionUsuarioText').html(),
                jQuery('#estado_modificacionFechaText').html(),
                jQuery('#estado_modificacionUsuarioText').html(),
                '', '', ''],
            colModel: [{
                    name: 'estadoId',
                    index: 'estadoId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'code',
                    index: 'code',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'paisId',
                    index: 'paisId',
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
                    hidden: true,
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
            pager: jQuery('#estadoGridPagerId'),
            caption: jQuery('#key_estado_title').val()
        });
        // jQuery UI Dialogs
        utilDialog.setStandardDialog('#d-removeEstado', 'Confirme', '¿Desea eliminar el registro?', ['Aceptar@estadoJS.remove()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-removeSeleccionEstado', 'Confirme', '¿Desea eliminar los registros seleccionados?', ['Aceptar@estadoJS.removeSelected()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-confirmSetEstatusEstado', 'Confirme', '¿Desea cambiar el estatus de los registros seleccionados?', ['Aceptar@estadoJS.setEstatusSeleccion()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-noSelectedEstado', 'Error', 'Para poder realizar esta operación seleccione uno ó más registros.', ['Aceptar@']);
        utilDialog.setStandardDialog('#d-registroDuplicadoEstado', 'Advertencia', 'Ya existe un registro con este nombre en la base de datos, ¿Desea crear un nuevo registro con este nombre?', ['Aceptar@estadoJS.doCommit()', 'Cancelar@']);
        utilDialog.setCustomDialogSelectColumnsToExport("#estadoColumnasExportar", '#estadoGrid');
        utilDialog.setCustomDialogSetEstatus('#estadoSetEstatusActivoInactivo',
                'estadoJS.prepareToSetEstatus(1)', 'estadoJS.prepareToSetEstatus(2)');

        // Inicializaciones extra para el grid
        utilGrid.setup('#estadoGrid', '#estadoGridPagerId', '#estadoGridCurrentPage', '#estadoGridOrderByColumn',
                '#estadoGridOrderByType', 'estadoJS');

        // Crear el modal de busqueda
        utilSearch.buildSearch('#d-searchEstado');

        // Recargar el widget jqGrid
        estadoJS.reloadGrid();

    }
    // Limpiar la página
    jQuery('#btnNewEstado').click();
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
    if (jQuery('#gridIndividualModeEstado').val() == 'true') {
        jQuery('#btnNewEstado').hide();
        estadoJS.findFirst();
    }
    jQuery('#grid-action-activar-inactivar').hide().next().hide();
});

var estadoJS = {
    /* Variable auxiliar para persistir un objeto (resultado de una consulta) 
     * obtenido mediante el callback de la función "findById(...)". Este objeto
     * es necesario para la funcionalidad de selects encadenados pero puede ser
     * utilizado para otros propósitos. El objeto existe solamente mientras se
     * esta editando la información en el formulario. Al presionar el botón
     * Nuevo o Guardar cualquier referencia a éste regresará "null". El 
     * objeto debe utilizarse como modo de "Solo Lectura" (la modificación de 
     * este objeto no se verá reflejada en la base de datos) */
    estado: null,
    /*
     * Variable auxiliar para mantener las busquedas del grid al momento de paginar, ordenar, etc.
     */
    cacheDWR: null,
    /*
     * Función que guarda un nuevo registro en la BD.
     */
    save: function () {
        utilEffect.showProgressBar();
        var estado = utilObject.buildObject('#estadoform', new EstadoDWR());
        estado.estadoId = 0;
        var listaEstados = [estado];
        EstadoDWR.save(listaEstados, estadoJS.saveOrUpdateCallback);
    },
    /*
     * Función que actualiza un registro existente en la BD.
     */
    update: function () {
        utilEffect.showProgressBar();
        var estado = utilObject.buildObject('#estadoform', new EstadoDWR());
        estado.estadoId = jQuery('#estadoform_estadoId').val();
        var listaEstados = [estado];
        EstadoDWR.update(listaEstados, estadoJS.saveOrUpdateCallback);
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
        if (jQuery('#gridIndividualModeEstado').val() == 'false') {
            utilForm.reset('#estadoform');
            jQuery('#estadoform_estadoId').val(0);
            estadoJS.estado = null;
            estadoJS.reloadGrid();
        }
        utilEffect.hideProgressBar();
    },
    /*
     * Función que elimina un registro en el grid al presionar el botón eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como parámetro.
     */
    remove: function () {
        utilEffect.showProgressBar();
        var id = estadoJS.getEliminarId();
        var listaIds = [id];
        EstadoDWR.remove(listaIds, estadoJS.removeCallback);
    },
    /*
     * Función que elimina los registros seleccionados en el grid al presionar el botón eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como parámetro.
     */
    removeSelected: function () {
        utilEffect.showProgressBar();
        var listaIds = utilGrid.gridGetSelectedRows('#estadoGrid');
        EstadoDWR.remove(listaIds, estadoJS.removeCallback);
    },
    /*
     * Callback de la función remove(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    removeCallback: function () {
        jQuery('#btnNewEstado').click();
        estadoJS.reloadGrid();
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
        EstadoDWR.findById(id, estadoJS.findByIdCallback);
    },
    /*
     * Callback de la función findById(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto con la información de la consulta.
     */
    findByIdCallback: function (data) {
        utilForm.reset('#estadoform');
        utilForm.populate('#estadoform', data);
        estadoJS.estado = data;
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
        estadoJS.cacheDWR = obj;
        var page = jQuery('#estadoGridCurrentPage').val();
        var rows = jQuery('#estadoGridRowsByPage').val();
        var order = jQuery('#estadoGridOrderByColumn').val();
        var orderType = jQuery('#estadoGridOrderByType').val();
        utilEffect.showProgressBar();
        EstadoDWR.findByCriteria(page, rows, order, orderType, obj, estadoJS.findByCriteriaCallback);
    },
    /*
     * Callback de la función findByCriteria(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto (Grid) con la información de la 
     * consulta.
     */
    findByCriteriaCallback: function (data) {
        utilGrid.gridUpdate('#estadoGrid', '#estadoGridCurrentPage', '#estadoGridRowsByPage', data);
        utilEffect.hideProgressBar();
    },
    /**
     * Funcion utilizado cuando la pagina es individual
     */
    findFirst: function () {
        utilEffect.showProgressBar();
        EstadoDWR.findFirst(estadoJS.findByIdCallback);
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
        var nombre = jQuery('#estadoform_nombre').val();
        EstadoDWR.isValidoNombre(nombre, estadoJS.isValidoNombreCallback);
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
        if (isValido == false && jQuery('#estadoform_estadoId').val() != 0) {
            estadoJS.doCommit();
            return;
        }

        // Nuevo registro, en este caso se permite el commit
        if (isValido == true && jQuery('#estadoform_estadoId').val() == 0) {
            estadoJS.doCommit();
            return;
        }

        // Registro existente, se le cambio el nombre, en este caso se permite el commit
        if (isValido == true && jQuery('#estadoform_estadoId').val() != 0) {
            estadoJS.doCommit();
            return;
        }

        // Mostrar mensaje de registro duplicado 
        utilDialog.showDialog('#d-registroDuplicadoEstado');
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
        EstadoDWR.setEstatus(estatusId, listaObjetos, estadoJS.setEstatusCallback);
    },
    /*
     * Función que cambia el Estatus de los elementos seleccionados en el widget
     * de jqGrid.
     * 
     */
    setEstatusSeleccion: function () {
        utilEffect.showProgressBar();
        var estatusId = estadoJS.estatusId;
        var listaObjetos = utilGrid.gridGetSelectedRows('#estadoGrid');
        EstadoDWR.setEstatus(estatusId, listaObjetos, estadoJS.setEstatusCallback);
    },
    /*
     * Callback de la función setEstatus(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     */
    setEstatusCallback: function () {
        estadoJS.reloadGrid();
        utilEffect.hideProgressBar();
    },
    /*
     * Función que muestra un mensaje de confirmación para Activar o Inactivar los registros
     * seleccionados del widget jQGrid.
     */
    prepareToSetEstatus: function (estatusId) {
        var seleccion = utilGrid.gridGetSelectedRows('#estadoGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedEstado');
        } else {
            estadoJS.estatusId = estatusId;
            // utilDialog.showDialog( '#d-confirmSetEstatusEstado' );
            estadoJS.setEstatusSeleccion();
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar los registros seleccionados
     * del widget jQGrid.
     */
    prepareToRemoveSelected: function () {
        var seleccion = utilGrid.gridGetSelectedRows('#estadoGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedEstado');
        } else {
            utilDialog.showDialog('#d-removeSeleccionEstado');
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar un regristro
     * del widget jQGrid.
     */
    prepareToRemove: function (id) {
        estadoJS.setEliminarId(id);
        utilDialog.showDialog('#d-removeEstado');
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
        estadoJS.eliminarId = eliminarId;
    },
    /*
     * Getter de la variable eliminarId
     **/
    getEliminarId: function () {
        return estadoJS.eliminarId;
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
        var validation = validanguage.validateForm('estadoform');
        if (validation.result) {
            estadoJS.isValidoNombre();
        }
    },
    /*
     * Función que inicia el proceso para GUARDAR y ACTUALIZAR/MODIFICAR
     * Una vez que se pasaron las validaciones se realiza el commit.
     */
    doCommit: function () {
        if (jQuery('#estadoform_estadoId').val() == 0) {
            estadoJS.save();
        } else {
            estadoJS.update();
        }
    },
    /*
     * Función que es llamada para actualizar la información del grid.
     */
    reloadGrid: function () {
        if (estadoJS.cacheDWR != null) {
            estadoJS.findByCriteria(estadoJS.cacheDWR);
        } else {
            estadoJS.findByCriteria(new EstadoDWR());
        }
    },
    /*
     * Función que obtene las opciones de un elemento select estadoform_paisId para recargarlo mediante AJAX.
     */
    getPais: function () {
        utilEffect.showProgressBar();
        EstadoDWR.getPais(estadoJS.getPaisCallback);
    },
    /*
     * Callback de la función getPais() esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    getPaisCallback: function (list) {
        var idCmb = jQuery('#estadoform_paisId').val();
        jQuery('#estadoform_paisId').find('option').remove();
        for (var i = 0; i < list.length; i++) {
            var object = list[i];
            var id = object.paisId;
            var nombre = object.nombre;
            jQuery('#estadoform_paisId').append('<option value="' + id + '">' + nombre + '</option>');
        }
        // Si el usuario selecciono una opcion en el popup entonces ponerlo como seleccionado
        var sel = parseInt(jQuery('#idFromIframe').val());
        if (sel == 0) {
            // La opcion del popup seleccionada esta inactiva, validar la opcion seleccionada antes de abrir el popup
            if (jQuery("#estadoform_paisId option[value='" + idCmb + "']").length > 0) {
                jQuery('#estadoform_paisId').val(idCmb);
            } else {
                jQuery('#estadoform_paisId').val(0);
            }
        } else {
            // Checar si la seleccion esta activa
            if (jQuery("#estadoform_paisId option[value='" + sel + "']").length > 0) {
                jQuery('#estadoform_paisId').val(sel);
            }
        }
        utilEffect.hideProgressBar();
    },
    /*
     * Función que es activa el modal de busqueda.
     */
    openSearch: function () {
        utilSearch.openSearch('#d-searchEstado', '#estadoGrid', '#estadoform', estadoJS, new EstadoDWR());
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
        estadoJS.entity = entity;
        estadoJS.headers = headers;
        estadoJS.format = format;
        estadoJS.reportName = jQuery('#key_estado_title').val() +
                '_' + utilMisc.getTodayDate('-');
        var criteriaExample = new EstadoDWR();
        if (estadoJS.cacheDWR != null) {
            criteriaExample = estadoJS.cacheDWR;
        }
        // utilEffect.showProgressBar();
        jQuery().toastmessage('showToast', {
            text: 'Filtrando información...',
            sticky: false,
            position: 'bottom-center',
            type: 'notice',
            closeText: '',
            close: function () {
            }
        });
        EstadoDWR.getReportDataTest(sortBy, sortType, criteriaExample, estadoJS.exportarDatosCallback);
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
                estadoJS.entity + '&headers=' + estadoJS.headers +
                '&reportName=' + estadoJS.reportName + '&format=' + estadoJS.format +
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
// Validaciones de estadoform
validanguage.el.estadoform_code = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};
validanguage.el.estadoform_paisId = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
validanguage.el.estadoform_nombre = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};

