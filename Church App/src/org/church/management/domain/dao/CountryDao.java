package org.church.management.domain.dao;

import java.util.List;

import org.church.management.domain.City;
import org.church.management.domain.Country;
import org.church.management.domain.State;
import org.church.management.domain.generic.dao.GenericDao;

public interface CountryDao extends GenericDao<Country, Integer>
{	
	public List<State> getAllStatesByCountry(Country country);
	
	public List<City> getAllCitiesByCountry(Country country);
}
