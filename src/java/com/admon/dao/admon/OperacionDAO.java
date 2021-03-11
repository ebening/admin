package com.admon.dao.admon;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.tool.hbm2x.StringUtils;

import com.admon.dao.GenericDAOImpl;
import com.admon.entity.admon.Aplicacion;
import com.admon.entity.admon.Modulo;
import com.admon.entity.admon.Operacion;

public class OperacionDAO extends GenericDAOImpl<Operacion, Long> {

    /*
 * Método para tipar el objeto genérico. Es utilizado
 * en la implementación del GenericDAO para identificar
 * el objeto genérico la cual es necesaria para realizar
 * las consultas a la BD.
 * @return Regresa un objeto <b>Class</b> que identifica el tipo genérico. */
    @Override
    protected Class<Operacion> getEntityClass() {
        return Operacion.class;
    }
    
    @SuppressWarnings("unchecked")
	public Operacion getOperacion(Long id){
    	Operacion operacion = new Operacion();
    	Session s = getSessionFactory().openSession();
		List<Object[]> list = s.createSQLQuery("SELECT OPERACION_ID,MODULO_ID,APLICACION_ID,URL,ORDEN,DESCRIPCION,NOMBRE_ACCION,HTML_ID,"
				+ " CREACION_FECHA,CREACION_USUARIO,MODIFICACION_FECHA,MODIFICACION_USUARIO,ESTATUS_ID,ELIMINADO_ID, "
				+ " (SELECT NOMBRE FROM SEGURIDAD_OPERACION_PARAMETROS SOP WHERE SO.OPERACION_ID=SOP.OPERACION_ID AND SOP.LENGUAJE_ID=1) AS NOMBRE_ES, "
				+ " (SELECT NOMBRE FROM SEGURIDAD_OPERACION_PARAMETROS SOP WHERE SO.OPERACION_ID=SOP.OPERACION_ID AND SOP.LENGUAJE_ID=2) AS NOMBRE_EN "
				+ " FROM SEGURIDAD_OPERACION SO WHERE OPERACION_ID="+id).list();
		for(Object[] o: list){
			Integer index=0;
			operacion.setOperacionId(((BigDecimal)o[index++]).longValue());
			operacion.setModuloId(((BigDecimal)o[index++]).longValue());
			operacion.setAplicacionId(((BigDecimal)o[index++]).longValue());
			operacion.setUrl((String)o[index++]);
			operacion.setOrden(o[index]==null?null:((BigDecimal)o[index++]).intValue());
			operacion.setDescripcion((String)o[index++]);
			operacion.setNombreAccion((String)o[index++]);
			operacion.setHtmlId((String)o[index++]);
			Object date = o[index++];
			if(date!=null && date instanceof Date){
				operacion.setCreacionFecha(new Timestamp(((Date)date).getTime()));
			}
			if(date!=null && date instanceof Timestamp){
				operacion.setCreacionFecha((Timestamp)date);
			}
			operacion.setCreacionUsuario(o[index]==null?null:((BigDecimal)o[index++]).intValue());
			date = o[index++];
			if(date!=null && date instanceof Date){
				operacion.setModificacionFecha(new Timestamp(((Date)date).getTime()));
			}
			if(date!=null && date instanceof Timestamp){
				operacion.setModificacionFecha((Timestamp)date);
			}
			operacion.setModificacionUsuario(o[index]==null?null:((BigDecimal)o[index++]).intValue());
			operacion.setEstatusId(o[index]==null?null:((BigDecimal)o[index++]).intValue());
			operacion.setEliminadoId(o[index]==null?null:((BigDecimal)o[index++]).intValue());
			operacion.setNombreES((String)o[index++]);
			operacion.setNombreEN((String)o[index++]);
    		break;
    	}
		s.close();
    	return operacion;
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
				Modulo a = new Modulo();
	    		a.setModuloId(((BigDecimal)o[0]).longValue());
	    		a.setNombre((String)o[1]);
	    		modulos.add(a);
	    	}
			s.close();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return modulos;
    }
    
    private String getQueryPrincipal(Map<String, Object> parametros){
    	StringBuilder query = new StringBuilder();
		query.append("SELECT ROWID AS rid FROM SEGURIDAD_OPERACION SO WHERE SO.ELIMINADO_ID=0 ");

		StringBuilder filter = new StringBuilder();
		if(StringUtils.isNotEmpty((String)parametros.get("operacion"))){
			filter.append(" AND SO.OPERACION_ID IN (SELECT SOP.OPERACION_ID FROM SEGURIDAD_OPERACION_PARAMETROS SOP WHERE SOP.NOMBRE LIKE '%").append((String)parametros.get("operacion")).append("%' )");
		}
		if(StringUtils.isNotEmpty((String)parametros.get("aplicacion"))){
			filter.append(" AND SO.APLICACION_ID IN (SELECT A.APLICACION_ID FROM SEGURIDAD_APLICACION A WHERE NOMBRE LIKE '%").append((String)parametros.get("aplicacion")).append("%' OR NOMBRE_INGLES LIKE '%").append((String)parametros.get("aplicacion")).append("%') ");
		}
		if(StringUtils.isNotEmpty((String)parametros.get("modulo"))){
			filter.append(" AND SO.MODULO_ID IN (SELECT MP.MODULO_ID FROM SEGURIDAD_MODULO_PARAMETROS MP WHERE MP.NOMBRE LIKE '%").append((String)parametros.get("modulo")).append("%') ");
		}
		if(StringUtils.isNotEmpty((String)parametros.get("descripcion"))){
			filter.append(" AND SO.DESCRIPCION LIKE '%").append((String)parametros.get("descripcion")).append("%'");
		}
		if(StringUtils.isNotEmpty((String)parametros.get("nombreAccion"))){
			filter.append(" AND SO.NOMBRE_ACCION LIKE '%").append((String)parametros.get("nombreAccion")).append("%'");
		}
		if(StringUtils.isNotEmpty((String)parametros.get("htmlId"))){
			filter.append(" AND SO.HTML_ID LIKE '%").append((String)parametros.get("htmlId")).append("%'");
		}
		if(StringUtils.isNotEmpty((String)parametros.get("url"))){
			filter.append(" AND SO.URL LIKE '%").append((String)parametros.get("url")).append("%'");
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
    			"(SELECT NOMBRE FROM SEGURIDAD_MODULO_PARAMETROS MP WHERE M.MODULO_ID=MP.MODULO_ID AND MP.LENGUAJE_ID=1) AS MODULO, "+ 
    			"(SELECT NOMBRE FROM SEGURIDAD_OPERACION_PARAMETROS SOP WHERE SO.OPERACION_ID=SOP.OPERACION_ID AND SOP.LENGUAJE_ID=1) AS OPERACION_ES, "+ 
    			"(SELECT NOMBRE FROM SEGURIDAD_OPERACION_PARAMETROS SOP WHERE SO.OPERACION_ID=SOP.OPERACION_ID AND SOP.LENGUAJE_ID=2) AS OPERACION_EN, "+ 
    			"SO.DESCRIPCION, SO.MODIFICACION_FECHA, SO.MODIFICACION_USUARIO,  "+
    			"(SELECT USUARIO FROM USUARIO U WHERE U.USUARIO_ID=SO.MODIFICACION_USUARIO) AS USUARIO, "+ 
    			"SO.ELIMINADO_ID, SO.OPERACION_ID, SO.ESTATUS_ID "+ 
    			"FROM SEGURIDAD_APLICACION A, SEGURIDAD_MODULO M, SEGURIDAD_OPERACION SO, (SELECT i.* "+
				"          FROM (SELECT i.*, ROWNUM AS rn  "+
				"                  FROM ( "+getQueryPrincipal(parametros)+" ) i "+
				"                 WHERE ROWNUM <= "+indexFinal+
				"               ) i "+
				"         WHERE rn >= "+indexInicio+" "+
				"       ) i "+
				"WHERE i.rid = SO.ROWID "+
				"AND SO.MODULO_ID=M.MODULO_ID AND M.APLICACION_ID=A.APLICACION_ID";
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
	public List<Operacion> getOperaciones(Map<String, Object> parametros) {
    	String query = getQuery(parametros);
    	List<Operacion> list = new ArrayList<>();
    	Session s = getSessionFactory().openSession();
    	List<Object[]> l = s.createSQLQuery(query).list();
    	for(Object[] ob: l){
    		Integer index = 0;
    		Operacion o = new Operacion();
    		o.setAplicacionId(((BigDecimal)ob[index++]).longValue());
    		o.setAplicacion((String)ob[index++]);
    		o.setModuloId(((BigDecimal)ob[index++]).longValue());
    		o.setModulo((String)ob[index++]);
    		o.setNombreES((String)ob[index++]);
    		o.setNombreEN((String)ob[index++]);
    		o.setDescripcion((String)ob[index++]);
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
    		o.setOperacionId(Long.valueOf(ob[index++].toString()));
    		o.setEstatusId(Integer.valueOf(ob[index++].toString()));
    		list.add(o);
    	}
    	s.close();
    	return list;
    }
    
    public void saveOperacion(Operacion o){
    	Session s = getSessionFactory().openSession();
    	Transaction t = s.beginTransaction();
    	
    	String query = "SELECT OPERACION_SEQ.NEXTVAL FROM DUAL";
    	Query q = s.createSQLQuery(query);
    	Long id = Long.valueOf(q.uniqueResult().toString());
    	
    	
    	query = "INSERT INTO SEGURIDAD_OPERACION (OPERACION_ID, MODULO_ID, APLICACION_ID, URL, ORDEN, DESCRIPCION, NOMBRE_ACCION, HTML_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA, MODIFICACION_USUARIO, ESTATUS_ID, ELIMINADO_ID) "
    			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    	
    	Integer index = 0;
    	q = s.createSQLQuery(query);
    	q.setLong(index++, id);
    	q.setLong(index++, o.getModuloId());
    	q.setLong(index++, o.getAplicacionId());
    	q.setString(index++, o.getUrl());
    	q.setInteger(index++, o.getOrden());
    	q.setString(index++, o.getDescripcion());
    	q.setString(index++, o.getNombreAccion());
    	q.setString(index++, o.getHtmlId());
    	q.setTimestamp(index++, o.getCreacionFecha());
    	q.setInteger(index++, o.getCreacionUsuario());
    	q.setTimestamp(index++, o.getModificacionFecha());
    	q.setInteger(index++, o.getModificacionUsuario());
    	q.setInteger(index++, o.getEstatusId());
    	q.setInteger(index++, o.getEliminadoId());
    	q.executeUpdate();
    	
    	query = "INSERT INTO SEGURIDAD_OPERACION_PARAMETROS (OPERACION_PARAMETRO_ID, OPERACION_ID, LENGUAJE_ID, NOMBRE, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA, MODIFICACION_USUARIO) "
    			+ "VALUES 	(OPERACION_PARAMETRO_SEQ.NEXTVAL,?,?,?,?,?,?,?)";
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
    
    public void update(Operacion o){
    	Session s = getSessionFactory().openSession();
    	Transaction t = s.beginTransaction();
    	String statusQuery="";
    	if(o.getEstatusId()!=null){
    		statusQuery = ", ESTATUS_ID=? ";
    	}
    	String query = "UPDATE ADMON.SEGURIDAD_OPERACION SET MODULO_ID=?, APLICACION_ID=?, URL=?, ORDEN=?, DESCRIPCION=?, NOMBRE_ACCION=?, HTML_ID=?, MODIFICACION_FECHA=?, MODIFICACION_USUARIO=? "+statusQuery
    			+ "WHERE OPERACION_ID=?";
    	Integer index = 0;
    	Query q = s.createSQLQuery(query);
    	q.setLong(index++, o.getModuloId());
    	q.setLong(index++, o.getAplicacionId());
    	q.setString(index++, o.getUrl());
    	q.setInteger(index++, o.getOrden());
    	q.setString(index++, o.getDescripcion());
    	q.setString(index++, o.getNombreAccion());
    	q.setString(index++, o.getHtmlId());
    	q.setTimestamp(index++, o.getModificacionFecha());
    	q.setInteger(index++, o.getModificacionUsuario());
    	if(o.getEstatusId()!=null) q.setInteger(index++, o.getEstatusId());
    	q.setLong(index++, o.getOperacionId());
    	q.executeUpdate();
    	
    	query = "UPDATE ADMON.SEGURIDAD_OPERACION_PARAMETROS SET NOMBRE=?, MODIFICACION_FECHA=?, MODIFICACION_USUARIO=? "
    			+ " WHERE OPERACION_ID=? AND LENGUAJE_ID=1";
    	q = s.createSQLQuery(query);
    	index = 0;
    	q.setString(index++, o.getNombreES());
    	q.setTimestamp(index++, o.getModificacionFecha());
    	q.setInteger(index++, o.getModificacionUsuario());
    	q.setLong(index++, o.getOperacionId());
    	q.executeUpdate();
    	
    	query = "UPDATE ADMON.SEGURIDAD_OPERACION_PARAMETROS SET NOMBRE=?, MODIFICACION_FECHA=?, MODIFICACION_USUARIO=? "
    			+ " WHERE OPERACION_ID=? AND LENGUAJE_ID=2";
    	q = s.createSQLQuery(query);
    	index = 0;
    	q.setString(index++, o.getNombreEN());
    	q.setTimestamp(index++, o.getModificacionFecha());
    	q.setInteger(index++, o.getModificacionUsuario());
    	q.setLong(index++, o.getOperacionId());
    	q.executeUpdate();
    	
    	t.commit();
    	s.close();
    }
    
    public void logicDelete(Operacion o){
    	Session s = getSessionFactory().openSession();
    	Transaction t = s.beginTransaction();
    	String query = "UPDATE ADMON.SEGURIDAD_OPERACION SET ELIMINADO_ID=?, MODIFICACION_FECHA=?, MODIFICACION_USUARIO=? "
    			+ "WHERE OPERACION_ID=? ";
    	Integer index = 0;
    	Query q = s.createSQLQuery(query);
    	q.setInteger(index++, o.getEliminadoId());
    	q.setTimestamp(index++, o.getModificacionFecha());
    	q.setInteger(index++, o.getModificacionUsuario());
    	q.setLong(index++, o.getOperacionId());
    	q.executeUpdate();
    	t.commit();
    	s.close();
    }
}
