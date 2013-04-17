package test.org.church.management.utils;

import junit.framework.Assert;

import org.church.management.utils.UrlUtils;
import org.junit.Test;

public class UrlUtilsTester 
{
	@Test
	public void validPattern()
	{
		boolean valid = false;
		
		valid = UrlUtils.validPattern("*");
		
		Assert.assertEquals(valid, false);
		
		valid = UrlUtils.validPattern("");
		
		Assert.assertEquals(true, true);
		
		valid = UrlUtils.validPattern("*.");
		
		Assert.assertEquals(valid, false);
		
		valid = UrlUtils.validPattern("...*");
		
		Assert.assertEquals(valid, false);
		
		valid = UrlUtils.validPattern("*.*");
		
		Assert.assertEquals(valid, false);
		
		valid = UrlUtils.validPattern("*.122.33.333");
		
		Assert.assertEquals(valid, false);
		
		valid = UrlUtils.validPattern("123.333.*.333");
		
		Assert.assertEquals(valid, false);
		
		valid = UrlUtils.validPattern("2.39.12.*");
		Assert.assertEquals(valid, true);
		
		valid = UrlUtils.validPattern("1.1.1.1");
		Assert.assertEquals(valid, true);
		
		valid = UrlUtils.validPattern("255.255.255.255");
		Assert.assertEquals(valid, true);
		
		valid = UrlUtils.validPattern("192.168.1.1");
		Assert.assertEquals(valid, true);
		
		valid = UrlUtils.validPattern("10.10.1.1");
		Assert.assertEquals(valid, true);
		
		valid = UrlUtils.validPattern("132.254.111.10");
		Assert.assertEquals(valid, true);
		
		valid = UrlUtils.validPattern("26.10.2.10");
		Assert.assertEquals(valid, true);
		
		valid = UrlUtils.validPattern("127.0.0.1");
		Assert.assertEquals(valid, true);
		
		valid = UrlUtils.validPattern("a.a.a.*");
		Assert.assertEquals(valid, false);
		
		valid = UrlUtils.validPattern("10.20.a.*");
		Assert.assertEquals(valid, false);
		
		valid = UrlUtils.validPattern("10.20.100.a");
		Assert.assertEquals(valid, false);
		
		valid = UrlUtils.validPattern("10.10.10");
		Assert.assertEquals(valid, false);
		
		Object[][] address = InvalidIPAddressProvider();
		
		for(int i = 0; i < address.length; i++)
		{
			Object[] array = address[i];
			
			for (int j = 0; j < array.length; j++)
			{
				valid = UrlUtils.validPattern(array[j].toString());
				Assert.assertEquals(valid, false);
			}
		}
		
	}
	
	public Object[][] InvalidIPAddressProvider() {
		return new Object[][]{
		   new Object[] {"10.10.10"},new Object[] {"10.10"},
                   new Object[] {"10"},new Object[] {"a.a.a.a"},
                   new Object[] {"10.0.0.a"},new Object[] {"10.10.10.256"},
		   new Object[] {"222.222.2.999"},new Object[] {"999.10.10.20"},
                   new Object[] {"2222.22.22.22"},new Object[] {"22.2222.22.2"},
                   new Object[] {"10.10.10"},new Object[] {"10.10.10"},	
		};
	}
	
	@Test 
	public void testVerifyIP()
	{
		String pattern = "100.*";
		
		boolean valid = UrlUtils.validPattern(pattern);
		Assert.assertEquals(valid, true);
		
		String host = "100.150.170.200";
		valid = UrlUtils.verifyIPAddress(pattern, host);
		
		Assert.assertEquals(valid, true);
		
		host = "150.33.39.212";
		valid = UrlUtils.verifyIPAddress(pattern, host);
		Assert.assertEquals(valid, false);
		
		host = "101.150.170.200";
		valid = UrlUtils.verifyIPAddress(pattern, host);
		
		Assert.assertEquals(valid, false);
		
		pattern = "100.150.*";
		
		host = "100.150.170.200";
		valid = UrlUtils.verifyIPAddress(pattern, host);
		
		Assert.assertEquals(valid, true);
		
		host = "100.151.170.200";
		valid = UrlUtils.verifyIPAddress(pattern, host);
		
		Assert.assertEquals(valid, false);
		
		host = "100.150.1.2";
		valid = UrlUtils.verifyIPAddress(pattern, host);
		
		Assert.assertEquals(valid, true);
		
		pattern = "100.150.49.*";
		valid = UrlUtils.validPattern(pattern);
		
		Assert.assertEquals(valid, true);
		
		host = "101.149.49.4";
		valid = UrlUtils.verifyIPAddress(pattern, host);
		Assert.assertEquals(valid, false);
		
		host = "100.150.49.4";
		valid = UrlUtils.verifyIPAddress(pattern, host);
		Assert.assertEquals(valid, true);
		
		pattern = "100.150.49.3";
		host = "100.150.49.3";
		valid = UrlUtils.verifyIPAddress(pattern, host);
		Assert.assertEquals(valid, true);
		
		host = "100.150.49.2";
		valid = UrlUtils.verifyIPAddress(pattern, host);
		Assert.assertEquals(valid, false);
	
		host = "100.150.49.03";
		valid = UrlUtils.verifyIPAddress(pattern, host);
		Assert.assertEquals(valid, true);
	}
}
