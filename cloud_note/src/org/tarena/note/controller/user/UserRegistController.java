package org.tarena.note.controller.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.UserService;

@Controller
@RequestMapping("/user")
public class UserRegistController {
	@Resource
	private UserService userService;
	@RequestMapping("/regist.do")
	@ResponseBody //将返回值变成json输出
	public NoteResult execute(String name,String password,String nick){
		NoteResult result=userService.regist(name, password, nick);
		return result;
		
	}
}
