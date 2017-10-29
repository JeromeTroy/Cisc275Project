package controller;

import java.util.Timer;
import java.util.TimerTask;

import model.*;

public class MainGame extends Thread implements Runnable {
	Timer timer;
	boolean isPaused; //is the game tick being paused here? The timer displayed is independent of the game timer

	public MainGame() {
		// this.model = model;
		this.timer = new Timer();
	}

	@Override
	public void run() {
		System.out.println("Main Game Run Begin");
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if (!isPaused) {
					MainController.tick();
				}
			}
		}, 0, 30);

	}
	
	public void pause(){
		isPaused = true;
	}
	
	public void unpause(){
		isPaused = false;
	}
}
