package data.bean.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import data.bean.DisqDetail;

public class DisqDetailMapping extends BasicMapping<DisqDetail> {

	@Override
	public DisqDetail mapping(ResultSet rs) {
		
		DisqDetail disqDetail = new DisqDetail();
		try {
			disqDetail.setDisKId(rs.getInt("disKId"));
			disqDetail.setId(rs.getInt("Id"));
			disqDetail.setNextId(rs.getInt("nextId"));
			disqDetail.setNum(rs.getInt("num"));
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return disqDetail;
		
	}

}
