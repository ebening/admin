package com.admon.bss.admon;

import com.admon.pkg.dao.OrganizacionPKGDAO;
import com.admon.bss.BaseBss;
import com.admon.bss.util.Aes;
import com.admon.dao.admon.OrganizacionGenerarDAO;
import com.admon.entity.admon.*;
import com.admon.model.admon.OrganizacionGenerarAction;
import com.admon.pkg.entity.OrganizacionGenerarRS;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Objects;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

public class OrganizacionGenerarBssImpl extends BaseBss implements OrganizacionGenerarBss {

    /* Inyección de dependencias con Spring. Estas dependencias se
     * configuran en el applicationContext.xml, y además necesitan un
     * método setter por cada variable que se anexa al final de la clase. */
    private OrganizacionPKGDAO organizacionPKGDAO;
    private OrganizacionGenerarDAO organizacionGenerarDAO;
    private ConfiguracionParametroBss configuracionParametroBss;
    private UsuarioBss usuarioBss;
    private OrganizacionCredencialBss organizacionCredencialBss;
    private OrganizacionBss organizacionBss;

    public OrganizacionGenerarBssImpl() {
    }

    /*
     * Método que guarda los objetos contenidos en la lista <b>objectList</b> en la tabla de <b>OrganizacionGenerar</b>.
     * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de objetos que se guardarán en la tabla de <b>OrganizacionGenerar</b> en la BD.
     * @return Regresa una lista con los id's de los registros guardados.
     */
    @Override
    public List<Integer> save(Integer userId, List<OrganizacionGenerar> organizacionGenerarList) {
        List<Integer> savedList = new ArrayList<Integer>();
        for (OrganizacionGenerar organizacionGenerar : organizacionGenerarList) {
            organizacionGenerar.setEstatusId(ACTIVO);
            organizacionGenerar.setEliminadoId(NOELIMINADO);
            organizacionGenerar.setModificacionUsuario(userId);
            organizacionGenerar.setCreacionUsuario(userId);
            organizacionGenerar.setCreacionFecha(new Timestamp(new Date().getTime()));
            organizacionGenerar.setModificacionFecha(new Timestamp(new Date().getTime()));
            savedList.add(organizacionGenerarDAO.save(organizacionGenerar));
        }
        return savedList;
    }

    /*
     * Método que actualiza la información de <b>organizacionGenerar</b> en la tabla de <b>OrganizacionGenerar</b>.
     * @param userId Es el id del usuario que realizó la operación. * @param organizacionGenerarList Lista de registros que se actualizarán en la tabla de <b>OrganizacionGenerar</b> en la BD.
     */
    @Override
    public List<Integer> update(Integer userId, List<OrganizacionGenerar> organizacionGenerarList) {
        List<Integer> idList = new ArrayList();
        for (OrganizacionGenerar organizacionGenerar : organizacionGenerarList) {
            organizacionGenerar.setModificacionUsuario(userId);
            organizacionGenerar.setModificacionFecha(new Timestamp(new Date().getTime()));
            organizacionGenerarDAO.update(organizacionGenerar);
            idList.add(organizacionGenerar.getOrganizacionGenerarId());
        }
        return idList;
    }

    /*
     * Método que elimina el registro <b>organizacionGenerar</b> en la tabla de <b>OrganizacionGenerar</b>.
     * @param userId Es el id del usuario que realizó la operación. * @param organizacionGenerarList Lista de registros que se eliminarán en la tabla de <b>OrganizacionGenerar</b> en la BD.
     */
    @Override
    public void delete(Integer userId, List<Integer> organizacionGenerarIdList) {
        for (Integer organizacionGenerarId : organizacionGenerarIdList) {
            OrganizacionGenerar organizacionGenerar = findById(organizacionGenerarId);
            organizacionGenerar.setEliminadoId(ELIMINADO);
            organizacionGenerar.setModificacionUsuario(userId);
            organizacionGenerar.setModificacionFecha(new Timestamp(new Date().getTime()));
            organizacionGenerarDAO.update(organizacionGenerar);
        }
    }

    /*
     * Método que guarda los objetos contenidos en la lista <b>objectList</b> en la tabla de <b>OrganizacionGenerar</b>.
     * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de objetos que se guardarán en la tabla de <b>OrganizacionGenerar</b> en la BD.
     * @return Regresa una lista con los id's de los registros guardados.
     */
    @Override
    public String crear(Integer userId, Integer organizacionId, OrganizacionGenerar organizacionGenerar) {
        List<ConfiguracionParametro> rutasList = getParametros("key_rutas");
        String rutaTaquilla = "";
        String rutaWeb = "";
        String rutaImpresion = "";
        for (ConfiguracionParametro o : rutasList) {
            if (o.getNombre().equalsIgnoreCase(codigos.getString("key_taquilla")) && organizacionGenerar.getTaquilla() == 1) {
                rutaTaquilla = o.getValor();
                copiarDirectorios(rutaTaquilla.concat("0"), rutaTaquilla.concat("" + organizacionId));
            }
            System.out.println(o.getNombre());
            if (o.getNombre().equalsIgnoreCase(codigos.getString("key_web")) && organizacionGenerar.getWeb() == 1) {
                rutaWeb = o.getValor();
                System.out.println(rutaWeb + organizacionId);

                copiarDirectorios(rutaWeb.concat("0"), rutaWeb + organizacionId);
            }
            if (o.getNombre().equalsIgnoreCase(codigos.getString("key_impresiones"))) {
                rutaImpresion = o.getValor();
                copiarDirectorios(rutaImpresion.concat("0"), rutaImpresion.concat("" + organizacionId));
            }
        }

        addCredencialesJSON(organizacionId, rutasList, organizacionGenerar);

        String spName = "organizacionGenerarPRC";
        List list = null;
        List<SPParametro> spList = new ArrayList();
        StringBuilder sbResult = new StringBuilder();
        try {
            sbResult.append("ORG_Id: " + organizacionId.toString());
            spList.add(new SPParametro("organizacionId", organizacionId));
            list = organizacionPKGDAO.callStoredProcedure(spName, spList);
            sbResult.append("\n Generado");
        } catch (Exception e) {
            sbResult.append("\n String: " + e.toString());
            sbResult.append("\n Message: " + e.getMessage());
            sbResult.append("\n LocalizedMessage: " + e.getLocalizedMessage());
            sbResult.append(e);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            sbResult.append(sw.toString());
            System.out.println("error> " + e);
        }
        return sbResult.toString();
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
     * Método que busca el registro <b>organizacionGenerar</b> por su NOMBRE en la tabla de <b>OrganizacionGenerar</b>.
     * @param nombre es el NOMBRE del registro que se quiere obtener.
     * @return Regresa un objeto tipo <b>OrganizacionGenerar</b> con la información de la consulta.
     */
    @Override
    public OrganizacionGenerar findByName(String nombre) {
        return getFirst(findByCriteria(createDetachedCriteria()
                .add(Expression.eq("nombre", nombre.trim()))));
    }

    /*
     * Busca el registro <b>organizacionGenerar</b> por su ID en la tabla de <b>OrganizacionGenerar</b>.
     * @param id es el ID del registro a obtener.
     * @return Regresa un objeto <b>OrganizacionGenerar</b> con la información del registro <b>organizacionGenerar</b>.
     */
    @Override
    public OrganizacionGenerar findById(Integer organizacionGenerarId) {
        OrganizacionGenerar organizacionGenerar = organizacionGenerarDAO.findById(organizacionGenerarId);
        if (organizacionGenerar == null) {
            return null;
        } else {
            return resolveDescription(Arrays.asList(organizacionGenerar)).get(0);
        }
    }

    @Override
    public List<OrganizacionGenerar> findByIntProperty(String propertyName, Integer value) {
        return resolveDescription(findByCriteria(
                createDetachedCriteria().add(Expression.eq(propertyName, value))));
    }

    /*
     * Método que hace una consulta a la tabla OrganizacionGenerar y obtiene los registros que coincidan con el objeto <b>organizacionGenerar</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
     * @param displayedPage Número de página que se desea mostrar al usuario en el widget JQGrid.
     * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
     * @param order Indica por que campo sera ordenada la búsqueda.
     * @param ordenTipo Tipo de ordenado acendente o decendente.
     * @param OrganizacionGenerar objeto que contiene los parámetros a buscar.
     * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    @Override
    public Grid findByCriteria(int displayedPage, int maxResult, String order,
            String ordenTipo, OrganizacionGenerar organizacionGenerar) {
        Grid grid = new Grid();
        DetachedCriteria criteria = createDetachedCriteria();
        // Auxiliar para busqueda por rango de fechas campo: CreacionFecha
        criteria.add(Example.create(organizacionGenerar));
        if (ordenTipo.equals("asc")) {
            criteria.addOrder(Order.asc(order));
        } else {
            criteria.addOrder(Order.desc(order));
        }
        int resultadosTotales = findSizeByCriteria(copy(criteria));
        List<OrganizacionGenerar> organizacionGenerarList = findByCriteriaLimit(criteria,
                maxResult * (displayedPage - 1), maxResult);
        grid.setGrid(resolveDescription(organizacionGenerarList));
        grid.setTotal(resultadosTotales);
        grid.setPaginas(lastPage(resultadosTotales, maxResult));
        return grid;
    }

    @Override
    public OrganizacionGenerar findFirst() {
        List<OrganizacionGenerar> organizacionGenerarList = findByCriteria(createDetachedCriteria());
        if (organizacionGenerarList.isEmpty()) {
            return null;
        } else {
            return organizacionGenerarList.get(0);
        }
    }

    public List<OrganizacionGenerar> resolveDescription(List<OrganizacionGenerar> list) {
        if (!list.isEmpty()) {
            List<Usuario> usuarioList = usuarioBss.findAll();
            List<Organizacion> organizacionList = organizacionBss.findAll();
            List<ConfiguracionParametro> estatusList = getParametros("key_estatus");
            List<ConfiguracionParametro> eliminadoList = getParametros("key_eliminado");
            List<ConfiguracionParametro> aplicacionList = getParametros("key_aplicacion");
            List<ConfiguracionParametro> nombreList = getParametros("key_tipo_requisito");

            for (OrganizacionGenerar organizacionGenerar : list) {

                organizacionGenerar.setNombre("");
                if (organizacionGenerar.getConceptoId() != null) {
                    for (ConfiguracionParametro o : nombreList) {
                        if (Objects.equals(o.getConfiguracionParametroId(), organizacionGenerar.getConceptoId())) {
                            organizacionGenerar.setNombre(o.getDescripcion());
                            break;
                        }
                    }
                }
//
//                organizacionGenerar.setOrganizacion("");
//                if (organizacionGenerar.getOrganizacionId() != null) {
//                    for (Organizacion o : organizacionList) {
//                        if (o.getOrganizacionId() == organizacionGenerar.getOrganizacionId()) {
//                            organizacionGenerar.setOrganizacion(o.getNombre());
//                            break;
//                        }
//                    }
//                }

                organizacionGenerar.setEstatus("");
                if (organizacionGenerar.getEstatusId() != null) {
                    for (ConfiguracionParametro o : estatusList) {
                        if (Integer.parseInt(o.getValor()) == organizacionGenerar.getEstatusId()) {
                            organizacionGenerar.setEstatus(o.getNombre());
                            break;
                        }
                    }
                }

                organizacionGenerar.setEliminado("");
                if (organizacionGenerar.getEliminadoId() != null) {
                    for (ConfiguracionParametro o : eliminadoList) {
                        if (Integer.parseInt(o.getValor()) == organizacionGenerar.getEliminadoId()) {
                            organizacionGenerar.setEliminado(o.getNombre());
                            break;
                        }
                    }
                }

//                organizacionGenerar.setAplicacion("");
//                if (organizacionGenerar.getConceptoId() != null) {
//                    for (ConfiguracionParametro o : aplicacionList) {
//                        if (Integer.parseInt(o.getValor()) == organizacionGenerar.getConceptoId()) {
//                            organizacionGenerar.setAplicacion(o.getNombre());
//                            break;
//                        }
//                    }
//                }
            }
        }
        return list;
    }

    public Integer findSizeByCriteria(DetachedCriteria criteria) {
        return organizacionGenerarDAO.findSizeByCriteria(generalizarCriteria(criteria));
    }

    public List<OrganizacionGenerar> findByCriteriaLimit(DetachedCriteria criteria, Integer from, Integer to) {
        return organizacionGenerarDAO.findByCriteriaLimit(generalizarCriteria(criteria), from, to);
    }

    /*
     * Método que realiza un búsqueda en la BD obteniendo todos los registros que coincidan con los parámetros
     * definidos en el objeto <b>criteria</b>.
     *
     * @param criteria Es un objeto que contiene los parámetros de búsqueda.
     * @return Regresa una lista de objetos <b>OrganizacionGenerar</b> que coinciden con los parámetros definidos en
     * <b>criteria</b>.
     */
    @Override
    public List<OrganizacionGenerar> findByCriteria(DetachedCriteria criteria) {
        return organizacionGenerarDAO.findByCriteria(generalizarCriteria(criteria));
    }

    @Override
    public List<OrganizacionGenerar> findByCriteriaIgnorePrivacy(OrganizacionGenerar organizacionGenerar) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Example.create(organizacionGenerar));
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        return organizacionGenerarDAO.findByCriteria(criteria);
    }

    public DetachedCriteria generalizarCriteria(DetachedCriteria criteria) {
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        resolvePrivacy(criteria, OrganizacionGenerarAction.class);
        return criteria;
    }

    /*
     * Método que obtiene todos los registros de la tabla de <b>OrganizacionGenerar</b>.
     * Este método es generalmente utilizado por los action para obtener las opciones
     * de los select que se llenan mediante struts.
     *
     * @return Regresa todos los registros de la tabla <b>OrganizacionGenerar</b>.
     */
    @Override
    public List<OrganizacionGenerar> findAll() {
        return findByCriteria(createDetachedCriteria().addOrder(Order.asc("nombre")));
    }

    /*
     * Método que obtiene todos los registros de la tabla de <b>OrganizacionGenerar</b>.
     * Este método es generalmente utilizado por los action para obtener las opciones
     * de los select que se llenan mediante struts.
     *
     * @return Regresa todos los registros de la tabla <b>OrganizacionGenerar</b>.
     */
    @Override
    public List<OrganizacionGenerar> findActive() {
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
        return organizacionGenerarDAO.createDetachedCriteria();
    }

    /*
     * Método que actualiza el Estatus de los ID' contenidos en la lista
     * <b>organizacionGenerarList</b> en la tabla de <b>OrganizacionGenerar</b>.
     * @param userId userId Es el ID del usuario que realizó la operación.
     * @param estatusId Es el ID del estatus al cual se quiere cambiar.
     * @param organizacionGenerarList Es una lista que contiene los registros los cuales se
     * quiere actualizar su Estatus.
     */
    @Override
    public void setEstatus(Integer userId, Integer estatusId, List<Integer> organizacionGenerarList) {
        for (Integer organizacionGenerarId : organizacionGenerarList) {
            OrganizacionGenerar organizacionGenerar = findById(organizacionGenerarId);
            if (estatusId == 1) {
                organizacionGenerar.setEstatusId(ACTIVO);
            } else {
                organizacionGenerar.setEstatusId(INACTIVO);
            }
            update(userId, Arrays.asList(organizacionGenerar));
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
    public String getReportDataTest(String order, String ordenTipo, OrganizacionGenerar organizacionGenerar) {
        Grid grid = findByCriteria(1, 10000, order, ordenTipo, organizacionGenerar);
        return addReportDataToSession(grid.getGrid());
    }

    /*
     * Metodos que actualizan los campos <select> en el jsp despues de haber hecho
     * una edición de datos en un popup externo.
     */
    @Override
    public List<Organizacion> getOrganizacion() {
        return addDummy(organizacionBss.findActive(), new Organizacion(), Organizacion.class);
    }

    @Override
    public String hasGrid() {
        return hasGrid(OrganizacionGenerarAction.class).toString();
    }

    @Override
    public String isIndividual() {
        return isIndividual(OrganizacionGenerarAction.class).toString();
    }

    @Override
    public String getNombreActionMenu() {
        Pagina pagina = findPageByActionClass(OrganizacionGenerarAction.class);
        if (pagina == null) {
            return "";
        } else {
            return pagina.getNombre();
        }
    }

    public boolean addCredencialesJSON(int organizacionId, List<ConfiguracionParametro> rutasList, OrganizacionGenerar organizacionGenerar) {
        JSONObject objson = new JSONObject();
        OrganizacionCredencial orgCred;
        System.out.println("addCredencialesJSON");
        List<OrganizacionCredencial> listOrgCred;
        listOrgCred = organizacionCredencialBss.findByCriteria(DetachedCriteria.forEntityName("com.admon.entity.admon.OrganizacionCredencial")
                .add(Expression.eq("organizacionId", organizacionId))
                .add(Expression.eq("aplicacionId", 2))
                .add(Expression.eq("estatusId", ACTIVO)));

        if (!listOrgCred.isEmpty()) {
            orgCred = listOrgCred.get(0);
            for (ConfiguracionParametro o : rutasList) {
                if (o.getNombre().equalsIgnoreCase(codigos.getString("key_taquilla")) && organizacionGenerar.getTaquilla() == 1) {
                    objson.put("domain", orgCred.getDomain());
                    objson.put("id", Aes.encrypt(Integer.toString(organizacionId)));
                    objson.put("path", "repositorio/taquilla/organizacion" + organizacionId + "/Templates/1/");
                    modificaJson(objson, orgCred.getDomain(), o.getValor());
                }
                if (o.getNombre().equalsIgnoreCase(codigos.getString("key_web")) && organizacionGenerar.getWeb() == 1) {
                    objson.put("domain", orgCred.getDomain());
                    objson.put("id", Aes.encrypt(Integer.toString(organizacionId)));
                    objson.put("path", "repositorio/web/" + organizacionId + "/Templates/1/");
                    objson.put("appId", orgCred.getIdapp().toString());
                    objson.put("recapchaId", orgCred.getIdrecapcha().toString());
                    System.out.println("o.getValor()" + o.getValor());
                    System.out.println(orgCred.getDomain());
                    modificaJson(objson, orgCred.getDomain(), o.getValor());
                }
                if (o.getNombre().equalsIgnoreCase(codigos.getString("key_impresiones"))) {
                    objson.put("domain", orgCred.getDomain());
                    objson.put("id", Aes.encrypt(Integer.toString(organizacionId)));
                    objson.put("path", "repositorio/impresiones/organizacion" + organizacionId + "/Templates/1");
                    modificaJson(objson, orgCred.getDomain(), o.getValor());
                }
            }
        }

        return true;
    }

    private boolean modificaJson(JSONObject objson, String domain, String ruta) {
        JSONParser parser = new JSONParser();
        try {
            JSONObject objIn = (JSONObject) parser.parse(new FileReader(ruta.concat("credenciales.json")));

            JSONArray arrCred = (JSONArray) objIn.get("data");
            boolean found = false;
            String rutaAux = "";

            for (Object o : arrCred) {
                JSONObject objCred = (JSONObject) o;

                if (domain.equalsIgnoreCase((String) objCred.get("domain"))) {
                    objCred = objson;
                    found = true;
                }
            }
            if (!found) {
                arrCred.add(objson);
            }

            rutaAux = objIn.toJSONString();
            rutaAux = rutaAux.replace("\\/", "/");
            System.out.println(rutaAux);
            FileWriter file = new FileWriter(ruta.concat("credenciales.json"));
            file.write(rutaAux);
            file.flush();
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return true;
    }

    private boolean copiaOrg(File origen, File destino) {

        if (origen.exists()) {
            try {
                InputStream in = new FileInputStream(origen);
                OutputStream out = new FileOutputStream(destino);

                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                return true;
            } catch (IOException ioe) {
                ioe.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean copiarDirectorios(String d1, String d2) {
        System.out.println("copiarDirectorios d1="+d1+" d2="+d2);
        File origen = new File(d1);
        File destino = new File(d2);
        boolean creado = false;
        if (origen.isDirectory()) {
            String[] ficheros = origen.list();
            if (!destino.exists()) {
                destino.mkdir();
            }
            for (int x = 0; x < ficheros.length; x++) {
                copiarDirectorios(d1.concat("/").concat(ficheros[x]), d2.concat("/").concat(ficheros[x]));
            }
        } else {
            creado = copiaOrg(origen, destino);
        }

        return creado;
    }

    @Override
    public List<OrganizacionGenerar> registraRequisitoCreacion(Integer organizacionId, Integer userId, Integer tipo, Integer concepto) {

        List organizacionGenerarLst = findByCriteria(createDetachedCriteria()
                .add(Expression.eq("conceptoId", concepto))
                .add(Expression.eq("tipo", tipo))
                .add(Expression.eq("organizacionId", organizacionId)));
        int organizacionGenerarId = ((OrganizacionGenerar) organizacionGenerarLst.get(0)).getOrganizacionGenerarId();

        List<OrganizacionGenerar> organizacionGenerarList = new ArrayList();
        OrganizacionGenerar organizacionGenerar = new OrganizacionGenerar();
        organizacionGenerar.setOrganizacionGenerarId(organizacionGenerarId);
        organizacionGenerar.setOrganizacionId(organizacionId);
        organizacionGenerar.setConceptoId(concepto);
        organizacionGenerar.setTipo(tipo);
        organizacionGenerar.setEstatusId(ACTIVO);
        organizacionGenerar.setEliminadoId(NOELIMINADO);
        organizacionGenerar.setCreacionFecha(new Timestamp(new Date().getTime()));
        organizacionGenerar.setCreacionUsuario(userId);

        organizacionGenerarList.add(organizacionGenerar);
        return organizacionGenerarList;
    }

    /*
     * Inyección de dependencias con Spring, cada
     * referencia definida al inicio de la clase requiere un método
     * Setter.
     */
    public void setOrganizacionGenerarDAO(OrganizacionGenerarDAO organizacionGenerarDAO) {
        this.organizacionGenerarDAO = organizacionGenerarDAO;
    }

    public void setConfiguracionParametroBss(ConfiguracionParametroBss configuracionParametroBss) {
        this.configuracionParametroBss = configuracionParametroBss;
    }

    public void setUsuarioBss(UsuarioBss usuarioBss) {
        this.usuarioBss = usuarioBss;
    }

    public void setOrganizacionBss(OrganizacionBss organizacionBss) {
        this.organizacionBss = organizacionBss;
    }

    public void setOrganizacionCredencialBss(OrganizacionCredencialBss organizacionCredencialBss) {
        this.organizacionCredencialBss = organizacionCredencialBss;
    }

    public void setOrganizacionPKGDAO(OrganizacionPKGDAO organizacionPKGDAO) {
        this.organizacionPKGDAO = organizacionPKGDAO;
    }

}
