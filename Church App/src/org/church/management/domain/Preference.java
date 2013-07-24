package org.church.management.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name="preferences")
public class Preference implements org.church.management.interfaces.entity.Entity<Integer>
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="name", unique=true, length=40)
	private String name;
	
	@Column(name="default_value", length=100)
	private String defaultValue;
	
	public Preference()
	{
		this.name = "";
		this.defaultValue = "";
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

	public int hashCode()
	{
		return new HashCodeBuilder().append(name).append(defaultValue).toHashCode();
	}
	
	public boolean equals(Object obj)
	{
		if(obj == null)
		{
			return false;
		}
		
		else if(obj instanceof Preference)
		{
			Preference preference = (Preference) obj;
			
			if(preference == this)
			{
				return true;
			}
			
			return new EqualsBuilder().append(name, preference.getName()).isEquals();
		}
		
		return false;
	}
	
	public Preference clone()
	{
		Preference preference = new Preference();
		preference.setName(name);
		
		return preference;
	}

	@Override
	public String getEntityType() 
	{
		return Preference.class.getName();
	}
}
