package data.bean.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import data.bean.Processes;

public class ProcessesMapping extends BasicMapping<Processes> {

	public Processes mapping(ResultSet rs)  {
		
		Processes process = new Processes();
		try {
			process.setColorNo(rs.getString("colorNo"));
			process.setDeleteTime(rs.getDate("deleteTime"));
			process.setId(rs.getInt("Id"));
			process.setIsDelete(rs.getInt("isDelete"));
			process.setProcName(rs.getString("procName"));
			process.setProcNo(rs.getString("procNo"));
			process.setUnitCost(rs.getInt("unitCost"));
			process.setUnitOutput(rs.getInt("unitOutput"));
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return process;
	}

}
