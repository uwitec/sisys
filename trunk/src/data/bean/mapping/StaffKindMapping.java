package data.bean.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import data.bean.StaffKind;

public class StaffKindMapping implements BeanMapping{

	public StaffKind mapping(ResultSet rs) throws SQLException {

		StaffKind StaffKind = new StaffKind();
		
		if(rs.next()) {			
			StaffKind.setId(rs.getInt("Id"));
			StaffKind.setKindDesc(rs.getString("kindDesc"));
			StaffKind.setIsDelete(rs.getInt("isDelete"));
			StaffKind.setDeleteTime(rs.getDate("deleteTime"));
		}
		
		return StaffKind;
	}

}
