package org.tarena.note.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.tarena.note.dao.NoteBookMapperDao;
import org.tarena.note.entity.NoteBook;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.NoteBookService;
import org.tarena.note.util.NoteUtil;
@Service("noteBookService")
public class NoteBookServiceImpl implements NoteBookService{
	@Resource
	private NoteBookMapperDao noteBookDao;

	@Override
	public NoteResult findNoteBooks(String user_id) {
		NoteResult result=null;
		List<NoteBook> noteBooks=noteBookDao.findByUserId(user_id);
		result=new NoteResult(0, null, noteBooks);
		return result;
	}

	@Override
	public NoteResult addNoteBook(String notebook_name, String user_id) {
		NoteResult result=new NoteResult();
		
		NoteBook notebook=new NoteBook();
		notebook.setCn_user_id(user_id);
		notebook.setCn_notebook_name(notebook_name);
		Timestamp time=new Timestamp(System.currentTimeMillis());
		notebook.setCn_notebook_createtime(time);
		notebook.setCn_notebook_type_id("5");//设置类型为normal
		
		if(noteBookDao.isExist(notebook)<=0){
			noteBookDao.save(notebook);
			result.setStatus(0);
			result.setMsg("添加成功");
			result.setData(notebook.getCn_notebook_id());
		}else{
			result.setStatus(1);
			result.setMsg("此笔记本已存在，请重新添加!");
			result.setData(null);
		}
		return result;
	}

	@Override
	public NoteResult rename(String user_id,String book_id,String book_name) {
		NoteResult result=new NoteResult();
		NoteBook book=new NoteBook();
		book.setCn_user_id(user_id);
		book.setCn_notebook_name(book_name);
		Map<String, String> map=new HashMap<String, String>();
		map.put("book_id", book_id);
		map.put("book_name", book_name);
		if(noteBookDao.isExist(book)>1){
			result.setStatus(1);
			result.setMsg("笔记本同名，请重新输入");
		}else{
			result.setStatus(0);
			result.setMsg("重命名成功");
			noteBookDao.rename(map);
		}
		
		return result;
	}


}
