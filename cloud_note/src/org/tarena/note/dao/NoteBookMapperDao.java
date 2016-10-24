package org.tarena.note.dao;

import java.util.List;
import java.util.Map;

import org.tarena.note.entity.NoteBook;

public interface NoteBookMapperDao {
	public List<NoteBook> findByUserId(String user_id);
	public void save(NoteBook notebook);
	public int isExist(NoteBook notebook);
	public void rename(Map<String, String> map);
}
