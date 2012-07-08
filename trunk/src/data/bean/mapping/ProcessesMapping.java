package data.bean.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import data.bean.Processes;

public class ProcessesMapping implements BeanMapping {

	public Object mapping(ResultSet rs) throws SQLException {
		
		Processes process = new Processes();
		if(rs.next()) {
			process.setColorNo(rs.getString("colorNo"));
			process.setDeleteTime(rs.getDate("deleteTime"));
			process.setId(rs.getInt("Id"));
			process.setIsDelete(rs.getInt("isDelete"));
			process.setProcName(rs.getString("procName"));
			process.setProcNo(rs.getString("procNo"));
			process.setUnitCost(rs.getInt("unitCost"));
			process.setUnitOutput(rs.getInt("unitOutput"));
		}
		return process;
	}

}
