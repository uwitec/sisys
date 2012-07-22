package data.action;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;

import data.bean.Page;
import data.bean.WFstandard;
import data.service.WorkFormSearchService;

public class WorkFormSearchAction extends BaseAction {
	/**
	 * @jianghan
	 */
	
	private static final long serialVersionUID = 1L;
	private int page;
	private List<WFstandard> workform = new ArrayList<WFstandard>();
	WorkFormSearchService wfsearch = new WorkFormSearchService();

	public String execute() throws Exception {
		return null;
	}
	
	//按批次搜索工单
	public String searchByBatch() {
		return wfsearch.searchByBatch();
	}

	//默认工单列表
	public String Firstpage() {
		return wfsearch.FirstPage();
	}

	//指定工单列表
	public String FindPage() {
		return wfsearch.FindPage();
	}
	//查询工单列表
	public String SearchForm() {
		return wfsearch.searchform();
	}
}
