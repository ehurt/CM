package org.church.management.security.realm;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ConcurrentAccessException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.HostUnauthorizedException;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.church.management.domain.User;
import org.church.management.domain.ValidHostAddress;
import org.church.management.domain.exceptions.DAOException;
import org.church.management.security.token.UsernamePasswordSessionToken;
import org.church.management.session.user.manager.SessionSystemManager;
import org.church.management.session.user.manager.exception.UserAlreadyLoginException;
import org.church.management.utils.UrlUtils;
import org.joda.time.LocalTime;

/**
 * 
 * @author Trae
 *
 * This class will authorization the logins for security.
 *
 */
public class SecurityRealm extends AuthorizingRealm
{

	public SecurityRealm()
	{
		setName("SecurityName");
	}
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException 
	{
		
		String username, password, sessionId;
		User user = new User();
		
		UsernamePasswordSessionToken userToken = (UsernamePasswordSessionToken) token;
		username = userToken.getUsername();
		password = new String(userToken.getPassword());
		
		user.setUsername(username);
		user.setPassword(password);
		sessionId = userToken.getSessionId();
		
		try 
		{
			boolean login = user.login();
			boolean validIPAddress = false;
		
			//check if the account is disabled
			if(user.isDisabled())
			{
				throw new DisabledAccountException("User account is disabled.");
			}
			
			if(login)
			{
				//check login in time
				boolean loginTime = user.validLoginTime(new LocalTime());
				
				if(loginTime)
				{
					//check the ip address
					String host = userToken.getHost();
					
					List<ValidHostAddress> addresses = user.getValidHostAddressForUser();
					
					if(addresses.size() == 0)
					{
						validIPAddress = true;
					}
					
					else
					{
						for(ValidHostAddress address : addresses)
						{
							String pattern = address.getPattern();
							validIPAddress = UrlUtils.verifyIPAddress(pattern, host);
							
							if(validIPAddress)
							{
								break;
							}
						}
						
						if(validIPAddress == false)
						{
							throw new HostUnauthorizedException("The host: "+host+" is unauthorized for user: "+username+".");
						}
					}
					
					SessionSystemManager.addUser(sessionId, user);
				}
				
				else
				{
					throw new AuthenticationException("Cannot login.");
				}
			}
			
			else
			{
				throw new IncorrectCredentialsException("Credentials are incorrect.");
			}
		} 
		catch (DAOException e) 
		{
			throw new AuthenticationException("Could not authenticate.", e);
		}
		
		catch(UserAlreadyLoginException e)
		{
			throw new ConcurrentAccessException("The username is already logged in.");
		}
		
		SimpleAuthenticationInfo authInfo = new SimpleAuthenticationInfo(user.getId(), user.getPassword(), getName());
		
		//TODO figure out how to set permissions
		
		return authInfo;
	}

}
