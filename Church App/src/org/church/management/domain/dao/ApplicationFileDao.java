package org.church.management.domain.dao;

import java.util.List;

import org.church.management.domain.ApplicationFile;
import org.church.management.domain.generic.dao.GenericDao;

public interface ApplicationFileDao extends GenericDao<ApplicationFile, Integer> 
{
	public List<ApplicationFile> getFilesByReference(String table, String id);
}
