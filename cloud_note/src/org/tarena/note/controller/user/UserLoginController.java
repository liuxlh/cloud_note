package org.tarena.note.controller.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.UserService;

@Controller//扫描
@RequestMapping("/user")
public class UserLoginController {
	@Resource//注入
	private UserService userService;
	
	@RequestMapping("/login.do")
	@ResponseBody//将返回值变成json输出
	public NoteResult execute(String name,String password,ModelMap model){
		//接收请求调用Service业务组件处理
		NoteResult result=userService.checkLogin(name, password);
		return result;
	}

}
