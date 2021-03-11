package com.admon.model.reportes;

import com.admon.model.BaseModel;
import com.admon.pkg.entity.EstadoCuentaDetalleRS;
import com.admon.pkg.entity.EstadoCuentaRS;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.NONE;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class EstadoCuentaExportarAction extends BaseModel {

    public HttpServletRequest servletRequest;
    public HttpServletResponse servletResponse;
    public String filename;
    public String reportName;
    public String format;
    public String reportKey;

    @Override
    public String execute() {
        try {
////            Integer repositorioId = Integer.parseInt(repositorioArchivos.getString("repositorioArchivoJasper"));
////            String pathBaseJasper = (String) catalogosBss.getPathArchivoJasper(organizacionId, repositorioId);
            setFormat(getServletRequest().getParameter("format"));
            setFilename(getServletRequest().getParameter("reportName"));
            setReportKey(getServletRequest().getParameter("reportKey"));
            List<String> listKeysECRS = new ArrayList<>();
            listKeysECRS = Arrays.asList(getReportKey().split("_"));
            // Obtener los datos para generar el reporte
            List<EstadoCuentaRS> listEstadoCuentaRS = getReportData(listKeysECRS.get(0));
            List<EstadoCuentaDetalleRS> listEstadoCuentaDetalleRS = getReportData(listKeysECRS.get(1));

            String pathBaseJasper = ("/app/taasgo/repositorio/administracion/reportes/"); //para PRD
//            String pathBaseJasper = ("C:\\Users\\acer\\Downloads\\Java\\iReport\\"); //para LOCAL
            String jasperName = ("reporteEstadoCuenta_Main.jasper");
            Map params = new HashMap<String, Object>();
            EstadoCuentaRS temporalECRS = new EstadoCuentaRS();
            if (!listEstadoCuentaRS.isEmpty()) {
                temporalECRS = listEstadoCuentaRS.get(0);
                params.put("SUBREPORT_DIR", pathBaseJasper);
                params.put("NombreRazonSocial", temporalECRS.getNombreOrganizacion());
                params.put("NombreComercial", temporalECRS.getNombreComercial());
                params.put("RFC", temporalECRS.getRFC());
                params.put("Domicilio", temporalECRS.getDomicilio());
                params.put("FechaLimitePago", new Date());
                params.put("NombreEvento", temporalECRS.getNombreEvento());
                params.put("NoBoletos", temporalECRS.getNoBoletos());
                params.put("Total", temporalECRS.getTotal());
                params.put("Monto", temporalECRS.getMonto());
                params.put("Descuento", temporalECRS.getDescuento());
                params.put("CargoXServicio", temporalECRS.getCargoXServicio());
                params.put("ComisionTasgo", temporalECRS.getComisionTaasgo());
                params.put("NoCortesias", temporalECRS.getNoCortesias());
                params.put("NoCancelaciones", temporalECRS.getNoCancelaciones());
                params.put("NoBoletoDuro", temporalECRS.getNoBoletoDuro());
                if (temporalECRS.getOrganizacionId() == 2
                        || temporalECRS.getOrganizacionId() == 6) {
                    params.put("PorcentajeXCortesia", 0.15);
                    params.put("PorcentajeXCancelacion", 0.15);
                    params.put("PorcentajeXBoletoDuro", 0.15);
                } else if (temporalECRS.getOrganizacionId() == 4) {
                    params.put("PorcentajeXCortesia", 0.15);
                    params.put("PorcentajeXCancelacion", 0.15);
                    params.put("PorcentajeXBoletoDuro", 0.15);
                } else {
                    params.put("PorcentajeXCortesia", 0.15);
                    params.put("PorcentajeXCancelacion", 0.15);
                    params.put("PorcentajeXBoletoDuro", 0.15);
                }
                params.put("IVA", 0.15);
            }
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(new File(pathBaseJasper + jasperName));
            JasperPrint jasperPrint;
            if (listEstadoCuentaRS.isEmpty()) {
                jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
            } else {
                jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JRBeanCollectionDataSource(listEstadoCuentaDetalleRS));
            }
            // Reporte como PDF
            if (getFormat().equalsIgnoreCase("PDF")) {
                ServletOutputStream outputStream = getServletResponse().getOutputStream();
                getServletResponse().setContentType("application/octet-stream");
                getServletResponse().setHeader("Content-Disposition", "attachment;filename=\"" + getFilename().replaceAll(" ", "") + ".pdf\"");
                JRPdfExporter exporter = new JRPdfExporter();
                exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, outputStream);
                exporter.exportReport();
                outputStream.flush();
                outputStream.close();
                return NONE;
            } else {
                // Reporte como Excel
                ServletOutputStream outputStream = getServletResponse().getOutputStream();
                getServletResponse().setContentType("application/vnd.ms-excel");
                getServletResponse().setHeader("Content-Disposition", "attachment;filename=\"" + getFilename().replaceAll(" ", "") + ".xls\"");
                JRXlsxExporter exporter = new JRXlsxExporter();
                exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, outputStream);
                exporter.exportReport();
                outputStream.flush();
                outputStream.close();
                return NONE;
            }
        } catch (JRException jre) {
            System.out.println("Error al generar el reporte: " + jre.toString());
            return ERROR;
        } catch (Exception e) {
            System.out.println("Error al generar el reporte: " + e.toString());
            return ERROR;
        }
    }

    @Override
    public HttpServletRequest getServletRequest() {
        return servletRequest;
    }

    @Override
    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    @Override
    public HttpServletResponse getServletResponse() {
        return servletResponse;
    }

    @Override
    public void setServletResponse(HttpServletResponse servletResponse) {
        this.servletResponse = servletResponse;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getReportKey() {
        return reportKey;
    }

    public void setReportKey(String reportKey) {
        this.reportKey = reportKey;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

}
