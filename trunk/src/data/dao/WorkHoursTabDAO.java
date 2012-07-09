package data.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import data.bean.Product;
import data.bean.WorkHoursTab;
import data.bean.mapping.ProductMapping;
import data.bean.mapping.WorkHoursTabMapping;
import data.util.GenericTemplate;

public class WorkHoursTabDAO {

	GenericTemplate genericTemplate;
	List<Object> value;
	String sql;
	int result;
	boolean flag;
	List<WorkHoursTab> list;
	
	/**
	 * 构造函数
	 */
	public WorkHoursTabDAO() {
		genericTemplate = new GenericTemplate();
		value = new ArrayList<Object>();
		result = 0;
		flag = false;
		list = new ArrayList<WorkHoursTab>();
	}
	
	public int create(WorkHoursTab entity) {
		// TODO Auto-generated method stub
		sql = "insert into workHoursTab values (?,?,?,?,?)";
		
		value.add(entity.getId());
		value.add(entity.getStaId());
		value.add(entity.getTime());
		value.add(entity.getWorkHours());
		value.add(entity.getSalary());
		
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

	public int delete(WorkHoursTab entity) {
		// TODO Auto-generated method stub
		sql = "delete from workHoursTab where Id=?";
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

	public int update(WorkHoursTab entity, Integer pk) {
		// TODO Auto-generated method stub
		sql = "update workHoursTab set staId=?,time=?,workHours=?,salary=? where Id=?";

		value.add(entity.getStaId());
		value.add(entity.getTime());
		value.add(entity.getWorkHours());
		value.add(entity.getSalary());
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

	public List<WorkHoursTab> read(WorkHoursTab entity) {
		// TODO Auto-generated method stub
		WorkHoursTabMapping workHoursTabMapping = new WorkHoursTabMapping();
		WorkHoursTab workHoursTab = null;
		ResultSet resultSet;
		sql = "select * from workHoursTab where Id=?";
		value.add(entity.getId());
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			//System.out.print(genericTemplate.executeQuery());
			workHoursTab = (WorkHoursTab) workHoursTabMapping.mapping(resultSet);
			list.add(workHoursTab);
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			genericTemplate.close();
		}
		return list;
	}

	public List<WorkHoursTab> readByPk(Integer pk) {
		// TODO Auto-generated method stub
		WorkHoursTabMapping workHoursTabMapping = new WorkHoursTabMapping();
		WorkHoursTab workHoursTab = null;
		ResultSet resultSet;
		sql = "select * from workHoursTab where Id=?";
		value.add(pk);
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			workHoursTab = (WorkHoursTab) workHoursTabMapping.mapping(resultSet);
			list.add(workHoursTab);
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
		sql = "select count(*) from workHoursTab";
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
