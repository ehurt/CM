package org.church.management.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name="states")
public class State implements org.church.management.interfaces.entity.Entity
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="NAME", length=50)
	private String name;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Country country;

	public State()
	{
		id = null;
		this.name = "";
		country = null;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int hashCode()
	{
		return new HashCodeBuilder().append(name).append(country).toHashCode();
	}
	
	public State clone()
	{
		State state = new State();
		state.setName(name);
		state.setCountry(country);
		
		return state;
	}
	
	public boolean equals(Object obj)
	{
		if(obj == null)
		{
			return false;
		}
		
		else if(obj instanceof State)
		{
			State state = (State) obj;
			
			if(state == this)
			{
				return true;
			}
			
			if(name.equals(state.getName()) && country.equals(state.getCountry()))
			{
				return true;
			}		
		}
		
		return false;
	}

	@Override
	public String getEntityType() 
	{
		return State.class.getName();
	}

	@Override
	public void setEntityType(String entityType)
	{	
	}
}
