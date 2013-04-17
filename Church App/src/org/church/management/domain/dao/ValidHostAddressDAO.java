package org.church.management.domain.dao;

import java.util.List;

import org.church.management.domain.ValidHostAddress;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

public class ValidHostAddressDAO extends DAO<ValidHostAddress>
{

	private static final long serialVersionUID = 1L;

	public ValidHostAddressDAO(Session session)
	{
		super(session, ValidHostAddressDAO.class, ValidHostAddress.class);
	}
	
	public List<ValidHostAddress> getValidHostAddress(String entity, String id)
	{
		List<ValidHostAddress> addresses = null;
		Criteria criteria = session.createCriteria(ValidHostAddress.class);
		SimpleExpression expr = Restrictions.eq("entity", entity);
		criteria.add(Restrictions.and(expr, Restrictions.eq("entity_id", id)));
		addresses = criteria.list();
		
		return addresses;
	}
}
