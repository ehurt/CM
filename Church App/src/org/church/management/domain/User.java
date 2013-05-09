package org.church.management.domain;

import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import org.apache.commons.beanutils.PropertyUtils;
import org.church.management.domain.crud.DomainOperations;
import org.church.management.domain.exceptions.DAOConstraintViolationException;
import org.church.management.domain.exceptions.DAOException;
import org.church.management.domain.exceptions.DAONoObjectFoundException;
import org.church.management.domain.exceptions.DAOStaleStateException;
import org.church.management.domain.manager.UserManager;
import org.church.management.domain.manager.ValidHostAddressManager;
import org.church.management.domain.standard.fields.StandardFields;
import org.church.management.record.locking.exception.LockException;
import org.joda.time.LocalTime;

@Entity
@Table(name="users")
public class User extends StandardFields implements DomainOperations<User>
{
	private static final long serialVersionUID = 1L;
	
	@Column(name="username", length=30, unique=true)
	private String username;
	
	@Column(name="password", length=30)
	private String password;
	
	@Column(name="first_name", length=50)
	private String firstName;
	
	@Column(name="last_name", length=50)
	private String lastName;
	
	@Column(name="email", length=70)
	private String email;
	
	@Column(name="phone", length=30)
	private String phone;
	
	@Column(name="cell_phone", length=30)
	private String cellPhone;
	
	@Column(name="fax", length=30)
	private String fax;

	@Column(name="is_disabled")
	private boolean isDisabled;
	
	@Column(name="start_of_day")
	@Type(type="org.joda.time.contrib.hibernate.PersistentLocalTimeAsTime")
	private LocalTime startOfDay;
	
	@Column(name="end_of_day")
	@Type(type="org.joda.time.contrib.hibernate.PersistentLocalTimeAsTime")
	private LocalTime endOfDay;
	
	@Column(name="login_start")
	@Type(type="org.joda.time.contrib.hibernate.PersistentLocalTimeAsTime")
	private LocalTime startTime;
	
	@Column(name="login_end")
	@Type(type="org.joda.time.contrib.hibernate.PersistentLocalTimeAsTime")
	private LocalTime endTime;
	
	@Column(name="isDeleted")
	private boolean isDeleted;
	
	//The folder where the files are located.
	@ManyToOne(fetch= FetchType.EAGER)
	private Directory drive;
	
	@Column(name="reset_password")
	private boolean resetPassword;
	
	@Column(name="quote", length=300)
	private String quote;
	
	private Map<String, UserPreference> preferences;
	
	@Transient
	private UserManager manager = null;
	@Transient
	private ValidHostAddressManager hostManager = null;
	
	public User()
	{
		super(User.class);
		manager = new UserManager();
		hostManager = new ValidHostAddressManager();
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
		setName(firstName+" "+lastName);
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
		
		setName(firstName+" "+lastName);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public boolean isDisabled() {
		return isDisabled;
	}

	public void setDisabled(boolean isDisabled) {
		this.isDisabled = isDisabled;
	}

	
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public String getName(){
		return firstName+" "+lastName;
	}
	
	
	public void setStartOfDay(LocalTime startOfDay) {
		this.startOfDay = startOfDay;
	}

	public LocalTime getStartOfDay() {
		return startOfDay;
	}

	public void setEndOfDay(LocalTime endOfDay) {
		this.endOfDay = endOfDay;
	}

	public LocalTime getEndOfDay() {
		return endOfDay;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	public boolean isDeleted()
	{
		return isDeleted;
	}
	
	public void setDeleted(boolean deleted)
	{
		this.isDeleted = deleted;
	}
	
	public void setDrive(Directory drive) 
	{
		this.drive = drive;
	}

	public Directory getDrive() 
	{
		return drive;
	}

	@Override
	public void copy(User source, User target) {
		try
		{
			PropertyUtils.copyProperties(target, source);
		}
		catch(Exception e){}
		
	}

	@Override
	public void delete() throws DAOException, DAOConstraintViolationException, DAONoObjectFoundException, DAOStaleStateException, LockException 
	{
		manager.delete(this);	
	}

	@Override
	public boolean exist() throws DAOException 
	{
		return manager.exists(getId());
	}

	@Override
	public List<User> findAll() throws DAOException
	{
		return manager.getAll();
	}

	@Override
	public User getFirstRecord() throws DAOException 
	{
		return manager.getFirstRecord();
	}

	@Override
	public void lock(String sessionId, String username) throws LockException {
		
		manager.lock(this, sessionId, username);
	}

	@Override
	public void retrieve() throws DAOException, DAONoObjectFoundException {
		User user = manager.getObject(getId());
		copy(user,this);
	}

	@Override
	public Integer rowCount() throws DAOException 
	{
		return manager.rowCount();
	}

	@Override
	public void save() throws DAOException, DAOConstraintViolationException, DAONoObjectFoundException
	{
		manager.save(this);
	}

	public void unlock() 
	{
		manager.unlock(this);
	}

	@Override
	public void update() throws DAOException, DAOConstraintViolationException, DAONoObjectFoundException, DAOStaleStateException 
	{
		manager.update(this);	
	}
	
	public boolean login() throws DAOException
	{
		return manager.login(username, password);
	}
	
	public void getUserByUsername() throws DAOException
	{
		User user = manager.getUserByUsername(this.username);
		copy(user, this);
	}

	@Override
	public int compareTo(User user) 
	{
		if(user == null)
		{
			return -1;
		}
		
		else if(user.getId() == this.getId())
		{
			return 0;
		}
		
		else if(user.getId() < this.getId())
		{
			return -1;
		}
		
		return 1;
	}
	
	public User clone()
	{
		User user = new User();
		copy(this, user);
		return user;
	}

	public boolean isResetPassword() {
		return resetPassword;
	}

	public void setResetPassword(boolean resetPassword) {
		this.resetPassword = resetPassword;
	}
	
	public boolean validLoginTime(LocalTime currentTime){
		boolean login = false;
		if(startTime == null || endTime == null){
			login = true;
		} else if(currentTime.compareTo(startTime) >= 0 && currentTime.compareTo(endTime) <= 0){
			login = true;
		}
		return login;
	}
	
	/**
	 * 
	 * This method will return all the host address that 
	 * the user can have access from.
	 * 
	 * @return
	 * @throws DAOException
	 */
	public List<ValidHostAddress> getValidHostAddressForUser() throws DAOException
	{
		return hostManager.getAllValidHostAddressByReference(this.getEntityType(), getId()+"");
	}

	public Map<String, UserPreference> getPreferences() 
	{
		return preferences;
	}

	public void setPreferences(Map<String, UserPreference> preferences) 
	{
		this.preferences = preferences;
	}
	
	public UserPreference getUserPreference(String preference)
	{
		return preferences.get(preference);
	}
}
