package org.church.management.domain.manager;

import java.util.ArrayList;
import java.util.List;

import org.church.management.domain.City;
import org.church.management.domain.Country;
import org.church.management.domain.State;
import org.church.management.domain.dao.CountryDAO;
import org.church.management.domain.exceptions.DAOException;
import org.church.management.hibernate.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class CountryManager extends SessionManager<Country>
{

	private static final long serialVersionUID = 1L;

	public CountryManager()
	{
		super(CountryManager.class, Country.class, CountryDAO.class);
	}
	
	public void loadObjects(Session session, Country object) throws HibernateException 
	{	
	}

	public List<String> getListOfConstraintViolations(Country object, Exception e) throws DAOException 
	{
		return new ArrayList<String>();
	}
	
	public List<State> getAllStatesByCountry(Country country) throws DAOException
	{
		Session session = null;
		List<State> states = null;
		
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			CountryDAO dao = new CountryDAO(session);
			states = dao.getAllStatesByCountry(country);
		}
		catch(Exception e)
		{
			logger.error("CountryManager.getAllStatesByCountry()- Could not retrieve states by country.", e);
			throw new DAOException("Could not retrieve a list of states.", e);
		}
		
		finally
		{
			session.flush();
			session.close();
		}
		
		return states;
	}
	
	public List<City> getAllCitiesByCountry(Country country) throws DAOException
	{
		Session session = null;
		List<City> cities = null;
		
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			CountryDAO dao = new CountryDAO(session);
			cities = dao.getAllCitiesByCountry(country);
		}
		catch(Exception e)
		{
			logger.error("CountryManager.getAllCitiesByCountry()- Could not retrieve cities by country.", e);
			throw new DAOException("Could not retrieve a list of cities.", e);
		}
		
		finally
		{
			session.flush();
			session.close();
		}
		
		return cities;
	}
}
