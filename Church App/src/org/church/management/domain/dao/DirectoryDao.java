package org.church.management.domain.dao;

import java.util.List;

import org.church.management.domain.ApplicationFile;
import org.church.management.domain.Directory;
import org.church.management.domain.generic.dao.GenericDao;

public interface DirectoryDao extends GenericDao<Directory, Integer>
{	
	public List<ApplicationFile> getFilesFromDirectory(Directory directory);
}
