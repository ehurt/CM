package org.church.management.domain.hibernate.dao;

import java.io.Serializable;
import java.util.List;

import org.church.management.domain.User;
import org.church.management.domain.dao.UserDao;
import org.hibernate.Criteria;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class HibernateUserDao extends HibernateGenericDao<User, Integer> implements UserDao
{
	public HibernateUserDao()
	{
		super(User.class);
	}

	@Override
	public boolean login(String username, String password)
	{
		User user = null;
		Session session = getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("username", username));
		List<User> users = criteria.list();
		
		if(users.size() > 0)
		{
			user = users.get(0);
			
			if(user.getUsername().equals(username) && user.getPassword().equals(password))
			{
				return true;
			}
		}
		
		return false;
	}

	@Override
	public User getUserByUserName(String username) 
	{
		User user = null;
		Session session = getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("username", username));
		List<User > list = criteria.list();
		
		if(list.size() > 0)
		{
			user = list.get(0);
		}
		
		return user;
	}
	
	public void delete(User user)
	{
		Session session = getCurrentSession();
		
		user.setDeleted(true);
		user.setUsername(null);
		session.merge(user);
	}
	
	public List<User> findAll()
	{
		Session session = getCurrentSession();
		
		List<User> list = null;
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("isDeleted", false));
		list = criteria.list();
		
		return list;
	}
	
	public User load(Serializable id)
	{
		Session session = getCurrentSession();
		User user = (User) session.load(User.class, id);
		if(user.isDeleted())
		{
			throw new ObjectNotFoundException("User does not exist.", "");
		}
		
		return user;
	}
	
}
