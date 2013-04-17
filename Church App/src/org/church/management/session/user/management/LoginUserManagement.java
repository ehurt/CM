package org.church.management.session.user.management;

import java.util.List;

import org.church.management.domain.User;
import org.church.management.session.user.SessionUser;

public interface LoginUserManagement 
{
	public boolean login(String sessionId, User user);
	
	public void logout(String sessionId);
	
	public List<SessionUser> getListOfCurrentUsers();
	
	public SessionUser getUserBySessionId(String sessionId);
}
