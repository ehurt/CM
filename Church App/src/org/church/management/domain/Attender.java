package org.church.management.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.church.management.domain.keys.AttenderTalentID;
import org.church.management.domain.standard.fields.StandardFields;
import org.church.management.exceptions.DuplicateException;

@SuppressWarnings("rawtypes")
@Entity
@Table(name="attenders")
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
	
	protected List<AttenderTalent> talents;

	protected List<AttenderAllergy> allergies;
	
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
		talents = new ArrayList<AttenderTalent>();
		allergies = new ArrayList<AttenderAllergy>();
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
		talents = new ArrayList<AttenderTalent>();
		allergies = new ArrayList<AttenderAllergy>();
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

	public void setFirstname(String firstname) 
	{
		this.firstname = firstname;
		setName(firstname+" "+middlename+" "+lastname);
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
		setName(firstname+" "+middlename+" "+lastname);
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
		setName(firstname+" "+middlename+" "+lastname);
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
	
	public List<AttenderTalent> getTalents() {
		return talents;
	}

	public void setTalents(List<AttenderTalent> talents) {
		this.talents = talents;
	}
	
	public String getMarriedStatus() {
		return marriedStatus;
	}

	public void setMarriedStatus(String marriedStatus) {
		this.marriedStatus = marriedStatus;
	}

	public List<AttenderAllergy> getAllergies() {
		return allergies;
	}

	public void setAllergies(List<AttenderAllergy> allergies) {
		this.allergies = allergies;
	}
	
	public void addAllergy(Allergy allergy, String notes) throws DuplicateException
	{
		AttenderAllergy attenderAllergy = new AttenderAllergy();
		attenderAllergy.getId().setAllergy(allergy);
		attenderAllergy.getId().setAttender(this);
		attenderAllergy.setNotes(notes);
		
		if(allergies.contains(attenderAllergy) == false)
		{
			allergies.add(attenderAllergy);
		}
		
		else
		{
			throw new DuplicateException("Cannot assign the same allergy twice.");
		}
	}
	
	public void removeAllergy(AttenderAllergy allergy)
	{
		allergies.remove(allergy);
	}
	
	public AttenderAllergy getAllergy(Allergy allergy)
	{
		for(AttenderAllergy attenderAllergy : allergies)
		{
			if(attenderAllergy.getId().getAllergy().equals(allergy))
			{
				return attenderAllergy;
			}
		}
		
		return null;
	}

	public void addTalent(Talent talent, String notes) throws DuplicateException
	{
		AttenderTalent attenderTalent = new AttenderTalent();
		AttenderTalentID id = attenderTalent.getId();
		id.setAttender(this);
		id.setTalent(talent);
		attenderTalent.setNotes(notes);
		
		if(talents.contains(attenderTalent) == false)
		{
			talents.add(attenderTalent);
		}
		
		else
		{
			throw new DuplicateException("Cannot assign the same talent twice.");
		}
	}
	
	public void removeTalent(AttenderTalent talent)
	{
		talents.remove(talent);
	}
	
	public AttenderTalent getTalent(Talent talent)
	{
		for(AttenderTalent attenderTalent : talents)
		{
			if(attenderTalent.getId().getTalent().equals(talent))
			{
				return attenderTalent;
			}
		}
		
		return null;
	}
	
	public boolean equals(Object object)
	{
		if(object instanceof Attender)
		{
			Attender that = (Attender) object;
			
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
		return new HashCodeBuilder().append(firstname).append(lastname).append(middlename).append(id).toHashCode();
	}
}
