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

    // Crear el mensaje 'Espere...'
    utilEffect.createProgressBar('Validando usuario, espere...');
    // Diálogo error en login
    utilDialog.setStandardDialog('#d-loginError',
            jQuery('#key_login_loginerrortitulo').val(),
            jQuery('#key_login_loginerrormensaje').val(), ['Aceptar@']);

    // Diálogo error en conexion
    utilDialog.setStandardDialog('#d-loginConexion',
            'Error',
            jQuery('#msgErrorServer').val() + '<br>' +
            jQuery('#msgReintentar').val(),
            ['Aceptar@']);

    // Focus
    jQuery('#loginform_usuario').focus();


    // Acción al presionar el botón : ENTRAR
    jQuery('.btnEntrar').click(function () {
        loginJS.prepareToLogin();
    });

    // Acción al presionar la tecla : ENTER
    jQuery('body').keydown(function (e) {
        if (e.which == 13) {
            jQuery('.btnEntrar').click();
        }
    });

    // Tooltip campo usuario
//    jQuery( '#loginform_usuario' ).hover( function() {
//        utilEffect.showRegularTooltip( '#loginform_usuario', 
//            jQuery( '#key_login_usuariotooltip' ).val() );
//    }, function(){
//        utilEffect.hideRegularTooltip( '#loginform_usuario' );
//    });
//
//    // Tooltip campo contraseÃ±a
//    jQuery( '#loginform_contrasena' ).hover( function() {
//        utilEffect.showRegularTooltip( '#loginform_contrasena', 
//            jQuery( '#key_login_contrasenatooltip' ).val() );
//    }, function() {
//        utilEffect.hideRegularTooltip( '#loginform_contrasena' );
//    });
});

var loginJS = {
    /*
     *
     * Función que intenta hacer un login con los datos ingresados.
     */
    autologin: function (user, pass) {
        utilForm.hideTooltips();
        utilEffect.showProgressBar();
        LoginDWR.login(user, pass, loginJS.autologinCallback);
    },
    /*
     * Callback de la función login(), esta función es llamada por DWR al
     * terminar de realizar la operación.
     * 
     * @param url (String) si el usuario es autentico se redigira a la url 
     * especificada, de lo contrario se mostrará un mensaje de error
     */
    autologinCallback: function (url) {
        utilEffect.hideProgressBar();
        if (url == '') {
            //Android.disconnect();
        } else if (url == null) {
            //Android.disconnect();
        } else {
            window.location.href = url;
        }
    },
    /*
     * Función valida que los campos del login.
     */
    prepareToLogin: function () {
        var validation = validanguage.validateForm('loginform');
        if (validation.result) {
            loginJS.login();
        }
    },
    /*
     * Función que intenta hacer un login con los datos ingresados.
     */
    login: function () {
        utilForm.hideTooltips();
        utilEffect.showProgressBar();
        LoginDWR.login(jQuery.trim(jQuery('#loginform_usuario').val()),
                jQuery.trim(jQuery('#loginform_contrasena').val()),
                loginJS.loginCallback);
    },
    /*
     * Callback de la función login(), esta función es llamada por DWR al
     * terminar de realizar la operación.
     * 
     * @param url (String) si el usuario es autentico se redigira a la url 
     * especificada, de lo contrario se mostrará un mensaje de error
     */
    loginCallback: function (url) {
        utilEffect.hideProgressBar();
        if (url == '') {
            utilDialog.showDialog('#d-loginConexion');
        } else if (url == null) {
            utilDialog.showDialog('#d-loginError');
        } else {
            window.location.href = url;
        }
    }
}

// Validaciones
validanguage.el.loginform_usuario = {
    characters: {
        mode: 'allow',
        expression: 'alphanumericspecial ',
        suppress: false
    },
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]
}

validanguage.el.loginform_contrasena = {
    characters: {
        mode: 'allow',
        expression: 'alphanumericspecial ',
        suppress: false
    },
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]
}

