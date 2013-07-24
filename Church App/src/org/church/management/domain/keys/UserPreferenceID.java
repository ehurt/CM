package org.church.management.domain.keys;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.church.management.domain.Preference;
import org.church.management.domain.User;

@Embeddable
public class UserPreferenceID implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private User user;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Preference preference;
	
	public UserPreferenceID()
	{
		this.setUser(new User());
		this.setPreference(new Preference());
	}

	public Preference getPreference() {
		return preference;
	}

	public void setPreference(Preference preference) {
		this.preference = preference;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public int hashCode()
	{
		return new HashCodeBuilder().append(user).append(preference).toHashCode();
	}
	
	public boolean equals(Object obj)
	{
		if(obj == null)
		{
			return false;
		}
		
		else if(obj instanceof UserPreferenceID)
		{
			UserPreferenceID that = (UserPreferenceID) obj;
			
			if(this == that)
			{
				return true;
			}
			
			return new EqualsBuilder().append(this.user, that.user).append(this.preference, that.preference).isEquals();
		}
		
		return false;
	}
}
