package com.admon.model;

import com.admon.entity.admon.Pagina;
import com.admon.model.util.SessionVar;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.*;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.directwebremoting.WebContextFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.xhtmlrenderer.pdf.ITextRenderer;

public class BaseModel extends ActionSupport implements ServletRequestAware, ServletResponseAware, ApplicationContextAware {

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    private static ApplicationContext CONTEXT;

    public BaseModel() {
        request = ServletActionContext.getRequest();
    }

    public String errores(String message) throws Exception {
        List messages = new LinkedList();
        messages.add(message);
        try {
            request.setAttribute("messages", messages);
        } catch (Exception ex) {
            Logger.getLogger(BaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < messages.size(); i++) {
            addActionError(messages.get(i).toString());
        }
        return ERROR;
    }

    /*
* Método que obtiene la variable de sesión SessionVar donde es guardada la información actual del usuario
* que esta logeado en el sistema.
*
* @return Regresa un objeto SessionVar con los datos de la sesión.
     */
    public SessionVar obtieneSessionVar() {
        SessionVar sessionVar = (SessionVar) WebContextFactory.get().getSession().getAttribute("SESSION_PORTAL");
        return sessionVar;
    }

    /*
* Método que obtiene la variable de sesión SessionVar donde es guardada la
* información actual del usuario que esta logeado en el sistema.
*
* @return Regresa un objeto SessionVar con los datos de la sesión.
     */
    public SessionVar obtieneSessionVarActionContext() {
        return (SessionVar) ActionContext.getContext().getSession().get("SESSION_PORTAL");
    }

    /*
* Método que obtiene las configuraciones desde la variable de sesión SessionVar donde es guardada la
* información actual del usuario que esta logeado en el sistema.
*
* @return regresa un objeto con las configuraciones
     */
    public Map<String, String> obtieneConfiguraciones() {
        SessionVar sessionVar = obtieneSessionVar();
        return sessionVar.getConfiguraciones();
    }

    /*
* Método que elimina un archivo subido por el usuario
*
* @param filename es el nombre del archivo
     */
    public void removeUploadedFile(String filename) {
        Map<String, String> configuraciones = obtieneConfiguraciones();
        String path = configuraciones.get("ruta_imagenes");
        StringBuilder sb = new StringBuilder();
        if (path.endsWith("/")) {
            sb.append(path).append(filename);
        } else {
            sb.append(path).append("/").append(filename);
        }
        try {
            File file = new File(sb.toString());
            if (file.delete()) {
                System.out.println(file.getName() + " is deleted!");
            } else {
                System.out.println("Delete operation is failed.");
            }
        } catch (Exception e) {
            Logger.getLogger(BaseModel.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
    }

    /*
* Método que encuentra si existe un archivo subido por el usuario con un
* nombre dado.
*
* @param filename es el nombre del archivo
     */
    public int checkIfUploadedFileExists(String filename) {
        int resultado = 0;
        Map<String, String> configuraciones = obtieneConfiguraciones();
        String path = configuraciones.get("ruta_imagenes");
        StringBuilder sb = new StringBuilder();
        if (path.endsWith("/")) {
            sb.append(path).append(filename);
        } else {
            sb.append(path).append("/").append(filename);
        }
        File file = new File(sb.toString());
        if (file.exists()) {
            resultado = 1;
        }
        return resultado;
    }

    public SessionVar obtieneSessionVarAction() {
        return (SessionVar) ServletActionContext.getRequest().getSession().getAttribute("SESSION_PORTAL");
    }

    /*
* Método que obtiene la url base, necesaria para obtener archivos de
* imagenes o incluso css para la generacion de reportes.
*
* @param request es un objeto request
* @return regresa la url base como un string
     */
    public String getBaseUrl() {
        HttpServletRequest req = ServletActionContext.getRequest();
        if ((req.getServerPort() == 80) || (req.getServerPort() == 443)) {
            return req.getScheme() + "://" + req.getServerName() + req.getContextPath();
        } else {
            return req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath();
        }
    }

    /*
* Método que obtiene las configuraciones desde un action de struts.
*
* @return regresa un objeto con las configuraciones del sistema
     */
    public Map<String, String> obtieneConfiguracionesDesdeStruts() {
        SessionVar sessionVar = obtieneSessionVarAction();
        Map<String, String> configuraciones = sessionVar.getConfiguraciones();
        return configuraciones;
    }

    public List getReportData(String reportKey) {
        SessionVar sessionVar = obtieneSessionVarAction();
        List list = sessionVar.getReportData().get(reportKey);
        sessionVar.getReportData().remove(reportKey);
        return list;
    }

    /*
* Método para obtener todos los registros de una determinada tabla en la
* base de datos. La información obtenida será reformateada para generar un
* reporte con la información del grid.
*
* @param fullyQualifiedClassName es el nombre completo de la clase que contiene el metodo getReportData
* @param sortBy es el nombre del campo por el cual serán ordenados los resultados
* @param sortType es el tipo de ordenado: ascendente o descendente
* @return list regresa la información para construir el reporte
     */
    public List getReportData(String fullyQualifiedClassName, String className, String sortBy, String sortType) {
        try {
            Class<?> clazz = Class.forName(fullyQualifiedClassName);
            // Id del bean registrado en applicationContext.xml correspondiente a la clase que tiene el metodo getReportData
            String beanId = className.substring(0, 1).toLowerCase() + className.substring(1, className.length()) + "Bss";
            // Con el id del bean obtener el objeto desde el contenedor de spring
            Object obj = (Object) CONTEXT.getBean(beanId);
            // Obtener la referencia al metodo
            Method metodo = clazz.getMethod("getReportData", String.class, String.class);
            // Invocar el metodo, el metodo regresa una lista de objetos
            return (List) metodo.invoke(obj, sortBy, sortType);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(BaseModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(BaseModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(BaseModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BaseModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(BaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getLink(String pagina, String catalogo) {
        String catalogoUpper = catalogo.substring(0, 1).toUpperCase() + catalogo.substring(1, catalogo.length());
        pagina = pagina.substring(0, 1).toLowerCase() + pagina.substring(1, pagina.length());
        for (Pagina page : obtieneSessionVarAction().getPagina()) {
            if (page.getUrl() != null) {
                if (page.getUrl().contains("/admon/admon/" + catalogo + ".action")) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("<span title='Editar registros en una ventana externa' class='ui-icon ui-icon-newwin icon-fix new-window'>");
                    sb.append("<span class='url'>/admon/admon/");
                    sb.append(catalogo);
                    sb.append(".action</span><span class='callback'>");
                    sb.append(pagina);
                    sb.append("JS.get");
                    sb.append(catalogoUpper);
                    sb.append("();");
                    sb.append("</span>");
                    sb.append("</span>");
                    return sb.toString();
                }
            }
        }
        return "";
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    public HttpServletRequest getServletRequest() {
        return request;
    }

    public HttpServletResponse getServletResponse() {
        return response;
    }

    /*
* This method is called from within the ApplicationContext once it is done
* starting up, it will stick a reference to itself into this bean.
*
* @param context a reference to the ApplicationContext.
     */
    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        CONTEXT = context;
    }

    /*
* This is about the same as context.getBean("beanName"), except it has its
* own static handle to the Spring context, so calling this method
* statically will give access to the beans by name in the Spring
* application context. As in the context.getBean("beanName") call, the
* caller must cast to the appropriate target class. If the bean does not
* exist, then a Runtime error will be thrown.
*
* @param beanName the name of the bean to get.
* @return an Object reference to the named bean.
     */
    public static Object getBean(String beanName) {
        return CONTEXT.getBean(beanName);
    }

    /**
     * Método que genera el reporte en formato HTML
     *
     * @param filename nomre del archivo
     * @param content contenido del archivo
     * @return
     */
    public boolean generaReporteHTML(String filename, String content) {
        try {
            ServletOutputStream outputStream = getServletResponse().getOutputStream();
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(content);
            renderer.layout();
            getServletResponse().setContentType("application/octet-stream");
            getServletResponse().setHeader("Content-Disposition", "attachment;filename=\"" + filename + ".pdf\"");
            renderer.createPDF(outputStream);
            outputStream.flush();
            outputStream.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error al generar el reporte." + e.getMessage());
            return false;
        }
    }

    /**
     * Método que genera el reporte en formato XLS
     *
     */
    public boolean generaReporteExcel(String filename) {
        try {
            ServletOutputStream outputStream = getServletResponse().getOutputStream();
            getServletResponse().setContentType("application/vnd.ms-excel");
            getServletResponse().setHeader("Content-Disposition", "attachment;filename=\"" + filename + ".xls\"");
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("reporte");
            // administracionCcmBss.getReporteExcel(sheet);
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error al generar el reporte." + e.getMessage());
            return false;
        }
    }

}
