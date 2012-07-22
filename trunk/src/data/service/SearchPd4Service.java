package data.service;

import java.sql.SQLException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;



import data.bean.Batch;
import data.bean.Department;
import data.bean.Product;
import data.dao.BatchDAO;
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
		Map<String,Object> map = new HashMap<String, Object>();
	
		//在产品表中查找proID
		DepartmentDAO departmentDAO =new DepartmentDAO();
		List<Department> department = new ArrayList<Department>();
		Map<String, Object> equalsmap = new HashMap<String, Object>();
		equalsmap.put("deptNo", deptNo);
		equalsmap.put("isDelete", 0);
		department=departmentDAO.findEntity(equalsmap);
		if(department.size() == 0){
			map.put("result", "error");
			map.put("message", "部门编号不存在！请重新输入！");
			return map;
		}
		deptName=department.get(0).getDeptName();
		System.out.println(deptName);
		int deptId=department.get(0).getId();
			
		ProductDAO productDAO=new ProductDAO();
		sql="select * from product where deptId='"+deptId+"' and isDelete='0'";
		list=productDAO.findEntityByList(sql);
		if(list.size() == 0){
			map.put("result", "error");
			map.put("message", "产品编号不存在！请重新输入！");
			return map;
		}
		for(int i=0;i<list.size();i++){
			List<Batch> batch=new ArrayList<Batch>();
			BatchDAO batchDAO=new BatchDAO();
			String sql1="select * from batch where proId ='"+list.get(i).getId()+"' and starttime between '"+starttime+"' and '"+endtime+"'";
			batch=batchDAO.findEntityByList(sql1);
			if(list.size() == 0){
				continue;
			}
			
			int temp_totalNum=0;
			int temp_disqNum=0;
			for(int j=0;j<batch.size();j++){
				if(batch.get(j).getTotalNum()==0){
					continue;
				}	
				temp_totalNum+=batch.get(j).getTotalNum();
				temp_disqNum+=batch.get(j).getDisqNum();
			}
				if (temp_totalNum==0){
					continue;
				}	
				else{
						double d = (double)((temp_disqNum*100)/temp_totalNum);
						double temp_disqPercent= (temp_disqNum*100)%temp_totalNum <5 ? d/100 : (d+1)/100;
						System.out.println(temp_disqPercent);
						list.get(i).setCompleteNum(temp_totalNum);
						list.get(i).setDisqNum(temp_disqNum);
						list.get(i).setDisqPerc(temp_disqPercent);
						
						completeNum=temp_totalNum+completeNum;
						disqNum=temp_disqNum+disqNum;
				}
			
		}
			if (completeNum==0){
				map.put("result", "error");
				map.put("message", "部门没有产品！请重新输入！");
				return map;	
			}
			else{
		double d = (double)((disqNum*100)/completeNum);
		disqPercent= (disqNum*100)%completeNum <5 ? d/100 : (d+1)/100;
			}						
		map.put("result", "success");
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
