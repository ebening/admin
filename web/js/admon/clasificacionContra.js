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
    utilInput.fixRadios('#clasificacionContraform');


    // <editor-fold defaultstate="collapsed" desc="Botones">
    // Botón : NUEVO
    jQuery('#btnNewClasificacionContra').click(function () {
        utilForm.reset('#clasificacionContraform');
        jQuery('#clasificacionContraform_clasificacionContraId').val(0);
        clasificacionContraJS.clasificacionContra = null;
    }).customButtonEffect('#btnNewClasificacionContra');
    // Botón : GUARDAR
    jQuery('#btnSaveClasificacionContra').click(function () {
        clasificacionContraJS.prepareToSave();
    }).customButtonEffect('#btnSaveClasificacionContra');
    // </editor-fold>

    if (jQuery('#gridVisibleClasificacionContra').val() == 'true') {
        jQuery('#clasificacionContraGrid').jqGrid({
            url: '',
            datatype: '',
            colNames: [
                jQuery('#clasificacionContra_clasificacionContraIdText').html(),
                jQuery('#clasificacionContra_nombreText').html(),
                jQuery('#clasificacionContra_ordenText').html(),
                jQuery('#clasificacionContra_estatusIdText').html(),
                jQuery('#clasificacionContra_eliminadoIdText').html(),
                jQuery('#clasificacionContra_creacionFechaText').html(),
                jQuery('#clasificacionContra_creacionUsuarioText').html(),
                jQuery('#clasificacionContra_modificacionFechaText').html(),
                jQuery('#clasificacionContra_modificacionUsuarioText').html(),
                '', '', ''],
            colModel: [{
                    name: 'clasificacionContraId',
                    index: 'clasificacionContraId',
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
                    name: 'orden',
                    index: 'orden',
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
                    search: true,
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
            pager: jQuery('#clasificacionContraGridPagerId'),
            caption: jQuery('#key_clasificacioncontra_title').val()
        });
        // jQuery UI Dialogs
        utilDialog.setStandardDialog('#d-removeClasificacionContra', 'Confirme', '¿Desea eliminar el registro?', ['Aceptar@clasificacionContraJS.remove()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-removeSeleccionClasificacionContra', 'Confirme', '¿Desea eliminar los registros seleccionados?', ['Aceptar@clasificacionContraJS.removeSelected()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-confirmSetEstatusClasificacionContra', 'Confirme', '¿Desea cambiar el estatus de los registros seleccionados?', ['Aceptar@clasificacionContraJS.setEstatusSeleccion()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-noSelectedClasificacionContra', 'Error', 'Para poder realizar esta operación seleccione uno ó más registros.', ['Aceptar@']);
        utilDialog.setStandardDialog('#d-registroDuplicadoClasificacionContra', 'Advertencia', 'Ya existe un registro con este nombre en la base de datos, ¿Desea crear un nuevo registro con este nombre?', ['Aceptar@clasificacionContraJS.doCommit()', 'Cancelar@']);
        utilDialog.setCustomDialogSelectColumnsToExport("#clasificacionContraColumnasExportar", '#clasificacionContraGrid');
        utilDialog.setCustomDialogSetEstatus('#clasificacionContraSetEstatusActivoInactivo',
                'clasificacionContraJS.prepareToSetEstatus(1)', 'clasificacionContraJS.prepareToSetEstatus(2)');

        // Inicializaciones extra para el grid
        utilGrid.setup('#clasificacionContraGrid', '#clasificacionContraGridPagerId', '#clasificacionContraGridCurrentPage', '#clasificacionContraGridOrderByColumn',
                '#clasificacionContraGridOrderByType', 'clasificacionContraJS');

        // Crear el modal de busqueda
        utilSearch.buildSearch('#d-searchClasificacionContra');

        // Recargar el widget jqGrid
        clasificacionContraJS.reloadGrid();

    }
    // Limpiar la página
    jQuery('#btnNewClasificacionContra').click();
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
    if (jQuery('#gridIndividualModeClasificacionContra').val() == 'true') {
        jQuery('#btnNewClasificacionContra').hide();
        clasificacionContraJS.findFirst();
    }
    jQuery('#grid-action-activar-inactivar').hide().next().hide();
});

var clasificacionContraJS = {
    /* Variable auxiliar para persistir un objeto (resultado de una consulta) 
     * obtenido mediante el callback de la función "findById(...)". Este objeto
     * es necesario para la funcionalidad de selects encadenados pero puede ser
     * utilizado para otros propósitos. El objeto existe solamente mientras se
     * esta editando la información en el formulario. Al presionar el botón
     * Nuevo o Guardar cualquier referencia a éste regresará "null". El 
     * objeto debe utilizarse como modo de "Solo Lectura" (la modificación de 
     * este objeto no se verá reflejada en la base de datos) */
    clasificacionContra: null,
    /*
     * Variable auxiliar para mantener las busquedas del grid al momento de paginar, ordenar, etc.
     */
    cacheDWR: null,
    /*
     * Función que guarda un nuevo registro en la BD.
     */
    save: function () {
        utilEffect.showProgressBar();
        var clasificacionContra = utilObject.buildObject('#clasificacionContraform', new ClasificacionContraDWR());
        clasificacionContra.clasificacionContraId = 0;
        var listaClasificacionContras = [clasificacionContra];
        ClasificacionContraDWR.save(listaClasificacionContras, clasificacionContraJS.saveOrUpdateCallback);
    },
    /*
     * Función que actualiza un registro existente en la BD.
     */
    update: function () {
        utilEffect.showProgressBar();
        var clasificacionContra = utilObject.buildObject('#clasificacionContraform', new ClasificacionContraDWR());
        clasificacionContra.clasificacionContraId = jQuery('#clasificacionContraform_clasificacionContraId').val();
        var listaClasificacionContras = [clasificacionContra];
        ClasificacionContraDWR.update(listaClasificacionContras, clasificacionContraJS.saveOrUpdateCallback);
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
        if (jQuery('#gridIndividualModeClasificacionContra').val() == 'false') {
            utilForm.reset('#clasificacionContraform');
            jQuery('#clasificacionContraform_clasificacionContraId').val(0);
            clasificacionContraJS.clasificacionContra = null;
            clasificacionContraJS.reloadGrid();
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
        var id = clasificacionContraJS.getEliminarId();
        var listaIds = [id];
        ClasificacionContraDWR.remove(listaIds, clasificacionContraJS.removeCallback);
    },
    /*
     * Función que elimina los registros seleccionados en el grid al presionar el botón eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como parámetro.
     */
    removeSelected: function () {
        utilEffect.showProgressBar();
        var listaIds = utilGrid.gridGetSelectedRows('#clasificacionContraGrid');
        ClasificacionContraDWR.remove(listaIds, clasificacionContraJS.removeCallback);
    },
    /*
     * Callback de la función remove(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    removeCallback: function () {
        jQuery('#btnNewClasificacionContra').click();
        clasificacionContraJS.reloadGrid();
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
        ClasificacionContraDWR.findById(id, clasificacionContraJS.findByIdCallback);
    },
    /*
     * Callback de la función findById(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto con la información de la consulta.
     */
    findByIdCallback: function (data) {
        utilForm.reset('#clasificacionContraform');
        utilForm.populate('#clasificacionContraform', data);
        clasificacionContraJS.clasificacionContra = data;
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
        clasificacionContraJS.cacheDWR = obj;
        var page = jQuery('#clasificacionContraGridCurrentPage').val();
        var rows = jQuery('#clasificacionContraGridRowsByPage').val();
        var order = jQuery('#clasificacionContraGridOrderByColumn').val();
        var orderType = jQuery('#clasificacionContraGridOrderByType').val();
        utilEffect.showProgressBar();
        ClasificacionContraDWR.findByCriteria(page, rows, order, orderType, obj, clasificacionContraJS.findByCriteriaCallback);
    },
    /*
     * Callback de la función findByCriteria(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto (Grid) con la información de la 
     * consulta.
     */
    findByCriteriaCallback: function (data) {
        utilGrid.gridUpdate('#clasificacionContraGrid', '#clasificacionContraGridCurrentPage', '#clasificacionContraGridRowsByPage', data);
        utilEffect.hideProgressBar();
    },
    /**
     * Funcion utilizado cuando la pagina es individual
     */
    findFirst: function () {
        utilEffect.showProgressBar();
        ClasificacionContraDWR.findFirst(clasificacionContraJS.findByIdCallback);
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
        var nombre = jQuery('#clasificacionContraform_nombre').val();
        ClasificacionContraDWR.isValidoNombre(nombre, clasificacionContraJS.isValidoNombreCallback);
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
        if (isValido == false && jQuery('#clasificacionContraform_clasificacionContraId').val() != 0) {
            clasificacionContraJS.doCommit();
            return;
        }

        // Nuevo registro, en este caso se permite el commit
        if (isValido == true && jQuery('#clasificacionContraform_clasificacionContraId').val() == 0) {
            clasificacionContraJS.doCommit();
            return;
        }

        // Registro existente, se le cambio el nombre, en este caso se permite el commit
        if (isValido == true && jQuery('#clasificacionContraform_clasificacionContraId').val() != 0) {
            clasificacionContraJS.doCommit();
            return;
        }

        // Mostrar mensaje de registro duplicado 
        utilDialog.showDialog('#d-registroDuplicadoClasificacionContra');
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
        ClasificacionContraDWR.setEstatus(estatusId, listaObjetos, clasificacionContraJS.setEstatusCallback);
    },
    /*
     * Función que cambia el Estatus de los elementos seleccionados en el widget
     * de jqGrid.
     * 
     */
    setEstatusSeleccion: function () {
        utilEffect.showProgressBar();
        var estatusId = clasificacionContraJS.estatusId;
        var listaObjetos = utilGrid.gridGetSelectedRows('#clasificacionContraGrid');
        ClasificacionContraDWR.setEstatus(estatusId, listaObjetos, clasificacionContraJS.setEstatusCallback);
    },
    /*
     * Callback de la función setEstatus(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     */
    setEstatusCallback: function () {
        clasificacionContraJS.reloadGrid();
        utilEffect.hideProgressBar();
    },
    /*
     * Función que muestra un mensaje de confirmación para Activar o Inactivar los registros
     * seleccionados del widget jQGrid.
     */
    prepareToSetEstatus: function (estatusId) {
        var seleccion = utilGrid.gridGetSelectedRows('#clasificacionContraGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedClasificacionContra');
        } else {
            clasificacionContraJS.estatusId = estatusId;
            // utilDialog.showDialog( '#d-confirmSetEstatusClasificacionContra' );
            clasificacionContraJS.setEstatusSeleccion();
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar los registros seleccionados
     * del widget jQGrid.
     */
    prepareToRemoveSelected: function () {
        var seleccion = utilGrid.gridGetSelectedRows('#clasificacionContraGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedClasificacionContra');
        } else {
            utilDialog.showDialog('#d-removeSeleccionClasificacionContra');
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar un regristro
     * del widget jQGrid.
     */
    prepareToRemove: function (id) {
        clasificacionContraJS.setEliminarId(id);
        utilDialog.showDialog('#d-removeClasificacionContra');
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
        clasificacionContraJS.eliminarId = eliminarId;
    },
    /*
     * Getter de la variable eliminarId
     **/
    getEliminarId: function () {
        return clasificacionContraJS.eliminarId;
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
        var validation = validanguage.validateForm('clasificacionContraform');
        if (validation.result) {
            clasificacionContraJS.isValidoNombre();
        }
    },
    /*
     * Función que inicia el proceso para GUARDAR y ACTUALIZAR/MODIFICAR
     * Una vez que se pasaron las validaciones se realiza el commit.
     */
    doCommit: function () {
        if (jQuery('#clasificacionContraform_clasificacionContraId').val() == 0) {
            clasificacionContraJS.save();
        } else {
            clasificacionContraJS.update();
        }
    },
    /*
     * Función que es llamada para actualizar la información del grid.
     */
    reloadGrid: function () {
        if (clasificacionContraJS.cacheDWR != null) {
            clasificacionContraJS.findByCriteria(clasificacionContraJS.cacheDWR);
        } else {
            clasificacionContraJS.findByCriteria(new ClasificacionContraDWR());
        }
    },
    /*
     * Función que es activa el modal de busqueda.
     */
    openSearch: function () {
        utilSearch.openSearch('#d-searchClasificacionContra', '#clasificacionContraGrid', '#clasificacionContraform', clasificacionContraJS, new ClasificacionContraDWR());
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
        clasificacionContraJS.entity = entity;
        clasificacionContraJS.headers = headers;
        clasificacionContraJS.format = format;
        clasificacionContraJS.reportName = jQuery('#key_clasificacioncontra_title').val() +
                '_' + utilMisc.getTodayDate('-');
        var criteriaExample = new ClasificacionContraDWR();
        if (clasificacionContraJS.cacheDWR != null) {
            criteriaExample = clasificacionContraJS.cacheDWR;
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
        ClasificacionContraDWR.getReportDataTest(sortBy, sortType, criteriaExample, clasificacionContraJS.exportarDatosCallback);
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
                clasificacionContraJS.entity + '&headers=' + clasificacionContraJS.headers +
                '&reportName=' + clasificacionContraJS.reportName + '&format=' + clasificacionContraJS.format +
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
// Validaciones de clasificacionContraform
validanguage.el.clasificacionContraform_nombre = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
validanguage.el.clasificacionContraform_orden = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};

