package org.church.management.domain.manager;

import java.util.ArrayList;
import java.util.List;

import org.church.management.domain.Address;
import org.church.management.domain.City;
import org.church.management.domain.Country;
import org.church.management.domain.State;
import org.church.management.domain.dao.AddressDAO;
import org.church.management.domain.exceptions.DAOException;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class AddressManager extends SessionManager<Address>
{

	private static final long serialVersionUID = 1L;

	public AddressManager()
	{
		super(AddressManager.class, Address.class, AddressDAO.class);
	}
	
	@Override
	public void loadObjects(Session session, Address object) throws HibernateException 
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
		
		if(object.getCity() != null)
		{
			City city = (City) session.load(City.class, object.getCity().getId());
			object.setCity(city);
		}
	}

	@Override
	public List<String> getListOfConstraintViolations(Address object, Exception e) throws DAOException 
	{
		return new ArrayList<String>();
	}

}
