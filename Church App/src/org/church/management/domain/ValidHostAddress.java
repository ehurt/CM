package org.church.management.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id = 0;
	
	@Column(name="valid_ip_pattern", length=20)
	private String pattern;
	
	@Column(name="entity", length=50)
	private String entity;
	
	@Column(name="object_id", length=30)
	private String objectId;
	
	public ValidHostAddress()
	{
		pattern = "";
		entity = "";
		objectId = "";
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
	
	public String getEntity()
	{
		return entity;
	}
	
	public void setEntity(String entity)
	{
		this.entity = entity;
	}
	
	public String getObjectId()
	{
		return objectId;
	}
	
	public void setObjectId(String id)
	{
		this.objectId = id;
	}
	
	public ValidHostAddress clone()
	{
		ValidHostAddress address = new ValidHostAddress();
		address.setEntity(entity);
		address.setObjectId(objectId);
		address.setPattern(pattern);
		
		return address;
	}
	
	public int hashCode()
	{
		return new HashCodeBuilder().append(pattern).append(entity).append(objectId).toHashCode();
	}
	
	public boolean equals(Object obj)
	{
		if(obj == null)
		{
			return false;
		}
		
		else if(obj instanceof ValidHostAddress)
		{
			ValidHostAddress address = (ValidHostAddress) obj;
			
			if(address == this)
			{
				return true;
			}
			
			return new EqualsBuilder().append(pattern, address.getPattern()).append(entity, address.getEntity()).append(objectId, address.getObjectId()).isEquals();
		}
		
		return false;
	}

	@Override
	public String getEntityType() 
	{
		return ValidHostAddress.class.getName();
	}

	@Override
	public void setEntityType(String entityType) 
	{		
	}
}
