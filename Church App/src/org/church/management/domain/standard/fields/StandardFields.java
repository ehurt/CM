package org.church.management.domain.standard.fields;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.church.management.domain.User;
import org.church.management.interfaces.entity.Entity;
import org.church.management.interfaces.entity.VersionedEntity;

/**
 * 
 * @author Trae
 * 
 * This class holds all the standard relative to all the almost all 
 * the domain objects in the application.
 */

@MappedSuperclass
public class StandardFields implements VersionedEntity, Serializable {

	private static final long serialVersionUID = 1L;
	
	//the primary key
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	
	//when was the object created
	@Column(name="created_on")
	@Temporal(TemporalType.TIMESTAMP)
	protected Calendar createdOn;
	
	//who created the record
	@OneToOne
	protected User createdBy;
	
	//when was the last time the object was modified
	@Column(name="last_modified_on")
	@Temporal(TemporalType.TIMESTAMP)
	protected Calendar lastModifiedOn;
	
	//who modified the record last
	@OneToOne
	protected User lastModifiedBy;
	
	//the last time the record was looked at
	@Column(name="last_viewed_on")
	@Temporal(TemporalType.TIMESTAMP)
	protected Calendar lastViewedOn;
	
	//who looked at the last object
	@OneToOne
	protected User lastViewedBy;
	
	@Column(name="name", length=100)
	protected String name;
	
	//who owners the record
	@OneToOne
	protected User owner;
	
	//Keep track of which version this entity type is.
	@Column(name="entity_type_version", nullable=true)
	protected int entityTypeVersion;
	
	@Transient
	protected String entityType;
	
	@SuppressWarnings("rawtypes")
	public StandardFields(Class entity){
		this.entityType = entity.getSimpleName();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Calendar getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Calendar createdOn) {
		this.createdOn = createdOn;
	}

	public Calendar getLastModifiedOn() {
		return lastModifiedOn;
	}

	public void setLastModifiedOn(Calendar lastModifiedOn) {
		this.lastModifiedOn = lastModifiedOn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public User getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(User lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public User getLastViewedBy() {
		return lastViewedBy;
	}

	public void setLastViewedBy(User lastViewedBy) {
		this.lastViewedBy = lastViewedBy;
	}

	public Calendar getLastViewedOn() {
		return lastViewedOn;
	}

	public void setLastViewedOn(Calendar lastViewedOn) {
		this.lastViewedOn = lastViewedOn;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public void setEntityTypeVersion(int version) {
		this.entityTypeVersion = version;
	}

	public int getEntityTypeVersion() {
		return entityTypeVersion;
	}

	@Transient
	public String getEntityType() {
		return entityType;
	}
	
	@Transient
	public void setEntityType(String entityType){
		this.entityType = entityType;
	}
}
