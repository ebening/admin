package com.admon.pkg.bss;

import com.admon.pkg.entity.EstadoCuentaDetalleRS;
import com.admon.pkg.entity.EstadoCuentaRS;
import java.sql.Date;
import java.util.List;

public interface ReportesPKGBss {

    public List<EstadoCuentaRS> estadoCuentaPRC(Date fechaInicial, Date fechaFinal, Integer organizacionId, Integer eventoId);
    public List<EstadoCuentaDetalleRS> estadoCuentaDetallePRC(Date fechaInicial, Date fechaFinal, Integer organizacionId, Integer eventoId);

}
