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
		// everyThing.add(fishy);
		
		timer = new Timer(); 				// fix this	
		
		theMap = new Map(1000, 100);		// map 1000 units long, 100 units tall
	}
	
	// getters
	public FishCharacter getFishy(){
		return fishy;
	}
	
	public StuffSet getStuff(){
		return everyThing;
	}
	
	public MiniGameModel getMiniGame(){
		return miniGame;
	}
	
	public double getTrashAmount(){
		return trashAmount;
	}
	
	public double getFoodAmount(){
		return foodAmount;
	}
	public Map getMap(){
		return theMap;
	}
	
	// adders
	public boolean addStuff(StuffInOcean s){
		return everyThing.add(s);
	}
	
	public void accumulate(){    			 //accumulate trash
		int incTrash = (int) (Math.random()*100%2)+1;  //TODO: how much trash needs to be generated per method call
		for (int i=0; i<incTrash; i++){
			everyThing.add(new Trash((int)Math.random()*100,(int)Math.random()*100));  
			//TODO: replace randomly generated location with locally random location
		}
	}
	

	public void removeTrash(){
		ArrayList<Trash> allTrash = new ArrayList<>();
		for (StuffInOcean s : everyThing){
			if (s.isTrash()){
				allTrash.add((Trash)s);
			}
		}
		
		everyThing.removeAll(allTrash);
	}
	
	// moving
	/*
	 * Moves everything
	 * Only allows movement based on map's moveMap method
	 */
	public void modelTick(){
		if (theMap.moveMap(fishy)) {				// if move allowed
			for (StuffInOcean crap : everyThing) {	// move everything
				crap.move(fishy);
			}
		}
	}
	
}
