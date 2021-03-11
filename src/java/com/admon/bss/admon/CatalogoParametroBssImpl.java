package com.admon.bss.admon;

import com.admon.bss.BaseBss;
import com.admon.dao.admon.CatalogoParametroDAO;
import com.admon.entity.admon.Catalogo;
import com.admon.entity.admon.CatalogoParametro;
import com.admon.entity.admon.ConfiguracionParametro;
import com.admon.entity.admon.Grid;
import com.admon.entity.admon.Organizacion;
import com.admon.entity.admon.Pagina;
import com.admon.entity.admon.Usuario;
import com.admon.model.admon.CatalogoParametroAction;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

public class CatalogoParametroBssImpl extends BaseBss implements CatalogoParametroBss {

    /* Inyección de dependencias con Spring. Estas dependencias se
     * configuran en el applicationContext.xml, y además necesitan un
     * método setter por cada variable que se anexa al final de la clase. */
    private CatalogoParametroDAO catalogoParametroDAO;
    private OrganizacionBss organizacionBss;
    private UsuarioBss usuarioBss;
    private ConfiguracionParametroBss configuracionParametroBss;
    private CatalogoBss catalogoBss;

    public CatalogoParametroBssImpl() {
    }

    /*
     * Método que guarda los objetos contenidos en la lista <b>objectList</b> en la tabla de <b>CatalogoParametro</b>.
     * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de objetos que se guardarán en la tabla de <b>CatalogoParametro</b> en la BD.
     * @return Regresa una lista con los id's de los registros guardados.
     */
    @Override
    public List<Integer> save(Integer userId, List<CatalogoParametro> catalogoParametroList) {
        List<Integer> savedList = new ArrayList<Integer>();
        for (CatalogoParametro catalogoParametro : catalogoParametroList) {
            catalogoParametro.setEstatusId(ACTIVO);
            catalogoParametro.setEliminadoId(NOELIMINADO);
            catalogoParametro.setModificacionUsuario(userId);
            catalogoParametro.setCreacionUsuario(userId);
            catalogoParametro.setCreacionFecha(new Timestamp(new Date().getTime()));
            catalogoParametro.setModificacionFecha(new Timestamp(new Date().getTime()));
            savedList.add(catalogoParametroDAO.save(catalogoParametro));
        }
        return savedList;
    }

    @Override
    public List<Integer> saveCatalogoParametro(int userId, int catalogoId, int organizacionId, List<CatalogoParametro> detalleNuevoList) {
        List<CatalogoParametro> detalleActualList = findListCatalogoParametroBy(catalogoId, organizacionId);
        List<Integer> savedOrUpdatedList = new ArrayList<Integer>();
        // Delete
        for (CatalogoParametro detalle : detalleActualList) {
            boolean isDeletable = true;
            for (CatalogoParametro detalleNuevo : detalleNuevoList) {
                if (detalle.getCatalogoParametroId().equals(detalleNuevo.getCatalogoParametroId())) {
                    isDeletable = false;
                }
            }
            if (isDeletable) {
                delete(userId, Arrays.asList(detalle.getCatalogoParametroId()));
            }
        }
        // Save y update
        for (CatalogoParametro detalle : detalleNuevoList) {
            if (detalle.getCatalogoParametroId() == 0) {
                savedOrUpdatedList.addAll(save(userId, Arrays.asList(detalle)));
            } else {
                savedOrUpdatedList.addAll(update(userId, Arrays.asList(detalle)));
            }
        }
        return savedOrUpdatedList;
    }

    /*
     * Método que actualiza la información de <b>catalogoParametro</b> en la tabla de <b>CatalogoParametro</b>.
     * @param userId Es el id del usuario que realizó la operación. * @param catalogoParametroList Lista de registros que se actualizarán en la tabla de <b>CatalogoParametro</b> en la BD.
     */
    @Override
    public List<Integer> update(Integer userId, List<CatalogoParametro> catalogoParametroList) {
        List<Integer> idList = new ArrayList();
        for (CatalogoParametro catalogoParametro : catalogoParametroList) {
            catalogoParametro.setModificacionUsuario(userId);
            catalogoParametro.setModificacionFecha(new Timestamp(new Date().getTime()));
            catalogoParametroDAO.update(catalogoParametro);
            idList.add(catalogoParametro.getCatalogoParametroId());
        }
        return idList;
    }

    /*
     * Método que elimina el registro <b>catalogoParametro</b> en la tabla de <b>CatalogoParametro</b>.
     * @param userId Es el id del usuario que realizó la operación. * @param catalogoParametroList Lista de registros que se eliminarán en la tabla de <b>CatalogoParametro</b> en la BD.
     */
    @Override
    public void delete(Integer userId, List<Integer> catalogoParametroIdList) {
        for (Integer catalogoParametroId : catalogoParametroIdList) {
            CatalogoParametro catalogoParametro = findById(catalogoParametroId);
            catalogoParametro.setEliminadoId(ELIMINADO);
            catalogoParametro.setModificacionUsuario(userId);
            catalogoParametro.setModificacionFecha(new Timestamp(new Date().getTime()));
            catalogoParametroDAO.update(catalogoParametro);
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
     * Método que busca el registro <b>catalogoParametro</b> por su NOMBRE en la tabla de <b>CatalogoParametro</b>.
     * @param nombre es el NOMBRE del registro que se quiere obtener.
     * @return Regresa un objeto tipo <b>CatalogoParametro</b> con la información de la consulta.
     */
    @Override
    public CatalogoParametro findByName(String nombre) {
        return getFirst(findByCriteria(createDetachedCriteria()
                .add(Expression.eq("nombre", nombre.trim()))));
    }

    /*
     * Busca el registro <b>catalogoParametro</b> por su ID en la tabla de <b>CatalogoParametro</b>.
     * @param id es el ID del registro a obtener.
     * @return Regresa un objeto <b>CatalogoParametro</b> con la información del registro <b>catalogoParametro</b>.
     */
    @Override
    public CatalogoParametro findById(Integer catalogoParametroId) {
        CatalogoParametro catalogoParametro = catalogoParametroDAO.findById(catalogoParametroId);
        if (catalogoParametro == null) {
            return null;
        } else {
            return resolveDescription(Arrays.asList(catalogoParametro)).get(0);
        }
    }

    @Override
    public CatalogoParametro findById(Integer catalogoParametroId, Integer organizacionId) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Expression.eq("catalogoParametroId", catalogoParametroId));
        criteria.add(Expression.eq("organizacionId", organizacionId));
        List<CatalogoParametro> listCatalogoParametro = catalogoParametroDAO.findByCriteria(criteria);
        if (listCatalogoParametro == null || listCatalogoParametro.size() < 1) {
            return null;
        } else {
            CatalogoParametro catalogoParametro = listCatalogoParametro.get(0);
            return resolveDescription(Arrays.asList(catalogoParametro)).get(0);
        }
    }

    @Override
    public List<CatalogoParametro> findListCatalogoParametroBy(Integer catalogoId, Integer organizacionId) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Expression.eq("catalogoId", catalogoId));
        criteria.add(Expression.eq("organizacionId", organizacionId));
        return resolveDescription(findByCriteria(criteria));
    }

    @Override
    public List<CatalogoParametro> findByIntProperty(String propertyName, Integer value) {
        return resolveDescription(findByCriteria(
                createDetachedCriteria().add(Expression.eq(propertyName, value))));
    }

    /*
     * Método que hace una consulta a la tabla CatalogoParametro y obtiene los registros que coincidan con el objeto <b>catalogoParametro</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
     * @param displayedPage Número de página que se desea mostrar al usuario en el widget JQGrid.
     * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
     * @param order Indica por que campo sera ordenada la búsqueda.
     * @param ordenTipo Tipo de ordenado acendente o decendente.
     * @param CatalogoParametro objeto que contiene los parámetros a buscar.
     * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    @Override
    public Grid findByCriteria(int displayedPage, int maxResult, String order,
            String ordenTipo, CatalogoParametro catalogoParametro) {
        Grid grid = new Grid();
        DetachedCriteria criteria = createDetachedCriteria();
        // Auxiliar para busqueda por rango de fechas campo: CreacionFecha
        if (catalogoParametro.getCreacionFechaInicial() != null) {
            criteria.add(Expression.ge("creacionFecha", getStartOfDay(catalogoParametro.getCreacionFechaInicial())));
        }
        if (catalogoParametro.getCreacionFechaFinal() != null) {
            criteria.add(Expression.le("creacionFecha", getEndOfDay(catalogoParametro.getCreacionFechaFinal())));
        }
        // Auxiliar para busqueda por rango de fechas campo: ModificacionFecha
        if (catalogoParametro.getModificacionFechaInicial() != null) {
            criteria.add(Expression.ge("modificacionFecha", getStartOfDay(catalogoParametro.getModificacionFechaInicial())));
        }
        if (catalogoParametro.getModificacionFechaFinal() != null) {
            criteria.add(Expression.le("modificacionFecha", getEndOfDay(catalogoParametro.getModificacionFechaFinal())));
        }
        criteria.add(Example.create(catalogoParametro));
        if (ordenTipo.equals("asc")) {
            criteria.addOrder(Order.asc(order));
        } else {
            criteria.addOrder(Order.desc(order));
        }
        int resultadosTotales = findSizeByCriteria(copy(criteria));
        List<CatalogoParametro> catalogoParametroList = findByCriteriaLimit(criteria,
                maxResult * (displayedPage - 1), maxResult);
        grid.setGrid(resolveDescription(catalogoParametroList));
        grid.setTotal(resultadosTotales);
        grid.setPaginas(lastPage(resultadosTotales, maxResult));
        return grid;
    }

    @Override
    public CatalogoParametro findFirst() {
        List<CatalogoParametro> catalogoParametroList = findByCriteria(createDetachedCriteria());
        if (catalogoParametroList.isEmpty()) {
            return null;
        } else {
            return catalogoParametroList.get(0);
        }
    }

    public List<CatalogoParametro> resolveDescription(List<CatalogoParametro> list) {
        if (!list.isEmpty()) {
            List<Usuario> usuarioList = usuarioBss.findAll();
            List<Catalogo> catalogoList = catalogoBss.findAll();
            List<Organizacion> organizacionList = organizacionBss.findAll();
            List<ConfiguracionParametro> estatusList = getParametros("key_estatus");
            List<ConfiguracionParametro> eliminadoList = getParametros("key_eliminado");
            for (CatalogoParametro catalogoParametro : list) {

                catalogoParametro.setUsuarioModificacion("");
                if (catalogoParametro.getModificacionUsuario() != null) {
                    for (Usuario o : usuarioList) {
                        if (o.getUsuarioId().equals(catalogoParametro.getModificacionUsuario())) {
                            catalogoParametro.setUsuarioModificacion(o.getUsuario());
                            break;
                        }
                    }
                }

                catalogoParametro.setOrganizacion("");
                if (catalogoParametro.getOrganizacionId() != null) {
                    for (Organizacion o : organizacionList) {
                        if (o.getOrganizacionId().equals(catalogoParametro.getOrganizacionId())) {
                            catalogoParametro.setOrganizacion(o.getNombre());
                            break;
                        }
                    }
                }
                catalogoParametro.setCatalogo("");
                if (catalogoParametro.getCatalogoId() != null) {
                    for (Catalogo o : catalogoList) {
                        if (o.getCatalogoId().equals(catalogoParametro.getCatalogoId())) {
                            catalogoParametro.setCatalogo(o.getNombre());
                            break;
                        }
                    }
                }

                catalogoParametro.setEstatus("");
                if (catalogoParametro.getEstatusId() != null) {
                    for (ConfiguracionParametro o : estatusList) {
                        if (Integer.parseInt(o.getValor()) == catalogoParametro.getEstatusId()) {
                            catalogoParametro.setEstatus(o.getNombre());
                            break;
                        }
                    }
                }

                catalogoParametro.setEliminado("");
                if (catalogoParametro.getEliminadoId() != null) {
                    for (ConfiguracionParametro o : eliminadoList) {
                        if (Integer.parseInt(o.getValor()) == catalogoParametro.getEliminadoId()) {
                            catalogoParametro.setEliminado(o.getNombre());
                            break;
                        }
                    }
                }
            }
        }
        return list;
    }

    public Integer findSizeByCriteria(DetachedCriteria criteria) {
        return catalogoParametroDAO.findSizeByCriteria(generalizarCriteria(criteria));
    }

    public List<CatalogoParametro> findByCriteriaLimit(DetachedCriteria criteria, Integer from, Integer to) {
        return catalogoParametroDAO.findByCriteriaLimit(generalizarCriteria(criteria), from, to);
    }
    /*
     * Método que realiza un búsqueda en la BD obteniendo todos los registros que coincidan con los parámetros
     * definidos en el objeto <b>criteria</b>.
     *
     * @param criteria Es un objeto que contiene los parámetros de búsqueda.
     * @return Regresa una lista de objetos <b>CatalogoParametro</b> que coinciden con los parámetros definidos en
     * <b>criteria</b>.
     */

    @Override
    public List<CatalogoParametro> findByCriteria(DetachedCriteria criteria) {
        return catalogoParametroDAO.findByCriteria(generalizarCriteria(criteria));
    }

    @Override
    public List<CatalogoParametro> findByCriteriaIgnorePrivacy(CatalogoParametro catalogoParametro) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Example.create(catalogoParametro));
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        return catalogoParametroDAO.findByCriteria(criteria);
    }

    public DetachedCriteria generalizarCriteria(DetachedCriteria criteria) {
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        resolvePrivacy(criteria, CatalogoParametroAction.class);
        return criteria;
    }

    /*
     * Método que obtiene todos los registros de la tabla de <b>CatalogoParametro</b>.
     * Este método es generalmente utilizado por los action para obtener las opciones
     * de los select que se llenan mediante struts.
     *
     * @return Regresa todos los registros de la tabla <b>CatalogoParametro</b>.
     */
    @Override
    public List<CatalogoParametro> findAll() {
        return findByCriteria(createDetachedCriteria().addOrder(Order.asc("valor")));
    }

    /*
     * Método que obtiene todos los registros de la tabla de <b>CatalogoParametro</b>.
     * Este método es generalmente utilizado por los action para obtener las opciones
     * de los select que se llenan mediante struts.
     *
     * @return Regresa todos los registros de la tabla <b>CatalogoParametro</b>.
     */
    @Override
    public List<CatalogoParametro> findActive() {
        return findByCriteria(createDetachedCriteria().add(
                Expression.eq("estatusId", ACTIVO)).addOrder(Order.asc("valor")));
    }

    /*
     * Método que obtiene un objeto DetachedCriteria.
     *
     * @return Regresa un objeto DetachedCriteria.
     */
    @Override
    public DetachedCriteria createDetachedCriteria() {
        return catalogoParametroDAO.createDetachedCriteria();
    }

    /*
     * Método que actualiza el Estatus de los ID' contenidos en la lista
     * <b>CatalogoParametroList</b> en la tabla de <b>CatalogoParametro</b>.
     * @param userId userId Es el ID del usuario que realizó la operación.
     * @param estatusId Es el ID del estatus al cual se quiere cambiar.
     * @param CatalogoParametroList Es una lista que contiene los registros los cuales se
     * quiere actualizar su Estatus.
     */
    @Override
    public void setEstatus(Integer userId, Integer estatusId, List<CatalogoParametro> catalogoParametroList) {
        for (CatalogoParametro catalogoParametroTemporal : catalogoParametroList) {
            CatalogoParametro catalogoParametro = findById(catalogoParametroTemporal.getCatalogoId(), catalogoParametroTemporal.getOrganizacionId());
            if (estatusId == 1) {
                catalogoParametro.setEstatusId(ACTIVO);
            } else {
                catalogoParametro.setEstatusId(INACTIVO);
            }
            update(userId, Arrays.asList(catalogoParametro));
        }
    }

    /*
     * Método que actualiza el Visible de los ID' contenidos en la lista
     * <b>CatalogoParametroList</b> en la tabla de <b>CatalogoParametro</b>.
     * @param userId userId Es el ID del usuario que realizó la operación.
     * @param visibleId Es el ID del visible al cual se quiere cambiar.
     * @param CatalogoParametroList Es una lista que contiene los registros los cuales se
     * quiere actualizar su Visible.
     */
    @Override
    public void setVisible(Integer userId, Integer visible, List<CatalogoParametro> catalogoParametroList) {
        for (CatalogoParametro catalogoParametroTemporal : catalogoParametroList) {
            CatalogoParametro catalogoParametro = findById(catalogoParametroTemporal.getCatalogoId(), catalogoParametroTemporal.getOrganizacionId());
            if (visible == 1) {
                catalogoParametro.setVisible(true);
            } else {
                catalogoParametro.setVisible(false);
            }
            update(userId, Arrays.asList(catalogoParametro));
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
    public String getReportDataTest(String order, String ordenTipo, CatalogoParametro catalogoParametro) {
        Grid grid = findByCriteria(1, 10000, order, ordenTipo, catalogoParametro);
        return addReportDataToSession(grid.getGrid());
    }

    @Override
    public List<Catalogo> getCatalogo() {
        return addDummy(catalogoBss.findActive(), new Catalogo(), Catalogo.class);
    }

    @Override
    public String hasGrid() {
        return hasGrid(CatalogoParametroAction.class).toString();
    }

    @Override
    public String isIndividual() {
        return isIndividual(CatalogoParametroAction.class).toString();
    }

    @Override
    public String getNombreActionMenu() {
        Pagina pagina = findPageByActionClass(CatalogoParametroAction.class);
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

    public void setCatalogoParametroDAO(CatalogoParametroDAO catalogoParametroDAO) {
        this.catalogoParametroDAO = catalogoParametroDAO;
    }

    public void setOrganizacionBss(OrganizacionBss organizacionBss) {
        this.organizacionBss = organizacionBss;
    }

    public void setUsuarioBss(UsuarioBss usuarioBss) {
        this.usuarioBss = usuarioBss;
    }

    public void setConfiguracionParametroBss(ConfiguracionParametroBss configuracionParametroBss) {
        this.configuracionParametroBss = configuracionParametroBss;
    }

    public void setCatalogoBss(CatalogoBss catalogoBss) {
        this.catalogoBss = catalogoBss;
    }

}
