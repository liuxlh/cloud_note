package org.tarena.note.controller.noteBook;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.NoteBookService;

@Controller
@RequestMapping("/noteBook")
public class NoteBookController {
	@Resource
	private NoteBookService noteBookService;
	
	
	@RequestMapping("/noteBooks.do")
	@ResponseBody//将返回值变成json对象
	public NoteResult execute(String user_id){
		NoteResult result=noteBookService.findNoteBooks(user_id);
		return result;
	}
	
	/**
	 * 添加笔记本
	 * @param notebook_name
	 * @return
	 */
	@RequestMapping("/addBook.do")
	@ResponseBody
	public NoteResult addNoteBook(String notebook_name,String user_id){
		NoteResult result=new NoteResult();
		
		result=noteBookService.addNoteBook(notebook_name,user_id);
		return result;
	}
	
	@RequestMapping("/renamebook.do")
	@ResponseBody
	public NoteResult rename(String user_id,String book_id,String book_name){
		
		System.out.println(user_id+","+book_id+","+book_name);
		NoteResult result=noteBookService.rename(user_id, book_id, book_name);
		return result;
	}

}
