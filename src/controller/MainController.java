package controller;

//TODO: organize imports
import model.*;
import javax.swing.*;
import view.TitleScreen;
import java.util.*;
import java.util.Timer;

public class MainController {
	// private static GameTimer timer;
	public static MainGameModel mainGameModel;
	private static GameTimerThread gameTimerThread;
	public static MiniGameModel miniGame;
	private int tickPeriod = 30; // in milliseconds

	public MainController() {
	}

	public MainGameModel getModel() {
		return mainGameModel;
	}

	public void startGame() {
		mainGameModel = new MainGameModel();
		gameTimerThread = new GameTimerThread(mainGameModel.getGameLengthSeconds(), tickPeriod,this);		gameTimerThread.start();

	}

	public void startTutorial() {
		System.out.println("Start Tutorial");
	}

	protected static void tick() {
		System.out.println("Tick");

		if (gameTimerThread.inMiniGame) {
			miniGame.update();
			if (miniGame.isGameOver()) {
				endMiniGame();
			}
		} else {
			mainGameModel.update();
		}
		if (mainGameModel.isGameOver()) {
			endGame();
		}
		if (mainGameModel.getIsCaught()) {
			launchMiniGame();
		}

	}

	public static void endGame() {
		// TODO: This part isn't working as expected
		gameTimerThread.stopTick();
		System.out.println("Game Over");
		System.out.println("End Screen");
	}

	public static void launchMiniGame() {
		gameTimerThread.enterMiniGameMode();
		miniGame = new MiniGameModel();
		System.out.println("MiniGame Launched...");
		mainGameModel.setCaught(false);

	}

	public static void endMiniGame() {
		gameTimerThread.exitMiniGameMode();
	}

}
