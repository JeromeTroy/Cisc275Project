package model;

import java.util.Random;

/**
 * @author Group 4
 * MainModel class, simplified using flyWeight
 */
public class MainModel {

	// Game stuff
	protected MainCharacter fishy;		// main character 	
	protected StuffSet everyThing; 		// all the trash and food
	protected Map map; 					// map
	protected MiniGame miniGame;			// mini game
	
	protected final int minSpeed = 5;
	
	// value for adding trash (distance away)
	// TODO: verify
	protected int accumulationDist = 3000;
	
	// flags for game control
	private boolean gameOver; 			// game still going on
	protected boolean inMiniGame; 		// activate minigame
	private boolean hasWon;				// winning the game
	
			// time addition from eating food
	
	// timing (all in ms)
	private int tickLength = 30; 						// time period of a single tick
	private double timeMin = .1;
	private int maxAllowedTime = (int) (timeMin*60*1000); 		// maximum allowed time for the game
	protected int remainingTime;							// remaining time
	private int startingTrash = 0;
	private int startingFood = 0;
	protected int miniHeight;
	protected int miniWidth;
	
	// scoring
	private int playerScore = 0;        // the player's score
	private int winBonus = 1000;		//bonus for winning game
	private int timeScore = (int)(-(1/60)*(maxAllowedTime - remainingTime));
	private int foodScore = 100; 		// change in score from eating food
	private int trashScore = 0; 		// change in score form eating trash
	protected int foodTime = 100;
	
	private int speed;
	
	protected double trashAccumulateMultiplier = 0.05;
	
	
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
		setSpeed(8);
		
	}
	
	/**
	 * @see MainModel()
	 * @param mapL 	map length
	 */
	public MainModel(int mapL) {
		this();
		map.setLength(mapL);
	}
	
	/**
	 * setting scroll speed
	 * @param a
	 */
	public void setSpeed(int a) {
		speed = a;
		fishy.setmotion(a);
	}
	
	/**
	 * get scroll speed
	 * @return
	 */
	public int getSpeed() {
		return speed;
	}
	/**
	 * setting up the model
	 * @param m				model
	 * @param mainCharRad	size of main character
	 * @param foodSize 		size of food
	 * @param trashSize		size of trash
	 * @param mapHeight		height of map
	 * @param mapLength		length of map
	 * @param mapUnique		unique length of map
	 */
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
	
	/**
	 * @param m
	 * @param mainCharRad 	
	 * @param foodSize
	 * @param trashSize
	 * @param mapHeight
	 * @param mapLength
	 * @param mapUnique
	 * @param miniW			mini game width
	 * @param miniH			mini game height
	 * @see setup
	 */
	public static void setup(MainModel m, int mainCharRad, int foodSize, int trashSize, 
			int mapHeight, int mapLength, int mapUnique, int miniW, int miniH) {
		
		setup(m,mainCharRad,foodSize,trashSize,mapHeight,mapLength,mapUnique);
		m.getMiniGame().setMiniHeight(miniH);
		m.getMiniGame().setMiniWidth(miniW);
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
	
	
	// updating
	/**
	 * Updating model by rotating fish and changing its speed
	 * @param newSpeed 			new speed for the fish
	 * @param deltaTheta		angle to rotate the fish
	 */
	public void update(int newSpeed, int deltaTheta) {
		if (!gameOver){
		if (!getInMiniGame()) { 			 		// in the main game
			
			// setup			getMainCharacter().setSpeed(newSpeed);
			getMainCharacter().setAngle(deltaTheta);
			
			// if move allowed
			//if (getMap().moveMap(getMainCharacter())) {
				getMap().moveMap(getMainCharacter());
				System.out.println("Valid move");
				
				// move everything and fish
				getStuffSet().move(getMainCharacter());
				
				if (newSpeed > minSpeed) {
					getMainCharacter().setSpeed(newSpeed/5000);
					getMainCharacter().move();
				}
			//}
			
			// move not allowed
			/*else {
				System.out.println("Invalid move, not moving");
			}*/
			
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
				remainingTime += foodTime;
			}
			
			// check if game over
			setGameOver(-getMap().getOrigin().getX() >= getMap().getLength());
			if (getGameOver()) {
				setHasWon(true);
				setPlayerScore(getPlayerScore() + timeScore + winBonus);
				gameOver();
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
				setPlayerScore(getPlayerScore() + timeScore);
				gameOver();
			}
		}
		}
	}
	
	
	// trash accumulation
	/**
	 * accumulation of trash and food
	 */
	public void accumulate() {
		// assume not added
		
		boolean foodAdded = false;
		
		// get locations (initialize x)
		
		int[] foodLoc = {accumulationDist, 0};
		
		// add trash first
		int trashAmount = everyThing.getTrashSize();
		for (int i=0; i<(1+(trashAmount*trashAccumulateMultiplier)); i++){
			boolean trashAdded = false;
			int[] trashLoc = {accumulationDist, 0};
			while (!trashAdded) {
				trashLoc[1] = randint(0, getMap().getHeight()); 		// random y location
				trashAdded = everyThing.add(trashLoc, "trash");			// try to add
			}
		}
		
		// add the food
		while (!foodAdded) {
			foodLoc[1] = randint(0,getMap().getHeight());			// random y location
			foodAdded = everyThing.add(foodLoc, "food"); 			// try to add
		}
	}
	
	/**
	 * accumulate all trash initially
	 */
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
	
	/**
	 * if the game is over clear out all the trash and food
	 */
	public void gameOver(){
		inMiniGame = false;
		getStuffSet().clearAll();
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
		if (getGameOver()) {
			str += "\nGame Over :-(";
		}
		return str;
	}
	
	/**
	 * convert the time remaining to a string
	 * @return
	 */
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
	/**
	 * increase the player score
	 */
	protected void increaseScore() {
		setPlayerScore(getPlayerScore() + getFoodScore());
	}
	
	
	// dec. score
	/**
	 * decrease player score
	 */
	protected void decreaseScore() {
		setPlayerScore(getPlayerScore() - getTrashScore());
	}
	
	
	// timing
	/**
	 * increment time
	 */
	protected void timeIncr() {
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
	/**
	 * is the minigame active
	 * @return
	 */
	public boolean getInMiniGame() {
		return inMiniGame;
	}
	
	
	// map
	/**
	 * current state of the map
	 * @return
	 */
	public Map getMap() {
		return map;
	}
	
	
	// main character
	/**
	 * current state of the main character
	 * @return
	 */
	public MainCharacter getMainCharacter() {
		return fishy;
	}
	
	
	// food and trash
	/**
	 * get all the stuff in the ocean
	 * @return
	 */
	public StuffSet getStuffSet() {
		return everyThing;
	}
	
	
	// score change from food
	/**
	 * score from food
	 * @return
	 */
	public int getFoodScore() {
		return foodScore;
	}
	
	
	/**
	 *  score change from trash
	 */
	public int getTrashScore() {
		return trashScore;
	}
	
	
	/**
	 * get current score
	 * @return
	 */
	public int getPlayerScore() {
		return playerScore;
	}
	
	
	/**
	 * get the mini game
	 * @return
	 */
	public MiniGame getMiniGame() {
		return miniGame;
	}
	
	
	/**
	 * length of one tick
	 * @return
	 */
	public int getTickLength() {
		return tickLength;
	}
	
	/**
	 * remaining time
	 * @return
	 */
	public int getRemainingTime() {
		return remainingTime;
	}
	
	
	/**
	 * was the player victorious
	 * @return
	 */
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
	
	
	/**
	 *  game over
	 * @param b
	 */
	public void setGameOver(boolean b) {
		gameOver = b;
	}
	
	
	/**
	 * in the mini game
	 * @param b
	 */
	public void setInMiniGame(boolean b) {
		inMiniGame = b;
	}
	
	
	/**
	 * current score
	 * @param a
	 */
	private void setPlayerScore(int a) {
		playerScore = a;
	}
	
	/**
	 * set length of one tick
	 * @param a
	 */
	public void setTickLength(int a) {
		tickLength = a;
	}
	
	/**
	 * set victory
	 * @param b
	 */
	protected void setHasWon(boolean b) {
		hasWon = b;
	}

	/**
	 * get the height of the minigame
	 * @return
	 */
	public int getMiniHeight() {
		return miniHeight;
	}

	/**
	 * set height of mini game
	 * @param miniHeight
	 */
	public void setMiniHeight(int miniHeight) {
		this.miniHeight = miniHeight;
	}

	/**
	 * width of minigame
	 * @return
	 */
	public int getMiniWidth() {
		return miniWidth;
	}

	/**
	 * get width of minigame
	 * @param miniWidth
	 */
	public void setMiniWidth(int miniWidth) {
		this.miniWidth = miniWidth;
	}

	/**
	 * starting amount of trash
	 * @return
	 */
	public int getStartingTrash() {
		return startingTrash;
	}

	/**
	 * set starting amount of trash
	 * @param startingTrash
	 */
	public void setStartingTrash(int startingTrash) {
		this.startingTrash = startingTrash;
	}

	/**
	 * starting amount of food
	 * @return
	 */
	public int getStartingFood() {
		return startingFood;
	}

	/**
	 * set the starting amount of food
	 * @param startingFood
	 */
	public void setStartingFood(int startingFood) {
		this.startingFood = startingFood;
	}
	
	
	
	
}
