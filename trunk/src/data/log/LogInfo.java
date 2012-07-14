package data.log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import data.bean.User;

//记录管理员的操作记录
public class LogInfo {
	
	private String filePath = "f://Log//";
	private String fileName = "";
	private File file;
	private Date date = null;
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public void createLog(User user) {
		//创建日志文件，默认保存在F：/Log目录下
		try {
			date = new Date();
			filePath += sdf.format(date);
			fileName = user.getUsername()+".txt";
			File folder = new File(filePath);
			file = new File(filePath + File.separator + fileName);
			if(!folder.exists()) {
				folder.mkdirs();
			}
			if(!file.exists()) {//判断是否存在
				file.createNewFile(); 
			} 
		} catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("创建日志文件错误！");
		}
		
		
	}
	
	//记录日志内容
	public void saveLog(User user, String content, long l) {
		createLog(user);
		try {
			//BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			Writer out = null ;	// 准备好一个输出的对象
			out = new FileWriter(file,true)  ;	// 通过对象多态性，进行实例化
			
			out.write("\r\n");
			out.write(sdf2.format(l)+ "    " + content);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
