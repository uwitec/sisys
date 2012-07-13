package data.list;

import java.util.ArrayList;
import java.util.List;

import data.bean.DisqKind;
import data.bean.mapping.DisqKindMapping;
import data.util.GenericQueryImpl;

public class DisqKindList extends GenericQueryImpl<DisqKind, DisqKindMapping> {

	List<Object> value;
	String sql;
	int result;
	List<DisqKind> list;
	static DisqKindMapping DisqKindMapping = new DisqKindMapping();
	
	public DisqKindList() {
		super(DisqKind.class, DisqKindMapping);
		// TODO Auto-generated constructor stub
		value = new ArrayList<Object>();
		result = 0;
		list = new ArrayList<DisqKind>();
	}
	
	public List<DisqKind> createSQL(String s){
		list = this.findEntityByList(s);
		return list;
	}

}
