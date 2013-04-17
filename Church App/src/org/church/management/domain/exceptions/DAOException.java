package org.church.management.domain.exceptions;

/**
 * 
 * @author Administrator
 *
 * This exception is thrown is an unexpected  
 * error has occurred in the managers.
 */
public class DAOException extends Exception 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DAOException()
	{
		super();
	}
	
	public DAOException(String message)
	{
		super(message);
	}
	
	public DAOException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	public DAOException(Throwable cause)
	{
		super(cause);
	}
}
