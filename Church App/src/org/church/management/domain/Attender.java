package org.church.management.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * @author ehurt
 * 
 * This class represents the church member and all the information
 * about them.
 */
@Entity
@Table(name="attenders")
public class Attender implements org.church.management.interfaces.entity.Entity<Integer>
{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//image of attender and information to help determine when the image was upload.
	private String image;
	private Date imageUploadOn;
	
	//attend name
	private String firstName = "";
	private String middleName = "";
	private String lastName = "";
	
	//birthday information
	private Date birthday = null;
	
	//sex of the person
	private boolean isMale = false;
	private boolean isFemale = false;
	
	//address
	private Address address;
	private String bldgAptUnit = "";
	
	//contact information
	private String phone = "";
	private String altPhone = "";
	private String cellPhone ="";
	private String email = "";
	
	//married information
	private Date anniversary = null;
	private String marriedBy = "";
	private String cityMarriedIn = "";
	private Attender spouse = null;
	
	//deceased 
	private Date dayOfDeath;
	private boolean deceased;
	
	private boolean isBaptized = false;
	private boolean isDisfellowshiped = false;
	private boolean isActive = false;
	
	//attender statuses
	private boolean isMember = false;
	private boolean isRegular = false;
	
	private String employer = "";
	private String occupation = "";
	private Date attendingSince = null;
	private String referredBy = "";
	private Date memberSince = null;
	private String specialGifting = "";
	private String notes = "";
	private boolean isPastor = false;
	private boolean isAssistedPastor = false;
	private boolean isLeader = false;
	private boolean isDeacon = false;
	private boolean isSundaySchoolTeacher = false;
	
	public Attender()
	{
		this.id = null;
		this.firstName = "";
		this.lastName = "";
		this.middleName = "";
		this.deceased = false;
		this.dayOfDeath = null;
		this.isActive = false;
		this.isBaptized = false;
		this.isDisfellowshiped = false;
		this.isMember = false;
		this.isRegular = false;
	}

	public String getFirstName() 
	{
		return firstName;
	}

	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}

	public String getMiddleName() 
	{
		return middleName;
	}

	public void setMiddleName(String middleName) 
	{
		this.middleName = middleName;
	}

	public String getLastName() 
	{
		return lastName;
	}

	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}

	public boolean isMale() 
	{
		return isMale;
	}

	public void setMale(boolean isMale) 
	{
		this.isMale = isMale;
	}

	public boolean isFemale() 
	{
		return isFemale;
	}

	public void setFemale(boolean isFemale) 
	{
		this.isFemale = isFemale;
	}

	public Address getAddress() 
	{
		return address;
	}

	public void setAddress(Address address) 
	{
		this.address = address;
	}

	public String getBldgAptUnit() 
	{
		return bldgAptUnit;
	}

	public void setBldgAptUnit(String bldgAptUnit) 
	{
		this.bldgAptUnit = bldgAptUnit;
	}

	public String getPhone() 
	{
		return phone;
	}

	public void setPhone(String phoneNb) {
		this.phone = phoneNb;
	}

	public String getAltPhone() 
	{
		return altPhone;
	}

	public void setAltPhone(String altPhoneNb) 
	{
		this.altPhone = altPhoneNb;
	}
	
	public String getCellPhone()
	{
		return cellPhone;
	}
	
	public void setCellPhone(String phone)
	{
		this.cellPhone = phone;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public String getEmployer() 
	{
		return employer;
	}

	public void setEmployer(String employee) 
	{
		this.employer = employee;
	}

	public String getOccupation() 
	{
		return occupation;
	}

	public void setOccupation(String occupation) 
	{
		this.occupation = occupation;
	}

	public Date getAttendingSince() 
	{
		return attendingSince;
	}

	public void setAttendingSince(Date attendingSince) 
	{
		this.attendingSince = attendingSince;
	}

	public String getReferredBy() 
	{
		return referredBy;
	}

	public void setReferredBy(String referredBy) 
	{
		this.referredBy = referredBy;
	}

	public boolean isBaptized() 
	{
		return isBaptized;
	}

	public void setBaptized(boolean isBaptized) 
	{
		this.isBaptized = isBaptized;
	}

	public boolean isDisfellowshiped() 
	{
		return isDisfellowshiped;
	}

	public void setDisfellowshiped(boolean isDisfellowshiped) 
	{
		this.isDisfellowshiped = isDisfellowshiped;
	}

	public Date getMemberSince() 
	{
		return memberSince;
	}

	public void setMemberSince(Date memberSince) 
	{
		this.memberSince = memberSince;
	}

	public String getSpecialGifting() 
	{
		return specialGifting;
	}

	public void setSpecialGifting(String specialGifting) 
	{
		this.specialGifting = specialGifting;
	}

	public String getNotes() 
	{
		return notes;
	}

	public void setNotes(String notes) 
	{
		this.notes = notes;
	}

	public Date getAnniversity() 
	{
		return anniversary;
	}

	public void setAnniversity(Date anniversity) 
	{
		this.anniversary = anniversity;
	}

	public Attender getSpourse() 
	{
		return spouse;
	}

	public void setSpourse(Attender spourse) 
	{
		this.spouse = spourse;
	}

	public String getMarriedBy() 
	{
		return marriedBy;
	}

	public void setMarriedBy(String marriedBy) 
	{
		this.marriedBy = marriedBy;
	}

	public String getCityMarriedIn() 
	{
		return cityMarriedIn;
	}

	public void setCityMarriedIn(String cityMarriedIn) 
	{
		this.cityMarriedIn = cityMarriedIn;
	}

	public Date getAnniversary() 
	{
		return anniversary;
	}

	public void setAnniversary(Date anniversary) 
	{
		this.anniversary = anniversary;
	}

	public Attender getSpouse() 
	{
		return spouse;
	}

	public void setSpouse(Attender spouse) 
	{
		this.spouse = spouse;
	}
	
	public String getImage()
	{
		return image;
	}
	
	public void setImage(String image)
	{
		this.image = image;
	}

	public Date getImageUploadOn() {
		return imageUploadOn;
	}

	public void setImageUploadOn(Date imageUploadOn) {
		this.imageUploadOn = imageUploadOn;
	}

	public boolean isActive() 
	{
		return isActive;
	}

	public void setActive(boolean isActive) 
	{
		this.isActive = isActive;
	}

	public boolean isPastor() 
	{
		return isPastor;
	}

	public void setPastor(boolean isPastor) 
	{
		this.isPastor = isPastor;
	}

	public boolean isAssistedPastor() 
	{
		return isAssistedPastor;
	}

	public void setAssistedPastor(boolean isAssistedPastor) 
	{
		this.isAssistedPastor = isAssistedPastor;
	}

	public boolean isLeader() 
	{
		return isLeader;
	}

	public void setLeader(boolean isLeader) 
	{
		this.isLeader = isLeader;
	}

	public boolean isDeacon() 
	{
		return isDeacon;
	}

	public void setDeacon(boolean isDeacon) 
	{
		this.isDeacon = isDeacon;
	}

	public boolean isMember() 
	{
		return isMember;
	}

	public void setMember(boolean isMember) 
	{
		this.isMember = isMember;
	}

	public boolean isRegular() 
	{
		return isRegular;
	}

	public void setRegular(boolean isRegular) 
	{
		this.isRegular = isRegular;
	}
	
	public boolean isSundaySchoolTeacher()
	{
		return isSundaySchoolTeacher;
	}
	
	public void setSundaySchoolTeacher(boolean teacher)
	{
		this.isSundaySchoolTeacher = teacher;
	}

	@Override
	public Integer getId() 
	{
		return id;
	}

	@Override
	public void setId(Integer id) 
	{
		this.id = id;
	}

	@Override
	public String getEntityType() 
	{
		return Attender.class.getName();
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getDayOfDeath() {
		return dayOfDeath;
	}

	public void setDayOfDeath(Date dayOfDeath) {
		this.dayOfDeath = dayOfDeath;
	}

	public boolean isDeceased() {
		return deceased;
	}

	public void setDeceased(boolean deceased) {
		this.deceased = deceased;
	}
}
