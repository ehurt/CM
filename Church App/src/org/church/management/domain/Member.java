package org.church.management.domain;

import java.util.Date;

/**
 * 
 * @author Trae
 *
 * This class holds all the information about the member. It inherits the information
 * from the attender.
 */

public class Member extends Attender
{
	private static final long serialVersionUID = 1L;
	
	private Date memberSince;
	
	private boolean isDisfellowshiped = false;
	
	private String employer = "";
	private String occupation = "";
	
	private String altPhone = "";
	
	public Member()
	{
		super(Member.class);
	}
	
	public String getEntityType()
	{
		return Member.class.getName();
	}

	public Date getMemberSince() {
		return memberSince;
	}

	public void setMemberSince(Date memberSince) {
		this.memberSince = memberSince;
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

	public boolean isDisfellowshiped() {
		return isDisfellowshiped;
	}

	public void setDisfellowshiped(boolean isDisfellowshiped) {
		this.isDisfellowshiped = isDisfellowshiped;
	}

	public String getAltPhone() {
		return altPhone;
	}

	public void setAltPhone(String altPhone) {
		this.altPhone = altPhone;
	}
}
