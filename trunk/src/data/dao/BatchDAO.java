package data.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import data.bean.Batch;
import data.bean.mapping.BatchMapping;

public class BatchDAO {

	GenericTemplate genericTemplate;
	List<Object> value;
	String sql;
	int result;
	boolean flag;
	List<Batch> list;
	
	/**
	 * 构造函数
	 */
	public BatchDAO() {
		genericTemplate = new GenericTemplate();
		value = new ArrayList<Object>();
		result = 0;
		flag = false;
		list = new ArrayList<Batch>();
	}
	
	public int create(Batch entity) {
		// TODO Auto-generated method stub
		sql = "insert into batch values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		value.add(entity.getId());
		value.add(entity.getBatchNo());
		value.add(entity.getFlowId());
		value.add(entity.getProId());
		value.add(entity.getWorkTabId());
		value.add(entity.getStatus());
		value.add(entity.getStartTime());
		value.add(entity.getEndTime());
		value.add(entity.getDisqNum());
		value.add(entity.getDisqPercent());
		value.add(entity.getTotalNum());
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

	public int delete(Batch entity) {
		// TODO Auto-generated method stub
		sql = "update batch set isDelete=?,deleteTime=? where id=?";
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

	public int update(Batch entity, Integer pk) {
		// TODO Auto-generated method stub
		sql = "update batch set batchNo=?,flowId=?,proId=?,workTabId=?,status=?,startTime=?,endTime=?,disqNum=?," +
				"disqPercent=?,totalNum=?,isDelete=?,deleteTime=? where id=?";

		value.add(entity.getBatchNo());
		value.add(entity.getFlowId());
		value.add(entity.getProId());
		value.add(entity.getWorkTabId());
		value.add(entity.getStatus());
		value.add(entity.getStartTime());
		value.add(entity.getEndTime());
		value.add(entity.getDisqNum());
		value.add(entity.getDisqPercent());
		value.add(entity.getTotalNum());
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

	public List<Batch> read(Batch entity) {
		// TODO Auto-generated method stub
		BatchMapping batchMapping = new BatchMapping();
		Batch batch = null;
		ResultSet resultSet;
		sql = "select * from batch where id=?";
		value.add(entity.getId());
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			//System.out.print(genericTemplate.executeQuery());
			batch = (Batch) batchMapping.mapping(resultSet);
			list.add(batch);
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			genericTemplate.close();
		}
		return list;
	}

	public List<Batch> readByPk(Integer pk) {
		// TODO Auto-generated method stub
		BatchMapping batchMapping = new BatchMapping();
		Batch batch = null;
		ResultSet resultSet;
		sql = "select * from batch where id=?";
		value.add(pk);
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			batch = (Batch) batchMapping.mapping(resultSet);
			list.add(batch);
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
		sql = "select count(*) from batch";
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
