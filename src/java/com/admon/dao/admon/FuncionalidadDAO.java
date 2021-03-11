package com.admon.dao.admon;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.admon.dao.GenericDAOImpl;
import com.admon.entity.admon.Aplicacion;
import com.admon.entity.admon.Catalogo;
import com.admon.entity.admon.Modulo;
import com.admon.entity.admon.Funcionalidad;
import com.admon.entity.admon.Operacion;
import java.sql.Date;
import org.hibernate.tool.hbm2x.StringUtils;

public class FuncionalidadDAO extends GenericDAOImpl<Funcionalidad, Long> {

   /*
 * Método para tipar el objeto genérico. Es utilizado
 * en la implementación del GenericDAO para identificar
 * el objeto genérico la cual es necesaria para realizar
 * las consultas a la BD.
 * @return Regresa un objeto <b>Class</b> que identifica el tipo genérico. */
    @Override
    protected Class<Funcionalidad> getEntityClass() {
        return Funcionalidad.class;
    }
    
    @SuppressWarnings("unchecked")
	public Funcionalidad getFuncionalidad(Long id){
    	Funcionalidad funcionalidad = new Funcionalidad();
    	Session s = getSessionFactory().openSession();
		List<Object[]> list = s.createSQLQuery("SELECT FUNCIONALIDAD_ID,OPERACION_ID,MODULO_ID,APLICACION_ID,DESCRIPCION, METODO, HTML_ID,TIPO_FUNCIONALIDAD_ID,"
				+ " CREACION_FECHA,CREACION_USUARIO,MODIFICACION_FECHA,MODIFICACION_USUARIO,ESTATUS_ID,ELIMINADO_ID, "
				+ " (SELECT NOMBRE FROM SEGURIDAD_OPER_FUNC_PARAMETROS SFP WHERE SF.FUNCIONALIDAD_ID=SFP.FUNCIONALIDAD_ID AND SFP.LENGUAJE_ID=1) AS NOMBRE_ES, "
				+ " (SELECT NOMBRE FROM SEGURIDAD_OPER_FUNC_PARAMETROS SFP WHERE SF.FUNCIONALIDAD_ID=SFP.FUNCIONALIDAD_ID AND SFP.LENGUAJE_ID=2) AS NOMBRE_EN "
				+ " FROM SEGURIDAD_OPERA_FUNCIONALIDAD SF WHERE FUNCIONALIDAD_ID="+id).list();
		for(Object[] o: list){
			Integer index=0;
			funcionalidad.setFuncionalidadId(((BigDecimal)o[index++]).longValue());
                        funcionalidad.setOperacionId(((BigDecimal)o[index++]).longValue());
			funcionalidad.setModuloId(((BigDecimal)o[index++]).longValue());
			funcionalidad.setAplicacionId(((BigDecimal)o[index++]).longValue());
			funcionalidad.setDescripcion((String)o[index++]);
                        funcionalidad.setMetodo((String)o[index++]);
			funcionalidad.setHtmlId((String)o[index++]);
                        funcionalidad.setTipoId(((BigDecimal)o[index++]).longValue());
			Object date = o[index++];
			if(date!=null && date instanceof Date){
				funcionalidad.setCreacionFecha(new Timestamp(((Date)date).getTime()));
			}
			if(date!=null && date instanceof Timestamp){
				funcionalidad.setCreacionFecha((Timestamp)date);
			}
			funcionalidad.setCreacionUsuario(o[index]==null?null:((BigDecimal)o[index++]).intValue());
			date = o[index++];
			if(date!=null && date instanceof Date){
				funcionalidad.setModificacionFecha(new Timestamp(((Date)date).getTime()));
			}
			if(date!=null && date instanceof Timestamp){
				funcionalidad.setModificacionFecha((Timestamp)date);
			}
			funcionalidad.setModificacionUsuario(o[index]==null?null:((BigDecimal)o[index++]).intValue());
			funcionalidad.setEstatusId(o[index]==null?null:((BigDecimal)o[index++]).intValue());
			funcionalidad.setEliminadoId(o[index]==null?null:((BigDecimal)o[index++]).intValue());
			funcionalidad.setNombreES((String)o[index++]);
			funcionalidad.setNombreEN((String)o[index++]);
    		break;
    	}
		s.close();
    	return funcionalidad;
    }

    @SuppressWarnings("unchecked")
    public List<Aplicacion> getAplicaciones(){
    	List<Aplicacion> aplicaciones = new ArrayList<>();
    	try{
    		Session s = getSessionFactory().openSession();
			List<Object[]> list = s.createSQLQuery("SELECT APLICACION_ID, NOMBRE FROM SEGURIDAD_APLICACION WHERE ELIMINADO_ID=0").list();
			for(Object[] o: list){
	    		Aplicacion a = new Aplicacion();
	    		a.setAplicacionId(((BigDecimal)o[0]).longValue());
	    		a.setNombre((String)o[1]);
	    		aplicaciones.add(a);
	    	}
			s.close();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return aplicaciones;
    }

    @SuppressWarnings("unchecked")
    public List<Modulo> getModulos(Long aplicacionId){
    	List<Modulo> modulos = new ArrayList<>();
    	try{
    		Session s = getSessionFactory().openSession();
			List<Object[]> list = s.createSQLQuery("SELECT MP.MODULO_ID, MP.NOMBRE "+
					"FROM SEGURIDAD_MODULO M, SEGURIDAD_MODULO_PARAMETROS MP "+
					"WHERE M.MODULO_ID = MP.MODULO_ID AND LENGUAJE_ID=1 "+
					"AND ELIMINADO_ID=0 AND M.APLICACION_ID="+aplicacionId).list();
			for(Object[] o: list){
				Modulo m = new Modulo();
	    		m.setModuloId(((BigDecimal)o[0]).longValue());
	    		m.setNombre((String)o[1]);
	    		modulos.add(m);
	    	}
			s.close();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return modulos;
    }
    
        @SuppressWarnings("unchecked")
    public List<Operacion> getOperaciones(Long moduloId){
    	List<Operacion> operaciones = new ArrayList<>();
    	try{
    		Session s = getSessionFactory().openSession();
			List<Object[]> list = s.createSQLQuery("SELECT SOP.OPERACION_ID, SOP.NOMBRE "+
					"FROM SEGURIDAD_OPERACION SO, SEGURIDAD_OPERACION_PARAMETROS SOP "+
					"WHERE SO.OPERACION_ID = SOP.OPERACION_ID AND LENGUAJE_ID=1 "+
					"AND ELIMINADO_ID=0 AND SO.MODULO_ID="+moduloId).list();
			for(Object[] o: list){
				Operacion op = new Operacion();
	    		op.setOperacionId(((BigDecimal)o[0]).longValue());
	    		op.setNombre((String)o[1]);
	    		operaciones.add(op);
	    	}
			s.close();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return operaciones;
    }
    
            @SuppressWarnings("unchecked")
    public List<Catalogo> getTipos(String clave){
    	List<Catalogo> tipos = new ArrayList<>();
    	try{
    		Session s = getSessionFactory().openSession();
			List<Object[]> list = s.createSQLQuery("SELECT CP.CATALOGO_PARAMETRO_ID, CP.CLAVE "+
					"FROM CATALOGO C, CATALOGO_PARAMETRO CP "+
					"WHERE C.CATALOGO_ID = CP.CATALOGO_ID "+
					"AND CP.ELIMINADO_ID=0 AND C.CLAVE ='"+clave+"'").list();
			for(Object[] o: list){
				Catalogo c = new Catalogo();
	    		c.setCatalogoId(((BigDecimal)o[0]).intValue());
	    		c.setClave((String)o[1]);
	    		tipos.add(c);
	    	}
			s.close();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return tipos;
    }
    
    private String getQueryPrincipal(Map<String, Object> parametros){
    	StringBuilder query = new StringBuilder();
		query.append("SELECT ROWID AS rid FROM SEGURIDAD_OPERA_FUNCIONALIDAD SF WHERE SF.ELIMINADO_ID=0 ");

		StringBuilder filter = new StringBuilder();
		if(StringUtils.isNotEmpty((String)parametros.get("funcionalidad"))){
			filter.append(" AND SF.FUNCIONALIDAD_ID IN (SELECT SFP.FUNCIONALIDAD_ID FROM SEGURIDAD_OPER_FUNC_PARAMETROS SFP WHERE SFP.NOMBRE LIKE '%").append((String)parametros.get("funcionalidad")).append("%' )");
		}
		if(StringUtils.isNotEmpty((String)parametros.get("aplicacion"))){
			filter.append(" AND SF.APLICACION_ID IN (SELECT A.APLICACION_ID FROM SEGURIDAD_APLICACION A WHERE NOMBRE LIKE '%").append((String)parametros.get("aplicacion")).append("%' OR NOMBRE_INGLES LIKE '%").append((String)parametros.get("aplicacion")).append("%') ");
		}
		if(StringUtils.isNotEmpty((String)parametros.get("modulo"))){
			filter.append(" AND SF.MODULO_ID IN (SELECT MP.MODULO_ID FROM SEGURIDAD_MODULO_PARAMETROS MP WHERE MP.NOMBRE LIKE '%").append((String)parametros.get("modulo")).append("%') ");
		}
		if(StringUtils.isNotEmpty((String)parametros.get("descripcion"))){
			filter.append(" AND SF.DESCRIPCION LIKE '%").append((String)parametros.get("descripcion")).append("%'");
		}
		if(StringUtils.isNotEmpty((String)parametros.get("nombreAccion"))){
			filter.append(" AND SF.NOMBRE_ACCION LIKE '%").append((String)parametros.get("nombreAccion")).append("%'");
		}
		if(StringUtils.isNotEmpty((String)parametros.get("htmlId"))){
			filter.append(" AND SF.HTML_ID LIKE '%").append((String)parametros.get("htmlId")).append("%'");
		}
		if(StringUtils.isNotEmpty((String)parametros.get("url"))){
			filter.append(" AND SF.URL LIKE '%").append((String)parametros.get("url")).append("%'");
		}
		
		query.append(" ORDER BY MODIFICACION_FECHA DESC");
		return query.toString();
    }
    
    private String getQuery(Map<String, Object> parametros){
    	Integer indexInicio = (Integer) parametros.get("indexInicio");
		Integer indexFinal = (Integer) parametros.get("indexFinal");
		if(indexInicio==null) indexInicio=1;
		if(indexFinal==null) indexFinal=10;
		
    	String query = "SELECT A.APLICACION_ID, A.NOMBRE AS APLICACION, M.MODULO_ID, "+
    			"(SELECT NOMBRE FROM SEGURIDAD_MODULO_PARAMETROS MP WHERE M.MODULO_ID=MP.MODULO_ID AND MP.LENGUAJE_ID=1) AS MODULO, SO.OPERACION_ID,"+ 
                        "(SELECT NOMBRE FROM SEGURIDAD_OPERACION_PARAMETROS SOP WHERE SO.OPERACION_ID=SOP.OPERACION_ID AND SOP.LENGUAJE_ID=1) AS OPERACION, "+
    			"(SELECT NOMBRE FROM SEGURIDAD_OPER_FUNC_PARAMETROS SFP WHERE SF.FUNCIONALIDAD_ID=SFP.FUNCIONALIDAD_ID AND SFP.LENGUAJE_ID=1) AS FUNCIONALIDAD_ES, "+ 
    			"(SELECT NOMBRE FROM SEGURIDAD_OPER_FUNC_PARAMETROS SFP WHERE SF.FUNCIONALIDAD_ID=SFP.FUNCIONALIDAD_ID AND SFP.LENGUAJE_ID=2) AS FUNCIONALIDAD_EN, SF.TIPO_FUNCIONALIDAD_ID, "+ 
    			"(SELECT CLAVE FROM CATALOGO_PARAMETRO CP WHERE SF.TIPO_FUNCIONALIDAD_ID=CP.CATALOGO_PARAMETRO_ID) AS TIPO, "+
                        "SF.DESCRIPCION, SF.METODO, SF.MODIFICACION_FECHA, SF.MODIFICACION_USUARIO,  "+
    			"(SELECT USUARIO FROM USUARIO U WHERE U.USUARIO_ID=SF.MODIFICACION_USUARIO) AS USUARIO, "+ 
    			"SF.ELIMINADO_ID, SF.FUNCIONALIDAD_ID, SF.ESTATUS_ID "+ 
    			"FROM SEGURIDAD_APLICACION A, SEGURIDAD_MODULO M, SEGURIDAD_OPERACION SO, SEGURIDAD_OPERA_FUNCIONALIDAD SF, (SELECT i.* "+
				"          FROM (SELECT i.*, ROWNUM AS rn  "+
				"                  FROM ( "+getQueryPrincipal(parametros)+" ) i "+
				"                 WHERE ROWNUM <= "+indexFinal+
				"               ) i "+
				"         WHERE rn >= "+indexInicio+" "+
				"       ) i "+
				"WHERE i.rid = SF.ROWID "+
				"AND SF.MODULO_ID=M.MODULO_ID AND M.APLICACION_ID=A.APLICACION_ID  AND SF.OPERACION_ID = SO.OPERACION_ID";
    	return query;
    }
    
    public int getCount(Map<String, Object> parametros){
    	Integer count = 0;
		String query = "select count(1) from ("+ getQueryPrincipal(parametros)+")";
		Session s = getSessionFactory().openSession();
		count = Integer.valueOf(s.createSQLQuery(query).uniqueResult().toString());
		s.close();
		return count;
	}
    
    @SuppressWarnings("unchecked")
	public List<Funcionalidad> getFuncionalidades(Map<String, Object> parametros) {
    	String query = getQuery(parametros);
    	List<Funcionalidad> list = new ArrayList<>();
    	Session s = getSessionFactory().openSession();
    	List<Object[]> l = s.createSQLQuery(query).list();
    	for(Object[] ob: l){
    		Integer index = 0;
    		Funcionalidad o = new Funcionalidad();
    		o.setAplicacionId(((BigDecimal)ob[index++]).longValue());
    		o.setAplicacion((String)ob[index++]);
    		o.setModuloId(((BigDecimal)ob[index++]).longValue());
    		o.setModulo((String)ob[index++]);
                o.setOperacionId(((BigDecimal)ob[index++]).longValue());
    		o.setOperacion((String)ob[index++]);
    		o.setNombreES((String)ob[index++]);
    		o.setNombreEN((String)ob[index++]);
                o.setTipoId(((BigDecimal)ob[index++]).longValue());
    		o.setTipo((String)ob[index++]);
                o.setDescripcion((String)ob[index++]);
    		o.setMetodo((String)ob[index++]);
    		Object date = ob[index++];
			if(date!=null && date instanceof Date){
				o.setModificacionFecha(new Timestamp(((Date)date).getTime()));
			}
			if(date!=null && date instanceof Timestamp){
				o.setModificacionFecha((Timestamp)date);
			}
			o.setModificacionUsuario(((BigDecimal)ob[index++]).intValue());
    		o.setModificacionUsuarioStr((String)ob[index++]);
    		o.setEliminadoId(Integer.valueOf(ob[index++].toString()));
    		o.setFuncionalidadId(Long.valueOf(ob[index++].toString()));
    		o.setEstatusId(Integer.valueOf(ob[index++].toString()));
    		list.add(o);
    	}
    	s.close();
    	return list;
    }
    
    public void saveFuncionalidad(Funcionalidad o){
    	Session s = getSessionFactory().openSession();
    	Transaction t = s.beginTransaction();
    	
    	String query = "SELECT FUNCIONALIDAD_SEQ.NEXTVAL FROM DUAL";
    	Query q = s.createSQLQuery(query);
    	Long id = Long.valueOf(q.uniqueResult().toString());
    	
    	
    	query = "INSERT INTO SEGURIDAD_OPERA_FUNCIONALIDAD (FUNCIONALIDAD_ID, OPERACION_ID, MODULO_ID, APLICACION_ID, DESCRIPCION, METODO, HTML_ID, TIPO_FUNCIONALIDAD_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA, MODIFICACION_USUARIO, ESTATUS_ID, ELIMINADO_ID) "
    			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    	
    	Integer index = 0;
    	q = s.createSQLQuery(query);
    	q.setLong(index++, id);
        q.setLong(index++, o.getOperacionId());
    	q.setLong(index++, o.getModuloId());
    	q.setLong(index++, o.getAplicacionId());
    	q.setString(index++, o.getDescripcion());
        q.setString(index++, o.getMetodo());
    	q.setString(index++, o.getHtmlId());
        q.setLong(index++, o.getTipoId());
    	q.setTimestamp(index++, o.getCreacionFecha());
    	q.setInteger(index++, o.getCreacionUsuario());
    	q.setTimestamp(index++, o.getModificacionFecha());
    	q.setInteger(index++, o.getModificacionUsuario());
    	q.setInteger(index++, o.getEstatusId());
    	q.setInteger(index++, o.getEliminadoId());
    	q.executeUpdate();
    	
    	query = "INSERT INTO SEGURIDAD_OPER_FUNC_PARAMETROS (OPER_FUNC_PARAMETRO_ID, FUNCIONALIDAD_ID, LENGUAJE_ID, NOMBRE, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA, MODIFICACION_USUARIO) "
    			+ "VALUES 	(FUNCIONALIDAD_PARAM_SEQ.NEXTVAL,?,?,?,?,?,?,?)";
    	index = 0;
    	q = s.createSQLQuery(query);
    	q.setLong(index++, id);
    	q.setLong(index++, 1);
    	q.setString(index++, o.getNombreES());
    	q.setTimestamp(index++, o.getCreacionFecha());
    	q.setInteger(index++, o.getCreacionUsuario());
    	q.setTimestamp(index++, o.getModificacionFecha());
    	q.setInteger(index++, o.getModificacionUsuario());
    	q.executeUpdate();
    	
    	index = 0;
    	q = s.createSQLQuery(query);
    	q.setLong(index++, id);
    	q.setLong(index++, 2);
    	q.setString(index++, o.getNombreEN());
    	q.setTimestamp(index++, o.getCreacionFecha());
    	q.setInteger(index++, o.getCreacionUsuario());
    	q.setTimestamp(index++, o.getModificacionFecha());
    	q.setInteger(index++, o.getModificacionUsuario());
    	q.executeUpdate();
    	
    	t.commit();
    	s.close();
    }
    
    public void update(Funcionalidad o){
    	Session s = getSessionFactory().openSession();
    	Transaction t = s.beginTransaction();
    	String statusQuery="";
    	if(o.getEstatusId()!=null){
    		statusQuery = ", ESTATUS_ID=? ";
    	}
    	String query = "UPDATE ADMON.SEGURIDAD_OPERA_FUNCIONALIDAD SET OPERACION_ID = ?, MODULO_ID=?, APLICACION_ID=?, DESCRIPCION=?, METODO=?, HTML_ID=?, TIPO_FUNCIONALIDAD_ID=?, MODIFICACION_FECHA=?, MODIFICACION_USUARIO=? "+statusQuery
    			+ "WHERE FUNCIONALIDAD_ID=?";
    	Integer index = 0;
    	Query q = s.createSQLQuery(query);
    	q.setLong(index++, o.getOperacionId());
        q.setLong(index++, o.getModuloId());
    	q.setLong(index++, o.getAplicacionId());
    	q.setString(index++, o.getDescripcion());
        q.setString(index++, o.getMetodo());
    	q.setString(index++, o.getHtmlId());
        q.setLong(index++, o.getTipoId());
    	q.setTimestamp(index++, o.getModificacionFecha());
    	q.setInteger(index++, o.getModificacionUsuario());
    	if(o.getEstatusId()!=null) q.setInteger(index++, o.getEstatusId());
    	q.setLong(index++, o.getFuncionalidadId());
    	q.executeUpdate();
    	
    	query = "UPDATE ADMON.SEGURIDAD_OPER_FUNC_PARAMETROS SET NOMBRE=?, MODIFICACION_FECHA=?, MODIFICACION_USUARIO=? "
    			+ " WHERE FUNCIONALIDAD_ID=? AND LENGUAJE_ID=1";
    	q = s.createSQLQuery(query);
    	index = 0;
    	q.setString(index++, o.getNombreES());
    	q.setTimestamp(index++, o.getModificacionFecha());
    	q.setInteger(index++, o.getModificacionUsuario());
    	q.setLong(index++, o.getFuncionalidadId());
    	q.executeUpdate();
    	
    	query = "UPDATE ADMON.SEGURIDAD_OPER_FUNC_PARAMETROS SET NOMBRE=?, MODIFICACION_FECHA=?, MODIFICACION_USUARIO=? "
    			+ " WHERE FUNCIONALIDAD_ID=? AND LENGUAJE_ID=2";
    	q = s.createSQLQuery(query);
    	index = 0;
    	q.setString(index++, o.getNombreEN());
    	q.setTimestamp(index++, o.getModificacionFecha());
    	q.setInteger(index++, o.getModificacionUsuario());
    	q.setLong(index++, o.getFuncionalidadId());
    	q.executeUpdate();
    	
    	t.commit();
    	s.close();
    }
    
    public void logicDelete(Funcionalidad o){
    	Session s = getSessionFactory().openSession();
    	Transaction t = s.beginTransaction();
    	String query = "UPDATE ADMON.SEGURIDAD_OPERA_FUNCIONALIDAD SET ELIMINADO_ID=?, MODIFICACION_FECHA=?, MODIFICACION_USUARIO=? "
    			+ "WHERE FUNCIONALIDAD_ID=? ";
    	Integer index = 0;
    	Query q = s.createSQLQuery(query);
    	q.setInteger(index++, o.getEliminadoId());
    	q.setTimestamp(index++, o.getModificacionFecha());
    	q.setInteger(index++, o.getModificacionUsuario());
    	q.setLong(index++, o.getFuncionalidadId());
    	q.executeUpdate();
    	t.commit();
    	s.close();
    }
}
