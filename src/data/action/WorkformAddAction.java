package data.action;

import java.text.SimpleDateFormat;
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
import data.list.DailyStaffDisqList;
import data.list.DisqDetailList;
import data.list.ProductList;
import data.list.ScheduleTabList;
import data.list.BatchList;
import data.list.WorkFormList;
import data.list.WorkHoursTabList;
import data.list.WorkTabList;
import data.list.FlowpathList;
import data.list.DisqKindList;
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
	private int flag = 0;
	private double pn;// 班产量，由页面读出
	private double salary;// 工序，由页面读出
	private String color = "";
	private String state = "";

	public String execute() throws Exception {
		return null;
	}

	public String WFadd(String staId, String proId, String procId,
			String quaNum, String disDetail, String bn, String color,
			String batNo, String salary) {
		if (staId == null || proId == null || procId == null || quaNum == null) {
			return "isnull";
		}
		System.out.print(procId);
		String sql;
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String time = df.format(date);
		pn = Double.parseDouble(bn);
		this.color = color;
		this.salary = Double.parseDouble(salary);
		work.setTime(date);
		work.setStaId(Integer.parseInt(staId));
		work.setProId(Integer.parseInt(proId));
		work.setProcId(Integer.parseInt(procId));
		work.setQuaNum(Integer.parseInt(quaNum));
		work.setgWaste(0);
		work.setlWaste(0);
		work.setDisDetail(disDetail);
		//去掉一天一张工单的约束
		/*sql = "select * from workhourstab where staId=" + work.getStaId()
				+ " and time='" + time + "'";
		List<WorkHoursTab> ptestlist = whtl.createSQL(sql);
		if (ptestlist.size() != 0) {
			return "personlimit";
		}*/
		//判断有没有对应的产品，将产品信息记录到工单中
		sql = "select * from product where Id=" + work.getProId();
		List<Product> prolist = prol.createSQL(sql);
		prol.getGenericTemplate().close();
		if(prolist.size() == 0) {
			return "error";
		}
		product = prolist.get(0);
		/*sql = "select * from workform order by Id DESC limit 0,1";
		List<WorkForm> wflist = wfl.createSQL(sql);
		
			if (wflist.size() == 0) {
				work.setId(1);
			} else {
				work.setId(wflist.get(0).getId() + 1);
			}*/
		
		

		//判断有没有对应的批次，将批次信息记录到工单中
		sql = "select * from batch where batchNo='" + batNo + "' and proId="
				+ proId;
		List<Batch> batlist = bl.createSQL(sql);
		bl.getGenericTemplate().close();
		if (batlist.size() == 0) {
			return "error";
		} else if (batlist.get(0).getStatus() == 1) {
			return "outofline";
		}		
		bat = batlist.get(0);
		work.setBatchId(batlist.get(0).getId());
		flow.setId(batlist.get(0).getFlowId());
		wtab.setId(batlist.get(0).getWorkTabId());
		AimNum = batlist.get(0).getTotalNum();
		//更新不合格部分的信息
		state = this.updatedisq(work);
		if (state.equals("success")) {
			//更新数据记录部分的信息
			state = this.updateworktab(work, flow, wtab);
			if (state != "success") {
				return state;
			}
		} else {
			return state;
		}
		//计算批次的不合格情况和不合格百分比
		bat.setDisqNum(DisqNum + bat.getDisqNum());
		if (bat.getCompleteNum() != 0 || bat.getDisqNum() != 0) {
			bat.setDisqPercent((double) bat.getDisqNum()
					/ ((double) bat.getCompleteNum() + (double) bat
							.getDisqNum()));
		}
		//计算产品的不合格情况和不合格百分比
		product.setDisqNum(product.getDisqNum() + DisqNum);
		if (product.getCompleteNum() != 0 || product.getDisqNum() != 0) {
			product.setDisqPerc((double) product.getDisqNum()
					/ ((double) product.getCompleteNum() + (double) product
							.getDisqNum()));
		}
		work.setWorkHours(workhour.getWorkHours());
		//error
		//数据库处理：创建工单记录；更新批次表、工作表、产品表；创建工时表、进度表、不合格详情和
		//员工-不合格每日详情相关记录

		WorkFormDAO wdao = new WorkFormDAO();
		wdao.create(work);

		BatchDAO batdao = new BatchDAO();
		batdao.update(bat, 1);
		// sddao.update(staff, 1);

		WorkTabDAO wtdao = new WorkTabDAO();
		wtdao.update(wtab, 1);

		ProductDAO prodao = new ProductDAO();
		prodao.update(product, 1);

		WorkHoursTabDAO whtdao = new WorkHoursTabDAO();
		whtdao.create(workhour);
		if (flag == 2) {
			ScheduleTabDAO sdao = new ScheduleTabDAO();
			sdao.create(s);
		}
		for (int i = 0; i < disqdetaillist.size(); i++) {

			DisqDetailDAO ddd = new DisqDetailDAO();
			ddd.create(disqdetaillist.get(i));
		}
		if (work.getDisDetail() != null && !work.getDisDetail().equals(":")) {

			DailyStaffDisqDAO dsdd = new DailyStaffDisqDAO();
			dsdd.create(dd);
		}
		return state;
	}
/**
 * 
 * 更新不合格部分的信息
 * 
 * @param wf
 * @return
 */
	public String updatedisq(WorkForm wf) {
		String sql;
		/*sql = "select * from workhourstab order by Id DESC limit 0,1";
		List<WorkHoursTab> whtlist = whtl.createSQL(sql);
		if (whtlist.size() != 0) {
			workhour.setId(whtlist.get(0).getId() + 1);
		} else {
			workhour.setId(1);
		}*/
		//设定工时表相关记录
		workhour.setStaId(wf.getStaId());
		workhour.setTime(new Date());
		//工时和工费的计算
		workhour.setWorkHours((double) wf.getQuaNum() * 8.0 / (double) pn);
		workhour.setSalary(workhour.getWorkHours() * salary);
		if(workhour.getWorkHours() < 0) {
			workhour.setWorkHours(0);
		} 
		if(workhour.getSalary() < 0) {
			workhour.setSalary(0);
		}
		if (wf.getDisDetail() == null || ":".equals(wf.getDisDetail())) {
			DisqNum = 0;
		} else {
			String[] unq = wf.getDisDetail().split(":");
			String[] str = unq[0].split("-");
			String[] std = unq[1].split("-");

			//得到不合格详情表的入口ID，方便得到nextId
			sql = "select * from disqdetail order by Id DESC limit 0,1";
			List<DisqDetail> dislist = ddl.createSQL(sql);
			ddl.getGenericTemplate().close();
			sql = "select * from dailystaffdisq order by Id DESC limit 0,1";
			List<DailyStaffDisq> dsdlist = dsdl.createSQL(sql);
			dsdl.getGenericTemplate().close();
			System.out.println("1213");
			for (int i = 0; i < str.length; i++) {
				System.out.println("str:" + str[i]);
				dkl = new DisqKindList();
				sql = "select * from disqKind where Id=" + str[i];
				List<DisqKind> dklist = dkl.createSQL(sql);
				dkl.getGenericTemplate().close();
				if (dklist.size() == 0) {
					return "error";
				} else if (dklist.get(0).getKind() == 1) {
					//计算工废、料废
					work.setlWaste(work.getlWaste() + Integer.parseInt(std[i]));
				} else {
					work.setgWaste(work.getgWaste() + Integer.parseInt(std[i]));
				}

				DisqDetail d = new DisqDetail();

				//对于第一个不合格品种类
				if (i == 0) {
					if (dislist.size() == 0) {
						dd.setDisqdeId(1);
					} else {
						dd.setDisqdeId(dislist.get(0).getId() + 1);
					}
					
					
					//且不是只有一个不合格品种类，则设定下一个的Id
					if (i != str.length - 1)
						d.setNextId(dd.getDisqdeId() + 1);
					/*if (dsdlist.size() == 0) {
						dd.setId(1);
					} else {
						dd.setId(dsdlist.get(0).getId() + 1);
					}*/
					
					
					//设定员工-不合格品详情的记录
					dd.setStaffId(wf.getStaId());
					dd.setTime(new Date());
					dd.setTotalNum(0);
					System.out.println(std.length);
					// 计算不合格品数量
					for (int j = 0; j < std.length; j++) {
						dd.setTotalNum(dd.getTotalNum()
								+ Integer.parseInt(std[i]));
					}
					DisqNum = dd.getTotalNum();
				} else if (i == str.length - 1) {	//如果不合格品种类的最后一个，设定nextId=0
					d.setNextId(0);
				} else {
					if (dislist.size() == 0) {		//否则设定nextId为具体Id
						d.setNextId(2 + i);
					} else {
						d.setNextId(dislist.get(0).getId() + 1);
					}
				}
				d.setId(dd.getDisqdeId() + i);
				d.setNum(Integer.parseInt(std[i]));
				d.setDisKId(dklist.get(0).getId());
				//将所有不合格品详情记录保存在disqdetaillist中
				disqdetaillist.add(d);
			}
		}
		return "success";
	}
/**
 * 
 * 
 * 更新数据记录部分内容
 * 
 * @param wf
 * @param flow
 * @param wtab
 * @return
 */
	public String updateworktab(WorkForm wf, Flowpath flow, WorkTab wtab) {
		String sql;
		int flowNum;
		sql = "select * from flowpath where Id=" + flow.getId();
		List<Flowpath> flowlist = fl.createSQL(sql);
		fl.getGenericTemplate().close();
		flow.setSequence(flowlist.get(0).getSequence());
		String[] str = flow.getSequence().split("-");// 注意分隔号
		flowNum = str.length;
		int k = wtab.getId() + flowNum;
		sql = "select * from worktab where Id between " + bat.getWorkTabId()
				+ " and " + k;
		List<WorkTab> wtlist = wtl.createSQL(sql);
		wtl.getGenericTemplate().close();
		int i = 0;
		for (; i < wtlist.size(); i++) {
			System.out.print(wtlist.get(i).getProcId());
			System.out.print(wtlist.get(i).getId());
			//匹配工作表，得到对应的工序
			if (wf.getProcId() == wtlist.get(i).getProcId()) {
				System.out.print(wtlist.get(i).getProcId());
				System.out.print(wtlist.get(i).getId());
				//更新相关数据段
				wtab.setId(wtlist.get(i).getId());
				wtab.setQuNum(wtlist.get(i).getQuNum() + wf.getQuaNum());
				wtab.setDisqNum(wtlist.get(i).getDisqNum() + DisqNum);
				//对非最后工序，判断是否超过前工序的产品数量
				if (i != 0
						&& wtab.getQuNum() + wtab.getDisqNum() > wtlist.get(
								i - 1).getQuNum()) {
					return "outofline";
				}
				//更加工作表信息更新工单
				wtab.setIsEnd(wtlist.get(i).getIsEnd());
				wtab.setProcId(wtlist.get(i).getProcId());
				wtab.setIsOver(0);
				//若是第一个工序
				if (i == 0) {
					//判断数量是否超出本批次目标数量
					if (wtab.getQuNum() + wtab.getDisqNum() > AimNum) {
						return "outofline";
					}
					//判断是否完成，若完成将其信息记录在进度表中
					if (wtab.getQuNum() + wtab.getDisqNum() == AimNum) {
						wtab.setIsOver(1);
						wtab.setOverTime(new Date());

						/*sql = "select * from scheduletab order by Id DESC limit 0,1";
						List<ScheduleTab> stlist = schl.createSQL(sql);*/

						/*if (stlist.size() == 0) {
							s.setId(1);
						} else {
							s.setId(stlist.get(0).getId() + 1);
						}*/
						s.setWtId(wtab.getId());
						s.setBatchId(wf.getBatchId());
						s.setTime(wf.getTime());
						s.setColorNo(color);
						s.setNum(wtab.getQuNum());
						flag = 2;

					}
				} else {	//不是第一个工序
					
					//最后一个工序，将作为本批次完成情况之一，记录到产品和批次表中，
					//包括完成数量，不合格品数量，不合格百分比
					if (i == wtlist.size() - 1) {
						product.setCompleteNum(product.getCompleteNum()
								+ wf.getQuaNum());
						bat.setCompleteNum(bat.getCompleteNum()
								+ wf.getQuaNum());
					}
					//对于所有工序，当它已完成,将其信息记录在进度表中
					if (wtab.getQuNum() + wtab.getDisqNum() == wtlist
							.get(i - 1).getQuNum()
							&& wtlist.get(i - 1).getIsOver() == 1) {
						wtab.setIsOver(1);
						wtab.setOverTime(new Date());
						//对于批次的最后一个工序，当其完成时，改变批次状态。
						if (i == wtlist.size() - 1) {
							if(bat.getStatus() == 0) {	//原处于正在生产状态，先改为已完成
								bat.setStatus(1);
							} else if(bat.getStatus() == 2) {	//原处于正在超期未完成状态，先改为超期完成
								bat.setStatus(4);
							}
						}

						/*sql = "select * from scheduletab order by Id DESC limit 0,1";
						List<ScheduleTab> stlist = schl.createSQL(sql);

						if (stlist.size() == 0) {
							s.setId(1);
						} else {
							s.setId(stlist.get(0).getId() + 1);
						}*/
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
