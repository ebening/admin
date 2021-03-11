<%java.util.ResourceBundle empresa = java.util.ResourceBundle.getBundle("empresa");%>
<%java.util.ResourceBundle version = java.util.ResourceBundle.getBundle("version");%>
<div class='foot ui-widget ui-widget-header no-border'>
    ver &nbsp;<%=empresa.getString("key_aplicacion_version")%>&nbsp; 
    build &nbsp;<a href='http://software.novasys.com.mx:8383/browse/COR-CR-<%=version.getString("build.number")%>'><%=version.getString("build.number")%></a> &nbsp; 
&nbsp;<%=version.getString("build.timestamp")%>
</div>

