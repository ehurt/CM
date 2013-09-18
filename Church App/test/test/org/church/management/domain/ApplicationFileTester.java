package test.org.church.management.domain;

import java.util.List;

import junit.framework.Assert;

import org.church.management.domain.Attachment;
import org.church.management.domain.Directory;
import org.church.management.domain.User;
import org.church.management.domain.exceptions.DAOConstraintViolationException;
import org.church.management.domain.exceptions.DAOException;
import org.church.management.domain.exceptions.DAONoObjectFoundException;
import org.church.management.domain.exceptions.DAOStaleStateException;
import org.church.management.record.locking.exception.LockException;
import org.junit.Test;

public class ApplicationFileTester
{
	/*
	@Test
	public void testSave() throws DAOException, DAOConstraintViolationException, DAONoObjectFoundException, DAOStaleStateException, LockException
	{
		User user = new User();
		user.setId(2);
		
		Directory directory = new Directory();
		directory.setName("User_1_201301292");
		directory.setPublicDirectory(false);
		directory.setPath("C:\\testing");
		
		directory.save();
		
		Attachment file = new Attachment();
		file.setMineType("image/png");
		file.setEntity(User.class.getSimpleName());
		file.setObjectId("2");
		file.setFilePath("C:\\testing\\1.txt");
		file.setDescription("Testing file.");
		file.setDirectory(directory);
		file.setOriginalFileName("testing.txt");
		file.setNewFileName("1.txt");
		
		file.save();
		file.delete();
		directory.delete();
	}
	
	@Test
	public void testUpdate() throws DAOException, DAOConstraintViolationException, DAONoObjectFoundException, DAOStaleStateException, LockException
	{
		User user = new User();
		user.setId(2);
		
		Directory directory = new Directory();
		directory.setName("User_1_201301292");
		directory.setPublicDirectory(false);
		directory.setPath("C:\\testing");
		
		directory.save();
		
		Attachment file = new Attachment();
		file.setMineType("image/png");
		file.setEntity(User.class.getSimpleName());
		file.setObjectId("2");
		file.setFilePath("C:\\testing\\1.txt");
		file.setDescription("Testing file.");
		file.setDirectory(directory);
		file.setOriginalFileName("testing.txt");
		file.setNewFileName("1.txt");
		
		file.save();
		
		file.setDescription("Testing file!");
		file.setMineType("image/png");
		file.setEntity(User.class.getSimpleName());
		file.setObjectId("2");
		file.setFilePath("C:\\testing\\1.txt");
		file.setDirectory(directory);
		file.setOriginalFileName("test.txt");
		file.setNewFileName("1.txt");
		
		file.update();
		
		directory.delete();
	}
	
	@Test
	public void testGetAllFiles() throws DAOException, DAOConstraintViolationException, DAONoObjectFoundException, DAOStaleStateException, LockException
	{
		User user = new User();
		user.setId(2);
		
		Directory directory = new Directory();
		directory.setName("User_1_201301292");
		directory.setPublicDirectory(false);
		directory.setPath("C:\\testing");
		
		directory.save();
		
		Attachment file = new Attachment();
		file.setMineType("image/png");
		file.setEntity(User.class.getSimpleName());
		file.setObjectId("2");
		file.setFilePath("C:\\testing\\1.txt");
		file.setDescription("Testing file.");
		file.setDirectory(directory);
		file.setOriginalFileName("testing.txt");
		file.setNewFileName("1.txt");
		
		file.save();
		
		file = new Attachment();
		file.setDescription("Testing.");
		file.setMineType("image/jpg");
		file.setEntity(User.class.getSimpleName());
		file.setObjectId("2");
		file.setFilePath("C:\\testing\\1.jpg");
		file.setDirectory(directory);
		file.setOriginalFileName("test.jpg");
		file.setNewFileName("1.jpg");
		
		file.save();
		
		List<Attachment> files = file.getFilesByReference(user);
		
		Assert.assertEquals(files.size(), 2);
		
		for(Attachment afile : files)
			System.out.println(afile.getName());
		
		directory.delete();
	}*/
}
