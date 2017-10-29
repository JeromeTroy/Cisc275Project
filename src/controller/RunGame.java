package controller;

import view.TitleScreen;
import view.GamePlayScreen;

public class RunGame implements Runnable {

	public RunGame(){
		super();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		TitleScreen.activateTitle();
		GamePlayScreen.activateGamePlayScreen();
	}

}
