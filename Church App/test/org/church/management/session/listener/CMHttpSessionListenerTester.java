package org.church.management.session.listener;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import javax.servlet.http.HttpSessionEvent;

import junit.framework.Assert;

import org.church.management.configuration.Configuration;
import org.church.management.domain.Note;
import org.church.management.domain.User;
import org.church.management.record.locking.exception.LockException;
import org.church.management.record.locking.info.LockInfo;
import org.church.management.record.locking.management.ObjectLockSystemManager;
import org.church.management.session.user.SessionUser;
import org.church.management.session.user.manager.SessionSystemManager;
import org.church.management.session.user.manager.exception.ConcurrentLoginException;
import org.junit.Test;

public class CMHttpSessionListenerTester {

	@SuppressWarnings("static-access")
	@Test
	public void testSession() throws ConcurrentLoginException
	{
		CMHttpSessionListener listener = new CMHttpSessionListener();
		Session session = new Session("263jfjnsksd");
		
		HttpSessionEvent event = new HttpSessionEvent(session);
		
		User user = new User();
		user.setId(1);
		
		SessionSystemManager.addUser(session.getId(), user);
		
		listener.sessionCreated(event);
		
		Assert.assertEquals(session.getMaxInactiveInterval(), 5400);
		Assert.assertEquals(listener.getSessions().size(), 1);
		
		listener.sessionDestroyed(event);
		Assert.assertEquals(listener.getSessions().size(), 0);
	}
	
	@Test
	public void testSessionDestroy() throws ConcurrentLoginException, LockException
	{
		Configuration.getResourceBundle();
		
		User user = new User();
		user.setId(1);
		CMHttpSessionListener listener = new CMHttpSessionListener();
		Session session = new Session("263jf78sksd");
		
		HttpSessionEvent event = new HttpSessionEvent(session);
		
		SessionSystemManager.addUser(session.getId(), user);
		
		Session session2 = new Session("382882");
		
		User user2 = new User();
		user2.setId(2);
		SessionSystemManager.addUser(session2.getId(), user2);
		
		Note note = new Note();
		note.setId(2);
		
		ObjectLockSystemManager.lock(note, session.getId(), "Tester1");
		
		note = new Note();
		note.setId(3);
		
		ObjectLockSystemManager.lock(note, session2.getId(), "Tester2");
		
		listener.sessionCreated(event);
		
		HttpSessionEvent event2 = new HttpSessionEvent(session2);
		
		listener.sessionCreated(event2);
		
		listener.sessionDestroyed(event);
		
		List<SessionUser> users = SessionSystemManager.getListOfCurrentUsers();
		
		Assert.assertEquals(users.size(), 1);
		
		List<LockInfo> locks = ObjectLockSystemManager.listOfLocks(session.getId());
		
		Assert.assertEquals(locks.size(), 0);
		
	}
	
	public class Session implements HttpSession
	{
		private String id;
		private int timeout;
		
		public Session(String id)
		{
			this.id = id;
		}

		@Override
		public Object getAttribute(String arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Enumeration<String> getAttributeNames() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getCreationTime() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public String getId() {
			// TODO Auto-generated method stub
			return id;
		}

		@Override
		public long getLastAccessedTime() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int getMaxInactiveInterval() {
			// TODO Auto-generated method stub
			return timeout;
		}

		@Override
		public ServletContext getServletContext() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public HttpSessionContext getSessionContext() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object getValue(String arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String[] getValueNames() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void invalidate() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean isNew() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void putValue(String arg0, Object arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void removeAttribute(String arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void removeValue(String arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setAttribute(String arg0, Object arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setMaxInactiveInterval(int arg0) {
			this.timeout = arg0;
			
		}
		
	}

}
