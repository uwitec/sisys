package data.test;

import data.bean.Product;
import data.bean.WorkForm;
import data.dao.ProductDAO;
import data.dao.WorkFormDAO;

public class WorkFormDAOTest {
	
	WorkFormDAO wdao = new WorkFormDAO();
	WorkForm w = new WorkForm();

	public static void main(String args[]) {
		WorkFormDAOTest test = new WorkFormDAOTest();
		//test.testCreate();
		//test.testUpdate();
		//test.testRead();
		//test.testReadByPk();
		//test.testCount();
		test.testDelete();
	}
	
	public void testCreate() {
		
		w.setStaId(1);
		w.setProcId(1);
		w.setBatchId(1);
		w.setProId(1);
		w.setQuaNum(10);
		w.setDisDetail("");
		w.setTime(null);
		w.setDeleteTime(null);
		w.setIsDelete(0);
		
		System.out.print(wdao.create(w));
	}
	
	public void testUpdate() {
		
		w.setStaId(1);
		w.setProcId(1);
		w.setBatchId(1);
		w.setProId(1);
		w.setQuaNum(11);
		w.setDisDetail("");
		w.setTime(null);
		w.setDeleteTime(null);
		w.setIsDelete(0);
		
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
