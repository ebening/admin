/**
 * Este script es ejecutado cuando la pagina esta lista
 * (todas las imagenes han sido cargadas y el DOM esta listo),
 * Es el punto de entrada para visualizar la pagina.
 */
jQuery( window ).load(function() {
    if ( utilError.isIframe() ) {
        utilWindow.prepareIframe();
    } else {
        utilScroll.ini();
        utilMinMax.checkState();
        utilWindow.fade();
        jQuery( window ).scroll( function () {
            utilScroll.scroll();
        });
    }
});

