package org.church.management.record.locking.management;

import java.util.ArrayList;
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
	private static Hashtable<String, LockInfo> locks = new Hashtable<String, LockInfo>();

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
		String key = object.getEntityType()+"-"+object.getId();
		boolean isLocked = locks.containsKey(key);
		
		//so the object is locked
		if(isLocked)
		{
			LockInfo info = locks.get(key);
			
			//check if it is the same person.
			if(info.getSessionId().equals(sessionId))
			{
				logger.info("ObjectLockSystemManager.lock()- "+username+" is re-locking record: "+key+".");
			}
			
			//different person
			else
			{
				logger.info("ObjectLockSystemManager.lock()- "+username+" could not acquire a lock on record: "+key+".");
				throw new LockException(info.getUsername(), object, info.getDate());
			}
		}
		
		else
		{
			Date lockTime = new Date();
			logger.info("ObjectLockSystemManager.lock()- "+username+" has locked record: "+key+" at "+lockTime+".");
			LockInfo info = new LockInfo(sessionId, username, object, lockTime);
			locks.put(key, info);
		}
		
	}
	
	/**
	 * 
	 * This method will release a lock on a record.
	 * 
	 * @param object: the lock object
	 */
	public static synchronized void unlock(Entity object)
	{
		String key = getEntityId(object);
		LockInfo info = locks.remove(key);
		logger.info(info.getUsername()+" has released the lock on object: "+key+" at "+new Date());
		System.out.println(info.getUsername()+" has released the lock on object: "+key+" at "+new Date());

	}
	
	/**
	 * This method will determine if the object has a lock on it.
	 * 
	 * @param object
	 * @return
	 */
	public static boolean isLocked(Entity object)
	{
		String key = getEntityId(object);
		return locks.containsKey(key);
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
		String key = getEntityId(object);
		return locks.get(key);
	}
	
	public static synchronized List<LockInfo> ListOfLocks(String sessionId)
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
		
		return list;
	}
	
	private static String getEntityId(Entity object)
	{
		return object.getEntityType()+"-"+object.getId();
	}
}
