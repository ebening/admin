/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admon.bss.reportes;

import com.admon.bss.BaseBss;
import com.admon.bss.admon.ConfiguracionParametroBss;
import com.admon.bss.admon.UsuarioBss;
import com.admon.dao.reportes.EstadoCuentaDetalleDAO;
import com.admon.entity.admon.ConfiguracionParametro;
import java.util.List;

/**
 *
 * @author acer
 */
public class EstadoCuentaDetalleBssImpl extends BaseBss implements EstadoCuentaDetalleBss {

    private EstadoCuentaDetalleDAO estadoCuentaDetalleDAO;
    private ConfiguracionParametroBss configuracionParametroBss;
    private UsuarioBss usuarioBss;

    public EstadoCuentaDetalleBssImpl() {
    }

    @Override
    public List<ConfiguracionParametro> getParametros(String catalogKey) {
        return addDummy(configuracionParametroBss.getParametros(catalogKey), new ConfiguracionParametro(), ConfiguracionParametro.class);
    }
    
    public void setEstadoCuentaDetalleDAO(EstadoCuentaDetalleDAO estadoCuentaDetalleDAO) {
        this.estadoCuentaDetalleDAO = estadoCuentaDetalleDAO;
    }

    public void setConfiguracionParametroBss(ConfiguracionParametroBss configuracionParametroBss) {
        this.configuracionParametroBss = configuracionParametroBss;
    }

    public void setUsuarioBss(UsuarioBss usuarioBss) {
        this.usuarioBss = usuarioBss;
    }
}
