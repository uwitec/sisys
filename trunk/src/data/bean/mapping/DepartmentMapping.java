package data.bean.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import data.bean.Department;

public class DepartmentMapping extends BasicMapping<Department>{

	public Department mapping(ResultSet rs) {

		Department department = new Department();
		try {			
			department.setId(rs.getInt("Id"));
			department.setDeptName(rs.getString("deptName"));
			department.setDeptNo(rs.getString("deptNo"));
			department.setIsDelete(rs.getInt("isDelete"));
			department.setDeleteTime(rs.getDate("deleteTime"));
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return department;
	}
}
