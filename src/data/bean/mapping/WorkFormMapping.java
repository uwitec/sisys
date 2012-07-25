package data.bean.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import data.bean.WorkForm;

public class WorkFormMapping extends BasicMapping<WorkForm> {

	@Override
	public WorkForm mapping(ResultSet rs) {
		
		WorkForm workForm = new WorkForm();
		try {
			workForm.setBatchId(rs.getInt("batchId"));
			workForm.setDeleteTime(rs.getDate("deleteTime"));
			workForm.setDisDetail(rs.getString("disDetail"));
			workForm.setgWaste(rs.getInt("gWaste"));
			workForm.setId(rs.getInt("Id"));
			workForm.setIsDelete(rs.getInt("isDelete"));
			workForm.setlWaste(rs.getInt("lWaste"));
			workForm.setProcId(rs.getInt("procId"));
			workForm.setProId(rs.getInt("proId"));
			workForm.setQuaNum(rs.getInt("quaNum"));
			workForm.setStaId(rs.getInt("staId"));
			workForm.setTime(rs.getDate("time"));
			workForm.setWorkHours(rs.getDouble("workHours"));
			workForm.setName(rs.getString("name"));
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return workForm;
	}

}
