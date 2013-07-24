package org.church.management.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.Type;
import org.joda.time.LocalTime;

/**
 * 
 * @author ehurt
 *
 * This class will contain the church information. 
 * This information can be used in a report.
 * This is the kind of information used for linking churches together.
 */
@Entity
@Table(name="church_information")
public class ChurchInfo implements org.church.management.interfaces.entity.Entity<Integer>
{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="church_name", length=50)
	private String churchName = "";
	
	@Column(name="church_mission", length=500)
	private String churchMission = "";
	
	@Column(name="minstry", length=50)
	private String partOfMinistry;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Address address;
	
	@Column(name="email", length=70)
	private String email;
	
	@Column(name="phone", length=30)
	private String phone;
	
	@Column(name="service_start")
	@Type(type="org.joda.time.contrib.hibernate.PersistentLocalTimeAsTime")
	private LocalTime serviceStart;
	
	@Column(name="sunday_school_time")
	@Type(type="org.joda.time.contrib.hibernate.PersistentLocalTimeAsTime")
	private LocalTime sundaySchoolTime;
	
	@Column(name="image", length=100)
	private String image = "";
	
	@Column(name="website", length=100)
	private String website = "";
	
	@OneToMany(fetch=FetchType.EAGER)
	private Member pastor;
	
	public ChurchInfo()
	{
		id = null;
		serviceStart = null;
		sundaySchoolTime = null;
		address = null;
		serviceStart = null;
		sundaySchoolTime = null;
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
	
	public String getChurchName() 
	{
		return churchName;
	}
	
	public void setChurchName(String churchName) 
	{
		this.churchName = churchName;
	}
	
	public String getChurchMission() 
	{	
		return churchMission;
	}
	
	public void setChurchMission(String churchMission) 
	{
		this.churchMission = churchMission;
	}
	
	public String getPhone() 
	{
		return phone;
	}
	
	public void setPhone(String phoneNb) 
	{
		this.phone = phoneNb;
	}
	
	public String getEmail() 
	{
		return email;
	}
	
	public void setEmail(String email) 
	{
		this.email = email;
	}
	
	public LocalTime getServiceStart() 
	{
		return serviceStart;
	}
	
	public void setServiceStart(LocalTime serviceStart) 
	{
		this.serviceStart = serviceStart;
	}
	
	public LocalTime getSundaySchoolTime()
	{
		return this.sundaySchoolTime;
	}
	
	public void setSundaySchoolTime(LocalTime sundaySchoolTime)
	{
		this.sundaySchoolTime = sundaySchoolTime;
	}
	
	public String getImage()
	{
		return this.image;
	}
	
	public void setImage(String image)
	{
		this.image = image;
	}
	
	public String getWebsite()
	{
		return website;
	}
	
	public void setWebsite(String url)
	{
		this.website = url;
	}
	
	public String getPartOfMinistry() {
		return partOfMinistry;
	}

	public void setPartOfMinistry(String partOfMinistry) {
		this.partOfMinistry = partOfMinistry;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public Member getPastor()
	{
		return pastor;
	}
	
	public void setPastor(Member pastor)
	{
		this.pastor = pastor;
	}

	@Override
	public String getEntityType() 
	{
		return ChurchInfo.class.getName();
	}
	
	public boolean equals(Object object)
	{
		if(object == null)
		{
			return false;
		}
		
		else
		{
			if(object instanceof ChurchInfo)
			{
				ChurchInfo that = (ChurchInfo) object;
				
				if(that == this)
				{
					return true;
				}
				
				return new EqualsBuilder().append(that.getId(), this.getId()).isEquals();
			}
		}
		
		return false;
	}
	
	public int hashCode()
	{
		return new HashCodeBuilder().append(churchName).append(churchMission).toHashCode();
	}
}
