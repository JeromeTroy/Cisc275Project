package controller;

//TODO: organize imports
import model.*;
import javax.swing.*;
import view.TitleScreen;
import java.util.*;
import java.util.Timer;

public class MainController {
	// private static GameTimer timer;
	public  MainGameModel mainGameModel;
	private  GameTimerThread gameTimerThread;
	public  MiniGameModel miniGame;
	private int tickPeriod = 30; // in milliseconds
	boolean inMiniGame; // is the game tick being paused here? The timer
	// displayed is independent of the game timer

	public MainController() {
	}

	public MainGameModel getModel() {
		return mainGameModel;
	}

	public void startGame() {
		mainGameModel = new MainGameModel();
		gameTimerThread = new GameTimerThread(mainGameModel.getGameLengthSeconds(), getTickPeriod(),this);		gameTimerThread.start();

	}

	public void startTutorial() {
		System.out.println("Start Tutorial");
	}

	protected void tick() {
		System.out.println("Tick");

		if (inMiniGame) {
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

	public void endGame() {
		gameTimerThread.stopTick();
		System.out.println("Game Over");
		System.out.println("End Screen");
	}

	public void launchMiniGame() {
		inMiniGame = true;
		miniGame = new MiniGameModel();
		System.out.println("MiniGame Launched...");
		mainGameModel.setCaught(false);

	}

	public void endMiniGame() {
		inMiniGame = false;
	}

	public int getTickPeriod() {
		return tickPeriod;
	}

	public void setTickPeriod(int tickPeriod) {
		this.tickPeriod = tickPeriod;
	}
	
	public boolean inMiniGame(){
		return  inMiniGame;
	}
	
	public void setInMiniGame(boolean b){
		inMiniGame = b;
	}

}
