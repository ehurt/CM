package org.church.management.session.system.manager;

import java.util.List;

import junit.framework.Assert;

import org.church.management.configuration.Configuration;
import org.church.management.domain.User;
import org.church.management.session.user.SessionUser;
import org.church.management.session.user.manager.SessionSystemManager;
import org.church.management.session.user.manager.exception.ConcurrentLoginException;
import org.junit.Test;

public class SessionSystemManagementTester {

	@Test
	public void testSingleLoginUser()
	{
		Configuration.getResourceBundle();
		SessionSystemManager.setAllowSameUserNameMultipleTimes(false);
		
		User user1 = new User();
		user1.setId(1);
		String sessionId1 = "AB123456";
		
		User user2 = new User();
		user2.setId(1);
		String sessionId2 = "CD123456";
		
		//first time login 
		try {
			SessionSystemManager.addUser(sessionId1, user1);
		} catch (ConcurrentLoginException e) {
			Assert.assertEquals(true, false);
		}
		
		//Reload back in
		try {
			SessionSystemManager.addUser(sessionId1, user1);
		} catch (ConcurrentLoginException e) {
			Assert.assertEquals(true, false);
		}
		
		//first time login for user2, it should fail, because the user with id 1 is already logged in.
		try {
			SessionSystemManager.addUser(sessionId2, user2);
			Assert.assertEquals(true, false);
		} catch (ConcurrentLoginException e) {

		}
		
		user2.setId(2);
		
		//user2 should be able to login now.
		try {
			SessionSystemManager.addUser(sessionId2, user2);
		} catch (ConcurrentLoginException e) {
			Assert.assertEquals(true, false);
		}
		
		user2.setId(1);
		
		//user 2 should not be to login as user 1
		
		try {
			SessionSystemManager.addUser(sessionId2, user2);
			Assert.assertEquals(true, false);
		} catch (ConcurrentLoginException e) {
		}
		
		user1.setId(3);
		
		//logged in some else
		try {
			SessionSystemManager.addUser(sessionId1, user1);
		} catch (ConcurrentLoginException e) {
			Assert.assertEquals(true, false);
		}
		
		List<SessionUser> users = SessionSystemManager.getListOfCurrentUsers();
		
		Assert.assertEquals(users.size(), 2);
		
		for(SessionUser user : users)
			System.out.println("Session User "+user.getUser().getId()+" "+user.getSessionId());
		
		SessionUser sessionUser = SessionSystemManager.getUserInfo(sessionId1);
		
		Assert.assertEquals(sessionId1, sessionUser.getSessionId());
		
		SessionSystemManager.logout(sessionId1);
		
		users = SessionSystemManager.getListOfCurrentUsers();
		
		for(SessionUser user : users)
			System.out.println("Session User "+user.getUser().getId()+" "+user.getSessionId());
		
		Assert.assertEquals(users.size(), 1);
		
		SessionSystemManager.logout(sessionId2);
		
		users = SessionSystemManager.getListOfCurrentUsers();
		
		Assert.assertEquals(users.size(), 0);
		
		user1.setId(1);
		
		try {
			SessionSystemManager.addUser(sessionId1, user1);
		} catch (ConcurrentLoginException e) {
			Assert.assertEquals(true, false);
		}
		
		SessionSystemManager.invalidUser(sessionId1);
		
		users = SessionSystemManager.getListOfCurrentUsers();
		
		Assert.assertEquals(users.size(), 0);
		
		System.out.println("SingleLoginManagement successfully passed.");
	}
	
	@Test
	public void testMultipleLoginUser()
	{
		Configuration.getResourceBundle();
		SessionSystemManager.setAllowSameUserNameMultipleTimes(true);
		
		User user1 = new User();
		user1.setId(1);
		String sessionId1 = "AB123456";
		
		User user2 = new User();
		user2.setId(1);
		String sessionId2 = "CD123456";
		
		try {
			SessionSystemManager.addUser(sessionId1, user1);
		} catch (ConcurrentLoginException e) {
			Assert.assertEquals(true, false);
		}
		
		try {
			SessionSystemManager.addUser(sessionId2, user2);
		} catch (ConcurrentLoginException e) {
			Assert.assertEquals(true, false);
		}
		
		List<SessionUser> users = SessionSystemManager.getListOfCurrentUsers();
	
		for(SessionUser user : users)
			System.out.println("Session User "+user.getUser().getId()+" "+user.getSessionId());
		
		Assert.assertEquals(users.size(), 2);
		
		SessionUser sessionUser = SessionSystemManager.getUserInfo(sessionId1);
		
		Assert.assertEquals(sessionId1, sessionUser.getSessionId());
		
		user1.setId(3);
		
		try {
			SessionSystemManager.addUser(sessionId1, user1);
		} catch (ConcurrentLoginException e) {
			Assert.assertEquals(true, false);
		}
		
		user2.setId(3);
		
		try {
			SessionSystemManager.addUser(sessionId2, user2);
		} catch (ConcurrentLoginException e) {
			Assert.assertEquals(true, false);
		}
		
		SessionSystemManager.logout(sessionId1);
		
		users = SessionSystemManager.getListOfCurrentUsers();
		
		for(SessionUser user : users)
			System.out.println("Session User "+user.getUser().getId()+" "+user.getSessionId());
		
		Assert.assertEquals(users.size(), 1);
		
		SessionSystemManager.invalidUser(sessionId2);
		
		users = SessionSystemManager.getListOfCurrentUsers();
		
		Assert.assertEquals(users.size(), 0);
		
		System.out.println("MultipleLoginManagement successfully passed.");
	}
}
