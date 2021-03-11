package com.admon.bss.admon;

import com.admon.bss.BaseBss;
import com.admon.dao.admon.NombreContraDAO;
import com.admon.entity.admon.*;
import com.admon.model.admon.NombreContraAction;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

public class NombreContraBssImpl extends BaseBss implements NombreContraBss {

    /* Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase. */
    private NombreContraDAO nombreContraDAO;
    private ConfiguracionParametroBss configuracionParametroBss;
    private UsuarioBss usuarioBss;
    private ClasificacionContraBss clasificacionContraBss;
    private SubclasificacionContraBss subclasificacionContraBss;

    public NombreContraBssImpl() {
    }

    /*
 * Método que guarda los objetos contenidos en la lista <b>objectList</b> en la tabla de <b>NombreContra</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de objetos que se guardarán en la tabla de <b>NombreContra</b> en la BD.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    @Override
    public List<Integer> save(Integer userId, List<NombreContra> nombreContraList) {
        List<Integer> savedList = new ArrayList<Integer>();
        for (NombreContra nombreContra : nombreContraList) {
            nombreContra.setEstatusId(ACTIVO);
            nombreContra.setEliminadoId(NOELIMINADO);
            nombreContra.setModificacionUsuario(userId);
            nombreContra.setCreacionUsuario(userId);
            nombreContra.setCreacionFecha(new Timestamp(new Date().getTime()));
            nombreContra.setModificacionFecha(new Timestamp(new Date().getTime()));
            savedList.add(nombreContraDAO.save(nombreContra));
        }
        return savedList;
    }

    /*
 * Método que actualiza la información de <b>nombreContra</b> en la tabla de <b>NombreContra</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param nombreContraList Lista de registros que se actualizarán en la tabla de <b>NombreContra</b> en la BD.
     */
    @Override
    public List<Integer> update(Integer userId, List<NombreContra> nombreContraList) {
        List<Integer> idList = new ArrayList();
        for (NombreContra nombreContra : nombreContraList) {
            nombreContra.setModificacionUsuario(userId);
            nombreContra.setModificacionFecha(new Timestamp(new Date().getTime()));
            nombreContraDAO.update(nombreContra);
            idList.add(nombreContra.getNombreContraId());
        }
        return idList;
    }

    /*
 * Método que elimina el registro <b>nombreContra</b> en la tabla de <b>NombreContra</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param nombreContraList Lista de registros que se eliminarán en la tabla de <b>NombreContra</b> en la BD.
     */
    @Override
    public void delete(Integer userId, List<Integer> nombreContraIdList) {
        for (Integer nombreContraId : nombreContraIdList) {
            NombreContra nombreContra = findById(nombreContraId);
            nombreContra.setEliminadoId(ELIMINADO);
            nombreContra.setModificacionUsuario(userId);
            nombreContra.setModificacionFecha(new Timestamp(new Date().getTime()));
            nombreContraDAO.update(nombreContra);
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
 * Método que busca el registro <b>nombreContra</b> por su NOMBRE en la tabla de <b>NombreContra</b>.
 * @param nombre es el NOMBRE del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>NombreContra</b> con la información de la consulta.
     */
    @Override
    public NombreContra findByName(String nombre) {
        return getFirst(findByCriteria(createDetachedCriteria()
                .add(Expression.eq("nombre", nombre.trim()))));
    }

    /*
 * Busca el registro <b>nombreContra</b> por su ID en la tabla de <b>NombreContra</b>.
 * @param id es el ID del registro a obtener.
 * @return Regresa un objeto <b>NombreContra</b> con la información del registro <b>nombreContra</b>.
     */
    @Override
    public NombreContra findById(Integer nombreContraId) {
        NombreContra nombreContra = nombreContraDAO.findById(nombreContraId);
        if (nombreContra == null) {
            return null;
        } else {
            return resolveDescription(Arrays.asList(nombreContra)).get(0);
        }
    }

    @Override
    public List<NombreContra> findByIntProperty(String propertyName, Integer value) {
        return resolveDescription(findByCriteria(
                createDetachedCriteria().add(Expression.eq(propertyName, value))));
    }

    /*
 * Método que hace una consulta a la tabla NombreContra y obtiene los registros que coincidan con el objeto <b>nombreContra</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
 * @param displayedPage Número de página que se desea mostrar al usuario en el widget JQGrid.
 * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
 * @param order Indica por que campo sera ordenada la búsqueda.
 * @param ordenTipo Tipo de ordenado acendente o decendente.
 * @param NombreContra objeto que contiene los parámetros a buscar.
 * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    @Override
    public Grid findByCriteria(int displayedPage, int maxResult, String order,
            String ordenTipo, NombreContra nombreContra) {
        Grid grid = new Grid();
        DetachedCriteria criteria = createDetachedCriteria();
        // Auxiliar para busqueda por rango de fechas campo: CreacionFecha
        if (nombreContra.getCreacionFechaInicial() != null) {
            criteria.add(Expression.ge("creacionFecha", getStartOfDay(nombreContra.getCreacionFechaInicial())));
        }
        if (nombreContra.getCreacionFechaFinal() != null) {
            criteria.add(Expression.le("creacionFecha", getEndOfDay(nombreContra.getCreacionFechaFinal())));
        }
        // Auxiliar para busqueda por rango de fechas campo: ModificacionFecha
        if (nombreContra.getModificacionFechaInicial() != null) {
            criteria.add(Expression.ge("modificacionFecha", getStartOfDay(nombreContra.getModificacionFechaInicial())));
        }
        if (nombreContra.getModificacionFechaFinal() != null) {
            criteria.add(Expression.le("modificacionFecha", getEndOfDay(nombreContra.getModificacionFechaFinal())));
        }
        criteria.add(Example.create(nombreContra));
        if (ordenTipo.equals("asc")) {
            criteria.addOrder(Order.asc(order));
        } else {
            criteria.addOrder(Order.desc(order));
        }
        int resultadosTotales = findSizeByCriteria(copy(criteria));
        List<NombreContra> nombreContraList = findByCriteriaLimit(criteria,
                maxResult * (displayedPage - 1), maxResult);
        grid.setGrid(resolveDescription(nombreContraList));
        grid.setTotal(resultadosTotales);
        grid.setPaginas(lastPage(resultadosTotales, maxResult));
        return grid;
    }

    @Override
    public NombreContra findFirst() {
        List<NombreContra> nombreContraList = findByCriteria(createDetachedCriteria());
        if (nombreContraList.isEmpty()) {
            return null;
        } else {
            return nombreContraList.get(0);
        }
    }

    public List<NombreContra> resolveDescription(List<NombreContra> list) {
        if (!list.isEmpty()) {
            List<Usuario> usuarioList = usuarioBss.findAll();
            List<ClasificacionContra> clasificacionContraList = clasificacionContraBss.findAll();
            List<SubclasificacionContra> subclasificacionContraList = subclasificacionContraBss.findAll();
            List<ConfiguracionParametro> tipoValorList = getParametros("key_tipovalor");
            List<ConfiguracionParametro> estatusList = getParametros("key_estatus");
            List<ConfiguracionParametro> eliminadoList = getParametros("key_eliminado");
            for (NombreContra nombreContra : list) {

                nombreContra.setUsuarioModificacion("");
                if (nombreContra.getModificacionUsuario() != null) {
                    for (Usuario o : usuarioList) {
                        if (o.getUsuarioId() == nombreContra.getModificacionUsuario()) {
                            nombreContra.setUsuarioModificacion(o.getUsuario());
                            break;
                        }
                    }
                }

                nombreContra.setClasificacionContra("");
                if (nombreContra.getClasificacionContraId() != null) {
                    for (ClasificacionContra o : clasificacionContraList) {
                        if (o.getClasificacionContraId() == nombreContra.getClasificacionContraId()) {
                            nombreContra.setClasificacionContra(o.getNombre());
                            break;
                        }
                    }
                }

                nombreContra.setSubclasificacionContra("");
                if (nombreContra.getSubclasificacionContraId() != null) {
                    for (SubclasificacionContra o : subclasificacionContraList) {
                        if (o.getSubclasificacionContraId() == nombreContra.getSubclasificacionContraId()) {
                            nombreContra.setSubclasificacionContra(o.getNombre());
                            break;
                        }
                    }
                }

                nombreContra.setTipoValor("");
                if (nombreContra.getTipoValorId() != null) {
                    for (ConfiguracionParametro o : tipoValorList) {
                        if (Integer.parseInt(o.getValor()) == nombreContra.getTipoValorId()) {
                            nombreContra.setTipoValor(o.getNombre());
                            break;
                        }
                    }
                }

                nombreContra.setEstatus("");
                if (nombreContra.getEstatusId() != null) {
                    for (ConfiguracionParametro o : estatusList) {
                        if (Integer.parseInt(o.getValor()) == nombreContra.getEstatusId()) {
                            nombreContra.setEstatus(o.getNombre());
                            break;
                        }
                    }
                }

                nombreContra.setEliminado("");
                if (nombreContra.getEliminadoId() != null) {
                    for (ConfiguracionParametro o : eliminadoList) {
                        if (Integer.parseInt(o.getValor()) == nombreContra.getEliminadoId()) {
                            nombreContra.setEliminado(o.getNombre());
                            break;
                        }
                    }
                }
            }
        }
        return list;
    }

    public Integer findSizeByCriteria(DetachedCriteria criteria) {
        return nombreContraDAO.findSizeByCriteria(generalizarCriteria(criteria));
    }

    public List<NombreContra> findByCriteriaLimit(DetachedCriteria criteria, Integer from, Integer to) {
        return nombreContraDAO.findByCriteriaLimit(generalizarCriteria(criteria), from, to);
    }

    /*
 * Método que realiza un búsqueda en la BD obteniendo todos los registros que coincidan con los parámetros
 * definidos en el objeto <b>criteria</b>.
 *
 * @param criteria Es un objeto que contiene los parámetros de búsqueda.
 * @return Regresa una lista de objetos <b>NombreContra</b> que coinciden con los parámetros definidos en
 * <b>criteria</b>.
     */
    @Override
    public List<NombreContra> findByCriteria(DetachedCriteria criteria) {
        return nombreContraDAO.findByCriteria(generalizarCriteria(criteria));
    }

    @Override
    public List<NombreContra> findByCriteriaIgnorePrivacy(NombreContra nombreContra) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Example.create(nombreContra));
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        return nombreContraDAO.findByCriteria(criteria);
    }

    public DetachedCriteria generalizarCriteria(DetachedCriteria criteria) {
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        resolvePrivacy(criteria, NombreContraAction.class);
        return criteria;
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>NombreContra</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>NombreContra</b>.
     */
    @Override
    public List<NombreContra> findAll() {
        return findByCriteria(createDetachedCriteria().addOrder(Order.asc("nombre")));
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>NombreContra</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>NombreContra</b>.
     */
    @Override
    public List<NombreContra> findActive() {
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
        return nombreContraDAO.createDetachedCriteria();
    }

    /*
 * Método que actualiza el Estatus de los ID' contenidos en la lista
 * <b>nombreContraList</b> en la tabla de <b>NombreContra</b>.
 * @param userId userId Es el ID del usuario que realizó la operación.
 * @param estatusId Es el ID del estatus al cual se quiere cambiar.
 * @param nombreContraList Es una lista que contiene los registros los cuales se
 * quiere actualizar su Estatus.
     */
    @Override
    public void setEstatus(Integer userId, Integer estatusId, List<Integer> nombreContraList) {
        for (Integer nombreContraId : nombreContraList) {
            NombreContra nombreContra = findById(nombreContraId);
            if (estatusId == 1) {
                nombreContra.setEstatusId(ACTIVO);
            } else {
                nombreContra.setEstatusId(INACTIVO);
            }
            update(userId, Arrays.asList(nombreContra));
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
    public String getReportDataTest(String order, String ordenTipo, NombreContra nombreContra) {
        Grid grid = findByCriteria(1, 10000, order, ordenTipo, nombreContra);
        return addReportDataToSession(grid.getGrid());
    }

    /*
 * Metodos que actualizan los campos <select> en el jsp despues de haber hecho
 * una edición de datos en un popup externo.
     */
    @Override
    public List<ClasificacionContra> getClasificacionContra() {
        return addDummy(clasificacionContraBss.findActive(), new ClasificacionContra(), ClasificacionContra.class);
    }

    @Override
    public List<SubclasificacionContra> getSubclasificacionContra() {
        return addDummy(subclasificacionContraBss.findActive(), new SubclasificacionContra(), SubclasificacionContra.class);
    }

    @Override
    public String hasGrid() {
        return hasGrid(NombreContraAction.class).toString();
    }

    @Override
    public String isIndividual() {
        return isIndividual(NombreContraAction.class).toString();
    }

    @Override
    public String getNombreActionMenu() {
        Pagina pagina = findPageByActionClass(NombreContraAction.class);
        if (pagina == null) {
            return "";
        } else {
            return pagina.getNombre();
        }
    }

    // Selects encadenados metodos
    @Override
    public List<NombreContra> filtrarNombreContra(int subclasificacionContraId) {
        return addDummy(findByCriteria(createDetachedCriteria().add(Expression.eq("subclasificacionContraId", subclasificacionContraId))
        ), new NombreContra(), NombreContra.class);
    }

    // Selects encadenados
    @Override
    public List<SubclasificacionContra> filtrarSubclasificacionContra(int clasificacionContraId) {
        return subclasificacionContraBss.filtrarSubclasificacionContra(clasificacionContraId);
    }

    /*
 * Inyección de dependencias con Spring, cada
 * referencia definida al inicio de la clase requiere un método
 * Setter.
     */
    public void setNombreContraDAO(NombreContraDAO nombreContraDAO) {
        this.nombreContraDAO = nombreContraDAO;
    }

    public void setConfiguracionParametroBss(ConfiguracionParametroBss configuracionParametroBss) {
        this.configuracionParametroBss = configuracionParametroBss;
    }

    public void setUsuarioBss(UsuarioBss usuarioBss) {
        this.usuarioBss = usuarioBss;
    }

    public void setClasificacionContraBss(ClasificacionContraBss clasificacionContraBss) {
        this.clasificacionContraBss = clasificacionContraBss;
    }

    public void setSubclasificacionContraBss(SubclasificacionContraBss subclasificacionContraBss) {
        this.subclasificacionContraBss = subclasificacionContraBss;
    }

}
