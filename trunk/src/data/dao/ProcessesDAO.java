package data.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import data.bean.Flowpath;
import data.bean.Processes;
import data.bean.mapping.FlowpathMapping;
import data.bean.mapping.ProcessesMapping;

public class ProcessesDAO {

	GenericTemplate genericTemplate;
	List<Object> value;
	String sql;
	int result;
	boolean flag;
	List<Processes> list;
	
	/**
	 * 构造函数
	 */
	public ProcessesDAO() {
		genericTemplate = new GenericTemplate();
		value = new ArrayList<Object>();
		result = 0;
		flag = false;
		list = new ArrayList<Processes>();
	}
	
	public int create(Processes entity) {
		// TODO Auto-generated method stub
		sql = "insert into process values (?,?,?,?,?,?,?,?)";
		
		value.add(entity.getId());
		value.add(entity.getProcName());
		value.add(entity.getColorNo());
		value.add(entity.getProcNo());
		value.add(entity.getUnitOutput());
		value.add(entity.getUnitCost());
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

	public int delete(Processes entity) {
		// TODO Auto-generated method stub
		sql = "delete from process where Id=?";
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

	public int update(Processes entity, Integer pk) {
		// TODO Auto-generated method stub
		sql = "update process set procName=?,colorNo=?,procNo=?,unitOutput=?,unitCost=?," +
				"isDelete=?,deleteTime=? where Id=?";

		value.add(entity.getProcName());
		value.add(entity.getColorNo());
		value.add(entity.getProcNo());
		value.add(entity.getUnitOutput());
		value.add(entity.getUnitCost());
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

	public List<Processes> read(Processes entity) {
		// TODO Auto-generated method stub
		ProcessesMapping processesMapping = new ProcessesMapping();
		Processes processes = null;
		ResultSet resultSet;
		sql = "select * from process where Id=?";
		value.add(entity.getId());
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			//System.out.print(genericTemplate.executeQuery());
			processes = (Processes) processesMapping.mapping(resultSet);
			list.add(processes);
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			genericTemplate.close();
		}
		return list;
	}

	public List<Processes> readByPk(Integer pk) {
		// TODO Auto-generated method stub
		ProcessesMapping processesMapping = new ProcessesMapping();
		Processes processes = null;
		ResultSet resultSet;
		sql = "select * from process where Id=?";
		value.add(pk);
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			processes = (Processes) processesMapping.mapping(resultSet);
			list.add(processes);
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
		sql = "select count(*) from process";
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
