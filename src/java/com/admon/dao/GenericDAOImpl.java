package com.admon.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.HibernateCallback;

public abstract class GenericDAOImpl<E, PK extends Serializable> extends BaseDAO implements GenericDAO<E, PK> {

    /*
* Método que guarda el registro <b>e</b> en la tabla de <b>E</b>.
* @param e Es el objeto que envía la información a la tabla de <b>E</b> en la BD.
* @return Regresa el id del registro creado.
     */
    @Override
    public PK save(E e) {
        return (PK) getHibernateTemplate().save(e);
    }

    /*
* Método que actualiza la información de <b>e</b> en la tabla de <b>E</b>.
* @param e Es el objeto que envia la información a la tabla de <b>E</b> en la BD.
     */
    @Override
    public void update(E e) {
        getHibernateTemplate().update(e);
    }

    /*
* Método que elimina el registro <b>e</b> en la tabla de <b>E</b>.
* @param e Es el registro en la tabla de <b>E</b> que se eliminará.
     */
    @Override
    public void delete(E e) {
        getHibernateTemplate().delete(e);
    }

    /*
* Método que busca el registro <b>e</b> por su ID en la tabla de <b>E</b>.
* @param id es el ID del registro que se quiere obtener.
* @return Regresa un objeto tipo <b>E</b> con la información del registro <b>e</b>.
     */
    @Override
    public E findById(PK id) {
        return (E) getHibernateTemplate().get(getEntityClass(), id);
    }

    /*
* Método que realiza un búsqueda en la BD obteniendo todos los registros
* que coincidan con los parámetros definidos en el objeto <b>criteria</b>.
* @param criteria Es un objeto que contiene los parámetros de búsqueda.
* @return Regresa una lista de objetos <b>E</b> que coinciden con los
* parámetros definidos en <b>criteria</b>.
     */
    @Override
    public List<E> findByCriteria(DetachedCriteria criteria) {
        return getHibernateTemplate().findByCriteria(criteria);
    }

    /*
* Método que realiza un búsqueda en la BD obteniendo todos los registros
* que coincidan con los parámetros definidos en el objeto <b>criteria</b>,
* los resultados son páginados antes de ser regresados.
* @param criteria Es un objeto que contiene los parámetros de búsqueda.
* @param firstResult Es el primer resultado que se desea mostrar.
* @param maxResults Es la cantidad total de resultados por página.
* @return Regresa una lista de objetos <b>E</b> que coinciden con los
* parámetros definidos en <b>criteria</b>.
     */
    @Override
    public List<E> findByCriteria(DetachedCriteria criteria, int firstResult, int maxResults) {
        return getHibernateTemplate().findByCriteria(criteria, firstResult, maxResults);
    }

    @Override
    public List<E> findByCriteriaLimit(final DetachedCriteria criteria,
            final Integer from, final Integer maxResults) {
        return (List<E>) getHibernateTemplate().execute(
                new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return criteria.getExecutableCriteria(session)
                        .setFirstResult(from)
                        .setMaxResults(maxResults).list();
            }
        });
    }

    @Override
    public Integer findSizeByCriteria(final DetachedCriteria criteria) {
        return (Integer) getHibernateTemplate().execute(
                new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return (Integer) criteria.getExecutableCriteria(session)
                        .setProjection(Projections.rowCount()).uniqueResult();
            }
        });
    }

    /*
* Método para obtener el tipo del objeto genérico.
* @return Regresa un objeto <b>Class</b> que identifica el tipo genérico. WARN: Éste método debe ser sobreescrito en el DAO de cada Entity.
     */
    protected abstract Class<E> getEntityClass();

    /*
* Método que obtiene un objeto <b>DetachedCriteria</b> (similar al objeto <b>Criteria</b>) sin necesidad de una Session en Hibernate.
* @return Regresa un objeto DetachedCriteria con el cual se configuran las consultas en la capa de Negocio y ejecutarlas más tarde en la capa DAO cuando se tenga acceso a una Session en Hibernate.
     */
    public DetachedCriteria createDetachedCriteria() {
        return DetachedCriteria.forClass(getEntityClass());
    }
}
