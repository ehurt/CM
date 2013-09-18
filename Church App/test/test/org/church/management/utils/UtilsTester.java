package test.org.church.management.utils;

import junit.framework.Assert;

import org.church.management.utils.Utils;
import org.joda.time.LocalTime;
import org.joda.time.chrono.GregorianChronology;
import org.junit.Test;

public class UtilsTester 
{
	@Test
	public void testLogin()
	{
		LocalTime start = new LocalTime(9, 0, 0, 0, GregorianChronology.getInstanceUTC());
		LocalTime end = new LocalTime(18, 0, 0, 0, GregorianChronology.getInstanceUTC());
		
		System.out.println(start.toDateTimeToday()+" "+end.toDateTimeToday());
		
		boolean check = Utils.validLoginTime(start, end);
		
		Assert.assertEquals(check, false);
	}
}
