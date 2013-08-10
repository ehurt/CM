package org.church.management.domain.keys;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.church.management.domain.Attender;
import org.church.management.domain.Talent;

@Embeddable
public class AttenderTalentID implements Serializable
{

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch=FetchType.EAGER)
	private Attender attender;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Talent talent;
	
	public Attender getAttender() 
	{
		return attender;
	}

	public void setAttender(Attender attender) {
		this.attender = attender;
	}

	public Talent getTalent() {
		return talent;
	}

	public void setTalent(Talent talent) {
		this.talent = talent;
	}
	
	public boolean equals(Object object)
	{
		if(object instanceof AttenderTalentID)
		{
			AttenderTalentID that = (AttenderTalentID) object;
			
			if(that == this)
			{
				return true;
			}
			
			return new EqualsBuilder().append(that.attender, this.attender).append(that.talent, this.talent).isEquals();
		}
		
		return false;
	}
	
	public int hashCode()
	{
		return new HashCodeBuilder().append(talent).append(attender).toHashCode();
	}
}
