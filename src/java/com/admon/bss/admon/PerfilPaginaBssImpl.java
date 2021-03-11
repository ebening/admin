package com.admon.bss.admon;

import com.admon.bss.BaseBss;
import com.admon.dao.admon.PerfilPaginaDAO;
import com.admon.entity.admon.*;
import com.admon.model.admon.PerfilPaginaAction;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

public class PerfilPaginaBssImpl extends BaseBss implements PerfilPaginaBss {

    /* Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase. */
    private PerfilPaginaDAO perfilPaginaDAO;
    private ConfiguracionParametroBss configuracionParametroBss;
    private UsuarioBss usuarioBss;
    private PerfilBss perfilBss;
    private PaginaBss paginaBss;

    public PerfilPaginaBssImpl() {
    }

    /*
 * Método que guarda los objetos contenidos en la lista <b>objectList</b> en la tabla de <b>PerfilPagina</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de objetos que se guardarán en la tabla de <b>PerfilPagina</b> en la BD.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    @Override
    public List<Integer> save(Integer userId, Integer perfilId, List<Integer> paginaList) {

        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Expression.eq("perfilId", perfilId));
        criteria.add(Expression.eq("estatusId", ACTIVO));
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        List<Integer> perfilPaginaIdLista = new ArrayList();
        for (PerfilPagina perfilPagina : findByCriteria(criteria)) {
            perfilPaginaIdLista.add(perfilPagina.getPerfilPaginaId());
        }
        delete(userId, perfilPaginaIdLista);

        List<Integer> savedList = new ArrayList<Integer>();
        for (int paginaId : paginaList) {
            PerfilPagina perfilPagina = new PerfilPagina();
            perfilPagina.setPerfilId(perfilId);
            perfilPagina.setPaginaId(paginaId);
            perfilPagina.setEstatusId(ACTIVO);
            perfilPagina.setEliminadoId(NOELIMINADO);
            perfilPagina.setModificacionUsuario(userId);
            perfilPagina.setCreacionUsuario(userId);
            perfilPagina.setCreacionFecha(new Timestamp(new Date().getTime()));
            perfilPagina.setModificacionFecha(new Timestamp(new Date().getTime()));
            savedList.add(perfilPaginaDAO.save(perfilPagina));
        }
        return savedList;
    }

    /*
 * Método que actualiza la información de <b>perfilPagina</b> en la tabla de <b>PerfilPagina</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param perfilPaginaList Lista de registros que se actualizarán en la tabla de <b>PerfilPagina</b> en la BD.
     */
    @Override
    public List<Integer> update(Integer userId, List<PerfilPagina> perfilPaginaList) {
        List<Integer> idList = new ArrayList();
        for (PerfilPagina perfilPagina : perfilPaginaList) {
            perfilPagina.setModificacionUsuario(userId);
            perfilPagina.setModificacionFecha(new Timestamp(new Date().getTime()));
            perfilPaginaDAO.update(perfilPagina);
            idList.add(perfilPagina.getPerfilPaginaId());
        }
        return idList;
    }

    /*
 * Método que elimina el registro <b>perfilPagina</b> en la tabla de <b>PerfilPagina</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param perfilPaginaList Lista de registros que se eliminarán en la tabla de <b>PerfilPagina</b> en la BD.
     */
    @Override
    public void delete(Integer userId, List<Integer> perfilPaginaIdList) {
        for (Integer perfilPaginaId : perfilPaginaIdList) {
            PerfilPagina perfilPagina = findById(perfilPaginaId);
            perfilPagina.setEliminadoId(ELIMINADO);
            perfilPagina.setModificacionUsuario(userId);
            perfilPagina.setModificacionFecha(new Timestamp(new Date().getTime()));
            perfilPaginaDAO.update(perfilPagina);
        }
    }

    /*
 * Busca el registro <b>perfilPagina</b> por su ID en la tabla de <b>PerfilPagina</b>.
 * @param id es el ID del registro a obtener.
 * @return Regresa un objeto <b>PerfilPagina</b> con la información del registro <b>perfilPagina</b>.
     */
    @Override
    public PerfilPagina findById(Integer perfilPaginaId) {
        PerfilPagina perfilPagina = perfilPaginaDAO.findById(perfilPaginaId);
        if (perfilPagina == null) {
            return null;
        } else {
            return resolveDescription(Arrays.asList(perfilPagina)).get(0);
        }
    }

    @Override
    public List<PerfilPagina> findByIntProperty(String propertyName, Integer value) {
        return resolveDescription(findByCriteria(
                createDetachedCriteria().add(Expression.eq(propertyName, value))));
    }

    /*
 * Método que hace una consulta a la tabla PerfilPagina y obtiene los registros que coincidan con el objeto <b>perfilPagina</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
 * @param displayedPage Número de página que se desea mostrar al usuario en el widget JQGrid.
 * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
 * @param order Indica por que campo sera ordenada la búsqueda.
 * @param ordenTipo Tipo de ordenado acendente o decendente.
 * @param PerfilPagina objeto que contiene los parámetros a buscar.
 * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    @Override
    public Grid findByCriteria(int displayedPage, int maxResult, String order,
            String ordenTipo, PerfilPagina perfilPagina) {
        Grid grid = new Grid();
        DetachedCriteria criteria = createDetachedCriteria();
        // Auxiliar para busqueda por rango de fechas campo: CreacionFecha
        if (perfilPagina.getCreacionFechaInicial() != null) {
            criteria.add(Expression.ge("creacionFecha", getStartOfDay(perfilPagina.getCreacionFechaInicial())));
        }
        if (perfilPagina.getCreacionFechaFinal() != null) {
            criteria.add(Expression.le("creacionFecha", getEndOfDay(perfilPagina.getCreacionFechaFinal())));
        }
        // Auxiliar para busqueda por rango de fechas campo: ModificacionFecha
        if (perfilPagina.getModificacionFechaInicial() != null) {
            criteria.add(Expression.ge("modificacionFecha", getStartOfDay(perfilPagina.getModificacionFechaInicial())));
        }
        if (perfilPagina.getModificacionFechaFinal() != null) {
            criteria.add(Expression.le("modificacionFecha", getEndOfDay(perfilPagina.getModificacionFechaFinal())));
        }
        criteria.add(Example.create(perfilPagina));
        if (ordenTipo.equals("asc")) {
            criteria.addOrder(Order.asc(order));
        } else {
            criteria.addOrder(Order.desc(order));
        }
        int resultadosTotales = findSizeByCriteria(copy(criteria));
        List<PerfilPagina> perfilPaginaList = findByCriteriaLimit(criteria,
                maxResult * (displayedPage - 1), maxResult);
        grid.setGrid(resolveDescription(perfilPaginaList));
        grid.setTotal(resultadosTotales);
        grid.setPaginas(lastPage(resultadosTotales, maxResult));
        return grid;
    }

    @Override
    public PerfilPagina findFirst() {
        List<PerfilPagina> perfilPaginaList = findByCriteria(createDetachedCriteria());
        if (perfilPaginaList.isEmpty()) {
            return null;
        } else {
            return perfilPaginaList.get(0);
        }
    }

    public List<PerfilPagina> resolveDescription(List<PerfilPagina> list) {
        if (!list.isEmpty()) {
            List<Usuario> usuarioList = usuarioBss.findAll();
            List<Perfil> perfilList = perfilBss.findAll();
            List<Pagina> paginaList = paginaBss.findAll();
            List<ConfiguracionParametro> estatusList = getParametros("key_estatus");
            List<ConfiguracionParametro> eliminadoList = getParametros("key_eliminado");
            for (PerfilPagina perfilPagina : list) {

                perfilPagina.setUsuarioModificacion("");
                if (perfilPagina.getModificacionUsuario() != null) {
                    for (Usuario o : usuarioList) {
                        if (o.getUsuarioId() == perfilPagina.getModificacionUsuario()) {
                            perfilPagina.setUsuarioModificacion(o.getUsuario());
                            break;
                        }
                    }
                }

                perfilPagina.setPerfil("");
                if (perfilPagina.getPerfilId() != null) {
                    for (Perfil o : perfilList) {
                        if (o.getPerfilId() == perfilPagina.getPerfilId()) {
                            perfilPagina.setPerfil(o.getNombre());
                            break;
                        }
                    }
                }

                perfilPagina.setPagina("");
                if (perfilPagina.getPaginaId() != null) {
                    for (Pagina o : paginaList) {
                        if (o.getPaginaId() == perfilPagina.getPaginaId()) {
                            perfilPagina.setPagina(o.getNombre());
                            break;
                        }
                    }
                }

                perfilPagina.setEstatus("");
                if (perfilPagina.getEstatusId() != null) {
                    for (ConfiguracionParametro o : estatusList) {
                        if (Integer.parseInt(o.getValor()) == perfilPagina.getEstatusId()) {
                            perfilPagina.setEstatus(o.getNombre());
                            break;
                        }
                    }
                }

                perfilPagina.setEliminado("");
                if (perfilPagina.getEliminadoId() != null) {
                    for (ConfiguracionParametro o : eliminadoList) {
                        if (Integer.parseInt(o.getValor()) == perfilPagina.getEliminadoId()) {
                            perfilPagina.setEliminado(o.getNombre());
                            break;
                        }
                    }
                }
            }
        }
        return list;
    }

    public Integer findSizeByCriteria(DetachedCriteria criteria) {
        return perfilPaginaDAO.findSizeByCriteria(generalizarCriteria(criteria));
    }

    public List<PerfilPagina> findByCriteriaLimit(DetachedCriteria criteria, Integer from, Integer to) {
        return perfilPaginaDAO.findByCriteriaLimit(generalizarCriteria(criteria), from, to);
    }

    /*
 * Método que realiza un búsqueda en la BD obteniendo todos los registros que coincidan con los parámetros
 * definidos en el objeto <b>criteria</b>.
 *
 * @param criteria Es un objeto que contiene los parámetros de búsqueda.
 * @return Regresa una lista de objetos <b>PerfilPagina</b> que coinciden con los parámetros definidos en
 * <b>criteria</b>.
     */
    @Override
    public List<PerfilPagina> findByCriteria(DetachedCriteria criteria) {
        return perfilPaginaDAO.findByCriteria(generalizarCriteria(criteria));
    }

    @Override
    public List<PerfilPagina> findByCriteriaIgnorePrivacy(PerfilPagina perfilPagina) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Example.create(perfilPagina));
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        return perfilPaginaDAO.findByCriteria(criteria);
    }

    public DetachedCriteria generalizarCriteria(DetachedCriteria criteria) {
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        resolvePrivacy(criteria, PerfilPaginaAction.class);
        return criteria;
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>PerfilPagina</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>PerfilPagina</b>.
     */
    @Override
    public List<PerfilPagina> findAll() {
        return findByCriteria(createDetachedCriteria());
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>PerfilPagina</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>PerfilPagina</b>.
     */
    @Override
    public List<PerfilPagina> findActive() {
        return findByIntProperty("estatusId", ACTIVO);
    }

    /*
 * Método que obtiene un objeto DetachedCriteria.
 *
 * @return Regresa un objeto DetachedCriteria.
     */
    @Override
    public DetachedCriteria createDetachedCriteria() {
        return perfilPaginaDAO.createDetachedCriteria();
    }

    /*
 * Método que actualiza el Estatus de los ID' contenidos en la lista
 * <b>perfilPaginaList</b> en la tabla de <b>PerfilPagina</b>.
 * @param userId userId Es el ID del usuario que realizó la operación.
 * @param estatusId Es el ID del estatus al cual se quiere cambiar.
 * @param perfilPaginaList Es una lista que contiene los registros los cuales se
 * quiere actualizar su Estatus.
     */
    @Override
    public void setEstatus(Integer userId, Integer estatusId, List<Integer> perfilPaginaList) {
        for (Integer perfilPaginaId : perfilPaginaList) {
            PerfilPagina perfilPagina = findById(perfilPaginaId);
            if (estatusId == 1) {
                perfilPagina.setEstatusId(ACTIVO);
            } else {
                perfilPagina.setEstatusId(INACTIVO);
            }
            update(userId, Arrays.asList(perfilPagina));
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
    public String getReportDataTest(String order, String ordenTipo, PerfilPagina perfilPagina) {
        Grid grid = findByCriteria(1, 10000, order, ordenTipo, perfilPagina);
        return addReportDataToSession(grid.getGrid());
    }

    /*
 * Metodos que actualizan los campos <select> en el jsp despues de haber hecho
 * una edición de datos en un popup externo.
     */
    @Override
    public List<Perfil> getPerfil() {
        return addDummy(perfilBss.findActive(), new Perfil(), Perfil.class);
    }

    @Override
    public List<Pagina> getPagina() {
        return addDummy(paginaBss.findActive(), new Pagina(), Pagina.class);
    }

    @Override
    public String hasGrid() {
        return hasGrid(PerfilPaginaAction.class).toString();
    }

    @Override
    public String isIndividual() {
        return isIndividual(PerfilPaginaAction.class).toString();
    }

    @Override
    public String getNombreActionMenu() {
        Pagina pagina = findPageByActionClass(PerfilPaginaAction.class);
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
    public void setPerfilPaginaDAO(PerfilPaginaDAO perfilPaginaDAO) {
        this.perfilPaginaDAO = perfilPaginaDAO;
    }

    public void setConfiguracionParametroBss(ConfiguracionParametroBss configuracionParametroBss) {
        this.configuracionParametroBss = configuracionParametroBss;
    }

    public void setUsuarioBss(UsuarioBss usuarioBss) {
        this.usuarioBss = usuarioBss;
    }

    public void setPerfilBss(PerfilBss perfilBss) {
        this.perfilBss = perfilBss;
    }

    public void setPaginaBss(PaginaBss paginaBss) {
        this.paginaBss = paginaBss;
    }

    /*
* Obtiene una lista de todos los registros válidos (activos y no eliminados) de la tabla PAGINA.
*
* @return regresa una lista de todos los registros de la tabla PAGINA
     */
    @Override
    public List<Pagina> obtienePaginas() {
        DetachedCriteria criteria = paginaBss.createDetachedCriteria();
        criteria.add(Expression.eq("estatusId", ACTIVO));
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        return paginaBss.findByCriteria(criteria);
    }


    /*
* Obtiene una lista de todas las paginas a las que un determinado usuario tiene acceso.
*
* @param perfilId es el usuario del cual se quiere obtener los accesos
* @return regresa una lista de todos los registros de la tabla PAGINA
     */
 /*
* Obtiene una lista de todas las paginas a las que un determinado usuario tiene acceso.
*
* @param usuarioId es el usuario del cual se quiere obtener los accesos
* @return regresa una lista de todos los registros de la tabla PAGINA
     */
    @Override
    public List<Integer> obtieneAccesos(int perfilId) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Expression.eq("perfilId", perfilId));
        criteria.add(Expression.eq("estatusId", ACTIVO));
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        List<Integer> paginaIdLista = new ArrayList();
        for (PerfilPagina perfilPagina : findByCriteria(criteria)) {
            paginaIdLista.add(perfilPagina.getPaginaId());
        }
        return paginaIdLista;
    }

}
