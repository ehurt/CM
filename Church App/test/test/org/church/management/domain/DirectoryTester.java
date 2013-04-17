package test.org.church.management.domain;

import java.util.List;

import org.church.management.domain.ApplicationFile;
import org.church.management.domain.Directory;
import org.church.management.domain.exceptions.DAOConstraintViolationException;
import org.church.management.domain.exceptions.DAOException;
import org.church.management.domain.exceptions.DAONoObjectFoundException;
import org.church.management.domain.exceptions.DAOStaleStateException;
import org.church.management.record.locking.exception.LockException;
import org.junit.Test;

public class DirectoryTester 
{
	
	@Test
	public void saveDirectory() throws DAOException, DAOConstraintViolationException, DAONoObjectFoundException, DAOStaleStateException, LockException
	{
		Directory directory = new Directory();
		directory.setName("Testing");
		directory.setPublicDirectory(false);
		directory.setPath("C:/Testing");
		
		directory.save();
		directory.delete();
	}
	
	@Test
	public void updateDirectory() throws DAOException, DAOConstraintViolationException, DAONoObjectFoundException, DAOStaleStateException, LockException
	{
		Directory directory = new Directory();
		directory.setName("Testing");
		directory.setPublicDirectory(false);
		directory.setPath("C:/Testing");
		
		directory.save();
		
		directory.setName("String");
		directory.update();
		directory.delete();
	}
	
	@Test 
	public void getFiles() throws DAOException, DAOConstraintViolationException, DAONoObjectFoundException, DAOStaleStateException, LockException
	{
		Directory directory = new Directory();
		directory.setName("Testing");
		directory.setPublicDirectory(false);
		directory.setPath("C:/Testing");
		
		directory.save();
		
		ApplicationFile file = new ApplicationFile();
		file.setName("Testing.txt");
		file.setDirectory(directory);
		
		file.save();
		
		file = new ApplicationFile();
		file.setName("Testing2.txt");
		file.setDirectory(directory);
		
		file.save();
		
		Directory directory2 = new Directory();
		directory2.setName("Testing1");
		directory2.setPublicDirectory(false);
		directory2.setPath("C:/Testing1");
		
		directory2.save();
		
		file = new ApplicationFile();
		file.setName("notinlist.txt");
		file.setDirectory(directory2);
		
		file.save();
		
		List<ApplicationFile> files = directory.getFilesFromDirectory();
		
		for(ApplicationFile dfile : files)
		{
			System.out.println(dfile.getName());
		}
		
		directory.delete();
		directory2.delete();
	}
	
}
