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
    utilInput.fixRadios('#perfilPaginaform');
    // Configuraciones adicionales
    jQuery.jstree._themes = '../css/jstreethemes/';
    jQuery('.paginas').hide();

    // Iniciar la construccion del jstree
    perfilPaginaJS.obtienePaginas();

    // Limpiar al cambiar de perfil
    jQuery('#perfilPaginaform_perfilId').change(function () {
        jQuery('.accesos').jstree('uncheck_all');
        perfilPaginaJS.obtieneAccesos();
    });


    // <editor-fold defaultstate="collapsed" desc="Botones">
    // Botón : NUEVO
    jQuery('#btnNewPerfilPagina').click(function () {
        utilForm.reset('#perfilPaginaform');
        jQuery('#perfilPaginaform_perfilPaginaId').val(0);
        perfilPaginaJS.perfilPagina = null;
    }).customButtonEffect('#btnNewPerfilPagina');
    // Botón : GUARDAR
    jQuery('#btnSavePerfilPagina').click(function () {
        perfilPaginaJS.prepareToSave();
    }).customButtonEffectBlue('#btnSavePerfilPagina');
    // </editor-fold>

//if ( jQuery( '#gridVisiblePerfilPagina' ).val() == 'true' ) {
//jQuery( '#perfilPaginaGrid' ).jqGrid({
//url      : '',
//datatype : '',
//colNames: [
//jQuery( '#perfilPagina_perfilPaginaIdText' ).html(),
//jQuery( '#perfilPagina_perfilIdText' ).html(),
//jQuery( '#perfilPagina_paginaIdText' ).html(),
//jQuery( '#perfilPagina_estatusIdText' ).html(),
//jQuery( '#perfilPagina_eliminadoIdText' ).html(),
//jQuery( '#perfilPagina_creacionFechaText' ).html(),
//jQuery( '#perfilPagina_creacionUsuarioText' ).html(),
//jQuery( '#perfilPagina_modificacionFechaText' ).html(),
//jQuery( '#perfilPagina_modificacionUsuarioText' ).html(),
//'','',''],
//colModel : [{
//name      : 'perfilPaginaId',
//index     : 'perfilPaginaId',
//align     : 'center',
//resizable : false,
//hidden    : true,
//search    : false
//},{
//name      : 'perfilId',
//index     : 'perfilId',
//align     : 'center',
//resizable : false,
//hidden    : false,
//search    : true
//},{
//name      : 'paginaId',
//index     : 'paginaId',
//align     : 'center',
//resizable : false,
//hidden    : false,
//search    : true
//},{
//name      : 'estatusId',
//index     : 'estatusId',
//align     : 'center',
//resizable : false,
//hidden    : true,
//search    : true
//},{
//name      : 'eliminadoId',
//index     : 'eliminadoId',
//align     : 'center',
//resizable : false,
//hidden    : true,
//search    : false
//},{
//name      : 'creacionFecha',
//index     : 'creacionFecha',
//align     : 'center',
//resizable : false,
//hidden    : true,
//search    : false
//},{
//name      : 'creacionUsuario',
//index     : 'creacionUsuario',
//align     : 'center',
//resizable : false,
//hidden    : true,
//search    : false
//},{
//name      : 'modificacionFecha',
//index     : 'modificacionFecha',
//align     : 'center',
//resizable : false,
//hidden    : false,
//search    : true
//},{
//name      : 'modificacionUsuario',
//index     : 'modificacionUsuario',
//align     : 'center',
//resizable : false,
//width     : 55,
//hidden    : false,
//search    : false
//},{
//name      : 'State',
//index     : 'State',
//align     : 'center',
//width     : 20,
//sortable  : false,
//search    : false,
//resizable : false
//},{
//name      : 'Edit',
//index     : 'Edit',
//align     : 'center',
//width     : 20,
//sortable  : false,
//search    : false,
//resizable : false
//},{
//name      : 'Remove',
//index     : 'Remove',
//align     : 'center',
//width     : 20,
//sortable  : false,
//search    : false,
//resizable : false
//}],
//height        : 230,
//toolbar       : false,
//hidegrid      : true,
//multiselect   : true,
//viewrecords   : true,
//rowList       : [10,20,30,50,100],
//pager         : jQuery( '#perfilPaginaGridPagerId' ),
//caption       : jQuery( '#key_perfilpagina_title' ).val()
//});
// jQuery UI Dialogs
//utilDialog.setStandardDialog( '#d-removePerfilPagina', 'Confirme', '¿Desea eliminar el registro?', ['Aceptar@perfilPaginaJS.remove()', 'Cancelar@'] );
//utilDialog.setStandardDialog( '#d-removeSeleccionPerfilPagina', 'Confirme', '¿Desea eliminar los registros seleccionados?', ['Aceptar@perfilPaginaJS.removeSelected()', 'Cancelar@'] );
//utilDialog.setStandardDialog( '#d-confirmSetEstatusPerfilPagina', 'Confirme', '¿Desea cambiar el estatus de los registros seleccionados?', ['Aceptar@perfilPaginaJS.setEstatusSeleccion()', 'Cancelar@'] );
//utilDialog.setStandardDialog( '#d-noSelectedPerfilPagina', 'Error', 'Para poder realizar esta operación seleccione uno ó más registros.', ['Aceptar@'] );
//utilDialog.setCustomDialogSelectColumnsToExport("#perfilPaginaColumnasExportar", '#perfilPaginaGrid');
//utilDialog.setCustomDialogSetEstatus('#perfilPaginaSetEstatusActivoInactivo', 
//'perfilPaginaJS.prepareToSetEstatus(1)', 'perfilPaginaJS.prepareToSetEstatus(2)');
    utilDialog.setStandardDialog('#d-noHayAccesos',
            jQuery('#key_usuariopagina_nohayaccesostitulo').val(),
            jQuery('#key_usuariopagina_nohayaccesosmensaje').val(), ['Aceptar@']);
//
// // Inicializaciones extra para el grid
//utilGrid.setup( '#perfilPaginaGrid', '#perfilPaginaGridPagerId', '#perfilPaginaGridCurrentPage', '#perfilPaginaGridOrderByColumn',
//'#perfilPaginaGridOrderByType', 'perfilPaginaJS' );
//
// // Crear el modal de busqueda
//utilSearch.buildSearch( '#d-searchPerfilPagina' );
//
// // Recargar el widget jqGrid
//perfilPaginaJS.reloadGrid();
//
//}
    // Limpiar la página
    jQuery('#btnNewPerfilPagina').click();
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
    if (jQuery('#gridIndividualModePerfilPagina').val() == 'true') {
        jQuery('#btnNewPerfilPagina').hide();
        perfilPaginaJS.findFirst();
    }
});

var perfilPaginaJS = {
    /* Variable auxiliar para persistir un objeto (resultado de una consulta) 
     * obtenido mediante el callback de la función "findById(...)". Este objeto
     * es necesario para la funcionalidad de selects encadenados pero puede ser
     * utilizado para otros propósitos. El objeto existe solamente mientras se
     * esta editando la información en el formulario. Al presionar el botón
     * Nuevo o Guardar cualquier referencia a éste regresará "null". El 
     * objeto debe utilizarse como modo de "Solo Lectura" (la modificación de 
     * este objeto no se verá reflejada en la base de datos) */
    perfilPagina: null,
    /*
     * Variable auxiliar para mantener las busquedas del grid al momento de paginar, ordenar, etc.
     */
    cacheDWR: null,
    /*
     * Función que guarda un nuevo registro en la BD.
     */
    save: function () {
        utilEffect.showProgressBar();
        var perfilPagina = utilObject.buildObject('#perfilPaginaform', new PerfilPaginaDWR());
        var perfilId = perfilPagina.perfilId;
        if (perfilId === 0) {
            utilDialog.showDialog('#d-seleccionPerfil');
            return;
        }
        var paginas = [];
        jQuery('.accesos li').each(function () {
            if (jQuery(this).hasClass('jstree-undetermined') || jQuery(this).hasClass('jstree-checked')) {
                var id = jQuery(this).attr('id');
                var idNumber = parseInt(id.substring(3, id.length));
                paginas.push(idNumber);
            }
        });

        if (paginas.length == 0) {
            utilDialog.showDialog('#d-noSeleccion');
            return;
        } else {
            utilEffect.showProgressBar();
            PerfilPaginaDWR.save(perfilId, paginas, perfilPaginaJS.saveOrUpdateCallback);
        }

//PerfilPaginaDWR.save( listaPerfilPaginas, perfilPaginaJS.saveOrUpdateCallback );
    },
    /*
     * Función que actualiza un registro existente en la BD.
     */
    update: function () {
        utilEffect.showProgressBar();
        var perfilPagina = utilObject.buildObject('#perfilPaginaform', new PerfilPaginaDWR());
        perfilPagina.perfilPaginaId = jQuery('#perfilPaginaform_perfilPaginaId').val();
        var listaPerfilPaginas = [perfilPagina];
        PerfilPaginaDWR.update(listaPerfilPaginas, perfilPaginaJS.saveOrUpdateCallback);
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
        if (jQuery('#gridIndividualModePerfilPagina').val() == 'false') {
            utilForm.reset('#perfilPaginaform');
            jQuery('#perfilPaginaform_perfilPaginaId').val(0);
            perfilPaginaJS.perfilPagina = null;
            perfilPaginaJS.reloadGrid();
        }
        utilEffect.hideProgressBar();
        jQuery('.accesos').jstree('uncheck_all');
    },
    /*
     * Función que elimina un registro en el grid al presionar el botón eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como parámetro.
     */
    remove: function () {
        utilEffect.showProgressBar();
        var id = perfilPaginaJS.getEliminarId();
        var listaIds = [id];
        PerfilPaginaDWR.remove(listaIds, perfilPaginaJS.removeCallback);
    },
    /*
     * Función que elimina los registros seleccionados en el grid al presionar el botón eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como parámetro.
     */
    removeSelected: function () {
        utilEffect.showProgressBar();
        var listaIds = utilGrid.gridGetSelectedRows('#perfilPaginaGrid');
        PerfilPaginaDWR.remove(listaIds, perfilPaginaJS.removeCallback);
    },
    /*
     * Callback de la función remove(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    removeCallback: function () {
        jQuery('#btnNewPerfilPagina').click();
        perfilPaginaJS.reloadGrid();
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
        PerfilPaginaDWR.findById(id, perfilPaginaJS.findByIdCallback);
    },
    /*
     * Callback de la función findById(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto con la información de la consulta.
     */
    findByIdCallback: function (data) {
        utilForm.reset('#perfilPaginaform');
        utilForm.populate('#perfilPaginaform', data);
        perfilPaginaJS.perfilPagina = data;
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
        perfilPaginaJS.cacheDWR = obj;
        var page = jQuery('#perfilPaginaGridCurrentPage').val();
        var rows = jQuery('#perfilPaginaGridRowsByPage').val();
        var order = jQuery('#perfilPaginaGridOrderByColumn').val();
        var orderType = jQuery('#perfilPaginaGridOrderByType').val();
        utilEffect.showProgressBar();
        PerfilPaginaDWR.findByCriteria(page, rows, order, orderType, obj, perfilPaginaJS.findByCriteriaCallback);
    },
    /*
     * Callback de la función findByCriteria(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto (Grid) con la información de la 
     * consulta.
     */
    findByCriteriaCallback: function (data) {
        utilGrid.gridUpdate('#perfilPaginaGrid', '#perfilPaginaGridCurrentPage', '#perfilPaginaGridRowsByPage', data);
        utilEffect.hideProgressBar();
    },
    /**
     * Funcion utilizado cuando la pagina es individual
     */
    findFirst: function () {
        utilEffect.showProgressBar();
        PerfilPaginaDWR.findFirst(perfilPaginaJS.findByIdCallback);
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
        PerfilPaginaDWR.setEstatus(estatusId, listaObjetos, perfilPaginaJS.setEstatusCallback);
    },
    /*
     * Función que cambia el Estatus de los elementos seleccionados en el widget
     * de jqGrid.
     * 
     */
    setEstatusSeleccion: function () {
        utilEffect.showProgressBar();
        var estatusId = perfilPaginaJS.estatusId;
        var listaObjetos = utilGrid.gridGetSelectedRows('#perfilPaginaGrid');
        PerfilPaginaDWR.setEstatus(estatusId, listaObjetos, perfilPaginaJS.setEstatusCallback);
    },
    /*
     * Callback de la función setEstatus(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     */
    setEstatusCallback: function () {
        perfilPaginaJS.reloadGrid();
        utilEffect.hideProgressBar();
    },
    /*
     * Función que muestra un mensaje de confirmación para Activar o Inactivar los registros
     * seleccionados del widget jQGrid.
     */
    prepareToSetEstatus: function (estatusId) {
        var seleccion = utilGrid.gridGetSelectedRows('#perfilPaginaGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedPerfilPagina');
        } else {
            perfilPaginaJS.estatusId = estatusId;
            // utilDialog.showDialog( '#d-confirmSetEstatusPerfilPagina' );
            perfilPaginaJS.setEstatusSeleccion();
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar los registros seleccionados
     * del widget jQGrid.
     */
    prepareToRemoveSelected: function () {
        var seleccion = utilGrid.gridGetSelectedRows('#perfilPaginaGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedPerfilPagina');
        } else {
            utilDialog.showDialog('#d-removeSeleccionPerfilPagina');
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar un regristro
     * del widget jQGrid.
     */
    prepareToRemove: function (id) {
        perfilPaginaJS.setEliminarId(id);
        utilDialog.showDialog('#d-removePerfilPagina');
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
        perfilPaginaJS.eliminarId = eliminarId;
    },
    /*
     * Getter de la variable eliminarId
     **/
    getEliminarId: function () {
        return perfilPaginaJS.eliminarId;
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
        var validation = validanguage.validateForm('perfilPaginaform');
        if (validation.result) {
            perfilPaginaJS.doCommit();
        }
    },
    /*
     * Función que inicia el proceso para GUARDAR y ACTUALIZAR/MODIFICAR
     * Una vez que se pasaron las validaciones se realiza el commit.
     */
    doCommit: function () {
        if (jQuery('#perfilPaginaform_perfilPaginaId').val() == 0) {
            perfilPaginaJS.save();
        } else {
            perfilPaginaJS.update();
        }
    },
    /*
     * Función que es llamada para actualizar la información del grid.
     */
    reloadGrid: function () {
        if (perfilPaginaJS.cacheDWR != null) {
            perfilPaginaJS.findByCriteria(perfilPaginaJS.cacheDWR);
        } else {
            perfilPaginaJS.findByCriteria(new PerfilPaginaDWR());
        }
    },
    /*
     * Función que obtene las opciones de un elemento select perfilPaginaform_perfilId para recargarlo mediante AJAX.
     */
    getPerfil: function () {
        utilEffect.showProgressBar();
        PerfilPaginaDWR.getPerfil(perfilPaginaJS.getPerfilCallback);
    },
    /*
     * Callback de la función getPerfil() esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    getPerfilCallback: function (list) {
        var idCmb = jQuery('#perfilPaginaform_perfilId').val();
        jQuery('#perfilPaginaform_perfilId').find('option').remove();
        for (var i = 0; i < list.length; i++) {
            var object = list[i];
            var id = object.perfilId;
            var nombre = object.nombre;
            jQuery('#perfilPaginaform_perfilId').append('<option value="' + id + '">' + nombre + '</option>');
        }
        // Si el usuario selecciono una opcion en el popup entonces ponerlo como seleccionado
        var sel = parseInt(jQuery('#idFromIframe').val());
        if (sel == 0) {
            // La opcion del popup seleccionada esta inactiva, validar la opcion seleccionada antes de abrir el popup
            if (jQuery("#perfilPaginaform_perfilId option[value='" + idCmb + "']").length > 0) {
                jQuery('#perfilPaginaform_perfilId').val(idCmb);
            } else {
                jQuery('#perfilPaginaform_perfilId').val(0);
            }
        } else {
            // Checar si la seleccion esta activa
            if (jQuery("#perfilPaginaform_perfilId option[value='" + sel + "']").length > 0) {
                jQuery('#perfilPaginaform_perfilId').val(sel);
            }
        }
        utilEffect.hideProgressBar();
    },
    /*
     * Función que obtene las opciones de un elemento select perfilPaginaform_paginaId para recargarlo mediante AJAX.
     */
    getPagina: function () {
        utilEffect.showProgressBar();
        PerfilPaginaDWR.getPagina(perfilPaginaJS.getPaginaCallback);
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
    /*
     * Función que es activa el modal de busqueda.
     */
    openSearch: function () {
        utilSearch.openSearch('#d-searchPerfilPagina', '#perfilPaginaGrid', '#perfilPaginaform', perfilPaginaJS, new PerfilPaginaDWR());
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
        perfilPaginaJS.entity = entity;
        perfilPaginaJS.headers = headers;
        perfilPaginaJS.format = format;
        perfilPaginaJS.reportName = jQuery('#key_perfilpagina_title').val() +
                '_' + utilMisc.getTodayDate('-');
        var criteriaExample = new PerfilPaginaDWR();
        if (perfilPaginaJS.cacheDWR != null) {
            criteriaExample = perfilPaginaJS.cacheDWR;
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
        PerfilPaginaDWR.getReportDataTest(sortBy, sortType, criteriaExample, perfilPaginaJS.exportarDatosCallback);
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
                perfilPaginaJS.entity + '&headers=' + perfilPaginaJS.headers +
                '&reportName=' + perfilPaginaJS.reportName + '&format=' + perfilPaginaJS.format +
                '&reportKey=' + reportKey;
    },
    /*
     * Obtiene una lista de todas las paginas a las que un determinado perfil tiene acceso.
     *
     */
    obtieneAccesos: function (id) {
        var perfil = utilObject.buildObject('#perfilPaginaform', new PerfilDWR());
        var perfilId = perfil.perfilId;
        if (perfilId == 0) {
            return;
        }
        utilEffect.showProgressBar();
        PerfilPaginaDWR.obtieneAccesos(perfilId, perfilPaginaJS.obtieneAccesosCallback);
    },
    /*
     * Callback de la función obtieneAccesos(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param paginas (List<Pagina>) es una lista de todos los registros válidos (activos y 
     * no eliminados) de la tabla PAGINA.
     */
    obtieneAccesosCallback: function (paginas) {
        utilEffect.hideProgressBar();
        if (paginas.length == 0 || paginas == null) {
            utilDialog.showDialog('#d-noHayAccesos');
        } else {
            jQuery('.accesos').jstree('uncheck_all');
            for (var i = 0; i < paginas.length; i++) {
                if (jQuery('#pag' + paginas[i]).hasClass('esMenu')) {
                    // No llamar check_node en menus, ya que al hacerlo se seleccionan
                    // todos los child nodes
                    // Si no tiene hijos pero si tiene acceso al menu entonces seleccionar el menu
                    // ya que no tiene hijos y es seguro ponerlo en checked
                    var children = jQuery.jstree._reference('.accesos')._get_children('#pag' + paginas[i]);
                    if (children.length == 0) {
                        jQuery.jstree._reference('.accesos').check_node('#pag' + paginas[i]);
                    }
                } else {
                    jQuery.jstree._reference('.accesos').check_node('#pag' + paginas[i]);
                }
            }
        }
    },
    /*
     * Función que Obtiene una lista de todos los registros válidos (activos y 
     * no eliminados) de la tabla PAGINA.
     */
    obtienePaginas: function () {
        PerfilPaginaDWR.obtienePaginas(perfilPaginaJS.obtienePaginasCallback)
    },
    /*
     * Callback de la función obtienePaginas(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param paginas (List<Pagina>) es una lista de todos los registros válidos (activos y 
     * no eliminados) de la tabla PAGINA.
     */
    obtienePaginasCallback: function (paginas) {
        if (paginas != null) {
            var i, pagina, id;
            // Fase 1
            for (i = 0; i < paginas.length; i++) {
                pagina = paginas[i];
                var nombre = pagina.nombre;
                id = pagina.paginaId;
                var url = pagina.anidar;
                var esMenu = '';
                if (url == '') {
                    esMenu = " class='esMenu '";
                }
                var html = '';
                html += "<li id='pag" + id + "' " + esMenu + ">";
                html += "<a href='#'>";
                html += nombre + "</a>";
                html += "</li>";
                jQuery('.paginas').append(html);
            }

            // Fase 2
            for (i = 0; i < paginas.length; i++) {
                pagina = paginas[i];
                var parentId = pagina.anidar;
                id = pagina.paginaId;
                if (utilError.checkById('#pag' + parentId)) {
                    jQuery('#pag' + id).appendTo('#pag' + parentId);
                }
            }
            // Fase 3
            jQuery('.paginas li').each(function () {
                var parentTag = jQuery(this).parent().is('li');
                if (parentTag) {
                    jQuery(this).parent().find('li').wrapAll('<ul />');
                }
            });
            // Fase 4
            jQuery('.accesos').jstree({
                'html_data': {
                    'data': jQuery('.paginas').html()
                },
                "themes": {
                    "theme": "default",
                    "icons": false
                },
                'plugins': ['themes', 'html_data', 'checkbox', 'sort', 'ui']
            }).on('loaded.jstree', function () {
                jQuery('.accesos').jstree('open_all');
            });
        }
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
// Validaciones de perfilPaginaform
validanguage.el.perfilPaginaform_perfilId = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};

