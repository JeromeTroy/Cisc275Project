package model;

import java.util.Random;

/**
 * @author jerome
 * MainModel class, simplified using flyWeight
 */
public class MainModel {

	private MainCharacter fishy;
	private StuffSet everyThing;
	private Map map;
	
	private int accumulationDist = 400;
	
	private int score = 0;
	private int foodScore = 10;
	
	/**
	 * Constructor
	 */
	public MainModel() {
		fishy = new MainCharacter();
		everyThing = new StuffSet();
		map = new Map();
	}
	
	/**
	 * Constructor
	 * @param length 		length of the map
	 * @param height		height of the map
	 */
	public MainModel(int length, int height) {
		fishy = new MainCharacter();
		everyThing = new StuffSet();
		map = new Map(length,height);
	}
	
	/**
	 * Constructor
	 * @param length		length of the map
	 * @param height 		height of the map
	 * @param unlen			unique length of the map
	 */
	public MainModel(int length, int height, int unlen) {
		fishy = new MainCharacter();
		everyThing = new StuffSet();
		map = new Map(length, height, unlen);
	}
	
	
	/**
	 * Updating the model
	 * Only updates if move is allowed
	 */
	public void update() {
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
		String collision = everyThing.whatCollided(fishy);
		if (collision.equals("trash")) {
			// TODO: execute minigame
		}else if (collision.equals("food")) {
			score += foodScore;
		}
	}
	
	
	/**
	 * Updating the model by rotating fish
	 * @param deltaTheta 		angle to rotate the fish
	 */
	public void update(int deltaTheta) {
		fishy.rotate(deltaTheta);
		update();
	}
	
	
	/**
	 * Updating model by rotating fish and changing its speed
	 * @param newSpeed 			new speed for the fish
	 * @param deltaTheta		angle to rotate the fish
	 */
	public void update(int newSpeed, int deltaTheta) {
		fishy.setSpeed(newSpeed);
		update(deltaTheta);
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
	private int randint(int min, int max) {
		Random rn = new Random();
		int val = min + rn.nextInt()%(max-min);
		return val;
	}
	
	public void setAccumulationDistance(int dist) {
		accumulationDist = dist;
	}
}
