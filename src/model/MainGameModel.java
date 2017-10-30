package model;

import java.util.*;

public class MainGameModel {

	private FishCharacter fishy; // the main character
	private Timer timer; // countdown timer
	private int trashAmount = 0; // level of the trash around the main character
	private int foodAmount; // level of food around the main character
	private StuffSet everyThing; // all the stuff in the ocean
	private int gameLengthSeconds;
	private Map theMap; // the map
	//private MiniGameModel miniGame; // mini game
	
	private int trashAccumulation = 1; // rate of increase of trash
	private int foodAccumulation = 1;  // rate of increase of food
	private boolean gameOver;
	//protected boolean isCaught;
	
	// TODO: verify these are correct
	private int accumulateXMin = 500;
	private int accumulateXMax = 600;
	private int accumulateYMin = 0;
	private int accumulateYMax;

	public MainGameModel() {
		startGame();
		gameOver = false;
		everyThing = new StuffSet();
		trashAmount = 0;
		foodAmount = 0;
		gameLengthSeconds = 180;

		fishy = new FishCharacter();
		everyThing.add(fishy);

		theMap = new Map(1000, 100); // map 1000 units long, 100 units tall
		accumulateYMax = theMap.getHeight();
		//isCaught = false;
		
		everyThing = new StuffSet();
		// everyThing.add(fishy);
		
		timer = new Timer(); 				// fix this	
		theMap = new Map(1000, 100);		// map 1000 units long, 100 units tall
		
	}

	// getters
	public FishCharacter getFishy() {
		return fishy;
	}

	public StuffSet getStuff() {
		return everyThing;
	}

//	public MiniGameModel getMiniGame() {
//		return miniGame;
//	}

	public int getTrashAmount() {
		return trashAmount;
	}

	public int getFoodAmount() {
		return foodAmount;
	}

	public Map getMap() {
		return theMap;
	}

//	public boolean getIsCaught() {
//		return isCaught;
//	}

	public int getGameLengthSeconds() {
		return gameLengthSeconds;
	}

	public int getTrashAccumulation() {
		return trashAccumulation;
	}

	public boolean isGameOver() {
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

//	public void setCaught(boolean isCaught) {
//		fishy.isCaught = isCaught;
//	}

	public void setEveryThing(StuffSet everyThing) {
		this.everyThing = everyThing;
	}

	public void setTheMap(Map theMap) {
		this.theMap = theMap;
	}

//	public void setTimer(Timer timer) {
//		this.timer = timer;
//	}
	
	public void setOver(boolean b){
		this.gameOver = b;
	}

	// adders
	public boolean addStuff(StuffInOcean s) {
		// TODO: modify method for arc
		return everyThing.add(s);
	}

	/*
	 * accumulateTrash() - accumulate trash randomly generates amount of trash
	 * between 0-set trashAccumulation places trash at random location of vector
	 * <0-100,0-100> parameters - none input - none return - none output - none
	 */
	public void accumulate() { // accumulate trash
		trashAmount += trashAccumulation;
		foodAmount += foodAccumulation;
		Trash newTrash = new Trash(randInt(accumulateXMin, accumulateXMax),randInt(accumulateYMin, accumulateYMax));
		Food newFood = new Food(randInt(accumulateXMin, accumulateXMax),randInt(accumulateYMin, accumulateYMax));
		everyThing.add(newTrash);
		everyThing.add(newFood);
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
		ArrayList<Trash> allTrash = new ArrayList<>();
		for (StuffInOcean s : everyThing) {
			if (s.isTrash()) {
				allTrash.add((Trash) s);
			}
		}

		everyThing.removeAll(allTrash);
	}

	public boolean endGame() {
		gameOver = true;
		System.out.println("Game Over");
		return true;
	}

	public void startGame() {
		System.out.println("Game Start...");
	}

	public void update() {
		//if (!isCaught) {
			System.out.println("Update Game Model");
		//}
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
		if (fishy.isCaught(everyThing.get(1))) {
			// TODO: call minigame
			
			removeTrash();
		}else {
			accumulate();
		}
	}
	
}
