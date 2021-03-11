jQuery(document).ready(function () {
    // Fecha
    jQuery('.session-date').html(utilMisc.getTodayDate('-'));

    // Boton Salir
    jQuery('#logout').click(function () {
        window.location.href = "/admon/jsp/seguridad/logout.jsp"
    });
    // Boton Salir
    jQuery('.salir').click(function () {
        window.location.href = "/admon/jsp/seguridad/logout.jsp"
    });
    // Boton Minimizar
    jQuery('#minimize').click(function () {
        utilMinMax.minimizar();
    });
// Ventana de Perfil Usuario
    jQuery('.link,.link').hover(function () {
        jQuery(this).css('cursor', 'pointer');
    }, function () {
        jQuery(this).css('cursor', 'default');
    }).click(function () {
        utilWindow.openNewWindow('/admon/seguridad/perfilUsuario.action', '', 400);
    });
});

