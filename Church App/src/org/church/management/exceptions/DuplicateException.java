package org.church.management.exceptions;

public class DuplicateException extends Exception 
{

	private static final long serialVersionUID = 1L;

	public DuplicateException(String message)
	{
		super(message);
	}
}
