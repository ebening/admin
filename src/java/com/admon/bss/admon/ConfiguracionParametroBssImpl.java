package com.admon.bss.admon;

import com.admon.bss.BaseBss;
import com.admon.dao.admon.ConfiguracionParametroDAO;
import com.admon.entity.admon.*;
import com.admon.model.admon.ConfiguracionParametroAction;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

public class ConfiguracionParametroBssImpl extends BaseBss implements ConfiguracionParametroBss {

    /* Inyección de dependencias con Spring. Estas dependencias se
     * configuran en el applicationContext.xml, y además necesitan un
     * método setter por cada variable que se anexa al final de la clase. */
    private ConfiguracionParametroDAO configuracionParametroDAO;
    private UsuarioBss usuarioBss;
    private ConfiguracionBss configuracionBss;

    public ConfiguracionParametroBssImpl() {
    }

    /*
     * Método que guarda los objetos contenidos en la lista <b>objectList</b> en la tabla de <b>ConfiguracionParametro</b>.
     * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de objetos que se guardarán en la tabla de <b>ConfiguracionParametro</b> en la BD.
     * @return Regresa una lista con los id's de los registros guardados.
     */
    @Override
    public List<Integer> save(Integer userId, List<ConfiguracionParametro> configuracionParametroList) {
        List<Integer> savedList = new ArrayList<Integer>();
        for (ConfiguracionParametro configuracionParametro : configuracionParametroList) {
            configuracionParametro.setEstatusId(ACTIVO);
            configuracionParametro.setEliminadoId(NOELIMINADO);
            configuracionParametro.setModificacionUsuario(userId);
            configuracionParametro.setCreacionUsuario(userId);
            configuracionParametro.setCreacionFecha(new Timestamp(new Date().getTime()));
            configuracionParametro.setModificacionFecha(new Timestamp(new Date().getTime()));
            savedList.add(configuracionParametroDAO.save(configuracionParametro));
        }
        return savedList;
    }

    /*
     * Método que actualiza la información de <b>configuracionParametro</b> en la tabla de <b>ConfiguracionParametro</b>.
     * @param userId Es el id del usuario que realizó la operación. * @param configuracionParametroList Lista de registros que se actualizarán en la tabla de <b>ConfiguracionParametro</b> en la BD.
     */
    @Override
    public List<Integer> update(Integer userId, List<ConfiguracionParametro> configuracionParametroList) {
        List<Integer> idList = new ArrayList();
        for (ConfiguracionParametro configuracionParametro : configuracionParametroList) {
            configuracionParametro.setModificacionUsuario(userId);
            configuracionParametro.setModificacionFecha(new Timestamp(new Date().getTime()));
            configuracionParametroDAO.update(configuracionParametro);
            idList.add(configuracionParametro.getConfiguracionParametroId());
        }
        return idList;
    }

    /*
     * Método que elimina el registro <b>configuracionParametro</b> en la tabla de <b>ConfiguracionParametro</b>.
     * @param userId Es el id del usuario que realizó la operación. * @param configuracionParametroList Lista de registros que se eliminarán en la tabla de <b>ConfiguracionParametro</b> en la BD.
     */
    @Override
    public void delete(Integer userId, List<Integer> configuracionParametroIdList) {
        for (Integer configuracionParametroId : configuracionParametroIdList) {
            ConfiguracionParametro configuracionParametro = findById(configuracionParametroId);
            configuracionParametro.setEliminadoId(ELIMINADO);
            configuracionParametro.setModificacionUsuario(userId);
            configuracionParametro.setModificacionFecha(new Timestamp(new Date().getTime()));
            configuracionParametroDAO.update(configuracionParametro);
        }
    }

    /*
     * Método que evalúa si existe al menos un registro con un determinado
     * nombre en la tabla.
     * @param nombre Es el nombre que se desea buscar en la tabla de la BD.
     * @return Regresa <b>false</b> si existe al menos un registro con el
     * nombre específicado como parámetro en el método, si no existe ningún
     * registro con ese nombre regresa un <b>true</b>.
     */
    @Override
    public boolean isValidoNombre(String nombre) {
        return !(findByCriteria(createDetachedCriteria().add(
                Expression.eq("nombre", nombre.trim()))).size() >= 1);
    }

    /*
     * Método que busca el registro <b>configuracionParametro</b> por su NOMBRE en la tabla de <b>ConfiguracionParametro</b>.
     * @param nombre es el NOMBRE del registro que se quiere obtener.
     * @return Regresa un objeto tipo <b>ConfiguracionParametro</b> con la información de la consulta.
     */
    @Override
    public ConfiguracionParametro findByName(String nombre) {
        return getFirst(findByCriteria(createDetachedCriteria()
                .add(Expression.eq("nombre", nombre.trim()))));
    }

    /*
     * Busca el registro <b>configuracionParametro</b> por su ID en la tabla de <b>ConfiguracionParametro</b>.
     * @param id es el ID del registro a obtener.
     * @return Regresa un objeto <b>ConfiguracionParametro</b> con la información del registro <b>configuracionParametro</b>.
     */
    @Override
    public ConfiguracionParametro findById(Integer configuracionParametroId) {
        ConfiguracionParametro configuracionParametro = configuracionParametroDAO.findById(configuracionParametroId);
        if (configuracionParametro == null) {
            return null;
        } else {
            return resolveDescription(Arrays.asList(configuracionParametro)).get(0);
        }
    }

    @Override
    public List<ConfiguracionParametro> findByIntProperty(String propertyName, Integer value) {
        return resolveDescription(findByCriteria(
                createDetachedCriteria().add(Expression.eq(propertyName, value))));
    }

    /*
     * Método que hace una consulta a la tabla ConfiguracionParametro y obtiene los registros que coincidan con el objeto <b>configuracionParametro</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
     * @param displayedPage Número de página que se desea mostrar al usuario en el widget JQGrid.
     * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
     * @param order Indica por que campo sera ordenada la búsqueda.
     * @param ordenTipo Tipo de ordenado acendente o decendente.
     * @param ConfiguracionParametro objeto que contiene los parámetros a buscar.
     * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    @Override
    public Grid findByCriteria(int displayedPage, int maxResult, String order,
            String ordenTipo, ConfiguracionParametro configuracionParametro) {
        Grid grid = new Grid();
        DetachedCriteria criteria = createDetachedCriteria();
        // Auxiliar para busqueda por rango de fechas campo: CreacionFecha
        if (configuracionParametro.getCreacionFechaInicial() != null) {
            criteria.add(Expression.ge("creacionFecha", getStartOfDay(configuracionParametro.getCreacionFechaInicial())));
        }
        if (configuracionParametro.getCreacionFechaFinal() != null) {
            criteria.add(Expression.le("creacionFecha", getEndOfDay(configuracionParametro.getCreacionFechaFinal())));
        }
        // Auxiliar para busqueda por rango de fechas campo: ModificacionFecha
        if (configuracionParametro.getModificacionFechaInicial() != null) {
            criteria.add(Expression.ge("modificacionFecha", getStartOfDay(configuracionParametro.getModificacionFechaInicial())));
        }
        if (configuracionParametro.getModificacionFechaFinal() != null) {
            criteria.add(Expression.le("modificacionFecha", getEndOfDay(configuracionParametro.getModificacionFechaFinal())));
        }
        criteria.add(Example.create(configuracionParametro));
        if (ordenTipo.equals("asc")) {
            criteria.addOrder(Order.asc(order));
        } else {
            criteria.addOrder(Order.desc(order));
        }
        int resultadosTotales = findSizeByCriteria(copy(criteria));
        List<ConfiguracionParametro> configuracionParametroList = findByCriteriaLimit(criteria,
                maxResult * (displayedPage - 1), maxResult);
        grid.setGrid(resolveDescription(configuracionParametroList));
        grid.setTotal(resultadosTotales);
        grid.setPaginas(lastPage(resultadosTotales, maxResult));
        return grid;
    }

    @Override
    public ConfiguracionParametro findFirst() {
        List<ConfiguracionParametro> configuracionParametroList = findByCriteria(createDetachedCriteria());
        if (configuracionParametroList.isEmpty()) {
            return null;
        } else {
            return configuracionParametroList.get(0);
        }
    }

    public List<ConfiguracionParametro> resolveDescription(List<ConfiguracionParametro> list) {
        if (!list.isEmpty()) {
            List<Usuario> usuarioList = usuarioBss.findAll();
            List<Configuracion> configuracionList = configuracionBss.findAll();
            List<ConfiguracionParametro> estatusList = getParametros("key_estatus");
            List<ConfiguracionParametro> eliminadoList = getParametros("key_eliminado");
            for (ConfiguracionParametro configuracionParametro : list) {

                configuracionParametro.setUsuarioModificacion("");
                if (configuracionParametro.getModificacionUsuario() != null) {
                    for (Usuario o : usuarioList) {
                        if (o.getUsuarioId() == configuracionParametro.getModificacionUsuario()) {
                            configuracionParametro.setUsuarioModificacion(o.getUsuario());
                            break;
                        }
                    }
                }

                configuracionParametro.setConfiguracion("");
                if (configuracionParametro.getConfiguracionId() != null) {
                    for (Configuracion o : configuracionList) {
                        if (o.getConfiguracionId() == configuracionParametro.getConfiguracionId()) {
                            configuracionParametro.setConfiguracion(o.getNombre());
                            break;
                        }
                    }
                }

                configuracionParametro.setEstatus("");
                if (configuracionParametro.getEstatusId() != null) {
                    for (ConfiguracionParametro o : estatusList) {
                        if (Integer.parseInt(o.getValor()) == configuracionParametro.getEstatusId()) {
                            configuracionParametro.setEstatus(o.getNombre());
                            break;
                        }
                    }
                }

                configuracionParametro.setEliminado("");
                if (configuracionParametro.getEliminadoId() != null) {
                    for (ConfiguracionParametro o : eliminadoList) {
                        if (Integer.parseInt(o.getValor()) == configuracionParametro.getEliminadoId()) {
                            configuracionParametro.setEliminado(o.getNombre());
                            break;
                        }
                    }
                }
            }
        }
        return list;
    }

    public Integer findSizeByCriteria(DetachedCriteria criteria) {
        return configuracionParametroDAO.findSizeByCriteria(generalizarCriteria(criteria));
    }

    public List<ConfiguracionParametro> findByCriteriaLimit(DetachedCriteria criteria, Integer from, Integer to) {
        return configuracionParametroDAO.findByCriteriaLimit(generalizarCriteria(criteria), from, to);
    }

    /*
     * Método que realiza un búsqueda en la BD obteniendo todos los registros que coincidan con los parámetros
     * definidos en el objeto <b>criteria</b>.
     *
     * @param criteria Es un objeto que contiene los parámetros de búsqueda.
     * @return Regresa una lista de objetos <b>ConfiguracionParametro</b> que coinciden con los parámetros definidos en
     * <b>criteria</b>.
     */
    @Override
    public List<ConfiguracionParametro> findByCriteria(DetachedCriteria criteria) {
        return configuracionParametroDAO.findByCriteria(generalizarCriteria(criteria));
    }

    @Override
    public List<ConfiguracionParametro> findByCriteriaIgnorePrivacy(ConfiguracionParametro configuracionParametro) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Example.create(configuracionParametro));
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        return configuracionParametroDAO.findByCriteria(criteria);
    }

    public DetachedCriteria generalizarCriteria(DetachedCriteria criteria) {
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        resolvePrivacy(criteria, ConfiguracionParametroAction.class);
        return criteria;
    }

    /*
     * Método que obtiene todos los registros de la tabla de <b>ConfiguracionParametro</b>.
     * Este método es generalmente utilizado por los action para obtener las opciones
     * de los select que se llenan mediante struts.
     *
     * @return Regresa todos los registros de la tabla <b>ConfiguracionParametro</b>.
     */
    @Override
    public List<ConfiguracionParametro> findAll() {
        return findByCriteria(createDetachedCriteria().addOrder(Order.asc("nombre")));
    }

    /*
     * Método que obtiene todos los registros de la tabla de <b>ConfiguracionParametro</b>.
     * Este método es generalmente utilizado por los action para obtener las opciones
     * de los select que se llenan mediante struts.
     *
     * @return Regresa todos los registros de la tabla <b>ConfiguracionParametro</b>.
     */
    @Override
    public List<ConfiguracionParametro> findActive() {
        return findByCriteria(createDetachedCriteria().add(
                Expression.eq("estatusId", ACTIVO)).addOrder(Order.asc("nombre")));
    }

    /*
     * Método que obtiene un objeto DetachedCriteria.
     *
     * @return Regresa un objeto DetachedCriteria.
     */
    @Override
    public DetachedCriteria createDetachedCriteria() {
        return configuracionParametroDAO.createDetachedCriteria();
    }

    /*
     * Método que actualiza el Estatus de los ID' contenidos en la lista
     * <b>configuracionParametroList</b> en la tabla de <b>ConfiguracionParametro</b>.
     * @param userId userId Es el ID del usuario que realizó la operación.
     * @param estatusId Es el ID del estatus al cual se quiere cambiar.
     * @param configuracionParametroList Es una lista que contiene los registros los cuales se
     * quiere actualizar su Estatus.
     */
    @Override
    public void setEstatus(Integer userId, Integer estatusId, List<Integer> configuracionParametroList) {
        for (Integer configuracionParametroId : configuracionParametroList) {
            ConfiguracionParametro configuracionParametro = findById(configuracionParametroId);
            if (estatusId == 1) {
                configuracionParametro.setEstatusId(ACTIVO);
            } else {
                configuracionParametro.setEstatusId(INACTIVO);
            }
            update(userId, Arrays.asList(configuracionParametro));
        }
    }

    /*
     * Método que obtiene los parámetros que le corresponden
     * a un determinado catálogo. Éste método es llamado por los Action
     * para obtener los parámetros del catálogo
     * @param catalogKey Es el código del catálogo del cual se quieren obtener los parámetros.
     * Los códigos se encuentran en el paquete <b>com.admon.bss.resources</b> 
     * en el archivo <b>CodesBss.properties</b>.
     * @return Regresa una lista con los parámetros del catálogo.
     */
    @Override
    public List<ConfiguracionParametro> getParametros(String catalogKey) {
        return findByCriteria(createDetachedCriteria()
                .add(Expression.eq("configuracionId", new Integer(codigos.getString(catalogKey))))
                .add(Expression.eq("estatusId", ACTIVO)));
    }

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
    @Override
    public String getReportDataTest(String order, String ordenTipo, ConfiguracionParametro configuracionParametro) {
        Grid grid = findByCriteria(1, 10000, order, ordenTipo, configuracionParametro);
        return addReportDataToSession(grid.getGrid());
    }

    /*
     * Metodos que actualizan los campos <select> en el jsp despues de haber hecho
     * una edición de datos en un popup externo.
     */
    @Override
    public List<Configuracion> getConfiguracion() {
        return addDummy(configuracionBss.findActive(), new Configuracion(), Configuracion.class);
    }

    @Override
    public String hasGrid() {
        return hasGrid(ConfiguracionParametroAction.class).toString();
    }

    @Override
    public String isIndividual() {
        return isIndividual(ConfiguracionParametroAction.class).toString();
    }

    @Override
    public String getNombreActionMenu() {
        Pagina pagina = findPageByActionClass(ConfiguracionParametroAction.class);
        if (pagina == null) {
            return "";
        } else {
            return pagina.getNombre();
        }
    }

    /*
     * Inyección de dependencias con Spring, cada
     * referencia definida al inicio de la clase requiere un método
     * Setter.
     */
    public void setConfiguracionParametroDAO(ConfiguracionParametroDAO configuracionParametroDAO) {
        this.configuracionParametroDAO = configuracionParametroDAO;
    }

//    public void setConfiguracionParametroBss(ConfiguracionParametroBss configuracionParametroBss) {
//        this.configuracionParametroBss = configuracionParametroBss;
//    }
    public void setUsuarioBss(UsuarioBss usuarioBss) {
        this.usuarioBss = usuarioBss;
    }

    public void setConfiguracionBss(ConfiguracionBss configuracionBss) {
        this.configuracionBss = configuracionBss;
    }

}
