package data.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimerTask;

import data.service.ManageDataService;

public class BackupData extends TimerTask{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			String filePath = "D:/backup/";
			ManageDataService mds = new ManageDataService();
			Calendar now = Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		    String fileName = "backup--" + format.format(now.getTime());
		    File folder = new File(filePath);
			if(!folder.exists()) {
				folder.mkdirs();
			}
			OutputStream os = new FileOutputStream(new File(filePath + fileName + ".sql"));
			mds.dbEport(os);
			System.out.println(fileName + "备份成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
