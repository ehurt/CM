package org.church.management.domain.exceptions;

/**
 * 
 * @author Administrator
 *
 * This exception is thrown when there is no object to
 * be updated or deleted. 
 */
public class DAOStaleStateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DAOStaleStateException()
	{
		super();
	}
	
	public DAOStaleStateException(String message)
	{
		super(message);
	}
	
	public DAOStaleStateException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	public DAOStaleStateException(Throwable cause)
	{
		super(cause);
	}

}
