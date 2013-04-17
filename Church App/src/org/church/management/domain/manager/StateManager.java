package org.church.management.domain.manager;

import java.util.ArrayList;
import java.util.List;

import org.church.management.domain.City;
import org.church.management.domain.Country;
import org.church.management.domain.State;
import org.church.management.domain.dao.StateDAO;
import org.church.management.domain.exceptions.DAOException;
import org.church.management.hibernate.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class StateManager extends SessionManager<State>
{
	private static final long serialVersionUID = 1L;

	public StateManager()
	{
		super(StateManager.class, State.class, StateDAO.class);
	}

	@Override
	public void loadObjects(Session session, State object) throws HibernateException 
	{
		if(object.getCountry() != null)
		{
			Country country = (Country) session.load(Country.class, object.getCountry().getId());
			object.setCountry(country);
		}
	}

	@Override
	public List<String> getListOfConstraintViolations(State object, Exception e)throws DAOException 
	{
		return new ArrayList<String>();
	}
	
	public List<City> getAllCitiesByState(State state) throws DAOException
	{
		Session session = null;
		List<City> cities = null;
		
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			StateDAO dao = new StateDAO(session);
			cities = dao.getAllCitiesByState(state);
		}
		catch(Exception e)
		{
			logger.error("StateDAO.getAllCitiesByState()- Could not retrieve the cities by state.", e);
			throw new DAOException("Could not retrieve the cities by state.", e);
		}
		
		finally
		{
			session.flush();
			session.close();
		}
		
		return cities;
	}
}
