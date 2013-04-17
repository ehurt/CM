package test.org.church.management.file.storage;

import java.io.FileNotFoundException;
import java.util.Properties;

import junit.framework.Assert;

import org.church.management.configuration.Configuration;
import org.church.management.file.storage.impl.FTPStorage;
import org.church.management.ftp.connection.pool.FTPConnectionPoolManagement;
import org.church.management.ftp.connection.pool.exception.ConnectionPoolExhaustedException;
import org.church.management.ftp.session.FTPSession;
import org.junit.Test;

public class FTPServerTester 
{
	/**
	 * 
	 * This method will test that pool can create new connections if the pool
	 * is exhausted. It also test the exception being thrown.
	 * 
	 * @throws Exception
	 */
	public void testLoginAndPool() throws Exception
	{
		Configuration config = new Configuration();
		Properties properties = config.getResourceBundle();
		properties.setProperty("storageFolder", "cm");
		properties.setProperty("host", "127.0.0.1");
		properties.setProperty("port", "2121");
		properties.setProperty("username", "admin");
		properties.setProperty("password", "admin");
		properties.setProperty("noopTimeout", "1000");
		properties.setProperty("minimumPoolSize", "4");
		properties.setProperty("maximumPoolSize", "10");
		
		FTPStorage storage = new FTPStorage(properties);
		
		Assert.assertEquals(storage.isConnected(), true);
		FTPConnectionPoolManagement pool = storage.getPool();
		
		for(int i = 0; i < 11; i++)
		{
			try
			{
				FTPSession session = pool.openSession();
			}
			catch(ConnectionPoolExhaustedException e)
			{
				System.out.println("Pool exhausted.");
				Assert.assertEquals(pool.getPoolSize(), 0);
			}
		}
		
		Assert.assertEquals(pool.getActiveConnections(), 10);
		pool.disconnect();
	}
	
	public void testClosingSession() throws Exception
	{
		Configuration config = new Configuration();
		Properties properties = config.getResourceBundle();
		properties.setProperty("storageFolder", "cm");
		properties.setProperty("host", "127.0.0.1");
		properties.setProperty("port", "2121");
		properties.setProperty("username", "admin");
		properties.setProperty("password", "admin");
		properties.setProperty("noopTimeout", "1000");
		properties.setProperty("minimumPoolSize", "4");
		properties.setProperty("maximumPoolSize", "10");
		
		FTPStorage storage = new FTPStorage(properties);
		
		Assert.assertEquals(storage.isConnected(), true);
		FTPConnectionPoolManagement pool = storage.getPool();
		FTPSession session = null;
		
		for(int i = 0; i < 11; i++)
		{
			try
			{
				session = pool.openSession();
			}
			catch(Exception e)
			{
				System.out.println("Pool exhausted.");
			}
			
			finally
			{
				session.close();
			}
		}
		
		Assert.assertEquals(pool.getActiveConnections(), 4);
		Assert.assertEquals(pool.getPoolSize(), 4);
		
		pool.disconnect();
	}
	
	public void testCreateFolder() throws Exception
	{
		Configuration config = new Configuration();
		Properties properties = config.getResourceBundle();
		properties.setProperty("storageFolder", "cm");
		properties.setProperty("host", "127.0.0.1");
		properties.setProperty("port", "2121");
		properties.setProperty("username", "admin");
		properties.setProperty("password", "admin");
		properties.setProperty("noopTimeout", "1000");
		properties.setProperty("minimumPoolSize", "4");
		properties.setProperty("maximumPoolSize", "10");
		
		FTPStorage storage = new FTPStorage(properties);
		
		String path = storage.createDirectory("User_1_2992");
		
		FTPConnectionPoolManagement pool = storage.getPool();
		
		Assert.assertEquals(pool.getPoolSize(), 4);
		Assert.assertEquals(pool.getActiveConnections(), 4);
		
		Assert.assertEquals(path.equals("/cm/User_1_2992"), true);
		
		pool.disconnect();
	}
	
	@Test
	public void testDeleteDirectory() throws Exception
	{
		Configuration config = new Configuration();
		Properties properties = config.getResourceBundle();
		properties.setProperty("storageFolder", "cm");
		properties.setProperty("host", "127.0.0.1");
		properties.setProperty("port", "2121");
		properties.setProperty("username", "admin");
		properties.setProperty("password", "admin");
		properties.setProperty("noopTimeout", "10000");
		properties.setProperty("minimumPoolSize", "1");
		properties.setProperty("maximumPoolSize", "1");
		
		FTPStorage storage = new FTPStorage(properties);
		
		String directory = "User_1200";
		storage.createDirectory(directory);
		storage.deleteDirectory(directory);
		
		FTPConnectionPoolManagement pool = storage.getPool();
		
		Assert.assertEquals(pool.getPoolSize(), 1);
		Assert.assertEquals(pool.getActiveConnections(), 1);
		
		//TODO test if the folder has files in it.
		
		try
		{
			storage.deleteDirectory(directory);
			Assert.assertEquals(false, true);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		Assert.assertEquals(pool.getPoolSize(), 1);
		Assert.assertEquals(pool.getActiveConnections(), 1);
		
		pool.disconnect();
	}
}
