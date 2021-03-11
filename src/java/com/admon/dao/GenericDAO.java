package com.admon.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;

/*
* Interface de DAO genérico que declara los métodos CRUD para la BD.
* @param <E>
* @param <PK>
 */
public interface GenericDAO<E, PK extends Serializable> {

    /*
* Método que guarda el registro <b>e</b> en la tabla de <b>E</b>.
* @param e Es el objeto que envía la información a la tabla de <b>E</b> en la BD.
* @return Regresa el id del registro creado.
     */
    public PK save(E e);

    /*
* Método que actualiza la información de <b>e</b> en la tabla de <b>E</b>.
* @param e Es el objeto que envia la información a la tabla de <b>E</b> en la BD.
     */
    public void update(E e);

    /*
* Método que elimina el registro <b>e</b> en la tabla de <b>E</b>.
* @param e Es el registro en la tabla de <b>E</b> que se eliminará.
     */
    public void delete(E e);

    /*
* Método que busca el registro <b>e</b> por su ID en la tabla de <b>E</b>.
* @param id es el ID del registro que se quiere obtener.
* @return Regresa un objeto tipo <b>E</b> con la información del registro <b>e</b>.
     */
    public E findById(PK id);

    /*
* Método que realiza un búsqueda en la BD obteniendo todos los registros
* que coincidan con los parámetros definidos en el objeto <b>criteria</b>.
* @param criteria Es un objeto que contiene los parámetros de búsqueda.
* @return Regresa una lista de objetos <b>E</b> que coinciden con los
* parámetros definidos en <b>criteria</b>.
     */
    public List<E> findByCriteria(DetachedCriteria criteria);

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
    public List<E> findByCriteria(DetachedCriteria criteria, int firstResult, int maxResults);

    public List<E> findByCriteriaLimit(final DetachedCriteria criteria,
            final Integer from, final Integer maxResults);

    public Integer findSizeByCriteria(final DetachedCriteria criteria);
}
