package test.org.church.management.domain;

import java.util.List;

import junit.framework.Assert;

import org.church.management.domain.Language;
import org.church.management.domain.exceptions.DAOConstraintViolationException;
import org.church.management.domain.exceptions.DAOException;
import org.church.management.domain.exceptions.DAONoObjectFoundException;
import org.church.management.domain.exceptions.DAOStaleStateException;
import org.church.management.record.locking.exception.LockException;
import org.junit.Test;

public class LanguageTester 
{
	
	@Test
	public void testLanguage() throws DAOException, DAOConstraintViolationException, DAONoObjectFoundException, DAOStaleStateException, LockException
	{
		Language language = new Language();
		language.setName("English");
		language.setAbbrevation("en");
		
		language.save();
		language.delete();
	}
	
	@Test
	public void getAllLanguages() throws DAOException, DAOConstraintViolationException, DAONoObjectFoundException, DAOStaleStateException, LockException
	{
		Language language = new Language();
		language.setName("English");
		language.setAbbrevation("en");
		
		language.save();
		List<Language> languages = language.getAll();
		Assert.assertEquals(languages.size(), 1);
		language.delete();
	}
}
