package data.bean;

import data.dao.WorkFormDAO;

public class Page {

	int size = 6;
	int pageNow = 1;
	int pageCount = 0;
	int rowCount = 0;
	int previous = pageCount - 1;
	int next = pageCount + 1;
	
	public Page() {
		WorkFormDAO wfd = new WorkFormDAO();
		rowCount = wfd.count();
		if(rowCount % size == 0) {
			pageCount = rowCount / size;
		} else {
			pageCount = rowCount / size + 1;
		}
		previous = pageNow - 1;
		next = pageNow + 1;
		if(previous <= 0) {
			previous = 1;
		}
	}
	

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	
	
}
