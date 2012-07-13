package data.util;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import data.action.BaseAction;
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

import jxl.*;
import jxl.read.biff.BiffException;

public class ExcelImportor extends BaseAction{
	private String filePath;
	
	public String staffImport() throws Exception {
		filePath = request.getParameter("filePath");
		Workbook book = Workbook.getWorkbook(new FileInputStream(filePath));
		Sheet sheet = book.getSheet(0);
		String title = sheet.getCell(0, 0).getContents();
		if(!title.trim().equals("制造部员工表")){
			return ERROR;
		}
		
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
						return ERROR;
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
						return ERROR;
					}
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
					return ERROR;
				}
			}
		}
		return SUCCESS;
		
	}
	
	public String proLineImport() throws Exception{
		filePath = request.getParameter("filePath");
		Workbook book = Workbook.getWorkbook(new FileInputStream(filePath));
		Sheet sheet = book.getSheet(0);
		String title = sheet.getCell(0, 0).getContents();
		if(!title.trim().equals("制造部及生产线编码表")){
			return ERROR;
		}
		ProductLine proLine;
		ProductLineDAO proLineDAO = new ProductLineDAO();
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
						return ERROR;
					}
				}
			}
			row++;
		}
		
		return SUCCESS;
	}
	
	public String proImport() throws Exception {
		filePath = request.getParameter("filePath");
		Workbook book = Workbook.getWorkbook(new FileInputStream(filePath));
		Sheet sheet = book.getSheet(0);
		String title = sheet.getCell(0, 0).getContents();
		if(!title.trim().equals("产品制造成本表")){
			return ERROR;
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
			return ERROR;
		}
		
		equalsMap.clear();
		equalsMap.put("lineNo", sheet.getCell(4, 32).getContents());
		proLineList = productLineDAO.findEntity(equalsMap);
		if(proLineList.size() == 0){
			return ERROR;
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
				return ERROR;
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
				return ERROR;
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
				return ERROR;
			}
			equalsMap.clear();
			equalsMap.put("procName", process.getProcName());
			processList = new ProcessesDAO().findEntity(equalsMap);
			if(processList.size() == 0){
				return ERROR;
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
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public static void main(String[] args) throws Exception {
//		staffImport();
//		proLineImport();
//		proImport();
	}
}
