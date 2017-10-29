package controller;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


public class GameTimer implements Runnable {
	Timer timer;
	int gameLength; //in seconds
	int currentTimeElapsed; //in milliseconds
	
	
	public GameTimer(int gameLength){
		timer = new Timer();
		this.gameLength = gameLength;
	}
	
	@Override
	public void run(){
		
	}
	
	public String toString(){
//		String.format("%2d min, %02d sec", 
//			    TimeUnit.MILLISECONDS.toMinutes(millis),
//			    TimeUnit.MILLISECONDS.toSeconds(millis) - 
//			    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
//			);
		return "time"; //TODO: return actual time
		
	}
	
	
	
}
