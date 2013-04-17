package org.church.management.file.storage;

public interface CloudFileStorage extends FileStorage
{
	public boolean isConnected();
	
	public void disconnect();
	
	public boolean reconnect();
}
