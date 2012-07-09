package data.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import data.bean.DisqKind;
import data.bean.Flowpath;
import data.bean.mapping.DisqKindMapping;
import data.bean.mapping.FlowpathMapping;

public class FlowpathDAO {

	GenericTemplate genericTemplate;
	List<Object> value;
	String sql;
	int result;
	boolean flag;
	List<Flowpath> list;
	
	/**
	 * 构造函数
	 */
	public FlowpathDAO() {
		genericTemplate = new GenericTemplate();
		value = new ArrayList<Object>();
		result = 0;
		flag = false;
		list = new ArrayList<Flowpath>();
	}
	
	public int create(Flowpath entity) {
		// TODO Auto-generated method stub
		sql = "insert into flowpath values (?,?,?,?,?)";
		
		value.add(entity.getId());
		value.add(entity.getSequence());
		value.add(entity.getProId());
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

	public int delete(Flowpath entity) {
		// TODO Auto-generated method stub
		sql = "update flowpath set isDelete=?,deleteTime=? where id=?";
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

	public int update(Flowpath entity, Integer pk) {
		// TODO Auto-generated method stub
		sql = "update flowpath set sequence=?,proId=?,isDelete=?,deleteTime=? where Id=?";

		value.add(entity.getSequence());
		value.add(entity.getProId());
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

	public List<Flowpath> read(Flowpath entity) {
		// TODO Auto-generated method stub
		FlowpathMapping flowpathMapping = new FlowpathMapping();
		Flowpath flowpath = null;
		ResultSet resultSet;
		sql = "select * from flowpath where Id=?";
		value.add(entity.getId());
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			//System.out.print(genericTemplate.executeQuery());
			flowpath = (Flowpath) flowpathMapping.mapping(resultSet);
			list.add(flowpath);
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			genericTemplate.close();
		}
		return list;
	}

	public List<Flowpath> readByPk(Integer pk) {
		// TODO Auto-generated method stub
		FlowpathMapping flowpathMapping = new FlowpathMapping();
		Flowpath flowpath = null;
		ResultSet resultSet;
		sql = "select * from flowpath where Id=?";
		value.add(pk);
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			flowpath = (Flowpath) flowpathMapping.mapping(resultSet);
			list.add(flowpath);
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
		sql = "select count(*) from flowpath";
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
