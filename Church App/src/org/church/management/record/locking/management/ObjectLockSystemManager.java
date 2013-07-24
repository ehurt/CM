package org.church.management.record.locking.management;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.church.management.interfaces.entity.Entity;
import org.church.management.record.locking.exception.LockException;
import org.church.management.record.locking.info.LockInfo;


@SuppressWarnings("rawtypes")
public class ObjectLockSystemManager 
{
	private static final Logger logger = Logger.getLogger(ObjectLockSystemManager.class);
	private static Hashtable<Object, LockInfo> locks = new Hashtable<Object, LockInfo>();

	private ObjectLockSystemManager(){}
	
	/**
	 * 
	 * This method will lock a record.
	 * 
	 * @param object : the object to be locked.
	 * @param sessionId : the user session id.
	 * @param username: the name of the user.
	 * @throws LockException : if the record is already locked.
	 */
	public static synchronized void lock(Entity object, String sessionId, String username) throws LockException
	{
		boolean isLocked = locks.containsKey(object);
		
		//so the object is locked
		if(isLocked)
		{
			LockInfo info = locks.get(object);
			
			//check if it is the same person.
			if(info.getSessionId().equals(sessionId))
			{
				logger.info("ObjectLockSystemManager.lock()- "+username+" is re-locking record: "+object+".");
			}
			
			//different person
			else
			{
				logger.info("ObjectLockSystemManager.lock()- "+username+" could not acquire a lock on record: "+object+".");
				throw new LockException(info.getUsername(), object, info.getDate());
			}
		}
		
		else
		{
			Date lockTime = new Date();
			logger.info("ObjectLockSystemManager.lock()- "+username+" has locked record: "+object+" at "+lockTime+".");
			LockInfo info = new LockInfo(sessionId, username, object, lockTime);
			locks.put(object, info);
		}
		
	}
	
	/**
	 * 
	 * This method will release a lock on a record.
	 * 
	 * @param object: the lock object
	 */
	public static synchronized void unlock(Entity object, String sessionId)
	{
		LockInfo info = locks.get(object);
		
		if(info != null)
		{
			if(info.getSessionId().equals(sessionId))
			{
				locks.remove(object);
				logger.info(info.getUsername()+" has released the lock on object: "+object+" at "+new Date());
			}
		}
	}
	
	/**
	 * 
	 * This method will unlock all records for a session.
	 * 
	 * @param sessionId
	 */
	public static synchronized void unlockAllRecordsForUser(String sessionId)
	{
		for(Object key: locks.keySet())
		{
			LockInfo info = locks.get(key);
			
			if(info.getSessionId().equals(sessionId))
			{
				locks.remove(key);
			}
		}
	}
	
	/**
	 * This method will determine if the object has a lock on it.
	 * 
	 * @param object
	 * @return
	 */
	public static boolean isLocked(Entity object)
	{
		return locks.containsKey(object);
	}
	
	/**
	 * 
	 * This method will return the lock info of a lock record.
	 * 
	 * @param object: the lock object.
	 * @return lock information else null.
	 */
	public static synchronized LockInfo retrieveLockInformation(Entity object)
	{
		return locks.get(object);
	}
	
	public static synchronized List<LockInfo> listOfLocks(String sessionId)
	{
		List<LockInfo> list = new ArrayList<LockInfo>();
		
		Iterator<LockInfo> iterator = locks.values().iterator();
		
		while(iterator.hasNext())
		{
			LockInfo info = (LockInfo) iterator.next();
		
			if(info.getSessionId().equals(sessionId))
			{
				list.add(info);
			}
		}
		
		return Collections.unmodifiableList(list);
	}
	
	/**
	 * 
	 * This method will return all the record locks.
	 * 
	 * @return
	 */
	public static synchronized List<LockInfo> listOfLocks()
	{
		return (List<LockInfo>) Collections.unmodifiableCollection(locks.values());
	}
}
