package data.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import data.bean.Batch;
import data.bean.DisqKind;
import data.bean.Processes;
import data.bean.Product;
import data.bean.Staff;
import data.bean.User;
import data.bean.WFstandard;
import data.bean.WorkForm;
import data.dao.DisqKindDAO;
import data.dao.ProcessesDAO;
import data.dao.ProductDAO;
import data.dao.StaffDAO;
import data.dao.WorkFormDAO;
import data.list.BatchList;
import data.list.DisqKindList;
import data.list.ProcessesList;
import data.list.ProductList;
import data.list.StaffList;
import data.log.LogInfo;

public class WorkFormService {

	ActionContext actionContext = ActionContext.getContext(); 
    Map session = actionContext.getSession();
    HttpServletRequest request = (HttpServletRequest)actionContext.get(ServletActionContext.HTTP_REQUEST);
    
	//进入工单添加页面
	public String formAdd(int height) {
		DisqKindDAO dkdao = new DisqKindDAO();
		List<DisqKind> dkList = dkdao.readAll();
		System.out.println(dkList);
		User user = (User)session.get("user");
		String name = user.getUsername();
		request.setAttribute("name", name);
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
		Processes processes;
		ProcessesDAO pdao = new ProcessesDAO();
		Map<String, String> equalsMap = new HashMap<String, String>();
		equalsMap.put("procNo",procNo);
		List<Processes> list = pdao.findEntity(equalsMap);
		if(list.size() != 0) {
			processes = list.get(0);
			session.put("processes", processes);
			return processes.getProcName();
		} else {
			return "该工序不存在";
		}
	}

	public String addWorkForm() {
		System.out.println(session.size());
		Staff staff = (Staff) session.get("staff");
		Product product = (Product) session.get("product");
		Processes processes = (Processes) session.get("processes");
		System.out.println(staff);
		System.out.println(product);
		System.out.println(processes);
		return "success";
	}
	

	//查看工单详情
	public String detail() {
		String result = "";
		
		WorkFormDAO wfd = new WorkFormDAO();
		int Id = Integer.parseInt(request.getParameter("wfId"));
		String sql = "select * from workform where Id=" + Id;
		List<WorkForm> wflist = wfd.findEntityByList(sql);
		if (wflist.size() == 0) {
			return "error";
		}
		WorkForm wf = wflist.get(0);
		WFstandard wfsave = new WFstandard();
		
		String name = wf.getName();
		
		StaffList stal = new StaffList();
		sql = "select * from staff where Id=" + wf.getStaId();
		List<Staff> stalist = stal.createSQL(sql);
		ProcessesList procl = new ProcessesList();
		sql = "select * from processes where Id=" + wf.getProcId();
		List<Processes> proclist = procl.createSQL(sql);
		ProductList prol = new ProductList();
		sql = "select * from product where Id=" + wf.getProId();
		List<Product> prolist = prol.createSQL(sql);
		BatchList bl = new BatchList();
		sql = "select * from batch where Id=" + wf.getBatchId();
		List<Batch> batlist = bl.createSQL(sql);
		wfsave.setBatchNo(batlist.get(0).getBatchNo());
		wfsave.setDisqDetail(wf.getDisDetail());
		wfsave.setProcName(proclist.get(0).getProcName());
		wfsave.setProcNo(proclist.get(0).getProcNo());
		wfsave.setProName(prolist.get(0).getProName());
		wfsave.setProNo(prolist.get(0).getProNo());
		wfsave.setQuaNum(wf.getQuaNum());
		wfsave.setStaName(stalist.get(0).getStaName());
		wfsave.setStaNo(stalist.get(0).getStaNo());
		wfsave.setWfId(wf.getId());
		wfsave.setDisqNum(wf.getgWaste() + wf.getlWaste());
		Date date = wf.getDeleteTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String time = "";
		if(date != null) {
			time = df.format(date);
		}
		wfsave.setDeletetime(time);
		if (wf.getIsDelete() == 1) {
			wfsave.setStatus("是");
		} else {
			wfsave.setStatus("否");
		}
		
		List<String> listkind = new ArrayList<String>();
		List<String> listnum = new ArrayList<String>();
		Map<String, String> disqmap = new HashMap<String, String>();
		
		String[] disq = null;
		String[] disqkind = null;
		String[] disqnum = null;
		if(!wfsave.getDisqDetail().equals(":")) {
			disq = wfsave.getDisqDetail().split(":");

			if(disq[0]!="" && disq[1]!="") {
				disqkind = disq[0].split("-");
				disqnum = disq[1].split("-");
				
				for(int i=0; i<disqkind.length; i++) {
					sql = "select * from disqKind where id=" + disqkind[i];
					DisqKindList dqklist = new DisqKindList();
					List<DisqKind> list = dqklist.createSQL(sql);
					if(list.size() == 0) {
						return "error";
					} else {
						listkind.add(list.get(0).getDisDesc());
					}
					listnum.add(disqnum[i]);
					disqmap.put(list.get(0).getDisDesc(), disqnum[i]);
				}
				
			}
		} 
		/*
		String[] disq = wfsave.getDisqDetail().split(":");

		if(disq[0]!="" && disq[1]!="") {
			String[] disqkind = disq[0].split("-");
			String[] disqnum = disq[1].split("-");
			
			for(int i=0; i<disqkind.length; i++) {
				sql = "select * from disqKind where id=" + disqkind[i];
				DisqKindList dqklist = new DisqKindList();
				List<DisqKind> list = dqklist.createSQL(sql);
				if(list.size() == 0) {
					return "error";
				} else {
					listkind.add(list.get(0).getDisDesc());
				}
				listnum.add(disqnum[i]);
				disqmap.put(list.get(0).getDisDesc(), disqnum[i]);
			}
			
		}
		*/
		request.setAttribute("wfsave",wfsave);
		request.setAttribute("disqkind", listkind);
		request.setAttribute("disqnum", listnum);
		request.setAttribute("disqmap", disqmap);
		request.setAttribute("name", name);
		
		User user = (User) session.get("user");
		switch (user.getLevel()) {
		case 1:
			result = "viewer";
			break;
		case 2:
			result = "operator";
			break;
		case 3:
			result = "admin";
			break;
		}
		return result;
	}
	
	//进入修改界面
	public String preAlter() {
		String result = "";
		String disqKind1 = "";
		String disqKind2 = "";
		String disqKind3 = "";
		String disqKind4 = "";
		String disqKind5 = "";
		String disqKind6 = "";
		String disqKind7 = "";
		String disqKind8 = "";
		
		int disqNum1 = 0;
		int disqNum2 = 0;
		int disqNum3 = 0;
		int disqNum4 = 0;
		int disqNum5 = 0;
		int disqNum6 = 0;
		int disqNum7 = 0;
		int disqNum8 = 0;
		
		WorkFormDAO wfd = new WorkFormDAO();
		int Id = Integer.parseInt(request.getParameter("wfId"));
		String sql = "select * from workform where Id=" + Id;
		List<WorkForm> wflist = wfd.findEntityByList(sql);
		if (wflist.size() == 0) {
			return "false";
		}
		WorkForm wf = wflist.get(0);
		WFstandard wfsave = new WFstandard();
		
		StaffList stal = new StaffList();
		sql = "select * from staff where Id=" + wf.getStaId();
		List<Staff> stalist = stal.createSQL(sql);
		ProcessesList procl = new ProcessesList();
		sql = "select * from processes where Id=" + wf.getProcId();
		List<Processes> proclist = procl.createSQL(sql);
		ProductList prol = new ProductList();
		sql = "select * from product where Id=" + wf.getProId();
		List<Product> prolist = prol.createSQL(sql);
		BatchList bl = new BatchList();
		sql = "select * from batch where Id=" + wf.getBatchId();
		List<Batch> batlist = bl.createSQL(sql);
		wfsave.setBatchNo(batlist.get(0).getBatchNo());
		wfsave.setDisqDetail(wf.getDisDetail());
		wfsave.setProcName(proclist.get(0).getProcName());
		wfsave.setProcNo(proclist.get(0).getProcNo());
		wfsave.setProName(prolist.get(0).getProName());
		wfsave.setProNo(prolist.get(0).getProNo());
		wfsave.setQuaNum(wf.getQuaNum());
		wfsave.setStaName(stalist.get(0).getStaName());
		wfsave.setStaNo(stalist.get(0).getStaNo());
		wfsave.setWfId(wf.getId());
		wfsave.setDisqNum(wf.getgWaste() + wf.getlWaste());
		Date date = wf.getDeleteTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String time = "";
		if(date != null) {
			time = df.format(date);
		}
		wfsave.setDeletetime(time);
		if (wf.getIsDelete() == 1) {
			wfsave.setStatus("是");
		} else {
			wfsave.setStatus("否");
		}
		String[] disq;
		String[] disqkind;
		String[] disqnum;
		if(wfsave.getDisqDetail()!="" && !wfsave.getDisqDetail().equals(":")) {
			disq = wfsave.getDisqDetail().split(":");
			disqkind = disq[0].split("-");
			disqnum = disq[1].split("-");
			String disqkinds = disq[0];
			System.out.println("1:" + disqkinds);
			//Map<String, String> disqmap = new HashMap<String, String>();
			for(int i=0; i<disqkind.length; i++) {
				sql = "select * from disqKind where Id=" + Integer.parseInt(disqkind[i]);
				DisqKindList dkl = new DisqKindList();
				List<DisqKind> list =  dkl.createSQL(sql);
				if(list.size() == 0) {
					return "error";
				} else {
					switch((i+1)) {
					case 1:
						disqKind1 = list.get(0).getDisDesc();
						disqNum1 = Integer.parseInt(disqnum[i]);
						break;
					case 2:
						disqKind2 = list.get(0).getDisDesc();
						disqNum2 = Integer.parseInt(disqnum[i]);
						break;
					case 3:
						disqKind3 = list.get(0).getDisDesc();
						disqNum3 = Integer.parseInt(disqnum[i]);
						break;
					case 4:
						disqKind4 = list.get(0).getDisDesc();
						disqNum4 = Integer.parseInt(disqnum[i]);
						break;
					case 5:
						disqKind5 = list.get(0).getDisDesc();
						disqNum5 = Integer.parseInt(disqnum[i]);
						break;
					case 6:
						disqKind6 = list.get(0).getDisDesc();
						disqNum6 = Integer.parseInt(disqnum[i]);
						break;
					case 7:
						disqKind7 = list.get(0).getDisDesc();
						disqNum7 = Integer.parseInt(disqnum[i]);
						break;
					case 8:
						disqKind8 = list.get(0).getDisDesc();
						disqNum8 = Integer.parseInt(disqnum[i]);
						break;
					}
				}
			}

			
		}
		request.setAttribute("disqKind1", disqKind1);
		request.setAttribute("disqNum1", disqNum1);
		request.setAttribute("disqKind2", disqKind2);
		request.setAttribute("disqNum2", disqNum2);
		request.setAttribute("disqKind3", disqKind3);
		request.setAttribute("disqNum3", disqNum3);
		request.setAttribute("disqKind4", disqKind4);
		request.setAttribute("disqNum4", disqNum4);
		request.setAttribute("disqKind5", disqKind5);
		request.setAttribute("disqNum5", disqNum5);
		request.setAttribute("disqKind6", disqKind6);
		request.setAttribute("disqNum6", disqNum6);
		request.setAttribute("disqKind7", disqKind7);
		request.setAttribute("disqNum7", disqNum7);
		request.setAttribute("disqKind8", disqKind8);
		request.setAttribute("disqNum8", disqNum8);
		DisqKindDAO dkdao = new DisqKindDAO();
		List<DisqKind> dkList = dkdao.readAll();
		System.out.println(dkList);
		request.setAttribute("dkList", dkList);
		
		int procId = proclist.get(0).getId();
		request.setAttribute("procId", procId);
		request.setAttribute("wfId", wfsave.getWfId());
		request.setAttribute("wfsave",wfsave);
		/*request.setAttribute("disqkinds", disqkinds);*/
		request.setAttribute("disqkinds", wfsave.getDisqDetail());
		return "success";
	}
	
	//修改工单详情
	public String alter() {
		return "success";
	}
 }
