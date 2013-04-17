package org.church.management.session.user.manager.exception;

public class UserAlreadyLoginException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserAlreadyLoginException(String message)
	{
		super(message);
	}
	
	public UserAlreadyLoginException(String message, Throwable t)
	{
		super(message, t);
	}
}
