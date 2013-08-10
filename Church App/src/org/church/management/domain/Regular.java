package org.church.management.domain;

public class Regular extends Attender
{
	private static final long serialVersionUID = 1L;
	
	private boolean isDisfellowshiped = false;
	
	private String employer = "";
	private String occupation = "";
	
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
