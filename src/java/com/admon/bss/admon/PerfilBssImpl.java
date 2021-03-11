package com.admon.bss.admon;

import com.admon.bss.BaseBss;
import com.admon.dao.admon.PerfilDAO;
import com.admon.entity.admon.*;
import com.admon.model.admon.PerfilAction;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

public class PerfilBssImpl extends BaseBss implements PerfilBss {

    /* Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase. */
    private PerfilDAO perfilDAO;
    private ConfiguracionParametroBss configuracionParametroBss;
    private UsuarioBss usuarioBss;
    // Elementos de struts2 para el JSP (Embebido)
    private PerfilPaginaBss perfilPaginaBss;

    public PerfilBssImpl() {
    }

    /*
 * Método que guarda los objetos contenidos en la lista <b>objectList</b> en la tabla de <b>Perfil</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de objetos que se guardarán en la tabla de <b>Perfil</b> en la BD.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    @Override
    public List<Integer> save(Integer userId, List<Perfil> perfilList) {
        List<Integer> savedList = new ArrayList<Integer>();
        for (Perfil perfil : perfilList) {
            perfil.setEstatusId(ACTIVO);
            perfil.setEliminadoId(NOELIMINADO);
            perfil.setModificacionUsuario(userId);
            perfil.setCreacionUsuario(userId);
            perfil.setCreacionFecha(new Timestamp(new Date().getTime()));
            perfil.setModificacionFecha(new Timestamp(new Date().getTime()));
            savedList.add(perfilDAO.save(perfil));
        }
        return savedList;
    }

    @Override
    public void savePerfilPagina(int userId, int perfilId,
            List<PerfilPagina> detalleNuevoList) {
        List<PerfilPagina> detalleActualList
                = perfilPaginaBss.findByIntProperty("perfilId",
                        perfilId);
        // Delete
        for (PerfilPagina detalle : detalleActualList) {
            boolean isDeletable = true;
            for (PerfilPagina detalleNuevo : detalleNuevoList) {
                if (detalle.getPerfilPaginaId()
                        == detalleNuevo.getPerfilPaginaId()) {
                    isDeletable = false;
                }
            }
            if (isDeletable) {
                perfilPaginaBss.delete(userId,
                        Arrays.asList(detalle.getPerfilPaginaId()));
            }
        }
        // Save y update
        for (PerfilPagina detalle : detalleNuevoList) {
            if (detalle.getPerfilPaginaId() == 0) {
//perfilPaginaBss.save(userId, Arrays.asList(detalle));
            } else {
//perfilPaginaBss.update(userId, Arrays.asList(detalle));
            }
        }
    }

    /*
 * Método que actualiza la información de <b>perfil</b> en la tabla de <b>Perfil</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param perfilList Lista de registros que se actualizarán en la tabla de <b>Perfil</b> en la BD.
     */
    @Override
    public List<Integer> update(Integer userId, List<Perfil> perfilList) {
        List<Integer> idList = new ArrayList();
        for (Perfil perfil : perfilList) {
            perfil.setModificacionUsuario(userId);
            perfil.setModificacionFecha(new Timestamp(new Date().getTime()));
            perfilDAO.update(perfil);
            idList.add(perfil.getPerfilId());
        }
        return idList;
    }

    /*
 * Método que elimina el registro <b>perfil</b> en la tabla de <b>Perfil</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param perfilList Lista de registros que se eliminarán en la tabla de <b>Perfil</b> en la BD.
     */
    @Override
    public void delete(Integer userId, List<Integer> perfilIdList) {
        for (Integer perfilId : perfilIdList) {
            Perfil perfil = findById(perfilId);
            perfil.setEliminadoId(ELIMINADO);
            perfil.setModificacionUsuario(userId);
            perfil.setModificacionFecha(new Timestamp(new Date().getTime()));
            perfilDAO.update(perfil);
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
 * Método que busca el registro <b>perfil</b> por su NOMBRE en la tabla de <b>Perfil</b>.
 * @param nombre es el NOMBRE del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>Perfil</b> con la información de la consulta.
     */
    @Override
    public Perfil findByName(String nombre) {
        return getFirst(findByCriteria(createDetachedCriteria()
                .add(Expression.eq("nombre", nombre.trim()))));
    }

    /*
 * Busca el registro <b>perfil</b> por su ID en la tabla de <b>Perfil</b>.
 * @param id es el ID del registro a obtener.
 * @return Regresa un objeto <b>Perfil</b> con la información del registro <b>perfil</b>.
     */
    @Override
    public Perfil findById(Integer perfilId) {
        Perfil perfil = perfilDAO.findById(perfilId);
        if (perfil == null) {
            return null;
        } else {
            return resolveDescription(Arrays.asList(perfil)).get(0);
        }
    }

    @Override
    public List<Perfil> findByIntProperty(String propertyName, Integer value) {
        return resolveDescription(findByCriteria(
                createDetachedCriteria().add(Expression.eq(propertyName, value))));
    }

    @Override
    public List<PerfilPagina> findPerfilPaginaByIntProperty(String propertyName, Integer value) {
        return perfilPaginaBss.findByIntProperty(propertyName, value);
    }

    /*
 * Método que hace una consulta a la tabla Perfil y obtiene los registros que coincidan con el objeto <b>perfil</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
 * @param displayedPage Número de página que se desea mostrar al usuario en el widget JQGrid.
 * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
 * @param order Indica por que campo sera ordenada la búsqueda.
 * @param ordenTipo Tipo de ordenado acendente o decendente.
 * @param Perfil objeto que contiene los parámetros a buscar.
 * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    @Override
    public Grid findByCriteria(int displayedPage, int maxResult, String order,
            String ordenTipo, Perfil perfil) {
        Grid grid = new Grid();
        DetachedCriteria criteria = createDetachedCriteria();
        // Auxiliar para busqueda por rango de fechas campo: CreacionFecha
        if (perfil.getCreacionFechaInicial() != null) {
            criteria.add(Expression.ge("creacionFecha", getStartOfDay(perfil.getCreacionFechaInicial())));
        }
        if (perfil.getCreacionFechaFinal() != null) {
            criteria.add(Expression.le("creacionFecha", getEndOfDay(perfil.getCreacionFechaFinal())));
        }
        // Auxiliar para busqueda por rango de fechas campo: ModificacionFecha
        if (perfil.getModificacionFechaInicial() != null) {
            criteria.add(Expression.ge("modificacionFecha", getStartOfDay(perfil.getModificacionFechaInicial())));
        }
        if (perfil.getModificacionFechaFinal() != null) {
            criteria.add(Expression.le("modificacionFecha", getEndOfDay(perfil.getModificacionFechaFinal())));
        }
        criteria.add(Example.create(perfil));
        if (ordenTipo.equals("asc")) {
            criteria.addOrder(Order.asc(order));
        } else {
            criteria.addOrder(Order.desc(order));
        }
        int resultadosTotales = findSizeByCriteria(copy(criteria));
        List<Perfil> perfilList = findByCriteriaLimit(criteria,
                maxResult * (displayedPage - 1), maxResult);
        grid.setGrid(resolveDescription(perfilList));
        grid.setTotal(resultadosTotales);
        grid.setPaginas(lastPage(resultadosTotales, maxResult));
        return grid;
    }

    @Override
    public Perfil findFirst() {
        List<Perfil> perfilList = findByCriteria(createDetachedCriteria());
        if (perfilList.isEmpty()) {
            return null;
        } else {
            return perfilList.get(0);
        }
    }

    public List<Perfil> resolveDescription(List<Perfil> list) {
        if (!list.isEmpty()) {
            List<Usuario> usuarioList = usuarioBss.findAll();
            List<ConfiguracionParametro> estatusList = getParametros("key_estatus");
            List<ConfiguracionParametro> eliminadoList = getParametros("key_eliminado");
            for (Perfil perfil : list) {

                perfil.setUsuarioModificacion("");
                if (perfil.getModificacionUsuario() != null) {
                    for (Usuario o : usuarioList) {
                        if (o.getUsuarioId() == perfil.getModificacionUsuario()) {
                            perfil.setUsuarioModificacion(o.getUsuario());
                            break;
                        }
                    }
                }

                perfil.setEstatus("");
                if (perfil.getEstatusId() != null) {
                    for (ConfiguracionParametro o : estatusList) {
                        if (Integer.parseInt(o.getValor()) == perfil.getEstatusId()) {
                            perfil.setEstatus(o.getNombre());
                            break;
                        }
                    }
                }

                perfil.setEliminado("");
                if (perfil.getEliminadoId() != null) {
                    for (ConfiguracionParametro o : eliminadoList) {
                        if (Integer.parseInt(o.getValor()) == perfil.getEliminadoId()) {
                            perfil.setEliminado(o.getNombre());
                            break;
                        }
                    }
                }
            }
        }
        return list;
    }

    public Integer findSizeByCriteria(DetachedCriteria criteria) {
        return perfilDAO.findSizeByCriteria(generalizarCriteria(criteria));
    }

    public List<Perfil> findByCriteriaLimit(DetachedCriteria criteria, Integer from, Integer to) {
        return perfilDAO.findByCriteriaLimit(generalizarCriteria(criteria), from, to);
    }

    /*
 * Método que realiza un búsqueda en la BD obteniendo todos los registros que coincidan con los parámetros
 * definidos en el objeto <b>criteria</b>.
 *
 * @param criteria Es un objeto que contiene los parámetros de búsqueda.
 * @return Regresa una lista de objetos <b>Perfil</b> que coinciden con los parámetros definidos en
 * <b>criteria</b>.
     */
    @Override
    public List<Perfil> findByCriteria(DetachedCriteria criteria) {
        return perfilDAO.findByCriteria(generalizarCriteria(criteria));
    }

    @Override
    public List<Perfil> findByCriteriaIgnorePrivacy(Perfil perfil) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Example.create(perfil));
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        return perfilDAO.findByCriteria(criteria);
    }

    public DetachedCriteria generalizarCriteria(DetachedCriteria criteria) {
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        resolvePrivacy(criteria, PerfilAction.class);
        return criteria;
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>Perfil</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>Perfil</b>.
     */
    @Override
    public List<Perfil> findAll() {
        return findByCriteria(createDetachedCriteria().addOrder(Order.asc("nombre")));
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>Perfil</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>Perfil</b>.
     */
    @Override
    public List<Perfil> findActive() {
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
        return perfilDAO.createDetachedCriteria();
    }

    /*
 * Método que actualiza el Estatus de los ID' contenidos en la lista
 * <b>perfilList</b> en la tabla de <b>Perfil</b>.
 * @param userId userId Es el ID del usuario que realizó la operación.
 * @param estatusId Es el ID del estatus al cual se quiere cambiar.
 * @param perfilList Es una lista que contiene los registros los cuales se
 * quiere actualizar su Estatus.
     */
    @Override
    public void setEstatus(Integer userId, Integer estatusId, List<Integer> perfilList) {
        for (Integer perfilId : perfilList) {
            Perfil perfil = findById(perfilId);
            if (estatusId == 1) {
                perfil.setEstatusId(ACTIVO);
            } else {
                perfil.setEstatusId(INACTIVO);
            }
            update(userId, Arrays.asList(perfil));
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
    public String getReportDataTest(String order, String ordenTipo, Perfil perfil) {
        Grid grid = findByCriteria(1, 10000, order, ordenTipo, perfil);
        return addReportDataToSession(grid.getGrid());
    }

    /*
 * Metodos que actualizan los campos <select> en el jsp despues de haber hecho
 * una edición de datos en un popup externo.
     */
    // <editor-fold defaultstate="collapsed" desc="Getters Detalle(s)">
    @Override
    public List<Perfil> getPerfilPaginaPerfil() {
        return perfilPaginaBss.getPerfil();
    }

    @Override
    public List<Pagina> getPerfilPaginaPagina() {
        return perfilPaginaBss.getPagina();
    }
    // </editor-fold>

    @Override
    public String hasGrid() {
        return hasGrid(PerfilAction.class).toString();
    }

    @Override
    public String isIndividual() {
        return isIndividual(PerfilAction.class).toString();
    }

    @Override
    public String hasGridPerfilPagina() {
        return perfilPaginaBss.hasGrid().toString();
    }

    @Override
    public String isIndividualPerfilPagina() {
        return perfilPaginaBss.isIndividual().toString();
    }

    @Override
    public String getNombreActionMenu() {
        Pagina pagina = findPageByActionClass(PerfilAction.class);
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
    public void setPerfilDAO(PerfilDAO perfilDAO) {
        this.perfilDAO = perfilDAO;
    }

    public void setConfiguracionParametroBss(ConfiguracionParametroBss configuracionParametroBss) {
        this.configuracionParametroBss = configuracionParametroBss;
    }

    public void setUsuarioBss(UsuarioBss usuarioBss) {
        this.usuarioBss = usuarioBss;
    }
    // Inyeccion de dependencias (Embebido)

    public void setPerfilPaginaBss(PerfilPaginaBss perfilPaginaBss) {
        this.perfilPaginaBss = perfilPaginaBss;
    }

}
