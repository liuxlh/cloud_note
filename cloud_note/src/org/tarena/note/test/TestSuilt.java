package org.tarena.note.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)//批量调用多个test
@SuiteClasses({JUnitTestDemo.class,TestUserService.class})
public class TestSuilt {
	
}
