package data.bean.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import data.bean.Department;

public class DepartmentMapping implements BeanMapping{

	public Department mapping(ResultSet rs) throws SQLException {

		Department department = new Department();
		if(rs.next()) {			
			department.setId(rs.getInt("Id"));
			department.setDeptName(rs.getString("deptName"));
			department.setDeptNo(rs.getString("deptNo"));
			department.setIsDelete(rs.getInt("isDelete"));
			department.setDeleteTime(rs.getDate("deleteTime"));
		}
		
		return department;
	}
}
