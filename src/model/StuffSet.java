package model;

import java.util.*;

/**
 * @author jerome
 * List to hold all the locations of objects in the ocean
 */

public class StuffSet extends ArrayList {
	

	private ArrayList<int[]> allFood;
	protected ArrayList<int[]> allTrash;
	
	private int foodSize = 1;
	private int trashSize = 1;
	
	private int accumulationTimer = 0;
	private int accumulationValue = 3;
	
	
	/**
	 * Default constructor
	 */
	public StuffSet() {
		allFood = new ArrayList();
		allTrash = new ArrayList();
	}
	
	
	/**
	 * Constructor
	 * @param fs 		size of food
	 * @param ts 		size of trash
	 */
	public StuffSet(int fs, int ts) {
		super();
		foodSize = fs;
		trashSize = ts;
	}
	
	/**
	 * Adder of objects
	 * @param coords 		location of object
	 * @param type			trash or food
	 * @return 				boolean if add was allowed
	 */
	public boolean add(int[] coords, String type) {
		boolean goodAdd = true;
		for (int[] v : allFood) {
			if (OurVector.distBetween(coords[0], coords[1], v[0], v[1]) <= foodSize) {
				goodAdd = false;
				break;
			}
		}
		if (goodAdd) {
			for (int[] v : allTrash) {
				if (OurVector.distBetween(coords[0], coords[1], v[0], v[1]) <= foodSize) {
					goodAdd = false;
					break;
				}
			}
		}
		if ((type == "food") && (goodAdd)){
			allFood.add(coords);
		}else if ((type == "trash") && (goodAdd)){
			allTrash.add(coords);
		}else {
			goodAdd = false;
		}
		return goodAdd;
	}
	
	/**
	 * Moving all stuff
	 * Stuff moves left to right
	 * @param fishy 		fish that everything is moving relative to
	 */
	public void move(MainCharacter fishy) {
		int speed = fishy.getSpeed();
		int angle = fishy.getAngle();
		int deltaX = (int) (-speed * Math.cos(Math.toRadians(angle)));
		for (int[] coord : allFood) {
			coord[0] += deltaX;
		}
		for (int[] coord : allTrash) {
			coord[0] += deltaX;
		}
		accumulationTimer = (accumulationTimer + 1)%accumulationValue;
	}
	
	/**
	 * Helper function to generate a random integer
	 * @param min 		minimum value
	 * @param max 		maximum value
	 * @return 			random number between min and max
	 */
	private int randInt(int min, int max) {
		Random rn = new Random();
		int val = min + (rn.nextInt())%(max - min);
		return val;
	}
	
	/** (non-Javadoc)
	 * @see java.util.AbstractCollection#toString()
	 * Prints the collection
	 */
	public String toString() {
		String str = "Food size: " + foodSize + ", food locations: \n" + printList(allFood);
		str += "\nTrash size: " + trashSize + ", trash locations: \n" + printList(allTrash);
		return str;
	}
	
	/**
	 * Tells whether it is time to add more trash
	 * @return 		if should accumulate
	 */
	public boolean shouldAccumulate() {
		return (accumulationTimer == 0);
	}
	
	/**
	 * Determines what if anything the fish has contacted
	 * @param fishy 		the fish character
	 * @return 				what the fish has collided with ("food" or "trash")
	 */
	public String whatCollided(MainCharacter fishy) {
		String what = "";
		for (int[] v : allTrash) {
			if (fishy.isContacting(v, trashSize)) {
				what = "trash";
				remove(v,"trash");
				break;
			}
		}
		if (what.equals("")) {
			for (int[] v: allFood) {
				if (fishy.isContacting(v, foodSize)) {
					what = "food";
					remove(v,"food");
					break;
				}
			}
		}
		return what;
	}
	public void setAccumulationValue(int val) {
		accumulationValue = val;
	}
	
	/**
	 * Removes object that was collided
	 * @param v 			coordinates to remove
	 * @param type 			type of object to remove
	 * @return 				whether the removal was successful
	 */
	public boolean remove(int[] v, String type) {
		if (type.equals("food")) {
			return allFood.remove(v);
		}
		else if (type.equals("trash")) {
			return allTrash.remove(v);
		}
		else {
			return false;
		}
	}
	
	/**
	 * Printing arraylists of numbers
	 * @param lst 		list to print
	 * @return 			string version of list
	 */
	public String printList(ArrayList<int[]> lst) {
		String str = "{" ;
		for (int[] v : lst) {
			str += "{" + v[0] + ", " + v[1] + "} ";
		}
		str += "}";
		return str;
	}
	
	public ArrayList<int[]> getTrash(){
		return allTrash;
	}
	
	public void removeAllTrash() {
		allTrash.clear();
	}
}
