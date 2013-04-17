package org.church.management.domain.dao;

import java.io.Serializable;
import java.util.List;

import org.church.management.domain.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class UserDAO extends DAO<User>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserDAO(Session session)
	{
		super(session, UserDAO.class, User.class);
	}
	
	public void delete(User user) throws HibernateException
	{
		try
		{
			user.setDeleted(true);
			user.setUsername(null);
			session.update(user);
		}
		catch(HibernateException e)
		{
			logger.error("UserDAO.delete()- An error has occurred while deleting user.", e);
			throw e;
		}
	}
	
	public boolean login(String username, String password)
	{
		User user = null;
		
		try
		{
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("username", username));
			List<User> users = criteria.list();
			
			if(users.size() > 0)
			{
				user = users.get(0);
				
				if(user.getUsername().equals(username) && user.getPassword().equals(password))
				{
					return true;
				}
			}
		}
		catch(HibernateException e)
		{
			logger.error("UserDAO.login()- An error has occurred while logging into the application with username: "+username+" password: "+password+"." , e);
			throw e;
		}
		
		return false;
	}
	
	public User getUserByUserName(String username)
	{
		User user = null;
		
		try
		{
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("username", username));
			List<User > list = criteria.list();
			
			if(list.size() > 0)
			{
				user = list.get(0);
			}
			
			else
			{
				throw new HibernateException("Cannot retrieve user.");
			}
		}
		catch(HibernateException e)
		{
			throw e;
		}
		
		return user;
	}
	
	public User getObject(Serializable key)
	{
		User user = null;
		
		try
		{
			user = (User) session.load(User.class, key);
			if(user.isDeleted())
			{
				throw new ObjectNotFoundException("User does not exist.", "");
			}
		}
		catch(HibernateException e)
		{
			throw e;
		}
		return user;
	}
	
	public List<User> getAll()
	{
		List<User> list = null;
		
		try
		{
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("isDeleted", false));
			list = criteria.list();
		}
		catch(HibernateException e)
		{
			logger.error("UserDAO.getAll()- An error has occured while retrieving a list of users.", e);
			throw e;
		}
		
		return list;
	}
}
