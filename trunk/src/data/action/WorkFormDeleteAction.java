package data.action;

import java.util.*;

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
import data.dao.BatchDAO;
import data.dao.DailyStaffDisqDAO;
import data.dao.DisqDetailDAO;
import data.dao.ProductDAO;
import data.dao.ScheduleTabDAO;
import data.dao.WorkFormDAO;
import data.dao.WorkHoursTabDAO;
import data.dao.WorkTabDAO;

public class WorkFormDeleteAction {
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

	private List<DisqDetail> disqdetaillist = new ArrayList<DisqDetail>();
	private int DisqNum;
	private int flag;
	private String state = "";

	public String execute() throws Exception {
		return null;
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
		} else if (wflist.get(0).getIsDelete() == 1) {
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
		if (state.equals("success")) {
			state = this.updateworktab(work, flow, wtab);
			if (state != "success") {
				return state;
			}
		} else {
			return state;
		}

		bat.setDisqNum(bat.getDisqNum() - DisqNum);
		bat.setDisqPercent((double) bat.getDisqNum()
				/ (double) bat.getCompleteNum());
		product.setDisqNum(product.getDisqNum() - DisqNum);
		product.setDisqPerc((double) product.getDisqNum()
				/ (double) product.getCompleteNum());
		wdao.update(work, 1);
		whtdao.delete(workhour);
		if (work.getDisDetail() != null) {
			dsdd.delete(dd);
			for (int i = 0; i < disqdetaillist.size(); i++) {
				ddd.delete(disqdetaillist.get(i));
			}
		}
		batdao.update(bat, 1);
		prodao.update(product, 1);
		wtdao.update(wtab, 1);
		if (flag == 1) {
			sdao.delete(s);
		}
		return state;
	}

	public String updatedisq(WorkForm wf) {
		String sql;
		sql = "select * from workhourstab where staId=" + wf.getStaId()
				+ " and time='" + wf.getTime() + "'";
		List<WorkHoursTab> whtlist = whtl.createSQL(sql);
		workhour = whtlist.get(0);

		if (wf.getDisDetail() == null) {
			DisqNum = 0;
		} else {
			String[] unq = wf.getDisDetail().split(":");
			String[] str = unq[0].split("-");
			sql = "select * from DailyStaffDisq where staffId=" + wf.getStaId()
					+ " and time='" + wf.getTime() + "'";
			List<DailyStaffDisq> dsdlist = dsdl.createSQL(sql);
			DisqNum = dsdlist.get(0).getTotalNum();
			int nid = dsdlist.get(0).getDisqdeId();
			dd = dsdlist.get(0);
			for (int i = 0; i < str.length; i++) {
				sql = "select * from disqKind where disDesc='" + str[i] + "'";
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
				if(i == wtlist.size() - 1){
					product.setCompleteNum(product.getCompleteNum()
							- wf.getQuaNum());
					bat.setCompleteNum(bat.getCompleteNum()
							- wf.getQuaNum());
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
		this.wtab = wtab;
		return "success";
	}
}
