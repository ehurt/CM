package org.church.management.domain.dao;

import java.util.List;

import org.church.management.domain.Note;
import org.church.management.domain.User;
import org.church.management.domain.generic.dao.GenericDao;

public interface NoteDao extends GenericDao<Note, Integer>
{
	public List<Note> retrieveOpenNotes(String entity, String id);
	
	public List<Note> retrievePrivateNotesByUser(String entity, String id, User user);
}
