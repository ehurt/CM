package org.church.management.security.token;

import org.apache.shiro.authc.UsernamePasswordToken;

public class UsernamePasswordSessionToken extends UsernamePasswordToken
{
	private static final long serialVersionUID = 1L;

	private String sessionId;
	
	public UsernamePasswordSessionToken()
	{
		super();
	}

	public String getSessionId() 
	{
		return sessionId;
	}

	public void setSessionId(String sessionId) 
	{
		this.sessionId = sessionId;
	}
}
