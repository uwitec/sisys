package data.list;

import java.util.ArrayList;
import java.util.List;

import data.bean.WorkHoursTab;
import data.bean.mapping.WorkHoursTabMapping;
import data.util.GenericQueryImpl;

public class WorkHoursTabList extends GenericQueryImpl<WorkHoursTab, WorkHoursTabMapping> {

	List<Object> value;
	String sql;
	int result;
	List<WorkHoursTab> list;
	static WorkHoursTabMapping WorkHoursTabMapping = new WorkHoursTabMapping();
	
	public WorkHoursTabList() {
		super(WorkHoursTab.class, WorkHoursTabMapping);
		// TODO Auto-generated constructor stub
		value = new ArrayList<Object>();
		result = 0;
		list = new ArrayList<WorkHoursTab>();
	}
	
	public List<WorkHoursTab> createSQL(String s){
		System.out.println("sql = ");
		System.out.println(s);
		list = this.findEntityByList(s);
		return list;
	}

}
