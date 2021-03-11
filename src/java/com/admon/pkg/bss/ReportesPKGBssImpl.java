package com.admon.pkg.bss;

import com.admon.pkg.dao.ReportesPKGDAO;
import com.admon.entity.admon.SPParametro;
import com.admon.pkg.entity.EstadoCuentaDetalleRS;
import com.admon.pkg.entity.EstadoCuentaRS;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ReportesPKGBssImpl implements ReportesPKGBss {

    // Spring IoC
    private ReportesPKGDAO reportesPKGDAO;

    public ReportesPKGBssImpl() {
    }

    // Spring setter
    public void setReportesPKGDAO(ReportesPKGDAO reportesPKGDAO) {
        this.reportesPKGDAO = reportesPKGDAO;
    }

    /**
     * Obtiene Estado de Cuenta por un rango de fechas
     *
     * @param fechaInicio fecha de inicio
     * @param fechaFin fecha de fin
     * @param organizacionId id de la organización
     * @param eventoId id del evento
     * @return estado de cuenta
     */
    @Override
    public List<EstadoCuentaRS> estadoCuentaPRC(Date fechaInicio, Date fechaFin, Integer organizacionId, Integer eventoId) {
        String spName = "estadoCuentaPRC";
        List list = null;
        List<SPParametro> spList = new ArrayList();
        try {
            spList.add(new SPParametro("fechaInicio", fechaInicio));
            spList.add(new SPParametro("fechaFin", fechaFin));
            spList.add(new SPParametro("organizacionId", organizacionId));
            spList.add(new SPParametro("eventoId", eventoId));
            list = reportesPKGDAO.callStoredProcedure(spName, spList);
        } catch (Exception e) {
            System.out.println("error> " + e);
        }
        return list;
    }

    /**
     * Obtiene Estado de Cuenta Detalle por un rango de fechas
     *
     * @param fechaInicio fecha de inicio
     * @param fechaFin fecha de fin
     * @param organizacionId id de la organización
     * @param eventoId id del evento
     * @return estado de cuenta
     */
    @Override
    public List<EstadoCuentaDetalleRS> estadoCuentaDetallePRC(Date fechaInicio, Date fechaFin, Integer organizacionId, Integer eventoId) {
        String spName = "estadoCuentaDetallePRC";
        List list = null;
        List<SPParametro> spList = new ArrayList();
        try {
            spList.add(new SPParametro("fechaInicio", fechaInicio));
            spList.add(new SPParametro("fechaFin", fechaFin));
            spList.add(new SPParametro("organizacionId", organizacionId));
            spList.add(new SPParametro("eventoId", eventoId));
            list = reportesPKGDAO.callStoredProcedure(spName, spList);
        } catch (Exception e) {
            System.out.println("error> " + e);
        }
        return list;
    }
}
