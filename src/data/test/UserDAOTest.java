package data.test;

import java.util.HashMap;
import java.util.Map;

import data.bean.User;
import data.dao.UserDAO;
import data.util.OrderType;

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
		//udt.testFindEntity();
		//udt.testFindEntity2();
		//udt.testCount2();
		//udt.testDelete();
	}
	public void testCreate() {
		u.setDeleteTime(null);
		u.setId(5);
		u.setIsDelete(0);
		u.setLevel(2);
		u.setPassword("123");
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
	public void testFindEntity() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", "123");
		System.out.print(udao.findEntity(map));
	}
	public void testFindEntity2() {
		Map<String, String> equalsMap = new HashMap<String, String>();
		Map<String, OrderType> orderMap = new HashMap<String, OrderType>();
		equalsMap.put("username", "123");
		orderMap.put("Id", OrderType.DESC);
		System.out.println(udao.findEntity(equalsMap, orderMap, 1, 5));
	}
	
	public void testCount2() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", "123");
		System.out.print(udao.countEntity(map));
	}
	
	public void testCount3() {
		Map<String, String> equalsMap = new HashMap<String, String>();
		Map<String, OrderType> orderMap = new HashMap<String, OrderType>();
		equalsMap.put("username", "123");
		orderMap.put("Id", OrderType.DESC);
		System.out.println(udao.findEntity(equalsMap, orderMap, 1, 5));
	}
}
