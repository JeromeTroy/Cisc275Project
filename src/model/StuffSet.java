package model;

import java.util.*;

/**
 * @author jerome
 * List to hold all the locations of objects in the ocean
 */

public class StuffSet {
	

	// lists of food and trash
	private ArrayList<int[]> allFood; 		// food
	protected ArrayList<int[]> allTrash; 	// trash
	
	// sizes
	private int foodSize = 10; 				// food size
	private int trashSize = 10;				// trash size
	
	// accumulation of stuff
	private int accumulationTimer = 0;		// timer
	private int accumulationValue = 20;		// signal value
	
	
	// methods
	
	
	// constructors
	/**
	 * Default constructor
	 * Initialize lists to empty
	 */
	public StuffSet() {
		allFood = new ArrayList<int[]>();
		allTrash = new ArrayList<int[]>();
	}
	
	
	/**
	 * Constructor
	 * @param fs 		size of food
	 * @param ts 		size of trash
	 */
	public StuffSet(int fs, int ts) {
		super(); 			// empty initialization
		// set sizes
		setFoodSize(fs); 	
		setTrashSize(ts);
	}
	
	
	// editing objects in list
	
	
	// adder
	/**
	 * Adder of objects
	 * @param coords 		location of object
	 * @param type			trash or food
	 * @return 				boolean if add was allowed
	 */
	public boolean add(int[] coords, String type) {
		boolean goodAdd = true; 		// assume can add
		for (int[] v : getFood()) {
			// for all food, make sure there are no collisions
			if (OurVector.distBetween(coords[0], coords[1], v[0], v[1]) <= Math.pow(2*getFoodSize(),2)) {
				goodAdd = false; 		// on collision, we cannot add
				break;
			}
		}
		// passed test for food
		if (goodAdd) {
			for (int[] v : getTrash()) {
				// for all trash, make sure there are no collisions
				if (OurVector.distBetween(coords[0], coords[1], v[0], v[1]) <= getTrashSize()) {
					goodAdd = false; 		// on collision, do not add
					break;
				}
			}
		}
		// if can add and is food, add to food list
		if ((type == "food") && (goodAdd)){
			getFood().add(coords);
		}
		// if can add and is trash, add to trash list
		else if ((type == "trash") && (goodAdd)){
			getTrash().add(coords);
		}
		// invalid type string
		else {
			goodAdd = false;
		}
		return goodAdd;
	}
	
	
	// remover
	/**
	 * Removes object that was collided
	 * @param v 			coordinates to remove
	 * @param type 			type of object to remove
	 * @return 				whether the removal was successful
	 */
	public boolean remove(int[] v, String type) {
		// removal of food
		if (type.equals("food")) {
			return getFood().remove(v);
		}
		// removal of trash
		else if (type.equals("trash")) {
			return getTrash().remove(v);
		}
		// invalid type
		else {
			return false;
		}
	}
	
	
	// deleting all trash
	public void removeAllTrash() {
		allTrash.clear();
	}
	
	public void clearAll() {
		allTrash.clear();
		allFood.clear();
	}
	
	
	// mover
	/**
	 * Moving all stuff
	 * Stuff moves left to right
	 * @param fishy 		fish that everything is moving relative to
	 */
	public void move(MainCharacter fishy) {
		// get the speed and angle
		int speed = fishy.getSpeed();
		int angle = fishy.getAngle();
		
		// standard case: stuff moves left, right relative to fish
		int deltaX = -8;
		
		// move every object individually
		
		// food moving
		for (int[] coord : getFood()) {
			coord[0] += deltaX; 
		}
		
		// trash moving
		for (int[] coord : getTrash()) {
			coord[0] += deltaX;
		}
		removePassedStuff(fishy);
		// one time step has passed
		accumulationTimer = (accumulationTimer + 1)%accumulationValue;
	}
	
	
	// collision detection
	/**
	 * Determines what if anything the fish has contacted
	 * @param fishy 		the fish character
	 * @return 				what the fish has collided with ("food" or "trash")
	 */
	public String whatCollided(MainCharacter fishy) {
		String what = ""; 		// empty string to determine collision
		
		// trash detection
		for (int[] v : getTrash()) {
			// is it contacting?
			if (fishy.isContacting(v, getTrashSize())) {
				what = "trash";
				remove(v,"trash"); 		// remove contacted object
				break;
			}
		}
		
		// no trash collisions
		if (what.equals("")) {
			// food detection
			for (int[] v: getFood()) {
				// is it contacting
				if (fishy.isContacting(v, getFoodSize())) {
					what = "food";
					remove(v,"food"); 		// remove contacted object
					break;
				}
			}
		}
		// which object was contacted: "food", "trash", ""
		return what;
	}
	
	
	// printing
	
	
	// helper function: printing arrayList of int[]
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
	
	
	// printing
	/** (non-Javadoc)
	 * @see java.util.AbstractCollection#toString()
	 * Prints the collection
	 */
	public String toString() {
		String str = "Food size: " + getFoodSize() + ", food locations: \n" + printList(getFood());
		str += "\nTrash size: " + getTrashSize() + ", trash locations: \n" + printList(getTrash());
		return str;
	}
	
	
	// accumulation detection
	/**
	 * Tells whether it is time to add more trash
	 * @return 		if should accumulate
	 */
	public boolean shouldAccumulate() {
		// if the accumulation timer has hit the flag value, it resets to zero
		return (accumulationTimer == 0);
	}
	
	
	public void removePassedStuff(MainCharacter fishy) {
		for (int[] v : allTrash) {
			if (v[0] < -fishy.getRadius()) {
				remove(v, "trash");
			}
		}
		
		for (int[] v : allFood) {
			if (v[0] < -fishy.getRadius()) {
				remove(v,"food");
			}
		}
	}
	
	// getters

	
	// lists
	
	
	public ArrayList<int[]> getTrash(){
		return allTrash;
	}
	
	
	public ArrayList<int[]> getFood(){
		return allFood;
	}
	
	
	// sizes
	
	
	public int getFoodSize() {
		return foodSize;
	}
	
	
	public int getTrashSize() {
		return trashSize;
	}
	
	
	// setters
	
	
	// sizes
	
	
	public void setTrashSize(int s) {
		trashSize = s;
	}
	
	
	public void setFoodSize(int s) {
		foodSize = s;
	}
	
	
	// accumulation flag
	
	
	public void setAccumulationValue(int val) {
		accumulationValue = val;
	}
	
	
	
	
}
