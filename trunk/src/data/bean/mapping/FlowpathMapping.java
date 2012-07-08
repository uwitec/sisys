package data.bean.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import data.bean.Flowpath;

public class FlowpathMapping implements BeanMapping {

	public Object mapping(ResultSet rs) throws SQLException {

		Flowpath flowpath = new Flowpath();
		if(rs.next()) {
			flowpath.setDeleteTime(rs.getDate("deleteTime"));
			flowpath.setId(rs.getInt("Id"));
			flowpath.setIsDelete(rs.getInt("isDelete"));
			flowpath.setProId(rs.getInt("proId"));
			flowpath.setSequence(rs.getString("sequence"));
		}
		return flowpath;
	}

}
