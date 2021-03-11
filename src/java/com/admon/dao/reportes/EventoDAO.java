package com.admon.dao.reportes;

import com.admon.dao.GenericDAOImpl;
import com.admon.entity.reportes.Evento;

public class EventoDAO extends GenericDAOImpl<Evento, Integer> {

    @Override
    protected Class<Evento> getEntityClass() {
        return Evento.class;
    }
}
