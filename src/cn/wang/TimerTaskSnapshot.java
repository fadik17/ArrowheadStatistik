package cn.wang;

import java.util.Date;
import java.util.TimerTask;

public class TimerTaskSnapshot extends TimerTask{


	public TimerTaskSnapshot() {
		// TODO Auto-generated constructor stub
	}

	public void run() {    
		
		Date date = new Date(this.scheduledExecutionTime());        
		System.out.println("A new update is made at：" + date);    }
}
