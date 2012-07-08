package data.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import data.bean.DisqDetail;
import data.bean.DisqKind;
import data.bean.mapping.DisqDetailMapping;
import data.bean.mapping.DisqKindMapping;

public class DisqKindDAO {

	GenericTemplate genericTemplate;
	List<Object> value;
	String sql;
	int result;
	boolean flag;
	List<DisqKind> list;
	
	/**
	 * 构造函数
	 */
	public DisqKindDAO() {
		genericTemplate = new GenericTemplate();
		value = new ArrayList<Object>();
		result = 0;
		flag = false;
		list = new ArrayList<DisqKind>();
	}
	
	public int create(DisqKind entity) {
		// TODO Auto-generated method stub
		sql = "insert into disqKind values (?,?,?,?,?)";
		
		value.add(entity.getId());
		value.add(entity.getDisDesc());
		value.add(entity.getKind());
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

	public int delete(DisqKind entity) {
		// TODO Auto-generated method stub
		sql = "delete from disqKind where Id=?";
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

	public int update(DisqKind entity, Integer pk) {
		// TODO Auto-generated method stub
		sql = "update disqKind set disDesc=?,kind=?,isDelete=?,deleteTime=? where Id=?";

		value.add(entity.getDisDesc());
		value.add(entity.getKind());
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

	public List<DisqKind> read(DisqKind entity) {
		// TODO Auto-generated method stub
		DisqKindMapping disqKindMapping = new DisqKindMapping();
		DisqKind disqKind = null;
		ResultSet resultSet;
		sql = "select * from disqKind where Id=?";
		value.add(entity.getId());
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			//System.out.print(genericTemplate.executeQuery());
			disqKind = (DisqKind) disqKindMapping.mapping(resultSet);
			list.add(disqKind);
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			genericTemplate.close();
		}
		return list;
	}

	public List<DisqKind> readByPk(Integer pk) {
		// TODO Auto-generated method stub
		DisqKindMapping disqKindMapping = new DisqKindMapping();
		DisqKind disqKind = null;
		ResultSet resultSet;
		sql = "select * from disqKind where Id=?";
		value.add(pk);
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			disqKind = (DisqKind) disqKindMapping.mapping(resultSet);
			list.add(disqKind);
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
		sql = "select count(*) from disqKind";
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
