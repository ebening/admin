package com.admon.dao.reportes;

import com.admon.dao.GenericDAOImpl;
import com.admon.pkg.entity.EstadoCuentaDetalleRS;

public class EstadoCuentaDetalleDAO extends GenericDAOImpl<EstadoCuentaDetalleRS, Integer> {

    @Override
    protected Class<EstadoCuentaDetalleRS> getEntityClass() {
        return EstadoCuentaDetalleRS.class;
    }
}
