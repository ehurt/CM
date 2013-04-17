package org.church.management.domain.manager;

import java.util.ArrayList;
import java.util.List;

import org.church.management.domain.ApplicationFile;
import org.church.management.domain.Directory;
import org.church.management.domain.dao.DirectoryDAO;
import org.church.management.domain.exceptions.DAOException;
import org.church.management.hibernate.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class DirectoryManager extends SessionManager<Directory>
{
	private static final long serialVersionUID = 1L;

	public DirectoryManager()
	{
		super(DirectoryManager.class, Directory.class, DirectoryDAO.class);
	}
	
	@Override
	public void loadObjects(Session session, Directory object) throws HibernateException 
	{	
	}

	@Override
	public List<String> getListOfConstraintViolations(Directory object, Exception e)throws DAOException 
	{
		return new ArrayList<String>();
	}
	
	public List<ApplicationFile> getFilesFromDirectory(Directory directory) throws DAOException
	{
		Session session = null;
		List<ApplicationFile> files = null;
		
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			DirectoryDAO dao = new DirectoryDAO(session);
			files = dao.getFilesFromDirectory(directory);
		}
		catch(HibernateException e)
		{
			logger.error("DirectoryManager.getFilesFromDirectory()- Could not retrieve files from directory.", e);
			throw new DAOException("Could not retrieve files from directory.", e);
		}
		
		finally
		{
			session.flush();
			session.close();
		}
		
		return files;
	}
}
