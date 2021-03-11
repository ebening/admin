package com.admon.model.admon;

import com.admon.bss.admon.UsuarioBss;
import com.admon.entity.admon.*;
import com.admon.model.BaseModel;
import java.util.List;

public class UsuarioDWR extends BaseModel {

    /*
 * Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase.
     */
    private UsuarioBss usuarioBss;

    public UsuarioDWR() {
    }

    /*
 * Guarda la lista <b>usuarioList</b> en la tabla <b>Usuario</b>.
 * @param usuarioList Lista a guardar.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    public List<Integer> save(List<Usuario> usuarioList, List<Integer> paginaIdLista) {
        return usuarioBss.save(obtieneSessionVar().getUsuarioId(), usuarioList, paginaIdLista);
    }

    /*
 * Método que actualiza la información de <b>usuario</b> en la tabla de <b>Usuario</b>.
 * @param usuarioList Lista de registros que se actualizarán en la tabla de <b>Usuario</b> en la BD.
     */
    public List<Integer> update(List<Usuario> usuarioList, List<Integer> paginaIdLista) {
        return usuarioBss.update(obtieneSessionVar().getUsuarioId(), usuarioList, paginaIdLista);
    }

    /*
 * Método que elimina el registro <b>usuario</b> en la tabla de <b>Usuario</b>.
 * Este método se llama <b>remove</b> debido a que en javascript (que se encarga de llamar a este método) <b>delete</b> es una palabra reservada.
 * @param usuarioList Lista de registros que se eliminarán en la tabla de <b>Usuario</b> en la BD.
     */
    public void remove(List<Integer> usuarioIdList) {
        usuarioBss.delete(obtieneSessionVar().getUsuarioId(), usuarioIdList);
    }

    /*
 * Método que evalúa si existe al menos un registro con un determinado
 * usuario en la tabla.
 * @param usuario Es el usuario que se desea buscar en la tabla de la BD.
 * @return Regresa <b>false</b> si existe al menos un registro con el
 * usuario específicado como parámetro en el método, si no existe ningún
 * registro con ese usuario regresa un <b>true</b>.
     */
    public boolean isValidoUsuario(String usuario) {
        return usuarioBss.isValidoUsuario(usuario);
    }

    /*
 * Método que busca el registro <b>usuario</b> por su ID en la tabla de <b>Usuario</b>.
 * @param id es el ID del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>Usuario</b> con la información del registro <b>usuario</b>.
     */
    public Usuario findById(Integer usuarioId) {
        return usuarioBss.findByIdAsIs(usuarioId);
    }

    /*
 * Método que hace una consulta a la tabla Usuario y obtiene los registros que coincidan con el objeto <b>usuario</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
 * @param p Número de página que se desea mostrar al usuario en el widget JQGrid.
 * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
 * @param order Indica por que campo sera ordenada la búsqueda.
 * @param ordenTipo Tipo de ordenado acendente o decendente.
 * @param Usuario objeto que contiene los parámetros a buscar.
 * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    public Grid findByCriteria(int p, int maxResult, String order, String ordenTipo, Usuario usuario) {
        return usuarioBss.findByCriteria(p, maxResult, order, ordenTipo, usuario);
    }

    /**
     * Método que obtiene el primer resultado de una consulta, utilizado cuando
     * la pagina tiene la propiedad individual
     *
     * @return regresa el primer resultado de la consulta, si no hay resultados
     * regresa null
     */
    public Usuario findFirst() {
        return usuarioBss.findFirst();
    }

    /*
 * Método que actualiza el Estatus de los objetos en la lista
 * <b>usuarioList</b> en la tabla de <b>Usuario</b>.
 * @param userId userId Es el ID del usuario que realizó la operación.
 * @param estatusId Es el ID del estatus al cual se quiere cambiar.
 * @param usuarioList Es una lista que contiene los registros los cuales se
 * quiere actualizar su Estatus.
     */
    public void setEstatus(Integer estatusId, List<Integer> usuarioList) {
        usuarioBss.setEstatus(obtieneSessionVar().getUsuarioId(), estatusId, usuarioList);
    }

    /*
 * Metodos que actualizan los campos <select> en el jsp despues de haber hecho
 * una edición de datos en un popup externo.
     */
    public List<Perfil> getPerfil() {
        return usuarioBss.getPerfil();
    }

    public String getReportDataTest(String order, String ordenTipo, Usuario usuario) {
        return usuarioBss.getReportDataTest(order, ordenTipo, usuario);
    }

    /*
 * Inyección de dependencias con Spring, cada
 * referencia definida al inicio de la clase requiere un método
 * Setter.
     */
    public void setUsuarioBss(UsuarioBss usuarioBss) {
        this.usuarioBss = usuarioBss;
    }

    /*
* Obtiene una lista de todos los registros válidos (activos y no eliminados) de la tabla PAGINA.
*
* @return regresa una lista de todos los registros de la tabla PAGINA
     */
    public List<Pagina> obtienePaginas() {
        return usuarioBss.obtienePaginas();
    }

    /*
* Obtiene una lista de todas las paginas a las que un determinado usuario tiene acceso.
*
* @param perfilId es el perfil del cual se quiere obtener los accesos
* @return regresa una lista de todos los registros de la tabla PAGINA
     */
    public List<Integer> obtieneAccesos(int perfilId) {
        return usuarioBss.obtieneAccesos(perfilId);
    }

}
