package data.test;

import data.bean.Batch;
import data.dao.BatchDAO;

public class BatchDAOTest {
	
	BatchDAO bdao = new BatchDAO();
	Batch b = new Batch();

	public static void main(String args[]) {
		BatchDAOTest bdt = new BatchDAOTest();
		//bdt.testCreate();
		//bdt.testUpdate();
		//bdt.testRead();
		//bdt.testReadByPk();
		//bdt.testCount();
		bdt.testDelete();
	}
	public void testCreate() {
		b.setBatchNo("");
		b.setDeleteTime(null);
		b.setDisqNum(1);
		b.setDisqPercent(0.01);
		b.setEndTime(null);
		b.setFlowId(1);
		b.setId(1);
		b.setIsDelete(0);
		b.setProId(1);
		b.setStartTime(null);
		b.setStatus(0);
		b.setTotalNum(1000);
		b.setWorkTabId(1);
		
		System.out.print(bdao.create(b));
	}
	public void testUpdate() {
		b.setBatchNo("");
		b.setDeleteTime(null);
		b.setDisqNum(1);
		b.setDisqPercent(0.01);
		b.setEndTime(null);
		b.setFlowId(1);
		b.setId(1);
		b.setIsDelete(0);
		b.setProId(1);
		b.setStartTime(null);
		b.setStatus(0);
		b.setTotalNum(1001);
		b.setWorkTabId(1);
		
		System.out.print(bdao.update(b,1));
	}
	public void testRead() {
		b.setId(1);
		System.out.print(bdao.read(b));
	}
	public void testReadByPk() {
		
		System.out.print(bdao.readByPk(1));
	}
	public void testDelete() {
		b.setId(1);
		System.out.print(bdao.delete(b));
	}
	public void testCount() {
		System.out.print(bdao.count());
	}
}
