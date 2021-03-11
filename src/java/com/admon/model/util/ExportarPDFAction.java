package com.admon.model.util;

import com.admon.model.BaseModel;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts2.ServletActionContext;
import org.xhtmlrenderer.pdf.ITextRenderer;

public class ExportarPDFAction extends BaseModel {

    public HttpServletRequest servletRequest;
    public HttpServletResponse servletResponse;
    public String filename;
    public String headers;
    public String reportName;
    public String format;
    public String reportKey;

    @Override
    public String execute() {
        try {

            // Locación de la clase entity que tiene los getters para obtener el valor de cada propiedad
            StringBuilder fullyQualifiedEntityName = new StringBuilder();
            fullyQualifiedEntityName.append("com.admon.entity.admon.");
            fullyQualifiedEntityName.append(getServletRequest().getParameter("entity"));

            // Setear los parametros obtenidos desde la url
            setHeaders(getServletRequest().getParameter("headers"));
            setReportName(getServletRequest().getParameter("entity"));
            setFormat(getServletRequest().getParameter("format"));
            setFilename(getServletRequest().getParameter("reportName"));
            setReportKey(getServletRequest().getParameter("reportKey"));

            // Obtener los datos para generar el reporte
            List list = getReportData(getReportKey());

            // Reporte como PDF
            if (getFormat().equalsIgnoreCase("pdf")) {
                ServletOutputStream outputStream = getServletResponse().getOutputStream();
                ITextRenderer renderer = new ITextRenderer();
                renderer.setDocumentFromString(getPdfRawContent(list, fullyQualifiedEntityName.toString()));
                renderer.layout();
                getServletResponse().setContentType("application/octet-stream");
                getServletResponse().setHeader("Content-Disposition", "attachment;filename=\"" + getFilename().replaceAll(" ", "") + ".pdf\"");
                renderer.createPDF(outputStream);
                outputStream.flush();
                outputStream.close();
                return NONE;
            } else {
                // Reporte como Excel
                ServletOutputStream outputStream = getServletResponse().getOutputStream();
                getServletResponse().setContentType("application/vnd.ms-excel");
                getServletResponse().setHeader("Content-Disposition", "attachment;filename=\"" + getFilename().replaceAll(" ", "") + ".xls\"");
                HSSFWorkbook workbook = new HSSFWorkbook();
                HSSFSheet sheet = workbook.createSheet(getFilename());
                getExcelRawContent(list, fullyQualifiedEntityName.toString(), sheet);
                workbook.write(outputStream);
                outputStream.flush();
                outputStream.close();
                return NONE;
            }
        } catch (Exception e) {
            return ERROR;
        }
    }

    public String readFile(String file, String csName)
            throws IOException {
        Charset cs = Charset.forName(csName);
        return readFile(file, cs);
    }

    public String readFile(String file, Charset cs)
            throws IOException {
// No real need to close the BufferedReader/InputStreamReader
// as they're only wrapping the stream
        FileInputStream stream = new FileInputStream(file);
        try {
            Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();
        } finally {
// Potential issue here: if this throws an IOException,
// it will mask any others. Normally I'd use a utility
// method which would log exceptions and swallow them
            stream.close();
        }
    }

    /*
* Método para obtener todos los registros de una determinada tabla en la
* base de datos. La información obtenida será reformateada para generar un
* excel con la información de la tabla.
*
* @param list es una lista de objetos
* @param fullyQualifiedEntityName es el nombre completo de la clase que
* contiene los metodos getters para obtener cada propiedad del objeto
* (incluido el nombre completo del paquete en el que se encuentra)
* @return regresa la información en formato html
     */
    public void getExcelRawContent(List list, String fullyQualifiedEntityName, HSSFSheet sheet) {
        try {
            List<String> colHeaders = Arrays.asList(getHeaders().split("-"));

            // Estilo Encabezado
            CellStyle cellHeaderStyle = sheet.getWorkbook().createCellStyle();
            addBordersToCell(cellHeaderStyle, CellStyle.BORDER_THIN, IndexedColors.GREY_80_PERCENT.getIndex());
            cellHeaderStyle.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
            cellHeaderStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
            cellHeaderStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            Font boldFont = sheet.getWorkbook().createFont();
            boldFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
            cellHeaderStyle.setFont(boldFont);

            // Estilo titulo 1
            CellStyle cellTitleStrongStyle = sheet.getWorkbook().createCellStyle();
            cellTitleStrongStyle.setAlignment(CellStyle.ALIGN_CENTER);
            cellTitleStrongStyle.setFont(boldFont);

            // Estilo titulo 2
            CellStyle cellTitleStyle = sheet.getWorkbook().createCellStyle();
            cellTitleStyle.setAlignment(CellStyle.ALIGN_CENTER);

            // Estilo zebra 1
            CellStyle cellOddNumberStyle = sheet.getWorkbook().createCellStyle();
            addBordersToCell(cellOddNumberStyle, CellStyle.BORDER_THIN, IndexedColors.GREY_50_PERCENT.getIndex());

            // Estilo zebra 2
            CellStyle cellEvenNumberStyle = sheet.getWorkbook().createCellStyle();
            addBordersToCell(cellEvenNumberStyle, CellStyle.BORDER_THIN, IndexedColors.GREY_50_PERCENT.getIndex());
            cellEvenNumberStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            cellEvenNumberStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

            // Información del reporte
            int rowIndex = 0;
            Map<String, String> configuraciones = obtieneConfiguracionesDesdeStruts();
            sheet.createRow(rowIndex).createCell(0).setCellValue(configuraciones.get("Cliente nombre").toUpperCase());
            sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, colHeaders.size() - 1));
            sheet.getRow(rowIndex).getCell(0).setCellStyle(cellTitleStrongStyle);
            rowIndex++;
            sheet.createRow(rowIndex).createCell(0).setCellValue(configuraciones.get("Aplicacion nombre completo").toUpperCase());
            sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, colHeaders.size() - 1));
            sheet.getRow(rowIndex).getCell(0).setCellStyle(cellTitleStyle);
            rowIndex++;
            sheet.createRow(rowIndex).createCell(0).setCellValue("REPORTE " + getReportName().toUpperCase());
            sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, colHeaders.size() - 1));
            sheet.getRow(rowIndex).getCell(0).setCellStyle(cellTitleStyle);
            rowIndex++;

            // Ecabezados de la tabla
            Row row = sheet.createRow(rowIndex);
            row.setHeightInPoints((short) 21);
            for (int header = 0; header < colHeaders.size(); header++) {
                Cell cell = row.createCell(header);
                cell.setCellStyle(cellHeaderStyle);
                try {
                    cell.setCellValue((String) colHeaders.get(header).split("@")[0]);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    cell.setCellValue("-N/A-");
                }
            }
            rowIndex++;

            // Contenido
            Class<?> entityClazz = Class.forName(fullyQualifiedEntityName);
            String memberName;
            for (int r = 0; r < list.size(); r++) {
                Row bodyRow = sheet.createRow(r + rowIndex);
// Obtener el objeto (fila)
                Object entityObject = list.get(r);
// Efecto zebra para la tabla
                CellStyle zebra = (r & 1) == 0 ? cellOddNumberStyle : cellEvenNumberStyle;
// Obtener los valors (celdas)
                for (int col = 0; col < colHeaders.size(); col++) {
                    Cell cell = bodyRow.createCell(col);
                    cell.setCellStyle(zebra);
                    try {
                        memberName = colHeaders.get(col).split("@")[1];
                        if (memberName.endsWith("Id")) {
                            memberName = memberName.substring(0, memberName.length() - 2);
                        }
                        Object regresaResultado = entityClazz.getMethod("get"
                                + memberName.substring(0, 1).toUpperCase()
                                + memberName.substring(1, memberName.length())).invoke(entityObject);
                        if (regresaResultado == null) {
                            cell.setCellValue("");
                        } else {
                            if (memberName.toLowerCase().contains("fecha")
                                    || memberName.toLowerCase().contains("date")) {
                                cell.setCellValue(formatDate(regresaResultado.toString()));
                            } else {
                                cell.setCellValue(regresaResultado.toString());
                            }
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        cell.setCellValue("-N/A-");
                    }
                }
            }

            // Ajustes finales a la hoja
            for (int header = 0; header < colHeaders.size(); header++) {
                sheet.autoSizeColumn(header);
            }
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(ExportarPDFAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(ExportarPDFAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ExportarPDFAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
* Metodo que setea los bordes a una celda
*
* @param cellStyle es la referencia de la celda
* @param borderType es el tipo de borde
* @param borderColor es el color de borde
* @return regresa una referencia a la celda a la cual se le aplico el
* formato de bordes
     */
    public CellStyle addBordersToCell(CellStyle cellStyle, short borderType, short borderColor) {
        cellStyle.setBorderBottom(borderType);
        cellStyle.setBorderLeft(borderType);
        cellStyle.setBorderRight(borderType);
        cellStyle.setBorderTop(borderType);
        cellStyle.setBottomBorderColor(borderColor);
        cellStyle.setLeftBorderColor(borderColor);
        cellStyle.setRightBorderColor(borderColor);
        cellStyle.setTopBorderColor(borderColor);
        return cellStyle;
    }

    /*
* Método para obtener todos los registros de una determinada tabla en la
* base de datos. La información obtenida será reformateada para generar un
* PDF con la información de la tabla.
*
* @param list es una lista de objetos
* @param fullyQualifiedEntityName es el nombre completo de la clase que
* contiene los metodos getters para obtener cada propiedad del objeto
* (incluido el nombre completo del paquete en el que se encuentra)
* @return regresa la información en formato html
     */
    public String getPdfRawContent(List list, String fullyQualifiedEntityName) throws IOException {
        StringBuilder content = new StringBuilder();
        try {
            // Formatear la lista que regresa el metodo getReportData para que sea imprimido en PDF
            content.append("<!DOCTYPE html>\n"); // <- Para compatibilidad con IE8
            content.append("<html>\n");
            content.append("<head>\n");
            content.append("<meta name=\"http-equiv\" content=\"Content-type: text/html; charset=ISO-8859-1\" />\n");
            content.append("<style>\n");
            String realPath = readFile(ServletActionContext.getServletContext().getRealPath("/css/estilos/reportes.css"), "UTF-8");
            realPath = realPath.replace("\n", "").replace("\r", "").replace(" ", "");
            content.append(realPath);
            content.append("</style>\n");
            content.append("</head>\n");
            content.append("<body>\n");
            content.append(getPdfRawHeaderContent());
            content.append("<div class='hr'></div>\n");
            content.append(getPdfRawBodyContent(list, fullyQualifiedEntityName));
            content.append("</body>\n");
            content.append("</html>\n");
        } catch (IllegalAccessException ex) {
            Logger.getLogger(BaseModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(BaseModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(BaseModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(BaseModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(BaseModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return content.toString();
    }

    /*
* Método que obtiene el encabezado de los reportes (logo, nombre de
* reporte, etc.)
*
* @param reportName es el nombre del reporte
* @return
     */
    public String getPdfRawHeaderContent() {
        StringBuilder content = new StringBuilder();
        // Presentacion del reporte
        Map<String, String> configuraciones = obtieneConfiguracionesDesdeStruts();
        String clienteNombre = configuraciones.get("Cliente nombre");
        String sistemaNombre = configuraciones.get("Aplicacion nombre completo");
        String rutaLogo = configuraciones.get("ruta_logo");
        content.append("<table class='tabla-encabezado'>\n");
        content.append("<tr>\n");
        content.append("<td class='tabla-encabezado-logo-container'>\n");
        String src = ServletActionContext.getServletContext().getRealPath("/images/logo/logo_reporte.png");
        content.append("<img src='").append(src).append("' />\n");
        content.append("</td>\n");
        content.append("<td>\n");
        content.append("<div class='tabla-encabezado-titulo-1' >").append(parseSymbol(clienteNombre.toUpperCase())).append("</div>");
        content.append("<div class='tabla-encabezado-titulo-2' >").append(parseSymbol(sistemaNombre.toUpperCase())).append("</div>");
        content.append("<div class='tabla-encabezado-titulo-2' >REPORTE ").append(parseSymbol(getReportName().toUpperCase())).append("</div>");
        content.append("</td>\n");
        content.append("<td style='text-align:right;'>\n");
        content.append(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        content.append("</td>\n");
        content.append("</tr>\n");
        content.append("</table>\n");
        return content.toString();
    }

    public String parseSymbol(String texto) {
        texto = texto.replaceAll("&", "&amp;");
        texto = texto.replaceAll("<", "&lt;");
        texto = texto.replaceAll(">", "&gt;");
        texto = texto.replaceAll("<br>", "</br>");
        return texto;
    }

    public String formatDate(String fecha) {
        String newString = "";
        try {
            newString = new SimpleDateFormat("dd-MM-yyyy").format(
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fecha));
        } catch (Exception e) {
            return fecha;
        }
        return newString;
    }


    /*
* Método que obtiene el contenido del cuerpo de los reportes en formato de
* tabla html.
*
* @param list es una lista de objetos
* @param fullyQualifiedEntityName es el nombre completo de la clase que
* contiene los metodos getters para obtener cada propiedad del objeto
* (incluido el nombre completo del paquete en el que se encuentra)
* @return regresa un string con los datos de la lista de objetos en formato
* de tabla html
* @throws NoSuchMethodException
* @throws IllegalAccessException
* @throws IllegalArgumentException
* @throws InvocationTargetException
     */
    public String getPdfRawBodyContent(List list, String fullyQualifiedEntityName)
            throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, ClassNotFoundException {
        StringBuilder content = new StringBuilder();
        // Ecabezados de la tabla
        List<String> colHeaders = Arrays.asList(getHeaders().split("-"));
        content.append("<table class='tabla-contenido border-all'>\n");

        // Encabezados
        content.append("<thead>\n");
        content.append("<tr>");
        for (int header = 0; header < colHeaders.size(); header++) {
            content.append("<th class='tabla-contenido-th border-all' >");
            try {
                content.append(parseSymbol(colHeaders.get(header).split("@")[0]));
            } catch (Exception e) {
                System.out.println(e.getMessage());
                content.append("-N/A-");
            }
            content.append("</th>");
        }
        content.append("</tr>");
        content.append("</thead>\n");

        // Contenido
        Class<?> entityClazz = Class.forName(fullyQualifiedEntityName);
        content.append("<tbody>\n");
        String memberName;
        for (int row = 0; row < list.size(); row++) {
            Object entityObject = list.get(row);
            String zebra = (row & 1) == 0 ? "class='tabla-contenido-tr-1'"
                    : "class='tabla-contenido-tr-2'";
            content.append("<tr ").append(zebra).append(">");
            for (int col = 0; col < colHeaders.size(); col++) {
                content.append("<td class='tabla-contenido-td border-all' >");
                try {
                    memberName = colHeaders.get(col).split("@")[1];
                    if (memberName.endsWith("Id")) {
                        memberName = memberName.substring(0, memberName.length() - 2);
                    }
                    Object regresaResultado = entityClazz.getMethod("get"
                            + memberName.substring(0, 1).toUpperCase()
                            + memberName.substring(1, memberName.length())).invoke(entityObject);

                    if (regresaResultado == null) {
                        content.append("");
                    } else {
                        if (memberName.toLowerCase().contains("fecha")
                                || memberName.toLowerCase().contains("date")) {
                            content.append(formatDate(regresaResultado.toString()));
                        } else {
                            content.append(parseSymbol(regresaResultado.toString()));
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    content.append("-N/A-");
                }
                content.append("</td>");
            }
            content.append("</tr>");
        }
        content.append("</tbody>\n");
        content.append("</table>");
        return content.toString();
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

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
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
