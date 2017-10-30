package controller;

import java.util.Timer;
import java.util.TimerTask;

import model.*;

public class MainGameThread extends Thread implements Runnable {
	Timer timer;
	boolean inMiniGame; //is the game tick being paused here? The timer displayed is independent of the game timer

	public MainGameThread() {
		// this.model = model;
		this.timer = new Timer();
	}

	@Override
	public void run() {
		System.out.println("Main Game Run Begin");
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				
					MainController.tick();
				
			}
		}, 0, 1000);

	}
	
	public void enterMiniGameMode(){
		inMiniGame = true;
	}
	
	public void exitMiniGameMode(){
		inMiniGame = false;
	}
}
