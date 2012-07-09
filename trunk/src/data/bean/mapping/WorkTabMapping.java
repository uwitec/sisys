package data.bean.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import data.bean.WorkTab;

public class WorkTabMapping implements BeanMapping {

	public Object mapping(ResultSet rs) throws SQLException {

		WorkTab workTab = new WorkTab();
		if(rs.next()) {
			workTab.setDisqNum(rs.getInt("disqNum"));
			workTab.setId(rs.getInt("Id"));
			workTab.setIsEnd(rs.getInt("isEnd"));
			workTab.setIsOver(rs.getInt("isOver"));
			workTab.setOverTime(rs.getDate("overTime"));
			workTab.setProcId(rs.getInt("procId"));
			workTab.setQuNum(rs.getInt("quNum"));
		}
		return workTab;
	}

}
