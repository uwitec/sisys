package data.service;

import java.sql.SQLException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;



import data.bean.Department;
import data.bean.Product;
import data.dao.DepartmentDAO;
import data.dao.ProductDAO;



public class SearchPd4Service {
	
	List<Product> list = new ArrayList<Product>();
	 String deptName=new String();
	 int completeNum=0;
	 int disqNum=0;
	 double disqPercent=0.00;
	String sql;

	
	public Map<String,Object> SearchPd4(String deptNo,String starttime,String endtime) throws SQLException{
	//在产品表中查找proID
	
		DepartmentDAO departmentDAO =new DepartmentDAO();
		List<Department> department = new ArrayList<Department>();
		Map<String, String> equalsmap = new HashMap<String, String>();
		equalsmap.put("deptNo", deptNo);
		department=departmentDAO.findEntity(equalsmap);
		deptName=department.get(0).getDeptName();
		System.out.println(deptName);
		int deptId=department.get(0).getId();
			
		ProductDAO productDAO=new ProductDAO();
		sql="select * from product where deptId='"+deptId+"' and time between '"+starttime+"' and '"+endtime+"'";
		list=productDAO.findEntityByList(sql);
		
		for(int i=0;i<list.size();i++){
			completeNum=completeNum+list.get(i).getCompleteNum();
			disqNum=disqNum+list.get(i).getDisqNum();
		}
		double d = (double)((disqNum*100)/completeNum);
		disqPercent= (disqNum*100)%completeNum <5 ? d/100 : (d+1)/100;
		System.out.println(disqPercent);
					
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("deptName", deptName);
		map.put("completeNum", completeNum);
		map.put("disqNum", disqNum);
		map.put("disqPercent", disqPercent);
		
		return map;
	}
	@SuppressWarnings("unchecked")
	public static void main(String args[]) throws SQLException, ParseException{
		SearchPd4Service s=new SearchPd4Service();
		List<Product> list=new ArrayList<Product>();
		String deptName =new String();
		Map<String,Object> map = new HashMap<String, Object>();
		map=s.SearchPd4("45","2012-07-10","2012-07-12");
		
		list=(List<Product>) map.get("list");
		deptName=(String) map.get("deptName");
		System.out.println(deptName);
		Iterator<Product> it =list.iterator();
		//list.iterator();
		while(it.hasNext())
		System.out.println(it.next().getCompleteNum());
	}
	
	
}
