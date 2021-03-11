package com.admon.bss.admon;

import com.admon.bss.BaseBss;
import com.admon.dao.admon.ComisionDAO;
import com.admon.entity.admon.*;
import com.admon.model.admon.ComisionAction;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

public class ComisionBssImpl extends BaseBss implements ComisionBss {

    /* Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase. */
    private ComisionDAO comisionDAO;
    private ConfiguracionParametroBss configuracionParametroBss;
    private UsuarioBss usuarioBss;
    private ClasificacionContraBss clasificacionContraBss;
    private SubclasificacionContraBss subclasificacionContraBss;
    private NombreContraBss nombreContraBss;

    public ComisionBssImpl() {
    }

    /*
 * Método que guarda los objetos contenidos en la lista <b>objectList</b> en la tabla de <b>Comision</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de objetos que se guardarán en la tabla de <b>Comision</b> en la BD.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    @Override
    public List<Integer> save(Integer userId, List<Comision> comisionList) {
        List<Integer> savedList = new ArrayList<Integer>();
        for (Comision comision : comisionList) {
            comision.setEstatusId(ACTIVO);
            comision.setEliminadoId(NOELIMINADO);
            comision.setModificacionUsuario(userId);
            comision.setCreacionUsuario(userId);
            comision.setCreacionFecha(new Timestamp(new Date().getTime()));
            comision.setModificacionFecha(new Timestamp(new Date().getTime()));
            savedList.add(comisionDAO.save(comision));
        }
        return savedList;
    }

    /*
 * Método que actualiza la información de <b>comision</b> en la tabla de <b>Comision</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param comisionList Lista de registros que se actualizarán en la tabla de <b>Comision</b> en la BD.
     */
    @Override
    public List<Integer> update(Integer userId, List<Comision> comisionList) {
        List<Integer> idList = new ArrayList();
        for (Comision comision : comisionList) {
            comision.setModificacionUsuario(userId);
            comision.setModificacionFecha(new Timestamp(new Date().getTime()));
            comisionDAO.update(comision);
            idList.add(comision.getComisionId());
        }
        return idList;
    }

    /*
 * Método que elimina el registro <b>comision</b> en la tabla de <b>Comision</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param comisionList Lista de registros que se eliminarán en la tabla de <b>Comision</b> en la BD.
     */
    @Override
    public void delete(Integer userId, List<Integer> comisionIdList) {
        for (Integer comisionId : comisionIdList) {
            Comision comision = findById(comisionId);
            comision.setEliminadoId(ELIMINADO);
            comision.setModificacionUsuario(userId);
            comision.setModificacionFecha(new Timestamp(new Date().getTime()));
            comisionDAO.update(comision);
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
//@Override
//public boolean isValidoNombre(String nombre) {
//return !(findByCriteria(createDetachedCriteria().add(
//Expression.eq("nombre", nombre.trim()))).size() >= 1);
//}

    /*
 * Método que busca el registro <b>comision</b> por su NOMBRE en la tabla de <b>Comision</b>.
 * @param nombre es el NOMBRE del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>Comision</b> con la información de la consulta.
     */
//@Override
//public Comision findByName(String nombre) {
//return getFirst(findByCriteria(createDetachedCriteria()
//.add(Expression.eq("nombre", nombre.trim()))));
//}

    /*
 * Busca el registro <b>comision</b> por su ID en la tabla de <b>Comision</b>.
 * @param id es el ID del registro a obtener.
 * @return Regresa un objeto <b>Comision</b> con la información del registro <b>comision</b>.
     */
    @Override
    public Comision findById(Integer comisionId) {
        Comision comision = comisionDAO.findById(comisionId);
        if (comision == null) {
            return null;
        } else {
            return resolveDescription(Arrays.asList(comision)).get(0);
        }
    }

    @Override
    public List<Comision> findByIntProperty(String propertyName, Integer value) {
        return resolveDescription(findByCriteria(
                createDetachedCriteria().add(Expression.eq(propertyName, value))));
    }

    /*
 * Método que hace una consulta a la tabla Comision y obtiene los registros que coincidan con el objeto <b>comision</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
 * @param displayedPage Número de página que se desea mostrar al usuario en el widget JQGrid.
 * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
 * @param order Indica por que campo sera ordenada la búsqueda.
 * @param ordenTipo Tipo de ordenado acendente o decendente.
 * @param Comision objeto que contiene los parámetros a buscar.
 * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    @Override
    public Grid findByCriteria(int displayedPage, int maxResult, String order,
            String ordenTipo, Comision comision) {
        Grid grid = new Grid();
        DetachedCriteria criteria = createDetachedCriteria();
        // Auxiliar para busqueda por rango de fechas campo: CreacionFecha
        if (comision.getCreacionFechaInicial() != null) {
            criteria.add(Expression.ge("creacionFecha", getStartOfDay(comision.getCreacionFechaInicial())));
        }
        if (comision.getCreacionFechaFinal() != null) {
            criteria.add(Expression.le("creacionFecha", getEndOfDay(comision.getCreacionFechaFinal())));
        }
        // Auxiliar para busqueda por rango de fechas campo: ModificacionFecha
        if (comision.getModificacionFechaInicial() != null) {
            criteria.add(Expression.ge("modificacionFecha", getStartOfDay(comision.getModificacionFechaInicial())));
        }
        if (comision.getModificacionFechaFinal() != null) {
            criteria.add(Expression.le("modificacionFecha", getEndOfDay(comision.getModificacionFechaFinal())));
        }
        criteria.add(Example.create(comision));
        if (ordenTipo.equals("asc")) {
            criteria.addOrder(Order.asc(order));
        } else {
            criteria.addOrder(Order.desc(order));
        }
        int resultadosTotales = findSizeByCriteria(copy(criteria));
        List<Comision> comisionList = findByCriteriaLimit(criteria,
                maxResult * (displayedPage - 1), maxResult);
        grid.setGrid(resolveDescription(comisionList));
        grid.setTotal(resultadosTotales);
        grid.setPaginas(lastPage(resultadosTotales, maxResult));
        return grid;
    }

    @Override
    public Comision findFirst() {
        List<Comision> comisionList = findByCriteria(createDetachedCriteria());
        if (comisionList.isEmpty()) {
            return null;
        } else {
            return comisionList.get(0);
        }
    }

    public List<Comision> resolveDescription(List<Comision> list) {
        if (!list.isEmpty()) {
            List<Usuario> usuarioList = usuarioBss.findAll();
            List<ClasificacionContra> clasificacionContraList = clasificacionContraBss.findAll();
            List<SubclasificacionContra> subclasificacionContraList = subclasificacionContraBss.findAll();
            List<NombreContra> nombreContraList = nombreContraBss.findAll();
            List<ConfiguracionParametro> estatusList = getParametros("key_estatus");
            List<ConfiguracionParametro> eliminadoList = getParametros("key_eliminado");
            for (Comision comision : list) {

                comision.setUsuarioModificacion("");
                if (comision.getModificacionUsuario() != null) {
                    for (Usuario o : usuarioList) {
                        if (o.getUsuarioId() == comision.getModificacionUsuario()) {
                            comision.setUsuarioModificacion(o.getUsuario());
                            break;
                        }
                    }
                }

                comision.setClasificacionContra("");
                if (comision.getClasificacionContraId() != null) {
                    for (ClasificacionContra o : clasificacionContraList) {
                        if (o.getClasificacionContraId() == comision.getClasificacionContraId()) {
                            comision.setClasificacionContra(o.getNombre());
                            break;
                        }
                    }
                }

                comision.setSubclasificacionContra("");
                if (comision.getSubclasificacionContraId() != null) {
                    for (SubclasificacionContra o : subclasificacionContraList) {
                        if (o.getSubclasificacionContraId() == comision.getSubclasificacionContraId()) {
                            comision.setSubclasificacionContra(o.getNombre());
                            break;
                        }
                    }
                }

                comision.setEstatus("");
                if (comision.getEstatusId() != null) {
                    for (ConfiguracionParametro o : estatusList) {
                        if (Integer.parseInt(o.getValor()) == comision.getEstatusId()) {
                            comision.setEstatus(o.getNombre());
                            break;
                        }
                    }
                }

                comision.setEliminado("");
                if (comision.getEliminadoId() != null) {
                    for (ConfiguracionParametro o : eliminadoList) {
                        if (Integer.parseInt(o.getValor()) == comision.getEliminadoId()) {
                            comision.setEliminado(o.getNombre());
                            break;
                        }
                    }
                }
            }
        }
        return list;
    }

    public Integer findSizeByCriteria(DetachedCriteria criteria) {
        return comisionDAO.findSizeByCriteria(generalizarCriteria(criteria));
    }

    public List<Comision> findByCriteriaLimit(DetachedCriteria criteria, Integer from, Integer to) {
        return comisionDAO.findByCriteriaLimit(generalizarCriteria(criteria), from, to);
    }

    /*
 * Método que realiza un búsqueda en la BD obteniendo todos los registros que coincidan con los parámetros
 * definidos en el objeto <b>criteria</b>.
 *
 * @param criteria Es un objeto que contiene los parámetros de búsqueda.
 * @return Regresa una lista de objetos <b>Comision</b> que coinciden con los parámetros definidos en
 * <b>criteria</b>.
     */
    @Override
    public List<Comision> findByCriteria(DetachedCriteria criteria) {
        return comisionDAO.findByCriteria(generalizarCriteria(criteria));
    }

    @Override
    public List<Comision> findByCriteriaIgnorePrivacy(Comision comision) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Example.create(comision));
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        return comisionDAO.findByCriteria(criteria);
    }

    public DetachedCriteria generalizarCriteria(DetachedCriteria criteria) {
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        resolvePrivacy(criteria, ComisionAction.class);
        return criteria;
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>Comision</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>Comision</b>.
     */
    @Override
    public List<Comision> findAll() {
        return findByCriteria(createDetachedCriteria().addOrder(Order.asc("nombre")));
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>Comision</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>Comision</b>.
     */
    @Override
    public List<Comision> findActive() {
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
        return comisionDAO.createDetachedCriteria();
    }

    /*
 * Método que actualiza el Estatus de los ID' contenidos en la lista
 * <b>comisionList</b> en la tabla de <b>Comision</b>.
 * @param userId userId Es el ID del usuario que realizó la operación.
 * @param estatusId Es el ID del estatus al cual se quiere cambiar.
 * @param comisionList Es una lista que contiene los registros los cuales se
 * quiere actualizar su Estatus.
     */
    @Override
    public void setEstatus(Integer userId, Integer estatusId, List<Integer> comisionList) {
        for (Integer comisionId : comisionList) {
            Comision comision = findById(comisionId);
            if (estatusId == 1) {
                comision.setEstatusId(ACTIVO);
            } else {
                comision.setEstatusId(INACTIVO);
            }
            update(userId, Arrays.asList(comision));
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
    public String getReportDataTest(String order, String ordenTipo, Comision comision) {
        Grid grid = findByCriteria(1, 10000, order, ordenTipo, comision);
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
//@Override
//public List<NombreContra> getNombreContra() {  
//List<NombreContra> listaNnombreContra =  new ArrayList();
//for(NombreContra nombreContra: nombreContraBss.findActive()){        
//if (nombreContra.getTipoValorId().equals(new Integer(codigos.getString("key_tipovalor_%")))) {
//nombreContra.setTipoValor(codigos.getString("key_tipovalorDesc_%"));
//} else {
//nombreContra.setTipoValor(codigos.getString("key_tipovalorDesc_$"));
//}
//listaNnombreContra.add(nombreContra);
//}    
//return addDummy(listaNnombreContra , new NombreContra(), NombreContra.class);
//}

    @Override
    public String hasGrid() {
        return hasGrid(ComisionAction.class).toString();
    }

    @Override
    public String isIndividual() {
        return isIndividual(ComisionAction.class).toString();
    }

    @Override
    public String getNombreActionMenu() {
        Pagina pagina = findPageByActionClass(ComisionAction.class);
        if (pagina == null) {
            return "";
        } else {
            return pagina.getNombre();
        }
    }

    // Selects encadenados
    @Override
    public List<SubclasificacionContra> filtrarSubclasificacionContra(int clasificacionContraId) {
        return subclasificacionContraBss.filtrarSubclasificacionContra(clasificacionContraId);
    }

    // Selects encadenados
//@Override
//public List<NombreContra> filtrarNombreContra(int subclasificacionContraId) {
//return nombreContraBss.filtrarNombreContra(subclasificacionContraId);
//}

    /*
 * Inyección de dependencias con Spring, cada
 * referencia definida al inicio de la clase requiere un método
 * Setter.
     */
    public void setComisionDAO(ComisionDAO comisionDAO) {
        this.comisionDAO = comisionDAO;
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

    public void setNombreContraBss(NombreContraBss nombreContraBss) {
        this.nombreContraBss = nombreContraBss;
    }

}
