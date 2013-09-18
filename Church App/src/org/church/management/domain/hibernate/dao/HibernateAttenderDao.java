package org.church.management.domain.hibernate.dao;

import java.util.List;

import org.church.management.domain.Attender;
import org.church.management.domain.Member;
import org.church.management.domain.Regular;
import org.church.management.domain.Visitor;
import org.church.management.domain.dao.AttenderDao;
import org.hibernate.Criteria;
import org.hibernate.Session;

public class HibernateAttenderDao extends HibernateGenericDao<Attender, Integer> implements AttenderDao
{
	public HibernateAttenderDao()
	{
		super(Attender.class);
	}

	@SuppressWarnings("unchecked")
	public List<Member> getAllMembers() 
	{
		Session session = getCurrentSession();
		Criteria criteria = session.createCriteria(Member.class);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Regular> getAllRegulars() 
	{
		Session session = getCurrentSession();
		Criteria criteria = session.createCriteria(Regular.class);
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Visitor> getAllVisitors()
	{
		Session session = getCurrentSession();
		Criteria criteria = session.createCriteria(Visitor.class);
		return criteria.list();
	}
	
}
