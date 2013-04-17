package org.church.management.domain.manager;

import java.util.ArrayList;
import java.util.List;

import org.church.management.domain.Directory;
import org.church.management.domain.Language;
import org.church.management.domain.User;
import org.church.management.domain.dao.UserDAO;
import org.church.management.domain.exceptions.DAOException;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import org.church.management.hibernate.utils.HibernateUtil;

public class UserManager extends SessionManager<User>
{

	private static final long serialVersionUID = 1L;

	public UserManager()
	{
		super(UserManager.class, User.class, UserDAO.class);
	}
	
	@Override
	public List<String> getListOfConstraintViolations(User object, Exception e) throws DAOException 
	{
		return new ArrayList<String>();
	}

	@Override
	public void loadObjects(Session session, User object) throws HibernateException 
	{
		if(object.getLanguage() != null)
		{
			Language language = object.getLanguage();
			language = (Language) session.load(Language.class, language.getId());
			object.setLanguage(language);
		}
		
		if(object.getDrive() != null)
		{
			Directory directory = object.getDrive();
			directory = (Directory) session.load(Directory.class, directory.getId());
			object.setDrive(directory);
		}
	}
	
	public boolean login(String username, String password) throws DAOException
	{
		boolean login = false;
		Session session = null;
		
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			UserDAO dao = new UserDAO(session);
			login = dao.login(username, password);
		}
		catch(HibernateException e)
		{
			throw new DAOException("Exception happened while logging.", e);
		}
		
		finally
		{
			if(session != null)
			{
				session.flush();
				session.close();
			}
		}
		
		return login;
	}
	
	public User getUserByUsername(String username) throws DAOException
	{
		Session session = null;
		User user = null;
		
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			UserDAO dao = new UserDAO(session);
			user = dao.getUserByUserName(username);
		}
		catch(HibernateException e)
		{
			logger.error("UserManager.getUserByUsername()- An error whiling retrieving user.", e);
			throw new DAOException(e);
		}
		
		finally
		{
			if(session != null)
			{
				session.flush();
				session.close();
			}
		}
		
		return user;
	}
	
}
