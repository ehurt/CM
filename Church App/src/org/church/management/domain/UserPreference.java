package org.church.management.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name="user_preferences")
public class UserPreference implements org.church.management.interfaces.entity.Entity
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne(fetch=FetchType.EAGER)
	private User user;
	
	@OneToOne(fetch= FetchType.EAGER)
	private Preference preference;
	
	@Column(name="value", length=100)
	private String value;
	
	public UserPreference()
	{
		this.value = "";
	}

	public Preference getPreference() {
		return preference;
	}

	public void setPreference(Preference preference) {
		this.preference = preference;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public boolean equals(Object obj)
	{
		if(obj == null)
		{
			return false;
		}
		
		else if(obj instanceof UserPreference)
		{
			UserPreference preference = (UserPreference) obj;
			
			if(preference == this)
			{
				return true;
			}
			
			if(getPreference().equals(preference.getPreference()) && getUser().equals(preference.getUser()))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public int hashCode()
	{
		return new HashCodeBuilder().append(value).append(user).append(preference).toHashCode();
	}
	
	public UserPreference clone()
	{
		UserPreference userPreference = new UserPreference();
		userPreference.setUser(user);
		userPreference.setValue(value);
		userPreference.setPreference(preference);
		
		return userPreference;
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
		return UserPreference.class.getName();
	}

	@Override
	public void setEntityType(String entityType) 
	{
	}
}
