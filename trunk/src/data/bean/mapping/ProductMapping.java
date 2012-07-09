package data.bean.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import data.bean.Product;

public class ProductMapping extends BasicMapping<Product> {

	public Product mapping(ResultSet rs) {
		
		Product product = new Product();		
		try {
			product.setDeleteTime(rs.getDate("deleteTime"));
			product.setDeptId(rs.getInt("deptId"));
			product.setDisqNum(rs.getInt("disqNum"));
			product.setDisqPerc(rs.getDouble("disqPerc"));
			product.setId(rs.getInt("Id"));
			product.setIsDelete(rs.getInt("isDelete"));
			product.setProlineId(rs.getInt("prolineId"));
			product.setProName(rs.getString("proName"));
			product.setProNo(rs.getString("proNo"));
			product.setTime(rs.getDate("time"));
			product.setTotalNum(rs.getInt("totalNum"));
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return product;
	}

}
