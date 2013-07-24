package org.church.management.servlet.context.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.church.management.configuration.Configuration;
import org.church.management.hibernate.utils.HibernateUtil;
import org.church.management.security.realm.SecurityRealm;
import org.church.management.session.user.manager.SessionSystemManager;

public class InitialContextListener implements ServletContextListener
{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) 
	{
		//TODO sent an alert to the centralized server to tell it the application is going down.
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) 
	{
		//load configuration file
		Configuration.getResourceBundle();
		
		//set the log4j log path, so the log files can be written out.
		System.setProperty("logPath", Configuration.getString("logPath"));
		
		//load Session System Manager
		SessionSystemManager.getListOfCurrentUsers();
		
		//Call Spring Container and create a dao for user.
		SecurityRealm realm = new SecurityRealm();
		DefaultSecurityManager manager = new DefaultSecurityManager(realm);
		SecurityUtils.setSecurityManager(manager);
		HibernateUtil.getSessionFactory();
	}

}
