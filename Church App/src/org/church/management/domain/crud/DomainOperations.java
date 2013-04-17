package org.church.management.domain.crud;

import java.util.List;

import org.church.management.domain.exceptions.DAOConstraintViolationException;
import org.church.management.domain.exceptions.DAOException;
import org.church.management.domain.exceptions.DAONoObjectFoundException;
import org.church.management.domain.exceptions.DAOStaleStateException;
import org.church.management.record.locking.exception.LockException;


/**
 * 
 * @author Trae
 *
 * @param <T>
 * 
 *  This interface is used for the basic crud Operations along with a few extras.
 * 
 */
public interface DomainOperations<T> extends Comparable<T>
{
	public void save() throws DAOException, DAOConstraintViolationException, DAONoObjectFoundException;
	
	public void delete() throws DAOException, DAOConstraintViolationException, DAONoObjectFoundException, DAOStaleStateException, LockException;
	
	public void update() throws DAOException, DAOConstraintViolationException, DAONoObjectFoundException, DAOStaleStateException;
	
	public boolean exist() throws DAOException;
	
	public Integer rowCount() throws DAOException;
	
	public void retrieve() throws DAOException, DAONoObjectFoundException;
	
	public List<T> findAll() throws DAOException;
	
	public T clone() throws Exception;
	
	public T getFirstRecord() throws DAOException;
	
	public void lock(String sessionId, String username) throws LockException;
	
	public void unlock();
	
	public void copy(T source, T target);
	
	public String getEntityType();
}
