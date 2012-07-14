package data.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import data.bean.Department;
import data.bean.Flowpath;
import data.bean.Processes;
import data.bean.Product;
import data.bean.ProductLine;
import data.bean.Staff;
import data.bean.StaffKind;
import data.dao.DepartmentDAO;
import data.dao.FlowpathDAO;
import data.dao.ProcessesDAO;
import data.dao.ProductDAO;
import data.dao.ProductLineDAO;
import data.dao.StaffDAO;
import data.dao.StaffKindDAO;
import data.util.ColorUtil;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ManageDataService {
	private int cols;
	private WritableFont font1;
	private WritableFont font2;
	private WritableCellFormat format1;
	private WritableCellFormat format2;
	
	//表格导出为excel文件
	public void tableExport(OutputStream os,String title,String content) throws Exception{
		System.out.println(title);
		System.out.println(content);
		WritableWorkbook book = Workbook.createWorkbook(os);
		book.setProtected(true);
		init();
		WritableSheet sheet = book.createSheet(title, 0);
		int row = 0;
		int col = 0;
		Label label = null;
		
		
		
		List<TD> listTD = getTDContent(content);
		Map<String,Boolean> map = new HashMap<String,Boolean>();
		
		sheet.mergeCells(0, 0, cols-1, 1);
		label = new Label(col,row,title,format1);
		sheet.addCell(label);
		row += 2;
		
		for (TD td : listTD) {
			System.out.println(td);
			if (td == null) { 
				row++; 
				col = 0; 
				continue; 
			} 

			while (map.get(col + "-" + row) != null) { 
				col++; 
			} 

			if (td.colspan > 1 || td.rowspan > 1) { 
				sheet.mergeCells(col, row, col + td.colspan - 1, row + td.rowspan - 1); 
				for (int i = col; i <= col + td.colspan - 1; i++) { 
					for (int j = row; j <= row + td.rowspan - 1; j++) { 
						map.put(i + "-" + j, true); 
					} 
				} 
			} 

			label = new Label(col, row, td.content,format2); 
			sheet.addCell(label); 

			map.put(col + "-" + row, true); 
			col += td.colspan; 
		}
		
		book.write();
		book.close();
	}
	
	//数据库导出为sql文件
	public void dbEport(OutputStream os){
		try{
			Runtime rt = Runtime.getRuntime();
			Process child = rt.exec("D:/Java/MySQL/MySQL Server 5.0/bin/mysqldump -uroot -proot sisys");
			
			InputStream in = child.getInputStream();
			InputStreamReader isr = new InputStreamReader(in,"utf8");
			String inStr;
			StringBuffer sb = new StringBuffer("");
			String outStr;
			BufferedReader br = new BufferedReader(isr);
			while ((inStr = br.readLine()) != null){
				sb.append(inStr + "\r\n");
			}
			outStr = sb.toString();
			
			OutputStreamWriter writer = new OutputStreamWriter(os, "GBK");
			writer.write(outStr);
			
			writer.flush();
			
			in.close();
			isr.close();
			br.close();
			writer.close();
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//导入sql文件到数据库
	public void dbImport(File myFile){
		try {
			Runtime rt = Runtime.getRuntime();
			Process child = rt.exec("D:/Java/MySQL/MySQL Server 5.0/bin/mysql -uroot -proot sisys");
			OutputStream out = child.getOutputStream();
			
			String inStr;
			StringBuffer sb = new StringBuffer("");
			String outStr;
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(myFile), "GBK"));
			while((inStr = br.readLine()) != null){
				sb.append(inStr + "\r\n");
			}
			outStr = sb.toString();
			OutputStreamWriter writer = new OutputStreamWriter(out, "utf8");
			writer.write(outStr);
			writer.flush();
			out.close();
			br.close();
			writer.close();
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	//员工表导入
	public String staffImport(File myFile) throws Exception {
		Workbook book = Workbook.getWorkbook(new FileInputStream(myFile));
		Sheet sheet = book.getSheet(0);
		String title = sheet.getCell(0, 0).getContents();
		if(!title.replaceAll(" ", "").equals("制造部员工表")){
			return "dataError";
		}
		
		int flag = 0;
		int row = 1;
		String deptName;
		int deptNo = 1;
		Cell[] cells;
		Department department = null;
		DepartmentDAO departmentDAO = new DepartmentDAO();
		Staff staff;
		StaffDAO staffDAO = new StaffDAO();
		Set<String> kindSet = new HashSet<String>();
		List<Department> depList;
		List<Staff> staList;
		List<StaffKind> kindList;
		StaffKind staffKind;
		StaffKindDAO staffKindDAO = new StaffKindDAO();
		
		while(row < sheet.getRows()){
			Map<String,String> equalsMap = new HashMap<String,String>();
			if(sheet.getCell(1, row).getContents().isEmpty()){
				deptName = sheet.getCell(0, row).getContents();
				equalsMap.put("deptName", deptName);
				
				depList = departmentDAO.findEntity(equalsMap);
				if(depList.size() == 0){
					department = new Department();
					department.setDeptName(deptName);
					department.setDeptNo(String.valueOf(deptNo));
					System.out.println(department.getDeptNo());
					int r = new DepartmentDAO().create(department);
					if(r <= 0){
						return "error";
					}
				}
				department = departmentDAO.findEntity(equalsMap).get(0);
				deptNo++;
				row++;
			}else{
				cells = sheet.getRow(row);
				equalsMap.put("staNo", cells[1].getContents());
				staList = staffDAO.findEntity(equalsMap);
				if(staList.size() != 0){
					System.out.println(staList.size());
					staff = staList.get(0);
				}else{
					staff = new Staff();
					staff.setStaName(cells[0].getContents());
					staff.setStaNo(cells[1].getContents());
					staff.setDeptId(department.getId());
					staff.setKind(cells[4].getContents());
					
					System.out.println(staff.getStaName());
					int r = new StaffDAO().create(staff);
					if(r <= 0){
						return "error";
					}
					flag = 1;
				}
				kindSet.add(cells[4].getContents());
				System.out.println(cells[0].getContents());
			}
			row++;
		}
		
		for(String kind : kindSet){
			Map<String,String> equalsMap = new HashMap<String,String>();
			equalsMap.put("kindDesc", kind);
			kindList = staffKindDAO.findEntity(equalsMap);
			if(kindList.size() == 0){
				staffKind = new StaffKind();
				staffKind.setKindDesc(kind);
				int r = new StaffKindDAO().create(staffKind);
				if(r <= 0){
					return "error";
				}
			}
		}
		if(flag == 0){
			return "dataSame";
		}
		return "success";
		
	}
	
	//生产线编码表导入
	public String proLineImport(File myFile) throws Exception{
		Workbook book = Workbook.getWorkbook(new FileInputStream(myFile));
		Sheet sheet = book.getSheet(0);
		String title = sheet.getCell(0, 0).getContents();
		if(!title.replaceAll(" ", "").equals("制造部及生产线编码表")){
			return "dataError";
		}
		ProductLine proLine;
		ProductLineDAO proLineDAO = new ProductLineDAO();
		int flag = 0;
		int row = 2;
		Cell[] cells;
		List<ProductLine> proLineList;
		System.out.println("test");
		while(row < sheet.getRows()){
			if(!sheet.getCell(0, row).getContents().isEmpty()){
				Map<String,String> equalsMap = new HashMap<String,String>();
				cells = sheet.getRow(row);
				equalsMap.put("lineNo", cells[0].getContents());
				proLineList = proLineDAO.findEntity(equalsMap);
				if(proLineList.size() == 0){
					proLine = new ProductLine();
					proLine.setLineNo(Integer.parseInt(cells[0].getContents()));
					proLine.setLineDesc(cells[1].getContents());
					System.out.println(proLine.getLineDesc());
					int r = new ProductLineDAO().create(proLine);
					if(r <= 0){
						return "error";
					}
					flag = 1;
				}
			}
			row++;
		}
		if(flag == 0){
			return "dataSame";
		}
		
		return "success";
	}
	
	//产品成本表导入
	public String proImport(File myFile) throws Exception {
		Workbook book = Workbook.getWorkbook(new FileInputStream(myFile));
		Sheet sheet = book.getSheet(0);
		String title = sheet.getCell(0, 0).getContents();
		if(!title.replaceAll(" ", "").equals("产品制造成本表")){
			return "dataError";
		}
		
		Product product;
		ProductDAO productDAO = new ProductDAO();
		Flowpath flowpath = new Flowpath();
		FlowpathDAO flowpathDAO = new FlowpathDAO();
		Processes process;
		ProcessesDAO processesDAO;
		DepartmentDAO departmentDAO = new DepartmentDAO();
		List<Department> deptList;
		ProductLineDAO productLineDAO = new ProductLineDAO();
		List<ProductLine> proLineList;
		List<Integer> sequenceList = new ArrayList<Integer>();
		int row = 2;
		Cell[] cells;
		List<Product> proList;
		List<Processes> processList;
		cells = sheet.getRow(row);
		
		Map<String,String> equalsMap = new HashMap<String,String>();
		
		equalsMap.clear();
		equalsMap.put("deptName", cells[8].getContents());
		deptList = departmentDAO.findEntity(equalsMap);
		if(deptList.size() == 0){
			return "error";
		}
		
		equalsMap.clear();
		equalsMap.put("lineNo", sheet.getCell(4, 32).getContents());
		proLineList = productLineDAO.findEntity(equalsMap);
		if(proLineList.size() == 0){
			return "noproLine";
		}
		
		equalsMap.clear();
		equalsMap.put("proNo", cells[5].getContents());
		proList = productDAO.findEntity(equalsMap);
		if(proList.size() == 0){
			product = new Product();
			
			product.setDeptId(deptList.get(0).getId());
			product.setProlineId(proLineList.get(0).getId());
			product.setProNo(cells[5].getContents());
			product.setProName(cells[2].getContents());
			product.setTotalNum(cells[11].getContents().isEmpty()?0:Integer.parseInt(cells[11].getContents()));
			int r = productDAO.create(product);
			if(r <= 0){
				return "error";
			}
		}else{
			product = proList.get(0);
			
			product.setDeptId(deptList.get(0).getId());
			product.setProlineId(proLineList.get(0).getId());
			product.setProNo(cells[5].getContents());
			product.setProName(cells[2].getContents());
			product.setTotalNum(cells[11].getContents().isEmpty()?0:Integer.parseInt(cells[11].getContents()));
			int r = productDAO.update(product, product.getId());
			if(r <= 0){
				return "error";
			}
		}
		product = productDAO.findEntity(equalsMap).get(0);
		
		for(row = 32;row <= 52;row++){
			cells = sheet.getRow(row);
			String color;
			if(cells[0].getContents().isEmpty()){
				break;
			}
			process = new Processes();
			processesDAO = new ProcessesDAO();
			color = ColorUtil.toHexEncoding(cells[2].getCellFormat().getBackgroundColour());
			process.setColorNo(color);
			process.setProcNo(cells[0].getContents());
			process.setProcName(cells[1].getContents());
			process.setUnitOutput(Integer.parseInt(cells[6].getContents().trim()));
			process.setUnitCost(Double.parseDouble(cells[8].getContents().trim()));
			System.out.println(Double.parseDouble(cells[8].getContents().trim()));
			int r = processesDAO.create(process);
			if(r <= 0){
				return "error";
			}
			equalsMap.clear();
			equalsMap.put("procName", process.getProcName());
			processList = new ProcessesDAO().findEntity(equalsMap);
			if(processList.size() == 0){
				return "error";
			}
			sequenceList.add(processList.get(processList.size()-1).getId());
			
		}
		
		String sequence;
		StringBuffer sb = new StringBuffer("");
		for(int i = 0;i < sequenceList.size();i++){
			if(sb != null){
				sb.append("-");
			}
			sb.append(sequenceList.get(i));
		}
		sequence = sb.toString();
		flowpath = new Flowpath();
		flowpath.setSequence(sequence);
		flowpath.setProId(product.getId());
		int r = flowpathDAO.create(flowpath);
		if(r <= 0){
			return "error";
		}
		
		return "success";
	}
	
	//提取td属性及内容
	private List<TD> getTDContent(String content){
		int begin = -1;
		int end = -1;
		int index = -1;
		String numberStr;
		int number;
		int flag = 0;
		List<TD> list = new ArrayList<TD>();
		if(content.indexOf("</TR>") >= 0){
			content = content.replaceAll("TR", "tr");
			content = content.replaceAll("TD", "td");
			content = content.replaceAll("rowSpan", "rowspan");
			content = content.replaceAll("colSpan", "colspan");
		}
		
		String[] trs = content.split("</tr>");
		for(String tr : trs){
			number = 1;
			String[] ss = tr.split("</td>");
			for(String s : ss){
				begin = s.indexOf("<td");
				if(begin == -1){
					continue;
				}
				
				s = s.substring(begin + 3);
				index = s.indexOf(">");
				TD td = new TD();
				
				begin = s.indexOf("rowspan=");
				if(begin != -1){
					end = s.indexOf(" ",begin);
					if(end == -1){
						end = index;
					}
					numberStr = s.substring(begin + 8, end).replace('\"' , ' ' ).replace('\'' , ' ' ).trim();
					number = Integer.parseInt(numberStr);
					td.rowspan = number;
				}
				
				begin = s.indexOf("colspan=");
				if(begin != -1){
					end = s.indexOf(" ",begin);
					if(end == -1){
						end = index;
					}
					numberStr = s.substring(begin + 8, end).replace('\"' , ' ' ).replace('\'' , ' ' ).trim();
					number = Integer.parseInt(numberStr);
					td.colspan = number;
					if(flag == 0){
						cols += number;
					}
				}else{
					if(flag == 0){
						cols++;
					}
				}
				
				td.content = s.substring(index + 1).replaceAll("<.*?>", "").replaceAll(" ", "").trim();
				list.add(td);
			}
			list.add(null);
			flag = 1;
			
		}
		
		return list;
	}
	
	//表格格式初始化
	private void init() throws Exception{
		font1= new WritableFont(WritableFont.TIMES,16,WritableFont.BOLD); 
		font2=new WritableFont(WritableFont.createFont("楷体 _GB2312"),10,WritableFont.NO_BOLD );
		format1=new WritableCellFormat(font1);
		format2=new WritableCellFormat(font2);
		format1.setAlignment(jxl.format.Alignment.CENTRE);
		format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		format2.setAlignment(jxl.format.Alignment.CENTRE);
		format2.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	}
	
}

class TD { 
	int rowspan = 1; 
	int colspan = 1; 
	String content; 
	
	@Override
	public String toString(){
		return "TD [rowspan=" + rowspan + "; colspan=" + colspan + "; content=" + content + ";]";
	}
}
