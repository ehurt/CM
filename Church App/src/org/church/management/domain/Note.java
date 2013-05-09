package org.church.management.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="notes")
public class Note implements org.church.management.interfaces.entity.Entity
{
	private Integer id;
	
	
	
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

	public int getEntityTypeVersion()
	{
		return 0;
	}

	public void setEntityTypeVersion(int version) 
	{	
	}

	public String getEntityType()
	{
		return Note.class.getSimpleName();
	}

	public void setEntityType(String entityType) 
	{	
	}

}
