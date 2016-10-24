package org.tarena.note.service;



import org.tarena.note.entity.Note;
import org.tarena.note.entity.NoteResult;


public interface NoteService {
	public NoteResult findNotes(String notebook_id);
	public NoteResult addNote(String user_id,String notebook_id,String note_title);
	public NoteResult noteContent(String note_id);
	
	public NoteResult updateNote(Note note);
	
	public NoteResult removeNote(String note_id);
}
