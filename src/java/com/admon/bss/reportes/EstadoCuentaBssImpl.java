package com.admon.bss.reportes;

import com.admon.bss.BaseBss;
import com.admon.bss.admon.ConfiguracionParametroBss;
import com.admon.bss.admon.OrganizacionBss;
import com.admon.bss.admon.UsuarioBss;
import com.admon.dao.reportes.EstadoCuentaDAO;
import com.admon.entity.admon.ConfiguracionParametro;
import com.admon.entity.admon.Organizacion;
import com.admon.entity.admon.Pagina;
import com.admon.entity.reportes.Evento;
import com.admon.model.reportes.EstadoCuentaAction;
import java.util.List;

public class EstadoCuentaBssImpl extends BaseBss implements EstadoCuentaBss {

    private EstadoCuentaDAO estadoCuentaDAO;
    private ConfiguracionParametroBss configuracionParametroBss;
    private UsuarioBss usuarioBss;
    private OrganizacionBss organizacionBss;
    private EventoBss eventoBss;

    public EstadoCuentaBssImpl() {
    }

    @Override
    public String addDataToSession(List list) {
        return addReportDataToSession(list);
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
    public String hasGrid() {
        return hasGrid(EstadoCuentaAction.class).toString();
    }

    @Override
    public String isIndividual() {
        return isIndividual(EstadoCuentaAction.class).toString();
    }

    @Override
    public String getNombreActionMenu() {
        Pagina pagina = findPageByActionClass(EstadoCuentaAction.class);
        if (pagina == null) {
            return "";
        } else {
            return pagina.getNombre();
        }
    }

    @Override
    public List<Evento> filtrarEvento(int organizacionId) {
        return eventoBss.filtrarEvento(organizacionId);
    }

    public void setEstadoCuentaDAO(EstadoCuentaDAO estadoCuentaDAO) {
        this.estadoCuentaDAO = estadoCuentaDAO;
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

    public void setEventoBss(EventoBss eventoBss) {
        this.eventoBss = eventoBss;
    }
}
