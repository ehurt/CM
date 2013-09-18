package test.org.church.management.domain;

import junit.framework.Assert;

import org.church.management.domain.ValidHostAddress;
import org.church.management.utils.UrlUtils;
import org.junit.Test;

public class ValidHostAddressTester extends TestMethods
{

	@Test
	public void testValidateAddress()
	{
		ValidHostAddress address = new ValidHostAddress();
		address.setPattern("100.*");
		
		String host = "100.150.170.200";
		boolean valid = UrlUtils.verifyIPAddress(address.getPattern(),  host);
		
		Assert.assertEquals(valid, true);
		
		host = "150.33.39.212";
		valid = UrlUtils.verifyIPAddress(address.getPattern(),  host);
		Assert.assertEquals(valid, false);
		
		host = "101.150.170.200";
		valid = UrlUtils.verifyIPAddress(address.getPattern(),  host);
		
		Assert.assertEquals(valid, false);
		
		host = "100.150.170.200";
		valid = UrlUtils.verifyIPAddress(address.getPattern(),  host);
		
		Assert.assertEquals(valid, true);
		
		host = "100.151.170.200";
		valid = UrlUtils.verifyIPAddress(address.getPattern(),  host);
		
		Assert.assertEquals(valid, true);
		
		host = "100.150.1.2";
		valid = UrlUtils.verifyIPAddress(address.getPattern(),  host);
		
		Assert.assertEquals(valid, true);
		
		host = "101.149.49.4";
		valid = UrlUtils.verifyIPAddress(address.getPattern(),  host);
		Assert.assertEquals(valid, false);
		
		host = "100.150.49.4";
		valid = UrlUtils.verifyIPAddress(address.getPattern(),  host);
		Assert.assertEquals(valid, true);
		
		host = "100.150.49.3";
		valid = UrlUtils.verifyIPAddress(address.getPattern(),  host);
		Assert.assertEquals(valid, true);
		
		host = "100.150.49.2";
		valid = UrlUtils.verifyIPAddress(address.getPattern(),  host);
		Assert.assertEquals(valid, true);
	
		host = "100.150.49.03";
		valid = UrlUtils.verifyIPAddress(address.getPattern(),  host);
		Assert.assertEquals(valid, true);
	}

	@Test
	public void testMap() {
		// TODO Auto-generated method stub
		
	}

	@Test
	public void testList() {
		// TODO Auto-generated method stub
		
	}

	@Test
	public void testEquals() 
	{
		ValidHostAddress address = new ValidHostAddress();
		address.setPattern("100.*");
		address.setEntity("User");
		address.setObjectId("1");
		
		ValidHostAddress address2 = address.clone();
		
		Assert.assertEquals(address.equals(address), true);
		Assert.assertEquals(address.equals(address2), true);
		
		Assert.assertEquals(address.equals(null), false);
		
		address2.setEntity("Host");
		address2.setObjectId("3");
		
		Assert.assertEquals(address.equals(address2), false);
		
		Assert.assertEquals(address.equals(""), false);
	}

	@Test
	public void testHashCode() 
	{
		ValidHostAddress address = new ValidHostAddress();
		address.setPattern("100.*");
		address.setEntity("User");
		address.setObjectId("1");
		
		System.out.println("Hash Code "+address.hashCode());
		
		ValidHostAddress address2 = address.clone();
		
		Assert.assertEquals(address.hashCode(), address2.hashCode());
	}

	@Test
	public void testClone() 
	{
		ValidHostAddress address = new ValidHostAddress();
		address.setPattern("100.*");
		address.setEntity("User");
		address.setObjectId("1");
		
		ValidHostAddress address2 = address.clone();
		
		Assert.assertEquals(address, address2);
		
	}
}
