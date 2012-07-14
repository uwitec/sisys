package data.action;

import java.io.InputStream;
import java.io.StringBufferInputStream;

import data.bean.DisqKind;
import data.bean.Product;
import data.bean.Staff;
import data.bean.WorkForm;
import data.service.WorkFormService;

public class WorkFormAction extends BaseAction {
 
	private int height = 3;
	private Staff staff = new Staff();
	private Product product = new Product();
	private WorkForm workForm = new WorkForm();
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
	public void setDisqKind(DisqKind disqKind) {
		this.disqKind = disqKind;
	}
	public DisqKind getDisqKind() {
		return disqKind;
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
	
	
	
	public String execute() {
		return null;
	}
	//增加工单
	public String formAdd() {
		return wfs.formAdd(height);
	}
	
	//增加不合格品种类
	public String addDisqKind() {
		return wfs.addDisqKind(disqKind);
	}
	
	//根据产品编号搜索对应产品名称
	public String preAddProNo() {
		inputStream = new StringBufferInputStream(wfs.preAddProNo(proNo));
		return "success";
	}
	
	//根据员工编号搜索对应员工姓名
	public String preAddStaNo() {
		inputStream = new StringBufferInputStream(wfs.preAddStaNo(staNo));
		return "success";
	}
	
	//根据工序编号搜索对应工序名称
	public String preAddProcNo() {
		inputStream = new StringBufferInputStream(wfs.preAddProcNo(procNo));
		return "success";
	}
}
