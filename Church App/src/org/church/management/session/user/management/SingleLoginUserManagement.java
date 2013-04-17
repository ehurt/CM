package org.church.management.session.user.management;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;
import org.church.management.domain.User;
import org.church.management.session.user.SessionUser;


/**
 * 
 * @author Trae
 *
 * This class will only allow single user to user name.
 * 
 */
public class SingleLoginUserManagement implements LoginUserManagement
{
	public static Logger logger = Logger.getLogger(SingleLoginUserManagement.class);
	public Hashtable<Integer, SessionUser> currentUsers = new Hashtable<Integer, SessionUser>();
	public Hashtable<String, Integer> sessionToUsers = new Hashtable<String, Integer>();
	
	public List<SessionUser> getListOfCurrentUsers() 
	{
		ArrayList<SessionUser> users = new ArrayList<SessionUser>();
		users.addAll(currentUsers.values());
		return users;
	}

	public boolean login(String sessionId, User user)
	{
		SessionUser sessionUser = null;
		
		//a different session login as a user already logged in.
		boolean sessionExists = sessionToUsers.containsKey(sessionId);
		boolean userLogin = currentUsers.containsKey(user.getId());
		
		//re-login
		if(sessionExists && userLogin)
		{
			//need to check if the session Id are different if so then return false
			sessionUser = currentUsers.get(user.getId());
			
			if(sessionUser.getSessionId().equals(sessionId) == false)
			{
				System.out.println("A different user login as someone already in the system.");
				return false;
			}
			
			System.out.println("Reloaded Back in");
			return true;
		}
		
		//logging as someone else
		else if(sessionExists && userLogin == false)
		{
			System.out.println("Logged in as different user");
			Integer id = sessionToUsers.get(sessionId);
			
			if(id != null)
			{
				sessionUser = currentUsers.get(id);
				currentUsers.remove(id);
				
				sessionUser.setUser(user);
				currentUsers.put(user.getId(), sessionUser);
				sessionToUsers.put(sessionId, user.getId());
			}
		}
		
		//first user logging and the user is already logged in
		else if(sessionExists == false && userLogin == true)
		{
			System.out.println("First time Different user try to login as someone already logged in.");
			return false;
		}
		
		else if(sessionExists == false && userLogin == false)
		{
			sessionUser = new SessionUser(sessionId, user, new Date());
			sessionToUsers.put(sessionId, user.getId());
			currentUsers.put(user.getId(), sessionUser);
		}
		
		return true;
	}

	public void logout(String sessionId) 
	{
		Integer userId = sessionToUsers.get(sessionId);
		sessionToUsers.remove(sessionId);
		
		if(userId != null)
		{
			currentUsers.remove(userId);
		}
	}

	@Override
	public SessionUser getUserBySessionId(String sessionId) 
	{
		SessionUser user = null;
		Integer userId = sessionToUsers.get(sessionId);
		
		if(userId != null){
			user = currentUsers.get(userId);
		}
		
		return user;
	}
}
