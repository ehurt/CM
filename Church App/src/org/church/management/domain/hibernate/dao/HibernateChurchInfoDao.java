package org.church.management.domain.hibernate.dao;

import org.church.management.domain.ChurchInfo;

public class HibernateChurchInfoDao extends HibernateGenericDao<ChurchInfo, Integer>
{
	public HibernateChurchInfoDao()
	{
		super(ChurchInfo.class);
	}
}
