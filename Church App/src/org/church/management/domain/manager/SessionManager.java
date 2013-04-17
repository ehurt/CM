package org.church.management.domain.manager;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.Calendar;
import java.util.List;


import org.apache.log4j.Logger;
import org.church.management.domain.dao.DAO;
import org.church.management.domain.exceptions.DAOConstraintViolationException;
import org.church.management.domain.exceptions.DAOException;
import org.church.management.domain.exceptions.DAONoObjectFoundException;
import org.church.management.domain.exceptions.DAOStaleStateException;
import org.church.management.domain.standard.fields.StandardFields;
import org.church.management.hibernate.utils.HibernateUtil;
import org.church.management.interfaces.entity.Entity;
import org.church.management.record.locking.exception.LockException;
import org.church.management.record.locking.info.LockInfo;
import org.church.management.record.locking.management.ObjectLockSystemManager;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StaleStateException;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import org.hibernate.ObjectNotFoundException;


/**
 * 
 * @author Administrator
 *
 * This class is a generic manager for managing the 
 * hibernate session for accessing the database. 
 *
 * @param <T>: the class which has an hibernate mapping.
 */
public abstract class SessionManager<T extends Entity> implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String managerName = "";
	private String objectClass = "";
	private String daoObject = "";
	
	@SuppressWarnings("rawtypes")
	protected Class persistClass = null;
	
	@SuppressWarnings("rawtypes")
	protected Class daoClass = null;
	
	protected static final Logger logger = Logger.getLogger(SessionManager.class);
	
	public SessionManager(Class manager, Class persistance, Class dao)
	{
		this.persistClass = persistance;
		this.daoClass = dao;
		this.managerName = manager.getName().substring(manager.getName().lastIndexOf('.')+1);
		this.objectClass = persistance.getName().substring(persistance.getName().lastIndexOf('.')+1);
		this.daoObject = dao.getName().substring(dao.getName().lastIndexOf('.')+1);
	}
	
	public void save(T object) throws DAOException, DAOConstraintViolationException, DAONoObjectFoundException
	{
		DAO<T> dao = null;
		Session session = null;
		SessionFactory sf = null;
		Transaction transaction = null;
		
		try
		{
			sf = HibernateUtil.getSessionFactory();
			session = sf.openSession();
			transaction = session.beginTransaction();
			
			if(object instanceof StandardFields)
			{
				StandardFields systemFields = (StandardFields) object;
				systemFields.setCreatedOn(Calendar.getInstance());
			}
			
			//load objects in session
			loadObjects(session, object);
			dao = getInstanceOfDataAccessObject(session);
			dao.save(object);
			transaction.commit();
		}
		catch(HibernateException e)
		{
			rollback(transaction);
			logger.error(managerName+".save()- An error occurred while saving an object of class: "+objectClass+".", e);
		
			if(e instanceof ObjectNotFoundException)
			{
				//work
				throw new DAONoObjectFoundException("An error occurred while loading objects into class: "+objectClass+".", e);
			}
			
			if(e instanceof ConstraintViolationException)
			{
				//work
				List<String> violations = getListOfConstraintViolations(object, e);
				throw new DAOConstraintViolationException("A constraint violation has occurred while saving object of class: "+objectClass+" to the database.", e, violations);
			}
			
			throw new DAOException("An error has occurred while saving an object for class: "+objectClass+" to the database.", e);
		}
		
		catch(Exception e)
		{
			//work
			rollback(transaction);
			logger.error(managerName+".save()- An error occurred while creating a dao object of class: "+daoObject+".", e);
			throw new DAOException("An error has occurred while creating a dao object for class: "+daoObject+".", e);
		}
		
		finally
		{
			if(session != null)
			{
				session.close();
			}
		}
	}
	
	public final void delete(T object) throws DAOException, DAOConstraintViolationException, DAONoObjectFoundException, DAOStaleStateException, LockException
	{
		DAO<T> dao = null;
		Session session = null;
		SessionFactory sf = null;
		Transaction transaction = null;
		
		try
		{
			LockInfo info = ObjectLockSystemManager.retrieveLockInformation(object);
			
			//TODO test out the locking mechanism info exists, then the record is locked.
			if(info != null)
			{
				throw new LockException(info);
			}
			
			sf = HibernateUtil.getSessionFactory();
			session = sf.openSession();
			transaction = session.beginTransaction();
			dao = getInstanceOfDataAccessObject(session);
			dao.delete(object);
			transaction.commit();
		}
		catch(HibernateException e)
		{
			logger.error(managerName+".delete()- An error occurred while deleting an object of class: "+objectClass+" with key "+object.getId()+".", e);
			rollback(transaction);
			
			if(e instanceof ObjectNotFoundException)
			{
				throw new DAONoObjectFoundException("An error occurred while deleting an object of class: "+objectClass+" with key "+object.getId()+". The object could of been deleted.", e);
			}
			
			if(e instanceof StaleStateException)
			{
				throw new DAOStaleStateException("An error occurred while updating an object of class: "+objectClass+" with key "+object.getId()+". The object does not existed.", e);
			}
			
			if(e instanceof ConstraintViolationException)
			{
				List<String> errors = getListOfConstraintViolations(object, e);
				throw new DAOConstraintViolationException("A constraint violation has occurred while deleting an object of class: "+objectClass+" with key "+object.getId()+".", e, errors);
			}
			
			throw new DAOException("An error occurred while deleting an object of class: "+objectClass+" with key "+object.getId()+".", e);
		}
		
		catch(Exception e)
		{
			logger.error(managerName+".delete()- An error occurred while creating a data access object of class: "+daoObject+".", e);
			rollback(transaction);
			throw new DAOException("An error occurred while creating a data access object of class: "+daoObject+".", e);
		}
		
		finally
		{
			if(session != null)
			{
				session.close();
			}
		}
	}
	
	public void update(T object) throws DAOException, DAOConstraintViolationException, DAONoObjectFoundException, DAOStaleStateException
	{
		Session session = null;
		SessionFactory sf = null;
		Transaction transaction = null;
		DAO<T> dao = null;
		
		try
		{
			sf = HibernateUtil.getSessionFactory();
			session = sf.openSession();
			transaction = session.beginTransaction();
			loadObjects(session, object);
			
			if(object instanceof StandardFields)
			{
				StandardFields systemFields = (StandardFields) object;
				systemFields.setLastModifiedOn(Calendar.getInstance());
			}
			
			dao = getInstanceOfDataAccessObject(session);
			dao.update(object);
			transaction.commit();
		}
		catch(HibernateException e)
		{
			logger.error(managerName+".update()- An error occurred while updating an object of class: "+objectClass+" with key "+object.getId()+".", e);
			rollback(transaction);
		
			if(e instanceof ObjectNotFoundException)
			{
				throw new DAONoObjectFoundException("An error occurred while updating an object of class:  "+objectClass+" with key "+object.getId()+".", e);
			}
			
			if(e instanceof StaleStateException)
			{
				throw new DAOStaleStateException("An error occurred while updating an object of class: "+objectClass+" with key "+object.getId()+". The object does not existed.", e);
			}
			
			if(e instanceof ConstraintViolationException)
			{
				List<String> errors = getListOfConstraintViolations(object, e);
				throw new DAOConstraintViolationException("A constraint violation occurred while updating object of class: "+objectClass+"with key "+object.getId()+".", e, errors);
			}
		
			throw new DAOException("An error while updating an object of class: "+objectClass+" object: "+object.getId()+".", e);
		}
		catch(Exception e)
		{
			logger.error(managerName+".update()- An error occurred while creating a data access object of class: "+daoObject+".", e);
			rollback(transaction);
			throw new DAOException("An error occurred while creating a data access object of class: "+daoObject+".", e);
		}
		
		finally
		{
			if(session != null)
			{
				session.close();
			}
		}
	}
	
	public T getObject(Serializable key) throws DAOException, DAONoObjectFoundException
	{
		T returnObject = null;
		Session session = null;
		SessionFactory sf = null;
		DAO<T> dao = null;
		
		try
		{
			sf = HibernateUtil.getSessionFactory();
			session = sf.openSession();
			
			dao = getInstanceOfDataAccessObject(session);
			returnObject = dao.getObject(key);
		}
		catch(HibernateException e)
		{
			logger.error(managerName+".getObject()- An error has occurred while retrieving an object of class: "+objectClass+" with id "+key+".", e);
			
			if(e instanceof ObjectNotFoundException)
			{
				//works
				throw new DAONoObjectFoundException("An error has occurred while retrieving an object of class: "+objectClass+" with id "+key+". It could of been deleted from the database.", e);
			}
			
			//works
			throw new DAOException("An error has occurred while retrieving an object of class: "+objectClass+" with id "+key+".", e);
		}
		
		catch(Exception e)
		{
			logger.error(managerName+".getObject()- An error has occurred while creating a dao of class: "+daoObject+".", e);
			throw new DAOException("An error has occurred while creating a dao object for class: "+daoObject+".", e);
		}
		
		finally
		{
			if(session != null)
			{
				session.flush();
				session.close();
			}
		}
		
		return returnObject;
	}
	
	public int rowCount() throws DAOException
	{
		int rowCount = 0;
		Session session = null;
		SessionFactory sf = null;
		DAO<T> dao = null;
		
		try
		{
			sf = HibernateUtil.getSessionFactory();
			session = sf.openSession();
			
			dao = getInstanceOfDataAccessObject(session);
			rowCount = dao.rowCount();
		}
		catch(HibernateException e)
		{
			logger.error(managerName+".rowCount()- An error occurred while retrieving a row counts of "+objectClass+".", e);
			throw new DAOException("An error occurred while retrieving a row count of "+objectClass+".");
		}
		
		catch(Exception e)
		{
			logger.error(managerName+".rowCount()- An error occurred while creating data access object for class: "+daoObject+".", e);
			throw new DAOException("An error occurred while creating an data access object for class: "+daoObject+".", e);
		}
		
		finally
		{
			if(session != null)
			{
				session.flush();
				session.close();
			}
		}
		
		return rowCount;
	}
	
	public List<T> getAll() throws DAOException
	{
		List<T> list = null;
		Session session = null;
		SessionFactory sf = null;
		DAO<T> dao = null;
		
		try
		{
			sf = HibernateUtil.getSessionFactory();
			session = sf.openSession();
			dao = getInstanceOfDataAccessObject(session);
			list = dao.getAll();
		}
		catch(HibernateException e)
		{
			logger.error(managerName+".getAll()- An error has occurred while retrieving a list of class: "+objectClass+".", e);
			throw new DAOException("An error has occurred while retrieving a list of class: "+objectClass+".", e);
		}
		
		catch(Exception e)
		{
			logger.error(managerName+".getAll()- An error has occurred while creating a dao for class: "+daoObject+".", e);
			throw new DAOException("An error has occurred while creating a dao for class: "+daoObject+".", e);
		}
		
		finally
		{
			if(session != null)
			{
				session.flush();
				session.close();
			}
		}
		
		return list;
	}
	
	public boolean exists(Serializable key) throws DAOException
	{
		boolean exists = false;
		Session session = null;
		SessionFactory sf = null;
		DAO<T> dao = null;
		
		try
		{
			sf = HibernateUtil.getSessionFactory();
			session = sf.openSession();
			
			dao = getInstanceOfDataAccessObject(session);
			exists = dao.exists(key);
		}
		catch(HibernateException e)
		{	
			logger.error(managerName+".exists()- An error occurred while checking if class "+objectClass+" with key "+key+" exists.", e);
			throw new DAOException("An error occurred while checking if class "+objectClass+" with key "+key+" exists.", e);
		}
		
		catch(Exception e)
		{
			logger.error(managerName+".exists()- An error occurred while creating a data access object for class: "+daoObject+".", e);
			throw new DAOException("An error occurred while creating a dao access object of class: "+daoObject+".", e);
		}
		
		finally
		{
			if(session != null)
			{
				session.flush();
				session.close();
			}
		}
		
		return exists;
	}
	
	public T getFirstRecord() throws DAOException
	{
		T row = null;
		Session session = null;
		SessionFactory sf = null;
		DAO<T> dao = null;
		
		try
		{
			sf = HibernateUtil.getSessionFactory();
			session = sf.openSession();
			dao = getInstanceOfDataAccessObject(session);
			row = dao.getFirstRecord();
		}
		catch(HibernateException e)
		{
			logger.error(managerName+".getFirstRecord()-  An error has occurred while retrieving the first record for "+objectClass+".", e);
			throw new DAOException("An error has occurred while retrieving the first record for "+objectClass+".", e);
		}
		
		catch(Exception e)
		{
			logger.error(managerName+".getFirstRecord()- An error occurred while creating data access object for class: "+daoObject+".", e);
			throw new DAOException("An error occurred while creating a dao access object of class: "+daoObject+".", e);
		}
		
		finally
		{
			if(session != null)
			{
				session.flush();
				session.close();
			}
		}
		
		return row;
	}
	
	/**
	 *  This method will return the proper dao to 
	 *  access the data with.
	 * @throws Exception : the exception could be thrown for a number of reasons.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected DAO<T> getInstanceOfDataAccessObject(Session session) throws Exception
	{
		DAO<T> dao = null;
		Object[] arguments = new Object[1];
		Class[] parameters = new Class[1];
		
		parameters[0] = Session.class;
		arguments[0] = session;
		
		try 
		{
			Constructor constructor = daoClass.getConstructor(parameters);
			dao = (DAO<T>) constructor.newInstance(arguments);
		}
		
		catch(Exception e)
		{
			logger.error(managerName+".getInstanceOfDataAccessObject()- An error occurred while retrieving the constructor of the dao for class: "+daoObject+".", e);
			throw e;
			
		}

		return dao;
	}
	
	/**
	 * 
	 * This method will roll back the transaction.
	 * 
	 * @param transaction
	 */
	protected void rollback(Transaction transaction)
	{
		try
		{
			if(transaction != null)
			{
				transaction.rollback();
			}
		}
		catch(Exception e)
		{
			logger.error(managerName+".rollback()- The transaction could not be rolled back.", e);
		}
	}
	
	public void lock(T object, String sessionId, String username) throws LockException
	{
		ObjectLockSystemManager.lock(object, sessionId, username);
	}
	
	public void unlock(T object)
	{
		ObjectLockSystemManager.unlock(object);
	}
	
	/**
	 * 
	 * This method will load the objects into the session.
	 * 
	 * @param session : the session to load the objects into.
	 * @param object
	 */
	public abstract void loadObjects(Session session, T object) throws HibernateException;
	
	/**
	 * 
	 * This method will return a list of constraint violations.
	 * 
	 * @return : the list of constraint violations.
	 */
	public abstract List<String> getListOfConstraintViolations(T object, Exception e) throws DAOException;
}
