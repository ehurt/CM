package org.church.management.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.church.management.domain.exceptions.DAOConstraintViolationException;
import org.church.management.domain.exceptions.DAOException;
import org.church.management.domain.exceptions.DAONoObjectFoundException;
import org.church.management.domain.exceptions.DAOStaleStateException;
import org.church.management.domain.manager.StateManager;
import org.church.management.record.locking.exception.LockException;

@Entity
@Table(name="states")
public class State implements org.church.management.interfaces.entity.Entity
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="NAME", length=50)
	private String name;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Country country;
	
	@Transient
	private StateManager manager;

	public State()
	{
		id = 0;
		this.name = "";
		country = null;
		manager = new StateManager();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
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
	
	public void save() throws DAOException, DAOConstraintViolationException, DAONoObjectFoundException
	{
		manager.save(this);
	}
	
	public void update() throws DAOException, DAOConstraintViolationException, DAONoObjectFoundException, DAOStaleStateException
	{
		manager.update(this);
	}
	
	public void delete() throws DAOException, DAOConstraintViolationException, DAONoObjectFoundException, DAOStaleStateException, LockException
	{
		manager.delete(this);
	}
	
	public List<State> getAll() throws DAOException
	{
		return manager.getAll();
	}
	
	public List<City> getAllCitiesByState() throws DAOException
	{
		return manager.getAllCitiesByState(this);
	}
}
