package data.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import data.bean.Batch;
import data.bean.WorkForm;
import data.bean.WorkHoursTab;
import data.bean.ScheduleTab;
import data.service.SearchJdService;
import data.service.SearchPd2Service;
import data.service.SearchPd4Service;
import data.service.SearchPd5Service;
import data.service.SearchPdService;
import data.service.SearchPpService;
import data.service.SearchWhService;

public class SearchAction extends BaseAction {
	/**
	 * Ganjun
	 */
	private static final long serialVersionUID = 1L;
	// 工时表参数
	private String staNo;
	private String startTime;
	private String endTime;
	// 进度表参数
	private String proNo;
	private String batchNo;
	// 部门表参数
	private String deptNo;
	// 生产线参数
	private String lineNo;
	// 工时表输出
	Map<String, Object> mapWh = new HashMap<String, Object>();
	List<WorkHoursTab> listWh = new ArrayList<WorkHoursTab>();
	// 进度表输出
	Map<String, Object> mapJd = new HashMap<String, Object>();
	List<ScheduleTab> listJd = new ArrayList<ScheduleTab>();
	ActionContext ac = ActionContext.getContext();
	// 员工表输出
	Map<String, Object> mapPp = new HashMap<String, Object>();
	List<WorkForm> listPp = new ArrayList<WorkForm>();
	// 不合格产品统计输出
	Map<String, Object> mapPd = new HashMap<String, Object>();
	List<Batch> listPd = new ArrayList<Batch>();
	// 不合格批次输出
	Map<String, Object> mapPd2 = new HashMap<String, Object>();
	// List<DisqBatch> listPd2 =new ArrayList<DisqBatch>();
	// 不合格部门输出
	Map<String, Object> mapPd4 = new HashMap<String, Object>();
	// 不合格生产线输出
	Map<String, Object> mapPd5 = new HashMap<String, Object>();

	@SuppressWarnings("unchecked")
	public String SearchWh() throws Exception {
		staNo = request.getParameter("staNo");
		startTime = request.getParameter("startTime");
		endTime = request.getParameter("endTime");
		System.out.println(staNo);

		SearchWhService searchWhService = new SearchWhService();

		mapWh = searchWhService.Search(staNo, startTime, endTime);
		if (mapWh != null) {
			listWh = (List<WorkHoursTab>) mapWh.get("list");
			ActionContext.getContext().put("Whsheet", mapWh.get("list"));
			ActionContext.getContext().put("staName", mapWh.get("staName"));
			ActionContext.getContext().put("sTime", startTime);
			ActionContext.getContext().put("eTime", endTime);
			return SUCCESS;
		} else
			return ERROR;

	}

	@SuppressWarnings("unchecked")
	public String SearchJd() throws Exception {
		proNo = request.getParameter("proNo");
		batchNo = request.getParameter("batchNo");
		startTime = request.getParameter("startTime");
		System.out.println(proNo);

		SearchJdService searchJdService = new SearchJdService();

		mapJd = searchJdService.SearchJd(proNo, startTime, batchNo);

		if (mapJd != null) {
			listJd = (List<ScheduleTab>) mapJd.get("list");
			String sTime = listJd.get(0).getTime().toString();
			String eTime = listJd.get(listJd.size() - 1).getTime().toString();
			ActionContext.getContext().put("Jdsheet", mapJd.get("list"));
			ActionContext.getContext().put("proName", mapJd.get("proName"));
			ActionContext.getContext().put("sTime", sTime);
			ActionContext.getContext().put("eTime", eTime);
			ActionContext.getContext().put("proNo", proNo);
			ActionContext.getContext().put("batchNo", batchNo);
			ActionContext.getContext().put("status", mapWh.get("status"));
			return SUCCESS;
		} else
			return ERROR;

	}

	public String SearchPp() throws Exception {

		staNo = request.getParameter("staNo");
		startTime = request.getParameter("startTime");
		endTime = request.getParameter("endTime");
		System.out.println(staNo);

		SearchPpService searchPpService = new SearchPpService();
		mapPp = searchPpService.SearchPp(staNo, startTime, endTime);

		if (mapPp != null) {
			
			ActionContext.getContext().put("Ppsheet", mapPp.get("list"));
			ActionContext.getContext().put("staName", mapPp.get("staName"));
			ActionContext.getContext().put("sTime", startTime);
			ActionContext.getContext().put("eTime", endTime);
			ActionContext.getContext().put("staNo", staNo);
			ActionContext.getContext().put("deptName", mapPp.get("deptName"));
			
			return SUCCESS;
		} else
			return ERROR;
	}

	// 不合格产品统计
	@SuppressWarnings("unchecked")
	public String SearchPd() throws Exception {
		proNo = request.getParameter("proNo");
		startTime = request.getParameter("startTime");
		endTime = request.getParameter("endTime");
		System.out.println(proNo);

		SearchPdService searchPdService = new SearchPdService();

		mapPd = searchPdService.SearchPd(proNo, startTime, endTime);

		if (mapPd != null) {
			listPd = (List<Batch>) mapPd.get("list");

			ActionContext.getContext().put("Pdsheet", mapPd.get("list"));
			ActionContext.getContext().put("proName", mapPd.get("proName"));
			ActionContext.getContext().put("sTime", startTime);
			ActionContext.getContext().put("eTime", endTime);
			ActionContext.getContext().put("TcompleteNum",
					mapPd.get("completeNum").toString());
			ActionContext.getContext().put("TdisqNum",
					mapPd.get("disqNum").toString());
			ActionContext.getContext().put("TdisqPercent",
					mapPd.get("disqPercent").toString());
			return SUCCESS;
		} else
			return ERROR;
	}

	// 不合格批次统计
	public String SearchPd2() throws Exception {
		batchNo = request.getParameter("batchNo");
		proNo = request.getParameter("proNo");
		startTime = request.getParameter("startTime");
		endTime = request.getParameter("endTime");
		System.out.println(batchNo);

		SearchPd2Service searchPd2Service = new SearchPd2Service();

		mapPd2 = searchPd2Service.SearchPd2(batchNo, proNo, startTime, endTime);

		if (mapPd2 != null) {
			// listPd2=(List<DisqBatch>) mapPd2.get("list");

			ActionContext.getContext().put("sTime", startTime);
			ActionContext.getContext().put("eTime", endTime);
			ActionContext.getContext().put("batchNo", batchNo);
			ActionContext.getContext().put("proName", mapPd2.get("proName"));
			ActionContext.getContext().put("Pd2sheet", mapPd2.get("list"));
			ActionContext.getContext().put("TcompleteNum",
					mapPd2.get("completeNum").toString());
			ActionContext.getContext().put("TdisqNum",
					mapPd2.get("disqNum").toString());
			ActionContext.getContext().put("TdisqPercent",
					mapPd2.get("disqPercent").toString());
			return SUCCESS;
		} else
			return ERROR;
	}

	// 不合格产品员工统计

	/*@SuppressWarnings("unchecked")
	public String SearchPd3() throws Exception {
		staNo = request.getParameter("staNo");
		startTime = request.getParameter("startTime");
		endTime = request.getParameter("endTime");
		System.out.println(staNo);

		SearchPd3Service searchPd3Service = new SearchPd3Service();

		mapPd2 = searchPd3Service.SearchPd3(staNo, startTime, endTime);

		if (mapPd2 != null) {
			listPd2 = (List<ScheduleTab>) mapPd2.get("list");
			String sTime = listJd.get(0).getTime().toString();
			String eTime = listJd.get(listJd.size() - 1).getTime().toString();
			ActionContext.getContext().put("Jdsheet", mapJd.get("list"));
			ActionContext.getContext().put("proName", mapJd.get("proName"));
			ActionContext.getContext().put("sTime", sTime);
			ActionContext.getContext().put("eTime", eTime);
			ActionContext.getContext().put("proNo", proNo);
			ActionContext.getContext().put("batchNo", batchNo);
			ActionContext.getContext().put("status", mapWh.get("status"));
			return SUCCESS;
		} else
			return ERROR;
	}*/

	// 不合格产品部门统计
	public String SearchPd4() throws Exception {
		deptNo = request.getParameter("deptNo");
		startTime = request.getParameter("startTime");
		endTime = request.getParameter("endTime");
		System.out.println(deptNo);

		SearchPd4Service searchPd4Service = new SearchPd4Service();

		mapPd4 = searchPd4Service.SearchPd4(deptNo, startTime, endTime);

		if (mapPd4 != null) {

			ActionContext.getContext().put("Pd4sheet", mapPd4.get("list"));
			ActionContext.getContext().put("deptName", mapPd4.get("deptName"));
			ActionContext.getContext().put("sTime", startTime);
			ActionContext.getContext().put("eTime", endTime);
			ActionContext.getContext().put("TcompleteNum",
					mapPd4.get("completeNum").toString());
			ActionContext.getContext().put("TdisqNum",
					mapPd4.get("disqNum").toString());
			ActionContext.getContext().put("TdisqPercent",
					mapPd4.get("disqPercent").toString());
			return SUCCESS;
		} else
			return ERROR;
	}

	// 不合格产生产线门统计

	public String SearchPd5() throws Exception {
		lineNo = request.getParameter("lineNo");
		startTime = request.getParameter("startTime");
		endTime = request.getParameter("endTime");
		System.out.println(lineNo);

		SearchPd5Service searchPd5Service = new SearchPd5Service();

		mapPd5 = searchPd5Service.SearchPd5(lineNo, startTime, endTime);

		if (mapPd5 != null) {

			ActionContext.getContext().put("Pd5sheet", mapPd5.get("list"));
			ActionContext.getContext().put("lineDesc", mapPd5.get("lineDesc"));
			ActionContext.getContext().put("sTime", startTime);
			ActionContext.getContext().put("eTime", endTime);
			ActionContext.getContext().put("TcompleteNum",
					mapPd5.get("completeNum").toString());
			ActionContext.getContext().put("TdisqNum",
					mapPd5.get("disqNum").toString());
			ActionContext.getContext().put("TdisqPercent",
					mapPd5.get("disqPercent").toString());
			return SUCCESS;
		} else
			return ERROR;
	}

	public void setStahNo(String staNo) {
		this.staNo = staNo;
	}

	public String getStaNo() {
		return staNo;
	}

}
