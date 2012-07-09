package data.test;

import data.bean.Batch;
import data.bean.User;
import data.dao.BatchDAO;
import data.dao.UserDAO;

public class UserDAOTest {
	UserDAO udao = new UserDAO();
	User u = new User();

	public static void main(String args[]) {
		UserDAOTest udt = new UserDAOTest();
		udt.testCreate();
		//udt.testUpdate();
		//udt.testRead();
		//udt.testReadByPk();
		//udt.testCount();
		//udt.testDelete();
	}
	public void testCreate() {
		u.setDeleteTime(null);
		u.setId(1);
		u.setIsDelete(0);
		u.setLevel(1);
		u.setPassword("");
		u.setUsername("123");
		System.out.print(udao.create(u));
	}
	public void testUpdate() {
		u.setDeleteTime(null);
		u.setId(1);
		u.setIsDelete(0);
		u.setLevel(1);
		u.setPassword("123");
		u.setUsername("123");
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
