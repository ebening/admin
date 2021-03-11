package com.admon.bss.admon;

import com.admon.bss.BaseBss;
import com.admon.dao.admon.LenguajeDAO;
import com.admon.entity.admon.ConfiguracionParametro;
import com.admon.entity.admon.Lenguaje;
import com.admon.entity.admon.Organizacion;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

public class LenguajeBssImpl extends BaseBss implements LenguajeBss {

    private LenguajeDAO lenguajeDAO;
    private ConfiguracionParametroBss configuracionParametroBss;
    private UsuarioBss usuarioBss;

    public List<Lenguaje> resolveDescription(List<Lenguaje> list) {
        return list;
    }

    @Override
    public List<Lenguaje> findByCriteria(DetachedCriteria criteria) {
        return lenguajeDAO.findByCriteria(criteria);
    }

    @Override
    public List<Lenguaje> findAll() {
        return findByCriteria(createDetachedCriteria().addOrder(Order.asc("nombre")));
    }

    /*
     * Método que obtiene todos los registros de la tabla de <b>Lenguaje</b>.
     * Este método es generalmente utilizado por los action para obtener las opciones
     * de los select que se llenan mediante struts.
     *
     * @return Regresa todos los registros de la tabla <b>Lenguaje</b>.
     */
    @Override
    public List<Lenguaje> findActive() {
        return findByCriteria(createDetachedCriteria().add(
                Expression.eq("estatusId", ACTIVO)).addOrder(Order.asc("nombre")));
    }

    @Override
    public DetachedCriteria createDetachedCriteria() {
        return lenguajeDAO.createDetachedCriteria();
    }

    @Override
    public List<ConfiguracionParametro> getParametros(String catalogKey) {
        return addDummy(configuracionParametroBss.getParametros(catalogKey), new ConfiguracionParametro(), ConfiguracionParametro.class);
    }

    public void setLenguajeDAO(LenguajeDAO lenguajeDAO) {
        this.lenguajeDAO = lenguajeDAO;
    }

    public void setConfiguracionParametroBss(ConfiguracionParametroBss configuracionParametroBss) {
        this.configuracionParametroBss = configuracionParametroBss;
    }

    public void setUsuarioBss(UsuarioBss usuarioBss) {
        this.usuarioBss = usuarioBss;
    }
}
