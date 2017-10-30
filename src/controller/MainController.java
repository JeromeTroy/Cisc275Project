package controller;


//TODO: organize imports
import model.*;
import javax.swing.*;
import view.TitleScreen;
import java.util.*;
import java.util.Timer;

public class MainController {
	private static GameTimer timer;
	public static MainGameModel mainGameModel;
	private static MainGameThread mainGameThread;
	public static MiniGameModel miniGame;
	
	public MainController(){
		mainGameModel = new MainGameModel();
		timer = new GameTimer(mainGameModel.getGameLengthSeconds());
		mainGameThread = new MainGameThread();
		
		}
	
	
	public static void main(String[] args) {
		openGame();
	}

	public MainGameModel getModel(){
		return mainGameModel;
	}
	
	public static void openGame(){
		Runnable theGame = new RunGame();
		javax.swing.SwingUtilities.invokeLater(theGame);
	}
	
	public static void startGame(){
		MainController controller = new MainController();
		mainGameThread.start();
		
	
}
	public static void startTutorial(){
		System.out.println("Start Tutorial");
	}
	
	protected static void tick() {
		System.out.println("Tick");
		if (mainGameThread.inMiniGame){
			miniGame.update();
			if (miniGame.isGameOver()){
				endMiniGame();
			}
		} else{
			mainGameModel.update();
			if (mainGameModel.isGameOver()){
				endGame();
			}
		}
		if (mainGameModel.getIsCaught()){
			launchMiniGame();
		}
		
	}
	
	public static void endGame(){
		//TODO: This part isn't working as expected
		mainGameThread.interrupt();
		System.out.println("Game Over");
		System.out.println("End Screen");
	}
	
	public static void launchMiniGame(){
		mainGameThread.enterMiniGameMode();
		miniGame = new MiniGameModel();
		System.out.println("MiniGame Launched...");
		mainGameModel.setCaught(false);
		
	}
	
	public static void endMiniGame(){
		mainGameThread.exitMiniGameMode();
	}
	
}
