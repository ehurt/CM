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
import org.church.management.domain.manager.LanguageManager;
import org.church.management.record.locking.exception.LockException;

/**
 * 
 * @author Trae
 *
 * This class will hold the information for a language.
 *
 */
@Entity
@Table(name="languages")
public class Language implements org.church.management.interfaces.entity.Entity
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="abbrevation", length=6)
	private String abbrevation;
	
	@Column(name="name", length=50)
	private String name;
	
	@Transient
	private LanguageManager manager;
	
	public Language()
	{
		manager = new LanguageManager();
	}
	
	public String getAbbrevation()
	{
		return abbrevation;
	}
	
	public void setAbbrevation(String abbrevation)
	{
		this.abbrevation = abbrevation;
	}
	
	public int getId() 
	{
		return id;
	}
	
	public void setId(int id) 
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
	
	public int getEntityTypeVersion() 
	{
		return 0;
	}

	public void setEntityTypeVersion(int version) 
	{
	}
	
	public String getEntityType()
	{
		return null;
	}
	
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
	
	public List<Language> getAll() throws DAOException
	{
		return manager.getAll();
	}
}
