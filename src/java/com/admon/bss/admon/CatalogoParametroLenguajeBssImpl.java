package com.admon.bss.admon;

import com.admon.bss.BaseBss;
import com.admon.dao.admon.CatalogoParametroLenguajeDAO;
import com.admon.entity.admon.CatalogoParametro;
import com.admon.entity.admon.CatalogoParametroLenguaje;
import com.admon.entity.admon.ConfiguracionParametro;
import com.admon.entity.admon.Grid;
import com.admon.entity.admon.Lenguaje;
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

public class CatalogoParametroLenguajeBssImpl extends BaseBss implements CatalogoParametroLenguajeBss {

    /* Inyección de dependencias con Spring. Estas dependencias se
     * configuran en el applicationContext.xml, y además necesitan un
     * método setter por cada variable que se anexa al final de la clase. */
    private CatalogoParametroLenguajeDAO catalogoParametroLenguajeDAO;
    private OrganizacionBss organizacionBss;
    private LenguajeBss lenguajeBss;
    private UsuarioBss usuarioBss;
    private ConfiguracionParametroBss configuracionParametroBss;
    private CatalogoParametroBss catalogoParametroBss;

    public CatalogoParametroLenguajeBssImpl() {
    }

    /*
     * Método que guarda los objetos contenidos en la lista <b>objectList</b> en la tabla de <b>CatalogoParametroLenguaje</b>.
     * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de objetos que se guardarán en la tabla de <b>CatalogoParametroLenguaje</b> en la BD.
     * @return Regresa una lista con los id's de los registros guardados.
     */
    @Override
    public List<Integer> save(Integer userId, List<CatalogoParametroLenguaje> catalogoParametroLenguajeList) {
        List<Integer> savedList = new ArrayList<Integer>();
        for (CatalogoParametroLenguaje catalogoParametroLenguaje : catalogoParametroLenguajeList) {
            catalogoParametroLenguaje.setEstatusId(ACTIVO);
            catalogoParametroLenguaje.setEliminadoId(NOELIMINADO);
            catalogoParametroLenguaje.setModificacionUsuario(userId);
            catalogoParametroLenguaje.setCreacionUsuario(userId);
            catalogoParametroLenguaje.setCreacionFecha(new Timestamp(new Date().getTime()));
            catalogoParametroLenguaje.setModificacionFecha(new Timestamp(new Date().getTime()));
            savedList.add(catalogoParametroLenguajeDAO.save(catalogoParametroLenguaje));
        }
        return savedList;
    }

    @Override
    public void saveCatalogoParametroLenguaje(int userId, int catalogoParametroId, int organizacionId, List<CatalogoParametroLenguaje> detalleNuevoList) {
        List<CatalogoParametroLenguaje> detalleActualList = findCatalogoParametroBy(catalogoParametroId, organizacionId);
        // Delete
        for (CatalogoParametroLenguaje detalle : detalleActualList) {
            boolean isDeletable = true;
            for (CatalogoParametroLenguaje detalleNuevo : detalleNuevoList) {
                if (detalle.getCatalogoParametroId()
                        == detalleNuevo.getCatalogoParametroId()) {
                    isDeletable = false;
                }
            }
            if (isDeletable) {
                delete(userId,
                        Arrays.asList(detalle.getCatalogoParametroId()));
            }
        }
        // Save y update
        for (CatalogoParametroLenguaje detalle : detalleNuevoList) {
            if (detalle.getCatalogoParametroId() == 0) {
                save(userId, Arrays.asList(detalle));
            } else {
                update(userId, Arrays.asList(detalle));
            }
        }
    }
    /*
     * Método que actualiza la información de <b>catalogoParametroLenguaje</b> en la tabla de <b>CatalogoParametroLenguaje</b>.
     * @param userId Es el id del usuario que realizó la operación. * @param catalogoParametroLenguajeList Lista de registros que se actualizarán en la tabla de <b>CatalogoParametroLenguaje</b> en la BD.
     */

    @Override
    public List<Integer> update(Integer userId, List<CatalogoParametroLenguaje> catalogoParametroLenguajeList) {
        List<Integer> idList = new ArrayList();
        for (CatalogoParametroLenguaje catalogoParametroLenguaje : catalogoParametroLenguajeList) {
            catalogoParametroLenguaje.setModificacionUsuario(userId);
            catalogoParametroLenguaje.setModificacionFecha(new Timestamp(new Date().getTime()));
            catalogoParametroLenguajeDAO.update(catalogoParametroLenguaje);
            idList.add(catalogoParametroLenguaje.getCatalogoParametroId());
        }
        return idList;
    }

    /*
     * Método que elimina el registro <b>catalogoParametroLenguaje</b> en la tabla de <b>CatalogoParametroLenguaje</b>.
     * @param userId Es el id del usuario que realizó la operación. * @param catalogoParametroLenguajeList Lista de registros que se eliminarán en la tabla de <b>CatalogoParametroLenguaje</b> en la BD.
     */
    @Override
    public void delete(Integer userId, List<Integer> catalogoParametroLenguajeIdList) {
        for (Integer catalogoParametroLenguajeId : catalogoParametroLenguajeIdList) {
            CatalogoParametroLenguaje catalogoParametroLenguaje = findById(catalogoParametroLenguajeId);
            catalogoParametroLenguaje.setEliminadoId(ELIMINADO);
            catalogoParametroLenguaje.setModificacionUsuario(userId);
            catalogoParametroLenguaje.setModificacionFecha(new Timestamp(new Date().getTime()));
            catalogoParametroLenguajeDAO.update(catalogoParametroLenguaje);
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
     * Método que busca el registro <b>catalogoParametroLenguaje</b> por su NOMBRE en la tabla de <b>CatalogoParametroLenguaje</b>.
     * @param nombre es el NOMBRE del registro que se quiere obtener.
     * @return Regresa un objeto tipo <b>CatalogoParametroLenguaje</b> con la información de la consulta.
     */
    @Override
    public CatalogoParametroLenguaje findByName(String nombre) {
        return getFirst(findByCriteria(createDetachedCriteria()
                .add(Expression.eq("nombre", nombre.trim()))));
    }

    /*
     * Busca el registro <b>catalogoParametroLenguaje</b> por su ID en la tabla de <b>CatalogoParametroLenguaje</b>.
     * @param id es el ID del registro a obtener.
     * @return Regresa un objeto <b>CatalogoParametroLenguaje</b> con la información del registro <b>catalogoParametroLenguaje</b>.
     */
    @Override
    public CatalogoParametroLenguaje findById(Integer catalogoParametroLenguajeId) {
        CatalogoParametroLenguaje catalogoParametroLenguaje = catalogoParametroLenguajeDAO.findById(catalogoParametroLenguajeId);
        if (catalogoParametroLenguaje == null) {
            return null;
        } else {
            return resolveDescription(Arrays.asList(catalogoParametroLenguaje)).get(0);
        }
    }

    @Override
    public List<CatalogoParametroLenguaje> findCatalogoParametroBy(Integer catalogoParametroId, Integer organizacionId) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Expression.eq("catalogoParametroId", catalogoParametroId));
        criteria.add(Expression.eq("organizacionId", organizacionId));
        return resolveDescription(findByCriteria(criteria));
    }

    /*
     * Método que hace una consulta a la tabla CatalogoParametroLenguaje y obtiene los registros que coincidan con el objeto <b>catalogoParametroLenguaje</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
     * @param displayedPage Número de página que se desea mostrar al usuario en el widget JQGrid.
     * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
     * @param order Indica por que campo sera ordenada la búsqueda.
     * @param ordenTipo Tipo de ordenado acendente o decendente.
     * @param CatalogoParametroLenguaje objeto que contiene los parámetros a buscar.
     * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    @Override
    public Grid findByCriteria(int displayedPage, int maxResult, String order,
            String ordenTipo, CatalogoParametroLenguaje catalogoParametroLenguaje) {
        Grid grid = new Grid();
        DetachedCriteria criteria = createDetachedCriteria();
        // Auxiliar para busqueda por rango de fechas campo: CreacionFecha
        if (catalogoParametroLenguaje.getCreacionFechaInicial() != null) {
            criteria.add(Expression.ge("creacionFecha", getStartOfDay(catalogoParametroLenguaje.getCreacionFechaInicial())));
        }
        if (catalogoParametroLenguaje.getCreacionFechaFinal() != null) {
            criteria.add(Expression.le("creacionFecha", getEndOfDay(catalogoParametroLenguaje.getCreacionFechaFinal())));
        }
        // Auxiliar para busqueda por rango de fechas campo: ModificacionFecha
        if (catalogoParametroLenguaje.getModificacionFechaInicial() != null) {
            criteria.add(Expression.ge("modificacionFecha", getStartOfDay(catalogoParametroLenguaje.getModificacionFechaInicial())));
        }
        if (catalogoParametroLenguaje.getModificacionFechaFinal() != null) {
            criteria.add(Expression.le("modificacionFecha", getEndOfDay(catalogoParametroLenguaje.getModificacionFechaFinal())));
        }
        criteria.add(Example.create(catalogoParametroLenguaje));
        if (ordenTipo.equals("asc")) {
            criteria.addOrder(Order.asc(order));
        } else {
            criteria.addOrder(Order.desc(order));
        }
        int resultadosTotales = findSizeByCriteria(copy(criteria));
        List<CatalogoParametroLenguaje> catalogoParametroLenguajeList = findByCriteriaLimit(criteria,
                maxResult * (displayedPage - 1), maxResult);
        grid.setGrid(resolveDescription(catalogoParametroLenguajeList));
        grid.setTotal(resultadosTotales);
        grid.setPaginas(lastPage(resultadosTotales, maxResult));
        return grid;
    }

    @Override
    public CatalogoParametroLenguaje findFirst() {
        List<CatalogoParametroLenguaje> catalogoParametroLenguajeList = findByCriteria(createDetachedCriteria());
        if (catalogoParametroLenguajeList.isEmpty()) {
            return null;
        } else {
            return catalogoParametroLenguajeList.get(0);
        }
    }

    public List<CatalogoParametroLenguaje> resolveDescription(List<CatalogoParametroLenguaje> list) {
        if (!list.isEmpty()) {
            List<Usuario> usuarioList = usuarioBss.findAll();
            List<CatalogoParametro> catalogoParametroList = catalogoParametroBss.findAll();
            List<Lenguaje> lenguajeList = lenguajeBss.findAll();
            List<Organizacion> organizacionList = organizacionBss.findAll();
            List<ConfiguracionParametro> estatusList = getParametros("key_estatus");
            List<ConfiguracionParametro> eliminadoList = getParametros("key_eliminado");
            for (CatalogoParametroLenguaje catalogoParametroLenguaje : list) {

                catalogoParametroLenguaje.setCatalogoParametro("");
                if (catalogoParametroLenguaje.getCatalogoParametroId() != null) {
                    for (CatalogoParametro o : catalogoParametroList) {
                        if (o.getCatalogoId().equals(catalogoParametroLenguaje.getCatalogoParametroId())) {
                            catalogoParametroLenguaje.setCatalogoParametro(o.getValor());
                            break;
                        }
                    }
                }

                catalogoParametroLenguaje.setLenguaje("");
                if (catalogoParametroLenguaje.getLenguajeId() != null) {
                    for (Lenguaje o : lenguajeList) {
                        if (o.getLenguajeId().equals(catalogoParametroLenguaje.getLenguajeId())) {
                            catalogoParametroLenguaje.setLenguaje(o.getNombre());
                            break;
                        }
                    }
                }

                catalogoParametroLenguaje.setOrganizacion("");
                if (catalogoParametroLenguaje.getOrganizacionId() != null) {
                    for (Organizacion o : organizacionList) {
                        if (o.getOrganizacionId().equals(catalogoParametroLenguaje.getOrganizacionId())) {
                            catalogoParametroLenguaje.setOrganizacion(o.getNombre());
                            break;
                        }
                    }
                }

                catalogoParametroLenguaje.setUsuarioModificacion("");
                if (catalogoParametroLenguaje.getModificacionUsuario() != null) {
                    for (Usuario o : usuarioList) {
                        if (o.getUsuarioId().equals(catalogoParametroLenguaje.getModificacionUsuario())) {
                            catalogoParametroLenguaje.setUsuarioModificacion(o.getUsuario());
                            break;
                        }
                    }
                }

                catalogoParametroLenguaje.setEstatus("");
                if (catalogoParametroLenguaje.getEstatusId() != null) {
                    for (ConfiguracionParametro o : estatusList) {
                        if (Integer.parseInt(o.getValor()) == catalogoParametroLenguaje.getEstatusId()) {
                            catalogoParametroLenguaje.setEstatus(o.getNombre());
                            break;
                        }
                    }
                }

                catalogoParametroLenguaje.setEliminado("");
                if (catalogoParametroLenguaje.getEliminadoId() != null) {
                    for (ConfiguracionParametro o : eliminadoList) {
                        if (Integer.parseInt(o.getValor()) == catalogoParametroLenguaje.getEliminadoId()) {
                            catalogoParametroLenguaje.setEliminado(o.getNombre());
                            break;
                        }
                    }
                }
            }
        }
        return list;
    }

    public Integer findSizeByCriteria(DetachedCriteria criteria) {
        return catalogoParametroLenguajeDAO.findSizeByCriteria(generalizarCriteria(criteria));
    }

    public List<CatalogoParametroLenguaje> findByCriteriaLimit(DetachedCriteria criteria, Integer from, Integer to) {
        return catalogoParametroLenguajeDAO.findByCriteriaLimit(generalizarCriteria(criteria), from, to);
    }
    /*
     * Método que realiza un búsqueda en la BD obteniendo todos los registros que coincidan con los parámetros
     * definidos en el objeto <b>criteria</b>.
     *
     * @param criteria Es un objeto que contiene los parámetros de búsqueda.
     * @return Regresa una lista de objetos <b>CatalogoParametroLenguaje</b> que coinciden con los parámetros definidos en
     * <b>criteria</b>.
     */

    @Override
    public List<CatalogoParametroLenguaje> findByCriteria(DetachedCriteria criteria) {
        return catalogoParametroLenguajeDAO.findByCriteria(generalizarCriteria(criteria));
    }

    @Override
    public List<CatalogoParametroLenguaje> findByCriteriaIgnorePrivacy(CatalogoParametroLenguaje catalogoParametroLenguaje) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Example.create(catalogoParametroLenguaje));
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        return catalogoParametroLenguajeDAO.findByCriteria(criteria);
    }

    public DetachedCriteria generalizarCriteria(DetachedCriteria criteria) {
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        resolvePrivacy(criteria, CatalogoParametroAction.class);
        return criteria;
    }

    /*
     * Método que obtiene todos los registros de la tabla de <b>CatalogoParametroLenguaje</b>.
     * Este método es generalmente utilizado por los action para obtener las opciones
     * de los select que se llenan mediante struts.
     *
     * @return Regresa todos los registros de la tabla <b>CatalogoParametroLenguaje</b>.
     */
    @Override
    public List<CatalogoParametroLenguaje> findAll() {
        return findByCriteria(createDetachedCriteria().addOrder(Order.asc("etiqueta")));
    }

    /*
     * Método que obtiene todos los registros de la tabla de <b>CatalogoParametroLenguaje</b>.
     * Este método es generalmente utilizado por los action para obtener las opciones
     * de los select que se llenan mediante struts.
     *
     * @return Regresa todos los registros de la tabla <b>CatalogoParametroLenguaje</b>.
     */
    @Override
    public List<CatalogoParametroLenguaje> findActive() {
        return findByCriteria(createDetachedCriteria().add(
                Expression.eq("estatusId", ACTIVO)).addOrder(Order.asc("etiqueta")));
    }

    /*
     * Método que obtiene un objeto DetachedCriteria.
     *
     * @return Regresa un objeto DetachedCriteria.
     */
    @Override
    public DetachedCriteria createDetachedCriteria() {
        return catalogoParametroLenguajeDAO.createDetachedCriteria();
    }

    /*
     * Método que actualiza el Estatus de los ID' contenidos en la lista
     * <b>CatalogoParametroList</b> en la tabla de <b>CatalogoParametroLenguaje</b>.
     * @param userId userId Es el ID del usuario que realizó la operación.
     * @param estatusId Es el ID del estatus al cual se quiere cambiar.
     * @param CatalogoParametroList Es una lista que contiene los registros los cuales se
     * quiere actualizar su Estatus.
     */
    @Override
    public void setEstatus(Integer userId, Integer estatusId, List<Integer> catalogoParametroList) {
        for (Integer catalogoParametroId : catalogoParametroList) {
            CatalogoParametroLenguaje catalogoParametroLenguaje = findById(catalogoParametroId);
            if (estatusId == 1) {
                catalogoParametroLenguaje.setEstatusId(ACTIVO);
            } else {
                catalogoParametroLenguaje.setEstatusId(INACTIVO);
            }
            update(userId, Arrays.asList(catalogoParametroLenguaje));
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
    public String getReportDataTest(String order, String ordenTipo, CatalogoParametroLenguaje catalogoParametroLenguaje) {
        Grid grid = findByCriteria(1, 10000, order, ordenTipo, catalogoParametroLenguaje);
        return addReportDataToSession(grid.getGrid());
    }
    /*
     * Metodos que actualizan los campos <select> en el jsp despues de haber hecho
     * una edición de datos en un popup externo.
     */

    @Override
    public List<CatalogoParametro> getCatalogoParametro() {
        return addDummy(catalogoParametroBss.findActive(), new CatalogoParametro(), CatalogoParametro.class);
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

    public void setCatalogoParametroLenguajeDAO(CatalogoParametroLenguajeDAO catalogoParametroLenguajeDAO) {
        this.catalogoParametroLenguajeDAO = catalogoParametroLenguajeDAO;
    }

    public void setOrganizacionBss(OrganizacionBss organizacionBss) {
        this.organizacionBss = organizacionBss;
    }

    public void setLenguajeBss(LenguajeBss lenguajeBss) {
        this.lenguajeBss = lenguajeBss;
    }

    public void setUsuarioBss(UsuarioBss usuarioBss) {
        this.usuarioBss = usuarioBss;
    }

    public void setConfiguracionParametroBss(ConfiguracionParametroBss configuracionParametroBss) {
        this.configuracionParametroBss = configuracionParametroBss;
    }

    public void setCatalogoParametroBss(CatalogoParametroBss catalogoParametroBss) {
        this.catalogoParametroBss = catalogoParametroBss;
    }

}
