package org.church.management.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;


/**
 * 
 * @author Trae
 *
 * This class is for attaching notes to an entity.
 *
 */
@Entity
@Table(name="notes")
public class Note implements org.church.management.interfaces.entity.Entity<Integer>
{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="entity", length=50)
	private String entity;
	
	@Column(name="object_id", length=30)
	private String objectId;
	
	@Column(name="title", length=100)
	private String title;
	
	@Column(name="body", length=1500)
	private String body;
	
	@Column(name="is_private")
	private boolean isPrivate;
	
	@ManyToOne
	private User owner;
	
	public Note()
	{
		id = null;
		body = "";
		title = "";
		isPrivate = false;
		objectId = "";
		entity = "";
	}
	
	public Integer getId() 
	{
		return id;
	}
	
	public void setId(Integer id) 
	{
		this.id = id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isPrivate() {
		return isPrivate;
	}

	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getEntityType() 
	{
		return Note.class.getName();
	}
	
	public int hashCode()
	{
		return new HashCodeBuilder().append(entity).append(objectId).append(isPrivate).append(title).append(body).toHashCode();
	}
	
	public Note clone()
	{
		Note note = new Note();
		note.setEntity(entity);
		note.setBody(body);
		note.setObjectId(objectId);
		note.setOwner(owner);
		note.setPrivate(isPrivate);
		note.setTitle(title);
		
		return note;
	}
	
	public boolean equals(Object obj)
	{
		if(obj == null)
		{
			return false;
		}
		
		if(obj instanceof Note)
		{
			Note note = (Note) obj;
			
			if(note == this)
			{
				return true;
			}
			
			return new EqualsBuilder().append(note.getId(), this.getId()).isEquals();
		}
		
		return false;
	}
}
