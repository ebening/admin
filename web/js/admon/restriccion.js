jQuery(document).ready(function () {
    
    // Actualizar "Aplicación" dependiendo del rol ***Falta
    // Actualizar "Módulos" al seleccionar "Aplicación" en formulario restriccion
    jQuery('#restriccionform_aplicacionId').change(function () {
        restriccionJS.filtrarModulo();
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
    utilInput.fixRadios('#paisform');


    // <editor-fold defaultstate="collapsed" desc="Botones">
    // Botón : NUEVO
    jQuery('#btnRestriccionRol').click(function () {
        utilForm.reset('#restriccionform');
        jQuery('#restriccionform_rolId').val(0);
        // Limpiar Grid de catálogos
        organizacionCredencialJS.reset();
        restriccionJS.restriccion = null;
    }).customButtonEffect('#btnNewRestriccion');
    // Botón : GUARDAR
    jQuery('#btnSaveRestriccion').click(function () {
        restriccionJS.prepareToSave();
    }).customButtonEffectBlue('#btnSaveRestriccion');
    // </editor-fold>
    if (jQuery('#gridVisibleCatalogo').val() == 'true') {
        jQuery('#catalogoGrid').jqGrid({
            url: '',
            datatype: '',
            colNames: [
                'id','',
                jQuery('#restriccion_catalogoIdText').html(),
                '',
                '',
                '','','','','Activo','Eliminar'],
            colModel: [{
                    name: 'catalogoId',
                    index: 'catalogoId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                },{
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
                    width: 250,
                    resizable: false,
                    hidden: false,
                    search: true
                },{
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
                    search: true
                }, {
                    name: 'modificacionUsuario',
                    index: 'modificacionUsuario',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'State',
                    index: 'State',
                    align: 'center',
                    width: 55,
                    sortable: false,
                    search: false,
                    hidden: false,
                    resizable: false
                }, {
                    name: 'Remove',
                    index: 'Remove',
                    align: 'center',
                    width: 55,
                    sortable: false,
                    search: false,
                    resizable: false
                }],
            height: 230,
            toolbar: false,
            hidegrid: true,
            multiselect: true,
            viewrecords: true,
            shrinkToFit : false,
            rowList: [10, 20, 30, 50, 100],
            pager: jQuery('#catalogoGridPagerId'),
            caption: jQuery('#restriccion_catalogoIdText').val()
        });
        // jQuery UI Dialogs
        utilDialog.setStandardDialog('#d-removeCatalogo', 'Confirme', '¿Desea eliminar el registro?', ['Aceptar@catalogoJS.remove()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-removeSeleccionCatalogo', 'Confirme', '¿Desea eliminar los registros seleccionados?', ['Aceptar@catalogoJS.removeSelected()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-confirmSetEstatusCatalogo', 'Confirme', '¿Desea cambiar el estatus de los registros seleccionados?', ['Aceptar@catalogoJS.setEstatusSeleccion()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-noSelectedCatalogo', 'Error', 'Para poder realizar esta operación seleccione uno ó más registros.', ['Aceptar@']);
        utilDialog.setStandardDialog('#d-registroDuplicadoCatalogo', 'Advertencia', 'Ya existe un registro con este nombre en la base de datos, ¿Desea crear un nuevo registro con este nombre?', ['Aceptar@catalogoJS.doCommit()', 'Cancelar@']);
        utilDialog.setCustomDialogSelectColumnsToExport("#catalogoColumnasExportar", '#catalogoGrid');
        utilDialog.setCustomDialogSetEstatus('#catalogoSetEstatusActivoInactivo',
                'catalogoJS.prepareToSetEstatus(1)', 'catalogoJS.prepareToSetEstatus(2)');

        // Inicializaciones extra para el grid
        utilGrid.setup('#catalogoGrid', '#catalogoGridPagerId', '#catalogoGridCurrentPage', '#catalogoGridOrderByColumn',
                '#catalogoGridOrderByType', 'catalogoJS');

        // Crear el modal de busqueda
        utilSearch.buildSearch('#d-searchCatalogo');

        // Recargar el widget jqGrid
        catalogoJS.reloadGrid();

    }

   
    // Limpiar la página
    jQuery('#btnNewRestriccion').click();
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
    if (jQuery('#gridIndividualModeCatalogo').val() == 'true') {
        jQuery('#btnNewCatalogo').hide();
        comisionJS.findFirst();
    }
    jQuery( '#catalogoGrid' ).setGridWidth(
        jQuery("#gbox_" + "catalogoGrid").closest(".grid-container").width() * .38 );
});

var restriccionJS = {
    /* Variable auxiliar para persistir un objeto (resultado de una consulta) 
     * obtenido mediante el callback de la función "findById(...)". Este objeto
     * es necesario para la funcionalidad de selects encadenados pero puede ser
     * utilizado para otros propósitos. El objeto existe solamente mientras se
     * esta editando la información en el formulario. Al presionar el botón
     * Nuevo o Guardar cualquier referencia a éste regresará "null". El 
     * objeto debe utilizarse como modo de "Solo Lectura" (la modificación de 
     * este objeto no se verá reflejada en la base de datos) */
    restriccion: null,
    /*
     * Variable auxiliar para mantener las busquedas del grid al momento de paginar, ordenar, etc.
     */
    cacheDWR: null,
    /*
     * Función que guarda un nuevo registro en la BD.
     */
    save: function () {
        utilEffect.showProgressBar();
        var restriccion = utilObject.buildObject('#restriccionform', new SeguridadRolRestriccionParDWR());
        restriccion.rolId = 0;
        var listaRestricciones = [restriccion];
        SeguridadRolRestriccionParDWR.save(listaRestricciones, restriccionJS.saveOrUpdateCallback);
    },
    /*
     * Función que actualiza un registro existente en la BD.
     */
    update: function () {
        utilEffect.showProgressBar();
        var restriccion = utilObject.buildObject('#restriccionform', new SeguridadRolRestriccionParDWR());
        restriccion.rolId = jQuery('#restriccionform_rolId').val();
        var listaRestricciones = [restriccion];
        SeguridadRolRestriccionParDWR.update(listaRestricciones, restriccionJS.saveOrUpdateCallback);
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
        if (jQuery('#gridIndividualModeCatalogo').val() == 'false') {
            utilForm.reset('#restriccionform');
            jQuery('#restriccionform_rolId').val(0);
            restriccionJS.restriccion = null;
            catalogoJS.reloadGrid();
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
        var id = restriccionJS.getEliminarId();
        var listaIds = [id];
        SeguridadRolRestriccionParDWR.remove(listaIds, restriccionJS.removeCallback);
    },
    /*
     * Función que elimina los registros seleccionados en el grid al presionar el botón eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como parámetro.
     */
    removeSelected: function () {
        utilEffect.showProgressBar();
        var listaIds = utilGrid.gridGetSelectedRows('#catalogoGrid');
        PaisDWR.remove(listaIds, restriccionJS.removeCallback);
    },
    /*
     * Callback de la función remove(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    removeCallback: function () {
        jQuery('#btnNewRestriccion').click();
        catalogoJS.reloadGrid();
    },
    /*
     * Función que válida el atributo <b>RolId</b> y <b>CatalogoParametroId</b> de un registro en la BD. 
     * Se valida si ya existe un registro con los mismos id, esto con el 
     * propósito de advertir al usuario sobre registros duplicados.
     * Por default el <b>CoreGenerator</b> está configurado solo para mostrar
     * advertencias al usuario y permite más de un registro con el mismo
     * nombre en la BD, está metodología puede cambiarse manualmente modificando
     * el <b>callback</b> de esta función.
     * NOTICE: Ésta función es generada por el <b>CoreGenerator</b>
     * únicamente cuando la tabla tiene los campos, si la tabla no
     * contiene ese campo la función estará ausente. 
     * 
     * Ésta función siempre es llamada antes de realizar algún commit a la BD 
     * (guardar ó actualizar/modificar).
     */
    isValidoNombre: function () {
        var rol = jQuery('#restricccionform_listaRolId').val();
        var catalogo = jQuery('#restricccionform_catalogoId').val();
        SeguridadRolRestriccionParDWR.isValidoNombre('nombre',catalogo,restriccionJS.isValidoNombreCallback);
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
        if (isValido == false && jQuery('#restriccionform_rolId').val() != 0) {
            restriccionJS.doCommit();
            return;
        }

        // Nuevo registro, en este caso se permite el commit
        if (isValido == true && jQuery('#restriccionform_rolId').val() == 0) {
            restriccionJS.doCommit();
            return;
        }

        // Registro existente, se le cambio el nombre, en este caso se permite el commit
        if (isValido == true && jQuery('#restriccionform_rolId').val() != 0) {
            restriccionJS.doCommit();
            return;
        }

        // Mostrar mensaje de registro duplicado 
        utilDialog.showDialog('#d-registroDuplicadoPais');
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
        restriccionJS.eliminarId = eliminarId;
    },
    /*
     * Getter de la variable eliminarId
     **/
    getEliminarId: function () {
        return restriccionJS.eliminarId;
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
        //var validation = validanguage.validateForm('restriccionform');
        if (true) {
            restriccionJS.isValidoNombre();
        }
    },
    /*
     * Función que inicia el proceso para GUARDAR y ACTUALIZAR/MODIFICAR
     * Una vez que se pasaron las validaciones se realiza el commit.
     */
    doCommit: function () {
        if (jQuery('#restriccionform_rolId').val() == 0) {
            restriccionJS.save();
        } else {
            restriccionJS.update();
        }
    },
    /*
     * Función que permite filtrar los módulos por Aplicación
     */
    filtrarModulo: function () {
            utilEffect.showProgressBar();
            SeguridadRolRestriccionParDWR.filtrarModulosByAplicacion(restriccionJS.filtrarTipoValorCallback);
    },  
    filtrarModuloAplicacionCallback: function (list) {
        nombreContraId=jQuery('select#comisionform_aplicacionId').val();
        for (var i = 0; i < list.length; i++) {
            var object = list[i];
            var id = object.nombreContraId;
            var valor = object.valor;
            if (id.toString() === nombreContraId.toString()) {
                jQuery('#comisionform_moduloId').val(valor);
            }
        }
        utilEffect.hideProgressBar();
    }
};
var catalogoJS = {
    /* Variable auxiliar para persistir un objeto (resultado de una consulta) 
     * obtenido mediante el callback de la función "findById(...)". Este objeto
     * es necesario para la funcionalidad de selects encadenados pero puede ser
     * utilizado para otros propósitos. El objeto existe solamente mientras se
     * esta editando la información en el formulario. Al presionar el botón
     * Nuevo o Guardar cualquier referencia a éste regresará "null". El 
     * objeto debe utilizarse como modo de "Solo Lectura" (la modificación de 
     * este objeto no se verá reflejada en la base de datos) */
    catalogo: null,
    /*
     * Variable auxiliar para mantener las busquedas del grid al momento de paginar, ordenar, etc.
     */
    cacheDWR: null,
    /*
     * Función que actualiza un registro existente en la BD.
     */
    update: function () {
        utilEffect.showProgressBar();
        var catalogo = utilObject.buildObject('#catalogoform', new CatalogoDWR());
        catalogo.catalogoId = jQuery('#restriccionform_catalogoId').val();
        var listaCatalogos = [catalogo];
        CatalogoDWR.update(listaCatalogos, catalogoJS.saveOrUpdateCallback);
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
        if (jQuery('#gridIndividualModeCatalogo').val() == 'false') {
            catalogoJS.reloadGrid();
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
        var id = catalogoJS.getEliminarId();
        var listaIds = [id];
        CatalogoDWR.remove(listaIds, catalogoJS.removeCallback);
    },
    /*
     * Función que elimina los registros seleccionados en el grid al presionar el botón eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como parámetro.
     */
    removeSelected: function () {
        utilEffect.showProgressBar();
        var listaIds = utilGrid.gridGetSelectedRows('#catalogoGrid');
        CatalogoDWR.remove(listaIds, restriccionJS.removeCallback);
    },
    /*
     * Callback de la función remove(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    removeCallback: function () {
        catalogoJS.reloadGrid();
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
        CatalogoDWR.findById(id, catalogoJS.findByIdCallback);
    },
    /*
     * Callback de la función findById(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto con la información de la consulta.
     */
    findByIdCallback: function (data) {
        utilForm.reset('#catalogoform');
        utilForm.populate('#catalogoform', data);
        catalogoJS.catalogo = data;
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
        console.log("findByCriteri");
        restriccionJS.cacheDWR = obj;
        var page = jQuery('#catalogoGridCurrentPage').val();
        var rows = jQuery('#catalogoGridRowsByPage').val();
        var order = jQuery('#catalogoGridOrderByColumn').val();
        var orderType = jQuery('#catalogoGridOrderByType').val();
        utilEffect.showProgressBar();
        CatalogoDWR.findByCriteria(page, rows, order, orderType, obj, catalogoJS.findByCriteriaCallback);
    },
    /*
     * Callback de la función findByCriteria(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto (Grid) con la información de la 
     * consulta.
     */
    findByCriteriaCallback: function (data) {
        console.log("Entrando>>>>>>>>>>>findByCriteriaCallback");
        utilGrid.gridUpdateMoreColumnFilter('#catalogoGrid', '#catalogoGridCurrentPage', '#catalogoGridRowsByPage', data,'organizacionId');
        utilEffect.hideProgressBar();
    },
    /**
     * Funcion utilizado cuando la pagina es individual
     */
    findFirst: function () {
        utilEffect.showProgressBar();
        CatalogoDWR.findFirst(catalogoJS.findByIdCallback);
    },

    /*
     * Función que cambia el estatus de un registro en el grid al presionar el botón Activar/Inactivar.
     * Manda llamar al método <b>setEstatus</b> de DWR, éste método.
     *
     * @param id (int) Es el id del registro al cual se le va a cambiar el estatusId
     * @param estatusId (int) Es el nuevo estatusId
     */
    setEstatus: function (id,organizacionId, estatusId) {
        utilEffect.showProgressBar();
        var listaObjetos = [id];
        console.log("Estatus");
        console.log(estatusId);
        console.log(listaObjetos);
      
      //  CatalogoDWR.setEstatus(estatusId, listaObjetos, catalogoJS.setEstatusCallback);
    },
    /*
     * Función que cambia el Estatus de los elementos seleccionados en el widget
     * de jqGrid.
     * 
     */
    setEstatusSeleccion: function () {
        utilEffect.showProgressBar();
        var estatusId = catalogoJS.estatusId;
        var listaObjetos = utilGrid.gridGetSelectedRows('#catalogoGrid');
        CatalogoDWR.setEstatus(estatusId, listaObjetos, catalogoJS.setEstatusCallback);
    },
    /*
     * Callback de la función setEstatus(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     */
    setEstatusCallback: function () {
        catalogoJS.reloadGrid();
        utilEffect.hideProgressBar();
    },
    /*
     * Función que muestra un mensaje de confirmación para Activar o Inactivar los registros
     * seleccionados del widget jQGrid.
     */
    prepareToSetEstatus: function (estatusId) {
        var seleccion = utilGrid.gridGetSelectedRows('#catalogoGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedCatalogo');
        } else {
            catalogoJS.estatusId = estatusId;
            catalogoJS.setEstatusSeleccion();
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar los registros seleccionados
     * del widget jQGrid.
     */
    prepareToRemoveSelected: function () {
        var seleccion = utilGrid.gridGetSelectedRows('#catalogoGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedCatalogo');
        } else {
            utilDialog.showDialog('#d-removeSeleccionCatalogo');
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar un regristro
     * del widget jQGrid.
     */
    prepareToRemove: function (id) {
        catalogoJS.setEliminarId(id);
        utilDialog.showDialog('#d-removeCatalogo');
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
        restriccionJS.eliminarId = eliminarId;
    },
    /*
     * Getter de la variable eliminarId
     **/
    getEliminarId: function () {
        return restriccionJS.eliminarId;
    },

    /*
     * Variable para guardar el estatus según el botón que se presionó: Activar o Inactivar si 
     * el usuario confirma la decisión al presionar el botón Aceptar del cuadro de diálogo. 
     **/
    estatusId: 0,
   
    /*
     * Función que inicia el proceso paraACTUALIZAR/MODIFICAR
     * Una vez que se pasaron las validaciones se realiza el commit.
     */
    doCommit: function () {
            catalogoJS.update();
    },
    /*
     * Función que es llamada para actualizar la información del grid.
     */
    reloadGrid: function () {
        if (catalogoJS.cacheDWR != null) {
            catalogoJS.findByCriteria(catalogoJS.cacheDWR);
        } else {
            catalogoJS.findByCriteria(new CatalogoDWR());
        }
    },
    /*
     * Función que es activa el modal de busqueda.
     */
    openSearch: function () {
        utilSearch.openSearch('#d-searchCatalogo', '#catalogoGrid', '#catalogoform', catalogoJS, new CatalogoDWR());
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
        catalogoJS.entity = entity;
        catalogoJS.headers = headers;
        catalogoJS.format = format;
        catalogoJS.reportName = jQuery('#key_catalogo_title').val() +
                '_' + utilMisc.getTodayDate('-');
        var criteriaExample = new CatalogoDWR();
        if (catalogoJS.cacheDWR != null) {
            criteriaExample = catalogoJS.cacheDWR;
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
        CatalogoDWR.getReportDataTest(sortBy, sortType, criteriaExample, catalogoJS.exportarDatosCallback);
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
                catalogoJS.entity + '&headers=' + catalogoJS.headers +
                '&reportName=' + catalogoJS.reportName + '&format=' + catalogoJS.format +
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
/*validanguage.el.paisform_code = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};
validanguage.el.paisform_nombre = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};

*/