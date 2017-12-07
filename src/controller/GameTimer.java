package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import model.*;
import javax.swing.Timer;

/**
 * @author Group 4
 * timer class that times all actions during the game
 *
 */
public class GameTimer extends Thread implements Runnable {
	Timer timer; 
	private int tickPeriod; //in milliseconds
	MainController c;
	
	/**
	 * @param c
	 * sets the MainController
	 */
	public GameTimer(MainController c) {
		this.c = c;
		this.tickPeriod = c.getTickPeriod();
		
		//create Swing timer with actionListener
		this.timer = createTimer(tickPeriod);
		
		
	}
	
	/**
	 * start the timer
	 */
	public void start(){
		timer.start();
	}
	
	/**
	 * stop the timer
	 */
	public void stopTimer(){
		timer.stop();	
	}
	
	/**
	 * pause the timer
	 */
	public void pauseTimer(){
		timer.stop();
		timer = createTimer(tickPeriod);
	}
	
	
	/**
	 * get timer
	 */
	public Timer getSwingTimer(){
		return timer;
	}
	
	/**
	 * create timer
	 * @param tickPeriod
	 * @return
	 */
	public Timer createTimer(int tickPeriod){
		return new Timer(tickPeriod, new ActionListener(){
		    public void actionPerformed(ActionEvent e) {
		        c.tick();
		        }
	});
	}
	
	

	
}

