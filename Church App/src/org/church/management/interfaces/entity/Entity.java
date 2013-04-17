package org.church.management.interfaces.entity;

public interface Entity 
{
	public int getId();
	public void setId(int id);
	
	public int getEntityTypeVersion();
	public void setEntityTypeVersion(int version);
	
	public String getEntityType();
	public void setEntityType(String entityType);
	
	//TODO Add the full class path here, so can access the class.
}
