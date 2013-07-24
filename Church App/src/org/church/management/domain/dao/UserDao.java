package org.church.management.domain.dao;

import org.church.management.domain.User;
import org.church.management.domain.generic.dao.GenericDao;

public interface UserDao extends GenericDao<User, Integer>
{
	public boolean login(String username, String password);
	
	public User getUserByUserName(String username);
}
