package data.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import data.bean.DisqKind;
import data.bean.Processes;
import data.bean.Product;
import data.bean.Staff;
import data.bean.User;
import data.dao.DisqKindDAO;
import data.dao.ProcessesDAO;
import data.dao.ProductDAO;
import data.dao.StaffDAO;
import data.log.LogInfo;

public class WorkFormService {

	ActionContext actionContext = ActionContext.getContext(); 
    Map session = actionContext.getSession();
    HttpServletRequest request = (HttpServletRequest)actionContext.get(ServletActionContext.HTTP_REQUEST);
    
	//进入工单添加页面
	public String formAdd(int height) {
		DisqKindDAO dkdao = new DisqKindDAO();
		List<DisqKind> dkList = dkdao.readAll();
		request.setAttribute("dkList", dkList);
		request.setAttribute("height", height);
		return "success";
	}
	
	//增加不合格品种类
	public String addDisqKind(DisqKind disqKind) {
		disqKind.setId(0);
		System.out.println(disqKind);
		DisqKindDAO dkdao = new DisqKindDAO();
		int num = dkdao.create(disqKind);
		if(num == 0) {
			return "false";
		} else {
			
			//记录操作
			LogInfo logInfo = new LogInfo();
		    User user1 = (User)session.get("user");
		    String content = "管理员" + user1.getUsername() + "增加不合格品种类。种类名称：" + disqKind.getDisDesc()
		    			+ ",种类分类：" + (disqKind.getKind()==0 ? "工废" : "料废");
		    logInfo.saveLog(user1, content, System.currentTimeMillis());
		    
			return "success";
		}
	}
	
	//根据产品编号搜索对应产品名称
	public String preAddProNo(String proNo) {
		Product product;
		ProductDAO pdao = new ProductDAO();
		Map<String, String> equalsMap = new HashMap<String, String>();
		equalsMap.put("proNo",proNo);
		List<Product> list = pdao.findEntity(equalsMap);
		if(list.size() != 0) {
			product = list.get(0);
			session.put("product", product);
			return product.getProName();
		} else {
			return "该产品不存在";
		}
	}

	//根据员工编号搜索对应员工姓名
	public String preAddStaNo(String staNo) {
		Staff staff;
		StaffDAO sdao = new StaffDAO();
		Map<String, String> equalsMap = new HashMap<String, String>();
		equalsMap.put("staNo",staNo);
		List<Staff> list = sdao.findEntity(equalsMap);
		if(list.size() != 0) {
			staff = list.get(0);
			session.put("staff", staff);
			return staff.getStaName();
		} else {
			return "该员工不存在";
		}
	}

	//根据工序编号搜索对应工序名称
	public String preAddProcNo(String procNo) {
		Processes Processes;
		ProcessesDAO pdao = new ProcessesDAO();
		Map<String, String> equalsMap = new HashMap<String, String>();
		equalsMap.put("procNo",procNo);
		List<Processes> list = pdao.findEntity(equalsMap);
		if(list.size() != 0) {
			Processes = list.get(0);
			session.put("Processes", Processes);
			return Processes.getProcName();
		} else {
			return "该工序不存在";
		}
	}

 }
