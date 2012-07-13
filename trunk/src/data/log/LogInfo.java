package data.log;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import data.bean.User;

//记录管理员的操作记录
public class LogInfo {
	
	private String filePath = "f://Log";
	private String fileName = "";
	private static Calendar c = Calendar.getInstance();
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public LogInfo() {
		File file = new File(filePath);
		if(!file.exists()) {
			file.mkdirs();
		}
	}
	public void saveLog(File file, User user) {
		
	}
}
