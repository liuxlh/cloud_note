package org.tarena.note.test;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.UserService;

public class TestUserRegist {
	public UserService userService;
	@Before
	public void init(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		userService=ac.getBean("userService", UserService.class);
	}
	@Test
	public void test1(){
		NoteResult result=userService.regist("demo", "123", "大猫");
		//断言
		Assert.assertEquals(1, result.getStatus());
		Assert.assertEquals("用户名已被占用", result.getMsg());
		Assert.assertNull(result.getData());
	}
	
	@Test
	public void test2(){
		NoteResult result=userService.regist("demo2", "123", "大猫");
		//断言
		Assert.assertEquals(0, result.getStatus());
		Assert.assertEquals("注册成功", result.getMsg());
		Assert.assertNull(result.getData());
	}

}
