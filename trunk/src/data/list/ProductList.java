package data.list;

import java.util.ArrayList;
import java.util.List;

import data.bean.Product;
import data.bean.mapping.ProductMapping;
import data.util.GenericQueryImpl;

public class ProductList extends GenericQueryImpl<Product, ProductMapping> {

	List<Object> value;
	String sql;
	int result;
	List<Product> list;
	static ProductMapping ProductMapping = new ProductMapping();
	
	public ProductList() {
		super(Product.class, ProductMapping);
		// TODO Auto-generated constructor stub
		value = new ArrayList<Object>();
		result = 0;
		list = new ArrayList<Product>();
	}
	
	public List<Product> createSQL(String s){
		list = this.findEntityByList(s);
		return list;
	}

}
