package com.admon.bss.admon;

import com.admon.entity.admon.ConfiguracionParametro;
import com.admon.entity.admon.Lenguaje;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;

public interface LenguajeBss {

    /*
     * Método que realiza un búsqueda en la BD obteniendo todos los registros que coincidan con los parámetros
     * definidos en el objeto <b>criteria</b>.
     *
     * @param criteria Es un objeto que contiene los parámetros de búsqueda.
     * @return Regresa una lista de objetos <b>Evento</b> que coinciden con los parámetros definidos en
     * <b>criteria</b>.
     */
    public List<Lenguaje> findByCriteria(DetachedCriteria criteria);

    /*
     * Método que obtiene todos los registros de la tabla de <b>Lenguaje</b>.
     * @return Regresa todos los registros de la tabla <b>Lenguaje</b>.
     */
    public List<Lenguaje> findAll();

    /*
     * Método que obtiene todos los registros de la tabla de <b>Lenguaje</b>.
     * @return Regresa todos los registros de la tabla <b>Lenguaje</b>.
     */
    public List<Lenguaje> findActive();

    /*
     * Método que obtiene un objeto DetachedCriteria.
     *
     * @return Regresa un objeto DetachedCriteria.
     */
    public DetachedCriteria createDetachedCriteria();

    /*
     * Método que obtiene los parámetros que le corresponden
     * a un determinado catálogo. Éste método es llamado por los Action
     * para obtener los parámetros del catálogo
     * @param catalogKey Es el código del catálogo del cual se quieren obtener los parámetros.
     * Los códigos se encuentran en el paquete <b>com.admon.bss.resources</b> 
     * en el archivo <b>CodesBss.properties</b>.
     * @return Regresa una lista con los parámetros del catálogo.
     */
    public List<ConfiguracionParametro> getParametros(String catalogKey);

}
