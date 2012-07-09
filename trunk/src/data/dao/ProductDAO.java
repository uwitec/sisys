package data.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import data.bean.Processes;
import data.bean.Product;
import data.bean.mapping.ProcessesMapping;
import data.bean.mapping.ProductMapping;

public class ProductDAO {

	GenericTemplate genericTemplate;
	List<Object> value;
	String sql;
	int result;
	boolean flag;
	List<Product> list;
	
	/**
	 * 构造函数
	 */
	public ProductDAO() {
		genericTemplate = new GenericTemplate();
		value = new ArrayList<Object>();
		result = 0;
		flag = false;
		list = new ArrayList<Product>();
	}
	
	public int create(Product entity) {
		// TODO Auto-generated method stub
		sql = "insert into product values (?,?,?,?,?,?,?,?,?,?,?)";
		
		value.add(entity.getId());
		value.add(entity.getDeptId());
		value.add(entity.getProlineId());
		value.add(entity.getProNo());
		value.add(entity.getProName());
		value.add(entity.getTime());
		value.add(entity.getDisqNum());
		value.add(entity.getDisqPerc());
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

	public int delete(Product entity) {
		// TODO Auto-generated method stub
		sql = "delete from product where Id=?";
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

	public int update(Product entity, Integer pk) {
		// TODO Auto-generated method stub
		sql = "update product set deptId=?,prolineId=?,proNo=?,proName=?,time=?," +
				"disqNum=?,disqPerc=?,totalNum=?,isDelete=?,deleteTime=? where Id=?";

		value.add(entity.getDeptId());
		value.add(entity.getProlineId());
		value.add(entity.getProNo());
		value.add(entity.getProName());
		value.add(entity.getTime());
		value.add(entity.getDisqNum());
		value.add(entity.getDisqPerc());
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

	public List<Product> read(Product entity) {
		// TODO Auto-generated method stub
		ProductMapping productMapping = new ProductMapping();
		Product product = null;
		ResultSet resultSet;
		sql = "select * from product where Id=?";
		value.add(entity.getId());
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			//System.out.print(genericTemplate.executeQuery());
			product = (Product) productMapping.mapping(resultSet);
			list.add(product);
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			genericTemplate.close();
		}
		return list;
	}

	public List<Product> readByPk(Integer pk) {
		// TODO Auto-generated method stub
		ProductMapping productMapping = new ProductMapping();
		Product product = null;
		ResultSet resultSet;
		sql = "select * from product where Id=?";
		value.add(pk);
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			product = (Product) productMapping.mapping(resultSet);
			list.add(product);
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
		sql = "select count(*) from product";
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
