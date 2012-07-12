package data.bean.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import data.bean.ScheduleTab;

public class ScheduleTabMapping extends BasicMapping<ScheduleTab> {

	public ScheduleTab mapping(ResultSet rs) {
		
		ScheduleTab scheduleTab = new ScheduleTab();
		try {
			scheduleTab.setBatchId(rs.getInt("batchId"));
			scheduleTab.setColorNo(rs.getString("colorNo"));
			scheduleTab.setId(rs.getInt("Id"));
			scheduleTab.setNum(rs.getInt("num"));
			scheduleTab.setTime(rs.getDate("time"));
			scheduleTab.setWtId(rs.getInt("wtId"));
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return scheduleTab;
		
	}

}
