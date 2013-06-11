package org.church.management.interfaces.entity;

import java.io.Serializable;

/**
 * 
 * @author Trae
 *
 * This interface is for entities that can change.
 *
 */
public interface VersionedEntity<ID extends Serializable> extends Entity<ID>
{
	public int getEntityTypeVersion();
	public void setEntityTypeVersion(int version);
}
