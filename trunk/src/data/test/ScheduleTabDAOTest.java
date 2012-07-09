package data.test;

import data.bean.Product;
import data.bean.ScheduleTab;
import data.dao.ProductDAO;
import data.dao.ScheduleTabDAO;

public class ScheduleTabDAOTest {
	
	ScheduleTabDAO sdao = new ScheduleTabDAO();
	ScheduleTab s = new ScheduleTab();

	public static void main(String args[]) {
		ScheduleTabDAOTest test = new ScheduleTabDAOTest();
		//test.testCreate();
		//test.testUpdate();
		//test.testRead();
		//test.testReadByPk();
		//test.testCount();
		//test.testDelete();
	}
	
	public void testCreate() {
		
		s.setId(1);
		s.setBatchId(1);
		s.setTime(null);
		s.setColorNo("");
		s.setNum(100);
		
		System.out.print(sdao.create(s));
	}
	
	public void testUpdate() {
		
		s.setId(1);
		s.setBatchId(1);
		s.setTime(null);
		s.setColorNo("");
		s.setNum(101);
		
		System.out.print(sdao.update(s,1));
	}
	
	public void testRead() {
		s.setId(1);
		System.out.print(sdao.read(s));
	}
	
	public void testReadByPk() {
		
		System.out.print(sdao.readByPk(1));
	}
	
	public void testDelete() {
		
		s.setId(1);
		System.out.print(sdao.delete(s));
	}
	
	public void testCount() {
		System.out.print(sdao.count());
	}
}
