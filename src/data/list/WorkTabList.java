package data.list;

import java.util.ArrayList;
import java.util.List;

import data.bean.WorkTab;
import data.bean.mapping.WorkTabMapping;
import data.util.GenericQueryImpl;

public class WorkTabList extends GenericQueryImpl<WorkTab, WorkTabMapping> {

	List<Object> value;
	String sql;
	int result;
	List<WorkTab> list;
	static WorkTabMapping WorkTabMapping = new WorkTabMapping();
	
	public WorkTabList() {
		super(WorkTab.class, WorkTabMapping);
		// TODO Auto-generated constructor stub
		value = new ArrayList<Object>();
		result = 0;
		list = new ArrayList<WorkTab>();
	}
	
	public List<WorkTab> createSQL(String s){
		list = this.findEntityByList(s);
		return list;
	}

}
