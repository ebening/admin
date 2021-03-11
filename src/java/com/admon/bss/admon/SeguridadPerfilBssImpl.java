package com.admon.bss.admon;

import com.admon.bss.BaseBss;
import com.admon.dao.admon.SeguridadPerfilDAO;
import com.admon.entity.admon.*;
import com.admon.model.admon.SeguridadPerfilAction;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

public class SeguridadPerfilBssImpl extends BaseBss implements SeguridadPerfilBss {

    /* Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase. */
    private SeguridadPerfilDAO seguridadPerfilDAO;
    private ConfiguracionParametroBss configuracionParametroBss;
    private UsuarioBss usuarioBss;

    public SeguridadPerfilBssImpl() {
    }

    /*
 * Método que guarda los objetos contenidos en la lista <b>objectList</b> en la tabla de <b>SeguridadPerfil</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de objetos que se guardarán en la tabla de <b>SeguridadPerfil</b> en la BD.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    @Override
    public List<Integer> save(Integer userId, List<SeguridadPerfil> seguridadPerfilList) {
        List<Integer> savedList = new ArrayList<Integer>();
        for (SeguridadPerfil seguridadPerfil : seguridadPerfilList) {
            seguridadPerfil.setEstatusId(ACTIVO);
            seguridadPerfil.setEliminadoId(NOELIMINADO);
            seguridadPerfil.setModificacionUsuario(userId);
            seguridadPerfil.setCreacionUsuario(userId);
            seguridadPerfil.setCreacionFecha(new Timestamp(new Date().getTime()));
            seguridadPerfil.setModificacionFecha(new Timestamp(new Date().getTime()));
            savedList.add(seguridadPerfilDAO.save(seguridadPerfil));
        }
        return savedList;
    }

    /*
 * Método que actualiza la información de <b>seguridadPerfil</b> en la tabla de <b>SeguridadPerfil</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param seguridadPerfilList Lista de registros que se actualizarán en la tabla de <b>SeguridadPerfil</b> en la BD.
     */
    @Override
    public List<Integer> update(Integer userId, List<SeguridadPerfil> seguridadPerfilList) {
        List<Integer> idList = new ArrayList();
        for (SeguridadPerfil seguridadPerfil : seguridadPerfilList) {
            seguridadPerfil.setModificacionUsuario(userId);
            seguridadPerfil.setModificacionFecha(new Timestamp(new Date().getTime()));
            seguridadPerfilDAO.update(seguridadPerfil);
            idList.add(seguridadPerfil.getPerfilId());
        }
        return idList;
    }

    /*
 * Método que elimina el registro <b>seguridadPerfil</b> en la tabla de <b>SeguridadPerfil</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param seguridadPerfilList Lista de registros que se eliminarán en la tabla de <b>SeguridadPerfil</b> en la BD.
     */
    @Override
    public void delete(Integer userId, List<Integer> seguridadPerfilIdList) {
        for (Integer seguridadPerfilId : seguridadPerfilIdList) {
            SeguridadPerfil seguridadPerfil = findById(seguridadPerfilId);
            seguridadPerfil.setEliminadoId(ELIMINADO);
            seguridadPerfil.setModificacionUsuario(userId);
            seguridadPerfil.setModificacionFecha(new Timestamp(new Date().getTime()));
            seguridadPerfilDAO.update(seguridadPerfil);
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
 * Método que busca el registro <b>seguridadPerfil</b> por su NOMBRE en la tabla de <b>SeguridadPerfil</b>.
 * @param nombre es el NOMBRE del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>SeguridadPerfil</b> con la información de la consulta.
     */
    @Override
    public SeguridadPerfil findByName(String nombre) {
        return getFirst(findByCriteria(createDetachedCriteria()
                .add(Expression.eq("nombre", nombre.trim()))));
    }

    /*
 * Busca el registro <b>seguridadPerfil</b> por su ID en la tabla de <b>SeguridadPerfil</b>.
 * @param id es el ID del registro a obtener.
 * @return Regresa un objeto <b>SeguridadPerfil</b> con la información del registro <b>seguridadPerfil</b>.
     */
    @Override
    public SeguridadPerfil findById(Integer seguridadPerfilId) {
        SeguridadPerfil seguridadPerfil = seguridadPerfilDAO.findById(seguridadPerfilId);
        if (seguridadPerfil == null) {
            return null;
        } else {
            return resolveDescription(Arrays.asList(seguridadPerfil)).get(0);
        }
    }

    @Override
    public List<SeguridadPerfil> findByIntProperty(String propertyName, Integer value) {
        return resolveDescription(findByCriteria(
                createDetachedCriteria().add(Expression.eq(propertyName, value))));
    }

    /*
 * Método que hace una consulta a la tabla SeguridadPerfil y obtiene los registros que coincidan con el objeto <b>seguridadPerfil</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
 * @param displayedPage Número de página que se desea mostrar al usuario en el widget JQGrid.
 * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
 * @param order Indica por que campo sera ordenada la búsqueda.
 * @param ordenTipo Tipo de ordenado acendente o decendente.
 * @param SeguridadPerfil objeto que contiene los parámetros a buscar.
 * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    @Override
    public Grid findByCriteria(int displayedPage, int maxResult, String order,
            String ordenTipo, SeguridadPerfil seguridadPerfil) {
        Grid grid = new Grid();
        DetachedCriteria criteria = createDetachedCriteria();
        // Auxiliar para busqueda por rango de fechas campo: CreacionFecha
        if (seguridadPerfil.getCreacionFechaInicial() != null) {
            criteria.add(Expression.ge("creacionFecha", getStartOfDay(seguridadPerfil.getCreacionFechaInicial())));
        }
        if (seguridadPerfil.getCreacionFechaFinal() != null) {
            criteria.add(Expression.le("creacionFecha", getEndOfDay(seguridadPerfil.getCreacionFechaFinal())));
        }
        // Auxiliar para busqueda por rango de fechas campo: ModificacionFecha
        if (seguridadPerfil.getModificacionFechaInicial() != null) {
            criteria.add(Expression.ge("modificacionFecha", getStartOfDay(seguridadPerfil.getModificacionFechaInicial())));
        }
        if (seguridadPerfil.getModificacionFechaFinal() != null) {
            criteria.add(Expression.le("modificacionFecha", getEndOfDay(seguridadPerfil.getModificacionFechaFinal())));
        }
        criteria.add(Example.create(seguridadPerfil));
        if (ordenTipo.equals("asc")) {
            criteria.addOrder(Order.asc(order));
        } else {
            criteria.addOrder(Order.desc(order));
        }
        int resultadosTotales = findSizeByCriteria(copy(criteria));
        List<SeguridadPerfil> seguridadPerfilList = findByCriteriaLimit(criteria,
                maxResult * (displayedPage - 1), maxResult);
        grid.setGrid(resolveDescription(seguridadPerfilList));
        grid.setTotal(resultadosTotales);
        grid.setPaginas(lastPage(resultadosTotales, maxResult));
        return grid;
    }

    @Override
    public SeguridadPerfil findFirst() {
        List<SeguridadPerfil> seguridadPerfilList = findByCriteria(createDetachedCriteria());
        if (seguridadPerfilList.isEmpty()) {
            return null;
        } else {
            return seguridadPerfilList.get(0);
        }
    }

    public List<SeguridadPerfil> resolveDescription(List<SeguridadPerfil> list) {
        if (!list.isEmpty()) {
            List<Usuario> usuarioList = usuarioBss.findAll();
            List<ConfiguracionParametro> estatusList = getParametros("key_estatus");
            List<ConfiguracionParametro> eliminadoList = getParametros("key_eliminado");
            for (SeguridadPerfil seguridadPerfil : list) {

                seguridadPerfil.setUsuarioModificacion("");
                if (seguridadPerfil.getModificacionUsuario() != null) {
                    for (Usuario o : usuarioList) {
                        if (o.getUsuarioId() == seguridadPerfil.getModificacionUsuario()) {
                            seguridadPerfil.setUsuarioModificacion(o.getUsuario());
                            break;
                        }
                    }
                }

                seguridadPerfil.setEstatus("");
                if (seguridadPerfil.getEstatusId() != null) {
                    for (ConfiguracionParametro o : estatusList) {
                        if (Integer.parseInt(o.getValor()) == seguridadPerfil.getEstatusId()) {
                            seguridadPerfil.setEstatus(o.getNombre());
                            break;
                        }
                    }
                }

                seguridadPerfil.setEliminado("");
                if (seguridadPerfil.getEliminadoId() != null) {
                    for (ConfiguracionParametro o : eliminadoList) {
                        if (Integer.parseInt(o.getValor()) == seguridadPerfil.getEliminadoId()) {
                            seguridadPerfil.setEliminado(o.getNombre());
                            break;
                        }
                    }
                }
            }
        }
        return list;
    }

    public Integer findSizeByCriteria(DetachedCriteria criteria) {
        return seguridadPerfilDAO.findSizeByCriteria(generalizarCriteria(criteria));
    }

    public List<SeguridadPerfil> findByCriteriaLimit(DetachedCriteria criteria, Integer from, Integer to) {
        return seguridadPerfilDAO.findByCriteriaLimit(generalizarCriteria(criteria), from, to);
    }

    /*
 * Método que realiza un búsqueda en la BD obteniendo todos los registros que coincidan con los parámetros
 * definidos en el objeto <b>criteria</b>.
 *
 * @param criteria Es un objeto que contiene los parámetros de búsqueda.
 * @return Regresa una lista de objetos <b>SeguridadPerfil</b> que coinciden con los parámetros definidos en
 * <b>criteria</b>.
     */
    @Override
    public List<SeguridadPerfil> findByCriteria(DetachedCriteria criteria) {
        return seguridadPerfilDAO.findByCriteria(generalizarCriteria(criteria));
    }

    @Override
    public List<SeguridadPerfil> findByCriteriaIgnorePrivacy(SeguridadPerfil seguridadPerfil) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Example.create(seguridadPerfil));
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        return seguridadPerfilDAO.findByCriteria(criteria);
    }

    public DetachedCriteria generalizarCriteria(DetachedCriteria criteria) {
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        resolvePrivacy(criteria, SeguridadPerfilAction.class);
        return criteria;
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>SeguridadPerfil</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>SeguridadPerfil</b>.
     */
    @Override
    public List<SeguridadPerfil> findAll() {
        return findByCriteria(createDetachedCriteria().addOrder(Order.asc("nombre")));
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>SeguridadPerfil</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>SeguridadPerfil</b>.
     */
    @Override
    public List<SeguridadPerfil> findActive() {
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
        return seguridadPerfilDAO.createDetachedCriteria();
    }

    /*
 * Método que actualiza el Estatus de los ID' contenidos en la lista
 * <b>seguridadPerfilList</b> en la tabla de <b>SeguridadPerfil</b>.
 * @param userId userId Es el ID del usuario que realizó la operación.
 * @param estatusId Es el ID del estatus al cual se quiere cambiar.
 * @param seguridadPerfilList Es una lista que contiene los registros los cuales se
 * quiere actualizar su Estatus.
     */
    @Override
    public void setEstatus(Integer userId, Integer estatusId, List<Integer> seguridadPerfilList) {
        for (Integer seguridadPerfilId : seguridadPerfilList) {
            SeguridadPerfil seguridadPerfil = findById(seguridadPerfilId);
            if (estatusId == 1) {
                seguridadPerfil.setEstatusId(ACTIVO);
            } else {
                seguridadPerfil.setEstatusId(INACTIVO);
            }
            update(userId, Arrays.asList(seguridadPerfil));
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
    public String getReportDataTest(String order, String ordenTipo, SeguridadPerfil seguridadPerfil) {
        Grid grid = findByCriteria(1, 10000, order, ordenTipo, seguridadPerfil);
        return addReportDataToSession(grid.getGrid());
    }

    /*
 * Metodos que actualizan los campos <select> en el jsp despues de haber hecho
 * una edición de datos en un popup externo.
     */

    @Override
    public String hasGrid() {
        return hasGrid(SeguridadPerfilAction.class).toString();
    }

    @Override
    public String isIndividual() {
        return isIndividual(SeguridadPerfilAction.class).toString();
    }

    @Override
    public String getNombreActionMenu() {
        Pagina pagina = findPageByActionClass(SeguridadPerfilAction.class);
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

    /**
     * @param seguridadPerfilDAO the seguridadPerfilDAO to set
     */
    public void setSeguridadPerfilDAO(SeguridadPerfilDAO seguridadPerfilDAO) {
        this.seguridadPerfilDAO = seguridadPerfilDAO;
    }

    /**
     * @param configuracionParametroBss the configuracionParametroBss to set
     */
    public void setConfiguracionParametroBss(ConfiguracionParametroBss configuracionParametroBss) {
        this.configuracionParametroBss = configuracionParametroBss;
    }

    /**
     * @param usuarioBss the usuarioBss to set
     */
    public void setUsuarioBss(UsuarioBss usuarioBss) {
        this.usuarioBss = usuarioBss;
    }
  
}
