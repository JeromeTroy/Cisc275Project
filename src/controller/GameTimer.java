package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import model.*;
import javax.swing.Timer;

public class GameTimer extends Thread implements Runnable {
	Timer timer; 
	private int tickPeriod; //in milliseconds
	private int gameLength; // in milliseconds
	private int timeElapsed = 0; // in milliseconds
	//private MainController c;  
	//TODO: implement miniGame timer
	
	public GameTimer(MainController c) {
		this.tickPeriod = c.getTickPeriod();
		this.gameLength = c.getGameLength();
		
		//create Swing timer with actionListener
		this.timer = new Timer(tickPeriod, new ActionListener(){
		    public void actionPerformed(ActionEvent e) {
		        c.tick();
		        //c.getGamePlayScreen().updateFishPosition();
		        }
		});
		
		timeElapsed+=tickPeriod;
		
	}
	
	//start the timer
	public void start(){
		timer.start();
	}
	
	//stop the timer
	public void stopTimer(){
		try {
			timer.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	//get timer
	public Timer getSwingTimer(){
		return timer;
	}

	
	/* toString() - returns the remaining time in the game in min:second format
	 * (non-Javadoc)
	 * @see java.lang.Thread#toString()
	 */
	@Override
	public String toString() {
		int millis = gameLength - timeElapsed;
		return String.format("%2d min, %02d sec", TimeUnit.MILLISECONDS.toMinutes(millis), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
		//return  // TODO: return actual time
	}
	
}

