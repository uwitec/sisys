package data.list;

import java.util.ArrayList;
import java.util.List;

import data.bean.Flowpath;
import data.bean.mapping.FlowpathMapping;
import data.util.GenericQueryImpl;

public class FlowpathList extends GenericQueryImpl<Flowpath, FlowpathMapping> {

	List<Object> value;
	String sql;
	int result;
	List<Flowpath> list;
	static FlowpathMapping FlowpathMapping = new FlowpathMapping();
	
	public FlowpathList() {
		super(Flowpath.class, FlowpathMapping);
		// TODO Auto-generated constructor stub
		value = new ArrayList<Object>();
		result = 0;
		list = new ArrayList<Flowpath>();
	}
	
	public List<Flowpath> createSQL(String s){
		list = this.findEntityByList(s);
		return list;
	}

}
