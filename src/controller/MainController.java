package controller;

//TODO: organize imports
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
	//Model
	private  MainModel model;
	private Tutorial tutorial;
	
	//Views
	private GamePlayScreen gameScreen;
	private static TitleScreen titleScreen;
	private static GameOverScreen gameOverScreen;
	private static TutorialScreen tutorialScreen;
	private static Window window;
	private JComponent currScreen;
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
	private final int speed = 8;
	
	//Timer
	private  GameTimer gameTimer;
	private int gameLength; //in milliseconds
	
	//Settings
	private int tickPeriod = 30; // in milliseconds
	boolean inMiniGame;
	public static boolean useView;
	// private mainView gameView;

	
	
	/**
	 * @param args
	 * main execution
	 */
	public static void main(String[] args) {
		//allows the program to be run with args to set the program to use the view or not
		MainController game = new MainController(true);
		if (game.useView){
			game.openView();
		} else {
			game.openConsole();
		}
		//game.gameTimer = new GameTimer(game);
		//TODO: get input and then iterate through game
		//while (!game.getModel().getGameOver()) {
			//game.tick();
		//}
		
		
		
	}
	
	
	/**
	 * openView - opens the titlescreen
	 * 
	 */
	public void openView() {
		window = new Window();
		
		//Runnable theGame = new RunGame();
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
		
	}
	
	/**
	 * creates a MainController with a model, and sets the scroll speed of the model
	 * initializes tutorial
	 */
	public MainController(){
		tutorial = new Tutorial();
		inMiniGame = false;
		model = new MainModel();
		model.setSpeed(speed);
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
		
		
//		while(currScreen == tutorialScreen){
//			int cx = tutorialScreen.getCursorX();
//			int cy = tutorialScreen.getCursorY();
//		
//			double dist = tutorial.getMainCharacter().getPosition().distFrom(cx, cy);
//			int angle = tutorial.getMainCharacter().getPosition().angleBetween(cx, cy);
//		
//			tutorial.update(0, angle - tutorial.getMainCharacter().getAngle());
//			
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}
	
	//controls state of the game
//	if (inMiniGame) {
//		miniGame.update();
//		if (miniGame.getGameOver()) {
//			endMiniGame();
//		}
//	} else {
//		mainGameModel.update();
//		if (useView) {
//			//GamePlayScreen.paint();
//		}
//	}
//	if (mainGameModel.getGameOver()) {
//		endGame();
//	}
//	if (mainGameModel.getFishy().getIsCaught()) {
//		launchMiniGame();
//	}
//	
	/**
	 * stop the game and display end game screen
	 */
	public void endGame() {
		gameTimer.stopTimer();
		if (getModel().getHasWon()) {
			System.out.println("Game Over");

		}
		else if (getModel().getRemainingTime() <= 0) {
			System.out.println("Game Over");

		}else {
			
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
		if (useView) {
			gameView = (GamePlayScreen) currScreen;
		}else {
			//System.out.println("Console Tick"); //TODO: remove
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

	/* endGame() - end the GamePlay
	 * 			   //TODO: needs to fire some sort of end score in view
	 * 
	 */
	
	
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
		//window.repaint();
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
	 * @return	whether the minigame is active
	 */
	public boolean inMiniGame(){
		return inMiniGame;
	}
	
	/**
	 * set if the minigame is active
	 * @param b
	 */
	public void setInMiniGame(boolean b){
		inMiniGame = b;
	}

	/**
	 * get the current state of the model
	 * @return
	 */
	public MainModel getModel(){
		return model;
	}
	
	public Tutorial getTutorial(){
		return tutorial;
	}
	
	/*
	public MainGameModel getMiniGame(){
		return miniGame;
	}
	*/
	
	/**
	 * set up game play screen
	 * @param g
	 */
	public void setGamePlayScreen(GamePlayScreen g){
		gameScreen = g;
	}
	
	/**
	 * get the game play screen state
	 * @return
	 */
	public GamePlayScreen getGamePlayScreen(){
		return gameScreen;
	}


	/**
	 * food image
	 * @return
	 */
	public String getFoodURL() {
		return foodURL;
	}


	/**
	 * trash image
	 * @return
	 */
	public String getTrashURL() {
		return trashURL;
	}


	/**
	 * background image
	 * @return
	 */
	public String getBgURL() {
		return bgURL;
	}


	/**
	 * diver image
	 * @return
	 */
	public String getHumanURL() {
		return humanURL;
	}


	/**
	 * fish image
	 * @return
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
	 * get the length of the game
	 * @return
	 */
	public int getGameLength() {
		return gameLength;
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
		// TODO Auto-generated method stub
		return model.getRemainingTime();
	}


	/**
	 * whether the game is over
	 * @return
	 */
	public boolean getGameOver() {
		// TODO Auto-generated method stub
		return model.getGameOver();
	}

	/**
	 * whether the player was victorious
	 * @return
	 */
	public boolean getHasWon() {
		return model.getHasWon();
	}


	public int getPlayerScore() {
		// TODO Auto-generated method stub
		return model.getPlayerScore();
	}
	
	//public int foodHypontenuse(){
	//}
	
	
	
	

}