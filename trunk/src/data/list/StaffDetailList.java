package data.list;

import java.util.ArrayList;
import java.util.List;

import data.bean.StaffDetail;
import data.bean.mapping.StaffDetailMapping;
import data.util.GenericQueryImpl;

public class StaffDetailList extends GenericQueryImpl<StaffDetail, StaffDetailMapping> {

	List<Object> value;
	String sql;
	int result;
	List<StaffDetail> list;
	static StaffDetailMapping StaffDetailMapping = new StaffDetailMapping();
	
	public StaffDetailList() {
		super(StaffDetail.class, StaffDetailMapping);
		// TODO Auto-generated constructor stub
		value = new ArrayList<Object>();
		result = 0;
		list = new ArrayList<StaffDetail>();
	}
	
	public List<StaffDetail> createSQL(String s){
		list = this.findEntityByList(s);
		return list;
	}

}
