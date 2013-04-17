package org.church.management.domain.dao;

import java.util.List;

import org.church.management.domain.City;
import org.church.management.domain.Country;
import org.church.management.domain.State;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

@SuppressWarnings("unchecked")
public class CountryDAO extends DAO<Country>
{

	private static final long serialVersionUID = 1L;
	
	public CountryDAO(Session session)
	{
		super(session, CountryDAO.class, Country.class);
	}
	
	public List<State> getAllStatesByCountry(Country country)
	{
		List<State> states = null;
		
		Criteria criteria = session.createCriteria(State.class);
		criteria.add(Restrictions.eq("country", country));
		states = criteria.list();
		
		return states;
	}
	
	public List<City> getAllCitiesByCountry(Country country)
	{
		Criteria criteria = session.createCriteria(City.class);
		criteria.add(Restrictions.eq("country", country));
		return criteria.list();
	}
}
