package data.util;

import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang.time.DateUtils;

public class TaskBackup implements ServletContextListener{
	public static final long PERIOD_DAY = DateUtils.MILLIS_IN_DAY;
	
	private Timer timer;

	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
		timer = new Timer("数据库备份",true);
		timer.schedule(new BackupData(), PERIOD_DAY);
	}

	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		
	}
	
}

class BackupData extends TimerTask{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
