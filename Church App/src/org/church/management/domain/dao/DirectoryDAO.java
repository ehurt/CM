package org.church.management.domain.dao;

import java.util.List;

import org.church.management.domain.ApplicationFile;
import org.church.management.domain.Directory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class DirectoryDAO extends DAO<Directory>
{

	private static final long serialVersionUID = 1L;

	public DirectoryDAO(Session session)
	{
		super(session, DirectoryDAO.class, Directory.class);
	}
	
	public void delete(Directory directory)
	{
		try
		{
			Query query = session.createQuery("delete from ApplicationFile as F where F.directory.id = :id");
			query.setParameter("id", directory.getId());
			query.executeUpdate();
			
			session.delete(directory);
		}
		catch(HibernateException e)
		{
			throw e;
		}
	}
	
	public List<ApplicationFile> getFilesFromDirectory(Directory directory)
	{
		List<ApplicationFile> files = null;
		
		try
		{
			Criteria criteria = session.createCriteria(ApplicationFile.class);
			criteria.add(Restrictions.eq("directory", directory));
			files = criteria.list();
		}
		catch(HibernateException e)
		{
			throw e;
		}
		
		return files;
	}
}
