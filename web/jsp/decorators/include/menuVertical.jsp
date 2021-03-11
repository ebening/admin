<%@page import="com.admon.model.util.SessionVar"%>
<%SessionVar sv = (SessionVar) session.getAttribute("SESSION_PORTAL");%>
<%=sv.getHtmlMenu()%>
<script>
    jQuery(document).ready(function () {
        jQuery('.m-root').menu();
        jQuery('.ui-menu-icon').hide();
    });
</script>
