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
    utilInput.fixRadios('#paginaform');


    // <editor-fold defaultstate="collapsed" desc="Botones">
    // Botón : NUEVO
    jQuery('#btnNewPagina').click(function () {
        utilForm.reset('#paginaform');
        jQuery('#paginaform_paginaId').val(0);
        paginaJS.pagina = null;
    }).customButtonEffect('#btnNewPagina');
    // Botón : GUARDAR
    jQuery('#btnSavePagina').click(function () {
        paginaJS.prepareToSave();
    }).customButtonEffectBlue('#btnSavePagina');
    // </editor-fold>

    if (jQuery('#gridVisiblePagina').val() == 'true') {
        jQuery('#paginaGrid').jqGrid({
            url: '',
            datatype: '',
            colNames: [
                jQuery('#pagina_paginaIdText').html(),
                jQuery('#pagina_nombreText').html(),
                jQuery('#pagina_anidarText').html(),
                jQuery('#pagina_urlText').html(),
                jQuery('#pagina_iconoText').html(),
                jQuery('#pagina_ordenText').html(),
                jQuery('#pagina_noIncluirEnMenuText').html(),
                jQuery('#pagina_privadoText').html(),
                jQuery('#pagina_sinGridText').html(),
                jQuery('#pagina_individualText').html(),
                jQuery('#pagina_estatusIdText').html(),
                jQuery('#pagina_eliminadoIdText').html(),
                jQuery('#pagina_creacionFechaText').html(),
                jQuery('#pagina_creacionUsuarioText').html(),
                jQuery('#pagina_modificacionFechaText').html(),
                jQuery('#pagina_modificacionUsuarioText').html(),
                '', '', ''],
            colModel: [{
                    name: 'paginaId',
                    index: 'paginaId',
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
                    name: 'anidar',
                    index: 'anidar',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'url',
                    index: 'url',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'icono',
                    index: 'icono',
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
                    name: 'noIncluirEnMenu',
                    index: 'noIncluirEnMenu',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'privado',
                    index: 'privado',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'sinGrid',
                    index: 'sinGrid',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'individual',
                    index: 'individual',
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
            pager: jQuery('#paginaGridPagerId'),
            caption: jQuery('#key_pagina_title').val()
        });
        // jQuery UI Dialogs
        utilDialog.setStandardDialog('#d-removePagina', jQuery('#msgAlertaText').val(), jQuery('#msgDeseaEliminarText').val(), ['Aceptar@paginaJS.remove()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-removeSeleccionPagina', jQuery('#msgAlertaText').val(), jQuery('#msgDeseaEliminarSeleccionText').val(), ['Aceptar@paginaJS.removeSelected()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-confirmSetEstatusPagina', jQuery('#msgAlertaText').val(), jQuery('#msgCambiarEstatusText').val(), ['Aceptar@paginaJS.setEstatusSeleccion()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-noSelectedPagina', jQuery('#msgAlertaText').val(), jQuery('#msgSeleccioneUnoOMas').val(), ['Aceptar@']);
        utilDialog.setStandardDialog('#d-registroDuplicadoPagina', jQuery('#msgAlertaText').val(), jQuery('#msgYaExisteRegistro').val(), ['Aceptar@paginaJS.doCommit()', 'Cancelar@']);
        utilDialog.setCustomDialogSelectColumnsToExport("#paginaColumnasExportar", '#paginaGrid');
        utilDialog.setCustomDialogSetEstatus('#paginaSetEstatusActivoInactivo',
                'paginaJS.prepareToSetEstatus(1)', 'paginaJS.prepareToSetEstatus(2)');

        // Inicializaciones extra para el grid
        utilGrid.setup('#paginaGrid', '#paginaGridPagerId', '#paginaGridCurrentPage', '#paginaGridOrderByColumn',
                '#paginaGridOrderByType', 'paginaJS');

        // Crear el modal de busqueda
        utilSearch.buildSearch('#d-searchPagina');

        // Recargar el widget jqGrid
        paginaJS.reloadGrid();

    }
    // Limpiar la página
    jQuery('#btnNewPagina').click();
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
    if (jQuery('#gridIndividualModePagina').val() == 'true') {
        jQuery('#btnNewPagina').hide();
        paginaJS.findFirst();
    }
    paginaJS.getMenus();
});

var paginaJS = {
    /* Variable auxiliar para persistir un objeto (resultado de una consulta) 
     * obtenido mediante el callback de la función "findById(...)". Este objeto
     * es necesario para la funcionalidad de selects encadenados pero puede ser
     * utilizado para otros propósitos. El objeto existe solamente mientras se
     * esta editando la información en el formulario. Al presionar el botón
     * Nuevo o Guardar cualquier referencia a éste regresará "null". El 
     * objeto debe utilizarse como modo de "Solo Lectura" (la modificación de 
     * este objeto no se verá reflejada en la base de datos) */
    pagina: null,
    /*
     * Variable auxiliar para mantener las busquedas del grid al momento de paginar, ordenar, etc.
     */
    cacheDWR: null,
    /*
     * Función que guarda un nuevo registro en la BD.
     */
    save: function () {
        utilEffect.showProgressBar();
        var pagina = utilObject.buildObject('#paginaform', new PaginaDWR());
        pagina.paginaId = 0;
        var listaPaginas = [pagina];
        PaginaDWR.save(listaPaginas, paginaJS.saveOrUpdateCallback);
    },
    /*
     * Función que actualiza un registro existente en la BD.
     */
    update: function () {
        utilEffect.showProgressBar();
        var pagina = utilObject.buildObject('#paginaform', new PaginaDWR());
        pagina.paginaId = jQuery('#paginaform_paginaId').val();
        var listaPaginas = [pagina];
        PaginaDWR.update(listaPaginas, paginaJS.saveOrUpdateCallback);
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
        if (jQuery('#gridIndividualModePagina').val() == 'false') {
            utilForm.reset('#paginaform');
            jQuery('#paginaform_paginaId').val(0);
            paginaJS.pagina = null;
            paginaJS.reloadGrid();
        }
        paginaJS.getMenus();
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
        var id = paginaJS.getEliminarId();
        var listaIds = [id];
        PaginaDWR.remove(listaIds, paginaJS.removeCallback);
    },
    /*
     * Función que elimina los registros seleccionados en el grid al presionar el botón eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como parámetro.
     */
    removeSelected: function () {
        utilEffect.showProgressBar();
        var listaIds = utilGrid.gridGetSelectedRows('#paginaGrid');
        PaginaDWR.remove(listaIds, paginaJS.removeCallback);
    },
    /*
     * Callback de la función remove(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    removeCallback: function () {
        paginaJS.getMenus();
        jQuery('#btnNewPagina').click();
        paginaJS.reloadGrid();
    },
    /*
     * Función que actualiza las opciones del select anidar despues de haber hecho 
     * una modificacion a la base de datos
     * 
     */
    getMenus: function () {
        utilEffect.showProgressBar();
        PaginaDWR.getMenus(paginaJS.getMenusCallback);
    },
    /*
     * Callback de la función getMenus(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    getMenusCallback: function (pageList) {
        jQuery('#paginaform_anidar').find('option').remove();
        for (var i = 0; i < pageList.length; i++) {
            var pagina = pageList[i];
            var id = pagina.paginaId;
            var nombre = pagina.nombre;
            var style = '';
            if (pagina.url == null || pagina.url == "") {
                style = 'style="font-weight: bold; color: #33B5E5"';
            }
            jQuery('#paginaform_anidar').append('<option ' + style +
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
        PaginaDWR.findById(id, paginaJS.findByIdCallback);
    },
    /*
     * Callback de la función findById(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto con la información de la consulta.
     */
    findByIdCallback: function (data) {
        utilForm.reset('#paginaform');
        utilForm.populate('#paginaform', data);
        paginaJS.pagina = data;
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
        paginaJS.cacheDWR = obj;
        var page = jQuery('#paginaGridCurrentPage').val();
        var rows = jQuery('#paginaGridRowsByPage').val();
        var order = jQuery('#paginaGridOrderByColumn').val();
        var orderType = jQuery('#paginaGridOrderByType').val();
        utilEffect.showProgressBar();
        PaginaDWR.findByCriteria(page, rows, order, orderType, obj, paginaJS.findByCriteriaCallback);
    },
    /*
     * Callback de la función findByCriteria(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto (Grid) con la información de la 
     * consulta.
     */
    findByCriteriaCallback: function (data) {
        utilGrid.gridUpdate('#paginaGrid', '#paginaGridCurrentPage', '#paginaGridRowsByPage', data);
        utilEffect.hideProgressBar();
    },
    /**
     * Funcion utilizado cuando la pagina es individual
     */
    findFirst: function () {
        utilEffect.showProgressBar();
        PaginaDWR.findFirst(paginaJS.findByIdCallback);
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
        var nombre = jQuery('#paginaform_nombre').val();
        PaginaDWR.isValidoNombre(nombre, paginaJS.isValidoNombreCallback);
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
        if (isValido == false && jQuery('#paginaform_paginaId').val() != 0) {
            paginaJS.doCommit();
            return;
        }

        // Nuevo registro, en este caso se permite el commit
        if (isValido == true && jQuery('#paginaform_paginaId').val() == 0) {
            paginaJS.doCommit();
            return;
        }

        // Registro existente, se le cambio el nombre, en este caso se permite el commit
        if (isValido == true && jQuery('#paginaform_paginaId').val() != 0) {
            paginaJS.doCommit();
            return;
        }

        // Mostrar mensaje de registro duplicado 
        utilDialog.showDialog('#d-registroDuplicadoPagina');
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
        PaginaDWR.setEstatus(estatusId, listaObjetos, paginaJS.setEstatusCallback);
    },
    /*
     * Función que cambia el Estatus de los elementos seleccionados en el widget
     * de jqGrid.
     * 
     */
    setEstatusSeleccion: function () {
        utilEffect.showProgressBar();
        var estatusId = paginaJS.estatusId;
        var listaObjetos = utilGrid.gridGetSelectedRows('#paginaGrid');
        PaginaDWR.setEstatus(estatusId, listaObjetos, paginaJS.setEstatusCallback);
    },
    /*
     * Callback de la función setEstatus(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     */
    setEstatusCallback: function () {
        paginaJS.reloadGrid();
        utilEffect.hideProgressBar();
    },
    /*
     * Función que muestra un mensaje de confirmación para Activar o Inactivar los registros
     * seleccionados del widget jQGrid.
     */
    prepareToSetEstatus: function (estatusId) {
        var seleccion = utilGrid.gridGetSelectedRows('#paginaGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedPagina');
        } else {
            paginaJS.estatusId = estatusId;
            // utilDialog.showDialog( '#d-confirmSetEstatusPagina' );
            paginaJS.setEstatusSeleccion();
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar los registros seleccionados
     * del widget jQGrid.
     */
    prepareToRemoveSelected: function () {
        var seleccion = utilGrid.gridGetSelectedRows('#paginaGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedPagina');
        } else {
            utilDialog.showDialog('#d-removeSeleccionPagina');
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar un regristro
     * del widget jQGrid.
     */
    prepareToRemove: function (id) {
        paginaJS.setEliminarId(id);
        utilDialog.showDialog('#d-removePagina');
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
        paginaJS.eliminarId = eliminarId;
    },
    /*
     * Getter de la variable eliminarId
     **/
    getEliminarId: function () {
        return paginaJS.eliminarId;
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
        var validation = validanguage.validateForm('paginaform');
        if (validation.result) {
            paginaJS.isValidoNombre();
        }
    },
    /*
     * Función que inicia el proceso para GUARDAR y ACTUALIZAR/MODIFICAR
     * Una vez que se pasaron las validaciones se realiza el commit.
     */
    doCommit: function () {
        if (jQuery('#paginaform_paginaId').val() == 0) {
            paginaJS.save();
        } else {
            paginaJS.update();
        }
    },
    /*
     * Función que es llamada para actualizar la información del grid.
     */
    reloadGrid: function () {
        if (paginaJS.cacheDWR != null) {
            paginaJS.findByCriteria(paginaJS.cacheDWR);
        } else {
            paginaJS.findByCriteria(new PaginaDWR());
        }
    },
    /*
     * Función que es activa el modal de busqueda.
     */
    openSearch: function () {
        utilSearch.openSearch('#d-searchPagina', '#paginaGrid', '#paginaform', paginaJS, new PaginaDWR());
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
        paginaJS.entity = entity;
        paginaJS.headers = headers;
        paginaJS.format = format;
        paginaJS.reportName = jQuery('#key_pagina_title').val() +
                '_' + utilMisc.getTodayDate('-');
        var criteriaExample = new PaginaDWR();
        if (paginaJS.cacheDWR != null) {
            criteriaExample = paginaJS.cacheDWR;
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
        PaginaDWR.getReportDataTest(sortBy, sortType, criteriaExample, paginaJS.exportarDatosCallback);
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
                paginaJS.entity + '&headers=' + paginaJS.headers +
                '&reportName=' + paginaJS.reportName + '&format=' + paginaJS.format +
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
// Validaciones de paginaform
validanguage.el.paginaform_nombre = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
validanguage.el.paginaform_anidar = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};
validanguage.el.paginaform_url = {
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
validanguage.el.paginaform_icono = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};
validanguage.el.paginaform_orden = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};

