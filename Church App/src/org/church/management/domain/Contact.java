package org.church.management.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.church.management.domain.standard.fields.StandardFields;

@Entity
@Table(name="contacts")
public class Contact extends StandardFields
{

	private static final long serialVersionUID = 1L;
	
	@Column(name="firstname", length=50, nullable=true)
	private String firstname;
	
	@Column(name="lastname", length=50, nullable=true)
	private String lastname;
	
	@Column(name="relationship", length=30, nullable=true)
	private String relationship;
	
	@Column(name="work", length=30, nullable=true)
	private String work;
	
	@Column(name="cell", length=30, nullable=true)
	private String cell;
	
	@Column(name="home", length=30, nullable=true)
	private String home;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private City city;
	
	@Column(name="is_attender")
	private boolean isAttender;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Attender attender;
	
	public Contact()
	{
		super(Contact.class);
		this.isAttender = false;
	}

	public boolean isAttender() {
		return isAttender;
	}

	public void setAttender(boolean isAttender) {
		this.isAttender = isAttender;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public String getCell() {
		return cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public Attender getAttender() {
		return attender;
	}

	public void setAttender(Attender attender) {
		this.attender = attender;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
	public int hashCode()
	{
		return new HashCodeBuilder().append(this.firstname).append(lastname).append(work).append(cell).append(home).append(relationship).append(isAttender).toHashCode();
	}
	
	public boolean equals(Object object)
	{
		if(object instanceof Contact)
		{
			Contact that = (Contact) object;
			
			if(that == this)
			{
				return true;
			}
			
			if(isAttender)
			{
				return this.attender.equals(that.attender);
			}
			
			else
			{
				return new EqualsBuilder().append(that.firstname, this.firstname).append(that.lastname, this.lastname).append(that.city, this.city).isEquals();
			}
		}
		
		return false;
	}

}
