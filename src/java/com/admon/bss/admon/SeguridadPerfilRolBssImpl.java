package com.admon.bss.admon;

import com.admon.bss.BaseBss;
import com.admon.dao.admon.SeguridadPerfilRolDAO;
import com.admon.entity.admon.*;
import com.admon.model.admon.SeguridadPerfilRolAction;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

public class SeguridadPerfilRolBssImpl extends BaseBss implements SeguridadPerfilRolBss {

    /* Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase. */
    private SeguridadPerfilRolDAO seguridadPerfilDAO;
    private ConfiguracionParametroBss configuracionParametroBss;
    private UsuarioBss usuarioBss;

    public SeguridadPerfilRolBssImpl() {
    }

    /*
 * Método que guarda los objetos contenidos en la lista <b>objectList</b> en la tabla de <b>SeguridadPerfilRol</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de objetos que se guardarán en la tabla de <b>SeguridadPerfilRol</b> en la BD.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    @Override
    public List<Integer> save(Integer userId, List<SeguridadPerfilRol> seguridadPerfilRolList) {
        List<Integer> savedList = new ArrayList<Integer>();
        for (SeguridadPerfilRol seguridadPerfilRol : seguridadPerfilRolList) {
            seguridadPerfilRol.setEliminadoId(NOELIMINADO);
            seguridadPerfilRol.setModificacionUsuario(userId);
            seguridadPerfilRol.setCreacionUsuario(userId);
            seguridadPerfilRol.setCreacionFecha(new Timestamp(new Date().getTime()));
            seguridadPerfilRol.setModificacionFecha(new Timestamp(new Date().getTime()));
            savedList.add(seguridadPerfilDAO.save(seguridadPerfilRol));
        }
        return savedList;
    }

    /*
 * Método que actualiza la información de <b>seguridadPerfilRol</b> en la tabla de <b>SeguridadPerfilRol</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param seguridadPerfilRolList Lista de registros que se actualizarán en la tabla de <b>SeguridadPerfilRol</b> en la BD.
     */
    @Override
    public List<Integer> update(Integer userId, List<SeguridadPerfilRol> seguridadPerfilRolList) {
        List<Integer> idList = new ArrayList();
        for (SeguridadPerfilRol seguridadPerfilRol : seguridadPerfilRolList) {
            seguridadPerfilRol.setModificacionUsuario(userId);
            seguridadPerfilRol.setModificacionFecha(new Timestamp(new Date().getTime()));
            seguridadPerfilDAO.update(seguridadPerfilRol);
            idList.add(seguridadPerfilRol.getPerfilRolId());
        }
        return idList;
    }

    /*
 * Método que elimina el registro <b>seguridadPerfilRol</b> en la tabla de <b>SeguridadPerfilRol</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param seguridadPerfilRolList Lista de registros que se eliminarán en la tabla de <b>SeguridadPerfilRol</b> en la BD.
     */
    @Override
    public void delete(Integer userId, List<Integer> seguridadPerfilRolIdList) {
        for (Integer seguridadPerfilRolId : seguridadPerfilRolIdList) {
            SeguridadPerfilRol seguridadPerfilRol = findById(seguridadPerfilRolId);
            seguridadPerfilRol.setEliminadoId(ELIMINADO);
            seguridadPerfilRol.setModificacionUsuario(userId);
            seguridadPerfilRol.setModificacionFecha(new Timestamp(new Date().getTime()));
            seguridadPerfilDAO.update(seguridadPerfilRol);
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
 * Método que busca el registro <b>seguridadPerfilRol</b> por su NOMBRE en la tabla de <b>SeguridadPerfilRol</b>.
 * @param nombre es el NOMBRE del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>SeguridadPerfilRol</b> con la información de la consulta.
     */
    @Override
    public SeguridadPerfilRol findByName(String nombre) {
        return getFirst(findByCriteria(createDetachedCriteria()
                .add(Expression.eq("nombre", nombre.trim()))));
    }

    /*
 * Busca el registro <b>seguridadPerfilRol</b> por su ID en la tabla de <b>SeguridadPerfilRol</b>.
 * @param id es el ID del registro a obtener.
 * @return Regresa un objeto <b>SeguridadPerfilRol</b> con la información del registro <b>SeguridadPerfilRol</b>.
     */
    @Override
    public SeguridadPerfilRol findById(Integer seguridadPerfilRolId) {
        SeguridadPerfilRol seguridadPerfilRol = seguridadPerfilDAO.findById(seguridadPerfilRolId);
        if (seguridadPerfilRol == null) {
            return null;
        } else {
            return resolveDescription(Arrays.asList(seguridadPerfilRol)).get(0);
        }
    }

    @Override
    public List<SeguridadPerfilRol> findByIntProperty(String propertyName, Integer value) {
        return resolveDescription(findByCriteria(
                createDetachedCriteria().add(Expression.eq(propertyName, value))));
    }

    /*
 * Método que hace una consulta a la tabla SeguridadPerfilRol y obtiene los registros que coincidan con el objeto <b>seguridadPerfilRol</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
 * @param displayedPage Número de página que se desea mostrar al usuario en el widget JQGrid.
 * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
 * @param order Indica por que campo sera ordenada la búsqueda.
 * @param ordenTipo Tipo de ordenado acendente o decendente.
 * @param SeguridadPerfilRol objeto que contiene los parámetros a buscar.
 * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    @Override
    public Grid findByCriteria(int displayedPage, int maxResult, String order,
            String ordenTipo, SeguridadPerfilRol seguridadPerfilRol) {
        Grid grid = new Grid();
        DetachedCriteria criteria = createDetachedCriteria();
        // Auxiliar para busqueda por rango de fechas campo: CreacionFecha
        if (seguridadPerfilRol.getCreacionFechaInicial() != null) {
            criteria.add(Expression.ge("creacionFecha", getStartOfDay(seguridadPerfilRol.getCreacionFechaInicial())));
        }
        if (seguridadPerfilRol.getCreacionFechaFinal() != null) {
            criteria.add(Expression.le("creacionFecha", getEndOfDay(seguridadPerfilRol.getCreacionFechaFinal())));
        }
        // Auxiliar para busqueda por rango de fechas campo: ModificacionFecha
        if (seguridadPerfilRol.getModificacionFechaInicial() != null) {
            criteria.add(Expression.ge("modificacionFecha", getStartOfDay(seguridadPerfilRol.getModificacionFechaInicial())));
        }
        if (seguridadPerfilRol.getModificacionFechaFinal() != null) {
            criteria.add(Expression.le("modificacionFecha", getEndOfDay(seguridadPerfilRol.getModificacionFechaFinal())));
        }
        criteria.add(Example.create(seguridadPerfilRol));
        if (ordenTipo.equals("asc")) {
            criteria.addOrder(Order.asc(order));
        } else {
            criteria.addOrder(Order.desc(order));
        }
        int resultadosTotales = findSizeByCriteria(copy(criteria));
        List<SeguridadPerfilRol> seguridadPerfilRolList = findByCriteriaLimit(criteria,
                maxResult * (displayedPage - 1), maxResult);
        grid.setGrid(resolveDescription(seguridadPerfilRolList));
        grid.setTotal(resultadosTotales);
        grid.setPaginas(lastPage(resultadosTotales, maxResult));
        return grid;
    }

    @Override
    public SeguridadPerfilRol findFirst() {
        List<SeguridadPerfilRol> seguridadPerfilRolList = findByCriteria(createDetachedCriteria());
        if (seguridadPerfilRolList.isEmpty()) {
            return null;
        } else {
            return seguridadPerfilRolList.get(0);
        }
    }

    public List<SeguridadPerfilRol> resolveDescription(List<SeguridadPerfilRol> list) {
        if (!list.isEmpty()) {
            List<Usuario> usuarioList = usuarioBss.findAll();
            List<ConfiguracionParametro> estatusList = getParametros("key_estatus");
            List<ConfiguracionParametro> eliminadoList = getParametros("key_eliminado");
            for (SeguridadPerfilRol seguridadPerfilRol : list) {

                seguridadPerfilRol.setUsuarioModificacion("");
                if (seguridadPerfilRol.getModificacionUsuario() != null) {
                    for (Usuario o : usuarioList) {
                        if (o.getUsuarioId() == seguridadPerfilRol.getModificacionUsuario()) {
                            seguridadPerfilRol.setUsuarioModificacion(o.getUsuario());
                            break;
                        }
                    }
                }

                seguridadPerfilRol.setEliminado("");
                if (seguridadPerfilRol.getEliminadoId() != null) {
                    for (ConfiguracionParametro o : eliminadoList) {
                        if (Integer.parseInt(o.getValor()) == seguridadPerfilRol.getEliminadoId()) {
                            seguridadPerfilRol.setEliminado(o.getNombre());
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

    public List<SeguridadPerfilRol> findByCriteriaLimit(DetachedCriteria criteria, Integer from, Integer to) {
        return seguridadPerfilDAO.findByCriteriaLimit(generalizarCriteria(criteria), from, to);
    }

    /*
 * Método que realiza un búsqueda en la BD obteniendo todos los registros que coincidan con los parámetros
 * definidos en el objeto <b>criteria</b>.
 *
 * @param criteria Es un objeto que contiene los parámetros de búsqueda.
 * @return Regresa una lista de objetos <b>SeguridadPerfilRol</b> que coinciden con los parámetros definidos en
 * <b>criteria</b>.
     */
    @Override
    public List<SeguridadPerfilRol> findByCriteria(DetachedCriteria criteria) {
        return seguridadPerfilDAO.findByCriteria(generalizarCriteria(criteria));
    }

    @Override
    public List<SeguridadPerfilRol> findByCriteriaIgnorePrivacy(SeguridadPerfilRol seguridadPerfilRol) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Example.create(seguridadPerfilRol));
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        return seguridadPerfilDAO.findByCriteria(criteria);
    }

    public DetachedCriteria generalizarCriteria(DetachedCriteria criteria) {
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        resolvePrivacy(criteria, SeguridadPerfilRolAction.class);
        return criteria;
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>SeguridadPerfilRol</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>SeguridadPerfilRol</b>.
     */
    @Override
    public List<SeguridadPerfilRol> findAll() {
        return findByCriteria(createDetachedCriteria().addOrder(Order.asc("nombre")));
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>SeguridadPerfilRol</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>SeguridadPerfilRol</b>.
     */
    @Override
    public List<SeguridadPerfilRol> findActive() {
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
    public String getReportDataTest(String order, String ordenTipo, SeguridadPerfilRol seguridadPerfilRol) {
        Grid grid = findByCriteria(1, 10000, order, ordenTipo, seguridadPerfilRol);
        return addReportDataToSession(grid.getGrid());
    }

    /*
 * Metodos que actualizan los campos <select> en el jsp despues de haber hecho
 * una edición de datos en un popup externo.
     */

    @Override
    public String hasGrid() {
        return hasGrid(SeguridadPerfilRolAction.class).toString();
    }

    @Override
    public String isIndividual() {
        return isIndividual(SeguridadPerfilRolAction.class).toString();
    }

    @Override
    public String getNombreActionMenu() {
        Pagina pagina = findPageByActionClass(SeguridadPerfilRolAction.class);
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
    public void setSeguridadPerfilRolDAO(SeguridadPerfilRolDAO seguridadPerfilRolDAO) {
        this.seguridadPerfilDAO = seguridadPerfilRolDAO;
    }

    public void setConfiguracionParametroBss(ConfiguracionParametroBss configuracionParametroBss) {
        this.configuracionParametroBss = configuracionParametroBss;
    }

    public void setUsuarioBss(UsuarioBss usuarioBss) {
        this.usuarioBss = usuarioBss;
    }
}
