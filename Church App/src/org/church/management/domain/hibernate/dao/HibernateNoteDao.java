package org.church.management.domain.hibernate.dao;

import java.util.List;

import org.church.management.domain.Note;
import org.church.management.domain.User;
import org.church.management.domain.dao.NoteDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

public class HibernateNoteDao extends HibernateGenericDao<Note, Integer> implements NoteDao
{

	public HibernateNoteDao() 
	{
		super(Note.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Note> retrieveOpenNotes(String entity, String id)
	{
		Session session = getCurrentSession();
		Criteria criteria = session.createCriteria(Note.class);
		LogicalExpression expression = Restrictions.and(Restrictions.eq("entity", entity), Restrictions.eq("objectId", id));
		expression = Restrictions.and(Restrictions.eq("isPrivate", false), expression);
		List<Note> notes = criteria.add(expression).list();
		return notes;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Note> retrievePrivateNotesByUser(String entity, String id, User user) 
	{
		Session session = getCurrentSession();
		Criteria criteria = session.createCriteria(Note.class);
		LogicalExpression expression = Restrictions.and(Restrictions.eq("entity", entity), Restrictions.eq("objectId", id));
		expression = Restrictions.and(Restrictions.eq("isPrivate", true), expression);
		expression = Restrictions.and(Restrictions.eq("owner", user), expression);
		List<Note> notes = criteria.add(expression).list();
		return notes;
	}
}
