package data.bean.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import data.bean.DisqDetail;

public class DisqDetailMapping implements BeanMapping {

	public Object mapping(ResultSet rs) throws SQLException {
		
		DisqDetail disqDetail = new DisqDetail();
		if(rs.next()) {
			disqDetail.setDisKId(rs.getInt("disKId"));
			disqDetail.setId(rs.getInt("Id"));
			disqDetail.setNextId(rs.getInt("nextId"));
			disqDetail.setNum(rs.getInt("num"));
		}
		return disqDetail;
		
	}

}
