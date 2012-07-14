package data.test;

import data.bean.Flowpath;
import data.dao.FlowpathDAO;

public class FlowpathDAOTest {
	
	FlowpathDAO fdao = new FlowpathDAO();
	Flowpath f = new Flowpath();

	public static void main(String args[]) {
		FlowpathDAOTest test = new FlowpathDAOTest();
		//test.testCreate();
		//test.testUpdate();
		//test.testRead();
		//test.testReadByPk();
		test.testCount();
		//test.testDelete();
	}
	
	public void testCreate() {
		
		f.setDeleteTime(null);
		f.setId(1);
		f.setIsDelete(0);
		f.setProId(1);
		f.setSequence("1-1");
		
		System.out.print(fdao.create(f));
	}
	
	public void testUpdate() {
		
		f.setDeleteTime(null);
		f.setId(1);
		f.setIsDelete(0);
		f.setProId(1);
		f.setSequence("1-1;2-1");
		
		System.out.print(fdao.update(f,1));
	}
	
	public void testRead() {
		f.setId(1);
		System.out.print(fdao.read(f));
	}
	
	public void testReadByPk() {
		
		System.out.print(fdao.readByPk(1));
	}
	
	public void testDelete() {
		
		f.setId(1);
		System.out.print(fdao.delete(f));
	}
	
	public void testCount() {
		System.out.print(fdao.count());
	}
}
