package org.church.management.domain.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.church.management.domain.City;
import org.church.management.domain.State;

public class StateDAO extends DAO<State>
{
	private static final long serialVersionUID = 1L;

	public StateDAO(Session session)
	{
		super(session, StateDAO.class, State.class);
	}
	
	public List<City> getAllCitiesByState(State state)
	{
		Criteria criteria = session.createCriteria(City.class);
		criteria.add(Restrictions.eq("state", state));
		return criteria.list();
	}
}
