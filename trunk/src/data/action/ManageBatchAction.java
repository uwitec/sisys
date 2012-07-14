package data.action;


import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;

import data.bean.Batch;
import data.bean.Flowpath;
import data.bean.Product;
import data.service.ManageBatchService;

public class ManageBatchAction extends BaseAction {
	
	private ManageBatchService mbs = new ManageBatchService();
	ActionContext context = ActionContext.getContext();  
    HttpServletRequest request = (HttpServletRequest) context.get(StrutsStatics.HTTP_REQUEST);  
	
	//批次和产品
	private String proNo = request.getParameter("proNo");
	private Batch batch = new Batch();
	private Product product = new Product();
	private Flowpath flowpath = new Flowpath();
	private String fp = "";
	//相应ajax请求
	private InputStream inputStream;
	private String fpath;
	
	//对应的get()和set()方法
	public Batch getBatch() {
		return batch;
	}
	public void setBatch(Batch batch) {
		this.batch = batch;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public void setFlowpath(Flowpath flowpath) {
		this.flowpath = flowpath;
	}
	public Flowpath getFlowpath() {
		return flowpath;
	}
	public void setFp(String fp) {
		this.fp = fp;
	}
	public String getFp() {
		return fp;
	}
	public void setProNo(String proNo) {
		this.proNo = proNo;
	}
	public String getProNo() {
		return proNo;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setFpath(String fpath) {
		this.fpath = fpath;
	}
	public String getFpath() {
		return fpath;
	}
	
	@Override
	public String execute() {
		return null;
	}
	
	//进入批次添加页面
	public String preAddBatch() throws Exception {
		product.setProNo(proNo);
		String result = mbs.preAddBatch(product);
		System.out.println(result);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(result);
		return null; 
	}
	
	//批次添加
	public String addBatch() {
		return mbs.addBatch(product, batch, fpath);
	}
	
	//超期批次的修改
	public String modifyOutDue() {
		return mbs.modifyOutDue(product, batch);
	}
	
	
	

}
