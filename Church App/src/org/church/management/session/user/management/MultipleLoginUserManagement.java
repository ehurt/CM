package org.church.management.session.user.management;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import org.church.management.domain.User;
import org.church.management.session.user.SessionUser;


/**
 * 
 * @author Trae
 *
 * This class will allow multiple users to use the same user account.
 *
 */
public class MultipleLoginUserManagement implements LoginUserManagement
{
	private Hashtable<String, SessionUser> currentUsers = new Hashtable<String, SessionUser>();
	
	public List<SessionUser> getListOfCurrentUsers() 
	{
		return (List<SessionUser>) Collections.unmodifiableCollection(currentUsers.values());
	}

	public boolean login(String sessionId, User user)
	{
		boolean sessionExists = currentUsers.containsKey(sessionId);
		
		//re-login
		if(sessionExists)
		{
			System.out.println("Logging in again.");
			SessionUser sessionUser = currentUsers.get(sessionId);
			sessionUser.setUser(user);
		}
		
		else
		{
			System.out.println("New User Logging in");
			SessionUser sessionUser = new SessionUser(sessionId, user, new Date());
			currentUsers.put(sessionId, sessionUser);
		}
		
		return true;
	}

	public void logout(String sessionId) 
	{
		currentUsers.remove(sessionId);
	}

	public SessionUser getUserBySessionId(String sessionId) 
	{
		return currentUsers.get(sessionId);
	}

}
