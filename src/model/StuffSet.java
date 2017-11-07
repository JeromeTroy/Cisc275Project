package model;

import java.util.*;

/**
 * @author jerome
 * List to hold all the locations of objects in the ocean
 */

public class StuffSet extends ArrayList {
	

	private ArrayList<int[]> allFood;
	private ArrayList<int[]> allTrash;
	
	private int foodSize;
	private int trashSize;
	
	
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
		ArrayList<int[]> testArray;
		if (type == "food") {
			testArray = allFood;
		}else if (type == "trash") {
			testArray = allTrash;
		}else {
			testArray = null;
			goodAdd = false;
		}
		if (goodAdd) {
			for (int[] v : testArray) {
				if (OurVector.distBetween(coords[0], coords[1], v[0], v[1]) <= foodSize) {
					goodAdd = false;
					break;
				}
			}
		}
		if (goodAdd) {
			testArray.add(coords);
		}
		return goodAdd;
	}
	
	public String toString() {
		String str = "Food size: " + foodSize + ", food locations: \n" + allFood.toString();
		str += "\nTrash size: " + trashSize + ", trash locations: \n" + allTrash.toString();
		return str;
	}
}
