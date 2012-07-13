package data.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import data.bean.Batch;
import data.bean.Flowpath;
import data.bean.Processes;
import data.bean.Product;
import data.bean.WorkTab;
import data.dao.BatchDAO;
import data.dao.FlowpathDAO;
import data.dao.ProcessesDAO;
import data.dao.ProductDAO;
import data.dao.WorkTabDAO;

public class ManageBatchService {
	
	//进入批次添加页面
	public String preAddBatch(Product product) {
		//根据产品编号查询对应产品ID
		ProductDAO pdao = new ProductDAO();
		Map<String, Object> equalsMap = new HashMap<String, Object>();
		equalsMap.put("proNo", product.getProNo());
		List<Product> pList = pdao.findEntity(equalsMap);
		//若产品不存在，返回none
		if(pList.size() == 0) {
			return "pnone";
		}
		product = pList.get(0);
		FlowpathDAO fdao = new FlowpathDAO();
		equalsMap.clear();
		equalsMap.put("proId", product.getId());
		List<Flowpath> fList = fdao.findEntity(equalsMap);
		//若对应流程不存在，返回none
		if(fList.size() == 0) {
			return "fnone";
		}
		Flowpath flowpath = fList.get(0);
		String process = flowpath.getSequence();
		String[] processes = process.split("-");
		StringBuffer fp = new StringBuffer();
		//得到对应的工序名称列表字符串，以“-”连接
		ProcessesDAO procdao = new ProcessesDAO();
		for(int i=0; i<processes.length; i++) {
			int procId = Integer.parseInt(processes[i]);
			equalsMap.clear();
			equalsMap.put("Id", procId);
			List<Processes> procList = procdao.findEntity(equalsMap);
			if(procList.size() == 0) {
				return "fnone";
			}
			fp.append(procList.get(0).getProcName());
			if(i != processes.length-1) {
				fp.append("-");
			}
		}
		//inputStream = fp.toString();
		//将流程信息及对应的id号保存到request里
		ActionContext context = ActionContext.getContext();  
	    HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);  
		request.setAttribute("product", product);
	    request.setAttribute("fp",fp.toString()); 
		request.setAttribute("flowpath", flowpath);
	    return fp.toString();
	}
	
	//批次添加
	public String addBatch(Product product, Batch batch, Flowpath flowpath, String fp) {
		batch.setProId(product.getId());
		//找到对应的流程		
		batch.setFlowId(flowpath.getId());
		String[] processes = fp.split("-");
		//在工作表建立初始化的相应记录
		WorkTabDAO wdao = new WorkTabDAO();
		WorkTab wt = new WorkTab();
		wt.setProcId(Integer.parseInt(processes[0]));
		int num = wdao.create(wt);
		if(num == 0) {
			return "false";
		}
		WorkTabDAO wdao1 = new WorkTabDAO();
		batch.setWorkTabId(wdao1.count());
		for(int i=1; i<processes.length; i++) {
			WorkTabDAO wdaoi = new WorkTabDAO();
			WorkTab wti = new WorkTab();
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
			return "success";
		} else {
			return "false";
		}
	}
}
