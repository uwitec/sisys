package data.build;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import data.bean.Flowpath;
import data.bean.ProductLine;
import data.dao.ProductLineDAO;

//读XLS文件，得到Flowpath的集合并将其存入数据库中
class ReadFlowpath {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD");
	List<Flowpath> flowpathList = new ArrayList<Flowpath>();
	Flowpath flowpath;
	public List<Flowpath> getFlowpathList(Sheet sheet) throws ParseException {
		for(int rowNum=1; rowNum<sheet.getColumns(); rowNum ++) {
			Cell[] c = sheet.getRow(rowNum);
			flowpath = new Flowpath();
			flowpath.setId(Integer.parseInt(c[0].getContents()));
			flowpath.setSequence(c[1].getContents());
			flowpath.setProId(Integer.parseInt(c[2].getContents()));
			flowpath.setIsDelete(Integer.parseInt(c[3].getContents()));
			flowpath.setDeleteTime(c[4].getContents().equals("null") ? null : sdf.parse(c[4].getContents()));
			
			flowpathList.add(flowpath);
		}
		return flowpathList;
	} 
	public void saveFlowpathList() {
		for(int i=0; i<flowpathList.size(); i++) {

			/*FlowpathDAO flowpathDAO = new FlowpathDAO();
			FlowpathDAO.create(flowpathList.get(i));*/
		}
	}
	public void save(Sheet sheet) {
		try {
			/*getProductLineList(sheet);
			saveProductLineList();*/
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}

