package controller;

import model.*;
import javax.swing.*;
import view.TitleScreen;

public class MainController {

	private MainGameModel model;
	
	
	public MainController(){
		model = new MainGameModel();
		
		model.addStuff(new Trash(1, 100,100));
		model.addStuff(new Trash(1, 0,0));
		model.addStuff(new Food(1, 50,50));
		System.out.println(model.getStuff());
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Starting Game...");
		Runnable theGame = new RunGame();
		javax.swing.SwingUtilities.invokeLater(theGame);
		System.out.println("Ending game");
		
		MainController theController = new MainController();
	}

}
