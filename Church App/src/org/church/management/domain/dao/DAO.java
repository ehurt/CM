package org.church.management.domain.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.church.management.interfaces.entity.Entity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;


/**
 * 
 * @author ehurt
 * 
 * This class is the dao to perform operations of on the object.
 *
 * @param <T>
 */

public abstract class DAO<T extends Entity> implements Serializable
{
	private static final long serialVersionUID = 1L;
	protected static final Logger logger = Logger.getLogger(DAO.class);
	
	protected Session session = null;
	protected Class persistClass = null;
	
	protected String daoName = "";
	protected String objectClass = "";
	
	public DAO(Session session, Class dao, Class persistance)
	{
		this.session = session;
		this.persistClass = persistance;
		this.daoName = dao.getName().substring(dao.getName().lastIndexOf('.')+1);
		this.objectClass = persistance.getName().substring(persistance.getName().lastIndexOf('.')+1);
	}
	
	public Session getSession()
	{
		return this.session;
	}
	
	public void setSession(Session session)
	{
		this.session = session;
	}

	public void save(T object) throws HibernateException
	{
		try
		{
			session.save(object);
		}
		catch(HibernateException e)
		{
			logger.error(daoName+".save() - An error has occurred while saving object of class: "+objectClass+".", e);
			throw e;
		}
	}
	
	public void delete(T object) throws HibernateException
	{
		try
		{			
			session.delete(object);
		}
		catch(HibernateException e)
		{
			logger.error(daoName+".delete()- An error has occurred while deleting object of class: "+objectClass+" object: "+object.getId()+".", e);
			throw e;
		}
	}
	
	public void update(T object) throws HibernateException
	{
		try
		{
			session.update(object);
		}
		catch(HibernateException e)
		{
			logger.error(daoName+".update()- An error has occurred while updating object of class: "+objectClass+" object: "+object.getId()+".", e);
			throw e;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAll() throws HibernateException
	{
		try
		{	
			return session.createCriteria(persistClass).list();
		}
		catch(HibernateException e)
		{
			logger.error(daoName+".getAll()- An error has occurred while retrieving a list of class: "+objectClass+".", e);
			throw e;
		}
	}
	
	@SuppressWarnings("unchecked")
	public T getObject(Serializable key) throws HibernateException
	{
		try
		{
			return (T) session.load(persistClass, key);
		}
		catch(HibernateException e)
		{
			logger.error(daoName+".getObject()- An error has occurred while retrieving a object of class: "+objectClass+" object: "+key+" .", e);
			throw e;
		}
	}
	
	public T getFirstRecord()
	{
		T row = null;
		
		try
		{
			Criteria criteria = session.createCriteria(persistClass);
			criteria.setMaxResults(1);
			List<T> list = criteria.list();
			
			if(list.size() > 0)
			{
				row = list.get(0);
			}
		}
		catch(HibernateException e)
		{
			throw e;
		}
		
		return row;
	}
	
	public boolean exists(Serializable key) throws HibernateException
	{
		try
		{	
			Object object = session.load(persistClass, key);
		
			if(object == null)
			{
				return false;
			}
			
			return true;
		}
		catch(HibernateException e)
		{
			if(e instanceof ObjectNotFoundException)
			{
				return false;
			}
			
			logger.error(daoName+".existed()- An error has occurred while checking if object of class: "+objectClass+" object: "+key+" exists.", e);
			throw e;
		}
	}
	
	public int rowCount() throws HibernateException
	{
		try
		{
			Criteria criteria = session.createCriteria(persistClass);
			criteria.setProjection(Projections.rowCount());
			List resultSet = criteria.list();
			return (Integer) resultSet.get(0);
		}
		catch(HibernateException e)
		{
			logger.error(daoName+".rowCount()- An error has occurred while a row count of class: "+objectClass+".", e);
			throw e;
		}
		
	}
}
