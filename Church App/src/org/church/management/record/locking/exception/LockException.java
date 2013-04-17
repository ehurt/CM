package org.church.management.record.locking.exception;

import java.util.Date;

import org.church.management.record.locking.info.LockInfo;

public class LockException extends Exception 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username = "";
	private Object lock = null;
	private Date date = null;
	
	public LockException(String username, Object lock, Date date)
	{
		this.username = username;
		this.lock = lock;
		this.date = date;
	}
	
	public LockException(LockInfo info)
	{
		this.username = info.getUsername();
		this.lock = info.getLock();
		this.date = info.getDate();
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
}
