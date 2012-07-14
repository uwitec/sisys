package data.bean.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import data.bean.DailyStaffDisq;

public class DailyStaffDisqMapping extends BasicMapping<DailyStaffDisq>  {

	public DailyStaffDisq mapping(ResultSet rs) {
		
		DailyStaffDisq dailyStaffDisq = new DailyStaffDisq();
		try {
			dailyStaffDisq.setDisqdeId(rs.getInt("disqdeId"));
			dailyStaffDisq.setId(rs.getInt("Id"));
			dailyStaffDisq.setStaffId(rs.getInt("staffId"));
			dailyStaffDisq.setTime(rs.getDate("time"));
			dailyStaffDisq.setTotalNum(rs.getInt("totalNum"));
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return dailyStaffDisq;
	}

}
