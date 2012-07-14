package data.test;

import data.bean.WorkHoursTab;
import data.dao.WorkHoursTabDAO;

public class WorkHoursTabDAOTest {
	
	WorkHoursTabDAO wdao = new WorkHoursTabDAO();
	WorkHoursTab w = new WorkHoursTab();

	public static void main(String args[]) {
		WorkHoursTabDAOTest test = new WorkHoursTabDAOTest();
		//test.testCreate();
		//test.testUpdate();
		//test.testRead();
		//test.testReadByPk();
		//test.testCount();
		//test.testDelete();
	}
	
	public void testCreate() {
		
		w.setStaId(1);
		w.setTime(null);
		w.setWorkHours(10);
		w.setSalary(100);
		w.setId(1);
		
		System.out.print(wdao.create(w));
	}
	
	public void testUpdate() {
		
		w.setStaId(1);
		w.setTime(null);
		w.setWorkHours(10);
		w.setSalary(101);
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
