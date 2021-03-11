package com.admon.bss.admon;

import com.admon.bss.BaseBss;
import com.admon.dao.admon.EstadoDAO;
import com.admon.entity.admon.*;
import com.admon.model.admon.EstadoAction;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

public class EstadoBssImpl extends BaseBss implements EstadoBss {

    /* Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase. */
    private EstadoDAO estadoDAO;
    private ConfiguracionParametroBss configuracionParametroBss;
    private UsuarioBss usuarioBss;
    private PaisBss paisBss;

    public EstadoBssImpl() {
    }

    /*
 * Método que guarda los objetos contenidos en la lista <b>objectList</b> en la tabla de <b>Estado</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de objetos que se guardarán en la tabla de <b>Estado</b> en la BD.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    @Override
    public List<Integer> save(Integer userId, List<Estado> estadoList) {
        List<Integer> savedList = new ArrayList<Integer>();
        for (Estado estado : estadoList) {
            estado.setEstatusId(ACTIVO);
            estado.setEliminadoId(NOELIMINADO);
            estado.setModificacionUsuario(userId);
            estado.setCreacionUsuario(userId);
            estado.setCreacionFecha(new Timestamp(new Date().getTime()));
            estado.setModificacionFecha(new Timestamp(new Date().getTime()));
            savedList.add(estadoDAO.save(estado));
        }
        return savedList;
    }

    /*
 * Método que actualiza la información de <b>estado</b> en la tabla de <b>Estado</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param estadoList Lista de registros que se actualizarán en la tabla de <b>Estado</b> en la BD.
     */
    @Override
    public List<Integer> update(Integer userId, List<Estado> estadoList) {
        List<Integer> idList = new ArrayList();
        for (Estado estado : estadoList) {
            estado.setModificacionUsuario(userId);
            estado.setModificacionFecha(new Timestamp(new Date().getTime()));
            estadoDAO.update(estado);
            idList.add(estado.getEstadoId());
        }
        return idList;
    }

    /*
 * Método que elimina el registro <b>estado</b> en la tabla de <b>Estado</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param estadoList Lista de registros que se eliminarán en la tabla de <b>Estado</b> en la BD.
     */
    @Override
    public void delete(Integer userId, List<Integer> estadoIdList) {
        for (Integer estadoId : estadoIdList) {
            Estado estado = findById(estadoId);
            estado.setEliminadoId(ELIMINADO);
            estado.setModificacionUsuario(userId);
            estado.setModificacionFecha(new Timestamp(new Date().getTime()));
            estadoDAO.update(estado);
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
 * Método que busca el registro <b>estado</b> por su NOMBRE en la tabla de <b>Estado</b>.
 * @param nombre es el NOMBRE del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>Estado</b> con la información de la consulta.
     */
    @Override
    public Estado findByName(String nombre) {
        return getFirst(findByCriteria(createDetachedCriteria()
                .add(Expression.eq("nombre", nombre.trim()))));
    }

    /*
 * Busca el registro <b>estado</b> por su ID en la tabla de <b>Estado</b>.
 * @param id es el ID del registro a obtener.
 * @return Regresa un objeto <b>Estado</b> con la información del registro <b>estado</b>.
     */
    @Override
    public Estado findById(Integer estadoId) {
        Estado estado = estadoDAO.findById(estadoId);
        if (estado == null) {
            return null;
        } else {
            return resolveDescription(Arrays.asList(estado)).get(0);
        }
    }

    @Override
    public List<Estado> findByIntProperty(String propertyName, Integer value) {
        return resolveDescription(findByCriteria(
                createDetachedCriteria().add(Expression.eq(propertyName, value))));
    }

    /*
 * Método que hace una consulta a la tabla Estado y obtiene los registros que coincidan con el objeto <b>estado</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
 * @param displayedPage Número de página que se desea mostrar al usuario en el widget JQGrid.
 * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
 * @param order Indica por que campo sera ordenada la búsqueda.
 * @param ordenTipo Tipo de ordenado acendente o decendente.
 * @param Estado objeto que contiene los parámetros a buscar.
 * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    @Override
    public Grid findByCriteria(int displayedPage, int maxResult, String order,
            String ordenTipo, Estado estado) {
        Grid grid = new Grid();
        DetachedCriteria criteria = createDetachedCriteria();
        // Auxiliar para busqueda por rango de fechas campo: CreacionFecha
        if (estado.getCreacionFechaInicial() != null) {
            criteria.add(Expression.ge("creacionFecha", getStartOfDay(estado.getCreacionFechaInicial())));
        }
        if (estado.getCreacionFechaFinal() != null) {
            criteria.add(Expression.le("creacionFecha", getEndOfDay(estado.getCreacionFechaFinal())));
        }
        // Auxiliar para busqueda por rango de fechas campo: ModificacionFecha
        if (estado.getModificacionFechaInicial() != null) {
            criteria.add(Expression.ge("modificacionFecha", getStartOfDay(estado.getModificacionFechaInicial())));
        }
        if (estado.getModificacionFechaFinal() != null) {
            criteria.add(Expression.le("modificacionFecha", getEndOfDay(estado.getModificacionFechaFinal())));
        }
        criteria.add(Example.create(estado));
        if (ordenTipo.equals("asc")) {
            criteria.addOrder(Order.asc(order));
        } else {
            criteria.addOrder(Order.desc(order));
        }
        int resultadosTotales = findSizeByCriteria(copy(criteria));
        List<Estado> estadoList = findByCriteriaLimit(criteria,
                maxResult * (displayedPage - 1), maxResult);
        grid.setGrid(resolveDescription(estadoList));
        grid.setTotal(resultadosTotales);
        grid.setPaginas(lastPage(resultadosTotales, maxResult));
        return grid;
    }

    @Override
    public Estado findFirst() {
        List<Estado> estadoList = findByCriteria(createDetachedCriteria());
        if (estadoList.isEmpty()) {
            return null;
        } else {
            return estadoList.get(0);
        }
    }

    public List<Estado> resolveDescription(List<Estado> list) {
        if (!list.isEmpty()) {
            List<Usuario> usuarioList = usuarioBss.findAll();
            List<Pais> paisList = paisBss.findAll();
            List<ConfiguracionParametro> estatusList = getParametros("key_estatus");
            List<ConfiguracionParametro> eliminadoList = getParametros("key_eliminado");
            for (Estado estado : list) {

                estado.setUsuarioModificacion("");
                if (estado.getModificacionUsuario() != null) {
                    for (Usuario o : usuarioList) {
                        if (o.getUsuarioId() == estado.getModificacionUsuario()) {
                            estado.setUsuarioModificacion(o.getUsuario());
                            break;
                        }
                    }
                }

                estado.setPais("");
                if (estado.getPaisId() != null) {
                    for (Pais o : paisList) {
                        if (o.getPaisId() == estado.getPaisId()) {
                            estado.setPais(o.getNombre());
                            break;
                        }
                    }
                }

                estado.setEstatus("");
                if (estado.getEstatusId() != null) {
                    for (ConfiguracionParametro o : estatusList) {
                        if (Integer.parseInt(o.getValor()) == estado.getEstatusId()) {
                            estado.setEstatus(o.getNombre());
                            break;
                        }
                    }
                }

                estado.setEliminado("");
                if (estado.getEliminadoId() != null) {
                    for (ConfiguracionParametro o : eliminadoList) {
                        if (Integer.parseInt(o.getValor()) == estado.getEliminadoId()) {
                            estado.setEliminado(o.getNombre());
                            break;
                        }
                    }
                }
            }
        }
        return list;
    }

    public Integer findSizeByCriteria(DetachedCriteria criteria) {
        return estadoDAO.findSizeByCriteria(generalizarCriteria(criteria));
    }

    public List<Estado> findByCriteriaLimit(DetachedCriteria criteria, Integer from, Integer to) {
        return estadoDAO.findByCriteriaLimit(generalizarCriteria(criteria), from, to);
    }

    /*
 * Método que realiza un búsqueda en la BD obteniendo todos los registros que coincidan con los parámetros
 * definidos en el objeto <b>criteria</b>.
 *
 * @param criteria Es un objeto que contiene los parámetros de búsqueda.
 * @return Regresa una lista de objetos <b>Estado</b> que coinciden con los parámetros definidos en
 * <b>criteria</b>.
     */
    @Override
    public List<Estado> findByCriteria(DetachedCriteria criteria) {
        return estadoDAO.findByCriteria(generalizarCriteria(criteria));
    }

    @Override
    public List<Estado> findByCriteriaIgnorePrivacy(Estado estado) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Example.create(estado));
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        return estadoDAO.findByCriteria(criteria);
    }

    public DetachedCriteria generalizarCriteria(DetachedCriteria criteria) {
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        resolvePrivacy(criteria, EstadoAction.class);
        return criteria;
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>Estado</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>Estado</b>.
     */
    @Override
    public List<Estado> findAll() {
        return findByCriteria(createDetachedCriteria().addOrder(Order.asc("nombre")));
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>Estado</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>Estado</b>.
     */
    @Override
    public List<Estado> findActive() {
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
        return estadoDAO.createDetachedCriteria();
    }

    /*
 * Método que actualiza el Estatus de los ID' contenidos en la lista
 * <b>estadoList</b> en la tabla de <b>Estado</b>.
 * @param userId userId Es el ID del usuario que realizó la operación.
 * @param estatusId Es el ID del estatus al cual se quiere cambiar.
 * @param estadoList Es una lista que contiene los registros los cuales se
 * quiere actualizar su Estatus.
     */
    @Override
    public void setEstatus(Integer userId, Integer estatusId, List<Integer> estadoList) {
        for (Integer estadoId : estadoList) {
            Estado estado = findById(estadoId);
            if (estatusId == 1) {
                estado.setEstatusId(ACTIVO);
            } else {
                estado.setEstatusId(INACTIVO);
            }
            update(userId, Arrays.asList(estado));
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
        return addDummy(configuracionParametroBss.getParametros(catalogKey), new ConfiguracionParametro(), ConfiguracionParametro.class);
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
    public String getReportDataTest(String order, String ordenTipo, Estado estado) {
        Grid grid = findByCriteria(1, 10000, order, ordenTipo, estado);
        return addReportDataToSession(grid.getGrid());
    }

    /*
 * Metodos que actualizan los campos <select> en el jsp despues de haber hecho
 * una edición de datos en un popup externo.
     */
    @Override
    public List<Pais> getPais() {
        return addDummy(paisBss.findActive(), new Pais(), Pais.class);
    }

    @Override
    public String hasGrid() {
        return hasGrid(EstadoAction.class).toString();
    }

    @Override
    public String isIndividual() {
        return isIndividual(EstadoAction.class).toString();
    }

    @Override
    public String getNombreActionMenu() {
        Pagina pagina = findPageByActionClass(EstadoAction.class);
        if (pagina == null) {
            return "";
        } else {
            return pagina.getNombre();
        }
    }

    // Selects encadenados metodos
    @Override
    public List<Estado> filtrarEstado(int paisId) {
        return addDummy(findByCriteria(createDetachedCriteria().add(Expression.eq("paisId", paisId))
        ), new Estado(), Estado.class);
    }

    /*
 * Inyección de dependencias con Spring, cada
 * referencia definida al inicio de la clase requiere un método
 * Setter.
     */
    public void setEstadoDAO(EstadoDAO estadoDAO) {
        this.estadoDAO = estadoDAO;
    }

    public void setConfiguracionParametroBss(ConfiguracionParametroBss configuracionParametroBss) {
        this.configuracionParametroBss = configuracionParametroBss;
    }

    public void setUsuarioBss(UsuarioBss usuarioBss) {
        this.usuarioBss = usuarioBss;
    }

    public void setPaisBss(PaisBss paisBss) {
        this.paisBss = paisBss;
    }

}
