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
    utilInput.fixRadios('#organizacionCredencialform');


    // <editor-fold defaultstate="collapsed" desc="Botones">
    // Botón : NUEVO
    jQuery('#btnNewOrganizacionCredencial').click(function () {
        utilForm.reset('#organizacionCredencialform');
        jQuery('#organizacionCredencialform_organizacionCredencialId').val(0);
        organizacionCredencialJS.organizacionCredencial = null;
    }).customButtonEffect('#btnNewOrganizacionCredencial');
    // Botón : GUARDAR
    jQuery('#btnSaveOrganizacionCredencial').click(function () {
        organizacionCredencialJS.prepareToSave();
    }).customButtonEffect('#btnSaveOrganizacionCredencial');
    // </editor-fold>

    if (jQuery('#gridVisibleOrganizacionCredencial').val() == 'true') {
        jQuery('#organizacionCredencialGrid').jqGrid({
            url: '',
            datatype: '',
            colNames: [
                jQuery('#organizacionCredencial_organizacionCredencialIdText').html(),
                jQuery('#organizacionCredencial_organizacionIdText').html(),
                jQuery('#organizacionCredencial_nombreText').html(),
                jQuery('#organizacionCredencial_domainText').html(),
                jQuery('#organizacionCredencial_aplicacionText').html(),
//                jQuery('#organizacionCredencial_idcredencialText').html(),
                jQuery('#organizacionCredencial_idanalyticsText').html(),
                jQuery('#organizacionCredencial_idappText').html(),
                jQuery('#organizacionCredencial_idrecapchaText').html(),
                jQuery('#organizacionCredencial_estatusIdText').html(),
                jQuery('#organizacionCredencial_eliminadoIdText').html(),
                jQuery('#organizacionCredencial_creacionFechaText').html(),
                jQuery('#organizacionCredencial_creacionUsuarioText').html(),
                jQuery('#organizacionCredencial_modificacionFechaText').html(),
                jQuery('#organizacionCredencial_modificacionUsuarioText').html(),
                '', '', ''],
            colModel: [{
                    name: 'organizacionCredencialId',
                    index: 'organizacionCredencialId',
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
                    name: 'nombre',
                    index: 'nombre',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'domain',
                    index: 'domain',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'idanalytics',
                    index: 'idanalytics',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
//                }, {
//                    name: 'idcredencial',
//                    index: 'idcredencial',
//                    align: 'center',
//                    resizable: false,
//                    hidden: false,
//                    search: true
                }, {
                    name: 'aplicacionId',
                    index: 'aplicacionId',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'idapp',
                    index: 'idapp',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'idrecapcha',
                    index: 'idrecapcha',
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
            pager: jQuery('#organizacionCredencialGridPagerId'),
            caption: jQuery('#key_organizacioncredencial_title').val()
        });
        // jQuery UI Dialogs
        utilDialog.setStandardDialog('#d-removeOrganizacionCredencial', 'Confirme', '¿Desea eliminar el registro?', ['Aceptar@organizacionCredencialJS.remove()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-removeSeleccionOrganizacionCredencial', 'Confirme', '¿Desea eliminar los registros seleccionados?', ['Aceptar@organizacionCredencialJS.removeSelected()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-confirmSetEstatusOrganizacionCredencial', 'Confirme', '¿Desea cambiar el estatus de los registros seleccionados?', ['Aceptar@organizacionCredencialJS.setEstatusSeleccion()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-noSelectedOrganizacionCredencial', 'Error', 'Para poder realizar esta operación seleccione uno ó más registros.', ['Aceptar@']);
        utilDialog.setStandardDialog('#d-registroDuplicadoOrganizacionCredencial', 'Advertencia', 'Ya existe un registro con este nombre en la base de datos, ¿Desea crear un nuevo registro con este nombre?', ['Aceptar@organizacionCredencialJS.doCommit()', 'Cancelar@']);
        utilDialog.setCustomDialogSelectColumnsToExport("#organizacionCredencialColumnasExportar", '#organizacionCredencialGrid');
        utilDialog.setCustomDialogSetEstatus('#organizacionCredencialSetEstatusActivoInactivo',
                'organizacionCredencialJS.prepareToSetEstatus(1)', 'organizacionCredencialJS.prepareToSetEstatus(2)');

        // Inicializaciones extra para el grid
        utilGrid.setup('#organizacionCredencialGrid', '#organizacionCredencialGridPagerId', '#organizacionCredencialGridCurrentPage', '#organizacionCredencialGridOrderByColumn',
                '#organizacionCredencialGridOrderByType', 'organizacionCredencialJS');

        // Crear el modal de busqueda
        utilSearch.buildSearch('#d-searchOrganizacionCredencial');

        // Recargar el widget jqGrid
        organizacionCredencialJS.reloadGrid();

    }
    // Limpiar la página
    jQuery('#btnNewOrganizacionCredencial').click();
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
    if (jQuery('#gridIndividualModeOrganizacionCredencial').val() == 'true') {
        jQuery('#btnNewOrganizacionCredencial').hide();
        organizacionCredencialJS.findFirst();
    }
});

var organizacionCredencialJS = {
    /* Variable auxiliar para persistir un objeto (resultado de una consulta) 
     * obtenido mediante el callback de la función "findById(...)". Este objeto
     * es necesario para la funcionalidad de selects encadenados pero puede ser
     * utilizado para otros propósitos. El objeto existe solamente mientras se
     * esta editando la información en el formulario. Al presionar el botón
     * Nuevo o Guardar cualquier referencia a éste regresará "null". El 
     * objeto debe utilizarse como modo de "Solo Lectura" (la modificación de 
     * este objeto no se verá reflejada en la base de datos) */
    organizacionCredencial: null,
    /*
     * Variable auxiliar para mantener las busquedas del grid al momento de paginar, ordenar, etc.
     */
    cacheDWR: null,
    /*
     * Función que guarda un nuevo registro en la BD.
     */
    save: function () {
        utilEffect.showProgressBar();
        var organizacionCredencial = utilObject.buildObject('#organizacionCredencialform', new OrganizacionCredencialDWR());
        organizacionCredencial.organizacionCredencialId = 0;
        var listaOrganizacionCredencials = [organizacionCredencial];
        OrganizacionCredencialDWR.save(listaOrganizacionCredencials, organizacionCredencialJS.saveOrUpdateCallback);
    },
    /*
     * Función que actualiza un registro existente en la BD.
     */
    update: function () {
        utilEffect.showProgressBar();
        var organizacionCredencial = utilObject.buildObject('#organizacionCredencialform', new OrganizacionCredencialDWR());
        organizacionCredencial.organizacionCredencialId = jQuery('#organizacionCredencialform_organizacionCredencialId').val();
        var listaOrganizacionCredencials = [organizacionCredencial];
        OrganizacionCredencialDWR.update(listaOrganizacionCredencials, organizacionCredencialJS.saveOrUpdateCallback);
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
        if (jQuery('#gridIndividualModeOrganizacionCredencial').val() == 'false') {
            utilForm.reset('#organizacionCredencialform');
            jQuery('#organizacionCredencialform_organizacionCredencialId').val(0);
            organizacionCredencialJS.organizacionCredencial = null;
            organizacionCredencialJS.reloadGrid();
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
        var id = organizacionCredencialJS.getEliminarId();
        var listaIds = [id];
        OrganizacionCredencialDWR.remove(listaIds, organizacionCredencialJS.removeCallback);
    },
    /*
     * Función que elimina los registros seleccionados en el grid al presionar el botón eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como parámetro.
     */
    removeSelected: function () {
        utilEffect.showProgressBar();
        var listaIds = utilGrid.gridGetSelectedRows('#organizacionCredencialGrid');
        OrganizacionCredencialDWR.remove(listaIds, organizacionCredencialJS.removeCallback);
    },
    /*
     * Callback de la función remove(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    removeCallback: function () {
        jQuery('#btnNewOrganizacionCredencial').click();
        organizacionCredencialJS.reloadGrid();
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
        OrganizacionCredencialDWR.findById(id, organizacionCredencialJS.findByIdCallback);
    },
    /*
     * Callback de la función findById(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto con la información de la consulta.
     */
    findByIdCallback: function (data) {
        utilForm.reset('#organizacionCredencialform');
        utilForm.populate('#organizacionCredencialform', data);
        organizacionCredencialJS.organizacionCredencial = data;
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
        organizacionCredencialJS.cacheDWR = obj;
        var page = jQuery('#organizacionCredencialGridCurrentPage').val();
        var rows = jQuery('#organizacionCredencialGridRowsByPage').val();
        var order = jQuery('#organizacionCredencialGridOrderByColumn').val();
        var orderType = jQuery('#organizacionCredencialGridOrderByType').val();
        utilEffect.showProgressBar();
        OrganizacionCredencialDWR.findByCriteria(page, rows, order, orderType, obj, organizacionCredencialJS.findByCriteriaCallback);
    },
    /*
     * Callback de la función findByCriteria(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto (Grid) con la información de la 
     * consulta.
     */
    findByCriteriaCallback: function (data) {
        utilGrid.gridUpdate('#organizacionCredencialGrid', '#organizacionCredencialGridCurrentPage', '#organizacionCredencialGridRowsByPage', data);
        utilEffect.hideProgressBar();
    },
    /**
     * Funcion utilizado cuando la pagina es individual
     */
    findFirst: function () {
        utilEffect.showProgressBar();
        OrganizacionCredencialDWR.findFirst(organizacionCredencialJS.findByIdCallback);
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
        var nombre = jQuery('#organizacionCredencialform_nombre').val();
        OrganizacionCredencialDWR.isValidoNombre(nombre, organizacionCredencialJS.isValidoNombreCallback);
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
        if (isValido == false && jQuery('#organizacionCredencialform_organizacionCredencialId').val() != 0) {
            organizacionCredencialJS.doCommit();
            return;
        }

        // Nuevo registro, en este caso se permite el commit
        if (isValido == true && jQuery('#organizacionCredencialform_organizacionCredencialId').val() == 0) {
            organizacionCredencialJS.doCommit();
            return;
        }

        // Registro existente, se le cambio el nombre, en este caso se permite el commit
        if (isValido == true && jQuery('#organizacionCredencialform_organizacionCredencialId').val() != 0) {
            organizacionCredencialJS.doCommit();
            return;
        }

        // Mostrar mensaje de registro duplicado 
        utilDialog.showDialog('#d-registroDuplicadoOrganizacionCredencial');
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
        OrganizacionCredencialDWR.setEstatus(estatusId, listaObjetos, organizacionCredencialJS.setEstatusCallback);
    },
    /*
     * Función que cambia el Estatus de los elementos seleccionados en el widget
     * de jqGrid.
     * 
     */
    setEstatusSeleccion: function () {
        utilEffect.showProgressBar();
        var estatusId = organizacionCredencialJS.estatusId;
        var listaObjetos = utilGrid.gridGetSelectedRows('#organizacionCredencialGrid');
        OrganizacionCredencialDWR.setEstatus(estatusId, listaObjetos, organizacionCredencialJS.setEstatusCallback);
    },
    /*
     * Callback de la función setEstatus(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     */
    setEstatusCallback: function () {
        organizacionCredencialJS.reloadGrid();
        utilEffect.hideProgressBar();
    },
    /*
     * Función que muestra un mensaje de confirmación para Activar o Inactivar los registros
     * seleccionados del widget jQGrid.
     */
    prepareToSetEstatus: function (estatusId) {
        var seleccion = utilGrid.gridGetSelectedRows('#organizacionCredencialGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedOrganizacionCredencial');
        } else {
            organizacionCredencialJS.estatusId = estatusId;
            // utilDialog.showDialog( '#d-confirmSetEstatusOrganizacionCredencial' );
            organizacionCredencialJS.setEstatusSeleccion();
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar los registros seleccionados
     * del widget jQGrid.
     */
    prepareToRemoveSelected: function () {
        var seleccion = utilGrid.gridGetSelectedRows('#organizacionCredencialGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedOrganizacionCredencial');
        } else {
            utilDialog.showDialog('#d-removeSeleccionOrganizacionCredencial');
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar un regristro
     * del widget jQGrid.
     */
    prepareToRemove: function (id) {
        organizacionCredencialJS.setEliminarId(id);
        utilDialog.showDialog('#d-removeOrganizacionCredencial');
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
        organizacionCredencialJS.eliminarId = eliminarId;
    },
    /*
     * Getter de la variable eliminarId
     **/
    getEliminarId: function () {
        return organizacionCredencialJS.eliminarId;
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
        var validation = validanguage.validateForm('organizacionCredencialform');
        if (validation.result) {
            organizacionCredencialJS.isValidoNombre();
        }
    },
    /*
     * Función que inicia el proceso para GUARDAR y ACTUALIZAR/MODIFICAR
     * Una vez que se pasaron las validaciones se realiza el commit.
     */
    doCommit: function () {
        if (jQuery('#organizacionCredencialform_organizacionCredencialId').val() == 0) {
            organizacionCredencialJS.save();
        } else {
            organizacionCredencialJS.update();
        }
    },
    /*
     * Función que es llamada para actualizar la información del grid.
     */
    reloadGrid: function () {
        if (organizacionCredencialJS.cacheDWR != null) {
            organizacionCredencialJS.findByCriteria(organizacionCredencialJS.cacheDWR);
        } else {
            organizacionCredencialJS.findByCriteria(new OrganizacionCredencialDWR());
        }
    },
    /*
     * Función que obtene las opciones de un elemento select organizacionCredencialform_organizacionId para recargarlo mediante AJAX.
     */
    getOrganizacion: function () {
        utilEffect.showProgressBar();
        OrganizacionCredencialDWR.getOrganizacion(organizacionCredencialJS.getOrganizacionCallback);
    },
    /*
     * Callback de la función getOrganizacion() esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    getOrganizacionCallback: function (list) {
        var idCmb = jQuery('#organizacionCredencialform_organizacionId').val();
        jQuery('#organizacionCredencialform_organizacionId').find('option').remove();
        for (var i = 0; i < list.length; i++) {
            var object = list[i];
            var id = object.organizacionId;
            var nombre = object.nombre;
            jQuery('#organizacionCredencialform_organizacionId').append('<option value="' + id + '">' + nombre + '</option>');
        }
        // Si el usuario selecciono una opcion en el popup entonces ponerlo como seleccionado
        var sel = parseInt(jQuery('#idFromIframe').val());
        if (sel == 0) {
            // La opcion del popup seleccionada esta inactiva, validar la opcion seleccionada antes de abrir el popup
            if (jQuery("#organizacionCredencialform_organizacionId option[value='" + idCmb + "']").length > 0) {
                jQuery('#organizacionCredencialform_organizacionId').val(idCmb);
            } else {
                jQuery('#organizacionCredencialform_organizacionId').val(0);
            }
        } else {
            // Checar si la seleccion esta activa
            if (jQuery("#organizacionCredencialform_organizacionId option[value='" + sel + "']").length > 0) {
                jQuery('#organizacionCredencialform_organizacionId').val(sel);
            }
        }
        utilEffect.hideProgressBar();
    },
    /*
     * Función que es activa el modal de busqueda.
     */
    openSearch: function () {
        utilSearch.openSearch('#d-searchOrganizacionCredencial', '#organizacionCredencialGrid', '#organizacionCredencialform', organizacionCredencialJS, new OrganizacionCredencialDWR());
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
        organizacionCredencialJS.entity = entity;
        organizacionCredencialJS.headers = headers;
        organizacionCredencialJS.format = format;
        organizacionCredencialJS.reportName = jQuery('#key_organizacioncredencial_title').val() +
                '_' + utilMisc.getTodayDate('-');
        var criteriaExample = new OrganizacionCredencialDWR();
        if (organizacionCredencialJS.cacheDWR != null) {
            criteriaExample = organizacionCredencialJS.cacheDWR;
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
        OrganizacionCredencialDWR.getReportDataTest(sortBy, sortType, criteriaExample, organizacionCredencialJS.exportarDatosCallback);
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
                organizacionCredencialJS.entity + '&headers=' + organizacionCredencialJS.headers +
                '&reportName=' + organizacionCredencialJS.reportName + '&format=' + organizacionCredencialJS.format +
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
// Validaciones de organizacionCredencialform
validanguage.el.organizacionCredencialform_organizacionId = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};
validanguage.el.organizacionCredencialform_nombre = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};
validanguage.el.organizacionCredencialform_domain = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
validanguage.el.organizacionCredencialform_idcredencial = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};
validanguage.el.organizacionCredencialform_path = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};
validanguage.el.organizacionCredencialform_idapp = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};
validanguage.el.organizacionCredencialform_idrecapcha = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};

