package org.church.management.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.church.management.domain.standard.fields.StandardFields;

/**
 * 
 * @author Trae
 * 
 * This class will hold information for files that 
 * linked to entities.
 */

@Entity
@Table(name="files")
public class Attachment extends StandardFields
{

	private static final long serialVersionUID = 1L;

	//mine type
	@Column(name="mine_type", length=40)
	private String mineType;

	//table
	@Column(name="entity", length=50)
	private String entity;
	
	//the id of the object
	@Column(name="object_id", length=30)
	private String objectId;
	
	//directoryName
	@ManyToOne(fetch=FetchType.EAGER)
	private Directory directory;
	
	//full path
	@Column(name="file_path", length=500)
	private String filePath;
	
	///description
	@Column(name="description", length=800)
	private String description;
	
	@Column(name="original_file_name", length=50)
	private String originalFileName;
	
	@Column(name="new_file_name", length=50)
	private String newFileName;

	
	public Attachment()
	{
		super(Attachment.class);
	}
	
	public String getMineType() {
		return mineType;
	}

	public void setMineType(String mineType) {
		this.mineType = mineType;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String table) {
		this.entity = table;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public Directory getDirectory() {
		return directory;
	}

	public void setDirectory(Directory directory) {
		this.directory = directory;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
		this.setName(originalFileName);
	}

	public String getNewFileName() {
		return newFileName;
	}

	public void setNewFileName(String newFileName) {
		this.newFileName = newFileName;
	}
	
	public Attachment clone()
	{
		Attachment file = new Attachment();
		file.setMineType(mineType);
		file.setEntity(entity);
		file.setObjectId(objectId);
		file.setDirectory(directory);
		file.setDescription(description);
		file.setOriginalFileName(originalFileName);
		
		return file;
	}
	
	public int hashCode()
	{
		HashCodeBuilder builder = new HashCodeBuilder();
		
		builder.append(mineType);
		builder.append(entity);
		builder.append(objectId);
		builder.append(description);
		builder.append(originalFileName);
		
		return builder.toHashCode();
	}
	
	public boolean equals(Object obj)
	{
		if(obj == null)
		{
			return false;
		}
		
		else if(obj instanceof Attachment)
		{
			Attachment file = (Attachment) obj;
			
			if(file == this)
			{
				return true;
			}
			
			if(directory.equals(file.getDirectory()) && name.equals(file.getName()))
			{
				return true;
			}
		}
		return false;
	}

}
