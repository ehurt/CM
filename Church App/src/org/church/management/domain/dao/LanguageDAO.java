package org.church.management.domain.dao;

import java.util.List;

import org.church.management.domain.Language;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class LanguageDAO extends DAO<Language>
{

	private static final long serialVersionUID = 1L;

	public LanguageDAO(Session session)
	{
		super(session, LanguageDAO.class, Language.class);
	}
	
	public Language getDefaultLanguage()
	{
		Criteria criteria = session.createCriteria(Language.class);
		criteria.add(Restrictions.eq("isDefault", true));
		List<Language> defaultLanguages = criteria.list();
		
		if(defaultLanguages.size() == 0)
		{
			return null;
		}
		
		return defaultLanguages.get(0);
	}
}
