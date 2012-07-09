package data.test;

import data.bean.Batch;
import data.bean.Department;
import data.dao.BatchDAO;
import data.dao.DepartmentDAO;

/**
 * 
 * @author huangxin
 * 测试完成
 */
public class DepartmentDAOTest {
	DepartmentDAO udao = new DepartmentDAO();
	Department u = new Department();

	public static void main(String args[]) {
		DepartmentDAOTest udt = new DepartmentDAOTest();
		//udt.testCreate();//1
		//udt.testUpdate();//1
		//udt.testRead();//1
		//udt.testReadByPk();//1
		//udt.testCount();//1
		udt.testDelete();
	}
	public void testCreate() {
		u.setDeleteTime(null);
		u.setId(1);
		u.setIsDelete(0);
		u.setDeptNo("1");
		u.setDeptName("123");
		System.out.print(udao.create(u));
	}
	public void testUpdate() {
		u.setDeleteTime(null);
		u.setId(1);
		u.setIsDelete(0);
		u.setDeptNo("1");
		u.setDeptName("124");
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
