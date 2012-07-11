package data.dao;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.bean.Batch;
import data.bean.User;
import data.util.GenericTemplate;

public class ExportorDAO {
	GenericTemplate genericTemplate;
	List<Object> value;
	String sql;
	int result;
	boolean flag;
	List<Batch> list;
	
	/**
	 * 构造函数
	 */
	public ExportorDAO() {
		genericTemplate = new GenericTemplate();
		value = new ArrayList<Object>();
		result = 0;
		flag = false;
		list = new ArrayList<Batch>();
	}
	
	public void query() throws SQLException{
		ResultSet rs = null;
		sql = "select * from user;";
		genericTemplate.setSqlValue(sql);
		try {
			rs = genericTemplate.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(rs.next()){
			System.out.println(rs.getInt("Id"));
		}
	}
	
	public static void main(String[] args) throws SQLException {
//		ExportorDAO ed = new ExportorDAO();
//		ed.query();
		User user = new User();
		Field[] fields = user.getClass().getDeclaredFields();
		for(int i = 0; i < fields.length;i++){
			System.out.println(fields[i].getName());
		}
	}
}
