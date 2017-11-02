package model;

import java.util.*;

public class MainGameModel {

	/*
	 * The main model for the game
	 * Will be the only thing from the model that the controller can see
	 */
	private FishCharacter fishy; 		// the main character
	
	private int trashAccumulation = 1; // rate of increase of trash
	private int foodAccumulation = 1;  // rate of increase of food
	// TODO: verify these are correct
	private int accumulateXMin = 0;
	private int accumulateXMax = 500;
	private int accumulateYMin = 0;
	private int accumulateYMax = 700;
	private int trashAmount = 0; 		// level of the trash around the main character
	private int foodAmount; 			// level of food around the main character
	protected StuffSet everyThing; 		// all the stuff in the ocean
	
	protected Map theMap; 				// the map
	
	private int gameLengthSeconds;		// alotted time for the game
	private Timer timer; 				// countdown timer	
	private int numTicks=0;				// TODO: WTF
	//public ArrayList<StuffInOcean> newStuff;
	//private MiniGameModel miniGame; // mini game
	
	private boolean gameOver;
	//protected boolean isCaught;
	

	/*
	 * Constructor
	 * Creates the model and initializes all requirements
	 */
	public MainGameModel() {
		startGame();							// start the game
		gameOver = false;						// not currently game over
		fishy = new FishCharacter();			// create the main character
		everyThing = new StuffSet(fishy);		// create the set of stuff, and give it the fish for comparisons
		trashAmount = 0;						// amount of trash
		foodAmount = 0;							// amount of food
		gameLengthSeconds = 180;				// length of game //TODO: subject to change

		theMap = new Map(1000, 100); 			// map 1000 units long, 100 units tall //TODO: subject to change
		accumulateYMax = theMap.getHeight();	// maximum y value that we can put trash at
				
		timer = new Timer(); 					// Establish timer //TODO: fix this	
		}


//	public MiniGameModel getMiniGame() {
//		return miniGame;
//	}

	// getters
	public FishCharacter getFishy() {
		return fishy;
	}

	public StuffSet getStuff() {
		return everyThing;
	}

	public int getTrashAmount() {
		return trashAmount;
	}

	public int getFoodAmount() {
		return foodAmount;
	}

	public Map getMap() {
		return theMap;
	}

	public int getGameLengthSeconds() {
		return gameLengthSeconds;
	}

	public int getTrashAccumulation() {
		return trashAccumulation;
	}

	public boolean getGameOver() {
		return gameOver;
	}

//	public Timer getTimer() {
//		return timer;
//	}

	public StuffSet getEveryThing() {
		return everyThing;
	}

	public Map getTheMap() {
		return theMap;
	}
	// setters
	public void setTrashAccumulation(int i) {
		trashAccumulation = i;
	}

	public void setTrashAmount(int trashAmount) {
		this.trashAmount = trashAmount;
	}

	public void setFoodAmount(int foodAmount) {
		this.foodAmount = foodAmount;
	}

	public void setGameLengthSeconds(int gameLengthSeconds) {
		this.gameLengthSeconds = gameLengthSeconds;
	}

	public void setEveryThing(StuffSet everyThing) {
		this.everyThing = everyThing;
	}

	public void setTheMap(Map theMap) {
		this.theMap = theMap;
	}

//	public void setTimer(Timer timer) {
//		this.timer = timer;
//	}
	
	public void setGameOver(boolean b){
		this.gameOver = b;
	}

	// adders
	public boolean addStuff(StuffInOcean s) {
		// TODO: modify method for arc
		return everyThing.add(s);
	}

	/*
	 * accumulateTrash() - accumulate trash and food randomly 
	 * generates amount of trash between 0 andtrashAccumulation places trash at random location of vector
	 * <0-100,0-100> parameters - none input - none return - none output - none
	 */
	//TODO: verify the proper amount of trash is added and in the right location.  Verify ALL needed trash is added
	public void accumulate() { // accumulate trash
		//initialize trash and food
		Trash newTrash;
		Food newFood;
		
		//generate new trash 
		for (int i=0; i<trashAccumulation; i++){
			newTrash = new Trash(randInt(accumulateXMin, accumulateXMax),randInt(accumulateYMin, accumulateYMax));
			if (everyThing.add(newTrash)){ //add trash and increment food amount if trash is added
				trashAmount++;
			}
		}
		//generate new food
		for (int i=0; i<foodAccumulation; i++){
			newFood = new Food(randInt(accumulateXMin, accumulateXMax),randInt(accumulateYMin, accumulateYMax));
			if (everyThing.add(newFood)){ //add food and increment food amount if trash is added
				foodAmount++;
				//newStuff.add(newFood);
			}
		}
	}
	
	/*
	 * helper function to calculate a random number in a range
	 */
	public static int randInt(int min, int max) {
		Random rn = new Random();
		int randNum = rn.nextInt(max - min + 1) + min;
		return randNum;
	}

	/*
	 * removeTrash() - removes all of the trash from the stuffSet parameters -
	 * none input - none return - none output - none
	 */
	public void removeTrash() {
		ArrayList<Trash> allTrash = new ArrayList<Trash>(); 		// initialize a list of all the trash
		for (StuffInOcean s : everyThing) {
			if (s.isTrash()) {
				allTrash.add((Trash)s);								// get all the trash
			}
		}
		everyThing.removeAll(allTrash);								// remove anything that is trash
	}

	/*
	 * Execute on the game ending
	 * TODO: ???
	 */
	public boolean endGame() {
		gameOver = true;
		System.out.println("Game Over");
		return true;
	}

	/*
	 * Start up the game
	 */
	public void startGame() {
		System.out.println("Game Start...");
	}

	// TODO: reconcile this and modelTick()
	public void update() {
		System.out.println("Update Game Model");
		numTicks++;
		if (numTicks%300==0){
			accumulate();
		}
		System.out.println(everyThing.size());
	}

	
	// moving
	/*
	 * Moves everything
	 * Only allows movement based on map's moveMap method
	 */
	// TODO: reconcile this and update()
	public void modelTick(){
		if (theMap.moveMap(fishy)) {				// if move allowed
			for (StuffInOcean crap : everyThing) {	// move everything
				crap.move(fishy);
				if (!(fishy.getIsCaught())) {
					fishy.isCaught(crap);
				}
			}
		}
		if (fishy.getIsCaught()) {
			// TODO: call minigame
			removeTrash();
		}else {
			accumulate();
		}
	}
	
	// print the game
	//TODO: make me more detailed
	public String toString(){
		return fishy.toString()+" Food items: "+foodAmount+" Trash Amount "+trashAmount;
	}
	
	
	
}
