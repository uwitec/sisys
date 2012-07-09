package data.test;

import data.bean.Batch;
import data.bean.DisqKind;
import data.dao.BatchDAO;
import data.dao.DisqKindDAO;

public class DisqKindDAOTest {
	
	DisqKindDAO dkd = new DisqKindDAO();
	DisqKind b = new DisqKind();

	public static void main(String args[]) {
		DisqKindDAOTest test = new DisqKindDAOTest();
		//test.testCreate();
		//test.testUpdate();
		//test.testRead();
		//test.testReadByPk();
		test.testCount();
		//test.testDelete();
	}
	
	public void testCreate() {
		
		b.setDeleteTime(null);
		b.setDisDesc("");
		b.setId(1);
		b.setIsDelete(0);
		b.setKind(1);
		
		System.out.print(dkd.create(b));
	}
	
	public void testUpdate() {
		
		b.setDeleteTime(null);
		b.setDisDesc("不合规格");
		b.setId(1);
		b.setIsDelete(0);
		b.setKind(1);
		
		System.out.print(dkd.update(b,1));
	}
	
	public void testRead() {
		b.setId(1);
		System.out.print(dkd.read(b));
	}
	public void testReadByPk() {
		
		System.out.print(dkd.readByPk(1));
	}
	public void testDelete() {
		b.setId(1);
		System.out.print(dkd.delete(b));
	}
	public void testCount() {
		System.out.print(dkd.count());
	}
}
