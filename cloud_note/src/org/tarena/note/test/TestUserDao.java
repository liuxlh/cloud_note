package org.tarena.note.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.dao.UserMapperDao;
import org.tarena.note.entity.User;

public class TestUserDao {
	public static void main(String[] args) {
		String conf="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		UserMapperDao userDao=ac.getBean("userMapperDao", UserMapperDao.class);
		User user=userDao.findByName("demo");
		if(user==null){
			System.out.println("用户不存在");
		}else{
			System.out.println(user);
		}
	}
}
