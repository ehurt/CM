package org.church.management.validators;

import javax.faces.validator.Validator;

import org.junit.Assert;
import org.junit.Test;

public class ValidHostPatternValidatorTester 
{
	Validator validator = new ValidHostPatternValidator();
	
	@Test
	public void testPatterns()
	{
		testAsserts("", false);
		testAsserts("*", false);
		testAsserts("*.100", false);
		testAsserts("*.", false);
		testAsserts("...*", false);
		testAsserts("*.*", false);
		testAsserts("*.122.33.333", false);
		testAsserts("123.333.*.333", false);
		testAsserts("a.a.a.*", false);
		testAsserts("10.20.a.*", false);
		testAsserts("10.20.100.a", false);
		testAsserts("10.10.10", false);
		testAsserts("2.39.12.*", true);
		testAsserts("1.1.1.1", true);
		testAsserts("255.255.255.255", true);
		testAsserts("192.168.1.1", true);
		testAsserts("10.10.1.1", true);
		testAsserts("132.254.111.10", true);
		testAsserts("26.10.2.10", true);
		testAsserts("127.0.0.1", true);
		
	}
	
	private void testAsserts(String pattern, boolean result)
	{
		try
		{
			validator.validate(null, null, pattern);
			Assert.assertEquals(result, true);
			System.out.println("Pattern "+pattern+" "+result);
		}
		catch(Exception e)
		{
			System.out.println("Pattern "+pattern+" "+result);
			Assert.assertEquals(result, false);
		}
	}
}
