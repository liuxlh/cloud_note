package org.tarena.note.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.tarena.note.dao.NoteMapperDao;
import org.tarena.note.entity.Note;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.NoteService;
import org.tarena.note.util.NoteUtil;

@Service("noteService")
public class NoteServiceImpl implements NoteService{
	@Resource
	private NoteMapperDao noteDao;

	@Override
	public NoteResult findNotes(String notebook_id) {
		NoteResult result=new NoteResult();
		List<Map<String, String>> notes=noteDao.findByNoteBookId(notebook_id);
		result.setStatus(0);
		result.setData(notes);
		return result;
	}

	@Override
	public NoteResult addNote(String user_id,String notebook_id,String note_title) {
		NoteResult result=new NoteResult();
		Note note=new Note();
//		String note_id=NoteUtil.creadId();
//		note.setCn_note_id(note_id);
		note.setCn_user_id(user_id);
		note.setCn_notebook_id(notebook_id);
		note.setCn_note_title(note_title);
		Timestamp create_time=new Timestamp(System.currentTimeMillis());
		note.setCn_note_create_time(create_time);
		note.setCn_note_status_id("1");//normal
		note.setCn_note_type_id("1");//normal
		note.setCn_note_body("");
		note.setCn_note_last_modify_time(null);
		if(noteDao.isExist(note)<=0){
			noteDao.save(note);
			result.setStatus(0);
			result.setMsg("添加笔记成功");
			result.setData(note.getCn_note_id());
		}else{
			result.setStatus(1);
			result.setMsg("此笔记本中已有该笔记,请重新输入,以便管理!");
			result.setData(note.getCn_note_id());
		}
		return result;
	}

	@Override
	public NoteResult noteContent(String note_id) {
		NoteResult result=new NoteResult();
		Note note1 =noteDao.noteContent(note_id);
		if(note1!=null){
			result.setStatus(0);
			result.setMsg("成功获取笔记内容");
			result.setData(note1);
		}
		return result;
	}

	@Override
	public NoteResult updateNote(Note note) {
		NoteResult result=new NoteResult();
		noteDao.updateNote(note);
		result.setStatus(0);
		result.setMsg("修改成功!");
		return result;
	}

	@Override
	public NoteResult removeNote(String note_id) {
		NoteResult result=new NoteResult();
		noteDao.updateStatus(note_id);
		result.setStatus(0);
		result.setMsg("删除笔记成功");
		return result;
	}

}
