package org.church.management.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name="allergies")
public class Allergy implements org.church.management.interfaces.entity.Entity<Integer>
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name="name", length=50)
	private String name;
	
	@Column(name="name_lc", length=50, unique=true)
	private String nameLc;

	@Column(name="read_only")
	private boolean readOnly;
	
	public Allergy()
	{
		this.id = null;
		this.name = "";
		this.setReadOnly(false);
	}
	
	@Override
	public Integer getId(){
		return id;
	}

	@Override
	public void setId(Integer id){
		this.id = id;	
	}

	@Override
	public String getEntityType() 
	{
		return Allergy.class.getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameLc() {
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

	public boolean equals(Object object)
	{
		if(object instanceof Allergy)
		{
			Allergy that = (Allergy) object;
			
			if(that == this)
			{
				return true;
			}
			
			return new EqualsBuilder().append(that.name.toLowerCase(), this.name.toLowerCase()).isEquals();
		}
		
		return false;
	}
	
	public int hashCode()
	{
		return new HashCodeBuilder().append(name).toHashCode();
	}
}
