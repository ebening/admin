<%@taglib  prefix='s' uri='/struts-tags'%>
<%@page import="com.admon.model.util.SessionVar"%>
<%SessionVar sv = (SessionVar) session.getAttribute("SESSION_PORTAL");%>

<!-- start: sidebar -->
<aside id="sidebar-left" class="sidebar-left">

    <div class="sidebar-header">
        <div class="sidebar-title">
            <s:text name='key_encabezado_menu'/>
        </div>
        <div class="sidebar-toggle hidden-xs" data-toggle-class="sidebar-left-collapsed" data-target="html" data-fire-event="sidebar-left-toggle">
            <i class="fa fa-bars" aria-label="Toggle sidebar"></i>
        </div>
    </div>

    <div class="nano">
        <div class="nano-content">
            <nav id="menu" class="nav-main" role="navigation">
                <%=sv.getHtmlMenu()%>
            </nav>
        </div>

        <script>
            // Preserve Scroll Position
            if (typeof localStorage !== 'undefined') {
                if (localStorage.getItem('sidebar-left-position') !== null) {
                    var initialPosition = localStorage.getItem('sidebar-left-position'),
                            sidebarLeft = document.querySelector('#sidebar-left .nano-content');
                    sidebarLeft.scrollTop = initialPosition;
                }
            }
            
            jQuery(document).ready(function () {
                jQuery(window).on('sidebar-left-toggle', function (e, toggle) {
                    jQuery('.ui-jqgrid').each(function () {
                        var id = jQuery(this).attr('id');
                        id = id.replace("gbox_", "");
                        jQuery('#' + id).setGridWidth(0);
                    });
                    setTimeout(function () {
                        jQuery('.ui-jqgrid').each(function () {
                            var id = jQuery(this).attr('id');
                            id = id.replace("gbox_", "");
                            var w = jQuery(this).closest('.grid-container').width();
                            jQuery('#' + id).setGridWidth(w);
                        });
                    }, 100);
                });
            });
        </script>

    </div>

</aside>
<!-- end: sidebar -->