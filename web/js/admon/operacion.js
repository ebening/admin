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
    utilInput.fixRadios('#operacionform');


    // Bot&oacute;n : NUEVO
    jQuery('#btnNewOperacion').click(function () {
        utilForm.reset('#operacionform');
        jQuery('#operacionId').val(0);
        operacionJS.operacion = null;
    }).customButtonEffect('#btnNewOperacion');
    // Bot&oacute;n : GUARDAR
    jQuery('#btnSaveOperacion').click(function () {
    	var validation = validanguage.validateForm('operacionform');
        if (validation.result) {
        	operacionJS.doCommit();
        }
    }).customButtonEffectBlue('#btnSaveOperacion');

    if (jQuery('#gridVisibleOperacion').val() == 'true') {
        jQuery('#operacionGrid').jqGrid({
            url: '',
            datatype: '',
            colNames: [
            	jQuery('#operacion_operacionId').val(),
            	jQuery('#operacion_aplicacionId').val(),
                jQuery('#operacion_aplicacion').val(),
                jQuery('#operacion_moduloId').val(),
                jQuery('#operacion_modulo').val(),
                jQuery('#operacion_nombreES').val(),
                jQuery('#operacion_nombreEN').val(),
                jQuery('#operacion_descripcion').val(),
                jQuery('#operacion_fechaModificacion').val(),
                jQuery('#operacion_usuarioModificacion').val(),
                jQuery('#operacion_activo').val(),
                jQuery('#operacion_modificar').val(),jQuery('#operacion_eliminar').val()
            ],
            colModel: [{
                    name: 'operacionId',
                    index: 'operacionId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'aplicacionId',
                    index: 'aplicacionId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'aplicacion',
                    index: 'aplicacion',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'moduloId',
                    index: 'moduloId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'modulo',
                    index: 'modulo',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'nombreES',
                    index: 'nombreES',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'nombreEN',
                    index: 'nombreEN',
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
                    name: 'modificacionFecha',
                    index: 'modificacionFecha',
                    align: 'center',
                    resizable: false,
                    width     : 65,
                    hidden: false,
                    search: false
                }, {
                    name: 'modificacionUsuarioStr',
                    index: 'modificacionUsuarioStr',
                    align: 'center',
                    resizable: false,
                    width     : 65,
                    hidden: false,
                    search: false
                }, {
                	name      : 'State',
                	index     : 'State',
                	align     : 'center',
                	width     : 35,
                	sortable  : false,
                	search    : false,
                	resizable : false
                	}, {
                    name: 'Edit',
                    index: 'Edit',
                    align: 'center',
                    width: 49,
                    sortable: false,
                    search: false,
                    resizable: false
                }, {
                    name: 'Remove',
                    index: 'Remove',
                    align: 'center',
                    width: 49,
                    sortable: false,
                    search: false,
                    resizable: false
                }],
            height: 260,
            toolbar: false,
            hidegrid: true,
            multiselect: true,
            viewrecords: true,
            rowList: [10, 20, 30, 50, 100],
            pager: jQuery('#operacionGridPagerId'),
            caption: jQuery('#operacion.title').val()
        });
        // jQuery UI Dialogs
        utilDialog.setStandardDialog('#d-removeOperacion', 'Confirme', '&iquest;Desea eliminar el registro?', ['Aceptar@operacionJS.remove()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-removeSeleccionOperacion', 'Confirme', '&iquest;Desea eliminar los registros seleccionados?', ['Aceptar@operacionJS.removeSelected()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-confirmSetEstatusOperacion', 'Confirme', '&iquest;Desea cambiar el estatus de los registros seleccionados?', ['Aceptar@operacionJS.setEstatusSeleccion()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-noSelectedOperacion', 'Error', 'Para poder realizar esta operaci&oacute;n seleccione uno &oacute; m&aacute;s registros.', ['Aceptar@']);
        utilDialog.setStandardDialog('#d-registroDuplicadoOperacion', 'Advertencia', 'Ya existe un registro con este nombre en la base de datos, &iquest;Desea crear un nuevo registro con este nombre?', ['Aceptar@operacionJS.doCommit()', 'Cancelar@']);
        utilDialog.setCustomDialogSelectColumnsToExport("#operacionColumnasExportar", '#operacionGrid');
        utilDialog.setCustomDialogSetEstatus('#operacionSetEstatusActivoInactivo',
                'operacionJS.prepareToSetEstatus(1)', 'operacionJS.prepareToSetEstatus(2)');

        // Inicializaciones extra para el grid
        utilGrid.setup('#operacionGrid', '#operacionGridPagerId', '#operacionGridCurrentPage', '#operacionGridOrderByColumn', '#operacionGridOrderByType', 'operacionJS');

        // Crear el modal de busqueda
        utilSearch.buildSearch('#d-searchOperacion');

        // Recargar el widget jqGrid
        operacionJS.reloadGrid();

    }
    // Limpiar la p&aacute;gina
    jQuery('#btnNewOperacion').click();
    // Edici&oacute;n externa
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
    if (jQuery('#gridIndividualModeOperacion').val() == 'true') {
        jQuery('#btnNewOperacion').hide();
        operacionJS.findFirst();
    }
    jQuery('#grid-action-activar-inactivar').hide().next().hide();
});

var operacionJS = {
    /* Variable auxiliar para persistir un objeto (resultado de una consulta) 
     * obtenido mediante el callback de la funci&oacute;n "findById(...)". Este objeto
     * es necesario para la funcionalidad de selects encadenados pero puede ser
     * utilizado para otros prop&oacute;sitos. El objeto existe solamente mientras se
     * esta editando la informaci&oacute;n en el formulario. Al presionar el bot&oacute;n
     * Nuevo o Guardar cualquier referencia a éste regresar&aacute; "null". El 
     * objeto debe utilizarse como modo de "Solo Lectura" (la modificaci&oacute;n de 
     * este objeto no se ver&aacute; reflejada en la base de datos) */
    operacion: null,
    /*
     * Variable auxiliar para mantener las busquedas del grid al momento de paginar, ordenar, etc.
     */
    cacheDWR: null,
    /*
     * Funci&oacute;n que guarda un nuevo registro en la BD.
     */
    save: function () {
        utilEffect.showProgressBar();
        var operacion = utilObject.buildObject('#operacionform', new OperacionDWR());
        operacion.operacionId = 0;
        operacion.aplicacionId = $('#aplicacionId').val();
        operacion.moduloId = $('#moduloId').val();
        var listaOperacions = [operacion];
        OperacionDWR.save(listaOperacions, operacionJS.saveOrUpdateCallback);
    },
    /*
     * Funci&oacute;n que actualiza un registro existente en la BD.
     */
    update: function () {
        utilEffect.showProgressBar();
        var operacion = utilObject.buildObject('#operacionform', new OperacionDWR());
        operacion.operacionId = jQuery('#operacionId').val();
        operacion.aplicacionId = $('#aplicacionId').val();
        operacion.moduloId = $('#moduloId').val();
        var listaOperacions = [operacion];
        OperacionDWR.update(listaOperacions, operacionJS.saveOrUpdateCallback);
    },
    /*
     * Callback de la funcion save() y update(), esta funci&oacute;n es llamada por DWR al 
     * terminar de realizar la operaci&oacute;n.
     * 
     * @param savedIds (array), lista de id's de objetos que
     * se guardaron en base de datos.
     */
    saveOrUpdateCallback: function (savedIds) {
        // Grid Multiple
        utilForm.reset('#operacionform');
        jQuery('#operacionId').val(0);
        operacionJS.operacion = null;
        operacionJS.reloadGrid();
        utilEffect.hideProgressBar();
        utilEffect.showToast('success', jQuery('#msgGuardadoOk').val());
    },
    /*
     * Funci&oacute;n que elimina un registro en el grid al presionar el bot&oacute;n eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como par&aacute;metro.
     */
    remove: function () {
        utilEffect.showProgressBar();
        var id = operacionJS.getEliminarId();
        var listaIds = [id];
        OperacionDWR.remove(listaIds, operacionJS.removeCallback);
    },
    /*
     * Funci&oacute;n que elimina los registros seleccionados en el grid al presionar el bot&oacute;n eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como par&aacute;metro.
     */
    removeSelected: function () {
        utilEffect.showProgressBar();
        var listaIds = utilGrid.gridGetSelectedRows('#operacionGrid');
        OperacionDWR.remove(listaIds, operacionJS.removeCallback);
    },
    /*
     * Callback de la funci&oacute;n remove(), esta funci&oacute;n es llamada por DWR al 
     * terminar de realizar la operaci&oacute;n.
     */
    removeCallback: function () {
        jQuery('#btnNewOperacion').click();
        operacionJS.reloadGrid();
    },
    /*
     * Funci&oacute;n que realiza una búsqueda de un objeto por su ID en la BD.
     * Usualmente es llamado cuando el usuario presiona el icono <b>Editar</b>
     * de una fila en el widget jqGrid.
     * 
     * @param id (Integer) Es el id del objeto que se quiere obtener.
     */
    findById: function (id) {
        utilEffect.showProgressBar();
        OperacionDWR.findById(id, operacionJS.findByIdCallback);
    },
    /*
     * Callback de la funci&oacute;n findById(...), esta funci&oacute;n es llamada por DWR al 
     * terminar de realizar la operaci&oacute;n.
     * 
     * @param data (Object) Es un objeto con la informaci&oacute;n de la consulta.
     */
    findByIdCallback: function (data) {
        utilForm.reset('#operacionform');
        utilForm.populate('#operacionform', data);
        $('#aplicacionId').val(data.aplicacionId);
        $('#operacionId').val(data.operacionId);
        utilEffect.hideProgressBar();
        operacionJS.getModulos(data.moduloId);
        operacionJS.operacion = data;
    },
    /*
     * Funci&oacute;n que realiza una búsqueda en la BD. Este objeto contiene 
     * todos los criterios para realizar la consulta.
     * 
     * @param obj (Object) Es el objeto con el cual se va a realizar la 
     * consulta. El resultado ser&aacute; una lista de objetos que concuerden con las
     * propiedades del objeto que se envi&oacute; como par&aacute;metro.
     */
    findByCriteria: function (obj) {
        operacionJS.cacheDWR = obj;
        var page = jQuery('#operacionGridCurrentPage').val();
        var rows = jQuery('#operacionGridRowsByPage').val();
        var order = jQuery('#operacionGridOrderByColumn').val();
        var orderType = jQuery('#operacionGridOrderByType').val();
        utilEffect.showProgressBar();
        OperacionDWR.findByCriteria(page, rows, order, orderType, obj, function(data){
        	utilGrid.gridUpdate('#operacionGrid', '#operacionGridCurrentPage', '#operacionGridRowsByPage', data);
            utilEffect.hideProgressBar();
        });
    },
    /**
     * Funcion utilizado cuando la pagina es individual
     */
    findFirst: function () {
        utilEffect.showProgressBar();
        OperacionDWR.findFirst(operacionJS.findByIdCallback);
    },
    /*
     * Funci&oacute;n que v&aacute;lida el atributo <b>NOMBRE</b> de un registro en la BD. 
     * Se valida si ya existe un registro con el mismo nombre, esto con el 
     * prop&oacute;sito de advertir al usuario sobre registros duplicados.
     * Por default el <b>CoreGenerator</b> est&aacute; configurado solo para mostrar
     * advertencias al usuario y permite m&aacute;s de un registro con el mismo
     * nombre en la BD, est&aacute; metodología puede cambiarse manualmente modificando
     * el <b>callback</b> de esta funci&oacute;n.
     * NOTICE: Ésta funci&oacute;n es generada por el <b>CoreGenerator</b>
     * únicamente cuando la tabla tiene el campo 'NOMBRE', si la tabla no
     * contiene ese campo la funci&oacute;n estar&aacute; ausente. 
     * 
     * Ésta funci&oacute;n siempre es llamada antes de realizar algún commit a la BD 
     * (guardar &oacute; actualizar/modificar).
     */
    isValidoNombre: function () {
        var nombre = jQuery('#operacionform_nombre').val();
        OperacionDWR.isValidoNombre(nombre, operacionJS.isValidoNombreCallback);
    },
    /*
     * Callback de la funci&oacute;n isValidoNombre(), esta funci&oacute;n es llamada por DWR al 
     * terminar de realizar la operaci&oacute;n.
     * 
     * @param isValido (boolean) es true cuando no existe ningún registro con
     * el nombre a validar, false cuando hay al menos un registro con el mismo
     * nombre.
     */
    isValidoNombreCallback: function (isValido) {
        // Cuando se esta actualizando un registro pero no se cambia el nombre, 
        // la funci&oacute;n isValidoNombre() siempre regresa false al momento de 
        // intentar guardar por que el registro que se esta editando tiene el mismo 
        // nombre que en base de datos, en este caso se permite el commit.
        if (isValido == false && jQuery('#operacionId').val() != 0) {
            operacionJS.doCommit();
            return;
        }

        // Nuevo registro, en este caso se permite el commit
        if (isValido == true && jQuery('#operacionId').val() == 0) {
            operacionJS.doCommit();
            return;
        }

        // Registro existente, se le cambio el nombre, en este caso se permite el commit
        if (isValido == true && jQuery('#operacionId').val() != 0) {
            operacionJS.doCommit();
            return;
        }

        // Mostrar mensaje de registro duplicado 
        utilDialog.showDialog('#d-registroDuplicadoOperacion');
    },

    /*
     * Funci&oacute;n que cambia el estatus de un registro en el grid al presionar el bot&oacute;n Activar/Inactivar.
     * Manda llamar al método <b>setEstatus</b> de DWR, éste método.
     *
     * @param id (int) Es el id del registro al cual se le va a cambiar el estatusId
     * @param estatusId (int) Es el nuevo estatusId
     */
    setEstatus: function (id, estatusId) {
        utilEffect.showProgressBar();
        var listaObjetos = [id];
        OperacionDWR.setEstatus(estatusId, listaObjetos, operacionJS.setEstatusCallback);
    },
    /*
     * Funci&oacute;n que cambia el Estatus de los elementos seleccionados en el widget
     * de jqGrid.
     * 
     */
    setEstatusSeleccion: function () {
        utilEffect.showProgressBar();
        var estatusId = operacionJS.estatusId;
        var listaObjetos = utilGrid.gridGetSelectedRows('#operacionGrid');
        OperacionDWR.setEstatus(estatusId, listaObjetos, operacionJS.setEstatusCallback);
    },
    /*
     * Callback de la funci&oacute;n setEstatus(), esta funci&oacute;n es llamada por DWR al 
     * terminar de realizar la operaci&oacute;n.
     * 
     */
    setEstatusCallback: function () {
        operacionJS.reloadGrid();
        utilEffect.hideProgressBar();
    },
    /*
     * Funci&oacute;n que muestra un mensaje de confirmaci&oacute;n para Activar o Inactivar los registros
     * seleccionados del widget jQGrid.
     */
    prepareToSetEstatus: function (estatusId) {
        var seleccion = utilGrid.gridGetSelectedRows('#operacionGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedOperacion');
        } else {
            operacionJS.estatusId = estatusId;
            operacionJS.setEstatusSeleccion();
        }
    },
    /*
     * Funci&oacute;n que muestra un mensaje de confirmaci&oacute;n para eliminar los registros seleccionados
     * del widget jQGrid.
     */
    prepareToRemoveSelected: function () {
        var seleccion = utilGrid.gridGetSelectedRows('#operacionGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedOperacion');
        } else {
            utilDialog.showDialog('#d-removeSeleccionOperacion');
        }
    },
    /*
     * Funci&oacute;n que muestra un mensaje de confirmaci&oacute;n para eliminar un regristro
     * del widget jQGrid.
     */
    prepareToRemove: function (id) {
        operacionJS.setEliminarId(id);
        utilDialog.showDialog('#d-removeOperacion');
    },

    /*
     * Variable para guardar el ID de la fila que se va a eliminar, si 
     * el usuario confirma la decisi&oacute;n al presionar el bot&oacute;n Aceptar del cuadro de di&aacute;logo. 
     **/
    eliminarId: 0,
    /*
     * Setter de la variable eliminarId
     **/
    setEliminarId: function (eliminarId) {
        operacionJS.eliminarId = eliminarId;
    },
    /*
     * Getter de la variable eliminarId
     **/
    getEliminarId: function () {
        return operacionJS.eliminarId;
    },

    /*
     * Variable para guardar el estatus según el bot&oacute;n que se presion&oacute;: Activar o Inactivar si 
     * el usuario confirma la decisi&oacute;n al presionar el bot&oacute;n Aceptar del cuadro de di&aacute;logo. 
     **/
    estatusId: 0,
    /*
     * Funci&oacute;n que se ejecuta al presionar el bot&oacute;n : GUARDAR.
     */
    prepareToSave: function () {
        var validation = validanguage.validateForm('operacionform');
        if (validation.result) {
            operacionJS.isValidoNombre();
        }
    },
    /*
     * Funci&oacute;n que inicia el proceso para GUARDAR y ACTUALIZAR/MODIFICAR
     * Una vez que se pasaron las validaciones se realiza el commit.
     */
    doCommit: function () {
        if (jQuery('#operacionId').val() == 0) {
            operacionJS.save();
        } else {
            operacionJS.update();
        }
    },
    /*
     * Funci&oacute;n que es llamada para actualizar la informaci&oacute;n del grid.
     */
    reloadGrid: function () {
        if (operacionJS.cacheDWR != null) {
            operacionJS.findByCriteria(operacionJS.cacheDWR);
        } else {
            operacionJS.findByCriteria(new OperacionDWR());
        }
    },
    /*
     * Funci&oacute;n que es activa el modal de busqueda.
     */
    openSearch: function () {
        utilSearch.openSearch('#d-searchOperacion', '#operacionGrid', '#operacionform', operacionJS, new OperacionDWR());
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
        operacionJS.entity = entity;
        operacionJS.headers = headers;
        operacionJS.format = format;
        operacionJS.reportName = jQuery('#operacion.title').val() +
                '_' + utilMisc.getTodayDate('-');
        var criteriaExample = new OperacionDWR();
        if (operacionJS.cacheDWR != null) {
            criteriaExample = operacionJS.cacheDWR;
        }
        // utilEffect.showProgressBar();
        jQuery().toastmessage('showToast', {
            text: 'Filtrando informaci&oacute;n...',
            sticky: false,
            position: 'bottom-center',
            type: 'notice',
            closeText: '',
            close: function () {
            }
        });
        OperacionDWR.getReportDataTest(sortBy, sortType, criteriaExample, operacionJS.exportarDatosCallback);
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
                operacionJS.entity + '&headers=' + operacionJS.headers +
                '&reportName=' + operacionJS.reportName + '&format=' + operacionJS.format +
                '&reportKey=' + reportKey;
    },
    
    getModulos: function(moduloId){
        utilEffect.showProgressBar();
        var aplicacionId = $("#aplicacionId").val();
        OperacionDWR.getModulos(aplicacionId, function(data){
        	var options = $("#moduloId");
        	options.empty();
        	options.append($("<option />").val("-1").text("Seleccione"));
        	$.each(data, function() {
        	    options.append($("<option />").val(this.moduloId).text(this.nombre));
        	});
        	if(moduloId!=null){
        		$("#moduloId").val(moduloId);
        	}
            utilEffect.hideProgressBar();
        });
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
// Validaciones de operacionform
validanguage.el.aplicacionId = {
	    characters: {
	        mode: 'allow', expression: 'alphanumericspecial', suppress: false},
	    validations: [ProfileRequiredComboField]};
validanguage.el.moduloId = {
	    characters: {
	        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
	    validations: [ProfileRequiredComboField]};
validanguage.el.operacionform_orden = {
	    characters: {
	        mode: 'allow', expression: 'numeric', suppress: false},
	    validations: [
	        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
validanguage.el.operacionform_nombreES = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
validanguage.el.operacionform_nombreEN = {
	    characters: {
	        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
	    validations: [
	        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};

