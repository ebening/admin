package com.admon.pkg.dao;

import com.admon.entity.admon.SPParametro;
import java.util.List;

public interface GenericPKGDAO {

    public List<Object> callStoredProcedure(
            String spName,
            List<SPParametro> listaParametros);
}
