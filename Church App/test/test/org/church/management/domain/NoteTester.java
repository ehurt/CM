package test.org.church.management.domain;

import java.util.List;

import junit.framework.Assert;

import org.church.management.domain.Note;
import org.church.management.domain.User;
import org.church.management.domain.hibernate.dao.HibernateNoteDao;
import org.church.management.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

public class NoteTester 
{
	@Test
	public void testRetrieveNote()
	{	
		HibernateNoteDao dao = new HibernateNoteDao();
		dao.setDebugging(true);
		
		HibernateUtil.getConfiguration();
		HibernateUtil.getSessionFactory();
		dao.setSessionFactory(HibernateUtil.getSessionFactory());
		
		Session session = dao.getCurrentSession();
		
		User user = new User();
		user.setUsername("Trae");
		user.setPassword("12345");
		
		Transaction transaction = session.beginTransaction();
		session.persist(user);
		transaction.commit();
		
		transaction = session.beginTransaction();
		
		for(int i = 0; i < 5; i++)
		{
			Note note = new Note();
			note.setTitle("Note "+i);
			note.setBody("Body Note "+i);
			note.setEntity("User");
			note.setObjectId("1");
			note.setPrivate(false);
			
			dao.persist(note);
		}
		
		for(int i = 0; i < 5; i++)
		{
			Note note = new Note();
			note.setTitle("Note "+i);
			note.setBody("Body Note "+i);
			note.setEntity("User");
			note.setObjectId("2");
			note.setPrivate(false);
			
			dao.persist(note);
		}
		
		for(int i = 0; i < 5; i++)
		{
			Note note = new Note();
			note.setTitle("Note "+i);
			note.setBody("Body Note "+i);
			note.setEntity("User");
			note.setObjectId("1");
			note.setPrivate(true);
			note.setOwner(user);
			
			dao.persist(note);
		}
		
		transaction.commit();
		
		List<Note> openNotes = dao.retrieveOpenNotes("User", "1");
		List<Note> privateNotes = dao.retrievePrivateNotesByUser("User", "1", user);
		
		for(Note note: openNotes)
		{
			System.out.println(note.getTitle()+" "+note.isPrivate());
		}
		
		for(Note note: privateNotes)
		{
			System.out.println(note.getTitle()+" "+note.isPrivate());
		}
		
		Assert.assertEquals(openNotes.size(), 5);
		Assert.assertEquals(privateNotes.size(), 5);
		
		session.close();
	}
}
