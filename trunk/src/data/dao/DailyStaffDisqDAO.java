package data.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import data.bean.Batch;
import data.bean.DailyStaffDisq;
import data.bean.mapping.BatchMapping;
import data.bean.mapping.DailyStaffDisqMapping;

public class DailyStaffDisqDAO {


	GenericTemplate genericTemplate;
	List<Object> value;
	String sql;
	int result;
	boolean flag;
	List<DailyStaffDisq> list;
	
	/**
	 * 构造函数
	 */
	public DailyStaffDisqDAO() {
		genericTemplate = new GenericTemplate();
		value = new ArrayList<Object>();
		result = 0;
		flag = false;
		list = new ArrayList<DailyStaffDisq>();
	}
	
	public int create(DailyStaffDisq entity) {
		// TODO Auto-generated method stub
		sql = "insert into dailyStaffDisq values (?,?,?,?,?)";
		
		value.add(entity.getId());
		value.add(entity.getDisqdeId());
		value.add(entity.getStaffId());
		value.add(entity.getTotalNum());
		value.add(entity.getTime());
		
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

	public int delete(DailyStaffDisq entity) {
		// TODO Auto-generated method stub
		sql = "delete from batch where Id=?";
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

	public int update(DailyStaffDisq entity, Integer pk) {
		// TODO Auto-generated method stub
		sql = "update batch set disqdeId=?,staffId=?,totalNum=?,time=? where Id=?";

		value.add(entity.getDisqdeId());
		value.add(entity.getStaffId());
		value.add(entity.getTotalNum());
		value.add(entity.getTime());		
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

	public List<DailyStaffDisq> read(DailyStaffDisq entity) {
		// TODO Auto-generated method stub
		DailyStaffDisqMapping dailyStaffDisqMapping = new DailyStaffDisqMapping();
		DailyStaffDisq dailyStaffDisq = null;
		ResultSet resultSet;
		sql = "select * from dailyStaffDisq where Id=?";
		value.add(entity.getId());
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			//System.out.print(genericTemplate.executeQuery());
			dailyStaffDisq = (DailyStaffDisq) dailyStaffDisqMapping.mapping(resultSet);
			list.add(dailyStaffDisq);
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			genericTemplate.close();
		}
		return list;
	}

	public List<DailyStaffDisq> readByPk(Integer pk) {
		// TODO Auto-generated method stub
		DailyStaffDisqMapping dailyStaffDisqMapping = new DailyStaffDisqMapping();
		DailyStaffDisq dailyStaffDisq = null;
		ResultSet resultSet;
		sql = "select * from dailyStaffDisq where Id=?";
		value.add(pk);
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			dailyStaffDisq = (DailyStaffDisq) dailyStaffDisqMapping.mapping(resultSet);
			list.add(dailyStaffDisq);
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
		sql = "select count(*) from dailyStaffDisq";
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
