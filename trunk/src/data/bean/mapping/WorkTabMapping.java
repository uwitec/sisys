package data.bean.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import data.bean.WorkTab;

public class WorkTabMapping extends BasicMapping<WorkTab> {

	public WorkTab mapping(ResultSet rs) {

		WorkTab workTab = new WorkTab();
		try {
			workTab.setDisqNum(rs.getInt("disqNum"));
			workTab.setId(rs.getInt("Id"));
			workTab.setIsEnd(rs.getInt("isEnd"));
			workTab.setIsOver(rs.getInt("isOver"));
			workTab.setOverTime(rs.getDate("overTime"));
			workTab.setProcId(rs.getInt("procId"));
			workTab.setQuNum(rs.getInt("quNum"));
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return workTab;
	}

}
