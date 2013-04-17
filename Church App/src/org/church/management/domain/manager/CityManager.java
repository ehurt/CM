package org.church.management.domain.manager;

import java.util.ArrayList;
import java.util.List;

import org.church.management.domain.City;
import org.church.management.domain.Country;
import org.church.management.domain.State;
import org.church.management.domain.dao.CityDAO;
import org.church.management.domain.exceptions.DAOException;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class CityManager extends SessionManager<City>
{
	private static final long serialVersionUID = 1L;

	public CityManager()
	{
		super(CityManager.class, City.class, CityDAO.class);
	}
	
	@Override
	public void loadObjects(Session session, City object) throws HibernateException 
	{
		if(object.getCountry() != null)
		{
			Country country = (Country) session.load(Country.class, object.getCountry().getId());
			object.setCountry(country);
		}
		
		if(object.getState() != null)
		{
			State state = (State) session.load(State.class, object.getState().getId());
			object.setState(state);
		}
	}

	@Override
	public List<String> getListOfConstraintViolations(City object, Exception e) throws DAOException 
	{
		return new ArrayList<String>();
	}

}
