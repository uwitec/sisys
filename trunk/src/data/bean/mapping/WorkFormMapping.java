package data.bean.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import data.bean.WorkForm;

public class WorkFormMapping implements BeanMapping {

	public Object mapping(ResultSet rs) throws SQLException {
		
		WorkForm workForm = new WorkForm();
		if(rs.next()) {
			workForm.setBatchId(rs.getInt("batchId"));
			workForm.setDeleteTime(rs.getDate("deleteTime"));
			workForm.setDisDetail(rs.getString("disDetail"));
			workForm.setId(rs.getInt("Id"));
			workForm.setIsDelete(rs.getInt("isDelete"));
			workForm.setProcId(rs.getInt("procId"));
			workForm.setProId(rs.getInt("proId"));
			workForm.setQuaNum(rs.getInt("quaNum"));
			workForm.setStaId(rs.getInt("staId"));
			workForm.setTime(rs.getDate("time"));
		}
		return workForm;
	}

}
