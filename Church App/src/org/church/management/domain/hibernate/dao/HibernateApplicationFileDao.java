package org.church.management.domain.hibernate.dao;

import java.util.List;

import org.church.management.domain.ApplicationFile;
import org.church.management.domain.dao.ApplicationFileDao;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class HibernateApplicationFileDao extends HibernateGenericDao<ApplicationFile, Integer> implements ApplicationFileDao
{
	public HibernateApplicationFileDao()
	{
		super(ApplicationFile.class);
	}
	
	public List<ApplicationFile> getFilesByReference(String table, String id)
	{
		List<ApplicationFile> files = null;
		Session session = getCurrentSession();
		
		Criteria criteria = session.createCriteria(ApplicationFile.class);
		criteria.add(Restrictions.eq("entity", table));
		criteria.add(Restrictions.eq("objectId", id));
		files = criteria.list();
		
		return files;
	}
}
