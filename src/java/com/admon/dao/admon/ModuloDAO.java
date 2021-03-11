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

import com.admon.dao.GenericDAOImpl;
import com.admon.entity.admon.Aplicacion;
import com.admon.entity.admon.Modulo;
import com.admon.entity.admon.Organizacion;

public class ModuloDAO extends GenericDAOImpl<Modulo, Long> {

    /*
 * Método para tipar el objeto genérico. Es utilizado
 * en la implementación del GenericDAO para identificar
 * el objeto genérico la cual es necesaria para realizar
 * las consultas a la BD.
 * @return Regresa un objeto <b>Class</b> que identifica el tipo genérico. */
    @Override
    protected Class<Modulo> getEntityClass() {
        return Modulo.class;
    }
    
    @SuppressWarnings("unchecked")
	public Modulo getModulo(Long id){
    	Modulo modulo = new Modulo();
    	Session s = getSessionFactory().openSession();
		List<Object[]> list = s.createSQLQuery("SELECT MODULO_ID,APLICACION_ID,URL,ORDEN,DESCRIPCION,NOMBRE_ACCION,NOMBRE_OBJETO,HTML_ID,"
				+ " CREACION_FECHA,CREACION_USUARIO,MODIFICACION_FECHA,MODIFICACION_USUARIO,ESTATUS_ID,ELIMINADO_ID, "
				+ " (SELECT NOMBRE FROM SEGURIDAD_MODULO_PARAMETROS SMP WHERE SM.MODULO_ID=SMP.MODULO_ID AND SMP.LENGUAJE_ID=1) AS NOMBRE_ES, "
				+ " (SELECT NOMBRE FROM SEGURIDAD_MODULO_PARAMETROS SMP WHERE SM.MODULO_ID=SMP.MODULO_ID AND SMP.LENGUAJE_ID=2) AS NOMBRE_EN "
				+ " FROM SEGURIDAD_MODULO SM WHERE MODULO_ID="+id).list();
		for(Object[] o: list){
                        Integer index=0;
			modulo.setModuloId(((BigDecimal)o[index++]).longValue());
			modulo.setAplicacionId(((BigDecimal)o[index++]).longValue());
			modulo.setUrl((String)o[index++]);
			modulo.setOrden(o[index]==null?null:((BigDecimal)o[index++]).intValue());
			modulo.setDescripcion((String)o[index++]);
			modulo.setNombreAccion((String)o[index++]);
                        modulo.setNombreObjeto((String)o[index++]);
			modulo.setHtmlId((String)o[index++]);
			Object date = o[index++];
			if(date!=null && date instanceof Date){
				modulo.setCreacionFecha(new Timestamp(((Date)date).getTime()));
			}
			if(date!=null && date instanceof Timestamp){
				modulo.setCreacionFecha((Timestamp)date);
			}
			modulo.setCreacionUsuario(o[index]==null?null:((BigDecimal)o[index++]).intValue());
			date = o[index++];
			if(date!=null && date instanceof Date){
				modulo.setModificacionFecha(new Timestamp(((Date)date).getTime()));
			}
			if(date!=null && date instanceof Timestamp){
				modulo.setModificacionFecha((Timestamp)date);
			}
			modulo.setModificacionUsuario(o[index]==null?null:((BigDecimal)o[index++]).intValue());
			modulo.setEstatusId(o[index]==null?null:((BigDecimal)o[index++]).intValue());
			modulo.setEliminadoId(o[index]==null?null:((BigDecimal)o[index++]).intValue());
			modulo.setNombreES((String)o[index++]);
			modulo.setNombreEN((String)o[index++]);
    		break;
    	}
		s.close();
    	return modulo;
    }
        
    @SuppressWarnings("unchecked")
    public List<Organizacion> getOrganizaciones(){
    	List<Organizacion> orgs = new ArrayList<>();
    	try{
    		Session s = getSessionFactory().openSession();
			List<Object[]> list = s.createSQLQuery("SELECT ORGANIZACION_ID, NOMBRE FROM ORGANIZACION WHERE ELIMINADO_ID=0").list();
			for(Object[] o: list){
	    		Organizacion a = new Organizacion();
	    		a.setOrganizacionId(((BigDecimal)o[0]).intValue());
	    		a.setNombre((String)o[1]);
	    		orgs.add(a);
	    	}
			s.close();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return orgs;
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
		query.append("SELECT ROWID AS rid FROM SEGURIDAD_MODULO SM WHERE SM.ELIMINADO_ID=0 ");

		StringBuilder filter = new StringBuilder();
//		if(StringUtils.isNotEmpty((String)parametros.get("funcionalidad"))){
//			filter.append(" AND SF.FUNCIONALIDAD_ID IN (SELECT SFP.FUNCIONALIDAD_ID FROM SEGURIDAD_OPER_FUNC_PARAMETROS SFP WHERE SFP.NOMBRE LIKE '%").append((String)parametros.get("funcionalidad")).append("%' )");
//		}
//		if(StringUtils.isNotEmpty((String)parametros.get("aplicacion"))){
//			filter.append(" AND SF.APLICACION_ID IN (SELECT A.APLICACION_ID FROM SEGURIDAD_APLICACION A WHERE NOMBRE LIKE '%").append((String)parametros.get("aplicacion")).append("%' OR NOMBRE_INGLES LIKE '%").append((String)parametros.get("aplicacion")).append("%') ");
//		}
//		if(StringUtils.isNotEmpty((String)parametros.get("modulo"))){
//			filter.append(" AND SF.MODULO_ID IN (SELECT MP.MODULO_ID FROM SEGURIDAD_MODULO_PARAMETROS MP WHERE MP.NOMBRE LIKE '%").append((String)parametros.get("modulo")).append("%') ");
//		}
//		if(StringUtils.isNotEmpty((String)parametros.get("descripcion"))){
//			filter.append(" AND SF.DESCRIPCION LIKE '%").append((String)parametros.get("descripcion")).append("%'");
//		}
//		if(StringUtils.isNotEmpty((String)parametros.get("nombreAccion"))){
//			filter.append(" AND SF.NOMBRE_ACCION LIKE '%").append((String)parametros.get("nombreAccion")).append("%'");
//		}
//		if(StringUtils.isNotEmpty((String)parametros.get("htmlId"))){
//			filter.append(" AND SF.HTML_ID LIKE '%").append((String)parametros.get("htmlId")).append("%'");
//		}
//		if(StringUtils.isNotEmpty((String)parametros.get("url"))){
//			filter.append(" AND SF.URL LIKE '%").append((String)parametros.get("url")).append("%'");
//		}
		
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
    			"(SELECT NOMBRE FROM SEGURIDAD_MODULO_PARAMETROS SMP WHERE SM.MODULO_ID=SMP.MODULO_ID AND SMP.LENGUAJE_ID=1) AS MODULO_ES, "+ 
    			"(SELECT NOMBRE FROM SEGURIDAD_MODULO_PARAMETROS SMP WHERE SM.MODULO_ID=SMP.MODULO_ID AND SMP.LENGUAJE_ID=2) AS MODULO_EN, "+ 
    			"SM.DESCRIPCION, SM.MODIFICACION_FECHA, SM.MODIFICACION_USUARIO,  "+
    			"(SELECT USUARIO FROM USUARIO U WHERE U.USUARIO_ID=SM.MODIFICACION_USUARIO) AS USUARIO, "+ 
    			"SM.ELIMINADO_ID, SM.MODULO_ID, SM.ESTATUS_ID " +
    			"FROM SEGURIDAD_APLICACION A, SEGURIDAD_MODULO M, SEGURIDAD_MODULO SM, (SELECT i.* "+
				"          FROM (SELECT i.*, ROWNUM AS rn  "+
				"                  FROM ( "+getQueryPrincipal(parametros)+" ) i "+
				"                 WHERE ROWNUM <= "+indexFinal+
				"               ) i "+
				"         WHERE rn >= "+indexInicio+" "+
				"       ) i "+
				"WHERE i.rid = SM.ROWID "+
				"AND A.APLICACION_ID=M.APLICACION_ID "+
				"AND M.APLICACION_ID = SM.APLICACION_ID AND M.MODULO_ID = SM.MODULO_ID";
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
	public List<Modulo> getModulos(Map<String, Object> parametros) {
    	String query = getQuery(parametros);
    	List<Modulo> list = new ArrayList<>();
    	Session s = getSessionFactory().openSession();
    	List<Object[]> l = s.createSQLQuery(query).list();
    	for(Object[] ob: l){
            Integer index = 0;
            Modulo o = new Modulo();
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
    		o.setModuloId(Long.valueOf(ob[index++].toString()));
    		o.setEstatusId(Integer.valueOf(ob[index++].toString()));
    		list.add(o);
    	}
    	s.close();
    	return list;
    }
    
    public void saveModulo(Modulo o){
    	Session s = getSessionFactory().openSession();
    	Transaction t = s.beginTransaction();
    	
    	String query = "SELECT MODULO_SEQ.NEXTVAL FROM DUAL";
    	Query q = s.createSQLQuery(query);
    	Long id = Long.valueOf(q.uniqueResult().toString());
    	
    	
    	query = "INSERT INTO SEGURIDAD_MODULO (MODULO_ID, APLICACION_ID, URL, ORDEN, DESCRIPCION, NOMBRE_ACCION, NOMBRE_OBJETO, HTML_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA, MODIFICACION_USUARIO, ESTATUS_ID, ELIMINADO_ID) "
    			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    	
    	q = s.createSQLQuery(query);
        Integer index = 0;
    	q.setLong(index++, id);
    	q.setLong(index++, o.getAplicacionId());
    	q.setString(index++, o.getUrl());
    	q.setInteger(index++, o.getOrden());
    	q.setString(index++, o.getDescripcion());
    	q.setString(index++, o.getNombreAccion());
        q.setString(index++, o.getNombreObjeto());
    	q.setString(index++, o.getHtmlId());
    	q.setTimestamp(index++, o.getCreacionFecha());
    	q.setInteger(index++, o.getCreacionUsuario());
    	q.setTimestamp(index++, o.getModificacionFecha());
    	q.setInteger(index++, o.getModificacionUsuario());
    	q.setInteger(index++, o.getEstatusId());
    	q.setInteger(index++, o.getEliminadoId());
    	q.executeUpdate();
    	
    	query = "INSERT INTO SEGURIDAD_MODULO_PARAMETROS (MODULO_PARAMETRO_ID, MODULO_ID, LENGUAJE_ID, NOMBRE, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA, MODIFICACION_USUARIO) "
    			+ "VALUES 	(MODULO_PARAMETRO_SEQ.NEXTVAL,?,?,?,?,?,?,?)";
    	q = s.createSQLQuery(query);
        index = 0;
    	q.setLong(index++, id);
    	q.setLong(index++, 1);
    	q.setString(index++, o.getNombreES());
    	q.setTimestamp(index++, o.getCreacionFecha());
    	q.setInteger(index++, o.getCreacionUsuario());
    	q.setTimestamp(index++, o.getModificacionFecha());
    	q.setInteger(index++, o.getModificacionUsuario());
    	q.executeUpdate();
    	
    	q = s.createSQLQuery(query);
        index = 0;
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
    
    public void update(Modulo o){
    	Session s = getSessionFactory().openSession();
    	Transaction t = s.beginTransaction();
        String statusQuery="";
    	if(o.getEstatusId()!=null){
    		statusQuery = ", ESTATUS_ID=? ";
    	}
    	String query = "UPDATE ADMON.SEGURIDAD_MODULO SET APLICACION_ID=?, URL=?, ORDEN=?, DESCRIPCION=?, NOMBRE_ACCION=?, NOMBRE_OBJETO=?, HTML_ID=?, MODIFICACION_FECHA=?, MODIFICACION_USUARIO=?"+statusQuery
    			+ "WHERE MODULO_ID=?";
    	Integer index = 0;
    	Query q = s.createSQLQuery(query);
    	q.setLong(index++, o.getAplicacionId());
    	q.setString(index++, o.getUrl());
    	q.setInteger(index++, o.getOrden());
    	q.setString(index++, o.getDescripcion());
    	q.setString(index++, o.getNombreAccion());
        q.setString(index++, o.getNombreObjeto());
    	q.setString(index++, o.getHtmlId());
    	q.setTimestamp(index++, o.getModificacionFecha());
    	q.setInteger(index++, o.getModificacionUsuario());
        if(o.getEstatusId()!=null) q.setInteger(index++, o.getEstatusId());
        q.setLong(index++, o.getModuloId());
        
    	q.executeUpdate();
    	
    	query = "UPDATE ADMON.SEGURIDAD_MODULO_PARAMETROS SET NOMBRE=?, MODIFICACION_FECHA=?, MODIFICACION_USUARIO=? "
    			+ " WHERE MODULO_ID=? AND LENGUAJE_ID=1";
    	q = s.createSQLQuery(query);
        index = 0;
    	q.setString(index++, o.getNombreES());
    	q.setTimestamp(index++, o.getModificacionFecha());
    	q.setInteger(index++, o.getModificacionUsuario());
    	q.setLong(index++, o.getModuloId());
    	q.executeUpdate();
    	
    	query = "UPDATE ADMON.SEGURIDAD_MODULO_PARAMETROS SET NOMBRE=?, MODIFICACION_FECHA=?, MODIFICACION_USUARIO=? "
    			+ " WHERE MODULO_ID=? AND LENGUAJE_ID=2";
    	q = s.createSQLQuery(query);
        index = 0;
    	q.setString(index++, o.getNombreEN());
    	q.setTimestamp(index++, o.getModificacionFecha());
    	q.setInteger(index++, o.getModificacionUsuario());
    	q.setLong(index++, o.getModuloId());
    	q.executeUpdate();
    	
    	t.commit();
    	s.close();
    }
    
    public void logicDelete(Modulo m){
    	Session s = getSessionFactory().openSession();
    	Transaction t = s.beginTransaction();
    	String query = "UPDATE ADMON.SEGURIDAD_MODULO SET ELIMINADO_ID=?, MODIFICACION_FECHA=?, MODIFICACION_USUARIO=? "
    			+ "WHERE MODULO_ID=? ";
    	Query q = s.createSQLQuery(query);
        Integer index = 0;
    	q.setInteger(index++, m.getEliminadoId());
    	q.setTimestamp(index++, m.getModificacionFecha());
    	q.setInteger(index++, m.getModificacionUsuario());
    	q.setLong(index++, m.getModuloId());
    	q.executeUpdate();
    	t.commit();
    	s.close();
    }
}
