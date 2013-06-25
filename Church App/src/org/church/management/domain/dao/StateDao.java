package org.church.management.domain.dao;

import java.util.List;

import org.church.management.domain.City;
import org.church.management.domain.State;
import org.church.management.domain.generic.dao.GenericDao;

public interface StateDao extends GenericDao<State, Integer>
{	
	public List<City> getAllCitiesByState(State state);
}
