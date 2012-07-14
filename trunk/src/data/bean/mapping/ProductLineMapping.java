package data.bean.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import data.bean.ProductLine;

public class ProductLineMapping extends BasicMapping<ProductLine> {

	@Override
	public ProductLine mapping(ResultSet rs) {
		
		ProductLine productLine = new ProductLine();
		try {
			productLine.setLineNo(rs.getInt("lineNo"));
			productLine.setDeleteTime(rs.getDate("deleteTime"));
			productLine.setId(rs.getInt("Id"));
			productLine.setIsDelete(rs.getInt("isDelete"));
			productLine.setLineDesc(rs.getString("lineDesc"));
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return productLine;
	}

}
