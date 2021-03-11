<%@page import="com.admon.entity.admon.Pagina"%>
<%@page import="java.util.List"%>
<%@page import="com.admon.model.util.SessionVar"%>
<%SessionVar sessionVarMenuHorizontal = (SessionVar) session.getAttribute("SESSION_PORTAL");%>
<div class='menuHorizontal menuHorizontalFix ui-corner-all'>
    <!-- Inicio -->
    <div id='home-link' style='float: left;'>
        <a href='/admon/index.action'>
            <span class='ui-icon ui-icon-home icon-fix'></span>
            Inicio</a>
        <span>&nbsp;|&nbsp;</span>
    </div>

    <!-- Menu -->
    <div class='menu-horizontal'></div>

    <div class='sesion-container-small ui-widget-header'>
        <span class='ui-icon ui-icon-person icon-user'></span>
        <div class='username-small'
             title='<%=sessionVarMenuHorizontal.getNombre()%> <%=sessionVarMenuHorizontal.getApellidoPaterno()%>'>
            <%=sessionVarMenuHorizontal.getNombre()%> <%=sessionVarMenuHorizontal.getApellidoPaterno()%>
        </div>
        <!-- Botón Salir/Logout -->
        <div id='logout-small' title='Salir'>
            <span class='ui-icon ui-icon-circle-close'> </span>
        </div>
        <!-- Botón Minimizar -->
        <div id='maximize-small' title='Minimizar'>
            <span class='ui-icon ui-icon-circle-triangle-s'> </span>
        </div>
        <div style='clear:both'></div>
    </div>
</div>
<script>
    jQuery(document).ready(function () {
        jQuery('#logout-small').click(function () {
            window.location.href = "/admon/jsp/seguridad/logout.jsp"
        });
        jQuery('#maximize-small').click(function () {
            utilMinMax.maximizar();
        });

        var paginas = [];
    <%List<Pagina> pages = sessionVarMenuHorizontal.getPagina();
        for (int i = 0; i < pages.size(); i++) {%>
        var pagina = {
            paginaId: <%= pages.get(i).getPaginaId()%>,
            nombre: '<%= pages.get(i).getNombre()%>',
            anidar: <%= pages.get(i).getAnidar()%>,
            noIncluirEnMenu: <%= pages.get(i).getNoIncluirEnMenu()%>,
            url: '<%= pages.get(i).getUrl()%>',
            orden: <%= pages.get(i).getOrden()%>
        };
        paginas.push(pagina);
    <%}%>
        utilMenu.createMenu(paginas, '.menu-horizontal');
    });
</script>  


