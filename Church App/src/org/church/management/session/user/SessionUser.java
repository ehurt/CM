package org.church.management.session.user;

import java.util.Date;

import org.church.management.domain.User;


/**
 * 
 * @author Administrator
 *
 * This class will hold all the information about who is
 * logged in.
 *
 */
public class SessionUser
{
	private User user = null;
	private Date startTime = null;
	private String sessionId = "";
	
	public SessionUser(String sessionId, User user, Date time)
	{
		this.user = user;
		this.sessionId = sessionId;
		this.startTime = time;
	}
	
	public String getSessionId()
	{
		return sessionId;
	}
	
	public void setSessionId(String sessionId)
	{
		this.sessionId = sessionId;
	}

	public User getUser() 
	{
		return user;
	}

	public void setUser(User user) 
	{
		this.user = user;
	}

	public Date getStartTime() 
	{
		return startTime;
	}

	public void setStartTime(Date startTime) 
	{
		this.startTime = startTime;
	}
}
