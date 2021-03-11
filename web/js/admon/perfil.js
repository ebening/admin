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
    utilInput.fixRadios('#perfilform');


    // <editor-fold defaultstate="collapsed" desc="Botones">
    // Botón : NUEVO
    jQuery('#btnNewPerfil').click(function () {
        utilForm.reset('#perfilform');
        jQuery('#perfilform_perfilId').val(0);
        // Limpiar el formulario y grid de perfilPagina
        perfilPaginaJS.reset();
        perfilJS.perfil = null;
    }).customButtonEffect('#btnNewPerfil');
    // Botón : GUARDAR
    jQuery('#btnSavePerfil').click(function () {
        perfilJS.prepareToSave();
    }).customButtonEffectBlue('#btnSavePerfil');
    // Botón AGREGAR (formulario perfilPagina)
    jQuery('#btnAddPerfilPagina').click(function () {
        perfilPaginaJS.prepareToAgregar();
    });
    // Botón AGREGAR -Sin validación- (formulario perfilPagina)
    jQuery('#btnAddPerfilPaginaNoValidation').click(function () {
        perfilPaginaJS.agregar();
    });
    // </editor-fold>

    if (jQuery('#gridVisiblePerfil').val() == 'true') {
        jQuery('#perfilGrid').jqGrid({
            url: '',
            datatype: '',
            colNames: [
                jQuery('#perfil_perfilIdText').html(),
                jQuery('#perfil_nombreText').html(),
                jQuery('#perfil_estatusIdText').html(),
                jQuery('#perfil_eliminadoIdText').html(),
                jQuery('#perfil_creacionFechaText').html(),
                jQuery('#perfil_creacionUsuarioText').html(),
                jQuery('#perfil_modificacionFechaText').html(),
                jQuery('#perfil_modificacionUsuarioText').html(),
                '', '', ''],
            colModel: [{
                    name: 'perfilId',
                    index: 'perfilId',
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
            pager: jQuery('#perfilGridPagerId'),
            caption: jQuery('#key_perfil_title').val()
        });
        // jQuery UI Dialogs
        utilDialog.setStandardDialog('#d-removePerfil', 'Confirme', '¿Desea eliminar el registro?', ['Aceptar@perfilJS.remove()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-removeSeleccionPerfil', 'Confirme', '¿Desea eliminar los registros seleccionados?', ['Aceptar@perfilJS.removeSelected()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-confirmSetEstatusPerfil', 'Confirme', '¿Desea cambiar el estatus de los registros seleccionados?', ['Aceptar@perfilJS.setEstatusSeleccion()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-noSelectedPerfil', 'Error', 'Para poder realizar esta operación seleccione uno ó más registros.', ['Aceptar@']);
        utilDialog.setStandardDialog('#d-confirma-eliminar-perfilpagina', 'Aviso', '¿Confirma eliminar la información del registro?', ['Aceptar@perfilPaginaJS.remove();', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-registroDuplicadoPerfil', 'Advertencia', 'Ya existe un registro con este nombre en la base de datos, ¿Desea crear un nuevo registro con este nombre?', ['Aceptar@perfilJS.doCommit()', 'Cancelar@']);
        utilDialog.setCustomDialogSelectColumnsToExport("#perfilColumnasExportar", '#perfilGrid');
        utilDialog.setCustomDialogSetEstatus('#perfilSetEstatusActivoInactivo',
                'perfilJS.prepareToSetEstatus(1)', 'perfilJS.prepareToSetEstatus(2)');

        // Inicializaciones extra para el grid
        utilGrid.setup('#perfilGrid', '#perfilGridPagerId', '#perfilGridCurrentPage', '#perfilGridOrderByColumn',
                '#perfilGridOrderByType', 'perfilJS');

        // Crear el modal de busqueda
        utilSearch.buildSearch('#d-searchPerfil');

        // Recargar el widget jqGrid
        perfilJS.reloadGrid();

    }
    // Grid perfilPagina embebido
    if (jQuery('#gridVisiblePerfilPagina').val() == 'true') {
        jQuery('#perfilPaginaGrid').jqGrid({
            url: '',
            datatype: '',
            colNames: [
                'Fila Id',
                jQuery('#perfilPagina_perfilPaginaIdText').html(),
                jQuery('#perfilPagina_perfilIdText').html(),
                jQuery('#perfilPagina_paginaIdText').html(),
                jQuery('#perfilPagina_paginaIdText').html(),
                jQuery('#perfilPagina_estatusIdText').html(),
                jQuery('#perfilPagina_eliminadoIdText').html(),
                jQuery('#perfilPagina_creacionFechaText').html(),
                jQuery('#perfilPagina_creacionUsuarioText').html(),
                jQuery('#perfilPagina_modificacionFechaText').html(),
                jQuery('#perfilPagina_modificacionUsuarioText').html(),
                '', ''],
            colModel: [{
                    name: 'filaId',
                    index: 'filaId',
                    hidden: true
                },
                {
                    name: 'perfilPaginaId',
                    index: 'perfilPaginaId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'perfilId',
                    index: 'perfilId',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: false

                }, {
                    name: 'paginaId',
                    index: 'paginaId',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: false

                }, {
                    name: 'pagina',
                    index: 'pagina',
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
                    hidden: true,
                    search: false
                }, {
                    name: 'modificacionUsuario',
                    index: 'modificacionUsuario',
                    align: 'center',
                    resizable: false,
                    width: 55,
                    hidden: true,
                    search: false
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
            hidegrid: false,
            multiselect: false,
            viewrecords: false,
            pager: jQuery('#perfilPaginaGridPagerId'),
            caption: jQuery('#key_perfilpagina_title').val()
        });
        jQuery('#perfilPaginaGridPagerId').hide();
        jQuery('#perfilPaginaGrid').setGridWidth(1174);
    }
    // Limpiar la página
    jQuery('#btnNewPerfil').click();
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
    if (jQuery('#gridIndividualModePerfil').val() == 'true') {
        jQuery('#btnNewPerfil').hide();
        perfilJS.findFirst();
    }
});

// Objeto auxiliar para la funcionalidad de desglose.
var desglosePerfilPaginaJS = {
    desglose: function (selectIdList) {
        desglosePerfilPaginaJS.cleanDesglose();
        utilForm.desglose(selectIdList);
    },
    cleanDesglose: function () {
        utilForm.cleanDesglose("#perfilPaginaform");
    }
};
var perfilPaginaJS = {
    isDesglose: false,
    filaId: null,
    perfilPagina: null,
    /**
     * Limpia el formulario.
     */
    clean: function () {
        utilForm.reset('#perfilPaginaform');
        jQuery('#perfilPaginaform_filaId').val(0);
        jQuery('#perfilPaginaform_perfilPaginaId').val(0);
        perfilPaginaJS.perfilPagina = null;
        perfilPaginaJS.perfilPaginaList = null;
    },
    /**
     * Limpia el formulario y el grid de perfilPagina, usado cuando el
     * usuario presiona el botón Nuevo.
     */
    reset: function () {
        perfilPaginaJS.clean();
        jQuery('#perfilPaginaGrid').clearGridData();
    },
    /** 
     * Valida los campos del formulario de perfilPagina antes de
     * agregarlos al grid.
     */
    prepareToAgregar: function () {
        var validation = validanguage.validateForm('perfilPaginaform');
        if (validation.result) {
            perfilPaginaJS.agregar();
            perfilPaginaJS.clean();
        }
    },
    /** 
     * Función llamada cuando se presiona el botón Agregar en el formulario de 
     * perfilPagina.
     */
    agregar: function () {
        // Formar el objeto con los datos del formulario
        var perfilPagina = utilObject.buildObject(
                '#perfilPaginaform', new PerfilPaginaDWR());
        // Valdar si se esta agregando uno nuevo o uno editado
        if (jQuery('#perfilPaginaform_filaId') == 0) {
            perfilPaginaJS.agregarNuevo(perfilPagina);
        } else {
            perfilPaginaJS.agregarEditado(perfilPagina);
        }
    },
    /* Agrega un registro editado al grid de perfilPagina */
    agregarEditado: function (object) {
        object.filaId = jQuery('#perfilPaginaform_filaId').val();
        jQuery('#perfilPaginaGrid').delRowData(object.filaId);
        perfilPaginaJS.agregarNuevo(object);
    },
    /**
     * Agrega un nuevo registro al grid de perfilPagina, de manera 
     * predeterminada la fila se agrega al final de las existentes.
     * 
     * @param object, es un objeto PerfilPagina con la información a
     * agregar.
     */
    agregarNuevo: function (object) {
        var filaId = perfilPaginaJS.findFilaIdMax() + 1;
        // Editar
        var editar_css = 'ui-icon ui-icon-pencil ui-icon-center';
        var editar_title = 'Editar';
        var editar_onclick = 'perfilPaginaJS.findByFilaId(' + filaId + ')';
        // Eliminar
        var eliminar_css = 'ui-icon ui-icon-trash ui-icon-center';
        var eliminar_title = 'Eliminar';
        var eliminar_onclick = 'perfilPaginaJS.prepareToRemove(' + filaId + ')';
        object.Edit = "<span class = '" + editar_css +
                "' title = '" + editar_title +
                "' style = 'cursor:pointer' onclick = '" + editar_onclick + "'></span>";
        object.Remove = "<span class = '" + eliminar_css +
                "' title = '" + eliminar_title +
                "' style = 'cursor:pointer' onclick = '" + eliminar_onclick + "'></span>";

        // Numero de Fila
        object.filaId = filaId;
        // Limpiar campos si es nuevo
        if (object.perfilPaginaId == 0) {
            utilObject.cleanCamposControl(object);
            utilObject.resolveDescription('#perfilPaginaform', object);
        }
        // Parsear fechas
        var propiedades = utilObject.getProperties(object);
        for (var i = 0; i < propiedades.length; i++) {
            var propiedad = propiedades[ i ] + '';
            if (propiedad.indexOf('fecha') >= 0) {
                if (object[ propiedad ] != null) {
                    object[ propiedad ] = jQuery.datepicker.formatDate(
                            'dd/mm/yy', new Date(object[ propiedad ])
                            );
                }
            }
        }
        // Enviar los datos al grid
        jQuery('#perfilPaginaGrid').addRowData(filaId, object);
    },
    /**
     * Obtiene el numero de fila mas alto en el grid.
     * 
     * @return regresa un entero igual al numero de la fila mas alto.
     */
    findFilaIdMax: function () {
        var filaIdMax = 0;
        var filaList = jQuery('#perfilPaginaGrid').getRowData();
        for (var i = 0; i < filaList.length; i++) {
            if (parseInt(filaList[i].filaId) > filaIdMax) {
                filaIdMax = parseInt(filaList[i].filaId);
            }
        }
        return parseInt(filaIdMax);
    },
    /** 
     * Función llamada al presionar el botón Editar en el registro de 
     * perfilPagina. Obtiene un objeto desde el grid mediante el numero de la
     * fila, el objeto es parseado para llenar los campos en el formulario.
     * 
     * @param filaId, es el numero de la fila.
     */
    /* Función llamada al presionar el botón Editar en el registro de perfilPagina*/
    findByFilaId: function (filaId) {
        var filas = jQuery('#perfilPaginaGrid').getRowData();
        for (var i = 0; i < filas.length; i++) {
            if (filas[ i ].filaId == filaId) {
                perfilPaginaJS.clean();
                perfilPaginaJS.perfilPagina = filas[i];
                utilForm.populate('#perfilPaginaform', filas[ i ]);
                break;
            }
        }
    },
    /** 
     * Función llamada al presionar el botón Eliminar en el registro de
     * perfilPagina.
     * 
     * @param filaId, es el numero de la fila.
     */
    prepareToRemove: function (filaId) {
        perfilPaginaJS.filaId = filaId;
        utilDialog.showDialog('#d-confirma-eliminar-perfilpagina');
    },
    /** 
     * Función que elimina una fila del grid perfilpagina, el numero de 
     * fila a eliminar es el que se encuentra almacenado en la variale
     * perfilpaginaJS.filaId.
     */
    remove: function () {
        jQuery('#perfilPaginaGrid').delRowData(perfilPaginaJS.filaId);
        perfilPaginaJS.clean();
    },

    /** 
     * Guarda los registros del detalle.
     * 
     * @param perfilId, es el id de perfil.
     */
    save: function (perfilId) {
        var dwrObjectList = new Array();
        // Funcionalidad desglose
        if (perfilPaginaJS.isDesglose) {
            jQuery('form[name="perfilPaginaform"]').each(function () {
                if (jQuery(this).hasClass('base-desglose') == false) {
                    // Construir el objeto
                    var obj = utilObject.buildObject('#' +
                            jQuery(this).attr('id'), new PerfilPaginaDWR());
                    obj.perfilId = perfilId;
                    dwrObjectList.push(obj);
                }
            });
        } else {
            dwrObjectList = utilObject.buildDWRObjectListByGridId(
                    '#perfilPaginaGrid', new PerfilPaginaDWR());
            for (var i = 0; i < dwrObjectList.length; i++) {
                dwrObjectList[ i ].perfilId = perfilId;
            }
        }
        PerfilDWR.savePerfilPagina(perfilId,
                dwrObjectList, perfilPaginaJS.saveCallback);
    },
    /* Callback de la función save(), esta función es llamada por DWR al terminar
     * de realizar la operación */
    saveCallback: function () {
    },
    /*
     * Función que obtene las opciones de un elemento select perfilPaginaform_paginaId para recargarlo mediante AJAX.
     */
    getPagina: function () {
        utilEffect.showProgressBar();
        PerfilDWR.getPerfilPaginaPagina(perfilPaginaJS.getPaginaCallback);
    },
    /*
     * Callback de la función getPagina() esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    getPaginaCallback: function (list) {
        var idCmb = jQuery('#perfilPaginaform_paginaId').val();
        jQuery('#perfilPaginaform_paginaId').find('option').remove();
        for (var i = 0; i < list.length; i++) {
            var object = list[i];
            var id = object.paginaId;
            var nombre = object.nombre;
            jQuery('#perfilPaginaform_paginaId').append('<option value="' + id + '">' + nombre + '</option>');
        }
        // Si el usuario selecciono una opcion en el popup entonces ponerlo como seleccionado
        var sel = parseInt(jQuery('#idFromIframe').val());
        if (sel == 0) {
            // La opcion del popup seleccionada esta inactiva, validar la opcion seleccionada antes de abrir el popup
            if (jQuery("#perfilPaginaform_paginaId option[value='" + idCmb + "']").length > 0) {
                jQuery('#perfilPaginaform_paginaId').val(idCmb);
            } else {
                jQuery('#perfilPaginaform_paginaId').val(0);
            }
        } else {
            // Checar si la seleccion esta activa
            if (jQuery("#perfilPaginaform_paginaId option[value='" + sel + "']").length > 0) {
                jQuery('#perfilPaginaform_paginaId').val(sel);
            }
        }
        utilEffect.hideProgressBar();
    },
    /* Función que obtiene perfilPagina */
    findPerfilPaginaByIntProperty: function (perfilId) {
        PerfilDWR.findPerfilPaginaByIntProperty("perfilId", perfilId,
                perfilPaginaJS.findPerfilPaginaByIntPropertyCallback);
    },
    /**
     * Callback de la función findPerfilPaginaByIntProperty(), esta función es llamada por DWR al terminar
     * de realizar la operación 
     * 
     * @param data, es una lista de objetos PerfilPagina que serán
     * insertados en el grid
     */
    findPerfilPaginaByIntPropertyCallback: function (data) {
        perfilPaginaJS.reset();
        if (data != null) {
            for (var i = 0; i < data.length; i++) {
                perfilPaginaJS.agregarNuevo(data[ i ]);
            }

        }
        utilEffect.hideProgressBar();
    },
    deleteMe: function () {
        // do nothing
    }
}
var perfilJS = {
    /* Variable auxiliar para persistir un objeto (resultado de una consulta) 
     * obtenido mediante el callback de la función "findById(...)". Este objeto
     * es necesario para la funcionalidad de selects encadenados pero puede ser
     * utilizado para otros propósitos. El objeto existe solamente mientras se
     * esta editando la información en el formulario. Al presionar el botón
     * Nuevo o Guardar cualquier referencia a éste regresará "null". El 
     * objeto debe utilizarse como modo de "Solo Lectura" (la modificación de 
     * este objeto no se verá reflejada en la base de datos) */
    perfil: null,
    /*
     * Variable auxiliar para mantener las busquedas del grid al momento de paginar, ordenar, etc.
     */
    cacheDWR: null,
    /*
     * Función que guarda un nuevo registro en la BD.
     */
    save: function () {
        utilEffect.showProgressBar();
        var perfil = utilObject.buildObject('#perfilform', new PerfilDWR());
        perfil.perfilId = 0;
        var listaPerfils = [perfil];
        PerfilDWR.save(listaPerfils, perfilJS.saveOrUpdateCallback);
    },
    /*
     * Función que actualiza un registro existente en la BD.
     */
    update: function () {
        utilEffect.showProgressBar();
        var perfil = utilObject.buildObject('#perfilform', new PerfilDWR());
        perfil.perfilId = jQuery('#perfilform_perfilId').val();
        var listaPerfils = [perfil];
        PerfilDWR.update(listaPerfils, perfilJS.saveOrUpdateCallback);
    },
    /*
     * Callback de la funcion save() y update(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param savedIds (array), lista de id's de objetos que
     * se guardaron en base de datos.
     */
    saveOrUpdateCallback: function (savedIds) {
        perfilPaginaJS.save(savedIds[ 0 ]);
        jQuery('#btnNewPerfil').click();
        perfilJS.perfil = null;
        perfilJS.reloadGrid();
    },
    /*
     * Función que elimina un registro en el grid al presionar el botón eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como parámetro.
     */
    remove: function () {
        utilEffect.showProgressBar();
        var id = perfilJS.getEliminarId();
        var listaIds = [id];
        PerfilDWR.remove(listaIds, perfilJS.removeCallback);
    },
    /*
     * Función que elimina los registros seleccionados en el grid al presionar el botón eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como parámetro.
     */
    removeSelected: function () {
        utilEffect.showProgressBar();
        var listaIds = utilGrid.gridGetSelectedRows('#perfilGrid');
        PerfilDWR.remove(listaIds, perfilJS.removeCallback);
    },
    /*
     * Callback de la función remove(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    removeCallback: function () {
        jQuery('#btnNewPerfil').click();
        perfilJS.reloadGrid();
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
        PerfilDWR.findById(id, perfilJS.findByIdCallback);
    },
    /*
     * Callback de la función findById(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto con la información de la consulta.
     */
    findByIdCallback: function (data) {
        utilForm.reset('#perfilform');
        utilForm.populate('#perfilform', data);
        perfilJS.perfil = data;
        perfilPaginaJS.findPerfilPaginaByIntProperty(
                jQuery('#perfilform_perfilId').val());
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
        perfilJS.cacheDWR = obj;
        var page = jQuery('#perfilGridCurrentPage').val();
        var rows = jQuery('#perfilGridRowsByPage').val();
        var order = jQuery('#perfilGridOrderByColumn').val();
        var orderType = jQuery('#perfilGridOrderByType').val();
        utilEffect.showProgressBar();
        PerfilDWR.findByCriteria(page, rows, order, orderType, obj, perfilJS.findByCriteriaCallback);
    },
    /*
     * Callback de la función findByCriteria(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto (Grid) con la información de la 
     * consulta.
     */
    findByCriteriaCallback: function (data) {
        utilGrid.gridUpdate('#perfilGrid', '#perfilGridCurrentPage', '#perfilGridRowsByPage', data);
        utilEffect.hideProgressBar();
    },
    /**
     * Funcion utilizado cuando la pagina es individual
     */
    findFirst: function () {
        utilEffect.showProgressBar();
        PerfilDWR.findFirst(perfilJS.findByIdCallback);
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
        var nombre = jQuery('#perfilform_nombre').val();
        PerfilDWR.isValidoNombre(nombre, perfilJS.isValidoNombreCallback);
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
        if (isValido == false && jQuery('#perfilform_perfilId').val() != 0) {
            perfilJS.doCommit();
            return;
        }

        // Nuevo registro, en este caso se permite el commit
        if (isValido == true && jQuery('#perfilform_perfilId').val() == 0) {
            perfilJS.doCommit();
            return;
        }

        // Registro existente, se le cambio el nombre, en este caso se permite el commit
        if (isValido == true && jQuery('#perfilform_perfilId').val() != 0) {
            perfilJS.doCommit();
            return;
        }

        // Mostrar mensaje de registro duplicado 
        utilDialog.showDialog('#d-registroDuplicadoPerfil');
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
        PerfilDWR.setEstatus(estatusId, listaObjetos, perfilJS.setEstatusCallback);
    },
    /*
     * Función que cambia el Estatus de los elementos seleccionados en el widget
     * de jqGrid.
     * 
     */
    setEstatusSeleccion: function () {
        utilEffect.showProgressBar();
        var estatusId = perfilJS.estatusId;
        var listaObjetos = utilGrid.gridGetSelectedRows('#perfilGrid');
        PerfilDWR.setEstatus(estatusId, listaObjetos, perfilJS.setEstatusCallback);
    },
    /*
     * Callback de la función setEstatus(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     */
    setEstatusCallback: function () {
        perfilJS.reloadGrid();
        utilEffect.hideProgressBar();
    },
    /*
     * Función que muestra un mensaje de confirmación para Activar o Inactivar los registros
     * seleccionados del widget jQGrid.
     */
    prepareToSetEstatus: function (estatusId) {
        var seleccion = utilGrid.gridGetSelectedRows('#perfilGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedPerfil');
        } else {
            perfilJS.estatusId = estatusId;
            // utilDialog.showDialog( '#d-confirmSetEstatusPerfil' );
            perfilJS.setEstatusSeleccion();
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar los registros seleccionados
     * del widget jQGrid.
     */
    prepareToRemoveSelected: function () {
        var seleccion = utilGrid.gridGetSelectedRows('#perfilGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedPerfil');
        } else {
            utilDialog.showDialog('#d-removeSeleccionPerfil');
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar un regristro
     * del widget jQGrid.
     */
    prepareToRemove: function (id) {
        perfilJS.setEliminarId(id);
        utilDialog.showDialog('#d-removePerfil');
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
        perfilJS.eliminarId = eliminarId;
    },
    /*
     * Getter de la variable eliminarId
     **/
    getEliminarId: function () {
        return perfilJS.eliminarId;
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
        var validation = validanguage.validateForm('perfilform');
        if (validation.result) {
            perfilJS.isValidoNombre();
        }
    },
    /*
     * Función que inicia el proceso para GUARDAR y ACTUALIZAR/MODIFICAR
     * Una vez que se pasaron las validaciones se realiza el commit.
     */
    doCommit: function () {
        if (jQuery('#perfilform_perfilId').val() == 0) {
            perfilJS.save();
        } else {
            perfilJS.update();
        }
    },
    /*
     * Función que es llamada para actualizar la información del grid.
     */
    reloadGrid: function () {
        if (perfilJS.cacheDWR != null) {
            perfilJS.findByCriteria(perfilJS.cacheDWR);
        } else {
            perfilJS.findByCriteria(new PerfilDWR());
        }
    },
    /*
     * Función que es activa el modal de busqueda.
     */
    openSearch: function () {
        utilSearch.openSearch('#d-searchPerfil', '#perfilGrid', '#perfilform', perfilJS, new PerfilDWR());
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
        perfilJS.entity = entity;
        perfilJS.headers = headers;
        perfilJS.format = format;
        perfilJS.reportName = jQuery('#key_perfil_title').val() +
                '_' + utilMisc.getTodayDate('-');
        var criteriaExample = new PerfilDWR();
        if (perfilJS.cacheDWR != null) {
            criteriaExample = perfilJS.cacheDWR;
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
        PerfilDWR.getReportDataTest(sortBy, sortType, criteriaExample, perfilJS.exportarDatosCallback);
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
                perfilJS.entity + '&headers=' + perfilJS.headers +
                '&reportName=' + perfilJS.reportName + '&format=' + perfilJS.format +
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
// Validaciones de perfilform
validanguage.el.perfilform_nombre = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
// Validaciones de perfilPaginaform
validanguage.el.perfilPaginaform_perfilId = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
validanguage.el.perfilPaginaform_paginaId = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};

