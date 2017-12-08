package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * @author Group 4
 * timer class that times all actions during the game
 *
 */
public class GameTimer extends Thread implements Runnable {
	Timer timer; //swing timer to control ticks
	private int tickPeriod; //in milliseconds
	MainController c; //controller that accesses timer
	
	/** 
	 * constructor - creates timer and fires controller tick
	 * @param c - MainController that should tick
	 */
	public GameTimer(MainController c) {
		this.c = c; //set controller
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
	 * create timer that fires tick method in controller
	 * @param tickPeriod
	 * @return
	 */
	public Timer createTimer(int tickPeriod){
		return new Timer(tickPeriod, new ActionListener(){
		    public void actionPerformed(ActionEvent e) {
		    	//controller tick
		        c.tick(); 
		        }
	});
	}
	
	

	
}

