package org.church.management.interfaces.entity;

import java.io.Serializable;

public interface Entity extends Serializable
{
	public Integer getId();
	public void setId(Integer id);
	
	public String getEntityType();
	public void setEntityType(String entityType);
}
