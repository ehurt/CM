package org.church.management.domain.hibernate.dao;

import java.util.List;

import org.church.management.domain.ValidHostAddress;
import org.church.management.domain.dao.ValidHostAddressDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

public class HibernateValidHostAddressDao extends HibernateGenericDao<ValidHostAddress, Integer> implements ValidHostAddressDao
{

	public HibernateValidHostAddressDao() 
	{
		super(ValidHostAddress.class);
	}
	
	public List<ValidHostAddress> getValidHostAddress(String entity, String id)
	{
		Session session = getCurrentSession();
		List<ValidHostAddress> addresses = null;
		Criteria criteria = session.createCriteria(ValidHostAddress.class);
		SimpleExpression expr = Restrictions.eq("entity", entity);
		criteria.add(Restrictions.and(expr, Restrictions.eq("entity_id", id)));
		addresses = criteria.list();
		
		return addresses;
	}

}
