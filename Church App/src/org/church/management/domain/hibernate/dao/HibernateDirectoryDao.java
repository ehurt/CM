package org.church.management.domain.hibernate.dao;

import java.util.List;

import org.church.management.domain.Attachment;
import org.church.management.domain.Directory;
import org.church.management.domain.dao.DirectoryDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class HibernateDirectoryDao extends HibernateGenericDao<Directory, Integer> implements DirectoryDao
{
	public HibernateDirectoryDao() 
	{
		super(Directory.class);
	}
	
	public List<Attachment> getFilesFromDirectory(Directory directory)
	{
		List<Attachment> files = null;

		Session session = getCurrentSession();
		Criteria criteria = session.createCriteria(Attachment.class);
		criteria.add(Restrictions.eq("directory", directory));
		files = criteria.list();
		
		return files;
	}
}
