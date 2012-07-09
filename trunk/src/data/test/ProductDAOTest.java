package data.test;

import data.bean.Processes;
import data.bean.Product;
import data.dao.ProcessesDAO;
import data.dao.ProductDAO;

public class ProductDAOTest {
	
	ProductDAO pdao = new ProductDAO();
	Product p = new Product();

	public static void main(String args[]) {
		ProductDAOTest test = new ProductDAOTest();
		//test.testCreate();
		//test.testUpdate();
		//test.testRead();
		//test.testReadByPk();
		test.testCount();
		//test.testDelete();
	}
	
	public void testCreate() {
		
		p.setDeptId(1);
		p.setDisqNum(10);
		p.setDisqPerc(0.01);
		p.setProlineId(1);
		p.setProName("");
		p.setTime(null);
		p.setTotalNum(10000);
		p.setProNo("123");
		p.setDeleteTime(null);
		p.setId(1);
		p.setIsDelete(0);
		
		System.out.print(pdao.create(p));
	}
	
	public void testUpdate() {
		
		p.setDeptId(1);
		p.setDisqNum(10);
		p.setDisqPerc(0.01);
		p.setProlineId(1);
		p.setProName("");
		p.setTime(null);
		p.setTotalNum(10001);
		p.setProNo("123");
		p.setDeleteTime(null);
		p.setId(1);
		p.setIsDelete(0);
		
		System.out.print(pdao.update(p,1));
	}
	
	public void testRead() {
		p.setId(1);
		System.out.print(pdao.read(p));
	}
	
	public void testReadByPk() {
		
		System.out.print(pdao.readByPk(1));
	}
	
	public void testDelete() {
		
		p.setId(1);
		System.out.print(pdao.delete(p));
	}
	
	public void testCount() {
		System.out.print(pdao.count());
	}
}
