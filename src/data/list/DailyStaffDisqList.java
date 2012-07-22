package data.list;

import java.util.ArrayList;
import java.util.List;

import data.bean.DailyStaffDisq;
import data.bean.mapping.DailyStaffDisqMapping;
import data.util.GenericQueryImpl;

public class DailyStaffDisqList extends GenericQueryImpl<DailyStaffDisq, DailyStaffDisqMapping> {

	List<Object> value;
	String sql;
	int result;
	List<DailyStaffDisq> list;
	static DailyStaffDisqMapping DailyStaffDisqMapping = new DailyStaffDisqMapping();
	
	public DailyStaffDisqList() {
		super(DailyStaffDisq.class, DailyStaffDisqMapping);
		// TODO Auto-generated constructor stub
		value = new ArrayList<Object>();
		result = 0;
		list = new ArrayList<DailyStaffDisq>();
	}
	
	public List<DailyStaffDisq> createSQL(String s){
		list = this.findEntityByList(s);
		return list;
	}

}
