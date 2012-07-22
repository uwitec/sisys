package data.build;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import data.bean.Product;
import data.dao.ProductDAO;

//读XLS文件，得到product的集合并将其存入数据库中
class ReadProduct {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD");
	List<Product> productList = new ArrayList<Product>();
	Product product;
	public List<Product> getProductList(Sheet sheet) throws ParseException {
		for(int rowNum=1; rowNum<sheet.getColumns(); rowNum ++) {
			Cell[] c = sheet.getRow(rowNum);
			product = new Product();
			product.setId(Integer.parseInt(c[0].getContents()));
			product.setDeptId(Integer.parseInt(c[1].getContents()));
			product.setProlineId(Integer.parseInt(c[2].getContents()));
			product.setProNo(c[3].getContents());
			product.setProName(c[4].getContents());
			product.setTime(c[5].getContents().equals("null") ? null : sdf.parse(c[5].getContents()));
			product.setDisqNum(Integer.parseInt(c[6].getContents()));
			product.setDisqPerc(Double.parseDouble(c[7].getContents()));
			product.setTotalNum(Integer.parseInt(c[8].getContents()));
			product.setIsDelete(Integer.parseInt(c[9].getContents()));
			product.setDeleteTime(c[10].getContents().equals("null") ? null : sdf.parse(c[10].getContents()));
			
			productList.add(product);
		}
		return productList;
	} 
	public void saveProductList() {
		for(int i=0; i<productList.size(); i++) {

			ProductDAO productDao = new ProductDAO();
			productDao.create(productList.get(i));
		}
	}
	public void save(Sheet sheet) {
		try {
			getProductList(sheet);
			saveProductList();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
