package org.church.management.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.church.management.domain.keys.AttenderTalentID;

@Entity
@Table(name="attender_talents")
public class AttenderTalent implements org.church.management.interfaces.entity.Entity<AttenderTalentID>
{

	private static final long serialVersionUID = 1L;

	@Id
	private AttenderTalentID id;
	
	private String notes;
	
	public AttenderTalent()
	{
		this.id = new AttenderTalentID();
		this.notes = "";
	}
	
	@Override
	public AttenderTalentID getId() 
	{
		return id;
	}

	@Override
	public void setId(AttenderTalentID id) 
	{
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
		return AttenderTalent.class.getName();
	}
	
	public boolean equals(Object object)
	{
		if(object instanceof AttenderTalent)
		{
			AttenderTalent that = (AttenderTalent) object;
			
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
		return new HashCodeBuilder().append(id.getAttender()).append(id.getTalent()).toHashCode();
	}
}
