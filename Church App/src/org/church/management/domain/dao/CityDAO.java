package org.church.management.domain.dao;

import org.church.management.domain.City;
import org.hibernate.Session;

public class CityDAO extends DAO<City>
{
	private static final long serialVersionUID = 1L;

	public CityDAO(Session session)
	{
		super(session, CityDAO.class, City.class);
	}
}
