package com.admon.model.reportes;

import com.admon.bss.reportes.EstadoCuentaBss;
import com.admon.entity.admon.Grid;
import com.admon.entity.admon.Organizacion;
import com.admon.entity.reportes.Evento;
import com.admon.model.BaseModel;
import com.admon.pkg.bss.ReportesPKGBss;
import com.admon.pkg.entity.EstadoCuentaDetalleRS;
import com.admon.pkg.entity.EstadoCuentaRS;
import java.util.ArrayList;
import java.util.List;

public class EstadoCuentaDWR extends BaseModel {

    private EstadoCuentaBss estadoCuentaBss;
    private ReportesPKGBss reportesPKGBss;

    public EstadoCuentaDWR() {
    }

    public Grid findByCriteria(int p, int maxResult, String order, String ordenTipo, EstadoCuentaRS estadoCuentaRS) {
        Grid grid = new Grid();
        List<EstadoCuentaRS> listEstadoCuentaRS = new ArrayList<>();
        listEstadoCuentaRS = reportesPKGBss.estadoCuentaPRC(
                estadoCuentaRS.getFechaPagoInicio(), estadoCuentaRS.getFechaPagoFin(),
                estadoCuentaRS.getOrganizacionId(), estadoCuentaRS.getEventoId());
        int resultadosTotales = listEstadoCuentaRS.size();
        grid.setGrid(listEstadoCuentaRS);
        grid.setTotal(resultadosTotales);
        grid.setPaginas(1);
        return grid;
    }

    public String reporteEstadoCuentaXLS(EstadoCuentaRS estadoCuentaRS) {
        try {
            StringBuilder sbKey = new StringBuilder();
            List<EstadoCuentaRS> listEstadoCuentaRS = new ArrayList<>();
            listEstadoCuentaRS = reportesPKGBss.estadoCuentaPRC(
                    estadoCuentaRS.getFechaPagoInicio(), estadoCuentaRS.getFechaPagoFin(),
                    estadoCuentaRS.getOrganizacionId(), estadoCuentaRS.getEventoId());
            String keyECRS = estadoCuentaBss.addDataToSession(listEstadoCuentaRS);
            sbKey.append(keyECRS);
            List<EstadoCuentaDetalleRS> listEstadoCuentaDetalleRS = new ArrayList<>();
            listEstadoCuentaDetalleRS = reportesPKGBss.estadoCuentaDetallePRC(
                    estadoCuentaRS.getFechaPagoInicio(), estadoCuentaRS.getFechaPagoFin(),
                    estadoCuentaRS.getOrganizacionId(), estadoCuentaRS.getEventoId());
            String keyECDetalleRS = estadoCuentaBss.addDataToSession(listEstadoCuentaDetalleRS);
            sbKey.append("_");
            sbKey.append(keyECDetalleRS);
            return sbKey.toString();
        } catch (Exception e) {
            System.out.println("Error al generar el reporte: " + e.toString());
            return "error::" + e.toString();
        }
    }

    public List<Organizacion> getOrganizacion() {
        return estadoCuentaBss.getOrganizacion();
    }

    public List<Evento> filtrarEvento(int organizacionId) {
        List<Evento> listEvento = null;
        listEvento = estadoCuentaBss.filtrarEvento(organizacionId);
        return listEvento;
    }

    public void setEstadoCuentaBss(EstadoCuentaBss estadoCuentaBss) {
        this.estadoCuentaBss = estadoCuentaBss;
    }

    public void setReportesPKGBss(ReportesPKGBss reportesPKGBss) {
        this.reportesPKGBss = reportesPKGBss;
    }
}
