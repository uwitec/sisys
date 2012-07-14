package data.action;

import java.util.*;

import data.bean.Batch;
import data.bean.DailyStaffDisq;
import data.bean.DisqDetail;
import data.bean.DisqKind;
import data.bean.Flowpath;
import data.bean.Processes;
import data.bean.Product;
import data.bean.ScheduleTab;
import data.bean.StaffDetail;
import data.bean.WorkForm;
import data.bean.WorkHoursTab;
import data.bean.WorkTab;
import data.dao.BatchDAO;
import data.dao.DailyStaffDisqDAO;
import data.dao.DisqDetailDAO;
import data.dao.ScheduleTabDAO;
import data.dao.StaffDetailDAO;
import data.dao.WorkFormDAO;
import data.dao.WorkTabDAO;
import data.list.BatchList;
import data.list.DailyStaffDisqList;
import data.list.DisqDetailList;
import data.list.DisqKindList;
import data.list.FlowpathList;
import data.list.ProcessesList;
import data.list.ProductList;
import data.list.ScheduleTabList;
import data.list.WorkFormList;
import data.list.WorkHoursTabList;
import data.list.WorkTabList;

public class WorkFormAlterAction {
	private WorkForm worksave = new WorkForm();
	private WorkForm work = new WorkForm();
	private Batch bat = new Batch();
	private Flowpath flow = new Flowpath();
	private WorkTab wtab = new WorkTab();
	private StaffDetail staff = new StaffDetail();
	private ScheduleTab s = new ScheduleTab();
	private DailyStaffDisq dd = new DailyStaffDisq();
	private DailyStaffDisq ddsave = new DailyStaffDisq();
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
	private ProcessesList pl = new ProcessesList();
	private ProductList prol = new ProductList();
	private WorkHoursTabList whtl = new WorkHoursTabList();

	private List<DisqDetail> disqdetaillist = new ArrayList<DisqDetail>();
	private List<DisqDetail> disqdetaillistsave = new ArrayList<DisqDetail>();
	private int DisqNum;
	private int DisqNumsave;
	private int AimNum;
	private int flag;
	private String color = "";
	private String state = "";

	public String execute() throws Exception {
		return null;
	}

	public String WFalter(String Id, String procId, String quaNum,
			String disDetail) {
		WorkFormDAO wdao = new WorkFormDAO();
		DailyStaffDisqDAO dsdd = new DailyStaffDisqDAO();
		DisqDetailDAO ddd = new DisqDetailDAO();
		StaffDetailDAO sddao = new StaffDetailDAO();
		BatchDAO batdao = new BatchDAO();
		WorkTabDAO wtdao = new WorkTabDAO();
		ScheduleTabDAO sdao = new ScheduleTabDAO();
		WorkformAddAction wfaa = new WorkformAddAction();
		WorkFormDeleteAction wfda = new WorkFormDeleteAction();
		String sql;
		work.setId(Integer.parseInt(Id));
		sql = "select * from workform where Id=" + work.getId();
		List<WorkForm> wflist = wfl.createSQL(sql);
		if (wflist.size() == 0) {
			return "error";
		}
		worksave = wflist.get(0);
		sql = "select * from batch where Id=" + worksave.getBatchId();
		List<Batch> batlist = bl.createSQL(sql);
		bat = batlist.get(0);
		flow.setId(batlist.get(0).getFlowId());
		work = worksave;
		work.setgWaste(0);
		work.setlWaste(0);
		work.setProcId(Integer.parseInt(procId));
		work.setQuaNum(Integer.parseInt(quaNum));
		work.setDisDetail(disDetail);
		if (work.getProcId() != worksave.getProcId()) {
			sql = "select * from process where Id=" + work.getProcId();
			List<Processes> procl = pl.createSQL(sql);
			int bn = procl.get(0).getUnitOutput();
			String color = procl.get(0).getColorNo();
			String salary = String.valueOf(procl.get(0).getUnitCost());
			state = wfda.WFdelete(work.getId(), true);
			if (state.equals("success")) {
				state = wfaa.WFadd(String.valueOf(work.getStaId()),
						String.valueOf(work.getProId()),
						String.valueOf(work.getProcId()),
						String.valueOf(work.getQuaNum()), disDetail,
						String.valueOf(bn), color, salary);
			}
		} else {
			sql = "select * from product where Id=" + work.getProId();
			List<Product> prolist = prol.createSQL(sql);
			product = prolist.get(0);
			sql = "select * from flowpath where Id=" + flow.getId();
			List<Flowpath> flowlist = fl.createSQL(sql);
			flow = flowlist.get(0);
			state = "success";
			DisqNum = 0;
			DisqNumsave = 0;
			if (work.getDisDetail().equals(worksave.getDisDetail()) == false) {
				state = this.updatedisq(work);
				bat.setDisqNum(bat.getDisqNum() - DisqNumsave + DisqNum);
				product.setDisqNum(product.getDisqNum() - DisqNumsave + DisqNum);
			}
			if (state.equals("success")) {
				state = this.updateworktab(work, flow, wtab);
				if (state.equals("success")) {
					if (flag == 1) {
						sdao.delete(s);
					} else if (flag == 2) {
						sdao.create(s);
					}
					wdao.update(work, 1);
					bat.setDisqPercent((double) bat.getDisqNum()
							/ (double) bat.getCompleteNum());
					product.setDisqPerc((double) product.getDisqNum()
							/ (double) product.getCompleteNum());
					batdao.update(bat, bat.getId());
					sddao.update(staff, 1);
					wtdao.update(wtab, 1);
					if (work.getDisDetail() != null) {
						dsdd.delete(ddsave);
						for (int i = 0; i < disqdetaillistsave.size(); i++) {
							ddd.delete(disqdetaillistsave.get(i));
						}
					}
				}
			} else {
				return state;
			}
		}
		return state;
	}

	public String updatedisq(WorkForm wf) {
		String sql;
		sql = "select * from workhourstab where staId=" + worksave.getStaId()
				+ " and time='" + wf.getTime() + "'";
		List<WorkHoursTab> whtlist = whtl.createSQL(sql);
		workhour = whtlist.get(0);

		if (worksave.getDisDetail() == null) {
			DisqNumsave = 0;
		} else {
			String[] unq = worksave.getDisDetail().split(":");
			String[] str = unq[0].split("-");
			sql = "select * from DailyStaffDisq where staffId=" + wf.getStaId()
					+ " and time='" + wf.getTime() + "'";
			List<DailyStaffDisq> dsdlistsave = dsdl.createSQL(sql);
			DisqNumsave = dsdlistsave.get(0).getTotalNum();
			int nid = dsdlistsave.get(0).getDisqdeId();
			ddsave = dsdlistsave.get(0);
			for (int i = 0; i < str.length; i++) {
				sql = "select * from disqKind where disDesc='" + str[i] + "'";
				List<DisqKind> dklistsave = dkl.createSQL(sql);
				if (dklistsave.size() == 0) {
					return "error";
				}

				sql = "select * from disqdetail where Id=" + nid;
				List<DisqDetail> dislistsave = ddl.createSQL(sql);
				nid = dislistsave.get(0).getNextId();
				disqdetaillistsave.add(dislistsave.get(0));
			}
		}
		workhour.setWorkHours((double) wf.getQuaNum()
				/ (double) worksave.getQuaNum() * workhour.getWorkHours());
		workhour.setSalary(whtlist.get(0).getSalary() * workhour.getWorkHours()
				/ whtlist.get(0).getWorkHours());
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
					d.setNextId(dislist.get(0).getId() + 2 + i);
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
		String[] str = flow.getSequence().split("-");// 注意分隔号
		flowNum = str.length;
		int k = wtab.getId() + flowNum;
		sql = "select * from worktab where Id between " + flow.getId()
				+ " and " + k;
		List<WorkTab> wtlist = wtl.createSQL(sql);
		for (int i = 0; i < wtlist.size(); i++) {
			if (wf.getProcId() == wtlist.get(i).getProcId()) {
				wtab.setId(wtlist.get(i).getId());
				wtab.setQuNum(wtlist.get(i).getQuNum() + wf.getQuaNum()
						- worksave.getQuaNum());
				wtab.setDisqNum(wtlist.get(i).getDisqNum() + DisqNum
						- DisqNumsave);
				if (i == 0 && wtab.getQuNum() + wtab.getDisqNum() > AimNum) {
					return "outofline";
				} else if (i == wtlist.size() - 1
						&& wtab.getQuNum() + wtab.getDisqNum() > wtlist.get(
								i - 1).getQuNum()) {
					return "outofline";
				} else if (i > 0
						&& i < wtlist.size() - 1
						&& (wtab.getQuNum() < wtlist.get(i + 1).getQuNum()
								+ wtlist.get(i + 1).getQuNum() || wtab
								.getQuNum() + wtab.getDisqNum() > wtlist.get(
								i - 1).getQuNum())) {
					return "outofline";
				}
				wtab.setIsEnd(wtlist.get(i).getIsEnd());
				wtab.setProcId(wtlist.get(i).getProcId());
				wtab.setIsOver(0);
				if (wtlist.get(i).getIsOver() == 1) {
					sql = "select * from scheduletab where wtId="
							+ wtab.getId();
					List<ScheduleTab> stlist = schl.createSQL(sql);
					s = stlist.get(0);
					flag = 1;
				}
				if (i == 0) {
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
						flag = 2;

					}
				} else {
					if (i == wtlist.size() - 1) {
						product.setCompleteNum(product.getCompleteNum()
								- worksave.getQuaNum() + wf.getQuaNum());
						bat.setCompleteNum(bat.getCompleteNum()
								- worksave.getQuaNum() + wf.getQuaNum());
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
						flag = 2;
					}
				}
				break;
			}
		}
		this.wtab = wtab;
		return "success";
	}
}
