package org.church.management.domain.manager;

import java.util.List;

import org.church.management.domain.ValidHostAddress;
import org.church.management.domain.dao.ValidHostAddressDAO;
import org.church.management.domain.exceptions.DAOException;
import org.church.management.hibernate.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class ValidHostAddressManager extends SessionManager<ValidHostAddress>
{
	private static final long serialVersionUID = 1L;

	public ValidHostAddressManager()
	{
		super(ValidHostAddressManager.class, ValidHostAddress.class, ValidHostAddressDAO.class);
	}
	
	@Override
	public void loadObjects(Session session, ValidHostAddress object) throws HibernateException 
	{	
	}

	@Override
	public List<String> getListOfConstraintViolations(ValidHostAddress object, Exception e) throws DAOException 
	{
		return null;
	}
	
	public List<ValidHostAddress> getAllValidHostAddressByReference(String entity, String id) throws DAOException
	{
		Session session = null;
		List<ValidHostAddress> addresses = null;
		
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			ValidHostAddressDAO dao = (ValidHostAddressDAO) getInstanceOfDataAccessObject(session);
			addresses = dao.getValidHostAddress(entity, id);
		}
		catch(Exception e)
		{
			logger.error("ValidHostAddress.getAllValidHostAddressByReference()- An error has occurred while retrieving a list of addresses.", e);
			throw new DAOException("An error has occurred while retrieving a list of addresses.", e);
		} 
		
		finally
		{
			if(session != null)
			{
				session.flush();
				session.close();
			}
		}
		
		return addresses;
	}
}
