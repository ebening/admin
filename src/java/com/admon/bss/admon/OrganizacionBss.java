package com.admon.bss.admon;

import com.admon.entity.admon.*;
import org.hibernate.criterion.DetachedCriteria;
import java.util.List;

public interface OrganizacionBss {

    /*
     * Método que guarda los objetos contenidos en la lista <b>objectList</b> en la tabla de <b>Organizacion</b>.
     * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de objetos que se guardarán en la tabla de <b>Organizacion</b> en la BD.
     * @return Regresa una lista con los id's de los registros guardados.
     */
    public List<Integer> save(Integer userId, List<Organizacion> organizacionList);

    public List<Integer> saveOrganizacionCredencial(int userId, int organizacionId,
            List<OrganizacionCredencial> detalleNuevoList);
//public void saveOrganizacionDetalle(int userId, int organizacionId,
//List<OrganizacionDetalle> detalleNuevoList);

    public void saveOrganizacionUsuario(int userId, int organizacionId,
            List<OrganizacionUsuario> detalleNuevoList);

    public String crearOrganizacionGenerar(int userId, int organizacionId,
            List<OrganizacionGenerar> detalleNuevoList);

    /*
     * Método que elimina el registro <b>organizacion</b> en la tabla de <b>Organizacion</b>.
     * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de registros que se actualizarán en la tabla de <b>Organizacion</b> en la BD.
     */
    public List<Integer> update(Integer userId, List<Organizacion> organizacionList);

    /*
     * Método que elimina el registro <b>organizacion</b> en la tabla de <b>Organizacion</b>.
     * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de registros que se eliminarán en la tabla de <b>Organizacion</b> en la BD.
     */
    public void delete(Integer userId, List<Integer> organizacionIdList);

    /*
     * Método que evalúa si existe al menos un registro con un determinado
     * nombre en la tabla.
     * @param nombre Es el nombre que se desea buscar en la tabla de la BD.
     * @return Regresa <b>false</b> si existe al menos un registro con el
     * nombre específicado como parámetro en el método, si no existe ningún
     * registro con ese nombre regresa un <b>true</b>.
     */
    public boolean isValidoNombre(String nombre);

    /*
     * Método que busca el registro <b>organizacion</b> por su NOMBRE en la tabla de <b>Organizacion</b>.
     * @param nombre es el NOMBRE del registro que se quiere obtener.
     * @return Regresa un objeto tipo <b>Organizacion</b> con la información de la consulta.
     */
    public Organizacion findByName(String nombre);

    /*
     * Método que busca el registro <b>organizacion</b> por su ID en la tabla de <b>Organizacion</b>.
     * @param id es el ID del registro que se quiere obtener.
     * @return Regresa un objeto tipo <b>Organizacion</b> con la información del registro <b>organizacion</b>.
     */
    public Organizacion findById(Integer organizacionId);

    public List<Organizacion> findByIntProperty(String propertyName, Integer value);

    public List<OrganizacionCredencial> findOrganizacionCredencialByIntProperty(String propertyName, Integer value);
//public List<OrganizacionDetalle> findOrganizacionDetalleByIntProperty(String propertyName, Integer value);

    public List<OrganizacionUsuario> findOrganizacionUsuarioByIntProperty(String propertyName, Integer value);

    public List<OrganizacionGenerar> findOrganizacionGenerarByIntProperty(String propertyName, Integer value);

    /*
     * Método que hace una consulta a la tabla Organizacion y obtiene los registros que coincidan con el objeto <b>organizacion</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados correctamente en el widget JQGrid del JSP.
     * @param p Número de página que se desea mostrar al usuario en el widget JQGrid.
     * @param maxResult Cantidad de registros por página, es la cantidad de filas que soporta el widget JQGrid.
     * @param order Indica por que campo sera ordenada la búsqueda.
     * @param ordenTipo Tipo de ordenado acendente o decendente.
     * @param Organizacion objeto que contiene los parámetros a buscar.
     * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    public Grid findByCriteria(int p, int maxResult, String order, String ordenTipo, Organizacion organizacion);

    /*
     * Método que realiza un búsqueda en la BD obteniendo todos los registros que coincidan con los parámetros
     * definidos en el objeto <b>criteria</b>.
     *
     * @param criteria Es un objeto que contiene los parámetros de búsqueda.
     * @return Regresa una lista de objetos <b>Organizacion</b> que coinciden con los parámetros definidos en
     * <b>criteria</b>.
     */
    public List<Organizacion> findByCriteria(DetachedCriteria criteria);

    public List<Organizacion> findByCriteriaIgnorePrivacy(Organizacion organizacion);

    /**
     * Método que obtiene el primer resultado de una consulta, utilizado cuando
     * la pagina tiene la propiedad individual
     *
     * @return regresa el primer resultado de la consulta, si no hay resultados
     * regresa null
     */
    public Organizacion findFirst();

    /*
     * Método que obtiene todos los registros de la tabla de <b>Organizacion</b>.
     * @return Regresa todos los registros de la tabla <b>Organizacion</b>.
     */
    public List<Organizacion> findActive();

    /*
     * Método que obtiene todos los registros de la tabla de <b>Organizacion</b>.
     * @return Regresa todos los registros de la tabla <b>Organizacion</b>.
     */
    public List<Organizacion> findAll();

    /*
     * Método que obtiene un objeto DetachedCriteria.
     *
     * @return Regresa un objeto DetachedCriteria.
     */
    public DetachedCriteria createDetachedCriteria();

    /*
     * Método que actualiza el Estatus de los ID' contenidos en la lista
     * <b>organizacionList</b> en la tabla de <b>Organizacion</b>.
     * @param userId userId Es el ID del usuario que realizó la operación.
     * @param estatusId Es el ID del estatus al cual se quiere cambiar.
     * @param organizacionList Es una lista que contiene los registros los cuales se
     * quiere actualizar su Estatus.
     */
//    public void setEstatus(Integer userId, Integer estatusId, List<Integer> organizacionList);

    /*
     * Método que actualiza el CreaEventos de los ID' contenidos en la lista
     * <b>organizacionList</b> en la tabla de <b>Organizacion</b>.
     * @param userId userId Es el ID del usuario que realizó la operación.
     * @param creaEventosId Es el ID del creaEventos al cual se quiere cambiar.
     * @param organizacionList Es una lista que contiene los registros los cuales se
     * quiere actualizar su CreaEventos.
     */
    public void setCreaEventos(Integer userId, Integer creaEventosId, List<Integer> organizacionList);

    /*
     * Método que actualiza el ServicioCompleto de los ID' contenidos en la lista
     * <b>organizacionList</b> en la tabla de <b>Organizacion</b>.
     * @param userId userId Es el ID del usuario que realizó la operación.
     * @param servicioCompletoId Es el ID del servicioCompleto al cual se quiere cambiar.
     * @param organizacionList Es una lista que contiene los registros los cuales se
     * quiere actualizar su ServicioCompleto.
     */
    public void setServicioCompleto(Integer userId, Integer servicioCompletoId, List<Integer> organizacionList);

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
     * Método que hace una consulta a la tabla Usuario y obtiene todos sus
     * registros correctamente filtrados con un objeto DetachedCriteria para ser
     * utilizados en los reportes de los grid (PDF y Excel).
     *
     * @param order Indica por que campo sera ordenada la búsqueda.
     * @param ordenTipo Tipo de ordenado acendente o decendente.
     * @return Regresa una lista de objetos <b>Usuario</b> con los datos a
     * mostrar en el reporte.
     */
    public String getReportDataTest(String order, String ordenTipo, Organizacion organizacion);

    /*
     * Metodos que actualizan los campos <select> en el jsp despues de haber hecho
     * una edición de datos en un popup externo.
     */
    // <editor-fold defaultstate="collapsed" desc="Getters Detalle(s)">
    /**
     * Método auxiliar utilizado para la funcionalidad "detalle"
     * (ORGANIZACION_CREDENCIAL) campo Organizacion.
     *
     * @return regresa una lista de objetos.
     */
    public List<Organizacion> getOrganizacionCredencialOrganizacion();

    /**
     * Método auxiliar utilizado para la funcionalidad "detalle"
     * (ORGANIZACION_DETALLE) campo Organizacion.
     *
     * @return regresa una lista de objetos.
     */
//public List<Organizacion> getOrganizacionDetalleOrganizacion();
    /**
     * Método auxiliar utilizado para la funcionalidad "detalle" (ORGANIZACION)
     * campo Pais.
     *
     * @return regresa una lista de objetos.
     */
    public List<Pais> getPais();

    /**
     * Método auxiliar utilizado para la funcionalidad "detalle" (ORGANIZACION)
     * campo Estado.
     *
     * @return regresa una lista de objetos.
     */
    public List<Estado> getEstado();

    /**
     * Método auxiliar utilizado para la funcionalidad "detalle" (ORGANIZACION)
     * campo Ciudad.
     *
     * @return regresa una lista de objetos.
     */
    public List<Ciudad> getCiudad();

    /**
     * Método auxiliar utilizado para la funcionalidad "detalle"
     * (ORGANIZACION_USUARIO) campo Organizacion.
     *
     * @return regresa una lista de objetos.
     */
    public List<Organizacion> getOrganizacionUsuarioOrganizacion();
    /**
     * Método auxiliar utilizado para la funcionalidad "detalle"
     * (ORGANIZACION_GENERAR) campo Organizacion.
     *
     * @return regresa una lista de objetos.
     */
    public List<Organizacion> getOrganizacionGenerarOrganizacion();

    // </editor-fold>

    public String hasGrid();

    public String isIndividual();

    public String hasGridOrganizacionCredencial();

    public String isIndividualOrganizacionCredencial();

//public String hasGridOrganizacionDetalle();
//public String isIndividualOrganizacionDetalle();
    public String hasGridOrganizacionUsuario();

    public String isIndividualOrganizacionUsuario();

    public String hasGridOrganizacionGenerar();

    public String isIndividualOrganizacionGenerar();
    
    public String getNombreActionMenu();

    public List<Estado> filtrarEstado(int paisId);

    public List<Ciudad> filtrarCiudad(int estadoId);
}
