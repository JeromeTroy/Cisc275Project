package model;

import java.util.Random;

/**
 * @author jerome
 * MainModel class, simplified using flyWeight
 */
public class MainModel {

	protected MainCharacter fishy;
	protected StuffSet everyThing;
	protected Map map;
	
	private int accumulationDist = 400;
	
	private int score = 0;
	private int foodScore = 10;
	
	private boolean gameOver;
	private boolean inMiniGame;
	
	private MiniGame miniGame;
	
	
	/**
	 * Constructor
	 */
	public MainModel() {
		gameOver = false;
		inMiniGame = false;
		everyThing = new StuffSet();
		map = new Map();
		fishy = new MainCharacter(map);
	}
	
	/**
	 * Constructor
	 * @param length 		length of the map
	 * @param height		height of the map
	 */
	public MainModel(int length, int height) {
		gameOver = false;
		inMiniGame = false;
		everyThing = new StuffSet();
		map = new Map(length,height);
		fishy = new MainCharacter(map);
	}
	
	/**
	 * Constructor
	 * @param length		length of the map
	 * @param height 		height of the map
	 * @param unlen			unique length of the map
	 */
	public MainModel(int length, int height, int unlen) {
		gameOver = false;
		inMiniGame = false;
		everyThing = new StuffSet();
		map = new Map(length, height, unlen);
		fishy = new MainCharacter(map);
	}
	
	/**
	 * Updating model by rotating fish and changing its speed
	 * @param newSpeed 			new speed for the fish
	 * @param deltaTheta		angle to rotate the fish
	 */
	public void update(int newSpeed, int deltaTheta) {
		if (!inMiniGame) {
			fishy.setSpeed(newSpeed);
			fishy.rotate(deltaTheta);
			if (map.moveMap(fishy)) {
				System.out.println("Valid move");
				everyThing.move(fishy);
				fishy.move();
			}
			else {
				System.out.println("Invalid move, not moving");
			}
			if (everyThing.shouldAccumulate()) {
				accumulate();
			}
			System.out.println(this);
			String collision = everyThing.whatCollided(fishy);
			System.out.println("Checking collisions");
			System.out.println("Collisions: " + collision);
			if (collision.equals("trash")) {
				miniGame = new MiniGame();
				inMiniGame = true;
			}else if (collision.equals("food")) {
				score += foodScore;
			}
		}else {
			miniGame.update(newSpeed, deltaTheta);
			inMiniGame = miniGame.miniGameOver();
			if (!inMiniGame) {
				System.out.println("Mini game over");
				everyThing.removeAllTrash();
			}
		}	
	}
	
	/**
	 * accumulation of trash and food
	 */
	public void accumulate() {
		boolean trashAdded = false;
		boolean foodAdded = false;
		int[] trashLoc = {accumulationDist, 0};
		int[] foodLoc = {accumulationDist, 0};
		while (!trashAdded) {
			trashLoc[1] = randint(0, map.getHeight());
			trashAdded = everyThing.add(trashLoc, "trash");
		}
		while (!foodAdded) {
			foodLoc[1] = randint(0,map.getHeight());
			foodAdded = everyThing.add(foodLoc, "food");
		}
	}
	
	/**
	 * Printing
	 *  (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String str = fishy.toString();
		str += "\n" + everyThing.toString();
		str += "\n" + map.toString();
		return str;
	}
	
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
	
	public void setAccumulationDistance(int dist) {
		accumulationDist = dist;
	}
	
	public boolean getGameOver() {
		return gameOver;
	}
	
	public void setGameOver(boolean b) {
		gameOver = b;
	}
}
