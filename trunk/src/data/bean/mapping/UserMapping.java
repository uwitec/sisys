package data.bean.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import data.bean.User;

public class UserMapping implements BeanMapping{

	public User mapping(ResultSet rs) throws SQLException {

		User user = new User();
		
		if(rs.next()) {			
			user.setId(rs.getInt("Id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setIsDelete(rs.getInt("isDelete"));
			user.setDeleteTime(rs.getDate("deleteTime"));
			user.setLevel(rs.getInt("level"));
		}
		
		return user;
	}

}
