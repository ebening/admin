package com.admon.bss.admon;

import com.admon.bss.BaseBss;
import com.admon.dao.admon.PaginaDAO;
import com.admon.entity.admon.*;
import com.admon.model.admon.PaginaAction;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

public class PaginaBssImpl extends BaseBss implements PaginaBss {

    /* Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase. */
    private PaginaDAO paginaDAO;
    private ConfiguracionParametroBss configuracionParametroBss;
    private UsuarioBss usuarioBss;

    public PaginaBssImpl() {
    }

    /*
 * Método que guarda los objetos contenidos en la lista <b>objectList</b> en la tabla de <b>Pagina</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de objetos que se guardarán en la tabla de <b>Pagina</b> en la BD.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    @Override
    public List<Integer> save(Integer userId, List<Pagina> paginaList) {
        List<Integer> savedList = new ArrayList<Integer>();
        for (Pagina pagina : paginaList) {
            pagina.setEstatusId(ACTIVO);
            pagina.setEliminadoId(NOELIMINADO);
            pagina.setModificacionUsuario(userId);
            pagina.setCreacionUsuario(userId);
            pagina.setCreacionFecha(new Timestamp(new Date().getTime()));
            pagina.setModificacionFecha(new Timestamp(new Date().getTime()));
            savedList.add(paginaDAO.save(pagina));
        }
        return savedList;
    }

    /*
 * Método que actualiza la información de <b>pagina</b> en la tabla de <b>Pagina</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param paginaList Lista de registros que se actualizarán en la tabla de <b>Pagina</b> en la BD.
     */
    @Override
    public List<Integer> update(Integer userId, List<Pagina> paginaList) {
        List<Integer> idList = new ArrayList();
        for (Pagina pagina : paginaList) {
            pagina.setModificacionUsuario(userId);
            pagina.setModificacionFecha(new Timestamp(new Date().getTime()));
            paginaDAO.update(pagina);
            idList.add(pagina.getPaginaId());
        }
        return idList;
    }

    /*
 * Método que elimina el registro <b>pagina</b> en la tabla de <b>Pagina</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param paginaList Lista de registros que se eliminarán en la tabla de <b>Pagina</b> en la BD.
     */
    @Override
    public void delete(Integer userId, List<Integer> paginaIdList) {
        for (Integer paginaId : paginaIdList) {
            Pagina pagina = findById(paginaId);
            pagina.setEliminadoId(ELIMINADO);
            pagina.setModificacionUsuario(userId);
            pagina.setModificacionFecha(new Timestamp(new Date().getTime()));
            paginaDAO.update(pagina);
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
 * Método que busca el registro <b>pagina</b> por su NOMBRE en la tabla de <b>Pagina</b>.
 * @param nombre es el NOMBRE del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>Pagina</b> con la información de la consulta.
     */
    @Override
    public Pagina findByName(String nombre) {
        return getFirst(findByCriteria(createDetachedCriteria()
                .add(Expression.eq("nombre", nombre.trim()))));
    }

    /*
 * Busca el registro <b>pagina</b> por su ID en la tabla de <b>Pagina</b>.
 * @param id es el ID del registro a obtener.
 * @return Regresa un objeto <b>Pagina</b> con la información del registro <b>pagina</b>.
     */
    @Override
    public Pagina findById(Integer paginaId) {
        Pagina pagina = paginaDAO.findById(paginaId);
        if (pagina == null) {
            return null;
        } else {
            return resolveDescription(Arrays.asList(pagina)).get(0);
        }
    }

    @Override
    public List<Pagina> findByIntProperty(String propertyName, Integer value) {
        return resolveDescription(findByCriteria(
                createDetachedCriteria().add(Expression.eq(propertyName, value))));
    }

    /*
 * Método que hace una consulta a la tabla Pagina y obtiene los registros que coincidan con el objeto <b>pagina</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
 * @param displayedPage Número de página que se desea mostrar al usuario en el widget JQGrid.
 * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
 * @param order Indica por que campo sera ordenada la búsqueda.
 * @param ordenTipo Tipo de ordenado acendente o decendente.
 * @param Pagina objeto que contiene los parámetros a buscar.
 * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    @Override
    public Grid findByCriteria(int displayedPage, int maxResult, String order,
            String ordenTipo, Pagina pagina) {
        Grid grid = new Grid();
        DetachedCriteria criteria = createDetachedCriteria();
        // Auxiliar para busqueda por rango de fechas campo: CreacionFecha
        if (pagina.getCreacionFechaInicial() != null) {
            criteria.add(Expression.ge("creacionFecha", getStartOfDay(pagina.getCreacionFechaInicial())));
        }
        if (pagina.getCreacionFechaFinal() != null) {
            criteria.add(Expression.le("creacionFecha", getEndOfDay(pagina.getCreacionFechaFinal())));
        }
        // Auxiliar para busqueda por rango de fechas campo: ModificacionFecha
        if (pagina.getModificacionFechaInicial() != null) {
            criteria.add(Expression.ge("modificacionFecha", getStartOfDay(pagina.getModificacionFechaInicial())));
        }
        if (pagina.getModificacionFechaFinal() != null) {
            criteria.add(Expression.le("modificacionFecha", getEndOfDay(pagina.getModificacionFechaFinal())));
        }
        criteria.add(Example.create(pagina));
        if (ordenTipo.equals("asc")) {
            criteria.addOrder(Order.asc(order));
        } else {
            criteria.addOrder(Order.desc(order));
        }
        int resultadosTotales = findSizeByCriteria(copy(criteria));
        List<Pagina> paginaList = findByCriteriaLimit(criteria,
                maxResult * (displayedPage - 1), maxResult);
        grid.setGrid(resolveDescription(paginaList));
        grid.setTotal(resultadosTotales);
        grid.setPaginas(lastPage(resultadosTotales, maxResult));
        return grid;
    }

    @Override
    public Pagina findFirst() {
        List<Pagina> paginaList = findByCriteria(createDetachedCriteria());
        if (paginaList.isEmpty()) {
            return null;
        } else {
            return paginaList.get(0);
        }
    }

    public List<Pagina> resolveDescription(List<Pagina> list) {
        if (!list.isEmpty()) {
            List<Usuario> usuarioList = usuarioBss.findAll();
            List<ConfiguracionParametro> estatusList = getParametros("key_estatus");
            List<ConfiguracionParametro> eliminadoList = getParametros("key_eliminado");
            List<Pagina> paginaList = getMenus();
            for (Pagina pagina : list) {

                pagina.setUsuarioModificacion("");
                if (pagina.getModificacionUsuario() != null) {
                    for (Usuario o : usuarioList) {
                        if (o.getUsuarioId() == pagina.getModificacionUsuario()) {
                            pagina.setUsuarioModificacion(o.getUsuario());
                            break;
                        }
                    }
                }

                pagina.setEstatus("");
                if (pagina.getEstatusId() != null) {
                    for (ConfiguracionParametro o : estatusList) {
                        if (Integer.parseInt(o.getValor()) == pagina.getEstatusId()) {
                            pagina.setEstatus(o.getNombre());
                            break;
                        }
                    }
                }

                pagina.setEliminado("");
                if (pagina.getEliminadoId() != null) {
                    for (ConfiguracionParametro o : eliminadoList) {
                        if (Integer.parseInt(o.getValor()) == pagina.getEliminadoId()) {
                            pagina.setEliminado(o.getNombre());
                            break;
                        }
                    }
                }
                for (Pagina o : paginaList) {
                    if (o.getPaginaId() == pagina.getAnidar()) {
                        pagina.setAnidarNombre(o.getNombre());
                        break;
                    }
                }
            }
        }
        return list;
    }

    public Integer findSizeByCriteria(DetachedCriteria criteria) {
        return paginaDAO.findSizeByCriteria(generalizarCriteria(criteria));
    }

    public List<Pagina> findByCriteriaLimit(DetachedCriteria criteria, Integer from, Integer to) {
        return paginaDAO.findByCriteriaLimit(generalizarCriteria(criteria), from, to);
    }

    /*
 * Método que realiza un búsqueda en la BD obteniendo todos los registros que coincidan con los parámetros
 * definidos en el objeto <b>criteria</b>.
 *
 * @param criteria Es un objeto que contiene los parámetros de búsqueda.
 * @return Regresa una lista de objetos <b>Pagina</b> que coinciden con los parámetros definidos en
 * <b>criteria</b>.
     */
    @Override
    public List<Pagina> findByCriteria(DetachedCriteria criteria) {
        return paginaDAO.findByCriteria(generalizarCriteria(criteria));
    }

    @Override
    public List<Pagina> findByCriteriaIgnorePrivacy(Pagina pagina) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Example.create(pagina));
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        return paginaDAO.findByCriteria(criteria);
    }

    public DetachedCriteria generalizarCriteria(DetachedCriteria criteria) {
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        resolvePrivacy(criteria, PaginaAction.class);
        return criteria;
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>Pagina</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>Pagina</b>.
     */
    @Override
    public List<Pagina> findAll() {
        return findByCriteria(createDetachedCriteria().addOrder(Order.asc("nombre")));
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>Pagina</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>Pagina</b>.
     */
    @Override
    public List<Pagina> findActive() {
        return findByCriteria(createDetachedCriteria().add(
                Expression.eq("estatusId", ACTIVO)).addOrder(Order.asc("orden")));
    }

    /*
 * Método que obtiene un objeto DetachedCriteria.
 *
 * @return Regresa un objeto DetachedCriteria.
     */
    @Override
    public DetachedCriteria createDetachedCriteria() {
        return paginaDAO.createDetachedCriteria();
    }

    /*
 * Método que actualiza el Estatus de los ID' contenidos en la lista
 * <b>paginaList</b> en la tabla de <b>Pagina</b>.
 * @param userId userId Es el ID del usuario que realizó la operación.
 * @param estatusId Es el ID del estatus al cual se quiere cambiar.
 * @param paginaList Es una lista que contiene los registros los cuales se
 * quiere actualizar su Estatus.
     */
    @Override
    public void setEstatus(Integer userId, Integer estatusId, List<Integer> paginaList) {
        for (Integer paginaId : paginaList) {
            Pagina pagina = findById(paginaId);
            if (estatusId == 1) {
                pagina.setEstatusId(ACTIVO);
            } else {
                pagina.setEstatusId(INACTIVO);
            }
            update(userId, Arrays.asList(pagina));
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
    public String getReportDataTest(String order, String ordenTipo, Pagina pagina) {
        Grid grid = findByCriteria(1, 10000, order, ordenTipo, pagina);
        return addReportDataToSession(grid.getGrid());
    }

    /**
     * Método para obtener las páginas que conforman el menu.
     *
     * @return regresa una lista de objetos Pagina.
     */
    @Override
    public List<Pagina> getMenus() {
        List<Pagina> paginaList = paginaDAO.findByCriteria(createDetachedCriteria()
                .add(Expression.eq("estatusId", ACTIVO))
                .add(Expression.eq("eliminadoId", NOELIMINADO))
                .addOrder(Order.asc("nombre")));
        addDummy(paginaList, new Pagina(), Pagina.class, "--Menu Ra&iacute;z--");
        return paginaList;
    }

    @Override
    public List<Pagina> findByPaginaIdList(List<Integer> paginaIdList) {
        return paginaDAO.findByCriteria(createDetachedCriteria()
                .add(Expression.in("paginaId", paginaIdList))
                .add(Expression.eq("estatusId", ACTIVO))
                .add(Expression.eq("eliminadoId", NOELIMINADO)));
    }

    /*
 * Metodos que actualizan los campos <select> en el jsp despues de haber hecho
 * una edición de datos en un popup externo.
     */
    @Override
    public String hasGrid() {
        return hasGrid(PaginaAction.class).toString();
    }

    @Override
    public String isIndividual() {
        return isIndividual(PaginaAction.class).toString();
    }

    @Override
    public String getNombreActionMenu() {
        Pagina pagina = findPageByActionClass(PaginaAction.class);
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
    public void setPaginaDAO(PaginaDAO paginaDAO) {
        this.paginaDAO = paginaDAO;
    }

    public void setConfiguracionParametroBss(ConfiguracionParametroBss configuracionParametroBss) {
        this.configuracionParametroBss = configuracionParametroBss;
    }

    public void setUsuarioBss(UsuarioBss usuarioBss) {
        this.usuarioBss = usuarioBss;
    }
}
