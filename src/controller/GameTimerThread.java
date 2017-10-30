package controller;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import model.*;

public class GameTimerThread extends Thread implements Runnable {
	Timer timer; 
	private int tickPeriod; //in milliseconds
	private int gameLength; // in milliseconds
	private int currentTimeElapsed = 0; // in milliseconds
	private MainController c;  
	//TODO: implement miniGame timer
	
	public GameTimerThread(int gameLengthInSeconds, int tickPeriod, MainController c) {
		//initialize parameters
		this.timer = new Timer();
		this.tickPeriod = tickPeriod;
		this.gameLength = gameLengthInSeconds * 1000;
		this.c =c;
	}

	/* run() - schedules the tick and keeps track of all timing involved in the game
	 * (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		System.out.println("Main Game Run Begin");
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				currentTimeElapsed += tickPeriod;
				c.tick();

			}
		}, 0, c.getTickPeriod());

	}
	
	/* stopTick() - method to stop the timer thread to end all ticking
	 * 
	 */
	public void stopTick() {
		timer.cancel();
	}
	
	/* toString() - returns the remaining time in the game in min:second format
	 * (non-Javadoc)
	 * @see java.lang.Thread#toString()
	 */
	@Override
	public String toString() {
		// String.format("%2d min, %02d sec",
		// TimeUnit.MILLISECONDS.toMinutes(millis),
		// TimeUnit.MILLISECONDS.toSeconds(millis) -
		// TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
		// );
		return "time"; // TODO: return actual time
	}
	
}

