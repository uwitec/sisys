package data.service;

import java.sql.SQLException;
import data.bean.User;
import data.dao.UserDAO;

public class LoginService {
	private UserDAO userDAO = new UserDAO();
	
	public User checkLogin(String username,String password,Integer level) throws SQLException{
		User user = null;
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
		User user = new User();
		LoginService loginService = new LoginService();
		user = loginService.checkLogin("admin", "admin", 3);
		System.out.println(user.getUsername());
	}
}
