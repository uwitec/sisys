package data.bean.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import data.bean.WorkHoursTab;

public class WorkHoursTabMapping implements BeanMapping {

	public Object mapping(ResultSet rs) throws SQLException {
		WorkHoursTab workHoursTab = new WorkHoursTab();
		if(rs.next()) {
			workHoursTab.setId(rs.getInt("Id"));
			workHoursTab.setSalary(rs.getDouble("salary"));
			workHoursTab.setStaId(rs.getInt("staId"));
			workHoursTab.setTime(rs.getDate("time"));
			workHoursTab.setWorkHours(rs.getDouble("workHours"));
		}
		return workHoursTab;
	}

}
