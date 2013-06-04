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
@Table(name="cities")
public class City
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="name", length=30)
	private String name;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private State state;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Country country;
	
	public City()
	{
		id = 0;
		name = "";
	}
	
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public int hashCode()
	{
		HashCodeBuilder builder = new HashCodeBuilder();
		
		builder.append(name);
		
		if(state != null)
		{
			builder.append(state);
		}
		
		else if(country != null)
		{
			builder.append(country);
		}
		
		return builder.toHashCode();
	}
	
	public boolean equals(Object obj)
	{
		if(obj == null)
		{
			return false;
		}
		
		else
		{
			if(obj instanceof City)
			{
				City city = (City) obj;
				
				if(city == this)
				{
					return true;
				}
				
				if(state != null)
				{
					if(state.equals(city.getState()) && name.equals(city.getName()))
					{
						return true;
					}
				}
				
				if(country != null)
				{
					if(country.equals(city.getCountry()) && name.equals(city.getName()))
					{
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	public City clone()
	{
		City city = new City();
		city.setName(name);
		city.setState(state);
		city.setCountry(country);
		
		return city;
	}
	
}
