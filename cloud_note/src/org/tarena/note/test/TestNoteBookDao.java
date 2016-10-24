package org.tarena.note.test;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.dao.NoteBookMapperDao;
import org.tarena.note.entity.NoteBook;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.NoteBookService;

public class TestNoteBookDao {
	private NoteBookMapperDao noteBookDao;
	private NoteBookService noteBookService;
	private ApplicationContext ac;
	
	@Before
	public void init(){
		String conf="applicationContext.xml";
		ac=new ClassPathXmlApplicationContext(conf);
		noteBookDao=ac.getBean("noteBookMapperDao", NoteBookMapperDao.class);
		noteBookService=ac.getBean("noteBookService", NoteBookService.class);
	}
//	@Test //测试noteBookMaperDao
//	public void test1(){
//		List<NoteBook> noteBooks=noteBookDao.findByUserId("48595f52-b22c-4485-9244-f4004255b972");
//		for (NoteBook noteBook : noteBooks) {
//			System.out.println(noteBook);
//		}
//	}
//	@Test //测试noteBookService
//	public void test2(){
//		NoteResult result=noteBookService.findNoteBooks("48595f52-b22c-4485-9244-f4004255b972");
//		List<NoteBook> noteBooks=(List<NoteBook>) result.getData();
//		for (NoteBook noteBook : noteBooks) {
//			System.out.println(noteBook.getCn_notebook_id()+","+noteBook.getCn_notebook_name());
//		}
//	}
	
	@Test
	public void test3(){
		Map<String, String> map=new HashMap<String, String>();
		map.put("book_id", "0037215c-09fe-4eaa-aeb5-25a340c6b39b");
		map.put("book_name", "wsfwsf");
		noteBookDao.rename(map);
	}
	@After
	public void destroy(){
		((AbstractApplicationContext) ac).close();
	}
}
