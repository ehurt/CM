package org.church.management.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="preferences")
public class Preference implements org.church.management.interfaces.entity.Entity
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="name", unique=true, length=40)
	private String name;
	
	@Column(name="default_value", length=100)
	private String defaultValue;
	
	public Preference()
	{
		
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
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

	public int getEntityTypeVersion() 
	{
		return 0;
	}

	public void setEntityTypeVersion(int version) 
	{	
	}

	public String getEntityType() 
	{
		return "";
	}

	public void setEntityType(String entityType) 
	{	
	}
}
