package data.service;

import java.sql.SQLException;
import java.util.List;

import data.bean.UserBean;
import data.dao.UserDAO;

public class LoginService {
	private UserDAO userDAO = new UserDAO();
	
	public UserBean checkLogin(String username,String password,Integer level) throws SQLException{
		UserBean user = null;
		/*List<Object> list = userDAO.queryByName(username);
		if(list.size() > 0){
			user = (UserBean)list.get(0);
			if(user.getPassword().equals(password)&&user.getLevel().equals(level)){
				return user;
			}
		}*/
		return null;
	}
	
	public static void main(String[] args) throws SQLException{
		UserBean user = new UserBean();
		LoginService loginService = new LoginService();
		user = loginService.checkLogin("admin", "admin", 3);
		System.out.println(user.getUsername());
	}
}