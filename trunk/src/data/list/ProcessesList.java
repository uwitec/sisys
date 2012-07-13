package data.list;

import java.util.ArrayList;
import java.util.List;

import data.bean.Processes;
import data.bean.mapping.ProcessesMapping;
import data.util.GenericQueryImpl;

public class ProcessesList extends GenericQueryImpl<Processes, ProcessesMapping> {

	List<Object> value;
	String sql;
	int result;
	List<Processes> list;
	static ProcessesMapping ProcessesMapping = new ProcessesMapping();
	
	public ProcessesList() {
		super(Processes.class, ProcessesMapping);
		// TODO Auto-generated constructor stub
		value = new ArrayList<Object>();
		result = 0;
		list = new ArrayList<Processes>();
	}
	
	public List<Processes> createSQL(String s){
		list = this.findEntityByList(s);
		return list;
	}

}
