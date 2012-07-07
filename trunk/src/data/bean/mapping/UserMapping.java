package data.bean.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import data.bean.UserBean;

public class UserMapping implements BeanMapping{

	public UserBean mapping(ResultSet rs) throws SQLException {
		UserBean user = new UserBean();
		
		user.setId(rs.getInt("id"));
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setLevel(rs.getInt("level"));
		
		return user;
	}

}
