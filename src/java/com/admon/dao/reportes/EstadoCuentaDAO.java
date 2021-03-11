package com.admon.dao.reportes;

import com.admon.dao.GenericDAOImpl;
import com.admon.entity.reportes.EstadoCuenta;

public class EstadoCuentaDAO extends GenericDAOImpl<EstadoCuenta, Integer> {

    @Override
    protected Class<EstadoCuenta> getEntityClass() {
        return EstadoCuenta.class;
    }
}
