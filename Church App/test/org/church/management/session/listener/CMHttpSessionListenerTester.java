package org.church.management.session.listener;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import javax.servlet.http.HttpSessionEvent;

import junit.framework.Assert;

import org.junit.Test;

public class CMHttpSessionListenerTester {

	@SuppressWarnings("static-access")
	@Test
	public void testSession()
	{
		CMHttpSessionListener listener = new CMHttpSessionListener();
		Session session = new Session("263jfjnsksd");
		
		HttpSessionEvent event = new HttpSessionEvent(session);
		
		listener.sessionCreated(event);
		
		Assert.assertEquals(session.getMaxInactiveInterval(), 5400);
		Assert.assertEquals(listener.getSessions().size(), 1);
		
		listener.sessionDestroyed(event);
		Assert.assertEquals(listener.getSessions().size(), 0);
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
