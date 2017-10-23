package controller;


//TODO: organize imports
import model.*;
import javax.swing.*;
import view.TitleScreen;

public class MainController {

	private MainGameModel model;
	
	
	public MainController(){
		model = new MainGameModel();
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Starting Game...");
		Runnable theGame = new RunGame();
		javax.swing.SwingUtilities.invokeLater(theGame);
		System.out.println("Ending game");
		
		MainController theController = new MainController();
		System.out.println(theController.getModel().getFishy());
		theController.getModel().getFishy().move(theController.getModel().getMap());
		theController.getModel().getFishy().rotate(90);
		System.out.println(theController.getModel().getFishy());
		theController.getModel().getFishy().move(theController.getModel().getMap());
		System.out.println(theController.getModel().getFishy());
	}

	public MainGameModel getModel(){
		return model;
	}
}
