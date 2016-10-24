package org.tarena.note.test;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.dao.NoteMapperDao;
import org.tarena.note.entity.Note;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.NoteService;

public class TestNoteDao {
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
//	@Test
//	public void test1(){
//		List<Map<String, String>> notes=noteDao.findByNoteBookId("d0b0727f-a233-4a1f-8600-f49fc1f25bc9");
//		System.out.println("test1");
//		for (Map<String, String> map : notes) {
//			System.out.println(map.get("cn_note_title"));
//			
//		}
//	}
	
//	@Test
//	public void test2(){
//		NoteResult result=noteService.findNotes("d0b0727f-a233-4a1f-8600-f49fc1f25bc9");
//		System.out.println("test2");
//		List<Map<String, String>> notes=(List<Map<String, String>>) result.getData();
//		for (Map<String, String> note : notes) {
//			System.out.println(note.get("cn_note_title"));
//		}
//	}
	
	@Test
	public void test3(){
		Note note=new Note();
		note.setCn_user_id("39295a3d-cc9b-42b4-b206-a2e7fab7e77c");
		note.setCn_notebook_id("4b86d1f9-6345-4532-bc50-ee86442f004b");
		note.setCn_note_id("019cd9e1-b629-4d8d-afd7-2aa9e2d6afe0");
		note.setCn_note_title("默认11");
		note.setCn_note_body("<p>1111阿萨德飞洒地方撒的发生大芬撒地方阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬<br/></p>");
		noteDao.updateNote(note);
		
	}

}
