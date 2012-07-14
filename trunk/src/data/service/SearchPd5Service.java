package data.service;

import java.sql.SQLException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;



import data.bean.Product;
import data.bean.ProductLine;

import data.dao.ProductDAO;
import data.dao.ProductLineDAO;



public class SearchPd5Service {
	
	List<Product> list = new ArrayList<Product>();
	 String lineDesc=new String();
	 int completeNum=0;
	 int disqNum=0;
	 double disqPercent=0.00;
	String sql;

	
	public Map<String,Object> SearchPd5(String lineNo,String starttime,String endtime) throws SQLException{
	//在产品表中查找proID
	
		ProductLineDAO productLineDAO =new ProductLineDAO();
		List<ProductLine> productLine = new ArrayList<ProductLine>();
		Map<String, String> equalsmap = new HashMap<String, String>();
		equalsmap.put("lineNo", lineNo);
		productLine=productLineDAO.findEntity(equalsmap);
		lineDesc=productLine.get(0).getLineDesc();
		System.out.println(lineDesc);
		int lineId=productLine.get(0).getId();
			
		ProductDAO productDAO=new ProductDAO();
		sql="select * from product where prolineId='"+lineId+"' and time between '"+starttime+"' and '"+endtime+"'";
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
		map.put("lineDesc", lineDesc);
		map.put("completeNum", completeNum);
		map.put("disqNum", disqNum);
		map.put("disqPercent", disqPercent);
		
		return map;
	}
	@SuppressWarnings("unchecked")
	public static void main(String args[]) throws SQLException, ParseException{
		SearchPd5Service s=new SearchPd5Service();
		List<Product> list=new ArrayList<Product>();
		Map<String,Object> map = new HashMap<String, Object>();
		map=s.SearchPd5("1","2012-07-10","2012-07-12");
		String lineDesc=new String();
		list=(List<Product>) map.get("list");
		lineDesc=(String) map.get("lineDesc");
		System.out.println(lineDesc);
		Iterator<Product> it =list.iterator();
		//list.iterator();
		while(it.hasNext())
		System.out.println(it.next().getCompleteNum());
	}
	
	
}
