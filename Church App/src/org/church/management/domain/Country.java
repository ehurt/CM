package org.church.management.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name="countries")
public class Country implements org.church.management.interfaces.entity.Entity<Integer>
{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="name", length=50, unique=true)
	private String name;
	
	@Column(name="abbrevation", length=6, unique=true)
	private String abbrevation;
	
	@Column(name="has_states")
	private boolean hasStates;

	public Country()
	{
		this.id = 0;
		name = "";
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
	
	public String getAbbrevation()
	{
		return this.abbrevation;
	}
	
	public void setAbbrevation(String abbrevation)
	{
		this.abbrevation = abbrevation;
	}
	
	public boolean isHasStates() {
		return hasStates;
	}

	public void setHasStates(boolean hasStates) {
		this.hasStates = hasStates;
	}

	public int hashCode()
	{
		return new HashCodeBuilder().append(name).append(abbrevation).toHashCode();
	}
	
	public Country clone()
	{
		Country country = new Country();
		country.setAbbrevation(abbrevation);
		country.setName(name);
		
		return country;
	}
	
	public boolean equals(Object obj)
	{
		if(obj == null)
		{
			return false;
		}
		
		else if(obj instanceof Country)
		{
			Country country = (Country) obj;
			
			if(country == this)
			{
				return true;
			}
			
			if(country.getName().equals(name) && country.getAbbrevation().equals(abbrevation))
			{
				return true;
			}
		}
		
		return false;
	}
}
