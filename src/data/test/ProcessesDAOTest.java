package data.test;

import data.bean.Processes;
import data.dao.ProcessesDAO;

public class ProcessesDAOTest {
	
	ProcessesDAO pdao = new ProcessesDAO();
	Processes p = new Processes();

	public static void main(String args[]) {
		ProcessesDAOTest test = new ProcessesDAOTest();
		//test.testCreate();//1
		//test.testUpdate();//1
		//test.testRead();//1
		//test.testReadByPk();//1
		//test.testCount();//1
		//test.testDelete();
	}
	
	public void testCreate() {
		
		p.setColorNo("123");
		p.setDeleteTime(null);
		p.setId(1);
		p.setIsDelete(0);
		p.setProcName("");
		p.setProcNo("");
		p.setUnitCost(1);
		p.setUnitOutput(2);
		
		System.out.print(pdao.create(p));
	}
	
	public void testUpdate() {
		
		p.setColorNo("123");
		p.setDeleteTime(null);
		p.setId(1);
		p.setIsDelete(0);
		p.setProcName("");
		p.setProcNo("");
		p.setUnitCost(1);
		p.setUnitOutput(3);
		
		System.out.print(pdao.update(p,1));
	}
	
	public void testRead() {
		p.setId(1);
		System.out.print(pdao.read(p));
	}
	
	public void testReadByPk() {
		
		System.out.print(pdao.readByPk(1));
	}
	
	public void testDelete() {
		
		p.setId(1);
		System.out.print(pdao.delete(p));
	}
	
	public void testCount() {
		System.out.print(pdao.count());
	}
}
