package org.church.management.domain.dao;

import java.util.List;

import org.church.management.domain.ApplicationFile;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;


public class ApplicationFileDAO extends DAO<ApplicationFile> 
{
	private static final long serialVersionUID = 1L;

	public ApplicationFileDAO(Session session)
	{
		super(session, ApplicationFileDAO.class, ApplicationFile.class);
	}
	
	public List<ApplicationFile> getFilesByReference(String table, String id)
	{
		List<ApplicationFile> files = null;
		
		try
		{
			Criteria criteria = session.createCriteria(ApplicationFile.class);
			criteria.add(Restrictions.eq("entity", table));
			criteria.add(Restrictions.eq("objectId", id));
			files = criteria.list();
		}
		catch(HibernateException e)
		{
			throw e;
		}
		
		return files;
	}
}
