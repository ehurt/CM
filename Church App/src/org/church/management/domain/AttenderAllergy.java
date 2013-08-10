package org.church.management.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.church.management.domain.keys.AttenderAllergyID;

@Entity
@Table(name="attender_allergies")
public class AttenderAllergy implements org.church.management.interfaces.entity.Entity<AttenderAllergyID>
{

	private static final long serialVersionUID = 1L;
	
	@Id
	private AttenderAllergyID id;

	@Column(name="notes", length=2000)
	private String notes;
	
	public AttenderAllergy()
	{
		id = new AttenderAllergyID();
		notes = "";
	}
	
	@Override
	public AttenderAllergyID getId(){
		return id;
	}

	@Override
	public void setId(AttenderAllergyID id){
		this.id = id;	
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String getEntityType() 
	{
		return AttenderAllergy.class.getName();
	}
	
	public boolean equals(Object object)
	{
		if(object instanceof AttenderAllergy)
		{
			AttenderAllergy that = (AttenderAllergy) object;
			
			if(that == this)
			{
				return true;
			}
			
			return new EqualsBuilder().append(that.id, this.id).isEquals();
		}
		
		return false;
	}
	
	public int hashCode()
	{
		return new HashCodeBuilder().append(id).toHashCode();
	}
}
