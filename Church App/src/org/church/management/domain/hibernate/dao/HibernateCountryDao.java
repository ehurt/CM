package org.church.management.domain.hibernate.dao;

import java.util.List;

import org.church.management.domain.City;
import org.church.management.domain.Country;
import org.church.management.domain.State;
import org.church.management.domain.dao.CountryDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class HibernateCountryDao extends HibernateGenericDao<Country, Integer> implements CountryDao
{
	public HibernateCountryDao()
	{
		super(Country.class);
	}
	
	public List<State> getAllStatesByCountry(Country country)
	{
		List<State> states = null;
		Session session = getCurrentSession();
		Criteria criteria = session.createCriteria(State.class);
		criteria.add(Restrictions.eq("country", country));
		states = criteria.list();
		
		return states;
	}
	
	public List<City> getAllCitiesByCountry(Country country)
	{
		Session session = getCurrentSession();
		Criteria criteria = session.createCriteria(City.class);
		criteria.add(Restrictions.eq("country", country));
		return criteria.list();
	}
}
