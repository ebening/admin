package com.admon.model.util;

import com.admon.model.BaseModel;
import com.opensymphony.xwork2.ActionContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

public class DownloadAction extends BaseModel {

    private HttpServletRequest servletRequest;
    private InputStream fileInputStream;
    public String file;

    @Override
    public String execute() {
        // Obtener la ruta raiz donde se esta el archivo que se va a descargar
        SessionVar sessionVar = (SessionVar) ActionContext.getContext().getSession().get("SESSION_PORTAL");
        Map<String, String> configuraciones = sessionVar.getConfiguraciones();
        String path = configuraciones.get("ruta_imagenes");

        // Obtener el nombre del archivo
        setFile(servletRequest.getParameter("file_path"));
        // Descarga
        try {
            StringBuilder sb = new StringBuilder();
            if (path.endsWith("/")) {
                sb.append(path).append(getFile());
            } else {
                sb.append(path).append("/").append(getFile());
            }
            File descarga = new File(sb.toString());
            if (descarga.exists()) {
                fileInputStream = new FileInputStream(descarga);
            } else {
                return ERROR;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DownloadAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return SUCCESS;
    }

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(InputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    @Override
    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }
}
