package org.church.management.log.appender;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.ThrowableInformation;

/**
 * 
 * @author Trae
 * 
 * This class will catch the logging events and send them
 * the project management system (jira or ontimenow).
 *
 */
public class BugReportingAppender extends AppenderSkeleton
{

	public void close() 
	{
			
	}

	@Override
	public boolean requiresLayout() 
	{
		return false;
	}

	@Override
	protected void append(LoggingEvent event) 
	{
		ThrowableInformation info = event.getThrowableInformation();
		Level level = event.getLevel();
		
		if(level == Level.ERROR)
		{
			
		}
		
		else if(level == Level.FATAL)
		{
			
		}
		
		else if(level == Level.WARN)
		{
			
		}
		
		else if(level == Level.DEBUG && info != null)
		{
			
		}
		
		else if(level == Level.INFO && info != null)
		{
			
		}
		
		else if(level == Level.TRACE && info != null)
		{
			
		}
		
		else if(level == Level.OFF && info != null)
		{
			
		}
		
		else if(level == Level.ALL && info != null)
		{
			
		}
	}
}
