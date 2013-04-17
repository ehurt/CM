package test.org.church.management.domain;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;

import org.church.management.domain.User;
import org.church.management.domain.exceptions.DAOConstraintViolationException;
import org.church.management.domain.exceptions.DAOException;
import org.church.management.domain.exceptions.DAONoObjectFoundException;
import org.church.management.domain.exceptions.DAOStaleStateException;
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
	@Test
	public void testInsertUser() throws DAOException, DAOConstraintViolationException, DAONoObjectFoundException
	{
		User user = new User();
		user.setUsername("Trae");
		user.setPassword("jimmy2000");
		user.setCellPhone("407-375-8975");
		user.setPhone("407-375-8975");
		user.setEmail("ehurtiii@yahoo.com");
		user.setFirstName("Trae");
		user.setLastName("Hurt");
		user.setTheme("start");
		user.setQuote("Sleep is good.");
		
		user.setFax("123-445-6996");
		user.setDisabled(false);
		user.setDeleted(false);
		user.setResetPassword(false);
		user.setLanguage(null);
		
		LocalTime startOfDay = new LocalTime(8, 30, 0);
		LocalTime endOfDay = new LocalTime(17, 30, 0);
		
		user.setStartOfDay(startOfDay);
		user.setEndOfDay(endOfDay);
		
		LocalTime startTime = new LocalTime(9, 0, 0);
		LocalTime endTime = new LocalTime(18, 0, 0);
		
		System.out.println("end time : "+endTime);
		
		user.setStartTime(startTime);
		user.setEndTime(endTime);
		
		user.save();
		
		User user2 = new User();
		user2.setId(user.getId());
		
		user2.retrieve();
		
		
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(user);
		transaction.commit();
		session.close();
	}
	
	@Test
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
		user.setTheme("start");
		user.setQuote("Sleep is good.");
		
		user.setFax("123-445-6996");
		user.setDisabled(false);
		user.setDeleted(false);
		user.setResetPassword(false);
		user.setLanguage(null);
		
		LocalTime startOfDay = new LocalTime(8, 30, 0);
		LocalTime endOfDay = new LocalTime(17, 30, 0);
		
		user.setStartOfDay(startOfDay);
		user.setEndOfDay(endOfDay);
		
		LocalTime startTime = new LocalTime(9, 0, 0);
		LocalTime endTime = new LocalTime(18, 0, 0);
		
		System.out.println("end time : "+endTime);
		
		user.setStartTime(startTime);
		user.setEndTime(endTime);
		
		user.save();
		
		user.delete();
		
		try
		{
			user.retrieve();
			Assert.assertEquals(false, true);
		}
		catch(DAONoObjectFoundException e)
		{
			System.out.println("User not found!");
		}
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(user);
		transaction.commit();
		session.close();
	}
	
	@Test
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
		user.setTheme("start");
		user.setQuote("Sleep is good.");
		
		user.setFax("123-445-6996");
		user.setDisabled(false);
		user.setDeleted(false);
		user.setResetPassword(false);
		user.setLanguage(null);
		
		LocalTime startOfDay = new LocalTime(8, 30, 0);
		LocalTime endOfDay = new LocalTime(17, 30, 0);
		
		user.setStartOfDay(startOfDay);
		user.setEndOfDay(endOfDay);
		
		LocalTime startTime = new LocalTime(9, 0, 0);
		LocalTime endTime = new LocalTime(18, 0, 0);
		
		System.out.println("end time : "+endTime);
		
		user.setStartTime(startTime);
		user.setEndTime(endTime);
		
		user.save();
		
		user = new User();
		user.setUsername("Trey");
		user.setPassword("jimmy2000");
		user.setCellPhone("407-375-8975");
		user.setPhone("407-375-8975");
		user.setEmail("ehurtiii@yahoo.com");
		user.setFirstName("Trae");
		user.setLastName("Hurt");
		user.setTheme("start");
		user.setQuote("Sleep is good.");
		
		user.setFax("123-445-6996");
		user.setDisabled(false);
		user.setDeleted(false);
		user.setResetPassword(false);
		user.setLanguage(null);
		
		startOfDay = new LocalTime(8, 30, 0);
		endOfDay = new LocalTime(17, 30, 0);
		
		user.setStartOfDay(startOfDay);
		user.setEndOfDay(endOfDay);
		
		startTime = new LocalTime(9, 0, 0);
		endTime = new LocalTime(18, 0, 0);
		
		System.out.println("end time : "+endTime);
		
		user.setStartTime(startTime);
		user.setEndTime(endTime);
		
		user.save();
		
		user = new User();
		user.setUsername("Ashley");
		user.setPassword("jimmy2000");
		user.setCellPhone("407-375-8975");
		user.setPhone("407-375-8975");
		user.setEmail("ehurtiii@yahoo.com");
		user.setFirstName("Trae");
		user.setLastName("Hurt");
		user.setTheme("start");
		user.setQuote("Sleep is good.");
		
		user.setFax("123-445-6996");
		user.setDisabled(false);
		user.setDeleted(false);
		user.setResetPassword(false);
		user.setLanguage(null);
		
		startOfDay = new LocalTime(8, 30, 0);
		endOfDay = new LocalTime(17, 30, 0);
		
		user.setStartOfDay(startOfDay);
		user.setEndOfDay(endOfDay);
		
		startTime = new LocalTime(9, 0, 0);
		endTime = new LocalTime(18, 0, 0);
		
		System.out.println("end time : "+endTime);
		
		user.setStartTime(startTime);
		user.setEndTime(endTime);
		
		user.save();
		
		user.delete();
		
		List<User> list = user.findAll();
		
		Assert.assertEquals(list.size(),2);
		
		for(User user2 : list)
		{
			if(user2.getUsername().equals("Ashley"))
			{
				Assert.assertEquals(true, false);
			}
		}
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("delete from User");
		query.executeUpdate();
		transaction.commit();
		session.close();
	}
	
	@Test
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
		user.setTheme("start");
		user.setQuote("Sleep is good.");
		
		user.setFax("123-445-6996");
		user.setDisabled(false);
		user.setDeleted(false);
		user.setResetPassword(false);
		user.setLanguage(null);
		
		LocalTime startOfDay = new LocalTime(8, 30, 0);
		LocalTime endOfDay = new LocalTime(17, 30, 0);
		
		user.setStartOfDay(startOfDay);
		user.setEndOfDay(endOfDay);
		
		LocalTime startTime = new LocalTime(9, 0, 0);
		LocalTime endTime = new LocalTime(18, 0, 0);
		
		System.out.println("end time : "+endTime);
		
		user.setStartTime(startTime);
		user.setEndTime(endTime);
		
		user.save();
		
		User user2 = new User();
		user2.setUsername("Trae");
		user2.getUserByUsername();
		
		Assert.assertEquals(user.getId(), user2.getId());
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("delete from User");
		query.executeUpdate();
		transaction.commit();
		session.close();
	}
	
	@Test
	public void testVerifyAddress()
	{
		
	}
}
