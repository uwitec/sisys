package data.util;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.lang.time.DateUtils;

import com.sun.org.apache.commons.logging.LogFactory;

public class TimerTest {
	public static void main(String[] args) {
		Timer timer = new Timer("test",true);
		System.out.println(DateUtils.MILLIS_IN_DAY);
		timer.schedule(new MyTask(),0,2000);
		
		while(true){//这个是用来停止此任务的,否则就一直循环执行此任务了
            try { 
                int ch = System.in.read();
                if(ch-'c'==0){ 
                    timer.cancel();//使用这个方法退出任务
                    
                }
            } catch (IOException e) { 
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } 
	}
}

class MyTask extends TimerTask{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
		if(cal.get(Calendar.HOUR_OF_DAY) == 0){
			System.out.println(new Date());
		}
		System.out.println(new Date());
		
	}
	
}
