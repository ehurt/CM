package org.church.management.hibernate.utils;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.church.management.domain.User;
import org.church.management.domain.exceptions.DAOConstraintViolationException;
import org.church.management.domain.exceptions.DAOException;
import org.church.management.domain.exceptions.DAONoObjectFoundException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * 
 * @author ehurt
 *
 * This class will be used all over the web application to give 
 * access to the session factory.
 *
 */
public class HibernateUtil 
{
	private static SessionFactory sessionFactory = null;
	
	private static final Logger logger = Logger.getLogger(HibernateUtil.class);
	
	private static Configuration configuration = null;
	
	static
	{
		try
		{	
			configuration = new Configuration();
			configuration.configure();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			logger.error("An error has occurred while initializing hibernate.", e);
		}
	}
	
	public static Configuration getConfiguration()
	{
		return configuration;
	}
	
	public static SessionFactory getSessionFactory()
	{
		if(sessionFactory == null)
		{
			sessionFactory = configuration.buildSessionFactory();
		}
		
		return sessionFactory;
	}
	
	public static void main(String[] args) throws DAOException, DAOConstraintViolationException, DAONoObjectFoundException
	{
		Configuration config = HibernateUtil.getConfiguration();
		config.addAnnotatedClass(User.class);
		HibernateUtil.getSessionFactory();
	}
}
