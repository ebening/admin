package com.admon.model.admon;

import com.admon.model.BaseModel;
import static com.opensymphony.xwork2.Action.SUCCESS;

public class CatalogoParametroAction extends BaseModel {

    public CatalogoParametroAction() {
    }

    /*
     * Método que llena los elementos del formulario del JSP mediante Struts al cargar la página catalogoParametro.jsp 
     * @return Regresa un código de tipo String, el método execute() es ejecutado y la respuesta es evaluada, finalmente se manda llamar al JSP correspondiente según la respuesta.
     */
    @Override
    public String execute() {
        return SUCCESS;
    }

}
