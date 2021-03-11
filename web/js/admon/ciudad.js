jQuery(document).ready(function () {

    // Actualizar "Estado" al seleccionar "Pais" en formulario ciudad
    jQuery('#ciudadform_paisId').change(function () {
        ciudadJS.filtrarEstado();
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
    utilInput.fixRadios('#ciudadform');


    // <editor-fold defaultstate="collapsed" desc="Botones">
    // Botón : NUEVO
    jQuery('#btnNewCiudad').click(function () {
        utilForm.reset('#ciudadform');
        // Limpiar campo con dependencias
        utilInput.removeOptionsSelectAndAddDummy('#ciudadform_estadoId', '--Seleccione--');
        jQuery('#ciudadform_ciudadId').val(0);
        ciudadJS.ciudad = null;
    }).customButtonEffect('#btnNewCiudad');
    // Botón : GUARDAR
    jQuery('#btnSaveCiudad').click(function () {
        ciudadJS.prepareToSave();
    }).customButtonEffectBlue('#btnSaveCiudad');
    // </editor-fold>

    if (jQuery('#gridVisibleCiudad').val() == 'true') {
        jQuery('#ciudadGrid').jqGrid({
            url: '',
            datatype: '',
            colNames: [
                jQuery('#ciudad_ciudadIdText').html(),
                jQuery('#ciudad_codeText').html(),
                jQuery('#ciudad_paisIdText').html(),
                jQuery('#ciudad_estadoIdText').html(),
                jQuery('#ciudad_nombreText').html(),
                jQuery('#ciudad_estatusIdText').html(),
                jQuery('#ciudad_eliminadoIdText').html(),
                jQuery('#ciudad_creacionFechaText').html(),
                jQuery('#ciudad_creacionUsuarioText').html(),
                jQuery('#ciudad_modificacionFechaText').html(),
                jQuery('#ciudad_modificacionUsuarioText').html(),
                '', '', ''],
            colModel: [{
                    name: 'ciudadId',
                    index: 'ciudadId',
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
                    name: 'estadoId',
                    index: 'estadoId',
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
            pager: jQuery('#ciudadGridPagerId'),
            caption: jQuery('#key_ciudad_title').val()
        });
        // jQuery UI Dialogs
        utilDialog.setStandardDialog('#d-removeCiudad', 'Confirme', '¿Desea eliminar el registro?', ['Aceptar@ciudadJS.remove()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-removeSeleccionCiudad', 'Confirme', '¿Desea eliminar los registros seleccionados?', ['Aceptar@ciudadJS.removeSelected()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-confirmSetEstatusCiudad', 'Confirme', '¿Desea cambiar el estatus de los registros seleccionados?', ['Aceptar@ciudadJS.setEstatusSeleccion()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-noSelectedCiudad', 'Error', 'Para poder realizar esta operación seleccione uno ó más registros.', ['Aceptar@']);
        utilDialog.setStandardDialog('#d-registroDuplicadoCiudad', 'Advertencia', 'Ya existe un registro con este nombre en la base de datos, ¿Desea crear un nuevo registro con este nombre?', ['Aceptar@ciudadJS.doCommit()', 'Cancelar@']);
        utilDialog.setCustomDialogSelectColumnsToExport("#ciudadColumnasExportar", '#ciudadGrid');
        utilDialog.setCustomDialogSetEstatus('#ciudadSetEstatusActivoInactivo',
                'ciudadJS.prepareToSetEstatus(1)', 'ciudadJS.prepareToSetEstatus(2)');

        // Inicializaciones extra para el grid
        utilGrid.setup('#ciudadGrid', '#ciudadGridPagerId', '#ciudadGridCurrentPage', '#ciudadGridOrderByColumn',
                '#ciudadGridOrderByType', 'ciudadJS');

        // Crear el modal de busqueda
        utilSearch.buildSearch('#d-searchCiudad');

        // Recargar el widget jqGrid
        ciudadJS.reloadGrid();

    }
    // Limpiar la página
    jQuery('#btnNewCiudad').click();
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
    if (jQuery('#gridIndividualModeCiudad').val() == 'true') {
        jQuery('#btnNewCiudad').hide();
        ciudadJS.findFirst();
    }
    jQuery('#grid-action-activar-inactivar').hide().next().hide();
});

var ciudadJS = {
    /* Variable auxiliar para persistir un objeto (resultado de una consulta) 
     * obtenido mediante el callback de la función "findById(...)". Este objeto
     * es necesario para la funcionalidad de selects encadenados pero puede ser
     * utilizado para otros propósitos. El objeto existe solamente mientras se
     * esta editando la información en el formulario. Al presionar el botón
     * Nuevo o Guardar cualquier referencia a éste regresará "null". El 
     * objeto debe utilizarse como modo de "Solo Lectura" (la modificación de 
     * este objeto no se verá reflejada en la base de datos) */
    ciudad: null,
    /*
     * Variable auxiliar para mantener las busquedas del grid al momento de paginar, ordenar, etc.
     */
    cacheDWR: null,
    /*
     * Función que guarda un nuevo registro en la BD.
     */
    save: function () {
        utilEffect.showProgressBar();
        var ciudad = utilObject.buildObject('#ciudadform', new CiudadDWR());
        ciudad.ciudadId = 0;
        var listaCiudads = [ciudad];
        CiudadDWR.save(listaCiudads, ciudadJS.saveOrUpdateCallback);
    },
    /*
     * Función que actualiza un registro existente en la BD.
     */
    update: function () {
        utilEffect.showProgressBar();
        var ciudad = utilObject.buildObject('#ciudadform', new CiudadDWR());
        ciudad.ciudadId = jQuery('#ciudadform_ciudadId').val();
        var listaCiudads = [ciudad];
        CiudadDWR.update(listaCiudads, ciudadJS.saveOrUpdateCallback);
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
        if (jQuery('#gridIndividualModeCiudad').val() == 'false') {
            utilForm.reset('#ciudadform');
            jQuery('#ciudadform_ciudadId').val(0);
            ciudadJS.ciudad = null;
            ciudadJS.reloadGrid();
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
        var id = ciudadJS.getEliminarId();
        var listaIds = [id];
        CiudadDWR.remove(listaIds, ciudadJS.removeCallback);
    },
    /*
     * Función que elimina los registros seleccionados en el grid al presionar el botón eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como parámetro.
     */
    removeSelected: function () {
        utilEffect.showProgressBar();
        var listaIds = utilGrid.gridGetSelectedRows('#ciudadGrid');
        CiudadDWR.remove(listaIds, ciudadJS.removeCallback);
    },
    /*
     * Callback de la función remove(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    removeCallback: function () {
        jQuery('#btnNewCiudad').click();
        ciudadJS.reloadGrid();
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
        CiudadDWR.findById(id, ciudadJS.findByIdCallback);
    },
    /*
     * Callback de la función findById(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto con la información de la consulta.
     */
    findByIdCallback: function (data) {
        utilForm.reset('#ciudadform');
        utilForm.populate('#ciudadform', data);
        ciudadJS.ciudad = data;
        jQuery('#ciudadform_paisId').trigger('change');
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
        ciudadJS.cacheDWR = obj;
        var page = jQuery('#ciudadGridCurrentPage').val();
        var rows = jQuery('#ciudadGridRowsByPage').val();
        var order = jQuery('#ciudadGridOrderByColumn').val();
        var orderType = jQuery('#ciudadGridOrderByType').val();
        utilEffect.showProgressBar();
        CiudadDWR.findByCriteria(page, rows, order, orderType, obj, ciudadJS.findByCriteriaCallback);
    },
    /*
     * Callback de la función findByCriteria(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto (Grid) con la información de la 
     * consulta.
     */
    findByCriteriaCallback: function (data) {
        utilGrid.gridUpdate('#ciudadGrid', '#ciudadGridCurrentPage', '#ciudadGridRowsByPage', data);
        utilEffect.hideProgressBar();
    },
    /**
     * Funcion utilizado cuando la pagina es individual
     */
    findFirst: function () {
        utilEffect.showProgressBar();
        CiudadDWR.findFirst(ciudadJS.findByIdCallback);
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
        var nombre = jQuery('#ciudadform_nombre').val();
        CiudadDWR.isValidoNombre(nombre, ciudadJS.isValidoNombreCallback);
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
        if (isValido == false && jQuery('#ciudadform_ciudadId').val() != 0) {
            ciudadJS.doCommit();
            return;
        }

        // Nuevo registro, en este caso se permite el commit
        if (isValido == true && jQuery('#ciudadform_ciudadId').val() == 0) {
            ciudadJS.doCommit();
            return;
        }

        // Registro existente, se le cambio el nombre, en este caso se permite el commit
        if (isValido == true && jQuery('#ciudadform_ciudadId').val() != 0) {
            ciudadJS.doCommit();
            return;
        }

        // Mostrar mensaje de registro duplicado 
        utilDialog.showDialog('#d-registroDuplicadoCiudad');
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
        CiudadDWR.setEstatus(estatusId, listaObjetos, ciudadJS.setEstatusCallback);
    },
    /*
     * Función que cambia el Estatus de los elementos seleccionados en el widget
     * de jqGrid.
     * 
     */
    setEstatusSeleccion: function () {
        utilEffect.showProgressBar();
        var estatusId = ciudadJS.estatusId;
        var listaObjetos = utilGrid.gridGetSelectedRows('#ciudadGrid');
        CiudadDWR.setEstatus(estatusId, listaObjetos, ciudadJS.setEstatusCallback);
    },
    /*
     * Callback de la función setEstatus(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     */
    setEstatusCallback: function () {
        ciudadJS.reloadGrid();
        utilEffect.hideProgressBar();
    },
    /*
     * Función que muestra un mensaje de confirmación para Activar o Inactivar los registros
     * seleccionados del widget jQGrid.
     */
    prepareToSetEstatus: function (estatusId) {
        var seleccion = utilGrid.gridGetSelectedRows('#ciudadGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedCiudad');
        } else {
            ciudadJS.estatusId = estatusId;
            // utilDialog.showDialog( '#d-confirmSetEstatusCiudad' );
            ciudadJS.setEstatusSeleccion();
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar los registros seleccionados
     * del widget jQGrid.
     */
    prepareToRemoveSelected: function () {
        var seleccion = utilGrid.gridGetSelectedRows('#ciudadGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedCiudad');
        } else {
            utilDialog.showDialog('#d-removeSeleccionCiudad');
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar un regristro
     * del widget jQGrid.
     */
    prepareToRemove: function (id) {
        ciudadJS.setEliminarId(id);
        utilDialog.showDialog('#d-removeCiudad');
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
        ciudadJS.eliminarId = eliminarId;
    },
    /*
     * Getter de la variable eliminarId
     **/
    getEliminarId: function () {
        return ciudadJS.eliminarId;
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
        var validation = validanguage.validateForm('ciudadform');
        if (validation.result) {
            ciudadJS.isValidoNombre();
        }
    },
    /*
     * Función que inicia el proceso para GUARDAR y ACTUALIZAR/MODIFICAR
     * Una vez que se pasaron las validaciones se realiza el commit.
     */
    doCommit: function () {
        if (jQuery('#ciudadform_ciudadId').val() == 0) {
            ciudadJS.save();
        } else {
            ciudadJS.update();
        }
    },
    /*
     * Función que es llamada para actualizar la información del grid.
     */
    reloadGrid: function () {
        if (ciudadJS.cacheDWR != null) {
            ciudadJS.findByCriteria(ciudadJS.cacheDWR);
        } else {
            ciudadJS.findByCriteria(new CiudadDWR());
        }
    },
    /*
     * Función que obtene las opciones de un elemento select ciudadform_paisId para recargarlo mediante AJAX.
     */
    getPais: function () {
        utilEffect.showProgressBar();
        CiudadDWR.getPais(ciudadJS.getPaisCallback);
    },
    /*
     * Callback de la función getPais() esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    getPaisCallback: function (list) {
        var idCmb = jQuery('#ciudadform_paisId').val();
        jQuery('#ciudadform_paisId').find('option').remove();
        for (var i = 0; i < list.length; i++) {
            var object = list[i];
            var id = object.paisId;
            var nombre = object.nombre;
            jQuery('#ciudadform_paisId').append('<option value="' + id + '">' + nombre + '</option>');
        }
        // Si el usuario selecciono una opcion en el popup entonces ponerlo como seleccionado
        var sel = parseInt(jQuery('#idFromIframe').val());
        if (sel == 0) {
            // La opcion del popup seleccionada esta inactiva, validar la opcion seleccionada antes de abrir el popup
            if (jQuery("#ciudadform_paisId option[value='" + idCmb + "']").length > 0) {
                jQuery('#ciudadform_paisId').val(idCmb);
            } else {
                jQuery('#ciudadform_paisId').val(0);
            }
        } else {
            // Checar si la seleccion esta activa
            if (jQuery("#ciudadform_paisId option[value='" + sel + "']").length > 0) {
                jQuery('#ciudadform_paisId').val(sel);
            }
        }
        utilEffect.hideProgressBar();
    },
    /*
     * Función que obtene las opciones de un elemento select ciudadform_estadoId para recargarlo mediante AJAX.
     */
    getEstado: function () {
        utilEffect.showProgressBar();
        CiudadDWR.getEstado(ciudadJS.getEstadoCallback);
    },
    /*
     * Callback de la función getEstado() esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    getEstadoCallback: function (list) {
        var idCmb = jQuery('#ciudadform_estadoId').val();
        jQuery('#ciudadform_estadoId').find('option').remove();
        for (var i = 0; i < list.length; i++) {
            var object = list[i];
            var id = object.estadoId;
            var nombre = object.nombre;
            jQuery('#ciudadform_estadoId').append('<option value="' + id + '">' + nombre + '</option>');
        }
        // Si el usuario selecciono una opcion en el popup entonces ponerlo como seleccionado
        var sel = parseInt(jQuery('#idFromIframe').val());
        if (sel == 0) {
            // La opcion del popup seleccionada esta inactiva, validar la opcion seleccionada antes de abrir el popup
            if (jQuery("#ciudadform_estadoId option[value='" + idCmb + "']").length > 0) {
                jQuery('#ciudadform_estadoId').val(idCmb);
            } else {
                jQuery('#ciudadform_estadoId').val(0);
            }
        } else {
            // Checar si la seleccion esta activa
            if (jQuery("#ciudadform_estadoId option[value='" + sel + "']").length > 0) {
                jQuery('#ciudadform_estadoId').val(sel);
            }
        }
        utilEffect.hideProgressBar();
    },
    /*
     * Función que es activa el modal de busqueda.
     */
    openSearch: function () {
        utilSearch.openSearch('#d-searchCiudad', '#ciudadGrid', '#ciudadform', ciudadJS, new CiudadDWR());
    },
    /** 
     * Filtrar Estado
     * 
     */
    filtrarEstado: function () {
        utilEffect.showProgressBar();
        CiudadDWR.filtrarEstado(
                jQuery('#ciudadform_paisId').val(),
                ciudadJS.filtrarEstadoCallback);
    },
    /** 
     * Callback de la función filtrarEstado(), esta
     * función es llamada por DWR al terminar de realizar la consulta.
     */
    filtrarEstadoCallback: function (data) {
        if (data == null || data.length == 0) {
            utilInput.removeOptionsSelectAndAddDummy('#ciudadform_estadoId', '--Seleccione--');
        } else {
            utilInput.populateSelect('#ciudadform_estadoId', data, 'estadoId', 'nombre');
            if (ciudadJS.ciudad !== null) {
                if (ciudadJS.ciudad.estadoId !== null) {
                    if (utilInput.existsOptionByValueInSelect('#ciudadform_estadoId', ciudadJS.ciudad.estadoId)) {
                        jQuery('#ciudadform_estadoId').val(ciudadJS.ciudad.estadoId);
                    } else {
                        jQuery('#ciudadform_estadoId').val(0);
                    }
                }
            }
            jQuery('#ciudadform_estadoId').trigger('change');
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
        ciudadJS.entity = entity;
        ciudadJS.headers = headers;
        ciudadJS.format = format;
        ciudadJS.reportName = jQuery('#key_ciudad_title').val() +
                '_' + utilMisc.getTodayDate('-');
        var criteriaExample = new CiudadDWR();
        if (ciudadJS.cacheDWR != null) {
            criteriaExample = ciudadJS.cacheDWR;
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
        CiudadDWR.getReportDataTest(sortBy, sortType, criteriaExample, ciudadJS.exportarDatosCallback);
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
                ciudadJS.entity + '&headers=' + ciudadJS.headers +
                '&reportName=' + ciudadJS.reportName + '&format=' + ciudadJS.format +
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
// Validaciones de ciudadform
validanguage.el.ciudadform_code = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};
validanguage.el.ciudadform_paisId = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
validanguage.el.ciudadform_estadoId = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
validanguage.el.ciudadform_nombre = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};

