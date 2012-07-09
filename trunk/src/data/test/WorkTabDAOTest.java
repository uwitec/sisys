package data.test;

import data.bean.Product;
import data.bean.WorkTab;
import data.dao.ProductDAO;
import data.dao.WorkTabDAO;

public class WorkTabDAOTest {
	
	WorkTabDAO wdao = new WorkTabDAO();
	WorkTab w = new WorkTab();

	public static void main(String args[]) {
		WorkTabDAOTest test = new WorkTabDAOTest();
		//test.testCreate();
		//test.testUpdate();
		//test.testRead();
		//test.testReadByPk();
		//test.testCount();
		test.testDelete();
	}
	
	public void testCreate() {
		
		w.setProcId(1);
		w.setQuNum(10);
		w.setDisqNum(1);
		w.setIsOver(0);
		w.setOverTime(null);
		w.setIsEnd(0);
		w.setId(1);
		
		System.out.print(wdao.create(w));
	}
	
	public void testUpdate() {
		
		w.setProcId(1);
		w.setQuNum(11);
		w.setDisqNum(1);
		w.setIsOver(0);
		w.setOverTime(null);
		w.setIsEnd(0);
		w.setId(1);
		
		System.out.print(wdao.update(w,1));
	}
	
	public void testRead() {
		w.setId(1);
		System.out.print(wdao.read(w));
	}
	
	public void testReadByPk() {
		
		System.out.print(wdao.readByPk(1));
	}
	
	public void testDelete() {
		
		w.setId(1);
		System.out.print(wdao.delete(w));
	}
	
	public void testCount() {
		System.out.print(wdao.count());
	}
}
