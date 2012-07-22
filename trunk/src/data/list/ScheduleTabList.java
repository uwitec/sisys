package data.list;

import java.util.ArrayList;
import java.util.List;

import data.bean.ScheduleTab;
import data.bean.mapping.ScheduleTabMapping;
import data.util.GenericQueryImpl;

public class ScheduleTabList extends GenericQueryImpl<ScheduleTab, ScheduleTabMapping> {

	List<Object> value;
	String sql;
	int result;
	List<ScheduleTab> list;
	static ScheduleTabMapping ScheduleTabMapping = new ScheduleTabMapping();
	
	public ScheduleTabList() {
		super(ScheduleTab.class, ScheduleTabMapping);
		// TODO Auto-generated constructor stub
		value = new ArrayList<Object>();
		result = 0;
		list = new ArrayList<ScheduleTab>();
	}
	
	public List<ScheduleTab> createSQL(String s){
		list = this.findEntityByList(s);
		return list;
	}

}
