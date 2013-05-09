package org.church.management.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.beanutils.PropertyUtils;
import org.church.management.domain.exceptions.DAOConstraintViolationException;
import org.church.management.domain.exceptions.DAOException;
import org.church.management.domain.exceptions.DAONoObjectFoundException;
import org.church.management.domain.exceptions.DAOStaleStateException;
import org.church.management.domain.manager.ValidHostAddressManager;
import org.church.management.record.locking.exception.LockException;
import org.church.management.utils.UrlUtils;

/**
 * 
 * @author Trae
 *
 * This class will hold information for a valid ip addresses
 * that a user or remote application can use.
 */

@javax.persistence.Entity
@Table(name="valid_host_addresses")
public class ValidHostAddress implements org.church.management.interfaces.entity.Entity
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id = 0;
	
	@Column(name="valid_ip_pattern", length=20)
	private String pattern;
	
	@Column(name="entity", length=50)
	private String entity;
	
	@Column(name="entity_id", length=20)
	private String entityId;
	
	@Transient
	private ValidHostAddressManager manager = null;
	
	public ValidHostAddress()
	{
		manager = new ValidHostAddressManager();
	}
	
	public Integer getId() 
	{
		return id;
	}

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public String getPattern() 
	{
		return pattern;
	}

	public void setPattern(String pattern) 
	{
		this.pattern = pattern;
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
	
	public String getEntity()
	{
		return entity;
	}
	
	public void setEntity(String entity)
	{
		this.entity = entity;
	}
	
	public String getEntityId()
	{
		return entityId;
	}
	
	public void setEntityId(String id)
	{
		this.entityId = id;
	}
	
	public List<ValidHostAddress> getAll() throws DAOException
	{
		return manager.getAll();
	}
	
	public void getObject() throws DAOException, DAONoObjectFoundException
	{
		ValidHostAddress address = manager.getObject(getId());
		copy(address, this);
	}

	public void copy(ValidHostAddress source, ValidHostAddress target) 
	{
		try
		{
			PropertyUtils.copyProperties(target, source);
		}
		catch(Exception e){}
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
	
	public List<ValidHostAddress> getAllValidHostAddressByReference(String entity, String id) throws DAOException
	{
		return manager.getAllValidHostAddressByReference(entity, id);
	}
	
	public boolean validateIPAddress(String host)
	{
		return UrlUtils.verifyIPAddress(pattern, host);
	}
}
