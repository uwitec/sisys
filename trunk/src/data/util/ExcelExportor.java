package data.util;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.Boolean;

import org.apache.struts2.ServletActionContext;

import data.action.BaseAction;

import jxl.*;
import jxl.write.*;
import jxl.write.Number;

public class ExcelExportor extends BaseAction{
	private String title;
	private String content;
	private int cols;
	private WritableFont font1;
	private WritableFont font2;
	private WritableCellFormat format1;
	private WritableCellFormat format2;
	
	public String tableExportor() throws Exception{
		request.setCharacterEncoding("GBK");
		title = request.getParameter("title");
		content = request.getParameter("content");
		if(content.isEmpty()){
			return ERROR;
		}
		
		response.setContentType("application/ms-excel");
		response.addHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(title, "UTF-8") + ".xls");
		OutputStream os = response.getOutputStream();
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
		
		return null;
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
		
		String[] trs = content.split("</TR>");
		for(String tr : trs){
			number = 1;
			String[] ss = tr.split("</TD>");
			for(String s : ss){
				begin = s.indexOf("<TD");
				if(begin == -1){
					continue;
				}
				
				s = s.substring(begin + 3);
				index = s.indexOf(">");
				TD td = new TD();
				
				begin = s.indexOf("rowSpan=");
				if(begin != -1){
					end = s.indexOf(" ",begin);
					if(end == -1){
						end = index;
					}
					numberStr = s.substring(begin + 8, end).replace('\"' , ' ' ).replace('\'' , ' ' ).trim();
					number = Integer.parseInt(numberStr);
					td.rowspan = number;
				}
				
				begin = s.indexOf("colSpan=");
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
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	
	public String getContent(){
		return content;
	}
	
	public static void main(String[] args) throws Exception {
//		 WritableWorkbook book = Workbook.createWorkbook(new File("test.xls"));
//		 WritableSheet sheet = book.createSheet("第一页", 0);
//		 Label label = new Label(0,0,"Test");
//		 sheet.addCell(label);
//		 Number number = new Number(1,0,555.12541);
//		 sheet.addCell(number);
//		 
//		 book.write();
//		 book.close();
		 
//		String s = " rowspan = <cheng>";
//		System.out.println(s.replaceAll("<.*?>", " 1"));
		
//		String content = "<tr align=\"center\"><td width=10%>员工名称</td><td width=10%>张三</td><td width=10%>工号</td><td colspan=2 width=20%>29001040</td><td width=10%>所属部门</td><td colspan=2 width=20%>机电</td></tr>";
//		List<TD> list = new ArrayList<TD>();
//		list = getTDContent(content);
//		for(TD td : list){
//			System.out.println(td);
//		}
	}
}

class TD { 
	int rowspan = 1; 
	int colspan = 1; 
	String content; 
	
	public String toString(){
		return "TD [rowspan=" + rowspan + "; colspan=" + colspan + "; content=" + content + ";]";
	}
}
