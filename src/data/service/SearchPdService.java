package data.service;

import java.sql.SQLException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import data.bean.Batch;
import data.bean.Product;
import data.dao.BatchDAO;
import data.dao.ProductDAO;



public class SearchPdService {
	
	List<Batch> list = new ArrayList<Batch>();
	 String proName=new String();
	 int completeNum=0;
	 int disqNum=0;
	 double disqPercent=0.00;
	String sql;

	
	public Map<String,Object> SearchPd(String proNo,String starttime,String endtime) throws SQLException{
		Map<String,Object> map = new HashMap<String, Object>();
	
		//在产品表中查找proID
		ProductDAO productDAO=new ProductDAO();
		List<Product> product = new ArrayList<Product>();
		Map<String, String> equalsmap = new HashMap<String, String>();
		equalsmap.put("proNo", proNo);
		product=productDAO.findEntity(equalsmap);
		if(product.size() == 0){
			map.put("result", "error");
			map.put("message", "产品编号不存在！请重新输入！");
			return map;
		}
		proName=product.get(0).getProName();
		System.out.println(product.size());
		int proId= product.get(0).getId();
	//在batch中查找其他信息	
		BatchDAO batchDAO =new BatchDAO();
		sql="select * from batch where proId='"+proId+"'and ('"+starttime+"'<=endTime or '"+endtime+"'>=startTime)";
		list=batchDAO.findEntityByList(sql);
		System.out.println(list.get(0).getDisqPercent());
		
		for(int i=0;i<list.size();i++){
			completeNum=completeNum+list.get(i).getCompleteNum();
			disqNum=disqNum+list.get(i).getDisqNum();
		}
		double d = (double)((disqNum*100)/completeNum);
		disqPercent= (disqNum*100)%completeNum == 0 ? d/100 : (d+1)/100;
		System.out.println(disqPercent);
					
		map.put("result", "success");
		map.put("list", list);
		map.put("proName", proName);
		map.put("completeNum", completeNum);
		map.put("disqNum", disqNum);
		map.put("disqPercent", disqPercent);
		
		return map;
	}
	@SuppressWarnings("unchecked")
	public static void main(String args[]) throws SQLException, ParseException{
		SearchPdService s=new SearchPdService();
		List<Batch> list=new ArrayList<Batch>();
		String proName =new String();
		Map<String,Object> map = new HashMap<String, Object>();
		map=s.SearchPd("1","2012-07-10","2012-07-12");
		
		list=(List<Batch>) map.get("list");
		proName=(String) map.get("proName");
		System.out.println(proName);
		Iterator<Batch> it =list.iterator();
		//list.iterator();
		while(it.hasNext())
		System.out.println(it.next().getCompleteNum());
	}
	
	
}
