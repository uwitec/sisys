package data.service;

import java.sql.SQLException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import data.bean.Staff;
import data.bean.WorkHoursTab;
import data.dao.StaffDAO;
import data.dao.WorkHoursTabDAO;


public class SearchWhService {
	
	List<WorkHoursTab> list = new ArrayList<WorkHoursTab>();
	String staName;
	//private Date startTime;
	//private Date endTime;
	List<Object> value;
	String sql;

	
	public Map<String,Object> Search(String staNo,String starttime,String endtime) throws SQLException, ParseException{
	//在staff表中查找staID
	/*  startTime= dateformat.parse(starttime);
		endTime= dateformat.parse(endtime);
		System.out.println(startTime+"	"+endTime);
	*/
		StaffDAO staffDAO=new StaffDAO();
		List<Staff> staff = new ArrayList<Staff>();
		Map<String, String> equalsmap = new HashMap<String, String>();
		equalsmap.put("staNo", staNo);
		staff=staffDAO.findEntity(equalsmap);
					
		System.out.println(staff.size());
		int staId= staff.get(0).getId();
		staName=staff.get(0).getStaName();
	//在workhourstab中查找其他信息	
		WorkHoursTabDAO workHoursTabDAO =new WorkHoursTabDAO();
		sql="select * from workhourstab where staId='"+staId+"' and time between '"+starttime+"' and '"+endtime+"'";

		//value.add(staId);
		//value.add(startTime);
		//value.add(endTime);
		list=workHoursTabDAO.findEntityByList(sql);
		System.out.println(list.get(0).getTime());
					
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("staName", staName);
		
		return map;
	}
	@SuppressWarnings("unchecked")
	public static void main(String args[]) throws SQLException, ParseException{
		SearchWhService s=new SearchWhService();
		List<WorkHoursTab> list=new ArrayList<WorkHoursTab>();
		String staName =new String();
		Map<String,Object> map = new HashMap<String, Object>();
		map=s.Search("352","2012-07-10","2012-07-12");
		
		list=(List<WorkHoursTab>) map.get("list");
		staName=(String) map.get("staff");
		System.out.println(staName);
		Iterator<WorkHoursTab> it =list.iterator();
		//list.iterator();
		while(it.hasNext())
		System.out.println(it.next().getWorkHours());
	}
}
