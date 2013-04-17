package org.church.management.record.locking.info;

import java.util.Date;

public class LockInfo 
{
	private String sessionId = "";
	private String username = "";
	private Object lock = null;
	private Date date = null;
	
	public LockInfo(String sessionId, String username, Object lock, Date date)
	{
		this.setSessionId(sessionId);
		this.username = username;
		this.lock = lock;
		this.date = date;
	}
	
	public void setUsername(String username) 
	{
		this.username = username;
	}
	
	public String getUsername() 
	{
		return username;
	}
	
	public void setLock(Object lock) 
	{
		this.lock = lock;
	}
	
	public Object getLock() 
	{
		return lock;
	}
	
	public void setDate(Date date) 
	{
		this.date = date;
	}
	
	public Date getDate() 
	{
		return date;
	}

	public void setSessionId(String sessionId) 
	{
		this.sessionId = sessionId;
	}

	public String getSessionId() 
	{
		return sessionId;
	}
}
