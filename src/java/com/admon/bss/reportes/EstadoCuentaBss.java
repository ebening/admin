package com.admon.bss.reportes;

import com.admon.entity.admon.ConfiguracionParametro;
import com.admon.entity.admon.Organizacion;
import com.admon.entity.reportes.Evento;
import com.admon.pkg.entity.EstadoCuentaRS;
import java.util.List;

public interface EstadoCuentaBss {

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

    public String addDataToSession(List list);
    /*
     * Metodos que actualizan los campos <select> en el jsp despues de haber hecho
     * una edición de datos en un popup externo.
     */

    public List<Organizacion> getOrganizacion();

    public String hasGrid();

    public String isIndividual();

    public String getNombreActionMenu();

    public List<Evento> filtrarEvento(int organizacionId);
}
