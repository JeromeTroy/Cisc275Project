package controller;


import model.*;
import javax.swing.*;
import view.*;
//import view.GameOverScreen;

import java.util.*;
import java.util.Timer;

/**
 * @author Group 4
 * Controlls the entire game
 *
 */
public class MainController {
	//Models
	private  MainModel model;
	private Tutorial tutorial;
	
	//Views
	private GamePlayScreen gameScreen;
	private static TitleScreen titleScreen;
	private static GameOverScreen gameOverScreen;
	private static TutorialScreen tutorialScreen;
	private static Window window;
	private JComponent currScreen;
	
	//image file locations
	private final String foodURL = "src/view/images/foodsmall.png";
	private final String trashURL = "src/view/images/trashsmall.png";
	private final String bgURL = "src/view/images/bg.png";
	private final String humanURL = "";
	private final String fishURL = "src/view/images/fishie.png";
	private final String minibgURL = "src/view/images/bg_minigame.png";
	private final String endbg_goodURL = "src/view/images/bg_goodend.png";
	private final String endbg_badURL = "src/view/images/bg_badend.png";
	private final String diverURL = "src/view/images/diver.png";
	private final String diverdarkURL = "src/view/images/diver2.png";
	private final String titleURL = "src/view/images/title_screen_final.png";
	
	
	//Timing
	private  GameTimer gameTimer; //game timer that fires ticks
	//private int gameLength; //in milliseconds
	
	//Settings
	private int tickPeriod = 30; // in milliseconds
	//boolean inMiniGame;
	public static boolean useView;
	
	//TODO: set vals here to control game;
	private int numRepeats = 10;
	private double timeInMin = 1;
	private double trashAccumulationMultiplier = 0.01; //adds 1 + multiplier of trash on screen per tick; keep under 20% //0 = trash & food //-1 no trash
	private int accumulationval = 20; //controls how fast accumulation occurs
	// private mainView gameView;
	private final int speed = 8;
	
	
	/**
	 * main method; opens console or view based on setting. Game setting must be set in method
	 * @param args
	 * main execution
	 */
	public static void main(String[] args) {
		//TODO: allows the program to be run with args to set the program to use the view or not
		MainController game = new MainController(true);
		if (game.useView){
			game.openView();
		} else {
			game.openConsole();
		}		
	}
	
	
	/**
	 * openView - create window and open the titlescreen
	 * 
	 */
	public void openView() {
		//create a window to hold the game screens
		window = new Window();
		
		//open title screen
		newGame();
	}
	
	/**
	 * starts a new game
	 */
	public void newGame() {
		MainController tmp = this;
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				titleScreen = TitleScreen.activateTitle(window, tmp);
				currScreen = titleScreen;
			}
		});
	}
	
	/** 
	 * openConsole - runs the game from the console
	 */
	private void openConsole(){
		//TODO: where did this go????
	}
	
	/**
	 * creates a MainController with a model, and sets the scroll speed of the model
	 * initializes tutorial
	 */
	public MainController(){
		tutorial = new Tutorial();
		//inMiniGame = false;
		model = new MainModel();
		model.setSpeed(speed);
		model.setTrashAccumulateMultiplier(trashAccumulationMultiplier);
		model.setAccumulationval(accumulationval);
	}
	/**
	 * MainController(boolean) - constructor
	 * @param b - should the view be used?
	 */
	public MainController(boolean b) {
		this();
		useView = b;
		
		
	}
	
	/**
	 * @return
	 * get the scroll speed of the game
	 */
	public int getSpeed() {
		return speed;
	}
	/**
	 * @param b 	should view be used
	 * @param len 	length of map
	 * @param hgt	height of map
	 */
	public MainController(boolean b, int len, int hgt) {
		this(b);
		model = new MainModel(len, hgt);
		
	}
	
	/**
	 * @param b 	use view
	 * @param len	map length
	 * @param hgt	map height
	 * @param ulg	unique length of map
	 */
	public MainController(boolean b, int len, int hgt, int ulg) {
		this(b);
		model = new MainModel(len,hgt,ulg);
		
	}


	/**
	 * startGame() - begins game play
	 * 				 creates instance of MainGameModel and the gametimer and starts the game timer
	 */
	public void startGame() {
		System.out.println("Start Game");
		//start timer to update model
		gameTimer = new GameTimer(this);
		gameTimer.start();
		currScreen = gameScreen;
		
		if (useView) {
			currScreen = gameScreen.activateGamePlayScreen(this, window);
			window.addTimer(gameTimer.getSwingTimer());
		}

	}
	
	
	/**
	 * startTutorial - initializes the tutorial window
	 */
	public void startTutorial() {
		System.out.println("Start Tutorial");
		tutorial = new Tutorial();
		TutorialScreen.activateTutorial(this, window); //setTitleScreen
		currScreen = tutorialScreen;
	}
	
//	
	/**
	 * stop the game and display end game screen
	 */
	public void endGame() {
		//stop ticking
		gameTimer.stopTimer();
		
		//print game over when game is won or when game has no time left
		if (getModel().getHasWon()) {
			System.out.println("Game Over");
		} else if (getModel().getRemainingTime() <= 0) {
			System.out.println("Game Over");
		}
		
		// reset model
		model = null;
		model = new MainModel();
		System.out.println("End Screen");
		MainController tmp = this;
		
		//setup game over screen
		gameOverScreen = GameOverScreen.activateGameOver(window, tmp);
		currScreen = gameOverScreen;
	}
	
	/** tick() - controls the model and view updating at each tick
	 * 
	 */
	protected void tick() {
		GamePlayScreen gameView;
		if (useView) { //game play mode
			gameView = (GamePlayScreen) currScreen;
			
			//clear trash off screen
			//model.getStuffSet().removeStuffBeforeXCoord((int)(model.getMap().getOrigin().getX()));
		}else { //consolve view
			
			//scanner input
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
			if (useView){
				//gameView.setU
				model.getInMiniGame();
			}
		}
	}	
	
	/**
	 * showing title
	 */
	public void showTitleScreen(){
		switchScreen(titleScreen);
	}
	

	/**
	 * show tutorial
	 */
	public void showTutorialScreen(){
		switchScreen(tutorialScreen);
	}
	
	
	/**
	 * switching between screens
	 * @param a 	screen to switch to
	 */
	public void switchScreen(JPanel a){
		System.out.println(a);
		window.setContentPane(a);
		window.revalidate();
	}

	/**
	 * length of time for one tick
	 * @return
	 */
	public int getTickPeriod() {
		return tickPeriod;
	}

	/**
	 * set time for one tick
	 * @param tickPeriod
	 */
	public void setTickPeriod(int tickPeriod) {
		this.tickPeriod = tickPeriod;
	}
	

	

	/**
	 * get the game model
	 * @return	game model
	 */
	public MainModel getModel(){
		return model;
	}
	
	/**
	 * get the tutorial model
	 * @return tutorial model
	 */
	public Tutorial getTutorial(){
		return tutorial;
	}
	
	
	/**
	 * set up game play screen
	 * @param g
	 */
	public void setGamePlayScreen(GamePlayScreen g){
		gameScreen = g;
	}
	
	/**
	 * get the game play screen
	 * @return
	 */
	public GamePlayScreen getGamePlayScreen(){
		return gameScreen;
	}


	/**
	 * get food image file location
	 * @return food image file location
	 */
	public String getFoodURL() {
		return foodURL;
	}


	/**
	 * get trash image file location
	 * @return trash image file location
	 */
	public String getTrashURL() {
		return trashURL;
	}


	/**
	 * get background image file location
	 * @return background image file location
	 */
	public String getBgURL() {
		return bgURL;
	}


	/**
	 * get diver image file location
	 * @return diver image file location
	 */
	public String getHumanURL() {
		return humanURL;
	}


	/**
	 * get fish image file location
	 * @return fish image file location
	 */
	public String getFishURL() {
		return fishURL;
	}


	/**
	 * get the current game play screen
	 * @return
	 */
	public GamePlayScreen getGameScreen() {
		return gameScreen;
	}


	/**
	 * set up the game play screen
	 * @param gameScreen
	 */
	public void setGameScreen(GamePlayScreen gameScreen) {
		this.gameScreen = gameScreen;
	}


	/**
	 * get the title screen
	 * @return
	 */
	public static TitleScreen getTitleScreen() {
		return titleScreen;
	}


	/**
	 * set titlescreen
	 * @param titleScreen
	 */
	public static void setTitleScreen(TitleScreen titleScreen) {
		MainController.titleScreen = titleScreen;
	}


	/**
	 * get the tutorial screen
	 * @return
	 */
	public static TutorialScreen getTutorialScreen() {
		return tutorialScreen;
	}


	/**
	 * set the tutorial screen
	 * @param tutorialScreen
	 */
	public static void setTutorialScreen(TutorialScreen tutorialScreen) {
		MainController.tutorialScreen = tutorialScreen;
	}


	/**
	 * get the current window
	 * @return
	 */
	public static Window getWindow() {
		return window;
	}


	/**
	 * set current window
	 * @param window
	 */
	public static void setWindow(Window window) {
		MainController.window = window;
	}


	/**
	 * minigame background image
	 * @return
	 */
	public String getMinibgURL() {
		return minibgURL;
	}


	/**
	 * ending background (winning)
	 * @return
	 */
	public String getEndbg_goodURL() {
		return endbg_goodURL;
	}


	/**
	 * ending background (losing)
	 * @return
	 */
	public String getEndbg_badURL() {
		return endbg_badURL;
	}


	/**
	 * diver image
	 * @return
	 */
	public String getDiverURL() {
		return diverURL;
	}


	/**
	 * alternative diver image
	 * @return
	 */
	public String getDiverdarkURL() {
		return diverdarkURL;
	}
	
	/**
	 * get the time displayed
	 * @return
	 */
	public String getTimeString() {
		return model.timeString();
	}


	/**
	 * title screen image
	 * @return
	 */
	public String getTitleURL() {
		return titleURL;
	}


	/**
	 * remaining time
	 * @return
	 */
	public int getRemainingTime() {
		return model.getRemainingTime();
	}


	/**
	 * whether the game is over
	 * @return
	 */
	public boolean getGameOver() {
		return model.getGameOver();
	}

	/**
	 * whether the player was victorious
	 * @return
	 */
	public boolean getHasWon() {
		return model.getHasWon();
	}


	/**
	 * get score
	 * @return
	 */
	public int getPlayerScore() {
		return model.getPlayerScore();
	}
	
	
	/**
	 * get map repeating lengths
	 * @return
	 */
	public int getNumRepeats() {
		return numRepeats;
	}
	
	/**
	 * get game length in mins
	 * @return total length in mins
	 */
	public double getTime() {
		return timeInMin;
	}


	public int getAccumulationval() {
		return accumulationval;
	}


	public void setAccumulationval(int accumulationval) {
		this.accumulationval = accumulationval;
	}

}