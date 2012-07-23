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
import data.bean.WorkForm;
import data.bean.WorkHoursTab;
import data.bean.WorkTab;
import data.dao.BatchDAO;
import data.dao.DailyStaffDisqDAO;
import data.dao.DisqDetailDAO;
import data.dao.ProductDAO;
import data.dao.ScheduleTabDAO;
import data.dao.WorkFormDAO;
import data.dao.WorkHoursTabDAO;
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
	private Batch batchnow = new Batch();
	private Flowpath flow = new Flowpath();
	private Flowpath flownow = new Flowpath();
	private WorkTab wtab = new WorkTab();
	private WorkTab wtabnow = new WorkTab();
	private ScheduleTab s = new ScheduleTab();
	private ScheduleTab ssave = new ScheduleTab();
	private DailyStaffDisq dd = new DailyStaffDisq();
	private DailyStaffDisq ddsave = new DailyStaffDisq();
	private Product product = new Product();
	private Product productnow = new Product();
	private WorkHoursTab workhour = new WorkHoursTab();
	private WorkHoursTab workhoursave = new WorkHoursTab();

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
	private int flag1;
	private String color = "";
	private String state = "";
	private double salary;
	private double pn;

	public String execute() throws Exception {
		return null;
	}

	public String WFalter(String Id, String staId, String proId, String procId,
			String quaNum, String disDetail, String pn, String color,
			String batNo, String salary) {
		String sql;
		work.setId(Integer.parseInt(Id));
		System.out.println(procId);
		sql = "select * from workform where Id=" + work.getId();
		List<WorkForm> wflist = wfl.createSQL(sql);
		wfl.getGenericTemplate().close();
		if (wflist.size() == 0) {
			return "error";
		} else if (wflist.get(0).getIsDelete() == 1) {
			return "isdelete";
		}
		worksave = wflist.get(0);
		this.salary = Double.parseDouble(salary);
		this.pn = Double.parseDouble(pn);

		sql = "select * from batch where Id=" + worksave.getBatchId();
		List<Batch> batlist = bl.createSQL(sql);
		bl.getGenericTemplate().close();
		bat = batlist.get(0);

		AimNum = bat.getTotalNum();

		sql = "select * from batch where batchNo='" + batNo + "' and proId="
				+ proId;
		bl = new BatchList();
		List<Batch> blist = bl.createSQL(sql);
		bl.getGenericTemplate().close();
		if (blist.size() == 0) {
			return "error";
		} else {
			batchnow = blist.get(0);
		}

		flow.setId(batlist.get(0).getFlowId());
		flownow.setId(batchnow.getFlowId());

		work.setBatchId(blist.get(0).getId());
		work.setDeleteTime(worksave.getDeleteTime());
		work.setIsDelete(worksave.getIsDelete());
		work.setTime(worksave.getTime());
		work.setWorkHours(worksave.getWorkHours());
		work.setStaId(Integer.parseInt(staId));
		work.setProId(Integer.parseInt(proId));
		work.setProcId(Integer.parseInt(procId));
		work.setQuaNum(Integer.parseInt(quaNum));
		work.setDisDetail(disDetail);
		work.setgWaste(0);
		work.setlWaste(0);
		// System.out.println(worksave);
		state = this.deletedisq(worksave);
		if (state.equals("success")) {
			state = this.deleteworktab(worksave, flow);
			if (state.equals("success")) {
				state = this.adddisq(work);
				if (state.equals("success")) {
					state = this.addworktab(work, flownow);
					if (state.equals("success")) {

						BatchDAO batdao = new BatchDAO();
						ProductDAO prodao = new ProductDAO();
						WorkTabDAO wtdao = new WorkTabDAO();
						if (bat.getId() != batchnow.getId()) {
							batdao.update(bat, 1);
						}
						if (productnow.getId() != product.getId()) {
							prodao.update(product, 1);
						}
						if (wtabnow.getId() != wtab.getId()) {
							wtdao.update(wtab, 1);
						}
						batdao = new BatchDAO();
						batdao.update(batchnow, 1);
						prodao = new ProductDAO();
						prodao.update(productnow, 1);
						wtdao = new WorkTabDAO();
						wtdao.update(wtabnow, 1);

						System.out.println(DisqNum);
						System.out.println(DisqNumsave);
						WorkFormDAO wdao = new WorkFormDAO();
						wdao.update(work, 1);
						WorkHoursTabDAO whtdao = new WorkHoursTabDAO();
						whtdao = new WorkHoursTabDAO();
						whtdao.delete(workhoursave);
						whtdao = new WorkHoursTabDAO();
						whtdao.create(workhour);
						if (worksave.getDisDetail() != null
								|| worksave.getDisDetail().equals("")) {
							DailyStaffDisqDAO dsdd = new DailyStaffDisqDAO();
							dsdd.delete(ddsave);
						}
						for (int i = 0; i < disqdetaillistsave.size(); i++) {

							DisqDetailDAO ddd = new DisqDetailDAO();
							ddd = new DisqDetailDAO();
							ddd.delete(disqdetaillistsave.get(i));
						}
						if (flag1 == 2) {
							ScheduleTabDAO sdao = new ScheduleTabDAO();
							sdao.create(s);
						}
						if (flag == 1) {
							ScheduleTabDAO sdao = new ScheduleTabDAO();
							sdao.delete(ssave);
						}
						for (int i = 0; i < disqdetaillist.size(); i++) {
							DisqDetailDAO ddd = new DisqDetailDAO();
							ddd = new DisqDetailDAO();
							ddd.create(disqdetaillist.get(i));
						}
						if (work.getDisDetail() != null
								|| work.getDisDetail().equals("")) {
							DailyStaffDisqDAO dsdd = new DailyStaffDisqDAO();
							dsdd.create(dd);
						}
						if (state.equals("success")) {
							bat.setStatus(0);
						}
					}
				}
			}
		}
		return state;
	}

	// 试删除原工单不合格信息
	public String deletedisq(WorkForm wf) {
		String sql;

		if (wf.getDisDetail() == null || wf.getDisDetail().equals(":")) {
			DisqNumsave = 0;
		} else {

			String[] unq = wf.getDisDetail().split(":");
			String[] str = unq[0].split("-");
			sql = "select * from workhourstab where staId=" + wf.getStaId()
					+ " and time='" + wf.getTime() + "'";
			List<WorkHoursTab> whtlist = whtl.createSQL(sql);
			whtl.getGenericTemplate().close();
			if (whtlist.size() == 0) {
				return "error";
			} else {
				workhoursave = whtlist.get(0);
				sql = "select * from DailyStaffDisq where staffId="
						+ wf.getStaId() + " and time='" + wf.getTime() + "'";
				List<DailyStaffDisq> dsdlist = dsdl.createSQL(sql);
				dsdl.getGenericTemplate().close();
				DisqNumsave = dsdlist.get(0).getTotalNum();
				int nid = dsdlist.get(0).getDisqdeId();
				ddsave = dsdlist.get(0);
				for (int i = 0; i < str.length; i++) {
					/*
					 * sql = "select * from disqKind where Id=" + str[i];
					 * List<DisqKind> dklist = dkl.createSQL(sql); if
					 * (dklist.size() == 0) { return "error"; }
					 */
					ddl = new DisqDetailList();
					sql = "select * from disqdetail where Id=" + nid;
					List<DisqDetail> dislist = ddl.createSQL(sql);
					ddl.getGenericTemplate().close();
					nid = dislist.get(0).getNextId();
					disqdetaillistsave.add(dislist.get(0));
				}
			}
		}
		return "success";
	}

	// 试删除原工单的数值记录
	public String deleteworktab(WorkForm wf, Flowpath flow) {

		String sql;
		int flowNum;
		sql = "select * from flowpath where Id=" + flow.getId();
		List<Flowpath> flowlist = fl.createSQL(sql);
		fl.getGenericTemplate().close();
		flow.setSequence(flowlist.get(0).getSequence());
		String[] str = flow.getSequence().split("-");// 注意分隔号
		flowNum = str.length;
		int k = bat.getWorkTabId() + flowNum - 1;
		sql = "select * from worktab where Id between " + bat.getWorkTabId()
				+ " and " + k;
		List<WorkTab> wtlist = wtl.createSQL(sql);
		wtl.getGenericTemplate().close();

		for (int i = 0; i < wtlist.size(); i++) {
			System.out.println(wtlist.get(0).getId());
			if (wf.getProcId() == wtlist.get(i).getProcId()) {
				// wtab = wtlist.get(i);
				System.out.println(wtlist.get(i).getId());
				wtab.setId(wtlist.get(i).getId());
				System.out.println(wtab.getId());
				wtab.setIsEnd(wtlist.get(i).getIsEnd());
				wtab.setIsOver(wtlist.get(i).getIsOver());
				wtab.setOverTime(wtlist.get(i).getOverTime());
				wtab.setProcId(wtlist.get(i).getProcId());
				wtab.setQuNum(wtlist.get(i).getQuNum() - wf.getQuaNum());
				wtab.setDisqNum(wtlist.get(i).getDisqNum() - DisqNumsave);

				// 防止正常修改时出现报警
				if (i != wtlist.size() - 1
						&& wtab.getQuNum() < wtlist.get(i + 1).getQuNum()
								+ wtlist.get(i + 1).getDisqNum()
						&& (work.getProcId() != worksave.getProcId()
								|| work.getBatchId() != worksave.getBatchId()
								|| work.getStaId() != worksave.getStaId() || work
								.getProId() != worksave.getProId())) {

					return "outofline";
				}
				// 最后一个工序的改变将带来产品和批次数量的修改
				if (i == wtlist.size() - 1) {
					product.setCompleteNum(product.getCompleteNum()
							- wf.getQuaNum());
					bat.setCompleteNum(bat.getCompleteNum() - wf.getQuaNum());
				}
				// 如果修改前是已完成状态，则进行如下处理：状态位和进度表的改变
				if (wtab.getIsOver() == 1) {
					flag = 1;
					wtab.setIsOver(0);
					wtab.setOverTime(null);
					sql = "select * from scheduletab where wtId="
							+ wtab.getId();
					List<ScheduleTab> stlist = schl.createSQL(sql);
					schl.getGenericTemplate().close();

					ssave = stlist.get(0);
				}
				break;
			}
		}

		bat.setDisqNum(bat.getDisqNum() - DisqNumsave);
		if (bat.getCompleteNum() != 0 || bat.getDisqNum() != 0) {
			bat.setDisqPercent((double) bat.getDisqNum()
					/ ((double) bat.getCompleteNum() + (double) bat
							.getDisqNum()));
		}

		product.setDisqNum(product.getDisqNum() - DisqNumsave);
		if (product.getCompleteNum() != 0 || product.getDisqNum() != 0) {
			product.setDisqPerc((double) product.getDisqNum()
					/ ((double) product.getCompleteNum() + (double) product
							.getDisqNum()));
		}

		/*
		 * for (int i = 0; i < wtlist.size() - 1; i++) { if
		 * (wtlist.get(i).getQuNum() < wtlist.get(i + 1).getQuNum() +
		 * wtlist.get(i + 1).getDisqNum()) { return "outofline"; } }
		 */
		return "success";
	}

	public String adddisq(WorkForm wf) {
		AimNum = batchnow.getTotalNum();// 修改AimNum为当前批次的目标数量

		if (bat.getId() == batchnow.getId()) {
			batchnow.setCompleteNum(bat.getCompleteNum());
			batchnow.setDisqNum(bat.getDisqNum());
			batchnow.setDisqPercent(bat.getDisqPercent());
		}
		if (product.getId() == productnow.getId()) {
			productnow.setCompleteNum(product.getCompleteNum());
			productnow.setDisqNum(product.getDisqNum());
			productnow.setDisqPerc(product.getDisqPerc());
		}

		String sql;
		sql = "select * from workhourstab order by Id DESC limit 0,1";
		whtl = new WorkHoursTabList();
		List<WorkHoursTab> whtlist = whtl.createSQL(sql);
		whtl.getGenericTemplate().close();
		if (whtlist.size() != 0) {
			workhour.setId(whtlist.get(0).getId() + 1);
		} else {
			workhour.setId(1);
		}
		workhour.setStaId(wf.getStaId());
		workhour.setTime(wf.getTime());
		workhour.setWorkHours((double) wf.getQuaNum() * 8.0 / (double) pn);
		workhour.setSalary(workhour.getWorkHours() * salary);
		if (wf.getDisDetail() == null || wf.getDisDetail().equals(":")) {
			DisqNum = 0;
		} else {
			String[] unq = wf.getDisDetail().split(":");
			String[] str = unq[0].split("-");
			String[] std = unq[1].split("-");

			sql = "select * from disqdetail order by Id DESC limit 0,1";
			ddl = new DisqDetailList();
			List<DisqDetail> dislist = ddl.createSQL(sql);
			dsdl = new DailyStaffDisqList();
			sql = "select * from dailystaffdisq order by Id DESC limit 0,1";
			List<DailyStaffDisq> dsdlist = dsdl.createSQL(sql);
			dsdl.getGenericTemplate().close();
			// System.out.println("不合格详情：" + wf.getDisDetail());

			for (int i = 0; i < str.length; i++) {
				sql = "select * from disqKind where Id =" + str[i];
				dkl = new DisqKindList();
				List<DisqKind> dklist = dkl.createSQL(sql);
				dkl.getGenericTemplate().close();
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
					if (i != str.length - 1)
						d.setNextId(dd.getDisqdeId() + 1);
					if (dsdlist.size() == 0) {
						dd.setId(1);
					} else {
						dd.setId(dsdlist.get(0).getId() + 1);
					}
					dd.setStaffId(wf.getStaId());
					dd.setTime(wf.getTime());
				} else if (i == str.length - 1) {
					d.setNextId(0);
				} else {
					if (dislist.size() == 0) {
						d.setNextId(i + 2);
					} else {
						d.setNextId(dislist.get(0).getId() + 2 + i);// 需要修改id，改为disqDetail的记录数目+1+i
					}
				}
				d.setId(dd.getDisqdeId() + i);
				d.setNum(Integer.parseInt(std[i]));
				d.setDisKId(dklist.get(0).getId());
				disqdetaillist.add(d);
			}
		}
		dd.setTotalNum(work.getlWaste() + work.getgWaste());
		DisqNum = dd.getTotalNum();
		return "success";
	}

	public String addworktab(WorkForm wf, Flowpath flow) {

		String sql;
		int flowNum;
		fl = new FlowpathList();
		sql = "select * from flowpath where Id=" + flow.getId();
		List<Flowpath> flowlist = fl.createSQL(sql);
		fl.getGenericTemplate().close();
		flow.setSequence(flowlist.get(0).getSequence());
		String[] str = flow.getSequence().split("-");// 注意分隔号
		flowNum = str.length;
		int k = batchnow.getWorkTabId() + flowNum - 1;
		sql = "select * from worktab where Id between "
				+ batchnow.getWorkTabId() + " and " + k;
		wtl = new WorkTabList();
		List<WorkTab> wtlist = wtl.createSQL(sql);
		wtl.getGenericTemplate().close();
		for (int i = 0; i < wtlist.size(); i++) {
			if (wf.getProcId() == wtlist.get(i).getProcId()) {
				System.out.print(wtlist.get(i).getProcId());
				System.out.print(wtlist.get(i).getId());
				wtabnow.setId(wtlist.get(i).getId());

				if (wtab.getId() == wtlist.get(i).getId()) {
					System.out.println("wtab跟wtabnow的id相同");
					wtabnow.setDisqNum(wtab.getDisqNum() + DisqNum);
					wtabnow.setIsEnd(wtab.getIsEnd());
					wtabnow.setIsOver(wtab.getIsOver());
					wtabnow.setOverTime(wtab.getOverTime());
					wtabnow.setProcId(wtab.getProcId());
					wtabnow.setQuNum(wtab.getQuNum() + wf.getQuaNum());
				} else {
					System.out.println("wtab跟wtabnow的id不同");
					System.out.println("wtab的Id：" + wtab.getId());
					System.out.println("wtabnow的Id：" + wtabnow.getId());

					wtabnow.setQuNum(wtlist.get(i).getQuNum() + wf.getQuaNum());
					wtabnow.setDisqNum(wtlist.get(i).getDisqNum() + DisqNum);
				}

				if (i != 0
						&& wtabnow.getQuNum() + wtabnow.getDisqNum() > wtlist
								.get(i - 1).getQuNum()) {
					return "outofline";
				}
				// 如果所修改工序是同一批次原工序的后一个工序，则判断是否超出范围
				/*System.out.println(i != 0
						&& wtab.getId() == wtabnow.getId() - 1);
				System.out.println(bat.getBatchNo().equals(
						batchnow.getBatchNo()));
				System.out
						.println(wtabnow.getQuNum() + wtabnow.getDisqNum() > wtlist
								.get(i - 1).getQuNum() - wtab.getQuNum());*/
				if (i != 0 && wtab.getId() == wtabnow.getId() - 1
						&& bat.getBatchNo().equals(batchnow.getBatchNo())) {
					if (wtabnow.getQuNum() + wtabnow.getDisqNum() > wtlist.get(
							i - 1).getQuNum()
							- worksave.getQuaNum()) {

						System.out.println("wtab的不合格品：" + wtab.getDisqNum());
						System.out.println("wtab的合格品：" + wtab.getQuNum());
						System.out.println("修改后工单的合格品：" + wf.getQuaNum());
						System.out.println("wtabnow的不合格品："
								+ wtabnow.getDisqNum());
						System.out.println("wtabnow的合格品：" + wtabnow.getQuNum());
						System.out.println("前工序的合格品数量："
								+ wtlist.get(i - 1).getQuNum());
						System.out.println("现在的不合格品：" + DisqNum);
						System.out.println("原来的不合格品：" + DisqNumsave);

						System.out.println("我的错3");
						return "outofline";
					}
				}
				// 判断是否比后工序数量少
				if (i != wtlist.size() - 1
						&& wtabnow.getQuNum() < wtlist.get(i + 1).getQuNum()
								+ wtlist.get(i + 1).getDisqNum()) {
					System.out.println("我的错2");
					return "outofline";
				}
				wtabnow.setIsEnd(wtlist.get(i).getIsEnd());
				wtabnow.setProcId(wtlist.get(i).getProcId());
				wtabnow.setIsOver(0);
				if (i == 0) {
					if (wtabnow.getQuNum() + wtabnow.getDisqNum() > AimNum) {
						System.out.println("wtab的不合格品：" + wtab.getDisqNum());
						System.out.println("wtab的合格品：" + wtab.getQuNum());
						System.out.println("修改后工单的合格品：" + wf.getQuaNum());
						System.out.println("wtabnow的不合格品："
								+ wtabnow.getDisqNum());
						System.out.println("wtabnow的合格品：" + wtabnow.getQuNum());
						System.out.println("前工序的合格品数量：" + AimNum);
						System.out.println("现在的不合格品：" + DisqNum);
						System.out.println("原来的不合格品：" + DisqNumsave);
						System.out.println("我的错1");
						return "outofline";
					}
					if (wtabnow.getQuNum() + wtabnow.getDisqNum() == AimNum) {
						wtabnow.setIsOver(1);
						wtabnow.setOverTime(wf.getTime());
						schl = new ScheduleTabList();
						sql = "select * from scheduletab order by Id DESC limit 0,1";
						List<ScheduleTab> stlist = schl.createSQL(sql);
						schl.getGenericTemplate().close();

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
						flag1 = 2;

					}
				} else {
					if (i == wtlist.size() - 1) {
						batchnow.setCompleteNum(bat.getCompleteNum()
								+ wf.getQuaNum());
						productnow.setCompleteNum(product.getCompleteNum()
								+ wf.getQuaNum());

					}
					if (wtabnow.getQuNum() + wtabnow.getDisqNum() == wtlist
							.get(i - 1).getQuNum()
							&& wtlist.get(i - 1).getIsOver() == 1) {
						wtabnow.setIsOver(1);
						wtabnow.setOverTime(wf.getTime());
						if (i == wtlist.size() - 1 && batchnow.getStatus() == 0) {
							batchnow.setStatus(1);
						}

						schl = new ScheduleTabList();
						sql = "select * from scheduletab order by Id DESC limit 0,1";
						List<ScheduleTab> stlist = schl.createSQL(sql);
						schl.getGenericTemplate().close();

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
						flag1 = 2;
					}
				}
				break;
			}
		}

		batchnow.setDisqNum(batchnow.getDisqNum() + DisqNum);
		if (batchnow.getCompleteNum() != 0 || batchnow.getDisqNum() != 0) {
			batchnow.setDisqPercent((double) batchnow.getDisqNum()
					/ ((double) batchnow.getCompleteNum() + (double) batchnow
							.getDisqNum()));
		}

		productnow.setDisqNum(productnow.getDisqNum() + DisqNum);
		if (productnow.getCompleteNum() != 0 || productnow.getDisqNum() != 0) {
			productnow
					.setDisqPerc((double) productnow.getDisqNum()
							/ ((double) productnow.getCompleteNum() + (double) productnow
									.getDisqNum()));
		}

		return "success";
	}
}
