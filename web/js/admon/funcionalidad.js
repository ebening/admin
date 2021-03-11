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
    utilInput.fixRadios('#funcionalidadform');


    // Bot&oacute;n : NUEVO
    jQuery('#btnNewFuncionalidad').click(function () {
        utilForm.reset('#funcionalidadform');
        jQuery('#funcionalidadId').val(0);
        funcionalidadJS.funcionalidad = null;
    }).customButtonEffect('#btnNewFuncionalidad');
    // Bot&oacute;n : GUARDAR
    jQuery('#btnSaveFuncionalidad').click(function () {
    	var validation = validanguage.validateForm('funcionalidadform');
        if (validation.result) {
        	funcionalidadJS.doCommit();
        }
    }).customButtonEffectBlue('#btnSaveFuncionalidad');

    if (jQuery('#gridVisibleFuncionalidad').val() == 'true') {
        jQuery('#funcionalidadGrid').jqGrid({
            url: '',
            datatype: '',
            colNames: [
            	jQuery('#funcionalidad_funcionalidadId').val(),
            	jQuery('#funcionalidad_aplicacionId').val(),
                jQuery('#funcionalidad_aplicacion').val(),
                jQuery('#funcionalidad_moduloId').val(),
                jQuery('#funcionalidad_modulo').val(),
                jQuery('#funcionalidad_operacionId').val(),
                jQuery('#funcionalidad_operacion').val(),
                jQuery('#funcionalidad_nombreES').val(),
                jQuery('#funcionalidad_tipoId').val(),
                jQuery('#funcionalidad_tipo').val(),
                jQuery('#funcionalidad_fechaModificacion').val(),
                jQuery('#funcionalidad_usuarioModificacion').val(),
                jQuery('#funcionalidad_activo').val(),
                jQuery('#funcionalidad_modificar').val(),jQuery('#funcionalidad_eliminar').val()
            ],
            colModel: [{
                    name: 'funcionalidadId',
                    index: 'funcionalidadId',
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
                    name: 'operacionId',
                    index: 'operacionId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'operacion',
                    index: 'operacion',
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
                    name: 'tipoId',
                    index: 'tipoId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'tipo',
                    index: 'tipo',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                },  {
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
                    width     : 40,
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
            pager: jQuery('#funcionalidadGridPagerId'),
            caption: jQuery('#funcionalidad.title').val()
        });
        // jQuery UI Dialogs
        utilDialog.setStandardDialog('#d-removeFuncionalidad', 'Confirme', '&iquest;Desea eliminar el registro?', ['Aceptar@funcionalidadJS.remove()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-removeSeleccionFuncionalidad', 'Confirme', '&iquest;Desea eliminar los registros seleccionados?', ['Aceptar@funcionalidadJS.removeSelected()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-confirmSetEstatusFuncionalidad', 'Confirme', '&iquest;Desea cambiar el estatus de los registros seleccionados?', ['Aceptar@funcionalidadJS.setEstatusSeleccion()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-noSelectedFuncionalidad', 'Error', 'Para poder realizar esta operaci&oacute;n seleccione uno &oacute; m&aacute;s registros.', ['Aceptar@']);
        utilDialog.setStandardDialog('#d-registroDuplicadoFuncionalidad', 'Advertencia', 'Ya existe un registro con este nombre en la base de datos, &iquest;Desea crear un nuevo registro con este nombre?', ['Aceptar@funcionalidadJS.doCommit()', 'Cancelar@']);
        utilDialog.setCustomDialogSelectColumnsToExport("#funcionalidadColumnasExportar", '#funcionalidadGrid');
        utilDialog.setCustomDialogSetEstatus('#funcionalidadSetEstatusActivoInactivo',
                'funcionalidadJS.prepareToSetEstatus(1)', 'funcionalidadJS.prepareToSetEstatus(2)');

        // Inicializaciones extra para el grid
        utilGrid.setup('#funcionalidadGrid', '#funcionalidadGridPagerId', '#funcionalidadGridCurrentPage', '#funcionalidadGridOrderByColumn', '#funcionalidadGridOrderByType', 'funcionalidadJS');

        // Crear el modal de busqueda
        utilSearch.buildSearch('#d-searchFuncionalidad');

        // Recargar el widget jqGrid
        funcionalidadJS.reloadGrid();

    }
    // Limpiar la p&aacute;gina
    jQuery('#btnNewFuncionalidad').click();
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
    if (jQuery('#gridIndividualModeFuncionalidad').val() == 'true') {
        jQuery('#btnNewFuncionalidad').hide();
        funcionalidadJS.findFirst();
    }
    jQuery('#grid-action-activar-inactivar').hide().next().hide();
});

var funcionalidadJS = {
    /* Variable auxiliar para persistir un objeto (resultado de una consulta) 
     * obtenido mediante el callback de la funci&oacute;n "findById(...)". Este objeto
     * es necesario para la funcionalidad de selects encadenados pero puede ser
     * utilizado para otros prop&oacute;sitos. El objeto existe solamente mientras se
     * esta editando la informaci&oacute;n en el formulario. Al presionar el bot&oacute;n
     * Nuevo o Guardar cualquier referencia a éste regresar&aacute; "null". El 
     * objeto debe utilizarse como modo de "Solo Lectura" (la modificaci&oacute;n de 
     * este objeto no se ver&aacute; reflejada en la base de datos) */
    funcionalidad: null,
    /*
     * Variable auxiliar para mantener las busquedas del grid al momento de paginar, ordenar, etc.
     */
    cacheDWR: null,
    /*
     * Funci&oacute;n que guarda un nuevo registro en la BD.
     */
    save: function () {
        utilEffect.showProgressBar();
        var funcionalidad = utilObject.buildObject('#funcionalidadform', new FuncionalidadDWR());
        funcionalidad.funcionalidadId = 0;
        funcionalidad.aplicacionId = $('#aplicacionId').val();
        funcionalidad.moduloId = $('#moduloId').val();
        funcionalidad.operacionId = $('#operacionId').val();
        funcionalidad.tipoId = $('#catalogoId').val();
        var listaFuncionalidads = [funcionalidad];
        FuncionalidadDWR.save(listaFuncionalidads, funcionalidadJS.saveOrUpdateCallback);
    },
    /*
     * Funci&oacute;n que actualiza un registro existente en la BD.
     */
    update: function () {
        utilEffect.showProgressBar();
        var funcionalidad = utilObject.buildObject('#funcionalidadform', new FuncionalidadDWR());
        funcionalidad.funcionalidadId = jQuery('#funcionalidadId').val();
        funcionalidad.aplicacionId = $('#aplicacionId').val();
        funcionalidad.moduloId = $('#moduloId').val();
        funcionalidad.operacionId = $('#operacionId').val();
        funcionalidad.tipoId = $('#catalogoId').val();
        var listaFuncionalidades = [funcionalidad];
        FuncionalidadDWR.update(listaFuncionalidades, funcionalidadJS.saveOrUpdateCallback);
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
        utilForm.reset('#funcionalidadform');
        jQuery('#funcionalidadId').val(0);
        funcionalidadJS.funcionalidad = null;
        funcionalidadJS.reloadGrid();
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
        var id = funcionalidadJS.getEliminarId();
        var listaIds = [id];
        FuncionalidadDWR.remove(listaIds, funcionalidadJS.removeCallback);
    },
    /*
     * Funci&oacute;n que elimina los registros seleccionados en el grid al presionar el bot&oacute;n eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como par&aacute;metro.
     */
    removeSelected: function () {
        utilEffect.showProgressBar();
        var listaIds = utilGrid.gridGetSelectedRows('#funcionalidadGrid');
        FuncionalidadDWR.remove(listaIds, funcionalidadJS.removeCallback);
    },
    /*
     * Callback de la funci&oacute;n remove(), esta funci&oacute;n es llamada por DWR al 
     * terminar de realizar la operaci&oacute;n.
     */
    removeCallback: function () {
        jQuery('#btnNewFuncionalidad').click();
        funcionalidadJS.reloadGrid();
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
        FuncionalidadDWR.findById(id, funcionalidadJS.findByIdCallback);
    },
    /*
     * Callback de la funci&oacute;n findById(...), esta funci&oacute;n es llamada por DWR al 
     * terminar de realizar la operaci&oacute;n.
     * 
     * @param data (Object) Es un objeto con la informaci&oacute;n de la consulta.
     */
    findByIdCallback: function (data) {
        utilForm.reset('#funcionalidadform');
        utilForm.populate('#funcionalidadform', data);
        $('#aplicacionId').val(data.aplicacionId);
        $('#funcionalidadId').val(data.funcionalidadId);
        $('#operacionId').val(data.operacionId);
        utilEffect.hideProgressBar();
       // $('#moduloId').val(data.moduloId);
        funcionalidadJS.getModulos(data.moduloId);
        funcionalidadJS.getOperaciones(data.moduloId, data.operacionId);
        funcionalidadJS.funcionalidad = data;
        $('#catalogoId').val(data.tipoId);
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
        funcionalidadJS.cacheDWR = obj;
        var page = jQuery('#funcionalidadGridCurrentPage').val();
        var rows = jQuery('#funcionalidadGridRowsByPage').val();
        var order = jQuery('#funcionalidadGridOrderByColumn').val();
        var orderType = jQuery('#funcionalidadGridOrderByType').val();
        utilEffect.showProgressBar();
        FuncionalidadDWR.findByCriteria(page, rows, order, orderType, obj, function(data){
        	utilGrid.gridUpdate('#funcionalidadGrid', '#funcionalidadGridCurrentPage', '#funcionalidadGridRowsByPage', data);
            utilEffect.hideProgressBar();
        });
    },
    /**
     * Funcion utilizado cuando la pagina es individual
     */
    findFirst: function () {
        utilEffect.showProgressBar();
        FuncionalidadDWR.findFirst(funcionalidadJS.findByIdCallback);
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
        var nombre = jQuery('#funcionalidadform_nombre').val();
        FuncionalidadDWR.isValidoNombre(nombre, funcionalidadJS.isValidoNombreCallback);
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
        if (isValido == false && jQuery('#funcionalidadId').val() != 0) {
            funcionalidadJS.doCommit();
            return;
        }

        // Nuevo registro, en este caso se permite el commit
        if (isValido == true && jQuery('#funcionalidadId').val() == 0) {
            funcionalidadJS.doCommit();
            return;
        }

        // Registro existente, se le cambio el nombre, en este caso se permite el commit
        if (isValido == true && jQuery('#funcionalidadId').val() != 0) {
            funcionalidadJS.doCommit();
            return;
        }

        // Mostrar mensaje de registro duplicado 
        utilDialog.showDialog('#d-registroDuplicadoFuncionalidad');
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
        FuncionalidadDWR.setEstatus(estatusId, listaObjetos, funcionalidadJS.setEstatusCallback);
    },
    /*
     * Funci&oacute;n que cambia el Estatus de los elementos seleccionados en el widget
     * de jqGrid.
     * 
     */
    setEstatusSeleccion: function () {
        utilEffect.showProgressBar();
        var estatusId = funcionalidadJS.estatusId;
        var listaObjetos = utilGrid.gridGetSelectedRows('#funcionalidadGrid');
        FuncionalidadDWR.setEstatus(estatusId, listaObjetos, funcionalidadJS.setEstatusCallback);
    },
    /*
     * Callback de la funci&oacute;n setEstatus(), esta funci&oacute;n es llamada por DWR al 
     * terminar de realizar la operaci&oacute;n.
     * 
     */
    setEstatusCallback: function () {
        funcionalidadJS.reloadGrid();
        utilEffect.hideProgressBar();
    },
    /*
     * Funci&oacute;n que muestra un mensaje de confirmaci&oacute;n para Activar o Inactivar los registros
     * seleccionados del widget jQGrid.
     */
    prepareToSetEstatus: function (estatusId) {
        var seleccion = utilGrid.gridGetSelectedRows('#funcionalidadGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedFuncionalidad');
        } else {
            funcionalidadJS.estatusId = estatusId;
            funcionalidadJS.setEstatusSeleccion();
        }
    },
    /*
     * Funci&oacute;n que muestra un mensaje de confirmaci&oacute;n para eliminar los registros seleccionados
     * del widget jQGrid.
     */
    prepareToRemoveSelected: function () {
        var seleccion = utilGrid.gridGetSelectedRows('#funcionalidadGrid');
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedFuncionalidad');
        } else {
            utilDialog.showDialog('#d-removeSeleccionFuncionalidad');
        }
    },
    /*
     * Funci&oacute;n que muestra un mensaje de confirmaci&oacute;n para eliminar un regristro
     * del widget jQGrid.
     */
    prepareToRemove: function (id) {
        funcionalidadJS.setEliminarId(id);
        utilDialog.showDialog('#d-removeFuncionalidad');
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
        funcionalidadJS.eliminarId = eliminarId;
    },
    /*
     * Getter de la variable eliminarId
     **/
    getEliminarId: function () {
        return funcionalidadJS.eliminarId;
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
        var validation = validanguage.validateForm('funcionalidadform');
        if (validation.result) {
            funcionalidadJS.isValidoNombre();
        }
    },
    /*
     * Funci&oacute;n que inicia el proceso para GUARDAR y ACTUALIZAR/MODIFICAR
     * Una vez que se pasaron las validaciones se realiza el commit.
     */
    doCommit: function () {
        if (jQuery('#funcionalidadId').val() == 0) {
            funcionalidadJS.save();
        } else {
            funcionalidadJS.update();
        }
    },
    /*
     * Funci&oacute;n que es llamada para actualizar la informaci&oacute;n del grid.
     */
    reloadGrid: function () {
        if (funcionalidadJS.cacheDWR != null) {
            funcionalidadJS.findByCriteria(funcionalidadJS.cacheDWR);
        } else {
            funcionalidadJS.findByCriteria(new FuncionalidadDWR());
        }
    },
    /*
     * Funci&oacute;n que es activa el modal de busqueda.
     */
    openSearch: function () {
        utilSearch.openSearch('#d-searchFuncionalidad', '#funcionalidadGrid', '#funcionalidadform', funcionalidadJS, new FuncionalidadDWR());
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
        funcionalidadJS.entity = entity;
        funcionalidadJS.headers = headers;
        funcionalidadJS.format = format;
        funcionalidadJS.reportName = jQuery('#funcionalidad.title').val() +
                '_' + utilMisc.getTodayDate('-');
        var criteriaExample = new FuncionalidadDWR();
        if (funcionalidadJS.cacheDWR != null) {
            criteriaExample = funcionalidadJS.cacheDWR;
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
        FuncionalidadDWR.getReportDataTest(sortBy, sortType, criteriaExample, funcionalidadJS.exportarDatosCallback);
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
                funcionalidadJS.entity + '&headers=' + funcionalidadJS.headers +
                '&reportName=' + funcionalidadJS.reportName + '&format=' + funcionalidadJS.format +
                '&reportKey=' + reportKey;
    },
    getModulos: function(moduloId){
        utilEffect.showProgressBar();
        var aplicacionId = $("#aplicacionId").val();
        FuncionalidadDWR.getModulos(aplicacionId, function(data){
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
    },
    getOperaciones: function(moduloId, operacionId){
        utilEffect.showProgressBar();
        var moduloId2 = $("#moduloId").val();
        if(moduloId === undefined) moduloId = moduloId2;
        FuncionalidadDWR.getOperaciones(moduloId, function(data){
        	var options = $("#operacionId");
        	options.empty();
        	options.append($("<option />").val("-1").text("Seleccione"));
        	$.each(data, function() {
        	    options.append($("<option />").val(this.operacionId).text(this.nombre));
        	});
                
        	if(operacionId!=null){
        		$("#operacionId").val(operacionId);
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
// Validaciones de funcionalidadform
validanguage.el.aplicacionId = {
	    characters: {
	        mode: 'allow', expression: 'alphanumericspecial', suppress: false},
	    validations: [ProfileRequiredComboField]};
validanguage.el.moduloId = {
	    characters: {
	        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
	    validations: [ProfileRequiredComboField]};
validanguage.el.operacionId = {
	    characters: {
	        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
	    validations: [ProfileRequiredComboField]};
validanguage.el.funcionalidadform_nombreES = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
validanguage.el.funcionalidadform_nombreEN = {
	    characters: {
	        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
	    validations: [
	        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};

