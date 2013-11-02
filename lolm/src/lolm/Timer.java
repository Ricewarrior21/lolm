package lolm;

import org.lwjgl.Sys;

public class Timer {
	long startTick, elapsedTick;
	long time;
	TimerSetting ts;
	
	// Constructor sets base tick, initalizes elapsedTick and time
	// Also initializes TimerSetting
	public Timer() {
		startTick = Sys.getTime() * 1000 / Sys.getTimerResolution();
		elapsedTick = 0;
		time = 1;
		ts = TimerSetting.NORMAL;
	}
	
	// Updates based on tick, measures new tick and takes the difference
	// between elapsedTick and startTick to determine time passed
	public void update() {
		elapsedTick = Sys.getTime() * 1000 / Sys.getTimerResolution();
		elapsedTick -= startTick;
		
		// If elapsedTick divided by timerSpeed has no remainder, increase time
		if ((elapsedTick % ts.speed) == 0) {
			time++;
		}
		
		// Safety measure to make sure tick never goes beyond 2000
		if (elapsedTick >= 2000) {
			startTick += 2000;
		}
	}
	
	// Increase and decrease speed functions
	// Work by looking at current enum position and increasing/decreasing
	// to the next one
	public void increaseSpeed() {
		if (ts.ordinal() < TimerSetting.values().length-1) {
			ts = TimerSetting.values()[ts.ordinal() + 1];
		}
	}
	public void decreaseSpeed() {
		if (ts.ordinal() > 0) {
			ts = TimerSetting.values()[ts.ordinal() - 1];
		}
	}
	
	// Getters
	public long getTime() {
		return time;
	}
	
	public long getTick() {
		return elapsedTick;
	}
	
	public TimerSetting getTimerSetting() {
		return ts;
	}
}
