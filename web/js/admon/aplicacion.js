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
    utilInput.fixRadios('#aplicacionform');


    // <editor-fold defaultstate="collapsed" desc="Botones">
    // Botón : NUEVO
    jQuery('#btnNewAplicacion').click(function () {
        utilForm.reset('#aplicacionform');
        jQuery('#aplicacionform_aplicacionId').val(0);
        aplicacionJS.aplicacion = null;
    }).customButtonEffect('#btnNewAplicacion');
    // Botón : GUARDAR
    jQuery('#btnSaveAplicacion').click(function () {
        aplicacionJS.prepareToSave();
    }).customButtonEffectBlue('#btnSaveAplicacion');
    // </editor-fold>

    if (jQuery('#gridVisibleAplicacion').val() == 'true') {
        jQuery('#aplicacionGrid').jqGrid({
            url: '',
            datatype: '',
            colNames: [
                jQuery('#aplicacion_aplicacionIdText').html(),
                jQuery('#aplicacion_nombreText').html(),
                jQuery('#aplicacion_nombreInglesText').html(),
                jQuery('#aplicacion_descripcionText').html(),
                jQuery('#aplicacion_estatusIdText').html(),
                jQuery('#aplicacion_eliminadoIdText').html(),
                jQuery('#aplicacion_creacionFechaText').html(),
                jQuery('#aplicacion_creacionUsuarioText').html(),
                jQuery('#aplicacion_modificacionFechaText').html(),
                jQuery('#aplicacion_modificacionUsuarioText').html(),
                '', '', ''],
            colModel: [{
                    name: 'aplicacionId',
                    index: 'aplicacionId',
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
                    name: 'nombreIngles',
                    index: 'nombreIngles',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
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
                    width: 50,
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
            pager: jQuery('#aplicacionGridPagerId'),
            caption: jQuery('#key_aplicacion_title').val()
        });
        // jQuery UI Dialogs
        utilDialog.setStandardDialog('#d-removeAplicacion', jQuery('#msgAlertaText').val(), jQuery('#msgDeseaEliminarText').val(), ['Aceptar@aplicacionJS.remove()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-removeSeleccionAplicacion', jQuery('#msgAlertaText').val(), jQuery('#msgDeseaEliminarSeleccionText').val(), ['Aceptar@aplicacionJS.removeSelected()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-confirmSetEstatusAplicacion', jQuery('#msgAlertaText').val(), jQuery('#msgCambiarEstatusText').val(), ['Aceptar@aplicacionJS.setEstatusSeleccion()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-noSelectedAplicacion', jQuery('#msgAlertaText').val(), jQuery('#msgSeleccioneUnoOMas').val(), ['Aceptar@']);
        utilDialog.setStandardDialog('#d-registroDuplicadoAplicacion', jQuery('#msgAlertaText').val(), jQuery('#msgYaExisteRegistro').val(), ['Aceptar@aplicacionJS.doCommit()', 'Cancelar@']);
        utilDialog.setCustomDialogSelectColumnsToExport("#aplicacionColumnasExportar", '#aplicacionGrid');
        utilDialog.setCustomDialogSetEstatus('#aplicacionSetEstatusActivoInactivo',
                'aplicacionJS.prepareToSetEstatus(1)', 'aplicacionJS.prepareToSetEstatus(2)');

        // Inicializaciones extra para el grid
        utilGrid.setup('#aplicacionGrid', '#aplicacionGridPagerId', '#aplicacionGridCurrentPage', '#aplicacionGridOrderByColumn',
                '#aplicacionGridOrderByType', 'aplicacionJS');

        // Crear el modal de busqueda
        utilSearch.buildSearch('#d-searchAplicacion');

        // Recargar el widget jqGrid
        aplicacionJS.reloadGrid();

    }
    // Limpiar la página
    jQuery('#btnNewAplicacion').click();
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
    if (jQuery('#gridIndividualModeAplicacion').val() == 'true') {
        jQuery('#btnNewAplicacion').hide();
        aplicacionJS.findFirst();
    }
    aplicacionJS.getAplicaciones();
});

var aplicacionJS = {
    /* Variable auxiliar para persistir un objeto (resultado de una consulta) 
     * obtenido mediante el callback de la función "findById(...)". Este objeto
     * es necesario para la funcionalidad de selects encadenados pero puede ser
     * utilizado para otros propósitos. El objeto existe solamente mientras se
     * esta editando la información en el formulario. Al presionar el botón
     * Nuevo o Guardar cualquier referencia a éste regresará "null". El 
     * objeto debe utilizarse como modo de "Solo Lectura" (la modificación de 
     * este objeto no se verá reflejada en la base de datos) */
    aplicacion: null,
    /*
     * Variable auxiliar para mantener las busquedas del grid al momento de aplicacionr, ordenar, etc.
     */
    cacheDWR: null,
    /*
     * Función que guarda un nuevo registro en la BD.
     */
    save: function () {
        utilEffect.showProgressBar();
        var aplicacion = utilObject.buildObject('#aplicacionform', new AplicacionDWR());
        aplicacion.aplicacionId = 0;
        var listaAplicacions = [aplicacion];
        AplicacionDWR.save(listaAplicacions, aplicacionJS.saveOrUpdateCallback);
    },
    /*
     * Función que actualiza un registro existente en la BD.
     */
    update: function () {
        utilEffect.showProgressBar();
        var aplicacion = utilObject.buildObject('#aplicacionform', new AplicacionDWR());
        aplicacion.aplicacionId = jQuery('#aplicacionform_aplicacionId').val();
        var listaAplicacions = [aplicacion];
        AplicacionDWR.update(listaAplicacions, aplicacionJS.saveOrUpdateCallback);
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
        utilForm.reset('#aplicacionform');
        jQuery('#aplicacionform_aplicacionId').val(0);
        aplicacionJS.aplicacion = null;
        aplicacionJS.reloadGrid();
        
        aplicacionJS.getAplicaciones();
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
        var id = aplicacionJS.getEliminarId();
        var listaIds = [id];
        AplicacionDWR.remove(listaIds, aplicacionJS.removeCallback);
    },
    /*
     * Función que elimina los registros seleccionados en el grid al presionar el botón eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como parámetro.
     */
    removeSelected: function () {
        utilEffect.showProgressBar();
        var listaIds = utilGrid.gridGetSelectedRows('#aplicacionGrid');
        AplicacionDWR.remove(listaIds, aplicacionJS.removeCallback);
    },
    /*
     * Callback de la función remove(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    removeCallback: function () {
        aplicacionJS.getAplicaciones();
        jQuery('#btnNewAplicacion').click();
        aplicacionJS.reloadGrid();
    },
    /*
     * Función que actualiza las opciones del select anidar despues de haber hecho 
     * una modificacion a la base de datos
     * 
     */
    getAplicaciones: function () {
        utilEffect.showProgressBar();
        AplicacionDWR.getAplicaciones(aplicacionJS.getAplicacionesCallback);
    },
    /*
     * Callback de la función getAplicaciones(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    getAplicacionesCallback: function (pageList) {
        jQuery('#aplicacionform_anidar').find('option').remove();
        for (var i = 0; i < pageList.length; i++) {
            var aplicacion = pageList[i];
            var id = aplicacion.aplicacionId;
            var nombre = aplicacion.nombre;
            var style = '';
            if (aplicacion.url == null || aplicacion.url == "") {
                style = 'style="font-weight: bold; color: #33B5E5"';
            }
            jQuery('#aplicacionform_anidar').append('<option ' + style +
                    ' value="' + id + '">' + nombre + '</option>');
        }
        utilEffect.hideProgressBar();
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
        AplicacionDWR.findById(id, aplicacionJS.findByIdCallback);
    },
    /*
     * Callback de la función findById(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto con la información de la consulta.
     */
    findByIdCallback: function (data) {
        utilForm.reset('#aplicacionform');
        utilForm.populate('#aplicacionform', data);
        aplicacionJS.aplicacion = data;
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
        aplicacionJS.cacheDWR = obj;
        var page = jQuery('#aplicacionGridCurrentPage').val();
        var rows = jQuery('#aplicacionGridRowsByPage').val();
        var order = jQuery('#aplicacionGridOrderByColumn').val();
        var orderType = jQuery('#aplicacionGridOrderByType').val();
        utilEffect.showProgressBar();
        AplicacionDWR.findByCriteria(page, rows, order, orderType, obj, aplicacionJS.findByCriteriaCallback);
    },
    /*
     * Callback de la función findByCriteria(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto (Grid) con la información de la 
     * consulta.
     */
    findByCriteriaCallback: function (data) {
        utilGrid.gridUpdate('#aplicacionGrid', '#aplicacionGridCurrentPage', '#aplicacionGridRowsByPage', data);
        utilEffect.hideProgressBar();
    },
    /**
     * Funcion utilizado cuando la aplicacion es individual
     */
    findFirst: function () {
        utilEffect.showProgressBar();
        AplicacionDWR.findFirst(aplicacionJS.findByIdCallback);
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
        var nombre = jQuery('#aplicacionform_nombre').val();
        AplicacionDWR.isValidoNombre(nombre, aplicacionJS.isValidoNombreCallback);
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
        if (isValido == false && jQuery('#aplicacionform_aplicacionId').val() != 0) {
            aplicacionJS.doCommit();
            return;
        }

        // Nuevo registro, en este caso se permite el commit
        if (isValido == true && jQuery('#aplicacionform_aplicacionId').val() == 0) {
            aplicacionJS.doCommit();
            return;
        }

        // Registro existente, se le cambio el nombre, en este caso se permite el commit
        if (isValido == true && jQuery('#aplicacionform_aplicacionId').val() != 0) {
            aplicacionJS.doCommit();
            return;
        }

        // Mostrar mensaje de registro duplicado 
        utilDialog.showDialog('#d-registroDuplicadoAplicacion');
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
        AplicacionDWR.setEstatus(estatusId, listaObjetos, aplicacionJS.setEstatusCallback);
    },
    /*
     * Función que cambia el Estatus de los elementos seleccionados en el widget
     * de jqGrid.
     * 
     */
    setEstatusSeleccion: function () {
        utilEffect.showProgressBar();
        var estatusId = aplicacionJS.estatusId;
        var listaObjetos = utilGrid.gridGetSelectedRows('#aplicacionGrid');
        AplicacionDWR.setEstatus(estatusId, listaObjetos, aplicacionJS.setEstatusCallback);
    },
    /*
     * Callback de la función setEstatus(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     */
    setEstatusCallback: function () {
        aplicacionJS.reloadGrid();
        utilEffect.hideProgressBar();
    },
    /*
     * Función que muestra un mensaje de confirmación para Activar o Inactivar los registros
     * seleccionados del widget jQGrid.
     */
    prepareToSetEstatus: function (estatusId) {
        var seleccion = utilGrid.gridGetSelectedRows('#aplicacionGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedAplicacion');
        } else {
            aplicacionJS.estatusId = estatusId;
             utilDialog.showDialog( '#d-confirmSetEstatusAplicacion' );
            aplicacionJS.setEstatusSeleccion();
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar los registros seleccionados
     * del widget jQGrid.
     */
    prepareToRemoveSelected: function () {
        var seleccion = utilGrid.gridGetSelectedRows('#aplicacionGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedAplicacion');
        } else {
            utilDialog.showDialog('#d-removeSeleccionAplicacion');
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar un regristro
     * del widget jQGrid.
     */
    prepareToRemove: function (id) {
        aplicacionJS.setEliminarId(id);
        utilDialog.showDialog('#d-removeAplicacion');
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
        aplicacionJS.eliminarId = eliminarId;
    },
    /*
     * Getter de la variable eliminarId
     **/
    getEliminarId: function () {
        return aplicacionJS.eliminarId;
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
        var validation = validanguage.validateForm('aplicacionform');
        if (validation.result) {
            aplicacionJS.isValidoNombre();
        }
    },
    /*
     * Función que inicia el proceso para GUARDAR y ACTUALIZAR/MODIFICAR
     * Una vez que se pasaron las validaciones se realiza el commit.
     */
    doCommit: function () {
        if (jQuery('#aplicacionform_aplicacionId').val() == 0) {
            aplicacionJS.save();
        } else {
            aplicacionJS.update();
        }
    },
    /*
     * Función que es llamada para actualizar la información del grid.
     */
    reloadGrid: function () {
        if (aplicacionJS.cacheDWR != null) {
            aplicacionJS.findByCriteria(aplicacionJS.cacheDWR);
        } else {
            aplicacionJS.findByCriteria(new AplicacionDWR());
        }
    },
    /*
     * Función que es activa el modal de busqueda.
     */
    openSearch: function () {
        utilSearch.openSearch('#d-searchAplicacion', '#aplicacionGrid', '#aplicacionform', aplicacionJS, new AplicacionDWR());
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
        aplicacionJS.entity = entity;
        aplicacionJS.headers = headers;
        aplicacionJS.format = format;
        aplicacionJS.reportName = jQuery('#key_aplicacion_title').val() +
                '_' + utilMisc.getTodayDate('-');
        var criteriaExample = new AplicacionDWR();
        if (aplicacionJS.cacheDWR != null) {
            criteriaExample = aplicacionJS.cacheDWR;
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
        AplicacionDWR.getReportDataTest(sortBy, sortType, criteriaExample, aplicacionJS.exportarDatosCallback);
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
                aplicacionJS.entity + '&headers=' + aplicacionJS.headers +
                '&reportName=' + aplicacionJS.reportName + '&format=' + aplicacionJS.format +
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
// Validaciones de aplicacionform
validanguage.el.aplicacionform_nombre = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
/*
validanguage.el.aplicacionform_anidar = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};
validanguage.el.aplicacionform_url = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter, {
            name: function (text) {
                if (text.length > 187) {
                    return false;
                } else {
                    return true;
                }
            },
            errorMsg: 'Solo se permiten 187 carácteres como máximo.',
            onerror: utilEffect.showValidationTooltip,
            onsuccess: utilEffect.hideValidationTooltip
        }
    ]};
validanguage.el.aplicacionform_icono = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};
validanguage.el.aplicacionform_orden = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};

*/