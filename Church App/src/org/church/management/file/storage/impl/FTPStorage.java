package org.church.management.file.storage.impl;

import it.sauronsoftware.ftp4j.FTPCodes;
import it.sauronsoftware.ftp4j.FTPDataTransferListener;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.church.management.annotations.Supported;
import org.church.management.annotations.Unsupported;
import org.church.management.file.storage.CloudFileStorage;
import org.church.management.ftp.connection.pool.FTPConnectionPoolManagement;
import org.church.management.ftp.session.FTPSession;
/***
 * 
 * @author Trae
 * 
 * This class is for storing files on a ftp server.
 *
 * need properties
 * 		host: the ip address to the ftp
 * 		port: the port for the ftp server
 * 		username: the username for ftp login
 * 		password: the password for ftp login
 * 		passive: this for data upload, the usually setting is true
 *		noopTimeout: this variable will ping the server to keep the connection alive.
 *		storageFolder: this directory is where all the directories and files are stored.
 *		
 *		minPoolSize: the minimum number of connections in the pool.
 *		maxPoolSize: the max number of connections in the pool.
 *
 *		TODO will need new properties for passive and active
 */
public class FTPStorage implements CloudFileStorage
{
	private static final Logger logger = Logger.getLogger(FTPStorage.class);
	
	private static String tempFolderPath;
	private static String fileSeparator;
	private String storageFolder;
	
	private FTPConnectionPoolManagement pool;
	
	static
	{
		fileSeparator = System.getProperty("file.separator");
	}
	
	public FTPStorage(Properties properties) throws Exception
	{
		Integer port = null;
		Integer noopTimeout = Integer.parseInt(properties.getProperty("noopTimeout"));
		String username = properties.getProperty("username");
		Boolean passive = Boolean.parseBoolean(properties.getProperty("passive"));
		String password = properties.getProperty("password");
		String host = properties.getProperty("host");
		int minimumPoolSize = Integer.parseInt(properties.getProperty("minimumPoolSize"));
		int maximumPoolSize = Integer.parseInt(properties.getProperty("maximumPoolSize"));
		
		String hostPort = properties.getProperty("port");
		
		if(hostPort == null)
		{
			port = null;
		}
		
		else 
		{
			port = Integer.parseInt(properties.getProperty("port"));
		}
		
		storageFolder = properties.getProperty("storageFolder");
		
		URL url = Thread.currentThread().getContextClassLoader().getResource("hibernate.cfg.xml");
		String path = url.getFile().replace("%20", " ");
		File file = new File(path);
		File parent = file.getParentFile();
		parent = parent.getParentFile();
		File tempFolder = new File(parent.getPath()+fileSeparator+"temp");
		tempFolderPath = tempFolder.getPath();
		
		if(port != null)
		{
			pool = new FTPConnectionPoolManagement(host, port, username, password, storageFolder, passive, minimumPoolSize, maximumPoolSize, noopTimeout);
		}
		
		else
		{
			pool = new FTPConnectionPoolManagement(host, username, password, storageFolder, passive, minimumPoolSize, maximumPoolSize, noopTimeout);
		}
	}
	
	@Supported
	public String createDirectory(String directory) throws Exception
	{
		String path = "";
		FTPSession session = null;
				
		try 
		{
			session = pool.openSession();
			path = session.createDirectory(directory);
		} 
		
		catch (IllegalStateException e) 
		{
			logger.error("FTPStorage.createDirectory()- Illegal State on the server.", e);
			throw e;
		} 
		
		catch (IOException e) 
		{
			logger.error("FTPServer.createDirectory()- IO Error could not create the directory.", e);
			throw e;
		} 
		
		catch (FTPIllegalReplyException e) 
		{
			logger.error("FTPServer.createDirectory()- Could not create the directory: "+directory+".", e);
			throw e;
		} 
		
		catch (FTPException e) 
		{
			logger.error("FTPServer.createDirectory()- Could not create the directory: "+directory+".", e);
			throw e;
		}
		
		finally
		{
			if(session != null)
			{
				session.close();
			}
		}
		
		return path;
	}

	@Supported
	public void deleteDirectory(String directory) throws Exception
	{
		FTPSession session = null;
		
		try 
		{
			session = pool.openSession();
			session.deleteDirectory(directory);
		} 
		
		catch (IllegalStateException e) 
		{
			logger.error("FTPServer.deleteDirectory()- Illegal State happened while deleting directory: "+directory+".", e);
			throw e;
		} 
		
		catch (IOException e) 
		{
			logger.error("FTPServer.deleteDirectory()- IO Error deleting the directory: "+directory+".", e);
			throw e;
		} 
		
		catch (FTPIllegalReplyException e) 
		{
			logger.error("FTPServer.deleteDirectory()- Could not delete the directory: "+directory+".", e);
			throw e;
		} 
		
		catch (FTPException e) 
		{
			logger.error("FTPServer.deleteDirectory()- Could not delete the directory: "+directory+".", e);
			
			if(e.getCode() == FTPCodes.FILE_NOT_FOUND)
			{
				throw new FileNotFoundException(directory+" could not be deleted.");
			}
			
			throw e;
		}
		
		finally
		{
			if(session != null)
			{
				session.close();
			}
		}
	}

	@Supported
	public String renameDirectory(String oldDirectly, String newDirectory) throws Exception 
	{
		return null;
	}

	@Override
	public String upload(String directory, File file) throws Exception 
	{
		String path = storageFolder+"/"+directory+"/"+file.getName();
		
		return null;
	}

	@Override
	public String copy(String folder, String renameFile, File file)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteFile(String filePath) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public File retrieve(String filePath) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update(String filePath, File file) throws Exception 
	{	
		return null;
	}

	@Override
	public String renameFile(String filepath, String newFileName)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Unsupported
	public List<String> getAvailableFileExtensions() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Unsupported
	public long getMemoryUsage() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Unsupported
	public long getMemoryLimitation() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<File> getAllFiles() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<File> getAllFilesForDirectory(String directory)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCloudStorage() 
	{
		return true;
	}

	@Override
	public File zipUpAllFiles(String archiveName) throws IOException
	{
		return null;
	}

	@Override
	public File zipUpDirectory(String fullPath, String archiveName) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public FTPConnectionPoolManagement getPool()
	{
		return this.pool;
	}
	
	/**
	 * 
	 * @author Trae
	 *
	 * This class is monitoring data transfer to ftp server.
	 *
	 */
	private class CMFTPDataTransferListener implements FTPDataTransferListener
	{

		public void aborted() {

		}

		public void completed() {

		}

		public void failed() {

		}

		public void started() {

		}

		public void transferred(int arg0) {

		}
	}

	public boolean isConnected() 
	{
		return pool.isConnected();
	}

	@Override
	public void disconnect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean reconnect() {
		// TODO Auto-generated method stub
		return false;
	}
}
