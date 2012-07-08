package data.test;

import java.util.Date;

import data.bean.Batch;
import data.bean.DailyStaffDisq;
import data.dao.BatchDAO;
import data.dao.DailyStaffDisqDAO;

public class DailyStaffDisqDAOTest {


	DailyStaffDisqDAO dsdd = new DailyStaffDisqDAO();
	DailyStaffDisq d = new DailyStaffDisq();

	public static void main(String args[]) {
		DailyStaffDisqDAOTest test = new DailyStaffDisqDAOTest();
		//test.testCreate();
		//test.testUpdate();
		//test.testRead();
		//test.testReadByPk();
		//test.testCount();
		test.testDelete();
	}
	public void testCreate() {
		d.setDisqdeId(1);
		d.setId(1);
		d.setStaffId(1);
		d.setTime(new Date());
		d.setTotalNum(100);
		
		System.out.print(dsdd.create(d));
	}
	public void testUpdate() {
		d.setDisqdeId(1);
		d.setId(1);
		d.setStaffId(1);
		d.setTime(new Date());
		d.setTotalNum(101);
		
		System.out.print(dsdd.update(d,1));
	}
	public void testRead() {
		d.setId(1);
		System.out.print(dsdd.read(d));
	}
	public void testReadByPk() {
		
		System.out.print(dsdd.readByPk(1));
	}
	public void testDelete() {
		d.setId(1);
		System.out.print(dsdd.delete(d));
	}
	public void testCount() {
		System.out.print(dsdd.count());
	}
}
