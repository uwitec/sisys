package data.bean.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import data.bean.StaffDetail;

public class StaffDetailMapping implements BeanMapping {

	public Object mapping(ResultSet rs) throws SQLException {

		StaffDetail staffDetail = new StaffDetail();
		if(rs.next()) {
			staffDetail.setlWaste(rs.getInt("lWaste"));
			staffDetail.setgWaste(rs.getInt("gWaste"));
			staffDetail.setId(rs.getInt("Id"));
			staffDetail.setProcName(rs.getString("procName"));
			staffDetail.setProName(rs.getString("proName"));
			staffDetail.setProNo(rs.getString("proNo"));
			staffDetail.setQuaNum(rs.getInt("quaNum"));
			staffDetail.setStaffId(rs.getInt("staffId"));
			staffDetail.setWorkHours(rs.getDouble("workHours"));
		}
		return staffDetail;
	}

}
