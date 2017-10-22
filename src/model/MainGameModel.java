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
	private int trashAccumulation = 2; //sets the accumulation of trash
	
	public MainGameModel(){
		fishy = new FishCharacter();
		trashAmount = 0;
		foodAmount = 0;
		
		everyThing = new StuffSet();
		everyThing.add(fishy);
		
		timer = new Timer(); 				// fix this?
		
	}
	
	public void accumulate(){    			 //accumulate trash
		int incTrash = (int) Math.random()*100%2;  //TODO: how much trash needs to be generated per method call
		for (int i=0; i<incTrash; i++){
			everyThing.add(new Trash());
		}
	}
	
	public void removeTrash(){
		for (StuffInOcean s : everyThing){
			if (s.isTrash()){
				everyT
			}
		}
	}
}
