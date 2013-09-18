package org.church.management.domain.keys;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.church.management.domain.Allergy;
import org.church.management.domain.Attender;

@Embeddable
public class AttenderAllergyID implements Serializable
{
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="attender_id")
	private Attender attender;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="allergy_id")
	private Allergy allergy;

	public AttenderAllergyID()
	{
	}

	public Allergy getAllergy() {
		return allergy;
	}

	public void setAllergy(Allergy allergy) {
		this.allergy = allergy;
	}

	public Attender getAttender() {
		return attender;
	}

	public void setAttender(Attender attender) {
		this.attender = attender;
	}
	
	public boolean equals(Object object)
	{
		if(object instanceof AttenderAllergyID)
		{
			AttenderAllergyID that = (AttenderAllergyID) object;
			
			if(that == this)
			{
				return true;
			}
			
			return new EqualsBuilder().append(that.allergy, this.allergy).append(that.attender, this.attender).isEquals();
		}
		
		return false;
	}
	
	public int hashCode()
	{
		return new HashCodeBuilder().append(attender).append(allergy).toHashCode();
	}
}
