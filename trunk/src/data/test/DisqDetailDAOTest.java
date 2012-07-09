package data.test;

import data.bean.Batch;
import data.bean.DisqDetail;
import data.dao.BatchDAO;
import data.dao.DisqDetailDAO;

public class DisqDetailDAOTest {
	
	DisqDetailDAO ddd = new DisqDetailDAO();
	DisqDetail d = new DisqDetail();

	public static void main(String args[]) {
		DisqDetailDAOTest test = new DisqDetailDAOTest();
		//test.testCreate();
		//test.testUpdate();
		//test.testRead();
		//test.testReadByPk();
		//test.testCount();
		test.testDelete();
	}
	public void testCreate() {
		d.setDisKId(1);
		d.setId(1);
		d.setNextId(1);
		d.setNum(1);
		System.out.print(ddd.create(d));
	}
	public void testUpdate() {
		d.setDisKId(1);
		d.setId(1);
		d.setNextId(1);
		d.setNum(2);
		
		System.out.print(ddd.update(d,1));
	}
	public void testRead() {
		d.setId(1);
		System.out.print(ddd.read(d));
	}
	public void testReadByPk() {
		
		System.out.print(ddd.readByPk(1));
	}
	public void testDelete() {
		d.setId(1);
		System.out.print(ddd.delete(d));
	}
	public void testCount() {
		System.out.print(ddd.count());
	}
}
