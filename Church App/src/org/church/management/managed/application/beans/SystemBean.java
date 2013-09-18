package org.church.management.managed.application.beans;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpSession;

import org.church.management.session.listener.CMHttpSessionListener;

/**
 * 
 * @author Trae Hurt
 *
 * This bean controls the state of the application, broadcast a system message,
 * and invalidate current users.
 *
 */
@ManagedBean
@ApplicationScoped
public class SystemBean 
{
	//keep track of how many errors have occurred 
	//while the application been running
	private static long errorCount = 0;
	
	//Version of the application
	private String version = "";
	
	//Will put the application in read-only state
	private boolean isDisabled = false;
	
	//Last Exception throw in the system
	private static Exception systemError = null;
	
	public SystemBean()
	{
		
	}
	
	@PostConstruct
	public void init()
	{
		//TODO get the bean in here which allow connection to all the users.
	}

	public static synchronized void addErrorCount() 
	{
		errorCount++;
	}

	public synchronized long getErrorCount() 
	{
		return errorCount;
	}
	
	public String getVersion()
	{
		return version;
	}
	
	public void setVersion(String version)
	{
		this.version = version;
	}

	public void setDisabled(boolean isDisabled) 
	{
		this.isDisabled = isDisabled;
	}

	public boolean isDisabled() 
	{
		return isDisabled;
	}
	
	/**
	 * 
	 * This method will take a status and a message to all the current users.
	 * 
	 * @param status : error, warning, and information
	 * @param message : a message
	 */
	public void broadCastMessage(String status, String message)
	{
		//TODO finish up broadCasting message to all the current users. Fire an event to broadcast message.
	}

	public static synchronized void setSystemError(Exception error) 
	{
		systemError = error;
	}

	public synchronized Exception getSystemError() 
	{
		return systemError;
	}

	public void invalidate(String sessionId, String message)
	{
		HttpSession session = CMHttpSessionListener.getSession(sessionId);
		
		if(session != null)
		{
			try
			{
				//invoke an event first to give them a message
				session.invalidate();
			}
			catch(Exception e){}
		}
	}
	
	public List<HttpSession> getAllCurrentSessions()
	{
		return new ArrayList<HttpSession>(CMHttpSessionListener.getSessions());
	}
}
