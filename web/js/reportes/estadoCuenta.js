jQuery(document).ready(function () {

    // Actualizar "Evento" al seleccionar "Organizacion" en formulario estadoCuenta
    jQuery('#estadoCuentaform_organizacionId').change(function () {
        estadoCuentaJS.filtrarEvento();
    });

    // Crear el mensaje 'Espere...'
    utilEffect.createProgressBar('Espere...');

    // Elementos button
    jQuery('.button').button();
    // Elementos datepicker
    utilInput.setAsDatepicker('.isDate');

    // <editor-fold defaultstate="collapsed" desc="Botones">
    // Botón : Buscar
    jQuery('#btnBuscarEstadoCuenta').click(function () {
        estadoCuentaJS.prepareToBuscar();
    }).customButtonEffect('#btnBuscarEstadoCuenta');
    // Botón : Exportar
    jQuery('#btnExportarEstadoCuenta').click(function () {
        estadoCuentaJS.prepareToExportarExcel();
    }).customButtonEffectBlue('#btnExportarEstadoCuenta');
    // </editor-fold>

    if (jQuery('#gridVisibleEstadoCuenta').val() == 'true') {
        jQuery('#estadoCuentaGrid').jqGrid({
            url: '',
            datatype: '',
            colNames: [
                jQuery('#estadoCuentaform_organizacionIdText').html(),
                jQuery('#key_estadocuenta_organizacion').val(),
                jQuery('#key_estadocuenta_evento').val(),
                jQuery('#key_estadocuenta_boletosvendidos').val(),
                jQuery('#key_estadocuenta_boletoscancelados').val(),
                jQuery('#key_estadocuenta_boletosduro').val(),
                jQuery('#key_estadocuenta_coletoscortesias').val(),
                jQuery('#key_estadocuenta_venta').val(),
                jQuery('#key_estadocuenta_cargoxsevicio').val(),
                jQuery('#key_estadocuenta_total').val(),
                jQuery('#key_estadocuenta_gananciataasgo').val()],
            colModel: [{
                    name: 'organizacionId',
                    index: 'organizacionId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'nombreOrganizacion',
                    index: 'nombreOrganizacion',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    width: 200,
                    search: false
                }, {
                    name: 'nombreEvento',
                    index: 'nombreEvento',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    width: 200,
                    search: false
                }, {
                    name: 'noBoletos',
                    index: 'noBoletos',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: false
                }, {
                    name: 'noCancelaciones',
                    index: 'noCancelaciones',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: false
                }, {
                    name: 'noBoletoDuro',
                    index: 'noBoletoDuro',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: false
                }, {
                    name: 'noCortesias',
                    index: 'noCortesias',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: false
                }, {
                    name: 'monto',
                    index: 'monto',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: false
                }, {
                    name: 'cargoXServicio',
                    index: 'cargoXServicio',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: false
                }, {
                    name: 'total',
                    index: 'total',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: false
                }, {
                    name: 'comisionTaasgo',
                    index: 'comisionTaasgo',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: false
                }],
            height: 230,
            toolbar: false,
            hidegrid: true,
            multiselect: true,
            viewrecords: true,
            rowList: [10, 20, 30, 50, 100],
            pager: jQuery('#estadoCuentaGridPagerId'),
            caption: jQuery('#key_estadocuenta_title').val()
        });
        // jQuery UI Dialogs

        // Inicializaciones extra para el grid
        utilGrid.setup('#estadoCuentaGrid', '#estadoCuentaGridPagerId', '#estadoCuentaGridCurrentPage', '#estadoCuentaGridOrderByColumn',
                '#estadoCuentaGridOrderByType', 'estadoCuentaJS');

        // Crear el modal de busqueda
        //utilSearch.buildSearch('#d-searchEstadoCuenta');

        // Recargar el widget jqGrid
        //estadoCuentaJS.reloadGrid();
    }
});

var estadoCuentaJS = {
    /* Variable auxiliar para persistir un objeto (resultado de una consulta) 
     * obtenido mediante el callback de la función "findById(...)". Este objeto
     * es necesario para la funcionalidad de selects encadenados pero puede ser
     * utilizado para otros propósitos. El objeto existe solamente mientras se
     * esta editando la información en el formulario. Al presionar el botón
     * Nuevo o Guardar cualquier referencia a éste regresará "null". El 
     * objeto debe utilizarse como modo de "Solo Lectura" (la modificación de 
     * este objeto no se verá reflejada en la base de datos) */
    estadoCuenta: null,
    /*
     * Variable auxiliar para mantener las busquedas del grid al momento de paginar, ordenar, etc.
     */
    cacheDWR: null,
    /*
     * Función que realiza una búsqueda en la BD. Este objeto contiene 
     * todos los criterios para realizar la consulta.
     * 
     * @param obj (Object) Es el objeto con el cual se va a realizar la 
     * consulta. El resultado será una lista de objetos que concuerden con las
     * propiedades del objeto que se envió como parámetro.
     */
    findByCriteria: function (obj) {
        estadoCuentaJS.cacheDWR = obj;
        var page = jQuery('#estadoCuentaGridCurrentPage').val();
        var rows = jQuery('#estadoCuentaGridRowsByPage').val();
        var order = jQuery('#estadoCuentaGridOrderByColumn').val();
        var orderType = jQuery('#estadoCuentaGridOrderByType').val();
        utilEffect.showProgressBar();
        EstadoCuentaDWR.findByCriteria(page, rows, order, orderType, obj, estadoCuentaJS.findByCriteriaCallback);
    },
    /*
     * Callback de la función findByCriteria(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto (Grid) con la información de la 
     * consulta.
     */
    findByCriteriaCallback: function (data) {
        utilGrid.gridUpdate('#estadoCuentaGrid', '#estadoCuentaGridCurrentPage', '#estadoCuentaGridRowsByPage', data);
        utilEffect.hideProgressBar();
    },
    /*
     * Función que es llamada para actualizar la información del grid.
     */
    reloadGrid: function () {
        if (estadoCuentaJS.cacheDWR != null) {
            estadoCuentaJS.findByCriteria(estadoCuentaJS.cacheDWR);
        } else {
            estadoCuentaJS.findByCriteria(new EstadoCuentaDWR());
        }
    },
    /*
     * Función que obtene las opciones de un elemento select estadoCuenta_organizacionId para recargarlo mediante AJAX.
     */
    getOrganizacion: function () {
        utilEffect.showProgressBar();
        EstadoCuentaDWR.getOrganizacion(estadoCuentaJS.getOrganizacionCallback);
    },
    /*
     * Callback de la función getOrganizacion() esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    getOrganizacionCallback: function (list) {
        var idCmb = jQuery('#estadoCuentaform_organizacionId').val();
        jQuery('#estadoCuentaform_organizacionId').find('option').remove();
        for (var i = 0; i < list.length; i++) {
            var object = list[i];
            var id = object.organizacionId;
            var nombre = object.nombre;
            jQuery('#estadoCuentaform_organizacionId').append('<option value="' + id + '">' + nombre + '</option>');
        }
        // Si el usuario selecciono una opcion en el popup entonces ponerlo como seleccionado
        var sel = parseInt(jQuery('#idFromIframe').val());
        if (sel == 0) {
            // La opcion del popup seleccionada esta inactiva, validar la opcion seleccionada antes de abrir el popup
            if (jQuery("#estadoCuentaform_organizacionId option[value='" + idCmb + "']").length > 0) {
                jQuery('#estadoCuentaform_organizacionId').val(idCmb);
            } else {
                jQuery('#estadoCuentaform_organizacionId').val(0);
            }
        } else {
            // Checar si la seleccion esta activa
            if (jQuery("#estadoCuentaform_organizacionId option[value='" + sel + "']").length > 0) {
                jQuery('#estadoCuentaform_organizacionId').val(sel);
            }
        }
        utilEffect.hideProgressBar();
    },
    /** 
     * Filtrar Evento
     * 
     */
    filtrarEvento: function () {
        utilEffect.showProgressBar();
        EstadoCuentaDWR.filtrarEvento(
                jQuery('#estadoCuentaform_organizacionId').val(),
                estadoCuentaJS.filtrarEventoCallback);
    },
    /** 
     * Callback de la función filtrarEvento(), esta
     * función es llamada por DWR al terminar de realizar la consulta.
     */
    filtrarEventoCallback: function (data) {
        if (data == null || data.length == 0) {
            utilInput.removeOptionsSelectAndAddDummy('#estadoCuentaform_eventoId', '--Seleccione--');
        } else {
            utilInput.populateSelect('#estadoCuentaform_eventoId', data, 'eventoId', 'nombre');
            if (estadoCuentaJS.estadoCuenta !== null) {
                if (estadoCuentaJS.estadoCuenta.eventoId !== null) {
                    if (utilInput.existsOptionByValueInSelect('#estadoCuentaform_eventoId', estadoCuentaJS.estadoCuenta.eventoId)) {
                        jQuery('#estadoCuentaform_eventoId').val(estadoCuentaJS.estadoCuenta.eventoId);
                    } else {
                        jQuery('#estadoCuentaform_eventoId').val(0);
                    }
                }
            }
            jQuery('#estadoCuentaform_eventoId').trigger('change');
        }
        utilEffect.hideProgressBar();
    },
    /** 
     * Valida los campos del formulario de estadoCuenta antes de buscar.
     */
    prepareToBuscar: function () {
        var validation = validanguage.validateForm('estadoCuentaform');
        if (validation.result) {
            estadoCuentaJS.buscar();
        }
    },
    /*
     * Función que buscar registros en la BD.
     */
    buscar: function () {
        utilEffect.showProgressBar();
        var estadoCuenta = utilObject.buildObject('#estadoCuentaform', new EstadoCuentaDWR());
        estadoCuentaJS.findByCriteria(estadoCuenta);
    },
    /** 
     * Valida los campos del formulario de estadoCuenta antes de exportar.
     */
    prepareToExportarExcel: function () {
        var validation = validanguage.validateForm('estadoCuentaform');
        if (validation.result) {
            estadoCuentaJS.exportarExcel();
        }
    },
    /*
     * Función que exporta registros de la BD.
     */
    exportarExcel: function () {
        utilEffect.showProgressBar();
        var estadoCuenta = utilObject.buildObject('#estadoCuentaform', new EstadoCuentaDWR());
        estadoCuentaJS.exportarDatos("XLS", estadoCuenta);
    },
    /*
     * 
     */
    format: null,
    reportName: null,
    exportarDatos: function (format, obj) {
        estadoCuentaJS.format = format;
        estadoCuentaJS.reportName = jQuery('#key_estadocuenta_title').val() +
                '_' + utilMisc.getTodayDate('-');
        estadoCuentaJS.cacheDWR = obj;
        utilEffect.showProgressBar();
        EstadoCuentaDWR.reporteEstadoCuentaXLS(obj, estadoCuentaJS.exportarDatosCallback);
    },
    /*
     * 
     */
    exportarDatosCallback: function (reportKey) {
        utilEffect.hideProgressBar();
        window.location.href = '/admon/reportes/estadoCuentaExportar.action?reportName=' + estadoCuentaJS.reportName
                + '&format=' + estadoCuentaJS.format + '&reportKey=' + reportKey;
    }
};
// Validaciones de estadoCuentaform
//validanguage.el.estadoCuentaform_fechaPagoInicio = {
//    characters: {
//        mode: 'allow', expression: '', suppress: false},
//    validations: [
//        ProfileStripWhitespace, ProfileRequiredField]};
//validanguage.el.estadoCuentaform_fechaPagoFin = {
//    characters: {
//        mode: 'allow', expression: 'numeric-', suppress: false},
//    validations: [
//        ProfileStripWhitespace, ProfileRequiredField]};
validanguage.el.estadoCuentaform_organizacionId = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [ProfileRequiredField]};
validanguage.el.estadoCuentaform_eventoId = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [ProfileRequiredField]};

