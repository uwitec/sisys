package data.action;

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
import data.bean.WFstandard;
import data.bean.WorkForm;
import data.dao.DisqKindDAO;
import data.list.BatchList;
import data.list.DailyStaffDisqList;
import data.list.DisqDetailList;
import data.list.DisqKindList;
import data.list.FlowpathList;
import data.list.ProcessesList;
import data.list.ProductList;
import data.list.ScheduleTabList;
import data.list.StaffList;
import data.list.WorkFormList;
import data.list.WorkHoursTabList;
import data.list.WorkTabList;

public class WorkFormEditAction extends BaseAction {

	private String staId;
	private String proId;
	private String procId;
	private String quaNum;
	private Staff sta = new Staff();
	private Product product = new Product();
	private Batch batch = new Batch();
	private WorkForm workform = new WorkForm();

	private String bn;
	private String color;
	private String batNo;
	private String salary;
	private String Id;
	private Map map = new HashMap();
	private String key;
	private String value;

	private BatchList bl;
	private DisqKindList dkl;
	private FlowpathList fl;
	private WorkTabList wtl;
	private WorkFormList wfl;
	private ScheduleTabList schl;
	private DailyStaffDisqList dsdl;
	private DisqDetailList ddl;
	private ProductList prol;
	private ProcessesList procl;
	private StaffList staff;
	private WorkHoursTabList whtl;

	ActionContext actionContext = ActionContext.getContext();
	Map session = actionContext.getSession();

	private String state;

	public String getQuaNum() {
		return quaNum;
	}

	public void setQuaNum(String quaNum) {
		this.quaNum = quaNum;
	}

	public Staff getSta() {
		return sta;
	}

	public void setSta(Staff sta) {
		this.sta = sta;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public WorkForm getWorkform() {
		return workform;
	}

	public void setWorkform(WorkForm workform) {
		this.workform = workform;
	}

	public String execute() throws Exception {
		return null;
	}

	// 工单增加
	public String formadd() {

		int height = 3;
		DisqKindDAO dkdao = new DisqKindDAO();
		List<DisqKind> dkList = dkdao.readAll();
		request.setAttribute("dkList", dkList);
		request.setAttribute("height", height);

		Staff staff = (Staff) session.get("staff");
		Processes proc = (Processes) session.get("processes");
		Product product = (Product) session.get("product");
		staId = String.valueOf(staff.getId());
		proId = String.valueOf(product.getId());
		procId = String.valueOf(proc.getId());
		quaNum = String.valueOf(request.getParameter("quaNum"));
		bn = String.valueOf(proc.getUnitOutput());
		color = proc.getColorNo();
		salary = String.valueOf(proc.getUnitCost());
		batNo = request.getParameter("batchNo");
		String disqDetail;
		String disqKind = "";
		String disqNum = "";
		for (int j = 1; j <= 8; j++) {
			/*
			 * String disqNum1 = request.getParameter("disqNum1"); String
			 * disqKind1 = request.getParameter("disqKind1"); String disqNum2 =
			 * request.getParameter("disqNum1"); String disqKind2 =
			 * request.getParameter("disqKind1"); String disqNum3 =
			 * request.getParameter("disqNum1"); String disqKind3 =
			 * request.getParameter("disqKind1"); String disqNum4 =
			 * request.getParameter("disqNum1"); String disqKind4 =
			 * request.getParameter("disqKind1"); String disqNum5 =
			 * request.getParameter("disqNum1"); String disqKind5 =
			 * request.getParameter("disqKind1"); String disqNum6 =
			 * request.getParameter("disqNum1"); String disqKind6 =
			 * request.getParameter("disqKind1"); String disqNum7 =
			 * request.getParameter("disqNum1"); String disqKind7 =
			 * request.getParameter("disqKind1"); String disqNum8 =
			 * request.getParameter("disqNum1"); String disqKind8 =
			 * request.getParameter("disqKind1");
			 */
			/*
			 * if(request.getParameter("disqNum" + j) == "" &&
			 * request.getParameter("disqNum" + j) == null &&
			 * request.getParameter("disqNum" + j) == "0") { break; }
			 */

			// 若输入不合格品的数量为空，则不进行保存
			if (!request.getParameter("disqNum" + j).trim().equals("")) {
				if (j == 1) {
					disqKind += request.getParameter("disqKind" + j);
				} else {
					disqKind += "-" + request.getParameter("disqKind" + j);
				}
				if (j == 1) {
					disqNum += request.getParameter("disqNum" + j);
				} else {
					disqNum += "-" + request.getParameter("disqNum" + j);
				}

			}

		}
		/*
		 * while(request.getParameter("disqNum" + i) != null) {
		 * if(!request.getParameter("disqKind" + i).equals("0")) {
		 * if((!(request.getParameter("disqNum" + i).equals("0")) &&
		 * (!(request.getParameter("disqNum" + i).equals(""))))) { disqKind +=
		 * request.getParameter("disqKind" + i) + "-"; disqNum +=
		 * request.getParameter("disqNum" + i) + "-"; } } i++; if(
		 * !(request.getParameter("disKind" + i) != "") &&
		 * !"0".equals(request.getParameter("disqKind" + i))) {
		 * if((!(request.getParameter("disqNum" + i).equals("0")) &&
		 * (!(request.getParameter("disqNum" + i).equals(""))))) { disqKind +=
		 * request.getParameter("disqKind" + i) ; disqNum +=
		 * request.getParameter("disqNum" + i); } } i++;
		 * 
		 * }
		 */
		/*
		 * String disqNum = (request.getParameter("disqNum" + i).equals("0") ? 0
		 * : request.getParameter("disqNum" + i)) + ""; String disqNum =
		 * (request.getParameter("disqNum" + 1) == "" ? 0 :
		 * request.getParameter("disqNum" + 1)) + ""; String disqKind =
		 * request.getParameter("disqKind" + 1); while
		 * (request.getParameter("disqKind" + i) != null && !disqNum.equals(""))
		 * { disqKind = disqKind + "-" + request.getParameter("disqKind" + i);
		 * disqNum = disqNum + "-" + (request.getParameter("disqNum" + i) == ""
		 * ? 0 : request .getParameter("disqNum" + i)); i++; }
		 */
		disqDetail = disqKind + ":" + disqNum;
		System.out.println(disqDetail);
		WorkformAddAction wfaa = new WorkformAddAction();
		state = wfaa.WFadd(staId, proId, procId, quaNum, disqDetail, bn, color,
				batNo, salary);
		// session中保存职员、工序、产品的信息
		session.remove("staff");
		session.remove("processes");
		session.remove("product");
		return state;
	}

	// 工单修改
	public String formalter() {
		// procId = request.getParameter("procId");
		// System.out.println(procId);
		batNo = request.getParameter("batchNo");
		Id = request.getParameter("wfId");
		quaNum = request.getParameter("quaNum");
		String staNo = request.getParameter("staNo");
		String staName = request.getParameter("staName");
		String proNo = request.getParameter("proNo");
		String procNo = request.getParameter("procNo");
		String procName = request.getParameter("procName");
		String sql = "select * from product where proNo=" + proNo;
		List<Product> prolist = prol.createSQL(sql);
		if (prolist.size() == 0) {
			return "error";
		}

		proId = String.valueOf(prolist.get(0).getId());
		sql = "select * from staff where staNo='" + staNo + "'";
		List<Staff> stalist = staff.createSQL(sql);
		if (stalist.size() == 0) {
			return "error";
		}
		staId = String.valueOf(stalist.get(0).getId());
		sql = "select * from processes where procName='" + procName + "' and procNo='" + procNo + "'";
		List<Processes> proclist = procl.createSQL(sql);
		System.out.println(proclist.size());
		if (proclist.size() == 0) {
			return "error";
		}
		procId = String.valueOf(proclist.get(0).getId());
		color = proclist.get(0).getColorNo();
		salary = String.valueOf(proclist.get(0).getUnitCost());
		bn = String.valueOf(proclist.get(0).getUnitOutput());
		// 存储要传回页面的不合格信息
		List<String> listkind = new ArrayList<String>();
		List<String> listnum = new ArrayList<String>();
		Map<String, String> disqmap = new HashMap<String, String>();
		String disqKind = "";
		String disqNum = "";
		for (int j = 1; j <= 8; j++) {

			// 若输入不合格品的数量为空，则不进行保存

			if (!request.getParameter("disqNum" + j).trim().equals("")
					&& !request.getParameter("disqNum" + j).trim().equals("0")) {
				sql = "select * from disqKind where Id=" + j;
				dkl = new DisqKindList();
				List<DisqKind> dklist = dkl.createSQL(sql);
				dkl.getGenericTemplate().close();

				if (dklist.size() == 0) {
					return "error";
				} else {
					listkind.add(dklist.get(0).getDisDesc());
				}

				if (j == 1) {
					disqKind += request.getParameter("disqKind" + j);
				} else {
					disqKind += "-" + request.getParameter("disqKind" + j);
				}
				if (j == 1) {
					disqNum += request.getParameter("disqNum" + j);
				} else {
					disqNum += "-" + request.getParameter("disqNum" + j);
				}

				listnum.add(request.getParameter("disqNum" + j));
				disqmap.put(dklist.get(0).getDisDesc(),
						request.getParameter("disqNum" + j));

			}
		}
		String disqDetail = disqKind + ":" + disqNum;
		WorkFormAlterAction wfala = new WorkFormAlterAction();
		state = wfala.WFalter(Id, staId, proId, procId, quaNum, disqDetail, bn,
				color, batNo, salary);
		WFstandard wfsave = new WFstandard();
		if (state.equals("success")) {
			wfsave.setBatchNo(batNo);
			wfsave.setDisqDetail(disqDetail);
			wfsave.setProNo(proNo);
			wfsave.setProName(request.getParameter("proName"));
			wfsave.setStaNo(staNo);
			wfsave.setStaName(staName);
			wfsave.setProcNo(procNo);
			wfsave.setProcName(procName);
			wfsave.setQuaNum(Integer.parseInt(quaNum));
			wfsave.setWfId(Integer.parseInt(Id));
			WorkFormList wfl = new WorkFormList();
			sql = "select * from workform where id=" + Id;
			List<WorkForm> wflist = wfl.createSQL(sql);
			WorkForm wf = wflist.get(0);
			Date date = wf.getDeleteTime();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String time = "";
			if (date != null) {
				time = df.format(date);
			}
			wfsave.setDeletetime(time);
			if (wf.getIsDelete() == 1) {
				wfsave.setStatus("是");
			} else {
				wfsave.setStatus("否");
			}
		}
		request.setAttribute("wfsave",wfsave);
		request.setAttribute("disqkind", listkind);
		request.setAttribute("disqnum", listnum);
		request.setAttribute("disqmap", disqmap);
		return state;
	}

	// 工单删除
	public String formdelete() {
		Id = request.getParameter("wfId");
		System.out.println("ID:" + Id);
		WorkFormDeleteAction wfda = new WorkFormDeleteAction();
		state = wfda.WFdelete(Integer.parseInt(Id), false);
		return state;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
