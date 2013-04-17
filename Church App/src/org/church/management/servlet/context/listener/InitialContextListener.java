package org.church.management.servlet.context.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.church.management.hibernate.utils.HibernateUtil;
import org.church.management.security.realm.SecurityRealm;

public class InitialContextListener implements ServletContextListener
{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) 
	{
		//TODO sent an alert to the centralized server to tell it, the application is going down.
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) 
	{
		SecurityRealm realm = new SecurityRealm();
		DefaultSecurityManager manager = new DefaultSecurityManager(realm);
		SecurityUtils.setSecurityManager(manager);
		HibernateUtil.getSessionFactory();
	}

}
