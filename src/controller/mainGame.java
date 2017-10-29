package controller;

import java.util.Timer;
import java.util.TimerTask;

import model.*;

public class mainGame implements Runnable {
	Timer timer;
	boolean isPaused = true; //is the game tick being paused here? The timer displayed is independent of the game timer

	public mainGame() {
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
}
