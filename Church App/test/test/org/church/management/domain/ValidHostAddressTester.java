package test.org.church.management.domain;

import junit.framework.Assert;

import org.church.management.domain.ValidHostAddress;
import org.church.management.domain.exceptions.DAOConstraintViolationException;
import org.church.management.domain.exceptions.DAOException;
import org.church.management.domain.exceptions.DAONoObjectFoundException;
import org.church.management.domain.exceptions.DAOStaleStateException;
import org.church.management.record.locking.exception.LockException;
import org.junit.Test;

import com.sun.tools.xjc.reader.xmlschema.bindinfo.BIConversion.User;

public class ValidHostAddressTester 
{
	@Test
	public void testSave() throws DAOException, DAOConstraintViolationException, DAONoObjectFoundException, DAOStaleStateException, LockException
	{
		ValidHostAddress address = new ValidHostAddress();
		address.setEntity(User.class.getSimpleName());
		address.setEntityId("1");
		address.setPattern("172.17.1.*");
		
		address.save();
		address.delete();
	}
	
	@Test
	public void testUpdate() throws DAOException, DAOConstraintViolationException, DAONoObjectFoundException, DAOStaleStateException, LockException
	{
		ValidHostAddress address = new ValidHostAddress();
		address.setEntity(User.class.getSimpleName());
		address.setEntityId("1");
		address.setPattern("172.17.1.*");
		
		address.save();
		
		address.setPattern("172.17.*");
		address.update();
		address.delete();
	}
	
	@Test
	public void testValidateAddress()
	{
		ValidHostAddress address = new ValidHostAddress();
		address.setPattern("100.*");
		
		String host = "100.150.170.200";
		boolean valid = address.validateIPAddress( host);
		
		Assert.assertEquals(valid, true);
		
		host = "150.33.39.212";
		valid = address.validateIPAddress( host);
		Assert.assertEquals(valid, false);
		
		host = "101.150.170.200";
		valid = address.validateIPAddress( host);
		
		Assert.assertEquals(valid, false);
		
		host = "100.150.170.200";
		valid = address.validateIPAddress( host);
		
		Assert.assertEquals(valid, true);
		
		host = "100.151.170.200";
		valid = address.validateIPAddress( host);
		
		Assert.assertEquals(valid, true);
		
		host = "100.150.1.2";
		valid = address.validateIPAddress( host);
		
		Assert.assertEquals(valid, true);
		
		host = "101.149.49.4";
		valid = address.validateIPAddress( host);
		Assert.assertEquals(valid, false);
		
		host = "100.150.49.4";
		valid = address.validateIPAddress( host);
		Assert.assertEquals(valid, true);
		
		host = "100.150.49.3";
		valid = address.validateIPAddress( host);
		Assert.assertEquals(valid, true);
		
		host = "100.150.49.2";
		valid = address.validateIPAddress( host);
		Assert.assertEquals(valid, true);
	
		host = "100.150.49.03";
		valid = address.validateIPAddress( host);
		Assert.assertEquals(valid, true);
	}
}
