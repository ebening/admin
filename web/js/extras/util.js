/*
 * util.js  - v.1.0.
 * Este archivo contiene diferentes objetos con funciones utiles para el manejo
 * de muchas tareas comunes, con esto se reutiliza codigo y se reduce
 * la complejidad de los archivos js.
 */


// Plugin jquery que que centra un elemento en pantalla
jQuery.fn.center = function () {
    this.css("position","absolute");
    this.css("top", Math.max(0, ((jQuery(window).height() - jQuery(this).outerHeight()) / 2) +
        jQuery(window).scrollTop()) + "px");
    this.css("left", Math.max(0, ((jQuery(window).width() - jQuery(this).outerWidth()) / 2) +
        jQuery(window).scrollLeft()) + "px");
    return this;
}

// Plugin jquery que cambia el tag de un elemento
jQuery.fn.changeElementType = function( newType ) {
    var attrs = {};
    jQuery.each(this[0].attributes, function(idx, attr) {
        attrs[attr.nodeName] = attr.nodeValue;
    });
    this.replaceWith(function() {
        return jQuery('<' + newType + '/>', attrs).append(jQuery(this).contents());
    });
}

jQuery.fn.customButtonEffect = function (id) {
//    jQuery(id).css({
//        'background-color': '#EFF2F5',
//        'color': '#5F6468'
//    }).hover(
//        // Hover state
//        function () {
//            jQuery(this).css({
//                'background-color': '#EDF0F5',
//                'color': '#5F6468'
//            });
//        },
//        // Normal state
//        function () {
//            jQuery(this).css({
//                'background-color': '#EFF2F5',
//                'color': '#5F6468'
//        });
//    });
};

jQuery.fn.customButtonEffectBlue = function (id) {
//    jQuery(id).css({
//        'color': '#fff',
//        'background-color': '#30A5FF'
//    }).hover(
//        // Hover state
//        function () {
//            jQuery(this).css({
//                'background-color': '#59B7FF',
//                'color': '#fff'
//            });
//        },
//        // Normal state
//        function () {
//            jQuery(this).css({
//                'background-color': '#30A5FF',
//                'color': '#fff'
//        });
//    });
};

var utilMisc = {
    getTodayDate : function ( sep ) {
        var d = new Date();
        var month = d.getMonth()+1;
        var day = d.getDate();
        var output = ( ( '' + day ).length < 2 ? '0' : '') + day + sep +
        ( ( '' + month ).length < 2 ? '0' : '') + month + sep + d.getFullYear();
        return output;
    },
    /**
     * Funcion que prepara los valores necesarios antes de exportar a PDF
     *
     * @param gridId (String)
     * @param dialogId (String)
     */
    prepareToExport : function ( gridId, dialogId ) {
        // Nombre del entity en Java (ejemplo: CatalogoParametro, Usuario, Pagina, etc.)
        // Tomando ventaja de los estandares en nombres tomados por el Core, el nombre del entity se 
        // puede obtener del parametro "gridId" de esta funcion. Por ejemplo
        // #catalogoParametroGrid => CatalogoParametro
        var entity = utilString.setfirstLetterToUpperCase( utilString.removeGrid(utilString.removeSharp(gridId)));
        // Nombre del objeto javascript que contiene la funcion exportarPDF(...)
        var objectJS = utilString.setfirstLetterToLowerCase(entity);

        var format = jQuery( dialogId + ' input[name=format]:checked' ).val();
        var headers = '';
        var sortBy = jQuery( dialogId ).find( 'select' + dialogId + 'sortBy' ).val();
        var sortType = jQuery( dialogId ).find( 'select' + dialogId + 'sortType' ).val();
        
        jQuery( dialogId ).find( 'input:checkbox' ).each( function() {
            if ( jQuery( this ).is( ':checked' ) ) {
                // En el reporte 'header' es el encabezado de la columna
                var header = jQuery( this )[0].nextSibling.nodeValue;
                // En el reporte 'index' es la propieadad del Entity para obtener el valor
                var index = jQuery( this ).val()
                if ( headers == '' ) {
                    headers = utilString.fixString( header ) + '@' + index;
                } else {
                    headers = headers + '-' + utilString.fixString( header ) + '@' + index;
                }
            }
        });
        // Llamar a la función exportarPDF que se encarga de iniciar la generación del PDF
        eval( objectJS + 'JS.exportarDatos("' + entity + '","' + headers + '","' + 
            sortBy + '","' + sortType + '","' + format + '")');
    },
    /**
     * Funcion que cambia el estatus de los registros seleccionados. El estatus 
     * puede ser Activo o Inactivo
     * 
     * @param dialogId es el id del dialogo
     * @param funcionEstatusActivo es un string que contiene la funcion para 
     * cambiar el estatus a Activo
     * @param funcionEstatusInactivo es un string que contiene la funcion para 
     * cambiar el estatus a Inactivo
     */
    prepareToSetEstatus : function ( dialogId, funcionEstatusActivo, funcionEstatusInactivo ) {
        var estatus = jQuery( dialogId + ' input[name=estatusActivoInactivo]:checked' ).val();
        if ( estatus == 'activo' ) {
            eval( funcionEstatusActivo );
        }
        if ( estatus == 'inactivo' ) {
            eval( funcionEstatusInactivo );
        }
    }
}

var utilCookie = {
    getCookie : function (c_name) {
        var c_value = document.cookie;
        var c_start = c_value.indexOf(" " + c_name + "=");
        if (c_start == -1) {
            c_start = c_value.indexOf(c_name + "=");
        }
        if (c_start == -1) {
            c_value = null;
        } else {
            c_start = c_value.indexOf("=", c_start) + 1;
            var c_end = c_value.indexOf(";", c_start);
            if (c_end == -1) {
                c_end = c_value.length;
            }
            c_value = unescape(c_value.substring(c_start,c_end));
        }
        return c_value;
    },
    setCookie : function( c_name, value, exdays, path ) {
        var exdate = new Date();
        exdate.setDate( exdate.getDate() + exdays );
        var c_value = escape( value ) + ( ( exdays == null ) ? '' : ';expires=' + exdate.toUTCString() );
        document.cookie = c_name + '=' + c_value + ';path=' + path;
    }
}

var utilScroll = {
    viewMarginTop : 0,
    headerHeight : 0,
    ini : function() {
        utilScroll.viewMarginTop = parseInt( jQuery( '.view' ).css( 'margin-top' ) );
        utilScroll.headerHeight = parseInt( jQuery( '.header' ).height() );
    },
    scroll : function() {
		utilMenu.close();
        if ( utilCookie.getCookie( 'headerstate' ) == 'max' ) {
            if ( jQuery( window ).scrollTop() > 69 ) {
                jQuery( '.menuHorizontal' ).addClass( 'fixed-menu' );
                jQuery( '.header' ).height( 92 );
            } else {
                jQuery( '.menuHorizontal' ).removeClass( 'fixed-menu' );
                jQuery( '.header' ).height( utilScroll.headerHeight );
            }
        } else {
            if ( jQuery( window ).scrollTop() > utilScroll.viewMarginTop ) {
                jQuery( '.menuHorizontal' ).addClass( 'fixed-menu' );
                jQuery( '.view' ).css( 'margin-top', '45px' );
                jQuery( '#maximize-small' ).hide();
            } else {
                jQuery( '.menuHorizontal' ).removeClass( 'fixed-menu' );
                jQuery( '.view' ).css( 'margin-top', utilScroll.viewMarginTop + 'px' );
                jQuery( '#maximize-small' ).show();
            }
        }
    }
}

var utilMinMax = {
    speed       : 'fast',
    menuHorizontalContainer: '.menuHorizontal',
    active : true,
    cookieKey : 'headerstate',
    stateMinVal : 'min',
    stateMaxVal : 'max',
    getContext : function() {
        return window.location.pathname.substring(
            0, window.location.pathname.indexOf( '/', 2 ) ) + '/';
    },
    /**
     * Función que se ejecuta al cargar una página, determina mediante una cookie
     * el estatus del encabezado (minimizado o maximizado) de la pagina previa
     * para mostrarlo en la página actual.
     */
    checkState: function() {
        var headerstate = utilCookie.getCookie( utilMinMax.cookieKey );
        if ( headerstate == null ) {
            utilCookie.setCookie( utilMinMax.cookieKey, utilMinMax.stateMaxVal, 
                365, utilMinMax.getContext() );
            jQuery( '.sesion-container-small' ).hide();
        } else {
            if ( headerstate == utilMinMax.stateMinVal ) {
                utilMinMax.minimizar();
            } else {
                // Maximizar
                jQuery( '.sesion-container-small' ).hide();
            }
        }
    },
    // Prepara para minimizar
    prepararParaMinimizar: function () {
        utilForm.hideTooltips();
        jQuery( utilMinMax.menuHorizontalContainer ).removeClass( 'menuHorizontalFix ui-corner-all' );
        jQuery( utilMinMax.menuHorizontalContainer ).addClass( 'ui-corner-all' );
    },
    minimizar : function() {
        if (utilMinMax.active) {
            utilMinMax.active = false;
            utilMinMax.prepararParaMinimizar();
            jQuery( '.sesion-container' ).hide();
            jQuery( '.header' ).toggle({
                duration : utilMinMax.speed,
                done: function() {
                    utilMinMax.mostrarUsuarioMinInfo();
                    utilCookie.setCookie( utilMinMax.cookieKey, 
                        utilMinMax.stateMinVal, 365, utilMinMax.getContext() );
                    utilMinMax.active = true;
                    utilScroll.scroll();
                }
            });
        }
    },
    // Prepara para maximizar
    prepararParaMaximizar : function (){
        utilForm.hideTooltips();
        jQuery( utilMinMax.menuHorizontalContainer ).removeClass( 'ui-corner-all' );
        jQuery( utilMinMax.menuHorizontalContainer ).addClass( 'menuHorizontalFix ui-corner-all' );
    },
    maximizar : function() {
        if (utilMinMax.active) {
            utilMinMax.active = false;
            utilMinMax.prepararParaMaximizar();
            jQuery( '.header' ).toggle({
                duration : utilMinMax.speed,
                done: function() {
                    jQuery( '.sesion-container' ).show();
                    utilMinMax.ocultarUsuarioMinInfo();
                    utilCookie.setCookie( utilMinMax.cookieKey, 
                        utilMinMax.stateMaxVal, 365, utilMinMax.getContext() );
                    utilMinMax.active = true;
                    utilScroll.scroll();
                }
            });
        }
    },
    mostrarUsuarioMinInfo: function () {
        // Reducir el ancho del menu
        jQuery( '.root-menu-container' ).width( jQuery( '.root-menu-container' ).width() - jQuery( 'sesion-container-small' ).outerWidth() );
        utilMenu.checkRootMenuContainerWidth();
        jQuery( '.sesion-container-small' ).css({
            'visibility' : 'visible',
            'display' : 'none'
        }).fadeIn( 'fast' );
    },
    ocultarUsuarioMinInfo : function () {
        jQuery( '.sesion-container-small' ).fadeOut( utilMinMax.speed , function(){
            // Restaurar el ancho del menu
            jQuery( '.root-menu-container' ).width( jQuery( '.root-menu-container' ).width() + jQuery( 'sesion-container-small' ).outerWidth() );
            utilMenu.checkRootMenuContainerWidth();
            jQuery( '.' + utilMinMax.usuarioMinInfoContainer ).remove();
        });
    }
}

/**
 * Objeto con funciones utiles para mensajes de dialogo con JQuery.
 */
var utilDialog = {
    /**
     * Invoca un dialogo jquery previamente creado.
     *
     * @param dialogId (String) Es el nombre del dialogo que se quiere mostrar.
     */
    showDialog : function ( dialogId ) {
        if ( utilError.checkById( dialogId ) == true ) {
            jQuery( dialogId ).dialog( 'open' );
        } else {
            alert("Mensaje de util.js/utilDialog.showDialog: Se intento abrir un dialogo jQuery con id: " + dialogId + ", este id no existe.");
        }
    },
	isAnyDialogOpen: function() {
        return jQuery('.ui-dialog').is(':visible');
    },
    /**
     * Construye un dialogo modal estandar mediante jQuery.
     *
     * @param dialogId (String) Es el id del dialogo que se va a crear.
     * @param title (String) Titulo del mensaje.
     * @param content (String) Contenido del mensaje (puede ser un string o codigo html).
     * @param calls (Array) Es un array de strings, cada string debe tener
     * el formato nombreBoton@nombreFuncion (se usa "@" como separador).
     *      nombreBoton: nombre del boton
     *      nombreFuncion: es el codigo o funcion que se ejecuta al presionar el boton.
     */
    setStandardDialog : function( dialogId, title, content, calls ) {
        if ( utilError.checkById( dialogId ) == true ) {
            utilError.printWarn("Mensaje de util.js/utilDialog.setStandardDialog: Se intento crear un dialogo jQuery con id: " + dialogId + ", este id ya esta en uso.");
        }

        // Validar requerimientos
        if ( utilError.checkById( '#jquery_dialogs' ) == false ) {
            jQuery( 'body' ).append( '<div id="jquery_dialogs" style="display: none;"></div>' );
        }

        // Remover el simbolo "#" del nombre del id para asignarlo al atributo "id" de un div
        var dialogIdSinSharp = utilString.removeSharp( dialogId );

        // Construir el dialog para HTML
        var html_dialog = "<div id='" + dialogIdSinSharp + "' title='" + title + "'>";
        // Icono
        html_dialog += "<span style='float: left;' class='ui-icon ui-icon-info'></span>";
        // Contenido
        html_dialog += "<div style='float: right; width:90%;' class='d-content'>" + content + "</div>";
        html_dialog += "</div>";
        jQuery( '#jquery_dialogs' ).append( html_dialog );

        // Construir los botones
        var logica = '';
        for (var i = 0; i < calls.length; i++ ) {
            var nombreBoton = calls[i].split( "@" )[ 0 ];
            var ejecutar = calls[i].split( "@" )[ 1 ];
            if ( i != 0 ) {
                logica += ",";
            }
            if ( ejecutar != "" ) {
                ejecutar += ";";
            }
            logica += "'" + nombreBoton + "' : function() {" + ejecutar + " jQuery( this ).dialog( 'close' ); }"
        }

        // Construir el dialog para jQuery
        var jquery_dialogo = "jQuery( '" + dialogId + "' ).dialog({";
        // Propiedades
        jquery_dialogo += "autoOpen     : false,";
        jquery_dialogo += "resizable    : false,";
        jquery_dialogo += "modal        : true,";
//        jquery_dialogo += "hide         : 'fade',";
//        jquery_dialogo += "show         : 'highlight',";
        // Eventos
        // Evento open
        jquery_dialogo += "open: function( event, ui ) {";
        jquery_dialogo += "jQuery( dialogId ).dialog( 'option', 'position', 'center' );"
        jquery_dialogo += "},";
        // Botones
        jquery_dialogo += "buttons: {";
        jquery_dialogo += logica
        jquery_dialogo += "}";
        jquery_dialogo += "});";

        // Construir el dialogo
        eval( jquery_dialogo );

        // Regresar el id del dialogo que se creo
        return dialogId;
    },
    /**
     * Funcion que cambia el estatus de los registros seleccionados. El estatus 
     * puede ser Activo o Inactivo
     * 
     * @param dialogId es el id del dialogo
     * @param funcionEstatusActivo es un string que contiene la funcion para 
     * cambiar el estatus a Activo
     * @param funcionEstatusInactivo es un string que contiene la funcion para 
     * cambiar el estatus a Inactivo
     */
    setCustomDialogSetEstatus : function ( dialogId, funcionEstatusActivo, funcionEstatusInactivo ) {
        // Titulo del mensaje
        var title = "Cambiar estatus";
        // Empezar a construir el contenido
        var content = '';
        content += '<div style="border-bottom: 1px solid #DDDDDD; padding-bottom: 5px; margin-bottom: 5px;">';
        content += '<b>Seleccione el Estatus</b><br>';
        content += '</div>';
        content += '<input type="radio" name="estatusActivoInactivo" id="eActivo" value="activo" checked="checked">Activar&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
        content += '<input type="radio" name="estatusActivoInactivo" value="inactivo">Inactivar';
        
        // Calls
        var calls = [
        "Aceptar@utilMisc.prepareToSetEstatus( '" + dialogId + "', '" + funcionEstatusActivo + "', '" + funcionEstatusInactivo + "' );jQuery('#eActivo').click();",
        "Cancelar@jQuery('#eActivo').click();"];
    
        // Construir el dialogo
        utilDialog.setStandardDialog( dialogId, title, content, calls );
    },
    /**
     * Funcion que construye un dialogo especial con la funcionalidad de 
     * seleccion de columnas en el widget de jqgrid, la seleccion de columnas
     * es necesaria para saber que columnas se van a mostrar cuando la informacion 
     * del grid se va a exportar a formato PDF
     * 
     * @param dialogId (String) Es el id del dialogo que se va a crear.
     * @param gridId (String) Es el id del grid. Es util para extraer la
     * informacion de las columnas.
     */
    setCustomDialogSelectColumnsToExport : function( dialogId, gridId ){
        // Titulo del mensaje
        var title = "Exportar datos";

        // Obtener los nombres de las columnas
        var columnNames = utilError.validateFields( jQuery( gridId ).jqGrid( 'getGridParam', 'colNames' ) );
        // Obtener las propiedades de las columnas ( para saber si estan ocultas o mostradas )
        var columnModel = utilError.validateFields( jQuery( gridId ).jqGrid( 'getGridParam', 'colModel' ) );

        // Empezar a construir el contenido
        var content = '';
        content += '<div style="border-bottom: 1px solid #DDDDDD; padding-bottom: 5px; margin-bottom: 5px;">';
        content += '<b>Formato</b><br>';
        content += '</div>';
        content += '<input type="radio" name="format" value="pdf" checked="checked">PDF&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
        content += '<input type="radio" name="format" value="excel">Excel';
        
        content += '<br><br>';
        content += '<div style="border-bottom: 1px solid #DDDDDD; padding-bottom: 5px; margin-bottom: 5px;">';
        content += '<b>Columnas</b>';
        content += '</div>';
        
        

        var options = '';
        var checked = 'selected="selected"';
        /**
         * Iterar sobre los nombres de las columnas
         * Omitir la columna "EliminadoId", "Fecha Creacion", "Creación Usuario", 
         * "Modificación Fecha", "Modificación Usuario" (columnNames.length - 6)
         */
        var isValid = true;
        content += '<div style="height: 150px; overflow-y: scroll;">';
        for ( var i = 0; i < columnNames.length; i++ ) {
            var nombreColumna = columnModel[i].name;
            var nombreEncabezado = columnNames[i];
            if (nombreColumna == 'State') {
                isValid = false;
            }
            if (isValid) {
                if (!utilString.validaContrasena(nombreEncabezado)) {
                    options += '<option value="' + nombreColumna + '" ' + checked + ' >' + utilString.fixString(nombreEncabezado) + '</option>'
                    checked = '';
                    content += "<input type='checkbox' value='" + nombreColumna + "' checked>" + nombreEncabezado + "<br>";
                }
            }
        }
        content += '</div>';

        content += '<br><div style="border-bottom: 1px solid #DDDDDD; padding-bottom: 5px; margin-bottom: 5px;">';
        content += '<b>Opciones</b>';
        content += '</div>';
        content += 'Ordenar por:<br>';
        content += '<select id="' + utilString.removeSharp( dialogId ) + 'sortBy" class="select" style="width: 100%;">';
        content += options;
        content += '</select>';
        content += '<br><br>Tipo:<br>';
        content += '<select id="' + utilString.removeSharp( dialogId ) + 'sortType" class="select" style="width: 100%;">';
        content += '<option value="desc" selected="selected" >Descendente</option>';
        content += '<option value="asc" >Ascendente</option>';
        content += '</select>';

        // Calls
        var calls = [
        "Aceptar@utilMisc.prepareToExport('" + gridId + "','"  + dialogId + "');",
        "Cancelar@" ];

        // Construir el dialogo
        utilDialog.setStandardDialog( dialogId, title, content, calls );
        
        /*
         * Establecer la cantidad minima de columnas que debe ser mostradas,
         * este chequeo se hace cada vez que se hace clic en el dialogo
         * mediante el evento "click". Si la cantidad de columnas es menor
         * que el minimo se deshabilita el boton "Aceptar".
         *
         */
        var minimo = 1;
        jQuery( dialogId + ' input:checkbox' ).click( function() {
            // Corregir el criterio "Ordenar por"
            var seleccionSelectActual = jQuery( dialogId ).find( 'select' ).first().val();
            // Remover todas las opciones del select
            jQuery( dialogId ).find( 'select' ).first().find( 'option' ).remove();
            // Obtener todas los checkbox seleccionados y agregarlos al select
            jQuery( dialogId ).find( 'input:checkbox:checked' ).each( function(){
                var value = jQuery( this ).val();
                var text = jQuery( this )[0].nextSibling.nodeValue;
                jQuery( dialogId ).find( 'select' ).first().append('<option value="' + value + '">' + text + '</option>');
            });
            // Buscar en el select la seleccion actual, si no existe seleccionar la primera opcion de la lista
            var opcion = jQuery( dialogId + ' option[value="' + seleccionSelectActual + '"]');
            if ( opcion.length == 0 ){
                jQuery( dialogId ).find( 'select' ).first().find( 'option' ).first().attr('selected', 'selected');
            } else {
                jQuery( dialogId + ' option[value="' + seleccionSelectActual + '"]').attr('selected', 'selected');
            }
            
            // Activar/Desactivar el boton Aceptar segun la seleccion minima de columnas
            var numb = jQuery( dialogId ).find( 'input:checkbox:checked' );
            if ( numb.length == 0 ) {
                jQuery( dialogId ).find( 'select' ).first().attr( 'disabled', 'disabled' );
            } else {
                jQuery( dialogId ).find( 'select' ).first().removeAttr( 'disabled' );
            }
            if ( numb.length <= minimo ) {
                jQuery( dialogId ).next().find( "button" ).first().button({
                    disabled : true
                });
            } else {
                jQuery( dialogId ).next().find( "button" ).first().button({
                    disabled : false
                });
            }
        });
    },
    /**
     * Funcion que construye un dialogo especial con la funcionalidad de
     * Mostrar/Ocultar columnas en el widget jqGrid.
     *
     * @param dialogId (String) Es el id del dialogo que se va a crear.
     * @param gridId (String) Es el id del grid. Es util para extraer la
     * informacion de las columnas.
     */
    setCustomDialogGridShowHideColumns : function( dialogId, gridId ) {

        // Titulo del mensaje
        var title = "Mostrar/Ocultar columnas";

        // Obtener los nombres de las columnas
        var columnNames = utilError.validateFields( jQuery( gridId ).jqGrid( 'getGridParam', 'colNames' ) );
        // Obtener las propiedades de las columnas ( para saber si estan ocultas o mostradas )
        var columnModel = utilError.validateFields( jQuery( gridId ).jqGrid( 'getGridParam', 'colModel' ) );

        // Empezar a construir el contenido
        var content = '';
        content += '<div style="border-bottom: 1px solid #DDDDDD; padding-bottom: 10px;">';
        content += 'Seleccione las columnas a mostrar.</div><br>';

        /**
         * Iterar sobre los nombres de las columnas
         * Omitir la columna "multiple selection" y "id" (i = 2)
         * Omitir la columna "Editar" (columnNames.length - 1)
         */
        for ( var i = 0; i < columnNames.length; i++ ){
            // Checkboxes
            if (columnModel[i].hidden){
                // Desmarcar las columnas ocultas
                content += "<input type='checkbox' value='" + columnModel[i].name + "'>" + columnNames[i] + "<br>";
            } else {
                // Marcar las columnas mostradas
                content += "<input type='checkbox' value='" + columnModel[i].name + "' checked>" + columnNames[i]+ "<br>";
            }
        }

        // Calls
        var calls = [
        "Aceptar@utilGrid.setColumnVisibility('" + gridId + "','"  + dialogId + "');",
        "Limpiar@jQuery( '"  + dialogId + "' ).find( 'input' ).attr( 'checked', false ); jQuery( '"  + dialogId + "' ).click(); return;",
        "Cancelar@" ];

        // Construir el dialogo
        utilDialog.setStandardDialog( dialogId, title, content, calls );

        // Acciones al abrir y cerrar el dialogo
        jQuery( dialogId ).dialog({
            open: function( event, ui ) {
                jQuery( dialogId ).click();
                jQuery( dialogId ).dialog( "option", "position", "center" );
            },
            close: function( event, ui ) {
                /**
                 * Al Aceptar/Cancelar hay que revisar cuales campos estan mostrados en el widget jqGrid
                 * para saber cuales checkboxes hay que marcar, asi cuando el usuario abre nuevamente
                 * el dialogo de Mostrar/Ocultar columnas ve los elementos marcados correctamente
                 */
                var elements = utilError.validateFields( jQuery( gridId ).jqGrid( 'getGridParam', 'colModel' ) );
                for ( var i = 0; i < elements.length; i++ ) {
                    jQuery( dialogId ).find( "input[value|='" + elements[i].name + "']" ).attr( 'checked', !elements[i].hidden );
                }
            }
        });

        /*
         * Establecer la cantidad minima de columnas que debe ser mostradas,
         * este chequeo se hace cada vez que se hace clic en el dialogo
         * mediante el evento "click". Si la cantidad de columnas es menor
         * que el minimo se deshabilita el boton "Aceptar".
         *
         */
        var minimo = 3;
        jQuery( dialogId ).click( function() {
            var numb = jQuery( dialogId ).find( 'input:checked' );
            if ( numb.length <= minimo ) {
                jQuery( dialogId ).next().find( "button:contains('Aceptar')" ).button({
                    disabled : true
                });
            } else {
                jQuery( dialogId ).next().find( "button:contains('Aceptar')" ).button({
                    disabled : false
                });
            }
        });
    },
    /**
     * Funcion que construye un dialogo especial para fijar el numero de filas en el widget jQGrid.
     *
     * @param dialogId (String) Es el id del dialogo que se va a crear.
     * @param gridId (String) Es el id del grid.
     * @param rowNumberHolder (String) Id del elemento que guarda el valor del numero de filas mostradas.
     * @param rowNumberChanger (String) Id del elemento que contiene el nuevo valor de numero de filas mostradas.
     */
    setCustomDialogGridChangeRowsByPage : function( dialogId, gridId, rowNumberHolder, rowNumberChanger ) {
        // Titulo del mensaje
        var title = "Opciones de tabla";
        // Contenido
        var content = "";
        content += "<span>Numero de filas: </span>";
        content += "<input type='text' value='' id='" + utilString.removeSharp( rowNumberChanger ) + "' ";
        content += "class = 'textbox ui-widget ui-widget-content ui-corner-all b-none' style='width:40px'/>";
        // Calls
        var calls = [
        "Aceptar@utilGrid.setRowNumber('" + gridId + "','" + rowNumberHolder + "','" + rowNumberChanger + "')",
        "Cancelar@" ];
        // Construir el dialogo
        utilDialog.setStandardDialog( dialogId, title, content, calls );
        // Acciones al abrir el dialogo
        jQuery( dialogId ).dialog({
            open: function(event, ui) {
                // Obtener el valor actual del numero de filas en el grid
                jQuery( rowNumberChanger ).val( jQuery( rowNumberHolder ).attr( "value" ) );
                // Centrar el dialogo en la pantalla
                jQuery( dialogId ).dialog( "option", "position", "center" );
            }
        });
    },
    /**
     * Funcion que abre en un modal una pagina externa de la aplicacion
     *
     * @param id: es el id del div donde se va a agregar el boton
     * @param url: es la url de la pagina que se va abrir en el dialogo
     * @param width(int): es el ancho del modal
     * @param height(int): es el alto del modal
     */
    setExternalResource : function (id, url, width, height ) {
        var text = utilString.removeSharp( id );
        var html = utilDialog.buildExternalResource(text, url, width, height)
        jQuery( id ).append( html );
        utilDialog.setExternalResourceDialog( '#ventana' + text, width, height);

        // Cargar el contenido
        jQuery( "#contenido" + text ).attr( 'src', jQuery( '#id' + text ).attr( "href" ) ).load(function() {
            utilDialog.loadDialogcallback( this, width );
        });

        // Abrir el modal
        jQuery( '#id' + text ).button().click( function() {
            jQuery( '#ventana' + text ).dialog( 'open' );
            return false;
        });
    },
    loadDialogcallback : function ( iframeId, width ) {
        jQuery( iframeId ).css('border', 'none' );
        jQuery( iframeId ).contents().find('body').css({
            'background':'none'
        });
        jQuery( iframeId ).contents().find('.middle').css({
            'margin':'0'
        }).removeClass('contenedor-sombra');
        jQuery( iframeId ).contents().find('.view').css({
            'margin':'0',
            'width': width
        }).show();
        jQuery( iframeId ).contents().find('.form-wrap').css({
            'padding':'0',
            'border':'none'
        });
        jQuery( iframeId ).contents().find('.grid-container').css({
            'margin':'0'
        });
        jQuery( iframeId ).contents().find('.header').remove();
        jQuery( iframeId ).contents().find('.menuHorizontal').remove();
        jQuery( iframeId ).contents().find('.foot').remove();
    }
}

var utilWindow = {
    url: null,
    width: 0,
    callback : '',
    /**
     * Limpia variables dinámicas.
     */
    cleanVars : function() {
        utilWindow.url = null;
        utilWindow.width = 0;
        utilWindow.callback = '';
    },
    /**
     * Función que abre un dialogo para mostrar mediante un iframe una página
     * del sistema, usualmente para edición externa.
     * 
     * @param url es la url que se abrirá en el iframe
     * @param callback código que se ejecuta al cerrar el dialogo
     * @param width, es el ancho en pixeles del dialogo al terminar de cargar
     * el iframe.
     */
    openNewWindow : function ( url, callback, width ) {
        utilWindow.url = url;
        utilWindow.width = width;
        utilWindow.callback = callback;
        utilWindow.buildHtmlWindow();
        utilWindow.buildElmentWindow();
        utilDialog.showDialog( '#external-window' );
    },
    buildHtmlWindow : function() {
        var html = '<div id="external-window" title="">';
        html += '<div id="external-window-loading" style="display: block; width: 350px; margin: 11% auto;">';
        html += '<div class="cargando-label">Espere...</div>';
        html += '<div id="external-window-progressbar"></div>';
        html += '</div>';
        html += '<iframe id="external-window-content" width="1" height="1" marginheight="0" marginwidth="0" frameborder="0" style="display: none;"></iframe>';
        html += '</div>';
        utilError.validateDialogContainer();
        jQuery( html ).appendTo( '#jquery_dialogs' );
    },
    buildElmentWindow : function() {
        jQuery( '#external-window' ).dialog({
            autoOpen        : false,
            resizable       : false,
            closeOnEscape   : true,
            modal           : true,
            width           : 400,
            height          : 200,
            show            : 'none',
            open: function( event, ui ) {
                utilWindow.prepareToOpen( jQuery( this ) );
            },
            buttons: {
                'Cerrar' : function() {
                    jQuery( this ).dialog( 'close' );
                    jQuery( this ).dialog( 'destroy' );
                    jQuery( '#external-window' ).remove();
                    eval( utilWindow.callback );
                }
            }
        });
        jQuery( '#external-window' ).siblings('div.ui-dialog-titlebar').parent().addClass('contenedor-sombra');
        jQuery( '#external-window' ).siblings('div.ui-dialog-titlebar').remove();
        jQuery( '#external-window' ).css({
            'padding'   : '0',
            'margin'    : '0',
            'overflow'  : 'hidden'
        });
    },
    prepareToOpen : function ( dialog ) {
        jQuery( '#external-window-progressbar' ).progressbar({
            value: false
        });
        jQuery( '#external-window-content' ).attr( 'src', utilWindow.url ).load(function() {
            utilWindow.loadDialogcallback( this, dialog );
        });
    },
    loadDialogcallback : function ( iframeId, dialog ) {
        // Evitar abrir un popup dentro de otro popup
        jQuery( iframeId ).contents().find( '.new-window' ).remove();
        // Evitar las scrollbars (el modal se ajusta a su contenido)
        jQuery( iframeId ).contents().find( 'body' ).css('overflow', 'hidden');
        // Remover el contenedor "Cargando"
        jQuery( '#external-window-loading' ).remove();
        // Tama?o del iframe
        jQuery( iframeId ).css({
            'width'     : utilWindow.width + 2, // +2px para que se vean los bordes
            'display'   : 'block'
        });
        // Tama?o del formulario
        var heightButtonPane = 78;
        var realHeight = jQuery( iframeId ).contents().find( '.form-wrap' ).outerHeight();
        // La pagina no se cargo, obtener el tama?o de la pagina de error
        if (realHeight <= heightButtonPane ) {
            realHeight = jQuery( iframeId ).contents().find( '.error-container' ).parent().outerHeight();
        }
        // Reajustar el tama?o del elemento .view
        jQuery( iframeId ).contents().find('.view').css({
            'width'     : utilWindow.width,
            'height'    : realHeight
        })
        // Reajustar el alto del iframe
        jQuery( iframeId ).css({
            'height'    : realHeight
        });
        // Reajustar el tama?o del dialogo
        dialog.dialog( "option", "width", utilWindow.width + 3); // +3px para que se vean los bordes
        dialog.dialog( "option", "height", realHeight + heightButtonPane);
        // Centrar el dialogo
        dialog.dialog( 'option', 'position', 'center' );
    },
    prepareIframe : function () {
        jQuery( 'body' ).css( 'background', 'none' );
        jQuery( '.middle' ).css( 'margin', '0' ).removeClass( 'contenedor-sombra' );
        jQuery( '.view' ).css( 'margin', '0' );
        jQuery( '.header').parent().remove();
        jQuery( '.header').remove();
        jQuery( '.menuHorizontal').remove();
        // ajuset para la nueva vista en popup
        jQuery( '.sidebar-left').remove();
        jQuery( '.sidebar-right').css( 'width', '1140px');
        jQuery( '.navbar').remove();
        // termina
        jQuery( '.foot' ).remove();
        jQuery( '.view' ).css( 'visibility', 'visible' );
        jQuery( '.form-wrap' ).css( 'margin', '0' );
        jQuery( '.pair-wrap ').css( 'margin-top', '20px' );
        jQuery( '.view' ).css( 'display', 'block');
    },
    fade : function() {
        // El contenedor principal se oculta para que todos los elementos
        // sean encontrados por jQuery durante el proceso de carga de la p?gia
        // para botones, inputs, jqgrid etc., una vez que se procesaron todos
        // los elementos, se cambia la propiedad 'visibility'
        jQuery( '.view' ).css('visibility', 'visible');
        // Para que el efecto fadeIn se ejecute se cambia la propiedad 'display'
        jQuery( '.view' ).css('display', 'none');
        // Efecto iniciado...
        jQuery( '.view' ).fadeIn( 'slow' );
    }
}
/**
 * Objeto con funciones utiles para construir la barra de menu horizontal.
 * DEPENDENCIAS:
 *     jquery.jqGrid-toolbarMenu-3.0-plugin.css
 *     jquery.jqGrid-toolbarMenu-3.0-plugin.js
 *     jquery.tinysort-1.5.3
 */
var utilMenu = {
    moreCssSelector : '.menu-overflow-special',
    rootMenuContainerCssSelector : '.root-menu-container',
    overflowMenuName : 'Mas...',
    tempOverflowMenuCssSelector : '.tmp-overflow-menu',
    excludedContainer : '.excluded-menu-container',
    menuContainer : null,
    /**
     * Función que cierra los menus abiertos
     */
    close : function() {
        jQuery( '.fg-menu-container' ).hide();
        jQuery( '.positionHelper' ).remove();
        jQuery( '.root-menu-container>a.novasys-custom-active-menu' ).
            removeClass( 'fg-menu-open novasys-custom-active-menu' ).click();
    },
    /**
     * Funcion que convierte una lista de objetos en un menu. El menu es construido
     * utilizando las propiedades del objeto:
     *      nombre: nombre del menu/pagina
     *      url: la direccion url
     *      id: el id de la pagina/submenu
     *      parentId: es el id del parent donde se anida esta pagina/submenu
     *      orden: es el orden del menu
     * Los archivos requeridos son automaticamente generados por el CoreGenerator
     *
     * @param lista: es un array javascript de objetos, cada objeto representa una pagina
     * y tiene los siguientes atributos: id, nombre, anidar, url y orden.
     * @param contenedor: es un selector css donde se construira el menu.
     */
    createMenu : function ( lista, contenedor ) {
        utilMenu.menuContainer = contenedor;
        utilMenu.list(lista);
        utilMenu.nest();
        utilMenu.structure();
        utilMenu.sort();
        utilMenu.menu();
        jQuery( '<div style="clear: both;"></div>').appendTo( utilMenu.rootMenuContainerCssSelector );
        utilMenu.checkRootMenuContainerWidth();
    },
    list : function( lista ) {
        for (var i = 0; i < lista.length; i++) {
            var pagina = lista[i];
            if ( parseInt( pagina.noIncluirEnMenu ) == 0 ) {
                var html = utilMenu.getElement(pagina.nombre, pagina.paginaId, pagina.anidar, pagina.url, pagina.orden);
                jQuery( utilMenu.menuContainer ).append(html);
            }
        }
    },
    getElement : function(nombre, id, parentId, url, orden) {
        if (url == '') url = '#';
        var html = '';
        html += "<li id='" + id + "' parentid='" + parentId + "' order='" + orden + "' name='" + nombre + "'>";
        html += "<a id='" + id + "' href='" + url + "' order='" + orden + "' name='" + nombre + "'>";
        html += "<span></span>";
        html += nombre;
        html += "</a>";
        html += "</li>";    
        return html;
    },
    nest : function () {
        jQuery( utilMenu.menuContainer + ' li' ).each(function() {
            var parentId = jQuery( this ).attr( 'parentid' );
            if ( utilError.checkById( 'li#' + parentId ) ) {
                jQuery( this ).appendTo( 'li#' + parentId );
            }
        });
    },
    structure : function() {
        jQuery( utilMenu.menuContainer + ' li' ).each( function() {
            if ( jQuery( this ).parent().is( 'li' ) ){
                jQuery( this ).parent().children().wrapAll( '<ul />' );
            }
        });
        jQuery( utilMenu.menuContainer + ' a' ).each( function() {
            if ( jQuery( this ).parent().get(0).tagName.toString().toLowerCase() == 'ul' ){
                jQuery( this ).insertBefore( jQuery( this ).parent().get(0) );
            }
        });
        jQuery( utilMenu.menuContainer ).append( '<div class="' + utilString.removeSharp( utilMenu.rootMenuContainerCssSelector ) + '"></div>' );
        jQuery( utilMenu.menuContainer + ' > li' ).each( function() {
            jQuery( this ).find('a').first().appendTo( utilMenu.rootMenuContainerCssSelector );
            jQuery( this ).hide();
            jQuery( this ).changeElementType( 'div' );
        });
    },
    sort : function() {
        jQuery( utilMenu.menuContainer ).find( 'ul > li' ).tsort( {
            attr : 'order'
        },{
            attr : 'name'
        });
        jQuery( utilMenu.rootMenuContainerCssSelector + ' a' ).tsort({
            attr : 'order'
        },{
            attr : 'name'
        });
    },
    menu : function() {
        jQuery(utilMenu.rootMenuContainerCssSelector + ' a').each(function () {
            var rootMenuId = '#' + jQuery(this).attr('id');
            if (jQuery(utilMenu.menuContainer + ' div' + rootMenuId).html() == '') {
                utilMenu.decorateRootMenuElement('a[id=' + jQuery(this).attr('id') + ']', 'ui-icon-triangle-1-e');
            } else {
                utilMenu.decorateRootMenuElement('a[id=' + jQuery(this).attr('id') + ']');
                jQuery(this).menuHor({
                    content: jQuery(utilMenu.menuContainer + ' div' + rootMenuId).html(),
                    flyOut: true,
                    positionOpts: {
                        posX: 'left',
                        posY: 'bottom',
                        offsetX: 0,
                        offsetY: 0,
                        directionH: 'right',
                        directionV: 'down',
                        detectH: true,
                        detectV: false,
                        linkToFront: false
                    }
                });
            }
        });
    },
    checkRootMenuContainerWidth : function () {
        jQuery( utilMenu.moreCssSelector ).remove();
        jQuery( utilMenu.tempOverflowMenuCssSelector ).remove();
        utilMenu.setRootMenuContainerMaximumWidth();
        var elementOverflowed = utilMenu.getElementOverflowed( utilMenu.rootMenuContainerCssSelector );
        if (elementOverflowed != '' ) {
            utilMenu.createOverflowMenuElement( elementOverflowed.split('@')[0] );
            utilMenu.adjustRootMenuContainerWidth( elementOverflowed.split('@')[1] );
        }
    },
    adjustRootMenuContainerWidth : function ( width ) {
        //var moreWidth = parseInt(  jQuery( utilMenu.moreCssSelector ).outerWidth( true ) );
        var moreWidth = 73; // Ancho real del elemento "Mas..."
        jQuery(utilMenu.rootMenuContainerCssSelector).width( width - moreWidth);
    },
    createOverflowMenuElement : function ( overflowedElement ) {
        // Elemento de menu
        var overflowMenuElement = '<a class="' + utilString.removeSharp(utilMenu.moreCssSelector) + '" href="#"><span></span>' + utilMenu.overflowMenuName + '</a>';
        jQuery( overflowMenuElement ).insertAfter( utilMenu.rootMenuContainerCssSelector );
        utilMenu.decorateRootMenuElement( utilMenu.moreCssSelector );
        // Contenido
        var contentOverflow = '<ul>';
        jQuery( utilMenu.rootMenuContainerCssSelector ).find( 'a#' + overflowedElement ).prev().prev().nextAll().each(function() {
            if ( jQuery( this ).is( 'a' ) ) {
                var elementId = jQuery( this ).attr( 'id' );
                contentOverflow += '<li><a href="#">' + jQuery( 'a[id=' + elementId + ']' ).text() + '</a>';
                contentOverflow += jQuery( 'div#' + elementId ).html();
                contentOverflow += '</li>';
            }
        })
        contentOverflow += '</ul>';
        jQuery( utilMenu.rootMenuContainerCssSelector ).append( '<div style="display: none;" class="' + utilString.removeSharp(utilMenu.tempOverflowMenuCssSelector) + '">' + contentOverflow + '</div>' );
        jQuery( utilMenu.moreCssSelector ).menuHor({
            content         : jQuery( utilMenu.tempOverflowMenuCssSelector ).html(),
            flyOut          : true,
            positionOpts    : {
                posX        : 'left',
                posY        : 'bottom',
                offsetX     : 0,
                offsetY     : 0,
                directionH  : 'left',
                directionV  : 'down',
                detectH     : true,
                detectV     : false,
                linkToFront : false
            }
        });
    },
    /**
     * Funcion que regresa el id del primer elemento que hace overflow sobre
     * el contenedor de menu.
     */
    getElementOverflowed : function ( rootMenuContainerCssSelector ) {
        var parent = jQuery( rootMenuContainerCssSelector );
        var children = parent.children().length;
        var elementId = ''
        if ( children > 0 ) {
            var maxWidth = 0;
            jQuery( parent ).children().each(function(){
                // Solo anchors
                if ( jQuery( this ).is( 'a' ) ) {
                    var thisWidth = jQuery( this ).outerWidth(true);
                    maxWidth += thisWidth;
                    if ( maxWidth > parent.width() && elementId == '' ) {
                        var id = jQuery( this ).attr( 'id' );
                        var width = maxWidth - thisWidth;
                        elementId = id + '@' + width;
                    }
                }
            });
        }
        return elementId;
    },
    /**
     * Funciones que hace algunas decoraciones con css para que elementos raiz de menu
     * tengan el mismo "Look & Feel" de jQuery
     */
    decorateRootMenuElement: function (cssSelector, icon) {
        jQuery(cssSelector).addClass('fg-button fg-button-icon-right');
        jQuery(cssSelector).css('margin-left', '2px');
        jQuery(cssSelector).css('margin-right', '2px');
        if (icon == undefined) {
            jQuery(cssSelector + ' span').addClass('ui-icon ui-icon-triangle-1-s');
        } else {
            jQuery(cssSelector + ' span').addClass('ui-icon ' + icon);
        }
        jQuery(cssSelector + ' span').show();

        // State
        jQuery( cssSelector ).hover(
            // Hover state
            function () {
                jQuery( cssSelector ).addClass( 'ui-state-hover ui-corner-all' );
                jQuery( cssSelector ).css( 'font-weight', 'normal' );
                jQuery( cssSelector ).css( 'margin-left', '1px' );
                jQuery( cssSelector ).css( 'margin-right', '1px' );
            },
            // Normal state
            function () {
                jQuery( cssSelector ).removeClass( 'ui-state-hover ui-corner-all' );
                jQuery( cssSelector ).css( 'font-weight', 'normal' );
                jQuery( cssSelector ).css( 'margin-left', '2px' );
                jQuery( cssSelector ).css( 'margin-right', '2px' );
            });

        jQuery( cssSelector ).css('margin-bottom', '2px');
    },
    setRootMenuContainerMaximumWidth : function () {
        var anchoBarraMenu = 0;
        // var home = jQuery( '#home-link' ).outerWidth(true) + 2;
        var home = 64; // El boton de inicio mide 64px de ancho
        var usuarioMinInfo = jQuery( '.sesion-container-small' ).outerWidth(true);
        if ( jQuery( '.header' ).is( ':visible' ) ) {
            anchoBarraMenu = jQuery( '.menuHorizontal' ).width() - home;
        } else {
            anchoBarraMenu = jQuery( '.menuHorizontal' ).width() - home - usuarioMinInfo;
        }
        jQuery( utilMenu.rootMenuContainerCssSelector ).attr('style', 'float: left; width: ' + anchoBarraMenu + 'px; height: 27px; overflow: hidden;' );
    }
}

var utilEffect = {
    createProgressBar: function( texto ) {
        if ( jQuery( '.cargando-container' ).length == 0 ){
            var html = '';
            html += "<div class='ui-widget ui-front ui-overlay ui-widget-content ui-corner-all cargando-container '>";
            html += "<div class='cargando-label'>" + texto + "</div>";
            
            // Progress bar (nativo de jquery)
            // html += "<div class='cargando'></div>";
            
            // Spinner
            html += "<div>";
            html += "<div class='loading-overlay-showing' ";
            html += "data-loading-overlay='' ";
            html += "data-loading-overlay-options=\"{ \"startShowing\": true }\" style='min-height: 60px; position: relative;'>";
            html += "<div class='loading-overlay'>";
            html += "<div class='loader black'></div>";
            html += "</div>";
            html += "</div>";
            html += "</div>";
            
            html += "</div>";
            jQuery( 'body' ).append( html );
            jQuery( '.cargando-container' ).hide();
            jQuery( '.cargando' ).progressbar({
                value: false
            });
        } else {
            utilError.printWarning( "Elemento '.cargando-container' ya existe" );
        }
    },
    showToast: function (toastType, msg) {
        jQuery().toastmessage('showToast', {
            text: msg,
            sticky: false,
            position: 'middle-center',
            type: toastType,
            closeText: '<div style="margin-top: 4px; text-align: center; font-size: 16px;"><i class="fa fa-times"></i></div>',
            close: function () {
            }
        });
    },
    /**
     * Funcion para mostrar un mensaje "Cargando" personalizado.
     * Util para procesos que requieren varios segundos en terminar.
     */
    showProgressBar: function() {
        if ( jQuery( '.cargando-container' ).length > 0 ) {
            if ( jQuery( '.cargando-container' ).is(":visible") ){
                
            } else {
                // Crear un <div> para hacer un efecto "modal"
                jQuery( "<div class='ui-widget-overlay deletable' ></div>" ).insertBefore( '.cargando-container' );
                jQuery( '.cargando-container' ).center().show();
                jQuery( '.loading' ).css( 'display', 'none' );
            }
        } else {
            utilError.printError( "Antes de llamar showProgressBar(), llamar createProgressBar()" );
        }
    },
    /**
     * Funcion para ocultar el mensaje "Cargando"
     */
    hideProgressBar: function(){
        jQuery( '.deletable' ).remove();
        jQuery( '.cargando-container' ).hide();
    },
    buttonize : function( button1, button2, button3) {
        jQuery( button1 ).button({
            icons: {
                primary: "ui-icon-folder-open"
            },
            text: false
        }).css({
            'margin' : '0',
            'height' : '24px',
            'border-right' : 'none',
            'border-top-right-radius' : '0',
            'border-bottom-right-radius' : '0'
        });
        jQuery( button2 ).button({
            icons: {
                primary: "ui-icon-trash"
            },
            text: false
        }).css({
            'margin-left' : '-3px',
            'height' : '24px',
            'border-right' : 'none',
            'border-radius' : '0'
        });
        jQuery( button3 ).button({
            icons: {
                primary: "ui-icon-arrowthickstop-1-s"
            },
            text: false
        }).css({
            'margin-left' : '-5px',
            'height' : '24px',
            'border-top-left-radius' : '0',
            'border-bottom-left-radius' : '0'
        });
    },
    initializeOptionTransfer : function ( allOptionsId, selectedOptionsId, 
        allOptionsMoveUpId, allOptionsMoveDownId,
        selectedOptionsMoveUpId, selectedOptionsMoveDownId,
        moveLeftId, moveRightId, moveAllToLeftId, moveAllToRightId) {
        
        // Decorar los select
        jQuery( allOptionsId + ' , ' + selectedOptionsId ).css({
            'width'  : '250px',
            'height' : '250px'
        });

        // Decorar los botones
        utilEffect.buttonIcon( [allOptionsMoveUpId, selectedOptionsMoveUpId] , "ui-icon-circle-arrow-n" );
        utilEffect.buttonIcon( [allOptionsMoveDownId, selectedOptionsMoveDownId] , "ui-icon-circle-arrow-s" );
        utilEffect.buttonIcon( [moveAllToLeftId] , "ui-icon-seek-prev" );
        utilEffect.buttonIcon( [moveAllToRightId] , "ui-icon-seek-next" );
        utilEffect.buttonIcon( [moveLeftId] , "ui-icon-seek-first" );
        utilEffect.buttonIcon( [moveRightId] , "ui-icon-seek-end" );
        
        // Botones Mover Arriba/Abajo
        jQuery( allOptionsMoveUpId + ',' + allOptionsMoveDownId + ',' + selectedOptionsMoveUpId + ',' + selectedOptionsMoveDownId ).css( {
            'width':'45px', 
            'border-radius':'16px'
        });
        jQuery( allOptionsMoveUpId + ',' + selectedOptionsMoveUpId ).css({
            'border-right':'0', 
            'border-top-right-radius' : '0',
            'border-bottom-right-radius' : '0',
            'outline': '0'
        });
        jQuery( allOptionsMoveDownId + ',' + selectedOptionsMoveDownId ).css({
            'margin-left':'-5px', 
            'border-top-left-radius' : '0',
            'border-bottom-left-radius' : '0',
            'outline': '0'
        });
        
        // Botones Mover Izquierda/Derecha
        jQuery( moveLeftId + ' , ' + moveRightId + ' , ' + moveAllToLeftId + ' , ' + moveAllToRightId ).css( {
            'height':'45px', 
            'border-radius':'16px'
        });
        jQuery( moveRightId + ' , ' + moveAllToRightId ).css({
            'border-bottom':'0', 
            'border-bottom-left-radius' : '0',
            'border-bottom-right-radius' : '0',
            'outline': '0'
        });
        jQuery( moveLeftId + ' , ' + moveAllToLeftId ).css({
            'margin-top':'-5px', 
            'border-top-left-radius' : '0',
            'border-top-right-radius' : '0',
            'outline': '0'
        });
        
        // Mover a la derecha
        jQuery( moveRightId ).click( function() {
            jQuery( allOptionsId + ' option:selected' ).remove().appendTo( selectedOptionsId );
            jQuery( selectedOptionsId + ' option').removeAttr("selected");
        });
        
        // Mover todo a la derecha
        jQuery( moveAllToRightId ).click( function() {
            jQuery( allOptionsId + ' option' ).remove().appendTo( selectedOptionsId );
            jQuery( selectedOptionsId + ' option').removeAttr("selected");
        });
        
        // Mover a la izquierda
        jQuery( moveLeftId ).click( function() {
            jQuery( selectedOptionsId + ' option:selected').remove().appendTo( allOptionsId );
            jQuery( allOptionsId + ' option').removeAttr("selected");
        });
        
        // Mover todo a la izquierda
        jQuery( moveAllToLeftId ).click( function() {
            jQuery( selectedOptionsId + ' option').remove().appendTo( allOptionsId );
            jQuery( allOptionsId + ' option').removeAttr("selected");
        });
        
        // Mover arriba allOptions
        jQuery( allOptionsMoveUpId ).click( function() {
            var op = jQuery( allOptionsId + ' option:selected');
            if( op.length ){
                op.first().prev().before( op );
            }
        });
        
        // Mover abajo allOptions
        jQuery( allOptionsMoveDownId ).click( function() {
            var op = jQuery( allOptionsId + ' option:selected');
            if( op.length ){
                op.last().next().after( op );
            }
        });
        
        // Mover arriba selectedOptions
        jQuery( selectedOptionsMoveUpId ).click( function() {
            var op = jQuery( selectedOptionsId + ' option:selected');
            if( op.length ){
                op.first().prev().before( op );
            }
        });
        
        // Mover abajo selectedOptions
        jQuery( selectedOptionsMoveDownId ).click( function() {
            var op = jQuery( selectedOptionsId + ' option:selected');
            if( op.length ){
                op.last().next().after( op );
            }
        });
    },
    /**
     * Funcion que convierte un boton comun en un boton de jquery decorado con el tema instalado,
     * el boton no contiene texto, solo el icono que es mandado como parametro.
     * @param ids es una lista de ids (strings)
     * @param icon es el icono de jquery que se agregara al boton
     */
    buttonIcon : function( ids, icon ) {
        for (var i = 0; i < ids.length; i++ ){
            jQuery( ids[i] ).button({
                icons: {
                    primary: icon
                },
                text: false
            });
        }
    },
    /**
     * Funcion que muestra un tooltip invocado por el framework de validaciones.
     * Nota: No modificar esta funcion, utilizar utilEffect.showRegularTooltip()
     * 
     * @param message (String), texto del tooltip
     */
    showValidationTooltip : function( message ) {
        utilEffect.showRegularTooltip( '#' + this.id, message, '#ffffff' );
    },
    /**
     * Funcion que oculta un tooltip invocado por el framework de validaciones.
     * Nota: No modificar esta funcion, utilizar utilEffect.hideRegularTooltip()
     * 
     * @param message (String), texto del tooltip
     */
    hideValidationTooltip : function() {
        utilEffect.hideRegularTooltip( '#' + this.id );
    },
    /**
     * Funcion que muestra un tooltip invocado manualmente
     * 
     * @param elementId (String), selector css
     * @param message (String), texto del tooltip
     * @param fontColor (String), color del texto
     */
    showRegularTooltip : function( elementId, message, fontColor ) {
        if ( Tipped == undefined ) {
            alert( "No se ha cargado jquery.tipped-[version].js" );
            return;
        }
        Tipped.create( elementId, message, {
            skin            : 'default-skin',
            hook            : 'topright',
            closeButtonSkin : 'default',
            containment     : false,
//            hideOn          : {element: 'self', event: 'keyup'},
            showOn          : false,
            closeButton     : true,
            onShow          : function( content, element ) {
                jQuery( element ).addClass( 'ui-state-error' );
            },
            onHide: function( content, element ) {
                jQuery( element ).removeClass( 'ui-state-error' );
            }
        });
        
        // Mostrar el tooltip
        Tipped.show( elementId );
        
        // Color del texto
        if ( fontColor == undefined || fontColor == null ) {
            fontColor = '#000000';
        }
        jQuery( '.t_ContentContainer' ).css( 'color', fontColor );
    },
    /**
     * Funcion que oculta un tooltip invocado manualmente
     * 
     * @param elementId (String), selector css
     */
    hideRegularTooltip : function( elementId ) {
        Tipped.remove( elementId );
    }
}

var utilSearch = {
    /* Varibales auxiliares */
    caption                     : 'Buscar...',
    gridId                      : '',
    dialogId                    : '',
    formId                      : '',
    object                      : null,
    objectDWR                   : null,
    contentContainer            : '.content-container',
    criteriaContainerSelector   : '.criteria-container',
    criteriaSelector            : '.criteria',
    criteriaValueSelector       : '.criteria-value',
    btnAddSelector              : '.btn-add',
    btnRemoveSelector           : '.btn-remove',
    btnCleanSelector            : '.btn-clean',
    btnSearchSelector           : '.btn-search',
    /**
     * Funcion que construye el modal de busquedas
     */
    buildSearch : function ( dialogId ) {
        utilError.validateDialogContainer();
        // Construye la estructura HTML para el dialogo de búsquedas y lo
        // agrega al DOM
        jQuery( '#jquery_dialogs' ).append( utilSearch.getHTMLSearchDialog( dialogId ) );
        
        // Agrega icono a los botones
        jQuery( utilSearch.btnAddSelector ).button({
            icons: {
                primary: "ui-icon-plus"
            }
        }).css('width', '100px');
        jQuery( utilSearch.btnCleanSelector ).button({
            icons: {
                primary: "ui-icon-arrowreturnthick-1-w"
            }
        }).css('width', '100px');
        jQuery( utilSearch.btnSearchSelector ).button({
            icons: {
                primary: "ui-icon-search"
            }
        }).css('width', '100px');
        
        // Convierte la estructura HTML en un diálogo
        jQuery( dialogId ).dialog({
            width       : 410,
            autoOpen    : false,
            resizable   : false,
            modal       : true,
            hide        : 'fade',
            show        : 'highlight',
            open        : function( event, ui ) {
                jQuery( dialogId ).dialog( 'option', 'position', 'center' );
            },
            close       : function( event, ui ) {
                jQuery( utilSearch.contentContainer ).empty();
            }
        });
        
        // Acción al presionar la tecla : ENTER
        jQuery( 'body' ).keydown( function (e) {
            var isOpen = jQuery( dialogId ).dialog( "isOpen" );
            if (isOpen && e.which == 13){
                jQuery( utilSearch.btnSearchSelector ).click();
            }
        });
    },
    /**
     * Funcion que obtiene el contenido basico del modal de busquedas:
     * Barra superior   : Bot?n Agregar
     * Barra central    : Div para contener las criterias
     *      - Criteria
     *      - Criteria value
     *      - Remover criteria
     * Barra inferior   : Boton Limpiar y buscar
     *
     * @param dialogId: es el id del dialogo de busquedas
     */
    getHTMLSearchDialog : function( dialogId ) {
        var html = "<div id='" + utilString.removeSharp( dialogId ) + "' title='" + utilSearch.caption + "'>";
        html += utilSearch.getHTMLAgregarButton();
        html += "<div style='margin: 4px 0; border-top: 1px dotted gray;'></div>";
        html += "<div class='" + utilString.removeSharp( utilSearch.contentContainer ) + "'></div>";
        html += "<div>";
        html += "<div style='margin: 4px 0; border-top: 1px dotted gray;'></div>";
        html += utilSearch.getHTMLLimpiarButton();
        html += utilSearch.getHTMLBuscarButton();
        html += '<div style="clear: both"></div>';
        html += "</div>";
        html += "</div>";
        return html;
    },
    getHTMLAgregarButton : function () {
        var title = 'Agregar';
        var style = 'float: right;';
        var html = '';
        html += "<div>";
        html += "<button style='" + style + "' class='" + 
        utilString.removeSharp( utilSearch.btnAddSelector ) + "' title='" + title + "'>";
        html += title;
        html += "</button>";
        html += "</div>";
        html += '<div style="clear: both"></div>';
        return html;
    },
    getHTMLLimpiarButton : function () {
        var title = 'Limpiar';
        var style = 'float: left;';
        var html = '';
        html += "<button style='" + style + "' class='" + 
        utilString.removeSharp( utilSearch.btnCleanSelector ) + "' title='" + title + "'>";
        html += title;
        html += "</button>";
        return html;
    },
    getHTMLBuscarButton : function () {
        var title = 'Buscar';
        var style = 'float: right;';
        var html = '';
        html += "<button style='" + style + "' class='" + 
        utilString.removeSharp( utilSearch.btnSearchSelector ) + "' title='" + title + "'>";
        html += title;
        html += "</button>";
        return html;
    },
    /**
     * Funcion que abre el modal de busquedas
     *
     * @param: dialogId es el id del dialogo
     * @param: gridId es el id del grid
     * @param: formId es el nombre del formulario
     * @param: object es el objeto que contiene la funcion findByCriteria
     * @param: objectDWR es el objeto DWR para realizar busquedas
     */
    openSearch : function ( dialogId, gridId, formId, object, objectDWR ) {
        // Preparar variables
        utilSearch.dialogId = dialogId;
        utilSearch.gridId = gridId;
        utilSearch.formId = formId;
        utilSearch.object = object;
        utilSearch.objectDWR = objectDWR;
        // Agregar funcionalidad a los botones
        utilSearch.funcionalidadBotonAgregar();
        utilSearch.funcionalidadBotonLimpiar();
        utilSearch.funcionalidadBotonBuscar();
        jQuery( utilSearch.btnCleanSelector ).click();
        jQuery( dialogId ).dialog( 'open' );
    },
    funcionalidadBotonAgregar : function () {
        jQuery( utilSearch.btnAddSelector ).unbind('click');
        jQuery( utilSearch.btnAddSelector ).click( function() {
            if ( utilSearch.canAddCriteria() == false ) return;
            if ( !utilSearch.validarFechas( utilSearch.dialogId ) ) {
                utilSearch.shakeSearchDialog();
                return;
            }
            utilSearch.buildHTMLCriteria();
            utilSearch.selectFirstCriteria();
            utilSearch.addCriteriaValue();
            utilSearch.decorate();
            utilSearch.disablePreviousCriterias();
            utilSearch.btnRemoveEvent();
            utilSearch.onChangeCriteria();
        });
    },
    funcionalidadBotonLimpiar : function () {
        jQuery( utilSearch.btnCleanSelector ).unbind( 'click' );
        jQuery( utilSearch.btnCleanSelector ).click( function () {
            jQuery( utilSearch.contentContainer ).empty();
            jQuery( utilSearch.btnAddSelector ).click();
        });
    },
    funcionalidadBotonBuscar : function () {
        jQuery( utilSearch.btnSearchSelector ).unbind( 'click' );
        jQuery( utilSearch.btnSearchSelector ).click( function () {
            jQuery( utilSearch.criteriaContainerSelector ).each( function() {
                // Fechas
                var result = utilSearch.validarFechas( utilSearch.dialogId );
                if ( !result ) {
                    utilSearch.shakeSearchDialog();
                    return false;
                }
                var objeto = utilObject.buildObject( utilSearch.dialogId , utilSearch.objectDWR );
                jQuery( utilSearch.gridId + 'CurrentPage').val('1');
                utilSearch.object.findByCriteria( objeto );
                jQuery( utilSearch.dialogId ).dialog( 'close' );
            });
        });
    },
    shakeSearchDialog : function() {
        jQuery( 'div[aria-describedby=' + utilString.removeSharp( 
            utilSearch.dialogId ) + ']'  ).effect( "shake", {
            times: 3
        }, 80 );
        jQuery().toastmessage('showToast', {
            text : jQuery('#msgRangoNoValido').val(),
            sticky : false,
            position : 'bottom-center',
            type : 'error',
            closeText: '<i class="fa fa-times"></i>',
            close : function () {
            }
        });
    },
    /**
     * Funcion que decora el contenedor de cada criteria
     */
    decorate : function () {
        utilSearch.decorateCriteriaSelector();
        utilSearch.decorateCriteriaValue();
        utilSearch.decorateBtnRemove();
    },
    decorateCriteriaSelector : function () {
        jQuery( utilSearch.criteriaSelector ).last().addClass( 'select' ).css({
            'width' : '180px'
        });
    },
    decorateCriteriaValue : function () {
        var criteriaValue = jQuery( utilSearch.criteriaValueSelector ).last();
        // Textbox
        if ( criteriaValue.hasClass( 'textbox' ) ) {
            // Fechas
            if ( criteriaValue.hasClass( 'isDate' ) ) {
                criteriaValue.css({
                    'margin-left' : '2px',
                    'width' : '78px',
                    'float':'left'
                }).prev().css({
                    'margin-left' : '2px',
                    'width' : '79px',
                    'float':'left'
                });
            } else {
                criteriaValue.css({
                    'margin-left' : '2px',
                    'width' : '159px',
                    'float':'left'
                });
            }
        }
        // Select
        if ( criteriaValue.hasClass( 'select' ) ) {
            // Seleccionar el primero
            criteriaValue.children().removeAttr( 'selected' );
            criteriaValue.children().first().attr( 'selected', true );
            var firstdata = criteriaValue.find( 'option:selected' ).val() + '';
            // Si tiene valor = 0 ("--Seleccione--") cambiarlo a "Cualquiera"
            if ( firstdata == '0' ) {
                criteriaValue.children().first().text( 'Cualquiera' );
            } else {
                // Si no tiene "--Seleccione--" agregar "Cualquiera"
                criteriaValue.prepend(jQuery ( '<option>', {
                    value: 0,
                    text : 'Cualquiera'
                }));
                criteriaValue.children().removeAttr( 'selected' );
                criteriaValue.children().first().attr( 'selected', true );
            }
            criteriaValue.css( {
                'margin-left' : '2px',
                'width' : '160px',
                'display' : 'inline'
            });
        }
    },
    decorateBtnRemove : function () {
        jQuery( utilSearch.btnRemoveSelector ).last().button({
            icons: {
                primary: "ui-icon-circle-minus"
            },
            text: false
        }).css({
            'margin-top': '2px',
            'height' : '32px'
        });
    },
    getHTMLCriteriaSelect : function () {
        var html  = '';
        html += '<select class="' + utilString.removeSharp( utilSearch.criteriaSelector ) + ' form-control" style="float: left;">';
        var availableCriterias = utilSearch.getAvailableCriterias();
        for ( var i = 0; i < availableCriterias.length; i++ ){
            html += '<option ';
            html += 'value = "' + availableCriterias[i].split( ':::' )[0] + '">';
            html += availableCriterias[i].split( ':::' )[1];
            html += '</option>';
        }
        html += '</select>';
        return html;
    },
    canAddCriteria : function ( gridId ) {
        var addedCriterias = jQuery( utilSearch.criteriaSelector ).length;
        var allCriterias = utilSearch.getAllCriterias( gridId ).length;
        if ( allCriterias == addedCriterias ) {
            return false;
        } else {
            return true;
        }
    },
    canRemoveCriteria : function () {
        var addedCriterias = jQuery( utilSearch.criteriaSelector ).length;
        if ( addedCriterias > 1 ) {
            return true;
        } else {
            return false;
        }
    },
    selectFirstCriteria : function () {
        jQuery( utilSearch.criteriaSelector ).last().children().removeAttr( 'selected' );
        jQuery( utilSearch.criteriaSelector ).last().children().first().attr('selected', true);
    },
    addCriteriaValue : function () {
        var elementSimpleName = jQuery( utilSearch.criteriaSelector ).last().val();
        var elementFullName = utilSearch.formId + '_' + elementSimpleName;
        
        var newId = utilString.removeSharp( utilSearch.dialogId ) + 
        '_' + elementSimpleName;
    
        // Radios
        if ( jQuery( elementFullName ).length == 0 && utilString.contains( elementFullName, '_' ) ) {
            var radioGroup = jQuery( 'input[ type="radio" ][ name="' + 
                elementSimpleName + '"]' );
            if ( radioGroup.length == 0 ) {
                return;
            } else {
                jQuery( utilInput.convertRadioGroupToHTMLSelect( elementFullName ) ).insertAfter(
                    jQuery( utilSearch.criteriaSelector ).last() )
                .addClass( utilString.removeSharp( utilSearch.criteriaValueSelector ) )
                .addClass( 'select' )
                .attr( 'id' , newId );
                return;
            }
        }

        /* Cuando se trata de fechas hay que remover la funcionalidad datepicker
         * del elemento original que se utiliza para crear los campos 
         * "Fecha Inicial" y "Fecha Final" debido a que se se crea un bug el 
         * cual no muestra el selector de fechas al llamar a la funcion 
         * datepicker() sobre un elemento que ya es datepicker */
        if ( jQuery( elementFullName ).hasClass( 'hasDatepicker' ) ) {
            jQuery( elementFullName ).datepicker( 'destroy' );
        }

        jQuery( elementFullName )
        .clone( true ) 
        .removeAttr( 'id' )
        .attr( 'id' , newId )
        .addClass( utilString.removeSharp( utilSearch.criteriaValueSelector ) )
        .insertAfter( jQuery( utilSearch.criteriaSelector ).last() );
        
        if ( jQuery( '#' + newId ).is( '.select' ) ) {
            utilForm.cleanSelect( jQuery( '#' + newId ).get( 0 ) );
            // Remover funcionalidad selects encadenados
            jQuery( '#' + newId ).unbind();
        } else if ( jQuery( '#' + newId ).is( '.textbox, .textarea, .fixme' ) ) {
            
            utilForm.cleanInputText( jQuery( '#' + newId ).get(0) );
            
            jQuery( '#' + newId ).removeClass( 'textbox-display' );
            jQuery( '#' + newId ).removeAttr( 'disabled' );
            jQuery( '#' + newId ).addClass( 'textbox' );
            
            /* Cuando el campo es un textbox de tipo fecha, éste tiene un 
             * tratamiento especial.
             * 
             * Si el campo es fecha se crea su campo tipo textbos mas uno 
             * adicional con el proposito de tener 2 campos: "Fecha Inicial" y 
             * "Fecha Final" para poder crear busquedas por rango de fechas 
             * 
             * Nota: esta regla se aplica cuando el nombre del campo */
            if ( jQuery( '#' + newId ).hasClass( 'isDate' ) ) {

                // Clonar el original para crear "Fecha Final"
                jQuery( '#' + newId )
                .attr( 'title', 'F. Inicial')
                .clone( true )
                .removeAttr( 'id' )
                .insertAfter( '#' + newId )
                .attr( 'id', newId + 'Final' )
                .attr( 'title', 'F. final');

                // Modificar el id del original a "Fecha Inicial"
                jQuery( '#' + newId )
                .removeAttr( 'id' )
                .attr('id', newId + 'Inicial' );

                // Convertirlos a datepicker
                utilInput.setAsDatepicker( '#' + newId + 'Inicial');
                utilInput.setAsDatepicker( '#' + newId + 'Final' );
                
                // Texto marca de agua
                jQuery( '#' + newId + 'Inicial' ).watermark();
                jQuery( '#' + newId + 'Final' ).watermark();
                
                // Restaurar la funcionalidad del elemento original
                utilInput.setAsDatepicker( elementFullName );
            }
        } else if ( jQuery( '#' + newId ).is( '.checkbox' ) ) {
            utilForm.cleanInputGroup( jQuery( '#' + newId ).get(0) );
        }
    },
    getHTMLBtnRemove : function () {
        var title = 'Remover';
        var style = 'float: right;';
        var html = '';
        html += "<button style='" + style + "' class='" + 
        utilString.removeSharp( utilSearch.btnRemoveSelector ) + "' title='" + title + "'>";
        html += "</button>";
        return html;
    },
    buildHTMLCriteria : function() {
        // Agregar criteria
        var html='<div class = "' + utilString.removeSharp( 
            utilSearch.criteriaContainerSelector ) + '" style="height: 38px;">';
        html += utilSearch.getHTMLCriteriaSelect();
        html += utilSearch.getHTMLBtnRemove();
        html += '<div style="clear: both"></div>';
        html += '</div>';
        jQuery( utilSearch.contentContainer ).append( html );
    },
    btnRemoveEvent : function () {
        jQuery( utilSearch.btnRemoveSelector ).unbind( 'click' );
        jQuery( utilSearch.btnRemoveSelector ).click( function () {
            if ( utilSearch.canRemoveCriteria() == false ) return;
            jQuery( this ).parent().empty().remove();
            utilSearch.disablePreviousCriterias();
        });
    },
    validarFechas : function( dialogId ) {
        var isValido = true;
        jQuery( dialogId ).find( utilSearch.criteriaContainerSelector ).each( function() {
            if ( jQuery( this ).find( '.isDate' ).length == 2 ) {
                var result = utilInput.isValidoFechaInicialFinal(
                    '#' + jQuery( this ).find( '.isDate' ).first().attr( 'id' ), 
                    '#' + jQuery( this ).find( '.isDate' ).first().next().attr( 'id' ) );
                if ( !result ) isValido = false;
                return false;
            }
        });
        return isValido;
    },
    onChangeCriteria : function () {
        jQuery( utilSearch.criteriaSelector ).unbind( 'change' );
        jQuery( utilSearch.criteriaSelector ).change( function() {
            jQuery( this ).nextUntil( '.btn-remove' ).remove();
            utilSearch.addCriteriaValue();
            utilSearch.decorateCriteriaValue();
        });
    },
    getAvailableCriterias : function () {
        var usedCriterias = utilSearch.getUsedCriterias();
        var allCriterias = utilSearch.getAllCriterias();
        var availableCriterias = [];
        for ( var i = 0; i < allCriterias.length; i++ ) {
            var currentCriteria = allCriterias[ i ];
            if ( jQuery.inArray( currentCriteria, usedCriterias ) == -1 ) {
                availableCriterias[ availableCriterias.length ] = currentCriteria;
            }
        }
        return availableCriterias;
    },
    getUsedCriterias : function () {
        var usedCriterias = [];
        jQuery( utilSearch.criteriaSelector ).each(function( index ){
            var data = '';
            data += jQuery( this ).find( 'option:selected' ).val() + '';
            data += ':::'
            data += jQuery( this ).find( 'option:selected' ).text() + '';
            usedCriterias[ index ] = data;
        });
        return usedCriterias;
    },
    getAllCriterias : function () {
        var allCriterias = [];
        var columnModel = jQuery( utilSearch.gridId ).jqGrid( 'getGridParam', 'colModel' );
        var colNames = jQuery( utilSearch.gridId ).jqGrid( 'getGridParam', 'colNames' )
        for (var i = 0; i < columnModel.length; i++ ) {
            var columnObject = columnModel[ i ];
            var isSearchable = columnObject.search;
            var data = '';
            if ( isSearchable ) {
                data += columnObject.index + '';
                data += ':::';
                data += jQuery.trim(colNames[i].toString().replace(/(\r\n|\n|\r)/gm,''));
                allCriterias[allCriterias.length] = data;
            }
        }
        return allCriterias;
    },
    /**
     * Funcion auxiliar que deshabilita los criterios previamente ingresados, esto ayuda a evitar
     * que se seleccione mas de una vez el mismo criterio, es decir el ultimo con alguno previo.
     */
    disablePreviousCriterias : function() {
        // Deshabilitar criterias
        if ( jQuery( utilSearch.criteriaSelector ).length > 1 ) {
            jQuery( utilSearch.criteriaSelector  ).attr( 'disabled', true );
            jQuery( utilSearch.criteriaSelector  ).last().attr( 'disabled' , false );
            jQuery( utilSearch.criteriaValueSelector ).attr( 'disabled', true );
            jQuery( utilSearch.criteriaValueSelector ).last().attr( 'disabled' , false );
            
            // Fechas
            if ( jQuery( utilSearch.criteriaValueSelector ).last().prev()
                .hasClass( 'isDate' ) ) {
                jQuery( utilSearch.criteriaValueSelector ).last().prev().attr( 'disabled' , false );
            }
            
            jQuery( utilSearch.btnRemoveSelector ).css( 'visibility', 'hidden' );
            jQuery( utilSearch.btnRemoveSelector ).last().css( 'visibility', 'visible' );
        } else {
            jQuery( utilSearch.criteriaSelector ).attr( 'disabled', false );
            jQuery( utilSearch.criteriaValueSelector ).last().attr( 'disabled' , false );
            // Fechas
            if ( jQuery( utilSearch.criteriaValueSelector ).last().prev()
                .hasClass( 'isDate' ) ) {
                jQuery( utilSearch.criteriaValueSelector ).last().prev().attr( 'disabled' , false );
            }
            jQuery( utilSearch.btnRemoveSelector ).last().css( 'visibility', 'hidden' );
        }
    }
}

/**
 * Objeto con funciones utiles para operaciones con jqGrid.
 */
var utilGrid = {
    hideGridAction : function( id ) {
        jQuery( id ).hide();
        jQuery( id ).next().hide();
    },
    /**
     * Actualiza el grid, esta funcion es llamada despuus de haber hecho alguna busqueda en
     * la BD.
     */
    gridUpdate : function( gridId, gridCurrentPage, gridRowsByPage, data ) {
        var entity = utilString.removeSharp( utilString.removeGrid( gridId ) );
        var grid = data.grid;
        jQuery( gridId ).clearGridData();
        jQuery( gridId ).setGridParam({
            rowNum      : jQuery( gridRowsByPage ).val(),
            page        : jQuery( gridCurrentPage ).val(),
            total       : data.total,
            lastpage    : data.paginas
        });

        if (grid.length == 0) {
            utilGrid.fixSinResultadosQueMostrar( gridId );
        }

        /**
         * Iterar el objeto que contiene la informacion de los registros para
         * llenar el widget jqGrid.
         */
        for( var i = 0; i < grid.length; i++ ) {
            // Id para editar, eliminar o cambiar estatus
            var id = grid[ i ][ entity + "Id" ];
            // Activo
            var activo_css = 'ui-icon ui-icon-bullet ui-icon-center';
            var activo_title = 'Activo';
            var activo_onclick = entity + 'JS.setEstatus( ' + id + ', 2 )';
            // funcionalidad para organizacionGrid
            var activo_eventos_onclick = entity + 'JS.setCreaEventos( ' + id + ', 1 )';
            var inactivo_eventos_onclick = entity + 'JS.setCreaEventos( ' + id + ', 0 )';
            var activo_sc_onclick = entity + 'JS.setServicioCompleto( ' + id + ', 1 )';
            var inactivo_sc_onclick = entity + 'JS.setServicioCompleto( ' + id + ', 0 )';
            // Inactivo
            var inactivo_css = 'ui-icon ui-icon-radio-off ui-icon-center';
            var inactivo_title = 'Inactivo';
            var inactivo_onclick = entity + 'JS.setEstatus( ' + id + ', 1 )';
            // Editar
            var editar_css = 'ui-icon ui-icon-pencil ui-icon-center';
            var editar_title = 'Editar';
            var editar_onclick = entity + 'JS.findById(' + id + ')';
            // Eliminar
            var eliminar_css = 'ui-icon ui-icon-trash ui-icon-center';
            var eliminar_title = 'Eliminar';
            var eliminar_onclick = entity + 'JS.prepareToRemove(' + id + ')';

            // funcionalidad para catalogoGrid
            // Activo
            var activoCatalogo_onclick = entity + 'JS.setEstatus( ' + id + ',' + grid[ i ]['organizacionId'] + ', 2 )';
            var activoCatalogoParametro_onclick = entity + 'JS.setEstatus( ' + id + ',' + grid[ i ]['catalogoId'] + ',' + grid[ i ]['organizacionId'] + ', 2 )';
            var activoCatalogoParametroLenguaje_onclick = entity + 'JS.setEstatus( ' + id + ',' + grid[ i ]['catalogoParametroId'] + ',' + grid[ i ]['organizacionId'] + ', 2 )';
            // Inactivo
            var inactivoCatalogo_onclick = entity + 'JS.setEstatus( ' + id + ',' + grid[ i ]['organizacionId'] + ', 1 )';
            var inactivoCatalogoParametro_onclick = entity + 'JS.setEstatus( ' + id + ',' + grid[ i ]['catalogoId'] + ',' + grid[ i ]['organizacionId'] + ', 1 )';
            var inactivoCatalogoParametroLenguaje_onclick = entity + 'JS.setEstatus( ' + id + ',' + grid[ i ]['catalogoParametroId'] + ',' + grid[ i ]['organizacionId'] + ', 1 )';
            // Editar
            var editarCatalogo_onclick = entity + 'JS.findById(' + id + ',' + grid[ i ]['organizacionId'] + ')';
            var editarCatalogoParametro_onclick = entity + 'JS.findById(' + id + ',' + grid[ i ]['catalogoId'] + ',' + grid[ i ]['organizacionId'] + ')';
            var editarCatalogoParametroLenguaje_onclick = entity + 'JS.findById(' + id + ',' + grid[ i ]['catalogoParametroId'] + ',' + grid[ i ]['organizacionId'] + ')';
            // Eliminar
            var eliminarCatalogo_onclick = entity + 'JS.prepareToRemove(' + id + ',' + grid[ i ]['organizacionId'] + ')';
            var eliminarCatalogoParametro_onclick = entity + 'JS.prepareToRemove(' + id + ',' + grid[ i ]['catalogoId'] + ',' + grid[ i ]['organizacionId'] + ')';
            var eliminarCatalogoParametroLenguaje_onclick = entity + 'JS.prepareToRemove(' + id + ',' + grid[ i ]['catalogoParametroId'] + ',' + grid[ i ]['organizacionId'] + ')';
            // funcionalidad para catalogoGrid
            // 
            // Parseado para fechas y select's
            var propiedades = utilObject.getProperties( grid[i] );
            for (var idx = 0; idx < propiedades.length; idx ++) {
                var attr = propiedades[ idx ] + '';
                // Especiales
                if ( attr == 'anidar' ) {
                    grid[ i ]['anidar'] = grid[ i ][ 'anidarNombre' ];
                } else if ( attr == 'modificacionUsuario' ) {
                    grid[ i ][ attr ] = grid[ i ].usuarioModificacion;
                }
                // Descripciones para select's
                if ( utilString.endsWith( attr, 'Id' ) ) {
                    if (attr != 'estatusId' && 
                        attr != 'eliminadoId' && 
                        attr != entity + 'Id' ) {
                        grid[ i ][ attr ] = grid[ i ][ attr.substring( 0, attr.length - 2 ) ];
                    }
                }
                // Fechas
                if ( utilString.containsIgnoreCase( attr, 'hora' ) ) {
                    if ( grid[ i ][ attr ]  != null ) {
                        grid[ i ][ attr ] = utilForm.getValorDateTimeFixedYear( grid[ i ][ attr ] );
                    }
                } else if ( utilString.containsIgnoreCase( attr, 'fecha' ) || 
                    utilString.containsIgnoreCase( attr, 'date' ) ) {
                    if ( grid[ i ][ attr ]  != null ) {
                        grid[ i ][ attr ] = jQuery.datepicker.formatDate('dd/mm/y', 
                            new Date( grid[ i ][ attr ] ) );
                    }
                }
            }
            if ( grid[ i ]['estatusId'] == 'Activo' || grid[ i ]['estatusId'] == 1 ) {
                grid[i].State = "<span class = '" + activo_css + "' title = '" + activo_title + "' style = 'cursor:pointer' onclick = '" + activo_onclick + "'></span>";
            } else {
                grid[i].State = "<span class = '" + inactivo_css + "' title = '" + inactivo_title + "' style = 'cursor:pointer' onclick = '" + inactivo_onclick + "'></span>";
            }

    // Funcionalidad para Organizacion

            if (gridId === '#organizacionGrid'){
                if ( grid[ i ]['creaEventosId'] == 'Activo' || grid[ i ]['creaEventosId'] == 1 ) {
                    grid[i].StateEventos = "<span class = '" + activo_css + "' title = '" + activo_title + "' style = 'cursor:pointer' onclick = '" + inactivo_eventos_onclick + "'></span>";
                    if ( grid[ i ]['servicioCompletoId'] == 'Activo' || grid[ i ]['servicioCompletoId'] == 1 ) {
                        grid[i].StateSC = "<span class = '" + activo_css + "' title = '" + activo_title + "' style = 'cursor:pointer' onclick = '" + inactivo_sc_onclick + "'></span>";
                    } else {
                        grid[i].StateSC = "<span class = '" + inactivo_css + "' title = '" + inactivo_title + "' style = 'cursor:pointer' onclick = '" + activo_sc_onclick + "'></span>";
                    }
                } else {
                    grid[i].StateEventos = "<span class = '" + inactivo_css + "' title = '" + inactivo_title + "' style = 'cursor:pointer' onclick = '" + activo_eventos_onclick + "'></span>";
                    grid[i].StateSC = "<span class = '" + inactivo_css + "' title = '" + inactivo_title + "' style = 'cursor:pointer' onclick = ''></span>";
                }
                
            }

    // Funcionalidad para Organizacion

//Funcionalidad para Catalogo
            if (gridId === '#catalogoGrid'||gridId === '#catalogoParametroGrid'||gridId === '#catalogoParametroLenguajeGrid'){

                if(gridId === '#catalogoGrid'){
                    grid[i].EditCatalogo = "<span class = '" + editar_css + "' title = '" + editar_title + "' style = 'cursor:pointer' onclick = '" + editarCatalogo_onclick + "'></span>";
                    grid[i].RemoveCatalogo = "<span class = '" + eliminar_css + "' title = '" + eliminar_title + "' style = 'cursor:pointer' onclick = '" + eliminarCatalogo_onclick + "'></span>";
                    
                    if ( grid[ i ]['estatusId'] == 'Activo' || grid[ i ]['estatusId'] == 1 ) {
                        grid[i].StateCatalogo = "<span class = '" + activo_css + "' title = '" + activo_title + "' style = 'cursor:pointer' onclick = '" + activoCatalogo_onclick + "'></span>";
                    } else {
                        grid[i].StateCatalogo = "<span class = '" + inactivo_css + "' title = '" + inactivo_title + "' style = 'cursor:pointer' onclick = '" + inactivoCatalogo_onclick + "'></span>";
                    }
                }else if(gridId === '#catalogoParametroGrid'){
                    grid[i].EditCatalogo = "<span class = '" + editar_css + "' title = '" + editar_title + "' style = 'cursor:pointer' onclick = '" + editarCatalogoParametro_onclick + "'></span>";
                    grid[i].RemoveCatalogo = "<span class = '" + eliminar_css + "' title = '" + eliminar_title + "' style = 'cursor:pointer' onclick = '" + eliminarCatalogoParametro_onclick + "'></span>";
                    if ( grid[ i ]['estatusId'] == 'Activo' || grid[ i ]['estatusId'] == 1 ) {
                        grid[i].StateCatalogo = "<span class = '" + activo_css + "' title = '" + activo_title + "' style = 'cursor:pointer' onclick = '" + activoCatalogoParametro_onclick + "'></span>";
                    } else {
                        grid[i].StateCatalogo = "<span class = '" + inactivo_css + "' title = '" + inactivo_title + "' style = 'cursor:pointer' onclick = '" + inactivoCatalogoParametro_onclick + "'></span>";
                    }
                }else if(gridId === '#catalogoParametroLenguajeGrid'){
                    grid[i].EditCatalogo = "<span class = '" + editar_css + "' title = '" + editar_title + "' style = 'cursor:pointer' onclick = '" + editarCatalogoParametroLenguaje_onclick + "'></span>";
                    grid[i].RemoveCatalogo = "<span class = '" + eliminar_css + "' title = '" + eliminar_title + "' style = 'cursor:pointer' onclick = '" + eliminarCatalogoParametroLenguaje_onclick + "'></span>";
                    if ( grid[ i ]['estatusId'] == 'Activo' || grid[ i ]['estatusId'] == 1 ) {
                        grid[i].StateCatalogo = "<span class = '" + activo_css + "' title = '" + activo_title + "' style = 'cursor:pointer' onclick = '" + activoCatalogoParametroLenguaje_onclick + "'></span>";
                    } else {
                        grid[i].StateCatalogo = "<span class = '" + inactivo_css + "' title = '" + inactivo_title + "' style = 'cursor:pointer' onclick = '" + inactivoCatalogoParametroLenguaje_onclick + "'></span>";
                    }
                }
            }
//Funcionalidad para Catalogo

            grid[i].Edit = "<span class = '" + editar_css + "' title = '" + editar_title + "' style = 'cursor:pointer' onclick = '" + editar_onclick + "'></span>";
            grid[i].Remove = "<span class = '" + eliminar_css + "' title = '" + eliminar_title + "' style = 'cursor:pointer' onclick = '" + eliminar_onclick + "'></span>";
            // setear decimales a dos en el grid de comision campo valor
            if (document.URL.split('/')[document.URL.split('/').length - 1] === 'comision.action') {
                if (grid[ i ]['valor']) {
                    var valor = new Number(grid[i].valor);
                    grid[i].valor = valor.toFixed(2);
                }
            }             
            jQuery( gridId ).addRowData( i + 1, grid[ i ] );
        }
        // Mensaje loading
        jQuery( '.loading' ).css( 'display', 'none' );
    },
    /**
     * Obtiene los registros seleccionados
     *
     * @param gridId (String) Es el id del grid.
     * @return Regresa la lista de los objetos seleccionados
     */
    gridGetSelectedRows : function ( gridId ) {
        var entity = utilString.removeSharp( utilString.removeGrid( gridId ) );
        var listaId = [];
        var selectedRows = jQuery( gridId ).jqGrid( 'getGridParam', 'selarrrow' );
        for (var i = 0 ; i < selectedRows.length ; i++ ) {
            var registro = jQuery( gridId ).getRowData( selectedRows[ i ] );
            var id = registro[ entity + "Id" ];
            if ( isNaN( id ) || id == "" ) {
                utilError.printError( "Error al obtener el id del registro" );
            } else if(gridId === '#catalogoGrid'){
                listaId[ listaId.length ] = {catalogoId: id, organizacionId: registro['organizacionId']}; 
            } else if(gridId === '#catalogoParametroGrid'){
                listaId[ listaId.length ] = {catalogoParametroId: id, organizacionId: registro['organizacionId'], catalogoId: registro['catalogoId']}; 
            } else if(gridId === '#catalogoParametroLenguajeGrid'){
                listaId[ listaId.length ] = {catalogoParametroLenguajeId: id, organizacionId: registro['organizacionId'], catalogoParametroId: registro['catalogoParametroId']}; 
            } else {
                listaId[ listaId.length ] = id; 
            }
        }
        return listaId;
    },
    /**
     * Funcion que construye la barra de herramientas en el widget jqGrid, la
     * barra de herramientas consta de menus que anaden funcionalidad al grid.
     *
     * @param gridId (String) Es el id del grid.
     * @param menus (Array) Es un array que contiene los nombres de los menus.
     * @param opciones (Array) Es un array que contiene las opciones de los menus.
     */
    buildGridToolbar : function( gridId, menus, opciones ){
        var topBarId = "#t_" + utilString.removeSharp( gridId );
        if ( !utilError.checkById( topBarId ) ){
            alert( "Mensaje de util.js/utilgrid.buildGridToolbar: no existe elemento con id: " + topBarId );
            return;
        }
        var entity = utilString.removeSharp( utilString.removeGrid( gridId ) );

        /**
         * El widget jqGrid tiene una barra de herramientas en la cual se le
         * pueden insertar diferentes menus o botones. Para que esta barra aparezca
         * es necesario configurar en el jqGrid el siguiente atributo:
         *
         * toolbar : [ true , 'top' ]
         *
         * Cuando es configurado de ese modo podemos acceder al elemento:
         * '#t_nombreDelGrid' en el cual se insertan elementos personalizados, en este
         * caso sera una barra de menu.
         */
        // Construir la barra de menus
        for ( var i = 0; i < menus.length; i++ ){

            var menuId = "menu" + i + entity;
            var opcionId = "opcionesMenu" + i + entity;

            // Construir cada menu
            jQuery( topBarId ).append("<a href='#' id='" + menuId + "'><span></span>" + menus[i] + "</a><div id='" + opcionId+ "'></div>");

            // Obtener el codigo HTML para crear cada opcion de menu
            var opcionHTML = "<ul>";
            var opcionLogic = opciones[i];
            for ( var opcion = 0; opcion < opcionLogic.length; opcion++ ) {
                var nombre = opcionLogic[opcion].split("@")[0];
                var ejecutar = opcionLogic[opcion].split("@")[1];
                opcionHTML += "<li><a href = 'javascript:" + ejecutar + "'>" + nombre + "</a></li>";
            }
            opcionHTML += "</ul>";
            jQuery( "#" + opcionId ).html( opcionHTML );

            // Insertar las opciones en su respectivo menu
            jQuery( "#" + menuId ).menuHor({
                content: jQuery( "#" + opcionId ).html(),
                flyOut: true
            });
        }

        // Fix para la apariencia de la barra de menus que vamos a crear.
        jQuery( topBarId ).css({
            'height' : '30px',
            'border-left' : 'none',
            'border-right' : 'none',
            'border-top' : 'none'
        });
        // Fix para que los menus se muestren en fila horizontal
        jQuery( topBarId + ' a' ).addClass( 'fg-button fg-button-icon-right' ).css( 'border', '0px' );
        // Pequena flecha que aparece a la derecha del nombre del menu
        jQuery( topBarId + ' span' ).addClass( 'ui-icon ui-icon-triangle-1-s' );
        // En este div se insertan las opciones de cada menu (elementos html), asi que hay que ocultarlo.
        jQuery( topBarId + ' div' ).addClass( 'hidden' );
    },
    /**
     * Funcion que cambia el numero de filas que el widget jqgrid muestra por pagina.
     *
     * @param gridId (String) Es el id del grid.
     * @param rowNumberHolder (String) Id del elemento que guarda el valor del numero de filas mostradas
     * @param rowNumberChanger (String) Id del elemento que contiene el nuevo valor de numero de filas mostradas
     */
    setRowNumber : function( gridId, rowNumberHolder, rowNumberChanger ) {
        // Obtener el valor capturado
        var filas = jQuery( rowNumberChanger ).val();
        // Solo hacer el cambio cuando el valor nuevo es diferente al actual
        if ( jQuery( rowNumberHolder ).val() != filas ){
            // Validar que sea un numero
            if ( !isNaN( filas ) ) {
                // Validar que sea mayor que 0
                if ( filas > 0 ) {
                    // Cambiar el numero de filas segun el valor obtenido
                    jQuery( rowNumberHolder ).attr( 'value', filas );
                }
                // Recargar el grid para aplicar los cambios
                jQuery( "#refresh_" + utilString.removeSharp( gridId ) ).click();
            }
        }
    },
    /**
     * Funcion que muestra/oculta columnas en el widget jqgrid basandose en checkboxes
     * contenidos dentro de un dialogo (modal) los cuales tienen el mismo nombre que las columnas.
     *
     * @param gridId (String)
     * @param dialogId (String)
     */
    setColumnVisibility : function ( gridId, dialogId ){
        /**
         * Aplicar los cambios que el usuario haya seleccionado,
         * mostrar las columnas que tengan el checkbox marcado y
         * ocultar las que no.
         */
        var checkboxes = jQuery( dialogId ).find( 'input' );
        checkboxes.each( function() {
            if ( jQuery( this ).is( ':checked' ) ){
                jQuery( gridId ).showCol( jQuery( this ).val() );
            } else {
                jQuery( gridId ).hideCol( jQuery( this ).val() );
            }
        });
    },
    setup : function ( gridId, gridPagerId, gridPageId, gridPageOrderId, 
			gridPageOrderTypeId, objectNameAsString ) {
        utilGrid.setSource( gridId, objectNameAsString );
		utilGrid.onChangeRowList( gridId, objectNameAsString );
        utilGrid.expandGridWidth( gridId );
        utilGrid.configurePaginator(gridId, gridPagerId, gridPageId, gridPageOrderId, gridPageOrderTypeId );
        utilGrid.configureNavigator(gridId, gridPagerId, gridPageId, gridPageOrderId, gridPageOrderTypeId );
    },
    /**
     * Función que se ejecut al cambiar la opción de numero de filas por grid
     */
    onChangeRowList : function( gridId, objectNameAsString ) {
        var id = '#gbox_' + utilString.removeSharp( gridId );
        jQuery( id + ' .ui-pg-selbox' ).unbind( 'change' );
        jQuery( id + ' .ui-pg-selbox' ).val( 
            jQuery( id + ' .ui-pg-selbox option:first' ).val() );
        jQuery( id + ' .ui-pg-selbox' ).change( function() {
            jQuery( gridId + 'RowsByPage' ).val( jQuery( this ).val() );
            jQuery( gridId + 'CurrentPage' ).val( 1 );
            eval( objectNameAsString + '.reloadGrid()' );
        });
    },
    setSource : function ( gridId, objectNameAsString ) {
        var id = utilString.removeSharp( gridId ) + '_source';
        var html = '<input type="hidden" id="' + id + '" value="' + objectNameAsString + '"/>';
        jQuery( 'body' ).append( html );
    },
    /**
     * El metodo onPaging de jqGrid 4.5.2 ha cambiado desde su version anterior.
     * onPaging acepta el parametro pgButton que es el boton que se presiona para
     * paginar (primero, previo, siguiente, ultimo). El calculo del numero de pagina
     * a mostrar ahora se debe de hacer manual. Solucion tomada de:
     * http://stackoverflow.com/questions/14333071/cant-get-the-page-parameter
     */
    getNewPageNumber : function( gridId, gridPagerId, pgButton ) {
        var ultima =  jQuery( gridId ).getGridParam( 'lastpage' );
        var newUserValue = jQuery( 'input.ui-pg-input', gridPagerId ).val();
        var newValue = 0;
        var currentValue = jQuery( gridId ).getGridParam( 'page' );
        if ( pgButton.indexOf( 'next' ) >= 0 ) {
            newValue = ++currentValue;
            if ( newValue > ultima ) newValue = ultima;
        } else if (pgButton.indexOf( 'prev' ) >= 0) {
            newValue = --currentValue;
            if ( newValue < 1 ) newValue = 1;
        } else if (pgButton.indexOf( 'last' ) >= 0) {
            newValue = ultima;
        } else if (pgButton.indexOf( 'first' ) >= 0) {
            newValue = 1;
        } else if (pgButton.indexOf( 'user' ) >= 0) {
            if ( newUserValue < 1 ) {
                newUserValue = 1;
            }
            if ( newUserValue > ultima ) {
                newUserValue = ultima;
            }
            newValue = newUserValue;
        }
        return newValue;
    },
    /**
     * Configurar al ancho del grid. Por default que ocupe el 100% del contenedor - 5px
     */
    expandGridWidth : function( gridId ) {
        //var minWidth = 850; // Ancho minimo para que los botones no se empalmen
        var minWidth = 1085; // Ancho minimo para que los botones no se empalmen
        var gridContainerWidth = jQuery( '.form-wrap' ).width() - 1;
        if ( utilError.isIframe() == true ) {
            jQuery( gridId ).setGridWidth( minWidth );
        } else {
            jQuery( gridId ).setGridWidth( gridContainerWidth );
        }
    },
    configurePaginator : function( gridId, gridPagerId, gridCurrentPage, gridOrderByColumn, gridOrderByType ) {
        // Configuracion de Paginator
        jQuery( gridId ).setGridParam({
            resizeStop : function(newwidth, index){
                utilGrid.expandGridWidth( '#' + jQuery( this ).attr( 'id' ) );
            },
            onSortCol: function( name, index, tipoOrden) {
                // Mensaje loading
                jQuery( '.loading' ).css( 'display', 'block' );
                jQuery( gridOrderByColumn ).val( name );
                jQuery( gridOrderByType ).val( tipoOrden );
                var entityAsString = jQuery( gridId + '_source' ).val();
                eval( entityAsString + '.findByCriteria(' + entityAsString + '.cacheDWR );' );
            },
            onPaging: function( pgButton ) {
                if ( jQuery( '#' + pgButton ).hasClass( 'ui-state-disabled' ) ) {
                    return;
                }
                // Mensaje loading
                jQuery( '.loading' ).css( 'display', 'block' );
                var paginaActual = utilGrid.getNewPageNumber( gridId, gridPagerId, pgButton );
                jQuery( gridCurrentPage ).val( paginaActual );
                var entityAsString = jQuery( gridId + '_source' ).val();
                eval( entityAsString + '.findByCriteria(' + entityAsString + '.cacheDWR );' );
                
            },
            gridComplete: function() {
                // Este evento es disparado cada que se agrega una fila de datos al grid
                // Mensaje loading
                jQuery( '.loading' ).css( 'display', 'block' );
                // Zebra
                jQuery( 'tr.jqgrow:odd' ).css( 'background', '#EEEEEE' );
                // Pagina actual mayor que paginas totales
                utilGrid.fixPaginaActualMayorQuePaginasTotales( this.id );
                // Resultados encontrados
                utilGrid.fixResultadosEncontrados( this.id );
                // Checkbox check-all
                utilGrid.fixCheckboxCheckAll( this.id );
            },
            onSelectRow : function( rowid, status, e) {
                if (utilError.isIframe()){
                    var registro = utilGrid.gridGetSelectedRows( '#' + this.id );
                    if ( registro.length == 0 ) {
                        window.parent.setIdFromIframe( 0 );
                    } else {
                        var id = registro[registro.length - 1];
                        if (id == undefined){
                            window.parent.setIdFromIframe( 0 );
                        } else {
                            window.parent.setIdFromIframe( id );
                        }
                    }
                }
            },
            beforeSelectRow: function (rowid, e) {
                var myGrid = $(this),
                i = $.jgrid.getCellIndex($(e.target).closest('td')[0]),
                cm = myGrid.jqGrid('getGridParam', 'colModel');
                return (cm[i].name === 'cb');
            }
        });
    },
    configureNavigator : function( gridId, gridPagerId, gridPageId, gridPageOrderId, gridPageOrderTypeId ) {
        jQuery( gridId ).jqGrid( 'navGrid', gridPagerId, {
            search  : false,
            refresh : false,
            edit    : false,
            add     : false,
            del     : false
        }).jqGrid( 'navButtonAdd', gridPagerId, {
            // Recargar
            id              : 'grid-action-actualizar',
            caption         : '',
            buttonicon      : 'ui-icon-refresh',
            onClickButton   : function () {
                // Mensaje loading
                jQuery( '.loading' ).css( 'display', 'block' );
                jQuery( gridPageId ).val('1');
                jQuery( gridPageOrderTypeId ).val( 'des' );
                jQuery( gridPageOrderId ).val( 'modificacionFecha' );
                eval( jQuery( gridId + '_source' ).val() + '.cacheDWR = null;' );
                eval( jQuery( gridId + '_source' ).val() + '.reloadGrid();' );
            },
            position        : 'last',
            title           : 'Actualizar',
            cursor          : 'pointer'
        }).navSeparatorAdd( gridPagerId , {
            // Separador
            sepclass : 'ui-separator',
            sepcontent: ''
        }).jqGrid( 'navButtonAdd', gridPagerId, {
            // Busquedas personalizadas
            id              : 'grid-action-buscar',
            caption         : '',
            buttonicon      : 'ui-icon-search',
            onClickButton   : function () {
                eval( jQuery( gridId + '_source' ).val() + '.openSearch();' );
            },
            position        : 'last',
            title           : 'Buscar',
            cursor          : 'pointer'
        }).navSeparatorAdd( gridPagerId , {
            // Separador
            sepclass : 'ui-separator',
            sepcontent: ''
        }).jqGrid( 'navButtonAdd', gridPagerId, {
            // Exportar datos a pdf o excel
            id              : 'grid-action-exportar',
            caption         : '',
            buttonicon      : 'ui-icon-extlink',
            onClickButton   : function () {
                utilDialog.showDialog( '#' + utilString.removeGrid( this.id ) + 'ColumnasExportar');
            },
            position        : 'last',
            title           : 'Exportar a PDF o Excel',
            cursor          : 'pointer'
        }).navSeparatorAdd( gridPagerId , {
            // Separador
            sepclass : 'ui-separator',
            sepcontent: ''
        }).jqGrid( 'navButtonAdd', gridPagerId, {
            // Activar/Inactivar masivo
            id              : 'grid-action-activar-inactivar',
            caption         : '',
            buttonicon      : 'ui-icon-radio-on',
            onClickButton   : function () {
                utilDialog.showDialog( '#' + utilString.removeGrid( this.id ) + 'SetEstatusActivoInactivo' );
            },
            position        : 'last',
            title           : 'Cambiar Estatus',
            cursor          : 'pointer'
        }).navSeparatorAdd( gridPagerId , {
            // Separador
            sepclass : 'ui-separator',
            sepcontent: ''
        }).jqGrid( 'navButtonAdd', gridPagerId, {
            // Eliminado masivo personalizado
            id              : 'grid-action-eliminar-masivo',
            caption         : '',
            buttonicon      : 'ui-icon-trash',
            onClickButton   : function () {
                eval( jQuery( gridId + '_source' ).val() + '.prepareToRemoveSelected();' );
            },
            position        : 'last',
            title           : 'Eliminar seleccionados',
            cursor          : 'pointer'
        });
    },
    fixPaginaActualMayorQuePaginasTotales : function( gridId ) {
        try {
            var paginasTotales = jQuery( '#' + gridId).getGridParam( 'lastpage' );
			if (utilString.contains(paginasTotales, ".")) {
                paginasTotales = utilString.replaceAll(paginasTotales, ".", "");
            }
            var paginaActual = jQuery( '#' + gridId).getGridParam('page');
            if (paginaActual > paginasTotales && paginasTotales != 0) {
                jQuery( '#next_' + gridId + 'PagerId' ).addClass('ui-state-disabled');
                jQuery( '#last_' + gridId + 'PagerId' ).addClass('ui-state-disabled');
                if ((paginaActual - 1) == paginasTotales ) {
                    jQuery( '#prev_' + gridId + 'PagerId' ).addClass('ui-state-disabled');
                    jQuery( '#first_' + gridId + 'PagerId' ).addClass('ui-state-disabled');
                }
                jQuery( '#' + gridId + 'PagerId_center' ).find('.ui-pg-input').val(paginasTotales);
                jQuery( '#' + gridId + 'CurrentPage').val( paginasTotales );
            }
        } catch (ex) {
        }
    },
    fixSinResultadosQueMostrar : function ( gridId ) {
        var info = 'Sin resultados que mostrar';
        jQuery( gridId + 'PagerId_right' ).find( '.ui-paging-info' ).html( info );
        var grid = utilString.removeSharp(gridId);
        jQuery( '#first_' + grid + 'PagerId' ).addClass('ui-state-disabled');
        jQuery( '#prev_' + grid + 'PagerId' ).addClass('ui-state-disabled');
        jQuery( '#next_' + grid + 'PagerId' ).addClass('ui-state-disabled');
        jQuery( '#last_' + grid + 'PagerId' ).addClass('ui-state-disabled');
    },
    fixResultadosEncontrados : function ( gridId ) {
        var resultadosTotales = jQuery( '#' + gridId ).getGridParam( 'total' );
        if ( resultadosTotales == null ) {
            resultadosTotales = 0;
        }
        var info = 'Resultados encontrados: ' + resultadosTotales;
        jQuery( '#' + gridId + 'PagerId_right' ).find( '.ui-paging-info' ).html( info );
    },
    fixCheckboxCheckAll : function ( gridId ) {
        if ( jQuery( '#cb_' + gridId ).is( ':checked' ) ) {
            var selected = utilGrid.gridGetSelectedRows( '#' + gridId );
            if (selected.length == 0) {
                utilForm.cleanInputGroup( jQuery( '#cb_' + gridId ).get(0) );
            }
        }
    }
}

/**
 * Objeto con funciones utiles para el manejo de formularios
 */
var utilForm = {
    /**
     * Limpia todos los campos de un formulario.
     *
     * @param formId (String) Es el nombre del formulario que se va a limpiar.
     */
    clean: function (formId) {
        jQuery(formId).find(':input').each(function () {
            var type = new String(this.type).toLowerCase();
            var tag = this.tagName.toLowerCase();
            var id = '#' + jQuery(this).attr('id');
            if (type === 'text' || type === 'password' || tag === 'textarea') {
                utilForm.cleanInputText(this);
            } else if (type === 'checkbox' || type === 'radio') {
                utilForm.cleanInputGroup(this);
            } else if (tag === 'select') {
                if (jQuery(this).hasClass('isAutocomplete')) {
                    utilInput.removeOptionsSelect(id);
                } else {
                    utilForm.cleanSelect(this);
                }
            }
        });
        // Color picker
        jQuery(formId).find('.color-selector').css(
                'backgroundColor', '#FFFFFF');
    },
    cleanInputText : function( element ) {
        element.value = "";
    },
    cleanInputGroup: function (element) {
        // Checkbox
        element.checked = false;
        // Radio
        var type = String(element.type).toLowerCase();
        if (type === 'radio') {
            var radioGroup = jQuery(element).attr('name');
            jQuery('input[type="radio"][name="' + radioGroup
                    + '"]').first().click();
        }
    },
    cleanSelect : function( element ) {
        element.selectedIndex = 0;
    },
    /**
     * Función que setea los valores en los elementos del formulario a partir de 
     * un objeto DWR obtenido de una consulta.
     * 
     * @param formId (String) Es el nombre del formulario.
     * @param objetoDWR (Object) Objeto que contiene la informacion de la consulta.
     */
    populate: function (formId, objetoDWR) {
        var propertyList = utilObject.getProperties(objetoDWR);
        for (var i = 0; i < propertyList.length; i++) {
            
            var elementName = propertyList[i];
            var jspElementId = formId + "_" + elementName;
            var value = objetoDWR[elementName];

            if (utilError.isRadio(formId, elementName)) {
                // Radio
                var radioStruts = jspElementId + value + '';
                utilForm.setValor(radioStruts, value, null);
            } else if (utilError.isAutocomplete(jspElementId)){
                // Autocomplete
                var text = null;
                var propertyDesc = elementName.substr(0, elementName.length-2);
                text = objetoDWR[propertyDesc];
                utilForm.setValor(jspElementId, value, text);
            } else {
                // Todo lo demas
                utilForm.setValor(formId + ' ' + jspElementId, value, null);
            }
        }
    },
    setValor: function (id, valor, texto) {
        if (jQuery(id).length > 0) {
            // RADIO
            if (jQuery(id).hasClass('radio')) {
                jQuery(id).prop('checked', true);
            } else if (jQuery(id).hasClass('isDate')) {
                // FECHA
                if (valor == null) {
                    jQuery(id).val('');
                } else {
                    if (valor.toString().match(/^(\d{1,2})\/(\d{1,2})\/(\d{2})$/)) {
                        /* BugFix: Cuando la fecha viene como dd/mm/y (31/12/14) debe
                         * ser seteada en el campo directamente */
                        jQuery(id).val(valor);
                    } else if (valor.toString().match(/^(\d{1,2})\/(\d{1,2})\/(\d{4})$/)) {
                        /* BugFix: Cuando la fecha viene como dd/mm/yy (31/12/2014) debe
                         * ser seteada en el campo directamente */
                        jQuery(id).val(valor);
                    } else {
                        var newVal = jQuery.datepicker.formatDate('dd/mm/yy',
                                new Date(valor));
                        jQuery(id).val(newVal);
                    }
                }
            } else if (jQuery(id).hasClass('isDateTime')) {
                // FECHA Y HORA
                if (valor == null) {
                    jQuery(id).val('');
                } else {
                    jQuery(id).val(utilForm.getValorDateTime(valor));
                }
            } else if (jQuery(id).hasClass('isColorPicker')) {
                // COLOR PICKER
                jQuery(id).val(valor);
                jQuery(id).prev().css('backgroundColor', valor);
            } else if (jQuery(id).hasClass('select')) {
                if (jQuery(id).hasClass('isAutocomplete')) {
                    // SELECT AUTOCOMPLETE
                    utilInput.removeOptionsSelect(id);
                    utilInput.addOptionToSelect(id, valor, texto);
                    jQuery(id).val(valor);
                    jQuery(id + 'Mask').val(texto);
                } else {
                    // SELECT
                    jQuery(id).val(valor);
                }
            } else if (jQuery(id).hasClass('textbox') ||
                    jQuery(id).hasClass('textarea')) {
                // TEXTBOX Y TEXTAREA
                jQuery(id).val(valor);
            } else if (jQuery(id).hasClass('checkbox')) {
                // CHECKBOX SIMPLE
                if (parseInt(valor) === 1) {
                    jQuery(id).prop('checked', true);
                } else {
                    jQuery(id).prop('checked', false);
                }
            } else {
                // CUALQUIER OTRO
                jQuery(id).val(valor);
            }
        }
    },
    getValorDateTime : function ( valor ) {
        var date = new Date(valor);
        var hora = date.getHours();
        if (hora < 10 ) hora = '0' + hora;
        var minuto = date.getMinutes();
        if (minuto < 10 ) minuto = '0' + minuto;
        return jQuery.datepicker.formatDate( 'dd/mm/yy', new Date( valor ) ) + " " + hora + ":" + minuto;
    },
    getValorDateTimeFixedYear : function ( valor ) {
        var date = new Date(valor);
        var hora = date.getHours();
        if (hora < 10 ) hora = '0' + hora;
        var minuto = date.getMinutes();
        if (minuto < 10 ) minuto = '0' + minuto;
        return jQuery.datepicker.formatDate( 'dd/mm/y', new Date( valor ) ) + " " + hora + ":" + minuto;
    },
    getValor: function (id) {
        if (jQuery('input[type=radio][name="' + id + '"]').length !== 0) {
            // RADIO
            return jQuery('input[type=radio][name="' + id + '"]:checked').val();
        } else if (jQuery(id).hasClass('isDate')) {
            // FECHA
            if (jQuery(id).val() != '') {
                return funciones.parseDate(jQuery(id).val());
            } else {
                return null;
            }
        } else if (jQuery(id).hasClass('isDateTime')) {
            // FECHA Y HORA
            if (jQuery(id).val() != '') {
                var data = jQuery(id).val();
                var ano = data.substring(6, 10);
                var mes = parseInt(data.substring(3, 5)) - 1;
                var dia = data.substring(0, 2);
                var hora = data.substring(11, 13);
                var minuto = data.substring(14, 16);
                return new Date(ano, mes, dia, hora, minuto, 0, 0);
            } else {
                return null;
            }
        } else if (jQuery(id).hasClass('isNumericFloat')) {
            // TEXTBOX (DECIMAL)
            // Puede contener el caracter "$"
            var newVal = jQuery(id).val();
            if (utilString.contains(newVal, "$")) {
                newVal = utilString.replaceAll(newVal, "$", "");
            }
            // Puede contener el caracter ","
            if (utilString.contains(newVal, ",")) {
                newVal = utilString.replaceAll(newVal, ",", "");
            }
            var valor = parseFloat(newVal);
            if (isNaN(valor)) {
                return null;
            } else {
                return valor;
            }
        } else if (jQuery(id).hasClass('select')) {
            // SELECT
            if (jQuery(id).val() == '') {
                return null;
            } else {
                return parseInt(jQuery(id).val());
            }
        } else if (jQuery(id).hasClass('textbox') ||
                jQuery(id).hasClass('textbox-colorpicker') ||
                jQuery(id).hasClass('textarea') ||
                jQuery(id).hasClass('textbox-upload')) {
            // TEXTBOX (TEXTO)
            if (jQuery(id).val() == '') {
                return null;
            } else {
                return jQuery(id).val();
            }
        } else if (jQuery(id).hasClass('checkbox')) {
            // CHECKBOX SIMPLE
            if (jQuery(id).is(':checked')) {
                return 1;
            } else {
                return 0;
            }
        } else {
            // CUALQUIER OTRO
            return jQuery(id).val();
        }
    },
    /**
     * Funcion que oculta los tooltips que estuviesen mostrados
     * TODO: Ocultar los tooltips de un determinado formulario
     *
     **/
    hideTooltips : function() {
        try {
            Tipped.hideAll();
        } catch(e){
            utilError.printError( e.toString() );
        }
    },
    /**
     * Funcion que realiza las siquientes acciones:
     * 1) Oculta los tooltips
     * 2) Limpia los campos del formulario
     *
     * @param formId (String) Es el id del formulario.
     *
     **/
    reset : function( formId ){
        this.hideTooltips();
        this.clean( formId );
    },
    /**
     * Función que desglosa un formulario por determinados campos
     * @param {type} formId es el id del formulario
     * @param {type} elementList es la lista de elementos que se tomaran como
     * base para desglosar el formulario
     * @param {type} shouldHideGrid define si el grid del formulario debe ocultarse
     * @returns {undefined}
     */
    desglose: function (selectIdList) {
        for (var i = 0; i < selectIdList.length; i++) {
            // Formulario a clonar, para obtenerlo se utiliza el propio select
            var formId = jQuery(selectIdList[i]).closest('form').attr('id');  
            
            // Contenido del formulario a clonar como HTML
            var formAsHTML = utilForm.getElementAsHTML('#' + formId);
            
            /* Para que la funcionalidad de desglose funcione es necesario
            identifcar cada formulario clonado los cuales tienen un id diferente 
            la unica propiedad que se mantiene constante es el atributo "name"*/
            var formNameSelector = 'form[name="' + utilString.removeSharp(formId) + '"]';
            
            // Por cada opcion del select se creara una copio del formulario
            jQuery(selectIdList[i] + ' option').each(function(index) {
                // Valor de la opción
                var value = jQuery(this).val();
                
                // Insertar el nuevo formulario en el documento
                jQuery(formNameSelector).last().after(formAsHTML);
                
                // Corregir su id
                var newId = jQuery(formNameSelector).last().attr('id') + '-' + value;
                jQuery(formNameSelector).last().attr('id', newId);
                
                // Remover clase identificadora
                jQuery('#' + newId).removeClass('base-desglose');
                
                // Remover elementos inecesarios del formulario clonado
                // Botones popup
                jQuery('#' + newId).find('.new-window').remove();
                // A partir del segundo clon se eliminan las etiquetas (.etiqueta 
                // y .etiqueta-small)
                if (parseInt(index) !== 0) {
                    utilForm.fixDesglose('#' + newId);
                }
                
                // Corregir el id de todos sus elementos
                jQuery('#' + newId).find(':input').each(function() {
                    var elId = jQuery(this).attr('id');
                    if (utilString.contains(elId, formId)) {
                        var elForm = jQuery(this).attr('id').split("_")[0];
                        var elId = jQuery(this).attr('id').split("_")[1];
                        jQuery(this).attr('id', elForm + '-' + value + "_" + elId);
                    }
                });
                
                // Nombre del select
                var elName = selectIdList[i].split("_")[1];
                
                // Setear la opcion
                utilInput.setSelectOptionByIndex('#' + newId + '_' + elName, 
                    parseInt(index));
                    
                // Deshabilitar el select
                jQuery('#' + newId + '_' + elName).prop('disabled', 'disabled');
            });
        }
    },
    cleanDesglose : function(formId) {
        var formNameSelector = 'form[name="' + utilString.removeSharp(formId) + '"]';
        jQuery(formNameSelector).each(function () {
            if (jQuery(this).hasClass('base-desglose') == false) {
                jQuery(this).remove();
            }
        });
    },
    fixDesglose : function(formSelector) {
        jQuery(formSelector).find('.etiqueta').each(function () {
                jQuery(this).remove();
        });
        jQuery(formSelector).find('.etiqueta-small').each(function () {
                jQuery(this).remove();
        });
    },
    getElementAsHTML : function(formId) {
        var formClone = jQuery('<div>').append(jQuery(formId).clone()).remove().html();
        return formClone;
    }
};

/**
 * Objeto con funciones utiles para construir objetos DWR con los valores del
 * formulario.
 */
var utilObject = {
	/**
     * Funcion que convierte las filas de un grid en una lista de objetos DWR.
     *
     * @param gridId, es el id del grid del cual se va a obtener la informacion.
     * @param objetoDWR, es un objeto javascript DWR que sirve como base para
     * extraer la información de cada fila del grid.
	 
     * @return regresa una lista de objetos javascript DWR
     * 
     */
    buildDWRObjectListByGridId : function( gridId, objetoDWR) {
        var allData = jQuery( gridId ).getRowData();
        var objectList = [];
        for (var i = 0; i < allData.length; i++ ) {
            objectList.push( 
                utilObject.buildDWRObjectByGridObject( 
                    jQuery.extend( true, {}, objetoDWR ), 
                    allData[ i ] ) 
                );
        }
        return objectList;
    },
    /**
     * Funcion que convierte un objeto fila de un grid a un objeto javascript 
     * DWR.
     *
     * @param objetoDWR, es un objeto javascript DWR que sirve como base para
     * extraer la información de la fila del grid.
     * @param objetoGrid, es un objeto construido a partir de la informacion de
     * una determinada fila del grid.
	 
     * @return regresa un objeto javascript DWR
     * 
     */
    buildDWRObjectByGridObject : function( objetoDWR, objetoGrid ) {
        var properties = utilObject.getProperties( objetoDWR );
        for ( var i = 0; i < properties.length; i++ ) {
            if ( properties[ i ].indexOf( 'fecha' ) >= 0 ) {
                if ( objetoGrid[ properties[ i ] ] == '' 
                    || objetoGrid[ properties[ i ] ] == null 
                    || objetoGrid[ properties[ i ] ] == undefined
                    || isNaN( Date.parse( objetoGrid[ properties[ i ] ] ) ) ) {
                    objetoDWR[ properties[ i ] + '' ] = null;
                } else {
                    objetoDWR[ properties[ i ] + '' ] = 
                    funciones.parseDate( objetoGrid[ properties[ i ] ] );
                }
            } else {
                objetoDWR[ properties[ i ] + '' ] = objetoGrid[ properties[ i ] + '' ];
            }
        }
        return objetoDWR;
    },
    /**
     * Construye un objeto DWR a partir de los valores en los campos de un
     * formulario
     *
     * @param formId (String) id del formulario.
     * @param objetoDWR (Object) un nuevo objeto DWR.
     * @return Regresa el objeto construido a partir de los elementos
     * del formulario.
     */
    buildObject: function (formId, objetoDWR) {
        var propertyList = utilObject.getProperties(objetoDWR);
        for (var i = 0; i < propertyList.length; i++) {
            var elementName = propertyList[i];
            var jspElementId = formId + "_" + elementName;
            if (utilError.isRadio(formId, elementName)) {
                objetoDWR[propertyList[i]] = utilForm.getValor(elementName);
            } else {
                objetoDWR[propertyList[i]] = utilForm.getValor(formId + ' ' + jspElementId);
            }
        }
        return objetoDWR;
    },
    /**
     * Funcion que obtiene las propiedades de un objeto, genera y regresa una lista de esas propiedades.
     *
     * @param object (Objeto) Es el objeto del cual se quieren obtener las propiedades
     * @return Regresa un array de strings con las propiedades del objeto.
     */
    getProperties : function( object ) {
        var objetoPropiedades = [];
        for( var key in object ) {
            objetoPropiedades.push( key + '' );
        }
        return objetoPropiedades;
    },
	/**
     * Función util para setear los campos de control de un objeto javascript
     * DWR a null.
     * 
     * @param object, es el objeto al cual se limpiaran los campos.
     * @return regresa el objeto con los campos de control limpios.
     */
    cleanCamposControl : function( object ) {
        object.estatusId = null;
        object.eliminadoId = null;
        object.creacionUsuario = null;
        object.modificacionUsuario = null;
        object.creacionFecha = null;
        object.modificacionFecha = null;
        return object;
    },
    /**
     * Función que resuleve las descripciones de las propiedades de un objeto.
     * Por ejemplo, estatusId=1 ==> estatus="Activo"
     * Utilizado al agregar datos del formulario al grid en la sección de 
     * detalle embebido.
     * 
     * @param formId, es el id del formulario de donde se obtienen las 
     * descripciones.
     * @param objectId, es el id de l objeto al cual se le setearan las 
     * descripciones.
     * @return regresa el objeto con las descripciones resueltas.
     */
    resolveDescription : function( formId, object ){
        var propiedades = utilObject.getProperties( object );
        for ( var i = 0; i < propiedades.length; i++ ) {
            if ( utilString.endsWith( propiedades[ i ], "Id" ) ) {
                var selector = formId + '_' + propiedades[ i ] + 
                ' option[ value = "' + object[ propiedades[ i ] ] + '" ]';
                var text = jQuery( selector ).text();
                var descriptionField = propiedades[ i ].substring( 0, 
                    propiedades[ i ].length - 2); 
                object[ descriptionField ] = text;                
            }
        }
        return object;
    }
}

var utilString = {
    /**
     * Cambia la primera letra de una cadena a minuscula.
     * 
     * @param str, es el string a modificar
     * @return regresa el texto modificado
     */
    setfirstLetterToLowerCase : function( str ) {
        return str.substring( 0, 1 ).toLowerCase() + 
        str.substring( 1, str.length );
    },
    /**
     * Cambia la primera letra de una cadena a mayuscula.
     * 
     * @param str, es el string a modificar
     * @return regresa el texto modificado
     */
    setfirstLetterToUpperCase : function( str ) {
        return str.substring( 0, 1 ).toUpperCase() + 
        str.substring( 1, str.length );
    },
    /**
     * Remueve el simbolo '#' o '.' de una cadena
     * 
     * @param str, es el string a modificar
     * @return regresa el texto modificado
     */
    removeSharp : function ( str ) {
        var firstChar = str.substring( 0, 1 );
        if ( firstChar == '#' || firstChar == '.' ) {
            return str.substring( 1, str.length );
        } else{
            return str;
        }
    },
    /**
     * Remueve el substring "Grid" de str.
     * 
     * @param str, es el string a modificar
     * @return regresa el texto modificado
     */
    removeGrid : function ( str ) {
        if ( str.indexOf( 'Grid' ) == -1 ) {
            return str;
        } else {
            return str.replace( 'Grid', '' );
        }
    },
    /**
     * Función de utilerias que valida texto (parametro str)
     * 
     * @param str, es el string a validar
     * @return regresa true si se pasa la validacion, de lo contrario regresa
     * false.
     */
    validaContrasena : function ( str ) {
        var wordMod = utilString.fixString( str.toLowerCase() );
        if ( wordMod == 'contrasena' || wordMod == 'contrase\u00F1a' 
            || wordMod == 'pass' || wordMod == 'password' ) {
            return true;
        }
        return false;
    },
    /**
     * Funcion que elimina saltos de linea de un texto
     * 
     * @param str, es el string a modificar
     * @return regresa el texto modificado
     */
    fixString : function ( str ) {
        return jQuery.trim( str.replace( /(\r\n|\n|\r)/gm, '' ) );
    },
    /**
     * Función que valida si un string tiene terminación substr.
     * 
     * @param str, es el string a validar,
     * @param substr, es la terminación a validar
     * @return regresa true si string termina en substr de lo contrario regresa
     * false.
     */
    endsWith : function( str, substr ) {
        return str.indexOf( substr, str.length - substr.length ) !== -1;
    },
	/**
	 * Función que valida si str contiene la secuencia de carateres en substr
	 *
     * @param str, es el string a validar
     * @param substr, es el substring a validar
     * @return regresa true si string contiene substr de lo contrario regresa
     * false.
     */
	contains: function (str, substr) {
		if (str === null) str = '';
		if (substr === null) substr = '';
		str += ''; substr += '';
		if (str.indexOf(substr) !== -1) {
			return true;
		}
		return false;
	},
	/**
	 * Función que valida si str contiene la secuencia de carateres en substr,
	 * ignorando mayusculas
	 *
     * @param str, es el string a validar
     * @param substr, es el substring a validar
     * @return regresa true si string contiene substr de lo contrario regresa
     * false.
     */
	containsIgnoreCase : function( str, substr ) {
		if ( str.toLowerCase().indexOf( substr.toLowerCase() ) != -1 ) {
			return true;
		}
		return false;
	},
	/**
	 * Busca todas las ocurrencias de substring dentro de string y las 
	 * reemplaza con replace
	 * @param {type} string
	 * @param {type} substring
	 * @param {type} replace
	 * @returns {unresolved}
	 */
	replaceAll: function (string, substring, replace) {
		return string.replace(new RegExp(utilString.escapeRegExp(substring), 'g'), replace);
	},
	/**
	 * Funcion auxiliar para utilString.replaceAll.
	 * @param {type} string
	 * @returns {unresolved}
	 */
	escapeRegExp: function (string) {
		return string.replace(/([.*+?^=!:${}()|\[\]\/\\])/g, "\\$1");
	}
}
/**
 * Objeto con funciones para el manejo de inputs
 */
utilInput = {
    /**
     * Funcion que convierte un input en tipos datepicker
     *
     * @param selector (String) es el selector de jquery (un id o una clase css)
     */
    setAsDatepicker : function ( selector ) {
        // Setea el input como datepicker
        jQuery( selector ).datepicker({
            changeMonth: true,
            changeYear: true
        });
        // Mascaras del input
        jQuery( selector ).mask( "99/99/9999" ,{
            placeholder:"_"
        });
    },
    /**
     * Funcion que convierte un input en tipo datetimepicker
     *
     * @param selector (String) es el selector de jquery (un id o una clase css)
     */
    setAsDateTimePicker : function ( selector ) {
        // Setea el input como dateTimePicker
        jQuery( selector ).datetimepicker({
            changeMonth: true,
            changeYear: true,
            timeFormat: "HH:mm",
            currentText: "Hoy",
            closeText: "Hecho",
            timeText: "Tiempo",
            hourText: "Hora",
            minuteText: "Minuto"
        });
        // Mascaras del input
        jQuery( selector ).mask( "99/99/9999 99:99" ,{
            placeholder:"_"
        });
    },
    /**
     * Funcion que convierte un input en tipos colorpicker
     *
     * @param selector (String) es el selector de jquery (un id o una clase css)
     */
    setAsColorpicker : function ( selector ) {
        jQuery( selector ).css({
            'width'     : '24px',
            'height'    : '24px',
            'float'     : 'left',
            'cursor'    : 'pointer',
            'background': 'url(../css/colorpickerthemes/select2-small.png) center'
        }).prop('title', 'Clic para cambiar color');

        jQuery( selector ).ColorPicker({
            color: '#f4f4f4',
            onShow: function ( colpkr ) {
                jQuery( colpkr ).fadeIn( 500 );
                return false;
            },
            onHide: function ( colpkr ) {
                jQuery( colpkr ).fadeOut( 500 );
                return false;
            },
            onChange: function (hsb, hex, rgb) {
                jQuery( selector ).css( 'backgroundColor', '#' + hex );
                jQuery( selector ).next().val( '#' + hex );
            }
        });
    },
	/**
     * Funión que elimina una opción de un elemento select en el jsp por el atributo
     * value de la opción.
     * @param selectId (string) es el id del elemento.
     * @param value (string) es el identificador de la opción para 
     * encontrarla y removerla.
     */
    removeSelectOptionByValue : function( selectId, value ) {
        jQuery( selectId + ' option[value="' + value + '"]' ).remove();
    },
    /**
     * Funión que elimina una opción de un elemento select en el jsp por el 
     * texto de la opción.
     * @param selectId (string) es el id del elemento.
     * @param text (string) es el text de la opción para 
     * encontrarla y removerla.
     */
    removeSelectOptionByText : function( selectId, text ) {
        jQuery( selectId + ' option').each( function() {
            if ( jQuery( this ).text().toLowerCase() == text.toLowerCase() ){
                jQuery( this ).remove();
            } 
        });
    },
    /**
     * Setea a un select una determinada opcion por su indice
     * @param {type} selectId id del select
     * @param {type} index indice de la opcion
     */
    setSelectOptionByIndex : function(selectId, index ) {
        jQuery(selectId).find('option').eq(index).prop('selected', true);
    },
    /**
     * Función que valida fecha inicial y final, la validación inlcuye:
     * 
     * @param fechaInicialId, es el id css del campo con fecha inicial
     * @param fechaFinalId, es el id css del campo con fecha final
     * @return true si las fechas son válidas de lo contrario false
     */
    isValidoFechaInicialFinal : function( fechaInicialId, fechaFinalId ) {
        if ( jQuery.trim( jQuery( fechaInicialId ).val() ) == '' || 
            jQuery.trim( jQuery( fechaFinalId ).val() ) == '' ) {
            return false;
        }
        var fechaInicial = utilForm.getValor( fechaInicialId );
        var fechaFinal = utilForm.getValor( fechaFinalId );
        if ( isNaN( fechaInicial.getTime() ) || 
            isNaN( fechaFinal.getTime() ) || 
            fechaInicial.getTime() > fechaFinal.getTime() ) {
            return false;
        }
        return true;
    },
    fixRadios : function( formId ) {
        jQuery( formId ).find( 'input[type="radio"]' ).each( function(){
            if ( jQuery( this ).val() == 0 ) {
                jQuery( this ).next().remove();
                jQuery( this ).remove();
            } else {
                jQuery( this ).next().addClass( 'radio-label' );
            }
        });
    },
    convertRadioGroupToHTMLSelect : function( radioGroupName ) {
        if ( utilString.contains( radioGroupName, '_' ) ) {
            radioGroupName = radioGroupName.split( '_' )[ 1 ];
        }
        var html = '';
        html += '<select>';
        jQuery( 'input[type="radio"][name="' + 
            utilString.removeSharp( radioGroupName ) + '"]' ).each( function() {
            html += '<option value="' + 
            jQuery( this ).val() + '">' + 
            jQuery( this ).next().html() + 
            '</option>';
        });
        html += '</select>';
        return html;
    },
    populateSelect : function( selectId, data, keyValue, keyText ) {
        utilInput.removeOptionsSelect(selectId);
        dwr.util.addOptions(utilString.removeSharp(selectId), data, keyValue, 
            keyText);
        /*
        utilInput.removeOptionsSelect( selectId );
        var html = '';
        for ( var i = 0; i < data.length; i++ ) {
            html += '<option value="' + data[ i ][ keyValue ] + '">' + 
            data[ i ][ keyText ] + '</option>';
        }
        jQuery( selectId ).html( html );
        */
    },
    /**
     * Función que busca una opcion por su atributo value dentro de un select.
     * @param {type} selectId es el id del select
     * @param {type} value es el valor de la opcion 
     * @returns {Boolean}
     */
    existsOptionByValueInSelect: function (selectId, value) {
        if (jQuery(selectId + ' option[value="' + value + '"]').length > 0) {
            return true;
        } else {
            return false;
        }
    },
    /**
     * Función que agrega un dummy a un componente select de un formulario
     * @param {type} id es el id del select
     * @param {type} dummyText es el texto del dummy
     * @returns {undefined}
     */
    addDummyToSelect : function( id, dummyText ) {
        var currentContent = jQuery( id ).html();
        jQuery( id ).html( '<option value="0">' + dummyText + '</option>' 
            + currentContent );
    },
    /**
     * Funcion que agrega una opcion a un elemento select.
     * @param {string} selectId es el id del select.
     * @param {int} value valor de la opción.
     * @param {string} text texto de la opción.
     */
    addOptionToSelect: function (selectId, value, text) {
        var currentContent = jQuery(selectId).html();
        jQuery(selectId).html('<option value="' + value + '">' + text +
                '</option>' + currentContent);
    },
	removeOptionsSelect : function( id ) {
        dwr.util.removeAllOptions(utilString.removeSharp(id));
        // jQuery( id ).empty();
    },
	removeOptionsSelectAndAddDummy : function( id, dummyText ) {
        utilInput.removeOptionsSelect(id);
		utilInput.addDummyToSelect( id, dummyText );
    },
    /**
     * Convierte un elemento select en un grupo de checkboxs.
     * @param {string} cssSelector id o clase css del select tag.
     * @param {string} label etiqueta de los checkbox.
     * @param {string} groupName grupo para asignar a los checkboxs.
     * @return {string} regresa el parametro groupName
     */
    convertHTMLSelectToHTMLCheckboxGroup: function (cssSelector, label, groupName) {
        var checkboxList = new Array();
        jQuery(cssSelector).find('option').each(function () {
            var checkbox = new Object();
            checkbox.value = jQuery(this).val();
            checkbox.text = jQuery(this).text();
            if (parseInt(checkbox.value) !== 0) {
                checkboxList.push(checkbox);
            }
        });
        var html = '<div class="pair-full">';
        html += '<div class="etiqueta">' + label + '</div>';
        for (var i = 0; i < checkboxList.length; i++) {
            html += '<input type="checkbox" name="' + groupName + '" value="' +
                    checkboxList[i].value + '">';
            html += '<label class="radio-label">' + checkboxList[i].text + '</label>';
        }
        html += '</div>';
        jQuery(cssSelector).parent().before(html);
        return groupName;
    },
    /**
     * Agrega funcionalidad click a cada chechbox de un determinado grupo.
     * @param {type} groupName es el nombre de grupo de checkboxs.
     * @param {type} selectId es el id del elemento select
     * @param {type} gridId es el id del grid
     * @param {type} btnId es el id del boton agregar del detalle
     */
    setCheckboxGroupFunctionality: function (groupName, selectId, gridId, btnId) {
        jQuery('input[name="' + groupName + '"]').each(function () {
            jQuery(this).click(function(){
                utilInput.setCheckboxFunctionality(groupName, selectId, gridId, btnId);
            });
        });
    },
    /**
     * Funcion que se ejecuta al dar un click sobre un checkbox, emula la 
     * funcionalidad de seleccion en el select y presionar el boton "Agregar" en
     * el formulario de detalle.
     * @param {type} groupName
     * @param {type} selectId
     * @param {type} gridId
     * @param {type} btnId
     * @returns {undefined}
     */
    setCheckboxFunctionality: function (groupName, selectId, gridId, btnId) {
        jQuery(gridId).clearGridData();
        jQuery('input[name="' + groupName + '"]').each(function() {
            if (jQuery(this).prop('checked') === true){
                jQuery(selectId).val(jQuery(this).val());
                jQuery(btnId).click();
            }
        });
    },
    convertHTMLSelectToAutocompleteTextbox: function (selectId, tolerance, callback) {
        // Id para el textbox para ingresar el string que inicia la 
        // función de "Autocomplete"
        var idMask = utilString.removeSharp(selectId) + 'Mask';
        // Id del div para dibujar las opciones que hagan match con el string
        // en forma de lista
        var idResult = utilString.removeSharp(selectId) + 'Result';
        // Id para el span que dibuja un icono que funciona como "Mostrar Todo"
        // var idMostrarTodo = utilString.removeSharp(selectId) + 'MostrarTodo';
        
        // Insertar elementos HTML para la función Autocomplete
        jQuery(selectId).after('<input type="text" class="textbox" id="' + 
                idMask + '">');
        jQuery('#' + idMask).after('<div id="' + idResult + 
                '" class="autocomplete-list-result shadow"></div>');
        jQuery('#' + idResult).hide();
        // var mostrarTodo = '<span class="ui-icon ui-icon-search icon-fix ';
        // mostrarTodo += 'autocomplete-show-all" ';
        // mostrarTodo += 'id="' + idMostrarTodo + '"';
        // mostrarTodo += 'title="Mostrar Todo"></span>';
        // jQuery('#' + idResult).parent().find('.etiqueta-small').after(mostrarTodo);
        
        // Ejecutar funcionalidad "Autocomplete" cada que se ingresa un caracter 
        // en el textbox creado.
        jQuery('#' + idMask).keyup(function () {
            var text = new String(jQuery.trim(jQuery(this).val() + ''));
            if (text === '') {
                utilInput.removeOptionsSelect(selectId);
                jQuery('#' + idResult).hide();
            } else {
                if (text.length >= parseInt(tolerance)) {
                    eval(callback);
                } else {
                    utilInput.removeOptionsSelect(selectId);
                    jQuery('#' + idResult).hide();
                }
            }
        });
        
        // Al perder el focus del textbox cerrar la lista de resultados
        jQuery('#' + idMask).change(function () {
            var hasSelection = false;
            jQuery('#' + idResult).find('.autocomplete-list-result-item').each(function () {
                if (jQuery(this).hasClass('ui-state-hover')) {
                    jQuery(this).click();
                    hasSelection = true;
                    return false;
                }
            });
            if (!hasSelection) {
                utilInput.removeOptionsSelect(selectId);
                jQuery('#' + idMask).val('');
                jQuery('#' + idResult).hide();
            }
        });
        
        // Funcionalidad para mostroar todos los resultados
        // jQuery('#' + idMostrarTodo).click(function() {
        //    var currentString = jQuery('#' + idMask).val();
        //    jQuery('#' + idMask).val('');
        //    jQuery('#' + idResult).empty();
        //    jQuery('#' + idResult).hide();
        //    eval(callback);
        //    jQuery('#' + idMask).val(currentString);
        //    jQuery('#' + idMask).click();
        // });
        
        jQuery('body').click(function () {
            jQuery('#' + idResult).hide();
        });
        
        // Ajustes finales
        jQuery('#' + idResult).css('width', jQuery('#' + idMask).width());
        jQuery(selectId).addClass('isAutocomplete');
        jQuery(selectId).css({
            'height': '0',
            'border': 'none',
            'padding': '0',
            'margin': '0'});
        
    },
    updateAutocompleteResult : function(selectId, list) {
        var property = selectId.split("_")[1];
        // Funcionalidad autocomplete
        if (jQuery(selectId + 'Result').length > 0) {
            if (list == null) {
                jQuery(selectId + 'Result').empty();
                jQuery(selectId + 'Result').hide();
            } else {
                if (list.length == 0) {
                    jQuery(selectId + 'Result').empty();
                    jQuery(selectId + 'Result').hide();
                } else {
                    var html = '';
                    for (var i = 0; i < list.length; i++) {
                        var object = list[i];
                        var id = object.metodoPagoId;
                        var nombre = object.nombre;
                        html += '<div id="' + property + '-' + id + '" '; 
                        html +='class="autocomplete-list-result-item">';
                        html += nombre + '</div>';
                    }
                    jQuery(selectId + 'Result').html(html);
                    
                    // Item Mouse Enter / Mouse Leave event
                    jQuery('.autocomplete-list-result-item').hover(function () {
                        jQuery(this).addClass('ui-state-hover ui-corner-all');
                    }, function () {
                        jQuery(this).removeClass('ui-state-hover ui-corner-all');
                    });
                    
                    // Item click event
                    jQuery('.autocomplete-list-result-item').unbind('click');
                    jQuery('.autocomplete-list-result-item').click(function () {
                        var val = new String(jQuery(this).attr('id')).split('-')[1];
                        jQuery(selectId).val(val);
                        var text = jQuery(selectId +' option[value="' + val + 
                                '"]').text();
                        jQuery(selectId + 'Mask').val(text);
                        jQuery(this).parent().hide();
                    });
                    
                    // Show result list
                    jQuery(selectId + 'Result').show();
                }
            }
        }
    }
}

// Objeto con funciones para el manejo de errores y validaciones
utilError = {
    isIframe : function () {
        var isIframe = ( window.location != window.parent.location ) ? true : false;
        return isIframe;
    },
    /**
     * Valida si un elemento en el formulario es de tipo radio (válido solo para 
     * radios generados por Struts).
     * 
     * @param formId id del formulario
     * @param elementName nombre del elemento
     * @returns {Boolean} regresa true si el elemento es radio
     */
    isRadio: function(formId, elementName) {
        if (jQuery(formId).find('input[type="radio"][name="' + elementName+ '"]').length > 0) {
            return true;
        } else {
            return false;
        }
    },
    /**
     * Valida si el elemento es autocomplete
     * @param {type} id es el id del elemento
     * @returns {Boolean} regresa true si el elemento es autocomplete, de lo 
     * contrario regresa false
     */
    isAutocomplete : function(id) {
        if (jQuery(id).length > 0) {
            if (jQuery(id).hasClass('isAutocomplete')) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    },
    validateDialogContainer : function () {
        if ( utilError.checkById( '#jquery_dialogs' ) == false ) {
            jQuery( 'body' ).append('<div id="jquery_dialogs" style="display: none; position: absolute; top: 0; left: -999;"></div>')
        }
    },
    checkById : function ( id ) {
        if ( jQuery( id ).length == 0 ) {
            return false;
        } else {
            return true;
        }
    },
    /**
     * Funcion que valida las columnas del widget jqGrid para eliminar elementos
     * no validos:
     * 0 : La columna de multi-seleccion
     * 1 : La columna de id
     * ultima columna : boton editar.
     *
     *
     */
    validateFields : function ( fieldList ) {
        var modList = new Array();
        /**
         * Iterar sobre los nombres de las columnas
         * Omitir la columna "multiple selection" y "id" (i = 2)
         * Omitir la columna "Editar" y "Eliminar" (columnNames.length - 2)
         */
        for ( var i = 2; i < fieldList.length - 2; i++ ) {
            modList[ i-2 ] = fieldList[ i ];
        }
        return modList;
    },
    printInfo : function ( text ) {
        try {
            console.info( text );
        } catch( ex ){}
    },
    printError : function ( text ) {
        try {
            console.error( text );
        } catch( ex ){}
    },
    printWarn  : function ( text ) {
        try {
            console.warn( text );
        } catch( ex ){}
    },
    printDebug : function ( text ) {
        try {
            console.debug( text );
        } catch( ex ){}
    },
    printTime: function (prefix) {
        var now = new Date();
        try {
            console.log(prefix + " - Tiempo: " + 
                    now.getHours() + ":" + 
                    now.getMinutes() + ":" + 
                    now.getSeconds() + ":" +
                    now.getMilliseconds());
        } catch (ex) {
        }
    }
}

