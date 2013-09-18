package org.church.management.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name="ethnicities")
public class Ethnicity implements org.church.management.interfaces.entity.Entity<Integer>
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name="name", length=30)
	private String name;
	
	@Column(name="name_lc", length=30)
	private String nameLc;
	
	public Ethnicity()
	{
		this.name = "";
	}
	
	public Integer getId() 
	{
		return id;
	}
	
	public void setId(Integer id) 
	{
		this.id = id;
	}

	public String getEntityType() 
	{
		return Ethnicity.class.getName();
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getNameLc()
	{
		nameLc = name.toLowerCase();
		return nameLc;
	}
	
	public void setNameLc(String nameLc)
	{
		this.nameLc = nameLc;
	}
	
	public boolean equals(Object object)
	{
		if(object instanceof Ethnicity)
		{
			Ethnicity that = (Ethnicity) object;
			
			if(that == this)
			{
				return true;
			}
			
			return new EqualsBuilder().append(that.getNameLc(), this.getNameLc()).isEquals();
		}
		
		return false;
	}

	public int hashCode()
	{
		return new HashCodeBuilder().append(name).append(getNameLc()).toHashCode();
	}
}
