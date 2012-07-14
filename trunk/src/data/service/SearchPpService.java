package data.service;

import java.sql.SQLException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import data.bean.Department;
import data.bean.Processes;
import data.bean.Product;
import data.bean.Staff;
import data.bean.WorkForm;

import data.dao.DepartmentDAO;
import data.dao.ProcessesDAO;
import data.dao.ProductDAO;
import data.dao.StaffDAO;
import data.dao.WorkFormDAO;



public class SearchPpService {
	
	List<WorkForm> list = new ArrayList<WorkForm>();
	List<PeopleComp> peopleComp = new ArrayList<PeopleComp>();
	private String staName;
	private String deptName;
	String sql;

	
	public Map<String,Object> SearchPp(String staNo,String starttime,String endtime) throws SQLException, ParseException{
	//在staff表中查找staID
		StaffDAO staffDAO=new StaffDAO();
		List<Staff> staff = new ArrayList<Staff>();
		Map<String, String> equalsmap = new HashMap<String, String>();
		equalsmap.put("staNo", staNo);
		staff=staffDAO.findEntity(equalsmap);
					
		System.out.println(staff.size());
		int staId= staff.get(0).getId();
		int deptId=staff.get(0).getDeptId();
		setStaName(staff.get(0).getStaName());
	//查找部门表
		DepartmentDAO departmentDAO=new DepartmentDAO();
		List<Department> department = new ArrayList<Department>();
		Map<String, Integer> equalsmap1 = new HashMap<String, Integer>();
		equalsmap1.put("Id", deptId);
		department=departmentDAO.findEntity(equalsmap1);		
		setDeptName(department.get(0).getDeptName());
		
	//在workform中查找其他信息	
		WorkFormDAO workFormDAO =new WorkFormDAO();
		sql="select * from workform where staId='"+staId+"' and time between '"+starttime+"' and '"+endtime+"'";
		//workFormDAO.queryWorkForm(sql);
		list=workFormDAO.findEntityByList(sql);
		System.out.println(list.get(0).getTime());
		
		//合并工单中同一产品同一工序的数据
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i).getWorkHours());
			for(int j=i+1;j<list.size();j++){
				if(list.get(j).getProId()==list.get(i).getProId()&&list.get(j).getProcId()==list.get(i).getProcId()){
					list.get(i).setQuaNum(list.get(i).getQuaNum()+list.get(j).getQuaNum());
					list.get(i).setgWaste(list.get(i).getgWaste()+list.get(j).getgWaste());
					list.get(i).setlWaste(list.get(i).getlWaste()+list.get(j).getlWaste());
					list.get(i).setWorkHours(list.get(i).getWorkHours()+list.get(j).getWorkHours());
					list.remove(j);
					j--;
				}
			}
		}
		//根据工单找出其他信息
	
		for(int i=0;i<list.size();i++){
			PeopleComp tmp = new PeopleComp();
			int proId=list.get(i).getProId();
			ProductDAO productDAO=new ProductDAO();
			List<Product> product=new ArrayList<Product>();
			Map<String, Integer> equalsmap2 = new HashMap<String, Integer>();
			equalsmap2.put("Id", proId);
			product=productDAO.findEntity(equalsmap2);
			
			int procId=list.get(i).getProcId();
			ProcessesDAO processesDAO = new ProcessesDAO();
			List<Processes> processes=new ArrayList<Processes>();
			Map<String, Integer> equalsmap3 = new HashMap<String, Integer>();
			equalsmap3.put("Id", procId);
			processes=processesDAO.findEntity(equalsmap3);
			
			tmp.proName=product.get(0).getProName();
			tmp.proNo=product.get(0).getProNo();
			tmp.procName=processes.get(0).getProcName();
			tmp.quaNum=list.get(i).getQuaNum();
			tmp.gWaste=list.get(i).getgWaste();
			tmp.lWaste=list.get(i).getlWaste();
			tmp.workHours=list.get(i).getWorkHours();
			
			peopleComp.add(tmp);
		}
		
					
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("list", peopleComp);
		map.put("staName", staName);
		map.put("deptName", deptName);
		return map;
	}
	@SuppressWarnings("unchecked")
	public static void main(String args[]) throws SQLException, ParseException{
		SearchPpService s=new SearchPpService();
		List<WorkForm> list=new ArrayList<WorkForm>();
		String staName =new String();
		Map<String,Object> map = new HashMap<String, Object>();
		map=s.SearchPp("352","2012-07-10","2012-07-16");
		
		list=(List<WorkForm>) map.get("list");
		staName=(String) map.get("staName");
		System.out.println(staName);
		Iterator<WorkForm> it =list.iterator();
		//list.iterator();
		while(it.hasNext())
		System.out.println(it.next().getgWaste());
	}
	public String getStaName() {
		return staName;
	}
	public void setStaName(String staName) {
		this.staName = staName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
class PeopleComp{
	public String proName;
	public String proNo;
	public String procName;
	public int quaNum;
	public int gWaste;
	public int lWaste;	
	public int workHours;
}
}
