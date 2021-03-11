jQuery( document ).ready( function() {

 // Actualizar "Estado" al seleccionar "Pais" en formulario organizacionDetalle
jQuery( '#organizacionDetalleform_paisId' ).change( function() {
organizacionDetalleJS.filtrarEstado();
});

 // Actualizar "Ciudad" al seleccionar "Estado" en formulario organizacionDetalle
jQuery( '#organizacionDetalleform_estadoId' ).change( function() {
organizacionDetalleJS.filtrarCiudad();
});


 // Crear el mensaje 'Espere...'
utilEffect.createProgressBar( 'Espere...' );

 // Elementos button
jQuery( '.button' ).button();
 // Elementos datepicker
utilInput.setAsDatepicker( '.isDate' );
 // Elementos datetimepicker
utilInput.setAsDateTimePicker( '.isDateTime' );
 // Elementos colorpicker
utilInput.setAsColorpicker( '.color-selector' );
 // Elementos radio
utilInput.fixRadios( '#organizacionDetalleform' );


 // <editor-fold defaultstate="collapsed" desc="Botones">
 // Botón : NUEVO
jQuery( '#btnNewOrganizacionDetalle' ).click( function () {
utilForm.reset( '#organizacionDetalleform' );
 // Limpiar campo con dependencias
utilInput.removeOptionsSelectAndAddDummy( '#organizacionDetalleform_estadoId', '--Seleccione--' );
 // Limpiar campo con dependencias
utilInput.removeOptionsSelectAndAddDummy( '#organizacionDetalleform_ciudadId', '--Seleccione--' );
jQuery( '#organizacionDetalleform_organizacionDetalleId' ).val(0); 
organizacionDetalleJS.organizacionDetalle = null;
}).customButtonEffect( '#btnNewOrganizacionDetalle' );
 // Botón : GUARDAR
jQuery( '#btnSaveOrganizacionDetalle' ).click( function () {
organizacionDetalleJS.prepareToSave();
}).customButtonEffect( '#btnSaveOrganizacionDetalle' );
 // </editor-fold>

if ( jQuery( '#gridVisibleOrganizacionDetalle' ).val() == 'true' ) {
jQuery( '#organizacionDetalleGrid' ).jqGrid({
url      : '',
datatype : '',
colNames: [
jQuery( '#organizacionDetalle_organizacionDetalleIdText' ).html(),
jQuery( '#organizacionDetalle_organizacionIdText' ).html(),
jQuery( '#organizacionDetalle_nombreText' ).html(),
jQuery( '#organizacionDetalle_rfcText' ).html(),
jQuery( '#organizacionDetalle_nombreComercialText' ).html(),
jQuery( '#organizacionDetalle_descripcionText' ).html(),
jQuery( '#organizacionDetalle_nombreContactoText' ).html(),
jQuery( '#organizacionDetalle_correoContactoText' ).html(),
jQuery( '#organizacionDetalle_telefonoContactoText' ).html(),
jQuery( '#organizacionDetalle_celularContactoText' ).html(),
jQuery( '#organizacionDetalle_direccionText' ).html(),
jQuery( '#organizacionDetalle_direccionAlternativaText' ).html(),
jQuery( '#organizacionDetalle_rutaImagenText' ).html(),
jQuery( '#organizacionDetalle_paisIdText' ).html(),
jQuery( '#organizacionDetalle_estadoIdText' ).html(),
jQuery( '#organizacionDetalle_ciudadIdText' ).html(),
jQuery( '#organizacionDetalle_estatusIdText' ).html(),
jQuery( '#organizacionDetalle_eliminadoIdText' ).html(),
jQuery( '#organizacionDetalle_creacionFechaText' ).html(),
jQuery( '#organizacionDetalle_creacionUsuarioText' ).html(),
jQuery( '#organizacionDetalle_modificacionFechaText' ).html(),
jQuery( '#organizacionDetalle_modificacionUsuarioText' ).html(),
'','',''],
colModel : [{
name      : 'organizacionDetalleId',
index     : 'organizacionDetalleId',
align     : 'center',
resizable : false,
hidden    : true,
search    : false
},{
name      : 'organizacionId',
index     : 'organizacionId',
align     : 'center',
resizable : false,
hidden    : false,
search    : true
},{
name      : 'nombre',
index     : 'nombre',
align     : 'center',
resizable : false,
hidden    : false,
search    : true
},{
name      : 'rfc',
index     : 'rfc',
align     : 'center',
resizable : false,
hidden    : false,
search    : true
},{
name      : 'nombreComercial',
index     : 'nombreComercial',
align     : 'center',
resizable : false,
hidden    : false,
search    : true
},{
name      : 'descripcion',
index     : 'descripcion',
align     : 'center',
resizable : false,
hidden    : true,
search    : false
},{
name      : 'nombreContacto',
index     : 'nombreContacto',
align     : 'center',
resizable : false,
hidden    : false,
search    : true
},{
name      : 'correoContacto',
index     : 'correoContacto',
align     : 'center',
resizable : false,
hidden    : false,
search    : true
},{
name      : 'telefonoContacto',
index     : 'telefonoContacto',
align     : 'center',
resizable : false,
hidden    : false,
search    : true
},{
name      : 'celularContacto',
index     : 'celularContacto',
align     : 'center',
resizable : false,
hidden    : false,
search    : true
},{
name      : 'direccion',
index     : 'direccion',
align     : 'center',
resizable : false,
hidden    : false,
search    : true
},{
name      : 'direccionAlternativa',
index     : 'direccionAlternativa',
align     : 'center',
resizable : false,
hidden    : false,
search    : true
},{
name      : 'rutaImagen',
index     : 'rutaImagen',
align     : 'center',
resizable : false,
hidden    : false,
search    : true
},{
name      : 'paisId',
index     : 'paisId',
align     : 'center',
resizable : false,
hidden    : false,
search    : true
},{
name      : 'estadoId',
index     : 'estadoId',
align     : 'center',
resizable : false,
hidden    : false,
search    : false
},{
name      : 'ciudadId',
index     : 'ciudadId',
align     : 'center',
resizable : false,
hidden    : false,
search    : false
},{
name      : 'estatusId',
index     : 'estatusId',
align     : 'center',
resizable : false,
hidden    : true,
search    : true
},{
name      : 'eliminadoId',
index     : 'eliminadoId',
align     : 'center',
resizable : false,
hidden    : true,
search    : false
},{
name      : 'creacionFecha',
index     : 'creacionFecha',
align     : 'center',
resizable : false,
hidden    : true,
search    : false
},{
name      : 'creacionUsuario',
index     : 'creacionUsuario',
align     : 'center',
resizable : false,
hidden    : true,
search    : false
},{
name      : 'modificacionFecha',
index     : 'modificacionFecha',
align     : 'center',
resizable : false,
hidden    : false,
search    : true
},{
name      : 'modificacionUsuario',
index     : 'modificacionUsuario',
align     : 'center',
resizable : false,
width     : 55,
hidden    : false,
search    : false
},{
name      : 'State',
index     : 'State',
align     : 'center',
width     : 20,
sortable  : false,
search    : false,
resizable : false
},{
name      : 'Edit',
index     : 'Edit',
align     : 'center',
width     : 20,
sortable  : false,
search    : false,
resizable : false
},{
name      : 'Remove',
index     : 'Remove',
align     : 'center',
width     : 20,
sortable  : false,
search    : false,
resizable : false
}],
height        : 230,
toolbar       : false,
hidegrid      : true,
multiselect   : true,
viewrecords   : true,
rowList       : [10,20,30,50,100],
pager         : jQuery( '#organizacionDetalleGridPagerId' ),
caption       : jQuery( '#key_organizaciondetalle_title' ).val()
});
 // jQuery UI Dialogs
utilDialog.setStandardDialog( '#d-removeOrganizacionDetalle', 'Confirme', '¿Desea eliminar el registro?', ['Aceptar@organizacionDetalleJS.remove()', 'Cancelar@'] );
utilDialog.setStandardDialog( '#d-removeSeleccionOrganizacionDetalle', 'Confirme', '¿Desea eliminar los registros seleccionados?', ['Aceptar@organizacionDetalleJS.removeSelected()', 'Cancelar@'] );
utilDialog.setStandardDialog( '#d-confirmSetEstatusOrganizacionDetalle', 'Confirme', '¿Desea cambiar el estatus de los registros seleccionados?', ['Aceptar@organizacionDetalleJS.setEstatusSeleccion()', 'Cancelar@'] );
utilDialog.setStandardDialog( '#d-noSelectedOrganizacionDetalle', 'Error', 'Para poder realizar esta operación seleccione uno ó más registros.', ['Aceptar@'] );
utilDialog.setStandardDialog( '#d-registroDuplicadoOrganizacionDetalle', 'Advertencia', 'Ya existe un registro con este nombre en la base de datos, ¿Desea crear un nuevo registro con este nombre?', ['Aceptar@organizacionDetalleJS.doCommit()','Cancelar@'] );
utilDialog.setCustomDialogSelectColumnsToExport("#organizacionDetalleColumnasExportar", '#organizacionDetalleGrid');
utilDialog.setCustomDialogSetEstatus('#organizacionDetalleSetEstatusActivoInactivo', 
'organizacionDetalleJS.prepareToSetEstatus(1)', 'organizacionDetalleJS.prepareToSetEstatus(2)');

 // Inicializaciones extra para el grid
utilGrid.setup( '#organizacionDetalleGrid', '#organizacionDetalleGridPagerId', '#organizacionDetalleGridCurrentPage', '#organizacionDetalleGridOrderByColumn',
'#organizacionDetalleGridOrderByType', 'organizacionDetalleJS' );

 // Crear el modal de busqueda
utilSearch.buildSearch( '#d-searchOrganizacionDetalle' );

 // Recargar el widget jqGrid
organizacionDetalleJS.reloadGrid();

}
 // Limpiar la página
jQuery( '#btnNewOrganizacionDetalle' ).click();
 // Edición externa
jQuery( '.new-window' ).tooltip();
jQuery( '.new-window' ).contents().hide();
jQuery( '.new-window' ).click( function() {
utilForm.hideTooltips();
jQuery( '#idFromIframe' ).val( 0 );
var url = jQuery( this ).find('.url').text();
var callback = jQuery( this ).find('.callback').text();
utilWindow.openNewWindow( url, callback, 1100 );
});

 // Si es individual precargar el formulario
if ( jQuery( '#gridIndividualModeOrganizacionDetalle' ).val() == 'true' ) {
jQuery( '#btnNewOrganizacionDetalle' ).hide();
organizacionDetalleJS.findFirst();
}
});

var organizacionDetalleJS = {
/* Variable auxiliar para persistir un objeto (resultado de una consulta) 
 * obtenido mediante el callback de la función "findById(...)". Este objeto
 * es necesario para la funcionalidad de selects encadenados pero puede ser
 * utilizado para otros propósitos. El objeto existe solamente mientras se
 * esta editando la información en el formulario. Al presionar el botón
 * Nuevo o Guardar cualquier referencia a éste regresará "null". El 
 * objeto debe utilizarse como modo de "Solo Lectura" (la modificación de 
 * este objeto no se verá reflejada en la base de datos) */
organizacionDetalle : null,
/*
 * Variable auxiliar para mantener las busquedas del grid al momento de paginar, ordenar, etc.
 */
cacheDWR : null,
/*
 * Función que guarda un nuevo registro en la BD.
 */
save : function() {
utilEffect.showProgressBar();
var organizacionDetalle = utilObject.buildObject( '#organizacionDetalleform', new OrganizacionDetalleDWR() );
organizacionDetalle.organizacionDetalleId = 0;
var listaOrganizacionDetalles = [ organizacionDetalle ];
OrganizacionDetalleDWR.save( listaOrganizacionDetalles, organizacionDetalleJS.saveOrUpdateCallback );
},
/*
 * Función que actualiza un registro existente en la BD.
 */
update : function() {
utilEffect.showProgressBar();
var organizacionDetalle = utilObject.buildObject( '#organizacionDetalleform', new OrganizacionDetalleDWR() );
organizacionDetalle.organizacionDetalleId = jQuery( '#organizacionDetalleform_organizacionDetalleId' ).val();
var listaOrganizacionDetalles = [ organizacionDetalle ];
OrganizacionDetalleDWR.update( listaOrganizacionDetalles, organizacionDetalleJS.saveOrUpdateCallback );
},
/*
 * Callback de la funcion save() y update(), esta función es llamada por DWR al 
 * terminar de realizar la operación.
 * 
 * @param savedIds (array), lista de id's de objetos que
 * se guardaron en base de datos.
 */
saveOrUpdateCallback : function( savedIds ) {
 // Grid Multiple
if ( jQuery( '#gridIndividualModeOrganizacionDetalle' ).val() == 'false' ) {
utilForm.reset( '#organizacionDetalleform' );
jQuery( '#organizacionDetalleform_organizacionDetalleId' ).val( 0 ); 
organizacionDetalleJS.organizacionDetalle = null;
organizacionDetalleJS.reloadGrid();
}
utilEffect.hideProgressBar();
},
/*
 * Función que elimina un registro en el grid al presionar el botón eliminar.
 * Manda llamar al método <b>remove</b> de DWR, éste método 
 * espera siempre una lista de id's como parámetro.
 */
remove : function() {
utilEffect.showProgressBar();
var id = organizacionDetalleJS.getEliminarId();
var listaIds = [ id ];
OrganizacionDetalleDWR.remove( listaIds, organizacionDetalleJS.removeCallback );
},
/*
 * Función que elimina los registros seleccionados en el grid al presionar el botón eliminar.
 * Manda llamar al método <b>remove</b> de DWR, éste método 
 * espera siempre una lista de id's como parámetro.
 */
removeSelected : function() {
utilEffect.showProgressBar();
var listaIds = utilGrid.gridGetSelectedRows( '#organizacionDetalleGrid' );
OrganizacionDetalleDWR.remove( listaIds, organizacionDetalleJS.removeCallback );
},
/*
 * Callback de la función remove(), esta función es llamada por DWR al 
 * terminar de realizar la operación.
 */
removeCallback : function() {
jQuery( '#btnNewOrganizacionDetalle' ).click();
organizacionDetalleJS.reloadGrid();
},
/*
 * Función que realiza una búsqueda de un objeto por su ID en la BD.
 * Usualmente es llamado cuando el usuario presiona el icono <b>Editar</b>
 * de una fila en el widget jqGrid.
 * 
 * @param id (Integer) Es el id del objeto que se quiere obtener.
 */
findById : function( id ) {
utilEffect.showProgressBar();
OrganizacionDetalleDWR.findById( id, organizacionDetalleJS.findByIdCallback );
},
/*
 * Callback de la función findById(...), esta función es llamada por DWR al 
 * terminar de realizar la operación.
 * 
 * @param data (Object) Es un objeto con la información de la consulta.
 */
findByIdCallback : function( data ) {
utilForm.reset( '#organizacionDetalleform' );
utilForm.populate( '#organizacionDetalleform' , data );
organizacionDetalleJS.organizacionDetalle = data;
jQuery('#organizacionDetalleform_paisId').trigger('change');
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
findByCriteria : function( obj ) {
organizacionDetalleJS.cacheDWR = obj;
var page = jQuery( '#organizacionDetalleGridCurrentPage' ).val();
var rows = jQuery( '#organizacionDetalleGridRowsByPage' ).val();
var order = jQuery( '#organizacionDetalleGridOrderByColumn' ).val();
var orderType = jQuery( '#organizacionDetalleGridOrderByType' ).val();
utilEffect.showProgressBar();OrganizacionDetalleDWR.findByCriteria( page, rows, order, orderType, obj, organizacionDetalleJS.findByCriteriaCallback );
},
/*
 * Callback de la función findByCriteria(...), esta función es llamada por DWR al 
 * terminar de realizar la operación.
 * 
 * @param data (Object) Es un objeto (Grid) con la información de la 
 * consulta.
 */
findByCriteriaCallback : function( data ) {
utilGrid.gridUpdate( '#organizacionDetalleGrid', '#organizacionDetalleGridCurrentPage', '#organizacionDetalleGridRowsByPage', data );
utilEffect.hideProgressBar();
},
/**
 * Funcion utilizado cuando la pagina es individual
 */
findFirst : function() {
utilEffect.showProgressBar();
OrganizacionDetalleDWR.findFirst( organizacionDetalleJS.findByIdCallback );
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
isValidoNombre : function() {
var nombre = jQuery( '#organizacionDetalleform_nombre' ).val();
OrganizacionDetalleDWR.isValidoNombre( nombre, organizacionDetalleJS.isValidoNombreCallback );
},
/*
 * Callback de la función isValidoNombre(), esta función es llamada por DWR al 
 * terminar de realizar la operación.
 * 
 * @param isValido (boolean) es true cuando no existe ningún registro con
 * el nombre a validar, false cuando hay al menos un registro con el mismo
 * nombre.
 */
isValidoNombreCallback : function( isValido ) {
 // Cuando se esta actualizando un registro pero no se cambia el nombre, 
 // la función isValidoNombre() siempre regresa false al momento de 
 // intentar guardar por que el registro que se esta editando tiene el mismo 
 // nombre que en base de datos, en este caso se permite el commit.
if ( isValido == false && jQuery( '#organizacionDetalleform_organizacionDetalleId').val() != 0 ) {
organizacionDetalleJS.doCommit();
return;
}

 // Nuevo registro, en este caso se permite el commit
if ( isValido == true  && jQuery( '#organizacionDetalleform_organizacionDetalleId').val() == 0 ) {
organizacionDetalleJS.doCommit();
return;
}

 // Registro existente, se le cambio el nombre, en este caso se permite el commit
if ( isValido == true  && jQuery( '#organizacionDetalleform_organizacionDetalleId').val() != 0 ) {
organizacionDetalleJS.doCommit();
return;
}

 // Mostrar mensaje de registro duplicado 
utilDialog.showDialog( '#d-registroDuplicadoOrganizacionDetalle' );
},

/*
 * Función que cambia el estatus de un registro en el grid al presionar el botón Activar/Inactivar.
 * Manda llamar al método <b>setEstatus</b> de DWR, éste método.
 *
 * @param id (int) Es el id del registro al cual se le va a cambiar el estatusId
 * @param estatusId (int) Es el nuevo estatusId
 */
setEstatus : function( id, estatusId ) {
utilEffect.showProgressBar();
var listaObjetos = [id];
OrganizacionDetalleDWR.setEstatus( estatusId, listaObjetos, organizacionDetalleJS.setEstatusCallback );
},
/*
 * Función que cambia el Estatus de los elementos seleccionados en el widget
 * de jqGrid.
 * 
 */
setEstatusSeleccion : function() {
utilEffect.showProgressBar();
var estatusId = organizacionDetalleJS.estatusId;
var listaObjetos = utilGrid.gridGetSelectedRows( '#organizacionDetalleGrid' );
OrganizacionDetalleDWR.setEstatus( estatusId, listaObjetos, organizacionDetalleJS.setEstatusCallback );
},
/*
 * Callback de la función setEstatus(), esta función es llamada por DWR al 
 * terminar de realizar la operación.
 * 
 */
setEstatusCallback : function() {
organizacionDetalleJS.reloadGrid();  
utilEffect.hideProgressBar();
},
/*
 * Función que muestra un mensaje de confirmación para Activar o Inactivar los registros
 * seleccionados del widget jQGrid.
 */
prepareToSetEstatus : function ( estatusId ) {
var seleccion = utilGrid.gridGetSelectedRows( '#organizacionDetalleGrid' )
if (seleccion.length == 0 ) {
utilDialog.showDialog( '#d-noSelectedOrganizacionDetalle' );
} else {
organizacionDetalleJS.estatusId = estatusId;
 // utilDialog.showDialog( '#d-confirmSetEstatusOrganizacionDetalle' );
organizacionDetalleJS.setEstatusSeleccion();
}
},
/*
 * Función que muestra un mensaje de confirmación para eliminar los registros seleccionados
 * del widget jQGrid.
 */
prepareToRemoveSelected : function() {
var seleccion = utilGrid.gridGetSelectedRows( '#organizacionDetalleGrid' )
if (seleccion.length == 0 ) {
utilDialog.showDialog( '#d-noSelectedOrganizacionDetalle' );
} else {
utilDialog.showDialog( '#d-removeSeleccionOrganizacionDetalle' );
}
},
/*
 * Función que muestra un mensaje de confirmación para eliminar un regristro
 * del widget jQGrid.
 */
prepareToRemove : function( id ) {
organizacionDetalleJS.setEliminarId( id );
utilDialog.showDialog( '#d-removeOrganizacionDetalle' );
},

/*
 * Variable para guardar el ID de la fila que se va a eliminar, si 
 * el usuario confirma la decisión al presionar el botón Aceptar del cuadro de diálogo. 
 **/
eliminarId : 0,
/*
 * Setter de la variable eliminarId
 **/
setEliminarId : function( eliminarId ) {
organizacionDetalleJS.eliminarId = eliminarId;
},
/*
 * Getter de la variable eliminarId
 **/
getEliminarId : function() {
return organizacionDetalleJS.eliminarId;
},

/*
 * Variable para guardar el estatus según el botón que se presionó: Activar o Inactivar si 
 * el usuario confirma la decisión al presionar el botón Aceptar del cuadro de diálogo. 
 **/
estatusId : 0,
/*
 * Función que se ejecuta al presionar el botón : GUARDAR.
 */
prepareToSave : function() {
var validation = validanguage.validateForm( 'organizacionDetalleform' );
if ( validation.result ) {
organizacionDetalleJS.isValidoNombre();
} 
},
/*
 * Función que inicia el proceso para GUARDAR y ACTUALIZAR/MODIFICAR
 * Una vez que se pasaron las validaciones se realiza el commit.
 */
doCommit : function () {
if ( jQuery( '#organizacionDetalleform_organizacionDetalleId' ).val() == 0 ) {
organizacionDetalleJS.save();
} else {
organizacionDetalleJS.update();
}
}, 
/*
 * Función que es llamada para actualizar la información del grid.
 */
reloadGrid : function() {
if ( organizacionDetalleJS.cacheDWR != null ) {
organizacionDetalleJS.findByCriteria( organizacionDetalleJS.cacheDWR );
} else {
organizacionDetalleJS.findByCriteria( new OrganizacionDetalleDWR() );
}
},
/*
 * Función que obtene las opciones de un elemento select organizacionDetalleform_organizacionId para recargarlo mediante AJAX.
 */
getOrganizacion : function() {
utilEffect.showProgressBar();
OrganizacionDetalleDWR.getOrganizacion(organizacionDetalleJS.getOrganizacionCallback);
},
/*
 * Callback de la función getOrganizacion() esta función es llamada por DWR al 
 * terminar de realizar la operación.
 */
getOrganizacionCallback : function( list ) {
var idCmb = jQuery( '#organizacionDetalleform_organizacionId' ).val();
jQuery( '#organizacionDetalleform_organizacionId' ).find( 'option' ).remove();
for (var i = 0; i < list.length; i++ ) {
var object = list[i];
var id = object.organizacionId;
var nombre = object.nombre;
jQuery( '#organizacionDetalleform_organizacionId' ).append('<option value="' + id + '">' + nombre + '</option>');
}
 // Si el usuario selecciono una opcion en el popup entonces ponerlo como seleccionado
var sel = parseInt(jQuery( '#idFromIframe' ).val());
if ( sel == 0 ) {
 // La opcion del popup seleccionada esta inactiva, validar la opcion seleccionada antes de abrir el popup
if ( jQuery( "#organizacionDetalleform_organizacionId option[value='" + idCmb + "']" ).length > 0 ) {
jQuery( '#organizacionDetalleform_organizacionId' ).val( idCmb );
} else {
jQuery( '#organizacionDetalleform_organizacionId' ).val( 0 );
}
} else {
 // Checar si la seleccion esta activa
if ( jQuery( "#organizacionDetalleform_organizacionId option[value='" + sel + "']" ).length > 0 ) {
jQuery( '#organizacionDetalleform_organizacionId' ).val( sel );
}
}
utilEffect.hideProgressBar();
},
/*
 * Función que obtene las opciones de un elemento select organizacionDetalleform_paisId para recargarlo mediante AJAX.
 */
getPais : function() {
utilEffect.showProgressBar();
OrganizacionDetalleDWR.getPais(organizacionDetalleJS.getPaisCallback);
},
/*
 * Callback de la función getPais() esta función es llamada por DWR al 
 * terminar de realizar la operación.
 */
getPaisCallback : function( list ) {
var idCmb = jQuery( '#organizacionDetalleform_paisId' ).val();
jQuery( '#organizacionDetalleform_paisId' ).find( 'option' ).remove();
for (var i = 0; i < list.length; i++ ) {
var object = list[i];
var id = object.paisId;
var nombre = object.nombre;
jQuery( '#organizacionDetalleform_paisId' ).append('<option value="' + id + '">' + nombre + '</option>');
}
 // Si el usuario selecciono una opcion en el popup entonces ponerlo como seleccionado
var sel = parseInt(jQuery( '#idFromIframe' ).val());
if ( sel == 0 ) {
 // La opcion del popup seleccionada esta inactiva, validar la opcion seleccionada antes de abrir el popup
if ( jQuery( "#organizacionDetalleform_paisId option[value='" + idCmb + "']" ).length > 0 ) {
jQuery( '#organizacionDetalleform_paisId' ).val( idCmb );
} else {
jQuery( '#organizacionDetalleform_paisId' ).val( 0 );
}
} else {
 // Checar si la seleccion esta activa
if ( jQuery( "#organizacionDetalleform_paisId option[value='" + sel + "']" ).length > 0 ) {
jQuery( '#organizacionDetalleform_paisId' ).val( sel );
}
}
utilEffect.hideProgressBar();
},
/*
 * Función que obtene las opciones de un elemento select organizacionDetalleform_estadoId para recargarlo mediante AJAX.
 */
getEstado : function() {
utilEffect.showProgressBar();
OrganizacionDetalleDWR.getEstado(organizacionDetalleJS.getEstadoCallback);
},
/*
 * Callback de la función getEstado() esta función es llamada por DWR al 
 * terminar de realizar la operación.
 */
getEstadoCallback : function( list ) {
var idCmb = jQuery( '#organizacionDetalleform_estadoId' ).val();
jQuery( '#organizacionDetalleform_estadoId' ).find( 'option' ).remove();
for (var i = 0; i < list.length; i++ ) {
var object = list[i];
var id = object.estadoId;
var nombre = object.nombre;
jQuery( '#organizacionDetalleform_estadoId' ).append('<option value="' + id + '">' + nombre + '</option>');
}
 // Si el usuario selecciono una opcion en el popup entonces ponerlo como seleccionado
var sel = parseInt(jQuery( '#idFromIframe' ).val());
if ( sel == 0 ) {
 // La opcion del popup seleccionada esta inactiva, validar la opcion seleccionada antes de abrir el popup
if ( jQuery( "#organizacionDetalleform_estadoId option[value='" + idCmb + "']" ).length > 0 ) {
jQuery( '#organizacionDetalleform_estadoId' ).val( idCmb );
} else {
jQuery( '#organizacionDetalleform_estadoId' ).val( 0 );
}
} else {
 // Checar si la seleccion esta activa
if ( jQuery( "#organizacionDetalleform_estadoId option[value='" + sel + "']" ).length > 0 ) {
jQuery( '#organizacionDetalleform_estadoId' ).val( sel );
}
}
utilEffect.hideProgressBar();
},
/*
 * Función que obtene las opciones de un elemento select organizacionDetalleform_ciudadId para recargarlo mediante AJAX.
 */
getCiudad : function() {
utilEffect.showProgressBar();
OrganizacionDetalleDWR.getCiudad(organizacionDetalleJS.getCiudadCallback);
},
/*
 * Callback de la función getCiudad() esta función es llamada por DWR al 
 * terminar de realizar la operación.
 */
getCiudadCallback : function( list ) {
var idCmb = jQuery( '#organizacionDetalleform_ciudadId' ).val();
jQuery( '#organizacionDetalleform_ciudadId' ).find( 'option' ).remove();
for (var i = 0; i < list.length; i++ ) {
var object = list[i];
var id = object.ciudadId;
var nombre = object.nombre;
jQuery( '#organizacionDetalleform_ciudadId' ).append('<option value="' + id + '">' + nombre + '</option>');
}
 // Si el usuario selecciono una opcion en el popup entonces ponerlo como seleccionado
var sel = parseInt(jQuery( '#idFromIframe' ).val());
if ( sel == 0 ) {
 // La opcion del popup seleccionada esta inactiva, validar la opcion seleccionada antes de abrir el popup
if ( jQuery( "#organizacionDetalleform_ciudadId option[value='" + idCmb + "']" ).length > 0 ) {
jQuery( '#organizacionDetalleform_ciudadId' ).val( idCmb );
} else {
jQuery( '#organizacionDetalleform_ciudadId' ).val( 0 );
}
} else {
 // Checar si la seleccion esta activa
if ( jQuery( "#organizacionDetalleform_ciudadId option[value='" + sel + "']" ).length > 0 ) {
jQuery( '#organizacionDetalleform_ciudadId' ).val( sel );
}
}
utilEffect.hideProgressBar();
},
/*
 * Función que es activa el modal de busqueda.
 */
openSearch : function() {
utilSearch.openSearch( '#d-searchOrganizacionDetalle', '#organizacionDetalleGrid', '#organizacionDetalleform', organizacionDetalleJS, new OrganizacionDetalleDWR() );
},
/** 
 * Filtrar Estado
 * 
 */
filtrarEstado : function(){
utilEffect.showProgressBar();
OrganizacionDetalleDWR.filtrarEstado(
jQuery( '#organizacionDetalleform_paisId' ).val(),
organizacionDetalleJS.filtrarEstadoCallback );},
/** 
 * Callback de la función filtrarEstado(), esta
 * función es llamada por DWR al terminar de realizar la consulta.
 */
filtrarEstadoCallback : function( data ) {
if ( data == null || data.length == 0 ) {
utilInput.removeOptionsSelectAndAddDummy( '#organizacionDetalleform_estadoId', '--Seleccione--' );
} else {
utilInput.populateSelect( '#organizacionDetalleform_estadoId', data, 'estadoId','nombre' );
if (organizacionDetalleJS.organizacionDetalle !== null) {
if (organizacionDetalleJS.organizacionDetalle.estadoId !== null) {
if (utilInput.existsOptionByValueInSelect('#organizacionDetalleform_estadoId', organizacionDetalleJS.organizacionDetalle.estadoId)) {
jQuery('#organizacionDetalleform_estadoId').val(organizacionDetalleJS.organizacionDetalle.estadoId);
} else {
jQuery('#organizacionDetalleform_estadoId').val(0);
}
}
}
jQuery( '#organizacionDetalleform_estadoId' ).trigger( 'change' );
}
utilEffect.hideProgressBar();
},
/** 
 * Filtrar Ciudad
 * 
 */
filtrarCiudad : function(){
utilEffect.showProgressBar();
OrganizacionDetalleDWR.filtrarCiudad(
jQuery( '#organizacionDetalleform_estadoId' ).val(),
organizacionDetalleJS.filtrarCiudadCallback );},
/** 
 * Callback de la función filtrarCiudad(), esta
 * función es llamada por DWR al terminar de realizar la consulta.
 */
filtrarCiudadCallback : function( data ) {
if ( data == null || data.length == 0 ) {
utilInput.removeOptionsSelectAndAddDummy( '#organizacionDetalleform_ciudadId', '--Seleccione--' );
} else {
utilInput.populateSelect( '#organizacionDetalleform_ciudadId', data, 'ciudadId','nombre' );
if (organizacionDetalleJS.organizacionDetalle !== null) {
if (organizacionDetalleJS.organizacionDetalle.ciudadId !== null) {
if (utilInput.existsOptionByValueInSelect('#organizacionDetalleform_ciudadId', organizacionDetalleJS.organizacionDetalle.ciudadId)) {
jQuery('#organizacionDetalleform_ciudadId').val(organizacionDetalleJS.organizacionDetalle.ciudadId);
} else {
jQuery('#organizacionDetalleform_ciudadId').val(0);
}
}
}
jQuery( '#organizacionDetalleform_ciudadId' ).trigger( 'change' );
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
entity : null,
headers : null,
format : null,
reportName : null,
exportarDatos : function( entity, headers, sortBy, sortType, format ) {
organizacionDetalleJS.entity = entity;
organizacionDetalleJS.headers = headers;
organizacionDetalleJS.format = format;
organizacionDetalleJS.reportName = jQuery( '#key_organizaciondetalle_title' ).val() + 
'_' + utilMisc.getTodayDate( '-' );
var criteriaExample = new OrganizacionDetalleDWR();
if ( organizacionDetalleJS.cacheDWR != null ) {
criteriaExample = organizacionDetalleJS.cacheDWR;
}
 // utilEffect.showProgressBar();
jQuery().toastmessage('showToast', {
text : 'Filtrando información...',
sticky : false,
position : 'bottom-center',
type : 'notice',
closeText: '',
close : function () {
}
});
OrganizacionDetalleDWR.getReportDataTest( sortBy, sortType, criteriaExample, organizacionDetalleJS.exportarDatosCallback );
},
exportarDatosCallback : function( reportKey ) {
 // utilEffect.hideProgressBar();
jQuery().toastmessage('showToast', {
text : 'Generando reporte...',
sticky : false,
position : 'middle-center',
type : 'notice',
closeText: '',
close : function () {
}
});
window.location.href = '/admon/admon/exportarPDF.action?entity=' +
organizacionDetalleJS.entity + '&headers=' + organizacionDetalleJS.headers + 
'&reportName=' + organizacionDetalleJS.reportName + '&format=' + organizacionDetalleJS.format +
'&reportKey=' + reportKey;
}
};
/*
 * Funcion que setea el id del elemento seleccionado en la fila del jqgrid en un hidden cuando el grid
 * esta en un iframe de edicion externa. El id es utilizado para que al momento de cerrar el dialogo
 * se actualize su respectivo select y setear el valor en ese mismo select con el id que el usuario selecciono.
 */
function setIdFromIframe( id ) {
jQuery( '#idFromIframe' ).val( id );
}
 // Validaciones de organizacionDetalleform
validanguage.el.organizacionDetalleform_organizacionId = {
characters : { 
mode : 'allow', expression : 'numeric-', suppress : false },
validations : [
ProfileStripWhitespace,ProfileCheckForInvalidCharacter]};
validanguage.el.organizacionDetalleform_nombre = {
characters : { 
mode : 'allow', expression : 'alphanumericspecial ', suppress : false },
validations : [
ProfileStripWhitespace,ProfileRequiredField,ProfileCheckForInvalidCharacter]};
validanguage.el.organizacionDetalleform_rfc = {
characters : { 
mode : 'allow', expression : 'alphanumericspecial ', suppress : false },
validations : [
ProfileStripWhitespace,ProfileCheckForInvalidCharacter]};
validanguage.el.organizacionDetalleform_nombreComercial = {
characters : { 
mode : 'allow', expression : 'alphanumericspecial ', suppress : false },
validations : [
ProfileStripWhitespace,ProfileCheckForInvalidCharacter]};
validanguage.el.organizacionDetalleform_descripcion = {
characters : { 
mode : 'allow', expression : 'alphanumericspecial ', suppress : false },
validations : [
ProfileStripWhitespace,ProfileCheckForInvalidCharacter,{
name: function( text ) {
if ( text.length > 3750 ) {
return false;
} else {
return true;
}
},
errorMsg: 'Solo se permiten 3750 carácteres como máximo.',
onerror: utilEffect.showValidationTooltip,
onsuccess: utilEffect.hideValidationTooltip
}
]};
validanguage.el.organizacionDetalleform_nombreContacto = {
characters : { 
mode : 'allow', expression : 'alphanumericspecial ', suppress : false },
validations : [
ProfileStripWhitespace,ProfileCheckForInvalidCharacter]};
validanguage.el.organizacionDetalleform_correoContacto = {
characters : { 
mode : 'allow', expression : 'alphanumericspecial ', suppress : false },
validations : [
ProfileStripWhitespace,ProfileCheckForInvalidCharacter]};
validanguage.el.organizacionDetalleform_telefonoContacto = {
characters : { 
mode : 'allow', expression : 'alphanumericspecial ', suppress : false },
validations : [
ProfileStripWhitespace,ProfileCheckForInvalidCharacter]};
validanguage.el.organizacionDetalleform_celularContacto = {
characters : { 
mode : 'allow', expression : 'alphanumericspecial ', suppress : false },
validations : [
ProfileStripWhitespace,ProfileCheckForInvalidCharacter]};
validanguage.el.organizacionDetalleform_direccion = {
characters : { 
mode : 'allow', expression : 'alphanumericspecial ', suppress : false },
validations : [
ProfileStripWhitespace,ProfileCheckForInvalidCharacter]};
validanguage.el.organizacionDetalleform_direccionAlternativa = {
characters : { 
mode : 'allow', expression : 'alphanumericspecial ', suppress : false },
validations : [
ProfileStripWhitespace,ProfileCheckForInvalidCharacter]};
validanguage.el.organizacionDetalleform_rutaImagen = {
characters : { 
mode : 'allow', expression : 'alphanumericspecial ', suppress : false },
validations : [
ProfileStripWhitespace,ProfileCheckForInvalidCharacter]};
validanguage.el.organizacionDetalleform_paisId = {
characters : { 
mode : 'allow', expression : 'numeric-', suppress : false },
validations : [
ProfileStripWhitespace,ProfileCheckForInvalidCharacter]};
validanguage.el.organizacionDetalleform_estadoId = {
characters : { 
mode : 'allow', expression : 'numeric-', suppress : false },
validations : [
ProfileStripWhitespace,ProfileCheckForInvalidCharacter]};
validanguage.el.organizacionDetalleform_ciudadId = {
characters : { 
mode : 'allow', expression : 'numeric-', suppress : false },
validations : [
ProfileStripWhitespace,ProfileCheckForInvalidCharacter]};

