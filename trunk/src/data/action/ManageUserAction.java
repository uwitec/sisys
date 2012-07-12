package data.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.bean.User;
import data.dao.UserDAO;
import data.service.ManageUserService;

public class ManageUserAction extends BaseAction{
	
	private ManageUserService mus = new ManageUserService();
	
	private User user = new User();
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public String execute(){
		return null;
	}
	
	//登录
	public String login() {
		return mus.login(user);
		
	}
	
	//增加用户
	public String add() {
		return mus.add(user);
	}
	
	//修改个人信息
	public String modify() {
		return mus.modify(user);
	}
}
