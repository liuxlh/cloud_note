package org.tarena.note.service;



import org.tarena.note.entity.NoteResult;

public interface NoteBookService {
	public NoteResult findNoteBooks(String user_id); 
	public NoteResult rename(String user_id,String book_id,String book_name);
	public NoteResult addNoteBook(String notebook_name, String user_id);

}
