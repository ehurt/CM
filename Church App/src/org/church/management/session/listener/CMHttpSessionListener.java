package org.church.management.session.listener;

import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;
import org.church.management.configuration.Configuration;
import org.church.management.record.locking.management.ObjectLockSystemManager;
import org.church.management.session.user.SessionUser;
import org.church.management.session.user.manager.SessionSystemManager;

/**
 * 
 * @author Trae
 *
 * This class will listen for http session creation and
 * destroying.
 *
 */
public class CMHttpSessionListener implements HttpSessionListener
{
	private static Logger logger = Logger.getLogger(CMHttpSessionListenerTester.class);
	private static Hashtable<String, HttpSession> sessions = new Hashtable<String, HttpSession>();
	
	public void sessionCreated(HttpSessionEvent e) 
	{
		sessions.put(e.getSession().getId(), e.getSession());
		HttpSession session = e.getSession();
		
		int sessionTimeout = Configuration.getInt("sessionTimeout", 60);
		
		if(sessionTimeout < 0)
		{
			session.setMaxInactiveInterval(-1);
		}
		
		else
		{
			int timeout = sessionTimeout*60;
			session.setMaxInactiveInterval(timeout);
		}
	}

	public void sessionDestroyed(HttpSessionEvent e) 
	{
		String sessionId = e.getSession().getId();
		
		sessions.remove(sessionId);
		
		//unlock all the record locks.
		ObjectLockSystemManager.unlockAllRecordsForUser(sessionId);
	
		//remove the logout the user.
		SessionUser user = SessionSystemManager.getUserInfo(sessionId);
		SessionSystemManager.logout(sessionId);
		
		logger.debug("CMHttpSessionListener.sessionDestroyed()- Unlock all records for user: "+user.getUser().getUsername()+".");
	}
		
	public static Collection<HttpSession> getSessions()
	{
		return Collections.unmodifiableCollection(sessions.values());
	}
	
	public static HttpSession getSession(String sessionId)
	{
		return sessions.get(sessionId);
	}
}
