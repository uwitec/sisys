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
import data.bean.ProductLine;

import data.dao.BatchDAO;
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
		Map<String,Object> map = new HashMap<String, Object>();
	
		//在产品表中查找proID
		ProductLineDAO productLineDAO =new ProductLineDAO();
		List<ProductLine> productLine = new ArrayList<ProductLine>();
		Map<String, String> equalsmap = new HashMap<String, String>();
		equalsmap.put("lineNo", lineNo);
		productLine=productLineDAO.findEntity(equalsmap);
		if(productLine.size() == 0){
			map.put("result", "error");
			map.put("message", "生产线编号不存在！请重新输入！");
			return map;
		}
		lineDesc=productLine.get(0).getLineDesc();
		System.out.println(lineDesc);
		int prolineId=productLine.get(0).getId();
		
		ProductDAO productDAO=new ProductDAO();
		sql="select * from product where prolineId='"+prolineId+"' and isDelete='0'";
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
			//遍历批次
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
			map.put("message", "生产线上没有产品！请重新输入！");
			return map;	
		}
		else{		
		double d = (double)((disqNum*100)/completeNum);
		disqPercent= (disqNum*100)%completeNum <5 ? d/100 : (d+1)/100;
		}						
		map.put("result", "success");
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
