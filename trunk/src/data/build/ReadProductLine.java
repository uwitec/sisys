package data.build;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import data.bean.ProductLine;
import data.dao.ProductLineDAO;

//读XLS文件，得到productLine的集合并将其存入数据库中
class ReadProductLine {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD");
	List<ProductLine> productLineList = new ArrayList<ProductLine>();
	ProductLine productLine;
	public List<ProductLine> getProductLineList(Sheet sheet) throws ParseException {
		for(int rowNum=1; rowNum<sheet.getColumns(); rowNum ++) {
			Cell[] c = sheet.getRow(rowNum);
			productLine = new ProductLine();
			productLine.setId(Integer.parseInt(c[0].getContents()));
			productLine.setLineDesc(c[1].getContents());
			productLine.setIsDelete(Integer.parseInt(c[2].getContents()));
			productLine.setDeleteTime(c[3].getContents().equals("null") ? null : sdf.parse(c[3].getContents()));
			
			productLineList.add(productLine);
		}
		return productLineList;
	} 
	public void saveProductLineList() {
		for(int i=0; i<productLineList.size(); i++) {

			ProductLineDAO productLineDao = new ProductLineDAO();
			productLineDao.create(productLineList.get(i));
		}
	}
	public void save(Sheet sheet) {
		try {
			getProductLineList(sheet);
			saveProductLineList();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}

