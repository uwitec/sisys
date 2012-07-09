package data.test;

import data.bean.Product;
import data.bean.StaffDetail;
import data.dao.ProductDAO;
import data.dao.StaffDetailDAO;

public class StaffDetailDAOTest {
	
	StaffDetailDAO sdao = new StaffDetailDAO();
	StaffDetail s = new StaffDetail();

	public static void main(String args[]) {
		StaffDetailDAOTest test = new StaffDetailDAOTest();
		//test.testCreate();
		//test.testUpdate();
		//test.testRead();
		//test.testReadByPk();
		//test.testCount();
		test.testDelete();
	}
	
	public void testCreate() {
		
		s.setStaffId(1);
		s.setProName("");
		s.setProNo("123");
		s.setProcName("");
		s.setQuaNum(10);
		s.setgWaste(1);
		s.setlWaste(1);
		
		System.out.print(sdao.create(s));
	}
	
	public void testUpdate() {
		
		s.setStaffId(1);
		s.setProName("");
		s.setProNo("123");
		s.setProcName("");
		s.setQuaNum(11);
		s.setgWaste(1);
		s.setlWaste(1);
		
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
