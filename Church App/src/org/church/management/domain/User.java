package org.church.management.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.church.management.domain.standard.fields.StandardFields;
import org.joda.time.LocalTime;

@Entity
@Table(name="users")
public class User extends StandardFields
{
	private static final long serialVersionUID = 1L;
	
	@Column(name="username", length=30, unique=true)
	private String username;
	
	@Column(name="password", length=30)
	private String password;
	
	@Column(name="first_name", length=50)
	private String firstName;
	
	@Column(name="last_name", length=50)
	private String lastName;
	
	@Column(name="email", length=70)
	private String email;
	
	@Column(name="phone", length=30)
	private String phone;
	
	@Column(name="cell_phone", length=30)
	private String cellPhone;
	
	@Column(name="fax", length=30)
	private String fax;

	@Column(name="is_disabled")
	private boolean isDisabled;
	
	//This field is for information for managers.(Good for scheduling)
	@Column(name="start_of_day")
	@Type(type="org.joda.time.contrib.hibernate.PersistentLocalTimeAsTime")
	private LocalTime startOfDay;
	
	//This field is for information for managers.(Good for scheduling)
	@Column(name="end_of_day")
	@Type(type="org.joda.time.contrib.hibernate.PersistentLocalTimeAsTime")
	private LocalTime endOfDay;
	
	@Column(name="login_start")
	@Type(type="org.joda.time.contrib.hibernate.PersistentLocalTimeAsTime")
	private LocalTime startTime;
	
	@Column(name="login_end")
	@Type(type="org.joda.time.contrib.hibernate.PersistentLocalTimeAsTime")
	private LocalTime endTime;
	
	@Column(name="isDeleted")
	private boolean isDeleted;
	
	//The folder where the files are located.
	@ManyToOne(fetch= FetchType.EAGER)
	private Directory drive;
	
	@Column(name="reset_password")
	private boolean resetPassword;
	
	@Column(name="quote", length=300)
	private String quote;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,targetEntity=UserPreference.class,orphanRemoval=true)
	private List<UserPreference> preferences;
	
	public User()
	{
		super(User.class);
		
		preferences = new ArrayList<UserPreference>();
		
		this.username = "";
		this.firstName = "";
		this.lastName = "";
		this.email = "";
		this.phone = "";
		this.isDisabled = false;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
		setName(firstName+" "+lastName);
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
		
		setName(firstName+" "+lastName);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public boolean isDisabled() {
		return isDisabled;
	}

	public void setDisabled(boolean isDisabled) {
		this.isDisabled = isDisabled;
	}

	
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public String getName(){
		return firstName+" "+lastName;
	}
	
	
	public void setStartOfDay(LocalTime startOfDay) {
		this.startOfDay = startOfDay;
	}

	public LocalTime getStartOfDay() {
		return startOfDay;
	}

	public void setEndOfDay(LocalTime endOfDay) {
		this.endOfDay = endOfDay;
	}

	public LocalTime getEndOfDay() {
		return endOfDay;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	public boolean isDeleted()
	{
		return isDeleted;
	}
	
	public void setDeleted(boolean deleted)
	{
		this.isDeleted = deleted;
	}
	
	public void setDrive(Directory drive) 
	{
		this.drive = drive;
	}

	public Directory getDrive() 
	{
		return drive;
	}

	public boolean isResetPassword() {
		return resetPassword;
	}

	public void setResetPassword(boolean resetPassword) {
		this.resetPassword = resetPassword;
	}

	public List<UserPreference> getPreferences() 
	{
		return preferences;
	}

	public void setPreferences(List<UserPreference> preferences) 
	{
		this.preferences = preferences;
	}
	
	public void addPreference(String value, Preference preference)
	{
		UserPreference userPreference = new UserPreference();
		userPreference.setUser(this);
		userPreference.setPreference(preference);
		userPreference.setValue(value);
		preferences.add(userPreference);
	}
	
	public void removePreference(Preference preference)
	{
		Iterator<UserPreference> iterator = preferences.iterator();
		
		while(iterator.hasNext())
		{
			UserPreference userPreference = iterator.next();
			
			if(userPreference.getPreference().equals(preference))
			{
				iterator.remove();
				return;
			}
		}
	}
	
	public UserPreference getPreference(String preference)
	{
		for(UserPreference userPreference: preferences)
		{
			if(userPreference.getPreference().getName().equals(preference))
			{
				return userPreference;
			}
		}
		
		return null;
	}
	
	public boolean equals(Object obj)
	{
		if(obj == null)
		{
			return false;
		}
		
		else if(obj instanceof User)
		{
			User user = (User) obj;
			
			if(user == this)
			{
				return true;
			}
			
			if(user.getUsername().equals(username))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public int hashCode()
	{
		return new HashCodeBuilder().append(username).append(firstName).append(lastName).append(email).append(phone).append(cellPhone).append(fax).append(isDisabled).append(name).toHashCode();
	}
	
	public User clone()
	{
		User user = new User();
		user.setCellPhone(cellPhone);
		user.setDeleted(isDeleted);
		user.setDisabled(isDisabled);
		user.setEmail(email);
		user.setEndOfDay(endOfDay);
		user.setEndTime(endTime);
		user.setEntityTypeVersion(entityTypeVersion);
		user.setFax(fax);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPassword(password);
		user.setPhone(phone);
		user.setQuote(quote);
		user.setResetPassword(resetPassword);
		user.setStartOfDay(startOfDay);
		user.setStartTime(startTime);
		user.setUsername(username);
		
		for(UserPreference preference : preferences)
		{
			user.addPreference(preference.getValue(), preference.getPreference());
		}
		
		return user;
	}
}
