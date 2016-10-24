package org.tarena.note.dao;

import java.util.List;
import java.util.Map;

import org.tarena.note.entity.Note;


public interface NoteMapperDao {
	public List<Map<String, String>> findByNoteBookId(String notebook_id);
	
	public int isExist(Note note);
	public void save(Note note);
	public Note noteContent(String note_id);
	
	public void updateNote(Note note);
	
	public void updateStatus(String note_id);

}
