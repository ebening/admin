jQuery(document).ready(function () {
    jQuery('.foot').removeClass('ui-corner-bottom').css({
        'position': 'absolute',
        'bottom': '0',
        'width': '100%'});

    // Apariencia de los botones
    jQuery('.button').button({
        icons: {
            primary: 'ui-icon-unlocked'
        }
    });

    // Tooltip campo usuario
//    jQuery( '#recuperarContrasenaform_usuario' ).hover( function() {
//        utilEffect.showRegularTooltip( '#recuperarContrasenaform_usuario', 
//            jQuery( '#key_recuperarcontrasena_usuariotooltip' ).val() );
//    }, function(){
//        utilEffect.hideRegularTooltip( '#recuperarContrasenaform_usuario' );
//    });

    // Crear el mensaje 'Espere...'
    utilEffect.createProgressBar('Espere...');

    // Focus
    jQuery('#recuperarContrasenaform_usuario').focus();

    // Acción al presionar el botón de recuperar contraseña
    jQuery('#btnRecuperar').click(function () {
        recuperarContrasenaJS.prepareToRecuperarContrasena();
    });

    // Acción al presionar la tecla : ENTER
    jQuery('#recuperarContrasenaform').keydown(function (e) {
        if (e.which == 13) {
            jQuery('#btnRecuperar').click();
        }
    });

    // Modal que muestra la respuesta
    jQuery('#d-respuesta').dialog({
        autoOpen: false,
        resizable: false,
        modal: true,
        show: 'highlight',
        open: function (event, ui) {
            jQuery(this).dialog('option', 'position', 'center');
        },
        buttons: {
            'Aceptar': function () {
                jQuery(this).dialog('close');
            }
        }
    });
});

var recuperarContrasenaJS = {
    /*
     * Función que valida el campo usuario.
     */
    prepareToRecuperarContrasena: function () {
        var validation = validanguage.validateForm('recuperarContrasenaform');
        if (validation.result) {
            recuperarContrasenaJS.recuperarContrasena();
        }
    },
    /*
     * Función que inicia el proceso de recuperación de contraseña.
     */
    recuperarContrasena: function () {
        utilForm.hideTooltips();
        utilEffect.showProgressBar();
        RecuperarContrasenaDWR.recuperarContrasena(
                jQuery.trim(jQuery('#recuperarContrasenaform_usuario').val()),
                recuperarContrasenaJS.recuperarContrasenaCallback);
    },
    /*
     * Callback de la función recuperarContrasena(), esta función es llamada por DWR al
     * terminar de realizar la operación.
     * 
     * @param respuesta (String) es una cadena de texto que define la respuesta del proceso de 
     * recuperacion de contraseña.
     */
    recuperarContrasenaCallback: function (respuesta) {
        utilEffect.hideProgressBar();
        jQuery('#respuesta-content').html(respuesta);
        jQuery('#d-respuesta').dialog('open');
    }
}

// Validaciones
validanguage.el.recuperarContrasenaform_usuario = {
    characters: {
        mode: 'allow',
        expression: 'alphanumericspecial ',
        suppress: false
    },
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]
}

