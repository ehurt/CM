package org.church.management.session.user.manager.exception;

public class ConcurrentLoginException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConcurrentLoginException(String message)
	{
		super(message);
	}
	
	public ConcurrentLoginException(String message, Throwable t)
	{
		super(message, t);
	}
}
