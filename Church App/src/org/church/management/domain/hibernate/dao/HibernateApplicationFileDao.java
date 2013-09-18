package org.church.management.domain.hibernate.dao;

import java.util.List;

import org.church.management.domain.Attachment;
import org.church.management.domain.dao.ApplicationFileDao;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class HibernateApplicationFileDao extends HibernateGenericDao<Attachment, Integer> implements ApplicationFileDao
{
	public HibernateApplicationFileDao()
	{
		super(Attachment.class);
	}
	
	public List<Attachment> getFilesByReference(String table, String id)
	{
		List<Attachment> files = null;
		Session session = getCurrentSession();
		
		Criteria criteria = session.createCriteria(Attachment.class);
		criteria.add(Restrictions.eq("entity", table));
		criteria.add(Restrictions.eq("objectId", id));
		files = criteria.list();
		
		return files;
	}
}
