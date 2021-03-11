package com.admon.bss.admon;

import com.admon.bss.BaseBss;
import com.admon.dao.admon.UsuarioDAO;
import com.admon.entity.admon.*;
import com.admon.model.admon.UsuarioAction;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

public class UsuarioBssImpl extends BaseBss implements UsuarioBss {

    /* Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase. */
    private UsuarioDAO usuarioDAO;
    private ConfiguracionParametroBss configuracionParametroBss;
    private PaginaBss paginaBss;
    private PerfilPaginaBss perfilPaginaBss;
    private PerfilBss perfilBss;

    public UsuarioBssImpl() {
    }

    /*
 * Método que guarda los objetos contenidos en la lista <b>objectList</b> en la tabla de <b>Usuario</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de objetos que se guardarán en la tabla de <b>Usuario</b> en la BD.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    @Override
    public List<Integer> save(Integer userId, List<Usuario> usuarioList, List<Integer> paginaList) {
        List<Integer> savedList = new ArrayList<Integer>();
        for (Usuario usuario : usuarioList) {
            String encryptedText = encripta(usuario.getContrasena());
            usuario.setContrasena(encryptedText);
            usuario.setEstatusId(ACTIVO);
            usuario.setEliminadoId(NOELIMINADO);
            usuario.setModificacionUsuario(userId);
            usuario.setCreacionUsuario(userId);
            usuario.setCreacionFecha(new Timestamp(new Date().getTime()));
            usuario.setModificacionFecha(new Timestamp(new Date().getTime()));
            savedList.add(usuarioDAO.save(usuario));
        }
        return savedList;
    }

    /*
 * Método que actualiza la información de <b>usuario</b> en la tabla de <b>Usuario</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param usuarioList Lista de registros que se actualizarán en la tabla de <b>Usuario</b> en la BD.
     */
    @Override
    public List<Integer> update(Integer userId, List<Usuario> usuarioList, List<Integer> paginaList) {
        List<Integer> idList = new ArrayList();
        for (Usuario usuario : usuarioList) {
            String encryptedText = encripta(usuario.getContrasena());
            usuario.setContrasena(encryptedText);
            usuario.setModificacionUsuario(userId);
            usuario.setModificacionFecha(new Timestamp(new Date().getTime()));
            usuarioDAO.update(usuario);
            idList.add(usuario.getUsuarioId());
        }
        return idList;
    }

    /*
 * Método que actualiza la información de <b>usuario</b> en la tabla de <b>Usuario</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param usuarioList Lista de registros que se actualizarán en la tabla de <b>Usuario</b> en la BD.
     */
    @Override
    public List<Integer> update(Integer userId, List<Usuario> usuarioList) {
        List<Integer> idList = new ArrayList();
        for (Usuario usuario : usuarioList) {
            String encryptedText = encripta(usuario.getContrasena());
            usuario.setContrasena(encryptedText);
            usuario.setModificacionUsuario(userId);
            usuario.setModificacionFecha(new Timestamp(new Date().getTime()));
            usuarioDAO.update(usuario);
            idList.add(usuario.getUsuarioId());
        }
        return idList;
    }

    /*
 * Método que elimina el registro <b>usuario</b> en la tabla de <b>Usuario</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param usuarioList Lista de registros que se eliminarán en la tabla de <b>Usuario</b> en la BD.
     */
    @Override
    public void delete(Integer userId, List<Integer> usuarioIdList) {
        for (Integer usuarioId : usuarioIdList) {
            Usuario usuario = findById(usuarioId);
            String encryptedText = encripta(usuario.getContrasena());
            usuario.setContrasena(encryptedText);
            usuario.setEliminadoId(ELIMINADO);
            usuario.setModificacionUsuario(userId);
            usuario.setModificacionFecha(new Timestamp(new Date().getTime()));
            usuarioDAO.update(usuario);
        }
    }

    /*
 * Método que busca un determinado <b>usuario</b> por sus credenciales (nombre de usuario y contraseña)
 * en la base de datos y que son especificadas como parámetros en el método. Es usado en la
 * autenticación del usuario (Login)
 * @param usuario es el nombre de usuario.
 * @param contrasena es la contraseña de usuario.
 * @return Regresa una lista de <b>Usuarios</b> que hacen match con las credenciales proporcionadas.
     */
    @Override
    public List<Usuario> findByCredentials(String usuario, String contrasena) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Expression.eq("usuario", usuario));
        criteria.add(Expression.eq("contrasena", contrasena));
        criteria.add(Expression.eq("estatusId", ACTIVO));
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        return usuarioDAO.findByCriteria(criteria);
    }

    /*
 * Método que evalúa si existe al menos un registro con un determinado
 * usuario en la tabla.
 * @param usuario Es el usuario que se desea buscar en la tabla de la BD.
 * @return Regresa <b>false</b> si existe al menos un registro con el
 * usuario específicado como parámetro en el método, si no existe ningún
 * registro con ese usuario regresa un <b>true</b>.
     */
    @Override
    public boolean isValidoUsuario(String usuario) {
        List<Usuario> list;
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Expression.eq("usuario", usuario));
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        list = usuarioDAO.findByCriteria(criteria);
        if (list.size() >= 1) {
            return false;
        } else {
            return true;
        }
    }

    /*
 * Busca el registro <b>usuario</b> por su ID en la tabla de <b>Usuario</b>.
 * @param id es el ID del registro a obtener.
 * @return Regresa un objeto <b>Usuario</b> con la información del registro <b>usuario</b>.
     */
    @Override
    public Usuario findById(Integer usuarioId) {
        Usuario usuario = usuarioDAO.findById(usuarioId);
        if (usuario == null) {
            return null;
        } else {
            return resolveDescription(Arrays.asList(usuario)).get(0);
        }
    }

    /*
 * Método que busca el registro <b>usuario</b> por su ID en la tabla de <b>Usuario</b>.
 * Este método obtiene los campos encriptados tal y como estan en la base de datos, regresa el objeto por su id
 * sin desencriptar ningun valor.
 * @param id es el ID del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>Usuario</b> con la información del registro <b>usuario</b>.
     */
    @Override
    public Usuario findByIdAsIs(Integer usuarioId) {
        // return usuarioDAO.findById(usuarioId);
        Usuario usuario = findById(usuarioId);
        String unencryptedPassword = desencripta(usuario.getContrasena());
        usuario.setContrasena(unencryptedPassword);
        return resolveDescription(Arrays.asList(usuario)).get(0);
    }

    @Override
    public List<Usuario> findByIntProperty(String propertyName, Integer value) {
        return resolveDescription(findByCriteria(
                createDetachedCriteria().add(Expression.eq(propertyName, value))));
    }

    /*
 * Método que hace una consulta a la tabla Usuario y obtiene los registros que coincidan con el objeto <b>usuario</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
 * @param displayedPage Número de página que se desea mostrar al usuario en el widget JQGrid.
 * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
 * @param order Indica por que campo sera ordenada la búsqueda.
 * @param ordenTipo Tipo de ordenado acendente o decendente.
 * @param Usuario objeto que contiene los parámetros a buscar.
 * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    @Override
    public Grid findByCriteria(int displayedPage, int maxResult, String order,
            String ordenTipo, Usuario usuario) {
        Grid grid = new Grid();
        DetachedCriteria criteria = createDetachedCriteria();
        // Auxiliar para busqueda por rango de fechas campo: CreacionFecha
        if (usuario.getCreacionFechaInicial() != null) {
            criteria.add(Expression.ge("creacionFecha", getStartOfDay(usuario.getCreacionFechaInicial())));
        }
        if (usuario.getCreacionFechaFinal() != null) {
            criteria.add(Expression.le("creacionFecha", getEndOfDay(usuario.getCreacionFechaFinal())));
        }
        // Auxiliar para busqueda por rango de fechas campo: ModificacionFecha
        if (usuario.getModificacionFechaInicial() != null) {
            criteria.add(Expression.ge("modificacionFecha", getStartOfDay(usuario.getModificacionFechaInicial())));
        }
        if (usuario.getModificacionFechaFinal() != null) {
            criteria.add(Expression.le("modificacionFecha", getEndOfDay(usuario.getModificacionFechaFinal())));
        }
        criteria.add(Example.create(usuario));
        if (ordenTipo.equals("asc")) {
            criteria.addOrder(Order.asc(order));
        } else {
            criteria.addOrder(Order.desc(order));
        }
        int resultadosTotales = findSizeByCriteria(copy(criteria));
        List<Usuario> usuarioList = findByCriteriaLimit(criteria,
                maxResult * (displayedPage - 1), maxResult);
        grid.setGrid(resolveDescription(usuarioList));
        grid.setTotal(resultadosTotales);
        grid.setPaginas(lastPage(resultadosTotales, maxResult));
        return grid;
    }

    @Override
    public Usuario findFirst() {
        List<Usuario> usuarioList = findByCriteria(createDetachedCriteria());
        if (usuarioList.isEmpty()) {
            return null;
        } else {
            return usuarioList.get(0);
        }
    }

    public List<Usuario> resolveDescription(List<Usuario> list) {
        if (!list.isEmpty()) {
            List<Usuario> usuarioList = findAll();
            List<Perfil> perfilList = perfilBss.findAll();
            List<ConfiguracionParametro> estatusList = getParametros("key_estatus");
            List<ConfiguracionParametro> eliminadoList = getParametros("key_eliminado");
            for (Usuario usuario : list) {

                usuario.setUsuarioModificacion("");
                if (usuario.getModificacionUsuario() != null) {
                    for (Usuario o : usuarioList) {
                        if (o.getUsuarioId() == usuario.getModificacionUsuario()) {
                            usuario.setUsuarioModificacion(o.getUsuario());
                            break;
                        }
                    }
                }

                usuario.setPerfil("");
                if (usuario.getPerfilId() != null) {
                    for (Perfil o : perfilList) {
                        if (o.getPerfilId() == usuario.getPerfilId()) {
                            usuario.setPerfil(o.getNombre());
                            break;
                        }
                    }
                }

                usuario.setEstatus("");
                if (usuario.getEstatusId() != null) {
                    for (ConfiguracionParametro o : estatusList) {
                        if (Integer.parseInt(o.getValor()) == usuario.getEstatusId()) {
                            usuario.setEstatus(o.getNombre());
                            break;
                        }
                    }
                }

                usuario.setEliminado("");
                if (usuario.getEliminadoId() != null) {
                    for (ConfiguracionParametro o : eliminadoList) {
                        if (Integer.parseInt(o.getValor()) == usuario.getEliminadoId()) {
                            usuario.setEliminado(o.getNombre());
                            break;
                        }
                    }
                }
            }
        }
        return list;
    }

    public Integer findSizeByCriteria(DetachedCriteria criteria) {
        return usuarioDAO.findSizeByCriteria(generalizarCriteria(criteria));
    }

    public List<Usuario> findByCriteriaLimit(DetachedCriteria criteria, Integer from, Integer to) {
        return usuarioDAO.findByCriteriaLimit(generalizarCriteria(criteria), from, to);
    }

    /*
 * Método que realiza un búsqueda en la BD obteniendo todos los registros que coincidan con los parámetros
 * definidos en el objeto <b>criteria</b>.
 *
 * @param criteria Es un objeto que contiene los parámetros de búsqueda.
 * @return Regresa una lista de objetos <b>Usuario</b> que coinciden con los parámetros definidos en
 * <b>criteria</b>.
     */
    @Override
    public List<Usuario> findByCriteria(DetachedCriteria criteria) {
        return usuarioDAO.findByCriteria(generalizarCriteria(criteria));
    }

    @Override
    public List<Usuario> findByCriteriaIgnorePrivacy(Usuario usuario) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Example.create(usuario));
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        return usuarioDAO.findByCriteria(criteria);
    }

    public DetachedCriteria generalizarCriteria(DetachedCriteria criteria) {
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        resolvePrivacy(criteria, UsuarioAction.class);
        return criteria;
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>Usuario</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>Usuario</b>.
     */
    @Override
    public List<Usuario> findAll() {
        return addDummy(findByCriteria(createDetachedCriteria().
                addOrder(Order.asc("usuario"))), new Usuario(), Usuario.class,
                "--Seleccione--");
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>Usuario</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>Usuario</b>.
     */
    @Override
    public List<Usuario> findActive() {
        return usuarioYNombreCompleto(
                nombreCompleto(
                        addDummy(findByCriteria(createDetachedCriteria().
                                add(Expression.eq("estatusId", ACTIVO)).
                                addOrder(Order.asc("usuario"))), new Usuario(), Usuario.class,
                                "--Seleccione--")));
    }

    /*
 * Método que obtiene un objeto DetachedCriteria.
 *
 * @return Regresa un objeto DetachedCriteria.
     */
    @Override
    public DetachedCriteria createDetachedCriteria() {
        return usuarioDAO.createDetachedCriteria();
    }

    /*
 * Método que actualiza el Estatus de los ID' contenidos en la lista
 * <b>usuarioList</b> en la tabla de <b>Usuario</b>.
 * @param userId userId Es el ID del usuario que realizó la operación.
 * @param estatusId Es el ID del estatus al cual se quiere cambiar.
 * @param usuarioList Es una lista que contiene los registros los cuales se
 * quiere actualizar su Estatus.
     */
    @Override
    public void setEstatus(Integer userId, Integer estatusId, List<Integer> usuarioList) {
        for (Integer usuarioId : usuarioList) {
            Usuario usuario = findByIdAsIs(usuarioId);
            if (estatusId == 1) {
                usuario.setEstatusId(ACTIVO);
            } else {
                usuario.setEstatusId(INACTIVO);
            }
            update(userId, Arrays.asList(usuario));
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
    public String getReportDataTest(String order, String ordenTipo, Usuario usuario) {
        Grid grid = findByCriteria(1, 10000, order, ordenTipo, usuario);
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
    public String hasGrid() {
        return hasGrid(UsuarioAction.class).toString();
    }

    @Override
    public String isIndividual() {
        return isIndividual(UsuarioAction.class).toString();
    }

    @Override
    public String getNombreActionMenu() {
        Pagina pagina = findPageByActionClass(UsuarioAction.class);
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
    public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public void setConfiguracionParametroBss(ConfiguracionParametroBss configuracionParametroBss) {
        this.configuracionParametroBss = configuracionParametroBss;
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
* @param perfilId es el perfil del cual se quiere obtener los accesos
* @return regresa una lista de todos los registros de la tabla PAGINA
     */
    @Override
    public List<Integer> obtieneAccesos(int perfilId) {
        return perfilPaginaBss.obtieneAccesos(perfilId);
    }

    public void setPerfilPaginaBss(PerfilPaginaBss perfilPaginaBss) {
        this.perfilPaginaBss = perfilPaginaBss;
    }

    public void setPaginaBss(PaginaBss paginaBss) {
        this.paginaBss = paginaBss;
    }

    public void setPerfilBss(PerfilBss perfilBss) {
        this.perfilBss = perfilBss;
    }

}
