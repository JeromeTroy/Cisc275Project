package controller;

//TODO: organize imports
import model.*;
import javax.swing.*;
import view.*;
import java.util.*;
import java.util.Timer;

public class MainController {
	//Model
	private  MainModel model;
	private Tutorial tutorial;
	
	//Views
	private GamePlayScreen gameScreen;
	private static TitleScreen titleScreen;
	private static TutorialScreen tutorialScreen;
	private static Window window;
	private static JPanel currScreen;
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
	
	
	//Timer
	private  GameTimer gameTimer;
	private int gameLength; //in milliseconds
	
	//Settings
	private int tickPeriod = 30; // in milliseconds
	boolean inMiniGame;
	private static boolean useView;
	// private mainView gameView;

	
	
	public static void main(String[] args) {
		//allows the program to be run with args to set the program to use the view or not
		if (args.length == 0){
			useView = true;
			openView();
		} else {
			useView = false;
			openConsole();
			
		}
		
	}
	
	
	/**
	 * openView - opens the titlescreen
	 * 
	 */
	private static void openView() {
		window = new Window();
		
		Runnable theGame = new RunGame();
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				titleScreen = TitleScreen.activateTitle(window);
				currScreen = titleScreen;
				//GamePlayScreen.activateGamePlayScreen();
			}
		});
	}
	
	/** 
	 * openConsole - runs the game from the console
	 */
	private static void openConsole(){
		MainController game = new MainController(false);
		//game.gameTimer = new GameTimer(game);
		//TODO: get input and then iterate through game
		while (!game.getModel().getGameOver()) {
			game.tick();
		}
	}
	
	/**
	 * MainController(boolean) - constructor
	 * @param b - should the view be used?
	 */
	public MainController(boolean b) {
		useView = b;
		model = new MainModel();
		inMiniGame = false;
		
		
	}
	public MainController(boolean b, int len, int hgt) {
		useView = b;
		model = new MainModel(len, hgt);
		inMiniGame = false;
		
	}
	
	public MainController(boolean b, int len, int hgt, int ulg) {
		useView = b;
		model = new MainModel(len,hgt,ulg);
		inMiniGame = false;
		
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
		
		
		if (useView) {
			GamePlayScreen.activateGamePlayScreen(this, window);
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
	
	/* tick() - controls the model and view updating at each tick
	 * 
	 */
	protected void tick() {

		if (useView) {
			//System.out.println("View Tick"); //TODO: remove
			//model.getMainCharacter();
			model.update(0,0);
			
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
		}
	}

	/* endGame() - end the GamePlay
	 * 			   //TODO: needs to fire some sort of end score in view
	 * 
	 */
	public void endGame() {
		gameTimer.stopTimer();
		System.out.println("Game Over");
		System.out.println("End Screen");
		showTitleScreen();
	}
	
	
	public void showTitleScreen(){
		switchScreen(titleScreen);
	}
	

	public void showTutorialScreen(){
		switchScreen(tutorialScreen);
	}
	
	
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


	public String getFoodURL() {
		return foodURL;
	}


	public String getTrashURL() {
		return trashURL;
	}


	public String getBgURL() {
		return bgURL;
	}


	public String getHumanURL() {
		return humanURL;
	}


	public String getFishURL() {
		return fishURL;
	}


	public GamePlayScreen getGameScreen() {
		return gameScreen;
	}


	public void setGameScreen(GamePlayScreen gameScreen) {
		this.gameScreen = gameScreen;
	}


	public static TitleScreen getTitleScreen() {
		return titleScreen;
	}


	public static void setTitleScreen(TitleScreen titleScreen) {
		MainController.titleScreen = titleScreen;
	}


	public static TutorialScreen getTutorialScreen() {
		return tutorialScreen;
	}


	public static void setTutorialScreen(TutorialScreen tutorialScreen) {
		MainController.tutorialScreen = tutorialScreen;
	}


	public static Window getWindow() {
		return window;
	}


	public static void setWindow(Window window) {
		MainController.window = window;
	}


	public int getGameLength() {
		return gameLength;
	}


	public String getMinibgURL() {
		return minibgURL;
	}


	public String getEndbg_goodURL() {
		return endbg_goodURL;
	}


	public String getEndbg_badURL() {
		return endbg_badURL;
	}


	public String getDiverURL() {
		return diverURL;
	}


	public String getDiverdarkURL() {
		return diverdarkURL;
	}
	
	//public int foodHypontenuse(){
	//}
	
	
	
	
	
	

}
