package org.church.management.configuration;

import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;

@SuppressWarnings("rawtypes")
public class Configuration 
{
	private static Logger logger = Logger.getLogger(Configuration.class);
	private static Properties properties = null;
	
	static
	{
		try
		{
			String propertyFile = "church_application_default.properties";
			URL url = Thread.currentThread().getContextClassLoader().getResource(propertyFile);
			String path = url.getFile().replace("%20", " ");
			properties = new Properties();
			properties.load(new FileInputStream(path));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.fatal("Configuration.<init> - An error has occurred loading the church_application.properties.", e);
		}
	}
	
	public static String getString(String property, String defaultValue)
	{
		String value = properties.getProperty(property);
		
		if(value == null)
		{
			return defaultValue;
		}
		
		return value;
	}
	
	public static String getString(String property)
	{
		String value = properties.getProperty(property);
		return value;
	}
	
	public static Boolean getBoolean(String property, boolean defaultValue)
	{
		String value = properties.getProperty(property);
		Boolean bValue = null;
		
		if(value == null)
		{
			return defaultValue;
		}
		
		try
		{
			bValue = Boolean.parseBoolean(properties.getProperty(property));
		}
		catch(Exception e)
		{
			logger.error("Configuration.getBoolean()- An error has occurred while parsing boolean value: "+value+" for property: "+property+".", e);
			return null;
		}
		
		return bValue;
	}
	
	public static Integer getInt(String property, int defaultValue)
	{
		String value = properties.getProperty(property);
		Integer intValue = null;
		
		if(value == null)
		{
			return defaultValue;
		}
		
		try
		{
			intValue = Integer.parseInt(value);
		}
		catch(Exception e)
		{
			logger.error("Configuration.getInt()- An error has occurred while parsing int value: "+value+" for property: "+property+".", e);

		}
		
		return intValue;
	}
	
	public static Double getDouble(String property, double defaultValue)
	{
		String value = properties.getProperty(property);
		Double doubleValue = null;
		
		if(value == null)
		{
			return defaultValue;
		}
		
		try
		{
			doubleValue = Double.parseDouble(value);
		}
		catch(Exception e)
		{
			return null;
		}
		
		return doubleValue;
	}
	
	public static Class getClass(String parameterName, Class defaultValue) {
    	String value = getString(parameterName);
    	Class classValue = null;
    	
    	if(value == null)
    	{
    		return defaultValue;
    	}
    	
    	try
    	{
    		classValue = Class.forName(value);
    	}
    	catch(Exception e)
    	{
    		classValue = defaultValue;
    	}
    	
    	return classValue;
    }
	
	public static Properties getResourceBundle()
	{
		return properties;
	}
}
