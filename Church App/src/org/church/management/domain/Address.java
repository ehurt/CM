package org.church.management.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.beanutils.PropertyUtils;
import org.church.management.domain.crud.DomainOperations;
import org.church.management.domain.exceptions.DAOConstraintViolationException;
import org.church.management.domain.exceptions.DAOException;
import org.church.management.domain.exceptions.DAONoObjectFoundException;
import org.church.management.domain.exceptions.DAOStaleStateException;
import org.church.management.domain.manager.AddressManager;
import org.church.management.interfaces.entity.Entity;
import org.church.management.record.locking.exception.LockException;

/**
 * 
 * @author Trae
 *
 * This class holds an address. 
 * It can be used by any object.
 */

@javax.persistence.Entity
@Table(name="addresses")
public class Address implements Entity, DomainOperations<Address>
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Transient
	private String entityType;
	
	@Column(name="address", length=100)
	private String address;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private City city;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private State state;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Country country;
	
	@Column(name="zipcode", length=12)
	private String zipcode;
	
	@Transient
	private AddressManager manager = null;
	
	public Address()
	{
		entityType = Address.class.getSimpleName();
		manager = new AddressManager();
	}
	
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int getEntityTypeVersion() {
		return 0;
	}

	@Override
	public void setEntityTypeVersion(int version) {
	}

	@Override
	public String getEntityType() {
		return entityType;
	}

	@Override
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	@Override
	public int compareTo(Address arg0) 
	{
		return 0;
	}

	@Override
	public void save() throws DAOException, DAOConstraintViolationException, DAONoObjectFoundException 
	{
		manager.save(this);	
	}

	@Override
	public void delete() throws DAOException, DAOConstraintViolationException, DAONoObjectFoundException, DAOStaleStateException, LockException 
	{
		manager.delete(this);	
	}

	@Override
	public void update() throws DAOException, DAOConstraintViolationException, DAONoObjectFoundException, DAOStaleStateException 
	{
		manager.update(this);	
	}

	@Override
	public boolean exist() throws DAOException 
	{
		return manager.exists(getId());
	}

	@Override
	public Integer rowCount() throws DAOException
	{
		return manager.rowCount();
	}

	@Override
	public void retrieve() throws DAOException, DAONoObjectFoundException 
	{
		Address address = manager.getObject(getId());
		copy(address, this);
	}

	@Override
	public List<Address> findAll() throws DAOException 
	{
		return manager.getAll();
	}

	@Override
	public Address getFirstRecord() throws DAOException 
	{
		return manager.getFirstRecord();
	}

	@Override
	public void lock(String sessionId, String username) throws LockException 
	{
		manager.lock(this, sessionId, username);	
	}

	@Override
	public void unlock() 
	{
		manager.unlock(this);
	}

	@Override
	public void copy(Address source, Address target) {
		try
		{
			PropertyUtils.copyProperties(target, source);
		}
		catch(Exception e){}
	}
	
	public Address clone()
	{
		Address address = new Address();
		copy(this, address);
		return address;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
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

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

}
