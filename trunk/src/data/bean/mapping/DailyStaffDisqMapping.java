package data.bean.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import data.bean.DailyStaffDisq;

public class DailyStaffDisqMapping implements BeanMapping  {

	public Object mapping(ResultSet rs) throws SQLException {
		
		DailyStaffDisq dailyStaffDisq = new DailyStaffDisq();
		if(rs.next()) {
			dailyStaffDisq.setDisqdeId(rs.getInt("disqdeId"));
			dailyStaffDisq.setId(rs.getInt("Id"));
			dailyStaffDisq.setStaffId(rs.getInt("staffId"));
			dailyStaffDisq.setTime(rs.getDate("time"));
			dailyStaffDisq.setTotalNum(rs.getInt("totalNum"));
		}
		return dailyStaffDisq;
	}

}
