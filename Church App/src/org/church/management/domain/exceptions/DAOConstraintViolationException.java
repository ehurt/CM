package org.church.management.domain.exceptions;

import java.util.List;

/**
 * 
 * @author Administrator
 *
 * This exception is thrown when some constraints 
 * were broken on the database. There is a list of violations
 * in this class.
 */
public class DAOConstraintViolationException extends Exception 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> violations = null;
	
	public DAOConstraintViolationException()
	{
		super();
	}
	
	public DAOConstraintViolationException(String message, List<String> violations)
	{
		super(message);
		this.violations = violations;
	}
	
	public DAOConstraintViolationException(String message, Throwable cause, List<String> violations)
	{
		super(message, cause);
		this.violations = violations;
	}
	
	public DAOConstraintViolationException(Throwable cause, List<String> violations)
	{
		super(cause);
		this.violations = violations;
	}
	
	public List<String> getConstraintViolations()
	{
		return violations;
	}
}
