package controller;

//TODO: organize imports
import model.*;
import javax.swing.*;
import view.*;
import java.util.*;
import java.util.Timer;

public class MainController {
	// private static GameTimer timer;
	private  MainModel model;
	private GamePlayScreen gameScreen;
	private  GameTimer gameTimer;
	//private  MiniGameModel miniGame;
	private int tickPeriod = 30; // in milliseconds
	boolean inMiniGame;
	
	private boolean useView;
	// private mainView gameView;

	public static void main(String[] args) {
		MainController game = new MainController(false);
		System.out.println(game.getModel());
		//game.gameTimer = new GameTimer(game);
		//TODO: get input and then iterate through game
		while (!game.getModel().getGameOver()) {
			game.tick();
			System.out.println(game.getModel());
		}
	}
	
	public MainController(boolean b) {
		useView = b;
		model = new MainModel();
		if (useView) {
			gameScreen = new GamePlayScreen();
		}
		inMiniGame = false;
	}
	public MainController(boolean b, int len, int hgt) {
		useView = b;
		model = new MainModel(len, hgt);
		if (useView) {
			gameScreen = new GamePlayScreen();
		}
		inMiniGame = false;
	}
	
	public MainController(boolean b, int len, int hgt, int ulg) {
		useView = b;
		model = new MainModel(len,hgt,ulg);
		if (useView) {
			gameScreen = new GamePlayScreen();
		}
	}

	/* startGame() - begins game play
	 * 				 creates instance of MainGameModel and the gametimer and starts the game timer
	 */
	public void startGame() {
		//gameTimerThread = new GameTimerThread(mainGameModel.getGameLengthSeconds(), getTickPeriod(),this);		
		//gameTimerThread.start();

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
		/*System.out.println("Tick");
		//controls state of the game
		if (inMiniGame) {
			miniGame.update();
			if (miniGame.getGameOver()) {
				endMiniGame();
			}
		} else {
			mainGameModel.update();
			if (useView) {
				//GamePlayScreen.paint();
			}
		}
		if (mainGameModel.getGameOver()) {
			endGame();
		}
		if (mainGameModel.getFishy().getIsCaught()) {
			launchMiniGame();
		}
		*/
		if (useView) {
			// TODO: stuff from view?
			//model.update();
		}else {
			Scanner sc = new Scanner(System.in);
			String angle = sc.nextLine();
			String speed = sc.nextLine();
			int deltaTheta;
			int newSpeed;
			try {
				deltaTheta = Integer.parseInt(angle);
			}catch(NumberFormatException ex) {
				deltaTheta = 0;
			}
			try {
				newSpeed = Integer.parseInt(speed);
			}catch(NumberFormatException ex) {
				newSpeed = 0;
			}
			model.update(newSpeed,deltaTheta);
		}
	}

	/* endGame() - end the GamePlay
	 * 			   //TODO: needs to fire some sort of end score in view
	 * 
	 */
	public void endGame() {
		//gameTimer.stopTimer();
		System.out.println("Game Over");
		System.out.println("End Screen");
	}

	/* launchMiniGame() - initializes minigame launches miniGame
	 * 
	 *
	public void launchMiniGame() {
		inMiniGame = true;
		miniGame = new MiniGameModel();
		System.out.println("MiniGame Launched...");
		mainGameModel.getFishy().setCaught(false);

	}

	public void endMiniGame() {
		inMiniGame = false;
	}
	*/
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

	public MainModel getModel(){
		return model;
	}
	/*
	public MainGameModel getMiniGame(){
		return miniGame;
	}
	*/
	
	public void setGamePlayScreen(GamePlayScreen g){
		gameScreen = g;
	}
	
	public GamePlayScreen getGamePlayScreen(){
		return gameScreen;
	}

}
