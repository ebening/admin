package com.admon.bss.admon;

import com.admon.bss.BaseBss;
import com.admon.dao.admon.CiudadDAO;
import com.admon.entity.admon.*;
import com.admon.model.admon.CiudadAction;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

public class CiudadBssImpl extends BaseBss implements CiudadBss {

    /* Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase. */
    private CiudadDAO ciudadDAO;
    private ConfiguracionParametroBss configuracionParametroBss;
    private UsuarioBss usuarioBss;
    private PaisBss paisBss;
    private EstadoBss estadoBss;

    public CiudadBssImpl() {
    }

    /*
 * Método que guarda los objetos contenidos en la lista <b>objectList</b> en la tabla de <b>Ciudad</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de objetos que se guardarán en la tabla de <b>Ciudad</b> en la BD.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    @Override
    public List<Integer> save(Integer userId, List<Ciudad> ciudadList) {
        List<Integer> savedList = new ArrayList<Integer>();
        for (Ciudad ciudad : ciudadList) {
            ciudad.setEstatusId(ACTIVO);
            ciudad.setEliminadoId(NOELIMINADO);
            ciudad.setModificacionUsuario(userId);
            ciudad.setCreacionUsuario(userId);
            ciudad.setCreacionFecha(new Timestamp(new Date().getTime()));
            ciudad.setModificacionFecha(new Timestamp(new Date().getTime()));
            savedList.add(ciudadDAO.save(ciudad));
        }
        return savedList;
    }

    /*
 * Método que actualiza la información de <b>ciudad</b> en la tabla de <b>Ciudad</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param ciudadList Lista de registros que se actualizarán en la tabla de <b>Ciudad</b> en la BD.
     */
    @Override
    public List<Integer> update(Integer userId, List<Ciudad> ciudadList) {
        List<Integer> idList = new ArrayList();
        for (Ciudad ciudad : ciudadList) {
            ciudad.setModificacionUsuario(userId);
            ciudad.setModificacionFecha(new Timestamp(new Date().getTime()));
            ciudadDAO.update(ciudad);
            idList.add(ciudad.getCiudadId());
        }
        return idList;
    }

    /*
 * Método que elimina el registro <b>ciudad</b> en la tabla de <b>Ciudad</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param ciudadList Lista de registros que se eliminarán en la tabla de <b>Ciudad</b> en la BD.
     */
    @Override
    public void delete(Integer userId, List<Integer> ciudadIdList) {
        for (Integer ciudadId : ciudadIdList) {
            Ciudad ciudad = findById(ciudadId);
            ciudad.setEliminadoId(ELIMINADO);
            ciudad.setModificacionUsuario(userId);
            ciudad.setModificacionFecha(new Timestamp(new Date().getTime()));
            ciudadDAO.update(ciudad);
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
 * Método que busca el registro <b>ciudad</b> por su NOMBRE en la tabla de <b>Ciudad</b>.
 * @param nombre es el NOMBRE del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>Ciudad</b> con la información de la consulta.
     */
    @Override
    public Ciudad findByName(String nombre) {
        return getFirst(findByCriteria(createDetachedCriteria()
                .add(Expression.eq("nombre", nombre.trim()))));
    }

    /*
 * Busca el registro <b>ciudad</b> por su ID en la tabla de <b>Ciudad</b>.
 * @param id es el ID del registro a obtener.
 * @return Regresa un objeto <b>Ciudad</b> con la información del registro <b>ciudad</b>.
     */
    @Override
    public Ciudad findById(Integer ciudadId) {
        Ciudad ciudad = ciudadDAO.findById(ciudadId);
        if (ciudad == null) {
            return null;
        } else {
            return resolveDescription(Arrays.asList(ciudad)).get(0);
        }
    }

    @Override
    public List<Ciudad> findByIntProperty(String propertyName, Integer value) {
        return resolveDescription(findByCriteria(
                createDetachedCriteria().add(Expression.eq(propertyName, value))));
    }

    /*
 * Método que hace una consulta a la tabla Ciudad y obtiene los registros que coincidan con el objeto <b>ciudad</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
 * @param displayedPage Número de página que se desea mostrar al usuario en el widget JQGrid.
 * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
 * @param order Indica por que campo sera ordenada la búsqueda.
 * @param ordenTipo Tipo de ordenado acendente o decendente.
 * @param Ciudad objeto que contiene los parámetros a buscar.
 * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    @Override
    public Grid findByCriteria(int displayedPage, int maxResult, String order,
            String ordenTipo, Ciudad ciudad) {
        Grid grid = new Grid();
        DetachedCriteria criteria = createDetachedCriteria();
        // Auxiliar para busqueda por rango de fechas campo: CreacionFecha
        if (ciudad.getCreacionFechaInicial() != null) {
            criteria.add(Expression.ge("creacionFecha", getStartOfDay(ciudad.getCreacionFechaInicial())));
        }
        if (ciudad.getCreacionFechaFinal() != null) {
            criteria.add(Expression.le("creacionFecha", getEndOfDay(ciudad.getCreacionFechaFinal())));
        }
        // Auxiliar para busqueda por rango de fechas campo: ModificacionFecha
        if (ciudad.getModificacionFechaInicial() != null) {
            criteria.add(Expression.ge("modificacionFecha", getStartOfDay(ciudad.getModificacionFechaInicial())));
        }
        if (ciudad.getModificacionFechaFinal() != null) {
            criteria.add(Expression.le("modificacionFecha", getEndOfDay(ciudad.getModificacionFechaFinal())));
        }
        criteria.add(Example.create(ciudad));
        if (ordenTipo.equals("asc")) {
            criteria.addOrder(Order.asc(order));
        } else {
            criteria.addOrder(Order.desc(order));
        }
        int resultadosTotales = findSizeByCriteria(copy(criteria));
        List<Ciudad> ciudadList = findByCriteriaLimit(criteria,
                maxResult * (displayedPage - 1), maxResult);
        grid.setGrid(resolveDescription(ciudadList));
        grid.setTotal(resultadosTotales);
        grid.setPaginas(lastPage(resultadosTotales, maxResult));
        return grid;
    }

    @Override
    public Ciudad findFirst() {
        List<Ciudad> ciudadList = findByCriteria(createDetachedCriteria());
        if (ciudadList.isEmpty()) {
            return null;
        } else {
            return ciudadList.get(0);
        }
    }

    public List<Ciudad> resolveDescription(List<Ciudad> list) {
        if (!list.isEmpty()) {
            List<Usuario> usuarioList = usuarioBss.findAll();
            List<Pais> paisList = paisBss.findAll();
            List<Estado> estadoList = estadoBss.findAll();
            List<ConfiguracionParametro> estatusList = getParametros("key_estatus");
            List<ConfiguracionParametro> eliminadoList = getParametros("key_eliminado");
            for (Ciudad ciudad : list) {

                ciudad.setUsuarioModificacion("");
                if (ciudad.getModificacionUsuario() != null) {
                    for (Usuario o : usuarioList) {
                        if (o.getUsuarioId() == ciudad.getModificacionUsuario()) {
                            ciudad.setUsuarioModificacion(o.getUsuario());
                            break;
                        }
                    }
                }

                ciudad.setPais("");
                if (ciudad.getPaisId() != null) {
                    for (Pais o : paisList) {
                        if (o.getPaisId() == ciudad.getPaisId()) {
                            ciudad.setPais(o.getNombre());
                            break;
                        }
                    }
                }

                ciudad.setEstado("");
                if (ciudad.getEstadoId() != null) {
                    for (Estado o : estadoList) {
                        if (o.getEstadoId() == ciudad.getEstadoId()) {
                            ciudad.setEstado(o.getNombre());
                            break;
                        }
                    }
                }

                ciudad.setEstatus("");
                if (ciudad.getEstatusId() != null) {
                    for (ConfiguracionParametro o : estatusList) {
                        if (Integer.parseInt(o.getValor()) == ciudad.getEstatusId()) {
                            ciudad.setEstatus(o.getNombre());
                            break;
                        }
                    }
                }

                ciudad.setEliminado("");
                if (ciudad.getEliminadoId() != null) {
                    for (ConfiguracionParametro o : eliminadoList) {
                        if (Integer.parseInt(o.getValor()) == ciudad.getEliminadoId()) {
                            ciudad.setEliminado(o.getNombre());
                            break;
                        }
                    }
                }
            }
        }
        return list;
    }

    public Integer findSizeByCriteria(DetachedCriteria criteria) {
        return ciudadDAO.findSizeByCriteria(generalizarCriteria(criteria));
    }

    public List<Ciudad> findByCriteriaLimit(DetachedCriteria criteria, Integer from, Integer to) {
        return ciudadDAO.findByCriteriaLimit(generalizarCriteria(criteria), from, to);
    }

    /*
 * Método que realiza un búsqueda en la BD obteniendo todos los registros que coincidan con los parámetros
 * definidos en el objeto <b>criteria</b>.
 *
 * @param criteria Es un objeto que contiene los parámetros de búsqueda.
 * @return Regresa una lista de objetos <b>Ciudad</b> que coinciden con los parámetros definidos en
 * <b>criteria</b>.
     */
    @Override
    public List<Ciudad> findByCriteria(DetachedCriteria criteria) {
        return ciudadDAO.findByCriteria(generalizarCriteria(criteria));
    }

    @Override
    public List<Ciudad> findByCriteriaIgnorePrivacy(Ciudad ciudad) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Example.create(ciudad));
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        return ciudadDAO.findByCriteria(criteria);
    }

    public DetachedCriteria generalizarCriteria(DetachedCriteria criteria) {
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        resolvePrivacy(criteria, CiudadAction.class);
        return criteria;
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>Ciudad</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>Ciudad</b>.
     */
    @Override
    public List<Ciudad> findAll() {
        return findByCriteria(createDetachedCriteria().addOrder(Order.asc("nombre")));
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>Ciudad</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>Ciudad</b>.
     */
    @Override
    public List<Ciudad> findActive() {
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
        return ciudadDAO.createDetachedCriteria();
    }

    /*
 * Método que actualiza el Estatus de los ID' contenidos en la lista
 * <b>ciudadList</b> en la tabla de <b>Ciudad</b>.
 * @param userId userId Es el ID del usuario que realizó la operación.
 * @param estatusId Es el ID del estatus al cual se quiere cambiar.
 * @param ciudadList Es una lista que contiene los registros los cuales se
 * quiere actualizar su Estatus.
     */
    @Override
    public void setEstatus(Integer userId, Integer estatusId, List<Integer> ciudadList) {
        for (Integer ciudadId : ciudadList) {
            Ciudad ciudad = findById(ciudadId);
            if (estatusId == 1) {
                ciudad.setEstatusId(ACTIVO);
            } else {
                ciudad.setEstatusId(INACTIVO);
            }
            update(userId, Arrays.asList(ciudad));
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
    public String getReportDataTest(String order, String ordenTipo, Ciudad ciudad) {
        Grid grid = findByCriteria(1, 10000, order, ordenTipo, ciudad);
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
    public List<Estado> getEstado() {
        return addDummy(estadoBss.findActive(), new Estado(), Estado.class);
    }

    @Override
    public String hasGrid() {
        return hasGrid(CiudadAction.class).toString();
    }

    @Override
    public String isIndividual() {
        return isIndividual(CiudadAction.class).toString();
    }

    @Override
    public String getNombreActionMenu() {
        Pagina pagina = findPageByActionClass(CiudadAction.class);
        if (pagina == null) {
            return "";
        } else {
            return pagina.getNombre();
        }
    }

    // Selects encadenados metodos
    @Override
    public List<Ciudad> filtrarCiudad(int estadoId) {
        return addDummy(findByCriteria(createDetachedCriteria().add(Expression.eq("estadoId", estadoId))
        ), new Ciudad(), Ciudad.class);
    }

    // Selects encadenados
    @Override
    public List<Estado> filtrarEstado(int paisId) {
        return estadoBss.filtrarEstado(paisId);
    }

    /*
 * Inyección de dependencias con Spring, cada
 * referencia definida al inicio de la clase requiere un método
 * Setter.
     */
    public void setCiudadDAO(CiudadDAO ciudadDAO) {
        this.ciudadDAO = ciudadDAO;
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

    public void setEstadoBss(EstadoBss estadoBss) {
        this.estadoBss = estadoBss;
    }

}
