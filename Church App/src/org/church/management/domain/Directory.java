package org.church.management.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name="directories")
public class Directory implements org.church.management.interfaces.entity.Entity<Integer>
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id = 0;
	
	@Column(name="name", length=50)
	private String name = "";
	
	@Column(name="path", length=400)
	private String path = "";
	
	@Column(name="is_public")
	private boolean publicDirectory;

	public Directory()
	{
		this.name = "";
		this.path = "";
	}
	
	public Integer getId() 
	{
		return id;
	}

	public void setId(Integer id) 
	{
		this.id = id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}

	public String getPath()
	{
		return path;
	}
	
	public void setPath(String path)
	{
		this.path = path;
	}
	
	public boolean isPublicDirectory()
	{
		return publicDirectory;
	}
	
	public void setPublicDirectory(boolean publicDirectory)
	{
		this.publicDirectory = publicDirectory;
	}
	
	public int hashCode()
	{
		return new HashCodeBuilder().append(name).append(path).append(publicDirectory).toHashCode();
	}
	
	public Directory clone()
	{
		Directory directory = new Directory();
		directory.setName(name);
		directory.setPath(path);
		directory.setPublicDirectory(publicDirectory);
		
		return directory;
	}
	
	public boolean equals(Object obj)
	{
		if(obj == null)
		{
			return false;
		}
		
		else if(obj instanceof Directory)
		{
			Directory directory = (Directory) obj;
			
			if(directory == this)
			{
				return true;
			}
			
			if(name.equals(directory.getName()) && path.equals(directory.getPath()))
			{
				return true;
			}
		}
		
		return false;
	}

	@Override
	public String getEntityType() 
	{
		return Directory.class.getName();
	}

	@Override
	public void setEntityType(String entityType) 
	{
	}
}
