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
    utilInput.fixRadios('#paisform');


    // <editor-fold defaultstate="collapsed" desc="Botones">
    // Botón : NUEVO
    jQuery('#btnNewPais').click(function () {
        utilForm.reset('#paisform');
        jQuery('#paisform_paisId').val(0);
        paisJS.pais = null;
    }).customButtonEffect('#btnNewPais');
    // Botón : GUARDAR
    jQuery('#btnSavePais').click(function () {
        paisJS.prepareToSave();
    }).customButtonEffectBlue('#btnSavePais');
    // </editor-fold>

    if (jQuery('#gridVisiblePais').val() == 'true') {
        jQuery('#paisGrid').jqGrid({
            url: '',
            datatype: '',
            colNames: [
                jQuery('#pais_paisIdText').html(),
                jQuery('#pais_codeText').html(),
                jQuery('#pais_nombreText').html(),
                jQuery('#pais_estatusIdText').html(),
                jQuery('#pais_eliminadoIdText').html(),
                jQuery('#pais_creacionFechaText').html(),
                jQuery('#pais_creacionUsuarioText').html(),
                jQuery('#pais_modificacionFechaText').html(),
                jQuery('#pais_modificacionUsuarioText').html(),
                '', '', ''],
            colModel: [{
                    name: 'paisId',
                    index: 'paisId',
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
            pager: jQuery('#paisGridPagerId'),
            caption: jQuery('#key_pais_title').val()
        });
        // jQuery UI Dialogs
        utilDialog.setStandardDialog('#d-removePais', 'Confirme', '¿Desea eliminar el registro?', ['Aceptar@paisJS.remove()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-removeSeleccionPais', 'Confirme', '¿Desea eliminar los registros seleccionados?', ['Aceptar@paisJS.removeSelected()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-confirmSetEstatusPais', 'Confirme', '¿Desea cambiar el estatus de los registros seleccionados?', ['Aceptar@paisJS.setEstatusSeleccion()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-noSelectedPais', 'Error', 'Para poder realizar esta operación seleccione uno ó más registros.', ['Aceptar@']);
        utilDialog.setStandardDialog('#d-registroDuplicadoPais', 'Advertencia', 'Ya existe un registro con este nombre en la base de datos, ¿Desea crear un nuevo registro con este nombre?', ['Aceptar@paisJS.doCommit()', 'Cancelar@']);
        utilDialog.setCustomDialogSelectColumnsToExport("#paisColumnasExportar", '#paisGrid');
        utilDialog.setCustomDialogSetEstatus('#paisSetEstatusActivoInactivo',
                'paisJS.prepareToSetEstatus(1)', 'paisJS.prepareToSetEstatus(2)');

        // Inicializaciones extra para el grid
        utilGrid.setup('#paisGrid', '#paisGridPagerId', '#paisGridCurrentPage', '#paisGridOrderByColumn',
                '#paisGridOrderByType', 'paisJS');

        // Crear el modal de busqueda
        utilSearch.buildSearch('#d-searchPais');

        // Recargar el widget jqGrid
        paisJS.reloadGrid();

    }
    // Limpiar la página
    jQuery('#btnNewPais').click();
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
    if (jQuery('#gridIndividualModePais').val() == 'true') {
        jQuery('#btnNewPais').hide();
        paisJS.findFirst();
    }
    jQuery('#grid-action-activar-inactivar').hide().next().hide();
});

var paisJS = {
    /* Variable auxiliar para persistir un objeto (resultado de una consulta) 
     * obtenido mediante el callback de la función "findById(...)". Este objeto
     * es necesario para la funcionalidad de selects encadenados pero puede ser
     * utilizado para otros propósitos. El objeto existe solamente mientras se
     * esta editando la información en el formulario. Al presionar el botón
     * Nuevo o Guardar cualquier referencia a éste regresará "null". El 
     * objeto debe utilizarse como modo de "Solo Lectura" (la modificación de 
     * este objeto no se verá reflejada en la base de datos) */
    pais: null,
    /*
     * Variable auxiliar para mantener las busquedas del grid al momento de paginar, ordenar, etc.
     */
    cacheDWR: null,
    /*
     * Función que guarda un nuevo registro en la BD.
     */
    save: function () {
        utilEffect.showProgressBar();
        var pais = utilObject.buildObject('#paisform', new PaisDWR());
        pais.paisId = 0;
        var listaPaiss = [pais];
        PaisDWR.save(listaPaiss, paisJS.saveOrUpdateCallback);
    },
    /*
     * Función que actualiza un registro existente en la BD.
     */
    update: function () {
        utilEffect.showProgressBar();
        var pais = utilObject.buildObject('#paisform', new PaisDWR());
        pais.paisId = jQuery('#paisform_paisId').val();
        var listaPaiss = [pais];
        PaisDWR.update(listaPaiss, paisJS.saveOrUpdateCallback);
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
        if (jQuery('#gridIndividualModePais').val() == 'false') {
            utilForm.reset('#paisform');
            jQuery('#paisform_paisId').val(0);
            paisJS.pais = null;
            paisJS.reloadGrid();
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
        var id = paisJS.getEliminarId();
        var listaIds = [id];
        PaisDWR.remove(listaIds, paisJS.removeCallback);
    },
    /*
     * Función que elimina los registros seleccionados en el grid al presionar el botón eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como parámetro.
     */
    removeSelected: function () {
        utilEffect.showProgressBar();
        var listaIds = utilGrid.gridGetSelectedRows('#paisGrid');
        PaisDWR.remove(listaIds, paisJS.removeCallback);
    },
    /*
     * Callback de la función remove(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    removeCallback: function () {
        jQuery('#btnNewPais').click();
        paisJS.reloadGrid();
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
        PaisDWR.findById(id, paisJS.findByIdCallback);
    },
    /*
     * Callback de la función findById(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto con la información de la consulta.
     */
    findByIdCallback: function (data) {
        utilForm.reset('#paisform');
        utilForm.populate('#paisform', data);
        paisJS.pais = data;
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
        paisJS.cacheDWR = obj;
        var page = jQuery('#paisGridCurrentPage').val();
        var rows = jQuery('#paisGridRowsByPage').val();
        var order = jQuery('#paisGridOrderByColumn').val();
        var orderType = jQuery('#paisGridOrderByType').val();
        utilEffect.showProgressBar();
        PaisDWR.findByCriteria(page, rows, order, orderType, obj, paisJS.findByCriteriaCallback);
    },
    /*
     * Callback de la función findByCriteria(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto (Grid) con la información de la 
     * consulta.
     */
    findByCriteriaCallback: function (data) {
        utilGrid.gridUpdate('#paisGrid', '#paisGridCurrentPage', '#paisGridRowsByPage', data);
        utilEffect.hideProgressBar();
    },
    /**
     * Funcion utilizado cuando la pagina es individual
     */
    findFirst: function () {
        utilEffect.showProgressBar();
        PaisDWR.findFirst(paisJS.findByIdCallback);
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
        var nombre = jQuery('#paisform_nombre').val();
        PaisDWR.isValidoNombre(nombre, paisJS.isValidoNombreCallback);
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
        if (isValido == false && jQuery('#paisform_paisId').val() != 0) {
            paisJS.doCommit();
            return;
        }

        // Nuevo registro, en este caso se permite el commit
        if (isValido == true && jQuery('#paisform_paisId').val() == 0) {
            paisJS.doCommit();
            return;
        }

        // Registro existente, se le cambio el nombre, en este caso se permite el commit
        if (isValido == true && jQuery('#paisform_paisId').val() != 0) {
            paisJS.doCommit();
            return;
        }

        // Mostrar mensaje de registro duplicado 
        utilDialog.showDialog('#d-registroDuplicadoPais');
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
        PaisDWR.setEstatus(estatusId, listaObjetos, paisJS.setEstatusCallback);
    },
    /*
     * Función que cambia el Estatus de los elementos seleccionados en el widget
     * de jqGrid.
     * 
     */
    setEstatusSeleccion: function () {
        utilEffect.showProgressBar();
        var estatusId = paisJS.estatusId;
        var listaObjetos = utilGrid.gridGetSelectedRows('#paisGrid');
        PaisDWR.setEstatus(estatusId, listaObjetos, paisJS.setEstatusCallback);
    },
    /*
     * Callback de la función setEstatus(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     */
    setEstatusCallback: function () {
        paisJS.reloadGrid();
        utilEffect.hideProgressBar();
    },
    /*
     * Función que muestra un mensaje de confirmación para Activar o Inactivar los registros
     * seleccionados del widget jQGrid.
     */
    prepareToSetEstatus: function (estatusId) {
        var seleccion = utilGrid.gridGetSelectedRows('#paisGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedPais');
        } else {
            paisJS.estatusId = estatusId;
            // utilDialog.showDialog( '#d-confirmSetEstatusPais' );
            paisJS.setEstatusSeleccion();
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar los registros seleccionados
     * del widget jQGrid.
     */
    prepareToRemoveSelected: function () {
        var seleccion = utilGrid.gridGetSelectedRows('#paisGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedPais');
        } else {
            utilDialog.showDialog('#d-removeSeleccionPais');
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar un regristro
     * del widget jQGrid.
     */
    prepareToRemove: function (id) {
        paisJS.setEliminarId(id);
        utilDialog.showDialog('#d-removePais');
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
        paisJS.eliminarId = eliminarId;
    },
    /*
     * Getter de la variable eliminarId
     **/
    getEliminarId: function () {
        return paisJS.eliminarId;
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
        var validation = validanguage.validateForm('paisform');
        if (validation.result) {
            paisJS.isValidoNombre();
        }
    },
    /*
     * Función que inicia el proceso para GUARDAR y ACTUALIZAR/MODIFICAR
     * Una vez que se pasaron las validaciones se realiza el commit.
     */
    doCommit: function () {
        if (jQuery('#paisform_paisId').val() == 0) {
            paisJS.save();
        } else {
            paisJS.update();
        }
    },
    /*
     * Función que es llamada para actualizar la información del grid.
     */
    reloadGrid: function () {
        if (paisJS.cacheDWR != null) {
            paisJS.findByCriteria(paisJS.cacheDWR);
        } else {
            paisJS.findByCriteria(new PaisDWR());
        }
    },
    /*
     * Función que es activa el modal de busqueda.
     */
    openSearch: function () {
        utilSearch.openSearch('#d-searchPais', '#paisGrid', '#paisform', paisJS, new PaisDWR());
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
        paisJS.entity = entity;
        paisJS.headers = headers;
        paisJS.format = format;
        paisJS.reportName = jQuery('#key_pais_title').val() +
                '_' + utilMisc.getTodayDate('-');
        var criteriaExample = new PaisDWR();
        if (paisJS.cacheDWR != null) {
            criteriaExample = paisJS.cacheDWR;
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
        PaisDWR.getReportDataTest(sortBy, sortType, criteriaExample, paisJS.exportarDatosCallback);
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
                paisJS.entity + '&headers=' + paisJS.headers +
                '&reportName=' + paisJS.reportName + '&format=' + paisJS.format +
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
// Validaciones de paisform
validanguage.el.paisform_code = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};
validanguage.el.paisform_nombre = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};

