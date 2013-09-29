package org.church.management.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.church.management.domain.keys.UserPreferenceID;

@Entity
@Table(name="user_preferences")
public class UserPreference implements org.church.management.interfaces.entity.Entity<UserPreferenceID>
{
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private UserPreferenceID id;
	
	@Column(name="value", length=100)
	private String value;
	
	public UserPreference()
	{
		this.value = "";
		this.id = new UserPreferenceID();
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public UserPreferenceID getId() 
	{
		return id;
	}

	public void setId(UserPreferenceID id) 
	{	
		this.id = id;
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
			
			return new EqualsBuilder().append(preference.getId(), this.getId()).isEquals();
		}
		
		return false;
	}
	
	public int hashCode()
	{
		return new HashCodeBuilder().append(value).append(id).toHashCode();
	}
	
	public UserPreference clone()
	{
		UserPreference userPreference = new UserPreference();
		userPreference.setValue(value);
		userPreference.setId(id);
		
		return userPreference;
	}
}
