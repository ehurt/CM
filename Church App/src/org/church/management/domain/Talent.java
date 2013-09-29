package org.church.management.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name="talents")
public class Talent implements org.church.management.interfaces.entity.Entity<Integer>
{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name="name", length=50)
	private String name;
	
	//make sure no duplicates get in
	@Column(name="name_lc", length=50, unique=true)
	private String nameLc;
	
	//this field is for prepopulate ones
	@Column(name="read_only")
	private boolean readOnly;
	
	public Talent()
	{
		this.name = "";
		this.readOnly = false;
		this.nameLc = "";
	}
	
	@Override
	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean equals(Object object)
	{
		if(object instanceof Talent)
		{
			Talent that = (Talent) object;
			
			if(that == this)
			{
				return true;
			}
			
			return new EqualsBuilder().append(that.getName().toLowerCase(), this.getName().toLowerCase()).isEquals();
		}
		
		return false;
	}
	
	public int hashCode()
	{
		return new HashCodeBuilder().append(name).toHashCode();
	}

	public String getNameLc()
	{
		nameLc = name.toLowerCase();
		return nameLc;
	}

	public void setNameLc(String nameLc) {
		this.nameLc = nameLc;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}
	
}
