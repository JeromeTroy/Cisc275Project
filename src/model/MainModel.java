package model;

import java.util.Random;

/**
 * @author jerome
 * MainModel class, simplified using flyWeight
 */
public class MainModel {

	// Game stuff
	protected MainCharacter fishy;		// main character 	
	protected StuffSet everyThing; 		// all the trash and food
	protected Map map; 					// map
	private MiniGame miniGame;			// mini game
	
	// value for adding trash (distance away)
	// TODO: verify
	private int accumulationDist = 3000;
	
	// flags for game control
	private boolean gameOver; 			// game still going on
	protected boolean inMiniGame; 		// activate minigame
	private boolean hasWon;				// winning the game
	
	// scoring
	private int playerScore = 0; 		// the player's score
	private int foodScore = 10; 		// change in score from eating food
	private int trashScore = 5; 		// change in score form eating trash
	
	// timing (all in ms)
	private int tickLength = 30; 						// time period of a single tick
	private int timeMin = 1;
	private int maxAllowedTime = timeMin*60*100; 		// maximum allowed time for the game
	private int remainingTime;						// remaining time time
	private int startingTrash = 0;
	private int startingFood = 10;
	
	private int miniHeight;
	private int miniWidth;
	
	// methods
	
	
	// constructors
	
	
	// default
	/**
	 * Constructor
	 */
	public MainModel() {
		setGameOver(false);
		setInMiniGame(false);
		everyThing = new StuffSet();
		map = new Map();
		fishy = new MainCharacter(map);
		remainingTime = maxAllowedTime;

		
	}
	
	public static void setup(MainModel m, int mainCharRad, int foodSize, int trashSize, 
			int mapHeight, int mapLength, int mapUnique) {
		
		m.getMainCharacter().setRadius(mainCharRad);
		m.getStuffSet().setFoodSize(foodSize);
		m.getStuffSet().setTrashSize(trashSize);
		m.getMap().setHeight(mapHeight);
		m.getMap().setLength(mapLength);
		m.getMap().setUniqueLength(mapUnique);
		m.setAccumulationDistance(mapUnique);
		m.accumulateAll();
	}
	
	public static void setup(MainModel m, int mainCharRad, int foodSize, int trashSize, 
			int mapHeight, int mapLength, int mapUnique, int miniW, int miniH) {
		
		m.getMainCharacter().setRadius(mainCharRad);
		m.getStuffSet().setFoodSize(foodSize);
		m.getStuffSet().setTrashSize(trashSize);
		m.getMap().setHeight(mapHeight);
		m.getMap().setLength(mapLength);
		m.getMap().setUniqueLength(mapUnique);
	}
	
		
	// map length and height
	/**
	 * Constructor
	 * @param length 		length of the map
	 * @param height		height of the map
	 */
	public MainModel(int length, int height) {
		this();
		map.setHeight(height);
		map.setLength(length);
		
	}
	
	
	// all map parameters
	/**
	 * Constructor
	 * @param length		length of the map
	 * @param height 		height of the map
	 * @param unlen			unique length of the map
	 */
	public MainModel(int length, int height, int unlen) {
		this(length,height);
		map.setUniqueLength(unlen);
		
	}
	
	public void update(){
		
	}
	
	// updating
	/**
	 * Updating model by rotating fish and changing its speed
	 * @param newSpeed 			new speed for the fish
	 * @param deltaTheta		angle to rotate the fish
	 */
	public void update(int newSpeed, int deltaTheta) {
		if (!getInMiniGame()) { 			 		// in the main game
			
			// setup			getMainCharacter().setSpeed(newSpeed);
			getMainCharacter().setAngle(deltaTheta);
			
			// if move allowed
			if (getMap().moveMap(getMainCharacter())) {
				System.out.println("Valid move");
				
				// move everything and fish
				getStuffSet().move(getMainCharacter());
				getMainCharacter().move();
			}
			
			// move not allowed
			else {
				System.out.println("Invalid move, not moving");
			}
			
			// time to accumulate
			if (getStuffSet().shouldAccumulate()) {
				accumulate();
			}
			
			// display the state
			System.out.println(this);
			
			// collision checking
			String collision = everyThing.whatCollided(fishy);
			System.out.println("Checking collisions");
			System.out.println("Collisions: " + collision);
			
			// collision with trash
			if (collision.equals("trash")) {
				decreaseScore(); 				// lose points
				miniGame = new MiniGame(miniWidth,miniHeight); 		// start minigame
				setInMiniGame(true);
			}
			// collision with food
			else if (collision.equals("food")) {
				increaseScore();// gain points
				remainingTime += 1000;
			}
			
			// check if game over
			setGameOver(getMainCharacter().getPosition().getX() >= getMap().getLength());
			if (getGameOver()) {
				setHasWon(true);
			}
		}
		// in the minigame
		else {
			// update the mini game
			getMiniGame().update(newSpeed, deltaTheta);
			
			// check if we should still be in the minigame
			setInMiniGame(!getMiniGame().getMiniGameOver());
			
			// minigame is over
			if (!getInMiniGame()) {
				System.out.println("Mini game over");
				getStuffSet().removeAllTrash(); 			// eliminate all trash in the main game
			} 
		}
		// time updating
		timeIncr();
		if (!getGameOver()) {
			setGameOver(getRemainingTime() <= 0);
			if (getGameOver()) {
				setHasWon(false);
			}
		}
	}
	
	
	// trash accumulation
	/**
	 * accumulation of trash and food
	 */
	public void accumulate() {
		// assume not added
		boolean trashAdded = false;
		boolean foodAdded = false;
		
		// get locations (initialize x)
		int[] trashLoc = {accumulationDist, 0};
		int[] foodLoc = {accumulationDist, 0};
		
		// add trash first
		while (!trashAdded) {
			trashLoc[1] = randint(0, getMap().getHeight()); 		// random y location
			trashAdded = everyThing.add(trashLoc, "trash");			// try to add
		}
	
		// add the food
		while (!foodAdded) {
			foodLoc[1] = randint(0,getMap().getHeight());			// random y location
			foodAdded = everyThing.add(foodLoc, "food"); 			// try to add
		}
	}
	
	public void accumulateAll() {
		for (int i=0; i<getStartingTrash(); i++) {
			boolean trashAdded = false;
			int[] trashLoc = {0, 0};
			while (!trashAdded) {
				trashLoc[0] = MainModel.randint(75, getMap().getLength());
				trashLoc[1] = MainModel.randint(75, getMap().getHeight());
				trashAdded = getStuffSet().add(trashLoc,"trash");
				//System.out.println("trash"+trashLoc[0]+"/"+getMap().getLength());
			}
		}
		
		for (int i=0; i<getStartingFood(); i++) {
			boolean foodAdded = false;
			int[] foodLoc = {0, 0};
			while (!foodAdded) {
				foodLoc[0] = MainModel.randint(75, getMap().getLength()-75);
				foodLoc[1] = MainModel.randint(75, getMap().getHeight()-75);
				foodAdded = getStuffSet().add(foodLoc,"food");
				//System.out.println("trash"+trashLoc[0]+"/"+getMap().getLength());
			}
		}
	}
	
	
	// helper function (random numbers)
	/**
	 * Determine a random integer between 2 numbers
	 * @param min 		minimum value
	 * @param max 		maximum value
	 * @return 			number between min and max
	 */
	protected static int randint(int min, int max) {
		Random rn = new Random();
		int val = min + Math.abs(rn.nextInt()%(max-min));
		return val;
	}
	
	
	// printing
	/**
	 * Printing
	 *  (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String str = fishy.toString();
		str += "\n" + everyThing.toString();
		str += "\n" + map.toString();
		str += "\n" + timeString();
		return str;
	}
	
	public String timeString() {
		String str = "Time remaining: ";
		//long ms = remainingTime % 1000 / 10;
		long second = (remainingTime / 1000) % 60;
		long minute = (remainingTime / (1000 * 60)) % 60;
		//String time = String.format("%02d:%02d:%02d", minute, second, ms);
		String time = String.format("%02d:%02d", minute, second);
		str += time;
		return str;
	}
	
	// scoring
	
	
	// inc. score
	private void increaseScore() {
		setPlayerScore(getPlayerScore() + getFoodScore());
	}
	
	
	// dec. score
	private void decreaseScore() {
		setPlayerScore(getPlayerScore() - getTrashScore());
	}
	
	
	// timing
	private void timeIncr() {
		remainingTime -= tickLength;
	}
	
	// getters
	
	
	// game over
	/**
	 * The controller will use this to determine if the game should still be played
	 * @return
	 */
	public boolean getGameOver() {
		return gameOver;
	}
	
	
	// in the mini game
	public boolean getInMiniGame() {
		return inMiniGame;
	}
	
	
	// map
	public Map getMap() {
		return map;
	}
	
	
	// main character
	public MainCharacter getMainCharacter() {
		return fishy;
	}
	
	
	// food and trash
	public StuffSet getStuffSet() {
		return everyThing;
	}
	
	
	// score change from food
	public int getFoodScore() {
		return foodScore;
	}
	
	
	// score change from trash
	public int getTrashScore() {
		return trashScore;
	}
	
	
	// current score
	public int getPlayerScore() {
		return playerScore;
	}
	
	
	// the mini game
	public MiniGame getMiniGame() {
		return miniGame;
	}
	
	
	public int getTickLength() {
		return tickLength;
	}
	
	public int getRemainingTime() {
		return remainingTime;
	}
	
	
	public boolean getHasWon() {
		return hasWon;
	}
	
	
	// setters
	
	
	// distance for accumulation
	/**
	 * Used to set the distance at which food can accumulate from view
	 * @param dist
	 */
	public void setAccumulationDistance(int dist) {
		accumulationDist = dist;
	}
	
	
	// game over
	public void setGameOver(boolean b) {
		gameOver = false;
	}
	
	
	// in the mini game
	public void setInMiniGame(boolean b) {
		inMiniGame = b;
	}
	
	
	// current score
	private void setPlayerScore(int a) {
		playerScore = a;
	}
	
	public void setTickLength(int a) {
		tickLength = a;
	}
	
	private void setHasWon(boolean b) {
		hasWon = b;
	}

	public int getMiniHeight() {
		return miniHeight;
	}

	public void setMiniHeight(int miniHeight) {
		this.miniHeight = miniHeight;
	}

	public int getMiniWidth() {
		return miniWidth;
	}

	public void setMiniWidth(int miniWidth) {
		this.miniWidth = miniWidth;
	}

	public int getStartingTrash() {
		return startingTrash;
	}

	public void setStartingTrash(int startingTrash) {
		this.startingTrash = startingTrash;
	}

	public int getStartingFood() {
		return startingFood;
	}

	public void setStartingFood(int startingFood) {
		this.startingFood = startingFood;
	}
	
	
	
	
}
