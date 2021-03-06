package org.church.management.domain.dao;

import java.util.List;

import org.church.management.domain.Attender;
import org.church.management.domain.Member;
import org.church.management.domain.Regular;
import org.church.management.domain.Visitor;
import org.church.management.domain.generic.dao.GenericDao;

public interface AttenderDao extends GenericDao<Attender, Integer>
{
	public List<Member> getAllMembers();
	
	public List<Regular> getAllRegulars();
	
	public List<Visitor> getAllVisitors();
}
