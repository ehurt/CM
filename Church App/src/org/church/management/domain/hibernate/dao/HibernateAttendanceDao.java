package org.church.management.domain.hibernate.dao;

import java.util.List;

import org.church.management.domain.Attendance;
import org.church.management.domain.dao.AttendanceDao;
import org.church.management.interfaces.entity.Entity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

@SuppressWarnings("rawtypes")
public class HibernateAttendanceDao extends HibernateGenericDao<Attendance, Integer> implements AttendanceDao
{

	public HibernateAttendanceDao() 
	{
		super(Attendance.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Attendance> getAttendance(Entity e) 
	{
		String objectId = e.getId().toString();
		String entity = e.getClass().getSimpleName();
		
		Session session = this.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Attendance.class);
		Criterion criterion = Restrictions.eq("entity", entity);
		criterion = Restrictions.and(criterion, Restrictions.eq("objectId", objectId));
		criteria.add(criterion);
		
		return criteria.list();
	}
}
