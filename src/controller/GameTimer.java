package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import model.*;
import javax.swing.Timer;

public class GameTimer extends Thread implements Runnable {
	Timer timer; 
	private int tickPeriod; //in milliseconds
	MainController c;
	
	public GameTimer(MainController c) {
		this.c = c;
		this.tickPeriod = c.getTickPeriod();
		
		//create Swing timer with actionListener
		this.timer = createTimer(tickPeriod);
		
		
	}
	
	//start the timer
	public void start(){
		timer.start();
	}
	
	//stop the timer
	public void stopTimer(){
		timer.stop();	
	}
	
	public void pauseTimer(){
		timer.stop();
		timer = createTimer(tickPeriod);
	}
	
	
	//get timer
	public Timer getSwingTimer(){
		return timer;
	}
	
	//create timer
	public Timer createTimer(int tickPeriod){
		return new Timer(tickPeriod, new ActionListener(){
		    public void actionPerformed(ActionEvent e) {
		        c.tick();
		        }
	});
	}
	
	

	
}

