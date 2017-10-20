package model;
import java.util.*;

public class MainGameModel {
	
	private FishCharacter fishy; 		// the main character
	private Timer timer;						// countdown timer
	private double trashAmount;					// level of the trash around the main character
	private double foodAmount;					// level of food around the main character
	private StuffSet everyThing; 				// all the stuff in the ocean
	
	private Map theMap; 					// the map
	private MiniGameModel miniGame;				// mini game

	
	public MainGameModel(){
		fishy = new FishCharacter();
		trashAmount = 0;
		foodAmount = 0;
		
		everyThing = new StuffSet();
		everyThing.add(fishy);
		
		timer = new Timer(); 				// fix this?
		
	}
}
