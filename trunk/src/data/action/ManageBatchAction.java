package data.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.bean.Batch;
import data.bean.Product;
import data.dao.BatchDAO;
import data.dao.ProductDAO;

public class ManageBatchAction extends BaseAction {
	
	//批次和产品
	private Batch batch = new Batch();
	private Product product = new Product();
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
	
	public String execute() {
		return null;
	}
	
	//超期批次的修改
	public String modifyOutDue() {
		//若输入值为空，返回empty
		if(product.getProNo()=="" || batch.getBatchNo()=="") {
			return "empty";
		} 
		//根据产品编号查询对应产品ID
		ProductDAO pdao = new ProductDAO();
		Map<String, Object> equalsMap = new HashMap<String, Object>();
		equalsMap.put("proNo", product.getProNo());
		List<Product> pList = pdao.findEntity(equalsMap);
		//若产品不存在，返回none
		if(pList.size() == 0) {
			return "none";
		}
		product.setId(pList.get(0).getId());
		batch.setProId(product.getId());
		//根据批次号和产品Id查询对应批次
		BatchDAO bdao = new BatchDAO();
		equalsMap.clear();
		equalsMap.put("batchNo", batch.getBatchNo());
		equalsMap.put("proId", batch.getProId());
		List<Batch> bList = bdao.findEntity(equalsMap);
		//若该批次不存在，返回none
		if(bList.size() == 0) {
			return "none";
		}
		Batch b = bList.get(0);
		b.setNote(batch.getNote());
		b.setStatus(3);

		//若修改成功则
		BatchDAO bdao1 = new BatchDAO();
		int num = bdao1.update(b, b.getId());
		if(num == 1) {
			return "success";
		} else {
			return "false";
		}
	}
		
}
