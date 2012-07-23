package data.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import data.bean.Batch;
import data.bean.Flowpath;
import data.bean.Processes;
import data.bean.Product;
import data.bean.User;
import data.bean.WorkTab;
import data.dao.BatchDAO;
import data.dao.FlowpathDAO;
import data.dao.ProcessesDAO;
import data.dao.ProductDAO;
import data.dao.WorkTabDAO;
import data.log.LogInfo;

public class ManageBatchService {
	
	
	ActionContext actionContext = ActionContext.getContext(); 
    Map session = actionContext.getSession();
	
	//进入批次添加页面
	public String preAddBatch(Product product) {
		//根据产品编号查询对应产品ID
		ProductDAO pdao = new ProductDAO();
		Map<String, Object> equalsMap = new HashMap<String, Object>();
		equalsMap.put("proNo", product.getProNo());
		List<Product> pList = pdao.findEntity(equalsMap);
		/*//若产品不存在，返回none
		if(pList.size() == 0) {
			return "pnone";
		}*/
		product = pList.get(0);
		FlowpathDAO fdao = new FlowpathDAO();
		equalsMap.clear();
		equalsMap.put("proId", product.getId());
		List<Flowpath> fList = fdao.findEntity(equalsMap);
		/*//若对应流程不存在，返回none
		if(fList.size() == 0) {
			return "fnone";
		}*/
		StringBuffer fp = null;
		Flowpath flowpath = null;
		StringBuffer result = new StringBuffer();
		for(int j=0; j<fList.size(); j++) {
			if(j != fList.size()-1) {
				result.append("<input type=\"radio\" name=\"fpath\" value=\"");
			} else {
				result.append("<input type=\"radio\" name=\"fpath\" checked value=\"");
			}
			
			flowpath = fList.get(j);
			String process = flowpath.getSequence();
			String[] processes = process.split("-");
			fp = new StringBuffer();
			//得到对应的工序名称列表字符串，以“-”连接
			ProcessesDAO procdao = new ProcessesDAO();
			for(int i=0; i<processes.length; i++) {
				int procId = Integer.parseInt(processes[i]);
				equalsMap.clear();
				equalsMap.put("Id", procId);
				List<Processes> procList = procdao.findEntity(equalsMap);
				/*if(procList.size() == 0) {
					return "fnone";
				}*/
				fp.append(procList.get(0).getProcName());
				if(i != processes.length-1) {
					fp.append("-");
				}
			}
			result.append(process);
			result.append("\">");
			result.append(fp);
		}
		
		//inputStream = fp.toString();
		//将流程信息及对应的id号保存到session里
		session.put("product", product);
	    System.out.println(product);
	    
		/*ActionContext context = ActionContext.getContext();  
	    HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);  
		request.setAttribute("product", product);
	    request.setAttribute("fp",fp.toString()); 
		request.setAttribute("flowpath", flowpath);*/
	    
	    return result.toString();
	}
	
	//批次添加
	public String addBatch(Product product, Batch batch, String fpath) {
		//判断输入是否完整
		if("".equals(batch.getBatchNo()) || "".equals(batch.getTotalNum()) || "".equals(product.getProNo())){
			return "isnull";
		}
		Product p = (Product) session.get("product");
		//判断产品是否存在
		ProductDAO pdao = new ProductDAO();
		Map<String, Object> equalsMap = new HashMap<String, Object>();
		equalsMap.put("proNo", product.getProNo());
		List<Product> pList = pdao.findEntity(equalsMap);
		//若产品不存在，返回none
		if(pList.size() == 0) {
			return "pnone";
		}
	
		//判断批次是否已经存在
		BatchDAO bdao = new BatchDAO();
		equalsMap.clear();
		equalsMap.put("batchNo", batch.getBatchNo());
		equalsMap.put("proId", String.valueOf(p.getId()));
		List<Batch> blist = bdao.findEntity(equalsMap);
		if( blist.size() != 0){
			return "repetition";
		}
		//批次不存在，新建批次
		Calendar startTime = Calendar.getInstance();
	    //Product p = (Product) session.get("product");
	    System.out.println(p);
	    batch.setIsDelete(0);
	    batch.setDeleteTime(null);
		batch.setProId(p.getId());
		batch.setStartTime(startTime.getTime());
		startTime.add(Calendar.DATE, p.getProCycle());
		batch.setEndTime(startTime.getTime());
		//找到对应的流程	
		FlowpathDAO fdao = new FlowpathDAO();
		//Map<String, String> equalsMap = new HashMap<String, String>();
		equalsMap.clear();
		equalsMap.put("sequence", fpath);
		List<Flowpath> fList = fdao.findEntity(equalsMap);
		batch.setFlowId(fList.get(0).getId());
		String[] processes = fpath.split("-");
		for(int i=0; i<processes.length; i++) {
			System.out.print(processes[i]);
		}
		
		//在工作表建立初始化的相应记录
		WorkTabDAO wdao = new WorkTabDAO();
		WorkTab wt = new WorkTab();
		wt.setId(0);
		wt.setProcId(Integer.parseInt(processes[0]));
		int num = wdao.create(wt);
		if(num == 0) {
			return "false";
		}
		WorkTabDAO wdao1 = new WorkTabDAO();
		int tabid = wdao1.count();
		//System.out.println(tabid + " " );
		batch.setWorkTabId(tabid);
		for(int i=1; i<processes.length; i++) {
			WorkTabDAO wdaoi = new WorkTabDAO();
			WorkTab wti = new WorkTab();
			wti.setId(0);
			if(i != processes.length-1) {
				wti.setIsEnd(0);
			} else {
				wti.setIsEnd(1);
			}
			wti.setProcId(Integer.parseInt(processes[i]));
			num = wdaoi.create(wti);
			if(num == 0) {
				return "false";
			}
		}
		batch.setStatus(0);
		bdao = new BatchDAO();
		num = bdao.create(batch);
		if(num == 0) {
			return "false";
		}
		
	    //记录管理员操作信息
	    LogInfo logInfo = new LogInfo();
	    User user = (User)session.get("user");
	    String content = "管理员" + user.getUsername() + "新建批次。产品名称：" + p.getProName() + ",产品编号：" + product.getProNo() 
	    			+ ";批次号：" + batch.getBatchNo();
	    logInfo.saveLog(user, content, System.currentTimeMillis());
	    
	    session.remove("product");
		return "success";
	}
	
	//超期批次的修改
	public String modifyOutDue(Product product, Batch batch) {
		//若输入值为空，返回empty
		if(product.getProNo()=="" || batch.getBatchNo()=="") {
			return "empty";
		} 
		//根据产品编号查询对应产品ID
		ProductDAO pdao = new ProductDAO();
		Map<String, Object> equalsMap = new HashMap<String, Object>();
		equalsMap.put("proNo", product.getProNo());
		equalsMap.put("isDelete", 0);
		List<Product> pList = pdao.findEntity(equalsMap);
		//若产品不存在，返回none
		if(pList.size() == 0) {
			return "none";
		}
		product.setId(pList.get(0).getId());
		batch.setProId(product.getId());
		//根据批次号和产品Id查询对应批次
		BatchDAO bdao = new BatchDAO();
		equalsMap.clear();
		equalsMap.put("batchNo", batch.getBatchNo());
		equalsMap.put("proId", batch.getProId());
		equalsMap.put("status", 2);
		equalsMap.put("isDelete", 0);
		List<Batch> bList = bdao.findEntity(equalsMap);
		//若该批次不存在，返回none
		if(bList.size() == 0) {
			return "none";
		}
		Batch b = bList.get(0);
		b.setNote(batch.getNote());
		b.setStatus(3);

		//若修改成功则返回success,若修改失败则返回false
		BatchDAO bdao1 = new BatchDAO();
		int num = bdao1.update(b, b.getId());
		if(num == 1) {
			
			//记录管理员操作信息
			ActionContext actionContext = ActionContext.getContext(); 
		    Map session = actionContext.getSession();
		    LogInfo logInfo = new LogInfo();
		    User user = (User)session.get("user");
		    String content = "管理员" + user.getUsername() + "修改超期批次。产品名称：" + product.getProName() + ",产品编号：" + product.getProNo() 
		    			+ ";批次号：" + batch.getBatchNo() + ";备注：" + batch.getNote();
		    logInfo.saveLog(user, content, System.currentTimeMillis());
		    
		    
			return "success";
		} else {
			return "false";
		}
	}
	
}
