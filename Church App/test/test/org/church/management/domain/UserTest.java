package test.org.church.management.domain;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;

import org.church.management.domain.Preference;
import org.church.management.domain.User;
import org.church.management.domain.UserPreference;
import org.church.management.domain.exceptions.DAOConstraintViolationException;
import org.church.management.domain.exceptions.DAOException;
import org.church.management.domain.exceptions.DAONoObjectFoundException;
import org.church.management.domain.exceptions.DAOStaleStateException;
import org.church.management.domain.hibernate.dao.HibernateUserDao;
import org.church.management.exceptions.DuplicateException;
import org.church.management.hibernate.utils.HibernateUtil;
import org.church.management.record.locking.exception.LockException;
import org.hibernate.Session;
import org.joda.time.LocalTime;
import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;

//done testing
public class UserTest extends TestCase 
{
	HibernateUserDao dao = null;
	
	public UserTest()
	{
		dao = new HibernateUserDao();
		dao.setDebugging(true);
		
		HibernateUtil.getConfiguration();
		HibernateUtil.getSessionFactory();
		dao.setSessionFactory(HibernateUtil.getSessionFactory());
	}
	
	public void testInsertUser() throws DAOException, DAOConstraintViolationException, DAONoObjectFoundException
	{
		
	}
		/*
		User user = new User();
		user.setUsername("Trae");
		user.setPassword("jimmy2000");
		user.setCellPhone("407-375-8975");
		user.setPhone("407-375-8975");
		user.setEmail("ehurtiii@yahoo.com");
		user.setFirstName("Trae");
		user.setLastName("Hurt");
		user.setQuote("Sleep is good.");
		
		user.setFax("123-445-6996");
		user.setDisabled(false);
		user.setDeleted(false);
		user.setResetPassword(false);
		
		LocalTime startOfDay = new LocalTime(8, 30, 0);
		LocalTime endOfDay = new LocalTime(17, 30, 0);
		
		user.setStartOfDay(startOfDay);
		user.setEndOfDay(endOfDay);
		
		LocalTime startTime = new LocalTime(9, 0, 0);
		LocalTime endTime = new LocalTime(18, 0, 0);
		
		System.out.println("end time : "+endTime);
		
		user.setStartTime(startTime);
		user.setEndTime(endTime);
		
		Session session = dao.getCurrentSession();
		session.beginTransaction();
		dao.persist(user);
		session.getTransaction().commit();
		
		User user2 = new User();
		user2.setId(user.getId());
		
		dao.get(user.getId());
		
		Transaction transaction = session.beginTransaction();
		session.delete(user);
		transaction.commit();
		session.close();
	}
	
	public void testDelete() throws DAOException, DAOConstraintViolationException, DAONoObjectFoundException, DAOStaleStateException, LockException
	{
		User user = new User();
		user.setUsername("Trae");
		user.setPassword("jimmy2000");
		user.setCellPhone("407-375-8975");
		user.setPhone("407-375-8975");
		user.setEmail("ehurtiii@yahoo.com");
		user.setFirstName("Trae");
		user.setLastName("Hurt");
		user.setQuote("Sleep is good.");
		
		user.setFax("123-445-6996");
		user.setDisabled(false);
		user.setDeleted(false);
		user.setResetPassword(false);
		
		LocalTime startOfDay = new LocalTime(8, 30, 0);
		LocalTime endOfDay = new LocalTime(17, 30, 0);
		
		user.setStartOfDay(startOfDay);
		user.setEndOfDay(endOfDay);
		
		LocalTime startTime = new LocalTime(9, 0, 0);
		LocalTime endTime = new LocalTime(18, 0, 0);
		
		System.out.println("end time : "+endTime);
		
		user.setStartTime(startTime);
		user.setEndTime(endTime);
		
		Session session = dao.getCurrentSession();
		
		dao.persist(user);
		
		Transaction transaction = session.beginTransaction();
		dao.delete(user);
		transaction.commit();
		
		try
		{
			dao.load(user.getId());
			Assert.assertEquals(false, true);
		}
		catch(Exception e)
		{
			System.out.println("User not found!");
		}
		
		session.close();
	}
	
	public void testGetAll() throws DAOException, DAOConstraintViolationException, DAONoObjectFoundException, DAOStaleStateException, LockException
	{
		User user = new User();
		user.setUsername("Trae");
		user.setPassword("jimmy2000");
		user.setCellPhone("407-375-8975");
		user.setPhone("407-375-8975");
		user.setEmail("ehurtiii@yahoo.com");
		user.setFirstName("Trae");
		user.setLastName("Hurt");
		user.setQuote("Sleep is good.");
		
		user.setFax("123-445-6996");
		user.setDisabled(false);
		user.setDeleted(false);
		user.setResetPassword(false);
		
		LocalTime startOfDay = new LocalTime(8, 30, 0);
		LocalTime endOfDay = new LocalTime(17, 30, 0);
		
		user.setStartOfDay(startOfDay);
		user.setEndOfDay(endOfDay);
		
		LocalTime startTime = new LocalTime(9, 0, 0);
		LocalTime endTime = new LocalTime(18, 0, 0);
		
		System.out.println("end time : "+endTime);
		
		user.setStartTime(startTime);
		user.setEndTime(endTime);
		
		Session session = dao.getCurrentSession();
		
		session.beginTransaction();
		dao.persist(user);
		session.getTransaction().commit();
		
		user = new User();
		user.setUsername("Trey");
		user.setPassword("jimmy2000");
		user.setCellPhone("407-375-8975");
		user.setPhone("407-375-8975");
		user.setEmail("ehurtiii@yahoo.com");
		user.setFirstName("Trae");
		user.setLastName("Hurt");
		user.setQuote("Sleep is good.");
		
		user.setFax("123-445-6996");
		user.setDisabled(false);
		user.setDeleted(false);
		user.setResetPassword(false);
		
		startOfDay = new LocalTime(8, 30, 0);
		endOfDay = new LocalTime(17, 30, 0);
		
		user.setStartOfDay(startOfDay);
		user.setEndOfDay(endOfDay);
		
		startTime = new LocalTime(9, 0, 0);
		endTime = new LocalTime(18, 0, 0);
		
		System.out.println("end time : "+endTime);
		
		user.setStartTime(startTime);
		user.setEndTime(endTime);
		
		session.beginTransaction();
		dao.persist(user);
		session.getTransaction().commit();
		
		user = new User();
		user.setUsername("Ashley");
		user.setPassword("jimmy2000");
		user.setCellPhone("407-375-8975");
		user.setPhone("407-375-8975");
		user.setEmail("ehurtiii@yahoo.com");
		user.setFirstName("Trae");
		user.setLastName("Hurt");
		user.setQuote("Sleep is good.");
		
		user.setFax("123-445-6996");
		user.setDisabled(false);
		user.setDeleted(false);
		user.setResetPassword(false);
		
		startOfDay = new LocalTime(8, 30, 0);
		endOfDay = new LocalTime(17, 30, 0);
		
		user.setStartOfDay(startOfDay);
		user.setEndOfDay(endOfDay);
		
		startTime = new LocalTime(9, 0, 0);
		endTime = new LocalTime(18, 0, 0);
		
		System.out.println("end time : "+endTime);
		
		user.setStartTime(startTime);
		user.setEndTime(endTime);
		
		session.beginTransaction();
		dao.persist(user);
		session.getTransaction().commit();
		
		session.beginTransaction();
		dao.delete(user);
		session.getTransaction().commit();
		
		List<User> list = dao.findAll();
		
		Assert.assertEquals(list.size(),2);
		
		for(User user2 : list)
		{
			if(user2.getUsername().equals("Ashley"))
			{
				Assert.assertEquals(true, false);
			}
		}
		
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("delete from User");
		query.executeUpdate();
		transaction.commit();
		session.close();
	}
	
	public void testRetrieveByUsername() throws DAOException, DAOConstraintViolationException, DAONoObjectFoundException
	{
		User user = new User();
		user.setUsername("Trae");
		user.setPassword("jimmy2000");
		user.setCellPhone("407-375-8975");
		user.setPhone("407-375-8975");
		user.setEmail("ehurtiii@yahoo.com");
		user.setFirstName("Trae");
		user.setLastName("Hurt");
		user.setQuote("Sleep is good.");
		
		user.setFax("123-445-6996");
		user.setDisabled(false);
		user.setDeleted(false);
		user.setResetPassword(false);
		
		LocalTime startOfDay = new LocalTime(8, 30, 0);
		LocalTime endOfDay = new LocalTime(17, 30, 0);
		
		user.setStartOfDay(startOfDay);
		user.setEndOfDay(endOfDay);
		
		LocalTime startTime = new LocalTime(9, 0, 0);
		LocalTime endTime = new LocalTime(18, 0, 0);
		
		System.out.println("end time : "+endTime);
		
		user.setStartTime(startTime);
		user.setEndTime(endTime);
		
		Session session = dao.getCurrentSession();
		
		session.beginTransaction();
		dao.persist(user);
		session.getTransaction().commit();
		
		User user2 = new User();
		user2 = dao.getUserByUserName("Trae");
		
		Assert.assertEquals(user.getId(), user2.getId());
		
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("delete from User");
		query.executeUpdate();
		transaction.commit();
		session.close();
	}
	

	public void testVerifyAddress()
	{
		
	}
	
	public void testHashCode()
	{
		User user = new User();
		user.setUsername("Trae");
		user.setPassword("jimmy2000");
		user.setCellPhone("407-375-8975");
		user.setPhone("407-375-8975");
		user.setEmail("ehurtiii@yahoo.com");
		user.setFirstName("Trae");
		user.setLastName("Hurt");
		user.setQuote("Sleep is good.");
		
		user.setFax("123-445-6996");
		user.setDisabled(false);
		user.setDeleted(false);
		user.setResetPassword(false);
		
		LocalTime startOfDay = new LocalTime(8, 30, 0);
		LocalTime endOfDay = new LocalTime(17, 30, 0);
		
		user.setStartOfDay(startOfDay);
		user.setEndOfDay(endOfDay);
		
		User user2 = user.clone();
		
		Assert.assertEquals(user.hashCode(), user2.hashCode());
	}
	
	public void testEquals()
	{
		User user = new User();
		user.setUsername("Trae");
		
		User user2 = new User();
		user2.setUsername("Trae");
		
		Assert.assertEquals(user, user2);
	}
	
	public void testMultipleUsernameNull()
	{
		Session session = dao.getCurrentSession();
		
		User user = new User();
		user.setUsername("Trae");
		user.setPassword("jimmy2000");
		user.setCellPhone("407-375-8975");
		user.setPhone("407-375-8975");
		user.setEmail("ehurtiii@yahoo.com");
		user.setFirstName("Trae");
		user.setLastName("Hurt");
		user.setQuote("Sleep is good.");
		
		user.setFax("123-445-6996");
		user.setDisabled(false);
		user.setDeleted(false);
		user.setResetPassword(false);
		
		session.beginTransaction();
		dao.persist(user);
		session.getTransaction().commit();
		
		User user2 = new User();
		user2.setUsername("Ashley");
		user2.setPassword("jimmy2000");
		user2.setCellPhone("407-375-8975");
		user2.setPhone("407-375-8975");
		user2.setEmail("ehurtiii@yahoo.com");
		user2.setFirstName("Trae");
		user2.setLastName("Hurt");
		user2.setQuote("Sleep is good.");
		
		user2.setFax("123-445-6996");
		user2.setDisabled(false);
		user2.setDeleted(false);
		user2.setResetPassword(false);

		session.beginTransaction();
		dao.delete(user);
		session.getTransaction().commit();
		
		session.beginTransaction();
		dao.delete(user2);
		session.getTransaction().commit();
		
		List<User> users = dao.findAll();
		
		Assert.assertEquals(users.size(), 0);
		
		session.close();
	}
	
	@Test
	public void testDuplicatePreferences()
	{
		User user = new User();
		user.setUsername("Trae");
		user.setPassword("jimmy2000");
		user.setCellPhone("407-375-8975");
		user.setPhone("407-375-8975");
		user.setEmail("ehurtiii@yahoo.com");
		user.setFirstName("Trae");
		user.setLastName("Hurt");
		user.setQuote("Sleep is good.");
		
		user.setFax("123-445-6996");
		user.setDisabled(false);
		user.setDeleted(false);
		user.setResetPassword(false);
		
		Preference preference = new Preference();
		preference.setName("Theme");
		
		try 
		{
			user.addPreference("start", preference);
		} catch (DuplicateException e) 
		{
			Assert.assertEquals(false, true);
		}
		
		Preference preference2 = new Preference();
		preference2.setName("Language");
		
		try
		{
			user.addPreference("en", preference2);
		}
		catch(Exception e)
		{
			Assert.assertEquals(false, true);
		}
		
		UserPreference themePreference = user.getPreference("Theme");
	
		Assert.assertEquals(themePreference.getId().getPreference().getName(), "Theme");
		
		Preference preference3 = new Preference();
		preference3.setName("theme");
		
		try
		{
			user.addPreference("", preference3);
			Assert.assertEquals(false, true);
		}
		catch(Exception e)
		{
			
		}
		
		user.removePreference(preference3);
		
		Assert.assertEquals(user.getPreferences().size(), 1);*/
	//}
}
