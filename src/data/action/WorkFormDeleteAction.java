package data.action;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;

import data.bean.DailyStaffDisq;
import data.bean.DisqDetail;
import data.bean.Product;
import data.bean.ScheduleTab;
import data.bean.WorkForm;
import data.bean.Batch;
import data.bean.Flowpath;
import data.bean.WorkHoursTab;
import data.bean.WorkTab;
import data.bean.DisqKind;
import data.list.DisqDetailList;
import data.list.ProductList;
import data.list.ScheduleTabList;
import data.list.WorkFormList;
import data.list.BatchList;
import data.list.WorkHoursTabList;
import data.list.WorkTabList;
import data.list.FlowpathList;
import data.list.DisqKindList;
import data.list.DailyStaffDisqList;
import data.dao.BatchDAO;
import data.dao.DailyStaffDisqDAO;
import data.dao.DisqDetailDAO;
import data.dao.ProductDAO;
import data.dao.ScheduleTabDAO;
import data.dao.WorkFormDAO;
import data.dao.WorkHoursTabDAO;
import data.dao.WorkTabDAO;

public class WorkFormDeleteAction extends BaseAction {

	ActionContext context = ActionContext.getContext();
	HttpServletRequest request = (HttpServletRequest) context
			.get(StrutsStatics.HTTP_REQUEST);
	Map session = context.getSession();
	
	private WorkForm work = new WorkForm();
	private Batch bat = new Batch();
	private Flowpath flow = new Flowpath();
	private WorkTab wtab = new WorkTab();
	private ScheduleTab s = new ScheduleTab();
	private DailyStaffDisq dd = new DailyStaffDisq();
	private Product product = new Product();
	private WorkHoursTab workhour = new WorkHoursTab();

	private WorkFormList wfl = new WorkFormList();
	private BatchList bl = new BatchList();
	private DisqKindList dkl = new DisqKindList();
	private FlowpathList fl = new FlowpathList();
	private WorkTabList wtl = new WorkTabList();
	private ScheduleTabList schl = new ScheduleTabList();
	private DailyStaffDisqList dsdl = new DailyStaffDisqList();
	private DisqDetailList ddl = new DisqDetailList();
	private ProductList prol = new ProductList();
	private WorkHoursTabList whtl = new WorkHoursTabList();
	private int wfId;

	private List<DisqDetail> disqdetaillist = new ArrayList<DisqDetail>();
	private int DisqNum;
	private int flag;//1:删除进度表；2：更新completeNum
	private String state = "";


	public void setWfId(int wfId) {
		this.wfId = wfId;
	}

	public int getWfId() {
		return wfId;
	}
	
	public String execute(){
		return null;
	}

	//删除工单
	public String wfDelete() {

		int wfId;
		
		if(request.getParameter("wfId")==null || request.getParameter("wfId")=="") {
			return "false";
		} else {
			wfId = Integer.parseInt(request.getParameter("wfId"));
		}
		return this.WFdelete(wfId, false);
	}
	
	public String WFdelete(int Id, boolean alter) {
		WorkFormDAO wdao = new WorkFormDAO();
		BatchDAO batdao = new BatchDAO();
		DailyStaffDisqDAO dsdd = new DailyStaffDisqDAO();
		DisqDetailDAO ddd = new DisqDetailDAO();
		WorkTabDAO wtdao = new WorkTabDAO();
		ScheduleTabDAO sdao = new ScheduleTabDAO();
		ProductDAO prodao = new ProductDAO();
		WorkHoursTabDAO whtdao = new WorkHoursTabDAO();
		String sql;
		work.setId(Id);
		sql = "select * from workform where Id=" + work.getId();
		List<WorkForm> wflist = wfl.createSQL(sql);
		if (wflist.size() == 0) {
			return "error";
		} else if (wflist.get(0).getIsDelete() == 1) {	//判断该工单是否删除
			return "isdelete";
		}
		work = wflist.get(0);
		if (alter == false) {
			work.setDeleteTime(new Date());
			work.setIsDelete(1);
		}
		sql = "select * from product where Id=" + work.getProId();
		List<Product> prolist = prol.createSQL(sql);
		product = prolist.get(0);
		sql = "select * from batch where Id=" + work.getBatchId();
		List<Batch> batlist = bl.createSQL(sql);
		bat = batlist.get(0);
		flow.setId(batlist.get(0).getFlowId());
		state = this.updatedisq(work);
		if (state.equals("success") || state.equals("outofline")) {
			state = this.updateworktab(work, flow, wtab);
			if (state.equals("success") == false
					&& state.equals("outofline") == false) {
				return state;
			}
		} else {
			return state;
		}

		bat.setDisqNum( bat.getDisqNum() - DisqNum);
		if (bat.getCompleteNum() != 0 || bat.getDisqNum() != 0) {
			bat.setDisqPercent((double) bat.getDisqNum()
					/ ((double) bat.getCompleteNum() + (double) bat
							.getDisqNum()));
		}
		product.setDisqNum(product.getDisqNum() - DisqNum);
		if (product.getCompleteNum() != 0 || product.getDisqNum() != 0) {
			product.setDisqPerc((double) product.getDisqNum()
					/ ((double) product.getCompleteNum() + (double) product
							.getDisqNum()));
		}
		wdao.update(work, 1);
		whtdao.delete(workhour);
		if (work.getDisDetail() != null) {
			dsdd.delete(dd);
			for (int i = 0; i < disqdetaillist.size(); i++) {
				ddd = new DisqDetailDAO();
				ddd.delete(disqdetaillist.get(i));
			}
		}
		if (state.equals("success")) {
			bat.setStatus(0);
		}
		batdao = new BatchDAO();
		batdao.update(bat, 1);
		prodao = new ProductDAO();
		prodao.update(product, 1);
		wtdao = new WorkTabDAO();
		wtdao.update(wtab, 1);
		if (flag == 1) {
			sdao = new ScheduleTabDAO();
			sdao.delete(s);
		}
		return state;
	}

	public String updatedisq(WorkForm wf) {
		String sql;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sql = "select * from workhourstab where staId=" + wf.getStaId()
				+ " and time='" + sdf.format(wf.getTime()) + "'";
		List<WorkHoursTab> whtlist = whtl.createSQL(sql);
		if(whtlist.size()==0) {
			return "error";
		}
		workhour = whtlist.get(0);

		if (wf.getDisDetail() == null) {
			DisqNum = 0;
		} else {
			String[] unq;
			String[] str;
			if(!wf.getDisDetail().equals(":")) {
				unq = wf.getDisDetail().split(":");
				str = unq[0].split("-");
				sql = "select * from DailyStaffDisq where staffId=" + wf.getStaId()
						+ " and time='" + wf.getTime() + "'";
				List<DailyStaffDisq> dsdlist = dsdl.createSQL(sql);
				if(dsdlist.size() != 0) {
					DisqNum = dsdlist.get(0).getTotalNum();
					int nid = dsdlist.get(0).getDisqdeId();
					dd = dsdlist.get(0);
					for (int i = 0; i < str.length; i++) {
						sql = "select * from disqKind where Id='" + str[i] + "'";
						List<DisqKind> dklist = dkl.createSQL(sql);
						if (dklist.size() == 0) {
							return "error";
						}

						sql = "select * from disqdetail where Id=" + nid;
						List<DisqDetail> dislist = ddl.createSQL(sql);
						nid = dislist.get(0).getNextId();
						disqdetaillist.add(dislist.get(0));
					}
				} 
			}
			
		}
		return "success";
	}

	public String updateworktab(WorkForm wf, Flowpath flow, WorkTab wtab) {
		String sql;
		int flowNum;
		sql = "select * from flowpath where Id=" + flow.getId();
		List<Flowpath> flowlist = fl.createSQL(sql);
		flow.setSequence(flowlist.get(0).getSequence());
		String[] str = flow.getSequence().split("-");// 注意分隔号
		flowNum = str.length;
		int k = bat.getWorkTabId() + flowNum;
		sql = "select * from worktab where Id between " + flow.getId()
				+ " and " + k;
		List<WorkTab> wtlist = wtl.createSQL(sql);
		for (int i = 0; i < wtlist.size(); i++) {
			if (wf.getProcId() == wtlist.get(i).getProcId()) {
				wtab = wtlist.get(i);
				wtab.setQuNum(wtlist.get(i).getQuNum() - wf.getQuaNum());
				wtab.setDisqNum(wtlist.get(i).getDisqNum() - DisqNum);
				if (i != wtlist.size() - 1
						&& wtab.getQuNum() < wtlist.get(i + 1).getQuNum()
								+ wtlist.get(i + 1).getDisqNum()) {
					return "outofline";
				}
				if (i == wtlist.size() - 1) {
					product.setCompleteNum(product.getCompleteNum()
							- wf.getQuaNum());
					bat.setCompleteNum(bat.getCompleteNum() - wf.getQuaNum());
				}
				if (wtab.getIsOver() == 1) {
					flag = 1;
					wtab.setIsOver(0);
					wtab.setOverTime(null);
					sql = "select * from scheduletab where wtId="
							+ wtab.getId();
					List<ScheduleTab> stlist = schl.createSQL(sql);

					s = stlist.get(0);
				}
			}
		}
		for (int i = 0; i < wtlist.size() - 1; i++) {
			if (wtlist.get(i).getQuNum() < wtlist.get(i + 1).getQuNum()
					+ wtlist.get(i + 1).getDisqNum()) {
				return "outofline";
			}
		}
		this.wtab = wtab;
		return "success";
	}

}
