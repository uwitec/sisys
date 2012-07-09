package data.bean.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import data.bean.ScheduleTab;

public class ScheduleTabMapping implements BeanMapping {

	public Object mapping(ResultSet rs) throws SQLException {
		
		ScheduleTab scheduleTab = new ScheduleTab();
		if(rs.next()) {
			scheduleTab.setBatchId(rs.getInt("batchId"));
			scheduleTab.setColorNo(rs.getString("colorNo"));
			scheduleTab.setId(rs.getInt("Id"));
			scheduleTab.setNum(rs.getInt("num"));
			scheduleTab.setTime(rs.getDate("time"));
		}
		return scheduleTab;
		
	}

}
