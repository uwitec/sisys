package data.list;

import java.util.ArrayList;
import java.util.List;

import data.bean.DisqDetail;
import data.bean.mapping.DisqDetailMapping;
import data.util.GenericQueryImpl;

public class DisqDetailList extends GenericQueryImpl<DisqDetail, DisqDetailMapping> {

	List<Object> value;
	String sql;
	int result;
	List<DisqDetail> list;
	static DisqDetailMapping DisqDetailMapping = new DisqDetailMapping();
	
	public DisqDetailList() {
		super(DisqDetail.class, DisqDetailMapping);
		// TODO Auto-generated constructor stub
		value = new ArrayList<Object>();
		result = 0;
		list = new ArrayList<DisqDetail>();
	}
	
	public List<DisqDetail> createSQL(String s){
		list = this.findEntityByList(s);
		return list;
	}

}
