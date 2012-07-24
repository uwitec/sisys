package data.service;

import java.sql.SQLException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import data.bean.Batch;

import data.bean.Flowpath;
import data.bean.Processes;
import data.bean.Product;
import data.bean.WorkTab
;
import data.dao.BatchDAO;
import data.dao.FlowpathDAO;
import data.dao.ProcessesDAO;
import data.dao.ProductDAO;
import data.dao.WorkTabDAO;

public class SearchPd2Service {
	List<DisqBatch> disqBatch =new ArrayList<DisqBatch>();
	 String proName=new String();
	 int completeNum=0;
	 int disqNum=0;
	 double disqPercent=0.00;
	
	String sql;
	
	public Map<String,Object> SearchPd2(String batchNo,String proNo,String starttime,String endtime) throws SQLException{
		Map<String,Object> map = new HashMap<String, Object>();
	
		//在产品表中查找proID
		ProductDAO productDAO=new ProductDAO();
		List<Product> product = new ArrayList<Product>();
		Map<String, String> equalsmap = new HashMap<String, String>();
		equalsmap.put("proNo", proNo);
		equalsmap.put("isDelete", "0");
		product=productDAO.findEntity(equalsmap);
		if(product.size() == 0){
			map.put("result", "error");
			map.put("message", "产品编号不存在！请重新输入！");
			return map;
		}
		System.out.println(product.size());
		
		proName=product.get(0).getProName();
		int proId=product.get(0).getId();
		
		//在batch中查找flowId
		BatchDAO batchDAO=new BatchDAO();
		List<Batch> batch = new ArrayList<Batch>();
		Map<String, Object> equalsmap1 = new HashMap<String, Object>();
		equalsmap1.put("batchNo", batchNo);
		equalsmap1.put("proId", proId);
		equalsmap1.put("isDelete", 0);
		batch=batchDAO.findEntity(equalsmap1);
		if(batch.size() == 0){
			map.put("result", "error");
			map.put("message", "批次编号不存在！请重新输入！");
			return map;
		}
		int flowId=batch.get(0).getFlowId();
		
	//在Flowpath中查找sequence	
		FlowpathDAO flowpathDAO =new FlowpathDAO();
		List<Flowpath> flowpath = new ArrayList<Flowpath>();
		Map<String, Object> equalsmap2 = new HashMap<String, Object>();
		equalsmap2.put("Id", flowId);
		equalsmap2.put("isDelete", 0);
		flowpath=flowpathDAO.findEntity(equalsmap2);
		if(flowpath.size() == 0){
			map.put("result", "error");
			map.put("message", "流程编号不存在！请重新输入！");
			return map;
		}
		System.out.println(flowpath.size());
		
//取出次产品流程中包含的所有工序
		String sequence=flowpath.get(0).getSequence();		
		String[] s=null;
		s=sequence.split("-");
		int temp=0;
		for(int i=0;i<s.length;i++){
			temp=Integer.parseInt(s[i]);
			//查询工序表，得到工序编号和名称
			ProcessesDAO processesDAO=new ProcessesDAO();
			List<Processes> processes = new ArrayList<Processes>();
			Map<String, Object> equalsmap3 = new HashMap<String, Object>();
			equalsmap3.put("Id", temp);
			equalsmap3.put("isDelete", 0);
			processes=processesDAO.findEntity(equalsmap3);
			if(processes.size() == 0){
				map.put("result", "error");
				map.put("message", "工序编号不存在！请重新输入！");
				return map;
			}
			
			//查询工作表得到不合格数量
			WorkTabDAO workTabDAO=new WorkTabDAO();
			List<WorkTab> workTab = new ArrayList<WorkTab>();
			sql="select * from worktab where procId='"+temp+"' and overtime between '"+starttime+"' and '"+endtime+"'";
			workTab=workTabDAO.findEntityByList(sql);
			if(workTab.size() == 0){
				map.put("result", "error");
				map.put("message", "工序编号不存在！请重新输入！");
				return map;
			}
			else{
				DisqBatch tempDB=new DisqBatch();
				tempDB.procNo=processes.get(0).getProcNo();
				tempDB.procName=processes.get(0).getProcName();
				tempDB.totalNum=workTab.get(0).getQuNum();
				tempDB.disqNum=workTab.get(0).getDisqNum();
				double d = (double)((tempDB.disqNum*100)/tempDB.totalNum);
				tempDB.disqPercent= ((tempDB.disqNum)*100)%tempDB.totalNum == 0 ? d/100 : (d+1)/100;			
				disqBatch.add(tempDB);
			}												
		}
		//计算总量
		for(int i=0;i<disqBatch.size();i++){
			disqNum=disqNum+disqBatch.get(i).disqNum;
		}
		completeNum=disqBatch.get(0).totalNum+disqBatch.get(0).disqNum;
		double d = (double)((disqNum*100)/completeNum);
		disqPercent= (disqNum*100)%completeNum == 0 ? d/100 : (d+1)/100;
		
		map.put("result", "success");
		map.put("list", disqBatch);
		map.put("proName", proName);
		map.put("completeNum", completeNum);
		map.put("disqNum", disqNum);
		map.put("disqPercent", disqPercent);
		
		return map;
	}	
	@SuppressWarnings("unchecked")
	public static void main(String args[]) throws SQLException, ParseException{
		SearchPd2Service s=new SearchPd2Service();
		List<DisqBatch> list=new ArrayList<DisqBatch>();
		Map<String,Object> map = new HashMap<String, Object>();
		map=s.SearchPd2("1","1","2012-07-08","2012-08-08");
		
		list=(List<DisqBatch>) map.get("list");
		
		;
		Iterator<DisqBatch> it =list.iterator();
		//list.iterator();
		while(it.hasNext())
		System.out.println(it.next().disqPercent);
	}
	
	
}

 class DisqBatch{
	
	public String procNo;
	public String procName;
	public int totalNum;
	public int disqNum;
	public double disqPercent;
	
	public DisqBatch(){
		super();
	}
}