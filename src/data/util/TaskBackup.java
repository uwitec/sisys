package data.util;

import java.util.Date;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang.time.DateUtils;


public class TaskBackup implements ServletContextListener{
	public static final long PERIOD_DAY = DateUtils.MILLIS_IN_DAY;
	
	private Timer timer;

	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
		timer.cancel();
	}

	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		timer = new Timer("数据库备份",true);
		Date date = new Date();
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		
		timer.schedule(new BackupData(),date,PERIOD_DAY);
	}
	
}
