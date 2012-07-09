package data.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import data.bean.Batch;
import data.bean.Staff;

import data.bean.mapping.BatchMapping;
import data.bean.mapping.StaffMapping;
import data.util.GenericTemplate;

public class StaffDAO {

	GenericTemplate genericTemplate;
	List<Object> value;
	String sql;
	int result;
	boolean flag;
	List<Staff> list;
	
	/**
	 * 构造函数
	 */
	public StaffDAO() {
		genericTemplate = new GenericTemplate();
		value = new ArrayList<Object>();
		result = 0;
		flag = false;
		list = new ArrayList<Staff>();
	}
	
	public int create(Staff entity) {
		// TODO Auto-generated method stub
		sql = "insert into staff values (?,?,?,?,?,?,?)";
		
		value.add(entity.getId());
		value.add(entity.getDeptId());
		value.add(entity.getKindId());
		value.add(entity.getStaName());
		value.add(entity.getStaNo());
		value.add(entity.getIsDelete());
		value.add(entity.getDeleteTime());
		
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			result = genericTemplate.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			genericTemplate.close();
		}
		
		return result;
	}

	public int delete(Staff entity) {
		// TODO Auto-generated method stub
		sql = "update staff set isDelete=?,deleteTime=? where id=?";
		value.add(1);
		value.add(new Date());
		value.add(entity.getId());
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			result = genericTemplate.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			genericTemplate.close();
		}

		return result;
	}

	public int update(Staff entity) {
		// TODO Auto-generated method stub
		sql = "update staff set deptId=?,kindId=?,staName=?,staNo=?,isDelete=?,deleteTime=? where id=?";


		value.add(entity.getDeptId());
		value.add(entity.getKindId());
		value.add(entity.getStaName());
		value.add(entity.getStaNo());
		value.add(entity.getIsDelete());
		value.add(entity.getDeleteTime());
		value.add(entity.getId());
		
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			result = genericTemplate.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			genericTemplate.close();
		}
		return result;
	}

	public List<Staff> read(Staff entity) {
		// TODO Auto-generated method stub
		StaffMapping staffMapping = new StaffMapping();
		Staff staff = null;
		ResultSet resultSet;
		sql = "select * from staff where Id=?";
		value.add(entity.getId());
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			//System.out.print(genericTemplate.executeQuery());
			staff = (Staff) staffMapping.mapping(resultSet);
			list.add(staff);
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			genericTemplate.close();
		}
		return list;
	}

	public List<Staff> readByPk(Integer pk) {
		// TODO Auto-generated method stub
		StaffMapping staffMapping = new StaffMapping();
		Staff staff = null;
		ResultSet resultSet;
		sql = "select * from staff where Id=?";
		value.add(pk);
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			staff = (Staff)staffMapping.mapping(resultSet);
			list.add(staff);
		} catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			genericTemplate.close();
		}
		return list;
	}

	public int count() {
		// TODO Auto-generated method stub
		ResultSet resultSet;
		sql = "select count(*) from staff";
		genericTemplate.setSqlValue(sql);
		try {
			resultSet = genericTemplate.executeQuery();
			while(resultSet.next()) {
				result ++;
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			genericTemplate.close();
		}
		return result;
	}

}
