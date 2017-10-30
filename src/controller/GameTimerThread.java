package controller;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import model.*;

public class GameTimerThread extends Thread implements Runnable {
	Timer timer;
	boolean inMiniGame; // is the game tick being paused here? The timer
						// displayed is independent of the game timer
	int tickPeriod;
	int gameLength; // in milliseconds
	int currentTimeElapsed = 0; // in milliseconds

	public GameTimerThread(int gameLengthInSeconds, int tickPeriod) {
		// this.model = model;
		this.timer = new Timer();
		this.tickPeriod = tickPeriod;
		this.gameLength = gameLengthInSeconds * 1000;
	}

	@Override
	public void run() {
		System.out.println("Main Game Run Begin");
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				currentTimeElapsed += tickPeriod;
				MainController.tick();

			}
		}, 0, tickPeriod);

	}

	public void enterMiniGameMode() {
		inMiniGame = true;
	}

	public void exitMiniGameMode() {
		inMiniGame = false;
	}

	public void stopTick() {
		timer.cancel();
	}

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
