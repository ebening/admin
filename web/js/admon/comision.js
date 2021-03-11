jQuery(document).ready(function () {
    comisionJS.getNombreContra();
    // Actualizar "SubclasificacionContra" al seleccionar "ClasificacionContra" en formulario comision
    jQuery('#comisionform_clasificacionContraId').change(function () {
        comisionJS.filtrarSubclasificacionContra();
    });

    jQuery('select#comisionform_clasificacionContraId').on('change', function () {
        var valor = $("#comisionform_clasificacionContraId option:selected").html();
        jQuery('#comisionform_nombreClasificacion').val(valor);
    });
    jQuery('select#comisionform_subclasificacionContraId').on('change', function () {
        var valor = $("#comisionform_subclasificacionContraId option:selected").html();
        jQuery('#comisionform_nombreSubclasificacion').val(valor);
    });
    jQuery('select#comisionform_nombreContraId').on('change', function () {
        var valor = $("#comisionform_nombreContraId option:selected").html();
        jQuery('#comisionform_nombre').val(valor);
        comisionJS.getNombreContraData(jQuery('select#comisionform_nombreContraId').val());
    });

    // Actualizar "NombreContra" al seleccionar "SubclasificacionContra" en formulario comision
//jQuery( '#comisionform_subclasificacionContraId' ).change( function() {
//comisionJS.filtrarNombreContra();
//});


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
    utilInput.fixRadios('#comisionform');


    // <editor-fold defaultstate="collapsed" desc="Botones">
    // Botón : NUEVO
    jQuery('#btnNewComision').click(function () {
        utilForm.reset('#comisionform');
        // Limpiar campo con dependencias
        utilInput.removeOptionsSelectAndAddDummy('#comisionform_subclasificacionContraId', '--Seleccione--');
        // Limpiar campo con dependencias
        utilInput.removeOptionsSelectAndAddDummy('#comisionform_nombreContraId', '--Seleccione--');
        jQuery('#comisionform_comisionId').val(0);
        comisionJS.comision = null;
    }).customButtonEffect('#btnNewComision');
    // Botón : GUARDAR
    jQuery('#btnSaveComision').click(function () {
        comisionJS.prepareToSave();
    }).customButtonEffect('#btnSaveComision');
    // </editor-fold>

    if (jQuery('#gridVisibleComision').val() == 'true') {
        jQuery('#comisionGrid').jqGrid({
            url: '',
            datatype: '',
            colNames: [
                jQuery('#comision_comisionIdText').html(),
                jQuery('#comision_clasificacionContraIdText').html(),
                jQuery('#comision_nombreClasificacionText').html(),
                jQuery('#comision_subclasificacionContraIdText').html(),
                jQuery('#comision_nombreSubclasificacionText').html(),
                jQuery('#comision_nombreContraIdText').html(),
                jQuery('#comision_nombreText').html(),
                "Tipo",
                jQuery('#comision_valorText').html(),
                jQuery('#comision_checkboxText').html(),
                jQuery('#comision_estatusIdText').html(),
                jQuery('#comision_eliminadoIdText').html(),
                jQuery('#comision_creacionFechaText').html(),
                jQuery('#comision_creacionUsuarioText').html(),
                jQuery('#comision_modificacionFechaText').html(),
                jQuery('#comision_modificacionUsuarioText').html(),
                '', '', ''],
            colModel: [{
                    name: 'comisionId',
                    index: 'comisionId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'clasificacionContraId',
                    index: 'clasificacionContraId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'nombreClasificacion',
                    index: 'nombreClasificacion',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'subclasificacionContraId',
                    index: 'subclasificacionContraId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'nombreSubclasificacion',
                    index: 'nombreSubclasificacion',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'nombreContraId',
                    index: 'nombreContraId',
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
                    name: 'tipoValor',
                    index: 'tipoValor',
                    align: 'center',
                    resizable: false,
                    width: 50,
                    hidden: false,
                    search: false
                }, {
                    name: 'valor',
                    index: 'valor',
                    align: 'right',
                    resizable: false,
                    width: 70,
                    hidden: false,
                    search: true
                }, {
                    name: 'checkbox',
                    index: 'checkbox',
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
            pager: jQuery('#comisionGridPagerId'),
            caption: jQuery('#key_comision_title').val()
        });
        // jQuery UI Dialogs
        utilDialog.setStandardDialog('#d-removeComision', 'Confirme', '¿Desea eliminar el registro?', ['Aceptar@comisionJS.remove()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-removeSeleccionComision', 'Confirme', '¿Desea eliminar los registros seleccionados?', ['Aceptar@comisionJS.removeSelected()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-confirmSetEstatusComision', 'Confirme', '¿Desea cambiar el estatus de los registros seleccionados?', ['Aceptar@comisionJS.setEstatusSeleccion()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-noSelectedComision', 'Error', 'Para poder realizar esta operación seleccione uno ó más registros.', ['Aceptar@']);
        utilDialog.setStandardDialog('#d-registroDuplicadoComision', 'Advertencia', 'Ya existe un registro con este nombre en la base de datos, ¿Desea crear un nuevo registro con este nombre?', ['Aceptar@comisionJS.doCommit()', 'Cancelar@']);
        utilDialog.setCustomDialogSelectColumnsToExport("#comisionColumnasExportar", '#comisionGrid');
        utilDialog.setCustomDialogSetEstatus('#comisionSetEstatusActivoInactivo',
                'comisionJS.prepareToSetEstatus(1)', 'comisionJS.prepareToSetEstatus(2)');

        // Inicializaciones extra para el grid
        utilGrid.setup('#comisionGrid', '#comisionGridPagerId', '#comisionGridCurrentPage', '#comisionGridOrderByColumn',
                '#comisionGridOrderByType', 'comisionJS');

        // Crear el modal de busqueda
        utilSearch.buildSearch('#d-searchComision');

        // Recargar el widget jqGrid
        comisionJS.reloadGrid();

    }
    // Limpiar la página
    jQuery('#btnNewComision').click();
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
    if (jQuery('#gridIndividualModeComision').val() == 'true') {
        jQuery('#btnNewComision').hide();
        comisionJS.findFirst();
    }
//jQuery( '#comisionGrid' ).setGridWidth(
//        jQuery("#gbox_" + "comisionGrid").closest(".grid-container").width()-101);
});

var comisionJS = {
    /* Variable auxiliar para persistir un objeto (resultado de una consulta) 
     * obtenido mediante el callback de la función "findById(...)". Este objeto
     * es necesario para la funcionalidad de selects encadenados pero puede ser
     * utilizado para otros propósitos. El objeto existe solamente mientras se
     * esta editando la información en el formulario. Al presionar el botón
     * Nuevo o Guardar cualquier referencia a éste regresará "null". El 
     * objeto debe utilizarse como modo de "Solo Lectura" (la modificación de 
     * este objeto no se verá reflejada en la base de datos) */
    comision: null,
    /*
     * Variable auxiliar para mantener las busquedas del grid al momento de paginar, ordenar, etc.
     */
    cacheDWR: null,
    /*
     * Función que guarda un nuevo registro en la BD.
     */
    save: function () {
        utilEffect.showProgressBar();
        var comision = utilObject.buildObject('#comisionform', new ComisionDWR());
        comision.comisionId = 0;
        var listaComisions = [comision];
        ComisionDWR.save(listaComisions, comisionJS.saveOrUpdateCallback);
    },
    /*
     * Función que actualiza un registro existente en la BD.
     */
    update: function () {
        utilEffect.showProgressBar();
        var comision = utilObject.buildObject('#comisionform', new ComisionDWR());
        comision.comisionId = jQuery('#comisionform_comisionId').val();
        var listaComisions = [comision];
        ComisionDWR.update(listaComisions, comisionJS.saveOrUpdateCallback);
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
        if (jQuery('#gridIndividualModeComision').val() == 'false') {
            utilForm.reset('#comisionform');
            jQuery('#comisionform_comisionId').val(0);
            comisionJS.comision = null;
            comisionJS.reloadGrid();
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
        var id = comisionJS.getEliminarId();
        var listaIds = [id];
        ComisionDWR.remove(listaIds, comisionJS.removeCallback);
    },
    /*
     * Función que elimina los registros seleccionados en el grid al presionar el botón eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como parámetro.
     */
    removeSelected: function () {
        utilEffect.showProgressBar();
        var listaIds = utilGrid.gridGetSelectedRows('#comisionGrid');
        ComisionDWR.remove(listaIds, comisionJS.removeCallback);
    },
    /*
     * Callback de la función remove(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    removeCallback: function () {
        jQuery('#btnNewComision').click();
        comisionJS.reloadGrid();
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
        ComisionDWR.findById(id, comisionJS.findByIdCallback);
    },
    /*
     * Callback de la función findById(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto con la información de la consulta.
     */
    findByIdCallback: function (data) {
        utilForm.reset('#comisionform');
        utilForm.populate('#comisionform', data);
        comisionJS.comision = data;
        jQuery('#comisionform_clasificacionContraId').trigger('change');
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
        comisionJS.cacheDWR = obj;
        var page = jQuery('#comisionGridCurrentPage').val();
        var rows = jQuery('#comisionGridRowsByPage').val();
        var order = jQuery('#comisionGridOrderByColumn').val();
        var orderType = jQuery('#comisionGridOrderByType').val();
        utilEffect.showProgressBar();
        ComisionDWR.findByCriteria(page, rows, order, orderType, obj, comisionJS.findByCriteriaCallback);
    },
    /*
     * Callback de la función findByCriteria(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto (Grid) con la información de la 
     * consulta.
     */
    findByCriteriaCallback: function (data) {
        utilGrid.gridUpdate('#comisionGrid', '#comisionGridCurrentPage', '#comisionGridRowsByPage', data);
        utilEffect.hideProgressBar();
    },
    /**
     * Funcion utilizado cuando la pagina es individual
     */
    findFirst: function () {
        utilEffect.showProgressBar();
        ComisionDWR.findFirst(comisionJS.findByIdCallback);
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
        var nombre = jQuery('#comisionform_nombre').val();
        ComisionDWR.isValidoNombre(nombre, comisionJS.isValidoNombreCallback);
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
        if (isValido == false && jQuery('#comisionform_comisionId').val() != 0) {
            comisionJS.doCommit();
            return;
        }

        // Nuevo registro, en este caso se permite el commit
        if (isValido == true && jQuery('#comisionform_comisionId').val() == 0) {
            comisionJS.doCommit();
            return;
        }

        // Registro existente, se le cambio el nombre, en este caso se permite el commit
        if (isValido == true && jQuery('#comisionform_comisionId').val() != 0) {
            comisionJS.doCommit();
            return;
        }

        // Mostrar mensaje de registro duplicado 
        utilDialog.showDialog('#d-registroDuplicadoComision');
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
        ComisionDWR.setEstatus(estatusId, listaObjetos, comisionJS.setEstatusCallback);
    },
    /*
     * Función que cambia el Estatus de los elementos seleccionados en el widget
     * de jqGrid.
     * 
     */
    setEstatusSeleccion: function () {
        utilEffect.showProgressBar();
        var estatusId = comisionJS.estatusId;
        var listaObjetos = utilGrid.gridGetSelectedRows('#comisionGrid');
        ComisionDWR.setEstatus(estatusId, listaObjetos, comisionJS.setEstatusCallback);
    },
    /*
     * Callback de la función setEstatus(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     */
    setEstatusCallback: function () {
        comisionJS.reloadGrid();
        utilEffect.hideProgressBar();
    },
    /*
     * Función que muestra un mensaje de confirmación para Activar o Inactivar los registros
     * seleccionados del widget jQGrid.
     */
    prepareToSetEstatus: function (estatusId) {
        var seleccion = utilGrid.gridGetSelectedRows('#comisionGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedComision');
        } else {
            comisionJS.estatusId = estatusId;
            // utilDialog.showDialog( '#d-confirmSetEstatusComision' );
            comisionJS.setEstatusSeleccion();
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar los registros seleccionados
     * del widget jQGrid.
     */
    prepareToRemoveSelected: function () {
        var seleccion = utilGrid.gridGetSelectedRows('#comisionGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedComision');
        } else {
            utilDialog.showDialog('#d-removeSeleccionComision');
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar un regristro
     * del widget jQGrid.
     */
    prepareToRemove: function (id) {
        comisionJS.setEliminarId(id);
        utilDialog.showDialog('#d-removeComision');
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
        comisionJS.eliminarId = eliminarId;
    },
    /*
     * Getter de la variable eliminarId
     **/
    getEliminarId: function () {
        return comisionJS.eliminarId;
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
        var validation = validanguage.validateForm('comisionform');
        if (validation.result) {
            comisionJS.isValidoNombre();
        }
    },
    /*
     * Función que inicia el proceso para GUARDAR y ACTUALIZAR/MODIFICAR
     * Una vez que se pasaron las validaciones se realiza el commit.
     */
    doCommit: function () {
        if (jQuery('#comisionform_comisionId').val() == 0) {
            comisionJS.save();
        } else {
            comisionJS.update();
        }
    },
    /*
     * Función que es llamada para actualizar la información del grid.
     */
    reloadGrid: function () {
        if (comisionJS.cacheDWR != null) {
            comisionJS.findByCriteria(comisionJS.cacheDWR);
        } else {
            comisionJS.findByCriteria(new ComisionDWR());
        }
    },
    /*
     * Función que obtene las opciones de un elemento select comisionform_clasificacionContraId para recargarlo mediante AJAX.
     */
    getClasificacionContra: function () {
        utilEffect.showProgressBar();
        ComisionDWR.getClasificacionContra(comisionJS.getClasificacionContraCallback);
    },
    /*
     * Callback de la función getClasificacionContra() esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    getClasificacionContraCallback: function (list) {
        var idCmb = jQuery('#comisionform_clasificacionContraId').val();
        jQuery('#comisionform_clasificacionContraId').find('option').remove();
        for (var i = 0; i < list.length; i++) {
            var object = list[i];
            var id = object.clasificacionContraId;
            var nombre = object.nombre;
            jQuery('#comisionform_clasificacionContraId').append('<option value="' + id + '">' + nombre + '</option>');
        }
        // Si el usuario selecciono una opcion en el popup entonces ponerlo como seleccionado
        var sel = parseInt(jQuery('#idFromIframe').val());
        if (sel == 0) {
            // La opcion del popup seleccionada esta inactiva, validar la opcion seleccionada antes de abrir el popup
            if (jQuery("#comisionform_clasificacionContraId option[value='" + idCmb + "']").length > 0) {
                jQuery('#comisionform_clasificacionContraId').val(idCmb);
            } else {
                jQuery('#comisionform_clasificacionContraId').val(0);
            }
        } else {
            // Checar si la seleccion esta activa
            if (jQuery("#comisionform_clasificacionContraId option[value='" + sel + "']").length > 0) {
                jQuery('#comisionform_clasificacionContraId').val(sel);
            }
        }
        utilEffect.hideProgressBar();
    },
    /*
     * Función que obtene las opciones de un elemento select comisionform_subclasificacionContraId para recargarlo mediante AJAX.
     */
    getSubclasificacionContra: function () {
        utilEffect.showProgressBar();
        ComisionDWR.getSubclasificacionContra(comisionJS.getSubclasificacionContraCallback);
    },
    /*
     * Callback de la función getSubclasificacionContra() esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    getSubclasificacionContraCallback: function (list) {
        var idCmb = jQuery('#comisionform_subclasificacionContraId').val();
        jQuery('#comisionform_subclasificacionContraId').find('option').remove();
        for (var i = 0; i < list.length; i++) {
            var object = list[i];
            var id = object.subclasificacionContraId;
            var nombre = object.nombre;
            jQuery('#comisionform_subclasificacionContraId').append('<option value="' + id + '">' + nombre + '</option>');
        }
        // Si el usuario selecciono una opcion en el popup entonces ponerlo como seleccionado
        var sel = parseInt(jQuery('#idFromIframe').val());
        if (sel == 0) {
            // La opcion del popup seleccionada esta inactiva, validar la opcion seleccionada antes de abrir el popup
            if (jQuery("#comisionform_subclasificacionContraId option[value='" + idCmb + "']").length > 0) {
                jQuery('#comisionform_subclasificacionContraId').val(idCmb);
            } else {
                jQuery('#comisionform_subclasificacionContraId').val(0);
            }
        } else {
            // Checar si la seleccion esta activa
            if (jQuery("#comisionform_subclasificacionContraId option[value='" + sel + "']").length > 0) {
                jQuery('#comisionform_subclasificacionContraId').val(sel);
            }
        }
        utilEffect.hideProgressBar();
    },
// funcion para setear tipoValor de catalogo nombre contra
    getNombreContraData: function (nombreContraId) {
        for (var i = 0; i < nombreContraList.length; i++) {
            var object = nombreContraList[i];
            var id = object.nombreContraId;
            var tipoValor = object.tipoValor;
            if (id.toString() === nombreContraId.toString()) {
                jQuery('#comisionform_tipoValor').val(tipoValor);
                var checkboxContra = object.checkboxContra;
                if (checkboxContra !== null) {
                    if (checkboxContra !== 1) {
                        jQuery('#comisionCheck').hide();
                    }
                } else {
                    jQuery('#comisionCheck').show();
                }
            }
        }
    },
    /*
     * Función que obtene las opciones de un elemento select comisionform_nombreContraId para recargarlo mediante AJAX.
     */
    getNombreContra: function () {
        utilEffect.showProgressBar();
        ComisionDWR.getNombreContra(comisionJS.getNombreContraCallback);
    },
    /*
     * Callback de la función getNombreContra() esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    getNombreContraCallback: function (list) {
        nombreContraList = list;
//var idCmb = jQuery( '#comisionform_nombreContraId' ).val();
//jQuery( '#comisionform_nombreContraId' ).find( 'option' ).remove();
//for (var i = 0; i < list.length; i++ ) {
//var object = list[i];
//var id = object.nombreContraId;
//var nombre = object.nombre;
//jQuery( '#comisionform_nombreContraId' ).append('<option value="' + id + '">' + nombre + '</option>');
//}
// // Si el usuario selecciono una opcion en el popup entonces ponerlo como seleccionado
//var sel = parseInt(jQuery( '#idFromIframe' ).val());
//if ( sel == 0 ) {
// // La opcion del popup seleccionada esta inactiva, validar la opcion seleccionada antes de abrir el popup
//if ( jQuery( "#comisionform_nombreContraId option[value='" + idCmb + "']" ).length > 0 ) {
//jQuery( '#comisionform_nombreContraId' ).val( idCmb );
//} else {
//jQuery( '#comisionform_nombreContraId' ).val( 0 );
//}
//} else {
// // Checar si la seleccion esta activa
//if ( jQuery( "#comisionform_nombreContraId option[value='" + sel + "']" ).length > 0 ) {
//jQuery( '#comisionform_nombreContraId' ).val( sel );
//}
//}
//utilEffect.hideProgressBar();
    },
    /*
     * Función que es activa el modal de busqueda.
     */
    openSearch: function () {
        utilSearch.openSearch('#d-searchComision', '#comisionGrid', '#comisionform', comisionJS, new ComisionDWR());
    },
    /** 
     * Filtrar SubclasificacionContra
     * 
     */
    filtrarSubclasificacionContra: function () {
        utilEffect.showProgressBar();
        ComisionDWR.filtrarSubclasificacionContra(
                jQuery('#comisionform_clasificacionContraId').val(),
                comisionJS.filtrarSubclasificacionContraCallback);
    },
    /** 
     * Callback de la función filtrarSubclasificacionContra(), esta
     * función es llamada por DWR al terminar de realizar la consulta.
     */
    filtrarSubclasificacionContraCallback: function (data) {
        if (data == null || data.length == 0) {
            utilInput.removeOptionsSelectAndAddDummy('#comisionform_subclasificacionContraId', '--Seleccione--');
        } else {
            utilInput.populateSelect('#comisionform_subclasificacionContraId', data, 'subclasificacionContraId', 'nombre');
            if (comisionJS.comision !== null) {
                if (comisionJS.comision.subclasificacionContraId !== null) {
                    if (utilInput.existsOptionByValueInSelect('#comisionform_subclasificacionContraId', comisionJS.comision.subclasificacionContraId)) {
                        jQuery('#comisionform_subclasificacionContraId').val(comisionJS.comision.subclasificacionContraId);
                    } else {
                        jQuery('#comisionform_subclasificacionContraId').val(0);
                    }
                }
            }
            jQuery('#comisionform_subclasificacionContraId').trigger('change');
        }
        utilEffect.hideProgressBar();
    },
    /** 
     * Filtrar NombreContra
     * 
     */
//filtrarNombreContra : function(){
//utilEffect.showProgressBar();
//ComisionDWR.filtrarNombreContra(
//jQuery( '#comisionform_subclasificacionContraId' ).val(),
//comisionJS.filtrarNombreContraCallback );},
///** 
// * Callback de la función filtrarNombreContra(), esta
// * función es llamada por DWR al terminar de realizar la consulta.
// */
//filtrarNombreContraCallback : function( data ) {
//if ( data == null || data.length == 0 ) {
//utilInput.removeOptionsSelectAndAddDummy( '#comisionform_nombreContraId', '--Seleccione--' );
//} else {
//utilInput.populateSelect( '#comisionform_nombreContraId', data, 'nombreContraId','nombre' );
//if (comisionJS.comision !== null) {
//if (comisionJS.comision.nombreContraId !== null) {
//if (utilInput.existsOptionByValueInSelect('#comisionform_nombreContraId', comisionJS.comision.nombreContraId)) {
//jQuery('#comisionform_nombreContraId').val(comisionJS.comision.nombreContraId);
//} else {
//jQuery('#comisionform_nombreContraId').val(0);
//}
//}
//}
//jQuery( '#comisionform_nombreContraId' ).trigger( 'change' );
//}
//utilEffect.hideProgressBar();
//},
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
        comisionJS.entity = entity;
        comisionJS.headers = headers;
        comisionJS.format = format;
        comisionJS.reportName = jQuery('#key_comision_title').val() +
                '_' + utilMisc.getTodayDate('-');
        var criteriaExample = new ComisionDWR();
        if (comisionJS.cacheDWR != null) {
            criteriaExample = comisionJS.cacheDWR;
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
        ComisionDWR.getReportDataTest(sortBy, sortType, criteriaExample, comisionJS.exportarDatosCallback);
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
                comisionJS.entity + '&headers=' + comisionJS.headers +
                '&reportName=' + comisionJS.reportName + '&format=' + comisionJS.format +
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
// Validaciones de comisionform
validanguage.el.comisionform_clasificacionContraId = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
validanguage.el.comisionform_nombreClasificacion = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};
validanguage.el.comisionform_subclasificacionContraId = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
validanguage.el.comisionform_nombreSubclasificacion = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};
validanguage.el.comisionform_nombreContraId = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
validanguage.el.comisionform_nombre = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
validanguage.el.comisionform_tipoValor = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};
validanguage.el.comisionform_valor = {
    characters: {
        mode: 'allow', expression: 'numeric.', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter, {
            name: function (text) {
                if (text.length == 0)
                    return true; // Esta linea se pone cuando el campo NO es requerido
                var regex = /^[0-9]{1,13}(?:\.[0-9]{1,3})?$/; // Modificar este regex si es necesario junto con errorMsg
                return regex.test(text);
            },
            errorMsg: 'Numero no válido. Solo se permiten 13 digitos y 3 decimales',
            onerror: utilEffect.showValidationTooltip,
            onsuccess: utilEffect.hideValidationTooltip
        }
    ]}

