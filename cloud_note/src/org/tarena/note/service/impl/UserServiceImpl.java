package org.tarena.note.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.tarena.note.dao.UserMapperDao;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.entity.User;
import org.tarena.note.service.UserService;
import org.tarena.note.util.NoteUtil;

@Service("userService")//扫描
public class UserServiceImpl implements UserService{
	@Resource//注入
	private UserMapperDao userDao;//注入
	@Override
	public NoteResult checkLogin(String name, String password) {
		NoteResult result;
		User user = userDao.findByName(name);
		if(user==null){
			result=new NoteResult(1, "用户名不存在", null);
			return result;
		}
		//将用户输入的密码加密处理
		String md5_pwd=NoteUtil.md5(password);
		//将加密后的密码和数据库中加密的结果对比
		if(!md5_pwd.equals(user.getCn_user_password())){
			result=new NoteResult(2, "密码不正确", null);
			return result;
		}
		result=new NoteResult(0, "登录成功", user.getCn_user_id());
		return result;
	}
	@Override
	public NoteResult regist(String name, String password, String nick) {
		NoteResult result=null;
		//检测用户名是否唯一
		User has_user=userDao.findByName(name);
		if(has_user!=null){
			result=new NoteResult(1, "用户名已被占用", null);
			return result;
		}
		//保存
		User user=new User();
		user.setCn_user_name(name);//设置用户名
		String md5_pwd=NoteUtil.md5(password);
		user.setCn_user_password(md5_pwd);//设置密码
		user.setCn_user_desc(nick);//设置昵称
//		String uid=NoteUtil.creadId();
//		System.out.println(uid.length());
//		user.setCn_user_id(uid);//设置ID
		userDao.save(user);//写入cn_user表
		
		result=new NoteResult(0, "注册成功", null);
		
		return result;
	}

}
