package data.service;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;

import data.bean.Batch;
import data.bean.Page;
import data.bean.Processes;
import data.bean.Product;
import data.bean.Staff;
import data.bean.User;
import data.bean.WFstandard;
import data.bean.WorkForm;
import data.dao.WorkFormDAO;
import data.list.BatchList;
import data.list.ProcessesList;
import data.list.ProductList;
import data.list.StaffList;
import data.list.WorkFormList;

public class WorkFormSearchService {

	ActionContext context = ActionContext.getContext();
	HttpServletRequest request = (HttpServletRequest) context
			.get(StrutsStatics.HTTP_REQUEST);
	Map session = context.getSession();

	private List<WFstandard> wfs;
	private List<WorkForm> work;

	/**
	 * 默认工单列表
	 * 
	 * @return
	 */
	public String FirstPage() {
		String result = "";
		// Page page = new Page();
		String sql = "select * from workform order by id desc limit 6";
		WorkFormList wfl = new WorkFormList();
		List<WFstandard> wfs = new ArrayList<WFstandard>();
		List<WorkForm> list = wfl.createSQL(sql);
		if (list.size() != 0) {
			request.setAttribute("page", list);
		} else {
			return "error";
		}
		// page.setPageNow(1);
		// 根据page查询工单
		for (int i = 0; i < list.size(); i++) {
			WFstandard wfsave = new WFstandard();
			sql = "select * from staff where Id=" + list.get(i).getStaId();
			List<Staff> stalist = stal.createSQL(sql);
			sql = "select * from processes where Id=" + list.get(i).getProcId();
			List<Processes> proclist = procl.createSQL(sql);
			sql = "select * from product where Id=" + list.get(i).getProId();
			List<Product> prolist = prol.createSQL(sql);
			sql = "select * from batch where Id=" + list.get(i).getBatchId();
			List<Batch> batlist = batl.createSQL(sql);
			wfsave.setBatchNo(batlist.get(0).getBatchNo());
			wfsave.setDisqDetail(list.get(i).getDisDetail());
			wfsave.setProcName(proclist.get(0).getProcName());
			wfsave.setProcNo(proclist.get(0).getProcNo());
			wfsave.setProName(prolist.get(0).getProName());
			wfsave.setProNo(prolist.get(0).getProNo());
			wfsave.setQuaNum(list.get(i).getQuaNum());
			System.out.println(stalist.size());
			wfsave.setStaName(stalist.get(0).getStaName());
			wfsave.setStaNo(stalist.get(0).getStaNo());
			wfsave.setWfId(list.get(i).getId());
			wfsave.setDisqNum(list.get(i).getgWaste() + list.get(i).getlWaste());
			Date date = list.get(i).getDeleteTime();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String time = "";
			if (date != null) {
				time = df.format(date);
			}
			wfsave.setDeletetime(time);
			if (list.get(i).getIsDelete() == 1) {
				wfsave.setStatus("是");
			} else {
				wfsave.setStatus("否");
			}
			wfs.add(wfsave);
		}
		/*
		 * if (workform.size() == 0) { return "error"; }
		 */
		request.setAttribute("form", wfs);
		// request.setAttribute("page", page);
	
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

	private WorkFormList workformlist = new WorkFormList();
	private StaffList stal = new StaffList();
	private ProcessesList procl = new ProcessesList();
	private ProductList prol = new ProductList();
	private BatchList batl = new BatchList();

	private List<WFstandard> workform = new ArrayList<WFstandard>();

	/**
	 * 按批次搜索工单
	 * 
	 * @return
	 */
	public String searchByBatch() {

		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(StrutsStatics.HTTP_REQUEST);
		Map session = context.getSession();

		String result = "";
		String batchNo = request.getParameter("batchNo");
		String proNo = request.getParameter("proNo");
		String staffNo = request.getParameter("staffNo");
		/*
		 * String sql = "select * from batch where batchNo='" + batchNo + "'";
		 * List<Batch> blist = batl.createSQL(sql);
		 */

		if ("".equals(batchNo)
				&& "".equals(proNo)
				&& "".equals(staffNo)
				&& !(session.get("staffNo") != null
						|| session.get("proNo") != null || session
						.get("batchNo") != null)) {

			User user = (User) session.get("user");
			switch (user.getLevel()) {
			case 1:
				result = "errorviewer";
				break;
			case 2:
				result = "erroroperator";
				break;
			case 3:
				result = "erroradmin";
				break;
			}
		} else { /*
				 * Batch batch = blist.get(0); sql =
				 * "select * from workform where batchId=" + batch.getId() +
				 * " order by procId"; List<WorkForm> wlist =
				 * workformlist.createSQL(sql);
				 */

			// request.setAttribute("form", wlist);
			List<WFstandard> wfslist = new ArrayList<WFstandard>();

			/*
			 * ActionContext context = ActionContext.getContext();
			 * HttpServletRequest request = (HttpServletRequest) context
			 * .get(StrutsStatics.HTTP_REQUEST); Map session =
			 * context.getSession();
			 */
			wfslist = Conditionsearch(staffNo, proNo, batchNo);

			request.setAttribute("form", wfslist);

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
		}
		return result;
	}

	/**
	 * 指定工单列表
	 * 
	 * @return
	 */
	public String FindPage() {
		String result = "";
		Page page = new Page();
		String pageNow = request.getParameter("page");
		if (pageNow != "") {
			page.setPageNow(Integer.parseInt(request.getParameter("page")));
		} else {
			page.setPageNow(1);
		}
		if (page.getPageNow() <= 0) {
			page.setPageNow(1);
		}
		if (page.getPageNow() >= page.getPageCount()) {
			page.setPageNow(page.getPageCount());
		}
		workform = this.WFsearch(page);
		if (workform.size() == 0) {
			return "error";
		}
		request.setAttribute("form", workform);
		request.setAttribute("page", page);

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

	// 根据搜索项搜索工单
	public String searchform() {
		String result = "";
		String sql = "";
		WFstandard wfsave = new WFstandard();
		String staNo = request.getParameter("staNo");
		String proNo = request.getParameter("proNo");
		String batNo = request.getParameter("batNo");
		workform = this.Conditionsearch(staNo, proNo, batNo);
		if (workform == null || workform.size() == 0) {
			return "error";
		}
		request.setAttribute("form", workform);

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

	/**
	 * 根据page查询工单
	 * 
	 * @param page
	 * @return
	 */
	public List<WFstandard> WFsearch(Page page) {
		WorkFormDAO wfd = new WorkFormDAO();
		String sql = "select * from workForm limit " + (page.getPageNow() - 1)
				* page.getPageCount() + "," + page.getPageNow()
				* page.getSize();
		List<WorkForm> list = wfd.findEntityByList(sql);
		System.out.println(list.size());
		wfs = new ArrayList<WFstandard>();

		for (int i = 0; i < list.size(); i++) {
			WFstandard wfsave = new WFstandard();
			sql = "select * from staff where Id=" + list.get(i).getStaId();
			List<Staff> stalist = stal.createSQL(sql);
			sql = "select * from processes where Id=" + list.get(i).getProcId();
			List<Processes> proclist = procl.createSQL(sql);
			sql = "select * from product where Id=" + list.get(i).getProId();
			List<Product> prolist = prol.createSQL(sql);
			sql = "select * from batch where Id=" + list.get(i).getBatchId();
			List<Batch> batlist = batl.createSQL(sql);
			wfsave.setBatchNo(batlist.get(0).getBatchNo());
			wfsave.setDisqDetail(list.get(i).getDisDetail());
			wfsave.setProcName(proclist.get(0).getProcName());
			wfsave.setProcNo(proclist.get(0).getProcNo());
			wfsave.setProName(prolist.get(0).getProName());
			wfsave.setProNo(prolist.get(0).getProNo());
			wfsave.setQuaNum(list.get(i).getQuaNum());
			System.out.println(stalist.size());
			wfsave.setStaName(stalist.get(0).getStaName());
			wfsave.setStaNo(stalist.get(0).getStaNo());
			wfsave.setWfId(list.get(i).getId());
			wfsave.setDisqNum(list.get(i).getgWaste() + list.get(i).getlWaste());
			Date date = list.get(i).getDeleteTime();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String time = "";
			if (date != null) {
				time = df.format(date);
			}
			wfsave.setDeletetime(time);
			if (list.get(i).getIsDelete() == 1) {
				wfsave.setStatus("是");
			} else {
				wfsave.setStatus("否");
			}
			wfs.add(wfsave);
		}
		return wfs;

	}

	public List<WFstandard> Conditionsearch(String staNo, String proNo,
			String batNo) {
		WorkFormDAO wfd = new WorkFormDAO();
		int flag = 0;
		String sql = "";
		int pId = 0;
		List<Staff> slist = new ArrayList<Staff>();
		List<Batch> stlist = new ArrayList<Batch>();
		if (!("").equals(staNo)) {
			sql = "select * from staff where staNo='" + staNo + "'";
			slist = stal.createSQL(sql);
			if (slist.size() != 0) {
				flag = flag + 1;
			} else {
				return null;
			}
		}
		if (!("").equals(proNo)) {
			sql = "select * from product where proNo='" + proNo + "'";
			List<Product> plist = prol.createSQL(sql);
			if (plist.size() != 0) {
				pId = plist.get(0).getId();
			} else {
				return null;
			}
		}

		if (pId != 0 && !("").equals(batNo)) {
			sql = "select * from batch where proId='" + pId + "' and batchNo='"
					+ batNo + "'";
			stlist = batl.createSQL(sql);
			if (stlist.size() != 0) {
				flag = flag + 2;
			} else {
				return null;
			}
		}
		switch (flag) {
		case 0:
			sql = "select * from workForm";
			break;
		case 1:
			sql = "select * from workForm where staId=" + slist.get(0).getId()
					+ " order by procId";
			break;
		case 2:
			sql = "select * from workForm where batchId="
					+ stlist.get(0).getId() + " order by procId";
			break;
		case 3:
			sql = "select * from workForm where batchId="
					+ stlist.get(0).getId() + " and staId="
					+ slist.get(0).getId() + " order by procId";
			break;
		}
		List<WorkForm> list = wfd.findEntityByList(sql);
		System.out.println(list.size());
		wfs = new ArrayList<WFstandard>();

		for (int i = 0; i < list.size(); i++) {
			WFstandard wfsave = new WFstandard();
			sql = "select * from staff where Id=" + list.get(i).getStaId();
			List<Staff> stalist = stal.createSQL(sql);
			sql = "select * from processes where Id=" + list.get(i).getProcId();
			List<Processes> proclist = procl.createSQL(sql);
			sql = "select * from product where Id=" + list.get(i).getProId();
			List<Product> prolist = prol.createSQL(sql);
			sql = "select * from batch where Id=" + list.get(i).getBatchId();
			List<Batch> batlist = batl.createSQL(sql);
			wfsave.setBatchNo(batlist.get(0).getBatchNo());
			wfsave.setDisqDetail(list.get(i).getDisDetail());
			wfsave.setProcName(proclist.get(0).getProcName());
			wfsave.setProcNo(proclist.get(0).getProcNo());
			wfsave.setProName(prolist.get(0).getProName());
			wfsave.setProNo(prolist.get(0).getProNo());
			wfsave.setQuaNum(list.get(i).getQuaNum());
			wfsave.setStaName(stalist.get(0).getStaName());
			wfsave.setStaNo(stalist.get(0).getStaNo());
			wfsave.setWfId(list.get(i).getId());
			wfsave.setDisqNum(list.get(i).getgWaste() + list.get(i).getlWaste());
			Date date = list.get(i).getDeleteTime();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String time = "";
			if (date != null) {
				time = df.format(date);
			}
			wfsave.setDeletetime(time);
			if (list.get(i).getIsDelete() == 1) {
				wfsave.setStatus("是");
			} else {
				wfsave.setStatus("否");
			}
			wfs.add(wfsave);
		}
		return wfs;
	}
}
