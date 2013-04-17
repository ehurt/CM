package test.org.church.management.utils;

import javax.persistence.Column;

import junit.framework.Assert;

import org.church.management.domain.User;
import org.church.management.utils.FieldUtils;
import org.junit.Test;

public class FieldUtilsTester {

	@Test
	public void testColumnLength() 
	{
		int length = FieldUtils.getColumnLength(User.class, "firstName", 75);
		Assert.assertEquals(length, 50);
	}
	
	@Test 
	public void testColumnName()
	{
		String name = FieldUtils.getColunmName(User.class, "firstName");
		Assert.assertEquals(name.equals("first_name"), true);
	}
	
	@Test
	public void testColumnNullable()
	{
		boolean nullable = FieldUtils.isColumnNullable(TestClass.class, "name");
		
		Assert.assertEquals(nullable, false);
	}
	
	@Test
	public void testColumnUnique()
	{
		boolean unique = FieldUtils.isColumnUnique(TestClass.class, "name");
		
		Assert.assertEquals(unique, true);
	}

	public class TestClass
	{
		@Column(name="NAME", nullable=false, unique=true, length=50)
		private String name;
	}
}
