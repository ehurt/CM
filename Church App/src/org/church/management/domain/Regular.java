package org.church.management.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@DiscriminatorValue(value="RE")
public class Regular extends Attender
{
	private static final long serialVersionUID = 1L;
	
	@Column(name="disfellowshiped")
	private boolean isDisfellowshiped = false;
	
	@Column(name="employeer", length=50)
	private String employer = "";
	
	@Column(name="occupation", length=50)
	private String occupation = "";
	
	@Column(name="alternative_phone", length=30)
	private String altPhone = "";
	
	public Regular()
	{
		super(Regular.class);
	}

	public boolean isDisfellowshiped() {
		return isDisfellowshiped;
	}

	public void setDisfellowshiped(boolean isDisfellowshiped) {
		this.isDisfellowshiped = isDisfellowshiped;
	}

	public String getEmployer() {
		return employer;
	}

	public void setEmployer(String employer) {
		this.employer = employer;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getAltPhone() {
		return altPhone;
	}

	public void setAltPhone(String altPhone) {
		this.altPhone = altPhone;
	}
}
