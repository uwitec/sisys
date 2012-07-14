package data.test;

import data.bean.ProductLine;
import data.dao.ProductLineDAO;

public class ProductLineDAOTest {
	
	ProductLineDAO pdao = new ProductLineDAO();
	ProductLine p = new ProductLine();

	public static void main(String args[]) {
		ProductLineDAOTest test = new ProductLineDAOTest();
		//test.testCreate();//1
		//test.testUpdate();//1
		//test.testRead();//1
		//test.testReadByPk();//1
		//test.testCount();//1
		//test.testDelete();
	}
	
	public void testCreate() {
		
		p.setLineDesc("");
		p.setDeleteTime(null);
		p.setId(1);
		p.setIsDelete(0);
		
		System.out.print(pdao.create(p));
	}
	
	public void testUpdate() {
		
		p.setLineDesc("123");
		p.setDeleteTime(null);
		p.setId(1);
		p.setIsDelete(0);
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
