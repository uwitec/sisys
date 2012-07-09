package data.test;

import data.bean.Batch;
import data.bean.StaffKind;
import data.dao.BatchDAO;
import data.dao.StaffKindDAO;

public class StaffKindDAOTest {
	StaffKindDAO udao = new StaffKindDAO();
	StaffKind u = new StaffKind();

	public static void main(String args[]) {
		StaffKindDAOTest udt = new StaffKindDAOTest();
		//udt.testCreate();
		//udt.testUpdate();
		//udt.testRead();
		//udt.testReadByPk();
		//udt.testCount();
		//udt.testDelete();
	}
	public void testCreate() {
		u.setDeleteTime(null);
		u.setId(2);
		u.setIsDelete(0);
		u.setKindDesc("kindDesc");
		System.out.print(udao.create(u));
	}
	public void testUpdate() {
		u.setDeleteTime(null);
		u.setId(1);
		u.setIsDelete(0);
		u.setKindDesc("kindDesc");
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
