package org.church.management.domain.dao;

import java.util.List;

import org.church.management.domain.Attachment;
import org.church.management.domain.generic.dao.GenericDao;

public interface ApplicationFileDao extends GenericDao<Attachment, Integer> 
{
	public List<Attachment> getFilesByReference(String table, String id);
}
