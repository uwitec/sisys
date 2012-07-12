package data.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.servlet.Servlet;

import org.apache.struts2.ServletActionContext;

import data.action.BaseAction;

public class DataSave extends BaseAction{
	private String filePath;
	
	
	public static void main(String[] args) throws Exception {
		 Calendar now = Calendar.getInstance();
		 SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		 System.out.println(format.format(now.getTime()));
		 
	}
	
	
	
	//数据库导出为sql文件
	public String dataExport() throws Exception{		
		try {
			Runtime rt = Runtime.getRuntime();
			Process child = rt.exec("E:/Program Files/MySQL/MySQL Server 5.0/bin/mysqldump -uroot -proot sisys");
			
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
			
			Calendar now = Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		    String fileName = "backup--" + format.format(now.getTime());
			response.setContentType("text/x-sql");
			response.addHeader("Content-Disposition", "attachment; filename=" + fileName + ".sql");
			
			OutputStream os = response.getOutputStream();
			OutputStreamWriter writer = new OutputStreamWriter(os, "GBK");
			writer.write(outStr);
			
			writer.flush();
			
			in.close();
			isr.close();
			br.close();
			writer.close();
			os.close();
			
			System.out.println("output OK!");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
		return null;
	}
	
	//导入sql文件到数据库
	public String dataImport() throws Exception{
		try {
			filePath = request.getParameter("filePath");
			Runtime rt = Runtime.getRuntime();
			Process child = rt.exec("E:/Program Files/MySQL/MySQL Server 5.0/bin/mysql -uroot -proot sisys");
			OutputStream out = child.getOutputStream();
			
			String inStr;
			StringBuffer sb = new StringBuffer("");
			String outStr;
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "GBK"));
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
			
			System.out.println("Success");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return SUCCESS;
	}
	
	public void setFilePath(String filePath){
		this.filePath = filePath;
	}
	
	public String getFilePath(){
		return filePath;
	}
}
