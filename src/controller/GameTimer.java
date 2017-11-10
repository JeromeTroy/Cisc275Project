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
	private int currentTimeElapsed = 0; // in milliseconds
	//private MainController c;  
	//TODO: implement miniGame timer
	
	public GameTimer(MainController c) {
		//create Swing timer with actionListener
		this.timer = new Timer(40, new ActionListener(){
		    public void actionPerformed(ActionEvent e) {
		        c.getGamePlayScreen().paintScreen();
		        c.tick();
		        //c.getGamePlayScreen().updateFishPosition();
		        }
		});
		this.tickPeriod = c.getTickPeriod();
		this.gameLength = c.getModel().getGameLengthSeconds() * 1000;
		currentTimeElapsed+=tickPeriod;
		//this.c =c;
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

