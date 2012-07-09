package data.bean.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import data.bean.ProductLine;

public class ProductLineMapping implements BeanMapping {

	public Object mapping(ResultSet rs) throws SQLException {
		
		ProductLine productLine = new ProductLine();
		if(rs.next()) {
			productLine.setDeleteTime(rs.getDate("deleteTime"));
			productLine.setId(rs.getInt("Id"));
			productLine.setIsDelete(rs.getInt("isDelete"));
			productLine.setLineDesc(rs.getString("lineDesc"));
		}
		return productLine;
	}

}
