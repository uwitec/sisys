package data.test;

import data.bean.Batch;
import data.bean.Staff;
import data.dao.BatchDAO;
import data.dao.StaffDAO;

public class StaffDAOTest {
	StaffDAO udao = new StaffDAO();
	Staff u = new Staff();

	public static void main(String args[]) {
		StaffDAOTest udt = new StaffDAOTest();
		//udt.testCreate();
		//udt.testUpdate();
		//udt.testRead();
		//udt.testReadByPk();
		udt.testCount();
		//udt.testDelete();
	}
	public void testCreate() {
		u.setDeleteTime(null);
		u.setId(1);
		u.setIsDelete(0);
		u.setDeptId(1);
		u.setKind("");
		u.setStaName("123");
		u.setStaNo("352");
		System.out.print(udao.create(u));
	}
	public void testUpdate() {
		u.setDeleteTime(null);
		u.setId(1);
		u.setIsDelete(0);
		u.setDeptId(1);
		u.setKind("");
		u.setStaName("1223");
		u.setStaNo("352");
		System.out.print(udao.update(u));
	}
	public void testRead() {
		u.setId(1);
		System.out.print(udao.read(u));
	}
	public void testReadByPk() {
		
		System.out.print(udao.readByPk(1));
	}
	public void testDelete() {
		u.setId(1);
		System.out.print(udao.delete(u));
	}
	public void testCount() {
		System.out.print(udao.count());
	}
}
