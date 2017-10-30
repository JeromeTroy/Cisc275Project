package controller;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


public class GameTimer implements Runnable {
	Timer timer;
	int gameLength; //in milliseconds
	int currentTimeElapsed = 0; //in milliseconds
	int tickPeriod;
	
	/* 2 arg Constructor: int, int
	 * gameLengthInSeconds - the length of the game in seconds
	 * tickPeriod - how frequently the tick occurs
	 */
	public GameTimer(int gameLengthInSeconds, int tickPeriod){
		timer = new Timer();
		this.gameLength = gameLengthInSeconds*1000;
		this.tickPeriod = tickPeriod;
	}
	
	@Override
	public void run(){
		timer.scheduleAtFixedRate(new TimerTask(){
			public void run(){
				currentTimeElapsed+=tickPeriod;
			}
		}, 0, tickPeriod);
	}
	
	@Override
	public String toString(){
//		String.format("%2d min, %02d sec", 
//			    TimeUnit.MILLISECONDS.toMinutes(millis),
//			    TimeUnit.MILLISECONDS.toSeconds(millis) - 
//			    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
//			);
		return "time"; //TODO: return actual time
		
	}
	
	public void StopTimer(){
		timer.cancel();
	}
	
	
	
}
