package org.church.management.domain.dao;

import java.util.List;

import org.church.management.domain.ValidHostAddress;
import org.church.management.domain.generic.dao.GenericDao;

public interface ValidHostAddressDao extends GenericDao<ValidHostAddress, Integer>
{	
	public List<ValidHostAddress> getValidHostAddress(String entity, String id);
}
