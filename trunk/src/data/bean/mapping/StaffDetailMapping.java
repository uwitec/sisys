package data.bean.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import data.bean.staffDetail;

public class StaffDetailMapping implements BeanMapping {

	public Object mapping(ResultSet rs) throws SQLException {

		staffDetail staffDetail = new staffDetail();
		return null;
	}

}
