package test.org.church.management.file.storage;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import org.church.management.configuration.Configuration;
import org.church.management.file.storage.FileStorage;
import org.church.management.file.storage.impl.ServerStorage;

import junit.framework.Assert;

import org.junit.Test;

/**
 *  
 *  update file,
 *  ApplicationFile
 */

public class ServerStorageTester 
{
	
	//The reflection is working.
	@SuppressWarnings("unchecked")
	@Test
	public void testServerStorageCreateDirectory() throws Exception
	{
		Class fileStorageClass = Configuration.getClass("fileStorage", ServerStorage.class);
		Properties bundle = Configuration.getResourceBundle();
		
		Constructor con = fileStorageClass.getConstructor(Properties.class);
		FileStorage storage = (FileStorage) con.newInstance(bundle);
		
		Assert.assertEquals(storage.getClass().getSimpleName(), "ServerStorage");
	
		System.getProperty("file.separator");
	}
	
	//The create Directory is working.
	@Test
	public void testCreateDirectory() throws Exception
	{
		Class fileStorageClass = Configuration.getClass("fileStorage", ServerStorage.class);
		Properties bundle = Configuration.getResourceBundle();
		
		Constructor con = fileStorageClass.getConstructor(Properties.class);
		FileStorage storage = (FileStorage) con.newInstance(bundle);
		
		String filename = storage.createDirectory("User_1_200202");
	
		File file = new File(filename);
		
		Assert.assertEquals(file.exists(), true);
		
		file.delete();
	}
	
	//The create file and deleteDirectory is working.
	@Test
	public void testCreateFile() throws Exception
	{
		Class fileStorageClass = Configuration.getClass("fileStorage", ServerStorage.class);
		Properties bundle = Configuration.getResourceBundle();
		
		Constructor con = fileStorageClass.getConstructor(Properties.class);
		FileStorage storage = (FileStorage) con.newInstance(bundle);
		
		String directoryPath = storage.createDirectory("User_1_200202");
		
		URL url = Thread.currentThread().getContextClassLoader().getResource("states.properties");
		String path = url.getFile().replace("%20", " ");
		
		File file = new File(path);
		String filepath = storage.copy("User_1_200202", "states.properties", file);
		
		file = new File(filepath);
		
		Assert.assertEquals(file.exists(), true);
		
		try
		{
			storage.deleteDirectory(directoryPath);
		}
		catch(Exception e)
		{
			Assert.assertEquals(true, false);
		}
		
		System.out.println("Directory is deleted.");
	}
	
	@Test
	public void testListOfFiles() throws Exception
	{
		Class fileStorageClass = Configuration.getClass("fileStorage", ServerStorage.class);
		Properties bundle = Configuration.getResourceBundle();
		
		Constructor con = fileStorageClass.getConstructor(Properties.class);
		FileStorage storage = (FileStorage) con.newInstance(bundle);
		
		String directoryPath = storage.createDirectory("User_1_200202");
		
		URL url = Thread.currentThread().getContextClassLoader().getResource("states.properties");
		String path = url.getFile().replace("%20", " ");
		
		File file = new File(path);
		String filepath = storage.copy("User_1_200202", "states.properties", file);
		
		url = Thread.currentThread().getContextClassLoader().getResource("countries.properties");
		path = url.getFile().replace("%20", " ");
		
		File countryfile = new File(path);
		
		String filepath2 = storage.copy("User_1_200202", "countries.properties", countryfile);
	
		List<File> files = storage.getAllFilesForDirectory(directoryPath);
		
		Assert.assertEquals(files.size(), 2);
		
		System.out.println();
		
		for(int i = 0; i < files.size(); i++)
			System.out.println(files.get(i).getPath());
		
		storage.deleteDirectory(directoryPath);
		
		System.out.println();
	}
	
	@Test
	public void testListAllFiles() throws Exception
	{
		Class fileStorageClass = Configuration.getClass("fileStorage", ServerStorage.class);
		Properties bundle = Configuration.getResourceBundle();
		
		Constructor con = fileStorageClass.getConstructor(Properties.class);
		FileStorage storage = (FileStorage) con.newInstance(bundle);
		
		String directoryPath = storage.createDirectory("User_1_200202");
		
		URL url = Thread.currentThread().getContextClassLoader().getResource("states.properties");
		String path = url.getFile().replace("%20", " ");
		
		File file = new File(path);
		String filepath = storage.copy("User_1_200202", "states.properties", file);
		
		url = Thread.currentThread().getContextClassLoader().getResource("countries.properties");
		path = url.getFile().replace("%20", " ");
		
		File countryfile = new File(path);
		
		String filepath2 = storage.copy("User_1_200202", "countries.properties", countryfile);
	
		String directoryPath2 = storage.createDirectory("User_1_200203");
		
		storage.copy("User_1_200203", "states.properties", file);
		storage.copy("User_1_200203", "countries.properties", countryfile);
		
		List<File> files = storage.getAllFiles();
		
		Assert.assertEquals(files.size(), 4);
		
		System.out.println();
		
		for(int i = 0; i < files.size(); i++)
			System.out.println(files.get(i).getPath());
		
		storage.deleteDirectory(directoryPath);
		storage.deleteDirectory(directoryPath2);
		
		System.out.println();
	}
	
	@Test
	public void testMemoryUsage() throws Exception
	{
		Class fileStorageClass = Configuration.getClass("fileStorage", ServerStorage.class);
		Properties bundle = Configuration.getResourceBundle();
		
		Constructor con = fileStorageClass.getConstructor(Properties.class);
		FileStorage storage = (FileStorage) con.newInstance(bundle);
		
		String directoryPath = storage.createDirectory("User_1_200202");
		
		URL url = Thread.currentThread().getContextClassLoader().getResource("states.properties");
		String path = url.getFile().replace("%20", " ");
		
		File file = new File(path);
		String filepath = storage.copy("User_1_200202", "states.properties", file);
		
		url = Thread.currentThread().getContextClassLoader().getResource("countries.properties");
		path = url.getFile().replace("%20", " ");
		
		File countryfile = new File(path);
		
		String filepath2 = storage.copy("User_1_200202", "countries.properties", countryfile);
	
		String directoryPath2 = storage.createDirectory("User_1_200203");
		
		storage.copy("User_1_200203", "states.properties", file);
		storage.copy("User_1_200203", "countries.properties", countryfile);
		
		Long bytes = storage.getMemoryUsage();
		
		Assert.assertNotSame(bytes, 0);
		
		System.out.println("Memory Usage "+bytes);
		
        storage.deleteDirectory(directoryPath);
	    storage.deleteDirectory(directoryPath2);
	}
	
	@Test
	public void testRetrieveFile() throws Exception
	{
		Class fileStorageClass = Configuration.getClass("fileStorage", ServerStorage.class);
		Properties bundle = Configuration.getResourceBundle();
		
		Constructor con = fileStorageClass.getConstructor(Properties.class);
		FileStorage storage = (FileStorage) con.newInstance(bundle);
		
		String directoryPath = storage.createDirectory("User_1_200202");
		
		URL url = Thread.currentThread().getContextClassLoader().getResource("states.properties");
		String path = url.getFile().replace("%20", " ");
		
		File file = new File(path);
		String filepath = storage.copy("User_1_200202", "states.properties", file);
		
		File retrievedFile = storage.retrieve(filepath);
		
		Assert.assertEquals(filepath, retrievedFile.getPath());
		
        storage.deleteDirectory(directoryPath);
	}
	
	@Test
	public void testDeleteFile() throws Exception
	{
		Class fileStorageClass = Configuration.getClass("fileStorage", ServerStorage.class);
		Properties bundle = Configuration.getResourceBundle();
		
		Constructor con = fileStorageClass.getConstructor(Properties.class);
		FileStorage storage = (FileStorage) con.newInstance(bundle);
		
		String directoryPath = storage.createDirectory("User_1_200202");
		
		URL url = Thread.currentThread().getContextClassLoader().getResource("states.properties");
		String path = url.getFile().replace("%20", " ");
		
		File file = new File(path);
		String filepath = storage.copy("User_1_200202", "states.properties", file);
		
		storage.deleteFile(filepath);
		
		List<File> files =  storage.getAllFilesForDirectory(directoryPath);
		
		Assert.assertEquals(files.size(), 0);
		
		storage.deleteDirectory(directoryPath);
	}
	
	@Test
	public void testRenameDirectory() throws Exception
	{
		Class fileStorageClass = Configuration.getClass("fileStorage", ServerStorage.class);
		Properties bundle = Configuration.getResourceBundle();
		
		Constructor con = fileStorageClass.getConstructor(Properties.class);
		FileStorage storage = (FileStorage) con.newInstance(bundle);
		
		String directoryPath = storage.createDirectory("User_1_200202");
		
		String directoryPath2 = storage.renameDirectory(directoryPath, "User_1_200203");
		File directory = new File(directoryPath2);
		
		Assert.assertEquals(directory.exists(), true);
		
		storage.deleteDirectory(directoryPath2);
	}
	
	@Test
	public void testUpdateFile() throws Exception
	{
		Class fileStorageClass = Configuration.getClass("fileStorage", ServerStorage.class);
		Properties bundle = Configuration.getResourceBundle();
		
		Constructor con = fileStorageClass.getConstructor(Properties.class);
		FileStorage storage = (FileStorage) con.newInstance(bundle);
		
		String directoryPath = storage.createDirectory("User_1_200202");
		
		URL url = Thread.currentThread().getContextClassLoader().getResource("states.properties");
		String path = url.getFile().replace("%20", " ");
		
		File file = new File(path);
		String filepath = storage.copy("User_1_200202", "states.properties", file);
		
		url = Thread.currentThread().getContextClassLoader().getResource("hibernate.cfg.xml");
		String countrypath = url.getFile().replace("%20", " ");
		
		File countryfile = new File(countrypath);
		
		String finalPath = storage.update(filepath, countryfile);
	
		file = new File(finalPath);
		
		System.out.println("Final Path "+finalPath);
		
		storage.deleteDirectory(directoryPath);
	}
	
	@Test
	public void testZipUp() throws Exception
	{
		Class fileStorageClass = Configuration.getClass("fileStorage", ServerStorage.class);
		Properties bundle = Configuration.getResourceBundle();
		
		Constructor con = fileStorageClass.getConstructor(Properties.class);
		FileStorage storage = (FileStorage) con.newInstance(bundle);
		
		String directoryPath = storage.createDirectory("User_1_200202");
		String directoryPath2 = storage.createDirectory("User_1_200203");
		
		URL url = Thread.currentThread().getContextClassLoader().getResource("states.properties");
		String path = url.getFile().replace("%20", " ");
		
		File file = new File(path);
		String filepath = storage.copy("User_1_200203", "states.properties", file);
		
		url = Thread.currentThread().getContextClassLoader().getResource("hibernate.cfg.xml");
		String countrypath = url.getFile().replace("%20", " ");
		
		filepath = storage.upload("User_1_200202", new File(countrypath));
		
		File archive = storage.zipUpAllFiles("storageFolder.zip");
		
		System.out.println("Archive Path "+archive.getAbsolutePath()+" exists "+archive.exists());
	}
	
	@Test
	public void testZipDirectory() throws Exception
	{
		Class fileStorageClass = Configuration.getClass("fileStorage", ServerStorage.class);
		Properties bundle = Configuration.getResourceBundle();
		
		Constructor con = fileStorageClass.getConstructor(Properties.class);
		FileStorage storage = (FileStorage) con.newInstance(bundle);
		
		String directoryPath = storage.createDirectory("User_1_200202");
		
		URL url = Thread.currentThread().getContextClassLoader().getResource("states.properties");
		String path = url.getFile().replace("%20", " ");
		
		File file = new File(path);
		String filepath = storage.copy("User_1_200202", "states.properties", file);
		
		File archive = storage.zipUpDirectory(directoryPath, "archive.zip");
		System.out.println("Archive "+archive.getAbsolutePath()+" exists "+archive.exists());
	}
	
	@Test
	public void testFileNotFoundExceptions()
	{
		
	}
}
