package data.bean.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import data.bean.StaffDetail;

public class StaffDetailMapping extends BasicMapping<StaffDetail> {

	public StaffDetail mapping(ResultSet rs) {

		StaffDetail staffDetail = new StaffDetail();
		try {
			staffDetail.setlWaste(rs.getInt("lWaste"));
			staffDetail.setgWaste(rs.getInt("gWaste"));
			staffDetail.setId(rs.getInt("Id"));
			staffDetail.setProcName(rs.getString("procName"));
			staffDetail.setProName(rs.getString("proName"));
			staffDetail.setProNo(rs.getString("proNo"));
			staffDetail.setQuaNum(rs.getInt("quaNum"));
			staffDetail.setStaffId(rs.getInt("staffId"));
			staffDetail.setWorkHours(rs.getDouble("workHours"));
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return staffDetail;
	}

}
