package org.church.management.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author Trae
 *
 * This class holds all the information about the member. It inherits the information
 * from the attender.
 */

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value="ME")
public class Member extends Attender
{
	private static final long serialVersionUID = 1L;
	
	@Column(name="member_since")
	@Temporal(TemporalType.DATE)
	private Date memberSince;
	
	@Column(name="disfellowshiped")
	private boolean isDisfellowshiped;
	
	@Column(name="employeer", length=50)
	private String employer = "";
	
	@Column(name="occupation", length=50)
	private String occupation = "";
	
	@Column(name="alternative_phone", length=30)
	private String altPhone = "";
	
	public Member()
	{
		super(Member.class);
		this.isDisfellowshiped = false;
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
