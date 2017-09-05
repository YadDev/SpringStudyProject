package com.ridlr.util;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Ranga Reddy
 * @version 1.0
 */
@Repository
@SuppressWarnings("hiding")
public class HibernateUtil {

	@Autowired
	private SessionFactory sessionFactory;

	public <Object> Serializable create(final Object entity) {
		return sessionFactory.getCurrentSession().save(entity);
	}

	public <Object> Object update(final Object entity) {
		sessionFactory.getCurrentSession().update(entity);
		return entity;
	}

	public <Object> void delete(final Object entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	public <Object> void delete(Serializable id, Class<Object> entityClass) {
		Object entity = fetchById(id, entityClass);
		delete(entity);
	}

	@SuppressWarnings("unchecked")
	public <Object> List<Object> fetchAll(Class<Object> entityClass) {
		return sessionFactory.getCurrentSession()
				.createQuery(" FROM " + entityClass.getName()).list();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <Object> List<Object> fetchAll(String query) {
		return sessionFactory.getCurrentSession().createSQLQuery(query).list();
	}

	@SuppressWarnings("unchecked")
	public <Object> Object fetchById(Serializable id, Class<Object> entityClass) {
		return (Object) sessionFactory.getCurrentSession().get(entityClass, id);
	}
}