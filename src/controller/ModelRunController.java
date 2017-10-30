package controller;

import model.*;

public class ModelRunController {
	
	
	public static void main(String[] args){
			
		
		MainGameModel m = new MainGameModel();
		FishCharacter f = m.getFishy();
		int xInc = 1;
		
		
		
		
	}
	
	public void autoMoveFish(FishCharacter f, int xInc){
		int currX = f.getPosition().getX();
		
		f.getPosition().setX(currX+xInc);
		f.getPosition().setY((int) Math.sin(currX+xInc));
	}
}
