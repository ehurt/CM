package org.church.management.ftp.session.impl;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.church.management.ftp.connection.pool.FTPConnectionPoolManagement;
import org.church.management.ftp.session.FTPSession;

import it.sauronsoftware.ftp4j.FTPClient;

public class FTPSessionImpl implements FTPSession
{
	private static final Logger logger = Logger.getLogger(FTPSessionImpl.class);
	private FTPClient client;
	private boolean closed = false;
	private FTPConnectionPoolManagement pool;
	private String currentDirectory = "";
	
	public FTPSessionImpl(String currentDirectory, FTPClient client, FTPConnectionPoolManagement pool)
	{
		this.client = client;
		this.pool = pool;
		this.currentDirectory = currentDirectory;
	}

	public void close() 
	{
		this.closed = true;
		pool.returnConnection(client);
	}
	
	public boolean isClosed()
	{
		return closed;
	}

	public String createDirectory(String directory) throws Exception 
	{
		if(closed)
		{
			throw new Exception("Session is currently closed.");
		}
		
		try
		{
			directory = currentDirectory+"/"+directory;
			client.createDirectory(directory);
		}
		catch(Exception e)
		{
			logger.error("FTPSessionImpl.createDirectory()- Could not create directory: "+directory+".", e);
			throw e;
		}
		
		return directory;
	}

	public void deleteDirectory(String directory) throws Exception 
	{
		if(closed)
		{
			throw new Exception("Session is currently closed.");
		}
		
		try
		{
			directory = currentDirectory+"/"+directory;
			client.deleteDirectory(directory);
		}
		catch(Exception e)
		{
			logger.error("FTPSessionImpl.deleteDirectory()- Could not delete directory: "+directory+".", e);
			throw e;
		}
	}

	public String renameDirectory(String oldDirectory, String name) throws Exception 
	{
		if(closed)
		{
			throw new Exception("Session is currently closed.");
		}
		
		try
		{
			client.rename(oldDirectory, name);
		}
		catch(Exception e)
		{
			logger.error("FTPSessionImpl.renameDirectory()- Could not rename directory: "+oldDirectory+" to "+name+".", e);
			throw e;
		}
		
		return name;
	}

	public String upload(String directory, File file) throws Exception 
	{
		String fullPath = "";
		
		if(closed)
		{
			throw new Exception("Session is currently closed.");
		}
		
		try
		{
			fullPath = directory+"/"+file.getName();
			client.changeDirectory(directory);
			client.upload(file);
			client.changeDirectory(currentDirectory);
		}
		catch(Exception e)
		{
			logger.error("FTPSessionImpl.upload()- Could not upload file to directory: "+directory+".", e);
			throw e;
		}
		
		return fullPath;
	}

	public String upload(String directory, String renameFile, File file)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(String filePath) throws Exception 
	{
		if(closed)
		{
			throw new Exception("Session is currently closed.");
		}
		
		try
		{
			client.deleteFile(filePath);
		}
		catch(Exception e)
		{
			logger.error("FTPSessionImpl.delete()- Could not delete file: "+filePath+".", e);
			throw e;
		}
	}

	public void retrieve(String remoteFilePath, File localFile) throws Exception 
	{
		if(closed)
		{
			throw new Exception("Session is currently closed.");
		}
		
		try
		{
			client.download(remoteFilePath, localFile);
		}
		catch(Exception e)
		{
			logger.error("FTPSessionImpl.retrieve()- Could not download file: "+remoteFilePath+".", e);
			throw e;
		}
	}

	public String update(String filePath, File file) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String renameFile(String filepath, String newFileName) throws Exception 
	{
		if(closed)
		{
			throw new Exception("Session is currently closed.");
		}
		
		try
		{
			//Test
			client.rename(filepath, newFileName);
		}
		catch(Exception e)
		{
			logger.error("FTPSessionImpl.renameFile()- Could not rename file: "+filepath+" to "+newFileName+".", e);
			throw e;
		}
		
		return null;
	}

	public List<File> getAllFilesForDirectory(String directory) throws Exception 
	{
		if(closed)
		{
			throw new Exception("Session is currently closed.");
		}
		
		return null;
	}
}
