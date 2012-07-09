package data.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import data.bean.Product;
import data.bean.ScheduleTab;
import data.bean.mapping.ProductMapping;
import data.bean.mapping.ScheduleTabMapping;

public class ScheduleTabDAO {

	GenericTemplate genericTemplate;
	List<Object> value;
	String sql;
	int result;
	boolean flag;
	List<ScheduleTab> list;
	
	/**
	 * 构造函数
	 */
	public ScheduleTabDAO() {
		genericTemplate = new GenericTemplate();
		value = new ArrayList<Object>();
		result = 0;
		flag = false;
		list = new ArrayList<ScheduleTab>();
	}
	
	public int create(ScheduleTab entity) {
		// TODO Auto-generated method stub
		sql = "insert into scheduleTab values (?,?,?,?,?)";
		
		value.add(entity.getId());
		value.add(entity.getBatchId());
		value.add(entity.getTime());
		value.add(entity.getColorNo());
		value.add(entity.getNum());
		
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

	public int delete(ScheduleTab entity) {
		// TODO Auto-generated method stub
		sql = "delete from scheduleTab where Id=?";
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

	public int update(ScheduleTab entity, Integer pk) {
		// TODO Auto-generated method stub
		sql = "update scheduleTab set batchId=?,time=?,colorNo=?,num=? where Id=?";

		value.add(entity.getBatchId());
		value.add(entity.getTime());
		value.add(entity.getColorNo());
		value.add(entity.getNum());
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

	public List<ScheduleTab> read(ScheduleTab entity) {
		// TODO Auto-generated method stub
		ScheduleTabMapping scheduleTabMapping = new ScheduleTabMapping();
		ScheduleTab scheduleTab = null;
		ResultSet resultSet;
		sql = "select * from scheduleTab where Id=?";
		value.add(entity.getId());
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			//System.out.print(genericTemplate.executeQuery());
			scheduleTab = (ScheduleTab) scheduleTabMapping.mapping(resultSet);
			list.add(scheduleTab);
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			genericTemplate.close();
		}
		return list;
	}

	public List<ScheduleTab> readByPk(Integer pk) {
		// TODO Auto-generated method stub
		ScheduleTabMapping scheduleTabMapping = new ScheduleTabMapping();
		ScheduleTab scheduleTab = null;
		ResultSet resultSet;
		sql = "select * from scheduleTab where Id=?";
		value.add(pk);
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			scheduleTab = (ScheduleTab) scheduleTabMapping.mapping(resultSet);
			list.add(scheduleTab);
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
		sql = "select count(*) from scheduleTab";
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
