package data.bean.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import data.bean.Staff;

public class StaffMapping implements BeanMapping{

	public Staff mapping(ResultSet rs) throws SQLException {

		Staff Staff = new Staff();
		
		if(rs.next()) {			
			Staff.setId(rs.getInt("Id"));
			Staff.setDeptId(rs.getInt("deptId"));
			Staff.setKindId(rs.getInt("kindId"));
			Staff.setStaName(rs.getString("staName"));
			Staff.setStaNo(rs.getString("staNo"));			
			Staff.setIsDelete(rs.getInt("isDelete"));
			Staff.setDeleteTime(rs.getDate("deleteTime"));

		}
		
		return Staff;
	}

}
