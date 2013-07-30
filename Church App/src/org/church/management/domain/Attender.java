package org.church.management.domain;

import java.util.Date;

import org.church.management.domain.standard.fields.StandardFields;

@SuppressWarnings("rawtypes")
public class Attender extends StandardFields
{
	private static final long serialVersionUID = 1L;

	private Date attendingSince;
	private String referredBy;
	
	private String photo;
	private Date photoUploadedOn;
	
	//(Mr., Miss, Mrs., or Dr.)
	protected String prefix;
	protected String firstname;
	protected String middlename;
	protected String lastname;
	
	protected Date birthday;
	
	protected Address address;
	
	protected String phone;
	protected String email;
	
	protected boolean isMale;
	protected boolean isFemale;
	
	protected Date dayOfDeath;
	protected boolean deceased;
	
	//(married, single, or windowed)
	protected String marriedStatus;
	
	protected boolean isActive;
	
	public Attender()
	{
		super(Attender.class);
		photo = "";
		prefix = "";
		firstname = "";
		middlename = "";
		lastname = "";
		address = null;
		phone = "";
		email = "";
		isMale = false;
		isFemale = false;
	}
	
	public Attender(Class c)
	{
		super(c);
		photo = "";
		prefix = "";
		firstname = "";
		middlename = "";
		lastname = "";
		address = null;
		phone = "";
		email = "";
		isMale = false;
		isFemale = false;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isMale() {
		return isMale;
	}

	public void setMale(boolean isMale) {
		this.isMale = isMale;
	}

	public boolean isFemale() {
		return isFemale;
	}

	public void setFemale(boolean isFemale) {
		this.isFemale = isFemale;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public Date getPhotoUploadedOn() {
		return photoUploadedOn;
	}

	public void setPhotoUploadedOn(Date photoUploadedOn) {
		this.photoUploadedOn = photoUploadedOn;
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

	public Date getAttendingSince() {
		return attendingSince;
	}

	public void setAttendingSince(Date attendingSince) {
		this.attendingSince = attendingSince;
	}

	public String getReferredBy() {
		return referredBy;
	}

	public void setReferredBy(String referredBy) {
		this.referredBy = referredBy;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
}
