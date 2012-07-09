package data.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import data.bean.Batch;
import data.bean.StaffKind;

import data.bean.mapping.BatchMapping;
import data.bean.mapping.StaffKindMapping;
import data.util.GenericTemplate;

public class StaffKindDAO {

	GenericTemplate genericTemplate;
	List<Object> value;
	String sql;
	int result;
	boolean flag;
	List<StaffKind> list;
	
	/**
	 * 构造函数
	 */
	public StaffKindDAO() {
		genericTemplate = new GenericTemplate();
		value = new ArrayList<Object>();
		result = 0;
		flag = false;
		list = new ArrayList<StaffKind>();
	}
	
	public int create(StaffKind entity) {
		// TODO Auto-generated method stub
		sql = "insert into staffkind values (?,?,?,?)";
		
		value.add(entity.getId());		
		value.add(entity.getKindDesc());
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

	public int delete(StaffKind entity) {
		// TODO Auto-generated method stub
		sql = "update staffkind set isDelete=?,deleteTime=? where id=?";
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

	public int update(StaffKind entity) {
		// TODO Auto-generated method stub
		sql = "update staffkind set kindDesc=?,isDelete=?,deleteTime=? where id=?";
		
		value.add(entity.getKindDesc());
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

	public List<StaffKind> read(StaffKind entity) {
		// TODO Auto-generated method stub
		StaffKindMapping staffKindMapping = new StaffKindMapping();
		StaffKind staffKind = null;
		ResultSet resultSet;
		sql = "select * from staffkind where Id=?";
		value.add(entity.getId());
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			//System.out.print(genericTemplate.executeQuery());
			staffKind = (StaffKind) staffKindMapping.mapping(resultSet);
			list.add(staffKind);
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			genericTemplate.close();
		}
		return list;
	}

	public List<StaffKind> readByPk(Integer pk) {
		// TODO Auto-generated method stub
		StaffKindMapping staffKindMapping = new StaffKindMapping();
		StaffKind staffKind = null;
		ResultSet resultSet;
		sql = "select * from staffkind where Id=?";
		value.add(pk);
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			staffKind = (StaffKind)staffKindMapping.mapping(resultSet);
			list.add(staffKind);
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
		sql = "select count(*) from staffkind";
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
