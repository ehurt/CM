package org.church.management.domain.hibernate.dao;

import java.util.List;

import org.church.management.domain.City;
import org.church.management.domain.State;
import org.church.management.domain.dao.StateDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class HibernateStateDao extends HibernateGenericDao<State, Integer> implements StateDao
{
	public HibernateStateDao()
	{
		super(State.class);
	}
	
	public List<City> getAllCitiesByState(State state)
	{
		Session session = getCurrentSession();
		Criteria criteria = session.createCriteria(City.class);
		criteria.add(Restrictions.eq("state", state));
		return criteria.list();
	}
}
