package org.church.management.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="attendances")
public class Attendance implements org.church.management.interfaces.entity.Entity<Integer>
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name="entity", length=50)
	private String entity;
	
	@Column(name="object_id", length=30)
	private String objectId;

	@Column(name="attended")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar attended;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@Column(name="attender_id", nullable=true)
	private Attender attender;
	
	public Attendance()
	{
		
	}
	
	public Integer getId() 
	{
		return id;
	}

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Calendar getAttended() {
		return attended;
	}

	public void setAttended(Calendar attended) {
		this.attended = attended;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public Attender getAttender() {
		return attender;
	}

	public void setAttender(Attender attender) {
		this.attender = attender;
	}
}
