package data.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import data.bean.WorkTab;
import data.bean.mapping.WorkTabMapping;
import data.util.GenericQueryImpl;
import data.util.GenericTemplate;

public class WorkTabDAO extends GenericQueryImpl<WorkTab, WorkTabMapping> {

	//GenericTemplate genericTemplate;
	List<Object> value;
	String sql;
	int result;
	boolean flag;
	List<WorkTab> list;
	static WorkTabMapping workTabMapping = new WorkTabMapping();
	
	/**
	 * 构造函数
	 */
	public WorkTabDAO() {
		super(WorkTab.class, workTabMapping);
		//genericTemplate = new GenericTemplate();
		value = new ArrayList<Object>();
		result = 0;
		flag = false;
		list = new ArrayList<WorkTab>();
	}
	
	public int create(WorkTab entity) {
		// TODO Auto-generated method stub
		sql = "insert into workTab values (?,?,?,?,?,?,?)";
		
		value.add(entity.getId());
		value.add(entity.getProcId());
		value.add(entity.getQuNum());
		value.add(entity.getDisqNum());
		value.add(entity.getIsOver());
		value.add(entity.getOverTime());
		value.add(entity.getIsEnd());
		
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

	public int delete(WorkTab entity) {
		// TODO Auto-generated method stub
		sql = "delete from workTab where Id=?";
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

	public int update(WorkTab entity, Integer pk) {
		// TODO Auto-generated method stub
		sql = "update workTab set procId=?,quNum=?,disqNum=?,isOver=?,overTime=?,isEnd=? where Id=?";


		value.add(entity.getProcId());
		value.add(entity.getQuNum());
		value.add(entity.getDisqNum());
		value.add(entity.getIsOver());
		value.add(entity.getOverTime());
		value.add(entity.getIsEnd());
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

	public List<WorkTab> read(WorkTab entity) {
		// TODO Auto-generated method stub
		WorkTabMapping workTabMapping = new WorkTabMapping();
		WorkTab workTab = null;
		ResultSet resultSet;
		sql = "select * from workTab where Id=?";
		value.add(entity.getId());
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			//System.out.print(genericTemplate.executeQuery());
			workTab = workTabMapping.mapping(resultSet);
			list.add(workTab);
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			genericTemplate.close();
		}
		return list;
	}

	public List<WorkTab> readByPk(Integer pk) {
		// TODO Auto-generated method stub
		WorkTabMapping workTabMapping = new WorkTabMapping();
		WorkTab workTab = null;
		ResultSet resultSet;
		sql = "select * from workTab where Id=?";
		value.add(pk);
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			workTab = workTabMapping.mapping(resultSet);
			list.add(workTab);
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
		sql = "select count(*) from workTab";
		genericTemplate.setSqlValue(sql);
		try {
			resultSet = genericTemplate.executeQuery();
			if(resultSet.next()) {
				result = resultSet.getInt("count(*)");
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			genericTemplate.close();
		}
		return result;
	}
}
