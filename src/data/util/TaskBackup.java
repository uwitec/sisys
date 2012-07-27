package data.util;

import java.util.Calendar;
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
		Calendar cal = Calendar.getInstance();
		long now = cal.getTimeInMillis();
		cal.add(Calendar.DATE, 1);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		long next = cal.getTimeInMillis();
		
		timer.schedule(new BackupData(),next-now,PERIOD_DAY);
	}
	
}
