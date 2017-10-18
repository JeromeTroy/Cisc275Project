package controller;

import javax.swing.*;
import view.TitleScreen;

public class MainController {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Starting Game...");
		Runnable theGame = new RunGame();
		javax.swing.SwingUtilities.invokeLater(theGame);
		System.out.println("Ending game");
	}

}
