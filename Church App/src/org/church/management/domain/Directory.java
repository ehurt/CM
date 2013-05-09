package org.church.management.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.church.management.domain.exceptions.DAOConstraintViolationException;
import org.church.management.domain.exceptions.DAOException;
import org.church.management.domain.exceptions.DAONoObjectFoundException;
import org.church.management.domain.exceptions.DAOStaleStateException;
import org.church.management.domain.manager.DirectoryManager;
import org.church.management.record.locking.exception.LockException;

@Entity
@Table(name="directories")
public class Directory implements org.church.management.interfaces.entity.Entity
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id = 0;
	
	@Column(name="name", length=50)
	private String name = "";
	
	@Column(name="path", length=400)
	private String path = "";
	
	@Column(name="is_public")
	private boolean publicDirectory;
	
	@Transient
	private DirectoryManager manager = null;

	public Directory()
	{
		manager = new DirectoryManager();
	}
	
	@Override
	public Integer getId() 
	{
		return id;
	}

	@Override
	public void setId(Integer id) 
	{
		this.id = id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}

	public String getPath()
	{
		return path;
	}
	
	public void setPath(String path)
	{
		this.path = path;
	}
	
	public boolean isPublicDirectory()
	{
		return publicDirectory;
	}
	
	public void setPublicDirectory(boolean publicDirectory)
	{
		this.publicDirectory = publicDirectory;
	}
	
	@Override
	public int getEntityTypeVersion() 
	{
		return 0;
	}

	@Override
	public void setEntityTypeVersion(int version) 
	{		
	}

	@Override
	public String getEntityType() 
	{
		return Directory.class.getSimpleName();
	}

	@Override
	public void setEntityType(String entityType)
	{	
	}

	public void save() throws DAOException, DAOConstraintViolationException, DAONoObjectFoundException
	{
		manager.save(this);
	}
	
	public void delete() throws DAOException, DAOConstraintViolationException, DAONoObjectFoundException, DAOStaleStateException, LockException
	{
		manager.delete(this);
	}
	
	public void update() throws DAOException, DAOConstraintViolationException, DAONoObjectFoundException, DAOStaleStateException
	{
		manager.update(this);
	}
	
	public List<ApplicationFile> getFilesFromDirectory() throws DAOException
	{
		return manager.getFilesFromDirectory(this);
	}
}
