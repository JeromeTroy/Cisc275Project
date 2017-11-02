package controller;

import java.util.ArrayList;
import java.util.*;
import model.*;

public class ModelRunController {
	static ArrayList<OurVector> positions; 
	
	public static void main(String[] args){
		
		
		MainGameModel m = new MainGameModel();
		MainCharacter f = m.getFishy();
		
		positions = new ArrayList<>();
		
		for (int i = 0; i<100000; i++){
			positions.add(new OurVector((int) Math.random()*1000, (int) Math.random()*1000));
		}
		
		m.startGame();
		for (int i = 0; i<100000; i++){
			m.update();
			f.getPosition().setX(positions.get(i).getX());
			f.getPosition().setY(positions.get(i).getY());
			System.out.println(m);
		}
		
		
		
	}
	
}
