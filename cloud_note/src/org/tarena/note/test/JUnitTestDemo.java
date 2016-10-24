package org.tarena.note.test;

import org.junit.*;

public class JUnitTestDemo {
	@BeforeClass//在测试类执行开始时只调用一次
	public static void myBefore(){
		System.out.println("---myBefore---");
	}
	
	
	@Before//在每个test执行之前调用一次
	public void init(){
		System.out.println("---myinit---");
	}
	@Test
	public void f1(){
		System.out.println("---f1---");
//		Assert.assertEquals(1, 0);//断言
	}
	@Test
	public void f2(){
		System.out.println("---f2---");
		String s=null;
		Assert.assertNotNull(s);
	}
	
	@After//在每个test执行之后调用一次
	public void destroy(){
		System.out.println("---mydestroy---");
	}
	
	@AfterClass//在测试类执行结尾时只调用一次
	public static void myAfter(){
		System.out.println("---myAfter---");
	}
}
