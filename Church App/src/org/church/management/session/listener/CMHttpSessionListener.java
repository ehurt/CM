package org.church.management.session.listener;

import java.util.Hashtable;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;
import org.church.management.configuration.Configuration;

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
	private static Logger log = Logger.getLogger(CMHttpSessionListenerTester.class);
	private static Hashtable<String, HttpSession> sessions = new Hashtable<String, HttpSession>();
	
	public void sessionCreated(HttpSessionEvent e) 
	{
		sessions.put(e.getSession().getId(), e.getSession());
		HttpSession session = e.getSession();
		
		int timeout = Configuration.getInt("sessionTimeout", 60)*60;
		session.setMaxInactiveInterval(timeout);
	}

	public void sessionDestroyed(HttpSessionEvent e) 
	{
		sessions.remove(e.getSession().getId());
	}
	
	public static Hashtable<String, HttpSession> getSessions()
	{
		return sessions;
	}

}
