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

/**
 * 基于用ajax实现了动态显示工单的情况下 即已知工单员工id，产品id，工序id，工序颜色 实现了工单的存储及数据库的修改功能
 * 
 */
public class WorkformAddAction {

	private WorkForm work = new WorkForm();
	private Batch bat = new Batch();
	private Flowpath flow = new Flowpath();
	private WorkTab wtab = new WorkTab();
	private ScheduleTab s = new ScheduleTab();
	private DailyStaffDisq dd = new DailyStaffDisq();
	private Product product = new Product();
	private WorkHoursTab workhour = new WorkHoursTab();

	private BatchList bl = new BatchList();
	private DisqKindList dkl = new DisqKindList();
	private FlowpathList fl = new FlowpathList();
	private WorkTabList wtl = new WorkTabList();
	private WorkFormList wfl = new WorkFormList();
	private ScheduleTabList schl = new ScheduleTabList();
	private DailyStaffDisqList dsdl = new DailyStaffDisqList();
	private DisqDetailList ddl = new DisqDetailList();
	private ProductList prol = new ProductList();
	private WorkHoursTabList whtl = new WorkHoursTabList();

	private List<DisqDetail> disqdetaillist = new ArrayList<DisqDetail>();
	private int DisqNum;
	private int AimNum;
	private int pn;// 班产量，由页面读出
	private double salary;// 工序，由页面读出
	private String color = "";
	private String state = "";

	public String execute() throws Exception {
		return null;
	}

	public String WFadd(String staId, String proId, String procId,
			String quaNum, String disDetail, String bn, String color,
			String salary) {
		if (staId == null || proId == null || procId == null || quaNum == null) {
			return "isnull";
		}
		WorkFormDAO wdao = new WorkFormDAO();
		BatchDAO batdao = new BatchDAO();
		WorkTabDAO wtdao = new WorkTabDAO();
		ScheduleTabDAO sdao = new ScheduleTabDAO();
		DisqDetailDAO ddd = new DisqDetailDAO();
		DailyStaffDisqDAO dsdd = new DailyStaffDisqDAO();
		ProductDAO prodao = new ProductDAO();
		WorkHoursTabDAO whtdao = new WorkHoursTabDAO();
		String sql;
		Date dat = new Date();
		pn = Integer.parseInt(bn);
		this.color = color;
		this.salary = Double.parseDouble(salary);
		work.setTime(dat);
		work.setStaId(Integer.parseInt(staId));
		work.setProId(Integer.parseInt(proId));
		work.setProcId(Integer.parseInt(procId));
		work.setQuaNum(Integer.parseInt(quaNum));
		work.setgWaste(0);
		work.setlWaste(0);
		work.setDisDetail(disDetail);
		sql = "select * from DailyStaffDisq where staffId=" + work.getStaId()
				+ " and time='" + work.getTime() + "'";
		List<DailyStaffDisq> dsdlist = dsdl.createSQL(sql);
		if (dsdlist.size() == 0) {
			return "personlimit";
		}
		sql = "select * from product where Id=" + work.getProId();
		List<Product> prolist = prol.createSQL(sql);
		product = prolist.get(0);
		sql = "select * from workform order by Id DESC limit 0,1";
		List<WorkForm> wflist = wfl.createSQL(sql);
		if (wflist.size() == 0) {
			work.setId(1);
		} else {
			work.setId(wflist.get(0).getId() + 1);
		}
		sql = "select * from batch where batchNo='" + bn + "' and proId="
				+ proId;
		List<Batch> batlist = bl.createSQL(sql);
		if (batlist.size() == 0) {
			return "error";
		} else if (batlist.get(0).getStatus() == 4) {
			return "batcherror";
		} else if (batlist.get(0).getStatus() == 1) {
			return "outofline";
		}
		bat = batlist.get(0);
		work.setBatchId(batlist.get(0).getId());
		flow.setId(batlist.get(0).getFlowId());
		wtab.setId(batlist.get(0).getWorkTabId());
		AimNum = batlist.get(0).getTotalNum();
		state = this.updatedisq(work);
		if (state.equals("success")) {
			state = this.updateworktab(work, flow, wtab);
			if (state != "success") {
				return state;
			}
		} else {
			return state;
		}
		bat.setDisqNum(DisqNum + bat.getDisqNum());
		bat.setDisqPercent((double) bat.getDisqNum()
				/ (double) bat.getCompleteNum());
		product.setDisqNum(product.getDisqNum() + DisqNum);
		product.setDisqPerc((double) product.getDisqNum()
				/ (double) product.getCompleteNum());
		wdao.create(work);
		batdao.update(bat, bat.getId());
		// sddao.update(staff, 1);
		wtdao.update(wtab, 1);
		sdao.create(s);
		prodao.update(product, 1);
		whtdao.create(workhour);
		for (int i = 0; i < disqdetaillist.size(); i++) {
			ddd.create(disqdetaillist.get(i));
		}
		dsdd.create(dd);
		return state;
	}

	public String updatedisq(WorkForm wf) {
		String sql;
		sql = "select * from workhourstab order by Id DESC limit 0,1";
		List<WorkHoursTab> whtlist = whtl.createSQL(sql);
		if (whtlist.size() != 0) {
			workhour.setId(whtlist.get(0).getId() + 1);
		} else {
			workhour.setId(1);
		}
		workhour.setStaId(wf.getStaId());
		workhour.setTime(new Date());
		workhour.setWorkHours(wf.getQuaNum() * 8.0 / pn);
		workhour.setSalary(workhour.getWorkHours() * salary);
		if (wf.getDisDetail() == null) {
			DisqNum = 0;
		} else {
			String[] unq = wf.getDisDetail().split(":");
			String[] str = unq[0].split("-");
			String[] std = unq[1].split("-");

			sql = "select * from disqdetail order by Id DESC limit 0,1";
			List<DisqDetail> dislist = ddl.createSQL(sql);
			sql = "select * from dailystaffdisq order by Id DESC limit 0,1";
			List<DailyStaffDisq> dsdlist = dsdl.createSQL(sql);

			for (int i = 0; i < str.length; i++) {
				sql = "select * from disqKind where disDesc='" + str[i] + "'";
				List<DisqKind> dklist = dkl.createSQL(sql);
				if (dklist.size() == 0) {
					return "error";
				} else if (dklist.get(0).getKind() == 1) {
					work.setlWaste(work.getlWaste() + Integer.parseInt(std[i]));
				} else {
					work.setgWaste(work.getgWaste() + Integer.parseInt(std[i]));
				}

				DisqDetail d = new DisqDetail();

				if (i == 0) {
					if (dislist.size() == 0) {
						dd.setDisqdeId(1);
					} else {
						dd.setDisqdeId(dislist.get(0).getId() + 1);
					}
					d.setNextId(dislist.get(0).getId() + 2);
					if (dsdlist.size() == 0) {
						dd.setId(1);
					} else {
						dd.setId(dsdlist.get(0).getId() + 1);
					}
					dd.setStaffId(wf.getStaId());
					dd.setTime(new Date());
					dd.setTotalNum(Integer.parseInt(std[i]));
					for (int j = 0; j < std.length; j++) {// 计算不合格品数量
						dd.setTotalNum(dd.getTotalNum()
								+ Integer.parseInt(std[i]));
					}
					DisqNum = dd.getTotalNum();
				} else if (i == str.length - 1) {
					d.setNextId(0);
				} else {
					d.setNextId(dislist.get(0).getId() + 2 + i);// 需要修改id，改为disqDetail的记录数目+1+i
				}
				d.setId(dd.getDisqdeId() + i);
				d.setNum(Integer.parseInt(std[i]));
				d.setDisKId(dklist.get(0).getId());
				disqdetaillist.add(d);
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
		int k = wtab.getId() + flowNum;
		sql = "select * from worktab where Id between " + flow.getId()
				+ " and " + k;
		List<WorkTab> wtlist = wtl.createSQL(sql);
		for (int i = 0; i < wtlist.size(); i++) {
			if (wf.getProcId() == wtlist.get(i).getProcId()) {
				wtab.setId(wtlist.get(i).getId());
				wtab.setQuNum(wtlist.get(i).getQuNum() + wf.getQuaNum());
				wtab.setDisqNum(wtlist.get(i).getDisqNum() + DisqNum);
				if (i != 0
						&& wtab.getQuNum() + wtab.getDisqNum() > wtlist.get(
								i - 1).getQuNum()) {
					return "outofline";
				}
				wtab.setIsEnd(wtlist.get(i).getIsEnd());
				wtab.setProcId(wtlist.get(i).getProcId());
				wtab.setIsOver(0);
				if (i == 0) {
					if (wtab.getQuNum() + wtab.getDisqNum() > AimNum) {
						return "outofline";
					}
					if (wtab.getQuNum() + wtab.getDisqNum() == AimNum) {
						wtab.setIsOver(1);
						wtab.setOverTime(new Date());

						sql = "select * from scheduletab order by Id DESC limit 0,1";
						List<ScheduleTab> stlist = schl.createSQL(sql);

						if (stlist.size() == 0) {
							s.setId(1);
						} else {
							s.setId(stlist.get(0).getId() + 1);
						}
						s.setWtId(wtab.getId());
						s.setBatchId(wf.getBatchId());
						s.setTime(wf.getTime());
						s.setColorNo(color);
						s.setNum(wtab.getQuNum());

					}
				} else {
					if (i == wtlist.size() - 1) {
						product.setCompleteNum(product.getCompleteNum()
								+ wf.getQuaNum());
						bat.setCompleteNum(bat.getCompleteNum()
								+ wf.getQuaNum());
					}
					if (wtab.getQuNum() + wtab.getDisqNum() == wtlist
							.get(i - 1).getQuNum()
							&& wtlist.get(i - 1).getIsOver() == 1) {
						wtab.setIsOver(1);
						wtab.setOverTime(new Date());
						if (i == wtlist.size() - 1 && bat.getStatus() == 0) {
							bat.setStatus(1);
						}

						sql = "select * from scheduletab order by Id DESC limit 0,1";
						List<ScheduleTab> stlist = schl.createSQL(sql);

						if (stlist.size() == 0) {
							s.setId(1);
						} else {
							s.setId(stlist.get(0).getId() + 1);
						}
						s.setBatchId(wf.getBatchId());
						s.setWtId(wtab.getId());
						s.setTime(wf.getTime());
						s.setColorNo(color);
						s.setNum(wtab.getQuNum());
					}
				}
				break;
			}
		}
		this.wtab = wtab;
		return "success";
	}
}
