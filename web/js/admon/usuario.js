jQuery( document ).ready( function() {


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
utilInput.fixRadios( '#usuarioform' );

 // Configuraciones adicionales
jQuery.jstree._themes = '../css/jstreethemes/';
jQuery( '.paginas' ).hide();

 // Limpiar la página
//utilForm.reset( '#usuarioPaginaform' );
//jQuery( '.accesos' ).jstree('uncheck_all');

 // Iniciar la construccion del jstree
usuarioJS.obtienePaginas();

 // Limpiar al cambiar de perfil
jQuery( '#usuarioform_perfilId' ).change(function(){
jQuery( '.accesos' ).jstree('uncheck_all');
usuarioJS.obtieneAccesos();
});
// 
 // <editor-fold defaultstate="collapsed" desc="Botones">
 // Botón : NUEVO
jQuery( '#btnNewUsuario' ).click( function () {
utilForm.reset( '#usuarioform' );
jQuery( '.accesos' ).jstree('uncheck_all');
jQuery( '#usuarioform_usuarioId' ).val(0); 
usuarioJS.usuario = null;
}).customButtonEffect( '#btnNewUsuario' );
 // Botón : GUARDAR
jQuery( '#btnSaveUsuario' ).click( function () {
usuarioJS.prepareToSave();
}).customButtonEffectBlue( '#btnSaveUsuario' );
 // </editor-fold>

if ( jQuery( '#gridVisibleUsuario' ).val() == 'true' ) {
jQuery( '#usuarioGrid' ).jqGrid({
url      : '',
datatype : '',
colNames: [
jQuery( '#usuario_usuarioIdText' ).html(),
jQuery( '#usuario_usuarioText' ).html(),
jQuery( '#usuario_contrasenaText' ).html(),
jQuery( '#usuario_nombreText' ).html(),
jQuery( '#usuario_apellidoPaternoText' ).html(),
jQuery( '#usuario_apellidoMaternoText' ).html(),
jQuery( '#usuario_correoText' ).html(),
jQuery( '#usuario_perfilIdText' ).html(),
jQuery( '#usuario_estatusIdText' ).html(),
jQuery( '#usuario_eliminadoIdText' ).html(),
jQuery( '#CREACION_USUARIOFechaText' ).html(),
jQuery( '#CREACION_USUARIOUsuarioText' ).html(),
jQuery( '#usuario_modificacionFechaText' ).html(),
jQuery( '#usuario_modificacionUsuarioText' ).html(),
'','',''],
colModel : [{
name      : 'usuarioId',
index     : 'usuarioId',
align     : 'center',
resizable : false,
hidden    : true,
search    : false
},{
name      : 'usuario',
index     : 'usuario',
align     : 'center',
resizable : false,
hidden    : false,
search    : true
},{
name      : 'contrasena',
index     : 'contrasena',
align     : 'center',
resizable : false,
hidden    : true,
search    : false
},{
name      : 'nombre',
index     : 'nombre',
align     : 'center',
resizable : false,
hidden    : false,
search    : true
},{
name      : 'apellidoPaterno',
index     : 'apellidoPaterno',
align     : 'center',
resizable : false,
hidden    : false,
search    : true
},{
name      : 'apellidoMaterno',
index     : 'apellidoMaterno',
align     : 'center',
resizable : false,
hidden    : false,
search    : true
},{
name      : 'correo',
index     : 'correo',
align     : 'center',
resizable : false,
hidden    : false,
search    : true
},{
name      : 'perfilId',
index     : 'perfilId',
align     : 'center',
resizable : false,
hidden    : false,
search    : true
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
pager         : jQuery( '#usuarioGridPagerId' ),
caption       : jQuery( '#key_usuario_title' ).val()
});
 // jQuery UI Dialogs
utilDialog.setStandardDialog( '#d-removeUsuario', jQuery('#msgAlertaText').val(), jQuery('#msgDeseaEliminarText').val(), ['Aceptar@usuarioJS.remove()', 'Cancelar@'] );
utilDialog.setStandardDialog( '#d-removeSeleccionUsuario', jQuery('#msgAlertaText').val(), jQuery('#msgDeseaEliminarSeleccionText').val(), ['Aceptar@usuarioJS.removeSelected()', 'Cancelar@'] );
utilDialog.setStandardDialog( '#d-confirmSetEstatusUsuario', jQuery('#msgAlertaText').val(), jQuery('#msgCambiarEstatusText').val(), ['Aceptar@usuarioJS.setEstatusSeleccion()', 'Cancelar@'] );
utilDialog.setStandardDialog( '#d-noSelectedUsuario', jQuery('#msgAlertaText').val(), jQuery('#msgSeleccioneUnoOMas').val(), ['Aceptar@'] );
utilDialog.setStandardDialog( '#d-registroDuplicadoUsuario', jQuery('#msgAlertaText').val(), jQuery('#msgYaExisteRegistro').val(), ['Aceptar@'] );
utilDialog.setCustomDialogSelectColumnsToExport("#usuarioColumnasExportar", '#usuarioGrid');
utilDialog.setCustomDialogSetEstatus('#usuarioSetEstatusActivoInactivo', 
'usuarioJS.prepareToSetEstatus(1)', 'usuarioJS.prepareToSetEstatus(2)');
 // Diálogos
utilDialog.setStandardDialog( '#d-noSeleccion',
jQuery( '#key_usuariopagina_noselecciontitulo' ).val(),
jQuery( '#key_usuariopagina_noseleccionmensaje' ).val(), ['Aceptar@'] );

utilDialog.setStandardDialog( '#d-guardadoCorrecto',
jQuery( '#key_usuariopagina_guardadocorrectotitulo' ).val(),
jQuery( '#key_usuariopagina_guardadocorrectomensaje' ).val(), ['Aceptar@'] );

utilDialog.setStandardDialog( '#d-noHayAccesos',
jQuery( '#key_usuariopagina_nohayaccesostitulo' ).val(),
jQuery( '#key_usuariopagina_nohayaccesosmensaje' ).val(), ['Aceptar@'] );

utilDialog.setStandardDialog( '#d-seleccionUsuario',
jQuery( '#key_usuariopagina_seleccionusuariotitulo' ).val(),
jQuery( '#key_usuariopagina_seleccionusuariomensaje' ).val(), ['Aceptar@'] );

 // Inicializaciones extra para el grid
utilGrid.setup( '#usuarioGrid', '#usuarioGridPagerId', '#usuarioGridCurrentPage', '#usuarioGridOrderByColumn',
'#usuarioGridOrderByType', 'usuarioJS' );
jQuery( '#usuarioGrid' ).setGridWidth(909);

 // Crear el modal de busqueda
utilSearch.buildSearch( '#d-searchUsuario' );

 // Recargar el widget jqGrid
usuarioJS.reloadGrid();

}
 // Limpiar la página
jQuery( '#btnNewUsuario' ).click();
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
if ( jQuery( '#gridIndividualModeUsuario' ).val() == 'true' ) {
jQuery( '#btnNewUsuario' ).hide();
usuarioJS.findFirst();
}
});

var usuarioJS = {
/* Variable auxiliar para persistir un objeto (resultado de una consulta) 
 * obtenido mediante el callback de la función "findById(...)". Este objeto
 * es necesario para la funcionalidad de selects encadenados pero puede ser
 * utilizado para otros propósitos. El objeto existe solamente mientras se
 * esta editando la información en el formulario. Al presionar el botón
 * Nuevo o Guardar cualquier referencia a éste regresará "null". El 
 * objeto debe utilizarse como modo de "Solo Lectura" (la modificación de 
 * este objeto no se verá reflejada en la base de datos) */
usuario : null,
/*
 * Variable auxiliar para mantener las busquedas del grid al momento de paginar, ordenar, etc.
 */
cacheDWR : null,
/*
 * Función que guarda un nuevo registro en la BD.
 */
save : function() {
utilEffect.showProgressBar();
var usuario = utilObject.buildObject( '#usuarioform', new UsuarioDWR() );
usuario.usuarioId = 0;
var listaUsuarios = [ usuario ];
var paginas = [];
jQuery( '.accesos li' ).each(function(){
if (jQuery( this ).hasClass( 'jstree-undetermined' ) || jQuery( this ).hasClass( 'jstree-checked' ) ){
var id = jQuery( this ).attr( 'id' );
var idNumber = parseInt(id.substring(3,id.length));
paginas.push( idNumber );
}
});
UsuarioDWR.save( listaUsuarios, paginas, usuarioJS.saveOrUpdateCallback );
},
/*
 * Función que actualiza un registro existente en la BD.
 */
update : function() {
utilEffect.showProgressBar();
var usuario = utilObject.buildObject( '#usuarioform', new UsuarioDWR() );
usuario.usuarioId = jQuery( '#usuarioform_usuarioId' ).val();
var listaUsuarios = [ usuario ];
var paginas = [];
jQuery( '.accesos li' ).each(function(){
if (jQuery( this ).hasClass( 'jstree-undetermined' ) || jQuery( this ).hasClass( 'jstree-checked' ) ){
var id = jQuery( this ).attr( 'id' );
var idNumber = parseInt(id.substring(3,id.length));
paginas.push( idNumber );
}
});
UsuarioDWR.update( listaUsuarios, paginas, usuarioJS.saveOrUpdateCallback );
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
if ( jQuery( '#gridIndividualModeUsuario' ).val() == 'false' ) {
utilForm.reset( '#usuarioform' );
jQuery( '.accesos' ).jstree('uncheck_all');
jQuery( '#usuarioform_usuarioId' ).val( 0 ); 
usuarioJS.usuario = null;
usuarioJS.reloadGrid();
}
utilEffect.hideProgressBar();
utilEffect.showToast('success', jQuery('#msgGuardadoOk').val());
},
/*
 * Función que elimina un registro en el grid al presionar el botón eliminar.
 * Manda llamar al método <b>remove</b> de DWR, éste método 
 * espera siempre una lista de id's como parámetro.
 */
remove : function() {
utilEffect.showProgressBar();
var id = usuarioJS.getEliminarId();
var listaIds = [ id ];
UsuarioDWR.remove( listaIds, usuarioJS.removeCallback );
},
/*
 * Función que elimina los registros seleccionados en el grid al presionar el botón eliminar.
 * Manda llamar al método <b>remove</b> de DWR, éste método 
 * espera siempre una lista de id's como parámetro.
 */
removeSelected : function() {
utilEffect.showProgressBar();
var listaIds = utilGrid.gridGetSelectedRows( '#usuarioGrid' );
UsuarioDWR.remove( listaIds, usuarioJS.removeCallback );
},
/*
 * Callback de la función remove(), esta función es llamada por DWR al 
 * terminar de realizar la operación.
 */
removeCallback : function() {
jQuery( '#btnNewUsuario' ).click();
usuarioJS.reloadGrid();
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
jQuery( '.accesos' ).jstree('uncheck_all');
//usuarioJS.obtieneAccesos( id );
UsuarioDWR.findById( id, usuarioJS.findByIdCallback );
},
/*
 * Callback de la función findById(...), esta función es llamada por DWR al 
 * terminar de realizar la operación.
 * 
 * @param data (Object) Es un objeto con la información de la consulta.
 */
findByIdCallback : function( data ) {
utilForm.reset( '#usuarioform' );
utilForm.populate( '#usuarioform' , data );
usuarioJS.usuario = data;
var valContrasena = jQuery( '#usuarioform_contrasena' ).val();
jQuery( '#usuarioform_confirmarContrasena' ).val( valContrasena );
usuarioJS.obtieneAccesos();
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
usuarioJS.cacheDWR = obj;
var page = jQuery( '#usuarioGridCurrentPage' ).val();
var rows = jQuery( '#usuarioGridRowsByPage' ).val();
var order = jQuery( '#usuarioGridOrderByColumn' ).val();
var orderType = jQuery( '#usuarioGridOrderByType' ).val();
utilEffect.showProgressBar();UsuarioDWR.findByCriteria( page, rows, order, orderType, obj, usuarioJS.findByCriteriaCallback );
},
/*
 * Callback de la función findByCriteria(...), esta función es llamada por DWR al 
 * terminar de realizar la operación.
 * 
 * @param data (Object) Es un objeto (Grid) con la información de la 
 * consulta.
 */
findByCriteriaCallback : function( data ) {
utilGrid.gridUpdate( '#usuarioGrid', '#usuarioGridCurrentPage', '#usuarioGridRowsByPage', data );
utilEffect.hideProgressBar();
},
/**
 * Funcion utilizado cuando la pagina es individual
 */
findFirst : function() {
utilEffect.showProgressBar();
UsuarioDWR.findFirst( usuarioJS.findByIdCallback );
},
/*
 * Función que válida el <b>Usuario</b> de un registro en la BD. 
 * Se valida si ya existe un registro con el mismo usuario, esto con el 
 * propósito de evitar usuarios duplicados.
 */
isValidoUsuario : function() {
var usuario = jQuery( '#usuarioform_usuario' ).val();
utilEffect.showProgressBar();
UsuarioDWR.isValidoUsuario( usuario, usuarioJS.isValidoUsuarioCallback );
},
/*
 * Callback de la función isValidoUsuario(), esta función es llamada por DWR al 
 * terminar de realizar la operación.
 * 
 * @param isValido (boolean) es true cuando no existe ningún registro con
 * el usuario a validar, false cuando hay al menos un registro con el mismo
 * usuario.
 */
isValidoUsuarioCallback : function( isValido ) {
utilEffect.hideProgressBar();
 // Cuando se esta actualizando un registro pero no se cambia el usuario, 
 // la función isValidoUsuario() siempre regresa false al momento de 
 // intentar guardar por que el registro que se esta editando tiene el mismo 
 // usuario que en base de datos, en este caso se permite el commit.
if ( isValido == false && jQuery( '#usuarioform_usuarioId').val() != 0 ) {
usuarioJS.doCommit();
return;
}

 // Nuevo registro, en este caso se permite el commit
if ( isValido == true  && jQuery( '#usuarioform_usuarioId').val() == 0 ) {
usuarioJS.doCommit();
return;
}

 // Registro existente, se le cambio el usuario, en este caso se permite el commit
if ( isValido == true  && jQuery( '#usuarioform_usuarioId').val() != 0 ) {
usuarioJS.doCommit();
return;
}

 // Mostrar mensaje de registro duplicado 
utilDialog.showDialog( '#d-registroDuplicadoUsuario' );
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
UsuarioDWR.setEstatus( estatusId, listaObjetos, usuarioJS.setEstatusCallback );
},
/*
 * Función que cambia el Estatus de los elementos seleccionados en el widget
 * de jqGrid.
 * 
 */
setEstatusSeleccion : function() {
utilEffect.showProgressBar();
var estatusId = usuarioJS.estatusId;
var listaObjetos = utilGrid.gridGetSelectedRows( '#usuarioGrid' );
UsuarioDWR.setEstatus( estatusId, listaObjetos, usuarioJS.setEstatusCallback );
},
/*
 * Callback de la función setEstatus(), esta función es llamada por DWR al 
 * terminar de realizar la operación.
 * 
 */
setEstatusCallback : function() {
usuarioJS.reloadGrid();  
utilEffect.hideProgressBar();
},
/*
 * Función que muestra un mensaje de confirmación para Activar o Inactivar los registros
 * seleccionados del widget jQGrid.
 */
prepareToSetEstatus : function ( estatusId ) {
var seleccion = utilGrid.gridGetSelectedRows( '#usuarioGrid' )
if (seleccion.length == 0 ) {
utilDialog.showDialog( '#d-noSelectedUsuario' );
} else {
usuarioJS.estatusId = estatusId;
 // utilDialog.showDialog( '#d-confirmSetEstatusUsuario' );
usuarioJS.setEstatusSeleccion();
}
},
/*
 * Función que muestra un mensaje de confirmación para eliminar los registros seleccionados
 * del widget jQGrid.
 */
prepareToRemoveSelected : function() {
var seleccion = utilGrid.gridGetSelectedRows( '#usuarioGrid' )
if (seleccion.length == 0 ) {
utilDialog.showDialog( '#d-noSelectedUsuario' );
} else {
utilDialog.showDialog( '#d-removeSeleccionUsuario' );
}
},
/*
 * Función que muestra un mensaje de confirmación para eliminar un regristro
 * del widget jQGrid.
 */
prepareToRemove : function( id ) {
usuarioJS.setEliminarId( id );
utilDialog.showDialog( '#d-removeUsuario' );
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
usuarioJS.eliminarId = eliminarId;
},
/*
 * Getter de la variable eliminarId
 **/
getEliminarId : function() {
return usuarioJS.eliminarId;
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
var validation = validanguage.validateForm( 'usuarioform' );
if ( validation.result ) {
usuarioJS.isValidoUsuario();
} 
},
/*
 * Función que inicia el proceso para GUARDAR y ACTUALIZAR/MODIFICAR
 * Una vez que se pasaron las validaciones se realiza el commit.
 */
doCommit : function () {
if ( jQuery( '#usuarioform_usuarioId' ).val() == 0 ) {
usuarioJS.save();
} else {
usuarioJS.update();
}
}, 
/*
 * Función que es llamada para actualizar la información del grid.
 */
reloadGrid : function() {
if ( usuarioJS.cacheDWR != null ) {
usuarioJS.findByCriteria( usuarioJS.cacheDWR );
} else {
usuarioJS.findByCriteria( new UsuarioDWR() );
}
},
/*
 * Función que obtene las opciones de un elemento select usuarioform_perfilId para recargarlo mediante AJAX.
 */
getPerfil : function() {
utilEffect.showProgressBar();
UsuarioDWR.getPerfil(usuarioJS.getPerfilCallback);
},
/*
 * Callback de la función getPerfil() esta función es llamada por DWR al 
 * terminar de realizar la operación.
 */
getPerfilCallback : function( list ) {
var idCmb = jQuery( '#usuarioform_perfilId' ).val();
jQuery( '#usuarioform_perfilId' ).find( 'option' ).remove();
for (var i = 0; i < list.length; i++ ) {
var object = list[i];
var id = object.perfilId;
var nombre = object.nombre;
jQuery( '#usuarioform_perfilId' ).append('<option value="' + id + '">' + nombre + '</option>');
}
 // Si el usuario selecciono una opcion en el popup entonces ponerlo como seleccionado
var sel = parseInt(jQuery( '#idFromIframe' ).val());
if ( sel == 0 ) {
 // La opcion del popup seleccionada esta inactiva, validar la opcion seleccionada antes de abrir el popup
if ( jQuery( "#usuarioform_perfilId option[value='" + idCmb + "']" ).length > 0 ) {
jQuery( '#usuarioform_perfilId' ).val( idCmb );
} else {
jQuery( '#usuarioform_perfilId' ).val( 0 );
}
} else {
 // Checar si la seleccion esta activa
if ( jQuery( "#usuarioform_perfilId option[value='" + sel + "']" ).length > 0 ) {
jQuery( '#usuarioform_perfilId' ).val( sel );
}
}
utilEffect.hideProgressBar();
},
/*
 * Función que es activa el modal de busqueda.
 */
openSearch : function() {
utilSearch.openSearch( '#d-searchUsuario', '#usuarioGrid', '#usuarioform', usuarioJS, new UsuarioDWR() );
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
usuarioJS.entity = entity;
usuarioJS.headers = headers;
usuarioJS.format = format;
usuarioJS.reportName = jQuery( '#key_usuario_title' ).val() + 
'_' + utilMisc.getTodayDate( '-' );
var criteriaExample = new UsuarioDWR();
if ( usuarioJS.cacheDWR != null ) {
criteriaExample = usuarioJS.cacheDWR;
}
 // utilEffect.showProgressBar();
jQuery().toastmessage('showToast', {
text : jQuery('#msgFiltrandoInformacion').val(),
sticky : false,
position : 'bottom-center',
type : 'notice',
closeText: '',
close : function () {
}
});
UsuarioDWR.getReportDataTest( sortBy, sortType, criteriaExample, usuarioJS.exportarDatosCallback );
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
usuarioJS.entity + '&headers=' + usuarioJS.headers + 
'&reportName=' + usuarioJS.reportName + '&format=' + usuarioJS.format +
'&reportKey=' + reportKey;
},
/*
* Obtiene una lista de todas las paginas a las que un determinado usuario tiene acceso.
*
*/
obtieneAccesos : function(){
utilEffect.showProgressBar();
var usuario = utilObject.buildObject( '#usuarioform', new UsuarioDWR() );
var perfilId = usuario.perfilId;
if (perfilId === 0) {
utilEffect.hideProgressBar();    
return;
}
UsuarioDWR.obtieneAccesos(perfilId, usuarioJS.obtieneAccesosCallback);
},
/*
* Callback de la función obtieneAccesos(), esta función es llamada por DWR al 
* terminar de realizar la operación.
* 
* @param paginas (List<Pagina>) es una lista de todos los registros válidos (activos y 
* no eliminados) de la tabla PAGINA.
*/
obtieneAccesosCallback : function( paginas ) {
utilEffect.hideProgressBar();
if (paginas.length == 0 || paginas == null){
utilDialog.showDialog( '#d-noHayAccesos' );
} else {
jQuery( '.accesos' ).jstree('uncheck_all');
for (var i = 0; i < paginas.length; i++) {
if ( jQuery('#pag' + paginas[i] ).hasClass( 'esMenu' ) ){
 // No llamar check_node en menus, ya que al hacerlo se seleccionan
 // todos los child nodes
 // Si no tiene hijos pero si tiene acceso al menu entonces seleccionar el menu
 // ya que no tiene hijos y es seguro ponerlo en checked
var children = jQuery.jstree._reference( '.accesos' )._get_children( '#pag' + paginas[i] );
if ( children.length == 0 ) {
jQuery.jstree._reference( '.accesos' ).check_node( '#pag' + paginas[i] );
}
} else {
jQuery.jstree._reference( '.accesos' ).check_node( '#pag' + paginas[i] );
}
}
}
},
/*
* Función que Obtiene una lista de todos los registros válidos (activos y 
* no eliminados) de la tabla PAGINA.
*/
obtienePaginas : function() {
UsuarioDWR.obtienePaginas(usuarioJS.obtienePaginasCallback)
},
/*
* Callback de la función obtienePaginas(), esta función es llamada por DWR al 
* terminar de realizar la operación.
* 
* @param paginas (List<Pagina>) es una lista de todos los registros válidos (activos y 
* no eliminados) de la tabla PAGINA.
*/
obtienePaginasCallback : function( paginas ) {
if ( paginas != null ){
var i, pagina, id;
 // Fase 1
for (i = 0; i < paginas.length; i++) {
pagina = paginas[i];
var nombre = pagina.nombre;
id = pagina.paginaId;
var url = pagina.anidar;
var esMenu = '';
if (url == ''){
esMenu = " class='esMenu '";
}
var html = '';
html += "<li id='pag" + id + "' " + esMenu + ">";
html += "<a href='#'>";
html += nombre + "</a>";
html +="</li>";
jQuery( '.paginas' ).append(html);
}

 // Fase 2
for (i = 0; i < paginas.length; i++) {
pagina = paginas[i];
var parentId = pagina.anidar;
id = pagina.paginaId;
if ( utilError.checkById( '#pag' + parentId ) ) {
jQuery( '#pag' + id ).appendTo( '#pag' + parentId );
}
}
 // Fase 3
jQuery( '.paginas li' ).each(function(){
var parentTag = jQuery( this ).parent().is( 'li' );
if (parentTag){
jQuery( this ).parent().find( 'li' ).wrapAll( '<ul />' );
}
});
 // Fase 4
jQuery( '.accesos' ).jstree({
'html_data' : {
'data' : jQuery( '.paginas' ).html()
},
"themes" : {
"theme" : "classic",
"icons" : false
},
'plugins' : [ 'themes', 'html_data', 'checkbox', 'sort', 'ui' ]
}).on('loaded.jstree', function() {
jQuery( '.accesos' ).jstree('open_all');
jQuery( '.accesos' ).off('click');
});
}
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
 // Validaciones de usuarioform
validanguage.el.usuarioform_usuario = {
characters : { 
mode : 'allow', expression : 'alphanumericspecial ', suppress : false },
validations : [
ProfileStripWhitespace,ProfileRequiredField,ProfileCheckForInvalidCharacter]};
validanguage.el.usuarioform_contrasena = {
characters : { 
mode : 'allow', expression : 'alphanumericspecial', suppress : false },
validations : [
ProfileStripWhitespace,ProfileRequiredField,ProfileCheckForInvalidCharacter]};
validanguage.el.usuarioform_confirmarContrasena = {
characters : { 
mode : 'allow', expression : 'alphanumericspecial', suppress : false },
validations : [
ProfileStripWhitespace,ProfileRequiredField,ProfileCheckForInvalidCharacter,{name: function() {return !!(this.value == jQuery( '#usuarioform_contrasena' ).val());},errorMsg: 'La contraseña es diferente',onerror: utilEffect.showValidationTooltip,onsuccess: utilEffect.hideValidationTooltip}]};
validanguage.el.usuarioform_nombre = {
characters : { 
mode : 'allow', expression : 'alphanumericspecial ', suppress : false },
validations : [
ProfileStripWhitespace,ProfileRequiredField,ProfileCheckForInvalidCharacter]};
validanguage.el.usuarioform_apellidoPaterno = {
characters : { 
mode : 'allow', expression : 'alphanumericspecial ', suppress : false },
validations : [
ProfileStripWhitespace,ProfileRequiredField,ProfileCheckForInvalidCharacter]};
validanguage.el.usuarioform_apellidoMaterno = {
characters : { 
mode : 'allow', expression : 'alphanumericspecial ', suppress : false },
validations : [
ProfileStripWhitespace,ProfileCheckForInvalidCharacter]};
validanguage.el.usuarioform_correo = {

validations : [
ProfileStripWhitespace,ProfileRequiredField,ProfileMail]};
validanguage.el.usuarioform_perfilId = {
characters : { 
mode : 'allow', expression : 'numeric-', suppress : false },
validations : [
ProfileStripWhitespace,ProfileRequiredField,ProfileCheckForInvalidCharacter]};

