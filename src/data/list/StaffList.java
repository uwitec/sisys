package data.list;

import java.util.ArrayList;
import java.util.List;

import data.bean.Staff;
import data.bean.mapping.StaffMapping;
import data.util.GenericQueryImpl;

public class StaffList extends GenericQueryImpl<Staff, StaffMapping> {

	List<Object> value;
	String sql;
	int result;
	List<Staff> list;
	static StaffMapping StaffMapping = new StaffMapping();
	
	public StaffList() {
		super(Staff.class, StaffMapping);
		// TODO Auto-generated constructor stub
		value = new ArrayList<Object>();
		result = 0;
		list = new ArrayList<Staff>();
	}
	
	public List<Staff> createSQL(String s){
		list = this.findEntityByList(s);
		return list;
	}

}
