package org.church.management.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="preferences")
public class Preference implements org.church.management.interfaces.entity.Entity
{
	private int id;
	private String name;
	private String defaultValue;
	
	public Preference()
	{
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDefaultValue() {
		return defaultValue;
	}
	
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	@Override
	public int getEntityTypeVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setEntityTypeVersion(int version) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getEntityType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setEntityType(String entityType) {
		// TODO Auto-generated method stub
		
	}
}
