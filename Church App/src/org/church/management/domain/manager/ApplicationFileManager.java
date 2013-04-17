package org.church.management.domain.manager;

import java.util.ArrayList;
import java.util.List;

import org.church.management.domain.ApplicationFile;
import org.church.management.domain.Directory;
import org.church.management.domain.dao.ApplicationFileDAO;
import org.church.management.domain.exceptions.DAOException;
import org.church.management.hibernate.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;


public class ApplicationFileManager extends SessionManager<ApplicationFile>
{

	private static final long serialVersionUID = 1L;

	public ApplicationFileManager()
	{
		super(ApplicationFileManager.class, ApplicationFile.class, ApplicationFileDAO.class);
	}
	
	@Override
	public void loadObjects(Session session, ApplicationFile object) throws HibernateException 
	{
		if(object.getDirectory() != null)
		{
			Directory directory = object.getDirectory();
			directory = (Directory) session.load(Directory.class, directory.getId());
			object.setDirectory(directory);
		}
	}

	@Override
	public List<String> getListOfConstraintViolations(ApplicationFile object, Exception e) throws DAOException 
	{
		return new ArrayList<String>();
	}
	
	public List<ApplicationFile> getFilesByReference(String table, String id) throws DAOException
	{
		Session session = null;
		List<ApplicationFile> files = null;
		
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			ApplicationFileDAO dao = new ApplicationFileDAO(session);
			files = dao.getFilesByReference(table, id);
		}
		catch(HibernateException e)
		{
			logger.error("ApplicationFileManager.getFilesByReference()- Could retrieving files for table: "+table+" and id "+id+".", e);
			throw new DAOException("Could retrieving files for table: "+table+" and id "+id+".", e);
		}
		
		finally
		{
			if(session != null)
			{
				session.flush();
				session.close();
			}
		}
		
		return files;
	}

}
