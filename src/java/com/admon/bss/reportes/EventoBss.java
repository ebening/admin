package com.admon.bss.reportes;

import com.admon.entity.admon.ConfiguracionParametro;
import com.admon.entity.admon.Organizacion;
import com.admon.entity.reportes.Evento;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;

public interface EventoBss {

    /*
     * Método que realiza un búsqueda en la BD obteniendo todos los registros que coincidan con los parámetros
     * definidos en el objeto <b>criteria</b>.
     *
     * @param criteria Es un objeto que contiene los parámetros de búsqueda.
     * @return Regresa una lista de objetos <b>Evento</b> que coinciden con los parámetros definidos en
     * <b>criteria</b>.
     */
    public List<Evento> findByCriteria(DetachedCriteria criteria);

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

    /*
     * Metodos que actualizan los campos <select> en el jsp despues de haber hecho
     * una edición de datos en un popup externo.
     */
    public List<Organizacion> getOrganizacion();

    public List<Evento> filtrarEvento(int organizacionId);
}
