package data.bean.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import data.bean.WorkHoursTab;

public class WorkHoursTabMapping extends BasicMapping<WorkHoursTab> {

	public WorkHoursTab mapping(ResultSet rs) {
		WorkHoursTab workHoursTab = new WorkHoursTab();
		try {
			workHoursTab.setId(rs.getInt("Id"));
			workHoursTab.setSalary(rs.getDouble("salary"));
			workHoursTab.setStaId(rs.getInt("staId"));
			workHoursTab.setTime(rs.getDate("time"));
			workHoursTab.setWorkHours(rs.getDouble("workHours"));
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return workHoursTab;
	}

}
