package test.org.church.management.domain;

import junit.framework.Assert;

import org.church.management.domain.Ethnicity;
import org.junit.Test;

public class EthnicityTester 
{
	@Test
	public void testEquals()
	{
		Ethnicity e = new Ethnicity();
		e.setName("White");
		
		Ethnicity e1 = new Ethnicity();
		e1.setName("white");
		
		Assert.assertEquals(e.equals(e1), true);
		
		System.out.println(e1.hashCode());
	}
}
