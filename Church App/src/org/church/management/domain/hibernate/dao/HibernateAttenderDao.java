package org.church.management.domain.hibernate.dao;

import java.util.List;

import org.church.management.domain.Attender;
import org.church.management.domain.dao.AttenderDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class HibernateAttenderDao extends HibernateGenericDao<Attender, Integer> implements AttenderDao
{
	public HibernateAttenderDao()
	{
		super(Attender.class);
	}

	@SuppressWarnings("unchecked")
	public List<Attender> getAllMembers() 
	{
		Session session = getCurrentSession();
		Criteria criteria = session.createCriteria(Attender.class);
		criteria.add(Restrictions.eq("isMember", true));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Attender> getAllRegulars() 
	{
		Session session = getCurrentSession();
		Criteria criteria = session.createCriteria(Attender.class);
		criteria.add(Restrictions.eq("isRegular", true));
		return criteria.list();
	}
	
}
