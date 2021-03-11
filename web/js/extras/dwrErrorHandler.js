/**
 * Funci&oacute;n que se ejecuta cuando ocurre un error detectado por DWR
 */
dwr.engine.setErrorHandler(function (errorString, exception) {
    jQuery(document).ready(function () {
        utilDialog.setStandardDialog('#dwrError', 'Error', '', ['Cerrar@']);
        var errorDesc = "<b>Ha ocurrido un error inesperado al realizar la operaci&oacute;n.</b><br><br>";
        errorDesc += 'Reintente nuevamente, en caso de que el error persista consulte con el administrador del sistema.<br><br>';
        errorDesc += '<b>Mensaje de DWR:</b><br>';

        errorDesc += '<b>Exception cause:</b><br>';
        errorDesc += '<div style="width: 100%; height: 60px; border: 1px solid #C4C4C4; overflow: auto;">';
        errorDesc += dwr.util.toDescriptiveString(exception, 3);
        errorDesc += '</div>';

        errorDesc += '<b>Error string:</b><br>';
        errorDesc += '<div style="width: 100%; height: 60px; border: 1px solid #C4C4C4; overflow: auto;">';
        errorDesc += errorString;
        errorDesc += '</div>';

        errorDesc += '<b>Exception message:</b><br>';
        errorDesc += '<div style="width: 100%; height: 60px; border: 1px solid #C4C4C4; overflow: auto;">';
        errorDesc += exception.message;
        errorDesc += '</div>';

        jQuery('.d-content').html(errorDesc + '<br>' + 'Verificar el log del servidor para m&aacute;s informaci&oacute;n.');

        jQuery('#dwrError').dialog({
            width: 600,
            close: function (event, ui) {
                jQuery("#dwrError").remove();
                try {
                    utilEffect.hideProgressBar();
                } catch (e) {
                }
            },
            open: function (event, ui) {
                jQuery('#dwrError').dialog('option', 'position', 'center');
            }
        });
        utilDialog.showDialog('#dwrError');
    });
});

// Sesi&oacute;n expirada
dwr.engine.setTextHtmlHandler(function () {
    jQuery(document).ready(function () {
        utilDialog.setStandardDialog('#dwrError', 'Sesi&oacute;n terminada', '', ['Cerrar@']);
        var errorDesc = '';
        errorDesc += 'La sesi&oacute;n a expirado.<br><br>';
        errorDesc += 'Al cerrar este mensaje ser&aacute; redirigido(a) a la pantalla de <b>Login</b>';
        jQuery('.d-content').html(errorDesc);
        utilDialog.showDialog('#dwrError');
        jQuery('#dwrError').dialog({
            close: function () {
                jQuery('#logout').click();
            }
        });
    });
});

