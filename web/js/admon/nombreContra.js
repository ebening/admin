jQuery(document).ready(function () {

    // Actualizar "SubclasificacionContra" al seleccionar "ClasificacionContra" en formulario nombreContra
    jQuery('#nombreContraform_clasificacionContraId').change(function () {
        nombreContraJS.filtrarSubclasificacionContra();
    });


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
    utilInput.fixRadios('#nombreContraform');


    // <editor-fold defaultstate="collapsed" desc="Botones">
    // Botón : NUEVO
    jQuery('#btnNewNombreContra').click(function () {
        utilForm.reset('#nombreContraform');
        // Limpiar campo con dependencias
        utilInput.removeOptionsSelectAndAddDummy('#nombreContraform_subclasificacionContraId', '--Seleccione--');
        jQuery('#nombreContraform_nombreContraId').val(0);
        nombreContraJS.nombreContra = null;
    }).customButtonEffect('#btnNewNombreContra');
    // Botón : GUARDAR
    jQuery('#btnSaveNombreContra').click(function () {
        nombreContraJS.prepareToSave();
    }).customButtonEffect('#btnSaveNombreContra');
    // </editor-fold>

    if (jQuery('#gridVisibleNombreContra').val() == 'true') {
        jQuery('#nombreContraGrid').jqGrid({
            url: '',
            datatype: '',
            colNames: [
                jQuery('#nombreContra_nombreContraIdText').html(),
                jQuery('#nombreContra_clasificacionContraIdText').html(),
                jQuery('#nombreContra_subclasificacionContraIdText').html(),
                jQuery('#nombreContra_nombreText').html(),
                jQuery('#nombreContra_tipoValorIdText').html(),
                jQuery('#nombreContra_checkboxContraText').html(),
                jQuery('#nombreContra_ordenText').html(),
                jQuery('#nombreContra_estatusIdText').html(),
                jQuery('#nombreContra_eliminadoIdText').html(),
                jQuery('#nombreContra_creacionFechaText').html(),
                jQuery('#nombreContra_creacionUsuarioText').html(),
                jQuery('#nombreContra_modificacionFechaText').html(),
                jQuery('#nombreContra_modificacionUsuarioText').html(),
                '', '', ''],
            colModel: [{
                    name: 'nombreContraId',
                    index: 'nombreContraId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'clasificacionContraId',
                    index: 'clasificacionContraId',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'subclasificacionContraId',
                    index: 'subclasificacionContraId',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: false
                }, {
                    name: 'nombre',
                    index: 'nombre',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'tipoValorId',
                    index: 'tipoValorId',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'checkboxContra',
                    index: 'checkboxContra',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
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
            pager: jQuery('#nombreContraGridPagerId'),
            caption: jQuery('#key_nombrecontra_title').val()
        });
        // jQuery UI Dialogs
        utilDialog.setStandardDialog('#d-removeNombreContra', 'Confirme', '¿Desea eliminar el registro?', ['Aceptar@nombreContraJS.remove()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-removeSeleccionNombreContra', 'Confirme', '¿Desea eliminar los registros seleccionados?', ['Aceptar@nombreContraJS.removeSelected()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-confirmSetEstatusNombreContra', 'Confirme', '¿Desea cambiar el estatus de los registros seleccionados?', ['Aceptar@nombreContraJS.setEstatusSeleccion()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-noSelectedNombreContra', 'Error', 'Para poder realizar esta operación seleccione uno ó más registros.', ['Aceptar@']);
        utilDialog.setStandardDialog('#d-registroDuplicadoNombreContra', 'Advertencia', 'Ya existe un registro con este nombre en la base de datos, ¿Desea crear un nuevo registro con este nombre?', ['Aceptar@nombreContraJS.doCommit()', 'Cancelar@']);
        utilDialog.setCustomDialogSelectColumnsToExport("#nombreContraColumnasExportar", '#nombreContraGrid');
        utilDialog.setCustomDialogSetEstatus('#nombreContraSetEstatusActivoInactivo',
                'nombreContraJS.prepareToSetEstatus(1)', 'nombreContraJS.prepareToSetEstatus(2)');

        // Inicializaciones extra para el grid
        utilGrid.setup('#nombreContraGrid', '#nombreContraGridPagerId', '#nombreContraGridCurrentPage', '#nombreContraGridOrderByColumn',
                '#nombreContraGridOrderByType', 'nombreContraJS');

        // Crear el modal de busqueda
        utilSearch.buildSearch('#d-searchNombreContra');

        // Recargar el widget jqGrid
        nombreContraJS.reloadGrid();

    }
    // Limpiar la página
    jQuery('#btnNewNombreContra').click();
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
    if (jQuery('#gridIndividualModeNombreContra').val() == 'true') {
        jQuery('#btnNewNombreContra').hide();
        nombreContraJS.findFirst();
    }
    jQuery('#nombreContraGrid').setGridWidth(
            jQuery("#gbox_" + "nombreContraGrid").closest(".grid-container").width() - 80);
    jQuery('#grid-action-activar-inactivar').hide().next().hide();
});

var nombreContraJS = {
    /* Variable auxiliar para persistir un objeto (resultado de una consulta) 
     * obtenido mediante el callback de la función "findById(...)". Este objeto
     * es necesario para la funcionalidad de selects encadenados pero puede ser
     * utilizado para otros propósitos. El objeto existe solamente mientras se
     * esta editando la información en el formulario. Al presionar el botón
     * Nuevo o Guardar cualquier referencia a éste regresará "null". El 
     * objeto debe utilizarse como modo de "Solo Lectura" (la modificación de 
     * este objeto no se verá reflejada en la base de datos) */
    nombreContra: null,
    /*
     * Variable auxiliar para mantener las busquedas del grid al momento de paginar, ordenar, etc.
     */
    cacheDWR: null,
    /*
     * Función que guarda un nuevo registro en la BD.
     */
    save: function () {
        utilEffect.showProgressBar();
        var nombreContra = utilObject.buildObject('#nombreContraform', new NombreContraDWR());
        nombreContra.nombreContraId = 0;
        var listaNombreContras = [nombreContra];
        NombreContraDWR.save(listaNombreContras, nombreContraJS.saveOrUpdateCallback);
    },
    /*
     * Función que actualiza un registro existente en la BD.
     */
    update: function () {
        utilEffect.showProgressBar();
        var nombreContra = utilObject.buildObject('#nombreContraform', new NombreContraDWR());
        nombreContra.nombreContraId = jQuery('#nombreContraform_nombreContraId').val();
        var listaNombreContras = [nombreContra];
        NombreContraDWR.update(listaNombreContras, nombreContraJS.saveOrUpdateCallback);
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
        if (jQuery('#gridIndividualModeNombreContra').val() == 'false') {
            utilForm.reset('#nombreContraform');
            jQuery('#nombreContraform_nombreContraId').val(0);
            nombreContraJS.nombreContra = null;
            nombreContraJS.reloadGrid();
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
        var id = nombreContraJS.getEliminarId();
        var listaIds = [id];
        NombreContraDWR.remove(listaIds, nombreContraJS.removeCallback);
    },
    /*
     * Función que elimina los registros seleccionados en el grid al presionar el botón eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como parámetro.
     */
    removeSelected: function () {
        utilEffect.showProgressBar();
        var listaIds = utilGrid.gridGetSelectedRows('#nombreContraGrid');
        NombreContraDWR.remove(listaIds, nombreContraJS.removeCallback);
    },
    /*
     * Callback de la función remove(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    removeCallback: function () {
        jQuery('#btnNewNombreContra').click();
        nombreContraJS.reloadGrid();
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
        NombreContraDWR.findById(id, nombreContraJS.findByIdCallback);
    },
    /*
     * Callback de la función findById(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto con la información de la consulta.
     */
    findByIdCallback: function (data) {
        utilForm.reset('#nombreContraform');
        utilForm.populate('#nombreContraform', data);
        nombreContraJS.nombreContra = data;
        jQuery('#nombreContraform_clasificacionContraId').trigger('change');
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
        nombreContraJS.cacheDWR = obj;
        var page = jQuery('#nombreContraGridCurrentPage').val();
        var rows = jQuery('#nombreContraGridRowsByPage').val();
        var order = jQuery('#nombreContraGridOrderByColumn').val();
        var orderType = jQuery('#nombreContraGridOrderByType').val();
        utilEffect.showProgressBar();
        NombreContraDWR.findByCriteria(page, rows, order, orderType, obj, nombreContraJS.findByCriteriaCallback);
    },
    /*
     * Callback de la función findByCriteria(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto (Grid) con la información de la 
     * consulta.
     */
    findByCriteriaCallback: function (data) {
        utilGrid.gridUpdate('#nombreContraGrid', '#nombreContraGridCurrentPage', '#nombreContraGridRowsByPage', data);
        utilEffect.hideProgressBar();
    },
    /**
     * Funcion utilizado cuando la pagina es individual
     */
    findFirst: function () {
        utilEffect.showProgressBar();
        NombreContraDWR.findFirst(nombreContraJS.findByIdCallback);
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
        var nombre = jQuery('#nombreContraform_nombre').val();
        NombreContraDWR.isValidoNombre(nombre, nombreContraJS.isValidoNombreCallback);
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
        if (isValido == false && jQuery('#nombreContraform_nombreContraId').val() != 0) {
            nombreContraJS.doCommit();
            return;
        }

        // Nuevo registro, en este caso se permite el commit
        if (isValido == true && jQuery('#nombreContraform_nombreContraId').val() == 0) {
            nombreContraJS.doCommit();
            return;
        }

        // Registro existente, se le cambio el nombre, en este caso se permite el commit
        if (isValido == true && jQuery('#nombreContraform_nombreContraId').val() != 0) {
            nombreContraJS.doCommit();
            return;
        }

        // Mostrar mensaje de registro duplicado 
        utilDialog.showDialog('#d-registroDuplicadoNombreContra');
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
        NombreContraDWR.setEstatus(estatusId, listaObjetos, nombreContraJS.setEstatusCallback);
    },
    /*
     * Función que cambia el Estatus de los elementos seleccionados en el widget
     * de jqGrid.
     * 
     */
    setEstatusSeleccion: function () {
        utilEffect.showProgressBar();
        var estatusId = nombreContraJS.estatusId;
        var listaObjetos = utilGrid.gridGetSelectedRows('#nombreContraGrid');
        NombreContraDWR.setEstatus(estatusId, listaObjetos, nombreContraJS.setEstatusCallback);
    },
    /*
     * Callback de la función setEstatus(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     */
    setEstatusCallback: function () {
        nombreContraJS.reloadGrid();
        utilEffect.hideProgressBar();
    },
    /*
     * Función que muestra un mensaje de confirmación para Activar o Inactivar los registros
     * seleccionados del widget jQGrid.
     */
    prepareToSetEstatus: function (estatusId) {
        var seleccion = utilGrid.gridGetSelectedRows('#nombreContraGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedNombreContra');
        } else {
            nombreContraJS.estatusId = estatusId;
            // utilDialog.showDialog( '#d-confirmSetEstatusNombreContra' );
            nombreContraJS.setEstatusSeleccion();
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar los registros seleccionados
     * del widget jQGrid.
     */
    prepareToRemoveSelected: function () {
        var seleccion = utilGrid.gridGetSelectedRows('#nombreContraGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedNombreContra');
        } else {
            utilDialog.showDialog('#d-removeSeleccionNombreContra');
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar un regristro
     * del widget jQGrid.
     */
    prepareToRemove: function (id) {
        nombreContraJS.setEliminarId(id);
        utilDialog.showDialog('#d-removeNombreContra');
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
        nombreContraJS.eliminarId = eliminarId;
    },
    /*
     * Getter de la variable eliminarId
     **/
    getEliminarId: function () {
        return nombreContraJS.eliminarId;
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
        var validation = validanguage.validateForm('nombreContraform');
        if (validation.result) {
            nombreContraJS.isValidoNombre();
        }
    },
    /*
     * Función que inicia el proceso para GUARDAR y ACTUALIZAR/MODIFICAR
     * Una vez que se pasaron las validaciones se realiza el commit.
     */
    doCommit: function () {
        if (jQuery('#nombreContraform_nombreContraId').val() == 0) {
            nombreContraJS.save();
        } else {
            nombreContraJS.update();
        }
    },
    /*
     * Función que es llamada para actualizar la información del grid.
     */
    reloadGrid: function () {
        if (nombreContraJS.cacheDWR != null) {
            nombreContraJS.findByCriteria(nombreContraJS.cacheDWR);
        } else {
            nombreContraJS.findByCriteria(new NombreContraDWR());
        }
    },
    /*
     * Función que obtene las opciones de un elemento select nombreContraform_clasificacionContraId para recargarlo mediante AJAX.
     */
    getClasificacionContra: function () {
        utilEffect.showProgressBar();
        NombreContraDWR.getClasificacionContra(nombreContraJS.getClasificacionContraCallback);
    },
    /*
     * Callback de la función getClasificacionContra() esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    getClasificacionContraCallback: function (list) {
        var idCmb = jQuery('#nombreContraform_clasificacionContraId').val();
        jQuery('#nombreContraform_clasificacionContraId').find('option').remove();
        for (var i = 0; i < list.length; i++) {
            var object = list[i];
            var id = object.clasificacionContraId;
            var nombre = object.nombre;
            jQuery('#nombreContraform_clasificacionContraId').append('<option value="' + id + '">' + nombre + '</option>');
        }
        // Si el usuario selecciono una opcion en el popup entonces ponerlo como seleccionado
        var sel = parseInt(jQuery('#idFromIframe').val());
        if (sel == 0) {
            // La opcion del popup seleccionada esta inactiva, validar la opcion seleccionada antes de abrir el popup
            if (jQuery("#nombreContraform_clasificacionContraId option[value='" + idCmb + "']").length > 0) {
                jQuery('#nombreContraform_clasificacionContraId').val(idCmb);
            } else {
                jQuery('#nombreContraform_clasificacionContraId').val(0);
            }
        } else {
            // Checar si la seleccion esta activa
            if (jQuery("#nombreContraform_clasificacionContraId option[value='" + sel + "']").length > 0) {
                jQuery('#nombreContraform_clasificacionContraId').val(sel);
            }
        }
        utilEffect.hideProgressBar();
    },
    /*
     * Función que obtene las opciones de un elemento select nombreContraform_subclasificacionContraId para recargarlo mediante AJAX.
     */
    getSubclasificacionContra: function () {
        utilEffect.showProgressBar();
        NombreContraDWR.getSubclasificacionContra(nombreContraJS.getSubclasificacionContraCallback);
    },
    /*
     * Callback de la función getSubclasificacionContra() esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    getSubclasificacionContraCallback: function (list) {
        var idCmb = jQuery('#nombreContraform_subclasificacionContraId').val();
        jQuery('#nombreContraform_subclasificacionContraId').find('option').remove();
        for (var i = 0; i < list.length; i++) {
            var object = list[i];
            var id = object.subclasificacionContraId;
            var nombre = object.nombre;
            jQuery('#nombreContraform_subclasificacionContraId').append('<option value="' + id + '">' + nombre + '</option>');
        }
        // Si el usuario selecciono una opcion en el popup entonces ponerlo como seleccionado
        var sel = parseInt(jQuery('#idFromIframe').val());
        if (sel == 0) {
            // La opcion del popup seleccionada esta inactiva, validar la opcion seleccionada antes de abrir el popup
            if (jQuery("#nombreContraform_subclasificacionContraId option[value='" + idCmb + "']").length > 0) {
                jQuery('#nombreContraform_subclasificacionContraId').val(idCmb);
            } else {
                jQuery('#nombreContraform_subclasificacionContraId').val(0);
            }
        } else {
            // Checar si la seleccion esta activa
            if (jQuery("#nombreContraform_subclasificacionContraId option[value='" + sel + "']").length > 0) {
                jQuery('#nombreContraform_subclasificacionContraId').val(sel);
            }
        }
        utilEffect.hideProgressBar();
    },
    /*
     * Función que es activa el modal de busqueda.
     */
    openSearch: function () {
        utilSearch.openSearch('#d-searchNombreContra', '#nombreContraGrid', '#nombreContraform', nombreContraJS, new NombreContraDWR());
    },
    /** 
     * Filtrar SubclasificacionContra
     * 
     */
    filtrarSubclasificacionContra: function () {
        utilEffect.showProgressBar();
        NombreContraDWR.filtrarSubclasificacionContra(
                jQuery('#nombreContraform_clasificacionContraId').val(),
                nombreContraJS.filtrarSubclasificacionContraCallback);
    },
    /** 
     * Callback de la función filtrarSubclasificacionContra(), esta
     * función es llamada por DWR al terminar de realizar la consulta.
     */
    filtrarSubclasificacionContraCallback: function (data) {
        if (data == null || data.length == 0) {
            utilInput.removeOptionsSelectAndAddDummy('#nombreContraform_subclasificacionContraId', '--Seleccione--');
        } else {
            utilInput.populateSelect('#nombreContraform_subclasificacionContraId', data, 'subclasificacionContraId', 'nombre');
            if (nombreContraJS.nombreContra !== null) {
                if (nombreContraJS.nombreContra.subclasificacionContraId !== null) {
                    if (utilInput.existsOptionByValueInSelect('#nombreContraform_subclasificacionContraId', nombreContraJS.nombreContra.subclasificacionContraId)) {
                        jQuery('#nombreContraform_subclasificacionContraId').val(nombreContraJS.nombreContra.subclasificacionContraId);
                    } else {
                        jQuery('#nombreContraform_subclasificacionContraId').val(0);
                    }
                }
            }
            jQuery('#nombreContraform_subclasificacionContraId').trigger('change');
        }
        utilEffect.hideProgressBar();
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
        nombreContraJS.entity = entity;
        nombreContraJS.headers = headers;
        nombreContraJS.format = format;
        nombreContraJS.reportName = jQuery('#key_nombrecontra_title').val() +
                '_' + utilMisc.getTodayDate('-');
        var criteriaExample = new NombreContraDWR();
        if (nombreContraJS.cacheDWR != null) {
            criteriaExample = nombreContraJS.cacheDWR;
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
        NombreContraDWR.getReportDataTest(sortBy, sortType, criteriaExample, nombreContraJS.exportarDatosCallback);
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
                nombreContraJS.entity + '&headers=' + nombreContraJS.headers +
                '&reportName=' + nombreContraJS.reportName + '&format=' + nombreContraJS.format +
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
// Validaciones de nombreContraform
validanguage.el.nombreContraform_clasificacionContraId = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
validanguage.el.nombreContraform_subclasificacionContraId = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
validanguage.el.nombreContraform_nombre = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
validanguage.el.nombreContraform_tipoValorId = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
validanguage.el.nombreContraform_orden = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};

