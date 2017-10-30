package controller;

import java.util.Timer;
import java.util.TimerTask;

import model.*;

public class MainGameThread extends Thread implements Runnable {
	Timer timer;
	boolean inMiniGame; //is the game tick being paused here? The timer displayed is independent of the game timer
	int tickPeriod;
	
	public MainGameThread(int tickPeriod) {
		// this.model = model;
		this.timer = new Timer();
		this.tickPeriod = tickPeriod;
	}

	@Override
	public void run() {
		System.out.println("Main Game Run Begin");
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				
					MainController.tick();
				
			}
		}, 0, tickPeriod);

	}
	
	public void enterMiniGameMode(){
		inMiniGame = true;
	}
	
	public void exitMiniGameMode(){
		inMiniGame = false;
	}
	
	public void stopTick(){
		timer.cancel();
	}
}
