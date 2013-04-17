package org.church.management.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.church.management.domain.exceptions.DAOConstraintViolationException;
import org.church.management.domain.exceptions.DAOException;
import org.church.management.domain.exceptions.DAONoObjectFoundException;
import org.church.management.domain.exceptions.DAOStaleStateException;
import org.church.management.domain.manager.CountryManager;
import org.church.management.record.locking.exception.LockException;

@Entity
@Table(name="countries")
public class Country implements org.church.management.interfaces.entity.Entity
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name", length=50, unique=true)
	private String name;
	
	@Column(name="abbrevation", length=6, unique=true)
	private String abbrevation;
	
	@Column(name="has_states")
	private boolean hasStates;
	
	@Transient
	private CountryManager manager;

	public Country()
	{
		this.id = 0;
		name = "";
		hasStates = false;
		manager = new CountryManager();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int getEntityTypeVersion() {
		return 0;
	}

	@Override
	public void setEntityTypeVersion(int version) {}
		

	@Override
	public String getEntityType() {
		return "";
	}

	@Override
	public void setEntityType(String entityType) {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isHasStates() {
		return hasStates;
	}

	public void setHasStates(boolean hasStates) {
		this.hasStates = hasStates;
	}
	
	public String getAbbrevation()
	{
		return this.abbrevation;
	}
	
	public void setAbbrevation(String abbrevation)
	{
		this.abbrevation = abbrevation;
	}
	
	public void save() throws DAOException, DAOConstraintViolationException, DAONoObjectFoundException
	{
		manager.save(this);
	}
	
	public void delete() throws DAOException, DAOConstraintViolationException, DAONoObjectFoundException, DAOStaleStateException, LockException
	{
		manager.delete(this);
	}
	
	public void update() throws DAOException, DAOConstraintViolationException, DAONoObjectFoundException, DAOStaleStateException
	{
		manager.update(this);
	}
	
	public List<Country> getAll() throws DAOException
	{
		return manager.getAll();
	}
	
	public List<State> getAllStatesByCountry() throws DAOException
	{
		return manager.getAllStatesByCountry(this);
	}
	
	public List<City> getAllCitiesByCountry() throws DAOException
	{
		return manager.getAllCitiesByCountry(this);
	}
}
