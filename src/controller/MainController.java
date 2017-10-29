package controller;


//TODO: organize imports
import model.*;
import javax.swing.*;
import view.TitleScreen;
import java.util.*;
import java.util.Timer;

public class MainController {
	private static Timer timer;
	private static MainGameModel model;
	private static Thread mainGame;
	
	public MainController(){
		model = new MainGameModel();
		timer = new Timer();
		mainGame = new Thread(new mainGame());
		//Thread miniGame = new Thread();
		//mainGame.start();
	}
	
	
	public static void main(String[] args) {
		openGame();
	}

	public MainGameModel getModel(){
		return model;
	}
	
	public static void openGame(){
		Runnable theGame = new RunGame();
		javax.swing.SwingUtilities.invokeLater(theGame);
	}
	
	public static void startGame(){
		MainController controller = new MainController();
		mainGame.start();
		
	
}
	public static void startTutorial(){
		System.out.println("Start Tutorial");
	}
	
	protected static void tick() {
		System.out.println("Tick");
		model.update();
		
	}
}
