package com.admon.model.admon;

import java.util.ArrayList;
import java.util.List;

import com.admon.bss.admon.ModuloBss;
import com.admon.entity.admon.Aplicacion;
import com.admon.entity.admon.Organizacion;
import com.admon.model.BaseModel;

public class ModuloAction extends BaseModel {
	
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private List<Aplicacion> aplicaciones;
	
    private ModuloBss moduloBss;
    private String nombreActionMenu;
    private String gridVisibleModulo;

    public ModuloAction() {}

    public String execute() {
        
        Aplicacion a = new Aplicacion();
    	a.setNombre("--Seleccione--");
    	a.setAplicacionId(-1L);
    	aplicaciones = new ArrayList<>();
    	aplicaciones.add(a);
        aplicaciones = moduloBss.getAplicaciones();
    	aplicaciones.add(0,a);
                
        setNombreActionMenu(moduloBss.getNombreActionMenu());
        setGridVisibleModulo(moduloBss.hasGrid());

        return SUCCESS;
    }

	public List<Aplicacion> getAplicaciones() {
		return aplicaciones;
	}

	public void setAplicaciones(List<Aplicacion> aplicaciones) {
		this.aplicaciones = aplicaciones;
	}

	public ModuloBss getModuloBss() {
		return moduloBss;
	}

	public void setModuloBss(ModuloBss moduloBss) {
		this.moduloBss = moduloBss;
	}

	public String getNombreActionMenu() {
		return nombreActionMenu;
	}

	public void setNombreActionMenu(String nombreActionMenu) {
		this.nombreActionMenu = nombreActionMenu;
	}


	public String getGridVisibleModulo() {
		return gridVisibleModulo;
	}

	public void setGridVisibleModulo(String gridVisibleModulo) {
		this.gridVisibleModulo = gridVisibleModulo;
	}
}
