package org.church.management.domain;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.beanutils.PropertyUtils;
import org.church.management.domain.crud.DomainOperations;
import org.church.management.domain.exceptions.DAOConstraintViolationException;
import org.church.management.domain.exceptions.DAOException;
import org.church.management.domain.exceptions.DAONoObjectFoundException;
import org.church.management.domain.exceptions.DAOStaleStateException;
import org.church.management.domain.manager.ApplicationFileManager;
import org.church.management.domain.standard.fields.StandardFields;
import org.church.management.record.locking.exception.LockException;

/**
 * 
 * @author Trae
 * 
 * This class will hold information for files that 
 * linked to entities.
 */

@Entity
@Table(name="files")
public class ApplicationFile extends StandardFields implements DomainOperations<ApplicationFile>
{

	private static final long serialVersionUID = 1L;

	//mine type
	@Column(name="mine_type", length=40)
	private String mineType;

	//table
	@Column(name="entity", length=50)
	private String entity;
	
	//the id of the object
	@Column(name="object_id", length=20)
	private String objectId;
	
	//directoryName
	@ManyToOne(fetch=FetchType.EAGER)
	private Directory directory;
	
	//full path
	@Column(name="file_path", length=500)
	private String filePath;
	
	///description
	@Column(name="description", length=800)
	private String description;
	
	@Column(name="original_file_name", length=50)
	private String originalFileName;
	
	@Column(name="new_file_name", length=50)
	private String newFileName;
	
	@Transient
	private ApplicationFileManager manager;
	
	public ApplicationFile()
	{
		super(ApplicationFile.class);
		manager = new ApplicationFileManager();
	}
	
	@Override
	public void lock(String sessionId, String username) throws LockException 
	{
		manager.lock(this, sessionId, username);	
	}

	@Override
	public void unlock() 
	{
		manager.unlock(this);
	}
	
	public String getMineType() {
		return mineType;
	}

	public void setMineType(String mineType) {
		this.mineType = mineType;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String table) {
		this.entity = table;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public Directory getDirectory() {
		return directory;
	}

	public void setDirectory(Directory directory) {
		this.directory = directory;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
		this.setName(originalFileName);
	}

	public String getNewFileName() {
		return newFileName;
	}

	public void setNewFileName(String newFileName) {
		this.newFileName = newFileName;
	}

	@Override
	public int compareTo(ApplicationFile o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void save() throws DAOException, DAOConstraintViolationException, DAONoObjectFoundException 
	{
		manager.save(this);	
	}

	@Override
	public void delete() throws DAOException, DAOConstraintViolationException, DAONoObjectFoundException, DAOStaleStateException, LockException 
	{
		manager.delete(this);	
	}

	@Override
	public void update() throws DAOException, DAOConstraintViolationException, DAONoObjectFoundException, DAOStaleStateException 
	{
		manager.update(this);
	}

	@Override
	public boolean exist() throws DAOException 
	{
		return manager.exists(getId());
	}

	@Override
	public Integer rowCount() throws DAOException 
	{
		return manager.rowCount();
	}

	@Override
	public void retrieve() throws DAOException, DAONoObjectFoundException 
	{
		ApplicationFile file = manager.getObject(getId());
		copy(file, this);
	}

	@Override
	public List<ApplicationFile> findAll() throws DAOException 
	{
		return manager.getAll();
	}

	@Override
	public ApplicationFile getFirstRecord() throws DAOException 
	{
		return manager.getFirstRecord();
	}
	
	public List<ApplicationFile> getFilesByReference(org.church.management.interfaces.entity.Entity entity) throws DAOException
	{
		String id = entity.getId()+"";
		String name = entity.getClass().getSimpleName();
		return manager.getFilesByReference(name, id);
	}

	@Override
	public void copy(ApplicationFile source, ApplicationFile target) 
	{
		try
		{
			PropertyUtils.copyProperties(target, source);
		}
		catch(Exception e)
		{
			
		}
	}
	
	public ApplicationFile clone()
	{
		ApplicationFile file = new ApplicationFile();
		copy(this, file);
		return file;
	}

}
