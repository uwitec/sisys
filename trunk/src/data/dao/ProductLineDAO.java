package data.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import data.bean.Product;
import data.bean.ProductLine;
import data.bean.mapping.ProductLineMapping;
import data.bean.mapping.ProductMapping;
import data.util.GenericTemplate;

public class ProductLineDAO {

	GenericTemplate genericTemplate;
	List<Object> value;
	String sql;
	int result;
	boolean flag;
	List<ProductLine> list;
	
	/**
	 * 构造函数
	 */
	public ProductLineDAO() {
		genericTemplate = new GenericTemplate();
		value = new ArrayList<Object>();
		result = 0;
		flag = false;
		list = new ArrayList<ProductLine>();
	}
	
	public int create(ProductLine entity) {
		// TODO Auto-generated method stub
		sql = "insert into productLine values (?,?,?,?)";
		
		value.add(entity.getId());
		value.add(entity.getLineDesc());
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

	public int delete(ProductLine entity) {
		// TODO Auto-generated method stub
		sql = "update productline set isDelete=?,deleteTime=? where id=?";
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

	public int update(ProductLine entity, Integer pk) {
		// TODO Auto-generated method stub
		sql = "update productLine set lineDesc=?,isDelete=?,deleteTime=? where Id=?";

		value.add(entity.getLineDesc());
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

	public List<ProductLine> read(ProductLine entity) {
		// TODO Auto-generated method stub
		ProductLineMapping productLineMapping = new ProductLineMapping();
		ProductLine productLine = null;
		ResultSet resultSet;
		sql = "select * from productLine where Id=?";
		value.add(entity.getId());
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			//System.out.print(genericTemplate.executeQuery());
			productLine = (ProductLine) productLineMapping.mapping(resultSet);
			list.add(productLine);
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			genericTemplate.close();
		}
		return list;
	}

	public List<ProductLine> readByPk(Integer pk) {
		// TODO Auto-generated method stub
		ProductLineMapping productLineMapping = new ProductLineMapping();
		ProductLine productLine = null;
		ResultSet resultSet;
		sql = "select * from productLine where Id=?";
		value.add(pk);
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			productLine = (ProductLine) productLineMapping.mapping(resultSet);
			list.add(productLine);
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
		sql = "select count(*) from productLine";
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
