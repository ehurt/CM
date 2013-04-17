package org.church.management.domain.exceptions;

/**
 * 
 * @author Administrator
 *
 * This exception is thrown when there is no object found
 * in the database that matches the id.
 *
 */
public class DAONoObjectFoundException extends Exception 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DAONoObjectFoundException()
	{
		super();
	}
	
	public DAONoObjectFoundException(String message)
	{
		super(message);
	}
	
	public DAONoObjectFoundException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	public DAONoObjectFoundException(Throwable cause)
	{
		super(cause);
	}
}
