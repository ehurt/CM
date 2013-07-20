package org.church.management.domain.hibernate.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.church.management.domain.generic.dao.GenericDao;
import org.church.management.interfaces.entity.Entity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;

@SuppressWarnings("unchecked")
public class HibernateGenericDao<T extends Entity<?>, I> implements GenericDao<T , I>
{
	private Class<T> persistedClass;
	
	private SessionFactory sessionFactory;
	
	private boolean debugging;
	private Session session;
	
	public HibernateGenericDao(Class<T> entity)
	{
		this.persistedClass = entity;
		this.setDebugging(false);
	}
	
	public Session getCurrentSession()
	{
		//this is for debugging purposes
		if(debugging)
		{
			if(session == null)
			{
				session = sessionFactory.openSession();
			}
			
			return session;
		}
		
		return sessionFactory.getCurrentSession();
	}
	
	public Session getOpenSession()
	{
		return sessionFactory.openSession();
	}
	
	public void persist(T entity)
	{
		getCurrentSession().persist(entity);
	}
	
	public void delete(T entity)
	{
		getCurrentSession().delete(entity);
	}
	
	public void evict(T entity)
	{
		getCurrentSession().evict(entity);
	}
	
	public T merge(T entity)
	{
		return (T) getCurrentSession().merge(entity);
	}
	
	public T load(Serializable id)
	{
		return (T) getCurrentSession().load(persistedClass, id);
	}
	
	public T get(Serializable id)
	{
		return (T) getCurrentSession().get(persistedClass, id);
	}
	
	public List<T> findAll()
	{
		return getCurrentSession().createCriteria(persistedClass).list();
	}
	
	public boolean exists(T entity)
	{
		return getCurrentSession().get(persistedClass, entity.getId()) != null ? true : false;
	}
	
	public int rowCount()
	{
		Criteria criteria = getCurrentSession().createCriteria(persistedClass).setProjection(Projections.rowCount());
		return (Integer) criteria.uniqueResult();
	}
	
	public List<T> query(String query)
	{
		return getCurrentSession().createQuery(query).list();
	}
	
	public List<T> query(String queryString, List<Object> parameters)
	{
		Query query = getCurrentSession().createQuery(queryString);
		
		for(int i = 0; i < parameters.size(); i++)
		{
			Object parameter = parameters.get(i);
			query.setParameter(i, parameter);
		}
		
		return (List<T>) query.list();
	}

	public SessionFactory getSessionFactory() 
	{
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public boolean isDebugging() 
	{
		return debugging;
	}

	public void setDebugging(boolean debugging) 
	{
		this.debugging = debugging;
	}	
}
