package org.tarena.note.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.dao.NoteBookMapperDao;
import org.tarena.note.dao.NoteMapperDao;
import org.tarena.note.entity.Note;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.NoteBookService;
import org.tarena.note.service.NoteService;
import org.tarena.note.util.NoteUtil;

public class TestAddNote {
	private NoteMapperDao noteDao;
	private NoteService noteService;
	private ApplicationContext ac;
	
	@Before
	public void init(){
		String conf="applicationContext.xml";
		ac=new ClassPathXmlApplicationContext(conf);
		noteDao=ac.getBean("noteMapperDao", NoteMapperDao.class);
		noteService=ac.getBean("noteService", NoteService.class);
	}
	@Test
	public void test1(){
		Note note=new Note();
		note.setCn_user_id("39295a3d-cc9b-42b4-b206-a2e7fab7e77c");
		note.setCn_notebook_id("20b4cbec-bd55-4c21-9c41-3a11ada2b803");
		note.setCn_note_title("测试参加活动——乱七八糟");
		int n=noteDao.isExist(note);
		if(n>0){
			System.out.println("该用户在此笔记本中已有这条笔记");
		}else{
			System.out.println("可以添加此笔记");
		}
	}
//	@Test
//	public void test2(){
//		Note note=new Note();
//		String note_id=NoteUtil.creadId();
//		note.setCn_note_id(note_id);
//		note.setCn_user_id("39295a3d-cc9b-42b4-b206-a2e7fab7e77c");
//		note.setCn_notebook_id("20b4cbec-bd55-4c21-9c41-3a11ada2b803");
//		note.setCn_note_title("笔记1笔记1笔记1笔记1");
//		if(noteDao.isExist(note)<=0){
//			noteDao.save(note);
//			System.out.println("添加成功");
//		}else{
//			System.out.println("添加失败");
//		}
//	}
	@Test
	public void test3(){
		Note note=new Note();
		String note_id=NoteUtil.creadId();
		note.setCn_note_id(note_id);
		note.setCn_user_id("39295a3d-cc9b-42b4-b206-a2e7fab7e77c");
		note.setCn_notebook_id("20b4cbec-bd55-4c21-9c41-3a11ada2b803");
		note.setCn_note_title("笔记笔记笔记笔记");
		NoteResult result=noteService.addNote("39295a3d-cc9b-42b4-b206-a2e7fab7e77c",
				"20b4cbec-bd55-4c21-9c41-3a11ada2b803",
				"笔记2笔记2笔记2笔记2");
		System.out.println(result.getMsg());
		
	}
	
	
	

}
