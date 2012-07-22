package data.list;

import java.util.ArrayList;
import java.util.List;

import data.bean.Batch;
import data.bean.mapping.BatchMapping;
import data.util.GenericQueryImpl;

public class BatchList extends GenericQueryImpl<Batch, BatchMapping> {

	List<Object> value;
	String sql;
	int result;
	List<Batch> list;
	static BatchMapping BatchMapping = new BatchMapping();
	
	public BatchList() {
		super(Batch.class, BatchMapping);
		// TODO Auto-generated constructor stub
		value = new ArrayList<Object>();
		result = 0;
		list = new ArrayList<Batch>();
	}
	
	public List<Batch> createSQL(String s){
		System.out.println("sql = ");
		System.out.println(s);
		list = this.findEntityByList(s);
		return list;
	}

}
