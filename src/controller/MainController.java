package controller;

//TODO: organize imports
import model.*;
import javax.swing.*;
import view.*;
import java.util.*;
import java.util.Timer;

public class MainController {
	// private static GameTimer timer;
	private  MainGameModel mainGameModel;
	private GamePlayScreen gameScreen;
	private  GameTimerThread gameTimerThread;
	private  MiniGameModel miniGame;
	private int tickPeriod = 30; // in milliseconds
	boolean inMiniGame;

	public MainController() {
	}

	/* startGame() - begins game play
	 * 				 creates instance of MainGameModel and the gametimer and starts the game timer
	 */
	public void startGame() {
		mainGameModel = new MainGameModel();
		gameTimerThread = new GameTimerThread(mainGameModel.getGameLengthSeconds(), getTickPeriod(),this);		
		gameTimerThread.start();

	}
	
	/* StartTutorial() -  POTENTIALLY DELETABLE
	 * 					  currently just prints to console
	 */
	public void startTutorial() {
		System.out.println("Start Tutorial");
	}

	/* tick() - controls the model and view updating at each tick
	 * 
	 */
	protected void tick() {
		System.out.println("Tick");
		
		//controls state of the game
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

	/* endGame() - end the GamePlay
	 * 			   //TODO: needs to fire some sort of end score in view
	 * 
	 */
	public void endGame() {
		gameTimerThread.stopTick();
		System.out.println("Game Over");
		System.out.println("End Screen");
	}

	/* launchMiniGame() - initializes minigame launches miniGame
	 * 
	 */
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
		return inMiniGame;
	}
	
	public void setInMiniGame(boolean b){
		inMiniGame = b;
	}

	public MainGameModel getModel(){
		return mainGameModel;
	}
	
	public MainGameModel getMiniGame(){
		return miniGame;
	}

}
