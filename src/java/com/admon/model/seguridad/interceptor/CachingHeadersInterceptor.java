package com.admon.model.seguridad.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.StrutsStatics;

public class CachingHeadersInterceptor implements Interceptor {

    @Override
    public void destroy() {
    }

    @Override
    public void init() {
    }

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {

        /*
*
* response.addHeader("Pragma", "no-cache");
* response.setHeader("Cache-Control",
* "no-cache,no-store,must-revalidate");
* response.addHeader("Cache-Control", "pre-check=0,post-check=0");
* response.setDateHeader("Expires", -1);
*
         */
        ActionContext context = actionInvocation.getInvocationContext();
        HttpServletResponse response = (HttpServletResponse) context.get(StrutsStatics.HTTP_RESPONSE);
        if (response != null) {
            response.setHeader("Cache-Control", "no-cache, no-store");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", -1);
        }
        return actionInvocation.invoke();
    }
}
