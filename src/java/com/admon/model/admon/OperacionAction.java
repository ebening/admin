package com.admon.model.admon;

import java.util.ArrayList;
import java.util.List;

import com.admon.bss.admon.OperacionBss;
import com.admon.entity.admon.Aplicacion;
import com.admon.entity.admon.Modulo;
import com.admon.model.BaseModel;

public class OperacionAction extends BaseModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Aplicacion> aplicaciones;
	private List<Modulo> modulos;
	
	private OperacionBss operacionBss;
    private String nombreActionMenu;
    private String gridVisibleOperacion;

    public OperacionAction() {}

    public String execute() {
    	
    	Aplicacion a = new Aplicacion();
    	a.setNombre("Seleccione");
    	a.setAplicacionId(-1L);
    	aplicaciones = operacionBss.getAplicaciones();
    	aplicaciones.add(0,a);
    	
    	Modulo m = new Modulo();
    	m.setNombre("Seleccione");
    	m.setModuloId(-1L);
    	modulos = new ArrayList<>();
    	modulos.add(m);
    	
        setNombreActionMenu(operacionBss.getNombreActionMenu());
        setGridVisibleOperacion(operacionBss.hasGrid());

        return SUCCESS;
    }

	public List<Aplicacion> getAplicaciones() {
		return aplicaciones;
	}

	public void setAplicaciones(List<Aplicacion> aplicaciones) {
		this.aplicaciones = aplicaciones;
	}

	public OperacionBss getOperacionBss() {
		return operacionBss;
	}

	public void setOperacionBss(OperacionBss operacionBss) {
		this.operacionBss = operacionBss;
	}

	public String getNombreActionMenu() {
		return nombreActionMenu;
	}

	public void setNombreActionMenu(String nombreActionMenu) {
		this.nombreActionMenu = nombreActionMenu;
	}

	public List<Modulo> getModulos() {
		return modulos;
	}

	public void setModulos(List<Modulo> modulos) {
		this.modulos = modulos;
	}

	public String getGridVisibleOperacion() {
		return gridVisibleOperacion;
	}

	public void setGridVisibleOperacion(String gridVisibleOperacion) {
		this.gridVisibleOperacion = gridVisibleOperacion;
	}
}
