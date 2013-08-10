package org.church.management.system.settings;

import org.apache.log4j.Logger;
import org.church.management.file.storage.FileStorage;

/**
 * 
 * @author Trae
 *
 * This class will hold the specific settings 
 * for the whole application.
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public final class SystemSettings 
{
	private Logger logger = Logger.getLogger(SystemSettings.class);
	
	private static FileStorage fileStorage = null;
	
	private SystemSettings(){}

	public static FileStorage getFileStorage() 
	{
		return fileStorage;
	}
	
	public static void setFileStorage(FileStorage storage)
	{
		fileStorage = storage;
	}
}
