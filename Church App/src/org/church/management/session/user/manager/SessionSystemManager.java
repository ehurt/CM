package org.church.management.session.user.manager;

import java.util.List;

import org.apache.log4j.Logger;
import org.church.management.configuration.Configuration;
import org.church.management.domain.User;
import org.church.management.session.user.SessionUser;
import org.church.management.session.user.management.LoginUserManagement;
import org.church.management.session.user.management.MultipleLoginUserManagement;
import org.church.management.session.user.management.SingleLoginUserManagement;
import org.church.management.session.user.manager.exception.UserAlreadyLoginException;



/**
 * 
 * @author THurt
 *
 * This class is in charge of managing the http sessions.
 * It will not allow more than one with the same user name
 * to be in the system at one time if configured.
 *
 */
public class SessionSystemManager 
{
	private static boolean allowDuplicateUser = Configuration.getBoolean("allowMultipleUserLogin", false);
	private static final Logger logger = Logger.getLogger(SessionSystemManager.class);
	private static LoginUserManagement management = null;
	
	static 
	{
		configureLoginManagement();
	}
	
	/**
	 * 
	 * This method will add a current user. If the username is already taken
	 * it will throw an exception.
	 * 
	 * @param session: the user's http session
	 * @param user: the user information.
	 * @throws UserAlreadyLoginException: if the user is already logged in.
	 */
	public synchronized static void addUser(String sessionId, User user) throws UserAlreadyLoginException
	{
		boolean isLoggedIn = management.login(sessionId, user);
		logger.info("SessionSystemManager.addUser()- User "+user.getId()+" "+sessionId+" was added "+sessionId);
		
		if(isLoggedIn == false){
			throw new UserAlreadyLoginException("The user is currently login in.");
		}
	}
	
	private static void configureLoginManagement()
	{
		if(allowDuplicateUser)
		{
			management = new MultipleLoginUserManagement();
		}
		
		else
		{
			management = new SingleLoginUserManagement();
		}
	}
	
	/**
	 * 
	 * This method will invalidate a user and remove them from the system.
	 * 
	 * @param user
	 */
	public synchronized static void invalidUser(String sessionId)
	{
		SessionUser user = management.getUserBySessionId(sessionId);
		management.logout(sessionId);
		logger.info("SessionSystemManager.invalidUser()- About to invalid user "+user.getUser().getId()+".");
	}
	
	public synchronized static void logout(String sessionId)
	{
		SessionUser user = management.getUserBySessionId(sessionId);
		management.logout(sessionId);
		logger.info("SessionSystemManager.logout()- About to logout user "+user.getUser().getId()+".");
	}
	
	public static void setAllowSameUserNameMultipleTimes(boolean allowSameUserNameMultipleTimes) 
	{
		SessionSystemManager.allowDuplicateUser = allowSameUserNameMultipleTimes;
		configureLoginManagement();
	}

	public static boolean isAllowSameUserNameMultipleTimes() 
	{
		return allowDuplicateUser;
	}

	public synchronized static List<SessionUser> getListOfCurrentUsers()
	{
		return management.getListOfCurrentUsers();
	}
	
	public synchronized static SessionUser getUserInfo(String sessionId)
	{
		return management.getUserBySessionId(sessionId);
	}
}
