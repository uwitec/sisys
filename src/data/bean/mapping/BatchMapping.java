package data.bean.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import data.bean.Batch;

public class BatchMapping  extends BasicMapping<Batch> {

	@Override
	public Batch mapping(ResultSet rs) throws ParseException {
		// TODO Auto-generated method stub
		Batch batch = new Batch();
		try {
			String start = rs.getString("startTime");
			String end = rs.getString("endTime");
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date startdate = df.parse(start);
			Date enddate = df.parse(end);
			batch.setStartTime(startdate);
			batch.setEndTime(enddate);
			batch.setBatchNo(rs.getString("batchNo"));
			batch.setCompleteNum(rs.getInt("completeNum"));
			batch.setDeleteTime(rs.getDate("deleteTime"));
			batch.setDisqNum(rs.getInt("disqNum"));
			batch.setDisqPercent(rs.getDouble("disqPercent"));
			batch.setFlowId(rs.getInt("flowId"));
			batch.setId(rs.getInt("id"));
			batch.setIsDelete(rs.getInt("isDelete"));
			batch.setProId(rs.getInt("proId"));
			batch.setStatus(rs.getInt("status"));
			batch.setTotalNum(rs.getInt("totalNum"));
			batch.setWorkTabId(rs.getInt("workTabId"));
		} catch(SQLException ex) {
			ex.printStackTrace();
		}		

		return batch;
	}

}
