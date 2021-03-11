package com.admon.model.admon;

import com.admon.bss.admon.UsuarioBss;
import com.admon.entity.admon.Usuario;
import com.admon.model.BaseModel;
import java.util.ArrayList;
import java.util.List;


public class UsuarioAction extends BaseModel {

/* Inyeccion de dependencias con Spring. Estas dependencias se configuran en 
 * el applicationContext.xml, y ademas necesitan un metodo setter por cada 
 * variable el cual es anexado al final de la clase. */
private UsuarioBss usuarioBss;

 // Elementos de struts2 para el JSP
private List perfilUsuario = new ArrayList();
private String perfilUsuarioLink = new String();
private List estatusUsuario = new ArrayList();
private List eliminadoUsuario = new ArrayList();
String gridVisibleUsuario = new String();
String gridIndividualModeUsuario = new String();
String nombreActionMenu = new String();

public UsuarioAction() {
}

/*
 * Método que llena los elementos del formulario del JSP mediante Struts al cargar la página usuario.jsp 
 * @return Regresa un código de tipo String, el método execute() es ejecutado y la respuesta es evaluada, finalmente se manda llamar al JSP correspondiente según la respuesta.
 */
@Override
public String execute() {
setNombreActionMenu(usuarioBss.getNombreActionMenu());
 // <editor-fold defaultstate="collapsed" desc="Getters">
 // Obtener todos los registros de la tabla Perfil.
setPerfilUsuario(usuarioBss.getPerfil());
setPerfilUsuarioLink(getLink("Usuario","perfil"));
 // Obtener los parámetros del catálogo Estatus
 // por su key en com.admon.bss.resourcesCodesBss.properties
setEstatusUsuario(usuarioBss.getParametros("key_estatus"));
 // Obtener los parámetros del catálogo Eliminado
 // por su key en com.admon.bss.resourcesCodesBss.properties
setEliminadoUsuario(usuarioBss.getParametros("key_eliminado"));
 // </editor-fold>

setGridVisibleUsuario(usuarioBss.hasGrid());
setGridIndividualModeUsuario(usuarioBss.isIndividual());
return SUCCESS;
}

/* Cada elemento que será llenado por Struts al cargar la página
 * deben tener métodos Getter y Setter. */
/* Getter y Setter select */
public List getPerfilUsuario() {
return perfilUsuario;
}

public void setPerfilUsuario(List perfil) {
this.perfilUsuario = perfil;
}
/* Getter y Setter popup */
public String getPerfilUsuarioLink() {
return perfilUsuarioLink;
}

public void setPerfilUsuarioLink(String perfilUsuarioLink) {
this.perfilUsuarioLink = perfilUsuarioLink;
}

/* Getter y Setter select */
public List getEstatusUsuario() {
return estatusUsuario;
}

public void setEstatusUsuario(List estatus) {
this.estatusUsuario = estatus;
}
/* Getter y Setter select */
public List getEliminadoUsuario() {
return eliminadoUsuario;
}

public void setEliminadoUsuario(List eliminado) {
this.eliminadoUsuario = eliminado;
}

public String getGridVisibleUsuario() {
return gridVisibleUsuario;
}

public void setGridVisibleUsuario(String gridVisibleUsuario) {
this.gridVisibleUsuario = gridVisibleUsuario;
}

public String getGridIndividualModeUsuario() {
return gridIndividualModeUsuario;
}

public void setGridIndividualModeUsuario(String gridIndividualModeUsuario) {
this.gridIndividualModeUsuario = gridIndividualModeUsuario;
}
public String getNombreActionMenu() {
return nombreActionMenu;
}

public void setNombreActionMenu(String nombreActionMenu) {
this.nombreActionMenu = nombreActionMenu;
}


/*
 * Inyección de dependencias con Spring, cada
 * referencia definida al inicio de la clase requiere un método
 * Setter.
 */
public void setUsuarioBss(UsuarioBss usuarioBss) {
this.usuarioBss = usuarioBss;
}

}
