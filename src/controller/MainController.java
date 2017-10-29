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
	
	
	public MainController(){
		model = new MainGameModel();
		timer = new Timer();
		Thread tutorial = new Thread();
		Thread mainGame = new Thread();
		Thread miniGame = new Thread();
		Thread endScreen = new Thread();
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
		
	
}
	public static void startTutorial(){
		System.out.println("Start Tutorial");
	}
	
	protected static void tick() {
		model.update();
		
	}
}
