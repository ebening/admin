jQuery(document).ready(function () {
    utilEffect.createProgressBar('Espere...');
    jQuery('.button').button();

    jQuery('#confirmar').click(function () {
        perfilUsuarioJS.prepareToCambiarContrasena();
    }).css({'width': 'calc(100% - 8px)'}).customButtonEffectBlue('#confirmar');
    ;

    jQuery('#cancelar').click(function () {
        perfilUsuarioJS.cancelar();
    });

    jQuery('#enviarCorreo').prop('checked', true);

    jQuery('#contrasenaAnterior').showPassword();
    jQuery('#contrasena').showPassword();
    jQuery('#confirmarContrasena').showPassword();
// jQuery( '#toggle-view' ).onoff();
});

var perfilUsuarioJS = {
    /**
     * Función previa al cambio de contraseña, se realizan las validaciones
     * requeridas.
     */
    prepareToCambiarContrasena: function () {
        var validation = validanguage.validateForm('perfilUsuarioform');
        if (validation.result) {
            perfilUsuarioJS.cambiarContrasena();
        }
    },
    /**
     * Función que inicia el proceso de cambio de contraseña
     */
    cambiarContrasena: function () {
        utilEffect.showProgressBar();
        PerfilUsuarioDWR.cambiarContrasena(
                jQuery('#contrasenaAnterior').val(),
                jQuery('#contrasena').val(),
                jQuery('#perfilUsuarioform_enviarCorreo').is(':checked'),
                perfilUsuarioJS.cambiarContrasenaCallback);
    },
    /**
     * Callback de la función cambiarContrasena(), esta función  es llamada por
     * DWR al terminar de realizar la operación
     */
    cambiarContrasenaCallback: function (resultCode) {
        utilEffect.hideProgressBar();
        if (resultCode == 0) {
            jQuery().toastmessage('showToast', {
                text: jQuery('#msgContrasenaCambio').val(),
                sticky: true,
                position: 'middle-center',
                type: 'success',
                closeText: '<i class="fa fa-times"></i>',
                close: function () {
                }
            });
        } else if (resultCode == 1) {
            jQuery().toastmessage('showToast', {
                text: jQuery('#msgContrasenaInvalida').val(),
                sticky: true,
                position: 'middle-center',
                type: 'warning',
                closeText: '<i class="fa fa-times"></i>',
                close: function () {
                }
            });
        } else if (resultCode == 2) {
            jQuery().toastmessage('showToast', {
                text: jQuery('#msgContrasenaCambioError').val(),
                sticky: true,
                position: 'middle-center',
                type: 'error',
                closeText: '<i class="fa fa-times"></i>',
                close: function () {
                }
            });
        }
        utilForm.reset('#perfilUsuarioform');
    },
    cancelar: function () {
        utilForm.reset('#perfilUsuarioform');
    }
}

// Validación contrasena anterior
validanguage.el.contrasenaAnterior = {
    characters: {
        mode: 'allow',
        expression: 'alphanumericspecial',
        suppress: false
    },
    validations: [ProfileStripWhitespace, ProfileRequiredField]
}

// Validación nueva contrasela
validanguage.el.contrasena = {
    characters: {
        mode: 'allow',
        expression: 'alphanumericspecial',
        suppress: false
    },
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter, {
            name: function (text) {
                if (text.length > 32) {
                    return false;
                } else {
                    return true;
                }
            },
            errorMsg: 'Solo se permiten 32 carácteres como máximo.',
            onerror: utilEffect.showValidationTooltip,
            onsuccess: utilEffect.hideValidationTooltip
        }, {
            name: function (text) {
                if (text.length < 4) {
                    return false;
                } else {
                    return true;
                }
            },
            errorMsg: 'La contraseña debe contener al menos 4 carácteres',
            onerror: utilEffect.showValidationTooltip,
            onsuccess: utilEffect.hideValidationTooltip
        }, {
            name: function (text) {
                if (text == jQuery('#contrasenaAnterior').val()) {
                    return false;
                } else {
                    return true;
                }
            },
            errorMsg: 'La constraseña es la misma que la original',
            onerror: utilEffect.showValidationTooltip,
            onsuccess: utilEffect.hideValidationTooltip
        }]
}

// Validación confirmar contraseña
validanguage.el.confirmarContrasena = {
    characters: {
        mode: 'allow',
        expression: 'alphanumericspecial',
        suppress: false
    },
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter, {
            name: function () {
                return !!(this.value == jQuery('#contrasena').val());
            },
            errorMsg: 'La contraseña es diferente',
            onerror: utilEffect.showValidationTooltip,
            onsuccess: utilEffect.hideValidationTooltip
        }]
}

