package data.list;

import java.util.ArrayList;
import java.util.List;

import data.bean.WorkForm;
import data.bean.mapping.WorkFormMapping;
import data.util.GenericQueryImpl;

public class WorkFormList extends GenericQueryImpl<WorkForm, WorkFormMapping> {

	List<Object> value;
	String sql;
	int result;
	List<WorkForm> list;
	static WorkFormMapping WorkFormMapping = new WorkFormMapping();
	
	public WorkFormList() {
		super(WorkForm.class, WorkFormMapping);
		// TODO Auto-generated constructor stub
		value = new ArrayList<Object>();
		result = 0;
		list = new ArrayList<WorkForm>();
	}
	
	public List<WorkForm> createSQL(String s){
		list = this.findEntityByList(s);
		return list;
	}

}
