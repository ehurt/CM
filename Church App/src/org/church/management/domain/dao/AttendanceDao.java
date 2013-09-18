package org.church.management.domain.dao;

import java.util.List;

import org.church.management.domain.Attendance;
import org.church.management.interfaces.entity.Entity;

@SuppressWarnings("rawtypes") 
public interface AttendanceDao 
{
	public List<Attendance> getAttendance(Entity entity);
}
