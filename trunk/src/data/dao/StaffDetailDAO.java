package data.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import data.bean.Product;
import data.bean.StaffDetail;
import data.bean.mapping.ProductMapping;
import data.bean.mapping.StaffDetailMapping;
import data.util.GenericTemplate;

public class StaffDetailDAO {

	GenericTemplate genericTemplate;
	List<Object> value;
	String sql;
	int result;
	boolean flag;
	List<StaffDetail> list;
	
	/**
	 * 构造函数
	 */
	public StaffDetailDAO() {
		genericTemplate = new GenericTemplate();
		value = new ArrayList<Object>();
		result = 0;
		flag = false;
		list = new ArrayList<StaffDetail>();
	}
	
	public int create(StaffDetail entity) {
		// TODO Auto-generated method stub
		sql = "insert into staffDetail values (?,?,?,?,?,?,?,?,?)";
		
		value.add(entity.getId());
		value.add(entity.getStaffId());
		value.add(entity.getProName());
		value.add(entity.getProNo());
		value.add(entity.getProcName());
		value.add(entity.getQuaNum());
		value.add(entity.getgWaste());
		value.add(entity.getlWaste());
		value.add(entity.getWorkHours());
		
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

	public int delete(StaffDetail entity) {
		// TODO Auto-generated method stub
		sql = "delete from staffDetail where Id=?";
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

	public int update(StaffDetail entity, Integer pk) {
		// TODO Auto-generated method stub
		sql = "update staffDetail set staffId=?,proName=?,proNo=?,procName=?,quaNum=?," +
				"gWaste=?,lWaste=?,workHours=? where Id=?";

		value.add(entity.getStaffId());
		value.add(entity.getProName());
		value.add(entity.getProNo());
		value.add(entity.getProcName());
		value.add(entity.getQuaNum());
		value.add(entity.getgWaste());
		value.add(entity.getlWaste());
		value.add(entity.getWorkHours());
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

	public List<StaffDetail> read(StaffDetail entity) {
		// TODO Auto-generated method stub
		StaffDetailMapping staffDetailMapping = new StaffDetailMapping();
		StaffDetail staffDetail = null;
		ResultSet resultSet;
		sql = "select * from staffDetail where Id=?";
		value.add(entity.getId());
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			//System.out.print(genericTemplate.executeQuery());
			staffDetail = (StaffDetail) staffDetailMapping.mapping(resultSet);
			list.add(staffDetail);
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			genericTemplate.close();
		}
		return list;
	}

	public List<StaffDetail> readByPk(Integer pk) {
		// TODO Auto-generated method stub
		StaffDetailMapping staffDetailMapping = new StaffDetailMapping();
		StaffDetail staffDetail = null;
		ResultSet resultSet;
		sql = "select * from staffDetail where Id=?";
		value.add(pk);
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			staffDetail = (StaffDetail) staffDetailMapping.mapping(resultSet);
			list.add(staffDetail);
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
		sql = "select count(*) from staffDetail";
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
