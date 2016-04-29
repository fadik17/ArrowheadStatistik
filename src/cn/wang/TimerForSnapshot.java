package cn.wang;

import java.util.Timer;


public class TimerForSnapshot {

	private Timer timer;
	

	public TimerForSnapshot() {
		timer = new Timer();        
		timer.schedule(new TimerTaskSnapshot(), 1000, 2000);
	}	
}
