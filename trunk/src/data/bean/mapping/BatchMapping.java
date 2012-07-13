package data.bean.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import data.bean.Batch;

public class BatchMapping  extends BasicMapping<Batch> {

	public Batch mapping(ResultSet rs) {
		// TODO Auto-generated method stub
		Batch batch = new Batch();
		try {
			batch.setBatchNo(rs.getString("batchNo"));
			batch.setCompleteNum(rs.getInt("completeNum"));
			batch.setDeleteTime(rs.getDate("deleteTime"));
			batch.setDisqNum(rs.getInt("disqNum"));
			batch.setDisqPercent(rs.getDouble("disqPercent"));
			batch.setEndTime(rs.getDate("endTime"));
			batch.setFlowId(rs.getInt("flowId"));
			batch.setId(rs.getInt("id"));
			batch.setIsDelete(rs.getInt("isDelete"));
			batch.setProId(rs.getInt("proId"));
			batch.setStartTime(rs.getDate("startTime"));
			batch.setStatus(rs.getInt("status"));
			batch.setTotalNum(rs.getInt("totalNum"));
			batch.setWorkTabId(rs.getInt("workTabId"));
		} catch(SQLException ex) {
			ex.printStackTrace();
		}		

		return batch;
	}

}
