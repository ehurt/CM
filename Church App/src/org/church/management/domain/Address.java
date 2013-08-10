package org.church.management.domain;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * 
 * @author Trae
 *
 * This class holds an address. 
 * It can be used by any object.
 */

@javax.persistence.Entity
@Table(name="addresses")
public class Address implements org.church.management.interfaces.entity.Entity<Integer>
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="address", length=100)
	private String address;
	
	@Column(name="apartment_number", length=20)
	private String apartmentNumber;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private City city;
	
	@ManyToOne(fetch=FetchType.EAGER, optional=true)
	private State state;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Country country;
	
	@Column(name="zipcode", length=12)
	private String zipcode;
	
	public Address()
	{
		id = null;
		address = "";
		city = null;
		state = null;
		country = null;
		zipcode = "";
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
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

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	@Override
	public String getEntityType() 
	{
		return Address.class.getName();
	}

	public String getApartmentNumber() {
		return apartmentNumber;
	}

	public void setApartmentNumber(String apartmentNumber) {
		this.apartmentNumber = apartmentNumber;
	}

	public int hashCode()
	{
		HashCodeBuilder builder = new HashCodeBuilder();
		
		builder.append(address);
		builder.append(zipcode);
		
		if(city != null)
		{
			builder.append(city);
		}

		if(state != null)
		{
			builder.append(state);
		}
		
		if(country != null)
		{
			builder.append(country);
		}
		
		return builder.toHashCode();
	}
	
	public Address clone()
	{
		Address address = new Address();
		
		address.setAddress(this.address);
		address.setCity(city);
		address.setCountry(country);
		address.setState(state);
		address.setZipcode(zipcode);
		return address;
	}
	
	public boolean equals(Object obj)
	{
		if(obj == null)
		{
			return false;
		}
		
		else if(obj instanceof Address)
		{
			Address address = (Address) obj;
			
			if(address == this)
			{
				return true;
			}
			
			if(state != null)
			{
				if(state.equals(address.getState()) && zipcode.equals(address.getZipcode()) && city.equals(address.getCity()) && this.address.equals(address.getAddress()))
				{
					return true;
				}
			}
			
			if(country != null)
			{
				if(country.equals(address.getCountry()) && zipcode.equals(address.getZipcode()) && city.equals(address.getCity()) && this.address.equals(address.getAddress()))
				{
					return true;
				}
			}
		}
		
		return false;
	}
}
