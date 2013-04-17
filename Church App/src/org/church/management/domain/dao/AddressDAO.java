package org.church.management.domain.dao;

import org.church.management.domain.Address;
import org.hibernate.Session;

public class AddressDAO extends DAO<Address>
{
	private static final long serialVersionUID = 1L;
	
	public AddressDAO(Session session)
	{
		super(session, Address.class, AddressDAO.class);
	}
}
