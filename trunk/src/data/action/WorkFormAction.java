package data.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;

import data.bean.Batch;
import data.bean.DisqKind;
import data.bean.Processes;
import data.bean.Product;
import data.bean.Staff;
import data.bean.WorkForm;
import data.service.WorkFormService;

public class WorkFormAction extends BaseAction {
 
	private int height = 3;
	private Staff staff = new Staff();
	private Product product = new Product();
	private Processes processes = new Processes();
	private WorkForm workForm = new WorkForm();
	private Batch batch = new Batch();
	
	private String disqKind1;
	private String disqNum1;
	private String disqKind2;
	private String disqNum2;
	private String disqKind3;
	private String disqNum3;
	
	private DisqKind disqKind = new DisqKind();
	private String proNo;
	private String staNo;
	private String procNo;
	private InputStream inputStream;
	
	private WorkFormService wfs = new WorkFormService();
		
	//get方法 和 set方法
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public WorkForm getWorkForm() {
		return workForm;
	}
	public void setWorkForm(WorkForm workForm) {
		this.workForm = workForm;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getHeight() {
		return height;
	}
	public void setProNo(String proNo) {
		this.proNo = proNo;
	}
	public String getProNo() {
		return proNo;
	}
	public void setStaNo(String staNo) {
		this.staNo = staNo;
	}
	public String getStaNo() {
		return staNo;
	}
	public void setProcNo(String procNo) {
		this.procNo = procNo;
	}
	public String getProcNo() {
		return procNo;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setBatch(Batch batch) {
		this.batch = batch;
	}
	public Batch getBatch() {
		return batch;
	}
	public void setDisqKind1(String disqKind1) {
		this.disqKind1 = disqKind1;
	}
	public String getDisqKind1() {
		return disqKind1;
	}
	public void setDisqNum1(String disqNum1) {
		this.disqNum1 = disqNum1;
	}
	public String getDisqNum1() {
		return disqNum1;
	}
	public void setDisqKind2(String disqKind2) {
		this.disqKind2 = disqKind2;
	}
	public String getDisqKind2() {
		return disqKind2;
	}
	public void setDisqKind3(String disqKind3) {
		this.disqKind3 = disqKind3;
	}
	public String getDisqKind3() {
		return disqKind3;
	}
	public void setDisqNum2(String disqNum2) {
		this.disqNum2 = disqNum2;
	}
	public String getDisqNum2() {
		return disqNum2;
	}
	public void setDisqNum3(String disqNum3) {
		this.disqNum3 = disqNum3;
	}
	public String getDisqNum3() {
		return disqNum3;
	}
	public DisqKind getDisqKind() {
		return disqKind;
	}
	public void setDisqKind(DisqKind disqKind) {
		this.disqKind = disqKind;
	}
	public void setProcesses(Processes processes) {
		this.processes = processes;
	}
	public Processes getProcesses() {
		return processes;
	}
	
	
	
	public String execute() {
		return null;
	}
	//进入增加工单界面
	public String formAdd() {
		return wfs.formAdd(height);
	}
	
	//增加不合格品种类
	public String addDisqKind() {
		return wfs.addDisqKind(disqKind);
	}
	
	//根据产品编号搜索对应产品名称
	public String preAddProNo() throws IOException {
		String result = wfs.preAddProNo(proNo);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(result);
		return null; 
	}
	
	//根据员工编号搜索对应员工姓名
	public String preAddStaNo() throws IOException {
		String result = wfs.preAddStaNo(staNo);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(result);
		return null; 
	}
	
	//根据工序编号搜索对应工序名称
	public String preAddProcNo() throws IOException {
		String result = wfs.preAddProcNo(procNo);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(result);
		return null; 
	}
	//增加工单
	public String addWorkForm() {
		String disqDetail = "";
		int i=2;
		String disqNum = (String) (request.getParameter("disqNum"+1) == "" ? 0 : request.getParameter("disqNum"+1));
		
		String disqKind = request.getParameter("disqKind" + 1);
		
		while(request.getParameter("disqKind" + i)!=null && !disqKind.equals("")) {
			disqKind = disqKind + "-" + request.getParameter("disqKind" + i);
			disqNum = disqNum + "-" + (request.getParameter("disqNum"+i) == "" ? 0 : request.getParameter("disqNum"+i));
			i++;
		}
		disqDetail = disqKind + ":" + disqNum;
		System.out.println(batch);
		System.out.println(workForm);
		System.out.println(disqDetail);
		wfs.addWorkForm();
		return "success";
	}
	
	//查看工单详情
	public String detail() {
		return wfs.detail();
		
	}
	
	//进入修改界面
	public String preAlter() {
		return wfs.preAlter();
	}
	
	//修改工单详情
	public String alter() {
		return wfs.alter();
	}
}
