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
import org.church.management.domain.manager.CityManager;
import org.church.management.record.locking.exception.LockException;


@Entity
@Table(name="cities")
public class City implements org.church.management.interfaces.entity.Entity
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name", length=30)
	private String name;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private State state;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Country country;
	
	@Transient
	private CityManager manager = null;
	
	public City()
	{
		id = 0;
		name = "";
		manager = new CityManager();
	}
	
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
		return null;
	}

	@Override
	public void setEntityType(String entityType) {}
	
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
	
	public List<City> getAll() throws DAOException
	{
		return manager.getAll();
	}
}
