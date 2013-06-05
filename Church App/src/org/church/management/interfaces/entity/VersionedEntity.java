package org.church.management.interfaces.entity;

/**
 * 
 * @author Trae
 *
 * This interface is for entities that can change.
 *
 */
public interface VersionedEntity extends Entity
{
	public int getEntityTypeVersion();
	public void setEntityTypeVersion(int version);
}
