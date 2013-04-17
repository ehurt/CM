package org.church.management.domain.manager;

import java.util.ArrayList;
import java.util.List;

import org.church.management.domain.Language;
import org.church.management.domain.dao.LanguageDAO;
import org.church.management.domain.exceptions.DAOException;
import org.church.management.hibernate.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class LanguageManager extends SessionManager<Language>
{
	private static final long serialVersionUID = 1L;

	public LanguageManager()
	{
		super(LanguageManager.class, Language.class, LanguageDAO.class);
	}

	@Override
	public void loadObjects(Session session, Language object) throws HibernateException 
	{	
	}

	@Override
	public List<String> getListOfConstraintViolations(Language object, Exception e) throws DAOException 
	{
		return new ArrayList<String>();
	}
	
	public Language getDefaultLanguage() throws DAOException
	{
		Session session = null;
		Language language = null;
		
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			LanguageDAO dao = new LanguageDAO(session);
			language = dao.getDefaultLanguage();
		}
		catch(HibernateException e)
		{
			logger.error("LanguageManager.getDefaultLanguage()- An error has occurred while retrieving default language.", e);
			throw new DAOException("An error has occurred while retrieving default language.", e);
		}
		finally
		{
			if(session != null)
			{
				session.flush();
				session.close();
			}
		}
		
		return language;
	}
}
