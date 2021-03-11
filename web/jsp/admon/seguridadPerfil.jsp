<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib  prefix='s' uri='/struts-tags'%>
<head>
    <script src='<s:url value='../dwr/interface/SeguridadPerfilRolDWR.js'/>' type='text/javascript' charset="UTF-8"></script>

    <title>
        <s:property escape='false' value='nombreActionMenu'/>
    </title>
</head>
<body>
    <div class='form-wrap panel-body'>
        <div class='titulo ui-corner-top'>
            <div class='title-text hidden'><s:property escape='false' value='nombreActionMenu'/></div>
            <div class='button-container'>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-default' id='btnNewPais'><s:text name='key_pais_bnew'/></div>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-primary' id='btnSavePais'><s:text name='key_pais_bsave'/></div>
            </div>
            <div style='clear: both'></div>
        </div>
        <!-- Hidden -->
        <input type='hidden' id='key_pais_title' value='<s:text name='key_pais_title'/>'/>
        <input type='hidden' id='key_pais_modificacionUsuario' value='<s:text name='key_pais_modificacionUsuario'/>'/>
        <input type='hidden' id='key_pais_modificacionFecha' value='<s:text name='key_pais_modificacionFecha'/>'/>
        <input type='hidden' id='paisGridOrderByColumn' value='modificacionFecha'/>
        <input type='hidden' id='paisGridOrderByType' value='des'/>
        <input type='hidden' id='paisGridCurrentPage' value='1'/>
        <input type='hidden' id='paisGridRowsByPage' value='10'/>
        <input type='hidden' id='idFromIframe' value=''/>
        <s:hidden id='gridVisiblePais' name='gridVisiblePais'/>
        <s:hidden id='gridIndividualModePais' name='gridIndividualModePais'/>

        <s:form id='paisform' name='paisform' cssClass='pair-wrap'>
            
        </s:form>


    </div>
</body>

