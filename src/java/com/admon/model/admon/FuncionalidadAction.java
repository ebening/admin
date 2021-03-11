package com.admon.model.admon;

import java.util.ArrayList;
import java.util.List;

import com.admon.bss.admon.FuncionalidadBss;
import com.admon.entity.admon.Aplicacion;
import com.admon.entity.admon.Catalogo;
import com.admon.entity.admon.Modulo;
import com.admon.entity.admon.Operacion;
import com.admon.model.BaseModel;

public class FuncionalidadAction extends BaseModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Aplicacion> aplicaciones;
	private List<Modulo> modulos;
        private List<Operacion> operaciones;
        private List<Catalogo> tipos;
	
    private FuncionalidadBss funcionalidadBss;
    private String nombreActionMenu;
    private String gridVisibleFuncionalidad;

    public FuncionalidadAction() {}

    public String execute() {
    	
    	Aplicacion a = new Aplicacion();
    	a.setNombre("Seleccione");
    	a.setAplicacionId(-1L);
    	aplicaciones = funcionalidadBss.getAplicaciones();
    	aplicaciones.add(0,a);
    	
    	Modulo m = new Modulo();
    	m.setNombre("Seleccione");
    	m.setModuloId(-1L);
    	modulos = new ArrayList<>();
    	modulos.add(m);
        
        Operacion o = new Operacion();
    	o.setNombre("Seleccione");
    	o.setOperacionId(-1L);
    	operaciones = new ArrayList<>();
    	operaciones.add(o);
        
        Catalogo c = new Catalogo();
    	c.setClave("Seleccione");
    	c.setCatalogoId(-1);
    	tipos = funcionalidadBss.getTipos("T_FUNCIONALIDAD"); //Todo:mover a properties
    	tipos.add(0,c);
    	
        setNombreActionMenu(funcionalidadBss.getNombreActionMenu());
        setGridVisibleFuncionalidad(funcionalidadBss.hasGrid());

        return SUCCESS;
    }

	public List<Aplicacion> getAplicaciones() {
		return aplicaciones;
	}

	public void setAplicaciones(List<Aplicacion> aplicaciones) {
		this.aplicaciones = aplicaciones;
	}

	public FuncionalidadBss getFuncionalidadBss() {
		return funcionalidadBss;
	}

	public void setFuncionalidadBss(FuncionalidadBss funcionalidadBss) {
		this.funcionalidadBss = funcionalidadBss;
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
	public String getGridVisibleFuncionalidad() {
		return gridVisibleFuncionalidad;
	}

	public void setGridVisibleFuncionalidad(String gridVisibleFuncionalidad) {
		this.gridVisibleFuncionalidad = gridVisibleFuncionalidad;
	}

        public List<Operacion> getOperaciones() {
            return operaciones;
        }

        public void setOperaciones(List<Operacion> operaciones) {
            this.operaciones = operaciones;
        }

        public List<Catalogo> getTipos() {
            return tipos;
        }

        public void setTipos(List<Catalogo> tipos) {
            this.tipos = tipos;
        }
        
       
}
