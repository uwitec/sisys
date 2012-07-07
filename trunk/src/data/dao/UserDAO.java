package data.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.bean.UserBean;
import data.bean.mapping.BeanMapping;
import data.bean.mapping.UserMapping;
import data.connect.GenericTemplate;

public class UserDAO {
	private GenericTemplate genericTemplate = new GenericTemplate();
	private BeanMapping mapping = new UserMapping();
	
	public List<Object> queryByName(String username) throws SQLException{
		List<Object> resultList = new ArrayList<Object>();
		
		String sqlValue = "select * from user where username=?";
		List<Object> values = new ArrayList<Object>();
		values.add(username);
		
		genericTemplate.setSqlValue(sqlValue);
		genericTemplate.setValues(values);
		genericTemplate.setMapping(mapping);
		
		resultList = genericTemplate.executeQuery();
		
		return resultList;
	}
	
	public static void main(String[] args) throws SQLException{
		UserBean user = new UserBean();
		UserDAO userDAO = new UserDAO();
		user = (UserBean)userDAO.queryByName("admin").get(0);
		System.out.println(user.getPassword());
	}
}
