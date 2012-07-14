package data.service;

import java.sql.SQLException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;




import data.bean.Batch;
import data.bean.Product;
import data.bean.ScheduleTab;
import data.dao.BatchDAO;
import data.dao.ProductDAO;
import data.dao.ScheduleTabDAO;
import data.util.*;

public class SearchJdService {
	
	List<ScheduleTab> list = new ArrayList<ScheduleTab>();
	private String proName;
	private String batchNo;
	private int status;
	String sql;

	
	public Map<String,Object> SearchJd(String proNo,String starttime,String batchNo) throws SQLException, ParseException{
	//在product表中查找proName和Id
	
		ProductDAO productDAO=new ProductDAO();
		List<Product> product = new ArrayList<Product>();
		Map<String, String> equalsmap = new HashMap<String, String>();
		equalsmap.put("proNo", proNo);
		product=productDAO.findEntity(equalsmap);
					
		System.out.println(product.size());
		proName= product.get(0).getProName();
		int proId=product.get(0).getId();
	
	//在batch表中中查找batchId	
		BatchDAO batchDAO =new BatchDAO();
		List<Batch> batch = new ArrayList<Batch>();
		Map<String, Object> equalsmap1 = new HashMap<String, Object>();
		equalsmap1.put("batchNo", batchNo);
		equalsmap1.put("proId", proId);
		batch=batchDAO.findEntity(equalsmap1);
		
		System.out.println(batch.size());
		int batchId=batch.get(0).getId();
		status=batch.get(0).getStatus();
		
	//在schedule中查找进度信息
		ScheduleTabDAO scheduleTabDAO=new ScheduleTabDAO();
	//	List<ScheduleTab> scheduleTab=new ArrayList<ScheduleTab>();
		
		sql="select * from scheduletab where batchId='"+batchId+"' and time>='"+starttime+"'";		
		list=scheduleTabDAO.findEntityByList(sql);
		System.out.println(list.get(0).getTime());
					
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("proName", proName);
		map.put("status", status);
		
		
		return map;
	}
	@SuppressWarnings("unchecked")
	public static void main(String args[]) throws SQLException, ParseException{
		SearchJdService s=new SearchJdService();
		List<ScheduleTab> list=new ArrayList<ScheduleTab>();
		String staName =new String();
		Map<String,Object> map = new HashMap<String, Object>();
		map=s.SearchJd("210290910","2012-07-14","122");
		
		System.out.println(map.size());
		list=(List<ScheduleTab>) map.get("list");
		staName=(String) map.get("proName");
		System.out.println(staName);
		Iterator<ScheduleTab> it =list.iterator();
		//list.iterator();
		while(it.hasNext())
		System.out.println(it.next().getTime().getDate());
	}
}
