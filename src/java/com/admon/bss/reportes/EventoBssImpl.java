package com.admon.bss.reportes;

import com.admon.bss.BaseBss;
import com.admon.bss.admon.ConfiguracionParametroBss;
import com.admon.bss.admon.OrganizacionBss;
import com.admon.bss.admon.UsuarioBss;
import com.admon.dao.reportes.EventoDAO;
import com.admon.entity.admon.ConfiguracionParametro;
import com.admon.entity.admon.Organizacion;
import com.admon.entity.reportes.Evento;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;

public class EventoBssImpl extends BaseBss implements EventoBss {

    private EventoDAO eventoDAO;
    private ConfiguracionParametroBss configuracionParametroBss;
    private UsuarioBss usuarioBss;
    private OrganizacionBss organizacionBss;

    public List<Evento> resolveDescription(List<Evento> list) {
        return list;
    }

    @Override
    public List<Evento> findByCriteria(DetachedCriteria criteria) {
        return eventoDAO.findByCriteria(criteria);
    }

    @Override
    public DetachedCriteria createDetachedCriteria() {
        return eventoDAO.createDetachedCriteria();
    }

    @Override
    public List<ConfiguracionParametro> getParametros(String catalogKey) {
        return addDummy(configuracionParametroBss.getParametros(catalogKey), new ConfiguracionParametro(), ConfiguracionParametro.class);
    }

    @Override
    public List<Organizacion> getOrganizacion() {
        return addDummy(organizacionBss.findActive(), new Organizacion(), Organizacion.class);
    }

    @Override
    public List<Evento> filtrarEvento(int organizacionId) {
        return addDummy(findByCriteria(createDetachedCriteria().add(Expression.eq("organizacionId", organizacionId))
        ), new Evento(), Evento.class);
    }

    public void setEventoDAO(EventoDAO eventoDAO) {
        this.eventoDAO = eventoDAO;
    }

    public void setConfiguracionParametroBss(ConfiguracionParametroBss configuracionParametroBss) {
        this.configuracionParametroBss = configuracionParametroBss;
    }

    public void setUsuarioBss(UsuarioBss usuarioBss) {
        this.usuarioBss = usuarioBss;
    }

    public void setOrganizacionBss(OrganizacionBss organizacionBss) {
        this.organizacionBss = organizacionBss;
    }
}
