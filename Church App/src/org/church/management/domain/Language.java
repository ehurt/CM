package org.church.management.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * 
 * @author Trae
 *
 * This class will hold the information for a language.
 *
 */
@Entity
@Table(name="languages")
public class Language implements org.church.management.interfaces.entity.Entity<Integer>
{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="abbrevation", length=6, unique=true)
	private String abbrevation;
	
	@Column(name="name", length=50)
	private String name;
	
	public Language()
	{
		this.name = "";
		this.abbrevation = "";
	}
	
	public String getAbbrevation()
	{
		return abbrevation;
	}
	
	public void setAbbrevation(String abbrevation)
	{
		this.abbrevation = abbrevation;
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
	
	public int hashCode()
	{
		return new HashCodeBuilder().append(name).append(abbrevation).toHashCode();
	}
	
	public Language clone()
	{
		Language language = new Language();
		language.setName(name);
		language.setAbbrevation(abbrevation);
		
		return language;
	}
	
	public boolean equals(Object obj)
	{
		if(obj == null)
		{
			return false;
		}
		
		else if(obj instanceof Language)
		{
			Language language = (Language) obj;
			
			if(language == this)
			{
				return true;
			}
			
			return new EqualsBuilder().append(name, language.getName()).append(abbrevation, language.getAbbrevation()).isEquals();
		}
		
		return false;
	}

	@Override
	public String getEntityType() 
	{
		return Language.class.getName();
	}
}
