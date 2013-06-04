package org.church.management.domain.entity.type;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 * @author Trae
 *
 * This class controls the domain object's properties.
 * Like how many custom fields can it have, is this class auditable.
 */
public class EntityType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//Name of the entity type. The Simple Class name.
	@Column(name="name", unique=true)
	private String name;
	
	//If this object is auditable
	@Column(name="is_auditable")
	private boolean isAuditable;
	
	//Number of custom fields
	@Column(name="maximum_of_custom_fields")
	private int numberOfCustomFields;

	//The entity type version.
	@Column(name="version")
	private int version;
	
	@Column(name="entity_type_full_class")
	private String entityTypeFullClassName;
	
	@Column(name="has_tasks")
	private boolean hasTasks;
	
	@Column(name="has_notes")
	private boolean hasNotes;

	@Column(name="hasAttachedFiles")
	private boolean hasAttachedFiles;
	
	@Column(name="hasActivaties")
	private boolean hasActivaties;
	
	public EntityType(Class c, boolean auditable, int version, int numberOfCustomFields){
		this.entityTypeFullClassName = c.getName();
		this.name = c.getSimpleName();
		this.isAuditable = auditable;
		this.version = version;
		this.numberOfCustomFields = numberOfCustomFields;
	}
	
	public EntityType(){
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAuditable() {
		return isAuditable;
	}

	public void setAuditable(boolean isAuditable) {
		this.isAuditable = isAuditable;
	}

	public int getNumberOfCustomFields() {
		return numberOfCustomFields;
	}

	public void setNumberOfCustomFields(int numberOfCustomFields) {
		this.numberOfCustomFields = numberOfCustomFields;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getVersion() {
		return version;
	}

	public void setEntityTypeFullClass(String entityTypeFullClass) {
		this.entityTypeFullClassName = entityTypeFullClass;
	}

	public String getEntityTypeFullClass() {
		return entityTypeFullClassName;
	}
	
	public boolean getHasTasks(){
		return this.hasTasks;
	}
	
	public void setHashTasks(boolean tasks){
		this.hasTasks = tasks;
	}
	
	public boolean isHasNotes() {
		return hasNotes;
	}

	public void setHasNotes(boolean hasNotes) {
		this.hasNotes = hasNotes;
	}

	public boolean isHasAttachedFiles() {
		return hasAttachedFiles;
	}

	public void setHasAttachedFiles(boolean hasAttachedFiles) {
		this.hasAttachedFiles = hasAttachedFiles;
	}

	public boolean isHasActivaties() {
		return hasActivaties;
	}

	public void setHasActivaties(boolean hasActivaties) {
		this.hasActivaties = hasActivaties;
	}

	public void setHasTasks(boolean hasTasks) {
		this.hasTasks = hasTasks;
	}
}
