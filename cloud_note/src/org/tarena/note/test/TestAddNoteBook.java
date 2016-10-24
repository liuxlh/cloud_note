package org.tarena.note.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.dao.NoteBookMapperDao;
import org.tarena.note.entity.NoteBook;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.NoteBookService;
import org.tarena.note.service.NoteService;
import org.tarena.note.util.NoteUtil;

public class TestAddNoteBook {
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
//	@Test
//	public void test1(){
//		NoteBook notebook=new NoteBook();
//		String notebook_id=NoteUtil.creadId();
//		notebook.setCn_notebook_id(notebook_id);
//		notebook.setCn_user_id("39295a3d-cc9b-42b4-b206-a2e7fab7e77c");
//		notebook.setCn_notebook_name("股份花里胡哨1");
//		noteBookDao.save(notebook);
//		System.out.println("11111");
//	}
	
//	@Test
//	public void test2(){
//		NoteBook notebook=new NoteBook();
//		notebook.setCn_user_id("52f9b276-38ee-447f-a3aa-0d54e7a736e4");
//		notebook.setCn_notebook_id("0037215c-09fe-4eaa-aeb5-25a340c6b39b");
//		notebook.setCn_notebook_name("tf规范的收入上覆盖1");
//		NoteResult result=new NoteResult();
//		result=noteBookService.addNoteBook(notebook);
//		System.out.println(result.getMsg());
//	}
	
//	@Test
//	public void test3(){
//		NoteBook notebook=new NoteBook();
//		notebook.setCn_user_id("39295a3d-cc9b-42b4-b206-a2e7fab7e77c");
//		notebook.setCn_notebook_id("0037215c-09fe-4eaa-aeb5-25a340c6b39b");
//		notebook.setCn_notebook_name("tf规范的收入上覆盖");
//		NoteBook notebook1=noteBookDao.isExist(notebook);
//		if(notebook1!=null){
//			System.out.println("该用户已有此笔记本，不必再需添加");
//		}else{
//			System.out.println("该用户添加笔记本成功");
//			
//		}
//	}

}
