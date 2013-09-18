package org.church.management.utils;

import java.util.Calendar;

import org.church.management.domain.Address;
import org.church.management.domain.Attender;
import org.joda.time.LocalTime;

/**
 * 
 * @author Trae
 *
 * This class is used to perform variety of different tasks.
 *
 */
public class Utils 
{
	//TODO Set the Chronology
	
	public static boolean validLoginTime(LocalTime start, LocalTime end)
	{
		LocalTime now = LocalTime.now();
		
		if(start.compareTo(now) <= 0 && end.compareTo(now) >= 0)
		{
			return true;
		}
		
		return false;
	}
	
	public static LocalTime convertTimeToJodaLocalTime(Calendar dateTime)
	{
		int hourOfDay  = dateTime.get(Calendar.HOUR_OF_DAY);
		int minutes = dateTime.get(Calendar.MINUTE);
		
		return new LocalTime(hourOfDay, minutes);
	}
	
	public static String formatAddress(Address address)
	{
		return "";
	}
	
	public static String formatAttenderName(Attender attender)
	{
		return "";
	}
	
	public static String formatPhone()
	{
		return "";
	}
}
