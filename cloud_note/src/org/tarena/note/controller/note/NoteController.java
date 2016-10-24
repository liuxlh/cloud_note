package org.tarena.note.controller.note;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.entity.Note;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.NoteService;
import org.tarena.note.util.NoteUtil;

@Controller
@RequestMapping("/note")
public class NoteController {
	@Resource
	private NoteService noteService;
	
	@RequestMapping("/notes.do")
	@ResponseBody //将返回值变成json对象
	public NoteResult execute(String notebook_id){
		NoteResult result=noteService.findNotes(notebook_id);
		return result;
	}
	@RequestMapping("/addNote.do")
	@ResponseBody //将返回值变成json对象
	public NoteResult addNote(String user_id,String notebook_id,String note_title){
		NoteResult result=noteService.addNote(user_id,notebook_id,note_title);
		return result;
	}
	@RequestMapping("/noteContent.do")
	@ResponseBody //将返回值变成json对象
	public NoteResult noteContent(String note_id){
		NoteResult result=noteService.noteContent(note_id);
		return result;
	}
	@RequestMapping("/updatenote.do")
	@ResponseBody //将返回值变成json对象
	public NoteResult updateNote(Note note){
		NoteResult result=noteService.updateNote(note);
		return result;
	}
	
	@RequestMapping("/remove.do")
	@ResponseBody //将返回值变成json对象
	public NoteResult removeNote(String note_id){
		NoteResult result=noteService.removeNote(note_id);
		return result;
	}

	
}
